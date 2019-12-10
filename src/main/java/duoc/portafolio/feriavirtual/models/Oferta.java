package duoc.portafolio.feriavirtual.models;

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
@Table(name = "OFERTAS")
public class Oferta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ofertas_sequence")
	@SequenceGenerator(name = "ofertas_sequence", sequenceName = "OFERTAS_SEQ", allocationSize = 1)
	@Column(name = "ID_OFERTA")
	private Integer idOferta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VENTA", nullable = false)
	private Venta venta;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name="ISCERTIFICADO",columnDefinition="char(1)")
	private String isCertificado;
	
	@Column(name="ISENVASADO",columnDefinition="char(1)")
	private String isEnvasado;
	
	@Column(name="ISGANADOR",columnDefinition="char(1)")
	private String isGanador;
}