package cidc.convenios.obj;

import java.io.Serializable;

public class CdpOBJ implements Serializable{
	
	private int Idcdp;
	private String codigo;
	private String nombre;
	private String fechaRegistro;
	private String observacion;
	private int usudigita;
	private int valortotal;
	private int financiero;
	private int [] valores;
	
	public int[] getValores() {
		return valores;
	}
	public void setValores(int[] valores) {
		this.valores = valores;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	public int getIdcdp() {
		return Idcdp;
	}
	public void setIdcdp(int idcdp) {
		Idcdp = idcdp;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int getUsudigita() {
		return usudigita;
	}
	public void setUsudigita(int usudigita) {
		this.usudigita = usudigita;
	}
	public int getValortotal() {
		return valortotal;
	}
	public void setValortotal(int valortotal) {
		this.valortotal = valortotal;
	}
	public int getFinanciero() {
		return financiero;
	}
	public void setFinanciero(int financiero) {
		this.financiero = financiero;
	}
	
	

	
	
	
	

}
