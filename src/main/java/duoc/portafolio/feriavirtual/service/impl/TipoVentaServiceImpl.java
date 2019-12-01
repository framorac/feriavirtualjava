package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.TipoVenta;
import duoc.portafolio.feriavirtual.repository.TipoVentaRepository;
import duoc.portafolio.feriavirtual.service.TipoVentaService;

@Service
public class TipoVentaServiceImpl extends GenericServiceImpl<TipoVenta, Integer> implements TipoVentaService{

	@Autowired
	private TipoVentaRepository tipoVentaRepository;
	
	@Override
	public CrudRepository<TipoVenta, Integer> getRepository() {
		return tipoVentaRepository;
	}
}