package cidc.consecutivo.obj;

import java.util.Date;

public class CorrespondenciaObj {

	private int id;
	private String cod;
	private String remitente;
	private String destinatario;
	private String observaciones;
	private int ano;
	private Date fecha;
	
	public CorrespondenciaObj(int id, String cod, String remitente,
			String destinatario, String observaciones,int ano) {
		super();
		this.id = id;
		this.cod = cod;
		this.remitente = remitente;
		this.destinatario = destinatario;
		this.observaciones = observaciones;
		this.ano=ano;
	}
	
	public CorrespondenciaObj() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public String getRemitente() {
		return remitente;
	}

	public void setRemitente(String remitente) {
		this.remitente = remitente;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


}
