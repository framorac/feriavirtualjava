package duoc.portafolio.feriavirtual.repository;

import java.util.Date;
import org.springframework.data.repository.CrudRepository;
import duoc.portafolio.feriavirtual.models.Venta;

public interface VentaRepository extends CrudRepository<Venta, Integer> {
	
	int countByFechaBetween(Date fechaInicio, Date fechaTermino);
}