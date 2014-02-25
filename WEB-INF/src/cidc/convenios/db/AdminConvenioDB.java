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
import cidc.general.login.Usuario;
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.CrearClaves;
import cidc.general.obj.Globales;
import cidc.proyectosGeneral.obj.CoInvest;
import cidc.proyectosGeneral.obj.ExtraDocProyecto;
import cidc.proyectosGeneral.obj.Proyecto;
import cidc.convenios.obj.DatosAjax;


import cidc.adminArticulos.obj.Articulo;
import cidc.adminArticulos.obj.DatEvaluador;
import cidc.adminArticulos.obj.EstadoArticulo;
import cidc.adminArticulos.obj.FiltroArticulo;
import cidc.adminEntidad.obj.EntidadOBJ;
import cidc.convenios.obj.Convenio;
import cidc.convenios.obj.EntidadAsociadaOBJ;
import cidc.convenios.obj.ExtraDocConvenio;
import cidc.convenios.obj.FinanzaOBJ;
import cidc.convenios.obj.GetConvenioOBJ;
import cidc.convenios.obj.GruposOBJ;
import cidc.convenios.obj.ObservacionesOBJ;
import cidc.convenios.obj.PersonaOBJ;
import cidc.convenios.obj.TiemposOBJ;



public class AdminConvenioDB extends BaseDB{
	GetConvenioOBJ conv=null;

	public AdminConvenioDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.convenios.consultas");
	}
	
	public boolean insertaObservacion(int idPro, String observacion,long usuario) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno = false;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("insertaObservacion"));
			ps.setLong(i++,idPro);
			ps.setString(i++,observacion);
			ps.setLong(i++,usuario);
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
	
	public List consultarRubros() {
		List listaRubros = new ArrayList();
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
        try {
			  cn = cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("consultaRubros"));
			  rs=ps.executeQuery();
			  while(rs.next())
			  {
				  DatosAjax datos = new DatosAjax();
				  datos.setCodigo(rs.getInt(1));
				  datos.setNombre(rs.getString(2));
				  listaRubros.add(datos);
			  }
		     } catch (SQLException e) {lanzaExcepcion(e);}
		       catch (Exception e) {lanzaExcepcion(e);}
		 finally
		 {
			 try{
				 rs.close();
				 ps.close();
				 cn.close();
			 }catch(Exception e){}
		 }
		return listaRubros;
	}

	
	
	public List<ExtraDocProyecto> getListaDocAnexos(long idconv) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ExtraDocProyecto> lista=new ArrayList<ExtraDocProyecto>();
		ExtraDocProyecto documento= null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaDocsConvenios"));
			ps.setLong(i++, idconv);
		
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				documento=new ExtraDocProyecto();			
				documento.setNombreDocumento(rs.getString(i++));
				documento.setFechaDoc(rs.getString(i++));
				documento.setNombreArchivo(rs.getString(i++));
				documento.setObservaciones(rs.getString(i++));	
				documento.setTipo(rs.getInt(i++));
				documento.setEstado(rs.getInt(i++));
				documento.setFechaCarga(rs.getString(i++));
				documento.setUsuario(rs.getString(i++));
				
				lista.add(documento);
			}
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
		return lista;
	}
	
	public int getIdNuevoDoc(int tipoId){
		System.out.println("entro a getidnuevodoc");
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int id=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getIdDocumento"+tipoId));
			rs=ps.executeQuery();
			while(rs.next()){
				id=rs.getInt(1)+1;
			}
			id=id-1;
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
		return id;
	}
	
	public boolean actualizaEstadoInforme(String idInforme,String estado) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("cambiaEstadoInforme"));
			ps.setInt(i++, Integer.parseInt(estado));
			ps.setLong(i++, Integer.parseInt(idInforme));
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
	
	public boolean nuevaCargaDocConvenio(ExtraDocConvenio documento, GetConvenioOBJ getconvenioobj,long idUsuario) {
		System.out.println("esta en nueva cargadocconvenio");
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		boolean noGenerico=true;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			
     
				if(documento.getTipo()==3)
					documento.setNombreDocumento("Informe Parcial");
				if(documento.getTipo()==2)
					documento.setNombreDocumento("Informe Final");
				
				ps=cn.prepareStatement(rb.getString("nuevaCargaDocConvenio"));
				ps.setLong(i++, Integer.parseInt(getconvenioobj.getIdconvenio()));			
				ps.setString(i++, documento.getNombreDocumento());
				ps.setString(i++, documento.getFechaDoc());
				ps.setString(i++, documento.getNombreArchivo());						
				ps.setString(i++, documento.getObservaciones());
				ps.setInt(i++, documento.getTipo());
				ps.setInt(i++, documento.getEstado());
				ps.setLong(i++, idUsuario);			
				ps.executeUpdate();
				retorno=true;
		
			i=1;
			
		/*	if(documento.getTipo()==4 || documento.getTipo()==5){
				ps=cn.prepareStatement(rb.getString("cambiaEstadoProyecto"+proyecto.getClaseProyecto()));
				if(documento.getTipo()==4) 	ps.setInt(i++, 3);
				if(documento.getTipo()==5) 	ps.setInt(i++, 4);
				ps.setInt(i++, proyecto.getId());			
				ps.executeUpdate();
			//	System.out.println(".--- sentenci de cambio--->"+ps);
			}
			*/
			cn.commit();
		} catch (SQLException e) {
			System.out.println("error en nuevacargadocconvenio");
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		System.out.println("retorno"+retorno);
		return retorno;
	}
	
	public boolean cambiaEstado(int idconv, String tipo, String estado ) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno = false;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("cambiaEstadoconvenio"));
			ps.setString(i++,estado);
			ps.setString(i++,tipo);
			ps.setLong(i++,idconv);
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
			ps.setInt(i++,convenio.getDuracion());
			ps.setInt(i++,convenio.getV_Duradias());
			ps.setString(i++, convenio.getFechaInicio());
			ps.setString(i++, convenio.getTipo());
			ps.setInt(i++,convenio.getNumDisp());
			ps.setString(i++, global.sumarMesesFecha(convenio.getFechaInicio(),(convenio.getDuracion())));
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
	
	
	public boolean insertarFinanza(GetConvenioOBJ convenio){
		
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("registrarFinanzas"));
			ps.setInt(i++,Integer.parseInt(convenio.getVAprobado()));
			ps.setInt(i++,Integer.parseInt(convenio.getIdconvenio()));
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
	 
	 public int idconvenio(String nombre){
		    Connection cn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			int id = 0;
			Convenio convenio= null;
		 try{
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("buscarconvenioID"));
			ps.setString(1, nombre);
			rs=ps.executeQuery();
			while(rs.next()){
				id=rs.getInt("pk_codconvenionum");
			}
			 
			 
		 }catch(Exception e){
			 lanzaExcepcion(e);
			 
		 }finally{
				cerrar(ps);
				cerrar(cn);
			}
		 
		 return id;
		 
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
        
          while (rs.next())
          {
           datos = new DatosAjax();
           datos.setCodigo(rs.getInt(1));
           datos.setNombre(rs.getString(2)); //select cod,nombres||' '||apellidos
           listaGrupos.add(datos);
          
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
      return listaGrupos;
	 }
	 
	 
	 public GetConvenioOBJ buscarConvenio(int id){
		 
			conv=getConvenio(id);
			
			if(conv!=null){
				conv.setListaPersonas(getListaPersonas(Integer.parseInt(conv.getIdconvenio())));
				conv.setListaObservaciones(getListaObservaciones(id));
				conv.setListaTiempos(getListaTiempos(id));	
				conv.setListagrupos(getListaGrupos(id));	
				conv.setListaentidadesConv(getListaEntidadesConv(id));	
				conv.setFinanza(getfinanzas(id));
			}
			return conv;
		}
	 
	 
		public List<PersonaOBJ> getListaPersonas(int id){
			PersonaOBJ personas=null;
			Connection cn=null;
			PreparedStatement ps=null;
			ResultSet rs=null;
			int i=1;
			List<PersonaOBJ> listapersonas=new ArrayList <PersonaOBJ>();
			
			try {
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("getPersonas"));
				ps.setInt(1, id);
				rs=ps.executeQuery();
				//System.out.println("-->"+ps);
				while(rs.next()){
					i=1;
					personas=new PersonaOBJ();
					personas.setIdPersona(rs.getInt(i++));
					personas.setDocumento(rs.getString(i++));
					personas.setNombre(rs.getString(i++));
					personas.setObservacion(rs.getString(i++));
					personas.setFechaInicio(rs.getString(i++));
					personas.setFechaFin(rs.getString(i++));
					personas.setRol(rs.getString(i++));
					personas.setRegitradoPor(rs.getString(i++));
					personas.setFechaReg(rs.getString(i++));
					listapersonas.add(personas);
				}
			} catch (SQLException e) {
				lanzaExcepcion(e);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return listapersonas;
		}
	 
	 
	 
	public GetConvenioOBJ getConvenio(int id) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		GetConvenioOBJ convenio= null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getConvenio2"));
			ps.setInt(i++,id);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;

				convenio= new GetConvenioOBJ();
				convenio.setIdconvenio(rs.getString(i++));
				convenio.setCodigo(rs.getString(i++));
				convenio.setFecha(rs.getString(i++));
				convenio.setNombreConvenio(rs.getString(i++));
				convenio.setObservaciones(rs.getString(i++));
				convenio.setEstado(rs.getString(i++));
				convenio.setV_DuraAnos(rs.getString(i++));
				convenio.setV_Durameses(rs.getString(i++));
				convenio.setV_Duradias(rs.getString(i++));
				convenio.setFechaInicio(rs.getString(i++));
				convenio.setTipo(rs.getString(i++));
				convenio.setNumDisp(rs.getString(i++));
				convenio.setFechaFinalizacion(rs.getString(i++));
				convenio.setN_UsuDigita(rs.getString(i++));
				convenio.setF_Digita(rs.getString(i++));
				convenio.setNombreproyecto(rs.getString(i++));
				convenio.setEstadop(rs.getString(i++));
				convenio.setTipop(rs.getString(i++));
				convenio.setFacultad(rs.getString(i++));
				convenio.setProycurri(rs.getString(i++));
				convenio.setGrupo(rs.getString(i++));
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
/*	
	public Convenio getConvenioo(int id) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Convenio convenio= null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getConvenio2"));
			ps.setInt(i++,id);
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
	*/
	
	public List getListaObservaciones(long idConv) {
		
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection cn=null;
		List  lista=new ArrayList ();
		ObservacionesOBJ observ=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getObservacionesConv"));
			ps.setLong(1,idConv);
			
			
			rs=ps.executeQuery();
		
			while(rs.next()){
				i=1;
				observ= new ObservacionesOBJ();
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
			cerrar(cn);
		}
		return lista;
	}
	
	public List getListaGrupos(long idConv){
		 List listaGrupos = new ArrayList();
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 TiemposOBJ tiempo=null; 
		 GruposOBJ grupo=null;
	
				
     try {
			  cn = cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("getConveniogrupos"));
			  ps.setLong(1, idConv);
			  rs=ps.executeQuery();
			  while(rs.next())
			  {
				 
				  grupo = new GruposOBJ();
				  grupo.setIdConvGru(rs.getInt(1));
				  grupo.setIdConvenio(rs.getInt(2));
				  grupo.setIdGrupo(rs.getInt(3));
				  grupo.setNombre(rs.getString(4));
				  listaGrupos.add(grupo);
			  }
		     } catch (SQLException e) {lanzaExcepcion(e);}
		       catch (Exception e) {lanzaExcepcion(e);}
		 finally
		 {
			 try{
				 rs.close();
				 ps.close();
				 cn.close();
			 }catch(Exception e){}
		 }
		return listaGrupos;
	}
	
	public List<TiemposOBJ> getListaTiempos(long idConv){
		 List<TiemposOBJ> listaTiempos = new ArrayList<TiemposOBJ>();
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 TiemposOBJ tiempo =null;
		 Globales global=new Globales();
				
      try {
			  cn = cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("getTiemposAdicionales"));
			  ps.setLong(1, idConv);
			  rs=ps.executeQuery();
			  while(rs.next())
			  {
				 
				  tiempo = new TiemposOBJ();
				  tiempo.setIdTiempo(rs.getInt(1));
				  tiempo.setTipoTiempo(rs.getInt(2));
				  tiempo.setValorTiempo(rs.getInt(3));
				  tiempo.setDescripcion(rs.getString(4));
				  tiempo.setRegitradoPor(rs.getString(5));
				  tiempo.setFechaTiempo(rs.getString(6));
				  conv.setFechaFinalizacion(global.sumarMesesFecha(conv.getFechaFinalizacion(), tiempo.getValorTiempo()));
				  listaTiempos.add(tiempo);
			  }
		     } catch (SQLException e) {lanzaExcepcion(e);}
		       catch (Exception e) {lanzaExcepcion(e);}
		 finally
		 {
			 try{
				 rs.close();
				 ps.close();
				 cn.close();
			 }catch(Exception e){}
		 }
		return listaTiempos;
	}
	
	public boolean insertarTiempo(TiemposOBJ tiempo,GetConvenioOBJ getconvenio,Usuario user) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("insertarTiempo"));
			ps.setInt(i++,Integer.parseInt(getconvenio.getIdconvenio()));
			ps.setInt(i++,tiempo.getTipoTiempo());
			ps.setString(i++,tiempo.getFechaTiempo());
			ps.setInt(i++,tiempo.getValorTiempo());
			ps.setString(i++,tiempo.getDescripcion());
			ps.setLong(i++,user.getIdUsuario());
			ps.executeUpdate();
		
			retorno=true;
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
	
	public boolean eliminarTiempo(String idTiempo) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("eliminarTiempo"));
			ps.setInt(1,Integer.parseInt(idTiempo));
			ps.executeUpdate();
		//	System.out.println("----->"+ps);
			retorno=true;
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
	
	public boolean registrarPersonaConvenio(GetConvenioOBJ convenio, PersonaOBJ persona) {
		
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("registrarPersonaConvenio"));
			System.out.println("Entro db");
			ps.setString(i++, persona.getDocumento());
			ps.setString(i++, persona.getNombre());
			ps.setString(i++, persona.getApellido());
			ps.setString(i++, persona.getObservacion());
			ps.setString(i++, persona.getFechaInicio());
			ps.setString(i++, persona.getFechaFin());
			ps.setString(i++, persona.getRol());
			ps.setInt(i++, Integer.parseInt(persona.getRegitradoPor()));
			ps.setString(i++, persona.getFechaReg());
			ps.setInt(i++,Integer.parseInt(convenio.getIdconvenio()));
			ps.executeUpdate();
		//	System.out.println("----->"+ps);
			retorno=true;
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
	

	public boolean eliminarPersonaConvenio(String idConv) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("eliminaPersonaConvenio"));
			ps.setInt(i++,Integer.parseInt(idConv));
			ps.executeUpdate();
			retorno=true;
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
	
public boolean registrarGrupoConvenio(GetConvenioOBJ convenio, String idGrupo) {
		
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("registrarGrupoConvenio"));
			System.out.println("Entro a agregar grupo");
			ps.setInt(i++,Integer.parseInt(convenio.getIdconvenio()));
			ps.setInt(i++,Integer.parseInt(idGrupo));
			
			ps.executeUpdate();
		//	System.out.println("----->"+ps);
			retorno=true;
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
	
	public boolean actualizarPersonaConvenio(PersonaOBJ persona) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizaPersonaConvenio"));
			ps.setString(i++,persona.getObservacion());
			ps.setString(i++,persona.getFechaInicio());
			ps.setString(i++,persona.getFechaFin());
			ps.setInt(i++,persona.getIdPersona());
		//	System.out.println("---actualiza-->"+ps);
			ps.executeUpdate();
			retorno=true;
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
public boolean registrarEntidadConvenio(GetConvenioOBJ convenio, EntidadAsociadaOBJ entidad){
		
		
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("registrarConvenioEntidad"));
			System.out.println("Entro db");
			ps.setInt(i++, Integer.parseInt(entidad.getEntidadid()));
			ps.setInt(i++, Integer.parseInt(convenio.getIdconvenio()));
			ps.setInt(i++, Integer.parseInt(entidad.getVEspecieConv()));
			ps.setInt(i++, Integer.parseInt(entidad.getVEfectivoConv()));
			ps.setInt(i++,(Integer.parseInt(entidad.getVEfectivoConv()))+Integer.parseInt(entidad.getVEspecieConv()));
			
			
			ps.executeUpdate();
		//	System.out.println("----->"+ps);
			retorno=true;
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


public List<EntidadAsociadaOBJ> getListaEntidadesConv(int id){
	EntidadAsociadaOBJ entid=null;
	Connection cn=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	int i=1;
	List<EntidadAsociadaOBJ> ListaEntidadesConv=new ArrayList <EntidadAsociadaOBJ>();
	
	try {
		cn=cursor.getConnection(super.perfil);
		ps=cn.prepareStatement(rb.getString("getEntidadesConv"));
		ps.setInt(1, id);
		rs=ps.executeQuery();
		//System.out.println("-->"+ps);
		while(rs.next()){
			i=1;
			entid=new EntidadAsociadaOBJ();
			entid.setIdentidadconvenio(rs.getString(i++));
			entid.setEntidadid(rs.getString(i++));
			entid.setConvenioid(rs.getString(i++));
			entid.setVEspecieConv(rs.getString(i++));
			entid.setVEfectivoConv(rs.getString(i++));
			entid.setVTotal(rs.getString(i++));
			ListaEntidadesConv.add(entid);
		}
	} catch (SQLException e) {
		lanzaExcepcion(e);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return ListaEntidadesConv;
}


public FinanzaOBJ getfinanzas(int id) {
	FinanzaOBJ finanza=null;
	Connection cn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
        try {
             cn = cursor.getConnection(super.perfil);
             ps = cn.prepareStatement(rb.getString("consultarFinanzaConvenio"));
             ps.setInt(1, id);
             rs = ps.executeQuery();
             while (rs.next()){
                finanza=new FinanzaOBJ();
                finanza.setIdfinanza(rs.getInt(1));
                finanza.setVAprobado(rs.getInt(2));
                finanza.setVComprometer(rs.getInt(3));
                finanza.setVEjecutar(rs.getInt(4));
                finanza.setVEspecie(rs.getInt(5));
                finanza.setVEfectivo(rs.getInt(6));
                finanza.setIdConvenioFinanza(rs.getInt(7));
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
	return finanza;
}


	
}


