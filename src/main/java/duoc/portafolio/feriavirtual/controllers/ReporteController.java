package duoc.portafolio.feriavirtual.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import duoc.portafolio.feriavirtual.repository.OfertaRepository;
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
	
	@Autowired
	private OfertaRepository ofertaRepository;
		
	@RequestMapping("/reportes")
	public String index(Model modelo) throws ParseException {
		modelo.addAttribute("usuarios", usuarioRepository.count());
		modelo.addAttribute("ventas", ventaRepository.count());
		modelo.addAttribute("subastas", subastaRepository.count());
		modelo.addAttribute("productos", productoRepository.count());
		modelo.addAttribute("ofertas", ofertaRepository.count());
		
		SimpleDateFormat formatea = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		String date2019Inicio = "01-JAN-2019";
		String date2019Final = "31-DEC-2019";
		String date2018Inicio = "01-JAN-2018";
		String date2018Final = "31-DEC-2018";
		String date2017Inicio = "01-JAN-2017";
		String date2017Final = "31-DEC-2017";
		String date2016Inicio = "01-JAN-2016";
		String date2016Final = "31-DEC-2016";
		String date2015Inicio = "01-JAN-2015";
		String date2015Final = "31-DEC-2015";
		Date fechaInicio1 = formatea.parse(date2019Inicio);
		Date fechaTermino1 = formatea.parse(date2019Final);
		Date fechaInicio2 = formatea.parse(date2018Inicio);
		Date fechaTermino2 = formatea.parse(date2018Final);
		Date fechaInicio3 = formatea.parse(date2017Inicio);
		Date fechaTermino3 = formatea.parse(date2017Final);
		Date fechaInicio4 = formatea.parse(date2016Inicio);
		Date fechaTermino4 = formatea.parse(date2016Final);
		Date fechaInicio5 = formatea.parse(date2015Inicio);
		Date fechaTermino5 = formatea.parse(date2015Final);
		modelo.addAttribute("ventasPorYear2019", ventaRepository.countByFechaBetween(fechaInicio1, fechaTermino1));
		modelo.addAttribute("ventasPorYear2018", ventaRepository.countByFechaBetween(fechaInicio2, fechaTermino2));
		modelo.addAttribute("ventasPorYear2017", ventaRepository.countByFechaBetween(fechaInicio3, fechaTermino3));
		modelo.addAttribute("ventasPorYear2016", ventaRepository.countByFechaBetween(fechaInicio4, fechaTermino4));
		modelo.addAttribute("ventasPorYear2015", ventaRepository.countByFechaBetween(fechaInicio5, fechaTermino5));
		
		return "reportes/reportes";
	}
}
