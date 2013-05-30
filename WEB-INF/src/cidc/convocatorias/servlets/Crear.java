package cidc.convocatorias.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.convocatorias.db.ConvocatoriasDB;
import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.convocatorias.obj.ParametrosOBJ;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class Crear extends ServletGeneral {
		
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		context=config.getServletContext();
		cursor=new CursorDB();
		String irA="/Convocatoria/Convocatoria.jsp";
		String mensaje="";
		int accion=Integer.parseInt(req.getParameter("accion"));
		System.out.println("accion:" +accion);
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ConvocatoriasDB convocatoriasDB=new ConvocatoriasDB(cursor,usuario.getPerfil());
		ConvocatoriaOBJ convocatoriaOBJ=(ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ");
		switch(accion){
		case ParametrosOBJ.Guardar:
			System.out.println("entra al case de insertar");
			irA="/Convocatoria/Parametrizar.jsp";
				if(convocatoriaOBJ!=null){
					if(convocatoriasDB.insertaConvocatoria(convocatoriaOBJ)){
						mensaje="Convocatoria Insertada";
					}else{
						mensaje="La convocatoria no pudo ser insertada \n"+convocatoriasDB.getMensaje();
						irA="/Convocatoria/Convocatoria.jsp";
					}
				}
//
		break;
                        case ParametrosOBJ.GuardarReq:
                                System.out.println("entra al guardarReq");
                                if(sesion.getAttribute("convocatoriaOBJ")!=null){
                                if(convocatoriasDB.nuevoRequisitoDoc((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Registro insertado Correctamente";
                                else
                                        mensaje="El registro no puede ser insertado";
                }
                        irA="/Convocatoria/RequisitosDoc.jsp";
                        break;

                        case ParametrosOBJ.GuardarRub:
                                System.out.println("entra al guardarRub");
                                if(sesion.getAttribute("convocatoriaOBJ")!=null){
                                if(convocatoriasDB.nuevoRubro((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Registro insertado Correctamente";
                                else
                                        mensaje="El registro no puede ser insertado";
                }
                        irA="/Convocatoria/AdmRubros.jsp";
                        break;
//

                        case ParametrosOBJ.GuardarCom:
                                System.out.println("entra al guardarRub");
                                if(sesion.getAttribute("convocatoriaOBJ")!=null){
                                if(convocatoriasDB.nuevoCompromiso((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Registro insertado Correctamente";
                                else
                                        mensaje="El registro no puede ser insertado";
                }
                        irA="/Convocatoria/AdmCompromisos.jsp";
                        break;

                        case ParametrosOBJ.GuardarCrit:
                                System.out.println("entra al guardarCrit");
                                if(sesion.getAttribute("convocatoriaOBJ")!=null){
                                if(convocatoriasDB.nuevoCriterio((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Registro insertado Correctamente";
                                else
                                        mensaje="El registro no puede ser insertado";
                }
                        irA="/Convocatoria/AdmCritAsp.jsp";
                        break;
                        case ParametrosOBJ.GuardarAsp:
                                System.out.println("entra al guardarAsp");
                                if(sesion.getAttribute("convocatoriaOBJ")!=null){
                                if(convocatoriasDB.nuevoAspecto((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Registro insertado Correctamente";
                                else
                                        mensaje="El registro no puede ser insertado";
                }
                        irA="/Convocatoria/AdmCritAsp.jsp";
                        break;


//
                        case ParametrosOBJ.GuardarEje:
                                System.out.println("entra al guardarEje");
                                if(sesion.getAttribute("convocatoriaOBJ")!=null){
                                if(convocatoriasDB.nuevoEje((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Registro insertado Correctamente";
                                else
                                        mensaje="El registro no puede ser insertado";
                }
                        irA="/Convocatoria/AdmEjes.jsp";
                        break;

                        case ParametrosOBJ.ModificarReq:
                        irA="/Convocatoria/RequisitosDoc.jsp";
                                System.out.println("entra al modificarReq de Crear.java");
			         if(convocatoriasDB.modificaRequisitoDoc((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Documento Actualizado Correctamente";
                                else
                                        mensaje="El Documento no pude ser actualizado";

                                buscar(req,resp,sesion,usuario,convocatoriasDB);
                                sesion.removeAttribute("convocatoriaOBJ");
                        break;
                        
			case ParametrosOBJ.ModificarRub:
                        irA="/Convocatoria/AdmRubros.jsp";
			         if(convocatoriasDB.modificaRubro((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Documento Actualizado Correctamente";
                                else
                                        mensaje="El Documento no pude ser actualizado";

                                buscarRubro(req,resp,sesion,usuario,convocatoriasDB);
                                sesion.removeAttribute("convocatoriaOBJ");
                        break;
//

			case ParametrosOBJ.ModificarCom:
                        irA="/Convocatoria/AdmCompromisos.jsp";
			         if(convocatoriasDB.modificaCompromiso((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Documento Actualizado Correctamente";
                                else
                                        mensaje="El Documento no pude ser actualizado";

                                buscarCompromiso(req,resp,sesion,usuario,convocatoriasDB);
                                sesion.removeAttribute("convocatoriaOBJ");
                        break;

                        case ParametrosOBJ.ModificarCrit:
                        irA="/Convocatoria/AdmCritAsp.jsp";
                                 if(convocatoriasDB.modificaCriterio((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Criterio Actualizado Correctamente";
                                else
                                        mensaje="El Criterio no pude ser actualizado";

                                buscarCriterio(req,resp,sesion,usuario,convocatoriasDB);
                                sesion.removeAttribute("convocatoriaOBJ");
                        break;
                        case ParametrosOBJ.ModificarAsp:
                        irA="/Convocatoria/AdmCritAsp.jsp";
                                 if(convocatoriasDB.modificaAspecto((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Aspecto Actualizado Correctamente";
                                else
                                        mensaje="El Aspecto no pude ser actualizado";

                                buscarAspecto(req,resp,sesion,usuario,convocatoriasDB);
                                sesion.removeAttribute("convocatoriaOBJ");
                        break;


                        case ParametrosOBJ.ModificarEje:
                        irA="/Convocatoria/AdmEjes.jsp";
                                 if(convocatoriasDB.modificaEje((ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ")))
                                        mensaje="Documento Actualizado Correctamente";
                                else
                                        mensaje="El Documento no pude ser actualizado";

                                buscarEje(req,resp,sesion,usuario,convocatoriasDB);
                                sesion.removeAttribute("convocatoriaOBJ");
                        break;
//

		case ParametrosOBJ.modificar:
			irA="/Convocatoria/ModificarConv.jsp";
				if(convocatoriaOBJ!=null){
					if(convocatoriasDB.modificarConvocatoria(convocatoriaOBJ)){	
						mensaje="Convocatoria Modificada";
					}else{
						mensaje="La convocatoria no pudo ser modificada  \n"+convocatoriasDB.getMensaje();
					}
				}
		break;
		}
		
		req.removeAttribute("accion");
		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
void buscar(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datReq=convocatoriasDB.getRequisito(Integer.parseInt(""+req.getParameter("reqId")));
                if(datReq!=null){
                        req.setAttribute("convocatoriaOBJ",datReq);
                }
        }
void buscarRubro(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datRub=convocatoriasDB.getRubro(Integer.parseInt(""+req.getParameter("rubId")));
                if(datRub!=null){
                        req.setAttribute("convocatoriaOBJ",datRub);
                }
        }
void buscarCompromiso(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datCom=convocatoriasDB.getCompromiso(Integer.parseInt(""+req.getParameter("comId")));
                if(datCom!=null){
                        req.setAttribute("convocatoriaOBJ",datCom);
                }
        }

void buscarCriterio(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datCrit=convocatoriasDB.getCriterio(Integer.parseInt(""+req.getParameter("critId")));
                if(datCrit!=null){
                        req.setAttribute("convocatoriaOBJ",datCrit);
                }
        }

void buscarAspecto(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datAsp=convocatoriasDB.getAspecto(Integer.parseInt(""+req.getParameter("aspId")));
                if(datAsp!=null){
                        req.setAttribute("convocatoriaOBJ",datAsp);
                }
        }


void buscarEje(HttpServletRequest req, HttpServletResponse resp, HttpSession sesion, Usuario usuario, ConvocatoriasDB convocatoriasDB)throws ServletException, IOException {
                ConvocatoriaOBJ datEje=convocatoriasDB.getEje(Integer.parseInt(""+req.getParameter("ejeId")));
                if(datEje!=null){
                        req.setAttribute("convocatoriaOBJ",datEje);
                }
        }

}
