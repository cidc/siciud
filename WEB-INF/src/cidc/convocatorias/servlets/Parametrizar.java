package cidc.convocatorias.servlets;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convocatorias.db.ConvocatoriasDB;
import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.convocatorias.obj.InsercionGralOBJ;
import cidc.convocatorias.obj.ParametrosOBJ;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class Parametrizar extends ServletGeneral {

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
		long id=0;
		mensaje="";
						System.out.println("entra");
		if(convocatoriaOBJ!=null)
			id=convocatoriaOBJ.getConvId();
		switch(accion){
			case ParametrosOBJ.Listar:
				x="unir";
				switch(de){
					case ParametrosOBJ.ListaAspecto:
						irA="/Convocatoria/Aspectos.jsp";
						if(id!=0){
							req.setAttribute("listaCritOBJ",convocatoriasDB.consultaCriterios(ParametrosOBJ.ListaAspecto,convocatoriaOBJ.getConvId()));
							req.setAttribute("listaAspInscOBJ",convocatoriasDB.consultaAspectos(ParametrosOBJ.ListaAspecto,convocatoriaOBJ.getConvId()));
							req.setAttribute("listaAspOBJ",convocatoriasDB.consultaAspectos(0,convocatoriaOBJ.getConvId()));

						//	req.setAttribute("ListaCritOBJ",convocatoriasDB.consultaCriterios(ParametrosOBJ.ListaAspecto,0));
						}
					break;
					case ParametrosOBJ.ListaCompromiso:
						irA="/Convocatoria/Compromisos.jsp";
						if(id!=0){
							req.setAttribute("cantComp",""+convocatoriasDB.getCantCompromisos(convocatoriaOBJ.getConvId()));
							req.setAttribute("listaCompOBJ",convocatoriasDB.consultaCompromisos(0,0));
							req.setAttribute("listaCompInscOBJ",convocatoriasDB.consultaCompromisos(ParametrosOBJ.ListaCompromiso,convocatoriaOBJ.getConvId()));
						}
					break;
					case ParametrosOBJ.ListaCriterio:
						irA="/Convocatoria/Criterios.jsp";
						if(id!=0){
							req.setAttribute("ListaCritOBJ",convocatoriasDB.consultaCriterios(ParametrosOBJ.ListaCriterio,0));
							req.setAttribute("ListaCritInscOBJ",convocatoriasDB.consultaCriterios(1,convocatoriaOBJ.getConvId()));
							req.setAttribute("listaAspOBJ",convocatoriasDB.consultaAspectos(0,convocatoriaOBJ.getConvId()));
						}
					break;
					case ParametrosOBJ.ListaEje:
						irA="/Convocatoria/Ejes.jsp";
						if(id!=0){
							req.setAttribute("listaEjesOBJ",convocatoriasDB.consultaEjes(0,0));
							req.setAttribute("listaEjesInscOBJ",convocatoriasDB.consultaEjes(ParametrosOBJ.ListaEje,convocatoriaOBJ.getConvId()));
						}
					break;
					case ParametrosOBJ.ListaRubros:
						irA="/Convocatoria/Rubros.jsp";
						if(id!=0){
							req.setAttribute("ListaRubrosOBJ",convocatoriasDB.consultaRubros(0,0));
							req.setAttribute("ListaRubrosInscOBJ",convocatoriasDB.consultaRubros(ParametrosOBJ.ListaRubros,convocatoriaOBJ.getConvId()));
						}
					break;
					case ParametrosOBJ.ListaDocumentosRequisito:
                                                irA="/Convocatoria/DocumentosRequisito.jsp";
                                                if(id!=0){
                                                        req.setAttribute("listaDocOBJ",convocatoriasDB.consultaDocumentosRequisito(0,0));
                                                        req.setAttribute("listaDocInscOBJ",convocatoriasDB.consultaDocumentosRequisito(ParametrosOBJ.ListaDocumentosRequisito,convocatoriaOBJ.getConvId()));
                                                }
                                        break;
					case ParametrosOBJ.General:
						irA="/Convocatoria/ModificarConv.jsp";
					break;
					case ParametrosOBJ.Pdf:
						irA="/Convocatoria/Cargar.jsp";
					break;
					case ParametrosOBJ.Parametrizar:
						irA="/Convocatoria/Parametrizar.jsp";
					break;
					case ParametrosOBJ.porcentajes:
						irA="/Convocatoria/Porcentajes.jsp";
						if(id!=0){
							int com=convocatoriasDB.getComiteEvaluador(convocatoriaOBJ.getConvId());
							req.setAttribute("porcentajes",convocatoriasDB.getPorcentajes(convocatoriaOBJ.getConvId()));
							if(req.getParameter("dependencia")!=null)
								req.setAttribute("aspectos",convocatoriasDB.getAspectosComite(req.getParameter("dependencia")));
							else
								req.setAttribute("aspectos",convocatoriasDB.getAspectosComite(""+com));
							req.setAttribute("aspGuard",convocatoriasDB.getAspectos3(convocatoriaOBJ));
							req.setAttribute("listaDepend",convocatoriasDB.getDependencias());
							req.setAttribute("id",""+com);
						}
					break;
					case ParametrosOBJ.cambioComboComite:
						irA="/Convocatoria/Porcentajes.jsp";
						if(id!=0){
							req.setAttribute("porcentajes",convocatoriasDB.getPorcentajes(convocatoriaOBJ.getConvId()));
							if(req.getParameter("dependencia")!=null)
								req.setAttribute("aspectos",convocatoriasDB.getAspectosComite(req.getParameter("dependencia")));
							req.setAttribute("aspGuard",convocatoriasDB.getAspectos3(convocatoriaOBJ));
							req.setAttribute("listaDepend",convocatoriasDB.getDependencias());
							req.setAttribute("id",""+req.getParameter("dependencia"));
						}
					break;
					case ParametrosOBJ.cortes:
						irA="/Convocatoria/Cortes.jsp";
						if(id!=0){
							convocatoriaOBJ.setListaCortes(convocatoriasDB.getCortes(convocatoriaOBJ.getConvId()));
							sesion.setAttribute("convocatoriaOBJ",convocatoriaOBJ);
						}
					break;
				}
			break;

			//*********************************GUARDAR ASPECTOS**************************************************************
			case ParametrosOBJ.Guardar:
				x="desviar";
				InsercionGralOBJ insercionGralOBJ=(InsercionGralOBJ)sesion.getAttribute("general") ;
				switch(de){
					case ParametrosOBJ.ListaAspecto:
						irA="/Convocatoria/Aspectos.jsp";
						if(insercionGralOBJ!=null && convocatoriaOBJ!=null){
							convocatoriasDB.borrar(ParametrosOBJ.ListaAspecto,convocatoriaOBJ.getConvId());
							convocatoriasDB.insertaAspectos(insercionGralOBJ,convocatoriaOBJ.getConvId());
							mensaje="Aspectos de convocatoria almacenados";
						}else{
							mensaje="Fallo al almacenar los aspectos de esta convocatoria";
						}
						req.setAttribute("listaCritOBJ",convocatoriasDB.consultaCriterios(ParametrosOBJ.ListaAspecto,convocatoriaOBJ.getConvId()));
						req.setAttribute("listaAspInscOBJ",convocatoriasDB.consultaAspectos(ParametrosOBJ.ListaAspecto,convocatoriaOBJ.getConvId()));
						req.setAttribute("listaAspOBJ",convocatoriasDB.consultaAspectos(0,convocatoriaOBJ.getConvId()));
					break;
					case ParametrosOBJ.ListaCompromiso:
						irA="/Convocatoria/Compromisos.jsp";
						if(insercionGralOBJ!=null && convocatoriaOBJ!=null){
							if(convocatoriasDB.borrar(ParametrosOBJ.ListaCompromiso,convocatoriaOBJ.getConvId())){
								if(convocatoriasDB.insertaCompromisos(insercionGralOBJ,convocatoriaOBJ.getConvId()))
									mensaje="Compromisos de convocatoria almacenados";
								else
									mensaje="Fallo al almacenar los compromisos de esta convocatoria";
							}
							else
								mensaje="Fallo al almacenar los compromisos de esta convocatoria";
							req.setAttribute("cantComp",""+convocatoriasDB.getCantCompromisos(convocatoriaOBJ.getConvId()));
							req.setAttribute("listaCompOBJ",convocatoriasDB.consultaCompromisos(0,0));
							req.setAttribute("listaCompInscOBJ",convocatoriasDB.consultaCompromisos(ParametrosOBJ.ListaCompromiso,convocatoriaOBJ.getConvId()));

						}else{
							mensaje="Fallo al almacenar los compromisos de esta convocatoria";
						}
					break;
					case ParametrosOBJ.ListaCriterio:
						irA="/Convocatoria/Criterios.jsp";
						convocatoriasDB.borrar(ParametrosOBJ.ListaCriterio,convocatoriaOBJ.getConvId());
						if(insercionGralOBJ!=null && convocatoriaOBJ!=null){
							convocatoriasDB.insertaCriterios(insercionGralOBJ,convocatoriaOBJ.getConvId());
							mensaje="Criterios de convocatoria almacenados";
						}else{
							mensaje="Fallo al almacenar los criterios de esta convocatoria";
						}
						req.setAttribute("ListaCritOBJ",convocatoriasDB.consultaCriterios(ParametrosOBJ.ListaCriterio,0));
						req.setAttribute("ListaCritInscOBJ",convocatoriasDB.consultaCriterios(1,convocatoriaOBJ.getConvId()));
					break;
					case ParametrosOBJ.ListaEje:
						irA="/Convocatoria/Ejes.jsp";
						convocatoriasDB.borrar(ParametrosOBJ.ListaEje,convocatoriaOBJ.getConvId());
			//			System.out.println("entra");
						if(insercionGralOBJ!=null && convocatoriaOBJ!=null){
							convocatoriasDB.insertaEjes(insercionGralOBJ,convocatoriaOBJ.getConvId());
							mensaje="Ejes tem�ticos de convocatoria almacenados";
						}else{
							mensaje="Fallo al almacenar los Ejes tem�ticos de esta convocatoria";
						}
						req.setAttribute("listaEjesOBJ",convocatoriasDB.consultaEjes(0,0));
						req.setAttribute("listaEjesInscOBJ",convocatoriasDB.consultaEjes(ParametrosOBJ.ListaEje,convocatoriaOBJ.getConvId()));
					break;
					case ParametrosOBJ.ListaRubros:
						irA="/Convocatoria/Rubros.jsp";
						convocatoriasDB.borrar(ParametrosOBJ.ListaRubros,convocatoriaOBJ.getConvId());
						if(insercionGralOBJ!=null && convocatoriaOBJ!=null){
System.out.println("-------------> "+insercionGralOBJ);
							convocatoriasDB.insertaRubros(insercionGralOBJ,convocatoriaOBJ.getConvId());
							convocatoriaOBJ.setObservacion(insercionGralOBJ.getObservacion());
							mensaje="Rubros de convocatoria almacenados";
						}else{
							mensaje="Fallo al almacenar los Rubros de esta convocatoria";
						}
						req.setAttribute("ListaRubrosOBJ",convocatoriasDB.consultaRubros(0,0));
						req.setAttribute("ListaRubrosInscOBJ",convocatoriasDB.consultaRubros(ParametrosOBJ.ListaRubros,convocatoriaOBJ.getConvId()));
					break;
                                        case ParametrosOBJ.ListaDocumentosRequisito:
                                             irA="/Convocatoria/DocumentosRequisito.jsp";                                                if(insercionGralOBJ!=null && convocatoriaOBJ!=null){
                                                        if(convocatoriasDB.borrar(ParametrosOBJ.ListaDocumentosRequisito,convocatoriaOBJ.getConvId())){
                                                                if(convocatoriasDB.insertaDocumentosRequisito(insercionGralOBJ,convocatoriaOBJ.getConvId()))
                                                                        mensaje="Documentos de convocatoria almacenados";
                                                                else
                                                                        mensaje="Fallo al almacenar los documentos de esta convocatoria";
                                                        }
                                                        else
                                                                mensaje="Fallo al almacenar los documentos de esta convocatoria";
                                                        req.setAttribute("listaDocOBJ",convocatoriasDB.consultaDocumentosRequisito(0,0));
                                                        req.setAttribute("listaDocInscOBJ",convocatoriasDB.consultaDocumentosRequisito(ParametrosOBJ.ListaDocumentosRequisito,convocatoriaOBJ.getConvId()));
                           			}else{
                                                        mensaje="Fallo al almacenar los documentos de esta convocatoria";
                                                }
					break;

					case ParametrosOBJ.porcentajes:
						irA="/Convocatoria/Porcentajes.jsp";
						convocatoriasDB.borrar(ParametrosOBJ.porcentajes,convocatoriaOBJ.getConvId());
						if(insercionGralOBJ!=null && convocatoriaOBJ!=null){
							convocatoriasDB.insertaPorcentajes(insercionGralOBJ,convocatoriaOBJ.getConvId());
							convocatoriasDB.actualizaEvalua(insercionGralOBJ,convocatoriaOBJ.getConvId());
							mensaje="Porcentajes de Evaluaci�n de convocatoria almacenados";
						}else{
							mensaje="Fallo al almacenar los Porcentajes de Evaluaci�n de esta convocatoria";
						}
						req.setAttribute("porcentajes",convocatoriasDB.getPorcentajes(convocatoriaOBJ.getConvId()));
						req.setAttribute("aspectos",convocatoriasDB.getAspectosComite(req.getParameter("dependencia")));
						req.setAttribute("listaDepend",convocatoriasDB.getDependencias());
						req.setAttribute("id",req.getParameter("dependencia"));
					break;
					case ParametrosOBJ.AspectoComite:
						irA="/Convocatoria/Porcentajes.jsp";
						convocatoriasDB.borrar(ParametrosOBJ.AspectoComite,convocatoriaOBJ.getConvId());
						if(insercionGralOBJ!=null && convocatoriaOBJ!=null){
							convocatoriasDB.insertaAspectosComite(insercionGralOBJ,convocatoriaOBJ.getConvId());
							mensaje="Aspectos de evaluaci�n de convocatoria almacenados";
						}else{
							mensaje="Fallo al almacenar los aspectos de Evaluaci�n de esta convocatoria";
						}
						int com=convocatoriasDB.getComiteEvaluador(convocatoriaOBJ.getConvId());
						//System.out.println("-------------> "+req.getParameter("comite"));
						req.setAttribute("porcentajes",convocatoriasDB.getPorcentajes(convocatoriaOBJ.getConvId()));
						req.setAttribute("aspectos",convocatoriasDB.getAspectosComite(req.getParameter("comite")));
						req.setAttribute("listaDepend",convocatoriasDB.getDependencias());
						req.setAttribute("aspGuard",convocatoriasDB.getAspectos3(convocatoriaOBJ));
						req.setAttribute("id",""+req.getParameter("comite"));
					break;
					case ParametrosOBJ.cortes:
						irA="/Convocatoria/Cortes.jsp";
						if(id!=0){
							if(req.getParameter("caso").equals("1")){
								if(convocatoriasDB.insertaCorte((InsercionGralOBJ)sesion.getAttribute("general"), convocatoriaOBJ.getConvId()))
									mensaje="Corte insertado Correctamente";
								else mensaje="El corte no pudo ser insertado";
							}
							if(req.getParameter("caso").equals("2")){
								if(convocatoriasDB.actualizaCorte((InsercionGralOBJ)sesion.getAttribute("general")))
									mensaje="Corte actualizado Correctamente";
								else mensaje="El corte no pudo ser actualizado";
							}
							if(req.getParameter("caso").equals("3")){
								if(convocatoriasDB.eliminaCorte((InsercionGralOBJ)sesion.getAttribute("general")))
									mensaje="Corte eliminado Correctamente";
								else mensaje="El corte no pudo ser eliminado";
							}

							convocatoriaOBJ.setListaCortes(null);
							convocatoriaOBJ.setListaCortes(convocatoriasDB.getCortes(convocatoriaOBJ.getConvId()));
							sesion.setAttribute("convocatoriaOBJ",convocatoriaOBJ);
							sesion.removeAttribute("general");
						}
					break;
				}
			break;
			case 0:
				irA="/Convocatoria/Parametrizar.jsp";
			break;
		}
		retorno[0]=x;
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
