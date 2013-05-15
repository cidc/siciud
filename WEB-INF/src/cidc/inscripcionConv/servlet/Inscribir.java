package cidc.inscripcionConv.servlet;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convocatorias.db.ConvocatoriasDB;
import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.general.db.CursorDB;
import cidc.general.db.UsuarioDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.inscripcionConv.db.InscripcionConvDB;
import cidc.inscripcionConv.obj.InscripcionConvOBJ;
import cidc.inscripcionConv.obj.ParametrosOBJ;
import cidc.inscripSistema.obj.Persona;

public class Inscribir extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		String irA="/InscripcionConv/Inscripcion.jsp";
		String mensaje="";
		String terminar="no";
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		UsuarioDB usuarioDB=new UsuarioDB(cursor,usuario.getPerfil());
		Persona persona=(Persona)sesion.getAttribute("persona");
		if(usuario.getPerfil()!=13)
			persona=usuarioDB.getPersona(usuario.getIdUsuario());
		ConvocatoriaOBJ convocatoriaOBJ=(ConvocatoriaOBJ)sesion.getAttribute("datosConv");
		InscripcionConvDB inscripcionConvDB=new InscripcionConvDB(cursor,usuario.getPerfil());
		InscripcionConvOBJ inscripcionConvOBJ=null;
		retorno[0]="unir";
		switch(accion){
			case ParametrosOBJ.cmdIngresar:
				if(usuario.getPerfil()==13){
					req.setAttribute("listaPropuestas", inscripcionConvDB.getListaPropuestas(usuario.getIdUsuario(),usuario.getPerfil(),convocatoriaOBJ.getConvId()));
					irA="/InscripcionConv/listaPropuesta.jsp";						
				}else{
					if(convocatoriaOBJ!=null){
						System.out.println("------>"+convocatoriaOBJ.getConvAno()+"  -- "+convocatoriaOBJ.getConvNumero());						
						   if(convocatoriaOBJ.getConvAno()==2013 && (convocatoriaOBJ.getConvNumero()==1 || convocatoriaOBJ.getConvNumero()==2 || convocatoriaOBJ.getConvNumero()==3 || convocatoriaOBJ.getConvNumero()==4 ||convocatoriaOBJ.getConvNumero()==12)){
							System.out.println("Ingreso a la convocatoria de este año------>");
							irA="/movilidad/adminMovilidad.x?accion=2";
							sesion.setAttribute("persona",persona);
						}else{
							if(convocatoriaOBJ.getConvAno()==2012 && convocatoriaOBJ.getConvNumero()==14){
								System.out.println("Ingreso a la convocatoria 14 de Apoyo a Artículos------>");
								irA="/articulos_Conv/ArticuloConv.x?accion=2";
								sesion.setAttribute("persona",persona);
							}
							else{
								req.setAttribute("listaPropuestas", inscripcionConvDB.getListaPropuestas(usuario.getIdUsuario(),usuario.getPerfil(),convocatoriaOBJ.getConvId()));
								terminar=req.getParameter("terminar");
								System.out.println("Terminar =------>" +terminar);
								irA="/InscripcionConv/listaPropuesta.jsp";
								if(terminar!=null){
									System.out.println("Terminar =------>" +terminar);
									if(terminar.equals("si")){
										String idProp=req.getParameter("idProp");
										System.out.println("IdPropuesta =------>" +idProp);
										if(inscripcionConvDB.enviarMail(idProp,persona));
										mensaje="El registro ha terminado correctamente, por favor verifique en su correo electrónico los datos de inscripción";
										req.setAttribute("terminar",terminar);
										
									//req.setAttribute("listaPropuestas", inscripcionConvDB.finalizarInscripcion(usuario.getIdUsuario(),usuario.getPerfil(),convocatoriaOBJ.getConvId(), convocatoriaOBJ.getConvId()));
									
									}							
								}								
							}
						}
					}
				}
			break;
			case ParametrosOBJ.cmdPaso0:
				sesion.setAttribute("persona",persona);
				sesion.setAttribute("ajaxProyCur",inscripcionConvDB.AjaxProyectoCur());
				sesion.setAttribute("listaTotalGrupos",inscripcionConvDB.totalGruposInvestigacion());
				req.setAttribute("areaSnies", inscripcionConvDB.AjaxAreasSnies());
				if(sesion.getAttribute("ajaxGrupo")==null)
					sesion.setAttribute("ajaxGrupo",inscripcionConvDB.AjaxGruposInvestigacion(1,1));
				req.setAttribute("listaRubrosOBJ",inscripcionConvDB.getRubros(convocatoriaOBJ.getConvId(),1));
				req.setAttribute("listaRubrosOBJ1",inscripcionConvDB.getRubros(convocatoriaOBJ.getConvId(),0));
				if(usuario.getPerfil()==13)
					irA="/InscripcionConv/Inscripcion2.jsp";
				else
					irA="/InscripcionConv/Inscripcion.jsp";
			break;

			case ParametrosOBJ.cmdPaso1:
				ConvocatoriasDB convocatoriasDB=new ConvocatoriasDB(cursor,usuario.getPerfil());
	        	long id=0;
				try{
					id = inscripcionConvDB.getMaxId(null)+1;
				}
				catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				inscripcionConvOBJ=(InscripcionConvOBJ)sesion.getAttribute("inscripcionConvOBJ");
				if(inscripcionConvDB.insertaInscripcion(inscripcionConvOBJ)){
					//******************conseguir el resumen de lo ya inscrito pero falta hacer la cuenta de los rubros

	        		req.setAttribute("resumen",inscripcionConvDB.getResumen(""+id,inscripcionConvOBJ));
	        		//******************consultar el listado de rubros almacenados en la convocatoria a la cual se vinculan.
	        		req.setAttribute("listaComp",convocatoriasDB.consultaCompromisos(ParametrosOBJ.ListaCompromiso,convocatoriaOBJ.getConvId()));
	        		irA="/InscripcionConv/Compromisos.jsp";
	        	}else{
	        		mensaje="La Inscripción no pudo ser almacenada...\n Por favor vuelva a intentarlo";
	        		irA="/InscripcionConv/Error.jsp";
	        	}
				retorno[0]="desviar";
			break;
			case ParametrosOBJ.cmdPaso2:
				System.out.println("Ingreso al paso 02	");
				inscripcionConvOBJ=(InscripcionConvOBJ)sesion.getAttribute("inscripcionConvOBJ");
				if(inscripcionConvDB.insertaCompromisos(inscripcionConvOBJ)){
					mensaje="Compromisos insertados correctamente";
					irA="/InscripcionConv/Cargar.jsp";
				}
				else{
					mensaje="El registro de los compromisos no pudo ser insertado correctamente \n"+inscripcionConvDB.getMensaje();
					irA="/InscripcionConv/Error.jsp";
				}
				accion=0;
				retorno[0]="desviar";
			break;
			case ParametrosOBJ.cmdPaso3:
				String idProp=req.getParameter("idProp");
				req.setAttribute("archivos",inscripcionConvDB.getInfoArchivos(idProp));
				irA="/InscripcionConv/Cargar.jsp";
			break;
		}
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
