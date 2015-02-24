package cidc.convenios.obj;

import java.io.Serializable;

public class CdpOBJ implements Serializable{
	
	public int getIdcdp() {
		return idcdp;
	}
	public void setIdcdp(int idcdp) {
		this.idcdp = idcdp;
	}
	private int idcdp;
	private String codigo;
	private String nombre;
	private String fechaRegistro;
	private String observacion;
	private int usudigita;
	private int valortotal;
	private int valorejecutado;
	private int financiero;
	private int [] idcdpValores;
	private int [] valores;
	private int [] reembolsoEntidad;
	private int reembolsototal;
	private String valorTotalString;
	private String valorDisponible;
	private String valorEjecutadoString;
	private String reembolsoTotalString;
	private String saldoDisponible;
	private String[] valoresString;
	
	public int[] getValores() {
		return valores;
	}
	public void setValores(int[] valores) {
		this.valores = valores;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(String fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int getUsudigita() {
		return usudigita;
	}
	public void setUsudigita(int usudigita) {
		this.usudigita = usudigita;
	}
	public int getValortotal() {
		return valortotal;
	}
	public void setValortotal(int valortotal) {
		this.valortotal = valortotal;
	}
	public int getFinanciero() {
		return financiero;
	}
	public void setFinanciero(int financiero) {
		this.financiero = financiero;
	}
	public int getValorejecutado() {
		return valorejecutado;
	}
	public void setValorejecutado(int valorejecutado) {
		this.valorejecutado = valorejecutado;
	}
	public int[] getIdcdpValores() {
		return idcdpValores;
	}
	public void setIdcdpValores(int[] idcdpValores) {
		this.idcdpValores = idcdpValores;
	}
	public int getReembolsototal() {
		return reembolsototal;
	}
	public void setReembolsototal(int reembolsototal) {
		this.reembolsototal = reembolsototal;
	}
	public int[] getReembolsoEntidad() {
		return reembolsoEntidad;
	}
	public void setReembolsoEntidad(int[] reembolsoEntidad) {
		this.reembolsoEntidad = reembolsoEntidad;
	}
	public String getValorTotalString() {
		return valorTotalString;
	}
	public void setValorTotalString(String valorTotalString) {
		this.valorTotalString = valorTotalString;
	}
	public String getValorDisponible() {
		return valorDisponible;
	}
	public void setValorDisponible(String valorDisponible) {
		this.valorDisponible = valorDisponible;
	}
	public String getValorEjecutadoString() {
		return valorEjecutadoString;
	}
	public void setValorEjecutadoString(String valorEjecutadoString) {
		this.valorEjecutadoString = valorEjecutadoString;
	}
	public String getReembolsoTotalString() {
		return reembolsoTotalString;
	}
	public void setReembolsoTotalString(String reembolsoTotalString) {
		this.reembolsoTotalString = reembolsoTotalString;
	}
	public String getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(String saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}
	public String[] getValoresString() {
		return valoresString;
	}
	public void setValoresString(String[] valoresString) {
		this.valoresString = valoresString;
	}
	
}
