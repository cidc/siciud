package cidc.proyectos.db;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;


import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.obj.Globales;
import cidc.proyectos.obj.BalanceGeneral;
import cidc.proyectos.obj.CambiosProy;
import cidc.proyectos.obj.CoInvest;
import cidc.proyectos.obj.Documento;
import cidc.proyectos.obj.FiltroProyecto;
import cidc.proyectos.obj.GastosRubro;
import cidc.proyectos.obj.Proyecto;
import cidc.proyectos.obj.ProyectoGenerico;
import cidc.proyectos.obj.Rubros;
import cidc.proyectos.servlet.GastosExcel;
import cidc.proyectosAntiguos.obj.GeneralOBJ;

public class ProyectosInvestigadorDB extends BaseDB {

	public static char sep=java.io.File.separatorChar;

	public ProyectosInvestigadorDB(CursorDB c, int perfil) {
		super(c, perfil);
		rb=ResourceBundle.getBundle("cidc.proyectos.consultas");
	}

	public List getListaProyectos(long idPersona){
		List lista=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ProyectoGenerico datos=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getListProyNuevosInvestigador"));
			ps.setLong(1,idPersona);
			rs=ps.executeQuery();
			i=1;
			while(rs.next()){
				i=1;
				datos=new ProyectoGenerico();
				datos.setIdProyecto(rs.getLong(i++));
				datos.setCodigo(rs.getString(i++));
				datos.setNombre(rs.getString(i++));
				datos.setEstado(rs.getString(i++));
				datos.setTipo(1);
				lista.add(datos);
			}
			ps=cn.prepareStatement(rb.getString("getListProyAntiguoInvestigador"));
			ps.setLong(1,idPersona);
			rs=ps.executeQuery();
			i=1;
			while(rs.next()){
				i=1;
				datos=new ProyectoGenerico();
				datos.setIdProyecto(rs.getLong(i++));
				datos.setCodigo(rs.getString(i++));
				datos.setNombre(rs.getString(i++));
				datos.setEstado(rs.getString(i++));
				datos.setTipo(2);
				lista.add(datos);
	//			System.out.println("A�ade pro");
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return lista;
	}

	public ProyectoGenerico getProyecto(String id,String tipo) {
		ProyectoGenerico proyecto=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Globales g= new Globales();
		String valor=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("verProyectoInvestigador"+tipo));
			ps.setLong(1, Long.parseLong(id));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				proyecto=new ProyectoGenerico();
				proyecto.setTipo(Integer.parseInt(tipo));
				proyecto.setIdProyecto(Long.parseLong(id));
				proyecto.setCodigo(rs.getString(i++));
				proyecto.setNombre(rs.getString(i++));
				proyecto.setFacultad(rs.getString(i++));
				proyecto.setProyCurricular(rs.getString(i++));
				proyecto.setDirector(rs.getString(i++));
				proyecto.setFecAprobacion(rs.getString(i++));
				proyecto.setNumConvocatoria(rs.getString(i++));
				proyecto.setConvocatoria(rs.getString(i++));
				proyecto.setDuracion(rs.getString(i++));
				proyecto.setGrupoInvestigacion(rs.getString(i++));
				proyecto.setArchivoProp(rs.getString(i++));
				proyecto.setConsecContrato(rs.getString(i++));
				proyecto.setConsecActa(rs.getString(i++));
				proyecto.setAno(rs.getString(i++));
				proyecto.setFecContrato(rs.getString(i++));//captura la fecha del contrato
				proyecto.setFecActaInicio(rs.getString(i++));//captura la fecha del acta de inicio
				proyecto.setTerminosRef(rs.getString(i++));
				proyecto.setEstado(rs.getString(i++));
				/********************************************/
				if(tipo.equals("2"))
					proyecto.setObservacionDigit(rs.getString(i++));
				/*************Asignando el valor aprobado en letras******************************/
				valor=rs.getString(i++);
				proyecto.setTipo2(rs.getInt(i++));
				if(valor!="" && valor!=null)
					valor=g.moneda(valor);
				proyecto.setValorLetras(valor);
				/********************************************************************************/
				proyecto.setListaObservaciones(getListaObservaciones(cn,Long.parseLong(id),tipo));
				proyecto.setCoInvestigadores(getListaCoinvestigadores(cn,Long.parseLong(id),tipo));
				proyecto.setListaCambios(getListaCambiosProyecto(cn,Long.parseLong(id),tipo));
				if(tipo.equals("1"))
					proyecto.setCompromisosConv(getCompromisosProyecto(cn,proyecto.getNumConvocatoria(),Long.parseLong(id),tipo));
				proyecto=getDocumentosProyecto(cn,proyecto,tipo);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}

		return proyecto;
	}

	private ProyectoGenerico getDocumentosProyecto(Connection cn,ProyectoGenerico proyecto,String tipo) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		Documento propuesta=null,contrato=null, actaIni=null, actaFin=null, infFinal=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("getDocumentos"+tipo));
			ps.setLong(1, proyecto.getIdProyecto());
			rs=ps.executeQuery();
			String nombre=null;
			String fecha=null;
			while(rs.next()){
				i=1;
				if(tipo.equals("1")){
//					**********Propuesta********************************
					propuesta=new Documento();
					propuesta.setUrl("/Propuestas/"+rs.getString(i++));
					propuesta.setFecha("");
					proyecto.setPropuesta(propuesta);
//					**********Contrato********************************
					contrato=new Documento();
					nombre=""+rs.getInt(i++);
					fecha=rs.getString(i++);
					contrato.setFecha(fecha);
					if(fecha!=null){
						fecha=fecha.substring(2,4);
					nombre="Contrato_"+nombre+"_"+fecha+".pdf";
					http://metis.udistrital.edu.co:8095/siciud/Documentos/Proyectos/Contratos/Contrato_47_10.pdf
					contrato.setUrl("/Proyectos/Contratos/"+nombre);
					proyecto.setContrato(contrato);
					}
//					**********Acta de Inicio********************************
					actaIni=new Documento();
					nombre=""+rs.getInt(i++);
					fecha=rs.getString(i++);
					actaIni.setFecha(fecha);
					if(fecha!=null){
						fecha=fecha.substring(2,4);
					nombre="ActaInicio_"+nombre+"_"+fecha+".pdf";
					actaIni.setUrl("/Proyectos/Actas/"+nombre);
					proyecto.setActaInicio(actaIni);
					}
//					********************************************************
				}
				if(tipo.equals("2")){
				//	**********Propuesta********************************
					propuesta=new Documento();
					propuesta.setUrl("/ProyectosAntiguos/Propuestas/"+rs.getString(i++));
					propuesta.setFecha("");
					propuesta.setObservaciones(rs.getString(i++));
					proyecto.setPropuesta(propuesta);
//					**********Contrato********************************
					contrato=new Documento();
					contrato.setUrl("/ProyectosAntiguos/Contratos/"+rs.getString(i++));
					contrato.setFecha(rs.getString(i++));
					contrato.setObservaciones(rs.getString(i++));
					proyecto.setContrato(contrato);
//					**********Acta de Inicio********************************
					actaIni=new Documento();
					actaIni.setUrl("/ProyectosAntiguos/ActasInicio/"+rs.getString(i++));
					actaIni.setFecha(rs.getString(i++));
					actaIni.setObservaciones(rs.getString(i++));
					proyecto.setActaInicio(actaIni);

//					********************************************************
//					**********Acta Final********************************
					actaFin=new Documento();
					actaFin.setUrl("/ProyectosAntiguos/ActasFinales/"+rs.getString(i++));
					actaFin.setFecha(rs.getString(i++));
					actaFin.setObservaciones(rs.getString(i++));
					proyecto.setActaFinal(actaFin);

//					********************************************************
//					**********Informe Final********************************
					infFinal=new Documento();
					infFinal.setUrl("/ProyectosAntiguos/ActasFinales/"+rs.getString(i++));
					infFinal.setFecha(rs.getString(i++));
					infFinal.setObservaciones(rs.getString(i++));
					proyecto.setInformeFinal(infFinal);
//					********************************************************
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return proyecto;
	}

	public List getListaCambiosProyecto(Connection cn,long idProyecto,String tipo){
		PreparedStatement ps=null;
		ResultSet rs=null;
		CambiosProy cambio=null;
		List listaCambios=new ArrayList();
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("getListaCambiosProyecto"+tipo));
			ps.setLong(1, idProyecto);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				cambio=new CambiosProy();
				cambio.setId(rs.getInt(i++));
				cambio.setSolicitud(rs.getString(i++));
				cambio.setRespuesta(rs.getString(i++));
				cambio.setDescripcion(rs.getString(i++));
				cambio.setTipoTxt(rs.getString(i++));
				cambio.setObservacion(rs.getString(i++));
				listaCambios.add(cambio);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}

		return listaCambios;
	}

	public List getCompromisosProyecto(Connection cn,String convocatoria,long idProyecto,String tipo){
		List listaComp=new ArrayList();
		PreparedStatement ps=null;
		ResultSet rs=null;
		if(convocatoria!=null){
			try{
				if(convocatoria.endsWith("2008"))
					ps=cn.prepareStatement(rb.getString("compromisosConv2008"));
				else
					ps=cn.prepareStatement(rb.getString("compromisosConv2009"));
				ps.setLong(1, idProyecto);
				rs=ps.executeQuery();
				while(rs.next()){
					listaComp.add(rs.getString(1));
				}
			}catch (SQLException e) {
				lanzaExcepcion(e);
			}
			finally{
				cerrar(rs);
				cerrar(ps);
			}
		}
		return listaComp;
	}
	public List getListaCoinvestigadores(Connection cn,long idProyecto,String tipo){
		PreparedStatement ps=null;
		ResultSet rs=null;
		CoInvest coInvest=null;
		List listaCoInve=new ArrayList();
		try {
			ps=cn.prepareStatement(rb.getString("getProyConInvesti"+tipo));
			ps.setLong(1, idProyecto);
			rs=ps.executeQuery();
			while(rs.next()){
				coInvest=new CoInvest();
				coInvest.setNombre(rs.getString(1)+" "+rs.getString(2));
				coInvest.setPapel(rs.getString(3));
				listaCoInve.add(coInvest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}

		return listaCoInve;
	}


	public String calculoValor(Connection cn,String prop){
		Globales g=new Globales();
		BigInteger cidc=BigInteger.valueOf(0);
		PreparedStatement ps=null;
		ResultSet rs=null;
		String dato="";
		try {
			ps=cn.prepareStatement(rb.getString("getResumenRubros"));
			ps.setLong(1,Long.parseLong(prop));
			rs=ps.executeQuery();
			while(rs.next()){
				dato=rs.getString(1);
				if(dato!=null && !dato.equals("") && !dato.equals("0"))
					cidc=cidc.add(new BigInteger(dato));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return g.moneda(cidc.toString());
	}


	public BalanceGeneral getBalanceProyecto(String idProyecto,String tipo){
		BalanceGeneral balance=null;
		Rubros rubros=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List listaRubros=new ArrayList();
		Globales global=new Globales();
		int i=1;
		boolean flag=true;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getRubrosProyectoInvestig"+tipo));
			ps.setLong(1,Long.parseLong(idProyecto));
			rs=ps.executeQuery();
			balance=new BalanceGeneral();
			balance.setIdProyecto(Long.parseLong(idProyecto));
			while(rs.next()){
				i=1;
				flag=false;
				rubros=new Rubros();
				rubros.setIdRubro(rs.getLong(i++));
				rubros.setNombreRubro(rs.getString(i++));
				rubros.setValorRubro(rs.getString(i++));
				rubros.setListaGastos(getGastosRubro(rubros.getIdRubro(),idProyecto,rubros,tipo));
				listaRubros.add(rubros);
			}
			if(tipo.equals("2") && flag){
				//esto es en caso que el proyecto no tenga aun registrados los rubros aprobados
				ps=cn.prepareStatement(rb.getString("getRubrosPrInvestigAux"));
				ps.setLong(1,Long.parseLong(idProyecto));
				rs=ps.executeQuery();
				while(rs.next()){
					i=1;
					flag=false;
					rubros=new Rubros();
					rubros.setIdRubro(rs.getLong(i++));
					rubros.setNombreRubro(rs.getString(i++));
					rubros.setValorRubro(""+0);
					rubros.setListaGastos(getGastosRubro(rubros.getIdRubro(),idProyecto,rubros,tipo));
					listaRubros.add(rubros);
				}
			}

			balance.setListaRubros(listaRubros);
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return balance;
	}
	public List getGastosRubro(long idRubro, String idProyecto, Rubros rubros,String tipo){
		List listaRubros=new ArrayList();
		GastosRubro gastos=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection cn=null;
		BigInteger acumulado=BigInteger.valueOf(0);
		Globales global=new Globales();
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getGastosRubroInvestig"+tipo));
			ps.setLong(1,idRubro);
			ps.setLong(2,Long.parseLong(idProyecto));
			rs=ps.executeQuery();
			gastos=new GastosRubro();
			gastos.setIdRubro(idRubro);
			while(rs.next()){
				i=1;
				gastos=new GastosRubro();
				gastos.setIdGasto(rs.getString(i++));
				gastos.setValorGasto(rs.getString(i++));
				gastos.setDescripcion(rs.getString(i++));
				gastos.setFecha(rs.getString(i++));
				gastos.setTipoGasto(rs.getInt(i++));
				gastos.setObservaciones(rs.getString(i++));
				listaRubros.add(gastos);
		//		if(gastos.getTipo()==1)
				acumulado=acumulado.add(new BigInteger(""+(Integer.parseInt(gastos.getValorGasto())*gastos.getTipoGasto())));

			}

			BigInteger a= new BigInteger(""+rubros.getValorRubro());
			rubros.setValorSaldo(global.moneda(""+a.subtract(acumulado)));

			rubros.setValorEjecutado(global.moneda(""+acumulado));
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return listaRubros;
	}

	public List getGastosRubrosDeLista(BalanceGeneral general,String idRub) {
		Rubros rubro=null;
		List gastosRubro=null;
		Iterator itRubros=general.getListaRubros().iterator();
		while (itRubros.hasNext()) {
			rubro=(Rubros)itRubros.next();
			if(rubro.getIdRubro()==Long.parseLong(idRub))
			gastosRubro=rubro.getListaGastos();
		}
		return gastosRubro;
	}

	public Rubros getRubro(BalanceGeneral general,String idRub){
		Rubros rubro=null;
		Iterator itRubros=general.getListaRubros().iterator();
		while (itRubros.hasNext()) {
			rubro=(Rubros)itRubros.next();
			if(Long.parseLong(idRub)==rubro.getIdRubro())
				break;
			else
				rubro=null;

		}
		return rubro;
	}


	public List getListaObservaciones(Connection cn,long idPro,String tipo) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		GeneralOBJ observ=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getObservacionesProy"+tipo));
			ps.setLong(1,idPro);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				observ= new GeneralOBJ();
				observ.setIdObservacion(rs.getLong(i++));
				observ.setFecha(rs.getString(i++));
				observ.setObservacion(rs.getString(i++));
				observ.setUsuario(rs.getString(i++));
				lista.add(observ);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return lista;
	}




}
