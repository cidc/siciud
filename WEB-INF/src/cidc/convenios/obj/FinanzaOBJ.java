package cidc.convenios.obj;

import java.io.Serializable;

public class FinanzaOBJ implements Serializable{
	
	private int idfinanza;
	private long VAprobado;
	private long VAportado;
	private long VComprometer;
	private long VEjecutar;
	private long VEfectivo;
	private long VEspecie;
	private int idConvenioFinanza;
	public int getIdfinanza() {
		return idfinanza;
	}
	public void setIdfinanza(int idfinanza) {
		this.idfinanza = idfinanza;
	}
	public long getVAprobado() {
		return VAprobado;
	}
	public void setVAprobado(long vAprobado) {
		VAprobado = vAprobado;
	}
	public long getVAportado() {
		return VAportado;
	}
	public void setVAportado(long vAportado) {
		VAportado = vAportado;
	}
	public long getVComprometer() {
		return VComprometer;
	}
	public void setVComprometer(long vComprometer) {
		VComprometer = vComprometer;
	}
	public long getVEjecutar() {
		return VEjecutar;
	}
	public void setVEjecutar(long vEjecutar) {
		VEjecutar = vEjecutar;
	}
	public long getVEfectivo() {
		return VEfectivo;
	}
	public void setVEfectivo(long vEfectivo) {
		VEfectivo = vEfectivo;
	}
	public long getVEspecie() {
		return VEspecie;
	}
	public void setVEspecie(long vEspecie) {
		VEspecie = vEspecie;
	}
	public int getIdConvenioFinanza() {
		return idConvenioFinanza;
	}
	public void setIdConvenioFinanza(int idConvenioFinanza) {
		this.idConvenioFinanza = idConvenioFinanza;
	}

}
