package duoc.portafolio.feriavirtual.models;

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
@Table(name = "DETALLE_VENTA")
public class DetalleVenta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_venta_sequence")
	@SequenceGenerator(name = "detalle_venta_sequence", sequenceName = "DETALLE_VENTA_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer idDetalleVenta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRODUCTO", nullable = false)
	private Producto producto;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_VENTA", nullable = false)
	private Venta venta;
	
	@Column(name = "CANTIDAD")
	private Integer cantidad;
}