package cidc.adminPropuestas.obj;

public class FiltroEvaluadorOBJ {
	
	private String codInvest;
	private String nombres;
	private String apellidos;
	private String areasTrabajo;
	private String facultad;
	private String proyectoCur;
	private int orden;
	private int tipEval;
	
	
	public String getApellidos() {
		return (apellidos!=null)? apellidos:"%";
	}
	public void setApellidos(String apellidos) {
		this.apellidos = "%"+apellidos+"%";
	}
	public int getTipEval() {
		return tipEval;
	}
	public void setTipEval(int tipEval) {
		this.tipEval = tipEval;
	}
	public String getAreasTrabajo() {
		return (areasTrabajo!=null) ? areasTrabajo:"%";
	}
	public void setAreasTrabajo(String areasTrabajo) {
		this.areasTrabajo = "%"+areasTrabajo+"%";
	}
	public String getCodInvest() {
		return codInvest;
	}
	public void setCodInvest(String codInvest) {
		this.codInvest = codInvest;
	}
	public String getFacultad() {
		return (facultad!=null) ? facultad:"%";
	}
	public void setFacultad(String facultad) {
		this.facultad = "%"+facultad+"%";
	}
	public String getNombres() {
		return (nombres!=null) ? nombres:"%";
	}
	public void setNombres(String nombres) {
		this.nombres = "%"+nombres+"%";
	}
	public String getProyectoCur() {
		return (proyectoCur!=null) ? proyectoCur:"%";
	}
	public void setProyectoCur(String proyectoCur) {
		this.proyectoCur = "%"+proyectoCur+"%";
	}
	public int getOrden() {
		return orden;
	}
	public void setOrden(int orden) {
		this.orden = orden;
	}
	
		
}
