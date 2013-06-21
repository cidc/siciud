package cidc.pqr.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cidc.convMovilidad.db.MovilidadDB;
import cidc.convMovilidad.obj.InfoGeneral;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;
import cidc.pqr.ws_Bizagi_obj.CasoDatos;
import cidc.pqr.ws_Bizagi_obj.PersonaDatos;
import cidc.pqr.ws_Bizagi_obj.Pqr;
import cidc.pqr.ws_cominicacion.CasoDB_WS;
import cidc.pqr.ws_cominicacion.PersonaDB_WS;

public class PqrServlet extends ServletGeneral{

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		System.out.println("Ingreso al Servlet =========>");
		context=config.getServletContext();
		cursor=new CursorDB();
		String irA="";
		System.out.println("kjbdlkjbv");
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		HttpSession sesion=req.getSession();
		Pqr pqr = new Pqr();
		pqr=(Pqr)sesion.getAttribute("pqr");
		PersonaDB_WS personaDB_WS = new PersonaDB_WS();
		PersonaDatos personaDatos =new PersonaDatos();
		CasoDB_WS casoDB_WS = new CasoDB_WS();
       	CasoDatos casodatos = new CasoDatos();
       	mensaje="";
		switch (accion) {
		case 1://crear caso
			System.out.println("caso 1");
           	//System.out.println(pqr.getTipoSol());
           	personaDatos.getPersonaID();
			personaDatos=(PersonaDatos)sesion.getAttribute("personaDatos");
           	casodatos.setTipodeSolicitante(pqr.getTipoSolicitante());
          	casodatos.setMedioDeRecepcion(String.valueOf(pqr.getRecepcion()));
          	casodatos.setTipoDeRequerimiento(pqr.getTipoSolicitud());
          	casodatos.setAsunto(pqr.getAsunto());
           	casodatos.setDescripcion(pqr.getDescripcion());
           	casodatos.setEscaladoOtraDependencia(String.valueOf(pqr.getEscalado()));
         	casodatos.setRecibirNotificacionesCorreo(String.valueOf(pqr.getNotificacionCorreo()));
         	casodatos.setArchivoCaso(pqr.getArchivoAdjunto());
         	personaDatos.setProyInv(pqr.getProyInv());
			personaDatos.setCodigo(pqr.getCodigo());
			personaDatos.setFaculta(pqr.getFaculta());
			personaDatos.setTipoInterno(pqr.getTipoInterno());
			casodatos.setArchivoCaso((File)sesion.getAttribute("archivo"));
			casodatos = casoDB_WS.CrearCaso(casodatos, personaDatos);
			casodatos.getArchivoCaso().delete();
	        System.out.println(""+casodatos.getCasoId());
	        irA="/pqr/registrarPeticion.jsp";
			
        	if(casodatos.getCasoId()==null)
        		mensaje="Ha ocurrio un problema";
        	else
        		mensaje="Caso se ha creado exitosamente con codigo "+casodatos.getCasoId();
        	sesion.setAttribute("basico", "display:none");
        	sesion.setAttribute("juridico", "display:none");
			sesion.setAttribute("tipoSolicitante", "display:none");
			sesion.setAttribute("crearCaso", "display:none");
			sesion.removeAttribute("personaDatos");
			break;
		case 2://crear persona
			System.out.println("caso 2");
			irA="/pqr/CrearPersona.jsp";
			personaDatos.setTitulo(pqr.getTitulo());
			personaDatos.setNombreRazonSocial(pqr.getNombre());
			personaDatos.setTipoDocumento(pqr.getTipoDoc());
			personaDatos.setDocumentoIdNit(pqr.getDocumento());
			personaDatos.setCorreoElectronico(pqr.getCorreo());
			personaDatos.setDireccion(pqr.getDireccion());
			personaDatos.setTelefonoMovil(pqr.getCelular());
			personaDatos.setCiudad(pqr.getCiudad());
			personaDatos.setTipoExterno(pqr.getTipoExterno());
			personaDatos.setRepresentante(pqr.getRepresentante());
			personaDatos.setContacto(pqr.getContacto());
			
			
			String respuesta=personaDB_WS.CrearPersona(personaDatos);
			if(respuesta==null)
				mensaje="Se ha producido un error en la creación";
			else{
				mensaje="la creacion de la persona ha sido exitosa";
				req.setAttribute("crearCaso", "display:none");
			}
			 break;
		case 3: //buscar persona
			personaDatos=personaDB_WS.buscarpersona(pqr.getDocumento());
			//codigo pruebas
				personaDatos.setTipoPersona("1");
				personaDatos.setTipoInterno("3");
				personaDatos.setTipoExterno("1");
				personaDatos.setTipoDocumento("4");
				personaDatos.setTitulo("2");
			//fin codigo pruebas
			sesion.setAttribute("personaDatos", personaDatos);
			System.out.println();
			if(personaDatos.getPersonaID()!=null){// debe ser ==null 
				mensaje="Esta persona no existe";
				req.setAttribute("crearCaso", "display:none");
				sesion.setAttribute("tipoSolicitante", "display:none");
			}else{
				sesion.setAttribute("crearCaso", "display:block");
				sesion.setAttribute("basico", "display:block");
				sesion.setAttribute("tipoSolicitante", "display:block");
				if (personaDatos.getTipoInterno()==null || personaDatos.getTipoInterno().equals("0")) {
					sesion.setAttribute("opcionales", "display:block");
					sesion.setAttribute("juridico", "display:none");
				} else {
					sesion.setAttribute("opcionales", "display:none");
					sesion.setAttribute("juridico", "display:block");
				}
			}
			irA="/pqr/registrarPeticion.jsp";
			break;
		case 4: //consultar caso
			casodatos.setCasoId(pqr.getIdCaso());
			//casodatos= casoDB_WS.consulta(casodatos);
			sesion.setAttribute("respuestaConsul", casodatos);
			irA="/pqr/consultarPeticion.jsp";
			break;
		default:
			sesion.setAttribute("opcionales", "display:none");
			sesion.setAttribute("basico", "display:none");
			sesion.setAttribute("juridico", "display:none");
			sesion.setAttribute("tipoSolicitante", "display:none");
			req.setAttribute("crearCaso", "display:none");
			irA="/pqr/registrarPeticion.jsp";
			break;
		}

		accion=0;
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
	
	public File cargar(HttpServletRequest req, String nombre, String carpeta)
			throws Exception {
		boolean retorno = true;

		HttpSession sesion = req.getSession();
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
			} catch (FileUploadException e) {
				baseDB.lanzaExcepcion(e);
				mensaje = "El documento de movilidad no pudo ser almacenado";

			}
		}
		return null;
	}
}
