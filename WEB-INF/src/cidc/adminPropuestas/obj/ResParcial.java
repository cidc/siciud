package cidc.adminPropuestas.obj;

import java.util.List;

public class ResParcial {
	private int idResultado;
	private int idConvocatoria;
	private int Corte;
	private int idPropuesta;
	private int radicado;
	private String Observaciones;

	private String director;
	private String mail;
	private String propuesta;
	private int duracion;
	private String grupo;

	private String presCIDC;
	private String presUD;
	private String presCONTRA;
	private String presTotal;

	private List listaCoInvest;

	public List getListaCoInvest() {
		return listaCoInvest;
	}
	public void setListaCoInvest(List listaCoInvest) {
		this.listaCoInvest = listaCoInvest;
	}
	public String getPresCIDC() {
		return presCIDC;
	}
	public String getPresCONTRA() {
		return presCONTRA;
	}
	public String getPresUD() {
		return presUD;
	}
	public void setPresCIDC(String presCIDC) {
		this.presCIDC = presCIDC;
	}
	public void setPresCONTRA(String presCONTRA) {
		this.presCONTRA = presCONTRA;
	}
	public void setPresUD(String presUD) {
		this.presUD = presUD;
	}
	public String getDirector() {
		return director;
	}
	public int getDuracion() {
		return duracion;
	}
	public String getGrupo() {
		return grupo;
	}
	public int getIdConvocatoria() {
		return idConvocatoria;
	}
	public int getCorte() {
		return Corte;
	}
	public int getIdPropuesta() {
		return idPropuesta;
	}
	public int getIdResultado() {
		return idResultado;
	}
	public String getMail() {
		return mail;
	}
	public String getObservaciones() {
		return Observaciones;
	}
	public String getPropuesta() {
		return propuesta;
	}
	public int getRadicado() {
		return radicado;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public void setIdConvocatoria(int idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	public void setCorte(int Corte) {
		this.Corte = Corte;
	}
	public void setIdPropuesta(int idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	public void setIdResultado(int idResultado) {
		this.idResultado = idResultado;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	public void setPropuesta(String propuesta) {
		this.propuesta = propuesta;
	}
	public void setRadicado(int radicado) {
		this.radicado = radicado;
	}
	public String getPresTotal() {
		return presTotal;
	}
	public void setPresTotal(String presTotal) {
		this.presTotal = presTotal;
	}
}
