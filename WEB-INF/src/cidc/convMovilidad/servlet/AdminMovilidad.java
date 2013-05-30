package cidc.convMovilidad.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convMovilidad.db.MovilidadDB;
import cidc.convMovilidad.obj.InfoGeneral;
import cidc.convMovilidad.obj.Parametros;
import cidc.convMovilidad.obj.Requisitos;
import cidc.general.db.CursorDB;
import cidc.general.db.UsuarioDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.inscripSistema.obj.Persona;

public class AdminMovilidad extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/convMovilidad/Insercion.jsp";
		Persona persona=(Persona)sesion.getAttribute("persona");
	//	UsuarioDB usuarioDB=new UsuarioDB(cursor,2);

		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		MovilidadDB movilidadDB=new MovilidadDB(cursor,2);
		InfoGeneral info=null;
		mensaje=null;
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));

		System.out.println("Valor de accion en el servlet AdminMovilidad------>"+ accion);
		retorno[0]="unir";
		

		switch(accion){
			case Parametros.insercion:
				info=(InfoGeneral)sesion.getAttribute("movilidad");
				info.setIdPersona(persona.getIdPersona());
				info.setGrupo(persona.getIdGrupo());
				if(info.getIdPropuesta()==0){
					if(movilidadDB.insertaPropuesta(info)){
						mensaje="Registro insertado correctamente, favor continuar la inscripción con la digitación de la agenda de cooperación, la cual es requisito indispensable para la aprobación de su inscripción";
						//actualizo
						sesion.setAttribute("movilidad", movilidadDB.consultaIndividual(movilidadDB.getConsec()));						
						irA="/convMovilidad/Insercion.jsp";
					}
					else{
						mensaje="No pudo ser registrada la propuesta\nFavor volver a intentar";
						req.setAttribute("listaMovilidad", movilidadDB.consultaLista(persona.getIdPersona()));
						irA="/convMovilidad/listaPropuestas.jsp";
					}					
				}else{
					if(movilidadDB.actualizarPropuesta(info)){
						mensaje="Registro actualizado correctamente.";
						//actualizo						
						irA="/convMovilidad/Insercion.jsp";
					}
					else{
						mensaje="No pudo ser actualziar la propuesta\nFavor volver a intentar";
						irA="/convMovilidad/Insercion.jsp";
					}
				}
				persona.setEstado(true);
				sesion.setAttribute("persona",persona);				
			break;
			case Parametros.agenda:
				//	info.setIdPersona(usuario.getIdUsuario());
				Requisitos requis=(Requisitos)sesion.getAttribute("requisitos");
				info =(InfoGeneral)sesion.getAttribute("movilidad");
				requis.setIdProp(info.getIdPropuesta());
				if(movilidadDB.insertaAgenda(requis)){
					mensaje="Registro insertado correctamente, favor completar la inscripción con la carga de los documentos que son requisito indispensable para la aprobación de su inscripción";
					sesion.setAttribute("InfoB",movilidadDB.getInfoB(""+info.getIdPropuesta()));
					//sesion.setAttribute("idPropuesta", movilidadDB.getConsec());
					irA="/convMovilidad/agenda.jsp";
				}
				else{
					mensaje="No pudo ser registrada la propuesta\nFavor volver a intentar";
					req.setAttribute("listaMovilidad", movilidadDB.consultaLista(persona.getIdPersona()));
					irA="/convMovilidad/listaPropuestas.jsp";
				}
				persona.setEstado(true);
				sesion.setAttribute("persona",persona);
			break;
			case Parametros.consultaLista:
				System.out.println("Consulta las propuestas incritas hasta el momento------>");
				if(persona!=null){
					System.out.println("Dentro del if para concultarLista------>");
					req.setAttribute("listaMovilidad", movilidadDB.consultaLista(persona.getIdPersona()));
					irA="/convMovilidad/listaPropuestas.jsp";
					sesion.setAttribute("persona",persona);
				}
				else{
					mensaje="Usuario no valido";
					irA="/InscripcionConv/VistaPreliminar.jsp";
				}
				System.out.println("papel "+persona.getPapel());
				sesion.removeAttribute("movilidad");
			break;
		/*	case Parametros.consultaIndividual:
				sesion.removeAttribute("movilidad");
				sesion.removeAttribute("requisitos");
				sesion.setAttribute("movilidad", movilidadDB.consultaIndividual(req.getParameter("id")));
				if(req.getParameter("estado").equals("1")){
					irA="/convMovilidad/agenda.jsp";
					sesion.setAttribute("idPropuesta", movilidadDB.consultaIndividual(req.getParameter("id")));
					sesion.setAttribute("estado", req.getParameter("estado"));
				}
				else{
					irA="/convMovilidad/Cargar.jsp";
					sesion.setAttribute("idPropuesta", movilidadDB.consultaIndividual(req.getParameter("id")));
					sesion.setAttribute("estado", req.getParameter("estado"));
				}
			break;*/
			case Parametros.enviaMail:
				//System.out.println("Inicia envio de mail soporte movilidad para ="+req.getParameter("id"));
				if(movilidadDB.enviarMail(req.getParameter("id"),persona))
					mensaje="Mensaje enviado satisfactoriamente";
				else
					mensaje="El mensaje no pudo ser enviado";
				////System.out.println("Termina proceso de envio de mail movilidad");
				req.setAttribute("listaMovilidad", movilidadDB.consultaLista(persona.getIdPersona()));
				////System.out.println("Termina consulta de propuestas por persona");
				//req.setAttribute("habilitado", movilidadDB.grupoHabilitado(persona));
				req.setAttribute("habilitadoMsm", movilidadDB.getMensaje());
				irA="/convMovilidad/listaPropuestas.jsp";
			break;
			case Parametros.ActializarPaso1:
				//EN ESTE CASE SE CONSULTA LA INFORMACIÓN GENERAL DE LA PROPUESTA DE MOVILIDAD
				System.out.println("Paso 01");
				info=(InfoGeneral)sesion.getAttribute("movilidad");
				if(req.getParameter("id")!=null)
					sesion.setAttribute("movilidad", movilidadDB.consultaIndividual(req.getParameter("id")));
				
				irA="/convMovilidad/Insercion.jsp";
			break;
			case Parametros.ActializarPaso2:
				//EN ESTE CASE EL SISTEMA CONSULTA LA INFORMACIÓN RELACIONADA CON LA AGENDA DE COPERACIÓN  
				System.out.println("Paso 02");
				info=(InfoGeneral)sesion.getAttribute("movilidad");
				//sesion.setAttribute("movilidad", movilidadDB.consultaIndividual(req.getParameter("id")));
				sesion.setAttribute("InfoB",movilidadDB.getInfoB(""+info.getIdPropuesta()));
				irA="/convMovilidad/agenda.jsp";
				
			break;
			case Parametros.ActializarPaso3:
		//		int conv = Integer.parseInt(req.getParameter("conv"));
			//	System.out.println("Conv:"+conv);
				int conv=0;
				if(req.getParameter("propConvId")!=null)
				conv=Integer.parseInt(req.getParameter("propConvId"));
				sesion.setAttribute("listaDocOBJ",movilidadDB.getDocumentos(conv));
				irA="/convMovilidad/Cargar.jsp";
				System.out.println("Paso 03");
			break;
			default:
				//System.out.println("entra al default de admin movilidad");
			break;
			
		}
		accion=0;
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
