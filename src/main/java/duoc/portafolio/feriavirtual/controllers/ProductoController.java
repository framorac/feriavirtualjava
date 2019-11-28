package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import duoc.portafolio.feriavirtual.models.Producto;
import duoc.portafolio.feriavirtual.service.ProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService productoServicio;
	
	@GetMapping("/productos")
	public String producto(Model modelo) {
		modelo.addAttribute("listadoProductos", productoServicio.getAll());
		return "productos/productos";
	}
	
	@GetMapping("/productos/crear/{id}")
	public String crear(@PathVariable("id") Integer id, Model modelo) {
		if (id != null) {
			modelo.addAttribute("producto", productoServicio.get(id));
		}
		
		return "productos/agregar";
	}
	
	@PostMapping("/productos/crear/{id}")
	public String crear(Producto producto, Model modelo) {
		productoServicio.save(producto);
		
		return "redirect:/productos";
	}
	
	public String borrar(@PathVariable Integer id, Model modelo) {
		productoServicio.delete(id);
		
		return "redirect:/productos";
	}
}