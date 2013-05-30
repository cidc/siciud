package cidc.inscripcionConv.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convocatorias.db.ConvocatoriasDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class ListaConvocatoria extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
	//	ConvocatoriaOBJ convocatoriaOBJ=(ConvocatoriaOBJ)sesion.getAttribute("editConv");
		ConvocatoriasDB convocatoriasDB=new ConvocatoriasDB(cursor,usuario.getPerfil());
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		String irA="/InscripcionConv/VistaPreliminar.jsp";
	//	System.out.println("irA="+irA);
		if(usuario.getPerfil()==13)
			sesion.setAttribute("listaConvOBJ",convocatoriasDB.listaConvocatorias(2));
		else
			sesion.setAttribute("listaConvOBJ",convocatoriasDB.listaConvocatorias(1));
		switch(accion){
			case 1:
				if(req.getParameter("accion")!=null)
					sesion.setAttribute("datosConv",convocatoriasDB.getConvocatoria(Long.parseLong(req.getParameter("ver"))));
					
		System.out.println("entrooo");
					sesion.setAttribute("datosConvDoc",convocatoriasDB.getDocConvocatoria(Long.parseLong(req.getParameter("ver"))));
			break;
		}
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]="";
		return retorno;
	}
}
