package duoc.portafolio.feriavirtual.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import duoc.portafolio.feriavirtual.commons.SessionAuxiliar;
import duoc.portafolio.feriavirtual.models.Usuario;
import duoc.portafolio.feriavirtual.models._EstructuraMenu;
import duoc.portafolio.feriavirtual.models._EstructuraMenu.MenuItem;
import duoc.portafolio.feriavirtual.service.UsuarioService;

@Controller
public class LoginController {
	
	
	@Autowired
	private UsuarioService usuarioServicio;
	
	@PostMapping("/login")
	public String login(@RequestParam("user") String user, @RequestParam("pass") String pass, HttpServletRequest request) {
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
			  String tipoUsuario = userFind.getPerfil().getTipo();
			  String nombre = userFind.getNombre();
			  request.getSession().setAttribute("tipoUsuario", tipoUsuario);
			  request.getSession().setAttribute("nombre", nombre);
			  request.getSession().setAttribute("menu", GenerarPerfil(tipoUsuario));
			  request.getSession().setAttribute("usuario", userFind);
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
		menuVenta.getMenus().add(new MenuItem("/ventas/crear/0", "Agregar Venta"));
		
		_EstructuraMenu menuSubasta = new _EstructuraMenu("Subastas");
		menuSubasta.getMenus().add(new MenuItem("/subastas", "Subastas"));
		menuSubasta.getMenus().add(new MenuItem("/subastas/ventas", "Agregar Subastas"));
		
		_EstructuraMenu menuOferta = new _EstructuraMenu("Ofertas");
		menuOferta.getMenus().add(new MenuItem("/ofertas", "Ofertas"));
		menuOferta.getMenus().add(new MenuItem("/ofertas/ventas", "Agregar Ofertas"));
		menuSubasta.getMenus().add(new MenuItem("/subastas/crear/0", "Agregar Subastas"));
				
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
}
