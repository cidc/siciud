package cidc.convenios.obj;

import java.io.Serializable;

public class GruposOBJ implements Serializable{
	
	private int idConvGru;
	private int idConvenio;
	private int idGrupo;
	private String nombre;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getIdConvGru() {
		return idConvGru;
	}
	public void setIdConvGru(int idConvGru) {
		this.idConvGru = idConvGru;
	}
	public int getIdConvenio() {
		return idConvenio;
	}
	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	

}
