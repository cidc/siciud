package cidc.convenios.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.CrearClaves;
import cidc.general.obj.Globales;
import cidc.proyectosAntiguos.obj.DatosAjax;
import cidc.adminArticulos.obj.Articulo;
import cidc.adminArticulos.obj.DatEvaluador;
import cidc.adminArticulos.obj.EstadoArticulo;
import cidc.adminArticulos.obj.FiltroArticulo;
import cidc.convenios.obj.Convenio;



public class AdminConvenioDB extends BaseDB{

	public AdminConvenioDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.convenios.consultas");
	}

	public boolean nuevoConvenio(Convenio convenio) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("nuevoConvenio2"));
			ps.setLong(i++,convenio.getCodigo());
			ps.setString(i++, convenio.getFecha());
			ps.setString(i++,convenio.getNombreConvenio());
			ps.setString(i++,convenio.getObservaciones());
			ps.setString(i++, convenio.getEstado());
			ps.setInt(i++,convenio.getV_DuraAnos());
			ps.setInt(i++,convenio.getV_Durameses());
			ps.setInt(i++,convenio.getV_Duradias());
			ps.setString(i++, convenio.getFechaInicio());
			ps.setString(i++, convenio.getTipo());
			ps.setInt(i++,convenio.getNumDisp());
		    ps.setString(i++, convenio.getFechaFinalizacion());
			ps.setFloat(i++, convenio.getVEfectivo());
			ps.setFloat(i++, convenio.getVEspecie());
			ps.setString(i++, convenio.getN_UsuDigita());
			ps.setString(i++, convenio.getF_Digita());
			ps.setString(i++, convenio.getNombreproyecto());
			ps.setInt(i++,convenio.getEstadop());
			ps.setInt(i++,convenio.getTipop());
			ps.setInt(i++, convenio.getFacultad());
			ps.setInt(i++, convenio.getProycurri());
			ps.setInt(i++, convenio.getGrupo());
			ps.setString(i++, convenio.getObjetivo());
			ps.setString(i++, convenio.getResumen());
			ps.setString(i++, convenio.getObservacionesp());
			ps.executeUpdate();
			retorno=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}
	
	 public List consultarInvestigadores (int cod)
	 {
	  List listaInvestigadores = new ArrayList();
	  Connection cn = null;
	  PreparedStatement ps = null;
	  ResultSet rs = null;
      DatosAjax datos = null;
      try{
          cn = cursor.getConnection(super.perfil);
          ps = cn.prepareStatement(rb.getString("consultainvestigadores"));
          ps.setInt(1, cod);
          rs = ps.executeQuery();
          while (rs.next())
          {
           datos = new DatosAjax();
           datos.setCodigo(rs.getInt(1));
           datos.setNombre(rs.getString(2)); //select cod,nombres||' '||apellidos
           listaInvestigadores.add(datos);
          }
         }
      catch (SQLException e){
            lanzaExcepcion(e);
            }
      catch (Exception e){
    	     lanzaExcepcion(e);
            }

      finally {
    	  try{
    		  rs.close();
    		  ps.close();
    		  cn.close();
    	  }
    	  catch (Exception e){}

	 }
      return listaInvestigadores;
	 }

	public List listaConvenio() {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		Convenio convenio= null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaConvenio2"));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				convenio= new Convenio();
				convenio.setIdconvenio(rs.getInt(i++));
				convenio.setCodigo(rs.getInt(i++));
				convenio.setFecha(rs.getString(i++));
				convenio.setNombreConvenio(rs.getString(i++));
				convenio.setObservaciones(rs.getString(i++));
				convenio.setEstado(rs.getString(i++));
				convenio.setV_DuraAnos(rs.getInt(i++));
				convenio.setV_Durameses(rs.getInt(i++));
				convenio.setV_Duradias(rs.getInt(i++));
				convenio.setFechaInicio(rs.getString(i++));
				convenio.setTipo(rs.getString(i++));
				convenio.setNumDisp(rs.getInt(i++));
				convenio.setFechaFinalizacion(rs.getString(i++));
				convenio.setVEfectivo(rs.getFloat(i++));
				convenio.setVEspecie(rs.getFloat(i++));
				convenio.setN_UsuDigita(rs.getString(i++));
				convenio.setF_Digita(rs.getString(i++));
				convenio.setNombreproyecto(rs.getString(i++));
				convenio.setEstadop(rs.getInt(i++));
				convenio.setTipop(rs.getInt(i++));
				convenio.setFacultad(rs.getInt(i++));
				convenio.setProycurri(rs.getInt(i++));
				convenio.setGrupo(rs.getInt(i++));
				convenio.setObjetivo(rs.getString(i++));
				convenio.setResumen(rs.getString(i++));
				convenio.setObservacionesp(rs.getString(i++));
				lista.add(convenio);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("se dano esta joda");
			lanzaExcepcion(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return lista;
	}
	
	 public List consultaProyectos()
		{
		 List listaProyectos = new ArrayList();
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 DatosAjax datos = null;

	     try {
			  cn =  cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("consultaproyectos"));
	          rs = ps.executeQuery();
	          while (rs.next())
	          {
	           datos = new DatosAjax();
	           datos.setCodigo(rs.getInt(1));
	           datos.setNombre(rs.getString(2));
	           listaProyectos.add(datos);
	          }
		     } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         lanzaExcepcion(e);
	        }catch (Exception e) {
			 lanzaExcepcion(e);
		    }
		     finally {
		            try {
		             rs.close();
		             ps.close();
		             cn.close();
		            }
		            catch (Exception e){}
		     }
	     return listaProyectos;
		}
	 
	 
	 
	 public List consultarGrupos (int cod)
	 {
	  List listaGrupos = new ArrayList();
	  Connection cn = null;
	  PreparedStatement ps = null;
	  ResultSet rs = null;
      DatosAjax datos = null;
      try{
    	  
          cn = cursor.getConnection(super.perfil);
          ps = cn.prepareStatement(rb.getString("consultagrupos"));
         
          ps.setInt(1, cod);
          rs = ps.executeQuery();
          System.out.println("ENTRO BASE DATOS");
          while (rs.next())
          {
           datos = new DatosAjax();
           datos.setCodigo(rs.getInt(1));
           datos.setNombre(rs.getString(2)); //select cod,nombres||' '||apellidos
           listaGrupos.add(datos);
           System.out.println("ENTRO2 BASE DATOS");
          }
         }
      catch (SQLException e){
            lanzaExcepcion(e);
            System.out.println("ERROR1 BASE DATOS");
            }
      catch (Exception e){
    	     lanzaExcepcion(e);
    	     System.out.println("ERROR2 BASE DATOS");
            }

      finally {
    	  try{
    		  rs.close();
    		  ps.close();
    		  cn.close();
    	  }
    	  catch (Exception e){}

	 }
      return listaGrupos;
	 }
	 
	public Convenio getConvenio(String id) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Convenio convenio= null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getConvenio2"));
			ps.setInt(i++,Integer.parseInt(id));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;

				convenio= new Convenio();
				convenio.setIdconvenio(rs.getInt(i++));
				convenio.setCodigo(rs.getInt(i++));
				convenio.setFecha(rs.getString(i++));
				convenio.setNombreConvenio(rs.getString(i++));
				convenio.setObservaciones(rs.getString(i++));
				convenio.setEstado(rs.getString(i++));
				convenio.setV_DuraAnos(rs.getInt(i++));
				convenio.setV_Durameses(rs.getInt(i++));
				convenio.setV_Duradias(rs.getInt(i++));
				convenio.setFechaInicio(rs.getString(i++));
				convenio.setTipo(rs.getString(i++));
				convenio.setNumDisp(rs.getInt(i++));
				convenio.setFechaFinalizacion(rs.getString(i++));
				convenio.setVEfectivo(rs.getFloat(i++));
				convenio.setVEspecie(rs.getFloat(i++));
				convenio.setN_UsuDigita(rs.getString(i++));
				convenio.setF_Digita(rs.getString(i++));
				convenio.setNombreproyecto(rs.getString(i++));
				convenio.setEstadop(rs.getInt(i++));
				convenio.setTipop(rs.getInt(i++));
				convenio.setFacultad(rs.getInt(i++));
				convenio.setProycurri(rs.getInt(i++));
				convenio.setGrupo(rs.getInt(i++));
				convenio.setObjetivo(rs.getString(i++));
				convenio.setResumen(rs.getString(i++));
				convenio.setObservacionesp(rs.getString(i++));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("se jodio por el ID");
			lanzaExcepcion(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return convenio;
	}

	public boolean actualizaConvenio(Convenio convenio) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizaConvenio"));
			ps.setInt(i++,convenio.getEstado());
			ps.setInt(i++,convenio.getNumero());
			ps.setString(i++,convenio.getNombreConvenio());
			ps.setString(i++,convenio.getEntidades());
			ps.setString(i++,convenio.getFecha());
			ps.setString(i++,convenio.getValor());
			ps.setInt(i++,convenio.getAno());
			ps.setString(i++,convenio.getCompromisos());
			ps.setString(i++,convenio.getObservaciones());
			ps.setLong(i++,convenio.getIdConvenio());
			ps.executeUpdate();
			retorno=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}
}



