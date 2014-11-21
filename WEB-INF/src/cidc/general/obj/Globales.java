package cidc.general.obj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

import cidc.general.login.Usuario;


public class Globales{
	private String []datosFecha;
	//private SimpleDateFormat formatoFechaSimple = new SimpleDateFormat( "dd/MM/yyyy");
	private String fechaHoy=null;


	public Globales(){
		Date hoy=new Date();
		this.fechaHoy=hoy.toLocaleString();
	}

	/*public String getFechaCompletaHoy(){
		Date hoy=new Date();
		return hoy.toLocaleString();
	}*/

	public String getFechaSimpleHoy(){
		return fechaHoy.substring(0,fechaHoy.lastIndexOf("/20")+5).replaceAll("/", "-");
	}

	public String getFechaSimple(String fecha){
	//	System.out.println("********"+fecha);
		SimpleDateFormat formatoFechaSimple = new SimpleDateFormat( "dd/MM/yyyy");
		return  formatoFechaSimple.format(fecha);
	}

	public void splitFechaSimple(Date fecha){
		SimpleDateFormat formato = new SimpleDateFormat( "dd/MM/yyyy");
		StringTokenizer tokens=new StringTokenizer(formato.format(fecha),"/");
	//	StringTokenizer tokens=new StringTokenizer(""+fecha,"/");
		datosFecha=new String [tokens.countTokens()];
		int i=0;
		while(tokens.hasMoreTokens()){
			datosFecha[i]=tokens.nextToken();
      //      System.out.println("-> "+datosFecha[i]);
            i++;
        }
	}
	public void splitFecha(String fecha){
		StringTokenizer tokens=new StringTokenizer(fecha,"-");
		datosFecha=new String [tokens.countTokens()];
		int i=0;
		while(tokens.hasMoreTokens()){
			datosFecha[i]=tokens.nextToken();
      //      System.out.println("-> "+datosFecha[i]);
            i++;
        }
	}
	public void splitFecha2(String fecha){
		StringTokenizer tokens=new StringTokenizer(fecha,"/");
		datosFecha=new String [tokens.countTokens()];
		int i=0;
		String x="";
		while(tokens.hasMoreTokens()){
			datosFecha[i]=tokens.nextToken();
      //      System.out.println("-> "+datosFecha[i]);
            i++;
        }
		if(Integer.parseInt(datosFecha[0])>1000){
			x=datosFecha[0];
			datosFecha[0]=datosFecha[2];
			datosFecha[2]=x;
		}
	}



/*	public String getSplitAno(){
		return this.datosFecha[2];
	}
	public String getSplitMes(){
		return this.datosFecha[1];
	}*/

	public String getAnoHoy(){
		splitFechaSimple(new Date());
		return this.datosFecha[2];
	}
	public String getAnoCortoHoy(){
		splitFechaSimple(new Date());
		String corto=this.datosFecha[2].substring(2,4);

		return corto;
	}
	public String getMesHoy(){
		splitFechaSimple(new Date());
		return this.datosFecha[1];
	}
	public String getDiaHoy(){
		splitFechaSimple(new Date());
		return this.datosFecha[0];
	}


	public String getNombreMesHoy(){
		String mes="";
		splitFechaSimple(new Date());
		switch(Integer.parseInt(this.datosFecha[1])){
			case 1:mes="Enero";break;
			case 2:mes="Febrero";break;
			case 3:mes="Marzo";	break;
			case 4:mes="Abril";	break;
			case 5:mes="Mayo";	break;
			case 6:mes="Junio";	break;
			case 7:mes="Julio";	break;
			case 8:mes="Agosto";break;
			case 9:mes="Septiembre";break;
			case 10:mes="Octubre";break;
			case 11:mes="Noviembre";break;
			case 12:mes="Diciembre";break;
		}
		return mes;
	}

	public String getDiaFecha(String fecha,int tipoFecha){
		fecha=fecha.replace('-', '/');
//		tipoFecha= 2 cuando es mes-dia-aï¿½o
		//tipoFecha= 1 cuando es dia-mes-aï¿½o
		splitFecha2(fecha);
	//	System.out.println("----"+this.datosFecha[tipoFecha]);
		return (this.datosFecha[tipoFecha]);
	}
	public String getNombreMes(String fecha, int tipoFecha){
		String mes="";
		//tipoFecha= 1 cuando es mes-dia-aï¿½o
		//tipoFecha= 0 cuando es dia-mes-aï¿½o
	//	System.out.println(fecha+" - "+tipoFecha);
		fecha=fecha.replace('-', '/');
		splitFecha2(fecha);
		switch(Integer.parseInt(this.datosFecha[tipoFecha])){
			case 1:mes="Enero";break;
			case 2:mes="Febrero";break;
			case 3:mes="Marzo";	break;
			case 4:mes="Abril";	break;
			case 5:mes="Mayo";	break;
			case 6:mes="Junio";	break;
			case 7:mes="Julio";	break;
			case 8:mes="Agosto";break;
			case 9:mes="Septiembre";break;
			case 10:mes="Octubre";break;
			case 11:mes="Noviembre";break;
			case 12:mes="Diciembre";break;
		}
		return mes;
	}
	public String getAnoFecha(String fecha){
		fecha=fecha.replace('-', '/');
	//	System.out.println(fecha);
		splitFecha2(fecha);
		return (this.datosFecha[2]);
	}



	public String getMd5(String texto) {
        String output="";
        MessageDigest messagedigest=null;
       try {

           byte[] textBytes = texto.getBytes();
           messagedigest = MessageDigest.getInstance("MD5");
           messagedigest.update(texto.getBytes());
           byte[] codigo = messagedigest.digest();
           output = new String(codigo);

       } catch (NoSuchAlgorithmException ex) {
           ex.printStackTrace();
       }
       return new String(messagedigest.digest());

   }

	public String moneda(String dato){
		BigInteger convetir=new BigInteger(dato);
		Locale locale = Locale.US;
		String cadena = NumberFormat.getCurrencyInstance(locale).format(convetir);
		return cadena.substring(0, cadena.lastIndexOf("."));
	}

	public String sinMoneda(String dato){
		String []vect=dato.split(",");
		String retorno="";
		retorno=vect[0].substring(vect[0].lastIndexOf('$')+1,vect[0].length());
		for(int i=1;i<vect.length;i++){
			retorno=retorno+vect[i];
		}
		return retorno;
	}
	public String formatearNumero(double numero){
		String retorno=null;
		if(numero==0)
			retorno="0.0";
		else{
			NumberFormat formatter = new DecimalFormat("###,###,###.0");
			retorno=formatter.format(numero);
		}
		return retorno;
	}
	public double formatearNumeroDouble(double numero){
		String retorno=null;
		if(numero==0)
			retorno="0.0";
		else{
			NumberFormat formatter = new DecimalFormat("###.0");
			retorno=formatter.format(numero);
		}
		return Double.parseDouble(retorno);
	}
	public String formatearNumero(float numero){
		String retorno=null;
		if(numero==0)
			retorno="0.0";
		else{
			NumberFormat formatter = new DecimalFormat("###,###,###.0");
			retorno=formatter.format(numero);
		}
		return retorno;
	}

	public Date getgreGorianDateHoy(){
		GregorianCalendar gc =new GregorianCalendar(Integer.parseInt(getAnoHoy()),Integer.parseInt(getMesHoy()),Integer.parseInt(getDiaHoy()));
		return gc.getTime();
	}

	public boolean entreFechas(Date a, Date b, Date eval){
	//	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
	//	System.out.println(f.format(eval));

		GregorianCalendar gc =new GregorianCalendar(Integer.parseInt(getAnoHoy()),Integer.parseInt(getMesHoy()),Integer.parseInt(getDiaHoy()));
		//gc.setGregorianChange(date)
	//	System.out.println("--->"+gc.getTime());
		if(a.compareTo(eval)==0 || b.compareTo(eval)==0)
			return true;
		//System.out.println(a+" ----- "+b+" ----- "+eval);
		if(a.compareTo(b)<0)
			if(eval.compareTo(a)>0 && eval.compareTo(b)<0)
				return true;
		if(a.compareTo(b)>0)
			if(eval.compareTo(a)<0 && eval.compareTo(b)>0)
				return true;
		return false;
	}

	public boolean entreFechas(String inicio, String fin, String comp){
		//	SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
		//	System.out.println(f.format(eval));
			Date a=new Date(inicio);
			Date b=new Date(fin);
			Date eval=new Date(comp);
			GregorianCalendar gc =new GregorianCalendar(Integer.parseInt(getAnoHoy()),Integer.parseInt(getMesHoy()),Integer.parseInt(getDiaHoy()));
			//gc.setGregorianChange(date)
		//	System.out.println("--->"+gc.getTime());

			if(a.compareTo(eval)==0 || b.compareTo(eval)==0)
				return true;
			//System.out.println(a+" ----- "+b+" ----- "+eval);
			if(a.compareTo(b)<0)
				if(eval.compareTo(a)>0 && eval.compareTo(b)<0)
					return true;
			if(a.compareTo(b)>0)
				if(eval.compareTo(a)<0 && eval.compareTo(b)>0)
					return true;
			return false;
		}

	public static void calendario(){
		Calendar hoy = Calendar.getInstance();
		hoy.set(2010, 0, 27);
		hoy.add(Calendar.DATE, 3);
	//	hoy.add(Calendar.MONTH, 2);
		System.out.println(hoy.getTime());
	}

	public String sumarMesesFecha(String fecha,int meses){
		String retorno=null;
		String []datos=new String[3];
		Calendar inicial = Calendar.getInstance();
		if(fecha.contains("/")){
			datos=fecha.split("/");
			inicial.set(Integer.parseInt(datos[2]), Integer.parseInt(datos[1])-1, Integer.parseInt(datos[0]));
		}else{
			datos=fecha.split("-");
			inicial.set(Integer.parseInt(datos[0]), Integer.parseInt(datos[1])-1, Integer.parseInt(datos[2]));
		}
		inicial.add(Calendar.MONTH, meses);
		//inicial.add(Calendar.DATE, -1);
		retorno=inicial.get(Calendar.YEAR)+"-";
		if((""+(inicial.get(Calendar.MONTH)+1)).length()==1)
			retorno+="0"+(inicial.get(Calendar.MONTH)+1);
		else
			retorno+=(inicial.get(Calendar.MONTH)+1);
		inicial.add(Calendar.DATE, -1);
		if((""+(inicial.get(Calendar.DATE))).length()==1)
			retorno+="-0"+(inicial.get(Calendar.DATE));			
		else
			retorno+="-"+(inicial.get(Calendar.DATE));
		return retorno;
	}

	public String getHoraSistema(){
		String fecha="";
		Calendar calendario = Calendar.getInstance();
		int hora, minutos;
		hora =calendario.get(Calendar.HOUR_OF_DAY);
		minutos = calendario.get(Calendar.MINUTE);		
		fecha= hora+":"+minutos;		
		return fecha;
		
	}
	public static void main(String[] args) {
		String x="2.455.667.8.9";
		if(x.contains("."))
			System.out.println("-->"+x.replace(".", ""));
		else
			System.out.println("nones");
		/*	Globales g=new Globales();
			Date d= new Date();
		System.out.println(g.getAnoHoy()+"-"+g.getMesHoy()+"-"+g.getDiaHoy());
		g.calendario();
	//	System.out.println(g.entreFechas(new Date("2010/02/15"),new Date("2010/02/19"),new Date()));

		try {

			System.out.println(Runtime.getRuntime().exec("ping 127.0.0.1"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	//	System.out.println("***"+g.getDiaFecha("2/08/2009", 0));
	//	System.out.println(g.getNombreMes("28/12/2009", 1));
	//	System.out.println(g.getAnoHoy());
	//	System.out.println(g.formatearNumeroDouble(8,687542215896));
	//	BigInteger cidc=BigInteger.valueOf(987987);
	//	System.out.println(g.moneda("9879872345234"));

	}
	
	/**
	 * agrega a cualquier numero ceros a la izquierda hasta que quede de 4 digitos
	 * @param cod es el numero en formato texto
	 * @return
	 */
	public String codigo(String cod){
		Calendar cal = Calendar.getInstance();
		int year=cal.get(Calendar.YEAR);
		Formatter fmt = new Formatter();
		fmt.format("%04d",Integer.parseInt(cod));
		cod="CIDC_"+fmt+"_"+year;
		System.out.println("El numero formateado " + cod);
		return cod;
	}
	
	/**
	 * El metodo crea una lista de años, inicia en el año que se envia como parametro y termina en el actual
	 * @param ano año desde el cual inicia la lista
	 * @return retorna una lista de años desde el año que se envia como parametro inicial hasta el año actual
	 */
	public List<Integer> listarAnos(String ano){
		int inicio=Integer.parseInt(ano);
		List<Integer> lista =new ArrayList<Integer>();
		int actual=Integer.parseInt(getAnoHoy());
		for (int i = inicio; i <= actual; i++) {
			lista.add(inicio);
			inicio++;
		}
		return lista;
	}
	
	/**
	 * El metodo codifica un archivo a base64 
	 * @param file
	 * @return
	 */
	public String convertirBase64(File file){
		byte[] fileArray = new byte[(int) file.length()];
		InputStream inputStream;
		Base64 base64 = new Base64();
		String encodedFile = "";
		try {
			inputStream = new FileInputStream(file);
			inputStream.read(fileArray);
			encodedFile = base64.encodeToString(fileArray);
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//System.out.println(encodedFile);	
		
		return encodedFile;
		
	}
}
