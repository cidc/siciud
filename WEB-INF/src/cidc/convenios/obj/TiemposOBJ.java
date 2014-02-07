package cidc.convenios.obj;

 

import java.io.Serializable;

public class TiemposOBJ implements Serializable {

	private int idTiempo;
	private int idProyecto;
	private int tipoTiempo;
	private String fechaTiempo;
	private int valorTiempo;
	private String descripcion;
	private String regitradoPor;
	
	
	public String getRegitradoPor() {
		return regitradoPor;
	}
	public void setRegitradoPor(String regitradoPor) {
		this.regitradoPor = regitradoPor;
	}
	public int getIdTiempo() {
		return idTiempo;
	}
	public void setIdTiempo(int idTiempo) {
		this.idTiempo = idTiempo;
	}
	public int getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}
	public int getTipoTiempo() {
		return tipoTiempo;
	}
	public void setTipoTiempo(int tipoTiempo) {
		this.tipoTiempo = tipoTiempo;
	}
	public String getFechaTiempo() {
		return fechaTiempo;
	}
	public void setFechaTiempo(String fechaTiempo) {
		this.fechaTiempo = fechaTiempo;
	}
	public int getValorTiempo() {
		return valorTiempo;
	}
	public void setValorTiempo(int valorTiempo) {
		this.valorTiempo = valorTiempo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
