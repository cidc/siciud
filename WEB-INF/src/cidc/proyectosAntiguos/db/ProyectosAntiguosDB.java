package cidc.proyectosAntiguos.db;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
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
import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.obj.Globales;
import cidc.proyectos.obj.GastosRubro;
import cidc.proyectosAntiguos.obj.CambiosOBJ;
import cidc.proyectosAntiguos.obj.CoinvestigadoresOBJ;
import cidc.proyectosAntiguos.obj.ConsultaProyectosOBJ;
import cidc.proyectosAntiguos.obj.DatosAjax;
import cidc.proyectosAntiguos.obj.DevolucionesOBJ;
import cidc.proyectosAntiguos.obj.GastosOBJ;
import cidc.proyectosAntiguos.obj.GeneralOBJ;
import cidc.proyectosAntiguos.obj.ParametrosOBJ;
import cidc.proyectosAntiguos.obj.ProyectoAntiguoOBJ;
import cidc.proyectosAntiguos.obj.DocumentosOBJ;

public class ProyectosAntiguosDB extends  BaseDB {

	ProyectoAntiguoOBJ objProyecto = null;

	public ProyectoAntiguoOBJ getObjProyecto() {
		return objProyecto;
	}

	public void setObjProyecto(ProyectoAntiguoOBJ objProyecto) {
		this.objProyecto = objProyecto;
	}

	public ProyectosAntiguosDB(CursorDB c, int p) {
		super(c, p);
		// TODO Auto-generated constructor stub
		rb = ResourceBundle.getBundle("cidc.proyectosAntiguos.consultas");
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

	 // lista los investigadores asociados al proyecto
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

	 //Presenta la lista de los Convenios
	 public List consultarConvenios(){
		 List listaConvenios = new ArrayList();
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
         try {
			  cn = cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("consultaconvenios"));
			  rs=ps.executeQuery();
			  while(rs.next())
			  {
				  DatosAjax datos = new DatosAjax();
				  datos.setCodigo(rs.getInt(1));
				  datos.setNombre(rs.getString(2));
				  listaConvenios.add(datos);
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
		return listaConvenios;
	 }

	 //Crear un nuevo proyecto
	 public boolean insertarProyecto(ProyectoAntiguoOBJ proyecto)
	  {
	   boolean retorno = false;
	   Connection cn = null;
	   PreparedStatement ps = null;
	   int i = 1;
       String comp = "";
       proyecto.setTipo(1);
//       if(proyecto.getTipo()== 2)
//       {
//    	 comp = proyecto.getCompromisos()[0];
//         for(int j=0; j<= proyecto.getCompromisos().length-1; j++)
//		    {
//        	 if (j!=0)
//        	 {comp = comp + ","+proyecto.getCompromisos()[j];}
//        	}
//       }
	   try
	   {
		cn = cursor.getConnection(super.perfil);
		cn.setAutoCommit(false);
		ps = cn.prepareStatement(rb.getString("insertarproyecto"));
		ps.setString(i++, proyecto.getAno());
		ps.setString(i++, proyecto.getCodigo());
		ps.setString(i++, proyecto.getNombre());
		ps.setInt(i++, proyecto.getFacultad());
		ps.setInt(i++, proyecto.getProycurri());
		ps.setInt(i++, proyecto.getGrupo());
		ps.setInt(i++, proyecto.getInvestigador());
		ps.setInt(i++, proyecto.getHoras());
		ps.setInt(i++, proyecto.getDuracion());
		ps.setDouble(i++, Double.parseDouble(proyecto.getPresupuesto()));
		ps.setString(i++, proyecto.getPalabras());
		ps.setString(i++, proyecto.getObjetivo());
		ps.setString(i++, proyecto.getResumen());
		if(!comp.equals(""))
		{ps.setString(i++, comp);}
		else {ps.setNull(i++, Types.VARCHAR);}
		ps.setString(i++, proyecto.getObservacion());
		ps.setInt(i++, proyecto.getTipo());
		ps.setInt(i++, proyecto.getConvenio());
		ps.setString(i++, proyecto.getConvocatoria());
		ps.setInt(i++, proyecto.getEstado());
		ps.executeUpdate();
		if (proyecto.getNomCoinvest()!=null)
		{insertarCoinvestigadores(proyecto, cn);}
		insertarIdDocumentos(proyecto, cn);//guardar id del proyecto en la tabla de los documentos
		cn.commit();
		setIdProyecto(proyecto);
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

	 //Agregar documentos
	 public void insertarIdDocumentos(ProyectoAntiguoOBJ objProy, Connection cn){
		 PreparedStatement ps = null;
		 try
		   {
			ps = cn.prepareStatement(rb.getString("insertarIdDocs"));
			ps.executeUpdate();
		   } catch (SQLException e) {lanzaExcepcion(e);}
		     catch (Exception e) {lanzaExcepcion(e);}
			 finally {
					  try
					  {
					   ps.close();
					  }catch (SQLException e){lanzaExcepcion(e);}
				     }
	 }


	 private boolean setIdProyecto(ProyectoAntiguoOBJ proyecto) {
		  Connection cn = null;
		  PreparedStatement ps = null;
		  ResultSet rs = null;
		  objProyecto = proyecto;
		  boolean retorno = false;
	      try{
	          cn = cursor.getConnection(super.perfil);
	          ps = cn.prepareStatement(rb.getString("consultaIdProyecto"));
	          ps.setString(1, proyecto.getCodigo());
	          rs = ps.executeQuery();
	          while (rs.next())
	          {
	           objProyecto.setId(rs.getInt(1));
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


	//Agregar coinvestigadores al proyecto
	 public void insertarCoinvestigadores(ProyectoAntiguoOBJ proyecto, Connection cn)
	 {
		 PreparedStatement ps = null;
		 try
		 {
			 ps = cn.prepareStatement(rb.getString("insertarCoinvestigadores"));
			 int j=1;
			 for(int i=0; i<= proyecto.getNomCoinvest().length-1; i++)
			 {
				 ps.setString(j++, proyecto.getNomCoinvest()[i]);
				 ps.setString(j++, proyecto.getApellCoinvest()[i]);
				 ps.setInt(j++, proyecto.getTipoInv()[i]);
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

	//Almacena los documentos en el sistema
	 public boolean almacenarDatosArchivo(int idProy, DocumentosOBJ objDocs){
	boolean respuesta = false;
	Connection cn = null;
	PreparedStatement ps = null;
	String obser = objDocs.getObservacion();
    try{
        cn = cursor.getConnection(super.perfil);
        switch(Integer.parseInt(objDocs.getTipo())){
        case ParametrosOBJ.ArchivoPropuesta:
             ps = cn.prepareStatement(rb.getString("actualizarPropuesta"));
             ps.setString(1, objDocs.getNombre());
             if(!obser.equals(""))
     		 {ps.setString(2, obser);}
     		 else {ps.setNull(2, Types.VARCHAR);}
             ps.setInt(3, idProy);
        break;
        case ParametrosOBJ.ArchivoActaInicio:
             ps = cn.prepareStatement(rb.getString("actualizarActaInicio"));
             ps.setString(1, objDocs.getNombre());
             ps.setString(2, objDocs.getFecha());
             if(!obser.equals(""))
     		 {ps.setString(3, obser);}
     		 else {ps.setNull(3, Types.VARCHAR);}
             ps.setInt(4, idProy);
        break;
        case ParametrosOBJ.ArchivoContrato:
            ps = cn.prepareStatement(rb.getString("actualizarContrato"));
            ps.setString(1, objDocs.getNombre());
            ps.setString(2, objDocs.getFecha());
            ps.setInt(3, objDocs.getNumero());
            if(!obser.equals(""))
    		 {ps.setString(4, obser);}
    		 else {ps.setNull(4, Types.VARCHAR);}
            ps.setInt(5, idProy);
       break;
        case ParametrosOBJ.ArchivoInformeFinal:
            ps = cn.prepareStatement(rb.getString("actualizarInfoFinal"));
            ps.setString(1, objDocs.getNombre());
            ps.setString(2, objDocs.getFecha());
            if(!obser.equals(""))
    		 {ps.setString(3, obser);}
    		 else {ps.setNull(3, Types.VARCHAR);}
            ps.setInt(4, idProy);
       break;
        case ParametrosOBJ.ArchivoActaFinal:
            ps = cn.prepareStatement(rb.getString("actualizarActaFinal"));
            ps.setString(1, objDocs.getNombre());
            ps.setString(2, objDocs.getFecha());
            if(!obser.equals(""))
   		 {ps.setString(3, obser);}
   		 else {ps.setNull(3, Types.VARCHAR);}
            ps.setInt(4, idProy);
       break;
        case ParametrosOBJ.ArchivoAnexos:
            ps = cn.prepareStatement(rb.getString("actualizarAnexos"));
            ps.setString(1, objDocs.getNombre());
            if(!obser.equals(""))
    		 {ps.setString(2, obser);}
    		 else {ps.setNull(2, Types.VARCHAR);}
            ps.setInt(3, idProy);
       break;
        }
        ps.executeUpdate();
       }
    catch (SQLException e){lanzaExcepcion(e);}
    catch (Exception e){lanzaExcepcion(e);}
    finally {
  	       try{
  		       ps.close();
  		       cn.close();
  	          }
  	  catch (Exception e){lanzaExcepcion(e);}
        }
		return respuesta;
	}

	public ProyectoAntiguoOBJ consultarNombresArchivos(long idProy, ProyectoAntiguoOBJ objDocs) {
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
         try {
			  cn = cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("consultarArchivos"));
			  ps.setLong(1, idProy);
			  rs=ps.executeQuery();
			  while(rs.next())
			  {
				objDocs.setNombrePropuesta(rs.getString(1));
				objDocs.setObserPro(rs.getString(2));
				objDocs.setNombreActainicio(rs.getString(3));
				objDocs.setObserAcIn(rs.getString(4));
				objDocs.setNombreContrato(rs.getString(5));
				objDocs.setObserCon(rs.getString(6));
				objDocs.setNombreInfofinal(rs.getString(7));
				objDocs.setObserInFin(rs.getString(8));
				objDocs.setNombreActafinal(rs.getString(9));
				objDocs.setObserAcFin(rs.getString(10));
				objDocs.setNombreAnexos(rs.getString(11));
				objDocs.setObserAnex(rs.getString(12));
				objDocs.setFechaAcIn(rs.getString(13));
				objDocs.setFechaCon(rs.getString(14));
				objDocs.setFechaInFin(rs.getString(15));
				objDocs.setFechaAcFin(rs.getString(16));
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
		return objDocs;
	}

	public ProyectoAntiguoOBJ consultarPresupuesto(long idProy, ProyectoAntiguoOBJ objProyecto) {
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 BigDecimal presupuesto = BigDecimal.valueOf(0);
		 BigDecimal ejecutado = BigDecimal.valueOf(0);
		 BigDecimal saldo = BigDecimal.valueOf(0);
		 BigDecimal reintegro = BigDecimal.valueOf(0);
		 boolean sumar = false;

        try {
			  cn = cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("consultarPresupuesto"));
			  ps.setLong(1, idProy);
			  ps.setLong(2, idProy);
			  ps.setLong(3, idProy);
			  rs=ps.executeQuery();
			  while(rs.next())
			  {
				ejecutado = rs.getBigDecimal(1);
				if(ejecutado==null)
				{ejecutado=BigDecimal.valueOf(0);}
				reintegro = (rs.getBigDecimal(2));
				if(reintegro==null)
				{reintegro=BigDecimal.valueOf(0);}
				presupuesto = rs.getBigDecimal(3);
				sumar = true;
			  }
			  if(sumar==true){
			  saldo = presupuesto.subtract(ejecutado);
			  saldo = saldo.add(reintegro);
			  objProyecto.setPresupuesto(""+presupuesto);
			  objProyecto.setEjecutado(""+ejecutado);
			  objProyecto.setSaldo(""+saldo);
			  }else{ objProyecto.setEjecutado("0"); objProyecto.setSaldo(objProyecto.getPresupuesto());}
			  objProyecto.setNombreInvestigador(consultarNombreInvestigador(idProy, objProyecto));
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
		return objProyecto;
	}


	private String consultarNombreInvestigador(long idProy, ProyectoAntiguoOBJ objProyecto2) {
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String nombreInvest = null;
       try {
			  cn = cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("consultarNombreInvestigador"));
			  ps.setLong(1, idProy);
			  rs=ps.executeQuery();
			  while(rs.next())
			  {
				  nombreInvest = rs.getString(1)+" "+rs.getString(2);
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
		return nombreInvest;
	}

	public List consultarSumaRubros (long idProy){
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        Globales g=new Globales();
        List aprobados=new ArrayList();
        DatosAjax datosAprobados=null;
        try {
        	aprobados=consultarRubrosAprobados(idProy);
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarTotalRubros"));
            ps.setLong(1, idProy);
            rs = ps.executeQuery();
            while (rs.next()){
                objProyecto = new ProyectoAntiguoOBJ();
            	objProyecto.setIdRubro(rs.getInt(1));
            	objProyecto.setSumaRubro(rs.getString(2));
            	objProyecto.setNombreRubro(rs.getString(3));
            	objProyecto.setSaldoRubro("0");
                lista.add(objProyecto);
                if(objProyecto.getSumaRubro()!=null){
                	for(int i=0;i<aprobados.size();i++){
                		datosAprobados=(DatosAjax)aprobados.get(i);
                		if(datosAprobados.getCodigo()==objProyecto.getIdRubro()){
                			objProyecto.setSaldoRubro(""+(Long.parseLong(g.sinMoneda(datosAprobados.getValor()))-
                					Long.parseLong(objProyecto.getSumaRubro())));
                		}
                	}
                	objProyecto.setSumaRubro(g.moneda(objProyecto.getSumaRubro()));
                	objProyecto.setSaldoRubro(g.moneda(objProyecto.getSaldoRubro()));
                }
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
        return lista;
        }

	public List consultarSumaReintegros (int idProy){
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();

        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarTotalReintegros"));
            ps.setInt(1, idProy);
            rs = ps.executeQuery();
            while (rs.next()){
                objProyecto = new ProyectoAntiguoOBJ();
            	objProyecto.setSumaReintegros(rs.getString(1));
                lista.add(objProyecto);
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
        return lista;
        }

	public List consultarGastosRubro (long idProy, int idRubro){
        Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();

        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarGastosRubro"));
            ps.setInt(1, idRubro);
            ps.setLong(2, idProy);
            rs = ps.executeQuery();
            GastosOBJ objGastos = null;
            while (rs.next()){
                objGastos = new GastosOBJ();
                objGastos.setIdGastos(rs.getInt(1));
            	objGastos.setDescripcion(rs.getString(2));
            	objGastos.setFecha(rs.getString(3));
            	objGastos.setValor(rs.getString(4));
            	objGastos.setObserv(rs.getString(5));
                lista.add(objGastos);
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
        return lista;
        }

	//Consulta Proyectos por convocatoria
	public List consultarListaProyectos(ConsultaProyectosOBJ objConsulProy) {
		 Connection cn = null;
	     PreparedStatement ps = null;
	     ResultSet rs = null;
	     List lista = new ArrayList();
	     int i=1;
	        try {
	            cn = cursor.getConnection(super.perfil);
	            ps = cn.prepareStatement(rb.getString("consultarListaProyectos"));
	            ps.setString(i++, objConsulProy.getGrupo());
	            ps.setString(i++, objConsulProy.getTipo());
	            ps.setString(i++, objConsulProy.getCodigo());
	            ps.setString(i++, objConsulProy.getFacultad());
                ps.setString(i++, objConsulProy.getNombre());
                ps.setString(i++, objConsulProy.getEstado());
	            rs = ps.executeQuery();
	            ProyectoAntiguoOBJ objProyectos = null;
	            while (rs.next()){
	            	i=1;
	                objProyectos = new ProyectoAntiguoOBJ();
	                objProyectos.setId(rs.getInt(i++));
	                objProyectos.setCodigo(rs.getString(i++));
	                objProyectos.setNombreInvestigador(rs.getString(i++));
	                objProyectos.setNombre(rs.getString(i++));
	                objProyectos.setFlag(rs.getInt(i++));
	                lista.add(objProyectos);
	            }
	      //      System.out.println("encuentra "+lista.size()+" proyectos");
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
	        return lista;
	}

	//Consulta los proyectos digitalizados
	public ProyectoAntiguoOBJ consultarProyectoAntiguo(long idProyecto) {
		ProyectoAntiguoOBJ objProyecto=null;
		Connection cn = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	        try {
	             cn = cursor.getConnection(super.perfil);
	             ps = cn.prepareStatement(rb.getString("consultarProyecto"));
	             ps.setLong(1, idProyecto);
	             rs = ps.executeQuery();
	             while (rs.next()){
	                objProyecto=new ProyectoAntiguoOBJ();
	                objProyecto.setId(rs.getInt(1));
	                objProyecto.setAno(rs.getString(2));
	                objProyecto.setCodigo(rs.getString(3));
	                objProyecto.setNombre(rs.getString(4));
	                objProyecto.setFacultad(rs.getInt(5));
	                objProyecto.setProycurri(rs.getInt(6));
	                objProyecto.setGrupo(rs.getInt(7));
	                objProyecto.setInvestigador(rs.getInt(8));
	                objProyecto.setHoras(rs.getInt(9));
	                objProyecto.setDuracion(rs.getInt(10));
	                objProyecto.setPalabras(rs.getString(11));
	                objProyecto.setObjetivo(rs.getString(12));
	                objProyecto.setResumen(rs.getString(13));
                   
	                String compromisos =rs.getString(14);
                    if(rs.getString(14)!=null){
                    	String vComp[]=compromisos.split(",");
                    	objProyecto.setCompromisos(vComp);
                    }
                    else {String v_comp[]=null;
                    	  objProyecto.setCompromisos(v_comp);}
                    objProyecto.setObservacion(rs.getString(15));
                    objProyecto.setTipo(rs.getInt(16));
                    objProyecto.setConvenio(rs.getInt(17));
                    objProyecto.setConvocatoria(rs.getString(18));
                    objProyecto.setPresupuesto(rs.getString(19));
                    objProyecto.setEstado(rs.getInt(20));
                    objProyecto = consultarNombresArchivos(idProyecto, objProyecto);
                    objProyecto = consultarPresupuesto(idProyecto, objProyecto);
                    objProyecto.setNomFac(rs.getString(21));
                    objProyecto.setNomProyCurri(rs.getString(22));
                    objProyecto.setNomGrupo(rs.getString(23));
                    objProyecto.setNomInves(rs.getString(24)+" "+ rs.getString(25));
                    objProyecto.setFlag(rs.getInt(26));
	            }
	             objProyecto.setListaObservaciones(getListaObservaciones(idProyecto));
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
		return objProyecto;
	}

	public boolean actualizarProyecto(ProyectoAntiguoOBJ objProyecto) {
		boolean retorno = false;
		Connection cn=null;
		PreparedStatement ps=null;
		String comp = "";
	       if(objProyecto.getTipo()== 2)
	       {
	    	 comp = objProyecto.getCompromisos()[0];
	         for(int j=0; j<= objProyecto.getCompromisos().length-1; j++)
			    {
	        	 if (j!=0)
	        	 {comp = comp + ","+objProyecto.getCompromisos()[j];}
	        	}
	       }
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizarProyecto"));
			ps.setString(1,objProyecto.getAno());
			ps.setString(2,objProyecto.getCodigo());
			ps.setString(3,objProyecto.getNombre());
			ps.setInt(4,objProyecto.getFacultad());
			ps.setInt(5,objProyecto.getProycurri());
			ps.setInt(6,objProyecto.getGrupo());
			ps.setInt(7,objProyecto.getInvestigador());
			ps.setInt(8,objProyecto.getHoras());
			ps.setInt(9,objProyecto.getDuracion());
			ps.setString(10,objProyecto.getPalabras());
			ps.setString(11,objProyecto.getObjetivo());
			ps.setString(12,objProyecto.getResumen());
			if(!comp.equals(""))
			{ps.setString(13, comp);}
			else {ps.setNull(13, Types.VARCHAR);}
			ps.setString(14, objProyecto.getObservacion());
			ps.setInt(15, objProyecto.getTipo());
			ps.setInt(16, objProyecto.getConvenio());
			ps.setString(17, objProyecto.getConvocatoria());
			ps.setDouble(18, Double.parseDouble(objProyecto.getPresupuesto()));
			ps.setInt(19, objProyecto.getEstado());
            ps.setInt(20, objProyecto.getId());
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

	// Consulta el inventario del proyecto
	public List consultarInventario(long idProy) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarInventario"));
            ps.setLong(1, idProy);
            rs = ps.executeQuery();
            GastosOBJ objInv = null;
            while (rs.next()){
            	objInv = new GastosOBJ();
            	objInv.setIdElemento(rs.getInt(1));
            	objInv.setRubro(rs.getInt(2));
            	objInv.setDescripcion(rs.getString(3));
            	objInv.setValor(rs.getString(4));
                lista.add(objInv);
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
        return lista;
	}

	//Consulta el inventario asignado por rubro
	public List consultarInvRub(long idProy) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarInvRub"));
            ps.setLong(1, idProy);
            rs = ps.executeQuery();
            GastosOBJ objInv = null;
            while (rs.next()){
            	objInv = new GastosOBJ();
            	objInv.setRubro(rs.getInt(1));
            	objInv.setNombreRubro(rs.getString(2));
                lista.add(objInv);
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
        return lista;
	}


	public void insertarCoinvestigador(long idPro, CoinvestigadoresOBJ objCoin) {
		Connection cn = null;
		PreparedStatement ps = null;
		try
		   {
			cn = cursor.getConnection(super.perfil);
			ps = cn.prepareStatement(rb.getString("insertarCoinvestigador"));
			ps.setLong(1, idPro);
			ps.setString(2, objCoin.getNombres());
			ps.setString(3, objCoin.getApellidos());
			ps.setString(4, objCoin.getRol());
			ps.setString(5, objCoin.getFechaIngreso());
			ps.setString(6, objCoin.getFechaSalida());
			ps.executeUpdate();
		   } catch (SQLException e) {lanzaExcepcion(e);}
		     catch (Exception e) {lanzaExcepcion(e);}
			 finally {
					  try
					  {
					   ps.close();
					   cn.close();
					  }catch (SQLException e){lanzaExcepcion(e);}
				     }
		 }

	public boolean eliminarCoinvestigador(int idCoinv) {
		boolean retorno = false;
		Connection cn = null;
		PreparedStatement ps = null;
		try
		   {
			cn = cursor.getConnection(super.perfil);
			ps = cn.prepareStatement(rb.getString("eliminarCoinvestigador"));
			ps.setInt(1, idCoinv);
			ps.executeUpdate();
			retorno=true;
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

	public List consultarRubrosAprobados(long idProyecto) {
		List listaRubros = new ArrayList();
		 Connection cn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 Globales g=new Globales();
        try {
			  cn = cursor.getConnection(super.perfil);
			  ps = cn.prepareStatement(rb.getString("consultaRubrosAprobados"));
			  ps.setLong(1, idProyecto);
			  rs=ps.executeQuery();
			  while(rs.next()){
				  DatosAjax datos = new DatosAjax();
				  datos.setCodigo(rs.getInt(1));
				  datos.setNombre(rs.getString(2));
				  datos.setValor(rs.getString(3));
				  listaRubros.add(datos);
				  if(datos.getValor()!=null)
					  datos.setValor(g.moneda(datos.getValor()));
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

	public boolean insertarGasto(long idPro, GastosOBJ objGasto) {
		boolean retorno = false;
		Connection cn = null;
		PreparedStatement ps = null;
		try
		   {
			cn = cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps = cn.prepareStatement(rb.getString("insertarGasto"));
			ps.setLong(1, idPro);
			ps.setInt(2, objGasto.getRubro());
			ps.setString(3, objGasto.getDescripcion());
			ps.setString(4, objGasto.getFecha());
			ps.setInt(5, objGasto.getTipo());
			ps.setDouble(6, Double.parseDouble(objGasto.getValor()));
			ps.setString(7, objGasto.getObserv());
			ps.executeUpdate();
			if (objGasto.getTipo()==2)
			{insertarInventario(objGasto, cn);}
			cn.commit();
			retorno=true;
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

	private void insertarInventario(GastosOBJ objGasto, Connection cn) {

		PreparedStatement ps = null;
		 try
		 {
			 ps = cn.prepareStatement(rb.getString("insertarInventario"));
			 ps.setString(1, objGasto.getPlaca());
			 ps.setString(2, objGasto.getMarca());
			 ps.setString(3, objGasto.getSerie());
			 ps.setString(4, objGasto.getObservacion());
			 ps.executeUpdate();
		 } catch (SQLException e) {lanzaExcepcion(e);}
	     catch (Exception e) {lanzaExcepcion(e);}
		 finally {
				  try
				  {
				   ps.close();
				  }catch (SQLException e){lanzaExcepcion(e);}
			     }

	}

	public boolean eliminarGasto(int idGasto) {
		boolean retorno = false;
		Connection cn = null;
		PreparedStatement ps = null;
		try
		   {
			cn = cursor.getConnection(super.perfil);
			ps = cn.prepareStatement(rb.getString("eliminarGasto"));
			ps.setInt(1, idGasto);
			ps.executeUpdate();
			retorno=true;
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

	public List consultarCoinvestigadores(long idProy) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarCoinvestigadores"));
            ps.setLong(1, idProy);
            rs = ps.executeQuery();
            CoinvestigadoresOBJ objCoinv = null;
            while (rs.next()){
            	objCoinv = new CoinvestigadoresOBJ();
            	objCoinv.setId(rs.getInt(1));
            	objCoinv.setNombres(rs.getString(2));
            	objCoinv.setApellidos(rs.getString(3));
            	objCoinv.setRol(rs.getString(4));
            	objCoinv.setFechaIngreso(rs.getString(5));
            	objCoinv.setFechaSalida(rs.getString(6));
                lista.add(objCoinv);
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
        return lista;
	}

	public GastosOBJ consultarElemento(int idElemento) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        GastosOBJ objElemento = null;
        try {
             cn = cursor.getConnection(super.perfil);
             ps = cn.prepareStatement(rb.getString("consultarElemento"));
             ps.setInt(1, idElemento);
             rs = ps.executeQuery();
            while (rs.next()){
            	objElemento = new GastosOBJ();
            	objElemento.setDescripcion(rs.getString(1));
            	objElemento.setValor(rs.getString(2));
            	objElemento.setPlaca(rs.getString(3));
            	objElemento.setMarca(rs.getString(4));
            	objElemento.setSerie(rs.getString(5));
            	objElemento.setObservacion(rs.getString(6));
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
        return objElemento;
	}

	public String consultarNomRubro(int id) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String nom = null;
        try {
             cn = cursor.getConnection(super.perfil);
             ps = cn.prepareStatement(rb.getString("consultarNomRubro"));
             ps.setInt(1, id);
             rs = ps.executeQuery();
            while (rs.next()){
            	nom = rs.getString(1);
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
        return nom;
	}

	public CoinvestigadoresOBJ consultarCoinvestigador(int idCoinv) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        CoinvestigadoresOBJ objCoinv = new CoinvestigadoresOBJ();
        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarCoinvestigador"));
            ps.setInt(1, idCoinv);
            rs = ps.executeQuery();
            while (rs.next()){
            	objCoinv.setId(rs.getInt(1));
            	objCoinv.setNombres(rs.getString(2));
            	objCoinv.setApellidos(rs.getString(3));
            	objCoinv.setRol(rs.getString(4));
            	objCoinv.setFechaIngreso(rs.getString(5));
            	objCoinv.setFechaSalida(rs.getString(6));
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
        return objCoinv;
	}

	public boolean actualizarCoinvestigador(int id, CoinvestigadoresOBJ objCoin) {
		boolean retorno = false;
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizarCoinvestigador"));
			ps.setString(1,objCoin.getNombres());
			ps.setString(2,objCoin.getApellidos());
			ps.setString(3,objCoin.getRol());
			ps.setString(4,objCoin.getFechaIngreso());
			ps.setString(5,objCoin.getFechaSalida());
			ps.setInt(6,id);
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

	public List consultarCambios(long idProy) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarCambios"));
            ps.setLong(1, idProy);
            rs = ps.executeQuery();
            CambiosOBJ objCamb = null;
            while (rs.next()){
            	objCamb = new CambiosOBJ();
            	objCamb.setId(rs.getInt(1));
            	objCamb.setSolicitud(rs.getString(2));
            	objCamb.setRespuesta(rs.getString(3));
            	objCamb.setAprobacion(rs.getString(4));
            	objCamb.setTipo(rs.getInt(5));
            	objCamb.setObservaciones(rs.getString(6));
                lista.add(objCamb);
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
        return lista;
	}

	public List consultarTipoCambio(long idProy) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarTipoCambio"));
            ps.setLong(1, idProy);
            rs = ps.executeQuery();
            CambiosOBJ objCamb = null;
            while (rs.next()){
            	objCamb = new CambiosOBJ();
            	objCamb.setTipo(rs.getInt(1));
                lista.add(objCamb);
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
        return lista;
	}

	public boolean eliminarCambio(int id) {
		boolean retorno = false;
		Connection cn = null;
		PreparedStatement ps = null;
		try
		   {
			cn = cursor.getConnection(super.perfil);
			ps = cn.prepareStatement(rb.getString("eliminarCambio"));
			ps.setInt(1, id);
			ps.executeUpdate();
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

	public boolean insertarCambio(long idPro, CambiosOBJ objCam) {
		boolean retorno = false;
		Connection cn = null;
		PreparedStatement ps = null;
		try
		   {
			cn = cursor.getConnection(super.perfil);
			ps = cn.prepareStatement(rb.getString("insertarCambio"));
			ps.setLong(1, idPro);
			ps.setString(2, objCam.getSolicitud());
			ps.setString(3, objCam.getRespuesta());
			ps.setString(4, objCam.getAprobacion());
			ps.setInt(5, objCam.getTipo());
			ps.setString(6, objCam.getObservaciones());
			ps.executeUpdate();
			retorno=true;
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

	public List consultarDevoluciones(long idProy) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarDevoluciones"));
            ps.setLong(1, idProy);
            rs = ps.executeQuery();
            GastosOBJ objInv = null;
            while (rs.next()){
            	objInv = new GastosOBJ();
            	objInv.setIdElemento(rs.getInt(1));
            	objInv.setRubro(rs.getInt(2));
            	objInv.setDescripcion(rs.getString(3));
            	objInv.setValor(rs.getString(4));
            	objInv.setDevuelto(rs.getBoolean(5));
            	objInv.setPlaca(rs.getString(6));
                lista.add(objInv);
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
        return lista;
	}

	public boolean actualizarDevoluciones(long id, DevolucionesOBJ objDevol) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno = false;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizarFalse"));
			ps.setLong(1,id);
			ps.executeUpdate();
			if (objDevol.getDevueltos()!= null){
			if (objDevol.getDevueltos().length > 0 ){
			int idElement = 0;
		         for(int j=0; j<= objDevol.getDevueltos().length-1; j++)
				    {
		        	 idElement = objDevol.getDevueltos()[j];
		        	 actualizarDevolucion(idElement);
				    }
			}
			}
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

	private void actualizarDevolucion(int idElement) {
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizarDevoluciones"));
			ps.setBoolean(1,true);
			ps.setInt(2,idElement);
			ps.executeUpdate();
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}

	}

	public List consultarProrrogas(long idProy) {
		Connection cn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            cn = cursor.getConnection(super.perfil);
            ps = cn.prepareStatement(rb.getString("consultarProrrogas"));
            ps.setLong(1, idProy);
            rs = ps.executeQuery();
            CambiosOBJ objCamb = null;
            while (rs.next()){
            	objCamb = new CambiosOBJ();
            	objCamb.setSolicitud(rs.getString(1));
            	objCamb.setRespuesta(rs.getString(2));
            	objCamb.setAprobacion(rs.getString(3));
            	objCamb.setObservaciones(rs.getString(4));
                lista.add(objCamb);
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
        return lista;
	}

	public boolean actualizarFlag(long idPro, String flag) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno = false;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizarFlag"));
			ps.setInt(1,Integer.parseInt(flag));
			ps.setLong(2,idPro);
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

	public boolean actualizarEstadoProyecto(long idPro, String estado) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno = false;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizarEstado"));
			ps.setInt(1,Integer.parseInt(estado));
			ps.setLong(2,idPro);
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
	
	public boolean insertaObservacion(long idPro, String observacion,long usuario) {
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

	public int getFlag(long idPro) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int retorno = 0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getFlag"));
			ps.setLong(1,idPro);
			rs=ps.executeQuery();
			while(rs.next()){
				retorno = rs.getInt(1);
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
		return retorno;
	}
	
	public int getEstado(long idPro) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int retorno = 0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getEstado"));
			ps.setLong(1,idPro);
			rs=ps.executeQuery();
			while(rs.next()){
				retorno = rs.getInt(1);
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
		return retorno;
	}
	
	public List getListaObservaciones(long idPro) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		GeneralOBJ observ=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getObservaciones"));
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
			//	System.out.println("encuentra observaciones");
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

	public boolean eliminaObservacion(String idObserv) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno = false;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("eliminaObservacion"));
			ps.setInt(1,Integer.parseInt(idObserv));
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

	public boolean insertaRubrosAprobrados(long idProyecto,String [] idRubros,String []valores){
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno = false;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("eliminaRubrosAprobados"));
			ps.setLong(1, idProyecto);
			ps.executeUpdate();
			if(idRubros!=null){
				ps=cn.prepareStatement(rb.getString("insertaRubrosAprobados"));
				for(int j=0;j<idRubros.length;j++){
					i=1;
					ps.setLong(i++, idProyecto);
					ps.setInt(i++, Integer.parseInt(idRubros[j]));
					ps.setDouble(i++, Double.parseDouble(valores[j]));
					ps.addBatch();
				}
				ps.executeBatch();
			}
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
	
	public List<ConvocatoriaOBJ> consultarConvocatorias(){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ConvocatoriaOBJ> lista=new ArrayList<ConvocatoriaOBJ>(); 
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaConvocatorias"));
			System.out.println("consulta "+ps.toString());
			rs=ps.executeQuery();
			while (rs.next()) {
				int i=1;
				ConvocatoriaOBJ conv=new ConvocatoriaOBJ();
				conv.setConvNombre(rs.getString(i++));
				conv.setConvId(rs.getInt(i++));
				lista.add(conv);
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}		
		return lista;
	}
	
}