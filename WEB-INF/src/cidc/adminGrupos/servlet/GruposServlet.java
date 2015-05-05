package cidc.adminGrupos.servlet;

import java.io.IOException;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminGrupos.db.AdminGruposDB;
import cidc.adminGrupos.db.grupo.GruposGestionDB;
import cidc.adminGrupos.obj.FiltroPersona;
import cidc.adminGrupos.obj.Integrante;
import cidc.adminGrupos.obj.ListaGrupos;
import cidc.adminGrupos.obj.Parametros;
import cidc.adminGrupos.obj.GrupoInvestigacion;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class GruposServlet extends ServletGeneral {


	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/grupos/VerGrupo.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		AdminGruposDB adminGruposDB=new AdminGruposDB(cursor,usuario.getPerfil());
		GruposGestionDB gruposGestionDB=new GruposGestionDB(cursor,usuario.getPerfil());
		long idGrupo=0;
		GrupoInvestigacion grupo=(GrupoInvestigacion)sesion.getAttribute("grupo");;
		mensaje="";
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		retorno[0]="unir";
		System.out.println("grupos servlet accion: "+accion);
		switch(accion){
			case Parametros.VerGrupo:
				sesion.removeAttribute("infoGrupo");
				grupo=adminGruposDB.getVerGrupo(req.getParameter("idGrupo"),sesion.getAttribute("listaMisGrupos"));
				List listaProy=adminGruposDB.AjaxProyectoCur(grupo.getFacultad());
				List listaAreas=adminGruposDB.AjaxAreasSnies();
				grupo.setProyectosCurriculares(listaProy);
				grupo.setAreasSNIES(listaAreas);
				sesion.setAttribute("infoGrupo", grupo);
				irA="/grupos/VerGrupo.jsp";
			break;
			case Parametros.ActualizaGrupo:
				if(adminGruposDB.ActualizaGrupo2((GrupoInvestigacion)sesion.getAttribute("grupo1"))){
					GrupoInvestigacion gr=(GrupoInvestigacion)sesion.getAttribute("infoGrupo");
					GrupoInvestigacion gr1=(GrupoInvestigacion)sesion.getAttribute("grupo1");
					gr.setWeb(gr1.getWeb());
					gr.setMail(gr1.getMail());
					gr.setCodproyCurr(gr1.getCodproyCurr());
					gr.setCodAreaSNIES(gr1.getCodAreaSNIES());
					sesion.setAttribute("infoGrupo",gr);
					sesion.removeAttribute("grupo1");
					irA="/grupos/VerGrupo.jsp";
					mensaje="El registro fue modificado Satisfactoriamente";
				}
				else
					mensaje="El registro no pudo ser modificado";
				irA="/grupos/VerGrupo.jsp";
			break;
			case Parametros.buscaIntegrantesGrupo:
			//	System.out.println("--entra a buscar integrantes de--->"+req.getParameter("idGrupo"));
				idGrupo=Long.parseLong(req.getParameter("idGrupo"));
				req.setAttribute("listaIntegrantes",adminGruposDB.buscaIntegrantesGrupo(idGrupo));
				req.setAttribute("nombreGrupo", getNombreGrupo((java.util.List)sesion.getAttribute("listaMisGrupos"),req.getParameter("idGrupo")));
				irA="/grupos/ListaIntegrantes.jsp";
				sesion.setAttribute("idGrupo",req.getParameter("idGrupo"));
			break;
			case Parametros.consultaPersonaOracle:
				//bscar todos cod, cc
				if(req.getParameter("cedula")!=null && req.getParameter("codigoUd")!=null)
					sesion.setAttribute("Integrantes",adminGruposDB.consultarIntegrantes(req.getParameter("cedula"),req.getParameter("codigoUd")));
				irA="/grupos/ListaPersonasOracle.jsp";
			break;
			case Parametros.verIntegranteGrupo:
				idGrupo=0;
				if(req.getParameter("flagMod").equals("1"))
					req.setAttribute("st", ""+Parametros.nuevoIntegranteGrupo);				
				else{
					req.setAttribute("st", ""+Parametros.actualizaIntegranteGrupo);
					idGrupo=Integer.parseInt(""+sesion.getAttribute("idGrupo"));
				}
				int idper=req.getParameter("id")==null?-1:Integer.parseInt(req.getParameter("id"));
				Integrante integrante=adminGruposDB.consultaDatosPersona(req.getParameter("cedula"),req.getParameter("codUD"),idGrupo,idper);
				integrante.setCodareasnies(integrante.getCodareasnies());
				integrante.setAreasSNIES(adminGruposDB.AjaxAreasSnies());
				sesion.setAttribute("integrante2",integrante);
				req.setAttribute("listaCodigos", adminGruposDB.getCodigosIntegrante(req.getParameter("cedula")));
				req.setAttribute("flagMod",req.getParameter("flagMod"));
				sesion.removeAttribute("integrante");
		//		System.out.println("--papel--->"+integrante.getPapel());
				if(integrante.getPapel()!=6)
					irA="/grupos/Integrante.jsp";
				else
					irA="/grupos/IntegranteExterno.jsp";
				
			break;
			case Parametros.actualizaIntegranteGrupo:
				Integrante nuevo=null;
				req.setAttribute("st", ""+Parametros.actualizaIntegranteGrupo);
				idGrupo=Long.parseLong(""+sesion.getAttribute("idGrupo"));
				if(adminGruposDB.actualizaIntegranteGrupo((Integrante)sesion.getAttribute("integrante"),idGrupo)){
					mensaje="El integrante fue actualizado correctamente";
					Integrante antiguo=(Integrante)sesion.getAttribute("integrante2");					
					nuevo=(Integrante)sesion.getAttribute("integrante");
					listaProy=adminGruposDB.AjaxProyectoCur(nuevo.getCodFacultad());
					listaAreas=adminGruposDB.AjaxAreasSnies();
					nuevo.setProyectosCurriculares(listaProy);
					//nuevo.setProyectosCurriculares(listaAreas);
					nuevo.setCodareasnies(nuevo.getCodareasnies());
					nuevo.setAreasSNIES(adminGruposDB.AjaxAreasSnies());
					sesion.setAttribute("integrante2",nuevo);
					sesion.removeAttribute("integrante");
				}
				else
					mensaje="El integrante no pudo ser actualizado correctamente";
				System.out.println("nuevo "+nuevo.getPapel());
				if(nuevo.getPapel()!=6){
					irA="/grupos/Integrante.jsp";
					req.setAttribute("listaCodigos", adminGruposDB.getCodigosIntegrante(req.getParameter("cedula")));
				}
				else
					irA="/grupos/IntegranteExterno.jsp";
			break;
			case Parametros.eliminaIntegranteGrupo:
				idGrupo=Long.parseLong(""+sesion.getAttribute("idGrupo"));
				if(adminGruposDB.eliminaIntegranteGrupo((Integrante)sesion.getAttribute("integrante2"),idGrupo))
					mensaje="El integrante fue eliminado correctamente";
				else
					mensaje="El integrante no pudo ser eliminado correctamente";
				req.setAttribute("listaIntegrantes",adminGruposDB.buscaIntegrantesGrupo(idGrupo));
				irA="/adminGrupos/adminIntegrantes/ListaIntegrante.jsp";
				retorno[0]="desviar";
			break;
			case Parametros.claveInvestigador:
				
				Integrante integ = (Integrante)sesion.getAttribute("integrante2");
				String nombre = integ.getNombres();
				String apellido = integ.getApellidos();
				String ID = "";
				String numero = Long.toString(integ.getId());
				
				nombre = nombre.trim();
				nombre = nombre.toLowerCase();
				apellido = apellido.trim();
				apellido = apellido.toLowerCase();
				
				ID = completeid(getidname(nombre), getidlastname(apellido)) + numero;
				
				//System.out.println("\n el ID del investigador es: "+ ID);
				
				req.setAttribute("st", ""+Parametros.actualizaIntegranteGrupo);
			
				if(gruposGestionDB.claveInvestigador(req.getParameter("id"),req.getParameter("papel"), ID)){
					
					mensaje="La clave fue asignada Satisfactoriamente";
				}else{
					mensaje="No se pudo asignar la clave del integrante.. favor volver a intentar";
				}
				irA="/grupos/Integrante.jsp";
			break;
			case Parametros.IntegranteGrupo:
				System.out.println("--entra a una cosa vacia que no tiene casi nada--->");
				req.setAttribute("st", ""+Parametros.nuevoIntegranteGrupo);
				irA="/grupos/Integrante.jsp";
				sesion.removeAttribute("integrante");
			break;
			case Parametros.nuevoIntegranteGrupo:
				idGrupo=Long.parseLong(""+sesion.getAttribute("idGrupo"));
				if(adminGruposDB.insertarPersona((Integrante)sesion.getAttribute("integrante"),idGrupo)){
					System.out.println("Bandera Crear UsuarioInvestigador");
					mensaje="El integrante fue insertado correctamente";
				}else
					mensaje="El integrante no pudo ser insertado correctamente \n"+adminGruposDB.getMensaje();
				
				req.setAttribute("listaIntegrantes",adminGruposDB.buscaIntegrantesGrupo(idGrupo));
				
				irA="/grupos/ListaIntegrantes.jsp";
			break;
			case Parametros.formNuevoInteExt:
				sesion.removeAttribute("integrante2");
				irA="/grupos/IntegranteExterno.jsp";
				req.setAttribute("st","7");
				req.setAttribute("flagmod",""+Parametros.nuevoIntegranteGrupo);
			break;
			default:
				irA="/grupos/ListaPersonasOracle.jsp";	
			break;
		}

		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
	
	public String getNombreGrupo(List listaGrupos,String id){
		String retorno=null;
		ListaGrupos grupo=null;
		for(int i=0;i<listaGrupos.size();i++){
			grupo=(ListaGrupos)listaGrupos.get(i);
			if(id.endsWith(""+grupo.getIdGrupo()))
				retorno=grupo.getNombreGrupo();
		}
		return retorno;
	}
/**
 * getidname() obtiene el nombre del integrante del Grupo de Investigacion
 * 				y retorna id_name una cadena tratada.
 * 
 */
	public String getidname(String name){
		String id_name = "";
		int espacionombre = 0;
			
			while(name.indexOf(" ") != -1){
				espacionombre = name.indexOf(" ");
				id_name = id_name + name.charAt(0);
				name = name.substring(espacionombre+1, name.length());
			}
			id_name = id_name + name.charAt(0);
		return id_name; 
	}

/**
 * getidlastname() obtiene el o los apellidos del integrante del grupo de
 * 					investigación, trata la cadena y retorna el string.
 * 
 */
	public String getidlastname(String lastname){
		
		String id_lastname = "";
		int espacionombre = 0;
		
		if(lastname.indexOf(" ") == -1)
		{
			id_lastname = lastname;
		}else{
			espacionombre = lastname.indexOf(" ");
			id_lastname = lastname.substring(0,espacionombre);
			lastname = lastname.substring(espacionombre+1, lastname.length());
			}
			while(lastname.indexOf(" ") != -1){
				espacionombre = lastname.indexOf(" ");
				id_lastname = id_lastname + lastname.charAt(0);
				lastname = lastname.substring(espacionombre+1, lastname.length());
				}
			id_lastname = id_lastname + lastname.charAt(0);
		return id_lastname;
	}
	
/**
 * 
 * @param name Toma id_name y lo concatena con id_lastname;
 * @param lastname Toma id_lastname y lo concatena con id_name
 * @return
 */
	
	public String completeid (String name, String lastname)
	{
		return name+lastname;
	}
}
