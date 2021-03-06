package cidc.convenios.servlet;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convenios.db.AdminConvenioDB;
import cidc.convenios.obj.AportesOBJ;
import cidc.convenios.obj.Convenio;
import cidc.convenios.obj.EntidadAsociadaOBJ;
import cidc.convenios.obj.GetConvenioOBJ;
import cidc.convenios.obj.Parametros;
import cidc.convenios.obj.PersonaOBJ;
import cidc.convenios.obj.TiemposOBJ;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;




public class AdminConvenios extends ServletGeneral {
	
	
//comentario
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/adminConvenio/NuevoConvenio.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		AdminConvenioDB adminConv= new AdminConvenioDB(cursor,usuario.getPerfil());
		Convenio conv=null;
		GetConvenioOBJ objconv=(GetConvenioOBJ)sesion.getAttribute("datoConvenio"); 
		Calendar fecha = new GregorianCalendar();
		mensaje="";
		int a�o = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH);
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		switch(accion){
			case Parametros.cmdInsertaConvenio:
				
				conv=(Convenio)sesion.getAttribute("nuevoConvenio");
				conv.setN_UsuDigita(usuario.getNombre());
				conv.setF_Digita(a�o + "-" + (mes+1) + "-" +dia+"");
				
				String idgru=req.getParameter("idgrupo");
			
				
				
				if(adminConv.nuevoConvenio(conv)){
					int idc=adminConv.idconvenio(req.getParameter("nombreConvenio"));
					objconv=new GetConvenioOBJ();
					objconv.setIdconvenio(idc+"");
					adminConv.registrarGrupoConvenio(objconv,idgru);
					adminConv.insertaObservacion(idc, req.getParameter("observaciones"),usuario.getIdUsuario());
					objconv.setVAprobado(""+conv.getVAprobado());
					adminConv.insertarFinanza(objconv);
					objconv=null;
				    mensaje="Resgistro insertado correctamente";
				}
				else
					mensaje="El resgistro no pudo ser insertado correctamente";
					
					
				sesion.removeAttribute("nuevoConvenio");
				irA="/adminConvenio/NuevoConvenio.jsp";
			break;
			case Parametros.cmdListaConvenio:
				//Buscar Convenio
				req.setAttribute("listaConvenios", adminConv.listaConvenio());
				irA="/adminConvenio/ListaConvenios.jsp";
			break;
			case Parametros.cmdGetConvenio:
				sesion.removeAttribute("datoConvenio");
				sesion.setAttribute("datoConvenio", adminConv.buscarConvenio(Integer.parseInt(req.getParameter("idConv"))));
				req.setAttribute("accion","4");
				irA="/adminConvenio/Verconvenio.jsp";
			break;
			case Parametros.cmdUpdateConvenio:
				/*if(adminConv.actualizaConvenio((Convenio)sesion.getAttribute("nuevoConvenio")))
					mensaje="Resgistro actualizado correctamente";
				else
					mensaje="El resgistro no pudo ser actualizado correctamente";
			//	req.setAttribute("datoConvenio", adminConv.getConvenio(req.getParameter("idConvenio")));
				req.setAttribute("accion","4");*/
				irA="/adminConvenio/NuevoConvenio.jsp";
			break;
			case Parametros.InsertaObservacionConvenio:
				if (adminConv.insertaObservacion(Integer.parseInt(req.getParameter("aa")), req.getParameter("obsconvenio"),usuario.getIdUsuario()))
					 mensaje="Observaci�n insertada correctamente";
                 else
                	mensaje="No se pudo insertar la observaci�n";
					 objconv.getListaObservaciones().clear();
				objconv.setListaObservaciones(adminConv.getListaObservaciones(Integer.parseInt(objconv.getIdconvenio())));
				
				 irA="/adminConvenio/Verconvenio.jsp";
			break;
			
			case Parametros.cambioEstado:
				  if (adminConv.cambiaEstado(Integer.parseInt(objconv.getIdconvenio()),req.getParameter("tipo"), req.getParameter("estado"))){
					 mensaje="Estado de convenio actualizado correctamente";
				}
               else
               	mensaje="el estado no se pudo ser actualizado";
				
				 objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
				 sesion.setAttribute("datoConvenio", objconv);
				 irA="/adminConvenio/Verconvenio.jsp";
			break;
			
			case Parametros.consultaDocumentos:
				
				sesion.setAttribute("listaDocs",adminConv.getListaDocAnexos(Integer.parseInt(objconv.getIdconvenio())));
				
				irA="/adminConvenio/Documentos.jsp";
				
			break;
case Parametros.AdicionarTiempo:
				
				if(adminConv.insertarTiempo((TiemposOBJ)sesion.getAttribute("tiempoConv"), objconv,usuario)){
					mensaje="El tiempo adicional fue registrado satisfactoriamente";
					 objconv.getListaTiempos().clear();
					objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
					sesion.setAttribute("datoConvenio", objconv);
				}else
					mensaje="El tiempo adicional no pudo ser registrado";
				sesion.removeAttribute("tiempoConv");				
				irA="/adminConvenio/VerTiempos.jsp";
				
			break;
			case Parametros.EliminarTiempo:
				if(adminConv.eliminarTiempo(req.getParameter("idTiempo"))){
					mensaje="El tiempo adicional fue eliminado satisfactoriamente";
					objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
					sesion.setAttribute("datoConvenio", objconv);
				}else
					mensaje="El tiempo adicional no pudo ser eliminado";
				sesion.removeAttribute("tiempoConv");				
				irA="/adminConvenio/VerTiempos.jsp";
			break;
			case Parametros.AdicionarPersona:
				PersonaOBJ objpersona=(PersonaOBJ)sesion.getAttribute("personaCon");
				objpersona.setRegitradoPor(""+usuario.getIdUsuario());
				objpersona.setFechaReg(a�o + "-" + (mes+1) + "-" +dia+"");
					if(adminConv.registrarPersonaConvenio(objconv,objpersona)){
						mensaje="La persona fue registrada satisfactoriamente";
						sesion.removeAttribute("datoConvenio");
						objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
						sesion.setAttribute("datoConvenio", objconv);
						
					}else
						mensaje="La persona no pudo ser registrada";
					sesion.removeAttribute("personaCon");		
					irA="/adminConvenio/Personas.jsp";				
				

			break;
			case Parametros.EliminarPersona:
				
				if(adminConv.eliminarPersonaConvenio(req.getParameter("idPersona"))){
					mensaje="La persona fue eliminada satisfactoriamente";
					sesion.removeAttribute("datoConvenio");
					objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
					sesion.setAttribute("datoConvenio", objconv);
				}else
					mensaje="La persona NO pudo ser eliminada";
				sesion.removeAttribute("personaCon");
				
					irA="/adminConvenio/Personas.jsp";
			break;
			case Parametros.ActualizarPersona:
				if(adminConv.actualizarPersonaConvenio((PersonaOBJ)sesion.getAttribute("personaCon"))){
					mensaje="La persona fue actualizada satisfactoriamente";
					sesion.removeAttribute("personaCon");
					sesion.removeAttribute("datoConvenio");
					objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
					sesion.setAttribute("datoConvenio", objconv);;
				}else
					mensaje="La persona no pudo ser actualizada";
				sesion.removeAttribute("personaCon");		
				irA="/adminConvenio/Personas.jsp";
			break;
			
			case Parametros.AdicionarGrupo:
			
				
				String idgrupo=req.getParameter("idgrupo");
				
				
					if(adminConv.registrarGrupoConvenio(objconv,idgrupo)){
						mensaje="el grupo fue registrado satisfactoriamente";
						sesion.removeAttribute("datoConvenio");
						objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
						sesion.setAttribute("datoConvenio", objconv);
						
					}else
						mensaje="el grupo no pudo ser registrada";
					
					
					irA="/adminConvenio/Grupos.jsp";				
				

			break;
			
			case Parametros.RegistroEntidad:
				
				EntidadAsociadaOBJ ent=(EntidadAsociadaOBJ)sesion.getAttribute("convEntidad");
					
				
					if(adminConv.registrarEntidadConvenio(objconv,(EntidadAsociadaOBJ)sesion.getAttribute("convEntidad"))){
						mensaje="El convenio fue vinculado Correctamente";
						sesion.removeAttribute("convEntidad");
						objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
						sesion.setAttribute("datoConvenio", objconv);
						
					}else
						mensaje="No se puedo vincular el convenio";
					
						req.setAttribute("mens",mensaje );
						irA="/adminEntidad/GestEntidad.x?validar=3&por=3";	
						sesion.removeAttribute("convEntidad");
				

			break;
				case Parametros.cmdListaRubrosAprobados:
					 req.removeAttribute("listaRubros");
					 
					 req.setAttribute("listaRubros", adminConv.consultarRubros());
					 
					 objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
					 sesion.setAttribute("datoConvenio", objconv);
					 
				
					 if(req.getAttribute("m")==null){
						 
					}else{
						mensaje=(String)req.getAttribute("m");
					 }
				
						
					// req.setAttribute("listaRubrosAprobados", proyAntiguosDB.consultarRubrosAprobados(objProyecto.getId()));
					 
					 irA="/adminConvenio/RubrosAprobados.jsp";
				
				break;
				case Parametros.cmdInsertaRubrosAprobados:
					
					  req.setAttribute("listaRubros", adminConv.consultarRubros());
					 
					 
					// adminConv.insertaRubrosAprobrados(Integer.parseInt(req.getParameter("idfinanza")), req.getParameter("idRubro"), req.getParameter("codigo"),req.getParameterValues("valorEntidad"), req.getParameter("observacion"),a�o + "-" + (mes+1) + "-" +dia+"",usuario.getIdUsuario(),Integer.parseInt(req.getParameter("numeroentidad")),req.getParameterValues("idconvent"));
					 
					if(adminConv.insertaRubrosAprobrados(Integer.parseInt(req.getParameter("idfinanza")), req.getParameter("idRubro"), req.getParameter("codigo"),req.getParameterValues("valorEntidad"), req.getParameter("observacion"),a�o + "-" + (mes+1) + "-" +dia+"",usuario.getIdUsuario(),Integer.parseInt(req.getParameter("numeroentidad")),req.getParameterValues("idconvent")))
						 mensaje="Rubros almacenados satisfactoriamente";
					 else
						 mensaje="Los rubros no pudieron ser almacenados";
					// req.setAttribute("listaRubros", proyAntiguosDB.consultarRubros());
					// req.setAttribute("listaRubrosAprobados", proyAntiguosDB.consultarRubrosAprobados(objProyecto.getId()));
					   irA="/adminConvenio/AdminConvenio.x?accion=15";
				
				break;
				
				case Parametros.CargarAportes:
					 req.setAttribute("nombreEntidad",req.getParameter("nombreEntidad"));
					 req.removeAttribute("listaAportesEntidad");
					 req.setAttribute("listaAportesEntidad", adminConv.buscarAportesEntidad(Integer.parseInt(req.getParameter("idCon"))));
					 req.setAttribute("entidadaporte", adminConv.entidadaporte(Integer.parseInt(req.getParameter("idCon"))));
					 req.setAttribute("idCon",req.getParameter("idCon"));
					 irA="/adminConvenio/Aportes.jsp";
				break;
				
				case Parametros.NuevoAporte:
					AportesOBJ aporte=null;
					aporte=(AportesOBJ)sesion.getAttribute("aporte");
					aporte.setUsudigita(usuario.getIdUsuario()+"");
					aporte.setFechaDigita(a�o + "-" + (mes+1) + "-" +dia+"");
					aporte.setTipoAporte("Efectivo");
					aporte.setNconvenioEntidad(req.getParameter("idCon"));
					req.setAttribute("nombreEntidad",req.getParameter("nombreEntidad"));
					req.setAttribute("idCon",req.getParameter("idCon"));
					req.setAttribute("entidadaporte", adminConv.entidadaporte(Integer.parseInt(req.getParameter("idCon"))));

				if(adminConv.registrarAporte(objconv,aporte)){
						mensaje="El aporte fue registrado";
						sesion.removeAttribute("convEntidad");
						objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
						sesion.setAttribute("datoConvenio", objconv);
					}else{
						mensaje="No se puede registrar el aporte";
					}
				req.removeAttribute("listaAportesEntidad");
				req.setAttribute("listaAportesEntidad", adminConv.buscarAportesEntidad(Integer.parseInt(req.getParameter("idCon"))));
				
					
					irA="/adminConvenio/Aportes.jsp";
				break;
				
				case Parametros.VerCrp:
					
					
					objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
					sesion.setAttribute("datoConvenio", objconv);
					objconv.setListacrpsConv(null);
					req.setAttribute("idcdp",req.getParameter("idcdp"));
					req.setAttribute("nombrecdp",req.getParameter("nombrecdp"));
					req.setAttribute("valortotal",req.getParameter("valor"));
					objconv.setListacrpsConv(adminConv.getcrp(Integer.parseInt(req.getParameter("idcdp")),(Integer.parseInt(objconv.getIdconvenio()))));
					irA="/adminConvenio/AdicionarCRP.jsp";
				break;
				
				case Parametros.AdicionarCRP:
					
					
					req.setAttribute("idcdp",req.getParameter("idcdp"));
					req.setAttribute("nombrecdp",req.getParameter("nombrecdp"));
					req.setAttribute("valortotal",req.getParameter("valortotal"));
					
					
				      if(adminConv.insertaCRP(Integer.parseInt(req.getParameter("idcdp")),Integer.parseInt(req.getParameter("valorcrp")) , req.getParameter("crp"),req.getParameter("codigocrp"), req.getParameter("clientecrp"), req.getParameter("observcrp"), a�o + "-" + (mes+1) + "-" +dia+"", usuario.getIdUsuario())){
				    	  mensaje="CRP almacenados satisfactoriamente";  
				      }else{
				    	  mensaje="El CRP NO  pudo ser almacenado";
				      }
						 irA="/adminConvenio/AdminConvenio.x?accion=19";
				break;
				case Parametros.EliminarCRP:
					req.getParameter("idcdp");
					req.getParameter("valorcrpeliminado");
					 
					
					if(adminConv.eliminarCRP(req.getParameter("crpd"),req.getParameter("idcdp"),req.getParameter("valorcrpeliminado"))){
						mensaje="El crp fue eliminado satisfactoriamente";
						objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
						sesion.setAttribute("datoConvenio", objconv);
					}else
						mensaje="El crp no pudo ser eliminado";
								
					irA="/adminConvenio/AdminConvenio.x?accion=19";
				break;
				case Parametros.EliminarCDP:
					 
					if(adminConv.eliminarCDP(req.getParameter("idcdp"))){
						mensaje="El crp fue eliminado satisfactoriamente";
						objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
						sesion.setAttribute("datoConvenio", objconv);
					}else{
						mensaje="El CDP no pudo ser eliminado,tiene CRP asignados";
					}
					req.setAttribute("m",mensaje);
								
					irA="/adminConvenio/AdminConvenio.x?accion=15";
				break;
				case Parametros.InsertarReembolso:
					
					if(adminConv.insertarReembolso(req.getParameterValues("idEntidadcdp"),req.getParameterValues("vreembolso"),Integer.parseInt(req.getParameter("numeroentidad")),Integer.parseInt(req.getParameter("idcdp")))){
						mensaje="El reembolso fue registrado satisfactoriamente";
						objconv=adminConv.buscarConvenio(Integer.parseInt(objconv.getIdconvenio()));
						sesion.setAttribute("datoConvenio", objconv);
					}else{
						mensaje="El reembolso no pudo ser registrado";
					}
						
					irA="/adminConvenio/RubrosAprobados.jsp";
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
