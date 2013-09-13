package cidc.certificaciones.obj;

public class CertificacionesOBJ {
	
	public int id_certificaciones;
	public String consCert;
	public String cod_verificacion;
	public int idPersona;
	public int idGrupo;
	public String nombreGrupo;
	public int facultad;
	public long tipoDoc;
	public String papel;	
	public String cuerpo_cer;
	public String fecha_cert;
	public String tipo;
	public boolean automatico;
	//Certificado de pertenencia a grupo
	public String cedula;
	public String nombre;
	public String numCedDe;
	public String nombreDirector;
	public String url;
	public String rol;
	public String categoriaGrupo;
	public String fechaIngreso;
	public String tipoGrupo;
	public String fechaSalida;
	
	public int getId_certificaciones() {
		return id_certificaciones;
	}
	public void setId_certificaciones(int idCertificaciones) {
		id_certificaciones = idCertificaciones;
	}
	public String getCod_verificacion() {
		return cod_verificacion;
	}
	public void setCod_verificacion(String codVerificacion) {
		cod_verificacion = codVerificacion;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public int getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(int idGrupo) {
		this.idGrupo = idGrupo;
	}
	public int getFacultad() {
		return facultad;
	}
	public void setFacultad(int facultad) {
		this.facultad = facultad;
	}
	public long getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(long tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getPapel() {
		return papel;
	}
	public void setPapel(String papel) {
		this.papel = papel;
	}
	public String getCuerpo_cer() {
		return cuerpo_cer;
	}
	public void setCuerpo_cer(String cuerpoCer) {
		cuerpo_cer = cuerpoCer;
	}
	public String getFecha_cert() {
		return fecha_cert;
	}
	public void setFecha_cert(String fechaCert) {
		fecha_cert = fechaCert;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isAutomatico() {
		return automatico;
	}
	public void setAutomatico(boolean automatico) {
		this.automatico = automatico;
	}
	public String getConsCert() {
		return consCert;
	}
	public void setConsCert(String consCert) {
		this.consCert = consCert;
	}
	public String getNombreGrupo() {
		return nombreGrupo;
	}
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNumCedDe() {
		return numCedDe;
	}
	public void setNumCedDe(String numCedDe) {
		this.numCedDe = numCedDe;
	}
	public String getNombreDirector() {
		return nombreDirector;
	}
	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getCategoriaGrupo() {
		return categoriaGrupo;
	}
	public void setCategoriaGrupo(String categoriaGrupo) {
		this.categoriaGrupo = categoriaGrupo;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public String getTipoGrupo() {
		return tipoGrupo;
	}
	public void setTipoGrupo(String tipoGrupo) {
		this.tipoGrupo = tipoGrupo;
	}
	public String getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	
}
