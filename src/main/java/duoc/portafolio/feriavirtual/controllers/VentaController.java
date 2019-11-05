package duoc.portafolio.feriavirtual.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VentaController {
	
	@GetMapping("/ventas")
	public String ventas() {
		return "ventas/ventas";
	}
}