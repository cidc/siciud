package cidc.convenios.obj;

import java.io.Serializable;
import java.util.List;



public class GetConvenioOBJ implements Serializable{
	
	private String idconvenio;
	private String nombreConvenio;
	private String observaciones;
	private String fecha;
	private String tipo;
	private String codigo;
	private String fechaInicio;
	private String numDisp;

	private String fechaFinalizacion;
	private String entidades;
	private String F_Digita;
	private String V_DuraAnos;
	private String V_Duradias;
	private String V_Durameses;
	private String VAprobado;
	private String N_UsuDigita;
    private String estado;
    
    private String nombreproyecto;
    private String estadop;
    private String tipop;
    private String facultad;
    private String proycurri;
    private String grupo;
    private String investigador;
    private String objetivo;
    private String resumen;
   
    
    private FinanzaOBJ finanza=null;
    private List<ObservacionesOBJ> listaObservaciones=null;
    private List<TiemposOBJ> listaTiempos=null;   
    private List<PersonaOBJ> listaPersonas=null;
    private List<PersonaOBJ> listagrupos=null;
    private List<EntidadAsociadaOBJ> listaentidadesConv=null;
    
    public List<PersonaOBJ> getListagrupos() {
		return listagrupos;
	}
	public void setListagrupos(List<PersonaOBJ> listagrupos) {
		this.listagrupos = listagrupos;
	}
	
    
    public List<ObservacionesOBJ> getListaObservaciones() {
		return listaObservaciones;
	}
	public void setListaObservaciones(List<ObservacionesOBJ> listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}
	
	public List<TiemposOBJ> getListaTiempos() {
		return listaTiempos;
	}
	public void setListaTiempos(List<TiemposOBJ> listaTiempos) {
		this.listaTiempos = listaTiempos;
	}
	
	public List<PersonaOBJ> getListaPersonas() {
		return listaPersonas;
	}
	public void setListaPersonas(List<PersonaOBJ> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}


    
	public String getIdconvenio() {
		return idconvenio;
	}
	public void setIdconvenio(String idconvenio) {
		this.idconvenio = idconvenio;
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
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public String getNumDisp() {
		return numDisp;
	}
	public void setNumDisp(String numDisp) {
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
	public String getF_Digita() {
		return F_Digita;
	}
	public void setF_Digita(String f_Digita) {
		F_Digita = f_Digita;
	}
	public String getV_DuraAnos() {
		return V_DuraAnos;
	}
	public void setV_DuraAnos(String v_DuraAnos) {
		V_DuraAnos = v_DuraAnos;
	}
	public String getV_Duradias() {
		return V_Duradias;
	}
	public void setV_Duradias(String v_Duradias) {
		V_Duradias = v_Duradias;
	}
	public String getV_Durameses() {
		return V_Durameses;
	}
	public void setV_Durameses(String v_Durameses) {
		V_Durameses = v_Durameses;
	}
	
	
	public String getN_UsuDigita() {
		return N_UsuDigita;
	}
	public void setN_UsuDigita(String n_UsuDigita) {
		N_UsuDigita = n_UsuDigita;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreproyecto() {
		return nombreproyecto;
	}
	public void setNombreproyecto(String nombreproyecto) {
		this.nombreproyecto = nombreproyecto;
	}
	public String getEstadop() {
		return estadop;
	}
	public void setEstadop(String estadop) {
		this.estadop = estadop;
	}
	public String getTipop() {
		return tipop;
	}
	public void setTipop(String tipop) {
		this.tipop = tipop;
	}
	public String getFacultad() {
		return facultad;
	}
	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String getProycurri() {
		return proycurri;
	}
	public void setProycurri(String proycurri) {
		this.proycurri = proycurri;
	}
	public String getGrupo() {
		return grupo;
	}
	public void setGrupo(String grupo) {
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
	
	public List<EntidadAsociadaOBJ> getListaentidadesConv() {
		return listaentidadesConv;
	}
	public void setListaentidadesConv(List<EntidadAsociadaOBJ> listaentidadesConv) {
		this.listaentidadesConv = listaentidadesConv;
	}
	public String getVAprobado() {
		return VAprobado;
	}
	public void setVAprobado(String vAprobado) {
		VAprobado = vAprobado;
	}
	
	public FinanzaOBJ getFinanza() {
		return finanza;
	}
	public void setFinanza(FinanzaOBJ finanza) {
		this.finanza = finanza;
	}
	public String getInvestigador() {
		return investigador;
	}
	public void setInvestigador(String investigador) {
		this.investigador = investigador;
	}
	
	

}