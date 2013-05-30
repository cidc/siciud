package cidc.adminPropuestas.obj;

public class PropuestaOBJ {

	private boolean convAbierta;
	private long codPropuesta ;
	private int[] codPropu ;
	private long total1;
	private long total2;
	private long total3;
	private long evaluador;
	private String nomDirector;
	private String nomPropuesta;
	private int estadoEvalInt;
	private int estadoEvalExt;
	private int estadoEvalComit;
	private boolean propActiva;
	private String[] propAprobada;
	private long codCriterio;
	private String nomCriterio;
	private float valCriterio;
	private long codAspecto;
	private String nomAspecto;
	private float valAspecto;
	private long codCriterioAspecto;
	private String[] observaciones;
	private int[] aprobo;
	private float[] observaciones1;
	private float[] observaciones2;
	private float[] observaciones3;
	private int[] codProp;
	private int[] convId;
	public String docNombre;
	public boolean estado;
	//public int codPropuesta;
	public int codigo;
        public String nombre;
        public String apellido;
        public float valor;
	public int criterio;
	public int ano;
	public int numero;
	public int conv;
        public String PropuestaAp;
        public String PropuestaOb;
        public String observa;
	private float[] valorCal;
	private float[] valorexterno;
	private float[] valorcomite;
	private int[] codEvaluador;
	private int[] codAsp;
	private int[] codCrit;
	private int[] codCrit2;
	private int[] codAsp2;
	private int[] convId2;
	private int[] codProp2;

	public long getCodPropuesta() {
		return codPropuesta;
	}
	public void setCodPropuesta(long codPropuesta) {
		this.codPropuesta = codPropuesta;
	}
	public int[] getCodPropu() {
		return codPropu;
	}
	public void setCodPropu(int[] codPropu) {
		this.codPropu = codPropu;
	}
	public long getTotal1() {
		return total1;
	}
	public void setTotal1(long total1) {
		this.total1 = total1;
	}
	public long getTotal2() {
		return total2;
	}
	public void setTotal2(long total2) {
		this.total2 = total2;
	}
	public long getTotal3() {
		return total3;
	}
	public void setTotal3(long total3) {
		this.total3 = total3;
	}
	public long getEvaluador() {
		return evaluador;
	}
	public void setEvaluador(long evaluador) {
		this.evaluador = evaluador;
	}
	public String getPropuestaAp() {
		return PropuestaAp;
	}
	public void setPropuestaAp(String PropuestaAp) {
		this.PropuestaAp = PropuestaAp;
	}
	public void setObserva(String observa) {
		this.observa = observa;
	}
	public String getObserva() {
		return observa;
	}
	public String getPropuestaOb() {
		return PropuestaOb;
	}
	public void setPropuestaOb(String PropuestaOb) {
		this.PropuestaOb = PropuestaOb;
	}
	public boolean isConvAbierta() {
		return convAbierta;
	}
	public void setConvAbierta(boolean convAbierta) {
		this.convAbierta = convAbierta;
	}
	public int getEstadoEvalComit() {
		return estadoEvalComit;
	}
	public void setEstadoEvalComit(int estadoEvalComit) {
		this.estadoEvalComit = estadoEvalComit;
	}
	public int getEstadoEvalExt() {
		return estadoEvalExt;
	}
	public void setEstadoEvalExt(int estadoEvalExt) {
		this.estadoEvalExt = estadoEvalExt;
	}
	public int getEstadoEvalInt() {
		return estadoEvalInt;
	}
	public void setEstadoEvalInt(int estadoEvalInt) {
		this.estadoEvalInt = estadoEvalInt;
	}
	public String getNomDirector() {
		return nomDirector;
	}
	public void setNomDirector(String nomDirector) {
		this.nomDirector = nomDirector;
	}
	public String getNomPropuesta() {
		return nomPropuesta;
	}
	public void setNomPropuesta(String nomPropuesta) {
		this.nomPropuesta = nomPropuesta;
	}
	public boolean isPropActiva() {
		return propActiva;
	}
	public void setPropActiva(boolean propActiva) {
		this.propActiva = propActiva;
	}
	public String[] getPropAprobada() {
		return propAprobada;
	}
	public void setPropAprobada(String[] propAprobada) {
		this.propAprobada = propAprobada;
	}
	public long getCodCriterio() {
		return codCriterio;
	}
	public void setCodCriterio(long codCriterio) {
		this.codCriterio = codCriterio;
	}
	public String getNomCriterio() {
		return nomCriterio;
	}
	public void setNomCriterio(String nomCriterio) {
		this.nomCriterio = nomCriterio;
	}
	public float getValCriterio() {
		return valCriterio;
	}
	public void setValCriterio(float valCriterio) {
		this.valCriterio = valCriterio;
	}
	public long getCodAspecto() {
		return codAspecto;
	}
	public void setCodAspecto(long codAspecto) {
		this.codAspecto = codAspecto;
	}
	public String getNomAspecto() {
		return nomAspecto;
	}
	public void setNomAspecto(String nomAspecto) {
		this.nomAspecto = nomAspecto;
	}
	public float getValAspecto() {
		return valAspecto;
	}
	public void setValAspecto(float valAspecto) {
		this.valAspecto = valAspecto;
	}
	public long getCodCriterioAspecto() {
		return codCriterioAspecto;
	}
	public void setCodCriterioAspecto(long codCriterioAspecto) {
		this.codCriterioAspecto = codCriterioAspecto;
	}
	public String[] getObservaciones() {
                return observaciones;
        }
        public void setObservaciones(String[] observaciones){
                this.observaciones = observaciones;
        }
	public int[] isAprobo() {
                return aprobo;
        }
        public void setAprobo(int[] aprobo){
                this.aprobo = aprobo;
        }
	public float[] getObservaciones1() {
                return observaciones1;
        }
        public void setObservaciones1(float[] observaciones1){
                this.observaciones1 = observaciones1;
        }
	public float[] getObservaciones2() {
                return observaciones2;
        }
        public void setObservaciones2(float[] observaciones2){
                this.observaciones2 = observaciones2;
        }
	public float[] getObservaciones3() {
                return observaciones3;
        }
        public void setObservaciones3(float[] observaciones3){
                this.observaciones3 = observaciones3;
        }
	public int[] getCodProp() {
                return codProp;
        }
        public void setCodProp(int[] codProp){
                this.codProp = codProp;
        }
	public int[] getCodProp2() {
                return codProp2;
        }
        public void setCodProp2(int[] codProp2){
                this.codProp2 = codProp2;
        }
	public int[] getConvId() {
                return convId;
        }
        public void setConvId(int[] convId){
                this.convId = convId;
        }
	public int[] getConvId2() {
                return convId2;
        }
        public void setConvId2(int[] convId2){
                this.convId2 = convId2;
        }
	public int[] getCodEvaluador() {
                return codEvaluador;
        }
        public void setCodEvaluador(int[] codEvaluador){
                this.codEvaluador = codEvaluador;
        }
	public int[] getCodAsp() {
                return codAsp;
        }
        public void setCodAsp(int[] codAsp){
                this.codAsp = codAsp;
        }
	public int[] getCodAsp2() {
                return codAsp2;
        }
        public void setCodAsp2(int[] codAsp2){
                this.codAsp2 = codAsp2;
        }
	public int[] getCodCrit() {
                return codCrit;
        }
        public void setCodCrit(int[] codCrit){
                this.codCrit = codCrit;
        }
	public int[] getCodCrit2() {
                return codCrit2;
        }
        public void setCodCrit2(int[] codCrit2){
                this.codCrit2 = codCrit2;
        }
	public float[] getValorCal() {
                return valorCal;
        }
        public void setValorCal(float[] valorCal){
                this.valorCal = valorCal;
        }
	public float[] getValorExterno() {
                return valorexterno;
        }
        public void setValorExterno(float[] valorexterno){
                this.valorexterno = valorexterno;
        }
	public float[] getValorComite() {
                return valorcomite;
        }
        public void setValorComite(float[] valorcomite){
                this.valorcomite = valorcomite;
        }
	public String getDocNombre() {
                return docNombre;
        }
        public void setDocNombre(String docNombre){
                this.docNombre = docNombre;
        }
	public int getCodigo() {
                return codigo;
        }
        public void setCodigo(int codigo) {
                this.codigo = codigo;
        }
        public String getNombre() {
                return nombre;
        }
        public void setEstado(boolean estado) {
                this.estado = estado;
        }
        public boolean getEstado() {
                return estado;
        }
        public void setNombre(String nombre) {
                this.nombre = nombre;
        }
        public String getApellido() {
                return apellido;
        }
        public void setApellido(String apellido) {
                this.apellido = apellido;
        }
        public float getValor() {
                return valor;
        }
        public void setValor(float valor) {
                this.valor = valor;
        }
        public int getCriterio() {
                return criterio;
        }
        public void setCriterio(int criterio) {
                this.criterio = criterio;
        }
        public int getAno() {
                return ano;
        }
        public void setAno(int ano) {
                this.ano = ano;
        }
        public int getNumero() {
                return numero;
        }
        public void setNumero(int numero) {
                this.numero = numero;
        }
        public int getConv() {
                return conv;
        }
        public void setConv(int conv) {
                this.conv = conv;
        }
}
