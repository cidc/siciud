package cidc.convenios.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.displaytag.util.ParamEncoder;

import cidc.adminInformes.obj.Parametros;
import cidc.convenios.db.AdminConvenioDB;
import cidc.convenios.obj.ExtraDocConvenio;
import cidc.convenios.obj.GetConvenioOBJ;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;


public class CargaDocumento extends ServletGeneral  {
	
	Usuario usuario = null;
	CargarDocumento cargarDocumento=new CargarDocumento();
	AdminConvenioDB adminconv = null ;

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/adminConvenio/AdminConvenio.x?accion=7";
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		
		
		GetConvenioOBJ objconv=null; 
		ExtraDocConvenio docNuevo=new ExtraDocConvenio();
		if(sesion.getAttribute("datoConvenio")!=null)
		  objconv=(GetConvenioOBJ)sesion.getAttribute("datoConvenio");
       
        int accion=0;
        String nombre;
        DiskFileUpload fu = new DiskFileUpload();
		FileItem archivoAdj=null;
        cursor=new CursorDB();
        
        
        adminconv= new AdminConvenioDB(cursor,usuario.getPerfil());
        
        mensaje=""; 
      	

		if (ServletFileUpload.isMultipartContent(req)){
		
			List items=new ArrayList();
			try {
				items = fu.parseRequest(req);
		        FileItem item=null;
		        if(items.size()>0){
			        Iterator iter = items.iterator();
			        while (iter.hasNext()) {
			            item = (FileItem) iter.next();
			            if (item.isFormField()) {
			            	if(item.getFieldName().equals("accion"))
			            		accion=Integer.parseInt(item.getString());

			           // 	if(accion==1 || accion==2){			            		
			            		docNuevo.setIdconvenio(Integer.parseInt(objconv.getIdconvenio()));
				            	if(item.getFieldName().equals("fechaDoc")){
				            		docNuevo.setFechaDoc(item.getString());
				            	}
				            	if(item.getFieldName().equals("estado")){
				            		//docNuevo.setEstado(Integer.parseInt(item.getString()));
				            	}
				            	if(item.getFieldName().equals("tipo")){
				            		docNuevo.setTipo(Integer.parseInt(item.getString()));
				            	}
				            	if(item.getFieldName().equals("observaciones"))
				            		docNuevo.setObservaciones(item.getString());
			            //	}
			           // 	if(accion==3 || accion==4 || accion==5){
			            	//	docNuevo.setIdconvenio(Integer.parseInt(objconv.getIdconvenio()));
			            		if(item.getFieldName().equals("nombre"))
			            			docNuevo.setNombreDocumento(item.getString());
			            		if(item.getFieldName().equals("observaciones"))
			            			docNuevo.setObservaciones(item.getString());
			            		if(item.getFieldName().equals("fecha"))
			            			docNuevo.setFechaDoc(item.getString());
			            //	}
			            }else{
			            	archivoAdj=item;
			            }
			        }
		        }
			}catch (FileUploadException e) {
				baseDB=new BaseDB(cursor,1);
				baseDB.lanzaExcepcion(e);
			} catch (Exception e) {
				baseDB=new BaseDB(cursor,1);
				baseDB.lanzaExcepcion(e);
			}
        }else{
        	accion=Integer.parseInt(req.getParameter("accion"));
        }
		
		String path=super.context.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
		//System.out.println("--ruta completa es:--->"+path);
		String carpeta="";
		carpeta="Convenios";
	
		
	
	
		switch(accion){
		
		
		
			case Parametros.insertaInformeConvenio:
				nombre="Informe_"+objconv.getIdconvenio()+"_"; 
				if(adminconv.nuevaCargaDocConvenio(cargaDocumento(path,nombre, carpeta+"/Informes",archivoAdj,docNuevo,Parametros.insertaInformeConvenio,objconv),objconv,usuario.getIdUsuario()))
					mensaje="Documento Cargado Satisfactoriamente";
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
			break;
			
			/*	case Parametros.actualizaEstadoInformeconvenio:
				adminconv.actualizaEstadoInforme(req.getParameter("idInforme"),req.getParameter("estado"),proyecto);
			break;
			*/
			case Parametros.insertarDocumentoActaFinalizacion:
				nombre="ActaFin_"+objconv.getIdconvenio()+"_";				
				if(adminconv.nuevaCargaDocConvenio(cargaDocumento(path,nombre,carpeta+"/Actas",archivoAdj,docNuevo,Parametros.insertarDocumentoActaFinalizacionConvenio,objconv),objconv,usuario.getIdUsuario()))
					mensaje="Documento Cargado Satisfactoriamente";
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
				
				
				sesion.setAttribute("datoConvenio", adminconv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio())));
			break;
			
			case Parametros.insertarDocumentoActaCancelacion:
				nombre="ActaCancel_"+objconv.getIdconvenio()+"_";
				if(adminconv.nuevaCargaDocConvenio(cargaDocumento(path,nombre, carpeta+"/Actas",archivoAdj,docNuevo,Parametros.insertarDocumentoExternoconvenio,objconv),objconv,usuario.getIdUsuario()))
					mensaje="Documento Cargado Satisfactoriamente";
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
				    sesion.setAttribute("datoConvenio", adminconv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio())));
			break;
			
			case Parametros.insertarDocumentoExterno:
				nombre="DocAnexo__"+objconv.getIdconvenio()+"_";
			
				if(adminconv.nuevaCargaDocConvenio(cargaDocumento(path,nombre, carpeta+"/Otros",archivoAdj,docNuevo,Parametros.insertarDocumentoExternoconvenio,objconv),objconv,usuario.getIdUsuario()))
					mensaje="Documento Cargado Satisfactoriamente";
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
			break;
			case Parametros.insertarDocumentoMemorandos:
				nombre="Memorandos_"+objconv.getIdconvenio()+"_";
				if(adminconv.nuevaCargaDocConvenio(cargaDocumento(path,nombre, carpeta+"/Memorandos",archivoAdj,docNuevo,Parametros.insertarDocumentoExternoconvenio,objconv),objconv,usuario.getIdUsuario()))
					mensaje="Documento Cargado Satisfactoriamente";
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
				    sesion.setAttribute("datoConvenio", adminconv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio())));
			break;
			case Parametros.insertarDocumentoProyecto:
				nombre="Proyecto_"+objconv.getIdconvenio()+"_";
				if(adminconv.nuevaCargaDocConvenio(cargaDocumento(path,nombre, carpeta+"/Proyecto",archivoAdj,docNuevo,Parametros.insertarDocumentoExternoconvenio,objconv),objconv,usuario.getIdUsuario()))
					mensaje="Documento Cargado Satisfactoriamente";
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
				    sesion.setAttribute("datoConvenio", adminconv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio())));
			break;
			
			case Parametros.insertarDocumentoOtrosi:
				req.getParameter("nombre");
				nombre="Otrosi_"+req.getParameter("nombre")+objconv.getIdconvenio()+"_";
				if(adminconv.nuevaCargaDocConvenio(cargaDocumento(path,nombre, carpeta+"/Otrosi",archivoAdj,docNuevo,Parametros.insertarDocumentoExternoconvenio,objconv),objconv,usuario.getIdUsuario()))
					mensaje="Documento Cargado Satisfactoriamente";
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
				    sesion.setAttribute("datoConvenio", adminconv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio())));
			break;
			case Parametros.insertarContrato:
				nombre="Contrato_"+objconv.getIdconvenio()+"_";
				if(adminconv.nuevaCargaDocConvenio(cargaDocumento(path,nombre, carpeta+"/Contratos",archivoAdj,docNuevo,Parametros.insertaInformeConvenio,objconv),objconv,usuario.getIdUsuario()))
					mensaje="Documento Cargado Satisfactoriamente";
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
				    sesion.setAttribute("datoConvenio", adminconv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio())));
				break;
		}

		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
		}
	
	public ExtraDocConvenio cargaDocumento(String path, String nombre,String carpeta,FileItem archivoAdj,  ExtraDocConvenio documento, int tipo, GetConvenioOBJ getconvenioobj){ 
		cursor=new CursorDB();
		if(documento!=null){			
			 try {
				 System.out.println("entra al try");
				 System.out.println("tipo"+tipo);
				 System.out.println("path"+path);
				 System.out.println("archivoAdj"+archivoAdj);
				 System.out.println("carpeta"+carpeta);
				 System.out.println("nombre"+nombre);
				 System.out.println("admingetidnuevodoc"+adminconv.getIdNuevoDoc(tipo));
				 //cargarDocumento.cargarGenerico(path,archivoAdj,carpeta,nombre,adminconv.getIdNuevoDoc(tipo));
		
				 
				 documento.setNombreArchivo(cargarDocumento.cargarGenerico(path,archivoAdj,carpeta,nombre,adminconv.getIdNuevoDoc(tipo)));
				 mensaje="Documento Cargado Satisfactoriamente";
		     } catch (Exception e) {
				// TODO Auto-generated catch block
		    	 System.out.println("se exploto");
				baseDB=new BaseDB(cursor,1);
				baseDB.lanzaExcepcion(e);
				mensaje="No se pudo completar la carga del documento\nFavor volver a intentar";
			}
	       // req.setAttribute("archivos",inscripcionConvDB.getInfoArchivos(""+idProp));		        
		}
		return documento;
	}

}
