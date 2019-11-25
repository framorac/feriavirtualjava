package duoc.portafolio.feriavirtual.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UsuarioController {
	
	@GetMapping("/usuarios")
	public String index() {
		return "usuarios/usuarios";
	}
}