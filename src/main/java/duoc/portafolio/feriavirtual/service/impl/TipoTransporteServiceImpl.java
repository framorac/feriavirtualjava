package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.TipoTransporte;
import duoc.portafolio.feriavirtual.repository.TipoTransporteRepository;
import duoc.portafolio.feriavirtual.service.TipoTransporteService;

@Service
public class TipoTransporteServiceImpl extends GenericServiceImpl<TipoTransporte, Integer> implements TipoTransporteService{

	@Autowired
	private TipoTransporteRepository tipoTransporteRepository;
	
	@Override
	public CrudRepository<TipoTransporte, Integer> getRepository() {
		return tipoTransporteRepository;
	}
}
