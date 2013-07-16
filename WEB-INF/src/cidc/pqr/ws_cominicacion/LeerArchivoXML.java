package cidc.pqr.ws_cominicacion;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

public class LeerArchivoXML {

	//public static Document getDocument( final String xmlFileName )
	public  Document getDocument( final String xmlFileName )
	{
	   Document document = null;
	   SAXReader reader = new SAXReader();
	   try
	   {
	      document = reader.read( xmlFileName );
	   }
	   catch (DocumentException e)
	   {
	      e.printStackTrace();
	   }
	   return document;
	}
	
	
}
