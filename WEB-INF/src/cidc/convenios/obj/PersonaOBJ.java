package cidc.convenios.obj;

import java.io.Serializable;

public class PersonaOBJ implements Serializable{

	
	private int idPersona;
	private String nombre;
	private String apellido;
	private String documento;
	private String rol;
	private String fechaInicio;
	private String fechaFin;
	private String observacion;
	private String regitradoPor;
	private String fechaReg;
	
	
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDocumento() {
		return documento;
	}
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getRegitradoPor() {
		return regitradoPor;
	}
	public void setRegitradoPor(String regitradoPor) {
		this.regitradoPor = regitradoPor;
	}
	public String getFechaReg() {
		return fechaReg;
	}
	public void setFechaReg(String fechaReg) {
		this.fechaReg = fechaReg;
	}
	


	
	
	
	
	
}
