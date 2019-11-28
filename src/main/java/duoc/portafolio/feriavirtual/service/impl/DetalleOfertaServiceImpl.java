package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.DetalleOferta;
import duoc.portafolio.feriavirtual.repository.DetalleOfertaRepository;
import duoc.portafolio.feriavirtual.service.DetalleOfertaService;

public class DetalleOfertaServiceImpl extends GenericServiceImpl<DetalleOferta, Integer> implements DetalleOfertaService{

	@Autowired
	private DetalleOfertaRepository detalleOfertaRepository;
	
	@Override
	public CrudRepository<DetalleOferta, Integer> getRepository() {
		return detalleOfertaRepository;
	}
}
