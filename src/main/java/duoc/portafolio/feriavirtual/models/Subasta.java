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
@Table(name = "SUBASTAS")
public class Subasta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subastas_sequence")
	@SequenceGenerator(name = "subastas_sequence", sequenceName = "SUBASTAS_SEQ1", allocationSize = 1)
	@Column(name = "ID_SUBASTA")
	private Integer idSubasta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OFERTA", nullable = false)
	private Oferta oferta;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Column(name = "FECHA_INICIO")
	private Date fechaInicio;
	
	@Column(name="ISCERTIFICADO",columnDefinition="char(1)")
	private String isCertificado;
	
	@Column(name="ISREFRIGERADO",columnDefinition="char(1)")
	private String isRefrigerado;
	
	@Column(name="CAPACIDADCARGA")
	private Integer capacidadCarga;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPOTRANSPORTE", nullable = false)
	private TipoTransporte tipoTransporte;
	
	@Column(name="PRECIO")
	private Integer precio;
	
	@Column(name="ISGANADOR",columnDefinition="char(1)")
	private String isGanador;
}
