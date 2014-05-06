package cidc.adminEntidad.obj;

import java.io.Serializable;



public class TelefonosOBJ implements Serializable {
	
	private int id;
	private String telefono = null;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
	

}
