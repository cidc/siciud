package cidc.evalMovilidad.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.evalMovilidad.db.EvalMovilidadDB;
import cidc.evalComite.obj.Criterio;
import cidc.evalMovilidad.obj.ParametrosOBJ;
import cidc.evalMovilidad.obj.TrayectoriaInvest;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class EvalMovilidad extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		Criterio criterio=null;
		TrayectoriaInvest trayect=null;
		HttpSession sesion=req.getSession();
		String irA="/evalMovilidad/listaPonencias.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		EvalMovilidadDB evalMovilidadDB=new EvalMovilidadDB(cursor,usuario.getPerfil());
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		mensaje=null;
		retorno[0]="unir";
		switch(accion){
			//Presenta los datos y la agenda de cooperación del investigador	
			case ParametrosOBJ.verAgenda:
				//INFOA contiene la información general del investigador
				sesion.setAttribute("InfoA",evalMovilidadDB.getTrayectoria(req.getParameter("idPon")));
				//INFOB contiene la agenda de cooperación cargada por el investigador
				sesion.setAttribute("InfoB",evalMovilidadDB.getInfoB(req.getParameter("idPon")));
				if(!usuario.isPerfil("48"))
					irA="/evalMovilidad/Form1.jsp";
				else
					irA="/evalMovilidad/Form1Aux.jsp";
				retorno[0]="desviar";
			break;
			//Evaluación de una propuesta en particular. Agenda de Cooperación
			case ParametrosOBJ.evalAgenda:
				//El perfil 48 es evaluador
				if(!usuario.isPerfil("48")){
					//criterioValor es el objeto que almacena los datos ingresados por el evaluador
					criterio=(Criterio)sesion.getAttribute("criterioValor");
					//InfiA es la información del investigador
					trayect=(TrayectoriaInvest)sesion.getAttribute("InfoA");
					//si se han ingresado los criterios de evalución, primera parte, es decir, Agenda de Cooperación
					if(criterio!=null){
						if(evalMovilidadDB.insertaCriterioB(criterio,trayect.getPapel(),usuario.getIdUsuario(),trayect.getIdPropuesta())){
							irA="/evalMovilidad/Form2.jsp";
							mensaje="Datos almacenados correctamente";
						}
					}else{
						irA="/evalMovilidad/Form2.jsp";
						mensaje="No se pudo guardar los datos del primer criterio";
					}
				}else{
					irA="/evalMovilidad/Form2Aux.jsp";
				}				
				retorno[0]="desviar";
			break;
			//Evaluación de una propuesta en particular. Trayectoria del investigador.
			case ParametrosOBJ.evalTrayectoria:
				if(!usuario.isPerfil("48")){
					criterio=(Criterio)sesion.getAttribute("criterioValor");
					trayect=(TrayectoriaInvest)sesion.getAttribute("InfoA");
					if(criterio!=null){
						if(evalMovilidadDB.insertaCriterioA(criterio,trayect.getPapel(),usuario.getIdUsuario(),trayect.getIdPropuesta())){
						//	sesion.setAttribute("InfoB",evalMovilidadDB.getInfoB(trayect.getIdPropuesta()));
							irA="/evalMovilidad/listaPonencias.jsp";
							mensaje="Datos almacenados correctamente";
						}
					}else{
						irA="/evalMovilidad/Form2.jsp";
						mensaje="No se pudo guardar los datos del primer criterio";
					}
				}else{
					irA="/evalMovilidad/listaPonencias.jsp";
				}
				retorno[0]="desviar";
			break;

	/*		case ParametrosOBJ.evalEvento:
				criterio=(Criterio)sesion.getAttribute("criterioValor");
				trayect=(TrayectoriaInvest)sesion.getAttribute("InfoA");
				if(criterio!=null){
					if(evalMovilidadDB.insertaCriterioC(criterio,trayect.getPapel(),usuario.getIdUsuario(),trayect.getIdPropuesta())){
						req.setAttribute("listaPonencias",evalMovilidadDB.getPropuestas());
						mensaje="Datos almacenados correctamente";
					}
				}else{
					irA="/evalMovilidad/Form1.jsp";
					mensaje="No se pudo guardar los datos del primer criterio";
				}
				retorno[0]="desviar";
			break;*/
		/*	case ParametrosOBJ.listaValores:
			//	req.setAttribute("listaTotales",evalMovilidadDB.getTotales());
				irA="/evalMovilidad/listaTotales.jsp";
				retorno[0]="desviar";
			break;*/
			// Desde este if se seleccionan las ponencias inscritas según el año y el corte de la misma
			default:
				if(req.getParameter("ano")!=null && req.getParameter("corte")!=null && req.getParameter("rol")!=null)
					req.setAttribute("listaPonencias",evalMovilidadDB.getPropuestas(req.getParameter("ano"),req.getParameter("corte"),usuario.getIdUsuario(), req.getParameter("rol")));
				req.setAttribute("ano", req.getParameter("ano"));
				req.setAttribute("corte", req.getParameter("corte"));
				req.setAttribute("rol", req.getParameter("rol"));
				retorno[0]="desviar";
			break;
		}

	//	System.out.println("ira="+irA);
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
