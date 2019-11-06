package duoc.portafolio.feriavirtual.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public abstract class GenericServiceImpl<Objeto, Key extends Serializable> implements GenericService<Objeto, Key>{
	@Override
	public Objeto save(Objeto objeto) {
		return getRepository().save(objeto);
	}

	@Override
	public void delete(Key id) {
		getRepository().deleteById(id);
	}

	@Override
	public Objeto get(Key id) {
		Optional<Objeto> objeto = getRepository().findById(id);
		
		if (objeto.isPresent()) {
			return objeto.get();
		}
		
		return null;
	}

	@Override
	public List<Objeto> getAll() {
		List<Objeto> listado = new ArrayList<>();
		getRepository().findAll().forEach(Objeto -> listado.add(Objeto));
		
		return listado;
	}
	
	public abstract CrudRepository<Objeto, Key> getRepository();
}