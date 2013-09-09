package cidc.pqr.xmlRespPersona;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.SAXReader;

import cidc.pqr.ws_Bizagi_obj.HistoricoDatos;
import cidc.pqr.ws_Bizagi_obj.ListasAtributosDatos;

public class XmlRespListasAtributos {
	
	String entidad;
		
	List<ListasAtributosDatos> listaDatosAtributos = new ArrayList<ListasAtributosDatos>();

	public List<ListasAtributosDatos> ConsultarListaAtrib(String xmlResCrearListAtrib, String entidad) throws DocumentException {
		// TODO Auto-generated method stub
		
		SAXReader reader = new SAXReader();  
	    Document doc = reader.read(new StringReader(xmlResCrearListAtrib)); 
	    
	    Namespace namespace1 = new Namespace("ns2","http://SOA.BizAgi/");
	    doc.getRootElement().add(namespace1);
	    
	    this.entidad=entidad;
	    String id = "id";
	    String titulo = null;
	    String codigo = null;
	    int posicion;
	    
	    System.out.println("ENTIDAD: "+entidad+"\n");
   
	    List listaElemeTipoPersona = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/"+entidad);
	    Iterator iteraElemeAcciones = listaElemeTipoPersona.iterator();
	   
	    List listaAtrTipoPersona = doc.selectNodes("/soap:Envelope/soap:Body/ns2:getEntitiesAsStringResponse/return/BizAgiWSResponse/Entities/"+entidad+"/@key");
	    Iterator iAtributosProy = listaAtrTipoPersona.iterator();
	    	    	
	    if(entidad=="TipodePersona")//TIPODEPERSONA (NATURAL, JURIDICA ...)	 
	    {	titulo="Titulo"; codigo="Codigo"; }
	    
	    else  if(entidad=="TipodeSolicitante")//TIPODEPERSONA (INTERNO, EXTERNO ...)	 
	    {	titulo="TipodeSolicitante"; codigo="Codigo"; }   
	    
	    else  if(entidad=="TipodeDocumento")//TIPO DOCUMENTO (CC,TI,CE.......)
	    {	titulo="TipodeDocumento"; codigo="Codigo"; }   
	    
	    else  if(entidad=="TitulosdePersona")//TITULOS DE PERSONA  (SR, SRA,SEÑORES....) 
	    {	titulo="Titulo"; codigo="Codigo"; }   
	    
	    else  if(entidad=="Ciudad")//CIUDAD  (BOGOTA, ....) 
	    {	titulo="Ciudad"; codigo="Codigo"; }  
	    
	    else  if(entidad=="TipodeSolicitanteInterno")//TIPO SOLICITANTE INTERNO (Dependencia, Docente, Estudiante)
	    {	titulo="Solicitante"; codigo=""; }  
	    
	    else  if(entidad=="TiposdeRubro")//TIPO RUBRO (Personal, Papeleria, Viajes)
	    {	titulo="TipodeRubro"; codigo="brubid"; }  
	    
	    else  if(entidad=="TipodeSolicitud")//TIPO SOLICITUD (OPS,CPS,OC,OPS-AR...)
	    {	titulo="TipodeSolicitud"; codigo="Codigo"; } 
	    
	    else  if(entidad=="TipodeRequerimiento")//TIPO DE REQUERIMIENTO (SOLICITUDES GENERALES, QUEJA, RECLAMO...)
	    {	titulo="TipodeRequerimiento"; codigo=""; } 
	    
	    else  if(entidad=="Departamento")//DEPARTAMENTO (DC, ...)
	    {	titulo="Departamento"; codigo="Codigo"; } 
	    
	    else  if(entidad=="MediodeNotificaciondeCie")//MEDIO NOTIFICACION (TELEFONO, CARTA, CORREO ...)
	    {	titulo="MediodeNotificacion"; codigo="Codigo"; } 
	    
	    else  if(entidad=="MediosdeNotificacionoRec")//MEDIO NOTIFICACION (TELEFONO, CARTA, CORREO ...)
	    {	titulo="Medio"; codigo="Codigo"; } 
	    
	    else  if(entidad=="Tiposdenumerodetelefono")//MEDIO NOTIFICACION (DOMICILIO, OFICINA, MOVIL...)
	    {	titulo="Tipodenumerotelefonico"; codigo="Codigo"; } 
	    else 
	    {	System.out.print("La entidad no se ha documentado");  entidad=null;} 	
	    
	    
	    posicion = 0;
	    	
	    	while (iAtributosProy.hasNext()&& entidad!=null)
	    	{

	    		Attribute a =(Attribute)iAtributosProy.next(); 
	    		listaDatosAtributos.add(new ListasAtributosDatos());
	    		listaDatosAtributos.get(posicion).setIdAtributo(a.getText());
	    		
				Element e = (Element)iteraElemeAcciones.next();
				e.getName();
						
				Iterator itTitulo = e.elementIterator(titulo);
				while (itTitulo.hasNext()){
					Element eTitulo = (Element)itTitulo.next();
					listaDatosAtributos.get(posicion).setNombreAtributo(eTitulo.getText());

							}
				
				Iterator itCodigo = e.elementIterator(codigo);
				while (itCodigo.hasNext()){
					Element eCodigo = (Element)itCodigo.next();
					listaDatosAtributos.get(posicion).setCodigoAtributo(eCodigo.getText());

							}
				
				posicion=posicion+1;
	    
			}

		return listaDatosAtributos;
	}

}
