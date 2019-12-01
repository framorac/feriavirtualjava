package duoc.portafolio.feriavirtual.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "TIPOVENTAS")
public class TipoVenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoventas_sequence")
	@SequenceGenerator(name = "tipoventas_sequence", sequenceName = "TIPOVENTAS_SEQ", allocationSize = 1)
	@Column(name = "ID_TIPOVENTA")
	private Integer idTipoVenta;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
}