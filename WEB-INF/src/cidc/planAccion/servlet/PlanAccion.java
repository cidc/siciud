package cidc.planAccion.servlet;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.Param;

import cidc.convMovilidad.db.MovilidadDB;
import cidc.convMovilidad.obj.InfoGeneral;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.servlet.ServletGeneral;
import cidc.logger.Log;
import cidc.planAccion.db.PlanAccionDB;
import cidc.planAccion.obj.Parametros;
import cidc.planAccion.obj.PlanAccionDatos;
import cidc.planAccion.obj.Actividades;
import cidc.planAccion.pdf.GenerarPDF;


public class PlanAccion extends ServletGeneral {

	public String [] operaciones(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		System.out.println("Ingreso al Servlet =========>");
		context=config.getServletContext();
		cursor=new CursorDB();
		String irA="/planAccion/DatosPlanAccion.jsp";
		int accion=0,corte=0;
		if(req.getParameter("accion")!=null)
			accion=Integer.parseInt(req.getParameter("accion"));
		HttpSession sesion=req.getSession();
		Usuario usuario=(Usuario)sesion.getAttribute("loginUsuario");
		PlanAccionDatos info = new PlanAccionDatos(); 
		info=(PlanAccionDatos)sesion.getAttribute("planaccion");
		Actividades actividad=null;
		PlanAccionDB planaccionDB=new PlanAccionDB(cursor,2);
		String id_grupo_plan=(String) sesion.getAttribute("idGrupo");
		System.out.println("El id del grupo es" +id_grupo_plan);
		int periodoActual =Calendar.getInstance().get(Calendar.YEAR);
		boolean consultarPlan=(sesion.getAttribute("consultar")==null)?false:(Boolean) sesion.getAttribute("consultar");
		boolean informe=(sesion.getAttribute("Informe")==null)?false:(Boolean) sesion.getAttribute("Informe");
//		if(sesion.getAttribute("consultar")==null)
//			consultarPlan = false;
//		else
//			consultarPlan=(Boolean) sesion.getAttribute("consultar");
		mensaje="";
		sesion.setAttribute("corte", (diferenciasDeFechas(new Date(), deStringToDate(Parametros.FECHACIERRE)))?true:false); 
//		if(diferenciasDeFechas(new Date(), deStringToDate(Parametros.FECHACIERRE)))
//			sesion.setAttribute("corte", true);
//		else
//			sesion.setAttribute("corte", false);
		Log log = new Log();
		log.mostrarLog(mensaje);
		switch(accion){
			//Buscar Planes de Accion relacionados a este grupo de invsetigación.
			case Parametros.BUSCARPLANES:
				System.out.println("Caso 1 ************"+req.getParameter("periodo"));
				if(consultarPlan||informe){
					info.setAnoinicio(req.getParameter("periodo"));
					info.setAnofinal(req.getParameter("periodo"));
					sesion.setAttribute("anoActual", req.getParameter("periodo"));
				}else{
					info.setAnoinicio(""+periodoActual);
					info.setAnofinal(""+periodoActual);
				}
			try {
				sesion.removeAttribute("nombrePdf");
				sesion.setAttribute("nombreGrupo", planaccionDB.consultarNombre(info).getNombregrupo().toUpperCase());
				sesion.setAttribute("listaActividades",planaccionDB.consultaPlanAccion(info));
				sesion.setAttribute("listaCriterios",planaccionDB.consultaCriterios());
				sesion.setAttribute("planaccion",planaccionDB.getPlanAccionDatos());
				sesion.setAttribute("habilitar", (!((String) sesion.getAttribute("anoActual")).equals("2013")) ? true: false);
			} catch (Exception e) {
				e.printStackTrace();
			}
				System.out.println("Valor idPlan ***********" + planaccionDB.getPlanAccionDatos().getIdPlan()+"deshabilitar"+sesion.getAttribute("deshabilitar")+
						"ano actual"+sesion.getAttribute("anoActual"));				
			if (informe) {
				irA="/planAccion/InformeGestion.jsp";
			}else
				irA="/planAccion/DatosPlanAccion.jsp";
			break;
			//Agrega Plan de Acción
			case Parametros.AGREGARPLAN:		
				System.out.println("Caso 2 ************");				
				actividad=(Actividades)sesion.getAttribute("actividad");
				System.out.println("Valor idPlan ***********" + req.getParameter("idPlan"));
				planaccionDB.crearActividad(actividad);
				//consultando nuevamente 
				info=(PlanAccionDatos)sesion.getAttribute("planaccion");
				sesion.setAttribute("listaActividades", planaccionDB.consultaPlanAccion(info));
				irA="/planAccion/DatosPlanAccion.jsp";
			break;
			//eliminar actividad
			case Parametros.ELIMINARPLAN:
				if(planaccionDB.eliminaActividad(req.getParameter("idActividad")))
					mensaje="La Actividad se ha eliminado satisfactoriamente";
				else
					mensaje="La Actividad no se ha podido eliminar";
				//consultando nuevamente 
				info=(PlanAccionDatos)sesion.getAttribute("planaccion");
				sesion.setAttribute("listaActividades", planaccionDB.consultaPlanAccion(info));
				irA="/planAccion/DatosPlanAccion.jsp";
			break;
			case Parametros.CAMBIARINTERFAZ:
				if(consultarPlan){
					consultarPlan=false;
					sesion.setAttribute("anoActual",""+periodoActual );
					sesion.removeAttribute("nombrePdf");
				}
				else{
					consultarPlan=true;
					sesion.removeAttribute("anoActual");
					sesion.removeAttribute("nombreGrupo");
				}
				sesion.removeAttribute("listaActividades");
				sesion.setAttribute("consultar", consultarPlan);
			break;
			case Parametros.GENERARPDF:
				info =planaccionDB.consultarNombre(info);
				System.out.println("id grupo"+info.getIdGrupo()+" nombre "+info.getNombregrupo());
				GenerarPDF pdf = new GenerarPDF();
				sesion.setAttribute("nombrePdf", pdf.generar(sesion.getAttribute("listaActividades"),sesion.getAttribute("listaCriterios"),info));
				mensaje="Reporte generado correctamente";
				break;
			case Parametros.ACTUALIZARACTIVIDAD:
				info.setAnoinicio(""+periodoActual);
				info.setAnofinal(""+periodoActual);
				actividad=(Actividades)sesion.getAttribute("actividad");
				//esta linea crea el plan si no existe, para que inserte la actividad, ademas consulta el id del plan
				planaccionDB.consultaPlanAccion(info);
				if(planaccionDB.ActualizarPLan(info.getIdPlan(), actividad.getIdActividad()))
					mensaje="La Actividad se ha insertado satisfactoriamente";
				else
					mensaje="La Actividad no se ha podido insertar";
				//consultando nuevamente 
				info=(PlanAccionDatos)sesion.getAttribute("planaccion");
				sesion.setAttribute("listaActividades", planaccionDB.consultaPlanAccion(info));
				irA="/planAccion/DatosPlanAccion.jsp";
				break;
			case Parametros.CONSULTAGRUPO:
				sesion.setAttribute("Informe", true);
				sesion.setAttribute("anoActual", ""+(periodoActual-1));
				irA="/planAccion/InformeGestion.jsp";
				break;
			case Parametros.INGRESARINFORME:
				actividad=(Actividades)sesion.getAttribute("actividad");
			if (planaccionDB.ingresarPorcentaje(actividad)) {
				sesion.setAttribute("listaActividades", planaccionDB.consultaPlanAccion(info));
				mensaje="Informe Ingresado con Éxito";
			}else
				mensaje="Ha ocurrido un error, por favor intente de nuevo ";
			irA="/planAccion/InformeGestion.jsp";
				break;
			case Parametros.ELIMINARPORCENTAJE:
				if(planaccionDB.eliminarPorcentaje(Integer.parseInt(req.getParameter("idActividad")))){
					sesion.setAttribute("listaActividades", planaccionDB.consultaPlanAccion(info));
					mensaje="Informe eliminado con éxito";
				}else
					mensaje="Ha ocurrido un error, por favor intente de nuevo ";
				irA="/planAccion/InformeGestion.jsp";
				break;
			default:
				sesion.setAttribute("Informe", false);
				sesion.setAttribute("anoActual", ""+periodoActual);
				sesion.removeAttribute("listaActividades");
				sesion.removeAttribute("nombrePdf");
				sesion.setAttribute("consultar", false);
				sesion.setAttribute("arregloAnos", crearhistorico(periodoActual));
				irA="/planAccion/DatosPlanAccion.jsp";
			break;
		}
		accion=0;
		retorno[0]="desviar";
		retorno[1]=irA;
		retorno[2]=mensaje;
		return retorno;
	}
	
	public List<String> crearhistorico(int anoActual){
		List<String> lista =new ArrayList<String>();
		for(int i=2011;i<=anoActual;i++){
			lista.add(""+i);
		}
		return lista;
	}
	
	//Diferencias entre dos fechas
    //@param fechaInicial La fecha de inicio
    //@param fechaFinal  La fecha de fin
    //@return Retorna el numero de dias entre dos fechas
    public static synchronized boolean diferenciasDeFechas(Date fechaInicial, Date fechaFinal) {

        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        try {
            fechaInicial = df.parse(fechaInicioString);
            String fechaFinalString = df.format(fechaFinal);
            fechaFinal = df.parse(fechaFinalString);
        }catch (Exception e) {
			// TODO: handle exception
		} 
        long fechaInicialMs = fechaInicial.getTime();
        long fechaFinalMs = fechaFinal.getTime();
        long diferencia = fechaFinalMs - fechaInicialMs;
        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
        if(dias<0){
        	return false;
        }else
        	return true;
    }

    //Devuele un java.util.Date desde un String en formato dd-MM-yyyy
    //@param La fecha a convertir a formato date
    //@return Retorna la fecha en formato Date
    public static synchronized java.util.Date deStringToDate(String fecha) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaEnviar = null;
        try {
            fechaEnviar = formatoDelTexto.parse(fecha);
            return fechaEnviar;
        }catch (Exception e) {
			// TODO: handle exception
		}
        return null;
    }

}