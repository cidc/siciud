package cidc.convenios.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convenios.db.AdminConvenioDB;
import cidc.convenios.obj.Convenio;
import cidc.convenios.obj.GetConvenioOBJ;
import cidc.convenios.obj.Parametros;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectosAntiguos.db.ProyectosAntiguosDB;
import cidc.proyectosGeneral.obj.ParametrosOBJ;
import cidc.proyectosGeneral.obj.Proyecto;

public class AdminConvenios extends ServletGeneral {
	
	

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		System.out.println("entro server");
		HttpSession sesion=req.getSession();
		String irA="/adminConvenio/NuevoConvenio.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		AdminConvenioDB adminConv= new AdminConvenioDB(cursor,usuario.getPerfil());
		Convenio conv=null;
		GetConvenioOBJ objconv=null; 
		Calendar fecha = new GregorianCalendar();
		mensaje="";
		
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		switch(accion){
			case Parametros.cmdInsertaConvenio:
				
				int año = fecha.get(Calendar.YEAR);
		        int mes = fecha.get(Calendar.MONTH);
		        int dia = fecha.get(Calendar.DAY_OF_MONTH);
				conv=(Convenio)sesion.getAttribute("nuevoConvenio");
				conv.setN_UsuDigita(usuario.getNombre());
				conv.setF_Digita(año + "-" + (mes+1) + "-" +dia+"");
				
				
				if(adminConv.nuevoConvenio(conv))
				    mensaje="Resgistro insertado correctamente";
				else
					mensaje="El resgistro no pudo ser insertado correctamente";
					
					int idc=adminConv.idconvenio(req.getParameter("nombreConvenio"));
				//	System.out.println("id del convenio:" + adminConv.idconvenio(req.getParameter("nombreConvenio")));
					adminConv.insertaObservacion(idc, req.getParameter("observaciones"),usuario.getIdUsuario());
				
				sesion.removeAttribute("nuevoConvenio");
				irA="/adminConvenio/NuevoConvenio.jsp";
			break;
			case Parametros.cmdListaConvenio:
				//Buscar Convenio
				req.setAttribute("listaConvenios", adminConv.listaConvenio());
				irA="/adminConvenio/ListaConvenios.jsp";
			break;
			case Parametros.cmdGetConvenio:
				sesion.setAttribute("datoConvenio", adminConv.buscarConvenio(Integer.parseInt(req.getParameter("idConv"))));
				req.setAttribute("accion","4");
				irA="/adminConvenio/Verconvenio.jsp";
			break;
			case Parametros.cmdUpdateConvenio:
				if(adminConv.actualizaConvenio((Convenio)sesion.getAttribute("nuevoConvenio")))
					mensaje="Resgistro actualizado correctamente";
				else
					mensaje="El resgistro no pudo ser actualizado correctamente";
			//	req.setAttribute("datoConvenio", adminConv.getConvenio(req.getParameter("idConvenio")));
				req.setAttribute("accion","4");
				irA="/adminConvenio/NuevoConvenio.jsp";
			break;
			case Parametros.InsertaObservacionConvenio:
				if (adminConv.insertaObservacion(Integer.parseInt(req.getParameter("aa")), req.getParameter("obsconvenio"),usuario.getIdUsuario()))
					 mensaje="Observación insertada correctamente";
                 else
                	mensaje="No se pudo insertar la observación";
				
				
				objconv=(GetConvenioOBJ)sesion.getAttribute("datoConvenio");
				//objconv= adminConv.buscarConvenio(Integer.parseInt(req.getParameter("aa")));
				//objconv.setListaObservaciones(adminConv.getListaObservaciones(Integer.parseInt(req.getParameter("aa"))));
				 objconv.getListaObservaciones().clear();
				objconv.setListaObservaciones(adminConv.getListaObservaciones(Integer.parseInt(objconv.getIdconvenio())));
				//sesion.setAttribute("datoConvenio",proyecto);
				 irA="/adminConvenio/Verconvenio.jsp";
			break;
			
			case Parametros.cambioEstado:
				    objconv=(GetConvenioOBJ)sesion.getAttribute("datoConvenio");
				 if (adminConv.cambiaEstado(Integer.parseInt(objconv.getIdconvenio()),req.getParameter("tipo"), req.getParameter("estado"))){
					 mensaje="Estado de convenio actualizado correctamente";
					 objconv.setEstado(req.getParameter("estado"));
					 objconv.setEstado(req.getParameter("tipo"));
			     }
               else
               	mensaje="el estado no se pudo ser actualizado";
				 //sesion.setAttribute("proyecto",proyecto);
				 irA="/adminConvenio/Verconvenio.jsp";
			break;
			
			case Parametros.consultaDocumentos:
				System.out.println("case consultadocumentos");
				irA="/adminConvenio/Documentos.jsp";
				
			break;
			
			default:
				irA="/adminConvenio/NuevoConvenio.jsp";
				 sesion.removeAttribute("nuevoConvenio");
			     if(sesion.getAttribute("ajaxProyCur")==null)
					  sesion.setAttribute("ajaxProyCur", adminConv.consultaProyectos());
			     
			     
			     break;
		}

		accion=0;
		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}

