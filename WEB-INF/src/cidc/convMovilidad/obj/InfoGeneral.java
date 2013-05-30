package cidc.convMovilidad.obj;

import java.io.Serializable;

//Serializable restaura los objetos, mantiene recuperar el estado del obj en las sesiones de los usuarios
public class InfoGeneral implements Serializable{

	private long idPropuesta;
	private long propConvId;
	private long idPersona;
	private int corte;
	private int grupo;	
	private int tipo;
	private int estado;
	private String tipoLetra;
	private String pagEvento;
	private String pais;
	private String ciudad;
	private String nombreEvento;
	//CaputarÃ¡ el texto de la lista de universidades
	private String institucion;
	//CaputarÃ¡ el texto de la segunda lista en organizadores del evento
	private String lista_arbitraje;
	private String fechaInicio;
	private String fechaFin;
	private String nombrePonencia;
	//relaciona el proyecto de investigación del Plan de Accion 
	private String proyectoinv;
	private String nombrePresentador;
	private String nombreAutores;

	private String siglasInstitu;
	private String cobertura;
	private String valorInsc;
	private int moneda;
	private String monedaTxt;
	private String trayectoria;
	private String cvlac;

	private String archivoAval;
	private String archivoAceptacion;
	private String archivoResumen;
	private String archivoProyCurri;
	private String archivoDecanatura;
	private String archivoConsFac;
	private String archivoConsAcade;
	private String archivoExcelencia;
	private String archivoCertificaciones;
	//Certificado del CIDC
	private String archivoCertificacionesCIDC;
	//Certificado de Resultados de otros viajes
	private String archivoResultados;
	//Carta donde expresa que no tiene apoyos económicos
	private String archivoApoyos;
	
	//formato productividad Cvlac
	private String archivoProduccion; 

 	//Id de convocatoria
	private long propConvId;
	
	public int getGrupo() {
		return grupo;
	}
	public void setGrupo(int grupo) {
		this.grupo = grupo;
	}
	
	public String getArchivoAceptacion() {
		return archivoAceptacion;
	}
	public String getArchivoAval() {
		return archivoAval;
	}
	public String getArchivoConsAcade() {
		return archivoConsAcade;
	}
	public String getArchivoConsFac() {
		return archivoConsFac;
	}
	public String getArchivoDecanatura() {
		return archivoDecanatura;
	}
	public String getArchivoExcelencia() {
		return archivoExcelencia;
	}
	public String getArchivoProyCurri() {
		return archivoProyCurri;
	}
	public String getArchivoResumen() {
		return archivoResumen;
	}
	public String getCiudad() {
		return ciudad;
	}
	public String getCobertura() {
		return cobertura;
	}
	public int getCorte() {
		return corte;
	}
	public String getCvlac() {
		return cvlac;
	}
	public String getFechaFin() {
		return fechaFin;
	}
	public String getFechaInicio() {
		return fechaInicio;
	}
	public long getIdPersona() {
		return idPersona;
	}
	public long getIdPropuesta() {
		return idPropuesta;
	}
	public long getIdConv() {
		return propConvId;
	}
	public String getInstitucion() {
		return institucion;
	}
	public int getMoneda() {
		return moneda;
	}
	public String getNombreAutores() {
		return nombreAutores;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public String getNombrePonencia() {
		return nombrePonencia;
	}
	public String getNombrePresentador() {
		return nombrePresentador;
	}
	public String getPagEvento() {
		return pagEvento;
	}
	public String getPais() {
		return pais;
	}
	public String getSiglasInstitu() {
		return siglasInstitu;
	}
	public int getTipo() {
		return tipo;
	}
	public String getTipoLetra() {
		return tipoLetra;
	}
	public String getTrayectoria() {
		return trayectoria;
	}
	public String getValorInsc() {
		return valorInsc;
	}
	public void setArchivoAceptacion(String archivoAceptacion) {
		this.archivoAceptacion = archivoAceptacion;
	}
	public void setArchivoAval(String archivoAval) {
		this.archivoAval = archivoAval;
	}
	public void setArchivoConsAcade(String archivoConsAcade) {
		this.archivoConsAcade = archivoConsAcade;
	}
	public void setArchivoConsFac(String archivoConsFac) {
		this.archivoConsFac = archivoConsFac;
	}
	public void setArchivoDecanatura(String archivoDecanatura) {
		this.archivoDecanatura = archivoDecanatura;
	}
	public void setArchivoExcelencia(String archivoExcelencia) {
		this.archivoExcelencia = archivoExcelencia;
	}
	public void setArchivoProyCurri(String archivoProyCurri) {
		this.archivoProyCurri = archivoProyCurri;
	}
	public void setArchivoResumen(String archivoResumen) {
		this.archivoResumen = archivoResumen;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public void setCobertura(String cobertura) {
		this.cobertura = cobertura;
	}
	public void setCorte(int corte) {
		this.corte = corte;
	}
	public void setCvlac(String cvlac) {
		this.cvlac = cvlac;
	}
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public void setIdPersona(long idPersona) {
		this.idPersona = idPersona;
	}
	public void setIdPropuesta(long idPropuesta) {
		this.idPropuesta = idPropuesta;
	}
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	public void setMoneda(int moneda) {
		this.moneda = moneda;
	}
	public void setNombreAutores(String nombreAutores) {
		this.nombreAutores = nombreAutores;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public void setNombrePonencia(String nombrePonencia) {
		this.nombrePonencia = nombrePonencia;
	}
	public void setNombrePresentador(String nombrePresentador) {
		this.nombrePresentador = nombrePresentador;
	}
	public void setPagEvento(String pagEvento) {
		this.pagEvento = pagEvento;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public void setSiglasInstitu(String siglasInstitu) {
		this.siglasInstitu = siglasInstitu;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public void setTipoLetra(String tipoLetra) {
		this.tipoLetra = tipoLetra;
	}
	public void setTrayectoria(String trayectoria) {
		this.trayectoria = trayectoria;
	}
	public void setValorInsc(String valorInsc) {
		this.valorInsc = valorInsc;
	}
	public String getMonedaTxt() {
		return monedaTxt;
	}
	public void setMonedaTxt(String monedaTxt) {
		this.monedaTxt = monedaTxt;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public String getLista_arbitraje() {
		return lista_arbitraje;
	}
	public void setLista_arbitraje(String listaArbitraje) {
		lista_arbitraje = listaArbitraje;
	}
	public String getArchivoCertificaciones() {
		return archivoCertificaciones;
	}
	public void setArchivoCertificaciones(String archivoCertificaciones) {
		this.archivoCertificaciones = archivoCertificaciones;
	}
	public String getProyectoinv() {
		return proyectoinv;
	}
	public void setProyectoinv(String proyectoinv) {
		this.proyectoinv = proyectoinv;
	}
	public String getArchivoCertificacionesCIDC() {
		return archivoCertificacionesCIDC;
	}
	public void setArchivoCertificacionesCIDC(String archivoCertificacionesCIDC) {
		this.archivoCertificacionesCIDC = archivoCertificacionesCIDC;
	}
	public String getArchivoResultados() {
		return archivoResultados;
	}
	public void setArchivoResultados(String archivoResultados) {
		this.archivoResultados = archivoResultados;
	}
	public String getArchivoApoyos() {
		return archivoApoyos;
	}
	public void setArchivoApoyos(String archivoApoyos) {
		this.archivoApoyos = archivoApoyos;
	}
	public String getArchivoProduccion() {
		return archivoProduccion;
	}
	public void setArchivoProduccion(String archivoproduccion) {
		this.archivoProduccion = archivoproduccion;
	}
	public long getPropConvId() {
                return propConvId;
        }
        public void setPropConvId(long propConvId) {
                this.propConvId = propConvId;
        }
	
}
