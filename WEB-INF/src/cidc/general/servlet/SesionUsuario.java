package cidc.general.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.adminGrupos.db.AdminGruposDB;
import cidc.general.db.CursorDB;
import cidc.general.db.UsuarioDB;
import cidc.general.inicio.CargaInicio;
import cidc.general.login.Usuario;
import cidc.general.obj.Parametros;


/**
 * Servlet Class
 *
 * @web.servlet              name="SesionUsuario"
 *                           display-name="Name for SesionUsuario"
 *                           description="Description for SesionUsuario"
 * @web.servlet-mapping      url-pattern="/SesionUsuario"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class SesionUsuario extends ServletGeneral {
	public CursorDB cursor;
	String [] retorno=new String [3];
	public ServletContext context=null;
	public ServletConfig config=null;
	public static String home;
	public static String error;
	public static String login;
	public RequestDispatcher rd=null;
	public AdminGruposDB adminGruposDB=null;

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		Usuario user=null;
		UsuarioDB usuarioDB=new UsuarioDB(cursor,Parametros.userVisitante);
		int accion=-1;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		String nick=req.getParameter("usuario");
		String clave=req.getParameter("clave");
		mensaje=null;
		retorno[0]="desviar";
		switch (accion) {
			case 1:
				if(clave!=null)
					user=usuarioDB.consultar(nick,clave);

				if(user!=null){
					sesion.setAttribute("loginUsuario",user);
		//			System.out.println("--perfiles--->"+user.getPerfilComp());
					if(user.isPerfil("8")||user.isPerfil("10")){
						adminGruposDB=new AdminGruposDB(cursor,user.getPerfil());
						sesion.setAttribute("listaMisGrupos", adminGruposDB.getGruposPersona(user.getIdUsuario()));
					}
						user.setPagina(super.homeUsuario);
					super.mensaje=usuarioDB.getMensaje();
				}else{
					super.mensaje="Usuario o Clave no son correctos";
				}
			break;
			case 0:
				CargaInicio cargaInicio=new CargaInicio();
				user=(Usuario)sesion.getAttribute("loginUsuario");
				user.setRecursos(null);
				user.setPerfilComp(cargaInicio.getPerfil(Parametros.userVisitante));
				user.setIdUsuario(0);
				user.setRecursos(cargaInicio.getMenu("2,0,0",2));
				user.setNick(null);
				user.setNombre(null);
				user.setPagina(super.home);
				sesion.setAttribute("loginUsuario",user);
				super.mensaje="Sesion Terminada";
			//	System.out.println("termina sesion y desvia para= "+super.menu);
			//	System.out.println("cantidad de recursos= "+user.getRecursos().size());
			break;
		}
		retorno[2]=super.mensaje;
		retorno[1]=super.menu;
		return retorno;
	}
}



