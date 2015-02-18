package cidc.proyectosGeneral.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.proyectos.obj.Parametros;
import cidc.proyectosGeneral.db.ProyectosGeneralDB;
import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.inscripcionConv.db.InscripcionConvDB;


public class Ajax extends ServletGeneral{

	ProyectosGeneralDB proyectosGeneralDB=null;
	InscripcionConvDB inscripcionConvDB=null;
	
	public String[] operaciones(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String irA="";
		try{
			irA="/adminProyectos/Ajax.jsp";
			HttpSession sesion = request.getSession();
			cursor=new CursorDB();
			Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
			proyectosGeneralDB=new ProyectosGeneralDB(cursor,usuario.getPerfil());
		//	int acci�n=Integer.parseInt(request.getParameter("accion"));
			int para=0;
			if(request.getParameter("para")!=null)
				para=Integer.parseInt(request.getParameter("para"));
			String []datos=null;
			switch(para){
				case Parametros.ajaxGrupos:
						datos=request.getParameterValues("dato");
						if(datos==null) break;
						sesion.setAttribute("ajaxGrupos",proyectosGeneralDB.AjaxGruposInvestigacion(Integer.parseInt(datos[0])));
						request.setAttribute("para",String.valueOf(Parametros.ajaxGrupos));
				break;
			}
		}catch(Exception e){e.printStackTrace();}
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]="";
		return retorno;
	}

}
