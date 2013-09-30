package cidc.consecutivo.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.consecutivo.db.CorrespondenciaDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class Correspondencia extends ServletGeneral{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int INGRESARCONSECUTIVO=1;
	private static final int FILTRODEBUSQUEDA=2;
	
	public String[] operaciones(HttpServletRequest req, HttpServletResponse resp){
		int accion=0;
		String irA="";
		HttpSession sesion=req.getSession();
		cursor=new CursorDB();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		CorrespondenciaDB cons= new CorrespondenciaDB(cursor, usuario.getPerfil());
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		switch(accion){
		case INGRESARCONSECUTIVO:
			usuario=cons.consultaUsuario(usuario);
			if(cons.insertarRegistro(usuario.getNombre() , req.getParameter("destinatario"), req.getParameter("observaciones"))){
				mensaje="insercion exitosa";
			}
			else
				mensaje="ha ocurrido un error";
			irA="/consecutivo/Correspondencia.jsp";
			req.setAttribute("listaConsecutivos", cons.ObtenerUltimos());
			break;
		case FILTRODEBUSQUEDA:
			req.setAttribute("listaFiltro", cons.consultarFiltro(req.getParameter("cod"), req.getParameter("remitente"), req.getParameter("destinatario"), req.getParameter("observaciones")));
			irA="/consecutivo/Buscar.jsp";
			break;
		default:
				req.setAttribute("listaConsecutivos", cons.ObtenerUltimos());
				irA="/consecutivo/Correspondencia.jsp";
				mensaje="";
		}
		accion=0;
		req.removeAttribute("accion");
		retorno[0]="";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}

}
