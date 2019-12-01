package duoc.portafolio.feriavirtual.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

import duoc.portafolio.feriavirtual.service.HistoricoEstadoVentaService;
import duoc.portafolio.feriavirtual.service.VentaService;
import duoc.portafolio.feriavirtual.models.*;

@Controller
public class SubastaController {
	
	@Autowired
	private VentaService ventaServicio;
	@Autowired
	private HistoricoEstadoVentaService hevServicio;
	
	@GetMapping("/subastas/agregar")
	public String Agregar(Model modelo) {
		
		  //modelo.addAttribute("listadoVentas", ventaServicio.getAll());
		  List<Venta> ventas = ventaServicio.getAll();
		  List<HistoricoEstadoVenta> hevs = hevServicio.getAll(); 
		
		  //ventas activas 
		  hevs = hevs.stream().filter(x -> x.getActivo() == '1').collect(Collectors.toList());
		 
		  modelo.addAttribute("listaVentasActivas", hevs);
		 
		return "/subastas/agregar";
	}

}