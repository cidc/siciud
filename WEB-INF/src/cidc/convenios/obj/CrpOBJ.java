package cidc.convenios.obj;

import java.io.Serializable;

public class CrpOBJ implements Serializable {
	
	private int idcrp;
	public int getIdcrp() {
		return idcrp;
	}
	public void setIdcrp(int idcrp) {
		this.idcrp = idcrp;
	}
	public int getIdcdp() {
		return idcdp;
	}
	public void setIdcdp(int idcdp) {
		this.idcdp = idcdp;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public int getUsudigita() {
		return usudigita;
	}
	public void setUsudigita(int usudigita) {
		this.usudigita = usudigita;
	}
	private int idcdp;
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	private int valor;
	private String nombre;
	private String codigo;
	private String cliente;
	private String observacion;
	private String fecha;
	private int usudigita;
	

}
