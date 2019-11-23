package duoc.portafolio.feriavirtual.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class ProductoController {
	
	@GetMapping("/productos")
	public String producto() {
		return "productos/producto";
	}
	
	@GetMapping("/productos/agregar")
	public String agregar() {
		return "productos/agregar";
	}
}