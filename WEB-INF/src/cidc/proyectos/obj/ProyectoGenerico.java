package cidc.proyectos.obj;

import java.io.Serializable;
import java.util.List;

public class ProyectoGenerico implements Serializable{

	private long idProyecto;
	private String ano;
	private String consecContrato;
	private String consecActa;
	private String codigo;
	private String director;
	private String estado;
	private String nombre;
	private String facultad;
	private String proyCurricular;
	private String fecAprobacion;
	private String fecActaInicio;
	private String fecActaFin;
	private String fecContrato;
	private String grupoInvestigacion;
	private String duracion;
	private String valorLetras;
	private String numConvocatoria;
	private String convocatoria;
	private String archivoProp;
	private String terminosRef;
	private String observacionDigit;

	private Documento propuesta;
	private Documento contrato;
	private Documento actaInicio;
	private Documento actaFinal;
	private Documento informeFinal;
	private Documento informeParcial;

	private int flag;
	private int tipo; //1) nuevo 2) antiguo
	private int tipo2; // 1)proyecto  2)convenio

	private List compromisosConv=null;
	private List documentos=null;
	private List listaObservaciones=null;
	private List coInvestigadores=null;
	private List listaCambios=null;

	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List getCoInvestigadores() {
		return coInvestigadores;
	}
	public void setCoInvestigadores(List coInvestigadores) {
		this.coInvestigadores = coInvestigadores;
	}
	public String getConsecActa() {
		return consecActa;
	}
	public void setConsecActa(String consecActa) {
		this.consecActa = consecActa;
	}
	public String getConsecContrato() {
		return consecContrato;
	}
	public void setConsecContrato(String consecContrato) {
		this.consecContrato = consecContrato;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDuracion() {
		return duracion;
	}
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String getFecActaFin() {
		return fecActaFin;
	}
	public void setFecActaFin(String fecActaFin) {
		this.fecActaFin = fecActaFin;
	}
	public String getFecActaInicio() {
		return fecActaInicio;
	}
	public void setFecActaInicio(String fecActaInicio) {
		this.fecActaInicio = fecActaInicio;
	}
	public String getFecAprobacion() {
		return fecAprobacion;
	}
	public void setFecAprobacion(String fecAprobacion) {
		this.fecAprobacion = fecAprobacion;
	}
	public String getFecContrato() {
		return fecContrato;
	}
	public void setFecContrato(String fecContrato) {
		this.fecContrato = fecContrato;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public String getGrupoInvestigacion() {
		return grupoInvestigacion;
	}
	public void setGrupoInvestigacion(String grupoInvestigacion) {
		this.grupoInvestigacion = grupoInvestigacion;
	}
	public long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(long idProyecto) {
		this.idProyecto = idProyecto;
	}
	public List getListaObservaciones() {
		return listaObservaciones;
	}
	public void setListaObservaciones(List listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}
	public String getNumConvocatoria() {
		return numConvocatoria;
	}
	public void setNumConvocatoria(String numConvocatoria) {
		this.numConvocatoria = numConvocatoria;
	}
	public String getProyCurricular() {
		return proyCurricular;
	}
	public void setProyCurricular(String proyCurricular) {
		this.proyCurricular = proyCurricular;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getValorLetras() {
		return valorLetras;
	}
	public void setValorLetras(String valorLetras) {
		this.valorLetras = valorLetras;
	}
	public String getArchivoProp() {
		return archivoProp;
	}
	public void setArchivoProp(String archivoProp) {
		this.archivoProp = archivoProp;
	}
	public String getConvocatoria() {
		return convocatoria;
	}
	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}
	public String getTerminosRef() {
		return terminosRef;
	}
	public void setTerminosRef(String terminosRef) {
		this.terminosRef = terminosRef;
	}
	public List getCompromisosConv() {
		return compromisosConv;
	}
	public void setCompromisosConv(List compromisosConv) {
		this.compromisosConv = compromisosConv;
	}
	public List getListaCambios() {
		return listaCambios;
	}
	public void setListaCambios(List listaCambios) {
		this.listaCambios = listaCambios;
	}
	public String getObservacionDigit() {
		return observacionDigit;
	}
	public void setObservacionDigit(String observacionDigit) {
		this.observacionDigit = observacionDigit;
	}
	public Documento getActaFinal() {
		return actaFinal;
	}
	public void setActaFinal(Documento actaFinal) {
		this.actaFinal = actaFinal;
	}
	public Documento getActaInicio() {
		return actaInicio;
	}
	public void setActaInicio(Documento actaInicio) {
		this.actaInicio = actaInicio;
	}
	public Documento getContrato() {
		return contrato;
	}
	public void setContrato(Documento contrato) {
		this.contrato = contrato;
	}
	public Documento getInformeFinal() {
		return informeFinal;
	}
	public void setInformeFinal(Documento informeFinal) {
		this.informeFinal = informeFinal;
	}
	public Documento getInformeParcial() {
		return informeParcial;
	}
	public void setInformeParcial(Documento informeParcial) {
		this.informeParcial = informeParcial;
	}
	public Documento getPropuesta() {
		return propuesta;
	}
	public void setPropuesta(Documento propuesta) {
		this.propuesta = propuesta;
	}
	public int getTipo2() {
		return tipo2;
	}
	public void setTipo2(int tipo2) {
		this.tipo2 = tipo2;
	}
	

}

