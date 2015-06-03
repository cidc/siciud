package cidc.general.asigPares.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminArticulos.db.AdminArticuloDB;
import cidc.adminArticulos.obj.ArticRevista;
import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.adminPropuestas.obj.FiltroEvaluadorOBJ;
import cidc.adminPropuestas.obj.Parametros;
import cidc.general.asigPares.db.AsignacionParesDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class AsignaEvaluador extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		AsignacionParesDB asignacion=new AsignacionParesDB(cursor,usuario.getPerfil());
		mensaje="";
		int accion=0;
		int producto=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		if(req.getParameter("producto")!=null)
			producto=Integer.parseInt(req.getParameter("producto"));
		req.setAttribute("prop",req.getParameter("prop"));
		retorno[0]="unir";
		switch(producto){
			case 1:
				switch(accion){
					case Parametros.cmdFiltraEval:
						FiltroEvaluadorOBJ filtro=(FiltroEvaluadorOBJ)sesion.getAttribute("filtroEval");
						filtro.setTipEval(Integer.parseInt(""+sesion.getAttribute("tipEval")));
						req.setAttribute("listaEvalOpcion",asignacion.filtro(filtro));
						sesion.removeAttribute("filtroEval");
						irA="/adminPropuestas/AsignaEvaluador.jsp";
					break;
					case Parametros.cmdAsignaEval:
					/*	System.out.println("pro= "+sesion.getAttribute("idProp"));
						System.out.println("tipeval= "+sesion.getAttribute("tipEval"));*/
						if(asignacion.asignaEvaluadorPropuesta(""+sesion.getAttribute("prop"),req.getParameter("codEval"),""+sesion.getAttribute("tipEval"))){
							retorno[0]="desviar";
							req.setAttribute("devolver","Evaluador Asignado Correctamente");
						}else
							mensaje=asignacion.getMensaje();
						irA="/adminPropuestas/EstadoPropuesta.x";
					break;
					case Parametros.cmdVerEval:
						req.setAttribute("datEvaluador",asignacion.datosEvaluador(req.getParameter("codEval"), Integer.parseInt(req.getParameter("tipEval"))));
						irA="/adminPropuestas/VerEvaluador.jsp";
					break;
					default:
						irA="/adminPropuestas/EstadoPropuesta.x";
					break;
				}
			break;
			default:
				switch(accion){
					case Parametros.cmdFiltraEval:
						FiltroEvaluadorOBJ filtro=(FiltroEvaluadorOBJ)sesion.getAttribute("filEvalArtic");
						filtro.setTipEval(2);
						req.setAttribute("listaEvalOpcion",asignacion.filtro(filtro));
						sesion.removeAttribute("filEvalArtic");
						irA="/adminArticulos/AsignaEvaluador.jsp";
					break;
					case Parametros.cmdAsignaEval:
						ArticRevista art=(ArticRevista)sesion.getAttribute("datosArticulo");
						if(asignacion.asignaEvaluadorArticulo(art.getIdArticulo(),req.getParameter("codEval"))){
							retorno[0]="desviar";
							irA="/adminArticulos/AdminArticulosRevista.x?accion=4&art="+art.getIdArticulo();
							req.setAttribute("devolver","Evaluador Asignado Correctamente");
						}else{
							mensaje=asignacion.getMensaje();
							req.setAttribute("devolver","Evaluador Asignado Correctamente");
							irA="/adminArticulos/AsignaEvaluador.jsp";
						}
					break;
					case Parametros.cmdVerEval:
						req.setAttribute("datEvaluador",asignacion.datosEvaluador(req.getParameter("codEval"), Integer.parseInt(req.getParameter("tipEval"))));
						irA="/adminArticulos/VerEvaluador.jsp";
					break;
					default:
			//			System.out.println("entra a esta cosa rara.. que depronto quito..");
						irA="/adminArticulos/EstadoArticulo.x";
					break;
				}
			break;
		}

		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
