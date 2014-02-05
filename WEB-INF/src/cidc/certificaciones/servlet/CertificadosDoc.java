package cidc.certificaciones.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.certificaciones.obj.CertificacionesOBJ;
import cidc.certificaciones.obj.Parametros;
import cidc.certificaciones.db.CertificadoDB;

public class CertificadosDoc extends ServletGeneral {
	public CursorDB cursor;
	public static char sep=java.io.File.separatorChar;
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		final int CERTIFICADOESPECIAL=3;
		int perteneciaGrupo = 1;
		int pazSalvo = 2;
		cursor=new CursorDB();
		String irA="";
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		CertificadoDB certifidoDB=new CertificadoDB(cursor,usuario.getPerfil());
		CertificacionesOBJ certificado=(CertificacionesOBJ)sesion.getAttribute("certificado");
		String path=req.getRealPath(req.getContextPath()).substring(0,req.getRealPath(req.getContextPath()).lastIndexOf(sep));
		int caso=0;
		int caso2=0;
		if(req.getAttribute("accion")!=null){
			caso=Integer.parseInt((String)req.getAttribute("accion"));
			caso2=Integer.parseInt((String)req.getAttribute("accion2"));
		}
		else{
			if(sesion.getAttribute("accion")!=null){
				caso=Integer.parseInt((String)sesion.getAttribute("accion"));
				caso2=Integer.parseInt((String)sesion.getAttribute("accion2"));
			}
		//uso esto para el caso en que vaya a imprimir el contrato
			else{
				caso=Integer.parseInt(req.getParameter("accion"));
				caso2=Integer.parseInt(req.getParameter("accion2"));
			}
		}
		 System.out.println("El valor que llega de la acción es"+caso+" caso2 "+caso2);
		
		switch(caso){
			case Parametros.CertPertenencia:			
				System.out.println("Para generar el certificado de pertenencia");
				CertificacionesOBJ cert=certifidoDB.crearcertificado1(certificado, path, resp);
				if(cert!=null){
					sesion.setAttribute("certificado",cert);
					mensaje="Documento creado exitosamente";
					sesion.removeAttribute("listacertificados");
					sesion.setAttribute("listacertificados",certifidoDB.buscarCertificadosPersona(caso2,perteneciaGrupo));
				}else
					mensaje="No se ha podido crear el certificado";
				sesion.removeAttribute("accion");
				irA="/Certificados/GenerarCertificado.jsp";
			break;
			case Parametros.CertPazSalvo:
				System.out.println("Para generar el certificado de paz y salvo");
				sesion.setAttribute("certificado",certifidoDB.certificadoPazSalvo(certificado, path, resp));
				irA="/Certificados/PazySalvo.jsp";
				mensaje="Documento creado exitosamente";
				sesion.removeAttribute("accion");
				sesion.removeAttribute("listacertificados");
				sesion.setAttribute("listacertificados",certifidoDB.buscarCertificadosPersona(caso2,pazSalvo));
			break;
//			case Parametros.CREARCERTACTIVIDADES:
//				System.out.println("entra caso 9");
//				irA="/Certificados/Actividades.jsp";
//				mensaje="Documento creado exitosamente";
//				sesion.removeAttribute("accion");
//				sesion.removeAttribute("listacertificados");
//				sesion.setAttribute("listacertificados",certifidoDB.buscarCertificadosPersona(caso2,pazSalvo));
			case Parametros.CREARCERTESPECIAL:
				certificado.setIdPersona((int)usuario.getIdUsuario());
				certifidoDB.certificadoEspecial(certificado, path, resp);
				sesion.setAttribute("listaEspeciales", certifidoDB.buscarCertificadosEspeciales(CERTIFICADOESPECIAL));
				mensaje="Certificado creado exitosamente";
				irA="/Certificados/Especial.jsp";
				break;
		}
				
		caso=0;
		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;		
		
	}

}
