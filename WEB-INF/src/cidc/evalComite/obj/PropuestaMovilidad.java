package cidc.evalMovilidad.obj;

import java.io.Serializable;

public class PropuestaMovilidad implements Serializable{

	private long idPropuesta;
	private int estado;
	private int papel;
	private String investigador;
	private String nombrePonencia;
	private double tecnologica;
	private double ingenieria;
	private double medioAmbiente;
	private double educacion;
	private double artes;
	private double director;
	private int cantEval;
	private String total;
	private TrayectoriaInvest infDocs;


	public double getArtes() {
		return artes;
	}
	public double getDirector() {
		return director;
	}
	public double getEducacion() {
		return educacion;
	}
	public int getEstado() {
		return estado;
	}
	public long getIdPropuesta() {
		return idPropuesta;
	}
	public double getIngenieria() {
		return ingenieria;
	}
	public String getInvestigador() {
		return investigador;
	}
	public double getMedioAmbiente() {
		return medioAmbiente;
	}
	public String getNombrePonencia() {
		return nombrePonencia;
	}
	public int getPapel() {
		return papel;
	}
	public double getTecnologica() {
		return tecnologica;
	}
	public String getTotal() {
		return total;
	}
	public void setArtes(double artes) {
		this.artes = artes;
	}
	public void setDirector(double director) {
		this.director = director;
	}
	public void setEducacion(double educacion) {
		this.educacion = educacion;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public void setIdPropuesta(long idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	public void setIngenieria(double ingenieria) {
		this.ingenieria = ingenieria;
	}
	public void setInvestigador(String investigador) {
		this.investigador = investigador;
	}
	public void setMedioAmbiente(double medioAmbiente) {
		this.medioAmbiente = medioAmbiente;
	}
	public void setNombrePonencia(String nombrePonencia) {
		this.nombrePonencia = nombrePonencia;
	}
	public void setPapel(int papel) {
		this.papel = papel;
	}
	public void setTecnologica(double tecnologica) {
		this.tecnologica = tecnologica;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public int getCantEval() {
		return cantEval;
	}
	public void setCantEval(int cantEval) {
		this.cantEval = cantEval;
	}
	public TrayectoriaInvest getInfDocs() {
		return infDocs;
	}
	public void setInfDocs(TrayectoriaInvest infDocs) {
		this.infDocs = infDocs;
	}

}
