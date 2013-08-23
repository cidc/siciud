package cidc.pqr.xmlRespPersona;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;

import  cidc.pqr.ws_Bizagi_obj.ParametrosDatos;

public class xmlRespParametros {

	String entidad;
	
	public ParametrosDatos ConsultarParametro(String buscarParametros, String entidad, ParametrosDatos parametrosDatos) throws DocumentException {
		// TODO Auto-generated method stub
		
		this.entidad=entidad;
		if (entidad=="TipodeRequerimiento"){
			
			SAXReader reader = new SAXReader();  
		    Document doc = reader.read(new StringReader(buscarParametros)); 
		    
		    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

		    doc.getRootElement().add(namespace1);
			
			List listaElementos = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/TipodeRequerimiento");
		    Iterator iteraElementos = listaElementos.iterator();
		    
			while(iteraElementos.hasNext()){
				Element e = (Element)iteraElementos.next();
				
				Iterator itTipoReqNom = e.elementIterator("TipodeRequerimiento");
				while (itTipoReqNom.hasNext()){
					Element eTipoReqNom = (Element)itTipoReqNom.next();
						parametrosDatos.setTipodeRequerimientoNombre(eTipoReqNom.getText());
				}
				
				Iterator itTipoReqTiempoResp = e.elementIterator("TiempodeRespuesta");
				while (itTipoReqTiempoResp.hasNext()){
					Element eTipoReqTiempResp = (Element)itTipoReqTiempoResp.next();
					parametrosDatos.setTipodeRequerimientoTiempoResp(eTipoReqTiempResp.getText());
				}
			}
			

		    

			
		}
		else if (entidad=="WFUSER"){
			
			SAXReader reader = new SAXReader();  
		    Document doc = reader.read(new StringReader(buscarParametros)); 
		    
		    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

		    doc.getRootElement().add(namespace1);
			
			List listaElemResponsable = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/WFUSER");
		    Iterator iteraElemResponsable = listaElemResponsable.iterator();
		    
		    while(iteraElemResponsable.hasNext()){
				Element e = (Element)iteraElemResponsable.next();
				
				Iterator itNombResponsable = e.elementIterator("fullName");
				while (itNombResponsable.hasNext()){
					Element eTipoRespon = (Element)itNombResponsable.next();
					parametrosDatos.setResponsableNombre(eTipoRespon.getText());
				}
				
				Iterator itTipoCorreoResp = e.elementIterator("contactEmail");
				while (itTipoCorreoResp.hasNext()){
					Element eCorreoResp = (Element)itTipoCorreoResp.next();
					parametrosDatos.setResponsableCorreo(eCorreoResp.getText());
				}
				
				Iterator itTelRespon = e.elementIterator("contactCell");
				while (itTelRespon.hasNext()){
					Element eTeloResp = (Element)itTelRespon.next();
					parametrosDatos.setResponsableTelefono(eTeloResp.getText());
				}
				
			Iterator itRole = e.elementIterator("Roles");
				while (itRole.hasNext()){
					Element eIdRole = (Element)itRole.next();
					System.out.println("Valor idRole: "+eIdRole.getText()+"\n");
				}
			}
		}
		else if (entidad=="CierredelCaso"){
			SAXReader reader = new SAXReader();  
		    Document doc = reader.read(new StringReader(buscarParametros)); 
		    
		    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

		    doc.getRootElement().add(namespace1);	
					    
			List listaElemCierreCaso = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/CierredelCaso");
		    Iterator iteraElemCierraCaso = listaElemCierreCaso.iterator();
			
		    while(iteraElemCierraCaso.hasNext()){
				Element e = (Element)iteraElemCierraCaso.next();
				//System.out.println("Nombre del elemento : "+e.getName());
							
				Iterator itArchivoRespuesta = e.elementIterator("ArchivodeRespuesta");
				while (itArchivoRespuesta.hasNext()){					
					Element eArchivoRespuesta = (Element)itArchivoRespuesta.next();
				
					Iterator itFile = eArchivoRespuesta.elementIterator("File");
					while (itFile.hasNext()){
						Element eFile = (Element)itFile.next();
					System.out.println("Valor Archivo Nombre: "+eFile.getName()+"\n");
					System.out.println("Valor Archivo codificado: "+eFile.getText()+"\n");

					}
				}		
				
				
				Iterator itRespuestaCaso = e.elementIterator("Respuesta");
				while (itRespuestaCaso.hasNext()){
					Element eRespuestaCaso = (Element)itRespuestaCaso.next();
					//System.out.println("Valor RespuestadelCaso: "+eRespuestaCaso.getText()+"\n");
					parametrosDatos.setCierredelCasoRespuesta(eRespuestaCaso.getText());
				}
				
				
				Iterator itFechaRespuesta = e.elementIterator("FechadeSoluciondelCaso");
				while (itFechaRespuesta.hasNext()){
					Element eFechaRespuesta = (Element)itFechaRespuesta.next();
					//System.out.println("Valor FechaRespuestadelCaso: "+eFechaRespuesta.getText()+"\n");
					parametrosDatos.setCierredelCasoFechaRespuesta(eFechaRespuesta.getText());
				}
				
		    }
			
			
		}
		else if (entidad=="ROLE"){
			SAXReader reader = new SAXReader();  
		    Document doc = reader.read(new StringReader(buscarParametros)); 
		    
		    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

		    doc.getRootElement().add(namespace1);	
					    
			List listaElemRole = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/ROLE");
		    Iterator iteraRole = listaElemRole.iterator();
			
		    
		    System.out.println("Nombre del elementoROLEEEE");
		    while(iteraRole.hasNext()){
				Element e = (Element)iteraRole.next();
				//System.out.println("Nombre del elemento : "+e.getName());
							
				
				Iterator itNombRole = e.elementIterator("RoleName");
				while (itNombRole.hasNext()){
					Element eNombRole = (Element)itNombRole.next();
					System.out.println("Valor RespuestadelCaso: "+eNombRole.getText()+"\n");
				}
		    }
			
		}
		else 
		System.out.println("la entidad no existe");
		
		return parametrosDatos;
		
	}

}
