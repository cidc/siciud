package cidc.inscripcionConv.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import cidc.convocatorias.db.ConvocatoriasDB;
//import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.convMovilidad.db.MovilidadDB;
import cidc.inscripcionConv.obj.InscripcionConvOBJ;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import cidc.general.db.BaseDB;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


public class CargaRequisitosProy extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/InscripcionConv/Cargar.jsp";
        	cursor=new CursorDB();
        	CargarDocumento cargarDocumento=new CargarDocumento();
        	HttpSession sesion=req.getSession();
        	Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		InscripcionConvOBJ inscripcionConvOBJ =(InscripcionConvOBJ)sesion.getAttribute("inscripcionConvOBJ");
		mensaje=null;
		String itemDoc="";
		String itemConv="";
		String itemProp="";
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
                                        if(item.getFieldName().equals("DocId"))
                                                itemDoc=item.getString();
                                        if(item.getFieldName().equals("propConvId"))
                                                itemConv=item.getString();
				        if(item.getFieldName().equals("idPropuesta"))
                                                itemProp=item.getString();
                                    }else{

                                        archivoAdj=item;
                                    }
                                }

		int Doc=Integer.parseInt(itemDoc);
		int Conv=Integer.parseInt(itemConv);
		int Prop=Integer.parseInt(itemProp);		

		System.out.println("en:"+Doc+Conv+Prop);
		String path=req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
                CargarDocumento cargaDoc=new CargarDocumento();
                //se almacena el documento cargado en el DD. (fisico)
                String arch=cargaDoc.cargarGenerico(path, archivoAdj, "Movilidad", inscripcionConvOBJ.getPropId()+"Mov"+itemDoc+"_", inscripcionConvOBJ.getPropId()); 
		System.out.println("archivoAdj:"+itemDoc);
	        if (movilidadDB.setRequisitos(Prop,arch,Doc,Conv)){
		mensaje="Documento almacenado correctamente";
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
        	retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]="";
		return retorno;
	}
}
