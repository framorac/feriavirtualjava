package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.HistoricoEstadoVenta;
import duoc.portafolio.feriavirtual.repository.HistoricoEstadoVentaRepository;
import duoc.portafolio.feriavirtual.service.HistoricoEstadoVentaService;

public class HistoricoEstadoVentaServiceImpl extends GenericServiceImpl<HistoricoEstadoVenta, Integer> implements HistoricoEstadoVentaService{

	@Autowired
	private HistoricoEstadoVentaRepository historicoEstadoVentaRepository;
	
	@Override
	public CrudRepository<HistoricoEstadoVenta, Integer> getRepository() {
		return historicoEstadoVentaRepository;
	}
}