package cidc.proyectosGeneral.obj;

import java.io.Serializable;

import java.util.List;

public class Proyecto implements Serializable{
	
	private int claseProyecto;
	
	//************Campos Genéricos*****************************
	
	private int id;
	private int idPropuesta;
	private int ano;
	private String codigo;
	private String nombreProyecto;	
	//Determina la cantidad de dinero aprobado en la propuesta
	private String aprobadoins;
	private String director;
	private String proyecto;
	private String facultad;
	private String proyCurricular;
	private String fecAprobacion;
	private String grupoInvestigacion;
	private int idGrupo;	
	private String duracion;
	private String valor; // hace referencia al valor del proyecto o total aprobado
	private String convocatoria;
	private String numConvocatoria;
	private int estado;
	private int flag;
	private int tipoProyecto; //proyecto de convocatoria (1) o convenio (2)
	private String fecEstimadoFin; //una fecha estimaci�n de fin de proyecto a partir de la duraci�n y las prorrogas
	//*********************************************************
	
	//************** Listas Genéricas *************************
	
	private List<CoInvest> listaCoInvestigadores=null;
	private List<ObservacionObj> listaObservaciones=null;
	private List<CompromisosOBJ> listaCompromisos=null;

	private List<Tiempos> listaTiempos=null;
	
	//*********************************************************
	
	//******** Campos para creación de contrato nuevo **********************************
	private String consecContrato;
	private String consecActa;		
	private String fecActaInicio;
	private String fecActaFin;
	private String fecContrato;	
	private String cedulaDir;
	private String cedulaDirDe;	
	private String duracionLetras;
	private String ejecucion;
	private String ejecucionLetras;
	private String informes;
	private String informesLetras;
	private String evaluacion;
	private String evaluacionLetras;	
	private String valorLetras;
	private String sesion;	
	private String compromisosConv;
	private String objContrato;
	private String archivo;
	private String termRefConvo;	
	private int principal;
	private String []papel;	
	private int idActaFin;
	private String anCortoActa;
	private String anCortoContrato;		
	private String fecActa;
	//En caso de que el contrato tenga un Gestor Financiero
	private String gestor;
	private String cedulaGestor;
	private String cedulaGestorDe;
	private boolean gestorfinanciero;
	private String tutor;
	private String cedulaTutor;
	private String cedulaTutorDe;
	//En caso de que el contrato tenga REALIZADOR
	private String realizador;
	private String cedulaRealizador;
	private String cedulaRealizadorDe;	
	
	
	////*********************************************************
	
		
	

	public String getFecEstimadoFin() {
		return fecEstimadoFin;
	}

	public String getFecActa() {
		return fecActa;
	}

	public void setListaObservaciones(List<ObservacionObj> listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}

/*	public void setListaCompromisos(List<String> listaCompromisos) {
		this.listaCompromisos = listaCompromisos;
	}
*/
	public void setFecActa(String fecActa) {
		this.fecActa = fecActa;
	}

	public void setListaCoInvestigadores(List<CoInvest> listaCoInvestigadores) {
		this.listaCoInvestigadores = listaCoInvestigadores;
	}

	public void setFecEstimadoFin(String fecEstimadoFin) {
		this.fecEstimadoFin = fecEstimadoFin;
	}

	public int getIdPropuesta() {
		return idPropuesta;
	}

	public void setIdPropuesta(int idPropuesta) {
		this.idPropuesta = idPropuesta;
	}

	public int getTipoProyecto() {
		return tipoProyecto;
	}

	public void setTipoProyecto(int tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

/*	public List<String> getListaCompromisos() {
		return listaCompromisos;
	}

	public void setListaCompromisoss(List<String> listaCompromisos) {
		this.listaCompromisos = listaCompromisos;
	}
*/	
	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public int getClaseProyecto() {
		return claseProyecto;
	}

	public void setClaseProyecto(int claseProyecto) {
		this.claseProyecto = claseProyecto;
	}
	
	public int getId() {
		return id;
	}

	public int getAno() {
		return ano;
	}

	public String getConsecContrato() {
		return consecContrato;
	}

	public String getConsecActa() {
		return consecActa;
	}

	public String getCodigo() {
		return codigo;
	}

	public String getDirector() {
		return director;
	}

	public String getProyecto() {
		return proyecto;
	}

	public String getFacultad() {
		return facultad;
	}

	public String getProyCurricular() {
		return proyCurricular;
	}

	public String getFecAprobacion() {
		return fecAprobacion;
	}

	public String getFecActaInicio() {
		return fecActaInicio;
	}

	public String getFecActaFin() {
		return fecActaFin;
	}

	public String getFecContrato() {
		return fecContrato;
	}

	public String getGrupoInvestigacion() {
		return grupoInvestigacion;
	}

	public int getIdGrupo() {
		return idGrupo;
	}

	public String getCedulaDir() {
		return cedulaDir;
	}

	public String getCedulaDirDe() {
		return cedulaDirDe;
	}

	public String getDuracion() {
		return duracion;
	}

	public String getDuracionLetras() {
		return duracionLetras;
	}

	public String getEjecucion() {
		return ejecucion;
	}

	public String getEjecucionLetras() {
		return ejecucionLetras;
	}

	public String getInformes() {
		return informes;
	}

	public String getInformesLetras() {
		return informesLetras;
	}

	public String getEvaluacion() {
		return evaluacion;
	}

	public String getEvaluacionLetras() {
		return evaluacionLetras;
	}

	public String getValor() {
		return valor;
	}

	public String getValorLetras() {
		return valorLetras;
	}

	public String getSesion() {
		return sesion;
	}

	public String getConvocatoria() {
		return convocatoria;
	}

	public String getNumConvocatoria() {
		return numConvocatoria;
	}

	public String getCompromisosConv() {
		return compromisosConv;
	}

	public String getObjContrato() {
		return objContrato;
	}

	public String getArchivo() {
		return archivo;
	}

	public String getTermRefConvo() {
		return termRefConvo;
	}

	public List<CoInvest> getListaCoInvestigadores() {
		return listaCoInvestigadores;
	}

	public int getPrincipal() {
		return principal;
	}

	public String[] getPapel() {
		return papel;
	}

	public int getEstado() {
		return estado;
	}

	public int getIdActaFin() {
		return idActaFin;
	}

	public String getAnCortoActa() {
		return anCortoActa;
	}

	public String getAnCortoContrato() {
		return anCortoContrato;
	}

	public int getFlag() {
		return flag;
	}

	public List getListaObservaciones() {
		return listaObservaciones;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public void setConsecContrato(String consecContrato) {
		this.consecContrato = consecContrato;
	}

	public void setConsecActa(String consecActa) {
		this.consecActa = consecActa;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}

	public void setProyCurricular(String proyCurricular) {
		this.proyCurricular = proyCurricular;
	}

	public void setFecAprobacion(String fecAprobacion) {
		this.fecAprobacion = fecAprobacion;
	}

	public void setFecActaInicio(String fecActaInicio) {
		this.fecActaInicio = fecActaInicio;
		setAnCortoActa(fecActaInicio.substring(2,4));
	}

	public void setFecActaFin(String fecActaFin) {
		this.fecActaFin = fecActaFin;
	}

	public void setFecContrato(String fecContrato) {
		this.fecContrato = fecContrato;
		setAnCortoContrato(fecContrato.substring(2,4));
	}

	public void setGrupoInvestigacion(String grupoInvestigacion) {
		this.grupoInvestigacion = grupoInvestigacion;
	}

	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}

	public void setCedulaDir(String cedulaDir) {
		this.cedulaDir = cedulaDir;
	}

	public void setCedulaDirDe(String cedulaDirDe) {
		this.cedulaDirDe = cedulaDirDe;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public void setDuracionLetras(String duracionLetras) {
		this.duracionLetras = duracionLetras;
	}

	public void setEjecucion(String ejecucion) {
		this.ejecucion = ejecucion;
	}

	public void setEjecucionLetras(String ejecucionLetras) {
		this.ejecucionLetras = ejecucionLetras;
	}

	public void setInformes(String informes) {
		this.informes = informes;
	}

	public void setInformesLetras(String informesLetras) {
		this.informesLetras = informesLetras;
	}

	public void setEvaluacion(String evaluacion) {
		this.evaluacion = evaluacion;
	}

	public void setEvaluacionLetras(String evaluacionLetras) {
		this.evaluacionLetras = evaluacionLetras;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void setValorLetras(String valorLetras) {
		this.valorLetras = valorLetras;
	}

	public void setSesion(String sesion) {
		this.sesion = sesion;
	}

	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}

	public void setNumConvocatoria(String numConvocatoria) {
		this.numConvocatoria = numConvocatoria;
	}

	public void setCompromisosConv(String compromisosConv) {
		this.compromisosConv = compromisosConv;
	}

	public void setObjContrato(String objContrato) {
		this.objContrato = objContrato;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	public void setTermRefConvo(String termRefConvo) {
		this.termRefConvo = termRefConvo;
	}

	public void setPrincipal(int principal) {
		this.principal = principal;
	}

	public void setPapel(String[] papel) {
		this.papel = papel;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public void setIdActaFin(int idActaFin) {
		this.idActaFin = idActaFin;
	}

	public void setAnCortoActa(String anCortoActa) {
		this.anCortoActa = anCortoActa;
	}

	public void setAnCortoContrato(String anCortoContrato) {
		this.anCortoContrato = anCortoContrato;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List<CompromisosOBJ> getListaCompromisos() {
		return listaCompromisos;
	}

	public void setListaCompromisos(List<CompromisosOBJ> listaCompromisos) {
		this.listaCompromisos = listaCompromisos;
	}
	public List<Tiempos> getListaTiempos() {
		return listaTiempos;
	}

	public void setListaTiempos(List<Tiempos> listaTiempos) {
		this.listaTiempos = listaTiempos;
	}
	public String getAprobadoins() {
		return aprobadoins;
	}

	public void setAprobadoins(String aprobadoins) {
		this.aprobadoins = aprobadoins;
	}

	public String getGestor() {
		return gestor;
	}

	public void setGestor(String gestor) {
		this.gestor = gestor;
	}

	public String getCedulaGestor() {
		return cedulaGestor;
	}

	public void setCedulaGestor(String cedulaGestor) {
		this.cedulaGestor = cedulaGestor;
	}

	public String getCedulaGestorDe() {
		return cedulaGestorDe;
	}

	public void setCedulaGestorDe(String cedulaGestorDe) {
		this.cedulaGestorDe = cedulaGestorDe;
	}

	public boolean isGestorfinanciero() {
		return gestorfinanciero;
	}

	public void setGestorfinanciero(boolean gestorfinanciero) {
		this.gestorfinanciero = gestorfinanciero;
	}

	public String getRealizador() {
		return realizador;
	}

	public void setRealizador(String realizador) {
		this.realizador = realizador;
	}

	public String getCedulaRealizador() {
		return cedulaRealizador;
	}

	public void setCedulaRealizador(String cedulaRealizador) {
		this.cedulaRealizador = cedulaRealizador;
	}

	public String getCedulaRealizadorDe() {
		return cedulaRealizadorDe;
	}

	public void setCedulaRealizadorDe(String cedulaRealizadorDe) {
		this.cedulaRealizadorDe = cedulaRealizadorDe;
	}

	public String getTutor() {
		return tutor;
	}

	public void setTutor(String tutor) {
		this.tutor = tutor;
	}

	public String getCedulaTutor() {
		return cedulaTutor;
	}

	public void setCedulaTutor(String cedulaTutor) {
		this.cedulaTutor = cedulaTutor;
	}

	public String getCedulaTutorDe() {
		return cedulaTutorDe;
	}

	public void setCedulaTutorDe(String cedulaTutorDe) {
		this.cedulaTutorDe = cedulaTutorDe;
	}		
}

