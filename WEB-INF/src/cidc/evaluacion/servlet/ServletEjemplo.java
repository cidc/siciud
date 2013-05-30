package cidc.evaluacion.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectos.obj.Parametros;
import cidc.evaluacion.db.ClaseNuevaEjm;
import cidc.evaluacion.obj.ParametrosOBJ;

public class ServletEjemplo extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/evaluacion/ListaTotales.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ClaseNuevaEjm nuevaClaseDB=new ClaseNuevaEjm(cursor,usuario.getPerfil());
		mensaje="";
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		retorno[0]="unir";
		System.out.println("entroooo "+accion+" ");
		switch(accion) {
			case ParametrosOBJ.listadoPonencias:
                                if(req.getParameter("ano")!=null)
                       //                 req.setAttribute("InfoPropuestas",nuevaClaseDB.getLista(req.getParameter("ano"), req.getParameter("corte")));
                                irA="/evaluacion/ListaTotales.jsp";
                         //       req.setAttribute("ano", req.getParameter("ano"));
                                retorno[0]="desviar";
                        break;

			case 2:
				irA="/evaluacion/PaginaConsulta.jsp";
			break;
			case 3:
				irA="/evaluacion/PaginaPrincipal.jsp";
			break;
		}
		accion=0;
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
