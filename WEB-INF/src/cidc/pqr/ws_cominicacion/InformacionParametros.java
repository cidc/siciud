package cidc.pqr.ws_cominicacion;

import org.dom4j.DocumentException;

import  cidc.pqr.ws_Bizagi_obj.ParametrosDatos;
import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;
import cidc.pqr.xmlRespPersona.xmlRespParametros;


public class InformacionParametros extends ConeccionDB_WS{
	
	ParametrosDatos parametros = new ParametrosDatos();
	
public ParametrosDatos ConsultarDatosParametros (String entidad, String filtro, String valorFiltro, ParametrosDatos parametrosDatos){
	
	super.setConnectionEM();
	
	String xmlBusquedaParametros = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">" +
			"<soapenv:Header/>" +
			"<soapenv:Body>" +
			"<soa:getEntitiesAsString>" +
			"<!--Optional:-->" +
			"<arg0>" +
			"<![CDATA[<BizAgiWSParam>" +
			"<EntityData>" +
			"<EntityName>"+entidad+"</EntityName>" +
			"<Filters>"+filtro+"="+valorFiltro+"</Filters>" +
			"</EntityData>" +
			"</BizAgiWSParam>]]>" +
			"</arg0>" +
			"</soa:getEntitiesAsString>" +
			"</soapenv:Body>" +
			"</soapenv:Envelope>";
	
	String buscarParametros = super.httpostConsultaEM(xmlBusquedaParametros);
	xmlRespParametros  parametroBuscado = new xmlRespParametros();
	
	if (entidad=="CierredelCaso"){
		System.out.println("busquedacasoxml: "+xmlBusquedaParametros);
	//	System.out.println("respbusquedacasoxml: "+buscarParametros);
	}
	
	
	try {
		parametros = parametroBuscado.ConsultarParametro(buscarParametros, entidad,  parametrosDatos );
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
	
	return parametros;
}
	
	

}
