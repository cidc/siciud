package cidc.evalMovilidad.obj;

public class InfoEvento {

	private String nombreEvento;
	private String pagWeb;
	private int cant;

	public int getCant() {
		return cant;
	}
	public String getNombreEvento() {
		return nombreEvento;
	}
	public String getPagWeb() {
		return pagWeb;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public void setNombreEvento(String nombreEvento) {
		this.nombreEvento = nombreEvento;
	}
	public void setPagWeb(String pagWeb) {
		this.pagWeb = pagWeb;
	}

}
