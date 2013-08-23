package cidc.general.servlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletConfig;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.Paginacion;
/**
 * Servlet Class
 * @web.servlet              name="ServletGeneral"
 *                           display-name="Name for ServletGeneral"
 *                           description="Description for ServletGeneral"
 * @web.servlet-mapping      url-pattern="/ServletGeneral"
 * @web.servlet-init-param   name="A parameter"
 *                           value="A value"
 */
public class ServletGeneral extends HttpServlet {

	public ServletContext context=null;
	public ServletConfig config=null;
	public static String home;
	public static String homeUsuario;
	public static String error;
	public static String menu;
/*	public static String administrador;
	public static String director;
	public static String funcionario;
	public static String evaluador;
	public static String investigador;*/
	public RequestDispatcher rd=null;
	public String [] retorno=new String [3];
	public CursorDB cursor;
	public BaseDB baseDB;
	public String mensaje;
	public static char sep=java.io.File.separatorChar;
	
	public int totalRegistros;
	public int nroPaginas;
	public int cantRegistrosMostrar=10;
	public int sets=10;
	public int desde=1;
	public int inicio=10;
	public int fin=1;

	public void init(ServletConfig config) throws ServletException {
		this.config=config;
		this.context=config.getServletContext();
		this.home=context.getInitParameter("paginaHome");
		this.homeUsuario=context.getInitParameter("paginaHomeUsuario");
		this.error=context.getInitParameter("paginaError");
		this.menu=context.getInitParameter("paginaMenuPerfil");
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException,
		IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException,
	IOException {
	// TODO Auto-generated method stub
	String[] retorno;
	try {
		retorno = operaciones(req, resp);
		if(retorno!=null){
			  if(retorno[0].equals("unir")){
				//  System.out.println("une con "+retorno[1]);
				  rd=context.getRequestDispatcher(retorno[1]);
				  req.setAttribute("msg",retorno[2]);
				  rd.include(req,resp);
			  }else{
				  rd=context.getRequestDispatcher(retorno[1]);
				  req.setAttribute("msg",retorno[2]);
				  rd.forward(req,resp);
			  }
		  }
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
//	System.out.println("termina");
	}
	
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException, ClassNotFoundException {
		return null;
	}
	
	public Paginacion getPaginacion(int cantidadRegistros,String regporpag,String desde){
		Paginacion paginacion=new Paginacion();
		
		paginacion.setTotalRegistros(cantidadRegistros);
		
		if(regporpag==null)
			paginacion.setRegPorPag(cantidadRegistros);
		else{
			if(!regporpag.equals("0"))
				paginacion.setRegPorPag(Integer.parseInt(regporpag));
			else
				paginacion.setRegPorPag(cantidadRegistros);
		}
		
		if(desde !=null){
			paginacion.setDesde(Integer.parseInt(desde));
		}else
			paginacion.setDesde(1);
				
		paginacion.setTotalRegistros(cantidadRegistros);
		paginacion.setOffset((paginacion.getDesde() * paginacion.getRegPorPag())-paginacion.getRegPorPag());
		paginacion.setCantPaginas(nroPaginas(cantidadRegistros,paginacion.getRegPorPag()));
		paginacion.setInicio(inicioPaginacion(paginacion.getCantPaginas(),paginacion.getDesde(),paginacion.getCantSet()));
		paginacion.setFin(finPaginacion(paginacion.getCantPaginas(),paginacion.getDesde(),paginacion.getCantSet()));
		
		return paginacion;
	}
	
	
	
	public int inicioPaginacion(int nropaginas, int pagSeleccionada, int nrosets)
	{
		int inicio=0;
		if(nropaginas<nrosets){
			inicio=1;
		}
		else{
			if(pagSeleccionada >=(nrosets -3)){
				inicio=pagSeleccionada -5;
			}
			if(pagSeleccionada <(nrosets -3)){
				inicio=1;
			}
			if(pagSeleccionada >(nropaginas -4))
			{
				inicio=pagSeleccionada -5;
			}
		}
		return  inicio;
	}

	public int finPaginacion(int nropaginas, int pagSeleccionada, int nrosets)
	{
		//System.out.println("--getFin--->"+nropaginas+"  "+pagSeleccionada+"  "+nrosets);
		int fin=0;
		if(nropaginas<nrosets){
			fin=nropaginas;
		}
		else{
			if(pagSeleccionada >=(nrosets -3)){
				fin=pagSeleccionada +5;
			}
			if(pagSeleccionada <(nrosets -3)){
				fin=10;
			}
			if(pagSeleccionada >=(nropaginas -4)){
				fin=nropaginas;
			}
		}
		return  fin;
	}

	public int nroPaginas(int totalRegistros, int sets){
		int nroPaginas=0;
		if(totalRegistros!=0){
			if(totalRegistros % sets ==0){
				nroPaginas=totalRegistros / sets;
			}
			if(totalRegistros % sets !=0){
				nroPaginas=(totalRegistros / sets)+1;
			}
		}
		return nroPaginas;
	}
	
	
}
