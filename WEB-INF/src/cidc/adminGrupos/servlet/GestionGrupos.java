package cidc.adminGrupos.servlet;

import java.io.IOException;
import java.util.List;

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
import cidc.general.obj.Paginacion;
import cidc.general.servlet.ServletGeneral;

public class GestionGrupos extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		String irA="/adminGrupos/HomeAdminGrupos.jsp";
		GrupoInvestigacion grupoInvestigacion=null;
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		AdminGruposDB adminGruposDB=new AdminGruposDB(cursor,usuario.getPerfil());
		GrupoInvestigacion grupo=(GrupoInvestigacion)sesion.getAttribute("grupo");;
		mensaje="";
		Paginacion pag=null;
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		req.setAttribute("prop",req.getParameter("prop"));
		retorno[0]="unir";
	//	System.out.println("entra a servlet GestionGrupo "+accion);
		switch(accion){
			case Parametros.NuevoGrupo:
				grupoInvestigacion=(GrupoInvestigacion)sesion.getAttribute("grupo");
				if(grupoInvestigacion!=null){
					if(adminGruposDB.nuevoGrupo(grupoInvestigacion)){
						mensaje="Grupo Insertado Correctamente";
						irA="/adminGrupos/Documentos.jsp";
					}
					else{
						mensaje="El Grupo no pudo ser Insertado Correctamente";
						irA="/adminGrupos/NuevoGrupo.jsp";
					}
				}
				
				req.setAttribute("grupo","display:none");
				sesion.removeAttribute("grupo");
			break;
			case Parametros.VistaListaGrupos:
				irA="/adminGrupos/ListaGrupos.jsp";
			break;
			case Parametros.ListaGrupos:			
				//obtener número de registros de la consulta
				pag=getPaginacion(adminGruposDB.getNumGrupos(req.getParameter("facultad"),req.getParameter("tipo")),req.getParameter("regporpag"),req.getParameter("desde"));
				req.setAttribute("paginacion", pag);
	        	//hacer consulta normalito pero enviando el Limit y el offset
	        	req.setAttribute("listaGrupos", adminGruposDB.getListaGrupos(req.getParameter("facultad"),req.getParameter("tipo"),pag.getOffset(),pag.getRegPorPag()));
				req.setAttribute("facultad", req.getParameter("facultad"));
				req.setAttribute("tipo", req.getParameter("tipo"));
				req.setAttribute("regporpag", req.getParameter("regporpag"));
				
				irA="/adminGrupos/ListaGrupos.jsp";
			break;
			case Parametros.VerGrupo:
				sesion.removeAttribute("grupo");				
				sesion.setAttribute("grupo", adminGruposDB.getVerGrupo(req.getParameter("id")));
				grupo=adminGruposDB.getVerGrupo(req.getParameter("id"));
				System.out.println(grupo.getFacultad());
				List listaProy=adminGruposDB.AjaxProyectoCur(grupo.getFacultad());
				List listaAreas=adminGruposDB.AjaxAreasSnies();
				grupo.setProyectosCurriculares(listaProy);
				grupo.setAreasSNIES(listaAreas);		
				sesion.setAttribute("grupo", grupo);
				irA="/adminGrupos/VerGrupo.jsp";
			break;
			case Parametros.ActualizaGrupo:
				if(adminGruposDB.ActualizaGrupo((GrupoInvestigacion)sesion.getAttribute("grupo"))){
					listaProy=adminGruposDB.AjaxProyectoCur(grupo.getFacultad());
					grupo.setProyectosCurriculares(listaProy);
					mensaje="El registro fu� modificado Satisfactoriamente";
				}
				else{
					mensaje="El registro no udo ser modificado";
				}
			//	sesion.setAttribute("grupo", adminGruposDB.getVerGrupo(req.getParameter("codigo")));
				irA="/adminGrupos/VerGrupo.jsp";
			break;
			case Parametros.buscaIntegrantesGrupo:
				req.setAttribute("listaIntegrantes",adminGruposDB.buscaIntegrantesGrupo(grupo.getCodigo()));
				//if(sesion.getAttribute("idGrupo")==null)
				//	sesion.setAttribute("idGrupo",req.getParameter("codigo"));
				
				irA="/adminGrupos/adminIntegrantes/ListaIntegrante.jsp";
			break;
			case Parametros.nuevoIntegranteGrupo:
		//		System.out.println("entra al de Germán");
				if(adminGruposDB.insertarPersona((Integrante)sesion.getAttribute("integrante"),grupo.getCodigo()))
					mensaje="El integrante fue insertado correctamente";
				else
					mensaje="El integrante no pudo ser insertado correctamente \n"+adminGruposDB.getMensaje();
				if(grupo!=null){
					req.setAttribute("listaIntegrantes",adminGruposDB.buscaIntegrantesGrupo(grupo.getCodigo()));
				}
				irA="/adminGrupos/adminIntegrantes/ListaIntegrante.jsp";
			break;
			case Parametros.verIntegranteGrupo:
				System.out.println("grupo"+ ((GrupoInvestigacion)sesion.getAttribute("grupo")).getCodigo());
				req.setAttribute("st", ""+Parametros.actualizaIntegranteGrupo);
				Integrante inte=adminGruposDB.verPersona(req.getParameter("id"),String.valueOf(((GrupoInvestigacion)sesion.getAttribute("grupo")).getCodigo()));
				inte.setCodareasnies(inte.getCodareasnies()); //linea nueva
				inte.setAreasSNIES(adminGruposDB.AjaxAreasSnies()); //linea nueva
				sesion.setAttribute("integrante2",inte);
				req.setAttribute("listaCodigos", adminGruposDB.getCodigosIntegrante(req.getParameter("cedula")));
				sesion.setAttribute("flagMod",req.getParameter("flagMod"));
				sesion.removeAttribute("integrante");
				irA="/adminGrupos/adminIntegrantes/Integrante.jsp";
			break;
			case Parametros.actualizaIntegranteGrupo:
				req.setAttribute("st", ""+Parametros.actualizaIntegranteGrupo);
				grupoInvestigacion=(GrupoInvestigacion)sesion.getAttribute("grupo");
				if(adminGruposDB.actualizaIntegranteGrupo((Integrante)sesion.getAttribute("integrante"),grupoInvestigacion.getCodigo())){
					mensaje="El integrante fue actualizado correctamente";
					Integrante antiguo=(Integrante)sesion.getAttribute("integrante2");					
					Integrante nuevo=(Integrante)sesion.getAttribute("integrante");
					listaProy=adminGruposDB.AjaxProyectoCur(nuevo.getCodFacultad());
					listaAreas=adminGruposDB.AjaxAreasSnies(); //nueva
					nuevo.setProyectosCurriculares(listaProy);
					//nuevo.setProyectosCurriculares(listaAreas);
					nuevo.setCodareasnies(nuevo.getCodareasnies()); //nueva
					nuevo.setAreasSNIES(adminGruposDB.AjaxAreasSnies()); //nueva
					sesion.setAttribute("integrante2",nuevo);
					sesion.removeAttribute("integrante");
				}else
					mensaje="El integrante no pudo ser actualizado correctamente";
				req.setAttribute("listaCodigos", adminGruposDB.getCodigosIntegrante(req.getParameter("cedula")));
				irA="/adminGrupos/adminIntegrantes/Integrante.jsp";
				retorno[0]="desviar";
			break;
			case Parametros.IntegranteGrupo:
				req.setAttribute("st", ""+Parametros.nuevoIntegranteGrupo);
				System.out.println("cedula "+req.getParameter("cedula")+" cod "+req.getParameter("codUD")+" id "+req.getParameter("id"));
				int idper=(req.getParameter("id")==null?-1:Integer.parseInt(req.getParameter("id")));
				Integrante inte2=adminGruposDB.consultaDatosPersona(req.getParameter("cedula"),req.getParameter("codUD"),0,idper);
				inte2.setCodareasnies(inte2.getCodareasnies()); //linea nueva
				inte2.setAreasSNIES(adminGruposDB.AjaxAreasSnies()); //linea nueva
				sesion.setAttribute("integrante2",inte2);
				req.setAttribute("listaCodigos", adminGruposDB.getCodigosIntegrante(req.getParameter("cedula")));
				sesion.setAttribute("flagMod",req.getParameter("flagMod"));

				irA="/adminGrupos/adminIntegrantes/Integrante.jsp";

			break;
			case Parametros.eliminaIntegranteGrupo:
				if(adminGruposDB.eliminaIntegranteGrupo((Integrante)sesion.getAttribute("integrante2"),grupo.getCodigo()))
					mensaje="El integrante fue eliminado correctamente";
				else
					mensaje="El integrante no pudo ser eliminado correctamente";
				req.setAttribute("listaIntegrantes",adminGruposDB.buscaIntegrantesGrupo(grupo.getCodigo()));
				irA="/adminGrupos/adminIntegrantes/ListaIntegrante.jsp";
				retorno[0]="desviar";
			break;
			case Parametros.habilitarIntegranteGrupo:
				if(adminGruposDB.habilitaIntegrante((Integrante)sesion.getAttribute("integrante")))
					mensaje="El integrante ha sido vinculado nuevamente al grupo de investigación";
				else
					mensaje="El integrante no pudo ser vinculado nuevamente";
				req.setAttribute("listaIntegrantes",adminGruposDB.buscaIntegrantesGrupo(grupo.getCodigo()));
				irA="/adminGrupos/adminIntegrantes/ListaIntegrante.jsp";
			break;
			case Parametros.buscaPersona:
				req.setAttribute("listaIntegrantes",adminGruposDB.buscaPersona((FiltroPersona)sesion.getAttribute("filtroPersona")));
				irA="/adminGrupos/ListaPersonas.jsp";
				sesion.removeAttribute("filtroPersona");
			break;
			case Parametros.verIntegrante:// por medio de filtro de búsqueda
				req.setAttribute("st", ""+Parametros.actualizaIntegranteGrupo);
				Integrante inte1=adminGruposDB.verPersona(req.getParameter("codigo"),req.getParameter("grupo"));
				inte1.setCodareasnies(inte1.getCodareasnies()); //linea nueva
				inte1.setAreasSNIES(adminGruposDB.AjaxAreasSnies()); //linea nueva
				sesion.removeAttribute("grupo");
				sesion.setAttribute("grupo", adminGruposDB.getVerGrupo(""+inte1.getGrupo()));
				sesion.setAttribute("integrante2",inte1);
				irA="/adminGrupos/adminIntegrantes/Integrante.jsp";
			break;
			case Parametros.claveInvestigador:
		//		System.out.println("entra a case de asignacion " +irA);
				String ID = "";
				ID = completeid((Integrante)sesion.getAttribute("integrante2"));
				
				//System.out.println("\n el ID del investigador es: "+ ID);
				
				GruposGestionDB gruposGestionDB=new GruposGestionDB(cursor,usuario.getPerfil());
				req.setAttribute("st", ""+Parametros.actualizaIntegranteGrupo);
		//		System.out.println("datos enviados son: " +req.getParameter("id")+" --- "+req.getParameter("papel"));
	if(gruposGestionDB.claveInvestigador(req.getParameter("id"),req.getParameter("papel"), ID)){
					mensaje="La clave fue asignada Satisfactoriamente";
				}else{
					mensaje="No se pudo asignar la clave del integrante.. favor volver a intentar";
				}
				irA="/adminGrupos/adminIntegrantes/Integrante.jsp";
		//		System.out.println("Se va del case" +irA);
			break;

			//********************case nuevo gestión integrantes********************************************************
			case Parametros.consultaPersonaOracle:
				//bscar todos cod, cc
				System.out.println("Ingreso con id 20");
				if(req.getParameter("cedula")!=null && req.getParameter("codigoUd")!=null){
					sesion.setAttribute("Integrantes",adminGruposDB.consultarIntegrantes(req.getParameter("cedula"),req.getParameter("codigoUd")));
					irA="/adminGrupos/adminIntegrantes/Integrante.jsp";
				}
							
				irA="/adminGrupos/adminIntegrantes/ListaPersonasOracle.jsp";
				//enviar bandera para saber si estamos en la busqueda de director para grupo nuevo
				req.setAttribute("director", req.getParameter("director"));

			break;
			//***********************************************************************************************************	
			case Parametros.consultarGrupos:
	        	req.setAttribute("listaGrupos", adminGruposDB.getListaGrupos(req.getParameter("facultad"),req.getParameter("tipo"),0,50));
	        	req.setAttribute("grupo","display:block");
	        	irA="/adminGrupos/Documentos.jsp";
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
	
	public String getidname(String name){
		String id_name = "";
		int espacionombre = 0;
			
			while(name.indexOf(" ") != -1){
				espacionombre = name.indexOf(" ");
				id_name = id_name + name.charAt(0);
				//System.out.println("\n el nombre aqui es :"+name+" El id_name es " + id_name);
				name = name.substring(espacionombre+1, name.length());
			}
			id_name = id_name + name.charAt(0);
			//System.out.println("\n el nombre aqui es :"+name+" y el producto: " + id_name);
		
		return id_name; 
	}
	
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
	
	public String completeid (Integrante integ)
	{
		
		String nombre = integ.getNombres();
		String apellido = integ.getApellidos();
		String ID = "";
		String numero = Long.toString(integ.getId());
		
		nombre = nombre.trim();
		nombre = nombre.toLowerCase();
		apellido = apellido.trim();
		apellido = apellido.toLowerCase();
		
		ID = getidname(nombre)+ getidlastname(apellido) + numero;
		
		return ID;
	}
}
