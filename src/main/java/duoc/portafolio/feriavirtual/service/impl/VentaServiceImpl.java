package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.Venta;
import duoc.portafolio.feriavirtual.repository.VentaRepository;
import duoc.portafolio.feriavirtual.service.VentaService;

@Service
public class VentaServiceImpl extends GenericServiceImpl<Venta, Integer>  implements VentaService{

	@Autowired
	private VentaRepository ventaRepository;
	
	@Override
	public CrudRepository<Venta, Integer> getRepository() {
		return ventaRepository;
	}
}