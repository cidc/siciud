package cidc.proyectos.servlet;

import java.io.File;
import java.io.IOException;
import java.net.Authenticator;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.displaytag.util.ParamEncoder;

import cidc.bizagi.soa.EntityManagerSOAImpl;
import cidc.bizagi.soa.EntityManagerSOAImplService;

import cidc.bizagi.soa.WorkflowEngineSOA;
import cidc.bizagi.soa.WorkflowEngineSOAImplService;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.obj.Globales;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectos.db.ProyectosInvestigadorDB;
import cidc.proyectos.obj.BalanceGeneral;
import cidc.proyectos.obj.Contratacion;
import cidc.proyectos.obj.Parametros;
import cidc.proyectos.obj.ProyectoGenerico;
import cidc.proyectos.obj.Rubros;
//import cidc.proyectosGeneral.ProyectosXml.ProyectoXML;
import cidc.proyectosGeneral.db.ProyectosGeneralDB;
import cidc.proyectosGeneral.obj.ExtraDocProyecto;
import cidc.proyectosGeneral.obj.Proyecto;



public class ProyectosInvestigadores extends ServletGeneral {

	       Usuario usuario = null;

	@SuppressWarnings("deprecation")
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/grupos/proyectos/ListaProyectos.jsp";
		cursor=new CursorDB();
		int accion=0;
		HttpSession sesion=req.getSession();
		usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ProyectosInvestigadorDB proyectosDB=new ProyectosInvestigadorDB(cursor,usuario.getPerfil());
		ProyectosGeneralDB proyGeneral=new ProyectosGeneralDB(cursor, usuario.getPerfil());
		ProyectoGenerico proyecto =null;
		final int PRORROGA=4;
		String path=super.context.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
		if(sesion.getAttribute("proyectoInvestigador")!=null)
			proyecto = (ProyectoGenerico)sesion.getAttribute("proyectoInvestigador");
		if(req.getParameter("accion")!=null)
			accion = Integer.parseInt(req.getParameter("accion"));
		mensaje="";
		switch(accion){
			case Parametros.cmdVerProyecto:
				ProyectoGenerico proyectoGen=proyectosDB.getProyecto(req.getParameter("id"),req.getParameter("tipo"));
				proyectoGen=obtenerFechaFin(proyectoGen);
				sesion.setAttribute("proyectoInvestigador", proyectoGen);
				//ProyectoXML proy=new ProyectoXML();
				//String resp2=proy.crearProyectoBizagi(proyectoGen);
				//System.out.println("resp crear proy "+resp2);
				sesion.setAttribute("proyectoDocumentos", proyGeneral.getListaDocAnexos(Long.parseLong(req.getParameter("id")),Integer.parseInt(req.getParameter("tipo"))));
				//sesion.setAttribute("proyectoInvestigador", proyectosDB.getProyecto(req.getParameter("id"),req.getParameter("tipo")));
				irA="/grupos/proyectos/VerProyecto.jsp";
			break;
			case Parametros.cmdBalanceGral:
				sesion.setAttribute("balanceProyecto",proyectosDB.getBalanceProyecto(""+proyecto.getIdProyecto(),""+proyecto.getTipo()));
				irA="/grupos/proyectos/BalanceGeneral.jsp";
			break;
			case Parametros.cmdListaGastosRubro:
				sesion.removeAttribute("tipoPersona");
				sesion.removeAttribute("tipoContratacion");
				sesion.removeAttribute("idContrato");
				List registroGasto = null;
				BalanceGeneral balanc =(BalanceGeneral)sesion.getAttribute("balanceProyecto");
				List<Rubros> lista= balanc.getListaRubros();
				for (Rubros rubro : lista) {
					if((rubro.getIdRubro()==Integer.parseInt(req.getParameter("idRub")))&&(rubro.getNombreRubro().equals("Personal"))){
						registroGasto=lista;
					}
				}
				sesion.setAttribute("gasto", registroGasto);
				sesion.setAttribute("idRub",req.getParameter("idRub"));
				sesion.setAttribute("listaGastosRubro",proyectosDB.getGastosRubrosDeLista((BalanceGeneral)sesion.getAttribute("balanceProyecto"),req.getParameter("idRub")));
				req.setAttribute("natural", "display:none");
				req.setAttribute("juridica", "display:none");
				req.setAttribute("basico", "display:none");
				sesion.setAttribute("adjuntos", "display:none");
				sesion.setAttribute("lista", "display:none");
				sesion.setAttribute("formulario", "display:block");
				sesion.removeAttribute("contratacion");
				irA="/grupos/proyectos/ListaGastos.jsp";
			break;
			case Parametros.ajaxTipoPersona:
				Contratacion cont=(Contratacion)sesion.getAttribute("contratacion");
				sesion.setAttribute("adjuntos", "display:none");
				sesion.setAttribute("lista", "display:block");
				sesion.setAttribute("formulario", "display:none");
				irA="/grupos/proyectos/ListaGastos.jsp";
				break;
			case Parametros.tipoContrato:
//				cont=(Contratacion)sesion.getAttribute("contratacion");
//				CargarDocumento carg= new CargarDocumento();
//				Date date = new Date();
//				String nombre =String.valueOf(date.getTime());
//				carg.cargar(req, nombre, "Bizagi");
//				irA="/grupos/proyectos/ListaGastos.jsp";
				break;
			case Parametros.infoRubroSolicitado:
				System.out.println("ingreso al servlet");
				irA="/grupos/proyectos/InfoSolicitud.jsp";
				break;
			case Parametros.MODIFICACIONRUBRO:
				//valida que el estado del proyecto sea vigente (quitar la negacion)
				if(proyecto.getEstado().equals("Vigente")){
					req.setAttribute("estado", true);
					req.setAttribute("visible", false);
					usuario=proyectosDB.consultaDatosPersonales(usuario);
					sesion.setAttribute("loginUsuario", usuario);
				}else
					req.setAttribute("estado", false);
					
				irA="/grupos/proyectos/SolModRub.jsp";
				break;
			case Parametros.ACTUALIZARDATOS:
				Usuario usuario2=(Usuario) sesion.getAttribute("usuarioT");
				int tipoMod=Integer.parseInt(req.getParameter("tipoModificacion"));
				usuario.setMail(usuario2.getMail());
				usuario.setCelular(usuario2.getCelular());
				usuario.setDireccion(usuario2.getDireccion());
				if(proyectosDB.actualizarDatos(usuario)){
					usuario=proyectosDB.consultaDatosPersonales(usuario);
					sesion.setAttribute("loginUsuario", usuario);
					Date fechaMin=null;
					Date fechaMax=null;
					boolean retorno=false;
					Globales glob= new Globales(); 
					try {
						if(proyecto.getTipo()==2)
							proyecto.setFecActaInicio(glob.cambiarFormatoFecha(proyecto.getFecActaInicio()));
						SimpleDateFormat formato= new SimpleDateFormat("yyyy-MM-dd");
						Date fechaCierre=formato.parse(proyecto.getFechaEstimadaFin());
						Date fechaInicio=formato.parse(proyecto.getFecActaInicio());
						mensaje="Datos Actualizados Correctamente";
						switch(tipoMod){
							case PRORROGA:
								fechaMin=glob.sumarRestarDiasFecha(fechaCierre, -60);
								fechaMax=glob.sumarRestarDiasFecha(fechaCierre, -30);
								if(glob.entreFechas(fechaMin, fechaMax, new Date())){
									retorno=true;
								}
								break;
							default:
								fechaMax=glob.sumarRestarDiasFecha(fechaCierre, -30);
								if(glob.entreFechas(fechaInicio, fechaMax, new Date())){
									retorno=true;
								}
						}
					mensaje=(retorno)?"Datos Actualizados Correctamente":"El proyecto no se encuentra en los tiempos establecidos para hacer esta solicitud";
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}else
					mensaje="Ha ocurrido un error, por favor intente de nuevo";
				irA="/grupos/proyectos/SolModRub.jsp";
				break;
			case Parametros.ENVIODATOSWEBSERVICE:
				System.out.println(req.getParameter("nomArchivo"));
				try {
					//System.out.println(tipoMod); esto hay que pasarlo a palabras
					//este bloque es para probar la conexion del sevicio web usando las entidades
					/*String inputXML="<?xml version=\"1.0\" encoding=\"utf-8\"?><xs:schema attributeFormDefault=\"qualified\" elementFormDefault=\"qualified\" xmlns:xs=\"http://www.w3.org/2001/XMLSchema\"><xs:element name=\"App\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"SolicituddeAccionesCiuda\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TipodeRequerimiento\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Responsable\"><xs:complexType><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ProyectodeInvestigacion\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Codigo\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ProyectodeInvestigacion\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Facultad\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Asunto\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Estado\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Codigo\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Estado\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ArchivosdelCaso\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Usuario\"><xs:complexType><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CierredelCaso\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"LeccionesAprendidas\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ArchivodeMejora\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ArchivodeRespuesta\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Mejora\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Respuesta\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"MediodeNotificaciondeCierr\"><xs:complexType><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FechadeSoluciondelCaso\" type=\"xs:dateTime\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RazonesdeCierredelCaso\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ResponsabledeMejora\" type=\"xs:integer\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"DetallesdeDocumentacion\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"GestiondeNotificaciones\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EnviarA\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"EmaildeDestino\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Remitente\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"MediodeNotificacion\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Medio\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Codigo\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RazonesdeExtensiondelCaso\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"DependenciaSolicitante\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Codigo\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Ext\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CorreoElectronicoDependenc\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Dependencia\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"JefeDependencia\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FlagsdelCaso\"><xs:complexType><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Descripcion\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FechadeApertura\" type=\"xs:dateTime\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Persona\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Cargo\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"DocumentodeIdentidadNIT\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Ciudad\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TelefonoMovil\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TipodeDocumento\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"CorreoElectronico\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TipodePersona\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Titulo\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Codigo\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"NombreRazonSocial\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Contacto\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Extension\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RepresentanteLega\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Titulo\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Direccion\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Dependencia\" type=\"xs:integer\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"MediodeRecepcion\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Medio\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Codigo\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Entradas\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ComentariosdeEscalamiento\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"SoluciondelCaso\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Adjuntos\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"ComentariosInformacion\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Comentarios\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"AsignadoOriginal\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"LlamardeNuevo\" type=\"xs:dateTime\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FechaOriginaldeSolicitud\" type=\"xs:dateTime\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Area\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Accion\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FechaMaximaExtension\" type=\"xs:dateTime\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"Dependencia\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"locDisplayName\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"locName\" type=\"xs:string\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"idWorkingTimeSchema\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"idLocation\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"costLocation\" type=\"xs:decimal\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"idTimeZone\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"idParentLocation\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"idOrg\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"locDescription\" type=\"xs:string\" /></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"RespuestadelCliente\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TipodeSolicitanteInterno\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"FechaEstimadadeCierre\" type=\"xs:dateTime\" /><xs:element minOccurs=\"0\" maxOccurs=\"1\" name=\"TipodeSolicitante\" type=\"xs:integer\" /><xs:element minOccurs=\"0\" maxOccurs=\"unbounded\" name=\"SolicitudesaOtrasDependenc\"><xs:complexType><xs:sequence><xs:element minOccurs=\"0\" maxOccurs=\"unbounded\" name=\"SolicitudesaOtrasDepende\"><xs:complexType/></xs:element></xs:sequence></xs:complexType></xs:element></xs:sequence><xs:attribute form=\"unqualified\" name=\"entityName\" type=\"xs:string\" /></xs:complexType></xs:element></xs:sequence></xs:complexType></xs:element></xs:schema>";
					EntityManagerSOAImplService pruebaservicio = new EntityManagerSOAImplService();
					EntityManagerSOAImpl calc = pruebaservicio.getEntityManagerSOAImplPort();
					System.out.println(calc.getCaseDataUsingSchemaAsString(5091, inputXML));*/
					
					String xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:soa=\"http://SOA.BizAgi/\"><soapenv:Header/>   <soapenv:Body><soa:createCasesAsString><!--Optional:-->         <arg0><![CDATA[<BizAgiWSParam><domain>domain</domain><userName>admon</userName><Cases><Case><Process>ModificacionRubros</Process><Entities><ModificacionRubros><Persona businessKey=\"DocumentodeIdentidadNIT=90100350369\"><DocumentodeIdentidadNIT>90100350369</DocumentodeIdentidadNIT><CorreoElectronico>faloCe23@gmail.com</CorreoElectronico><NombreRazonSocial>chapn colorado23</NombreRazonSocial></Persona><Proyecto businessKey=\"CodigodelProyecto='2-2-2-2'\"><NombredelProyecto>proyecto falso 13</NombredelProyecto><CodigodelProyecto>2-2-2-2</CodigodelProyecto></Proyecto><TipoRequerimiento businessKey=\"ModificacionRubros='Prórroga'\"/></ModificacionRubros></Entities></Case></Cases></BizAgiWSParam>]]></arg0>      </soa:createCasesAsString>   </soapenv:Body></soapenv:Envelope>";
					String nombreArchivo=path+"/Documentos/Bizagi/"+req.getParameter("nomArchivo");
					File base64= new File(nombreArchivo);
					Globales glob= new Globales();
					String archivoBase64 = glob.convertirBase64(base64);
//					WorkflowEngineSOAImplService wfs = new WorkflowEngineSOAImplService();
//					WorkflowEngineSOA wfe = wfs.getWorkflowEngineSOAImplPort();
//					String respWS =null;
//					if(!(respWS= wfe.createCasesAsString(xml)).equals(null)){
						req.setAttribute("visible", true);
						req.setAttribute("estado", true);
						ProyectosInvestigadorDB proyDB=new ProyectosInvestigadorDB(cursor,usuario.getPerfil());
						proyDB.guardarSolicitudBPM(usuario, proyecto, String.valueOf(sesion.getAttribute("TipoModificacion")), nombreArchivo);
						sesion.removeAttribute("TipoModificacion");
//						System.out.println(respWS);
//					}
					irA = "/grupos/proyectos/SolModRub.jsp";
				} catch (Exception e) {
					e.printStackTrace();
				}
				mensaje="";
				break;
			default:
				req.setAttribute("listaProyectos", proyectosDB.getListaProyectos(usuario.getIdUsuario()));
			irA="/grupos/proyectos/ListaProyectos.jsp";
			break;
		}

		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
		}
	
	public ExtraDocProyecto obtenerDatos(HttpServletRequest req, ExtraDocProyecto docNuevo){
		List items=new ArrayList();
		try {
			DiskFileUpload fu = new DiskFileUpload();
			items = fu.parseRequest(req);
	        FileItem item=null;
		        Iterator iter = items.iterator();
		        while (iter.hasNext()) {
		            item = (FileItem) iter.next();
		            if (item.isFormField()) {
			            	if(item.getFieldName().equals("tipo")){
			            		docNuevo.setTipo(Integer.parseInt(item.getString()));
			            	}
			            	if(item.getFieldName().equals("observaciones"))
			            		docNuevo.setObservaciones(item.getString());
		            }
		        
		        }
	        }catch (Exception e) {
	        	baseDB.lanzaExcepcion(e);
	        }
		return docNuevo;
	}
	/**
	 * se encarga de hacer el mapeo entre clases para invocar al metodo que retorna la fecha de cierre del proyecto
	 * @param proyectoGen
	 */
	public ProyectoGenerico obtenerFechaFin(ProyectoGenerico proyectoGen){
		ProyectosGeneralDB pGDB= new ProyectosGeneralDB(cursor,usuario.getPerfil());
		Proyecto proyecto=new Proyecto();
		proyecto.setId((int) proyectoGen.getIdProyecto());
		proyecto.setDuracion(proyectoGen.getDuracion());
		proyecto.setFecAprobacion(proyectoGen.getFecAprobacion());
		proyecto.setTipoProyecto(proyectoGen.getTipo());
		proyecto.setClaseProyecto(proyectoGen.getTipo());
		proyectoGen.setFechaEstimadaFin(pGDB.getListaTiempos(proyecto,0));
		return proyectoGen;
	}
}

