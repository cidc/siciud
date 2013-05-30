package cidc.adminPropuestas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.adminPropuestas.obj.PropuestaOBJ;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class ResultadosEvaluacion extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/adminPropuestas/ResultadoEvaluacion.jsp";
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
                		System.out.println("entra a consultas...");
				sesion.setAttribute("listaPropOBJ",adminPropuestaDB.getPropuestas(Integer.parseInt(req.getParameter("ano")),Integer.parseInt(req.getParameter("num")),req.getParameter("activa"),Integer.parseInt(req.getParameter("tipo"))));
				sesion.setAttribute("listaPropOBJ",adminPropuestaDB.getPropuestas(Integer.parseInt(req.getParameter("ano")),Integer.parseInt(req.getParameter("num")),req.getParameter("activa"),Integer.parseInt(req.getParameter("tipo"))));
				sesion.setAttribute("listaCalProy",adminPropuestaDB.getCalificacion(Integer.parseInt(req.getParameter("ano")),Integer.parseInt(req.getParameter("num")),req.getParameter("activa"),Integer.parseInt(req.getParameter("tipo"))));
				sesion.setAttribute("listaCalObs",adminPropuestaDB.getCalificacionObservaciones(Integer.parseInt(req.getParameter("ano")),Integer.parseInt(req.getParameter("num")),req.getParameter("activa"),Integer.parseInt(req.getParameter("tipo"))));
				sesion.setAttribute("listaEvalOBJ",adminPropuestaDB.ListaEvaluadores());
				req.setAttribute("convEstado",adminPropuestaDB.estadoConvocat(Integer.parseInt(req.getParameter("ano")),Integer.parseInt(req.getParameter("num"))));
			break;

                        case 2:
                		System.out.println("entra a Servlet");
			int tipo = Integer.parseInt(req.getParameter("tipo"));
			if(sesion.getAttribute("propuestaOBJ")!=null){
				if(adminPropuestaDB.EvaluacionPropuestas((PropuestaOBJ)sesion.getAttribute("propuestaOBJ"),tipo))
                                        mensaje="Propuestas evaluadas Correctamente";
                                } else
                                        mensaje="El registro no puede ser insertado";
                        irA="/adminPropuestas/ResultadoEvaluacion.jsp";
                        break;
		//	case 3:
                //		System.out.println("entra a Documentos");
                           //     buscar(req,resp,sesion,usuario,adminPropuestaDB);
                            // sesion.setAttribute("listaDocOBJ",adminPropuestaDB.getDocumentos(Integer.parseInt(req.getParameter("codPropuesta"))));

//				   retorno[0]="desviar";
		//		irA="/adminPropuestas/VerDocumentos.jsp";
                  //      break;
		}

		sesion.setAttribute("ano",req.getParameter("ano"));
		sesion.setAttribute("num",req.getParameter("num"));
		sesion.setAttribute("activa",req.getParameter("activa"));
		sesion.setAttribute("tipo",req.getParameter("tipo"));
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]="";
		return retorno;
	}
/*
void buscar(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, AdminPropuestaDB adminPropuestaDB)throws ServletException, IOException {
                PropuestaOBJ datDoc=adminPropuestaDB.getDocumentos(Integer.parseInt(""+req.getParameter("codPropuesta")));
                if(datDoc!=null){
                                System.out.println("entra al buscarrrr");
                        req.setAttribute("PropuestaOBJ",datDoc);
                }
        }
*/
}
