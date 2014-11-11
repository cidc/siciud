package cidc.proyectosGeneral.obj;

import java.io.Serializable;

public class FiltroGeneralProyecto implements Serializable{

	private String facultad;
	private String proyCur;
	private String grupo;
	private String codigo;
	private String estado;
	private String tipoGrupo;
	private String tipoProyecto;
	private String convocatoriaAno;
	private String nombreProyecto;
	private String palabrasClaves;
	private String ano;
	private int tipoConvocatoria;
	
	
	
	public String getPalabrasClaves() {
		return (palabrasClaves!=null)?palabrasClaves:"%";
	}
	public void setPalabrasClaves(String palabrasClaves) {
		this.palabrasClaves = "% "+palabrasClaves+" %";
	}
	public String getTipoGrupo() {
		return (tipoGrupo!=null)?tipoGrupo:"%";
	}
	public String getTipoProyecto() {
		return (tipoProyecto!=null)?tipoProyecto:"%";
	}
	public String getConvocatoriaAno() {
		return (convocatoriaAno!=null)?convocatoriaAno:"%";
	}
	public String getNombreProyecto() {
		return (nombreProyecto!=null)?nombreProyecto:"%";
	}
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	public void setTipoProyecto(String tipoProyecto) {
		this.tipoProyecto = tipoProyecto;
	}
	public void setConvocatoriaAno(String convocatoriaAno) {
		this.convocatoriaAno = convocatoriaAno;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = "%"+nombreProyecto+"%";
	}
	public String getFacultad() {
		return (facultad!=null)?facultad:"%";
	}
	public void setFacultad(String facultad) {
		if(!facultad.equals("0"))
			this.facultad = facultad;
	}
	public String getGrupo() {
		return (grupo!=null)?grupo:"%";
	}
	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}
	public String getProyCur() {
		return (proyCur!=null)?proyCur:"%";
	}
	public void setProyCur(String proyCur) {
		this.proyCur = proyCur;
	}
	public String getCodigo() {
		return (codigo!=null)?codigo:"%";
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEstado() {
		return (estado!=null)?estado:"%";
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public int getTipoConvocatoria() {
		return tipoConvocatoria;
	}
	public void setTipoConvocatoria(int tipoConvocatoria) {
		this.tipoConvocatoria = tipoConvocatoria;
	}
	
}
