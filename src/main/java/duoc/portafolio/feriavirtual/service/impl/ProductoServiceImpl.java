package duoc.portafolio.feriavirtual.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import duoc.portafolio.feriavirtual.commons.GenericServiceImpl;
import duoc.portafolio.feriavirtual.models.Producto;
import duoc.portafolio.feriavirtual.repository.ProductoRepository;
import duoc.portafolio.feriavirtual.service.ProductoService;

@Service
public class ProductoServiceImpl extends GenericServiceImpl<Producto, Integer> implements ProductoService{

	@Autowired
	private ProductoRepository productoRepository;
	
	@Override
	public CrudRepository<Producto, Integer> getRepository() {
		return productoRepository;
	}
}
