package cidc.planAccion.obj;

public class Actividades {
	
	private long idActividad;
	private long idPlan;
	private String actividad;
	private String descripcion;
	private String meta;
	private long idCriterio;
	private int porcentaje;
	
	public long getIdActividad() {
		return idActividad;
	}
	public void setIdActividad(long idActividad) {
		this.idActividad = idActividad;
	}
	public long getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(long idPlan) {
		this.idPlan = idPlan;
	}
	public String getActividad() {
		return actividad;
	}
	public void setActividad(String actividad) {
		this.actividad = actividad.trim();
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion.trim();
	}
	public String getMeta() {
		return meta;
	}
	public void setMeta(String meta) {
		this.meta = meta.trim();
	}
	public long getIdCriterio() {
		return idCriterio;
	}
	public void setIdCriterio(long idCriterio) {
		this.idCriterio = idCriterio;
	}
	public int getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(int porcentaje) {
		this.porcentaje = porcentaje;
	}	
	
}
