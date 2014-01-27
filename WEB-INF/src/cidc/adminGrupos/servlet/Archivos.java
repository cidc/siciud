package cidc.adminGrupos.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import cidc.adminGrupos.db.AdminGruposDB;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;

public class Archivos extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/adminGrupos/Documentos.jsp";
        CargarDocumento cargarDocumento=new CargarDocumento();
        HttpSession sesion=req.getSession();
        DiskFileUpload fu = new DiskFileUpload();
		FileItem archivoAdj=null;
		String itemDoc=null;
		String idGrupo=null;
		final int actaCIDC=1;
		final int actaFac=2;
        cursor=new CursorDB();
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
				            	if(item.getFieldName().equals("id"))
				            		itemDoc=item.getString();
				            	if(item.getFieldName().equals("idGrupo"))
				            		idGrupo=item.getString();
				            }else{
				            	archivoAdj=item;
				            }
				        }
			        }
				}catch (FileUploadException e) {
					// TODO Auto-generated catch block
					baseDB=new BaseDB(cursor,1);
					baseDB.lanzaExcepcion(e);
				} catch (Exception e) {
					baseDB=new BaseDB(cursor,1);
					baseDB.lanzaExcepcion(e);
				}

			}
			
			String nombre =idGrupo;
			CargarDocumento cargaDoc = new CargarDocumento();
			String arch="";
			Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
			if(itemDoc!=null){
				String path = req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
				AdminGruposDB admin=new AdminGruposDB(cursor,usuario.getPerfil());	
				try {
					switch(Integer.parseInt(itemDoc)){
						case 1:
							 arch = cargaDoc.cargarGenerico(path, archivoAdj,"Actas_Grupos", "actCIDC-"+nombre, 0);
							 admin.actArchivo(Long.parseLong(idGrupo), arch,actaCIDC);
							 mensaje="Acta de creación del Grupo/Semillero ante el CIDC cargada Exitosamente";
						break;
						case 2:
							 arch = cargaDoc.cargarGenerico(path, archivoAdj,"Actas_Grupos", "actFac-"+nombre, 0);
							 admin.actArchivo(Long.parseLong(idGrupo), arch,actaFac);
							 mensaje="Acta de creación del Grupo/Semillero ante el Consejo de Facultad cargada exitosamente";
						break;
					}
				} catch (Exception e) {
					baseDB.lanzaExcepcion(e);
					mensaje = "El documento de movilidad no pudo ser almacenado";
				}
			}
		
        retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}

