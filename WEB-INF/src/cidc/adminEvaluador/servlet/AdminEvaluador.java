package cidc.adminEvaluador.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminEvaluador.db.EvaluadorDB;
import cidc.adminEvaluador.obj.DatEvaluadorOBJ;
import cidc.adminEvaluador.obj.FiltroEvaluadorOBJ;
import cidc.adminEvaluador.obj.Parametros;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class AdminEvaluador extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/adminEvaluador/VerEvaluador.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		EvaluadorDB evaluadorDB=new EvaluadorDB(cursor,usuario.getPerfil());
		mensaje="";
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		System.out.println("entra al caseEval" + accion);

		retorno[0]="unir";
		switch(accion){
			case Parametros.cmdFiltraEval:
				FiltroEvaluadorOBJ filtro=(FiltroEvaluadorOBJ)sesion.getAttribute("buscaEval");
				req.setAttribute("listaEvaluadores",evaluadorDB.filtraEvaluador(filtro));
				req.setAttribute("tipEval", ""+filtro.getTipEval());
			//	sesion.removeAttribute("buscaEval");
				irA="/adminEvaluador/BuscarEvaluador.jsp";
			break;
			case Parametros.cmdVerEval:
				buscar(req,resp,sesion,usuario,evaluadorDB);
				retorno[0]="desviar";
			break;
			case Parametros.cmdEliminEval:
				if(evaluadorDB.eliminaEvaluador(req.getParameter("codEval")))
					mensaje="Evaluador Eliminado Correctamente";
				else
					mensaje="El Evaluador no pude ser eliminado\n Tiene Registros Asociados";
				irA="/adminEvaluador/BuscarEvaluador.jsp";
			break;
			case Parametros.cmdModifEval:
				if(evaluadorDB.modificaEvaluador((DatEvaluadorOBJ)sesion.getAttribute("modfEval")))
					mensaje="Evaluador Actualizado Correctamente";
				else
					mensaje="El Evaluador no pude ser actualizado";

				buscar(req,resp,sesion,usuario,evaluadorDB);
				sesion.removeAttribute("modfEval");
			break;
			case Parametros.cmdNuevoTitulo:
				if(evaluadorDB.nuevoTitulo(req.getParameter("id"),req.getParameter("tipoTitulo"),req.getParameter("titulo")))
					mensaje="Registro insertado Correctamente";
				else
					mensaje="El registro no pude ser insertado";
				buscar(req,resp,sesion,usuario,evaluadorDB);
			break;
			case Parametros.cmdBorraTitulo:
				if(evaluadorDB.borraTitulo(req.getParameter("idItem")))
					mensaje="Registro eliminado Correctamente";
				else
					mensaje="El registro no pude ser Eliminado";
				buscar(req,resp,sesion,usuario,evaluadorDB);
			break;
			case Parametros.cmdNuevaArea:
				if(evaluadorDB.nuevaArea(req.getParameter("codEval"),req.getParameter("area"),req.getParameter("campos")))
					mensaje="Registro insertado Correctamente";
				else
					mensaje="El registro no pude ser insertado";
				buscar(req,resp,sesion,usuario,evaluadorDB);
			break;
			case Parametros.cmdBorraArea:
				if(evaluadorDB.borraArea(req.getParameter("codEval"),req.getParameter("idItem")))
					mensaje="Registro eliminado Correctamente";
				else
					mensaje="El registro no pude ser eliminado";
				buscar(req,resp,sesion,usuario,evaluadorDB);
			break;
			case Parametros.cmdNuevoEval:
				if(sesion.getAttribute("modfEval")!=null){
					if(evaluadorDB.insertaEvaluador((DatEvaluadorOBJ)sesion.getAttribute("modfEval")))
						mensaje="Registro insertado Correctamente";
					else
						mensaje="El registro no pude ser insertado";
					sesion.removeAttribute("modfEval");
				}
				req.setAttribute("listaFacultades",evaluadorDB.getFacultades());
				req.setAttribute("listaSuperAreas",evaluadorDB.getSuperAreas());
				irA="/adminEvaluador/NuevoEvaluador.jsp";
			break;
			//Esta opci�n necesita que el recurso adminEaluador.x tenga como parametro el numero 13
			case Parametros.cmdDatosCombos:
				req.setAttribute("listaFacultades",evaluadorDB.getFacultades());
				req.setAttribute("listaSuperAreas",evaluadorDB.getSuperAreas());
				irA="/adminEvaluador/NuevoEvaluador.jsp";
			break;
			case Parametros.cmdEnviarClave:
				buscar(req,resp,sesion,usuario,evaluadorDB);
				if(evaluadorDB.getAsigarClave(req.getParameter("id")))
					mensaje="Clave asignada Satisfactoriamente";
				else
					mensaje="Error al asignar la clave";
				irA="/adminEvaluador/VerEvaluador.jsp";
			break;
			case Parametros.cmdCargarDocumentos:
				req.setAttribute("datos", evaluadorDB.getInfoDocs(req.getParameter("codEval")));
				irA="/adminEvaluador/CargarDocumentos.jsp";
			break;
			case Parametros.cmdPropuestasAsignadas:
				req.setAttribute("listaProp", evaluadorDB.propAsignadas(req.getParameter("codEval")));
				irA="/adminEvaluador/PropuestasAsignadas.jsp";
			break;
		}
		accion=0;
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
	public void buscar(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, EvaluadorDB evaluadorDB)throws ServletException, IOException {
//		InscripcionConvDB insc=new InscripcionConvDB(cursor,usuario.getPerfil());
		//System.out.println("---cod-->"+req.getParameter("codEval")+" tipo= "+req.getParameter("tipEval"));
		DatEvaluadorOBJ datEval=evaluadorDB.getEvaluador(req.getParameter("codEval"),Integer.parseInt(""+req.getParameter("tipEval")));
		if(datEval!=null){
			req.setAttribute("listaSuperAreas",evaluadorDB.getSuperAreas());
			req.setAttribute("datEvaluador",datEval);
		}
	}
}
