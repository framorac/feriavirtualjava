package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import duoc.portafolio.feriavirtual.service.DetalleOfertaService;
import duoc.portafolio.feriavirtual.service.HistoricoEstadoVentaService;
import duoc.portafolio.feriavirtual.service.OfertaService;
import duoc.portafolio.feriavirtual.service.SubastaService;
import duoc.portafolio.feriavirtual.service.TipoTransporteService;
import duoc.portafolio.feriavirtual.service.VentaService;
import oracle.sql.DATE;
import duoc.portafolio.feriavirtual.models.*;

@Controller
public class SubastaController {
	
	@Autowired
	private VentaService ventaServicio;
	
	@Autowired
	private HistoricoEstadoVentaService hevServicio;
	
	@Autowired
	private DetalleOfertaService detalleOfertaServicio;
	
	@Autowired
	private TipoTransporteService tipoTransporteService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private SubastaService subastaService;
	
	@GetMapping("/subastas")
	public String MisSubastas(HttpSession session, Model modelo) {
		int miId = ((Usuario)session.getAttribute("usuario")).getIdUsuario();
		List<Subasta> misSubastas = new ArrayList<Subasta>();
		if(miId != 1) {
			misSubastas = subastaService.getAll().stream().filter(x -> x.getUsuario().getIdUsuario() == miId).collect(Collectors.toList());
		}
		else {
			misSubastas = subastaService.getAll();
		}
		
		modelo.addAttribute("misSubastas", misSubastas);
		return "subastas/subastas";
	}
	
	@GetMapping("/subastas/formularioAgregar/{id}/{cantidad}")
	public String FormularioAgregar(@PathVariable("id") Integer id, @PathVariable("cantidad") Integer cantidad, Model modelo, HttpServletRequest request) {
		Oferta ofertaSeleccionada = ofertaService.get(id);
		request.getSession().setAttribute("oferta", ofertaSeleccionada);
		modelo.addAttribute("oferta", ofertaSeleccionada);
		modelo.addAttribute("idOferta", id);
		int cantidadMinima = (int) (cantidad*1.1);
		modelo.addAttribute("cantidadMinima", cantidadMinima);
		modelo.addAttribute("ahora", LocalDate.now());
		modelo.addAttribute("tipoTransportes", tipoTransporteService.getAll());
		modelo.addAttribute("subasta", new Subasta());
		return "/subastas/formularioAgregar";
	}
	
	@PostMapping("/subastas/ingresar")
	public String SubastaIngresar(Subasta subasta, HttpSession session) {
		subasta.setFechaInicio(Date.valueOf(LocalDate.now()));
		subasta.setUsuario((Usuario)session.getAttribute("usuario"));
		subasta.setOferta((Oferta)session.getAttribute("oferta"));
		subastaService.save(subasta);
		return "/subastas/subastas";
	}
	
	@GetMapping("/subastas/ventas")
	public String SubastasVentas(Model modelo, HttpSession session) {
		class Objeto
		{
			public Venta venta;
			public int cantidad;
			public int idOferta;
			public boolean ventaSubastada;
			public String color;
			public Objeto(Venta venta, int cantidad, int idOferta, boolean ventaSubastada, String color) {
				super();
				this.venta = venta;
				this.cantidad = cantidad;
				this.idOferta = idOferta;
				this.ventaSubastada = ventaSubastada;
				this.color = color;
			}
		}
		  List<Venta> ventas = ventaServicio.getAll();
		  List<HistoricoEstadoVenta> hevs = hevServicio.getAll(); 
		  List<Objeto> info = new ArrayList<Objeto>();
		  List<Subasta> subastas = subastaService.getAll();
		  int miId = ((Usuario)session.getAttribute("usuario")).getIdUsuario();
		  List<Subasta> misSubastas = subastas.stream().filter(x -> x.getUsuario().getIdUsuario() == miId).collect(Collectors.toList());
		  //ventas activas 
		  hevs = hevs.stream().filter(x -> x.getActivo() == '1' && x.getTipo().getTipo().equals("en subasta")).collect(Collectors.toList());
		  // Traerme el detalle de la venta para subastar
		  List<DetalleOferta> detallesOferta = detalleOfertaServicio.getAll();
		  //filtramos los detalles
		  List<DetalleOferta> aux = new ArrayList<DetalleOferta>();
		  for(HistoricoEstadoVenta h : hevs) {
			 // obtenemos los detalles de la oferta asociada a la venta
			  aux = detallesOferta.stream().filter(x -> x.getOferta().getVenta().getIdVenta() == h.getVenta().getIdVenta()).collect(Collectors.toList());
			  int totalPeso = 0;
			  for(DetalleOferta detOf : aux) {
				 totalPeso += detOf.getCantidad();
			  }
			  if(aux.size() > 0) {
				  boolean isVentaSubastada = false;
				  String color = "white";
				  // revisar si dentro de las ventas existe una subasta hecha por mi cuenta
				  for(Subasta s : misSubastas) {
					  if(s.getOferta().getVenta().equals(h.getVenta())) {
						  isVentaSubastada = true;
						  color = "red";
						  break;
					  }
				  }
				  info.add(new Objeto(h.getVenta(), totalPeso, aux.get(0).getOferta().getIdOferta(), isVentaSubastada, color));  
			  }
		  }
		   
		  modelo.addAttribute("listaVentasActivas", info);
		 
		return "/subastas/agregar";
	}
}