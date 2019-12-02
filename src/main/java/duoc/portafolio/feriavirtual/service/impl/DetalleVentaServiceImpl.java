package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.DetalleVenta;
import duoc.portafolio.feriavirtual.repository.DetalleVentaRepository;
import duoc.portafolio.feriavirtual.service.DetalleVentaService;;

@Service
public class DetalleVentaServiceImpl extends GenericServiceImpl<DetalleVenta, Integer> implements DetalleVentaService{

	@Autowired
	private DetalleVentaRepository detalleVentaRepository;
	
	@Override
	public CrudRepository<DetalleVenta, Integer> getRepository() {
		return detalleVentaRepository;
	}
}
