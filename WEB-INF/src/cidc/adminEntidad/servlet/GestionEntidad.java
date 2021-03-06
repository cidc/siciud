package cidc.adminEntidad.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminEntidad.db.EntidadDB;
import cidc.adminEntidad.obj.EntidadOBJ;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.adminEntidad.obj.ParametrosOBJ;


/**
 * Servlet Class
 *
 * @web.servlet              name="GestionEntidad"
 *                           display-name="Name for GestionEntidad"
 *                           description="Description for GestionEntidad
 * @web.servlet-mapping      url-pattern="/GestionEntidad"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */



public class GestionEntidad  extends ServletGeneral{

	
	   Usuario usuario = null;
	  
		public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
			HttpSession sesion = req.getSession();
			
			context=config.getServletContext();
			cursor = new CursorDB();
			EntidadOBJ objEntidad =null;
			String irA = "/index.html";
			mensaje="";
			// Usuario user = (Usuario) sesion.getAttribute("loginUsuario");
			EntidadDB entidadDB = new EntidadDB(cursor, 2);
			if(sesion.getAttribute("entidades")!=null)
				objEntidad = (EntidadOBJ)sesion.getAttribute("entidades");

			usuario = (Usuario) sesion.getAttribute("loginUsuario");

			
			int val = 0;
			
			if (req.getParameter("validar")!= null){
				val = Integer.parseInt(req.getParameter("validar"));
				}
			

		   switch (val)
			{
			 case ParametrosOBJ.InsertarEntidad:
				     irA = "/adminConvenio/Entidad/Entidad.jsp";
				     objEntidad.setUsudigita(usuario.getNombre());
				     
				     if (entidadDB.insertarEntidad(objEntidad))
				    	 mensaje="La información de la entidad se ha almacenado correctamente";
                     else
                    	mensaje="Problemas al almacenar la información de la Entidad";
				       sesion.setAttribute("entidades", entidadDB.consultarEntidad(entidadDB.getObjEntidad().getId()));
				       
                 break;
			 case ParametrosOBJ.ActualizarEntidad:
			     irA="/adminConvenio/Entidad/Entidad.jsp";
			     objEntidad.setUsudigita(usuario.getNombre());
			     if (entidadDB.actualizarEntidad(objEntidad))
			    	 mensaje="La información de la entidad se ha actualizado correctamente";
                 else
                	 mensaje="Problemas al actualizar la información de la entidad";
			     objEntidad = entidadDB.consultarEntidad(objEntidad.getId());
			     sesion.removeAttribute("entidades");
                 sesion.setAttribute("entidades", objEntidad);
               break;
			 case ParametrosOBJ.ListaEntidad:
					//Buscar Entidad
				
				 int por=0;
				 
				 
				 if (req.getParameter("por")!= null){
						 por = Integer.parseInt(req.getParameter("por"));
						}
				 
				 req.removeAttribute("listaEntidades");
				 req.setAttribute("listaEntidades", entidadDB.listaEntidad());
				 switch (por) {
				case ParametrosOBJ.ListaEntidadPara:
					irA="/adminConvenio/Entidad/ListaEntidades.jsp";
					break;
				case 	ParametrosOBJ.ListaEntidadConvenio:
					irA="/adminConvenio/EntidadConvenio.jsp";
					
					break;
				case ParametrosOBJ.ListaEntidadConvenioInsertar:
					irA="/adminConvenio/EntidadConvenio.jsp";
					mensaje=(String)req.getAttribute("mens");
					req.removeAttribute("mens");
					break;
				default:
					irA="/adminConvenio/Entidad/ListaEntidades.jsp";
					break;
				}
				
				break;
			 case ParametrosOBJ.VerEntidad:
				 	sesion.removeAttribute("entidades");
					sesion.setAttribute("entidades", entidadDB.consultarEntidad(Integer.parseInt(req.getParameter("idEnt"))));
					req.setAttribute("accion","4");
					irA="/adminConvenio/Entidad/Entidad.jsp";
				break;
	
    	             default:
    	            	 irA = "/adminConvenio/Entidad/NuevaEntidad.jsp";
    	            	 mensaje="ERROR";
    	            	 break;
    	     }			
		   
			val=0;
			retorno[0]="desviar";
			retorno[1]=irA;
			retorno[2]=mensaje;
			return retorno;         
	
		}
}
