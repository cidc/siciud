package cidc.pqr.xmlRespPersona;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;

import cidc.pqr.ws_cominicacion.InformacionParametros;
import  cidc.pqr.ws_Bizagi_obj.ParametrosDatos;

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
	
	public ParametrosDatos consultaCasoPRQ(String xmlResCrearCasoPQR) throws DocumentException {
		SAXReader reader = new SAXReader();  
	    Document doc = reader.read(new StringReader(xmlResCrearCasoPQR)); 
	    InformacionParametros infoParametros = new InformacionParametros();
	    ParametrosDatos parametrosDatos = new ParametrosDatos();

	    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

	    doc.getRootElement().add(namespace1);
	/**************ATRIBUTOS *******************/	
List listaNumeroCaso = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/@key");

		Iterator iAtributosNumCaso = listaNumeroCaso.iterator();
		
		while (iAtributosNumCaso.hasNext()){

			Attribute a =(Attribute)iAtributosNumCaso.next(); 
			String numeroDeCaso = a.getValue();
			parametrosDatos.setCasoAsociado(numeroDeCaso);
		}
		List listaTipoRequerimiento = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/SolicituddeAccionesCiuda/TipodeRequerimiento/@key");

		Iterator iAtributosTipoRequer = listaTipoRequerimiento.iterator();
		while (iAtributosTipoRequer.hasNext()){
			Attribute a =(Attribute)iAtributosTipoRequer.next(); 
			String numeroTipoReq = a.getValue();
			System.out.println("XmlRespCaso Numero Requeremiento: " +numeroTipoReq);
			infoParametros.ConsultarDatosParametros("TipodeRequerimiento", "id", numeroTipoReq, parametrosDatos);

		}
		
		List listaResponsable = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/SolicituddeAccionesCiuda/Responsable/@key");

		Iterator iAtributosResponsable = listaResponsable.iterator();
		while (iAtributosResponsable.hasNext()){
			Attribute a =(Attribute)iAtributosResponsable.next(); 
			String numeroResponsable = a.getValue();
			System.out.println("XmlRespCaso Numero Responsable: " +numeroResponsable);
			infoParametros.ConsultarDatosParametros("WFUSER", "id", numeroResponsable, parametrosDatos);
			//infoParametros.ConsultarDatosParametros("ROLE", "id", numeroResponsable, parametrosDatos);

		}
		
		List listaNombreArchivo = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/SolicituddeAccionesCiuda/CierredelCaso/ArchivodeRespuesta/File/@fileName");
		Iterator iAtributosNombreArchivo = listaNombreArchivo.iterator();
		while (iAtributosNombreArchivo.hasNext()){
			Attribute a =(Attribute)iAtributosNombreArchivo.next(); 
			String nombreArchivo = a.getValue();
			parametrosDatos.setArchivoRespuestaNombre(nombreArchivo);
						
		}
		
		/*********ELEMENTOS********/
		
		List listaElementos = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/SolicituddeAccionesCiuda");
	    Iterator iteraElementos = listaElementos.iterator();
	    
	    while(iteraElementos.hasNext()){
			Element e = (Element)iteraElementos.next();
			e.getName();
			
			Iterator itAsuntoCaso = e.elementIterator("Asunto");
			while (itAsuntoCaso.hasNext()){
				Element eAsuntoCaso = (Element)itAsuntoCaso.next();
				//System.out.println("Valor Asunto: "+eAsuntoCaso.getText()+"\n");
				parametrosDatos.setCasoAsunto(eAsuntoCaso.getText());
			}
			
			Iterator itFechaAper = e.elementIterator("FechadeApertura");
			while (itFechaAper.hasNext()){
				Element eFechaAper = (Element)itFechaAper.next();
				//System.out.println("Valor FechaAper: "+eFechaAper.getText()+"\n");
				parametrosDatos.setCasoFechaApertura(eFechaAper.getText());
			}
			
			Iterator itFechaEstCierre = e.elementIterator("FechaEstimadadeCierre");
			while (itFechaEstCierre.hasNext()){
				Element eFechaEstCierre = (Element)itFechaEstCierre.next();
				//System.out.println("Valor FechaEstCierre: "+eFechaAper.getText()+"\n");
				parametrosDatos.setCasoFechaEstimadaCierre(eFechaEstCierre.getText());
			}
			
	    }
	    
	    List listaElementosArchivo = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/SolicituddeAccionesCiuda/CierredelCaso");
	    Iterator iteraArchivo = listaElementosArchivo.iterator();
	    while(iteraArchivo.hasNext()){
			Element e = (Element)iteraArchivo.next();
			e.getName();
			
			
			Iterator iterArchResp = e.elementIterator("ArchivodeRespuesta");
			while (iterArchResp.hasNext()){
				Element eArchResp = (Element)iterArchResp.next();
				
				
				Iterator iterFile = eArchResp.elementIterator("File");
				while (iterFile.hasNext()){
					Element eFile = (Element)iterFile.next();
					parametrosDatos.setArchivoRespuestaDocumento(eFile.getText());
				}
				
			}
			
			Iterator iterMensajeResp = e.elementIterator("Respuesta");
			while (iterMensajeResp.hasNext()){
				Element eMensajeResp = (Element)iterMensajeResp.next();
				parametrosDatos.setCierredelCasoRespuesta(eMensajeResp.getText());
			}
			
			Iterator iterFechaSolCaso = e.elementIterator("FechadeSoluciondelCaso");
			while (iterFechaSolCaso.hasNext()){
				Element eFechaSolCaso = (Element)iterFechaSolCaso.next();
				//System.out.println("Valor MenResp: "+eFechaSolCaso.getText());
				parametrosDatos.setCierredelCasoFechaRespuesta(eFechaSolCaso.getText());
			}
			
	    }
	    
	    List listaElementosPersona = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getCaseDataUsingSchemaAsStringResponse/return/BizAgiResponse/App/SolicituddeAccionesCiuda/Persona");
	    Iterator iteraPersona = listaElementosPersona.iterator();
	    while(iteraPersona.hasNext()){
			Element e = (Element)iteraPersona.next();
			e.getName();			
			
			Iterator iterTelMovil = e.elementIterator("TelefonoMovil");
			while (iterTelMovil.hasNext()){
				Element eTelMovil = (Element)iterTelMovil.next();
				parametrosDatos.setPersonaTelMov(eTelMovil.getText());
				
			 }
			
			Iterator iterCorreoE = e.elementIterator("CorreoElectronico");
			while (iterCorreoE.hasNext()){
				Element eCorreoE = (Element)iterCorreoE.next();
				parametrosDatos.setPersonaCorreo(eCorreoE.getText());
				
			 }
			
			Iterator iterNombreRaz = e.elementIterator("NombreRazonSocial");
			while (iterNombreRaz.hasNext()){
				Element eNombRaz = (Element)iterNombreRaz.next();
				parametrosDatos.setPersonaNombreRazon(eNombRaz.getText());
				
			 }
			
			  Iterator iterDocumento = e.elementIterator("DocumentodeIdentidadNIT");
              while (iterDocumento.hasNext()){
                      Element eDocumento = (Element)iterDocumento.next();
                      parametrosDatos.setPersonaDocumentoNIT(eDocumento.getText());
                     
                      
               }
			
			}
	    
		return parametrosDatos;
	}

	

}
