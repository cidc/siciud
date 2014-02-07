package cidc.convenios.obj;

import java.io.Serializable;

public class ExtraDocConvenio implements Serializable{
	
	private long idDoc;
	private String fechaDoc;
	private String fechaCarga;
	private int tipo;
	private int estado;	
	private String observaciones;
	private String nombreArchivo;
	private String nombreDocumento;
	private String nombreObservaciones;
	private String usuario;
	
	private long idconvenio;
	
	public long getIdconvenio() {
		return idconvenio;
	}
	public void setIdconvenio(long idconvenio) {
		this.idconvenio = idconvenio;
	}
	public long getIdDoc() {
		return idDoc;
	}
	public void setIdDoc(long idDoc) {
		this.idDoc = idDoc;
	}
	public String getFechaDoc() {
		return fechaDoc;
	}
	public void setFechaDoc(String fechaDoc) {
		this.fechaDoc = fechaDoc;
	}
	public String getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(String fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		System.out.println("entra al setnombre");
		this.nombreArchivo = nombreArchivo;
	}
	public String getNombreDocumento() {
		return nombreDocumento;
	}
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}
	public String getNombreObservaciones() {
		System.out.println("entra al set nombre");
		return nombreObservaciones;
	}
	public void setNombreObservaciones(String nombreObservaciones) {
		this.nombreObservaciones = nombreObservaciones;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	

}
