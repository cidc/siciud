package cidc.consecutivo.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.consecutivo.db.CorrespondenciaDB;
import cidc.consecutivo.obj.CorrespondenciaObj;
import cidc.consecutivo.otros.Operaciones;
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
			Operaciones otr= new Operaciones();
			if (!otr.checkYear(cons.consultarAño())) {
				cons.reiniciarNumeracion();
			}
			usuario = cons.consultaUsuario(usuario);
			if (cons.insertarRegistro(usuario.getNombre(), req.getParameter("destinatario"),
					req.getParameter("observaciones"))) {
				cons.aumentaConsecutivo();
				mensaje = "El consecutivo se ha registrado correctamente";
			} else
				mensaje = "Ha ocurrido un error, por favor intentelo de nuevo";
			req.setAttribute("listaConsecutivos", cons.ObtenerUltimos());
			irA = "/consecutivo/Correspondencia.jsp";
			break;
		case FILTRODEBUSQUEDA:
			CorrespondenciaObj obj =(CorrespondenciaObj) sesion.getAttribute("datosConsecutivo");
			req.setAttribute("listaFiltro", cons.consultarFiltro(obj));
			irA="/consecutivo/Buscar.jsp";
			sesion.removeAttribute("datosConsecutivo");
			mensaje="";
			break;
		default:
				req.setAttribute("listaConsecutivos", cons.ObtenerUltimos());
				irA="/consecutivo/Correspondencia.jsp";
				sesion.setAttribute("listAno", new Operaciones().Years());
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
