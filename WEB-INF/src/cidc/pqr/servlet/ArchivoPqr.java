package cidc.pqr.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

import cidc.general.db.CursorDB;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;
import cidc.pqr.ws_Bizagi_obj.PersonaDatos;

@SuppressWarnings("serial")
public class ArchivoPqr extends ServletGeneral{
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		context=config.getServletContext();
		String irA="";
		System.out.println("kjbdlkjbv");
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		HttpSession sesion=req.getSession();
		mensaje="";
		PersonaDatos personaDatos =new PersonaDatos();
		personaDatos=(PersonaDatos)sesion.getAttribute("personaDatos");
		Calendar c1= Calendar.getInstance();
		String nombre=String.valueOf(c1.get(Calendar.DATE));
		mensaje = null;
		String itemDoc = "";
		DiskFileUpload fu = new DiskFileUpload();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 1024);
		FileItem archivoAdj = null;
		if (ServletFileUpload.isMultipartContent(req)) {
			List items = new ArrayList();
			try {
				items = fu.parseRequest(req);
				FileItem item = null;
				if (items.size() > 0) {
					Iterator iter = items.iterator();
					while (iter.hasNext()) {
						item = (FileItem) iter.next();
						if (item.isFormField()) {
							if (item.getFieldName().equals("id"))
								itemDoc = item.getString();
						} else {

							archivoAdj = item;
						}
					}
					CargarDocumento cargaDoc = new CargarDocumento();
					// se almacena el documento cargado en el DD. (fisico)
					String path = req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
					String arch = cargaDoc.cargarGenerico(path, archivoAdj,"Bizagi", nombre, 0);
					System.out.println("path: "+path+" archivo: "+arch);
				}
			} catch (Exception e) {
				baseDB.lanzaExcepcion(e);
				mensaje = "El documento de movilidad no pudo ser almacenado";

			}
		}
		
		
		
		accion=0;
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
	
}
