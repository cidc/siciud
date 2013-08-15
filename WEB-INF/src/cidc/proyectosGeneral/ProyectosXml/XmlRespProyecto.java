package cidc.proyectosGeneral.ProyectosXml;

import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;

import cidc.proyectosGeneral.obj.ProyectoDatos;

public class XmlRespProyecto {
	
	String codigoProyectoCreado=null;
	
	public String respuestaCrearProyecto(String xmlProyectoCreado)throws DocumentException{
		
		SAXReader reader = new SAXReader();  
	    Document doc = reader.read(new StringReader(xmlProyectoCreado)); 
	    
	    
	    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");

	    doc.getRootElement().add(namespace1);
	    List listaAtributos = doc.selectNodes("/soap:Envelope/soap:Body/ns2:saveEntityAsStringResponse/return/Entities/Proyecto/@key");
	    
		Iterator iAtributos = listaAtributos.iterator();
		
		while (iAtributos.hasNext()){
			Attribute a =(Attribute)iAtributos.next(); 
			this.codigoProyectoCreado= a.getValue();
			System.out.println("Valor Codigo Proyecto Creado: " +codigoProyectoCreado);
		}
		
		
		return codigoProyectoCreado;
		}

	public ProyectoDatos respuestaConsultaProyecto(String xmlProyectoBuscado, ProyectoDatos datosProyecto ) throws DocumentException{
		// TODO Auto-generated method stub
		
		SAXReader reader = new SAXReader();  
	    Document doc = reader.read(new StringReader(xmlProyectoBuscado)); 
	    System.out.println(doc.asXML()+" fin");	   
	    
	    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");
	    
	    /*******************ATRIBUTOS************************/
	    doc.getRootElement().add(namespace1);

		List listaAtrProy = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/Proyecto/@key");//codigoPersona
	    
		Iterator iAtributosProy = listaAtrProy.iterator();
		while (iAtributosProy.hasNext()){
			Attribute a =(Attribute)iAtributosProy.next(); 
			//System.out.println("Valor Codigo Bizagi: " +a.getValue());
			datosProyecto.setCodigoProyectoenBizagi(a.getValue());
			
			
			/*******************ELEMENTOS************************/
			List listaElementos = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/Proyecto");
			 Iterator iteraElementos = listaElementos.iterator();
			 
			while(iteraElementos.hasNext()){
				Element e = (Element)iteraElementos.next();
				//System.out.println("Nombre del elemento a: "+e.getName());
				//System.out.println("Valor del elemento a : "+e.getText()+"\n");
				e.getName();
				
				Iterator itNombreProy = e.elementIterator("NombredelProyecto");
				while (itNombreProy.hasNext()){
					Element eNombreProy = (Element)itNombreProy.next();
					//System.out.println("Nombre del elemento b: "+eCiudad.getName());
					//System.out.println("Valor del elemento b: "+eCiudad.getText()+"\n");
					datosProyecto.setNombreProyecto(eNombreProy.getText());
				}
				
				Iterator itCodigoProyecto = e.elementIterator("CodigodelProyecto");
				while (itCodigoProyecto.hasNext()){
					Element eCodigoProy = (Element)itCodigoProyecto.next();
					//System.out.println("Valor del elemento C: "+eCodigoProy.getText()+"\n");
					datosProyecto.setCodigoProyecto(eCodigoProy.getText());
					}
				
				
				Iterator itProyCIDC = e.elementIterator("ProyectodelCIDC");
				while (itProyCIDC.hasNext()){
					Element eProyCIDC = (Element)itProyCIDC.next();
					//System.out.println("Valor del elemento S: "+eProyCIDC.getText()+"\n");
					datosProyecto.setProyectodelCIDC(eProyCIDC.getText());
					}
				
				Iterator itDirProy = e.elementIterator("DirectordeProyecto");
				while (itDirProy.hasNext()){
					Element eDirProy = (Element)itDirProy.next();
					System.out.println("Valor del elemento R: "+eDirProy.getText()+"\n");
					datosProyecto.setDirectorProyecto(eDirProy.getText());
					}
				
			}
			
		} 
	    
		
		
		return datosProyecto;
	}

}
