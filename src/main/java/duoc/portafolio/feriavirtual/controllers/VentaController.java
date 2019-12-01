package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import duoc.portafolio.feriavirtual.models.Venta;
import duoc.portafolio.feriavirtual.service.VentaService;

@Controller
public class VentaController {
	
	@Autowired
	private VentaService ventaServicio;
	
	@RequestMapping("/ventas")
	public String index(Model modelo) {
		modelo.addAttribute("listadoVentas", ventaServicio.getAll());
		
		return "ventas/ventas";
	}
	
	@GetMapping("/ventas/crear/{id}")
	public String editar(@PathVariable("id") Integer id, Model modelo) {		
		if (id != null && id != 0) {
			modelo.addAttribute("venta", ventaServicio.get(id));
		} else {
			modelo.addAttribute("venta", new Venta());
		}
		
		return "ventas/agregar";
	}
	
	@PostMapping("/ventas/guardar")
	public String crear(Venta venta, Model modelo) {
		ventaServicio.save(venta);
		
		return "redirect:/ventas";
	}
	
	@GetMapping("/ventas/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, Model modelo) {
		ventaServicio.delete(id);
		
		return "redirect:/ventas";
	}
}