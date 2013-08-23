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

import cidc.pqr.ws_Bizagi_obj.PersonaDatos;





public class XmlRespPersona  {

	private String codigoPersonaCreada = null;
	PersonaDatos persona = new PersonaDatos();
	
public String CrearPersona(String xmlCrearPersona)throws DocumentException {
	
	SAXReader reader = new SAXReader();  
    Document doc = reader.read(new StringReader(xmlCrearPersona)); 
    //System.out.println(doc.asXML()+" fin");	  //Codigo XML recibido 
      
    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

    doc.getRootElement().add(namespace1);
    List listaAtributos = doc.selectNodes("/soap:Envelope/soap:Body/ns2:saveEntityAsStringResponse/return/Entities/PERSONA/@key");
    
	Iterator iAtributos = listaAtributos.iterator();
	
	while (iAtributos.hasNext()){
		Attribute a =(Attribute)iAtributos.next(); 
		this.codigoPersonaCreada = a.getValue();
		//System.out.println("Valor Atributo: " +codigoPersonaCreada);
	}
	
	return getCodigoPersonaCreada();
}




public PersonaDatos ConsultarDatosPersona(String DocumentoID_NIT) throws DocumentException{
	
	SAXReader reader = new SAXReader();  
    Document doc = reader.read(new StringReader(DocumentoID_NIT)); 
    System.out.println(doc.asXML()+" fin");	   
 
    
    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");
    
    /*******************ATRIBUTOS************************/
    doc.getRootElement().add(namespace1);
	List listaAtrPer = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/PERSONA/@key");//codigoPersona
	List listaAtrCiudad = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/PERSONA/Ciudad/@key");//codigoCiudad
	List listaAtrTipoPer = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/PERSONA/TipodePersona/@key");//Codigo TipodePersona
	List listaAtrTitutlo = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/PERSONA/Titulo/@key");//Codigo Titulo
	List listaAtrTipoDoc = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/PERSONA/TipodeDocumento/@key");//Codigo Titulo
	
  	Iterator iAtributosPer = listaAtrPer.iterator();
	while (iAtributosPer.hasNext()){
		Attribute a =(Attribute)iAtributosPer.next(); 
		persona.setPersonaID(a.getValue());
	} 
	
	
 	Iterator iAtributosCiud = listaAtrCiudad.iterator();
	while (iAtributosCiud.hasNext()){
		Attribute a =(Attribute)iAtributosCiud.next(); 
		persona.setCiudad(a.getValue());
	}
	
	
 	Iterator iAtributosTipoPer = listaAtrTipoPer.iterator();
	while (iAtributosTipoPer.hasNext()){
		Attribute a =(Attribute)iAtributosTipoPer.next(); 
		persona.setTipoPersona(a.getValue());
	}
	
 	Iterator iAtriTitulo = listaAtrTitutlo.iterator();
	while (iAtriTitulo.hasNext()){
		Attribute a =(Attribute)iAtriTitulo.next(); 
		persona.setTitulo(a.getValue());
	}
	
 	Iterator iAtriTipoDoc = listaAtrTipoDoc.iterator();
	while (iAtriTipoDoc.hasNext()){
		Attribute a =(Attribute)iAtriTipoDoc.next(); 
		persona.setTipoDocumento(a.getValue());	
	}
	
	/***************ELEMENTOS***********************/
	
	List listaElementos = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/PERSONA");
    Iterator iteraElementos = listaElementos.iterator();
	
	while(iteraElementos.hasNext()){
		Element e = (Element)iteraElementos.next();
		//System.out.println("Nombre del elemento : "+e.getName());
		//System.out.println("Valor del elemento : "+e.getText()+"\n");
		e.getName();
		
		Iterator itCidudadIn = e.elementIterator("Ciudad");
		while (itCidudadIn.hasNext()){
			Element eCiudad = (Element)itCidudadIn.next();
			//System.out.println("Nombre del elemento: "+eCiudad.getName());
			//System.out.println("Valor del elemento: "+eCiudad.getText()+"\n");
			
			Iterator itCiudad = eCiudad.elementIterator("Ciudad");
			while (itCiudad.hasNext()){
				Element eCodCiudad = (Element)itCiudad.next();
				//System.out.println("Nombre del elemento: "+eCodCiudad.getName());
				//System.out.println("Valor del elemento: "+eCodCiudad.getText()+"\n");
			}
			
			Iterator itCodCiudad = eCiudad.elementIterator("Codigo");
			while (itCodCiudad.hasNext()){
				Element eCodCiudad = (Element)itCodCiudad.next();
				//System.out.println("Nombre del elemento: "+eCodCiudad.getName());
				//System.out.println("Valor del elemento: "+eCodCiudad.getText()+"\n");
			}
		}
		
		Iterator itCorreo = e.elementIterator("CorreoElectronico");
		while (itCorreo.hasNext()){
			Element eCorreo = (Element)itCorreo.next();
			persona.setCorreoElectronico(eCorreo.getText());
		}
		
		Iterator itDireccion = e.elementIterator("Direccion");
		while (itDireccion.hasNext()){
			Element eDireccion = (Element)itDireccion.next();
			persona.setDireccion(eDireccion.getText());
		}
		
		Iterator itDocIdentidad = e.elementIterator("DocumentodeIdentidadNIT");
		while (itDocIdentidad.hasNext()){
			Element eDocIdent = (Element)itDocIdentidad.next();
			persona.setDocumentoIdNit(eDocIdent.getText());
		}
		
		
		Iterator itNombreRazonSocial = e.elementIterator("NombreRazonSocial");
		while (itNombreRazonSocial.hasNext()){
			Element eNombreRazonSocial = (Element)itNombreRazonSocial.next();
			persona.setNombreRazonSocial(eNombreRazonSocial.getText());
		}
		
		
		
		Iterator itTelefonoMovil = e.elementIterator("TelefonoMovil");
		while (itTelefonoMovil.hasNext()){
			Element eTelefonoMovil = (Element)itTelefonoMovil.next();
			persona.setTelefonoMovil(eTelefonoMovil.getText());
		}
		
		
		Iterator itTipoPersona = e.elementIterator("TipodePersona");
		while (itTipoPersona.hasNext()){
			Element eTipoPersona = (Element)itTipoPersona.next();
			//System.out.println("Nombre del elemento: "+eTipoPersona.getName());
			//System.out.println("Valor del elemento: "+eTipoPersona.getText()+"\n");
			
			Iterator itTituloTipoPersona = eTipoPersona.elementIterator("Titulo");
			while (itTituloTipoPersona.hasNext()){
				Element eTituloTipoPersona = (Element)itTituloTipoPersona.next();
				//System.out.println("Nombre del elemento: "+eTituloTipoPersona.getName());
				//System.out.println("Valor del elemento: "+eTituloTipoPersona.getText()+"\n");
			}
			
			Iterator itCodigoTipoPersona = eTipoPersona.elementIterator("Codigo");
			while (itCodigoTipoPersona.hasNext()){
				Element eCodigoTipoPersona = (Element)itCodigoTipoPersona.next();
				//System.out.println("Nombre del elemento: "+eCodigoTipoPersona.getName());
				//System.out.println("Valor del elemento: "+eCodigoTipoPersona.getText()+"\n");
			}				
		}
		
		
		Iterator itTiposPersona = e.elementIterator("Titulo");
		while (itTiposPersona.hasNext()){
			Element eTiposPersona = (Element)itTiposPersona.next();
			//System.out.println("Nombre del elemento: "+eTiposPersona.getName());
			//System.out.println("Valor del elemento: "+eTiposPersona.getText()+"\n");
			
			Iterator itTituloTiposPersona = eTiposPersona.elementIterator("Titulo");
			while (itTituloTiposPersona.hasNext()){
				Element eTituloTipoPersona = (Element)itTituloTiposPersona.next();
				//System.out.println("Nombre del elemento: "+eTituloTipoPersona.getName());
				//System.out.println("Valor del elemento: "+eTituloTipoPersona.getText()+"\n");
			}
			
			Iterator itCodigoTiposPersona = eTiposPersona.elementIterator("Codigo");
			while (itCodigoTiposPersona.hasNext()){
				Element eCodigoTipoPersona = (Element)itCodigoTiposPersona.next();
				//System.out.println("Nombre del elemento: "+eCodigoTipoPersona.getName());
				//System.out.println("Valor del elemento: "+eCodigoTipoPersona.getText()+"\n");
			}
			
			
		}
		
		
		Iterator itTipoDocumento = e.elementIterator("TipodeDocumento");
		while (itTipoDocumento.hasNext()){
			Element eTipoDocumento = (Element)itTipoDocumento.next();
			//System.out.println("Nombre del elemento: "+eTipoDocumento.getName());
			//System.out.println("Valor del elemento: "+eTipoDocumento.getText()+"\n");
			
			Iterator itTituloTipoDocumento = eTipoDocumento.elementIterator("TipodeDocumento");
			while (itTituloTipoDocumento.hasNext()){
				Element eTituloTipoDocumento = (Element)itTituloTipoDocumento.next();
				//System.out.println("Nombre del elemento: "+eTituloTipoDocumento.getName());
				//System.out.println("Valor del elemento: "+eTituloTipoDocumento.getText()+"\n");
			}
			
			Iterator itCodigoTipoDocumento = eTipoDocumento.elementIterator("Codigo");
			while (itCodigoTipoDocumento.hasNext()){
				Element eCodigoTipoDocumento = (Element)itCodigoTipoDocumento.next();
				//System.out.println("Nombre del elemento: "+eCodigoTipoDocumento.getName());
				//System.out.println("Valor del elemento: "+eCodigoTipoDocumento.getText()+"\n");
			}
			
			
		}
		
		
		
	}
	return persona;
		
	
}

public String getCodigoPersonaCreada() {
	return codigoPersonaCreada;
}

public void setCodigoPersonaCreada(String codigoPersonaCreada) {
	this.codigoPersonaCreada = codigoPersonaCreada;
}	 
	
}
