package cidc.proyectos.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectos.db.ProyectosDB;
import cidc.proyectos.db.ProyectosInvestigadorDB;
import cidc.proyectos.obj.BalanceGeneral;
import cidc.proyectos.obj.Contratacion;
import cidc.proyectos.obj.Proyecto;
import cidc.proyectos.obj.Parametros;
import cidc.proyectos.obj.ProyectoGenerico;
import cidc.proyectos.obj.Rubros;


public class ProyectosInvestigadores extends ServletGeneral {

	       Usuario usuario = null;

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/grupos/proyectos/ListaProyectos.jsp";
		cursor=new CursorDB();
		int accion=0;
		HttpSession sesion=req.getSession();
		usuario=(Usuario)sesion.getAttribute("loginUsuario");
		ProyectosInvestigadorDB proyectosDB=new ProyectosInvestigadorDB(cursor,usuario.getPerfil());
		ProyectoGenerico proyecto =null;
		if(sesion.getAttribute("proyectoInvestigador")!=null)
			proyecto = (ProyectoGenerico)sesion.getAttribute("proyectoInvestigador");
		if(req.getParameter("accion")!=null)
			accion = Integer.parseInt(req.getParameter("accion"));
		switch(accion){
			case Parametros.cmdVerProyecto:
				sesion.setAttribute("proyectoInvestigador", proyectosDB.getProyecto(req.getParameter("id"),req.getParameter("tipo")));
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
				irA="/grupos/proyectos/ListaGastos.jsp";
			break;
			case Parametros.ajaxTipoPersona:
				List<Contratacion> iterador= new ArrayList<Contratacion>();
				if(Integer.parseInt((String) req.getParameter("tipoPersona"))==1){
					iterador.add(new Contratacion(1, "OPS"));
					iterador.add(new Contratacion(2,"OPS-AR"));
					iterador.add(new Contratacion(3,"OPA-AR(Par)"));
					sesion.setAttribute("tipoPersona", 1);
				}else{
					sesion.setAttribute("tipoPersona", 2);
				}
				iterador.add(new Contratacion(4,"CPS"));
				iterador.add(new Contratacion(5,"OC"));
				iterador.add(new Contratacion(6,"OS"));
				sesion.setAttribute("tipoContrato", iterador);
				sesion.removeAttribute("idContrato");
				irA="/grupos/proyectos/ListaGastos.jsp";
				break;
			case Parametros.tipoContrato:
				int tipoCon = Integer.parseInt((String)req.getParameter("tipoContratacion"));
				switch (tipoCon){
					case 1:
						sesion.setAttribute("idContrato", 1);
					break;
					case 2:
						sesion.setAttribute("idContrato", 2);
						break;
					case 3:
						sesion.setAttribute("idContrato", 3);
						break;
					case 4:
						sesion.setAttribute("idContrato", 4);
						break;
					case 5:
						sesion.setAttribute("idContrato", 5);
						break;
					case 6:
						sesion.setAttribute("idContrato", 6);
						break;
				}
				irA="/grupos/proyectos/ListaGastos.jsp";
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
}

