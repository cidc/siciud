package cidc.proyectos.servlet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cidc.general.login.Usuario;
import cidc.proyectos.obj.ProyectoGenerico;

public class GenerarXML {//podemos crear una clase abstracta y una interfaz que implemente los metodos
	
	public GenerarXML(){
		
	}
	
	public String crearPersona(Usuario usuario, ProyectoGenerico proy){
		switch (Integer.parseInt(usuario.getGenero())) {
		case 1://femenino
				usuario.setGenero("Sra.");
			break;
		case 2://masculino
			usuario.setGenero("Sr.");
			break;
		default:
			usuario.setGenero("Señores");
			break;
		}
		SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
		String xml=null;
		try {
			Date fechaCierre=formato.parse(proy.getFechaEstimadaFin());//esta dos lineas se pueden mejorar creando los atributos en el objeto e instanciandolos desde el inicio
			Date fechaInicio=formato.parse(proy.getFecActaInicio());
			xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\"><soapenv:Header/><soapenv:Body><soa:saveEntityAsString>" +
					"<!--Optional:--><arg0><![CDATA[<BizAgiWSParam><Entities>" +
					"<Proyecto businessKey=\"CodigodelProyecto='"+proy.getCodigo()+"'\">" +
					"<NombredelProyecto>"+proy.getNombre()+"</NombredelProyecto>" +
					"<CodigodelProyecto>"+proy.getCodigo()+"</CodigodelProyecto>" +
					"<ProyectodelCIDC>true</ProyectodelCIDC>" +
					"<EstadoDeProyecto>"+proy.getEstado()+"</EstadoDeProyecto>" +
					"<FechaDeInicio>"+fechaInicio+"</FechaDeInicio>" +
					"<FechaDeFinalizacion>"+fechaCierre+"</FechaDeFinalizacion>" +
					"</Proyecto>"+
					"<PERSONA businessKey=\"DocumentodeIdentidadNIT='"+usuario.getCedula()+"'\">" +
					"<Direccion>"+usuario.getDireccion()+"</Direccion>" +
					"<DocumentodeIdentidadNIT>"+usuario.getCedula()+"</DocumentodeIdentidadNIT>" +
					"<TelefonoMovil>"+usuario.getCelular()+"</TelefonoMovil>" +
					"<NombreRazonSocial>"+usuario.getNombre()+"</NombreRazonSocial>" +
					"<Ciudad businessKey=\"id=2\"/>" +
					"<TipodeDocumento businessKey=\"id='"+usuario.getTipoDoc()+"'\"/>" +
					"<Titulo businessKey=\"id="+usuario.getGenero()+"\"/>" +
					"<CorreoElectronico>"+usuario.getMail()+"</CorreoElectronico>" +
					"<TipodePersona businessKey=\"id=51\"/>" +
					"<Ciudad businessKey=\"Ciudad='Bogotá'\"/>"+
					"</PERSONA></Entities></BizAgiWSParam>]]></arg0></soa:saveEntityAsString></soapenv:Body></soapenv:Envelope>​";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return xml;
	}
	
	
public String crearCaso(Usuario usuario,ProyectoGenerico proy, int tipoRequerimiento ){
	String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\"><soapenv:Header/><soapenv:Body>" +
			"<soa:createCasesAsString><!--Optional:--><arg0><![CDATA[<BizAgiWSParam><domain>domain</domain>" +
			"<userName>admon</userName><Cases><Case>" +
			"<Process>ModificacionRubros</Process><Entities><ModificacionRubros>" +
			"<Persona businessKey=\"DocumentodeIdentidadNIT='"+usuario.getCedula()+"'\">" +
			"</Persona>" +
			"<Proyecto businessKey=\"CodigodelProyecto='"+proy.getCodigo()+"'\">" +
			"</Proyecto>" +
			"<TipoRequerimiento businessKey=\"ModificacionRubros='"+tipoRequerimiento+"'\"/>" +
			"</ModificacionRubros></Entities></Case></Cases></BizAgiWSParam>" +
			"]]></arg0></soa:createCasesAsString></soapenv:Body></soapenv:Envelope>";
		return xml;
	}
}
