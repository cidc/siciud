package cidc.inscripcionConv.obj;

import java.io.Serializable;

public class InscripcionConvOBJ implements Serializable{

	public String getPropDirPro() {
		return propDirPro;
	}
	public void setPropDirPro(String propDirPro) {
		this.propDirPro = propDirPro;
	}
	private long propConvId;
	private long propId;
	private int propFacultad;
	private int propGrupoInv;
	private int propProyCur;
	private int propInvesPrincId;
	private int propHorasInv;
	private String propNombreInvestig;
	private String propNombre;
	private String propArchivo;
	private String propMailInvest;
	private String propObservaciones;
	private String propPalClave;
	private String propAbstract;
	//Modificación para la convacotia 2012-05 Proyecto asiciado al Plan de Accion y profesor director 
	private String proyectoinv;
	private String propDirPro;

	private String []propCoInvDocumento;
	private String []propCoInvNombres;
	private String []propCoInvApellidos;
	private String []propCoInvPapel;
	private int []propCoInvHoras;

	private int []propGrupoAsociado;
		
	private long []idRubCidc;
	private long []idRubOtros;
	private String []propPresPropRubroUd;
	private String []propPresPropRubroCidc;
	private String []propPresPropRubroContra;

	private int []idCompromisos;
	private int []cantComp;
	
	
	public int[] getPropGrupoAsociado() {
		return propGrupoAsociado;
	}
	public void setPropGrupoAsociado(int[] propGrupoAsociado) {
		this.propGrupoAsociado = propGrupoAsociado;
	}
	public long[] getIdRubCidc() {
		return idRubCidc;
	}
	public long[] getIdRubOtros() {
		return idRubOtros;
	}
	public String[] getPropCoInvApellidos() {
		return propCoInvApellidos;
	}
	public String[] getPropCoInvDocumento() {
		return propCoInvDocumento;
	}
	public String[] getPropCoInvNombres() {
		return propCoInvNombres;
	}
	public String[] getPropPresPropRubroCidc() {
		return propPresPropRubroCidc;
	}
	public String[] getPropPresPropRubroContra() {
		return propPresPropRubroContra;
	}
	public String[] getPropPresPropRubroUd() {
		return propPresPropRubroUd;
	}
	public void setIdRubCidc(long[] idRubCidc) {
		this.idRubCidc = idRubCidc;
	}
	public void setIdRubOtros(long[] idRubOtros) {
		this.idRubOtros = idRubOtros;
	}
	public void setPropCoInvApellidos(String[] propCoInvApellidos) {
		this.propCoInvApellidos = propCoInvApellidos;
	}
	public void setPropCoInvDocumento(String[] propCoInvDocumento) {
		this.propCoInvDocumento = propCoInvDocumento;
	}
	public String[] getPropCoInvPapel() {
		return propCoInvPapel;
	}
	public void setPropCoInvPapel(String[] propCoInvPapel) {
		this.propCoInvPapel = propCoInvPapel;
	}
	public void setPropCoInvNombres(String[] propCoInvNombres) {
		this.propCoInvNombres = propCoInvNombres;
	}
	public void setPropPresPropRubroCidc(String[] propPresPropRubroCidc) {
		this.propPresPropRubroCidc = propPresPropRubroCidc;
	}
	public void setPropPresPropRubroContra(String[] propPresPropRubroContra) {
		this.propPresPropRubroContra = propPresPropRubroContra;
	}
	public void setPropPresPropRubroUd(String[] propPresPropRubroUd) {
		this.propPresPropRubroUd = propPresPropRubroUd;
	}
	public String getPropAbstract() {
		return propAbstract;
	}
	public String getPropArchivo() {
		return propArchivo;
	}
	public long getPropConvId() {
		return propConvId;
	}
	public int getPropFacultad() {
		return propFacultad;
	}
	public int getPropGrupoInv() {
		return propGrupoInv;
	}
	public long getPropId() {
		return propId;
	}
	public int getPropInvesPrincId() {
		return propInvesPrincId;
	}
	public String getPropMailInvest() {
		return propMailInvest;
	}
	public String getPropNombre() {
		return propNombre;
	}
	public String getPropNombreInvestig() {
		return propNombreInvestig;
	}
	public String getPropObservaciones() {
		return propObservaciones;
	}
	public String getPropPalClave() {
		return propPalClave;
	}
	public int getPropProyCur() {
		return propProyCur;
	}
	public void setPropAbstract(String propAbstract) {
		this.propAbstract = propAbstract;
	}
	public void setPropArchivo(String propArchivo) {
		this.propArchivo = propArchivo;
	}
	public void setPropConvId(long propConvId) {
		this.propConvId = propConvId;
	}
	public void setPropFacultad(int propFacultad) {
		this.propFacultad = propFacultad;
	}
	public void setPropGrupoInv(int propGrupoInv) {
		this.propGrupoInv = propGrupoInv;
	}
	public void setPropId(long propId) {
		this.propId = propId;
	}
	public void setPropInvesPrincId(int propInvesPrincId) {
		this.propInvesPrincId = propInvesPrincId;
	}
	public void setPropMailInvest(String propMailInvest) {
		this.propMailInvest = propMailInvest;
	}
	public void setPropNombre(String propNombre) {
		this.propNombre = propNombre;
	}
	public void setPropNombreInvestig(String propNombreInvestig) {
		this.propNombreInvestig = propNombreInvestig;
	}
	public void setPropObservaciones(String propObservaciones) {
		this.propObservaciones = propObservaciones;
	}
	public void setPropPalClave(String propPalClave) {
		this.propPalClave = propPalClave;
	}
	public void setPropProyCur(int propProyCur) {
		this.propProyCur = propProyCur;
	}
	public int getPropHorasInv() {
		return propHorasInv;
	}
	public void setPropHorasInv(int propHorasInv) {
		this.propHorasInv = propHorasInv;
	}
	public int[] getIdCompromisos() {
		return idCompromisos;
	}
	public void setIdCompromisos(int[] idCompromisos) {
		this.idCompromisos = idCompromisos;
	}
	public int[] getPropCoInvHoras() {
		return propCoInvHoras;
	}
	public void setPropCoInvHoras(int[] propCoInvHoras) {
		this.propCoInvHoras = propCoInvHoras;
	}
	public int[] getCantComp() {
		return cantComp;
	}
	public void setCantComp(int[] cantComp) {
		this.cantComp = cantComp;
	}
	public String getProyectoinv() {
		return proyectoinv;
	}
	public void setProyectoinv(String proyectoinv) {
		this.proyectoinv = proyectoinv;
	}	
}
