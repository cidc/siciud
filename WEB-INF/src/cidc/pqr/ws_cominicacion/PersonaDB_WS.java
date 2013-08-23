package cidc.pqr.ws_cominicacion;
import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.StringEscapeUtils;
import org.dom4j.DocumentException;

import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;
import cidc.pqr.ws_Bizagi_obj.PersonaDatos;
import cidc.pqr.xmlRespPersona.XmlRespPersona;

public class PersonaDB_WS extends ConeccionDB_WS {

	PersonaDatos persona = null;
		
	public PersonaDatos getPersonaDatos(){
		return persona;
	}
	
	public void setPersonaDatos(PersonaDatos personadatos){
		this.persona = personadatos;
				
	}
	
public List<PersonaDatos> consulta(PersonaDatos datos){
	
	List<PersonaDatos>infoPersona = new ArrayList<PersonaDatos>();
	
	persona = datos;
	return infoPersona;
}

/*
public boolean  crearpersona(PersonaDatos crearper){
	boolean personaCreada= false;
	return personaCreada;	
}*/


public PersonaDatos buscarpersona(String documento){
	
	String documentopersona = String.valueOf(documento);
	String busquedapersona = new String();
	XmlRespPersona personaBuscada = new XmlRespPersona();
	super.setConnectionEM();
	
	String xmldatobusqueda="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">"
	  +" <soapenv:Header/>"
	   +"<soapenv:Body>"
	      +"<soa:getEntitiesAsString>"
	         +"<!--Optional:-->"
	         +"<arg0>"
	+"<![CDATA[<BizAgiWSParam><EntityData><EntityName>PERSONA</EntityName><Filters>DocumentodeIdentidadNIT="+documentopersona+"</Filters></EntityData></BizAgiWSParam>]]>"
	+"</arg0>"
	      +"</soa:getEntitiesAsString>"
	   +"</soapenv:Body>"
	+"</soapenv:Envelope>";
	 
	
	busquedapersona = super.httpostConsultaEM(StringEscapeUtils.unescapeXml(xmldatobusqueda));
	
	
	try {
		persona  = personaBuscada.ConsultarDatosPersona(busquedapersona);
		
		
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return persona;
	
}

public String  CrearPersona (PersonaDatos persona){
	
	String datosExterno = "";
	
	
	System.out.println("TIPO DE PERSONA"+persona.getTipoExterno());
	if (persona.getTipoExterno().equals("52")){
		datosExterno="<RepresentanteLega>"+persona.getRepresentante()+"</RepresentanteLega>"+
		"<Contacto>"+persona.getContacto()+"</Contacto>";		
	}
	
	
	super.setConnectionEM();
	String xmlCrearpersona= "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\">"
	  + "<soapenv:Header/>"
	   +"<soapenv:Body>"
	      +"<soa:saveEntityAsString>"
	         +"<!--Optional:-->"
	         +"<arg0>"
	           +"<![CDATA["
	           +"<BizAgiWSParam>"
	             +"<Entities>"
	                //+"<PERSONA>"
	                +"<PERSONA businessKey=\"DocumentodeIdentidadNIT='"+persona.getDocumentoIdNit()+"'\">"
	                 +"<Direccion>"+persona.getDireccion()+"</Direccion>"
	                 + "<DocumentodeIdentidadNIT>"+persona.getDocumentoIdNit()+"</DocumentodeIdentidadNIT>"
	                 + "<TelefonoMovil>"+persona.getTelefonoMovil()+"</TelefonoMovil>"
	                 +  "<NombreRazonSocial>"+persona.getNombreRazonSocial()+"</NombreRazonSocial>"
	                 +  "<Ciudad businessKey=\"id="+persona.getCiudad()+"\"/>"
	                 +  "<TipodeDocumento businessKey=\"id="+persona.getTipoDocumento()+"\"/>"
	                 +  "<Titulo businessKey=\"id="+persona.getTitulo()+"\"/>"
	                 +  "<CorreoElectronico>"+persona.getCorreoElectronico()+"</CorreoElectronico>"
	                 +datosExterno
	                 +  "<TipodePersona businessKey=\"id="+persona.getTipoExterno()+"\"/>"
	              //    +"<Contacto>"+persona.getContacto()+"</Contacto>"//----------------------
                    //     +  "<RepresentanteLega>"+persona.getRepresentante()+"</RepresentanteLega>"//-----
	                +"</PERSONA>"
	             +"</Entities>"
	          +"</BizAgiWSParam>]]>"
	+"</arg0>"
	      +"</soa:saveEntityAsString>"
	   +"</soapenv:Body>"
	+"</soapenv:Envelope>";
System.out.println("xml es: "+xmlCrearpersona);
	String crearPersona = super.httpostConsultaEM(xmlCrearpersona);
	System.out.println("RESPUESTA WS"+crearPersona);
	XmlRespPersona personaCreada = new XmlRespPersona();
	String mensajePerCreada = null;
	try {
		mensajePerCreada = personaCreada.CrearPersona(crearPersona);
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return mensajePerCreada;
	
}
	
}
