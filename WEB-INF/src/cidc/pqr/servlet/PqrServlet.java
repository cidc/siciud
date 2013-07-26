package cidc.pqr.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.pqr.ws_cominicacion.InformacionHistorico;
import cidc.pqr.ws_Bizagi_obj.HistoricoDatos;


import cidc.general.db.CursorDB;
import cidc.general.servlet.ServletGeneral;
import cidc.pqr.ws_Bizagi_obj.CasoDatos;
import cidc.pqr.ws_Bizagi_obj.ParametrosDatos;
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
			personaDatos.setFacultad(pqr.getFacultad());
			personaDatos.setTipoInterno(pqr.getTipoInterno());
			personaDatos.setMedioRespuesta(String.valueOf(pqr.getMedioRespuesta()));
			casodatos.setEscaladoOtraDependencia("no");
			casodatos.setArchivoCaso((File)sesion.getAttribute("archivo"));
			casodatos = casoDB_WS.CrearCaso(casodatos, personaDatos);
			if(casodatos.getArchivoCaso()!=null)
				casodatos.getArchivoCaso().delete();
	        System.out.println(""+casodatos.getCasoId());
	        irA="/pqr/registrarPeticion.jsp";
			
        	if(casodatos.getCasoId()==null)
        		mensaje="Ha ocurrio un problema";
        	else{
        		mensaje="Caso se ha creado exitosamente con codigo "+casodatos.getCasoId();
        		sesion.setAttribute("basico", "display:none");
        		sesion.setAttribute("juridico", "display:none");
        		sesion.setAttribute("tipoSolicitante", "display:none");
        		req.setAttribute("crearCaso", "display:none");
        		sesion.removeAttribute("personaDatos");
        		sesion.removeAttribute("archivo");
        	}
			break;
		case 2://crear persona
			System.out.println("caso 2");
			personaDatos.setTitulo(pqr.getTitulo());
			personaDatos.setNombreRazonSocial(pqr.getNombre());
			personaDatos.setTipoDocumento(pqr.getTipoDoc());
			personaDatos.setDocumentoIdNit(pqr.getDocumento());
			personaDatos.setCorreoElectronico(pqr.getCorreo());
			personaDatos.setDireccion(pqr.getDireccion());
			personaDatos.setTelefonoMovil(pqr.getCelular());
			personaDatos.setCiudad(pqr.getCiudad());
			if(!pqr.getTipoInterno().equals("0"))
				personaDatos.setTipoExterno("51");
			else
				personaDatos.setTipoExterno(pqr.getTipoExterno());
			personaDatos.setTipoInterno(pqr.getTipoInterno());
			personaDatos.setRepresentante(pqr.getRepresentante());
			personaDatos.setContacto(pqr.getContacto());
			personaDatos.setCodigo(pqr.getCodigo());
			personaDatos.setFacultad(pqr.getFacultad());
			personaDatos.setProyInv(pqr.getProyInv());
			personaDatos.setTipoPersona(pqr.getTipoSolicitante());
			
			/*if(!personaDatos.getTipoInterno().equals("0")){
				personaDatos.setCiudad("2");
				personaDatos.setTipoPersona("51");
			}*/

			String respuesta=personaDB_WS.CrearPersona(personaDatos);
			personaDatos.setTipoPersona("0");
			sesion.setAttribute("personaDatos", personaDatos);
			if(respuesta==null)//debe ser ==null
				mensaje="Se ha producido un error en la creación";
			else{
				mensaje="la creacion de la persona ha sido exitosa";
				req.setAttribute("crearCaso", "display:block");
				sesion.setAttribute("basico", "display:block");
			}
		
			irA="/pqr/registrarPeticion.jsp";
			 break;
		case 3: //buscar persona
			personaDatos=personaDB_WS.buscarpersona(pqr.getDocumento());
			personaDatos.setDocumentoIdNit(pqr.getDocumento());
			//codigo pruebas
//				personaDatos.setTipoPersona("1");
//				personaDatos.setTipoInterno("3");
//				personaDatos.setTipoExterno("1");
//				personaDatos.setTipoDocumento("4");
//				personaDatos.setTitulo("2");
			//fin codigo pruebas
			sesion.setAttribute("personaDatos", personaDatos);
			System.out.println();
			if(personaDatos.getPersonaID()==null){// debe ser ==null 
				mensaje="Esta persona NO existe, por favor registre sus DATOS";
				req.setAttribute("crearCaso", "display:none");
				sesion.setAttribute("tipoSolicitante", "display:block");
				req.setAttribute("botonCrear", "display:block");
				//req.setAttribute("crearCaso", "display:none");
				//sesion.setAttribute("tipoSolicitante", "display:none");
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
			 System.out.println("CONSULTAR CASO PQR");
	         casoDB_WS = new CasoDB_WS();
	         ParametrosDatos parametrosRespuesta;
	         parametrosRespuesta = casoDB_WS.consultarCasoPQR(req.getParameter("idCaso"));
	         if(parametrosRespuesta!=null){
		         InformacionHistorico historico = new InformacionHistorico();
		         List <HistoricoDatos> historicoCaso = historico.consultarHistoricoCaso(req.getParameter("idCaso"));    
		         req.setAttribute("respuestaConsul", crearParrafo(parametrosRespuesta, historicoCaso));
	         }else
	        	 mensaje="No se ha encontrado un caso con el Id "+req.getParameter("idCaso");
			irA="/pqr/consultarPeticion.jsp";
			break;
		default:
			sesion.setAttribute("opcionales", "display:none");
			sesion.setAttribute("basico", "display:none");
			sesion.setAttribute("juridico", "display:none");
			sesion.setAttribute("tipoSolicitante", "display:none");
			req.setAttribute("crearCaso", "display:none");
			req.setAttribute("botonCrear", "display:none");
			sesion.removeAttribute("archivo");
			irA="/pqr/registrarPeticion.jsp";
			break;
		}

		accion=0;
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
	
	public String crearParrafo(ParametrosDatos datos, List<HistoricoDatos> historico){
		String parrafo="Estimado (a) "+datos.getPersonaNombreRazon()+"\n";
		parrafo+="Su solicitud radicada con el número "+datos.getCasoAsociado()+" el día "+datos.getCasoFechaApertura();
		parrafo+="está siendo atendida por "+datos.getResponsableNombre()+" y la fecha estimada de respuesta es el "+datos.getCasoFechaEstimadaCierre()+". \n";
		parrafo+="Si requiere mayor información sobre el estado de su solicitud puede comunicare al correo electrónico: "+datos.getResponsableCorreo()+". \n";
		parrafo+="Los datos registrados por Usted en la solicitud son los siguientes: \n";
		parrafo+="Nombre o Razón Social:"+datos.getPersonaNombreRazon()+". \n";
		parrafo+="Teléfono: "+datos.getPersonaTelMov()+". \n";
		parrafo+="Correo Electrónico: "+datos.getPersonaCorreo()+" \n";
		parrafo+="Si requiere actualizar la información indicada, favor envíe un correo electrónico a cidc@udistrital.edu.co indicando en el asunto " +
				"\"Actualización datos de contacto\". \nCordialmente. \nEquipo Informática - CIDC.\n\nSu caso ha seguido el siguiente tramite:\n\n";
		
		 for (int i=0; i<historico.size();i++){
			 
			 parrafo+= "Accion: "+historico.get(i).getAccionHistorico()+"\n";
			 parrafo+= "Fecha: "+historico.get(i).getFechaHistorico()+"\n";
			 parrafo+= "Usuario: "+historico.get(i).getUsuario()+"\n";
			 parrafo+= "\n";
	    	 
	    	 
	    }
		
		return parrafo;
	}

}
