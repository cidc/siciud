package cidc.convocatorias.obj;

public class InsercionGralOBJ {
	public int[] criterios;
	public int[] aspectos;
	public int[] ejeTematico;
	public int[] compromiso;
	public int[] rubros;
	public int[] documentos;
	public boolean[] docEstado;

	public int[] critValor;
	public double[] rubValor;
	public float[] aspValor;
	public int[] obligatorio;
	public String[] observaciones;
	public String observacion;

	public double porcentInt;
	public double porcentExt;
	public double porcentComit;
	public int aspectComit;
	public String comite; //comite evaluador de la convocatoria


	public int idCorte;
	public int numCorte;
	public long convId;
	public String fechaApertura;
	public String fechaCierre;
	public String auxApertura;
	public String auxCierre;

	public Boolean convEvalua;

	public String getAuxApertura() {
		return auxApertura;
	}
	public void setAuxApertura(String auxApertura) {
		this.auxApertura = auxApertura;
	}
	public String getAuxCierre() {
		return auxCierre;
	}
	public void setAuxCierre(String auxCierre) {
		this.auxCierre = auxCierre;
	}
	public String getFechaApertura() {
		return fechaApertura;
	}
	public void setFechaApertura(String fechaApertura) {
		this.fechaApertura = fechaApertura;
	}
	public String getFechaCierre() {
		return fechaCierre;
	}
	public void setFechaCierre(String fechaCierre) {
		this.fechaCierre = fechaCierre;
	}
	public long getConvId() {
		return convId;
	}
	public void setConvId(long convId) {
		this.convId = convId;
	}
	public int getNumCorte() {
		return numCorte;
	}
	public void setNumCorte(int numCorte) {
		this.numCorte = numCorte;
	}
	public String getComite() {
		return comite;
	}
	public void setComite(String comite) {
		this.comite = comite;
	}
	public String getObservacion() {
		return (observacion!=null) ? observacion:"";
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int[] getAspectos() {
		return aspectos;
	}
	public void setAspectos(int[] aspectos) {
		this.aspectos = aspectos;
	}
	public int[] getCompromiso() {
		return compromiso;
	}
	public void setCompromiso(int[] compromiso) {
		this.compromiso = compromiso;
	//	System.out.println("asigna compromisos");
	}
	public int[] getCriterios() {
		return criterios;
	}
	public void setCriterios(int[] criterios) {
		this.criterios = criterios;
		//System.out.println("asigna criterios");
	}
	public int[] getCritValor() {
		return critValor;
	}
	public void setCritValor(int[] critValor) {
		this.critValor = critValor;
	//	System.out.println("asigna valores de criterios");
	}
	public int[] getEjeTematico() {
		return ejeTematico;
	}
	public void setEjeTematico(int[] ejeTematico) {
		this.ejeTematico = ejeTematico;
	}
	public int[] getObligatorio() {
		return obligatorio;
	}
	public void setObligatorio(int[] obligatorio) {
		this.obligatorio = obligatorio;
	}
	public String[] getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String[] observaciones) {
		this.observaciones = observaciones;
	}
	public float[] getAspValor() {
		return aspValor;
	}
	public void setAspValor(float[] aspValor) {
		this.aspValor = aspValor;
	}
	public int[] getRubros() {
		return rubros;
	}
	public void setRubros(int[] rubros) {
		this.rubros = rubros;
	}
	public double[] getRubValor() {
		return rubValor;
	}
	public void setRubValor(double[] rubValor) {
		this.rubValor = rubValor;
	}
	public double getPorcentComit() {
		return porcentComit;
	}
	public double getPorcentExt() {
		return porcentExt;
	}
	public double getPorcentInt() {
		return porcentInt;
	}
	public void setPorcentComit(double porcentComit) {
		this.porcentComit = porcentComit;
	}
	public void setPorcentExt(double porcentExt) {
		this.porcentExt = porcentExt;
	}
	public void setPorcentInt(double porcentInt) {
		this.porcentInt = porcentInt;
	}
	public int getAspectComit() {
		return aspectComit;
	}
	public void setAspectComit(int aspectComit) {
		this.aspectComit = aspectComit;
	}
	public int getIdCorte() {
		return idCorte;
	}
	public void setIdCorte(int idCorte) {
		this.idCorte = idCorte;
	}
	public int[] getDocumentos() {
		return documentos;
	}
	public void setDocumentos(int[] documentos) {
		this.documentos = documentos;
	}
	public boolean[] getDocEstado() {
		return docEstado;
	}
	public void setDocEstado(boolean[] docEstado) {
		this.docEstado = docEstado;
	}
	public boolean getConvEvalua() {
		return convEvalua;
	}
	public void setConvEvalua(boolean convEvalua) {
		this.convEvalua = convEvalua;
	}


}
