package duoc.portafolio.feriavirtual.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import duoc.portafolio.feriavirtual.commons.SessionAuxiliar;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home(HttpSession session) {
		SessionAuxiliar sa = new SessionAuxiliar();
		String r = sa.IsSessioned("home/home", session);
		return r;
	}
}
