package cidc.proyectos.servlet;

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

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectos.db.ProyectosInvestigadorDB;
import cidc.proyectos.obj.ProyectoGenerico;
import cidc.proyectosGeneral.db.ProyectosGeneralDB;
import cidc.proyectosGeneral.obj.ExtraDocProyecto;
import cidc.proyectosGeneral.obj.Proyecto;

public class CargarInformes extends ServletGeneral{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Usuario usuario = null;
	CargarDocumento cargarDocumento=new CargarDocumento();
	ProyectosGeneralDB proyectoGeneralDB = null;

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/grupos/proyectos/ListaProyectos.jsp";
		cursor=new CursorDB();
		int accion=0;
		HttpSession sesion=req.getSession();
		usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ProyectosInvestigadorDB proyectosDB=new ProyectosInvestigadorDB(cursor,usuario.getPerfil());
		ProyectosGeneralDB proyGeneral=new ProyectosGeneralDB(cursor, usuario.getPerfil());
		ProyectoGenerico proyecto =null;
		String path=super.context.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
		if(sesion.getAttribute("proyectoInvestigador")!=null)
			proyecto = (ProyectoGenerico)sesion.getAttribute("proyectoInvestigador");
		ExtraDocProyecto docNuevo=new ExtraDocProyecto();
        DiskFileUpload fu = new DiskFileUpload();
		FileItem archivoAdj=null;
        cursor=new CursorDB();
        proyectoGeneralDB=new ProyectosGeneralDB(cursor,usuario.getPerfil());
        
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
			            		docNuevo.setIdProyecto(proyecto.getIdProyecto());
				            	if(item.getFieldName().equals("tipo")){
				            		docNuevo.setTipo(Integer.parseInt(item.getString()));
				            	}
				            	if(item.getFieldName().equals("observaciones"))
				            		docNuevo.setObservaciones(item.getString());
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
		String carpeta="";
		if(proyecto.getTipo()==1)
			carpeta="Proyectos/Informes";
		else
			carpeta="ProyectosAntiguos/Informes";
		switch(accion){
			case cidc.proyectos.obj.Parametros.cargarInforme:
				String nombre="Informe_"+proyecto.getIdProyecto()+"_"+proyGeneral.getIdNuevoDoc(cidc.adminInformes.obj.Parametros.insertarDocumentoActaFinalizacion,proyecto.getTipo());
				CargarDocumento crg= new CargarDocumento();
				ExtraDocProyecto doc=new ExtraDocProyecto();
				if(crg.cargar(req, nombre, carpeta)){
					Proyecto proy =new Proyecto();
					proy.setClaseProyecto(proyecto.getTipo());
					proy.setId((int)proyecto.getIdProyecto());
					proyGeneral.nuevaCargaDocProyecto(doc, proy, usuario.getIdUsuario());
//					sesion.setAttribute("proyectoDocumentos", proyGeneral.getListaDocAnexos(Long.parseLong(req.getParameter("id")),Integer.parseInt(req.getParameter("tipo"))));
//					sesion.setAttribute("proyectoInvestigador", proyectosDB.getProyecto(req.getParameter("id"),req.getParameter("tipo")));
					mensaje="Documento Cargado Satisfactoriamente";
				}
				else
					mensaje="No se pudo completar la carga del documento \nFavor volver a intentar";
				irA="/grupos/proyectos/VerProyecto.jsp";
				break;
					
		}

		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
		}

}
