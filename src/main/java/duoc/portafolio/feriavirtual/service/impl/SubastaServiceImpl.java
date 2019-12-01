package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.Subasta;
import duoc.portafolio.feriavirtual.repository.SubastaRepository;
import duoc.portafolio.feriavirtual.service.SubastaService;

public class SubastaServiceImpl extends GenericServiceImpl<Subasta, Integer> implements SubastaService{

	@Autowired
	private SubastaRepository subastaRepository;
	
	@Override
	public CrudRepository<Subasta, Integer> getRepository() {
		return subastaRepository;
	}
}