package cidc.adminPropuestas.obj;

import java.util.List;

public class EstadoPropuestaOBJ {

	private String nombrePropuesta;
	private String codInvest;
	private String nombreInvest;
	private List listaEvaluador;
	private String idEvalAceptado;
	private String fechaLoginPsw;
	private String fechaDocs;
	private String fechaEval;
	private String archivo;
	private String anexos;

	public String getFechaDocs() {
		return fechaDocs;
	}
	public void setFechaDocs(String fechaDocs) {
		this.fechaDocs = fechaDocs;
	}
	public String getFechaEval() {
		return fechaEval;
	}
	public void setFechaEval(String fechaEval) {
		this.fechaEval = fechaEval;
	}
	public List getListaEvaluador() {
		return listaEvaluador;
	}
	public void setListaEvaluador(List listaEvaluador) {
		this.listaEvaluador = listaEvaluador;
	}
	public String getFechaLoginPsw() {
		return fechaLoginPsw;
	}
	public void setFechaLoginPsw(String fechaLoginPsw) {
		this.fechaLoginPsw = fechaLoginPsw;
	}
	public String getIdEvalAceptado() {
		return idEvalAceptado;
	}
	public void setIdEvalAceptado(String idEvalAceptado) {
		this.idEvalAceptado = idEvalAceptado;
	}
	public String getNombrePropuesta() {
		return nombrePropuesta;
	}
	public void setNombrePropuesta(String nombrePropuesta) {
		this.nombrePropuesta = nombrePropuesta;
	}
	public String getCodInvest() {
		return codInvest;
	}
	public void setCodInvest(String codInvest) {
		this.codInvest = codInvest;
	}
	public String getNombreInvest() {
		return nombreInvest;
	}
	public void setNombreInvest(String nombreInvest) {
		this.nombreInvest = nombreInvest;
	}
	public String getArchivo() {
		return archivo;
	}
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}
	public String getAnexos() {
		return anexos;
	}
	public void setAnexos(String anexos) {
		this.anexos = anexos;
	}

}