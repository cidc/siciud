package cidc.convenios.obj;

import java.io.Serializable;

public class DatosRubro implements Serializable{
	private int idrubro;
	private int idconventd;
	private int codigorubroconvenio;
	private String nombre;
	private String valor;
	
	public int getIdrubro() {
		return idrubro;
	}
	public void setIdrubro(int idrubro) {
		this.idrubro = idrubro;
	}
	public int getIdconventd() {
		return idconventd;
	}
	public void setIdconventd(int idconventd) {
		this.idconventd = idconventd;
	}
	public int getCodigorubroconvenio() {
		return codigorubroconvenio;
	}
	public void setCodigorubroconvenio(int codigorubroconvenio) {
		this.codigorubroconvenio = codigorubroconvenio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
	

}
