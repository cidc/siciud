package cidc.proyectos.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.general.db.CursorDB;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;
import cidc.proyectos.obj.Contratacion;

public class Cargar extends ServletGeneral{
	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
		String irA="/grupos/proyectos/ListaGastos.jsp";
		cursor=new CursorDB();
		HttpSession sesion=req.getSession();
		Contratacion cont= new Contratacion();
		cont=(Contratacion)sesion.getAttribute("contratacion");
		CargarDocumento carg= new CargarDocumento();
		Date date = new Date();
		String nombre =String.valueOf(date.getTime());
		if(carg.cargar(req, nombre, "Bizagi"))
			mensaje="Documento Subido Exitosamente";
		else
			mensaje="Ocurrio un Problema al Subir el Documento";
		sesion.setAttribute("lista", "display:none");
		retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
		
	}
}
