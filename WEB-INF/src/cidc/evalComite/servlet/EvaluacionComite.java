package cidc.evalComite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.evalMovilidad.db.EvalMovilidadDB;
import cidc.evalComite.obj.Criterio;
import cidc.evalComite.obj.ParametrosOBJ;
import cidc.evalComite.obj.TrayectoriaInvest;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class EvaluacionComite extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		Criterio criterio=null;
		TrayectoriaInvest trayect=null;
		HttpSession sesion=req.getSession();
		String irA="/EvalComite/listaPonencias.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		EvalMovilidadDB EvalComiteDB=new EvalMovilidadDB(cursor,usuario.getPerfil());
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		mensaje=null;
		retorno[0]="unir";
		System.out.println("entra a el servlet adecuado "+accion+" "+req.getParameter("ano")+" "+req.getParameter("corte"));
		switch(accion){
			case ParametrosOBJ.listaEventos:
				if(req.getParameter("ano")!=null && req.getParameter("corte")!=null)
					req.setAttribute("infoEvento",EvalComiteDB.getListaEventos(req.getParameter("ano"),req.getParameter("corte")));
				irA="/EvalComite/listaEventos.jsp";
				req.setAttribute("ano", req.getParameter("ano"));
				req.setAttribute("corte", req.getParameter("corte"));
				retorno[0]="desviar";
			break;
			case ParametrosOBJ.listadoPonencias:
				if(req.getParameter("ano")!=null && req.getParameter("corte")!=null)
					req.setAttribute("listaTotales",EvalComiteDB.getTotales(req.getParameter("ano"), req.getParameter("corte")));
				irA="/EvalComite/listaTotales.jsp";
				req.setAttribute("ano", req.getParameter("ano"));
				req.setAttribute("corte", req.getParameter("corte"));
				retorno[0]="desviar";
			break;
			case ParametrosOBJ.verInfoEvento:
				System.out.println("Evento: "+req.getParameter("evento"));
				sesion.setAttribute("infoEvento",EvalComiteDB.getInfoEvento(req.getParameter("evento")));
				retorno[0]="desviar";
				req.setAttribute("evento", req.getParameter("evento"));
				irA="/EvalComite/Form3.jsp";
			break;

			case ParametrosOBJ.definir:
				if(EvalComiteDB.cambiaEstado(req.getParameter("idPon"), req.getParameter("definitiva"),req.getParameter("observacion")))
					mensaje="Asignacion registrada correctamente";
				else
					mensaje="No se pudo regitrar la asignacion";
				req.setAttribute("listaTotales",EvalComiteDB.getTotales(req.getParameter("ano"), req.getParameter("corte")));
				irA="/EvalComite/listaTotales.jsp";
				req.setAttribute("ano", req.getParameter("ano"));
				req.setAttribute("corte", req.getParameter("corte"));
				retorno[0]="desviar";
			break;

			case ParametrosOBJ.evalEvento:
				System.out.println("Eval evento************************");
				criterio=(Criterio)sesion.getAttribute("criterioValor");
				if(criterio!=null){
					System.out.println("En el if");
					if(EvalComiteDB.insertaCriterioC(criterio,req.getParameter("evento"))){						
						mensaje="Datos almacenados correctamente";
					}
				}else{
					mensaje="No se pudo guardar los datos";
				}
				irA="/EvalComite/listaEventos.jsp";				
				retorno[0]="desviar";
				sesion.removeAttribute("criterioValor");
			break;

			default:
			//	req.setAttribute("listaTotales",EvalComiteDB.getTotales(req.getParameter("ano"), req.getParameter("corte")));
				irA="/EvalComite/inicial.jsp";
				retorno[0]="desviar";
			break;
		}


		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
