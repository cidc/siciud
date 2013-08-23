package cidc.pqr.ws_cominicacion;

import java.util.List;

import org.dom4j.DocumentException;


import cidc.pqr.xmlRespPersona.XmlRespListasAtributos;

import cidc.pqr.ws_Bizagi_obj.ListasAtributosDatos;
import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;

public class ListasAtributos extends ConeccionDB_WS {

	
	List<ListasAtributosDatos> listaAtrib = null;
	
	
	
	public List<ListasAtributosDatos> consultarAtributos (String entidad) {
		
		super.setConnectionEM();
	
		String xmlConsultaListaAtributos = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">" +
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
				"<soa:getEntitiesAsString><!--Optional:-->" +
				"<arg0>" +
				"<![CDATA[<BizAgiWSParam><EntityData><EntityName>"+entidad+"</EntityName><Filters></Filters></EntityData></BizAgiWSParam>]]>" +
				"</arg0>" +
				"</soa:getEntitiesAsString></soapenv:Body></soapenv:Envelope>";
		
		
		String xmlResCrearListAtrib = super.httpostConsultaEM(xmlConsultaListaAtributos);
		System.out.println("\nXML ENVIADO LISTA ATRIBUTOS \n"+xmlConsultaListaAtributos);
		XmlRespListasAtributos consultadeListAtrib = new XmlRespListasAtributos();
		System.out.println("\nXML RESPUESTA LISTA ATRIBUTOS \n"+xmlResCrearListAtrib);
		
		try {
			listaAtrib  = consultadeListAtrib.ConsultarListaAtrib(xmlResCrearListAtrib, entidad);

		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaAtrib;
	
	}
	
}
