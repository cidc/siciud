package cidc.pqr.ws_cominicacion;

import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;
import cidc.pqr.ws_Bizagi_obj.CasoDatos;
import cidc.pqr.ws_Bizagi_obj.PersonaDatos;
import cidc.pqr.xmlRespPersona.XmlRespCaso;



public class CasoDB_WS extends ConeccionDB_WS {

	CasoDatos caso = null;
	
	
	public CasoDatos getPersonaDatos(){
		return caso;
	}
	
	
	public void setCasoDatos(CasoDatos casodatos){
		this.caso= casodatos;
				
	}
	
	
public List<CasoDatos> consulta(CasoDatos datos)  {
	
	List<CasoDatos>infoCaso = new ArrayList<CasoDatos>();
	
	caso = datos;
	return infoCaso;
}


public CasoDatos  CrearCaso (CasoDatos DatosForm, PersonaDatos persona){
	
	super.setConnectionWF();
	String xmlCrearCaso= "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">"
    		+"<soapenv:Header/>"
	        +"<soapenv:Body>"
	+"<soa:createCasesAsString>"
	        +"<!--Optional:-->"+"<arg0><![CDATA[<BizAgiWSParam><domain>domain</domain><userName>admon</userName><Cases><Case><Process>AtencionDeAccionesCiudadan</Process><Entities><SolicituddeAccionesCiuda>" +
	        		"<TipodeSolicitante businessKey=\"id="+DatosForm.getTipodeSolicitante()+"\"/>" +
	        		"<MediodeRecepcion businessKey=\"id="+DatosForm.getMedioDeRecepcion()+"\"/>"+
	        		"<TipodeRequerimiento businessKey=\"id="+DatosForm.getTipoDeRequerimiento()+"\"/>"+
	        		"<Asunto>"+DatosForm.getAsunto()+"</Asunto>" +
	        		"<Descripcion>"+DatosForm.getDescripcion()+"</Descripcion>"+
	        			"<FlagsdelCaso><EscaladodeOtraDependencia>"+DatosForm.getEscaladoOtraDependencia()+"</EscaladodeOtraDependencia><RecibirNotificacionesporCo>"+DatosForm.getRecibirNotificacionesCorreo()+"</RecibirNotificacionesporCo></FlagsdelCaso>"+
	        				"</SolicituddeAccionesCiuda>" +

	        		"</Entities></Case></Cases></BizAgiWSParam>]]></arg0>"
	        +"</soa:createCasesAsString>"
	+"</soapenv:Body>"
	+"</soapenv:Envelope>";

			String crearCaso = super.httpostConsultaEM(xmlCrearCaso);
			XmlRespCaso casoCreado = new XmlRespCaso();
			
			try {
				System.out.println(crearCaso);
				caso = casoCreado.CrearCaso(crearCaso, DatosForm);
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return caso;

}


	
}
