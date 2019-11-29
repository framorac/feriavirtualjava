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
@Table(name = "TIPOTRANSPORTE")
public class TipoTransporte {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipotransporte_sequence")
	@SequenceGenerator(name = "tipotransporte_sequence", sequenceName = "TIPOTRANSPORTE_SEQ", allocationSize = 1)
	@Column(name = "ID_TIPOTRANSPORTE")
	private Integer idTipoTransporte;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
}