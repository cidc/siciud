package cidc.adminPropuestas.obj;

public class DatoConvocatoriaOBJ {
	
	private String estado;
	private String codConvocatoria;
	private String tipo;
	private boolean evalua;
	
	public String getCodConvocatoria() {
		return codConvocatoria;
	}
	public void setCodConvocatoria(String codConvocatoria) {
		this.codConvocatoria = codConvocatoria;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isEvalua() {
		return evalua;
	}
	public void setEvalua(boolean evalua) {
		this.evalua = evalua;
	}
		
}