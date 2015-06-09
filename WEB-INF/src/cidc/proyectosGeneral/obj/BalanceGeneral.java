package cidc.proyectosGeneral.obj;

import java.io.Serializable;
import java.util.List;

public class BalanceGeneral implements Serializable{
	
	long idProyecto;
	String totalAprobado;
	String totalEjecutado;
	String totalSaldo;
	String totalComprometido;
	
	public String getTotalEjecutado() {
		return totalEjecutado;
	}
	public String getTotalSaldo() {
		return totalSaldo;
	}
	public void setTotalEjecutado(String totalEjecutado) {
		this.totalEjecutado = totalEjecutado;
	}
	public void setTotalSaldo(String totalSaldo) {
		this.totalSaldo = totalSaldo;
	}
	List<Rubros> listaRubros;
	
	public long getIdProyecto() {
		return idProyecto;
	}
	public String getTotalAprobado() {
		return totalAprobado;
	}
	public List<Rubros> getListaRubros() {
		return listaRubros;
	}
	public void setIdProyecto(long idProyecto) {
		this.idProyecto = idProyecto;
	}
	public void setTotalAprobado(String totalAprobado) {
		this.totalAprobado = totalAprobado;
	}
	public void setListaRubros(List<Rubros> listaRubros) {
		this.listaRubros = listaRubros;
	}
	public String getTotalComprometido() {
		return totalComprometido;
	}
	public void setTotalComprometido(String totalComprometido) {
		this.totalComprometido = totalComprometido;
	}
	
}
