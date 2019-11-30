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
@Table(name = "HISTORICO_ESTADO_VENTAS")
public class HistoricoEstadoVenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historico_estado_ventas_sequence")
	@SequenceGenerator(name = "historico_estado_ventas_sequence", sequenceName = "HISTORICO_ESTADO_VENTAS_SEQ", allocationSize = 1)
	@Column(name = "ID_TIPOESTADO")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_ESTADO", nullable = false)
	private TipoEstado tipo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VENTA", nullable = false)
	private Venta venta;
	
	@Column(name = "FECHA")
	private Date fecha;
}