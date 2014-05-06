package cidc.general.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cidc.general.inicio.CargaInicio;
import cidc.general.inicio.Menu;
import cidc.general.login.Usuario;
import cidc.general.obj.EncriptarURL;
import cidc.general.obj.Parametros;

/**
 * Servlet Class
 *
 * @web.servlet              name="filtro"
 *                           display-name="Name for filtro"
 *                           description="Description for filtro"
 * @web.servlet-mapping      url-pattern="/filtro"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class filtro implements Filter {
	private FilterConfig config=null;
	public ServletContext context=null;
	private Usuario user=null;
	private CargaInicio cargaInicio=null;
	private HttpSession sesion=null;
	private HttpServletRequest req=null;
	public static String home;
	public static String homeUsuario;
	public static String error;
	public static String menuPerfil;
	public static String menu;
	RequestDispatcher rd=null;
	boolean encontrado=false;
	EncriptarURL crypto=new EncriptarURL();
	String encriptado="";
	String irA="";
	Menu listaMenu=null;

	String [] parametros=null;

	public void init(FilterConfig arg0) throws ServletException {
		this.config=arg0;
		this.home=config.getServletContext().getInitParameter("paginaHome");
		this.homeUsuario=config.getServletContext().getInitParameter("paginaHomeUsuario");
		this.error=config.getServletContext().getInitParameter("paginaError");
		this.menuPerfil=config.getServletContext().getInitParameter("paginaMenuPerfil");
		this.menu=config.getServletContext().getInitParameter("paginaMenu");
		System.out.println("Inicio del filtro");



		cargaInicio=new CargaInicio();
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub


		encontrado=false;
		req=(HttpServletRequest)request;
		sesion=req.getSession();
		irA="";
		listaMenu=null;
	/*	System.out.println("*** context path-->"+ req.getContextPath());
		System.out.println("*** AuthType-->"+ req.getAuthType());
		System.out.println("*** LocalName-->"+ req.getLocalName());
		System.out.println("*** PathInfo-->"+ req.getPathInfo());
		System.out.println("*** PathTranslated-->"+ req.getPathTranslated());
		System.out.println("*** QueryString-->"+ req.getQueryString());
		System.out.println("*** RequestedSessionId-->"+ req.getRequestedSessionId());
		System.out.println("*** RequestURI-->"+ req.getRequestURI());
		System.out.println("*** Scheme-->"+ req.getScheme());
		System.out.println("*** ServletPath-->"+ req.getServletPath());*/

		user=(Usuario)sesion.getAttribute("loginUsuario");
		if(user==null){
	//		System.out.println("usuario nulo");
			user=new Usuario();
			user.setPerfilComp(cargaInicio.getPerfil(Parametros.userVisitante));
			user.setIdUsuario(0);
			user.setNick(null);
			user.setNombre(null);
			user.setRecursos(cargaInicio.getMenu("2,0,0",2));
			sesion.setAttribute("loginUsuario",user);
	//		System.out.println("Crea objeto usuario");
		}
	//	System.out.println("existe un usuario ="+user.getPerfil());
	//	System.out.println("cantidad ="+user.getRecursos().size());
	//	recursos=cargaInicio.getRecursosPerfil(user.getIdPerfil());

		encriptado=req.getServletPath();
	//	System.out.println("--dir--->"+encriptado);
		irA=encriptado;
	//	irA=crypto.decrypt(encriptado);
	/*	if(irA.contains(".y?"))
			parametrosUrl=irA.substring(irA.lastIndexOf("?"),irA.length());*/
		if(irA!=null){
			if(irA.contains(".cript")){
				irA=irA.substring(1,irA.length());
				irA="/"+crypto.decrypt(irA);
				if(req.getQueryString()!=null)
					parametros=req.getQueryString().split("&");
				if(parametros!=null)
				for(int i=0;i<parametros.length;i++){
					req.setAttribute(parametros[i].split("=")[0],parametros[i].split("=")[1]);
				}
			}
		}

		if(irA.equals(this.home)||irA.equals(this.homeUsuario)||irA.equals(this.menu)||irA.equals(this.menuPerfil)||irA.equals("/ingresoUsuario.x")||irA.equals("/PaginaNoEncontrada.jsp")||irA.equals("/notificaciones/adminNotificacio.x")||irA.equals("/AyudaTooltip.jsp")){
			//System.out.println("---datos envio-->"+req.getQueryString());
			chain.doFilter(request,response);
			return;
		}

		if(user.getRecursos()!=null){
	//		System.out.println("longitud="+user.getRecursos().size());
			try{
			for(int i=0;i<user.getRecursos().size();i++){
				listaMenu=(Menu)user.getRecursos().get(i);
				if(irA.startsWith(listaMenu.getRecurso())){
					encontrado=true;
					rd=config.getServletContext().getRequestDispatcher(listaMenu.getRecurso());
					rd.include(request,response);
					return;
				}
	//			System.out.println("----"+listaMenu.getRecurso());
			/*	if(irA.equals(listaMenu.getRecurso())){
					encontrado=true;
					System.out.println("Saliendo A --> "+listaMenu.getRecurso());
					chain.doFilter(request,response);
					return;
				}*/
			}
			}catch(Exception e){
				//System.out.println("Sesi�n concluida " + new java.util.Date().toLocaleString());
				e.printStackTrace();
			}
		}

		if(!encontrado){
			System.out.println("********************* Sistema SICIUD -----> recurso: "+irA);
			rd=config.getServletContext().getRequestDispatcher("/PaginaNoEncontrada.jsp");
			rd.forward(request,response);
		}
	}

	public void destroy(){
		// TODO Auto-generated method stub
		config=null;
		System.out.println("***-****************** Sistema SICIUD ---> destruccion del filtro");
		sesion.removeAttribute("loginUsuario");
	}


}
