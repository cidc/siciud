package cidc.pqr.xmlRespPersona;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;
import cidc.pqr.ws_Bizagi_obj.HistoricoDatos;



public class XmlRespHistorico {

	
	//HistoricoDatos objetoDatosHisto = new HistoricoDatos();
	//List listaConsutaHisto = null;
	List<HistoricoDatos> ListahistoDatos= new ArrayList<HistoricoDatos>();
	
	public List <HistoricoDatos> ConsultarHistorico(String buscarHistoricoCaso) throws DocumentException {
		// TODO Auto-generated method stub
		
		SAXReader reader = new SAXReader();  
	    Document doc = reader.read(new StringReader(buscarHistoricoCaso)); 
	    
	    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

	    doc.getRootElement().add(namespace1);
		
	    List listaElemeAcciones = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/SolicituddeAccionesCiuda/HistoriadelCaso/HistoricodeCasosdeAtenci/Accion");
	    Iterator iteraElemeAcciones = listaElemeAcciones.iterator();
	    
    
	    int posicion = 0;
		while(iteraElemeAcciones.hasNext()){
			Element e = (Element)iteraElemeAcciones.next();
			e.getName();
			
			Iterator itAccion = e.elementIterator("Accion");
			while (itAccion.hasNext()){
				Element eAccion = (Element)itAccion.next();
				//System.out.println("Valor del elemento Accion 2: "+eAccion.getText()+"\n");
				
				ListahistoDatos.add(new HistoricoDatos());
				ListahistoDatos.get(posicion).setAccionHistorico(eAccion.getText());
				
				posicion=posicion+1;

						}
			}
		
		 List listaElemeFechaUsuario = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/SolicituddeAccionesCiuda/HistoriadelCaso/HistoricodeCasosdeAtenci");
		    Iterator iteraElemeFechaUsuario = listaElemeFechaUsuario.iterator();
		
		    
		     posicion = 0;
		    while(iteraElemeFechaUsuario.hasNext()){
				Element e = (Element)iteraElemeFechaUsuario.next();

				e.getName();
				
				Iterator itFecha = e.elementIterator("Fecha");
				while (itFecha.hasNext()){
					Element eFecha = (Element)itFecha.next();

				//	System.out.println("Valor del elemento Fecha: "+eFecha.getText()+"\n");
					ListahistoDatos.get(posicion).setFechaHistorico(eFecha.getText());
			
							}
				
				Iterator itUsuario = e.elementIterator("Usuario");
				while (itUsuario.hasNext()){
					Element eUsuario = (Element)itUsuario.next();
					//System.out.println("Valor del elemento Usuario: "+eUsuario.getText()+"\n");
					ListahistoDatos.get(posicion).setUsuario(eUsuario.getText());
							}
				posicion=posicion+1;
		    }
		    
		    

	  
	 
		return ListahistoDatos;
	}

	
	
	
	
	
}
