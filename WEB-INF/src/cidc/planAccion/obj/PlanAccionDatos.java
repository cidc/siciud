package cidc.planAccion.obj;

public class PlanAccionDatos {
	private String nombregrupo="";
	private int idGrupo;
	private String anoinicio="";
	private String anofinal="";
	private Long idPlan;
	private String director;
	
	public Long getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(Long idPlan) {
		this.idPlan = idPlan;
	}
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getNombregrupo() {
		return nombregrupo;		
	}
	public void setNombregrupo(String nombregrupo) {
		this.nombregrupo = nombregrupo;
	}
	public String getAnoinicio() {
		return anoinicio;
	}
	public void setAnoinicio(String anoinicio) {
		this.anoinicio = anoinicio;
	}
	public String getAnofinal() {
		return anofinal;
	}
	public void setAnofinal(String anofinal) {
		this.anofinal = anofinal;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
}
