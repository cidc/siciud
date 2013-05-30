package cidc.convocatorias.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convocatorias.db.ConvocatoriasDB;
import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.convocatorias.obj.ParametrosOBJ;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class Ejes extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		String x="";
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ConvocatoriaOBJ convocatoriaOBJ=(ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ");
		ConvocatoriasDB convocatoriasDB=new ConvocatoriasDB(cursor,usuario.getPerfil());
		int de=0;
		int accion=0;
		
		if(req.getParameter("accion")!=null){
			accion=Integer.parseInt(req.getParameter("accion"));
		}

		if(req.getParameter("irA")!=null)
			de=Integer.parseInt(req.getParameter("irA"));
		else
			de=ParametrosOBJ.Parametrizar;
		String irA="";
		irA="/Convocatoria/VerEje.jsp";
		long id=0;
		mensaje="";
		if(convocatoriaOBJ!=null)
			id=convocatoriaOBJ.getConvId();
		switch(accion){

			//*********************************GUARDAR DOCUMENTOS**************************************************************
			case ParametrosOBJ.GuardarEje:
				System.out.println("entra al guardar" +accion);
				if(sesion.getAttribute("convocatoriaOBJ")!=null){
                                if(convocatoriasDB.nuevoEje((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Registro insertado Correctamente";
                                else
                                        mensaje="El registro no puede ser insertado";
		}
			irA="/Convocatoria/NuevoEje.jsp";
			break;
			case ParametrosOBJ.NuevoEje:
			irA="/Convocatoria/NuevoEje.jsp";
                        break;
			case ParametrosOBJ.ModificarEje:
		System.out.println("entra a Compromisos" +accion);
                                buscar(req,resp,sesion,usuario,convocatoriasDB);
                                retorno[0]="desviar";
                        break;
			case 0:
				irA="/Convocatoria/AdmEjes.jsp";
			break;
		}
		retorno[0]=x;
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
void buscar(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datEje=convocatoriasDB.getEje(Integer.parseInt(""+req.getParameter("ejeId")));
                if(datEje!=null){
				System.out.println("entra al buscarrrr");
                        req.setAttribute("convocatoriaOBJ",datEje);
                }
        }
}
