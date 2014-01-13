package cidc.adminPropuestas.obj;

public class PropuestaOBJ {

	private boolean convAbierta;
	private long codPropuesta ;
	private String nomDirector;
	private String nomPropuesta;
	private int estadoEvalInt;
	private int estadoEvalExt;
	private int estadoEvalComit;
	private boolean propActiva;

	public long getCodPropuesta() {
		return codPropuesta;
	}
	public void setCodPropuesta(long codPropuesta) {
		this.codPropuesta = codPropuesta;
	}
	public boolean isConvAbierta() {
		return convAbierta;
	}
	public void setConvAbierta(boolean convAbierta) {
		this.convAbierta = convAbierta;
	}
	public int getEstadoEvalComit() {
		return estadoEvalComit;
	}
	public void setEstadoEvalComit(int estadoEvalComit) {
		this.estadoEvalComit = estadoEvalComit;
	}
	public int getEstadoEvalExt() {
		return estadoEvalExt;
	}
	public void setEstadoEvalExt(int estadoEvalExt) {
		this.estadoEvalExt = estadoEvalExt;
	}
	public int getEstadoEvalInt() {
		return estadoEvalInt;
	}
	public void setEstadoEvalInt(int estadoEvalInt) {
		this.estadoEvalInt = estadoEvalInt;
	}
	public String getNomDirector() {
		return nomDirector;
	}
	public void setNomDirector(String nomDirector) {
		this.nomDirector = nomDirector;
	}
	public String getNomPropuesta() {
		return nomPropuesta;
	}
	public void setNomPropuesta(String nomPropuesta) {
		this.nomPropuesta = nomPropuesta;
	}
	public boolean isPropActiva() {
		return propActiva;
	}
	public void setPropActiva(boolean propActiva) {
		this.propActiva = propActiva;
	}
}
