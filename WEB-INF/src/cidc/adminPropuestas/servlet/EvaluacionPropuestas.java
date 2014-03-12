package cidc.adminPropuestas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.adminPropuestas.obj.Parametros;
import cidc.adminPropuestas.obj.PropuestaOBJ;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class EvaluacionPropuestas extends ServletGeneral {

	public String[] operaciones(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		context = config.getServletContext();
		cursor = new CursorDB();
		HttpSession sesion = req.getSession();
		String irA = "/adminPropuestas/EvaluarPropuestas.jsp";
		Usuario usuario = (Usuario) sesion.getAttribute("loginUsuario");
		AdminPropuestaDB adminPropuestaDB = new AdminPropuestaDB(cursor,
				usuario.getPerfil());
		int accion = 0;
		int ano=0;
		int tipo=0;
		if (req.getParameter("accion") != null)
			accion = Integer.parseInt(req.getParameter("accion"));
		if (req.getParameter("ano") != null && !req.getParameter("ano").equals("")){
			req.setAttribute("listaNum", adminPropuestaDB.ajaxNumConvocat(Integer.parseInt(req.getParameter("ano"))));
			 ano=Integer.parseInt(req.getParameter("ano"));
		}
		req.setAttribute("listaConv", adminPropuestaDB.ajaxConv());
		switch (accion) {
		case 1:
			int num=Integer.parseInt(req.getParameter("num"));
			tipo=Integer.parseInt(req.getParameter("tipo"));
			System.out.println("entra a consultas...");
			sesion.setAttribute("listaPropOBJ",
					adminPropuestaDB.getPropuestas(ano,num,req.getParameter("activa"),tipo));
			System.out.println("entra a consultas1...");
			sesion.setAttribute("listaDocOBJ",
					adminPropuestaDB.getDocumentos(ano,num));
			System.out.println("entra a consultas2...");
			sesion.setAttribute("listaCritOBJ",
					adminPropuestaDB.ListaCriterios(ano,num));
			System.out.println("entra a consultas3...");
			sesion.setAttribute("listaAspOBJ",
					adminPropuestaDB.ListaAspectos(ano,num));
			System.out.println("entra a consultas4...");
			sesion.setAttribute("listaEvalOBJ",
					adminPropuestaDB.ListaEvaluadores());
			System.out.println("entra a consultas5...");
			sesion.setAttribute("listaCalOBJ",
					adminPropuestaDB.getCalificacionGeneral(ano,num,req.getParameter("activa"),tipo));
			System.out.println("entra a consultas6...");
			sesion.setAttribute("listaCalObs",
					adminPropuestaDB.getCalificacionObservaciones(ano,num,req.getParameter("activa"),tipo));
			System.out.println("entra a consultas7...");
			req.setAttribute("convEstado",
					adminPropuestaDB.estadoConvocat(ano,num));
			System.out.println("entra a consultas8...");
			break;

		case Parametros.EVALGUARDAR:
			System.out.println("entra a Servlet");
			tipo=Integer.parseInt(req.getParameter("tipo"));
			if (sesion.getAttribute("propuestaOBJ") != null) {
				if (adminPropuestaDB.EvaluacionPropuestas(
						(PropuestaOBJ) sesion.getAttribute("propuestaOBJ"),
						tipo))
					mensaje = "Propuestas evaluadas Correctamente";
			} else
				mensaje = "El registro no puede ser insertado";
			irA = "/adminPropuestas/EvaluarPropuestas.jsp";
			break;
		case Parametros.EVALACTUALIZAR:
			System.out.println("entra a actualizar");
			int tipo1 = Integer.parseInt(req.getParameter("tipo"));
			if (sesion.getAttribute("propuestaOBJ") != null) {
				if (adminPropuestaDB.ActualizaEvaluacionPropuestas((PropuestaOBJ) sesion.getAttribute("propuestaOBJ"),tipo1))
					mensaje = "Propuestas actualizadas Correctamente";
			} else
				mensaje = "El registro no puede ser actualizado";
			irA = "/adminPropuestas/EvaluarPropuestas.jsp";
			break;
		}

		sesion.setAttribute("ano", req.getParameter("ano"));
		sesion.setAttribute("num", req.getParameter("num"));
		sesion.setAttribute("activa", req.getParameter("activa"));
		sesion.setAttribute("tipo", req.getParameter("tipo"));
		retorno[0] = "unir";
		retorno[1] = irA;
		// retorno[2]=mensaje;
		return retorno; 
	}
	/*
	 * void buscar(HttpServletRequest req, HttpServletResponse resp, HttpSession
	 * sesion, Usuario usuario, AdminPropuestaDB adminPropuestaDB)throws
	 * ServletException, IOException { PropuestaOBJ
	 * datDoc=adminPropuestaDB.getDocumentos
	 * (Integer.parseInt(""+req.getParameter("codPropuesta")));
	 * if(datDoc!=null){ System.out.println("entra al buscarrrr");
	 * req.setAttribute("PropuestaOBJ",datDoc); } }
	 */
}
