package cidc.general.login;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
	private int perfil;
	private String perfilComp;
	private long idUsuario;
	private String nombre;
	private String nick;
	private String cedula;
	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	private String pagina;
	private List recursos;

	public boolean isPerfil(String buscar){
		boolean retorno=false;
		String perfiles=this.perfilComp;
		if(perfiles!=null){
			String []listado=perfiles.split(",");
			for(int i=0;i<listado.length;i++){
				if(listado[i].equals(""+buscar)){
					retorno=true;
					break;
				}
			}
		}
		return retorno;
	}

	public long getIdUsuario() {
		return idUsuario;
	}
	public String getNick() {
		return nick;
	}
	public String getNombre() {
		return nombre;
	}
	public int getPerfil() {
		return perfil;
	}
	public String getPerfilComp() {
		return perfilComp;
	}
	public List getRecursos() {
		return recursos;
	}
	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}
	public void setPerfilComp(String perfilComp) {
		this.perfilComp = perfilComp;
	}
	public void setRecursos(List recursos) {
		this.recursos = recursos;
	}
	public String getPagina() {
		return pagina;
	}
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
}
