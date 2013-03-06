package cidc.proyectos.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.proyectos.obj.Documento;
import cidc.proyectos.obj.Parametros;
import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.inscripcionConv.db.InscripcionConvDB;


public class Ajax extends ServletGeneral{


	public String[] operaciones(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		InscripcionConvDB inscripcionConvDB=null;
		AdminPropuestaDB adminPropuestaDB=null;
		String irA="";
		try{
			irA="/adminProyectos/Ajax.jsp";
			HttpSession sesion = request.getSession();
			cursor=new CursorDB();
			Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
			inscripcionConvDB=new InscripcionConvDB(cursor,usuario.getPerfil());
		//	int acci�n=Integer.parseInt(request.getParameter("accion"));
			int para=0;
			if(request.getParameter("para")!=null)
				para=Integer.parseInt(request.getParameter("para"));
			String []datos=null;
			System.out.println("entro");
			switch(para){
				case Parametros.ajaxGrupos:
						datos=request.getParameterValues("dato");
						if(datos==null) break;
				//		System.out.println("entra"+para);
						sesion.setAttribute("ajaxGrupos",inscripcionConvDB.AjaxGruposInvestigacion(Integer.parseInt(datos[0]),Integer.parseInt(datos[1])));
						
						request.setAttribute("para",String.valueOf(Parametros.ajaxGrupos));
				break;
		/*		case Parametros.ajaxNumeros:
					datos=request.getParameterValues("dato");
					adminPropuestaDB=new AdminPropuestaDB(cursor,usuario.getPerfil());
					request.setAttribute("ajaxNumeros",adminPropuestaDB.ajaxNumConvocat(Integer.parseInt(datos[0])));
					request.setAttribute("para",""+Parametros.ajaxNumeros);
				break;*/
			}
		}catch(Exception e){e.printStackTrace();}
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]="";
		return retorno;
	}

}
