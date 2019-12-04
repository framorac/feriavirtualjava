package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import duoc.portafolio.feriavirtual.repository.ProductoRepository;
import duoc.portafolio.feriavirtual.repository.SubastaRepository;
import duoc.portafolio.feriavirtual.repository.UsuarioRepository;
import duoc.portafolio.feriavirtual.repository.VentaRepository;

@Controller
public class ReporteController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VentaRepository ventaRepository;
	
	@Autowired
	private SubastaRepository subastaRepository;
	
	@Autowired
	private ProductoRepository productoRepository;
		
	@RequestMapping("/reportes")
	public String index(Model modelo) {
		modelo.addAttribute("usuarios", usuarioRepository.count());
		modelo.addAttribute("ventas", ventaRepository.count());
		modelo.addAttribute("subastas", subastaRepository.count());
		modelo.addAttribute("productos", productoRepository.count());
		
		return "reportes/reportes";
	}
}