package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import duoc.portafolio.feriavirtual.models.Usuario;
import duoc.portafolio.feriavirtual.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public String index(Model modelo) {
		modelo.addAttribute("listadoUsuarios", usuarioService.getAll());
		return "usuarios/usuarios";
	}
	
	@GetMapping("/usuarios/crear/{id}")
	public String crear(@PathVariable("id") Integer id, Model modelo) {
		if (id != null) {
			modelo.addAttribute("usuario", usuarioService.get(id));
		}
		
		return "usuarios/usuarios";
	}
	
	@PostMapping("/usuarios/crear/{id}")
	public String crear(Usuario usuario, Model modelo) {
		usuarioService.save(usuario);
		
		return "redirect:/usuarios";
	}
	
	public String borrar(@PathVariable Integer id, Model modelo) {
		usuarioService.delete(id);
		
		return "redirect:/usuarios";
	}
}