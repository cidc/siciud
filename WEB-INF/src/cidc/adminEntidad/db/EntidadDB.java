package cidc.adminEntidad.db;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import cidc.adminEntidad.obj.EntidadOBJ;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.obj.Globales;
import cidc.proyectosAntiguos.obj.ProyectoAntiguoOBJ;




public class EntidadDB extends  BaseDB{

	EntidadOBJ objEntidad= null;
	
		
	public EntidadOBJ getObjEntidad() {
		return objEntidad;
	}


	public void setObjEntidad(EntidadOBJ objEntidad) {
		this.objEntidad = objEntidad;
	}


	public EntidadDB(CursorDB c, int p) {
		super(c, p);
		// TODO Auto-generated constructor stub
		rb = ResourceBundle.getBundle("cidc.adminEntidad.consultas");
	}

	
	public boolean insertarEntidad(EntidadOBJ entidad)
	  {
	   boolean retorno = false;
	   Connection cn = null;
	   PreparedStatement ps = null;
	   int i = 1;

     
	   try
	   {
		cn = cursor.getConnection(super.perfil);
		cn.setAutoCommit(false);
		ps = cn.prepareStatement(rb.getString("insertarEntidad"));
		
		ps.setString(i++, entidad.getNombre());
		ps.setString(i++, entidad.getTipo());
		ps.setString(i++, entidad.getNacionalidad());
		ps.setString(i++, entidad.getCiudad());
		ps.setString(i++, entidad.getDireccion());
		ps.setString(i++, entidad.getObservaciones());
		ps.setString(i++, entidad.getUsudigita());
		ps.setFloat(i++, entidad.getAporte());
		ps.setString(i++,  entidad.getFecha());			
		ps.executeUpdate();
		if (entidad.getTelefonos()!=null)
		{insertarTelefonos(entidad, cn);}
		cn.commit();
		setIdEntidad(entidad);
		retorno = true;
	   } catch (SQLException e) {lanzaExcepcion(e);}
	     catch (Exception e) {lanzaExcepcion(e);}
		 finally {
				  try
				  {
				   ps.close();
				   cn.close();
				  }catch (SQLException e){lanzaExcepcion(e);}
			     }
		 return retorno;
	 }
	
	 public void insertarTelefonos(EntidadOBJ entidad, Connection cn)
	 {
		 PreparedStatement ps = null;
		 try
		 {
			 ps = cn.prepareStatement(rb.getString("insertarTelefonos"));
			 int j=1;
			 for(int i=0; i<= entidad.getTelefonos().length-1; i++)
			 {
				 ps.setString(j++, entidad.getTelefonos()[i]);
				 j=1;
				 ps.addBatch();
			 }
            ps.executeBatch();
		 } catch (SQLException e) {lanzaExcepcion(e);}
	     catch (Exception e) {lanzaExcepcion(e);}
		 finally {
				  try
				  {
				   ps.close();
				  }catch (SQLException e){lanzaExcepcion(e);}
			     }
	 }
	 
	 
	 private boolean setIdEntidad(EntidadOBJ entidad) {
		  Connection cn = null;
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  objEntidad = entidad;
		  boolean retorno = false;
	      try{
	          cn = cursor.getConnection(super.perfil);
	          ps = cn.prepareStatement(rb.getString("consultaIdEntidad"));
	          ps.setString(1, entidad.getNombre());
	          ps.setString(2, entidad.getCiudad());
	          rs = ps.executeQuery();
	          while (rs.next())
	          {
	           objEntidad.setId(rs.getInt(1));
	           retorno = true;
	          }
	         }
	      catch (SQLException e){lanzaExcepcion(e);}
	      catch (Exception e){lanzaExcepcion(e);}
	      finally {
	    	       try{
	    		       rs.close();
	    		       ps.close();
	    		       cn.close();
	    	          }
	    	  catch (Exception e){lanzaExcepcion(e);}
	          }
	      return retorno;
	 }
	 
	 
		public EntidadOBJ consultarEntidad(long idEntidad) {
			EntidadOBJ objEntidad=null;
			Connection cn = null;
		    PreparedStatement ps = null;
		    ResultSet rs = null;
		        try {
		             cn = cursor.getConnection(super.perfil);
		             ps = cn.prepareStatement(rb.getString("consultarEntidad"));
		             ps.setLong(1, idEntidad);
		             rs = ps.executeQuery();
		             while (rs.next()){
		                objEntidad=new EntidadOBJ();
		                objEntidad.setId(rs.getInt(1));
		                objEntidad.setNombre(rs.getString(2));
		                objEntidad.setTipo(rs.getString(3));
		                objEntidad.setNacionalidad(rs.getString(4));
		                objEntidad.setCiudad(rs.getString(5));
		                objEntidad.setDireccion(rs.getString(6));
		                objEntidad.setObservaciones(rs.getString(7));
		                objEntidad.setUsudigita(rs.getString(8));
		                objEntidad.setAporte(rs.getInt(9));
		                objEntidad.setFecha(rs.getString(10));
		              
		            }
		             
		        } catch (SQLException e) {lanzaExcepcion(e);}
			       catch (Exception e) {lanzaExcepcion(e);}
		        finally {
		            try {
		             rs.close();
		             ps.close();
		             cn.close();
		            }
		            catch (SQLException e){}
		            }
			return objEntidad;
		}
	
	
		public boolean actualizarEntidad(EntidadOBJ objEntidad) {
			boolean retorno = false;
			Connection cn=null;
			PreparedStatement ps=null;
			String comp = "";
		   
			try {
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("actualizarEntidad"));
				ps.setString(1,objEntidad.getNombre());
				ps.setString(2,objEntidad.getTipo());
				ps.setString(3,objEntidad.getNacionalidad());
				ps.setString(4,objEntidad.getCiudad());
				ps.setString(5,objEntidad.getDireccion());
				ps.setString(6,objEntidad.getObservaciones());
				ps.setString(7,objEntidad.getUsudigita());
				ps.setDouble(8,objEntidad.getAporte());
				ps.setString(9,objEntidad.getFecha());
				ps.setInt(10,objEntidad.getId());
			
				
				ps.executeUpdate();
				retorno = true;
			}catch (SQLException e) {
				lanzaExcepcion(e);
			}catch (Exception e) {
				lanzaExcepcion(e);
			}finally{
				cerrar(ps);
				cerrar(cn);
			}
			return retorno;
		}
		
		
		
}
