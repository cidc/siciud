package cidc.convMovilidad.servlet;

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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import cidc.convMovilidad.db.MovilidadDB;
import cidc.convMovilidad.obj.InfoGeneral;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;

public class ArchivosMovilidadServlet extends ServletGeneral {
	//prepara el servlet para capturar los archivos
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/convMovilidad/Cargar.jsp";
		cursor=new CursorDB();
        CargarDocumento cargarDocumento=new CargarDocumento();
        HttpSession sesion=req.getSession();
        Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
        InfoGeneral infoGeneral =(InfoGeneral)sesion.getAttribute("movilidad");
        mensaje=null;
		String itemDoc="";
		//cursor es un objeto para establecer la conexion con la BD
		MovilidadDB movilidadDB=new MovilidadDB(cursor,usuario.getPerfil());
		DiskFileUpload fu = new DiskFileUpload();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024*1024);
		FileItem archivoAdj=null;
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
			            	if(item.getFieldName().equals("id"))
			            		itemDoc=item.getString();
			            }else{
			            	
			            	archivoAdj=item;
			            }
			        }
				System.out.println("archivoAdj:"+archivoAdj);
			        String path=req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
			        CargarDocumento cargaDoc=new CargarDocumento();
			        //se almacena el documento cargado en el DD. (fisico)
			        String arch=cargaDoc.cargarGenerico(path, archivoAdj, "Movilidad", infoGeneral.getIdPropuesta()+"Mov"+itemDoc+"_", infoGeneral.getIdPropuesta());
			       if(movilidadDB.actualizaArchivo(infoGeneral,arch , itemDoc)){
			        	mensaje="Documento almacenado correctamente";
			        	sesion.removeAttribute("movilidad");
			        	sesion.setAttribute("movilidad", movilidadDB.consultaIndividual(""+infoGeneral.getIdPropuesta()));
			       }
			       else
			    	   mensaje="El documento no pudo ser almacenado";
		        }
			} catch (FileUploadException e) {
				// TODO Auto-generated catch block
				baseDB=new BaseDB(cursor,1);
				baseDB.lanzaExcepcion(e);
				mensaje="El documento de movilidad no pudo ser almacenado";
			} catch (Exception e) {
				baseDB=new BaseDB(cursor,1);
				baseDB.lanzaExcepcion(e);
				mensaje="El documento de movilidad no pudo ser almacenado";
			}
		}
        retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}

