package cidc.convenios.obj;

import java.io.Serializable;
import java.util.List;

import cidc.proyectosGeneral.obj.ObservacionObj;

public class Convenio implements Serializable{
	
	private int idconvenio;
	private String nombreConvenio;
	private String observaciones;
	private String fecha;
	private String tipo;
	private long codigo;
	private String fechaInicio;
	private int numDisp;

	private String fechaFinalizacion;
	private String entidades;
	private String F_Digita;
	private int V_DuraAnos;
	private int V_Duradias;
	private int V_Durameses;
	private int duracion;
	private float VEfectivo;
	private float VEspecie;
	private String N_UsuDigita;
    private String estado;
    
    private String nombreproyecto= null;
    private int estadop;
    private int tipop;
    private int facultad;
    private int proycurri;
    private int grupo;
    private String objetivo = null;
    private String resumen = null;
    
    
    private String observacionesp= null;
    
   
    
  
	
    
   
	public String getNombreproyecto() {
		return nombreproyecto;
	}
	public void setNombreproyecto(String nombreproyecto) {
		this.nombreproyecto = nombreproyecto;
	}
	public int getEstadop() {
		return estadop;
	}
	public void setEstadop(int estadop) {
		this.estadop = estadop;
	}
	public int getTipop() {
		return tipop;
	}
	public void setTipop(int tipop) {
		this.tipop = tipop;
	}
	public int getFacultad() {
		return facultad;
	}
	public void setFacultad(int facultad) {
		this.facultad = facultad;
	}
	public int getProycurri() {
		return proycurri;
	}
	public void setProycurri(int proycurri) {
		this.proycurri = proycurri;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public String getObservacionesp() {
		return observacionesp;
	}
	public void setObservacionesp(String observacionesp) {
		this.observacionesp = observacionesp;
	}
	
    
    
    
    
    
    
	public float getVEfectivo() {
		return VEfectivo;
	}
	public void setVEfectivo(float vEfectivo) {
		VEfectivo = vEfectivo;
	}
	public float getVEspecie() {
		return VEspecie;
	}
	public void setVEspecie(float vEspecie) {
		VEspecie = vEspecie;
	}
	
	

	
    
    public int getIdconvenio() {
		return idconvenio;
	}
	public void setIdconvenio(int idconvenio) {
		this.idconvenio = idconvenio;
	}
    
    public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
    
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getV_DuraAnos() {
		return V_DuraAnos;
	}
	public void setV_DuraAnos(int v_DuraAnos) {
		V_DuraAnos = v_DuraAnos;
	}
	public int getV_Duradias() {
		return V_Duradias;
	}
	public void setV_Duradias(int v_Duradias) {
		V_Duradias = v_Duradias;
	}
	public int getV_Durameses() {
		return V_Durameses;
	}
	public void setV_Durameses(int v_Durameses) {
		V_Durameses = v_Durameses;
	}
	
	
	
	public String getF_Digita() {
		return F_Digita;
	}
	public void setF_Digita(String f_Digita) {
		F_Digita = f_Digita;
	}

	
	public String getN_UsuDigita() {
		return N_UsuDigita;
	}
	public void setN_UsuDigita(String n_UsuDigita) {
		N_UsuDigita = n_UsuDigita;
	}
	
 
	
	public String getNombreConvenio() {
		return nombreConvenio;
	}
	public void setNombreConvenio(String nombreConvenio) {
		this.nombreConvenio = nombreConvenio;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public int getNumDisp() {
		return numDisp;
	}
	public void setNumDisp(int numDisp) {
		this.numDisp = numDisp;
	}

	public String getFechaFinalizacion() {
		return fechaFinalizacion;
	}
	public void setFechaFinalizacion(String fechaFinalizacion) {
		this.fechaFinalizacion = fechaFinalizacion;
	}
	public String getEntidades() {
		return entidades;
	}
	public void setEntidades(String entidades) {
		this.entidades = entidades;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	

	
	

	



}

