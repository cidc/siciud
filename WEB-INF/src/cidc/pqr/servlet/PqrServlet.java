package cidc.pqr.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
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
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		Pqr pqr = new Pqr();
		pqr=(Pqr)sesion.getAttribute("pqr");
		PersonaDB_WS personaDB_WS = new PersonaDB_WS();
		PersonaDatos personaDatos =new PersonaDatos();
		CasoDB_WS casoDB_WS = new CasoDB_WS();
       	CasoDatos casodatos = new CasoDatos();
       	//codigo temporal
       	sesion.removeAttribute("titulo");
       	sesion.removeAttribute("tipoPersona");
       	sesion.removeAttribute("tipoDoc");
       	//fin codigo temporal
		switch (accion) {
		case 1://crear caso
			System.out.println("caso 1");
           	//System.out.println(pqr.getTipoSol());
           	//persona.getPersonaID();
			sesion.removeAttribute("personaDatos");
           	casodatos.setTipodeSolicitante(pqr.getTipoSol());
          	casodatos.setMedioDeRecepcion(String.valueOf(pqr.getRecepcion()));
          	casodatos.setTipoDeRequerimiento(pqr.getTipoSolicitud());
          	casodatos.setAsunto(pqr.getAsunto());
           	casodatos.setDescripcion(pqr.getDescripcion());
           	casodatos.setEscaladoOtraDependencia(String.valueOf(pqr.getEscalado()));
         	casodatos.setRecibirNotificacionesCorreo(String.valueOf(pqr.getNotificacionCorreo()));
        	casodatos = casoDB_WS.CrearCaso(casodatos, personaDatos);
        	System.out.println(""+casodatos.getCasoId());
        	irA="/pqr/registrarPeticion.jsp";
        	if(casodatos.getCasoId()==null)
        		mensaje="Ha ocurrio un problema";
        	else
        		mensaje="Caso creado exitosamente";
			break;
		case 2://crear persona
			System.out.println("caso 2");
			irA="/pqr/Crearpersona.jsp";
			personaDatos.setTitulo(pqr.getTitulo());
			personaDatos.setNombreRazonSocial(pqr.getNombre());
			personaDatos.setTipoPersona(pqr.getTipoPersona());
			personaDatos.setTipoDocumento(pqr.getTipoDoc());
			personaDatos.setDocumentoIdNit(pqr.getDocumento());
			personaDatos.setCorreoElectronico(pqr.getCorreo());
			personaDatos.setDireccion(pqr.getDireccion());
			personaDatos.setTelefonoMovil(pqr.getCelular());
			personaDatos.setCiudad(pqr.getCiudad());
			String respuesta=personaDB_WS.CrearPersona(personaDatos);
			if(respuesta==null)
				mensaje="Se ha producido un error en la creación";
			else
				mensaje="la creacion de la persona ha sido exitosa";
		case 3: //buscar persona
			//personaDatos=personaDB_WS.buscarpersona(pqr.getDocumento());
			personaDatos.setNombreRazonSocial("cess");
			personaDatos.setTitulo("ing");
			personaDatos.setDocumentoIdNit("1016029811");
			personaDatos.setCorreoElectronico("cess@hotmail.com");
			personaDatos.setDireccion("cra 27");
			personaDatos.setTelefonoMovil("4126514");
			personaDatos.setCiudad("bgta");
			sesion.setAttribute("personaDatos", personaDatos);
			if(personaDatos.getPersonaID()==null)
				mensaje="Esta persona no existe";
			irA="/pqr/registrarPeticion.jsp";
		default:
			break;
		}

		accion=0;
		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
	
	public void crearListas(HttpSession sesion){
		ArrayList<String> titulo =new ArrayList<String>();
		titulo.add("sr");
		titulo.add("sra");
		titulo.add("senores");
		titulo.add("dr");
		titulo.add("ing");
		sesion.setAttribute("titulo", titulo);
		ArrayList<String> persona =new ArrayList<String>();
		persona.add("Natural");
		persona.add("Jurídica");
		sesion.setAttribute("personaTipo", persona);
		ArrayList<String> cedula= new ArrayList<String>();
		cedula.add("Cédula de Cíudadania");
		cedula.add("Tarjeta de Identidad");
		cedula.add("Cédula de extranjería");
		cedula.add("Pasaporte");
		sesion.setAttribute("tipoDoc", cedula);
	}
}
