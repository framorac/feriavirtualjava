package duoc.portafolio.feriavirtual.controllers;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import duoc.portafolio.feriavirtual.models.*;
import duoc.portafolio.feriavirtual.service.DetalleOfertaService;
import duoc.portafolio.feriavirtual.service.DetalleVentaService;
import duoc.portafolio.feriavirtual.service.HistoricoEstadoVentaService;
import duoc.portafolio.feriavirtual.service.OfertaService;
import duoc.portafolio.feriavirtual.service.SubastaService;
import duoc.portafolio.feriavirtual.service.VentaService;

@Controller
public class OfertaController {
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private VentaService ventaService;
	
	@Autowired 
	private DetalleVentaService detalleVentaService;
	
	@Autowired 
	private HistoricoEstadoVentaService hevServicio;
	 
	@GetMapping("/ofertas")
	public String MisOfertas(HttpSession session, Model modelo) {
		class Objeto{
			public Oferta oferta;
			public int total;
			public boolean isCertificado;
			public boolean isEnvasado;
			public Objeto(Oferta oferta, int total, boolean isCertificado, boolean isEnvasado) {
				super();
				this.oferta = oferta;
				this.total = total;
				this.isCertificado = isCertificado;
				this.isEnvasado = isEnvasado;
			}
			
		}
		List<Objeto> ofertasConTotales = new ArrayList<Objeto>();
		int miId = ((Usuario)session.getAttribute("usuario")).getIdUsuario();
		List<Oferta> misOfertas = new ArrayList<Oferta>();
		List<DetalleOferta> misDetalles = new ArrayList<DetalleOferta>();
		if(miId != 1) {
			misOfertas = ofertaService.getAll().stream().filter(x -> x.getUsuario().getIdUsuario() == miId).collect(Collectors.toList());
		}
		else {
			misOfertas = ofertaService.getAll();
		}
		
		for(Oferta o : misOfertas) {
			List<DetalleOferta> misDetallesFiltrados = misDetalles.stream().filter(x -> x.getOferta().getIdOferta() == o.getIdOferta()).collect(Collectors.toList());
			int subtotal = 0;
			for(DetalleOferta dof: misDetallesFiltrados) {
				subtotal += dof.getPrecio();
			}
			boolean isCertificado = false;
			boolean isEnvasado = false;
			if(o.getIsCertificado() == "1") {
				isCertificado = true;
			}
			if(o.getIsEnvasado() == "1") {
				isEnvasado = true;
			}
			ofertasConTotales.add(new Objeto(o, subtotal, isCertificado, isEnvasado));
		}
		
		modelo.addAttribute("misOfertas", ofertasConTotales);
		return "ofertas/ofertas";
	}
	
	@GetMapping("/ofertas/ventas")
	public String OfertasVentas(Model modelo, HttpSession session) {
		List<Venta> ventas = ventaService.getAll();
		List<HistoricoEstadoVenta> hevs = hevServicio.getAll();
		int miId = ((Usuario) session.getAttribute("usuario")).getIdUsuario();
		// ventas activas
		hevs = hevs.stream().filter(x -> x.getActivo() == '1' && x.getTipo().getTipo().equals("abierto"))
				.collect(Collectors.toList());
		// Traerme el detalle de la venta para ofertar
		List<DetalleVenta> detallesVenta = detalleVentaService.getAll();
		
		class Objeto{
			public Venta venta;
			public List<DetalleVenta> detalleVenta;
			public Objeto(Venta venta, List<DetalleVenta> detalleVenta) {
				super();
				this.venta = venta;
				this.detalleVenta = detalleVenta;
			}
		}
		List<Objeto> ventasDetalladas = new ArrayList<Objeto>();
		for(HistoricoEstadoVenta hev : hevs) {
			List<DetalleVenta> detallesFiltrados = detallesVenta.stream().filter(x -> x.getVenta() == hev.getVenta()).collect(Collectors.toList());
			ventasDetalladas.add(new Objeto(hev.getVenta(), detallesFiltrados));
		}
		modelo.addAttribute("ventasDetalladas", ventasDetalladas);
		return "/ofertas/agregar";
	}
	
	@GetMapping("/ofertas/formularioAgregar/{id}")
	public String FormularioAgregar(@PathVariable("id") Integer id, Model modelo, HttpServletRequest request) {
		Venta ventaSeleccionada = ventaService.get(id);
		request.getSession().setAttribute("ventaSeleccionada", ventaSeleccionada);
		modelo.addAttribute("ventaSeleccionada", ventaSeleccionada);
		modelo.addAttribute("idVenta", id);
		modelo.addAttribute("ahora", LocalDate.now());
		
		class DynamicObject{
			public Oferta oferta = new Oferta();
			public List<DetalleOferta> detalles = new ArrayList<DetalleOferta>();
			public Oferta getOferta() {
				return oferta;
			}
			public void setOferta(Oferta oferta) {
				this.oferta = oferta;
			}
			public List<DetalleOferta> getDetalles() {
				return detalles;
			}
			public void setDetalles(List<DetalleOferta> detalles) {
				this.detalles = detalles;
			}
			
		}
		DynamicObject objeto = new DynamicObject();
		
		modelo.addAttribute("ofertaConDetalles", objeto);
		return "/ofertas/formularioAgregar";
	}
	

	@PostMapping("/ofertas/ingresar")
	public String OfertaIngresar(Object o, HttpSession session) {
		
		return "/subastas/subastas";
	}

}