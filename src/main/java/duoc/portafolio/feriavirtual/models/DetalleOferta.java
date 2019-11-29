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
@Table(name = "DETALLE_OFERTA")
public class DetalleOferta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "detalle_ofertas_sequence")
	@SequenceGenerator(name = "detalle_ofertas_sequence", sequenceName = "DETALLE_OFERTA_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_OFERTA", nullable = false)
	private Oferta oferta;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_PRODUCTO", nullable = false)
	private Producto producto;
	
	@Column(name = "CANTIDAD")
	private Integer cantidad;
	
	@Column(name = "PRECIO")
	private Integer precio;
}