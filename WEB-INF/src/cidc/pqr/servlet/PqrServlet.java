package cidc.pqr.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.pqr.ws_cominicacion.InformacionHistorico;
import cidc.pqr.ws_Bizagi_obj.HistoricoDatos;


import cidc.general.db.CursorDB;
import cidc.general.servlet.ServletGeneral;
import cidc.pqr.ws_Bizagi_obj.Archivo64;
import cidc.pqr.ws_Bizagi_obj.CasoDatos;
import cidc.pqr.ws_Bizagi_obj.ParametrosDatos;
import cidc.pqr.ws_Bizagi_obj.PersonaDatos;
import cidc.pqr.ws_Bizagi_obj.Pqr;
import cidc.pqr.ws_cominicacion.CasoDB_WS;
import cidc.pqr.ws_cominicacion.PersonaDB_WS;

public class PqrServlet extends ServletGeneral{

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		System.out.println("Ingreso al Servlet =========> pqrservlet.x");
		context=config.getServletContext();
		cursor=new CursorDB();
		String irA="";
		int accion=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		HttpSession sesion=req.getSession();
		Pqr pqr = new Pqr();
		pqr=(Pqr)sesion.getAttribute("pqr");
		PersonaDB_WS personaDB_WS = new PersonaDB_WS();
		PersonaDatos personaDatos =new PersonaDatos();
		CasoDB_WS casoDB_WS = new CasoDB_WS();
       	CasoDatos casodatos = new CasoDatos();
       	mensaje="";
		switch (accion) {
		case 1://crear caso
			System.out.println("caso 1");
           	//System.out.println(pqr.getTipoSol());
           	personaDatos.getPersonaID();
			personaDatos=(PersonaDatos)sesion.getAttribute("personaDatos");
           	casodatos.setTipodeSolicitante(pqr.getTipoSolicitante());
          	casodatos.setMedioDeRecepcion(String.valueOf(pqr.getRecepcion()));
          	casodatos.setTipoDeRequerimiento(pqr.getTipoSolicitud());
          	casodatos.setAsunto(pqr.getAsunto());
           	casodatos.setDescripcion(pqr.getDescripcion());
           	casodatos.setEscaladoOtraDependencia(String.valueOf(pqr.getEscalado()));
         	casodatos.setRecibirNotificacionesCorreo(String.valueOf(pqr.getNotificacionCorreo()));
         	casodatos.setArchivoCaso(pqr.getArchivoAdjunto());
         	personaDatos.setProyInv(pqr.getProyInv());
			personaDatos.setCodigo(pqr.getCodigo());
			personaDatos.setFacultad(pqr.getFacultad());
			personaDatos.setTipoInterno(pqr.getTipoInterno());
			personaDatos.setMedioRespuesta(String.valueOf(pqr.getMedioRespuesta()));
			casodatos.setEscaladoOtraDependencia("no");
			personaDatos.setRepresentante(pqr.getRepresentante());
			personaDatos.setContacto(pqr.getContacto());
			casodatos.setArchivoCaso((File)sesion.getAttribute("archivo"));
			casodatos = casoDB_WS.CrearCaso(casodatos, personaDatos);
			if(casodatos.getArchivoCaso()!=null)
				casodatos.getArchivoCaso().delete();
	        System.out.println(""+casodatos.getCasoId());
	        irA="/pqr/registrarPeticion.jsp";
			
        	if(casodatos.getCasoId()==null)
        		mensaje="Ha ocurrio un problema";
        	else{
        		mensaje="Caso se ha creado exitosamente con codigo "+casodatos.getCasoId();
        		sesion.setAttribute("basico", "display:none");
        		sesion.setAttribute("juridico", "display:none");
        		sesion.setAttribute("tipoSolicitante", "display:none");
        		req.setAttribute("crearCaso", "display:none");
        		sesion.removeAttribute("personaDatos");
        		sesion.removeAttribute("archivo");
        	}
			break;
		case 2://crear persona
			System.out.println("caso 2");
			personaDatos.setTitulo(pqr.getTitulo());
			personaDatos.setNombreRazonSocial(pqr.getNombre());
			personaDatos.setTipoDocumento(pqr.getTipoDoc());
			personaDatos.setDocumentoIdNit(pqr.getDocumento());
			personaDatos.setCorreoElectronico(pqr.getCorreo());
			personaDatos.setDireccion(pqr.getDireccion());
			personaDatos.setTelefonoMovil(pqr.getCelular());
			personaDatos.setCiudad(pqr.getCiudad());
			if(!pqr.getTipoInterno().equals("0"))
				personaDatos.setTipoExterno("101");
			else
				personaDatos.setTipoExterno(pqr.getTipoExterno());
			personaDatos.setTipoInterno(pqr.getTipoInterno());
			personaDatos.setCodigo(pqr.getCodigo());
			personaDatos.setFacultad(pqr.getFacultad());
			personaDatos.setProyInv(pqr.getProyInv());			
			/*if(!personaDatos.getTipoInterno().equals("0")){
				personaDatos.setCiudad("2");
				personaDatos.setTipoPersona("51");
			}*/

			String respuesta=personaDB_WS.CrearPersona(personaDatos);
			personaDatos.setPersonaID(pqr.getDocumento());
			personaDatos.setTipoPersona(pqr.getTipoExterno());
			sesion.setAttribute("personaDatos", personaDatos);
			if(respuesta==null)//debe ser ==null
				mensaje="Se ha producido un error en la creación";
			else{
				mensaje="la creacion de la persona ha sido exitosa";
				req.setAttribute("crearCaso", "display:block");
				sesion.setAttribute("basico", "display:block");
			}
			accion=3;
			irA="/pqr/registrarPeticion.jsp";
		case 3: //buscar persona
			personaDatos=personaDB_WS.buscarpersona(pqr.getDocumento());
			personaDatos.setDocumentoIdNit(pqr.getDocumento());
			//codigo pruebas
//				personaDatos.setTipoPersona("1");
//				personaDatos.setTipoInterno("3");
//				personaDatos.setTipoExterno("1");
//				personaDatos.setTipoDocumento("4");
//				personaDatos.setTitulo("2");
			//fin codigo pruebas
			sesion.setAttribute("personaDatos", personaDatos);
			System.out.println();
			if(personaDatos.getPersonaID()==null){// debe ser ==null 
				mensaje="Esta persona NO existe, por favor registre sus DATOS";
				req.setAttribute("crearCaso", "display:none");
				sesion.setAttribute("tipoSolicitante", "display:block");
				req.setAttribute("botonCrear", "display:block");
				//req.setAttribute("crearCaso", "display:none");
				//sesion.setAttribute("tipoSolicitante", "display:none");
			}else{
				sesion.setAttribute("crearCaso", "display:block");
				sesion.setAttribute("basico", "display:block");
				sesion.setAttribute("tipoSolicitante", "display:block");
				if (personaDatos.getTipoPersona().equals("51")||personaDatos.getTipoPersona().equals("52") ) {
					sesion.setAttribute("opcionales", "display:none");
					sesion.setAttribute("juridico", "display:block");
					if(personaDatos.getTipoPersona().equals("52"))
						sesion.setAttribute("complemento", "display:block");
					else
						sesion.setAttribute("complemento", "display:none");
				} else {
					sesion.setAttribute("opcionales", "display:block");
					sesion.setAttribute("juridico", "display:none");
				}
			}
			irA="/pqr/registrarPeticion.jsp";
			break;
		case 4: //consultar caso
			 System.out.println("CONSULTAR CASO PQR");
	         casoDB_WS = new CasoDB_WS();
	         ParametrosDatos parametrosRespuesta;
	         int cedula=Integer.parseInt(req.getParameter("cedula"));
	         parametrosRespuesta = casoDB_WS.consultarCasoPQR(req.getParameter("idCaso"),req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep)));
	         if(parametrosRespuesta.getPersonaDocumentoNIT()!=null && (parametrosRespuesta.getPersonaDocumentoNIT().equals(""+cedula))){
		         InformacionHistorico historico = new InformacionHistorico();
		         List <HistoricoDatos> historicoCaso = historico.consultarHistoricoCaso(req.getParameter("idCaso"),req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep)));    
		         req.setAttribute("respuestaConsul", crearParrafo(parametrosRespuesta, historicoCaso));
		         req.setAttribute("respuesta", "display:block");
		         if(parametrosRespuesta.getArchivoRespuestaDocumento()!=null){
			         Archivo64 arch64=new Archivo64();
			         String datos= parametrosRespuesta.getArchivoRespuestaDocumento();
			         req.setAttribute("archRespuesta",arch64.decodificar(datos, parametrosRespuesta.getArchivoRespuestaNombre(), 
			        		 req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep))));
		         }
		         
	         }else
	        	 mensaje="No se ha encontrado un caso con el Id "+req.getParameter("idCaso")+ " y la cédula "+cedula;
			irA="/pqr/consultarPeticion.jsp";
			break;
		case 5:
			irA="/pqr/registrarPeticion.jsp";
			mensaje="el archivo "+sesion.getAttribute("nombreArc")+" fue cargado exitosamente";
			break;
		default:
			sesion.setAttribute("opcionales", "display:none");
			sesion.setAttribute("basico", "display:none");
			sesion.setAttribute("juridico", "display:none");
			sesion.setAttribute("tipoSolicitante", "display:none");
			req.setAttribute("crearCaso", "display:none");
			req.setAttribute("botonCrear", "display:none");
			sesion.setAttribute("complemento", "display:none");
			sesion.removeAttribute("archivo");
			sesion.removeAttribute("nombreArc");
			sesion.removeAttribute("personaDatos");
			irA="/pqr/registrarPeticion.jsp";
			break;
		}

		accion=0;
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
	
	public String crearParrafo(ParametrosDatos datos, List<HistoricoDatos> historico){
		
		int itamano=historico.size()-1;
		
		String parrafo="";
            System.out.println("Valor del elemento tamano: "+itamano);
            //System.out.println("Valor del elemento parame REQUIREEEE: "+parametrosRespuesta.getFlagSolicitarInfoRequiriente());
                //System.out.println("Valor del elemento histo0 REQUIREEEE: "+historicoCaso.get(itamano).getAccionHistorico());
          
            System.out.println("\nESTADO DEL CASO: \n");
            
            parrafo+="\nESTADO DEL CASO: ";
            if(datos.getCasoAsociado()!=null && historico.size()==0){
     
                parrafo+="REGISTRADO (En proceso de asignación de una persona a su caso). \n";
            }
            else if (historico.size()>0 && (datos.getFlagSolicitarInfoRequiriente().equals("False")||datos.getFlagSolicitarInfoRequiriente().equals("")) 
                    && !(historico.get(itamano).getAccionHistorico().equals("Solución Encontrada" ) || historico.get(itamano).getAccionHistorico().equals("Cancelado" ))){
          
              
                parrafo+="EN ATENCIÓN (Su caso está en proceso de solución).\n";
            }
            else if (historico.size()>0 && datos.getFlagSolicitarInfoRequiriente().equals("True")){
           
                parrafo+="SE SOLICITA AMPLIACIÓN DE INFORMACIÓN (Se ha solicitado a usted información adicional para solucionar su caso, verifique su correo electrónico). \n";
            }
            else if (historico.size()>0 && historico.get(itamano).getAccionHistorico().equals("Cancelado")){
               
                parrafo+="CANCELADO (Su caso ha sido anulado).\n";
            }
            else if (historico.size()>0 && historico.get(itamano).getAccionHistorico().equals("Solución Encontrada")){
            	parrafo+="SOLUCIONADO (Su caso ha sido atendido). \n";
                
            }
            else {System.out.println("Comuniquese con el Centro de Investigaciones para obtener información de su caso - 3239300 ext 1320-1329");}
                                           

  
            if(datos.getCasoAsociado()!=null && historico.size()==0){
            	parrafo+="\n\nSu caso se encuentra registrado y el trascurso del día sera asignado a uno de los funcionarios del Centro de Investigaciones ";
            	
            }else{
	            	if (datos.getEncargadoActualNombre()!=null){
	   				 parrafo+="\nPERSONA ACTUALMENTE RESPONSABLE EN EL CENTRO DE INVESTIGACIONES:\n"+
	   				//"Rol: "+datos.getEncargadoActualRolNombre()+"\n"+
	   				"Nombre :"+datos.getEncargadoActualNombreCompleto()+"\n"+
	   				"Correo electrónico :"+ datos.getEncargadoActualCorreo()+"\n"+
	   				"Telefono :"+datos.getEncargadoActualTel()+"\n\n";
	   				
	   				}
	   				else {
	   					 parrafo+="\nPERSONA ACTUALMENTE RESPONSABLE EN EL CENTRO DE INVESTIGACIONES:\n"+
	   					//"Rol: "+datos.getEncargadoActualRolNombre()+"\n"+
	   					"Nombre :"+datos.getResponsableNombre()+"\n"+
	   					"Correo electrónico :"+ datos.getResponsableCorreo()+"\n"+
	   					"Telefono :"+datos.getResponsableTelefono()+"\n\n";	
	   					
	   					
	   					
	   				}
	   				

	   				parrafo+="Numero de radicado: "+datos.getCasoAsociado()+"\n";
	   				parrafo+="Fecha de radicado: "+datos.getCasoFechaApertura().substring(0, 10)+"\n";
	   				parrafo+="Hora de radicado: "+datos.getCasoFechaApertura().substring(11, 16)+"\n";
	   				parrafo+="Tiempo estimado de respuesta: "+datos.getTipodeRequerimientoTiempoResp()+" dias hábiles a partir de la fecha de radicación. \n\n";
	   				parrafo+="LOS DATOS REGISTRADOS POR USTED EN EL SISTEMA SON LOS SIGUIENTES: \n";
	   				parrafo+="Nombre o Razón Social:"+datos.getPersonaNombreRazon()+". \n";
	   				parrafo+="Teléfono: "+datos.getPersonaTelMov()+". \n";
	   				parrafo+="Correo Electrónico: "+datos.getPersonaCorreo()+" \n\n";
	   				parrafo+="Si requiere actualizar la información indicada, favor envíe un correo electrónico a cidc@udistrital.edu.co indicando en el asunto " +
	   						"\"Actualización datos de contacto\". \n\n\nSU CASO HA SEGUIDO EL SIGUIENTE TRAMITE:\n\n";
	   				
	   				
	   				 for ( itamano=0; itamano<historico.size();itamano++){
	   					 
	   					 parrafo+= "Accion: "+historico.get(itamano).getAccionHistorico()+"\n";
	   					 parrafo+= "Fecha: "+historico.get(itamano).getFechaHistorico().substring(0,10)+"\n";
	   					parrafo+= "Hora: "+historico.get(itamano).getFechaHistorico().substring(11,16)+"\n";
	   					 parrafo+= "Usuario: "+historico.get(itamano).getUsuario()+"\n";
	   					 parrafo+= "\n"; 
	   			    	 
	   			    	 
	   			    }
            	}
		return parrafo;
	}

}
