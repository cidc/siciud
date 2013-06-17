package cidc.proyectos.obj;

import java.io.Serializable;

public class Documento implements Serializable{

	private String url;
	private String fecha;
	private String Observaciones;
	
	//se adicionan los siguientes paramentros para que la clase tambien sea objetos de Documentos de contratacion
	private int id;
	private String nombre;
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
