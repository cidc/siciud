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

public class CritAsp extends ServletGeneral {

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
		irA="/Convocatoria/VerCritAsp.jsp";
		long id=0;
		mensaje="";
		if(convocatoriaOBJ!=null)
			id=convocatoriaOBJ.getConvId();
		switch(accion){

			//*********************************GUARDAR DOCUMENTOS**************************************************************
			case ParametrosOBJ.GuardarCrit:
				System.out.println("entra al guardar" +accion);
				if(sesion.getAttribute("convocatoriaOBJ")!=null){
                                if(convocatoriasDB.nuevoCriterio((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Registro insertado Correctamente";
                                else
                                        mensaje="El registro no puede ser insertado";
		}
			irA="/Convocatoria/NuevoCritAsp.jsp";
			break;
			case ParametrosOBJ.NuevoCrit:
			irA="/Convocatoria/NuevoCritAsp.jsp";
                        break;
			case ParametrosOBJ.ModificarCrit:
                                buscar(req,resp,sesion,usuario,convocatoriasDB);
                                retorno[0]="desviar";
                        break;
			case ParametrosOBJ.NuevoAsp:
				System.out.println("entra a Criterios:" +Integer.parseInt(""+req.getParameter("critId")));
				int critId=Integer.parseInt(""+req.getParameter("critId"));
				irA="/Convocatoria/NuevoAsp.jsp";
                                retorno[0]="desviar";
                        break;
			case ParametrosOBJ.ModificarAsp:
                                buscarasp(req,resp,sesion,usuario,convocatoriasDB);
                                retorno[0]="desviar";
				irA="/Convocatoria/VerAsp.jsp";
                        break;

			case 0:
				irA="/Convocatoria/AdmCritAsp.jsp";
			break;
		}
		retorno[0]=x;
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
void buscar(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datCrit=convocatoriasDB.getCriterio(Integer.parseInt(""+req.getParameter("critId")));
                if(datCrit!=null){
				System.out.println("entra al buscarrrr");
                        req.setAttribute("convocatoriaOBJ",datCrit);
                }
        }
void buscarasp(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datAsp=convocatoriasDB.getAspecto(Integer.parseInt(""+req.getParameter("aspId")));
                if(datAsp!=null){
				System.out.println("entra al buscar correcto");
                        req.setAttribute("convocatoriaOBJ",datAsp);
                }
        }
}
