package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.Contrato;
import duoc.portafolio.feriavirtual.repository.ContratosRepository;
import duoc.portafolio.feriavirtual.service.ContratoService;

@Service
public class ContratoServiceImpl extends GenericServiceImpl<Contrato, Integer> implements ContratoService{

	@Autowired
	private ContratosRepository contratoRepository;
	
	@Override
	public CrudRepository<Contrato, Integer> getRepository() {
		return contratoRepository;
	}
}
