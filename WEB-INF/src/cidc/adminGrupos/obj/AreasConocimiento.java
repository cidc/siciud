package cidc.adminGrupos.obj;

import java.io.Serializable;

public class AreasConocimiento implements Serializable{
	
	private int codigo;
	private String nombre;	
	private boolean subarea;
	private int codigoObjSocio;
	private String nombreObjSocio;
	
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean isSubarea() {
		return subarea;
	}
	public void setSubarea(boolean subarea) {
		this.subarea = subarea;
	}
	public int getCodigoObjSocio() {
		return codigoObjSocio;
	}
	public void setCodigoObjSocio(int codigoObjSocio) {
		this.codigoObjSocio = codigoObjSocio;
	}
	public String getNombreObjSocio() {
		return nombreObjSocio;
	}
	public void setNombreObjSocio(String nombreObjSocio) {
		this.nombreObjSocio = nombreObjSocio;
	}
	
	
}
