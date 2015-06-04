package cidc.proyectosGeneral.obj;

import java.util.Date;

public class Comprometido {

	private int idCompr;
	private int valorCompr;
	private int numCDP;
	private int numRP;
	private Date fechaCDP;
	private Date fechaRP;
	private String Observaciones;
	
	public Comprometido(){
		
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

	public int getNumCDP() {
		return numCDP;
	}

	public void setNumCDP(int numCDP) {
		this.numCDP = numCDP;
	}

	public int getNumRP() {
		return numRP;
	}

	public void setNumRP(int numRP) {
		this.numRP = numRP;
	}

	public Date getFechaCDP() {
		return fechaCDP;
	}

	public void setFechaCDP(Date fechaCDP) {
		this.fechaCDP = fechaCDP;
	}

	public Date getFechaRP() {
		return fechaRP;
	}

	public void setFechaRP(Date fechaRP) {
		this.fechaRP = fechaRP;
	}

	public String getObservaciones() {
		return Observaciones;
	}

	public void setObservaciones(String observaciones) {
		Observaciones = observaciones;
	}

	
}
