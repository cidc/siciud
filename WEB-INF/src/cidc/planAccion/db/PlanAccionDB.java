package cidc.planAccion.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.planAccion.obj.Actividades;
import cidc.planAccion.obj.Criterios;
import cidc.planAccion.obj.PlanAccionDatos;


public class PlanAccionDB extends BaseDB{
	//constructor
	PlanAccionDatos planAccionDatos = null;
	
	public PlanAccionDatos getPlanAccionDatos() {
		return planAccionDatos;
	}

	public void setPlanAccionDatos(PlanAccionDatos planAccionDatos) {
		this.planAccionDatos = planAccionDatos;
	}

	public PlanAccionDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		//archivo que almacena sentencias sql
		rb=ResourceBundle.getBundle("cidc.planAccion.consultas");
	}
	
	public List <Actividades> consultaPlanAccion(PlanAccionDatos info){
		boolean retorno=false;
		Actividades actividades=null;
		List <Actividades> lista= new ArrayList <Actividades>(); 
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		planAccionDatos = info;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("buscarplanAccion"));
			System.out.println("Luego de buscar si existe un Plan ***********");
			ps.setInt(i++, info.getIdGrupo());
			ps.setString(i++, info.getAnoinicio());
			ps.setString(i++, info.getAnofinal());
			System.out.println(ps.toString());
			rs=ps.executeQuery();
			
			while(rs.next()){
				System.out.println("Econtro un Plan de Accion asociado al grupo***********");
				planAccionDatos.setIdPlan(rs.getLong(1));				
				retorno=true; 
			}
			i=1;
			if (retorno){
				System.out.println("Busca las actividades en caso de que exista el plan de acción ***********");
				ps=cn.prepareStatement(rb.getString("buscarActividadesPlan"));
				ps.setInt(i++, info.getIdGrupo());
				ps.setString(i++, info.getAnoinicio());
				ps.setString(i++, info.getAnofinal());
				System.out.println(ps.toString());
				rs=ps.executeQuery();				
				while(rs.next()){
					i=1;
					actividades= new Actividades();
					actividades.setIdActividad(rs.getLong(i++));
					actividades.setIdPlan(rs.getLong(i++));
					actividades.setIdCriterio(rs.getLong(i++));
					actividades.setActividad(rs.getString(i++));
					actividades.setDescripcion(rs.getString(i++));
					actividades.setMeta(rs.getString(i++));
					actividades.setPorcentaje(rs.getInt(i++));
					lista.add(actividades);
				}	
			}
//			else{
//				System.out.println("EL PLAN DE ACCIÓN NO EXISTE ***********");
//				agregarPlan(info);
//				ps=cn.prepareStatement(rb.getString("buscarplanAccion"));
//				ps.setInt(i++, info.getIdGrupo());
//				ps.setString(i++, info.getAnoinicio());
//				ps.setString(i++, info.getAnofinal());
//				rs=ps.executeQuery();
//				while(rs.next()){
//					planAccionDatos.setIdPlan(rs.getLong(1));
//				}
//			}
		}
		catch (SQLException e){
			lanzaExcepcion(e);	
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(ps);
			cerrar(cn);
		}
		return lista;
	}
		
	public boolean agregarPlan(PlanAccionDatos info){
		System.out.println("En el metodo de Agregar Plan de Accion ***********");
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("insertaplanAccion"));
			ps.setInt(i++, info.getIdGrupo());
			ps.setString(i++, info.getAnoinicio());
			ps.setString(i++, info.getAnofinal());
			ps.executeUpdate();
			retorno=true; 
		}
		catch (SQLException e){
			lanzaExcepcion(e);	
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		//retorno es booleana y retorna false si algo fallo
		return retorno;
	}		

	public PlanAccionDatos consultarNombre(PlanAccionDatos info){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try{
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("buscarGrupo"));
			ps.setInt(i++, info.getIdGrupo());
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				info.setNombregrupo(rs.getString(i++));
			}
		}
		catch (SQLException e){
			lanzaExcepcion(e);	
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);			
		}
		
		return info;
		
	}
	public List <Criterios> consultaCriterios(){
		Criterios criterio=null;
		List <Criterios> lista= new ArrayList <Criterios>(); 
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("buscarCriterio"));
				rs=ps.executeQuery();				
				while(rs.next()){
					i=1;
					criterio= new Criterios();
					criterio.setIdCriterio(rs.getLong(i++));
					criterio.setDescripcion(rs.getString(i++));					
					lista.add(criterio);	
				}				
		}
		catch (SQLException e){
			lanzaExcepcion(e);	
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);			
		}
		return lista;
	}

	public boolean crearActividad(Actividades actividad,PlanAccionDatos info) {
		// TODO Auto-generated method stub
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			if (actividad.getIdPlan()==0){
				System.out.println("EL PLAN DE ACCIÓN NO EXISTE ***********");
				agregarPlan(info);
				ps=cn.prepareStatement(rb.getString("buscarplanAccion"));
				ps.setInt(i++, info.getIdGrupo());
				ps.setString(i++, info.getAnoinicio());
				ps.setString(i++, info.getAnofinal());
				rs=ps.executeQuery();
				while(rs.next()){
					actividad.setIdPlan(rs.getLong(1));
				}
			}
			
			
			ps=cn.prepareStatement(rb.getString("insertaActividadPlanAccion"));
			ps.setLong(i++, actividad.getIdPlan());
			ps.setLong(i++, actividad.getIdCriterio());
			ps.setString(i++, actividad.getActividad());
			ps.setString(i++, actividad.getDescripcion());	
			ps.setString(i++, actividad.getMeta());
			ps.executeUpdate();
			retorno=true;
		}
		catch (SQLException e) {
			lanzaExcepcion(e);
		}		
		catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(ps);
			cerrar(cn);			
		}
		return retorno;
	}

	public boolean eliminaActividad(String idActividad) {
		System.out.println("Eliminado la actividad#---->"+idActividad);
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("eliminaActividad"));
			ps.setLong(i++,Long.parseLong(idActividad));
			System.out.println("Eliminados registro=="+ps.executeUpdate());
			retorno=true;
		}
		catch (SQLException e) {
			lanzaExcepcion(e);
		}		
		catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(ps);
			cerrar(cn);			
		}
		return retorno;
	}	
	
	public boolean ActualizarPLan(PlanAccionDatos info, long idAct) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Actividades actividad=new Actividades();
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("buscaActividad"));
			ps.setLong(i++, idAct);
			rs=ps.executeQuery();
			
			while(rs.next()){
				i=1;
				actividad.setIdPlan(info.getIdPlan());
				actividad.setIdCriterio((rs.getInt(i++)));
				actividad.setActividad(rs.getString(i++));
				actividad.setDescripcion(rs.getString(i++));
				actividad.setMeta(rs.getString(i++));
				retorno=true; 
			}
			crearActividad(actividad,info);
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}		
		catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(ps);
			cerrar(cn);			
		}
		return retorno;
	}
	/**
	 * este metodo se encarga de actualizar los porcentajes de las actividades de los planes de accion 
	 * @param actividad continen la informacion de idActividad y el pocentaje
	 * @return
	 */
	public boolean ingresarPorcentaje(Actividades actividad){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		for (int j = 0; j < actividad.getIdActividades().length; j++) {
			int i=1;
			try {
				cn = cursor.getConnection(super.perfil);
				ps = cn.prepareStatement(rb.getString("IngresarPorcentaje"));
				if(actividad.getPorcentajes_()[j]>100){
					ps.setInt(i++, 100);
				}else{
					ps.setInt(i++, actividad.getPorcentajes_()[j]);
				}
				ps.setLong(i++, actividad.getIdActividades()[j]);
				ps.executeUpdate();
				retorno = true;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				lanzaExcepcion(e);
				retorno = false;
			} finally {
				//obligatorio en todas las conexiones con la base de datos 
				cerrar(ps);
				cerrar(cn);
			}
		}
		return retorno;
	}
	
	public boolean eliminarPorcentaje( int idActividad){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("IngresarPorcentaje"));
			ps.setInt(i++, 0);
			ps.setLong(i++, idActividad);
			ps.execute();
			retorno=true;
		} catch (Exception e) {
			lanzaExcepcion(e);
			retorno = false;
		} finally {
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(ps);
			cerrar(cn);
		}
		
		return retorno;
	}
	
	public List<PlanAccionDatos> ConsultaGruposPlan(String ano, String tipo, String facultad){
    	List<PlanAccionDatos> lista = new ArrayList<PlanAccionDatos>();
    	Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ConsultaGruposPlan"));
			ps.setString(i++, ano);
			ps.setString(i++, ano);
			ps.setString(i++, ano);
			ps.setString(i++, ano);
			ps.setInt(i++, Integer.parseInt(tipo));
			ps.setInt(i++, Integer.parseInt(facultad));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				PlanAccionDatos pad=new PlanAccionDatos();
				pad.setIdGrupo(rs.getInt(i++));
				pad.setNombregrupo(rs.getString(i++));
				pad.setDirector(rs.getString(i++));
				pad.setIdPlan(rs.getLong(i++));
				pad.setContActividades(rs.getInt(i++));
				lista.add(pad);
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		} finally {
			//obligatorio en todas las conexiones con la base de datos 
			cerrar(ps);
			cerrar(cn);
		}
		return lista;
    }
}
