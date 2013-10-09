package cidc.proyectosGeneral.ProyectosXml;

import org.dom4j.DocumentException;

import cidc.proyectos.obj.ProyectoGenerico;
import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;
import cidc.proyectosGeneral.ProyectosXml.XmlRespProyecto;


public class ProyectoXML extends ConeccionDB_WS {

	//ProyectoDatos proyecto = null;
	
	
	public String crearProyectoBizagi (ProyectoGenerico infoProyecto){
		super.setConnectionEM();
		
		String xmlCrearProyecto ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">" +
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
				"<soa:saveEntityAsString>" +
				"<arg0><![CDATA[<BizAgiWSParam><Entities>" +
				"<Proyecto businessKey=\"CodigodelProyecto='"+infoProyecto.getCodigo()+"'\">" +
				"<CodigodelProyecto>"+infoProyecto.getCodigo()+"</CodigodelProyecto>" +
				"<NombredelProyecto>"+infoProyecto.getNombre()+"</NombredelProyecto>" +
				//"<ProyectodelCIDC>"+infoProyecto.getTipo2()+"</ProyectodelCIDC>"+
				"<DirectordeProyecto>"+infoProyecto.getDirector()+"</DirectordeProyecto>" +
				"</Proyecto>" +
				"</Entities></BizAgiWSParam>]]>" +
				"</arg0>" +
				"</soa:saveEntityAsString></soapenv:Body></soapenv:Envelope>";
		
		System.out.println("respuestaXML: "+xmlCrearProyecto);
		String crearProyecto = super.httpostConsultaEM(xmlCrearProyecto);
	
		System.out.println(crearProyecto);
		XmlRespProyecto proyectoCreado = new XmlRespProyecto();
		String MensajeProyectoCreado = null;
		try {
			MensajeProyectoCreado = proyectoCreado.respuestaCrearProyecto(crearProyecto);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return MensajeProyectoCreado;
		}
	
	
	public ProyectoGenerico consultarProyectoBizagi (String codigoProyecto, ProyectoGenerico datosProyecto)	{
		
		XmlRespProyecto proyectoBuscado = new XmlRespProyecto();
		super.setConnectionEM();
		
		String xmlBusquedaProyecto="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">" +
				"<soapenv:Header/>" +
				"<soapenv:Body>" +
				"<soa:getEntitiesAsString>" +
				"<arg0>" +
				"<![CDATA[<BizAgiWSParam>" +
				"<EntityData>" +
				"<EntityName>Proyecto</EntityName>" +
				"<Filters>CodigodelProyecto='"+codigoProyecto+"'</Filters>" +
				"</EntityData>" +
				"</BizAgiWSParam>]]>" +
				"</arg0>" +
				"</soa:getEntitiesAsString></soapenv:Body></soapenv:Envelope>";
	
		String consultarPersona = super.httpostConsultaEM(xmlBusquedaProyecto);
		System.out.println("respuestaXML: "+consultarPersona);
		
		try {
			proyecto = proyectoBuscado.respuestaConsultaProyecto(consultarPersona, datosProyecto);
			
			
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proyecto;
		
		
	}
	
	
	
}
