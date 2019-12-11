package duoc.portafolio.feriavirtual.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import duoc.portafolio.feriavirtual.commons.SessionAuxiliar;
import duoc.portafolio.feriavirtual.models.Contrato;
import duoc.portafolio.feriavirtual.models.DetalleOferta;
import duoc.portafolio.feriavirtual.models.DetalleVenta;
import duoc.portafolio.feriavirtual.models.Oferta;
import duoc.portafolio.feriavirtual.models.Usuario;
import duoc.portafolio.feriavirtual.models._EstructuraMenu;
import duoc.portafolio.feriavirtual.models._EstructuraMenu.MenuItem;
import duoc.portafolio.feriavirtual.service.ContratoService;
import duoc.portafolio.feriavirtual.service.DetalleOfertaService;
import duoc.portafolio.feriavirtual.service.DetalleVentaService;
import duoc.portafolio.feriavirtual.service.OfertaService;
import duoc.portafolio.feriavirtual.service.UsuarioService;
import oracle.net.aso.x;

@Controller
public class LoginController {
	
	
	@Autowired
	private UsuarioService usuarioServicio;
	@Autowired
	private ContratoService contratoServicio;
	@Autowired
	private OfertaService ofertaServicio;
	@Autowired
	private DetalleOfertaService detalleOfertaServicio;
	@Autowired
	private DetalleVentaService detalleVentaServicio;
	
	@PostMapping("/login")
	public String login(@RequestParam("user") String user, @RequestParam("pass") String pass, HttpServletRequest request, Model modelo) {
		  String nombreSession = (String)request.getSession().getAttribute("nombre");
		  String tipoUsuarioSession = (String)request.getSession().getAttribute("tipoUsuario");
		  if(nombreSession != null && tipoUsuarioSession != null) { 
			  return "/home/home";
			  
		  }
		  
		  List<Usuario> usuarios = usuarioServicio.getAll();
		  Usuario userFind = new Usuario();
		  for(Usuario u : usuarios) {
			  if(u.getUsername().equals(user) && u.getPassword().equals(pass)) {
				  userFind = u;  
				  break;
			  }
			  else {
				  userFind = null;
			  }
			  
		  }
		  if(userFind != null) {
			  int idUsuario = userFind.getIdUsuario();
			  Date ahora = Date.valueOf(LocalDate.now());
			  List<Contrato> contratos = contratoServicio.getAll().stream().filter(x -> x.getUsuario().getIdUsuario() == idUsuario && x.getFechaTermino().after(ahora) ).collect(Collectors.toList());
			  if(idUsuario == 1 || (contratos != null && contratos.size() != 0 && contratos.get(0) != null)) {
				  String tipoUsuario = userFind.getPerfil().getTipo();
				  String nombre = userFind.getNombre();
				  request.getSession().setAttribute("tipoUsuario", tipoUsuario);
				  request.getSession().setAttribute("nombre", nombre);
				  request.getSession().setAttribute("menu", GenerarPerfil(tipoUsuario));
				  request.getSession().setAttribute("usuario", userFind);
				  request.getSession().setAttribute("contrato", contratos.get(0));
				  request.getSession().setAttribute("valores", AgregarGrafico(idUsuario));
				  request.getSession().setAttribute("valores2", AgregarGrafico2());
			  }
			  else {
				  String msg = "Contrato inválido o no vigente";
				  modelo.addAttribute("warning", msg);
				  userFind = null;
			  }
			 
		  }
		  else {
			  String msg = "Usuario y/o Contraseña no válidos";
			  modelo.addAttribute("warning", msg);
		  }
		  return userFind != null ? "/home/home" : "/login/login";
	}
	
	// En caso de que el usuario quiera ingresar de la url
	// lo mandamos al home en caso de que exista sesion valida
	@GetMapping("/login")
	public String login(HttpSession session) {
		SessionAuxiliar sa = new SessionAuxiliar();
		String r = sa.IsSessioned("home/home", session);
		return r;
	}
	
	@GetMapping("/salir")
	public String salir(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/login/login";
	}
	
	private List<_EstructuraMenu> GenerarPerfil(String tipoUsuario) {
		List<_EstructuraMenu> estructuraMenu = new ArrayList<_EstructuraMenu>();
		// menu comun
		
		_EstructuraMenu menuVenta = new _EstructuraMenu("Ventas");
		menuVenta.getMenus().add(new MenuItem("/ventas", "Ventas"));
		menuVenta.getMenus().add(new MenuItem("/ventas/detallar", "Agregar Venta"));
		
		_EstructuraMenu menuSubasta = new _EstructuraMenu("Subastas");
		menuSubasta.getMenus().add(new MenuItem("/subastas", "Subastas"));
		menuSubasta.getMenus().add(new MenuItem("/subastas/ventas", "Agregar Subastas"));
		
		_EstructuraMenu menuOferta = new _EstructuraMenu("Ofertas");
		menuOferta.getMenus().add(new MenuItem("/ofertas", "Ofertas"));
		menuOferta.getMenus().add(new MenuItem("/ofertas/ventas", "Agregar Ofertas"));
				
		_EstructuraMenu menuProducto = new _EstructuraMenu("Productos");
		menuProducto.getMenus().add(new MenuItem("/productos", "Productos"));
		menuProducto.getMenus().add(new MenuItem("/productos/crear/0", "Agregar Productos"));
		
		_EstructuraMenu menuReporte = new _EstructuraMenu("Reportes");
		menuReporte.getMenus().add(new MenuItem("/reportes", "Reportes"));
		
		switch(tipoUsuario) {
		case "admin":
			estructuraMenu.add(menuVenta);
			estructuraMenu.add(menuSubasta);
			estructuraMenu.add(menuOferta);
			estructuraMenu.add(menuProducto);
			estructuraMenu.add(menuReporte);
			break;
		case "cliente externo":
			estructuraMenu.add(menuVenta);
			break;
		case "transporte":
			estructuraMenu.add(menuSubasta);
			break;
		case "productor":
			estructuraMenu.add(menuOferta);
			estructuraMenu.add(menuProducto);
			break;
		case "consultor":
			estructuraMenu.add(menuReporte);
			break;
		}
		
		return  estructuraMenu;
	}
	
	private Object AgregarGrafico(int idUsuario) {
		List<Oferta> ofertas = ofertaServicio.getAll().stream().filter(x -> x.getUsuario().getIdUsuario() == idUsuario).collect(Collectors.toList());
		List<DetalleOferta> detallesOferta = detalleOfertaServicio.getAll().stream().collect(Collectors.toList());
		class Objeto{
			public int[] valores = new int[2];
			public Objeto(int[] valores) {
				super();
				this.valores = valores;
			}
		}
		List<Objeto> objetos = new ArrayList<Objeto>();
		for(Oferta oferta : ofertas) {
			List<DetalleOferta> detAux = detallesOferta.stream().filter(x -> x.getOferta().getIdOferta() == oferta.getIdOferta()).collect(Collectors.toList());
			int total = 0;
			for(DetalleOferta d : detAux) {
				total += d.getCantidad();
			}
			int[] n = new int[] {oferta.getIdOferta(), total};
			objetos.add(new Objeto(n));
		}
		return objetos;
	}
	
	private Object AgregarGrafico2() {
		List<DetalleVenta> detalles = detalleVentaServicio.getAll();
		List<DetalleVenta> detallesFrutas = detalles.stream().filter(x -> x.getProducto().getTipoProducto().getTipo().equals("Frutas")).collect(Collectors.toList());
		List<DetalleVenta> detallesVerduras = detalles.stream().filter(x -> x.getProducto().getTipoProducto().getTipo().equals("Verduras")).collect(Collectors.toList());
		List<DetalleVenta> detallesSecos = detalles.stream().filter(x -> x.getProducto().getTipoProducto().getTipo().equals("Frutos secos")).collect(Collectors.toList());
		class Objeto{
			public int valor;
			public String producto;
			public Objeto(int valor, String producto) {
				super();
				this.valor = valor;
				this.producto = producto;
			}
		}
		List<Objeto> objetos = new ArrayList<Objeto>();
		int totalFrutas = 0;
		int totalVerduras = 0;
		int totalSecos = 0;
		for(DetalleVenta d : detallesFrutas) {
			totalFrutas += d.getCantidad();
		}
		for(DetalleVenta d : detallesVerduras) {
			totalVerduras += d.getCantidad();
		}
		for(DetalleVenta d : detallesSecos) {
			totalSecos += d.getCantidad();
		}
		objetos.add(new Objeto(totalFrutas, "Frutas"));
		objetos.add(new Objeto(totalVerduras, "Verduras"));
		objetos.add(new Objeto(totalSecos, "Frutos secos"));
		
		return objetos;
	}
}
