package cidc.adminPropuestas.obj;

public class ListaEvaluadorOBJ {
	
	private long codEval;
	private String nombreEval;
	private String fechaAsig;
	private String fechaResp;
	private String fechaCancela;
	private int estado;
	
	public long getCodEval() {
		return codEval;
	}
	public void setCodEval(long codEval) {
		this.codEval = codEval;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getFechaAsig() {
		return fechaAsig;
	}
	public void setFechaAsig(String fechaAsig) {
		this.fechaAsig = fechaAsig;
	}
	public String getFechaCancela() {
		return fechaCancela;
	}
	public void setFechaCancela(String fechaCancela) {
		this.fechaCancela = fechaCancela;
	}
	public String getFechaResp() {
		return fechaResp;
	}
	public void setFechaResp(String fechaResp) {
		this.fechaResp = fechaResp;
	}
	public String getNombreEval() {
		return nombreEval;
	}
	public void setNombreEval(String nombreEval) {
		this.nombreEval = nombreEval;
	}
	
}