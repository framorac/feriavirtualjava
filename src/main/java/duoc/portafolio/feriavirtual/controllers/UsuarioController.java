package duoc.portafolio.feriavirtual.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import duoc.portafolio.feriavirtual.models.Usuario;
import duoc.portafolio.feriavirtual.service.PerfilService;
import duoc.portafolio.feriavirtual.service.UsuarioService;

@Controller
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private PerfilService perfilService;
	
	@RequestMapping("/usuarios")
	public String index(Model modelo) {
		modelo.addAttribute("listadoUsuarios", usuarioService.getAll());
		
		return "usuarios/usuarios";
	}
	
	@GetMapping("/usuarios/crear/{id}")
	public String editar(@PathVariable("id") Integer id, Model modelo) {
		modelo.addAttribute("perfiles", perfilService.getAll());
		modelo.addAttribute("fecha", LocalDateTime.now());
		
		if (id != null && id != 0) {
			modelo.addAttribute("usuario", usuarioService.get(id));
		} else {
			modelo.addAttribute("usuario", new Usuario());
		}
		
		return "usuarios/agregar";
	}
	
	@PostMapping("/usuarios/guardar")
	public String crear(Usuario usuario, Model modelo) {
		usuarioService.save(usuario);
		
		return "redirect:/usuarios";
	}
	
	@GetMapping("/usuarios/eliminar/{id}")
	public String eliminar(@PathVariable Integer id, Model modelo) {
		usuarioService.delete(id);
		
		return "redirect:/usuarios";
	}
}