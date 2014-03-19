package cidc.adminPropuestas.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.adminPropuestas.obj.Parametros;
import cidc.convMovilidad.db.MovilidadDB;
import cidc.evalPropuesta.db.EvalPropuestaDB;
import cidc.general.asigPares.db.AsignacionParesDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class EstadoPropuesta extends ServletGeneral {

	public static final int TIPOPROYECTO=1;
	
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/adminPropuestas/EstadoPropuesta.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		AdminPropuestaDB adminPropuestaDB=new AdminPropuestaDB(cursor,usuario.getPerfil());
		EvalPropuestaDB evalPropuestaDB=new EvalPropuestaDB(cursor,usuario.getPerfil());
		AsignacionParesDB asignacion=new AsignacionParesDB(cursor,usuario.getPerfil());
		MovilidadDB movilidadDB=new MovilidadDB(cursor,usuario.getPerfil());
		int accion=0;
		mensaje="";
		retorno[0]="unir";
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		switch(accion){
			case Parametros.cmdRespuesta :
				if(sesion.getAttribute("prop")!=null && req.getParameter("resp")!=null && req.getParameter("codEval")!=null && sesion.getAttribute("tipEval")!=null)
					if(adminPropuestaDB.asignaRespuesta(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(req.getParameter("resp")),req.getParameter("codEval"),Integer.parseInt(""+sesion.getAttribute("tipEval"))))
						mensaje="Registro Modificado Correctamente";
					else
						mensaje="El registro NO se pudo actualizar";
				sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))));
			break;
			case Parametros.cmdCancelar:
				if(sesion.getAttribute("prop")!=null && req.getParameter("resp")!=null ){
					if(adminPropuestaDB.cancelaEval(Long.parseLong(""+sesion.getAttribute("prop")),req.getParameter("codEval"),Integer.parseInt(""+sesion.getAttribute("tipEval"))))
						mensaje="Cancelación registrada Correctamente";
					else
						mensaje="La Cancelación no se pudo registrar";
				}
				sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))));
			break;
	/*		case Parametros.cmdMailAsignacion:
				if(req.getParameter("prop")!=null && req.getParameter("codEval")!=null )
					if(adminPropuestaDB.enviarMailAsign(Long.parseLong(req.getParameter("prop")),req.getParameter("codEval")))
						mensaje="e-mail Enviado Correctamente";
					else
						mensaje="El e-mail mo se pudo enviar";
			break;*/
			case Parametros.cmdMailLoginPsw:
			/*	System.out.println(""+sesion.getAttribute("prop"));
				System.out.println(""+req.getParameter("codEval"));
				System.out.println(""+sesion.getAttribute("tipEval"));*/
				if(sesion.getAttribute("prop")!=null && req.getParameter("codEval")!=null && sesion.getAttribute("tipEval")!=null){
		/*			if(adminPropuestaDB.crearLoginPsw(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))))
						mensaje="El e-mail fue enviado Correctamente";
					else
						mensaje="El e-mail mo se pudo enviar";*/
				}
				sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))));
			break;
			case Parametros.cmdAsignaEval:
		//		req.setAttribute("tipEval",req.getParameter("tipEval"));
				req.setAttribute("prop",req.getParameter("prop"));
				irA="/adminPropuestas/AsignaEvaluador.jsp";
				retorno[0]="desviar";
				if(req.getAttribute("devolver")!=null){
					sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))));
					irA="/adminPropuestas/EstadoPropuesta.jsp";
					mensaje=""+req.getAttribute("devolver");
				}
			break;
			case Parametros.cmdVerEvaluacion:
		//		System.out.println("eval="+req.getParameter("tipoEval"));
				req.setAttribute("resultEvaluacion",evalPropuestaDB.getResultEval(req.getParameter("tipoEval"),req.getParameter("idProp")));
				irA="/EvalPropuestas/ResultEvaluacion.jsp";
			break;
			case Parametros.cmdVerInscripcion:
				req.setAttribute("resumen",adminPropuestaDB.getResumenInscripcion(req.getParameter("idProp")));
				req.setAttribute("docs", movilidadDB.buscarDocumentosInscritos(Integer.parseInt(req.getParameter("idProp")),adminPropuestaDB.buscarConvocatoria(Integer.parseInt(req.getParameter("ano")), Integer.parseInt(req.getParameter("num")))));
				irA="/adminPropuestas/resumenInscripcion.jsp";
			break;
			case Parametros.cmdEntregaDocs:
				if(sesion.getAttribute("prop")!=null && req.getParameter("codEval")!=null && sesion.getAttribute("tipEval")!=null){
					if(adminPropuestaDB.entregaDocs(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))))
						mensaje="Registro modificado Correctamente";
					else
						mensaje="El registro no pudo ser modificado";
				}
				sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))));
			break;
			case Parametros.cmdVerResParcial:
				if(req.getParameter("prop")!=null && !req.getParameter("prop").equals("")){
					req.setAttribute("parcial", adminPropuestaDB.resParcial(""+req.getParameter("prop")));
				}
				irA="/adminPropuestas/ResParcial.jsp";
			//	sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))));
			break;
			case Parametros.cmdIngresaResParcial:
		//		System.out.println("inserción");
				if(req.getParameter("prop")!=null && !req.getParameter("prop").equals("")){
					if(adminPropuestaDB.insertaRespParcial(req.getParameter("prop"), req.getParameter("radicado"), req.getParameter("corte"), req.getParameter("observaciones")))
						mensaje="Registro Insertado Correctamente";
					else
						mensaje="El registro no pudo ser insertado";
					req.setAttribute("parcial", adminPropuestaDB.resParcial(""+req.getParameter("prop")));
				}
				irA="/adminPropuestas/ResParcial.jsp";
			//	sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))));
			break;
			case Parametros.cmdModifResParcial:
		//		System.out.println("modificación");
				if(req.getParameter("idResultado")!=null && !req.getParameter("idResultado").equals("")){
					if(adminPropuestaDB.modificaRespParcial(req.getParameter("idResultado"),req.getParameter("radicado"),req.getParameter("corte"),req.getParameter("observaciones")))
						mensaje="Registro Modificado Correctamente";
					else
						mensaje="El registro no pudo ser modificado";
					req.setAttribute("parcial", adminPropuestaDB.resParcial(""+req.getParameter("prop")));
				}

				irA="/adminPropuestas/ResParcial.jsp";
			break;
			case Parametros.cmdactivacion:
				if(req.getParameter("prop")!=null && req.getParameter("activa")!=null){
					if(adminPropuestaDB.activaPropuesta(Long.parseLong(req.getParameter("prop")),Integer.parseInt(req.getParameter("activa"))))
						mensaje=adminPropuestaDB.getMensaje();
				}
				irA="/adminPropuestas/ListaPropuestas.jsp";
				req.setAttribute("listaConv",adminPropuestaDB.ajaxConv());
				sesion.setAttribute("listaPropOBJ",adminPropuestaDB.getPropuestas(Integer.parseInt((String)sesion.getAttribute("ano")),Integer.parseInt((String)sesion.getAttribute("num")),(String)sesion.getAttribute("activa"),TIPOPROYECTO));
			//	sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(""+sesion.getAttribute("prop")),Integer.parseInt(""+sesion.getAttribute("tipEval"))));
			break;
			default:
				if(req.getParameter("prop")!=null && !req.getParameter("prop").equals("")&& req.getParameter("tipEval")!=null && !req.getParameter("tipEval").equals("")){
					sesion.setAttribute("prop",req.getParameter("prop"));
					sesion.setAttribute("datosPropuesta",adminPropuestaDB.estEvalProp(Long.parseLong(req.getParameter("prop")),Integer.parseInt(req.getParameter("tipEval"))));
					sesion.setAttribute("tipEval",req.getParameter("tipEval"));
					sesion.removeAttribute("datosArticulo");
				}else{
			//		System.out.println("pailas con alguno "+req.getParameter("prop")+" - "+req.getParameter("tipEval"));
				}
			break;
		}
		accion=0;
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
