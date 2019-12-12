package duoc.portafolio.feriavirtual.models;

import java.sql.Date;

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

import lombok.Data;

@Data
@Entity
@Table(name = "CONTRATOS")
public class Contrato {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contratos_sequence")
	@SequenceGenerator(name = "contratos_sequence", sequenceName = "CONTRATOS_SEQ", allocationSize = 1)
	@Column(name = "ID_CONTRATO")
	private Integer idContrato;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name = "FECHA_TERMINO")
	private Date fechaTermino;
}