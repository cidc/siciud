package cidc.convenios.obj;

import java.io.Serializable;

public class FinanzaOBJ implements Serializable{
	
	private int idfinanza;
	private int VAprobado;
	private int VAportado;
	private int VComprometer;
	private int VEjecutar;
	private int VEfectivo;
	private int VEspecie;
	private int idConvenioFinanza;
	public int getIdfinanza() {
		return idfinanza;
	}
	public void setIdfinanza(int idfinanza) {
		this.idfinanza = idfinanza;
	}
	public int getVAprobado() {
		return VAprobado;
	}
	public void setVAprobado(int vAprobado) {
		VAprobado = vAprobado;
	}
	public int getVAportado() {
		return VAportado;
	}
	public void setVAportado(int vAportado) {
		VAportado = vAportado;
	}
	public int getVComprometer() {
		return VComprometer;
	}
	public void setVComprometer(int vComprometer) {
		VComprometer = vComprometer;
	}
	public int getVEjecutar() {
		return VEjecutar;
	}
	public void setVEjecutar(int vEjecutar) {
		VEjecutar = vEjecutar;
	}
	public int getVEfectivo() {
		return VEfectivo;
	}
	public void setVEfectivo(int vEfectivo) {
		VEfectivo = vEfectivo;
	}
	public int getVEspecie() {
		return VEspecie;
	}
	public void setVEspecie(int vEspecie) {
		VEspecie = vEspecie;
	}
	public int getIdConvenioFinanza() {
		return idConvenioFinanza;
	}
	public void setIdConvenioFinanza(int idConvenioFinanza) {
		this.idConvenioFinanza = idConvenioFinanza;
	}

}
