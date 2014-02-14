package cidc.inscripcionConv.obj;

import java.io.Serializable;
import java.util.List;

public class ResumenInscOBJ implements Serializable{

	private long idPropuesta;
	private String convocatoria;
	private String facultad;
	private String grupo;
	private String investigador;
	private String propuesta;
	private String propAbstract;
	private String palClaves;
	private String docAnexo;
	private String nombreDoc;
	private String fechaInscripcion;
	private int numConv;

	private List coInvestigadores;
	private List rubros;
	private List compromisos;

	public List getCompromisos() {
		return compromisos;
	}
	public void setCompromisos(List compromisos) {
		this.compromisos = compromisos;
	}
	public String getConvocatoria() {
		return convocatoria;
	}
	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public List getCoInvestigadores() {
		return coInvestigadores;
	}
	public void setCoInvestigadores(List coInvestigadores) {
		this.coInvestigadores = coInvestigadores;
	}
	public String getInvestigador() {
		return investigador;
	}
	public void setInvestigador(String investigador) {
		this.investigador = investigador;
	}
	public String getPropuesta() {
		return propuesta;
	}
	public void setPropuesta(String propuesta) {
		this.propuesta = propuesta;
	}
	public List getRubros() {
		return rubros;
	}
	public void setRubros(List rubros) {
		this.rubros = rubros;
	}
	public String getPalClaves() {
		return palClaves;
	}
	public void setPalClaves(String palClaves) {
		this.palClaves = palClaves;
	}
	public String getPropAbstract() {
		return propAbstract;
	}
	public void setPropAbstract(String propAbstract) {
		this.propAbstract = propAbstract;
	}
	public long getIdPropuesta() {
		return idPropuesta;
	}
	public void setIdPropuesta(long idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	public String getDocAnexo() {
		return docAnexo;
	}
	public void setDocAnexo(String docAnexo) {
		this.docAnexo = docAnexo;
	}
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public int getNumConv() {
		return numConv;
	}
	public void setNumConv(int numConv) {
		this.numConv = numConv;
	}
	public String getNombreDoc() {
		return nombreDoc;
	}
	public void setNombreDoc(String nombreDoc) {
		this.nombreDoc = nombreDoc;
	}
	
}
