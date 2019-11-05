package duoc.portafolio.feriavirtual.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PERFILES")
public class Perfil implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfiles_sequence")
	@SequenceGenerator(name = "perfiles_sequence", sequenceName = "PERFILES_SEQ", allocationSize = 1)
	@Column(name = "ID_PERFIL")
	private Integer idPerfil;
	
	@Column(name = "TIPO")
	private String tipo;
	
	@Column(name = "DESCRIPCION")
	private String descripcion;
		
	public Perfil() {
		super();
	}

	public Perfil(Integer idPerfil, String tipo, String descripcion) {
		super();
		this.idPerfil = idPerfil;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}
	
	public Integer getIdPerfil() {
		return idPerfil;
	}
	public void setIdPerfil(Integer idPerfil) {
		this.idPerfil = idPerfil;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}