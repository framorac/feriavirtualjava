package duoc.portafolio.feriavirtual.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "USUARIOS")
public class Usuario implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuarios_sequence")
	@SequenceGenerator(name = "usuarios_sequence", sequenceName = "USUARIO_SEQ1", allocationSize = 1)
	@Column(name = "ID_USUARIO")
	private Integer idUsuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PERFIL", nullable = false)
	private Perfil perfil;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column(name = "APELLIDO")
	private String apellido;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;
}