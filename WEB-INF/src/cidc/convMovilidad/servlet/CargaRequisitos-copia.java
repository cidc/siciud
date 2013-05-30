package cidc.convMovilidad.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import cidc.convocatorias.db.ConvocatoriasDB;
//import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.convMovilidad.db.MovilidadDB;
import cidc.convMovilidad.obj.InfoGeneral;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.CargarDocumento;
import cidc.general.servlet.ServletGeneral;

public class CargaRequisitos extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		String irA="/convMovilidad/Cargar.jsp";
        	CargarDocumento cargarDocumento=new CargarDocumento();
        	HttpSession sesion=req.getSession();
        	Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		InfoGeneral infoGeneral =(InfoGeneral)sesion.getAttribute("movilidad");
		//ConvocatoriaOBJ convocatoriaOBJ=(ConvocatoriaOBJ)sesion.getAttribute("convocatoriaOBJ");
        String nombre="Requisito";
        cursor=new CursorDB();
       // if(infoGeneral!=null){
        	//nombre=infoGeneral.getConvAno()+"-"+infoGeneral.getConvNumero();
	/*	int conv=0;
                if(req.getParameter("propConvId")!=null)
                conv=Integer.parseInt(req.getParameter("propConvId"));*/
		int iddoc=0;
                if(req.getParameter("DocId")!=null)
			System.out.println("entra al cargar n");
                //iddoc=Integer.parseInt(req.getParameter("DocId"));

	        if(cargarDocumento.cargar(req,nombre,"Convocatorias")){
	        	MovilidadDB movilidadDB=new MovilidadDB(cursor,usuario.getPerfil());
	        	String x=cargarDocumento.getNombreArchivo();
	        	String ext=x.substring(x.lastIndexOf("."),x.length());
	        	movilidadDB.setRequisitos(infoGeneral.getIdPropuesta(),nombre+ext);
	        	req.setAttribute("estado","Se han cargado el archivo "+cargarDocumento.getNombreArchivo()+"");
	        }else{
	        	req.setAttribute("estado","Fallo la carga del archivo "+cargarDocumento.getNombreArchivo()+"");
	        }
        //}else{
        	req.setAttribute("estado","Debe Seleccionar una Convocatoria");
        //}

        retorno[0]="unir";
		retorno[1]=irA;
		retorno[2]="";
		return retorno;
	}
}

