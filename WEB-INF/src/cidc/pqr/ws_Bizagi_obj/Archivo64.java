package cidc.pqr.ws_Bizagi_obj;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;


public class Archivo64 {
	
	 
		public String encodeFileToBase64Binary(File file) throws IOException {
	 
			byte[] bytes = loadFile(file);
			byte[] encoded = Base64.encodeBase64(bytes);
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
}
