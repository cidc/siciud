package cidc.pqr.ws_cominicacion;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.xml.internal.ws.encoding.XMLHTTPBindingCodec;

import cidc.pqr.ws_Bizagi_obj.HistoricoDatos;
import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;
import cidc.pqr.xmlRespPersona.XmlRespCaso;
import cidc.pqr.xmlRespPersona.XmlRespHistorico;
import cidc.pqr.xmlRespPersona.xmlRespParametros;

import java.util.Iterator;
import java.util.List;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class InformacionHistorico extends ConeccionDB_WS {

	List <HistoricoDatos> datosHistorico =  null;
	
	

	public List <HistoricoDatos> consultarHistoricoCaso(String numeroCaso, String path)  {
		
		super.setConnectionEM();
		char sep=java.io.File.separatorChar;
		path+=sep+"WEB-INF"+sep+"src"+sep+"cidc"+sep+"pqr"+sep+"archivosXml"+sep;
		LeerArchivoXML leerArchivoXml = new LeerArchivoXML();
		String xmlFileName = "D:/TOMCAT 6.0/webapps/siciud/WEB-INF/src/cidc/pqr/archivosXml/consultaHistorico.xml";
	    Document document = leerArchivoXml.getDocument( xmlFileName );
	    List listaElementos = document.selectNodes("/soapenv:Envelope/soapenv:Body/soa:getCaseDataUsingSchemaAsString/arg0");
	    Iterator iteraElementos = listaElementos.iterator();
		
		while(iteraElementos.hasNext()){
			Element e = (Element)iteraElementos.next();

			e.setText(numeroCaso);

		}
		
	    String xmlConsultarHistorico=document.asXML();
	  //  System.out.println("XML CONSULTA HISTORICO "+xmlConsultarHistorico);
		
		String XmlResCrearCasoPQR = super.httpostConsultaEM(xmlConsultarHistorico);
		XmlRespHistorico consultadeCasoPQR = new XmlRespHistorico();
		
		try {
			datosHistorico  = consultadeCasoPQR.ConsultarHistorico(XmlResCrearCasoPQR);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("RESPUESTA CONSULTA CASO\n"+XmlResCrearCasoPQR);
		
		
		
		return datosHistorico;
	}
	


}
