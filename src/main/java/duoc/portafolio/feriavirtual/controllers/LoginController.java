package duoc.portafolio.feriavirtual.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import duoc.portafolio.feriavirtual.commons.SessionAuxiliar;
import duoc.portafolio.feriavirtual.models.Usuario;
import duoc.portafolio.feriavirtual.service.ProductoService;
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
}
