package cidc.pqr.xmlRespPersona;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;

import cidc.pqr.ws_Bizagi_obj.CasoDatos;



public class XmlRespCaso {
	
	//CasoDatos caso = new CasoDatos();
	private String codigoCasoCreado = null;
	
	public String getCodigoCasoCreado() {
		return codigoCasoCreado;
	}

	public void setCodigoCasoCreado(String codigoCasoCreado) {
		this.codigoCasoCreado = codigoCasoCreado;
	}

	
		
	public CasoDatos CrearCaso(String xmlCrearCaso, CasoDatos DatosForm)throws DocumentException {
		System.out.println("Entra metodo : ");
		SAXReader reader = new SAXReader();  
	    Document doc = reader.read(new StringReader(xmlCrearCaso)); 
	    //System.out.println(doc.asXML()+" fin");	  //Codigo XML recibido 
	      
	    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

	    doc.getRootElement().add(namespace1);
	    
	    List listaElementos = doc.selectNodes("/soap:Envelope/soap:Body/ns2:createCasesAsStringResponse/return/processes/process");
	    
	    Iterator iteraElementosCaso = listaElementos.iterator();
	    //System.out.println(""+);
		
		while(iteraElementosCaso.hasNext())
		{
			Element ePrincipal = (Element)iteraElementosCaso.next();
			System.out.println("Nombre del elemento : "+ePrincipal.getName());
			//System.out.println("Valor del elemento : "+ePrincipal.getText()+"\n");
			ePrincipal.getName();
			
			Iterator itIdCaso = ePrincipal.elementIterator("processId");
			while (itIdCaso.hasNext())
			{
				Element eIdCaso = (Element)itIdCaso.next();
				System.out.println("Nombre del elemento: "+eIdCaso.getName());
				System.out.println("Valor del elemento: "+eIdCaso.getText()+"\n");
				DatosForm.setCasoId(eIdCaso.getText());
			}
			
			Iterator itFechaEstSol = ePrincipal.elementIterator("processSolutionDate");
			while (itFechaEstSol.hasNext()){
				Element eFechaEstSol = (Element)itFechaEstSol.next();
				System.out.println("Nombre del elemento: "+eFechaEstSol.getName());
				System.out.println("Valor del elemento: "+eFechaEstSol.getText()+"\n");
				DatosForm.setFechaEstimadaCierre(eFechaEstSol.getText());
			}
		}
		
		return DatosForm;
	}

	

}
