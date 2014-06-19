package cidc.general.obj;

public class CrearClaves {
	private int cont=0,numLetraASCII;
	private char letra;

	public String getClave(){
	StringBuffer codigo = new StringBuffer();
	while(cont<10){
	    numLetraASCII=(int)(Character.MAX_HIGH_SURROGATE*Math.random());//Esto es para que de numero del 0 al maximo numero de la codificacion UTF-16
	   	if(numLetraASCII>=48 && numLetraASCII<=57){
	   		letra=(char)numLetraASCII;//Es un caracter numerico
		    cont++;
		    codigo.append(letra);//el append se usa para concatenar
	    }
	   	if(numLetraASCII>=65 && numLetraASCII<=90){
	   		letra=(char)numLetraASCII;//Es una letra mayuscula
	   	    cont++;
	   	    codigo.append(letra);
	    }
	    if(numLetraASCII>=97&& numLetraASCII<=122){
	       letra=(char)numLetraASCII;//Es una letra minuscula
	   	    cont++;
	        codigo.append(letra);
	    }
	}
	//System.out.println("clave= "+codigo);
	return codigo.toString();
	}
}
