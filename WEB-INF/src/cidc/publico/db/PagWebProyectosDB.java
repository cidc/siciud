package cidc.publico.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cidc.adminGrupos.obj.FiltroPagWebSemillero;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.inscripcionConv.obj.GruposOBJ;
import cidc.publico.obj.FiltroPagWebProyectos;

public class PagWebProyectosDB extends BaseDB
{
	
	public PagWebProyectosDB(CursorDB c, int perfil) 
	{
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.publico.consultas");
	}
	
	public List AjaxGruposInvestigacion(int facultad)
	{
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null; 
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ajaxGrupos"));
			ps.setLong(1,facultad);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				GruposOBJ gruposOBJ=new GruposOBJ();
				gruposOBJ.setCodigo(rs.getInt(i++));
				gruposOBJ.setNombre(rs.getString(i++));
				l.add(gruposOBJ);
	//			System.out.println(gruposOBJ.getNombre());
			}
		//	System.out.println("la cantidad de grupos encontrados es:"+l.size());
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return l;
	}
	
	public List getListaProyectosParaWeb(FiltroPagWebProyectos filtro) {
		List listaProyectos= new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		FiltroPagWebProyectos proyectoWeb = null;
		int i=1;		
		try {
			cn=cursor.getConnection(super.perfil); 
			
				ps=cn.prepareStatement(rb.getString("listaProyectosWeb"));
				ps.setString(i++, filtro.getNombreProyecto2());
				ps.setString(i++, filtro.getFacultad());
				ps.setString(i++, filtro.getIdGrupo());
				ps.setString(i++, filtro.getNombreDirector());
				ps.setString(i++, filtro.getApellidoDirector());
				ps.setString(i++, filtro.getNombreProyecto2());
				ps.setString(i++, filtro.getFacultad());
				ps.setString(i++, filtro.getIdGrupo());
				ps.setString(i++, filtro.getNombreDirector());
				ps.setString(i++, filtro.getApellidoDirector());
				System.out.println("----->"+ps);
				rs=ps.executeQuery();
				
			while(rs.next())
			{
				i=1;
				proyectoWeb = new FiltroPagWebProyectos();
				proyectoWeb.setIdCodigoProyecto(rs.getInt(i++));
				proyectoWeb.setIdDirector(rs.getInt(i++));
				proyectoWeb.setNombreCompletoDirector(rs.getString(i++));
				proyectoWeb.setFacultad(rs.getString(i++));
				proyectoWeb.setCodigoProyecto(rs.getString(i++));
				proyectoWeb.setNombreProyecto(rs.getString(i++));
				proyectoWeb.setResumen(rs.getString(i++));
				proyectoWeb.setFecha_inicio(rs.getString(i++));
				proyectoWeb.setEstado(rs.getString(i++));
				proyectoWeb.setIdGrupo(rs.getString(i++));
				proyectoWeb.setNombre_grupo(rs.getString(i++));
				proyectoWeb.setTipo_proyecto(rs.getInt(i++));
				proyectoWeb.setNombre_convocatoria_convenio(rs.getString(i++));
				proyectoWeb.setTabla(rs.getInt(i++));
				
				listaProyectos.add(proyectoWeb);
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
		
		return listaProyectos;
	}
	
	public FiltroPagWebProyectos infoProyectoNuevoWeb (int idProyecto)
	{
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		FiltroPagWebProyectos proyectoWeb = null;
		int i=1;
		
		try
		{
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("verInfoProyectoNuevoWeb"));
			ps.setInt(1, idProyecto);
			rs=ps.executeQuery();
			while(rs.next())
			{
				i=1;
				proyectoWeb = new FiltroPagWebProyectos();
				proyectoWeb.setIdCodigoProyecto(rs.getInt(i++));
				proyectoWeb.setIdDirector(rs.getInt(i++));
				proyectoWeb.setCvlac(rs.getString(i++));
				proyectoWeb.setNombreCompletoDirector(rs.getString(i++));
				proyectoWeb.setFacultad(rs.getString(i++));
				proyectoWeb.setCodigoProyecto(rs.getString(i++));
				proyectoWeb.setNombreProyecto(rs.getString(i++));
				proyectoWeb.setResumen(rs.getString(i++));
				proyectoWeb.setFecha_inicio(rs.getString(i++));
				proyectoWeb.setEstado(rs.getString(i++));
				proyectoWeb.setIdGrupo(rs.getString(i++));
				proyectoWeb.setNombre_grupo(rs.getString(i++));
				proyectoWeb.setTipo_proyecto(rs.getInt(i++));
				proyectoWeb.setNombre_convocatoria_convenio(rs.getString(i++));
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
		return proyectoWeb;
	}
	
	public FiltroPagWebProyectos infoProyectoAntiguoWeb (int idProyecto)
	{
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		FiltroPagWebProyectos proyectoWeb = null;
		int i=1;
		
		try
		{
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("verInfoProyectoAntiguoWeb"));
			ps.setInt(1, idProyecto);
			rs=ps.executeQuery();
			while(rs.next())
			{
				i=1;
				proyectoWeb = new FiltroPagWebProyectos();
				proyectoWeb.setIdCodigoProyecto(rs.getInt(i++));
				proyectoWeb.setIdDirector(rs.getInt(i++));
				proyectoWeb.setCvlac(rs.getString(i++));
				proyectoWeb.setNombreCompletoDirector(rs.getString(i++));
				proyectoWeb.setFacultad(rs.getString(i++));
				proyectoWeb.setCodigoProyecto(rs.getString(i++));
				proyectoWeb.setNombreProyecto(rs.getString(i++));
				proyectoWeb.setResumen(rs.getString(i++));
				proyectoWeb.setFecha_inicio(rs.getString(i++));
				proyectoWeb.setEstado(rs.getString(i++));
				proyectoWeb.setIdGrupo(rs.getString(i++));
				proyectoWeb.setNombre_grupo(rs.getString(i++));
				proyectoWeb.setTipo_proyecto(rs.getInt(i++));
				proyectoWeb.setNombre_convocatoria_convenio(rs.getString(i++));
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
		return proyectoWeb;
	}
}
