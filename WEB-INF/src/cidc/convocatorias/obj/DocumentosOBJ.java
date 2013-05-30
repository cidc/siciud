package cidc.convocatorias.obj;

public class DocumentosOBJ {
	public int codigo;
	public String nombre;
	public boolean estado;
	public int obligatorio;

	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public boolean getEstado() {
                return estado;
        }
        public void setEstado(boolean estado) {
                this.estado = estado;
        }
	public int isObligatorio() {
                return obligatorio;
        }
        public void setObligatorio(int obligatorio) {
                this.obligatorio = obligatorio;
        }
        public int getObligatorio() {
                return obligatorio;
        }


}
