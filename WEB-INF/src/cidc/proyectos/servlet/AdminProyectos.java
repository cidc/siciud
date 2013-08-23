package cidc.proyectos.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminInformes.db.AdminInformesDB;
import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectos.db.ProyectosDB;
import cidc.proyectos.obj.BalanceGeneral;
import cidc.proyectos.obj.Devolutivo;
import cidc.proyectos.obj.FiltroProyecto;
import cidc.proyectos.obj.GastosRubro;
import cidc.proyectos.obj.Parametros;
import cidc.proyectos.obj.Proyecto;

public class AdminProyectos extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/adminProyectos/ListaProyectos.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ProyectosDB proyectosDB=new ProyectosDB(cursor,usuario.getPerfil());
		AdminPropuestaDB adminPropuestaDB=new AdminPropuestaDB(cursor,usuario.getPerfil());
		mensaje="";
		Proyecto proyecto=(Proyecto)sesion.getAttribute("proyecto");
		int accion=0;
		long id=0;
		System.out.println("entra al busca "+req.getAttribute("accion"));
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		if(req.getAttribute("accion")!=null && accion==0)
				accion=Parametros.cmdContratoPdf;
		retorno[0]="unir";
		switch(accion){
			case Parametros.cmdVerListaProyectos:
				req.setAttribute("listaConv",adminPropuestaDB.ajaxConv());
				req.setAttribute("listaProyectos",proyectosDB.getListaProyectos((FiltroProyecto) sesion.getAttribute("filtroProyecto")));
				sesion.removeAttribute("filtroProyecto");
			break;
			case Parametros.cmdVerProyectos:
				sesion.setAttribute("proyecto",proyectosDB.getVerProyecto(req.getParameter("id")));
				irA="/adminProyectos/VerProyecto.jsp";
			break;
			case Parametros.cmdActaInicio:
			/*	String sitio=req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf("\\"));
			     // String sitio="/usr/local/jboss/server/default/deploy/siciud.war";
		        sitio=sitio+"\\Documentos\\Proyectos\\";
			     //   sitio=sitio+"/Documentos/Proyectos/";
				if(proyectosDB.crearActaInicio(resp,req.getParameter("id"),sitio))
					mensaje="Acta de Inicio creada Satisfactoriamente";
				else
					mensaje="Acta de Inicio creada Satisfactoriamente";*/
				retorno[0]="unir";
				req.setAttribute("caso",""+Parametros.cmdActaInicio);
				irA="/GestionProyectos/documentosServlet.x";
				//irA="/adminProyectos/VerProyecto.jsp";
			break;
			case Parametros.cmdContrato:
				retorno[0]="unir";
				irA="/adminProyectos/Contrato.jsp";
			break;
			case Parametros.cmdContratoPdf:
				req.setAttribute("caso",""+Parametros.cmdContrato);
				retorno[0]="unir";

				irA="/GestionProyectos/documentosServlet.x";
			break;
			case Parametros.cmdBalanceGral:
		//		System.out.println("entra al balance");
				sesion.setAttribute("balanceProyecto",proyectosDB.getBalanceProyecto(req.getParameter("id")));
				irA="/adminProyectos/BalanceGeneral.jsp";
			break;
			case Parametros.cmdListaGastosRubro:
				sesion.setAttribute("idRub",req.getParameter("idRub"));
				req.setAttribute("listaGastosRubro",proyectosDB.getGastosRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
				sesion.setAttribute("nombreRubro",proyectosDB.getNombreRubro());
				irA="/adminProyectos/ListaGastos.jsp";
			break;
			case Parametros.cmdGastoRubro:
				req.setAttribute("rubro",proyectosDB.getRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
				irA="/adminProyectos/NuevoGasto.jsp";
			break;
			case Parametros.cmdNuevoGastoRubro:
				if(proyectosDB.registraGasto((GastosRubro)sesion.getAttribute("RegistraGasto")))
					mensaje="El registro fue insertado correctamente";
				else
					mensaje="El registro no pudo ser insertado correctamente";
				req.setAttribute("bandera","si");
				irA="/adminProyectos/ListaGastos.jsp";
			break;
			case Parametros.cmdEliminaGasto:
		//		System.out.println("Entra a eliminar gasto");
				if(proyectosDB.eliminarGasto(req.getParameter("idGasto")))
					mensaje="El registro fue eliminado correctamente";
				else
					mensaje="El registro no pudo ser eliminado correctamente";
				req.setAttribute("listaGastosRubro",proyectosDB.getGastosRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),(String)sesion.getAttribute("idRub")));
				req.setAttribute("bandera","si");
				irA="/adminProyectos/ListaGastos.jsp";
			break;
			case Parametros.cmdActaFinalizacion:
			break;
			case Parametros.cmdGetInformes:
				AdminInformesDB informes=new AdminInformesDB(cursor,usuario.getPerfil());
	//			sesion.setAttribute("listaInformes",informes.getListaInformes(Long.parseLong(proyecto.getId())));
	//			sesion.setAttribute("listaDocs",informes.getListaDocAnexos(Long.parseLong(proyecto.getId())));
				irA="/adminProyectos/Documentos.jsp";
			break;
			case Parametros.cmdActualizarFlag:
				id=Integer.parseInt(proyecto.getId());
				 if (proyectosDB.actualizarFlag(id, req.getParameter("flag"))){
	               	mensaje="El estado de la actualización han sido modificado correctamente";
	               	proyecto.setFlag(Integer.parseInt(req.getParameter("flag")));
	               	sesion.setAttribute("proyecto",proyecto);
				 }
                else
               	 mensaje="No se pudo actualizar correctamente el estado de revisión";
				 irA="/adminProyectos/VerProyecto.jsp";
			 break;
			 case Parametros.cmdGuardarObservacion:
				 id=Integer.parseInt(proyecto.getId());
				 if (proyectosDB.insertaObservacion(id, req.getParameter("obsProyecto"),usuario.getIdUsuario()))
					 mensaje="Observación insertada correctamente";
                 else
                	mensaje="No se pudo insertar la observación";
				 proyecto.setListaObservaciones(proyectosDB.getListaObservaciones(id));
				 sesion.setAttribute("proyecto",proyecto);
				 irA="/adminProyectos/VerProyecto.jsp";
			 break;
			 case Parametros.cmdLecturaExcel:
					req.setAttribute("rubro",proyectosDB.getRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
					irA="/adminProyectos/GastosExcel.jsp";
			 break;
			 case Parametros.cmdInsertaInfoExcel:
				 	id=Integer.parseInt(proyecto.getId());
					proyectosDB.registraGastosExcel((java.util.List)sesion.getAttribute("listaGastoLeidos"),id);
					sesion.setAttribute("balanceProyecto",proyectosDB.getBalanceProyecto(""+id));
					irA="/adminProyectos/BalanceGeneral.jsp";
					sesion.removeAttribute("listaGastoLeidos");
			 break;
			 case Parametros.cmdCambioEstado:
				 id=Integer.parseInt(proyecto.getId());
				 if (proyectosDB.cambiaEstado(id, req.getParameter("estado"))){
					 mensaje="Estado de proyecto actualizado correctamente";
					 proyecto.setEstado(Integer.parseInt(req.getParameter("estado")));
				 }
                 else
                	mensaje="el estado no se pudo ser actualizado";
				 sesion.setAttribute("proyecto",proyecto);
				 irA="/adminProyectos/VerProyecto.jsp";
			 break;
			 case Parametros.cmdListarParaInventario:
				 req.setAttribute("listaElementos",proyectosDB.getElementosInventarioRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
				 req.setAttribute("listaGrupos", proyectosDB.getListaTotalGrupos());
				 irA="/adminProyectos/EntregaElementos.jsp";
			 break;
			 case Parametros.cmdEntregarElementos:				 
				 if(proyectosDB.entregarElementosProyecto((Devolutivo)sesion.getAttribute("listaDevolutivo"))){
					 mensaje="Elementos entregados satisfactoriamente";
					 BalanceGeneral bg=(BalanceGeneral)proyectosDB.getBalanceProyecto(""+req.getParameter("idProyecto"));
					 sesion.setAttribute("balanceProyecto",bg);
					 req.setAttribute("listaGastosRubro",proyectosDB.getGastosRubro(bg,req.getParameter("idRub")));
				 }
				 else
					 mensaje="Los elementos no se pudieron registrar\n"+proyectosDB.getMensaje();
				 irA="/adminProyectos/ListaGastos.jsp";
			 break;
			 default:
				req.setAttribute("listaConv",adminPropuestaDB.ajaxConv());
			 break;
		}
		accion=0;
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
