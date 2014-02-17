package cidc.convenios.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convenios.db.AdminConvenioDB;
import cidc.convenios.obj.Parametros;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;



public class Ajax extends ServletGeneral {
	public String[] operaciones(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String irA="";
		String mensaje="";
		System.out.println("ajaxc.java");
		
		try{
			irA="/adminConvenio/Ajax.jsp";
			HttpSession sesion = request.getSession();
			cursor=new CursorDB();
			//ProyectosAntiguosDB proyAntiguosDB = new ProyectosAntiguosDB(cursor, 2);
			Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
			AdminConvenioDB adminConv= new AdminConvenioDB(cursor,usuario.getPerfil());
			int para=0;
			if(request.getParameter("para")!=null)
				para=Integer.parseInt(request.getParameter("para"));
			String []datos=null;
			System.out.println("para"+para);
			System.out.println("dato de mierda:"+request.getParameter("dato"));
			//System.out.println("para"+datos.toString());
			if(Integer.parseInt(request.getParameter("remove"))==1){
			sesion.removeAttribute("ajaxGrupos");
			sesion.removeAttribute("ajaxFacultad");
			sesion.removeAttribute("ajaxInvest");
			sesion.removeAttribute("ajaxProyCur");}
			switch(para){
				case Parametros.AjaxGrupos:
					 datos=request.getParameterValues("dato");
					 if(datos==null) break;
					 irA="/adminConvenio/Ajax.jsp";
                     //sesion.setAttribute("ajaxGrupos",proyAntiguosDB.consultarGrupos(Integer.parseInt(datos[0])));
                     sesion.setAttribute("ajaxGrupos",adminConv.consultarGrupos(Integer.parseInt(datos[0])));
					 request.setAttribute("para",String.valueOf(Parametros.AjaxGrupos));
                     break;
				case Parametros.AjaxInvestigadores:
					 datos=request.getParameterValues("dato");
					 if(datos==null) break;
					 irA="/adminConvenio/Ajax.jsp";
					 sesion.setAttribute("ajaxInvest",adminConv.consultarInvestigadores(Integer.parseInt(datos[0])));
					 request.setAttribute("para",String.valueOf(Parametros.AjaxInvestigadores));
			         break;
			}
		}catch(Exception e){e.printStackTrace();}

		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}

}
