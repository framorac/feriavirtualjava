package duoc.portafolio.feriavirtual.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.istack.Nullable;

import duoc.portafolio.feriavirtual.commons.SessionAuxiliar;
import duoc.portafolio.feriavirtual.models.*;
import duoc.portafolio.feriavirtual.service.DetalleOfertaService;
import duoc.portafolio.feriavirtual.service.DetalleVentaService;
import duoc.portafolio.feriavirtual.service.HistoricoEstadoVentaService;
import duoc.portafolio.feriavirtual.service.OfertaService;
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
	private DetalleOfertaService detalleOfertaService;
	
	@Autowired 
	private HistoricoEstadoVentaService hevServicio;
	
	@RequestMapping(value = {"/ofertas"})
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
		List<DetalleOferta> misDetalles = detalleOfertaService.getAll();
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
				subtotal += dof.getPrecio()*dof.getCantidad();
			}
			boolean isCertificado = false;
			boolean isEnvasado = false;
			if(o.getIsCertificado().equals("1")) {
				isCertificado = true;
			}
			if(o.getIsEnvasado().equals("1")) {
				isEnvasado = true;
			}
			ofertasConTotales.add(new Objeto(o, subtotal, isCertificado, isEnvasado));
		}
		
		modelo.addAttribute("misOfertas", ofertasConTotales);
		Object o = session.getAttribute("idOfertaDetallada");
		if(o != null) {
			int idOferta = (int)o;
			List<DetalleOferta> expandir = misDetalles.stream().filter(x -> x.getOferta().getIdOferta() == idOferta).collect(Collectors.toList());
			modelo.addAttribute("ofertaExpandida", expandir);
		}
		else {
			modelo.addAttribute("ofertaExpandida", null);
		}
		return "ofertas/ofertas";
	}
	
	@GetMapping("/ofertas/{idOferta}")
	public String OfertaDetallada(HttpSession session, @PathVariable("idOferta") Integer idOferta, HttpServletRequest request) {
		request.getSession().setAttribute("idOfertaDetallada", idOferta);
		return "redirect:/ofertas";
	}
	
	@GetMapping("/ofertas/ventas")
	public String OfertasVentas(Model modelo, HttpSession session, HttpServletRequest request) {
		SessionAuxiliar sa = new SessionAuxiliar();
		if(!sa.IsSessioned(session, request)) {
			return "login/login";
		}
		List<Venta> ventas = ventaService.getAll();
		List<HistoricoEstadoVenta> hevs = hevServicio.getAll();
		int miId = ((Usuario) session.getAttribute("usuario")).getIdUsuario();
		// ventas activas
		hevs = hevs.stream().filter(x -> x.getActivo() == '1' && x.getTipo().getTipo().equals("abierto"))
				.collect(Collectors.toList());
		// Traerme el detalle de la venta para ofertar
		List<DetalleVenta> detallesVenta = detalleVentaService.getAll();
		
		List<_EstructuraDinamica> ventasDetalladas = new ArrayList<_EstructuraDinamica>(); 
		//List<Objeto> ventasDetalladas = new ArrayList<Objeto>();
		for(HistoricoEstadoVenta hev : hevs) {
			List<DetalleVenta> detallesFiltrados = detallesVenta.stream().filter(x -> x.getVenta() == hev.getVenta()).collect(Collectors.toList());
			ventasDetalladas.add(new _EstructuraDinamica(null, null, hev.getVenta(), detallesFiltrados));
		}
		modelo.addAttribute("ventasDetalladas", ventasDetalladas);
		return "/ofertas/agregar";
	}
	
	@GetMapping("/ofertas/formularioAgregar/{id}")
	public String FormularioAgregar(@PathVariable("id") Integer id, Model modelo, HttpServletRequest request, HttpSession session) {
		SessionAuxiliar sa = new SessionAuxiliar();
		if(!sa.IsSessioned(session, request)) {
			return "login/login";
		}
		
		Venta ventaSeleccionada = ventaService.get(id);
		request.getSession().setAttribute("ventaSeleccionada", ventaSeleccionada);
		modelo.addAttribute("ventaSeleccionada", ventaSeleccionada);
		modelo.addAttribute("idVenta", id);
		modelo.addAttribute("ahora", LocalDate.now());
		
		List<DetalleVenta> detallesVenta =  detalleVentaService.getAll().stream().filter(x -> x.getVenta().getIdVenta() == id).collect(Collectors.toList());
		List<DetalleOferta> detallesOferta = new ArrayList<DetalleOferta>();
		for(DetalleVenta dv : detallesVenta) {
			DetalleOferta doferta = new DetalleOferta();
			doferta.setProducto(dv.getProducto());
			detallesOferta.add(doferta);
		}
		_EstructuraDinamica ed = new _EstructuraDinamica(null, detallesOferta, null, null);
		modelo.addAttribute("ed", ed);
		modelo.addAttribute("detallesVenta", detallesVenta);
		return "/ofertas/formularioAgregar";
	}
	

	@PostMapping("/ofertas/ingresar")
	public String OfertaIngresar(@ModelAttribute _EstructuraDinamica ed, String isCertificado, String isEnvasado , HttpSession session) {	
		Oferta nuevaOferta = new Oferta();
		nuevaOferta.setFechaInicio(Date.valueOf(LocalDate.now()));
		nuevaOferta.setIsCertificado(isCertificado);
		nuevaOferta.setIsEnvasado(isEnvasado);
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		nuevaOferta.setUsuario(usuario);
		Venta venta = (Venta)session.getAttribute("ventaSeleccionada");
		nuevaOferta.setVenta(venta);
		nuevaOferta.setIsGanador("0");
		Oferta ofertaGrabado = ofertaService.save(nuevaOferta);
		List<DetalleOferta> detalles = ed.getDetalles();
		for(DetalleOferta dOferta : detalles) {
			dOferta.setOferta(ofertaGrabado);
			detalleOfertaService.save(dOferta);
		}
		
		return "redirect:/ofertas";
	}
}