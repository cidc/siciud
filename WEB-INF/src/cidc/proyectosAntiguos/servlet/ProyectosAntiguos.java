package cidc.proyectosAntiguos.servlet;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectos.obj.Parametros;
import cidc.proyectosAntiguos.db.ProyectosAntiguosDB;
import cidc.proyectosAntiguos.obj.CambiosOBJ;
import cidc.proyectosAntiguos.obj.CoinvestigadoresOBJ;
import cidc.proyectosAntiguos.obj.ConsultaProyectosOBJ;
import cidc.proyectosAntiguos.obj.DevolucionesOBJ;
import cidc.proyectosAntiguos.obj.GastosOBJ;
import cidc.proyectosAntiguos.obj.ParametrosOBJ;
import cidc.proyectosAntiguos.obj.ProyectoAntiguoOBJ;

/**
 * Servlet Class
 *
 * @web.servlet              name="ProyectosAntiguos"
 *                           display-name="Name for ProyectosAntiguos"
 *                           description="Description for ProyectosAntiguos"
 * @web.servlet-mapping      url-pattern="/ProyectosAntiguos"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */

public class ProyectosAntiguos extends ServletGeneral {

	       Usuario usuario = null;

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		HttpSession sesion = req.getSession();
		context=config.getServletContext();
		cursor = new CursorDB();
		ProyectoAntiguoOBJ objProyecto =null;
		String irA = "/index.html";
		mensaje="";
		// Usuario user = (Usuario) sesion.getAttribute("loginUsuario");
		ProyectosAntiguosDB proyAntiguosDB = new ProyectosAntiguosDB(cursor, 2);
		if(sesion.getAttribute("proyectos")!=null)
			objProyecto = (ProyectoAntiguoOBJ)sesion.getAttribute("proyectos");

		usuario = (Usuario) sesion.getAttribute("loginUsuario");

		long idPro =0;
		int val = 0;

		if (req.getParameter("validar")!= null){
			val = Integer.parseInt(req.getParameter("validar"));}

		switch (val)
			{
			 case ParametrosOBJ.InsertarProyecto:
				     irA = "/proyectosAntiguos/ProyectoAntiguo.jsp";
				     if (proyAntiguosDB.insertarProyecto(objProyecto))
				    	 mensaje="La informaci�n del proyecto se ha almacenado correctamente";
                     else
                    	 mensaje="Problemas al almacenar la informaci�n del proyecto";
			         sesion.setAttribute("proyectos", proyAntiguosDB.consultarProyectoAntiguo(proyAntiguosDB.getObjProyecto().getId()));
                     //req.setAttribute("convenios",proyAntiguosDB.consultarConvenios());
    	             break;

			 case ParametrosOBJ.PaginaFiltro:
				 	 sesion.removeAttribute("proyectos");
				 	 sesion.removeAttribute("proyecto");
				     irA = "/proyectosAntiguos/FiltroListaProAn.jsp";
				     if(sesion.getAttribute("ajaxProyCur")==null)
				    	 sesion.setAttribute("ajaxProyCur",proyAntiguosDB.consultaProyectos());
		             break;

			 case ParametrosOBJ.ListaProyectos:
				     irA = "/proyectosAntiguos/FiltroListaProAn.jsp";
				     sesion.setAttribute("lista", proyAntiguosDB.consultarListaProyectos((ConsultaProyectosOBJ)sesion.getAttribute("consultas")));
			         sesion.removeAttribute("consultas");
                     sesion.removeAttribute("proyectos");
                     break;

			 case ParametrosOBJ.ConsultarProyecto:
				     if (usuario.getPerfil()== 16){irA="/proyectosAntiguos/ProyectoAntiguo.jsp";}
				     if (usuario.getPerfil()!= 16){irA="/proyectosAntiguos/ProyectoAntiguoCom.jsp";}
			         int idProyecto = Integer.parseInt(req.getParameter("idProyecto"));
			         objProyecto = proyAntiguosDB.consultarProyectoAntiguo(idProyecto);
			         sesion.setAttribute("proyectos", objProyecto);
			         mensaje="";
			         break;

			 case ParametrosOBJ.ActualizarProyecto:
				     irA="/proyectosAntiguos/ProyectoAntiguo.jsp";
				     if (proyAntiguosDB.actualizarProyecto(objProyecto))
				    	 mensaje="La información del proyecto se ha actualizado correctamente";
                     else
                    	 mensaje="Problemas al actualizar la información del proyecto";
				     objProyecto = proyAntiguosDB.consultarProyectoAntiguo(objProyecto.getId());
				     sesion.removeAttribute("proyectos");
	                 sesion.setAttribute("proyectos", objProyecto);
	                 req.setAttribute("convenios",proyAntiguosDB.consultarConvenios());
                     break;

			 case ParametrosOBJ.ListaConvenios:
				     if (usuario.getPerfil()== 16){irA="/proyectosAntiguos/ProyectoAntiguo.jsp";}
			         if (usuario.getPerfil()!= 16){irA="/proyectosAntiguos/ProyectoAntiguoCom.jsp";}
			         req.setAttribute("convenios",proyAntiguosDB.consultarConvenios());
			         if(sesion.getAttribute("ajaxProyCur")==null)
				    	 sesion.setAttribute("ajaxProyCur",proyAntiguosDB.consultaProyectos());
			         mensaje="";
                     break;

			 case ParametrosOBJ.PaginaArchivos:
				     irA = "/proyectosAntiguos/ArchivosProyectoAntiguo.jsp";
                     break;

			 case ParametrosOBJ.SumaRubros:
				     irA = "/proyectosAntiguos/PresupuestoProyectoAntiguo.jsp";
			         idPro = Integer.parseInt(req.getParameter("id"));
		             sesion.setAttribute("listaRubros", proyAntiguosDB.consultarSumaRubros(idPro));
		             req.setAttribute("rubros",proyAntiguosDB.consultarRubros());
		             req.setAttribute("listaRubrosAprobados", proyAntiguosDB.consultarRubrosAprobados(objProyecto.getId()));
		             break;

			 case ParametrosOBJ.GastosRubro:
				     idPro = objProyecto.getId();
				     irA = "/proyectosAntiguos/PresupuestoProyectoAntiguo.jsp";
				     int idRubro=Integer.parseInt(req.getParameter("idRubro"));
				     req.setAttribute("lista1", proyAntiguosDB.consultarGastosRubro(idPro, idRubro));
				     req.setAttribute("rubros",proyAntiguosDB.consultarRubros());
				     String nomRub = proyAntiguosDB.consultarNomRubro(idRubro);
				     req.setAttribute("nombreRubro", nomRub);
				     req.setAttribute("listaRubrosAprobados", proyAntiguosDB.consultarRubrosAprobados(objProyecto.getId()));
			         break;

			 case ParametrosOBJ.ListaCoinvestigadores:
				     irA = "/proyectosAntiguos/Coinvestigadores.jsp";
				     idPro = objProyecto.getId();
			         req.setAttribute("lista", proyAntiguosDB.consultarCoinvestigadores(idPro));
		             break;

			 case ParametrosOBJ.InsertarCoinvestigador:
				     irA = "/proyectosAntiguos/Coinvestigadores.jsp";
		             idPro = objProyecto.getId();
				     proyAntiguosDB.insertarCoinvestigador(idPro, (CoinvestigadoresOBJ)sesion.getAttribute("coin"));
	                 req.setAttribute("lista", proyAntiguosDB.consultarCoinvestigadores(idPro));
	                 sesion.removeAttribute("coin");
	                 mensaje="Se ha insertado el integrante correctamente";
                     break;

			 case ParametrosOBJ.EliminarCoinvestigador:
				     irA = "/proyectosAntiguos/Coinvestigadores.jsp";
			         int idCoinv = Integer.parseInt(req.getParameter("idCoinv"));
			         if (proyAntiguosDB.eliminarCoinvestigador(idCoinv))
			        	 mensaje="Se ha eliminado el integrante correctamente";
                     else
                    	 mensaje="No se pudo eliminar el integrante";
				     idPro = objProyecto.getId();
			         req.setAttribute("lista", proyAntiguosDB.consultarCoinvestigadores(idPro));
                     break;

			 case ParametrosOBJ.InsertarGasto:
				     irA = "/proyectosAntiguos/PresupuestoProyectoAntiguo.jsp";
                     idPro = objProyecto.getId();
                     if (proyAntiguosDB.insertarGasto(idPro, (GastosOBJ)sesion.getAttribute("gasto")))
                    	 mensaje="El nuevo gasto se ha almacenado correctamente";
                     else
                    	 mensaje="No se pudo almacenar el gasto";
                     sesion.removeAttribute("coin");
                     sesion.setAttribute("lista", proyAntiguosDB.consultarSumaRubros(idPro));
		             req.setAttribute("rubros",proyAntiguosDB.consultarRubros());
		             objProyecto = proyAntiguosDB.consultarProyectoAntiguo(idPro);
		             sesion.setAttribute("proyectos", objProyecto);
		             sesion.removeAttribute("gasto");
                     break;

			 case ParametrosOBJ.EliminarGasto:
				     irA = "/proyectosAntiguos/PresupuestoProyectoAntiguo.jsp";
	                 int idGasto = Integer.parseInt(req.getParameter("id"));
	                 if (proyAntiguosDB.eliminarGasto(idGasto))
	                	 mensaje="Se ha eliminado el gasto correctamente";
                     else
                    	 mensaje="No se pudo eliminar el gasto";
	                 idPro = objProyecto.getId();
	                 sesion.setAttribute("lista", proyAntiguosDB.consultarSumaRubros(idPro));
		             req.setAttribute("rubros",proyAntiguosDB.consultarRubros());
		             sesion.setAttribute("proyectos", proyAntiguosDB.consultarProyectoAntiguo(idPro));
                     break;

			 case ParametrosOBJ.ListaInventario:
				     irA = "/proyectosAntiguos/Inventario.jsp";
			         idPro = objProyecto.getId();
	                 req.setAttribute("lista", proyAntiguosDB.consultarInventario(idPro));
	                 req.setAttribute("lista1", proyAntiguosDB.consultarInvRub(idPro));
                     break;

			 case ParametrosOBJ.ElementoInventario:
				     irA = "/proyectosAntiguos/Elemento.jsp";
	                 int idElem = Integer.parseInt(req.getParameter("idElemento"));
                     req.setAttribute("elemento", proyAntiguosDB.consultarElemento(idElem));
                     break;

			 case ParametrosOBJ.ConsultarCoinvestigadores:
				     irA = "/proyectosAntiguos/Coinvestigadores.jsp";
                     idPro = objProyecto.getId();
                     req.setAttribute("lista", proyAntiguosDB.consultarCoinvestigadores(idPro));
                     int idCoin = Integer.parseInt(req.getParameter("idCoinv"));
                     req.setAttribute("coinv", proyAntiguosDB.consultarCoinvestigador(idCoin));
                     req.setAttribute("accion", ""+18);
                     break;

			 case ParametrosOBJ.ActualizarCoinvestigador:
				     irA = "/proyectosAntiguos/Coinvestigadores.jsp";
                     idPro = objProyecto.getId();
                     int idCoi = Integer.parseInt(req.getParameter("idCoinv"));
                     if (proyAntiguosDB.actualizarCoinvestigador(idCoi, (CoinvestigadoresOBJ)sesion.getAttribute("coin")))
	                	 mensaje="Se ha actualizado el integrante correctamente";
                     else
                    	 mensaje="No se pudo actualizar el integrante";
                     req.setAttribute("lista", proyAntiguosDB.consultarCoinvestigadores(idPro));
                     sesion.removeAttribute("coin");
                     break;

			 case ParametrosOBJ.ConsultarCambios:
				     if (usuario.getPerfil()== 16 || usuario.getPerfil()== 3){
				    	 irA = "/proyectosAntiguos/Cambios.jsp";
				    }
		             if (usuario.getPerfil()!= 16 && usuario.getPerfil()!= 3){
		            	 req.setAttribute("lista2", proyAntiguosDB.consultarProrrogas(objProyecto.getId()));
		            	 irA = "/proyectosAntiguos/Prorrogas.jsp";}
                     req.setAttribute("lista", proyAntiguosDB.consultarCambios(objProyecto.getId()));
                     req.setAttribute("lista1", proyAntiguosDB.consultarTipoCambio(objProyecto.getId()));
                     break;

			 case ParametrosOBJ.EliminarCambio:
				     irA = "/proyectosAntiguos/Cambios.jsp";
			         int idCam = Integer.parseInt(req.getParameter("id"));
			         if(proyAntiguosDB.eliminarCambio(idCam))
			        	 mensaje="Se ha eliminado el cambio correctamente";
			         else
			        	 mensaje="No se pudo eliminar el cambio";
				     idPro = objProyecto.getId();
				     req.setAttribute("lista", proyAntiguosDB.consultarCambios(idPro));
                     req.setAttribute("lista1", proyAntiguosDB.consultarTipoCambio(idPro));
                     break;

			 case ParametrosOBJ.InsertarCambio:
				     irA = "/proyectosAntiguos/Cambios.jsp";
                     idPro = objProyecto.getId();
		             if(proyAntiguosDB.insertarCambio(idPro, (CambiosOBJ)sesion.getAttribute("cambio")))
		            	 mensaje="Se ha registrado el cambio correctamente";
		             else
		            	 mensaje="No se pudo registrar el cambio";
		             req.setAttribute("lista", proyAntiguosDB.consultarCambios(idPro));
                     req.setAttribute("lista1", proyAntiguosDB.consultarTipoCambio(idPro));
			         sesion.removeAttribute("cambio");
                     break;
			 case ParametrosOBJ.ConsultarDevoluciones:
				     irA = "/proyectosAntiguos/Devoluciones.jsp";
			         idPro = objProyecto.getId();
                     req.setAttribute("lista", proyAntiguosDB.consultarDevoluciones(idPro));
                     req.setAttribute("lista1", proyAntiguosDB.consultarInvRub(idPro));
			         break;
			 case ParametrosOBJ.ActualizarDevoluciones:
			         irA = "/proyectosAntiguos/Devoluciones.jsp";
		             idPro = objProyecto.getId();
		             DevolucionesOBJ objDevol = (DevolucionesOBJ)sesion.getAttribute("devol");
                     if (proyAntiguosDB.actualizarDevoluciones(idPro, objDevol))
                    	 mensaje="Las devoluciones han sido actualizadas correctamente";
                     else
                    	 mensaje="Las devoluciones no han sido actualizadas correctamente";
		             req.setAttribute("lista", proyAntiguosDB.consultarDevoluciones(idPro));
                     req.setAttribute("lista1", proyAntiguosDB.consultarInvRub(idPro));
                     sesion.removeAttribute("devol");
		         break;
			 case ParametrosOBJ.actualizarFlag:
				 idPro = objProyecto.getId();
				 if (proyAntiguosDB.actualizarFlag(idPro, req.getParameter("flag")))
                	 mensaje="El estado de la actualización han sido modificado correctamente";
                 else
                	 mensaje="No se pudo actualizar correctamente el estado de revisión";
				 objProyecto.setFlag(proyAntiguosDB.getFlag(idPro));
				 sesion.setAttribute("proyectos",objProyecto);
				 irA = "/proyectosAntiguos/ProyectoAntiguo"+req.getParameter("desde")+".jsp";
			 break;
			 case ParametrosOBJ.actualizarEstadoProyecto:
				 idPro = objProyecto.getId();
				 if (proyAntiguosDB.actualizarEstadoProyecto(idPro, req.getParameter("estado")))
                	 mensaje="El estado del proyecto ha sido modificado correctamente";
                 else
                	 mensaje="No se pudo actualizar correctamente el estado del proyecto";
				 objProyecto.setEstado(proyAntiguosDB.getEstado(idPro));
				 sesion.setAttribute("proyectos",objProyecto);
				 irA = "/proyectosAntiguos/ProyectoAntiguo"+req.getParameter("desde")+".jsp";
			 break;
			 case ParametrosOBJ.guardarObservacion:
				 idPro = objProyecto.getId();
				 if (proyAntiguosDB.insertaObservacion(idPro, req.getParameter("obsProyecto"),usuario.getIdUsuario()))
                	 mensaje="Observación insertada correctamente";
                 else
                	 mensaje="No se pudo insertar la observación";
				 objProyecto.setListaObservaciones(proyAntiguosDB.getListaObservaciones(idPro));
				 sesion.setAttribute("proyectos",objProyecto);
				 irA = "/proyectosAntiguos/ProyectoAntiguoCom.jsp";
			 break;
			 case ParametrosOBJ.eliminaObservacion:
				 idPro = objProyecto.getId();
				 if (proyAntiguosDB.eliminaObservacion(req.getParameter("idObservacion")))
                	 mensaje="Observación eliminada correctamente";
                 else
                	 mensaje="No se pudo eliminada la observación";
				 objProyecto.setListaObservaciones(proyAntiguosDB.getListaObservaciones(idPro));
				 sesion.setAttribute("proyectos",objProyecto);
				 irA = "/proyectosAntiguos/ProyectoAntiguoCom.jsp";
			 break;
			 case ParametrosOBJ.cmdListaRubrosAprobados:
				 req.setAttribute("listaRubros", proyAntiguosDB.consultarRubros());
				 req.setAttribute("listaRubrosAprobados", proyAntiguosDB.consultarRubrosAprobados(objProyecto.getId()));
				 irA="/proyectosAntiguos/RubrosAprobados.jsp";
			 break;
			 case ParametrosOBJ.cmdInsertaRubrosAprobados:
				 if(proyAntiguosDB.insertaRubrosAprobrados(objProyecto.getId(), req.getParameterValues("idRubro"),req.getParameterValues("valorRubro")))
					 mensaje="Rubros almacenados satisfactoriamente";
				 else
					 mensaje="Los rubros no pudieron ser almacenados";
				 req.setAttribute("listaRubros", proyAntiguosDB.consultarRubros());
				 req.setAttribute("listaRubrosAprobados", proyAntiguosDB.consultarRubrosAprobados(objProyecto.getId()));
				 irA="/proyectosAntiguos/RubrosAprobados.jsp";
			 break;
		     default: irA = "/proyectosAntiguos/NuevoProyectoAntiguo.jsp";
				     // req.setAttribute("convenios",proyAntiguosDB.consultarConvenios());
		     		req.setAttribute("convocatoria",proyAntiguosDB.consultarConvocatorias());
				      sesion.removeAttribute("proyectos");
				      if(sesion.getAttribute("ajaxProyCur")==null)
					    sesion.setAttribute("ajaxProyCur",proyAntiguosDB.consultaProyectos());
				      Calendar c=Calendar.getInstance();
				      req.setAttribute("ano", c.get(Calendar.YEAR));
		     break;

			}
		val=0;
		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
		}
}
