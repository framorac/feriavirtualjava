package duoc.portafolio.feriavirtual.models;

import java.util.ArrayList;
import java.util.List;

import com.sun.istack.Nullable;

public class _EstructuraDinamica {

	// colocar cualquier número de variables que quiera unificar
	// y agregarlas al constructor
	public @Nullable Oferta oferta = new Oferta();
	public @Nullable List<DetalleOferta> detalles = new ArrayList<DetalleOferta>();
	public @Nullable Venta venta = new Venta();
	public @Nullable List<DetalleVenta> detalleVenta = new ArrayList<DetalleVenta>();
	public _EstructuraDinamica(@Nullable Oferta oferta, @Nullable List<DetalleOferta> detalles, @Nullable Venta venta,
			@Nullable List<DetalleVenta> detalleVenta) {
		super();
		this.oferta = oferta;
		this.detalles = detalles;
		this.venta = venta;
		this.detalleVenta = detalleVenta;
	}
	public Oferta getOferta() {
		return oferta;
	}
	public void setOferta(Oferta oferta) {
		this.oferta = oferta;
	}
	public List<DetalleOferta> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<DetalleOferta> detalles) {
		this.detalles = detalles;
	}
	public Venta getVenta() {
		return venta;
	}
	public void setVenta(Venta venta) {
		this.venta = venta;
	}
	public List<DetalleVenta> getDetalleVenta() {
		return detalleVenta;
	}
	public void setDetalleVenta(List<DetalleVenta> detalleVenta) {
		this.detalleVenta = detalleVenta;
	}
	
	
	
	// colocar aqui las weas nulleables para que acepte o no los parametros necesarios.
	

	
}
