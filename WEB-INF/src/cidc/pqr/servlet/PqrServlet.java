package cidc.pqr.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.logger.Log;
import cidc.planAccion.db.PlanAccionDB;
import cidc.planAccion.obj.Actividades;
import cidc.planAccion.obj.Parametros;
import cidc.planAccion.obj.PlanAccionDatos;
import cidc.pqr.obj.Pqr;
import cidc.pqr.ws_Bizagi_obj.CasoDatos;
import cidc.pqr.ws_cominicacion.CasoDB_WS;

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
		
		switch (accion) {
		case 1://crear caso
			System.out.println("caso 1");

			CasoDB_WS casoDB_WS = new CasoDB_WS();
           	CasoDatos casodatos = new CasoDatos();
           	System.out.println(pqr.getTipoSol());
           	
          
           	casodatos.setTipodeSolicitante(pqr.getTipoSol());
          	casodatos.setMedioDeRecepcion("1");
          	casodatos.setTipoDeRequerimiento("1");
          	casodatos.setAsunto(pqr.getAsunto());
           	casodatos.setDescripcion(pqr.getDescripcion());
          // 	casodatos.setEscaladoOtraDependencia("false");//enviar a otra dependencia
         // 	casodatos.setFechaOriginalSolicitud("2013-05-30T");// enviar a otra dependencia
         //  	casodatos.setRecibirNotificacionesCorreo("true");//recibir notificaciones por correo
           	
          // 	casodatos.setTipoDeRequerimiento(pqr.getTipoSolicitud());
          // 	casodatos.setAsunto(pqr.getAsunto());
           //	casodatos.setDescripcion(pqr.getDescripcion());
         	//casodatos = casoDB_WS.CrearCaso(casodatos);   
           	
           //	System.out.println(pqr.getTipoSol());
           	
        	casodatos = casoDB_WS.CrearCaso(casodatos);
        	
        	
        	
			
			break;
		case 2://crear persona
			System.out.println("caso 2");
		default:
			break;
		}
		return null;
	}
}
