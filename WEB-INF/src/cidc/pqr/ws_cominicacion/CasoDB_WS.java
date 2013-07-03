package cidc.pqr.ws_cominicacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.DocumentException;

import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;
import cidc.pqr.ws_Bizagi_obj.CasoDatos;
import cidc.pqr.ws_Bizagi_obj.PersonaDatos;
import cidc.pqr.xmlRespPersona.XmlRespCaso;
import cidc.pqr.ws_Bizagi_obj.Archivo64;


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


public CasoDatos  CrearCaso (CasoDatos datosForm, PersonaDatos persona) throws IOException{
	
	super.setConnectionWF();
	String doc64 ="";
	if(datosForm.getArchivoCaso()==null){
		doc64 ="";
	}else{
		Archivo64 convertir64 = new Archivo64();
		//String ruta = DatosForm.getArchivoCaso();
		doc64 = convertir64.encodeFileToBase64Binary(datosForm.getArchivoCaso());
//		System.out.println(doc64);
	}
	
	String xmlCrearCaso= "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">"
    		+"<soapenv:Header/>"
	        +"<soapenv:Body>"
	+"<soa:createCasesAsString>"
	        +"<!--Optional:-->"+"<arg0><![CDATA[<BizAgiWSParam><domain>domain</domain><userName>admon</userName><Cases><Case><Process>AtencionDeAccionesCiudadan</Process><Entities><SolicituddeAccionesCiuda>" +
	        		"<TipodeSolicitante businessKey=\"id="+datosForm.getTipodeSolicitante()+"\"/>" +
	        		"<MediodeRecepcion businessKey=\"id="+datosForm.getMedioDeRecepcion()+"\"/>"+
	        		"<TipodeRequerimiento businessKey=\"id="+datosForm.getTipoDeRequerimiento()+"\"/>"+
	        		"<Asunto>"+datosForm.getAsunto()+"</Asunto>" +
	        		"<ArchivosdelCaso><File fileName=\""+datosForm.getArchivoCaso().getName()+"\">"+doc64+"</File></ArchivosdelCaso>"+
	        		"<Descripcion>"+datosForm.getDescripcion()+"</Descripcion>"+
	        		"<FlagsdelCaso><EscaladodeOtraDependencia>"+datosForm.getEscaladoOtraDependencia()+"</EscaladodeOtraDependencia><RecibirNotificacionesporCo>"+datosForm.getRecibirNotificacionesCorreo()+"</RecibirNotificacionesporCo></FlagsdelCaso>"+
	        		"<Persona businessKey=\"id="+persona.getPersonaID()+"\"/>"+
	        		//"<Persona businesskey=\"id="+persona.getPersonaID()+"\"/>"+
	        	//	 "<TipodeSolicitanteInterno businessKey=\"id="+persona.getTipoInterno()+"\"/>" +//---------------------------------------------------
               //      "<ProyectodeInvestigacion>" +
                  //           "<ProyectodeInvestigacion>"+persona.getProyInv()+"</ProyectodeInvestigacion><Codigo>"+persona.getCodigo()+"</Codigo><Facultad>"+persona.getFaculta()+"</Facultad></ProyectodeInvestigacion>"+
	        		
	        				"</SolicituddeAccionesCiuda>" +
	        			
	        					
	        		"</Entities></Case></Cases></BizAgiWSParam>]]></arg0>"
	        +"</soa:createCasesAsString>"
	+"</soapenv:Body>"
	+"</soapenv:Envelope>";

			String crearCaso = super.httpostConsultaEM(xmlCrearCaso);
			XmlRespCaso casoCreado = new XmlRespCaso();
			System.out.println("xml: "+xmlCrearCaso);
			try {
				System.out.println(crearCaso);
				caso = casoCreado.CrearCaso(crearCaso, datosForm);
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return caso;

}


	
}
