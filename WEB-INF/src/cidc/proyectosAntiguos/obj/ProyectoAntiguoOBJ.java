package cidc.proyectosAntiguos.obj;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ProyectoAntiguoOBJ implements Serializable{

	private int id;
	private String ano = null;
    private String codigo = null;
    private String nombre = null;
    private int facultad;
    private int proycurri;
    private int grupo;
    private int investigador;

    private String nomFac = null;
    private String nomProyCurri = null;
    private String nomGrupo = null;
    private String nomInves = null;

    private int horas;
    private int duracion;
    private String presupuesto = null;
    private String palabras = null;
    private String objetivo = null;
    private String resumen = null;
    private String [] compromisos = null;
    private String observacion = null;
    private int tipo;
    private int convenio;
    private String convocatoria = null;
    private int convocatorias;
    private int estado;
    private String[] apellCoinvest = null;
    private String[] nomCoinvest = null;
    private int[] tipoInv = null;

    private String nombrePropuesta = null;
    private String nombreActainicio = null;
    private String nombreContrato = null;
    private String nombreInfofinal = null;
    private String nombreActafinal = null;
    private String nombreAnexos = null;
    private String fechaAcIn= null;
    private String fechaCon = null;
    private String fechaInFin = null;
    private String fechaAcFin = null;
    private String obserPro = null;
    private String obserAcIn= null;
    private String obserCon = null;
    private String obserInFin = null;
    private String obserAcFin = null;
    private String obserAnex = null;

    private String sumaRubro = null;
    private String nombreRubro = null;
    private String saldoRubro = null;
    private int idRubro;

    private String nombreInvestigador = null;

    private String ejecutado = null;
    private String sumaReintegros = null;
    private String saldo = null;


    private List listaObservaciones;
    private int flag;


	public String getNombreActafinal() {
		return nombreActafinal;
	}
	public void setNombreActafinal(String nombreActafinal) {
		this.nombreActafinal = nombreActafinal;
	}
	public String getNombreActainicio() {
		return nombreActainicio;
	}
	public void setNombreActainicio(String nombreActainicio) {
		this.nombreActainicio = nombreActainicio;
	}
	public String getNombreContrato() {
		return nombreContrato;
	}
	public void setNombreContrato(String nombreContrato) {
		this.nombreContrato = nombreContrato;
	}
	public String getNombreInfofinal() {
		return nombreInfofinal;
	}
	public void setNombreInfofinal(String nombreInfofinal) {
		this.nombreInfofinal = nombreInfofinal;
	}
	public String getNombrePropuesta() {
		return nombrePropuesta;
	}
	public void setNombrePropuesta(String nombrePropuesta) {
		this.nombrePropuesta = nombrePropuesta;
	}

	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public int getConvenio() {
		return convenio;
	}
	public void setConvenio(int convenio) {
		this.convenio = convenio;
	}
	public String getConvocatoria() {
		return convocatoria;
	}
	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	public int getFacultad() {
		return facultad;
	}
	public void setFacultad(int facultad) {
		this.facultad = facultad;
	}
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	public int getHoras() {
		return horas;
	}
	public void setHoras(int horas) {
		this.horas = horas;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getInvestigador() {
		return investigador;
	}
	public void setInvestigador(int investigador) {
		this.investigador = investigador;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getObjetivo() {
		return objetivo;
	}
	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getPalabras() {
		return palabras;
	}
	public void setPalabras(String palabras) {
		this.palabras = palabras;
	}
	public String getPresupuesto() {
		return presupuesto;
	}
	public void setPresupuesto(String presupuesto) {
		this.presupuesto = presupuesto;
	}
	public int getProycurri() {
		return proycurri;
	}
	public void setProycurri(int proycurri) {
		this.proycurri = proycurri;
	}
	public String getResumen() {
		return resumen;
	}
	public void setResumen(String resumen) {
		this.resumen = resumen;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public String[] getApellCoinvest() {
		return apellCoinvest;
	}
	public void setApellCoinvest(String[] apellCoinvest) {
		this.apellCoinvest = apellCoinvest;
	}
	public String[] getNomCoinvest() {
		return nomCoinvest;
	}
	public void setNomCoinvest(String[] nomCoinvest) {
		this.nomCoinvest = nomCoinvest;
	}
	public int[] getTipoInv() {
		return tipoInv;
	}
	public void setTipoInv(int[] tipoInv) {
		this.tipoInv = tipoInv;
	}
	public void setCompromisos(String[] compromisos) {
		this.compromisos = compromisos;
	}
	public String[] getCompromisos() {
		return compromisos;
	}
	public String getNombreAnexos() {
		return nombreAnexos;
	}
	public void setNombreAnexos(String nombreAnexos) {
		this.nombreAnexos = nombreAnexos;
	}
	public String getEjecutado() {
		return ejecutado;
	}
	public void setEjecutado(String ejecutado) {
		this.ejecutado = ejecutado;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getNombreRubro() {
		return nombreRubro;
	}
	public void setNombreRubro(String nombreRubro) {
		this.nombreRubro = nombreRubro;
	}
	public String getSumaRubro() {
		return sumaRubro;
	}
	public void setSumaRubro(String sumaRubro) {
		this.sumaRubro = sumaRubro;
	}
	public int getIdRubro() {
		return idRubro;
	}
	public void setIdRubro(int idRubro) {
		this.idRubro = idRubro;
	}
	public String getNombreInvestigador() {
		return nombreInvestigador;
	}
	public void setNombreInvestigador(String nombreInvestigador) {
		this.nombreInvestigador = nombreInvestigador;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getSumaReintegros() {
		return sumaReintegros;
	}
	public void setSumaReintegros(String sumaReintegros) {
		this.sumaReintegros = sumaReintegros;
	}
	public String getObserAcIn() {
		return obserAcIn;
	}
	public void setObserAcIn(String obserAcIn) {
		this.obserAcIn = obserAcIn;
	}
	public String getObserAnex() {
		return obserAnex;
	}
	public void setObserAnex(String obserAnex) {
		this.obserAnex = obserAnex;
	}
	public String getObserCon() {
		return obserCon;
	}
	public void setObserCon(String obserCon) {
		this.obserCon = obserCon;
	}
	public String getObserInFin() {
		return obserInFin;
	}
	public void setObserInFin(String obserInFin) {
		this.obserInFin = obserInFin;
	}
	public String getObserPro() {
		return obserPro;
	}
	public void setObserPro(String obserPro) {
		this.obserPro = obserPro;
	}
	public String getObserAcFin() {
		return obserAcFin;
	}
	public void setObserAcFin(String obserAcFin) {
		this.obserAcFin = obserAcFin;
	}
	public String getNomFac() {
		return nomFac;
	}
	public void setNomFac(String nomFac) {
		this.nomFac = nomFac;
	}
	public String getNomGrupo() {
		return nomGrupo;
	}
	public void setNomGrupo(String nomGrupo) {
		this.nomGrupo = nomGrupo;
	}
	public String getNomInves() {
		return nomInves;
	}
	public void setNomInves(String nomInves) {
		this.nomInves = nomInves;
	}
	public String getNomProyCurri() {
		return nomProyCurri;
	}
	public void setNomProyCurri(String nomProyCurri) {
		this.nomProyCurri = nomProyCurri;
	}
	public String getFechaAcFin() {
		return fechaAcFin;
	}
	public void setFechaAcFin(String fechaAcFin) {
		this.fechaAcFin = fechaAcFin;
	}
	public String getFechaAcIn() {
		return fechaAcIn;
	}
	public void setFechaAcIn(String fechaAcIn) {
		this.fechaAcIn = fechaAcIn;
	}

	public String getFechaCon() {
		return fechaCon;
	}
	public void setFechaCon(String fechaCon) {
		this.fechaCon = fechaCon;
	}
	public String getFechaInFin() {
		return fechaInFin;
	}
	public void setFechaInFin(String fechaInFin) {
		this.fechaInFin = fechaInFin;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public List getListaObservaciones() {
		return listaObservaciones;
	}
	public void setListaObservaciones(List listaObservaciones) {
		this.listaObservaciones = listaObservaciones;
	}
	public String getSaldoRubro() {
		return saldoRubro;
	}
	public void setSaldoRubro(String saldoRubro) {
		this.saldoRubro = saldoRubro;
	}
	public int getConvocatorias() {
		return convocatorias;
	}
	public void setConvocatorias(int convocatorias) {
		this.convocatorias = convocatorias;
	}
	
}