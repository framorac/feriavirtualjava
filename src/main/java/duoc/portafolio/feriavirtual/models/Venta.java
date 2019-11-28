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

import lombok.Data;

@Data
@Entity
@Table(name = "VENTAS")
public class Venta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ventas_sequence")
	@SequenceGenerator(name = "ventas_sequence", sequenceName = "VENTAS_SEQ", allocationSize = 1)
	@Column(name = "ID_VENTA")
	private Integer idVenta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_USUARIO", nullable = false)
	private Usuario usuario;
	
	@Column(name = "FECHA")
	private Date fecha;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_TIPOVENTA", nullable = false)
	private TipoVenta tipoVenta;
}