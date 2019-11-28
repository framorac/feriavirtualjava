package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.Oferta;
import duoc.portafolio.feriavirtual.repository.OfertaRepository;
import duoc.portafolio.feriavirtual.service.OfertaService;

public class OfertaServiceImpl extends GenericServiceImpl<Oferta, Integer> implements OfertaService{

	@Autowired
	private OfertaRepository ofertaRepository;
	
	@Override
	public CrudRepository<Oferta, Integer> getRepository() {
		return ofertaRepository;
	}
}