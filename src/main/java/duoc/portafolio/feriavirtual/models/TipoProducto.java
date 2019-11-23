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
@Table(name = "TIPOPRODUCTOS")
public class TipoProducto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoproductos_sequence")
	@SequenceGenerator(name = "tipoproductos_sequence", sequenceName = "TIPOPRODUCTOS_SEQ", allocationSize = 1)
	@Column(name = "ID_TIPOPRODUCTO")
	private Integer idTipoProducto;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
}