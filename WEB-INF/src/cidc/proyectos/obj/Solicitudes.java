package cidc.proyectos.obj;

import java.util.Date;

public class Solicitudes {

	private int idSolicitud;
	private int idPersona;
	private String numeroCaso;
	private int idProyecto;
	private int tipoProyecto;
	private Date fechaSolicitud;
	private String tipoSolicitud;
	private String nombreProyecto;
	private String urlDoc; 
	
	public Solicitudes(){
		
	}

	public int getIdSolicitud() {
		return idSolicitud;
	}

	public void setIdSolicitud(int idSolicitud) {
		this.idSolicitud = idSolicitud;
	}

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public String getNumeroCaso() {
		return numeroCaso;
	}

	public void setNumeroCaso(String numeroCaso) {
		this.numeroCaso = numeroCaso;
	}

	public int getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public int getTipoProyecto() {
		return tipoProyecto;
	}

	public void setTipoProyecto(int tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getTipoSolicitud() {
		return tipoSolicitud;
	}

	public void setTipoSolicitud(String tipoSolicitud) {
		this.tipoSolicitud = tipoSolicitud;
	}

	public String getUrlDoc() {
		return urlDoc;
	}

	public void setUrlDoc(String urlDoc) {
		this.urlDoc = urlDoc;
	}

	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	
}
