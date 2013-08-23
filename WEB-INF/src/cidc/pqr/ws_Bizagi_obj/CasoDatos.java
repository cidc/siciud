package cidc.pqr.ws_Bizagi_obj;

import java.io.File;

public class CasoDatos {
	

	public String getTipodeSolicitante() {
		return tipodeSolicitante;
	}
	public void setTipodeSolicitante(String tipodeSolicitante) {
		this.tipodeSolicitante = tipodeSolicitante;
	}
	public String getEscaladoOtraDependencia() {
		return escaladoOtraDependencia;
	}
	private String medioDeRecepcion;
	private String tipoDeRequerimiento;
	private String asunto;
	private String descripcion;
	private String escaladoOtraDependencia;
	private File archivoCaso;
	private String fechaOriginalSolicitud;
	private String recibirNotificacionesCorreo;
	private String tipodeSolicitante;
	/****/
	private String casoId;
	private String Accion;//Apertura, SolucionEncontrada, SolicitudAceptadaPequiriente
	private String Area;//No se implementa?
	private String Dependencia;//No se implementa?
	private String solucionInmediata;
	private String medioNotificacionRespuesta;//cierre del caso
	private String respuestaCaso;
	private String archivoRespuesta;
	private String fechaApertura;
	private String fechaEstimadaCierre;
	private String fechaEjecutadoCierre;
	private String personaEncargada;
	private String estadoActividad;
	private String fechadeInicioEnActivadad;
	
	
	
	public String getCasoId() {
		return casoId;
	}
	public void setCasoId(String casoId) {
		this.casoId = casoId;
	}
	public String getFechaEjecutadoCierre() {
		return fechaEjecutadoCierre;
	}
	public void setFechaEjecutadoCierre(String fechaEjecutadoCierre) {
		this.fechaEjecutadoCierre = fechaEjecutadoCierre;
	}
	public String getEstadoActividad() {
		return estadoActividad;
	}
	public void setEstadoActividad(String estadoActividad) {
		this.estadoActividad = estadoActividad;
	}
	public String getFechadeInicioEnActivadad() {
		return fechadeInicioEnActivadad;
	}
	public void setFechadeInicioEnActivadad(String fechadeInicioEnActivadad) {
		this.fechadeInicioEnActivadad = fechadeInicioEnActivadad;
	}
	public String getMedioDeRecepcion() {
		return medioDeRecepcion;
	}
	public void setMedioDeRecepcion(String medioDeRecepcion) {
		this.medioDeRecepcion = medioDeRecepcion;
	}
	public String getTipoDeRequerimiento() {
		return tipoDeRequerimiento;
	}
	public void setTipoDeRequerimiento(String tipoDeRequerimiento) {
		this.tipoDeRequerimiento = tipoDeRequerimiento;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String isEscaladoOtraDependencia() {
		return escaladoOtraDependencia;
	}
	public void setEscaladoOtraDependencia(String escaladoOtraDependencia) {
		this.escaladoOtraDependencia = escaladoOtraDependencia;
	}
	
	public String getFechaOriginalSolicitud() {
		return fechaOriginalSolicitud;
	}
	public void setFechaOriginalSolicitud(String fechaOriginalSolicitud) {
		this.fechaOriginalSolicitud = fechaOriginalSolicitud;
	}
	public String getRecibirNotificacionesCorreo() {
		return recibirNotificacionesCorreo;
	}
	public void setRecibirNotificacionesCorreo(String recibirNotificacionesCorreo) {
		this.recibirNotificacionesCorreo = recibirNotificacionesCorreo;
	}
	public String getSolucionInmediata() {
		return solucionInmediata;
	}
	public void setSolucionInmediata(String solucionInmediata) {
		this.solucionInmediata = solucionInmediata;
	}
	public String getMedioNotificacionRespuesta() {
		return medioNotificacionRespuesta;
	}
	public void setMedioNotificacionRespuesta(String medioNotificacionRespuesta) {
		this.medioNotificacionRespuesta = medioNotificacionRespuesta;
	}
	public String getRespuestaCaso() {
		return respuestaCaso;
	}
	public void setRespuestaCaso(String respuestaCaso) {
		this.respuestaCaso = respuestaCaso;
	}
	public String getArchivoRespuesta() {
		return archivoRespuesta;
	}
	public void setArchivoRespuesta(String archivoRespuesta) {
		this.archivoRespuesta = archivoRespuesta;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getFechaEstimadaCierre() {
		return fechaEstimadaCierre;
	}
	public void setFechaEstimadaCierre(String fechaEstimadaCierre) {
		this.fechaEstimadaCierre = fechaEstimadaCierre;
	}
	public String getPersonaEncargada() {
		return personaEncargada;
	}
	public void setPersonaEncargada(String personaEncargada) {
		this.personaEncargada = personaEncargada;
	}
	public String getAccion() {
		return Accion;
	}
	public void setAccion(String accion) {
		Accion = accion;
	}
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getDependencia() {
		return Dependencia;
	}
	public void setDependencia(String dependencia) {
		Dependencia = dependencia;
	}
	public File getArchivoCaso() {
		return archivoCaso;
	}
	public void setArchivoCaso(File archivoCaso) {
		this.archivoCaso = archivoCaso;
	}

	
}
