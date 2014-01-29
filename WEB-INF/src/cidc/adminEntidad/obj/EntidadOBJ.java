package cidc.adminEntidad.obj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class EntidadOBJ implements Serializable {
	private int id;
	private String nombre= null;
	private String tipo= null;
	private String nacionalidad= null;
	private String ciudad = null;
	private String direccion= null;
	private String observaciones= null;
	private String usudigita= null;
	private float aporte;
	private String fecha= null;
	private String[] telefonos = null;
	
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getUsudigita() {
		return usudigita;
	}
	public void setUsudigita(String usudigita) {
		this.usudigita = usudigita;
	}
	public float getAporte() {
		return aporte;
	}
	public void setAporte(float aporte) {
		this.aporte = aporte;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String[] getTelefonos() {
		return telefonos;
	}
	public void setTelefonos(String[] telefonos) {
		this.telefonos = telefonos;
	}
	
	   
	
	
	
	
	
}
