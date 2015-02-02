package cidc.inscripcionConv.obj;

import java.io.Serializable;

public class ResumenCompromOBJ implements Serializable{

	private int idProp;
	private int idCompromiso;
	private String producto;
	private String indicador;
	private int cantidad;
	private boolean cumplido;

	public int getCantidad() {
		return cantidad;
	}
	public String getIndicador() {
		return indicador;
	}
	public String getProducto() {
		return producto;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public int getIdProp() {
		return idProp;
	}
	public void setIdProp(int idProp) {
		this.idProp = idProp;
	}
	public int getIdCompromiso() {
		return idCompromiso;
	}
	public void setIdCompromiso(int idCompromiso) {
		this.idCompromiso = idCompromiso;
	}
	public boolean isCumplido() {
		return cumplido;
	}
	public void setCumplido(boolean cumplido) {
		this.cumplido = cumplido;
	}
}

