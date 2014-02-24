package cidc.convocatorias.obj;

import java.util.ArrayList;
import java.util.List;

public class ConvocatoriaOBJ {
	public long convId;
	public int convAno;
	public int convNumero;
	public int convTipo;
	public String convNombre;
	public String convCuantia;
	public int convDuracion;
	public String convFecInicio;
	public String convFecFin;
	public String convDirigido;
	public boolean convPublica;
	public String convArchivo;
	public String convResolucion;
	public String convAd;
	public String observacion;
	public int cantProduct;
	public String corteActual;
	public boolean inscripcion=false;// para saber si habilita o no el botón de inscripciones
	public List cortes;
	public String reqNombre;
	public boolean reqEstado;
	public int reqId;
	public String rubNombre;
	public int rubId;
	public String comNombre;
	public boolean comEstado;
	public int comId;
	public String ejeNombre;
	public boolean ejeEstado;
	public int ejeId;
	public String critNombre;
	public boolean critEstado;
	public int critId;
	public String aspNombre;
	public boolean aspEstado;
	public int aspId;
	public boolean convEvalua;
	public int rol;

	private List listaCortes=new ArrayList();

	public String getObservacion() {
		return (observacion!=null) ? observacion:"";
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public int getConvAno() {
		return convAno;
	}
	public void setConvAno(int convAno) {
		this.convAno = convAno;
	}
	public String getConvArchivo() {
		return convArchivo;
	}
	public void setConvArchivo(String convArchivo) {
		this.convArchivo = convArchivo;
	}
	public String getConvCuantia() {
		return convCuantia;
	}
	public void setConvCuantia(String convCuantia) {
		this.convCuantia = convCuantia;
	}
	public String getConvDirigido() {
		return convDirigido;
	}
	public void setConvDirigido(String convDirigido) {
		this.convDirigido = convDirigido;
	}
	public int getConvDuracion() {
		return convDuracion;
	}
	public void setConvDuracion(int convDuracion) {
		this.convDuracion = convDuracion;
	}
	public String getConvFecFin() {
		return convFecFin;
	}
	public void setConvFecFin(String convFecFin) {
		this.convFecFin = convFecFin;
	}
	public String getConvFecInicio() {
		return convFecInicio;
	}
	public void setConvFecInicio(String convFecInicio) {
		this.convFecInicio = convFecInicio;
	}
	public long getConvId() {
		return convId;
	}
	public void setConvId(long convId) {
		this.convId = convId;
	}
	public String getConvNombre() {
		return convNombre;
	}
	public void setConvNombre(String convNombre){
		this.convNombre = convNombre;
	}
	public String getReqNombre() {
		return reqNombre;
	}
	public void setReqNombre(String reqNombre){
		this.reqNombre = reqNombre;
	}
	public boolean isReqEstado() {
		return reqEstado;
	}
	public void setReqEstado(boolean reqEstado) {
		this.reqEstado = reqEstado;
	}
	public int getReqId() {
		return reqId;
	}
	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	public String getRubNombre() {
		return rubNombre;
	}
	public void setRubNombre(String rubNombre){
		this.rubNombre = rubNombre;
	}
	public int getRubId() {
		return rubId;
	}
	public void setRubId(int rubId) {
		this.rubId = rubId;
	}
	public int getConvNumero() {
		return convNumero;
	}
	public void setConvNumero(int convNumero) {
		this.convNumero = convNumero;
	}
	public int getConvTipo() {
		return convTipo;
	}
	public void setConvTipo(int convTipo) {
		this.convTipo = convTipo;
	}
	public boolean isConvPublica() {
		return convPublica;
	}
	public void setConvPublica(boolean convPublica) {
		this.convPublica = convPublica;
	//	System.out.println("asigna publica  ="+convPublica);
	}
	public boolean isConvEvalua() {
		return convEvalua;
	}
	public void setConvEvalua(boolean convEvalua) {
		this.convEvalua = convEvalua;
	//	System.out.println("asigna publica  ="+convPublica);
	}
	public String getConvResolucion() {
		return convResolucion;
	}
	public void setConvResolucion(String convResolucion) {
		this.convResolucion = convResolucion;
	}
	public String getConvAd() {
		return convAd;
	}
	public void setConvAd(String convAd) {
		this.convAd = convAd;
	}
	public int getCantProduct() {
		return cantProduct;
	}
	public void setCantProduct(int cantProduct) {
		this.cantProduct = cantProduct;
	}
	public String getCorteActual() {
		return corteActual;
	}
	public void setCorteActual(String corteActual) {
		this.corteActual = corteActual;
	}
	public List getListaCortes() {
		return listaCortes;
	}
	public void setListaCortes(List listaCortes) {
		this.listaCortes = listaCortes;
	}
	public List getCortes() {
		return cortes;
	}
	public void setCortes(List cortes) {
		this.cortes = cortes;
	}
	public boolean isInscripcion() {
		return inscripcion;
	}
	public void setInscripcion(boolean inscripcion) {
		this.inscripcion = inscripcion;
	}
	public String getComNombre() {
		return comNombre;
	}
	public void setComNombre(String comNombre){
		this.comNombre = comNombre;
	}
	public boolean isComEstado() {
		return comEstado;
	}
	public void setComEstado(boolean comEstado) {
		this.comEstado = comEstado;
	}
	public int getComId() {
		return comId;
	}
	public void setComId(int comId) {
		this.comId = comId;
	}
	public String getEjeNombre() {
                return ejeNombre;
        }
        public void setEjeNombre(String ejeNombre){
                this.ejeNombre = ejeNombre;
        }
        public boolean isEjeEstado() {
                return ejeEstado;
        }
        public void setEjeEstado(boolean ejeEstado) {
                this.ejeEstado = ejeEstado;
        }
        public int getEjeId() {
                return ejeId;
        }
        public void setEjeId(int ejeId) {
                this.ejeId = ejeId;
        }
	public String getCritNombre() {
                return critNombre;
        }
        public void setCritNombre(String critNombre){
                this.critNombre = critNombre;
        }
        public boolean isCritEstado() {
                return critEstado;
        }
        public void setCritEstado(boolean critEstado) {
                this.critEstado = critEstado;
        }
        public int getCritId() {
                return critId;
        }
        public void setCritId(int critId) {
                this.critId = critId;
        }
	public String getAspNombre() {
                return aspNombre;
        }
        public void setAspNombre(String aspNombre){
                this.aspNombre = aspNombre;
        }
        public boolean isAspEstado() {
                return aspEstado;
        }
        public void setAspEstado(boolean aspEstado) {
                this.aspEstado = aspEstado;
        }
        public int getAspId() {
                return aspId;
        }
        public void setAspId(int aspId) {
                this.aspId = aspId;
        }
		public int getRol() {
			return rol;
		}
		public void setRol(int rol) {
			this.rol = rol;
		}

}
