package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import duoc.portafolio.feriavirtual.models.Venta;
import duoc.portafolio.feriavirtual.service.VentaService;

@Controller
public class VentaController {
	
	@Autowired
	private VentaService ventaServicio;
	
	@GetMapping("/ventas")
	public String ventas(Model modelo) {
		modelo.addAttribute("listadoVentas", ventaServicio.getAll());
		return "ventas/ventas";
	}
	
	@GetMapping("/ventas/crear/{id}")
	public String crear(@PathVariable("id") Integer id, Model modelo) {
		if (id != null) {
			modelo.addAttribute("venta", ventaServicio.get(id));
		}
		
		return "ventas/agregar";
	}
	
	@PostMapping("/ventas/crear/{id}")
	public String crear(Venta venta, Model modelo) {
		ventaServicio.save(venta);
		
		return "redirect:/ventas";
	}
	
	public String borrar(@PathVariable Integer id, Model modelo) {
		ventaServicio.delete(id);
		
		return "redirect:/ventas";
	}
}