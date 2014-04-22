package cidc.certificaciones.signer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Enumeration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.logic.pki.KeyStoreTools;
import edu.logic.pki.PDFSignVerifier;
import edu.logic.pki.PDFSigner;

public class Firmar {
	public ResourceBundle rb;
	
public String firmarPDF(File documento,String ruta) throws Exception{
        
        //String pdfFile = "docs/prueba_P12.pdf";
		rb=ResourceBundle.getBundle("cidc.general.conect");
        KeyStore p12 = KeyStore.getInstance("pkcs12");
        p12.load(new FileInputStream(ruta),rb.getString("clave1").toCharArray());
        Enumeration en = p12.aliases();
        File pdfSigned=null;
        while (en.hasMoreElements()) {
            String object = (String) en.nextElement();
            PrivateKey key = (PrivateKey) p12.getKey(object, rb.getString("clave1").toCharArray());
            Certificate[] cc = p12.getCertificateChain(object); 
            
             //Sign PDF
            PDFSigner pdfs = new PDFSigner();
            pdfSigned = pdfs.sign(documento, key, cc[0]);
        }
        System.out.println("nombre: "+pdfSigned.getName());
        return pdfSigned.getName();
    }

}
