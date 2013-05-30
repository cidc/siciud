package cidc.evalComite.obj;

import java.io.Serializable;

public class TrayectoriaInvest implements Serializable{

	private long idPropuesta;
	private long tipoInvest;
	private String investigador;
	private String nombrePonencia;
	private String nombreGrupo;
	private String nombreProyecto; //Proyecto relacionado con el Plan de Accion
	private String cvLac;
	private int papel;
	private String []listadDoc;
	private String []listadEventos;
 
	public long getIdPropuesta() {
		return idPropuesta;
	}
	public String getInvestigador() {
		return investigador;
	}
	public String getNombrePonencia() {
		return nombrePonencia;
	}
	public void setIdPropuesta(long idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	public void setInvestigador(String investigador) {
		this.investigador = investigador;
	}
	public void setNombrePonencia(String nombrePonencia) {
		this.nombrePonencia = nombrePonencia;
	}
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	public String getCvLac() {
		return cvLac;
	}
	public void setCvLac(String cvLac) {
		this.cvLac = cvLac;
	}
	public String[] getListadDoc() {
		return listadDoc;
	}
	public void setListadDoc(String[] listadDoc) {
		this.listadDoc = listadDoc;
	}
	public String[] getListadEventos() {
		return listadEventos;
	}
	public void setListadEventos(String[] listadEventos) {
		this.listadEventos = listadEventos;
	}
	public long getTipoInvest() {
		return tipoInvest;
	}
	public void setTipoInvest(long tipoInvest) {
		this.tipoInvest = tipoInvest;
	}
	public int getPapel() {
		return papel;
	}
	public void setPapel(int papel) {
		this.papel = papel;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

}
