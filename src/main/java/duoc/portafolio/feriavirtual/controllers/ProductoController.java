package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import duoc.portafolio.feriavirtual.models.Producto;
import duoc.portafolio.feriavirtual.service.ProductoService;
import duoc.portafolio.feriavirtual.service.TipoProductoService;

@Controller
public class ProductoController {
	
	@Autowired
	private ProductoService productoServicio;
	
	@Autowired
	private TipoProductoService tipoProductoService;
	
	@RequestMapping("/productos")
	public String index(Model modelo) {
		modelo.addAttribute("listadoProductos", productoServicio.getAll());
		modelo.addAttribute("tipoProductos", tipoProductoService.getAll());
		
		return "productos/productos";
	}
	
	@GetMapping("/productos/crear/{id}")
	public String editar(@PathVariable("id") Integer id, Model modelo) {
		if (id != null && id != 0) {
			modelo.addAttribute("producto", productoServicio.get(id));
			modelo.addAttribute("tipoProductos", tipoProductoService.getAll());
		} else {
			modelo.addAttribute("producto", new Producto());
			modelo.addAttribute("tipoProductos", tipoProductoService.getAll());
		}
		
		return "productos/agregar";
	}
	
	@PostMapping("/productos/guardar")
	public String crear(Producto producto, Model modelo) {
		productoServicio.save(producto);
		
		return "redirect:/productos";
	}
	
	@GetMapping("/productos/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, Model modelo) {
		productoServicio.delete(id);
		
		return "redirect:/productos";
	}
}