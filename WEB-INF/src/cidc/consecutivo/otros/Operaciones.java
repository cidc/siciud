package cidc.consecutivo.otros;

import java.util.Calendar;
import java.util.Vector;

public class Operaciones {
	
	public Operaciones(){
		
	}
	
	/**
	 * crea un vector con los años desde el 2013 hasta el actual
	 * @return retorna el vector con los años correspondientes en orden descendente
	 */
	public Vector<Integer> Years(){
		int fechaInicio=2013;
		Calendar c1=Calendar.getInstance();
		int fechaActual=c1.get(Calendar.YEAR);
		Vector<Integer> lista= new Vector<Integer>(); 
		for (int i = fechaActual; i >=fechaInicio; i--) {
			lista.add(i);
		}
		return lista;
	}
	
	public boolean checkYear(int year){
		Calendar c1=Calendar.getInstance();
		int fechaActual=c1.get(Calendar.YEAR);
		if(year!=fechaActual)
			return false;
		else
			return true;
	}
	
	public int anoActual(){
		Calendar c1=Calendar.getInstance();
		int fechaActual=c1.get(Calendar.YEAR);
		return fechaActual;
	}

}
