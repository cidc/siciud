package cidc.proyectosGeneral.servlet;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminGrupos.db.AdminGruposDB;

import cidc.adminPropuestas.db.AdminPropuestaDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectosGeneral.obj.CoInvest;
import cidc.proyectosGeneral.obj.Devolutivo;
import cidc.proyectosGeneral.obj.GastosRubro;
import cidc.proyectosGeneral.obj.BalanceGeneral;
import cidc.proyectosGeneral.obj.ParametrosOBJ;
import cidc.proyectosGeneral.obj.Tiempos;
import cidc.proyectosGeneral.db.ProyectosGeneralDB;
import cidc.proyectosGeneral.obj.FiltroGeneralProyecto;
import cidc.proyectosGeneral.obj.Proyecto;

public class AdminGeneralProyectos extends ServletGeneral {

	Proyecto proyecto=null; 
	
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		String irA="/adminProyectos/ListaProyectos.jsp";
		ProyectosGeneralDB proyectosGeneralDB=new ProyectosGeneralDB(cursor,usuario.getPerfil());
		AdminPropuestaDB adminPropuestaDB=new AdminPropuestaDB(cursor,usuario.getPerfil());
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		proyecto=(Proyecto)sesion.getAttribute("proyecto");
		mensaje="";		
		switch(accion)
		{
		//	Filtra la inforamción general de los proyectos de investigación
		case ParametrosOBJ.filtrarProyectos:
				sesion.setAttribute("listaConv",adminPropuestaDB.ajaxConv());
				sesion.setAttribute("listaProyectos",proyectosGeneralDB.getListaProyectos((FiltroGeneralProyecto) sesion.getAttribute("filtroProyecto")));
				sesion.removeAttribute("filtroProyecto");
			break;			
			case ParametrosOBJ.verProyecto:
				sesion.removeAttribute("proyecto");
				sesion.removeAttribute("balanceProyecto");
				sesion.setAttribute("proyecto",proyectosGeneralDB.buscarProyecto(req.getParameter("id"),req.getParameter("tipo")));						
				irA="/adminProyectos/VerProyecto.jsp";
			break;			
			case ParametrosOBJ.InsertaObservacionProyecto:
				 if (proyectosGeneralDB.insertaObservacion(proyecto.getId(),proyecto.getClaseProyecto(), req.getParameter("obsProyecto"),usuario.getIdUsuario()))
					 mensaje="Observación insertada correctamente";
                 else
                	mensaje="No se pudo insertar la observación";
				 
				 proyecto.getListaObservaciones().clear();
				 proyecto.setListaObservaciones(proyectosGeneralDB.getListaObservaciones(proyecto.getId(),proyecto.getClaseProyecto()));
				 
				 sesion.setAttribute("proyecto",proyecto);
				 irA="/adminProyectos/VerProyecto.jsp";
			break;			
			case ParametrosOBJ.cambioEstado:
				 if (proyectosGeneralDB.cambiaEstado(proyecto.getId(),proyecto.getClaseProyecto(), req.getParameter("estado"))){
					 mensaje="Estado de proyecto actualizado correctamente";
					 proyecto.setEstado(Integer.parseInt(req.getParameter("estado")));
				 }
                 else
                	mensaje="el estado no se pudo ser actualizado";
				 sesion.setAttribute("proyecto",proyecto);
				 irA="/adminProyectos/VerProyecto.jsp";
			break;
			case ParametrosOBJ.cambioFlag:
			 if (proyectosGeneralDB.actualizarFlag(proyecto.getId(),proyecto.getClaseProyecto(), req.getParameter("flag"))){
              	mensaje="El estado de la actualización han sido modificado correctamente";
              	proyecto.setFlag(Integer.parseInt(req.getParameter("flag")));
              	sesion.setAttribute("proyecto",proyecto);
			 } else
				 mensaje="No se pudo actualizar correctamente el estado de revisiÃ³n";
			 
			 irA="/adminProyectos/VerProyecto.jsp";
			break;
			case ParametrosOBJ.consultaDocumentos:				
				sesion.setAttribute("listaDocs",proyectosGeneralDB.getListaDocAnexos(proyecto.getId(),proyecto.getClaseProyecto()));				
				irA="/adminProyectos/Documentos"+proyecto.getClaseProyecto()+".jsp";
			break;
			case ParametrosOBJ.cmdBalanceGral:
				sesion.removeAttribute("balanceProyecto");
				sesion.setAttribute("balanceProyecto",proyectosGeneralDB.getBalanceProyecto(proyecto));
				irA="/adminProyectos/BalanceGeneral.jsp";
			break;			
			case ParametrosOBJ.cmdListaGastosRubro:
				sesion.setAttribute("idRub",req.getParameter("idRub"));
				req.setAttribute("listaGastosRubro",proyectosGeneralDB.getGastosRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
				sesion.setAttribute("nombreRubro",proyectosGeneralDB.getNombreRubro());
				irA="/adminProyectos/ListaGastos.jsp";
			break;
			case ParametrosOBJ.cmdListaEliminaGasto:
				if(proyectosGeneralDB.eliminarGasto(req.getParameter("idGasto"),proyecto))
					mensaje="El registro fue eliminado correctamente";
				else
					mensaje="El registro no pudo ser eliminado correctamente";
				req.setAttribute("listaGastosRubro",proyectosGeneralDB.getGastosRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),(String)sesion.getAttribute("idRub")));
				req.setAttribute("bandera","si");
				irA="/adminProyectos/ListaGastos.jsp";
			break;
			case ParametrosOBJ.cmdListarParaInventario:
				req.setAttribute("listaElementos",proyectosGeneralDB.getElementosInventarioRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
				req.setAttribute("listaGrupos", proyectosGeneralDB.getListaTotalGrupos());
				irA="/adminProyectos/EntregaElementos.jsp";
			break;		
			
			//Entrega de elementos a grupos de investigación
			case ParametrosOBJ.cmdEntregarElementos:
			if(proyectosGeneralDB.entregarElementosProyecto((Devolutivo)sesion.getAttribute("listaDevolutivo"),proyecto)){
				 mensaje="Elementos entregados satisfactoriamente";
				 BalanceGeneral bg=(BalanceGeneral)proyectosGeneralDB.getBalanceProyecto(proyecto);
				 sesion.setAttribute("balanceProyecto",bg);
				 req.setAttribute("listaGastosRubro",proyectosGeneralDB.getGastosRubro(bg,req.getParameter("idRub")));
			 }
			 else
				 mensaje="Los elementos no se pudieron registrar\n"+proyectosGeneralDB.getMensaje();
			 irA="/adminProyectos/ListaGastos.jsp";			
			break;
			case ParametrosOBJ.cmdFormNuevoGasto:
				req.setAttribute("rubro",proyectosGeneralDB.getRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
				irA="/adminProyectos/NuevoGasto.jsp";
			break;
			
			case ParametrosOBJ.cmdRegistrarGasto:
				if(proyectosGeneralDB.registraGasto((GastosRubro)sesion.getAttribute("RegistraGasto"),proyecto))
					mensaje="El registro fue insertado correctamente";
				else
					mensaje="La placa que intenta registrar ya existe";
				req.setAttribute("bandera","si");
				sesion.removeAttribute("RegistraGasto");
				irA="/adminProyectos/ListaGastos.jsp";
			break;
			case ParametrosOBJ.cmdEliminarGasto:
				if(proyectosGeneralDB.eliminarGasto(req.getParameter("idGasto"),proyecto))
					mensaje="El registro fue eliminado correctamente";
				else
					mensaje="El registro no pudo ser eliminado correctamente";
				req.setAttribute("listaGastosRubro",proyectosGeneralDB.getGastosRubro((BalanceGeneral)sesion.getAttribute("balanceProyecto"),(String)sesion.getAttribute("idRub")));
				req.setAttribute("bandera","si");
				irA="/adminProyectos/ListaGastos.jsp";
			break;
			case ParametrosOBJ.cmdConsultaListaRubros:
				req.setAttribute("listaRubros", proyectosGeneralDB.consultarRubros(proyecto));
				irA="/adminProyectos/ModificarRubros.jsp";
			break;
			case ParametrosOBJ.cmdModificarRubros:
				if(proyectosGeneralDB.modificarPresupuesto(proyecto, req.getParameterValues("idRubro"), req.getParameterValues("valorRubro"))){
					mensaje="Los rubros fueron actualizados satisfactoriamente";
				}else
					mensaje="Los rubros no pudieron ser actualizados";
				sesion.setAttribute("balanceProyecto",proyectosGeneralDB.getBalanceProyecto(proyecto));
				irA="/adminProyectos/BalanceGeneral.jsp";
			break;
			case ParametrosOBJ.cmdAdicionarTiempo:
				if(proyectosGeneralDB.insertarTiempo((Tiempos)sesion.getAttribute("tiempo"), proyecto,usuario)){
					mensaje="El tiempo adicional fue registrado satisfactoriamente";
					proyecto.setListaTiempos(proyectosGeneralDB.getListaTiempos(proyecto));
					sesion.setAttribute("proyecto", proyecto);
				}else
					mensaje="El tiempo adicional no pudo ser registrado";
				sesion.removeAttribute("tiempo");				
				irA="/adminProyectos/VerTiempos.jsp";
			break;
			case ParametrosOBJ.cmdEliminarTiempo:
				if(proyectosGeneralDB.eliminarTiempo(req.getParameter("idTiempo"), proyecto)){
					mensaje="El tiempo adicional fue eliminado satisfactoriamente";
					proyecto.setListaTiempos(proyectosGeneralDB.getListaTiempos(proyecto));
					sesion.setAttribute("proyecto", proyecto);
				}else
					mensaje="El tiempo adicional no pudo ser eliminado";
				sesion.removeAttribute("tiempo");				
				irA="/adminProyectos/VerTiempos.jsp";
			break;
			case ParametrosOBJ.cmdRegistraIntegrante:
				String tipo=req.getParameter("desde");
				if(req.getParameter("desde")==null){
					if(proyectosGeneralDB.registrarIntegrante(proyecto,(CoInvest)sesion.getAttribute("integrante"))){
						mensaje="La persona fue registrada satisfactoriamente";
						proyecto.setListaCoInvestigadores(proyectosGeneralDB.getListaCoInvestigadores(proyecto.getIdPropuesta(),""+proyecto.getClaseProyecto()));
						sesion.setAttribute("proyecto", proyecto);
					}else
						mensaje="La persona no pudo ser registrada";
					sesion.removeAttribute("integrante");		
					irA="/adminProyectos/Coinvestigadores.jsp";				
				}else{
					if(proyectosGeneralDB.registrarIntegrante2(proyecto,(CoInvest)sesion.getAttribute("integrante"))){
						mensaje="La persona fue registrada satisfactoriamente";
						proyecto.setListaCoInvestigadores(proyectosGeneralDB.getListaCoInvestigadorescontrato(proyecto.getIdPropuesta(),""+proyecto.getClaseProyecto()));
						sesion.setAttribute("proyecto", proyecto);
					}else
						mensaje="La persona no pudo ser registrada";
					sesion.removeAttribute("integrante");		
					irA="/adminProyectos/Contrato.jsp";					
				}


			break;
			case ParametrosOBJ.cmdEliminaIntegrante:
				tipo=req.getParameter("desde");
				if(proyectosGeneralDB.eliminarIntegrante(req.getParameter("id"),proyecto)){
					mensaje="La persona fue eliminada satisfactoriamente";
					proyecto.setListaCoInvestigadores(proyectosGeneralDB.getListaCoInvestigadores(proyecto.getIdPropuesta(),""+proyecto.getClaseProyecto()));
					sesion.setAttribute("proyecto", proyecto);
				}else
					mensaje="La persona NO pudo ser eliminada";
				sesion.removeAttribute("integrante");
				if(tipo!=null)
					irA="/adminProyectos/Contrato.jsp";
				else
					irA="/adminProyectos/Coinvestigadores.jsp";
			break;
			case ParametrosOBJ.cmdActualizaIntegrante:
				if(proyectosGeneralDB.actualizarIntegrante(proyecto,(CoInvest)sesion.getAttribute("integrante"))){
					mensaje="La persona fue actualizada satisfactoriamente";
					proyecto.setListaCoInvestigadores(proyectosGeneralDB.getListaCoInvestigadores(proyecto.getIdPropuesta(),""+proyecto.getClaseProyecto()));
					sesion.setAttribute("proyecto", proyecto);
				}else
					mensaje="La persona no pudo ser actualizada";
				sesion.removeAttribute("integrante");		
				irA="/adminProyectos/Coinvestigadores.jsp";
			break;
			default:
				irA="/adminProyectos/FiltroProyectosGeneral.jsp";
				sesion.setAttribute("ajaxProyCur",proyectosGeneralDB.AjaxProyectoCur());
			break;
		}		
		accion=0;
		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
