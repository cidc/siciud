package cidc.convenios.obj;

import java.io.Serializable;
import java.util.List;

import cidc.proyectosGeneral.obj.ObservacionObj;

public class ObservacionesOBJ  implements Serializable{
	
	private long idObservacion;
	public long getIdObservacion() {
		return idObservacion;
	}
	public void setIdObservacion(long idObservacion) {
		this.idObservacion = idObservacion;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	private String observacion;
	private String usuario;
	private String fecha;	
	

}
