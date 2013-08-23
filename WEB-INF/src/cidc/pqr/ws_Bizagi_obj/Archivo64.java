package cidc.pqr.ws_Bizagi_obj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.text.Normalizer;
import java.util.regex.Pattern;

import org.apache.axis.encoding.Base64;



public class Archivo64 {
	
	 
		public String encodeFileToBase64Binary(File file) throws IOException {
	 
			byte[] bytes = loadFile(file);
			byte[] encoded = org.apache.commons.codec.binary.Base64.encodeBase64(bytes);
			String encodedString = new String(encoded);
	 
			return encodedString;
		}
	 
		private static byte[] loadFile(File file) throws IOException {
		    InputStream is = new FileInputStream(file);
	 
		    long length = file.length();
		    if (length > Integer.MAX_VALUE) {
		        // File is too large
		    }
		    byte[] bytes = new byte[(int)length];
		    
		    int offset = 0;
		    int numRead = 0;
		    while (offset < bytes.length
		           && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
		        offset += numRead;
		    }
	 
		    if (offset < bytes.length) {
		        throw new IOException("Could not completely read file "+file.getName());
		    }
	 
		    is.close();
		    return bytes;
		}
		
		
		public String  decodificar  (String codificado, String nombreArchivo,String ruta){
		
		  try {
			  char sep=java.io.File.separatorChar;
		        Base64 b = new Base64();
		        nombreArchivo=remove2(nombreArchivo);
		        System.out.println("nombre del archivo: :) "+nombreArchivo);
		        ruta+=sep+"Documentos"+sep+"Bizagi"+sep;
		        byte[] imageBytes = b.decode(codificado); 
		        FileOutputStream fos = new FileOutputStream(ruta+nombreArchivo);
		        fos.write(imageBytes);
		        fos.close();
		    } catch (Exception e) {

		        System.out.println("Error :::" + e);
		        e.printStackTrace();
		    }
		  return nombreArchivo;
		}
		    
		public static String remove2(String input) {
		    // Descomposición canónica
		    String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);
		    // Nos quedamos únicamente con los caracteres ASCII
		    Pattern pattern = Pattern.compile("\\p{ASCII}+");
		    return pattern.matcher(normalized).replaceAll("");
		}//remove2
		
		
		
}
