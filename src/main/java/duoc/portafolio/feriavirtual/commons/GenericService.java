package duoc.portafolio.feriavirtual.commons;

import java.io.Serializable;
import java.util.List;

public interface GenericService<Objeto, Key extends Serializable> {
	Objeto save(Objeto objeto);
	void delete(Key id);
	Objeto get(Key id);
	List<Objeto> getAll();
}