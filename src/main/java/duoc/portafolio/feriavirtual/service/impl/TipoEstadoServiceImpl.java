package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.TipoEstado;
import duoc.portafolio.feriavirtual.repository.TipoEstadoRepository;
import duoc.portafolio.feriavirtual.service.TipoEstadoService;

@Service
public class TipoEstadoServiceImpl extends GenericServiceImpl<TipoEstado, Integer> implements TipoEstadoService{

	@Autowired
	private TipoEstadoRepository tipoEstadoRepository;
	
	@Override
	public CrudRepository<TipoEstado, Integer> getRepository() {
		return tipoEstadoRepository;
	}
}