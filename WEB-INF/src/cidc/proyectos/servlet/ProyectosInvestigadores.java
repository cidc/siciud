package cidc.proyectos.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectos.db.ProyectosInvestigadorDB;
import cidc.proyectos.obj.BalanceGeneral;
import cidc.proyectos.obj.Parametros;
import cidc.proyectos.obj.ProyectoGenerico;
import cidc.proyectosGeneral.db.ProyectosGeneralDB;;


public class ProyectosInvestigadores extends ServletGeneral {

	       Usuario usuario = null;

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/grupos/proyectos/ListaProyectos.jsp";
		cursor=new CursorDB();
		int accion=0;
		HttpSession sesion=req.getSession();
		usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ProyectosInvestigadorDB proyectosDB=new ProyectosInvestigadorDB(cursor,usuario.getPerfil());
		ProyectosGeneralDB proyGeneral=new ProyectosGeneralDB(cursor, usuario.getPerfil());
		ProyectoGenerico proyecto =null;
		if(sesion.getAttribute("proyectoInvestigador")!=null)
			proyecto = (ProyectoGenerico)sesion.getAttribute("proyectoInvestigador");
		if(req.getParameter("accion")!=null)
			accion = Integer.parseInt(req.getParameter("accion"));

		switch(accion){
			case Parametros.cmdVerProyecto:
				sesion.setAttribute("proyectoDocumentos", proyGeneral.getListaDocAnexos(Long.parseLong(req.getParameter("id")),Integer.parseInt(req.getParameter("tipo"))));
				sesion.setAttribute("proyectoInvestigador", proyectosDB.getProyecto(req.getParameter("id"),req.getParameter("tipo")));
				irA="/grupos/proyectos/VerProyecto.jsp";
			break;
			case Parametros.cmdBalanceGral:
				sesion.setAttribute("balanceProyecto",proyectosDB.getBalanceProyecto(""+proyecto.getIdProyecto(),""+proyecto.getTipo()));
				irA="/grupos/proyectos/BalanceGeneral.jsp";
			break;
			case Parametros.cmdListaGastosRubro:
				sesion.setAttribute("idRub",req.getParameter("idRub"));
				req.setAttribute("listaGastosRubro",proyectosDB.getGastosRubrosDeLista((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
				irA="/grupos/proyectos/ListaGastos.jsp";
			break;
			default:
				req.setAttribute("listaProyectos", proyectosDB.getListaProyectos(usuario.getIdUsuario()));
			irA="/grupos/proyectos/ListaProyectos.jsp";
			break;
		}

		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
		}
}

