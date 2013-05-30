package cidc.convocatorias.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convocatorias.db.ConvocatoriasDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class ListaRequisitos extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ConvocatoriasDB convocatoriasDB=new ConvocatoriasDB(cursor,usuario.getPerfil());
		String irA="/Convocatoria/ListaRequisitosDoc.jsp";
		int ver=0;
		if(req.getParameter("ver")!=null)
			ver=Integer.parseInt(req.getParameter("ver"));
		
		sesion.setAttribute("listaReqOBJ",convocatoriasDB.listaRequisitosDoc(0));
	//	System.out.println("irA="+irA);
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]="";
		return retorno;
	}
}
