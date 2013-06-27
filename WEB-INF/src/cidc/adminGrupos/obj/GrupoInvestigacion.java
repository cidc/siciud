package cidc.adminGrupos.obj;

import java.io.Serializable;
import java.util.List;

public class GrupoInvestigacion implements Serializable{

	private long codigo;
	private long idDir;
	private int tipo;
	private int facultad;
	private String nombre;
	private String facultadTxt;	
	private String siglas;
	private String dirNombre;
	private String dirApellido;
	private String cedDirector;
	private String codUdDirector;
	private int categoria=1;
	private String fechaCreacion;
	private String mail;
	private String web;
	private String observaciones;
	private String codColciencias;
	private String mision;
	private String vision;
	private String descripcion;
	private String grupLac;
	private int estado;
	private boolean movilidad;
	private List integrantes;
	private int codproyCurr;
	private int codAreaSNIES;
	private int cgsareasnies;
	private List proyectosCurriculares;
	private List areasSNIES;
	private String fechaCreacionFacultad;
	private int numeroCIDC;
	private int numeroFac;
	private String actaCidc;
	private String actaFacultad;

	private boolean modificable;

	
	public String getCodColciencias() {
		return codColciencias;
	}
	public String getMision() {
		return mision;
	}
	public String getVision() {
		return vision;
	}
	public void setCodColciencias(String codColciencias) {
		this.codColciencias = codColciencias;
	}
	public void setMision(String mision) {
		this.mision = mision;
	}
	public void setVision(String vision) {
		this.vision = vision;
	}
	public List getIntegrantes() {
		return integrantes;
	}
	public void setIntegrantes(List integrantes) {
		this.integrantes = integrantes;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getGrupLac() {
		return grupLac;
	}
	public void setGrupLac(String grupLac) {
		this.grupLac = grupLac;
	}
	public String getFacultadTxt() {
		return facultadTxt;
	}
	public void setFacultadTxt(String facultadTxt) {
		this.facultadTxt = facultadTxt;
	}
	
	public boolean isMovilidad() {
		return movilidad;
	}
	public void setMovilidad(boolean movilidad) {
		this.movilidad = movilidad;
	}
	public int getCategoria() {
		return categoria;
	}
	public void setCategoria(int categoria) {
		this.categoria = categoria;
	//	System.out.println("asigna catego "+categoria);
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getDirApellido() {
		return dirApellido;
	}
	public void setDirApellido(String dirApellido) {
		this.dirApellido = dirApellido;
	}
	public String getDirNombre() {
		return dirNombre;
	}
	public void setDirNombre(String dirNombre) {
		this.dirNombre = dirNombre;
	}
	public int getFacultad() {
		return facultad;
	}
	public void setFacultad(int facultad) {
		this.facultad = facultad;
	}
	public String getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(String fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSiglas() {
		return (siglas!=null)?siglas:".";
	}
	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public long getIdDir() {
		return idDir;
	}
	public void setIdDir(long idDir) {
		this.idDir = idDir;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getCedDirector() {
		return cedDirector;
	}
	public void setCedDirector(String cedDirector) {
		this.cedDirector = cedDirector;
	}
	public String getCodUdDirector() {
		return codUdDirector;
	}
	public void setCodUdDirector(String codUdDirector) {
		this.codUdDirector = codUdDirector;
	}
	public boolean isModificable() {
		return modificable;
	}
	public void setModificable(boolean modificable) {
		this.modificable = modificable;
	}
	public void setProyectosCurriculares(List proyectosCurriculares) {
		this.proyectosCurriculares = proyectosCurriculares;
	}
	public List getProyectosCurriculares() {
		return proyectosCurriculares;
	}
	public int getCodproyCurr() {
		return codproyCurr;
	}
	public void setCodproyCurr(int codproyCurr) {
		this.codproyCurr = codproyCurr;
	}
	public int getCgsareasnies() {
		return cgsareasnies;
	}
	public void setCgsareasnies(int cgsareasnies) {
		this.cgsareasnies = cgsareasnies;
	}
	public List getAreasSNIES() {
		return areasSNIES;
	}
	public void setAreasSNIES(List areasSNIES) {
		this.areasSNIES = areasSNIES;
	}
	public int getCodAreaSNIES() {
		return codAreaSNIES;
	}
	public void setCodAreaSNIES(int codAreaSNIES) {
		this.codAreaSNIES = codAreaSNIES;
	}
	public String getFechaCreacionFacultad() {
		return fechaCreacionFacultad;
	}
	public void setFechaCreacionFacultad(String fechaCreacionFacultad) {
		this.fechaCreacionFacultad = fechaCreacionFacultad;
	}
	public int getNumeroCIDC() {
		return numeroCIDC;
	}
	public void setNumeroCIDC(int numeroCIDC) {
		this.numeroCIDC = numeroCIDC;
	}
	public int getNumeroFac() {
		return numeroFac;
	}
	public void setNumeroFac(int numeroFac) {
		this.numeroFac = numeroFac;
	}
	public String getActaCidc() {
		return actaCidc;
	}
	public void setActaCidc(String actaCidc) {
		this.actaCidc = actaCidc;
	}
	public String getActaFacultad() {
		return actaFacultad;
	}
	public void setActaFacultad(String actaFacultad) {
		this.actaFacultad = actaFacultad;
	}
	
}