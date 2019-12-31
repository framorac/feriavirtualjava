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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import duoc.portafolio.feriavirtual.models.DetalleOferta;
import duoc.portafolio.feriavirtual.models.DetalleVenta;
import duoc.portafolio.feriavirtual.models.HistoricoEstadoVenta;
import duoc.portafolio.feriavirtual.models.Producto;
import duoc.portafolio.feriavirtual.models.Subasta;
import duoc.portafolio.feriavirtual.models.TipoEstado;
import duoc.portafolio.feriavirtual.models.TipoVenta;
import duoc.portafolio.feriavirtual.models.Usuario;
import duoc.portafolio.feriavirtual.models.Venta;
import duoc.portafolio.feriavirtual.models.Oferta;
import duoc.portafolio.feriavirtual.models._EstructuraDinamica;
import duoc.portafolio.feriavirtual.service.DetalleOfertaService;
import duoc.portafolio.feriavirtual.service.DetalleVentaService;
import duoc.portafolio.feriavirtual.service.HistoricoEstadoVentaService;
import duoc.portafolio.feriavirtual.service.OfertaService;
import duoc.portafolio.feriavirtual.service.ProductoService;
import duoc.portafolio.feriavirtual.service.SubastaService;
import duoc.portafolio.feriavirtual.service.TipoEstadoService;
import duoc.portafolio.feriavirtual.service.TipoVentaService;
import duoc.portafolio.feriavirtual.service.UsuarioService;
import duoc.portafolio.feriavirtual.service.VentaService;

@Controller
public class VentaController {
	
	@Autowired
	private VentaService ventaServicio;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TipoVentaService tipoVentaService;
	
	@Autowired
	private ProductoService productoService;
	
	@Autowired
	private HistoricoEstadoVentaService hevService;
	
	@Autowired
	private DetalleVentaService detalleVentaService;
	
	@Autowired
	private TipoEstadoService tipoEstadoService;
	
	@Autowired
	private OfertaService ofertaService;
	
	@Autowired
	private DetalleOfertaService detalleOfertaService;
	
	@Autowired
	private SubastaService subastaService;
	
	@RequestMapping("/ventas")
	public String index(Model modelo, HttpSession session){
		class Objeto{
			public String estado;
			public Venta venta;
			public boolean isRecepcionado; 
			public Objeto(String estado, Venta venta, boolean isRecepcionado) {
				super();
				this.estado = estado;
				this.venta = venta;
				this.isRecepcionado = isRecepcionado;
			}
		}
		
		List<Venta> ventas = ventaServicio.getAll();
		List<HistoricoEstadoVenta> hevs = hevService.getAll();
		int miId = ((Usuario)session.getAttribute("usuario")).getIdUsuario();
		
		List<Venta> misVentas = new ArrayList<Venta>();
		List<DetalleVenta> misDetalles = detalleVentaService.getAll();
		List<Objeto> ventasConEstado = new ArrayList<Objeto>();
		if(miId != 1) {
			misVentas = ventaServicio.getAll().stream().filter(x -> x.getUsuario().getIdUsuario() == miId).collect(Collectors.toList());
		}
		else {
			misVentas = ventaServicio.getAll();
		}
		
		for(Venta v : misVentas) {
			List<HistoricoEstadoVenta> hev = hevs.stream().filter(x -> x.getVenta().getIdVenta() == v.getIdVenta() && x.getActivo() == '1').collect(Collectors.toList());
			String estado = "NONE";
			if(hev.size() > 0) {
				estado = hev.get(0).getTipo().getTipo();
				boolean isRecepcionado = estado.equals("en camino") ? true: false;
				Objeto nuevoObjeto = new Objeto(estado, v, isRecepcionado);
				ventasConEstado.add(nuevoObjeto);
			}	
		}
		
		modelo.addAttribute("ventasConEstado", ventasConEstado);
		Object o = session.getAttribute("idVentaDetallada");
		if(o != null) {
			int idVenta = (int)o;
			List<DetalleVenta> expandir = misDetalles.stream().filter(x -> x.getVenta().getIdVenta() == idVenta).collect(Collectors.toList());
			modelo.addAttribute("ventaExpandida", expandir);
		}
		else {
			modelo.addAttribute("ventaExpandida", null);
		}
		
		// obtenemos el model que proviene de cerrar
		String mensaje = (String)session.getAttribute("mensaje");
		if(mensaje != null && !mensaje.isEmpty()) {
			modelo.addAttribute("mensaje", mensaje);
			session.removeAttribute("mensaje");
		}
		
		return "ventas/ventas";
	}
	
	@GetMapping("/ventas/detallar")
	public String agregar(Model modelo, HttpSession session) {
		List<DetalleVenta> detalleVenta = new ArrayList<DetalleVenta>();
		_EstructuraDinamica ed = new _EstructuraDinamica(null, null, null, detalleVenta);
		List<Producto> productos = productoService.getAll();
		while(detalleVenta.size() < 20) detalleVenta.add(new DetalleVenta());
		modelo.addAttribute("detalles", ed);
		modelo.addAttribute("productos", productos);
		return "/ventas/agregar";
	}
	
	@PostMapping("/ventas/ingresar")
	public String VentaIngresar(@ModelAttribute _EstructuraDinamica ed, HttpSession session) {	
		// creamos la venta y la guardamos
		Venta nuevaVenta = new Venta();
		nuevaVenta.setFecha(Date.valueOf(LocalDate.now()));
		TipoVenta tp = tipoVentaService.get(2);
		nuevaVenta.setTipoVenta(tp);
		Usuario u = (Usuario)session.getAttribute("usuario");
		nuevaVenta.setUsuario(u);
		Venta ventaIngresada = ventaServicio.save(nuevaVenta);
		//
		HistoricoEstadoVenta hev = new HistoricoEstadoVenta();
		hev.setActivo('1');
		hev.setFecha(Date.valueOf(LocalDate.now()));
		hev.setVenta(ventaIngresada);
		TipoEstado te = tipoEstadoService.getAll().stream().filter(x -> x.getIdTipoEstado() == 6).collect(Collectors.toList()).get(0);
		hev.setTipo(te);
		hevService.save(hev);
		//
		List<DetalleVenta> detallesVenta = ed.detalleVenta.stream().filter(x -> x.getProducto() != null).collect(Collectors.toList());
		for(DetalleVenta dv : detallesVenta) {
			dv.setVenta(ventaIngresada);
			detalleVentaService.save(dv);
		}
		
		return "redirect:/ventas";
	}
	
	public String borrar(@PathVariable Integer id, Model modelo) {
		ventaServicio.delete(id);
		
		return "redirect:/ventas";
	}
	
	@PostMapping("/ventas/finalizar")
	public String Finalizar(@RequestParam("idVenta") int idVenta, Model modelo) {
		Venta venta = ventaServicio.get(idVenta);
		modelo.addAttribute("venta", venta);
		List<DetalleVenta> detallesVenta = detalleVentaService.getAll().stream().filter(x -> x.getVenta().getIdVenta() == idVenta).collect(Collectors.toList());
		modelo.addAttribute("detallesVenta", detallesVenta);
		List<Oferta> ofertasGanadoras = ofertaService.getAll().stream().filter(x -> x.getVenta().getIdVenta() == idVenta && x.getIsGanador().equals("1")).collect(Collectors.toList());
		Oferta ofertaGanadora = ofertasGanadoras.size() > 0 ? ofertasGanadoras.get(0) : null;
		if(ofertaGanadora == null) {
			modelo.addAttribute("msgFinalizar", "No hay ofertas ganadora");
		}
		else {
			List<DetalleOferta> detallesOferta = detalleOfertaService.getAll().stream().filter(x -> x.getOferta().getIdOferta() == ofertaGanadora.getIdOferta()).collect(Collectors.toList());
			List<Subasta> subastas = subastaService.getAll().stream().filter(x -> x.getOferta().getIdOferta() == ofertaGanadora.getIdOferta() && x.getIsGanador() != null && x.getIsGanador().equals("1")).collect(Collectors.toList());
			Subasta subastaGanadora = subastas.size() > 0 ? subastas.get(0) : null;
			if(subastaGanadora == null) {
				modelo.addAttribute("msgFinalizar", "No hay subasta ganadora");
			}
			else {
				// guardamos la info en el modelo
				modelo.addAttribute("ofertaGanadora", ofertaGanadora);
				modelo.addAttribute("detallesOferta", detallesOferta);
				modelo.addAttribute("subasta", subastaGanadora);
			}
		}
		
		return "/ventas/finalizar";
	}
	
	@PostMapping("/ventas/cerrar")
	public String Cerrar(@RequestParam("idVenta") int idVenta, @RequestParam("aceptado") int aceptado,  Model modelo, HttpServletRequest request, HttpSession session) {
		String mensaje = "";
		HistoricoEstadoVenta newHistoricoVenta = new HistoricoEstadoVenta();
		Venta v = ventaServicio.get(idVenta);
		HistoricoEstadoVenta before = hevService.getAll().stream().filter(x -> x.getVenta().getIdVenta() == idVenta && x.getActivo() == '1').collect(Collectors.toList()).get(0);
		before.setActivo('0');
		hevService.save(before);
		newHistoricoVenta.setVenta(v);
		newHistoricoVenta.setActivo('1');
		newHistoricoVenta.setFecha(Date.valueOf(LocalDate.now()));
		TipoEstado te = new TipoEstado();
		if(aceptado == 1) {
			te = tipoEstadoService.getAll().stream().filter(x -> x.getTipo().equals("recepcionado")).collect(Collectors.toList()).get(0);
			mensaje = "Muchas gracias por su preferencia, el administrador se contactará con usted para proceder con el pago";
		}
		else {
			te = tipoEstadoService.getAll().stream().filter(x -> x.getTipo().equals("cancelada")).collect(Collectors.toList()).get(0);
			mensaje= "Lamentamos el inconveniente, el admnistrador se contactará con usted para proceder con la devolución de los productos";
		}
		newHistoricoVenta.setTipo(te);
		hevService.save(newHistoricoVenta);
		request.getSession().setAttribute("mensaje", mensaje);
		return "redirect:/ventas";
	}
	
}