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
		case 1:
			System.out.println("caso 1");
			break;
		case 2:
			System.out.println("caso 2");
		default:
			break;
		}
		return null;
	}
}
