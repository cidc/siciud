package cidc.convenios.obj;

import java.io.Serializable;

public class EntidadAsociadaOBJ implements Serializable {
	private String identidadconvenio;
	private String entidadid;
	private String convenioid;
	private String VEspecieConv;
	private String VEfectivoConv;
	private String VTotal;
	public String getIdentidadconvenio() {
		return identidadconvenio;
	}
	public void setIdentidadconvenio(String identidadconvenio) {
		this.identidadconvenio = identidadconvenio;
	}
	public String getEntidadid() {
		return entidadid;
	}
	public void setEntidadid(String entidadid) {
		this.entidadid = entidadid;
	}
	public String getConvenioid() {
		return convenioid;
	}
	public void setConvenioid(String convenioid) {
		this.convenioid = convenioid;
	}
	public String getVEspecieConv() {
		return VEspecieConv;
	}
	public void setVEspecieConv(String vEspecieConv) {
		VEspecieConv = vEspecieConv;
	}
	public String getVEfectivoConv() {
		return VEfectivoConv;
	}
	public void setVEfectivoConv(String vEfectivoConv) {
		VEfectivoConv = vEfectivoConv;
	}
	public String getVTotal() {
		return VTotal;
	}
	public void setVTotal(String vTotal) {
		VTotal = vTotal;
	}

}