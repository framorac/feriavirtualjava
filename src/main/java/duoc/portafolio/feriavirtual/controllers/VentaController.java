package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import duoc.portafolio.feriavirtual.models.Venta;
import duoc.portafolio.feriavirtual.service.ProductoService;
import duoc.portafolio.feriavirtual.service.TipoVentaService;
import duoc.portafolio.feriavirtual.service.UsuarioService;
import duoc.portafolio.feriavirtual.service.VentaService;

@Controller
public class VentaController {
	
	@Autowired
	private VentaService ventaServicio;
	
<<<<<<< src/main/java/duoc/portafolio/feriavirtual/controllers/VentaController.java
	@GetMapping("/ventas")
	public String ventas(Model modelo) {
=======
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private TipoVentaService tipoVentaService;
	
	@Autowired
	private ProductoService productoService;
	
	@RequestMapping("/ventas")
	public String index(Model modelo) {
>>>>>>> src/main/java/duoc/portafolio/feriavirtual/controllers/VentaController.java
		modelo.addAttribute("listadoVentas", ventaServicio.getAll());
		return "ventas/ventas";
	}
	
	@GetMapping("/ventas/crear/{id}")
<<<<<<< src/main/java/duoc/portafolio/feriavirtual/controllers/VentaController.java
	public String crear(@PathVariable("id") Integer id, Model modelo) {
		if (id != null) {
=======
	public String editar(@PathVariable("id") Integer id, Model modelo) {	
		modelo.addAttribute("usuarios", usuarioService.getAll());
		modelo.addAttribute("tipoVentas", tipoVentaService.getAll());
		modelo.addAttribute("productos", productoService.getAll());
		
		if (id != null && id != 0) {
>>>>>>> src/main/java/duoc/portafolio/feriavirtual/controllers/VentaController.java
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