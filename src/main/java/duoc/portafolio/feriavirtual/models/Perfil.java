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
@Table(name = "PERFILES")
public class Perfil {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfiles_sequence")
	@SequenceGenerator(name = "perfiles_sequence", sequenceName = "PERFILES_SEQ", allocationSize = 1)
	@Column(name = "ID_PERFIL")
	private Integer idPerfil;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
}