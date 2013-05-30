package cidc.evalComite.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;

public class EvaluacionComite extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		HttpSession sesion=req.getSession();
		String irA="/evalComite/inicial.jsp";
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		mensaje=null;
		retorno[0]="unir";
		System.out.println("entra a el servlet adecuado");


		irA="/evalComite/inicial.jsp";

		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
}
