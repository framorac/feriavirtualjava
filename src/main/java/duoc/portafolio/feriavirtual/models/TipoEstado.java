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
@Table(name = "TIPOESTADOS")
public class TipoEstado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoestados_sequence")
	@SequenceGenerator(name = "tipoestados_sequence", sequenceName = "TIPOESTADOS_SEQ", allocationSize = 1)
	@Column(name = "ID_TIPOESTADO")
	private Integer idTipoEstado;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
}