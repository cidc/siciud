package cidc.certificaciones.obj;

public class ProyectoActividad {
	
	String nombreProyect;
	int tipoPersona; //indica si la persona es director o investigador principal
	int codigo;
	String tipoProy; // indica si es un proyecto digitalizado o no
	
	public ProyectoActividad(){
		
	}

	public String getNombre() {
		return nombreProyect;
	}

	public void setNombre(String nombre) {
		this.nombreProyect = nombre;
	}

	public int getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(int tipo) {
		this.tipoPersona = tipo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipoProy() {
		return tipoProy;
	}

	public void setTipoProy(String tipoProy) {
		this.tipoProy = tipoProy;
	}
	
	private void syso() {
		// TODO Auto-generated method stub

	}
	
}
