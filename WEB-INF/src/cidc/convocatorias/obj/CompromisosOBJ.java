package cidc.convocatorias.obj;

public class CompromisosOBJ {
	public int codigo;
	public String nombre;
	public String indicador;
	public int obligatorio;
	public int cantidad;
	public int compValor;
	public int valor;

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
	public void setValor(int valor) {
		this.valor = valor;
	}
	public int getValor() {
		return valor;
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
	public String getIndicador() {
		return indicador;
	}
	public void setIndicador(String indicador) {
		this.indicador = indicador;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int getCompValor() {
                return compValor;
        }
	public void setCompValor(int compValor) {
		this.compValor = compValor;
	}
}
