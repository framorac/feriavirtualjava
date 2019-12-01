package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.TipoProducto;
import duoc.portafolio.feriavirtual.repository.TipoProductoRepository;
import duoc.portafolio.feriavirtual.service.TipoProductoService;

@Service
public class TipoProductoServiceImpl extends GenericServiceImpl<TipoProducto, Integer> implements TipoProductoService{

	@Autowired
	private TipoProductoRepository tipoProductoRepository;
	
	@Override
	public CrudRepository<TipoProducto, Integer> getRepository() {
		return tipoProductoRepository;
	}
}