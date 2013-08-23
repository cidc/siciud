package cidc.pqr.ws_cominicacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;

import cidc.pqr.ws_cominicacion.LeerArchivoXML;


import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;
import cidc.pqr.ws_Bizagi_obj.CasoDatos;
import cidc.pqr.ws_Bizagi_obj.PersonaDatos;
import cidc.pqr.xmlRespPersona.XmlRespCaso;
import cidc.pqr.ws_Bizagi_obj.Archivo64;
import  cidc.pqr.ws_Bizagi_obj.ParametrosDatos;
import cidc.pqr.xmlRespPersona.xmlRespParametros;

public class CasoDB_WS extends ConeccionDB_WS {

	CasoDatos caso = null;
	ParametrosDatos parametrosDatos = null;
	
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
	String nombreArchivo ="";
	String cadena="";
	String cadenaTipoSolicitanteInterno="";

	if(datosForm.getArchivoCaso()!=null){
		Archivo64 convertir64 = new Archivo64();
		//String ruta = DatosForm.getArchivoCaso();
		doc64 = convertir64.encodeFileToBase64Binary(datosForm.getArchivoCaso());
		nombreArchivo=datosForm.getArchivoCaso().getName();
		cadena="<ArchivosdelCaso><File fileName=\""+nombreArchivo+"\">"+doc64+"</File></ArchivosdelCaso>";
		
	
	}
	
	if (!persona.getTipoInterno().equals("0")){
		cadenaTipoSolicitanteInterno= "<TipodeSolicitanteInterno businessKey=\"id="+persona.getTipoInterno()+"\"/>";
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
	        		cadena+
	        		"<Descripcion>"+datosForm.getDescripcion()+"</Descripcion>"+
	        		"<GestiondeNotificaciones><MediodeNotificacion businessKey=\"id="+persona.getMedioRespuesta()+"\"/></GestiondeNotificaciones>"+
	        		"<FlagsdelCaso><EscaladodeOtraDependencia>false</EscaladodeOtraDependencia><EscaladodeOtraDependencia>false</EscaladodeOtraDependencia><RecibirNotificacionesporCo>"+datosForm.getRecibirNotificacionesCorreo()+"</RecibirNotificacionesporCo></FlagsdelCaso>"+
	        		"<ProyectodeInvestigacion><ProyectodeInvestigacion>"+persona.getProyInv()+"</ProyectodeInvestigacion><Codigo>"+persona.getCodigo()+"</Codigo><Facultad>"+persona.getFacultad()+"</Facultad></ProyectodeInvestigacion>"+
	        		"<Persona businessKey=\"id="+persona.getPersonaID()+"\"/>"+persona.getTipoInterno()+
	        		
	        		cadenaTipoSolicitanteInterno +//---------------------------------------------------
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
				//System.out.println(crearCaso);
				caso = casoCreado.CrearCaso(crearCaso, datosForm);
				
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	return caso;

}

public ParametrosDatos consultarCasoPQR (String numeroCaso, String path){
	
	super.setConnectionEM();
	char sep=java.io.File.separatorChar;
	path+=sep+"WEB-INF"+sep+"src"+sep+"cidc"+sep+"pqr"+sep+"archivosXml"+sep;
	LeerArchivoXML leerArchivoXml = new LeerArchivoXML();
	String xmlFileName = path+"consultaCasoPQR.xml";
    Document document = leerArchivoXml.getDocument( xmlFileName );
    List listaElementos = document.selectNodes("/soapenv:Envelope/soapenv:Body/soa:getCaseDataUsingSchemaAsString/arg0");
    Iterator iteraElementos = listaElementos.iterator();
	
	while(iteraElementos.hasNext()){
		Element e = (Element)iteraElementos.next();

		e.setText(numeroCaso);

	}

    String xmlConsultarCasoPQR=document.asXML();
   // System.out.println(xmlConsultarCasoPQR);
	
	String XmlResCrearCasoPQR = super.httpostConsultaEM(xmlConsultarCasoPQR);
	XmlRespCaso consultadeCasoPQR = new XmlRespCaso();
	
	try {
		parametrosDatos  = consultadeCasoPQR.consultaCasoPRQ(XmlResCrearCasoPQR);
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println("RESPUESTA CONSULTA CASO\n"+XmlResCrearCasoPQR);
	if(parametrosDatos.getPersonaDocumentoNIT()==null)
	    consultaPQR_Scope(numeroCaso, parametrosDatos);
	
	
	return parametrosDatos;
}


public ParametrosDatos consultaPQR_Scope(String numeroCaso, ParametrosDatos parametros_Scope){
    
    super.setConnectionEM();
    
    String xmlConsultarCasoScpe="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">" +
            "   <soapenv:Header/>" +
            "   <soapenv:Body>" +
            "<soa:getCaseDataUsingXPathsAsString>" +
            "<!--Optional:-->" +
            "<arg0>" +
            "<![CDATA[" +
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                    "<BizAgiWSParam>" +
                    "<CaseInfo>" +
                    "<CaseNumber>"+numeroCaso+"</CaseNumber>" +
                    "<idTask>"+71+"</idTask>"+
                    "</CaseInfo>" +
                    "<XPaths>" +
                    "<XPath XPath=\"SolicituddeAccionesCiuda.Persona.DocumentodeIdentidadNIT\"/>" +
                            "<XPath XPath=\"SolicituddeAccionesCiuda.Asunto\"/>" +
                                    "<XPath XPath=\"SolicituddeAccionesCiuda.FechadeApertura\"/>" +
                                            "</XPaths>" +
                                            "</BizAgiWSParam>]]>" +
                                            "</arg0>" +
                                            "      </soa:getCaseDataUsingXPathsAsString>" +
                                            "   </soapenv:Body></soapenv:Envelope>";
    
    String consultarCasoScope = super.httpostConsultaEM(xmlConsultarCasoScpe);
    XmlRespCaso CasoScopeConsultar = new XmlRespCaso();
  //  System.out.println("RESPUESTA XML SCOPE: "+ consultarCasoScope);
    
    try {
    //    System.out.println("RESPUESTA XML SCOPE: ");
        parametros_Scope  = CasoScopeConsultar.consultaCasoScope(consultarCasoScope, parametros_Scope);
    } catch (DocumentException e) {
        
        e.printStackTrace();
    }
        
    return parametros_Scope;
}







	
}
