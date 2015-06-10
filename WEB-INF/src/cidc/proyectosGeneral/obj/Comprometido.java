package cidc.proyectosGeneral.obj;

import java.util.Date;

public class Comprometido {

	private int idCompr;
	private int valorCompr;
	private String numNecesidad;
	private String numCDP;
	private String numRP;
	private Date fechaCDP_d;
	private Date fechaRP_d;
	private Date fechaNecesidad_d;
	private String fechaCDP;
	private String fechaRP;
	private String fechaNecesidad;
	private String Observaciones;
	
	public Comprometido(){
		numCDP="0";
		numCDP="0";
		numRP="0";
	}

	public int getIdCompr() {
		return idCompr;
	}

	public void setIdCompr(int idCompr) {
		this.idCompr = idCompr;
	}

	public int getValorCompr() {
		return valorCompr;
	}

	public void setValorCompr(int valorCompr) {
		this.valorCompr = valorCompr;
	}

	public String getNumNecesidad() {
		return numNecesidad;
	}

	public void setNumNecesidad(String numNecesidad) {
		this.numNecesidad = numNecesidad;
	}

	public String getNumCDP() {
		return numCDP;
	}

	public void setNumCDP(String numCDP) {
		this.numCDP = numCDP;
	}

	public String getNumRP() {
		return numRP;
	}

	public void setNumRP(String numRP) {
		this.numRP = numRP;
	}

	public Date getFechaCDP_d() {
		return fechaCDP_d;
	}

	public void setFechaCDP_d(Date fechaCDP_d) {
		this.fechaCDP_d = fechaCDP_d;
	}

	public Date getFechaRP_d() {
		return fechaRP_d;
	}

	public void setFechaRP_d(Date fechaRP_d) {
		this.fechaRP_d = fechaRP_d;
	}

	public Date getFechaNecesidad_d() {
		return fechaNecesidad_d;
	}

	public void setFechaNecesidad_d(Date fechaNecesidad_d) {
		this.fechaNecesidad_d = fechaNecesidad_d;
	}

	public String getFechaCDP() {
		return fechaCDP;
	}

	public void setFechaCDP(String fechaCDP) {
		this.fechaCDP = fechaCDP;
	}

	public String getFechaRP() {
		return fechaRP;
	}

	public void setFechaRP(String fechaRP) {
		this.fechaRP = fechaRP;
	}

	public String getFechaNecesidad() {
		return fechaNecesidad;
	}

	public void setFechaNecesidad(String fechaNecesidad) {
		this.fechaNecesidad = fechaNecesidad;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}
	
}
