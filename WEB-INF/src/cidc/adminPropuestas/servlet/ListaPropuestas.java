package cidc.adminPropuestas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class ListaPropuestas extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/adminPropuestas/ListaPropuestas.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		AdminPropuestaDB adminPropuestaDB=new AdminPropuestaDB(cursor,usuario.getPerfil());
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		if(req.getParameter("ano")!=null && !req.getParameter("ano").equals(""))
			req.setAttribute("listaNum",adminPropuestaDB.ajaxNumConvocat(Integer.parseInt(req.getParameter("ano"))));

		req.setAttribute("listaConv",adminPropuestaDB.ajaxConv());
		switch(accion){
			case 1:
				sesion.setAttribute("listaPropOBJ",adminPropuestaDB.getPropuestasAnt(Integer.parseInt(req.getParameter("ano")),Integer.parseInt(req.getParameter("num")),req.getParameter("activa")));
				req.setAttribute("convEstado",adminPropuestaDB.estadoConvocat(Integer.parseInt(req.getParameter("ano")),Integer.parseInt(req.getParameter("num"))));
			break;
		}
		sesion.setAttribute("ano",req.getParameter("ano"));
		sesion.setAttribute("num",req.getParameter("num"));
		sesion.setAttribute("activa",req.getParameter("activa"));
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]="";
		return retorno;
	}
}
