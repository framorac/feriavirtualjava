package duoc.portafolio.feriavirtual.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductoController {
	
	@GetMapping("/productos")
	public String producto() {
		return "productos/productos";
	}
	
	@GetMapping("/productos/agregar")
	public String agregar() {
		return "productos/agregar";
	}
}