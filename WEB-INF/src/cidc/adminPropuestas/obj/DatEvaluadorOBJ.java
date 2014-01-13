package cidc.adminPropuestas.obj;

import java.util.List;

public class DatEvaluadorOBJ {

	private int tipo;
	private String codigo;
	private String nombres ;
	private String apellidos;
	private String mail;
	private String telefono;
	private String celular;
	private List areasTrabajo;
	private String facultad;
	private String grupoInvest;
	private String proyectoCur;

	public String getApellidos() {
		return apellidos;
	}
	public String getCelular() {
		return celular;
	}
	public String getCodigo() {
		return codigo;
	}
	public String getFacultad() {
		return facultad;
	}
	public String getMail() {
		return mail;
	}
	public String getNombres() {
		return nombres;
	}
	public String getProyectoCur() {
		return proyectoCur;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public List getAreasTrabajo() {
		return areasTrabajo;
	}
	public void setAreasTrabajo(List areasTrabajo) {
		this.areasTrabajo = areasTrabajo;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public void setProyectoCur(String proyectoCur) {
		this.proyectoCur = proyectoCur;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String getGrupoInvest() {
		return grupoInvest;
	}
	public void setGrupoInvest(String grupoInvest) {
		this.grupoInvest = grupoInvest;
	}

}

