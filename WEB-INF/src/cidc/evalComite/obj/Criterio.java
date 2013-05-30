package cidc.evalComite.obj;

import java.io.Serializable;

public class Criterio implements Serializable{
	private float []valCri1;
	private float []valCri2;
	private float []valCri3;
	

	public float[] getValCri1() {
		return valCri1;
	}
	public float[] getValCri2() {
		return valCri2;
	}
	public float[] getValCri3() {
		return valCri3;
	}
	public void setValCri1(float[] valCri1) {
		this.valCri1 = valCri1;
	}
	public void setValCri2(float[] valCri2) {
		this.valCri2 = valCri2;
	}
	public void setValCri3(float[] valCri3) {
		this.valCri3 = valCri3;
	}
}
