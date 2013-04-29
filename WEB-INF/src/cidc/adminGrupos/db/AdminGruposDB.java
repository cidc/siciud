package cidc.adminGrupos.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.sun.org.apache.bcel.internal.generic.SALOAD;



import cidc.adminGrupos.obj.FiltroPagWebGrupo;
import cidc.adminGrupos.obj.FiltroPagWebSemillero;
import cidc.adminGrupos.obj.FiltroPersona;
import cidc.adminGrupos.obj.GrupoInvestigacion;
import cidc.adminGrupos.obj.Integrante;
import cidc.adminGrupos.obj.ListaGrupos;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.obj.Globales;
import cidc.general.obj.Parametros;

import cidc.inscripcionConv.obj.GruposOBJ;
import cidc.inscripcionConv.obj.ProyCurOBJ;
import cidc.adminGrupos.obj.AreasConocimiento;



public class AdminGruposDB extends BaseDB{

	private List listaProyectos =new ArrayList();

	public AdminGruposDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.adminGrupos.consultas");
	}

	public List AjaxProyectoCur(int facultad) {
		List l=new ArrayList();
		ProyCurOBJ proyCurOBJ=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn = cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ajaxProyectCur"));
			ps.setLong(1,facultad);
			rs=ps.executeQuery();
			while(rs.next()){
				proyCurOBJ=new ProyCurOBJ();
				proyCurOBJ.setCodigo(rs.getInt(1));
				proyCurOBJ.setNombre(rs.getString(2));
				l.add(proyCurOBJ);
			}
		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
	//	System.out.println(l.size());
		return l;
	}

	public List AjaxGruposInvestigacion(int facultad,int tipo){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ajaxGrupos"));
			ps.setLong(1,facultad);
			ps.setLong(2,tipo);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				GruposOBJ gruposOBJ=new GruposOBJ();
				gruposOBJ.setCodigo(rs.getInt(i++));
				gruposOBJ.setNombre(rs.getString(i++));
				l.add(gruposOBJ);
			}
		} catch (SQLException e) {
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

	public long getIdDirecPostgresql(String cedula){
		long retorno=-1;
		int i=1;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("buscaDirectorPostgres"));
			ps.setString(i++, cedula);
			rs=ps.executeQuery();
			while(rs.next()){
				retorno=rs.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retorno;
	}

	public boolean nuevoGrupo(GrupoInvestigacion grupoInvestigacion) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Integrante integrante=null;
		int flag=2;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			//***************************Buscar Director en Postgersql******************************************
			grupoInvestigacion.setIdDir(getIdDirecPostgresql(grupoInvestigacion.getCedDirector()));
			//**************************************************************************************************
			integrante=consultaDatosPersona(grupoInvestigacion.getCedDirector(),grupoInvestigacion.getCodUdDirector(),0,(int)(grupoInvestigacion.getIdDir()));
			if(grupoInvestigacion.getIdDir()!=-1)
				integrante.setId(grupoInvestigacion.getIdDir());
			//***************************Insertar Nueva persona Director en Postgersql******************************************
			if(grupoInvestigacion.getIdDir()==-1){
				insertarPersona(integrante);
				flag=1;
			}
//			**************************************************************************************************
			i=1;
			ps=cn.prepareStatement(rb.getString("insertaGrupo"+flag));
			ps.setString(i++, grupoInvestigacion.getNombre().toUpperCase());
			ps.setString(i++, grupoInvestigacion.getSiglas().toUpperCase());
			ps.setString(i++, grupoInvestigacion.getWeb());
			ps.setString(i++, grupoInvestigacion.getMail());
			ps.setString(i++, grupoInvestigacion.getFechaCreacion().replace('/', '-'));
			ps.setInt(i++, grupoInvestigacion.getFacultad());
			ps.setInt(i++, grupoInvestigacion.getTipo());
			ps.setInt(i++, grupoInvestigacion.getCategoria());
			ps.setInt(i++, grupoInvestigacion.getEstado());
			if(flag==2)
				ps.setLong(i++, grupoInvestigacion.getIdDir());
			ps.setString(i++, grupoInvestigacion.getObservaciones());
			ps.setString(i++, grupoInvestigacion.getDescripcion());
			ps.setString(i++, grupoInvestigacion.getGrupLac());
			ps.setString(i++, grupoInvestigacion.getMision());
			ps.setString(i++, grupoInvestigacion.getVision());
			ps.setString(i++, grupoInvestigacion.getCodColciencias());
			ps.executeUpdate();
			insertarDirectorGrupo(cn, integrante, flag);
			cn.commit();
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
	
	public List getListaGrupos(String facultad, String tipo, int desde,int regporpag) {
		List listaGrupos= new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		GrupoInvestigacion grupo=null;
		int i=1;
		if(facultad!=null){
			try {
				cn=cursor.getConnection(super.perfil); 
				
					ps=cn.prepareStatement(rb.getString("listaGrupos"));
					ps.setLong(i++, Long.parseLong(facultad));
					ps.setLong(i++, Integer.parseInt(tipo));
					ps.setInt(i++, regporpag);
					ps.setInt(i++, desde);
					
				
				rs=ps.executeQuery();
				while(rs.next()){
					i=1;
					grupo=new GrupoInvestigacion();
					grupo.setCodigo(rs.getLong(i++));
					grupo.setNombre(rs.getString(i++).toLowerCase());
					grupo.setDirNombre(rs.getString(i++));
					grupo.setEstado(rs.getInt(i++));
					listaGrupos.add(grupo);
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
		}
		return listaGrupos;
	}
	
	
	public int getNumGrupos(String facultad, String tipo) {
		
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		int retorno=0;
		if(facultad!=null){
			try {
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("numGrupos"));
				ps.setLong(i++, Long.parseLong(facultad));
				ps.setLong(i++, Integer.parseInt(tipo));
				rs=ps.executeQuery();
				while(rs.next()){
					retorno=rs.getInt(1);					
					
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
		}
		return retorno;
	}
	
	public GrupoInvestigacion getVerGrupo(String id, Object listaGrupos) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		GrupoInvestigacion grupo=new GrupoInvestigacion();
		grupo.setModificable(false);
		ListaGrupos g=null;
		List lista=(List)listaGrupos;
		for(int i=0;i<lista.size();i++){
			g=(ListaGrupos)lista.get(i);
			if(Integer.parseInt(id)==g.getIdGrupo() && g.isDirector()){
				grupo.setModificable(true);
				break;
			}
		}
		int i=1;

			try {
				cn=cursor.getConnection(super.perfil);
				cn.setAutoCommit(false);
				ps=cn.prepareStatement(rb.getString("verGrupo"));
				ps.setLong(1, Long.parseLong(id));
				rs=ps.executeQuery();
				while(rs.next()){
					grupo.setCodigo(rs.getLong(i++));
					grupo.setNombre(rs.getString(i++));
					grupo.setSiglas(rs.getString(i++));
					grupo.setWeb(rs.getString(i++));
					grupo.setMail(rs.getString(i++));
					grupo.setFechaCreacion(rs.getString(i++));
					grupo.setFacultad(rs.getInt(i++));
					grupo.setTipo(rs.getInt(i++));
					grupo.setCategoria(rs.getInt(i++));
					grupo.setIdDir(rs.getLong(i++));
					grupo.setDirNombre(rs.getString(i++));
					grupo.setDirApellido(rs.getString(i++));
					grupo.setEstado(rs.getInt(i++));
					grupo.setObservaciones(rs.getString(i++));
					grupo.setMovilidad(rs.getBoolean(i++));
					grupo.setDescripcion(rs.getString(i++));
					grupo.setGrupLac(rs.getString(i++));
					grupo.setMision(rs.getString(i++));
					grupo.setVision(rs.getString(i++));
					grupo.setCodColciencias(rs.getString(i++));
					grupo.setCodproyCurr(rs.getInt(i++));
					grupo.setCodAreaSNIES(rs.getInt(i++));
					grupo.setModificable(false);

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

		return grupo;
	}
	public GrupoInvestigacion getVerGrupo(String id) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		GrupoInvestigacion grupo=null;
		ListaGrupos g=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("verGrupo"));
			ps.setLong(1, Long.parseLong(id));
			System.out.println("consulta -->"+ps.toString());
			rs=ps.executeQuery();
			while(rs.next()){
				grupo=new GrupoInvestigacion();
				grupo.setCodigo(rs.getLong(i++));
				grupo.setNombre(rs.getString(i++));
				grupo.setSiglas(rs.getString(i++));
				grupo.setWeb(rs.getString(i++));
				grupo.setMail(rs.getString(i++));
				grupo.setFechaCreacion(rs.getString(i++));
				grupo.setFacultad(rs.getInt(i++));				
				grupo.setTipo(rs.getInt(i++));
				grupo.setCategoria(rs.getInt(i++));
				grupo.setIdDir(rs.getLong(i++));
				grupo.setDirNombre(rs.getString(i++));
				grupo.setDirApellido(rs.getString(i++));
				grupo.setEstado(rs.getInt(i++));
				grupo.setObservaciones(rs.getString(i++));
				grupo.setMovilidad(rs.getBoolean(i++));
				grupo.setDescripcion(rs.getString(i++));
				grupo.setGrupLac(rs.getString(i++));
				grupo.setMision(rs.getString(i++));
				grupo.setVision(rs.getString(i++));
				grupo.setCodColciencias(rs.getString(i++));
				grupo.setCodproyCurr(rs.getInt(i++));
				grupo.setCodAreaSNIES(rs.getInt(i++));
				grupo.setModificable(false);
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

		return grupo;
	}
	public boolean ActualizaGrupo(GrupoInvestigacion grupo) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null,ps2=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ActualizaGrupo"));
			ps.setInt(i++,grupo.getFacultad());
			ps.setString(i++,grupo.getNombre());
			ps.setString(i++,grupo.getSiglas().toUpperCase());
			ps.setInt(i++,grupo.getTipo());
			ps.setString(i++,grupo.getFechaCreacion());
			ps.setInt(i++,grupo.getCategoria());
			ps.setString(i++,grupo.getMail());
			ps.setString(i++,grupo.getWeb());
			ps.setInt(i++,grupo.getEstado());
			ps.setString(i++,grupo.getObservaciones());
			ps.setBoolean(i++,grupo.isMovilidad());			
			ps.setString(i++,grupo.getDescripcion());
			ps.setString(i++,grupo.getGrupLac());
			ps.setString(i++,grupo.getMision());
			ps.setString(i++,grupo.getVision());
			ps.setString(i++,grupo.getCodColciencias());
			ps.setInt(i++, grupo.getCodproyCurr());
			ps.setInt(i++, grupo.getCodAreaSNIES());			
			ps.setLong(i++,grupo.getCodigo());	
			
			i=1;
			ps.executeUpdate();

			ps2=cn.prepareStatement(rb.getString("ActualizaDirector"));
			ps2.setString(i++,grupo.getDirNombre());
			ps2.setString(i++,grupo.getDirApellido());
			ps2.setLong(i++,grupo.getIdDir());
			ps2.executeUpdate();
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

	public boolean ActualizaGrupo2(GrupoInvestigacion grupo) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ActualizaGrupo2"));
			ps.setString(i++,grupo.getMail());
			ps.setString(i++,grupo.getWeb());
			ps.setString(i++,grupo.getDescripcion());
			ps.setString(i++,grupo.getGrupLac());
			ps.setInt(i++, grupo.getCodproyCurr());
			ps.setInt(i++, grupo.getCodAreaSNIES());		
			ps.setLong(i++,grupo.getCodigo());
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


	public List buscaIntegrantesGrupo(long idGrupo) {
		List listaIntegrantes=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Integrante integrante=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaIntegrantes"));
			ps.setLong(i++, idGrupo);
			rs=ps.executeQuery();
			System.out.println("lista integrantes: "+ps.toString());
			while(rs.next()){
				i=1;
				integrante=new Integrante();
				integrante.setId(rs.getLong(i++));
				integrante.setNombres(rs.getString(i++));
				integrante.setApellidos(rs.getString(i++));
				integrante.setPapel(rs.getInt(i++));
				integrante.setFechaSalida(rs.getString(i++));
				integrante.setCodigoUd(rs.getString(i++));
				integrante.setCedula(rs.getString(i++));
				listaIntegrantes.add(integrante);
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
		return listaIntegrantes;
	}
	public Integrante verIntegranteGrupo(String idIntegrante) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Integrante integrante=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("verIntegrante"));
			ps.setLong(1, Long.parseLong(idIntegrante));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				integrante=new Integrante();
				integrante.setId(rs.getLong(i++));
				integrante.setCodigoUd(rs.getString(i++));
				integrante.setTipoCed(rs.getInt(i++));
				integrante.setCedula(rs.getString(i++));
				integrante.setDeCed(rs.getString(i++));
				integrante.setNombres(rs.getString(i++));
				integrante.setApellidos(rs.getString(i++));
				integrante.setMail(rs.getString(i++));
				integrante.setTel1(rs.getString(i++));
				integrante.setCel1(rs.getString(i++));
				integrante.setCodFacultad(rs.getInt(i++));
				integrante.setIdProyectoCurr(rs.getInt(i++));
				integrante.setPapel(rs.getInt(i++));
				integrante.setFechaVinculacion(rs.getString(i++));
				integrante.setFechaSalida(rs.getString(i++));
				integrante.setUltimaActua(rs.getString(i++));
				integrante.setCvlac(rs.getString(i++));
				integrante.setCodareasnies(rs.getInt(i++));
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
		return integrante;
	}
	public boolean nuevoIntegranteGrupo(Integrante integrante) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno=false;
		int i=1;
		try {
	//		System.out.println("---cod persona-->"+integrante.getCodigoUd());
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("nuevaPersona"));
			ps.setInt(i++,integrante.getTipoCed());
			ps.setString(i++,integrante.getCedula());
			ps.setString(i++,integrante.getDeCed());
			ps.setString(i++,integrante.getCodigoUd());
			ps.setString(i++,integrante.getNombres());
			ps.setString(i++,integrante.getApellidos());
			ps.setString(i++,integrante.getMail());
			ps.setString(i++,integrante.getTel1());
			ps.setString(i++,integrante.getCel1());
			ps.setString(i++,integrante.getCvlac());
			ps.executeUpdate();
			i=1;
			ps=cn.prepareStatement(rb.getString("nuevoIntegrante"));
			ps.setInt(i++,integrante.getCodFacultad());
			ps.setLong(i++,integrante.getIdGrupo());
			ps.setLong(i++,integrante.getIdProyectoCurr());
			ps.setInt(i++,integrante.getPapel());
			ps.setString(i++,integrante.getFechaVinculacion());
			ps.executeUpdate();
			cn.commit();
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


	public boolean eliminaIntegranteGrupo(Integrante integrante,long grupo) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno=false;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("eliminaIntegrante"));
			ps.setLong(1, integrante.getId());
			ps.setLong(2, grupo);
			ps.executeUpdate();
			cn.commit();
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

	public boolean habilitaIntegrante(Integrante integrante) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		Globales global=new Globales();
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("reintegrarIntegrante"));
			ps.setString(1, global.getFechaSimpleHoy());
			ps.setLong(2, integrante.getId());
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

	public List buscaPersona(FiltroPersona filtroPersona) {
		List listaPersonas=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Integrante integrante=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("buscaPersona"));

	/*		System.out.println(filtroPersona.getNombres());
			System.out.println(filtroPersona.getApellidos());
			System.out.println(filtroPersona.getMail());
			System.out.println(filtroPersona.getCedula());*/

			ps.setString(i++, filtroPersona.getNombres());
			ps.setString(i++, filtroPersona.getApellidos());
			ps.setString(i++, filtroPersona.getMail());
			ps.setString(i++, filtroPersona.getCedula());
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				integrante=new Integrante();
				integrante.setId(rs.getLong(i++));
				integrante.setNombres(rs.getString(i++));
				integrante.setApellidos(rs.getString(i++));
				integrante.setNombreFacultad(rs.getString(i++));
				integrante.setIdGrupo(rs.getInt(i++));
				integrante.setNombreGrupo(rs.getString(i++));
				listaPersonas.add(integrante);
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
		return listaPersonas;
	}

	public Integrante verPersona(String idPersona,String idGrupo) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Integrante integrante=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("verPersona"));
			ps.setLong(1, Long.parseLong(idPersona));
			ps.setLong(2, Long.parseLong(idGrupo));
			System.out.println("----->"+ps.toString());			
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				integrante=new Integrante();
				integrante.setId(rs.getLong(i++));
				integrante.setCodigoUd(rs.getString(i++));
				integrante.setTipoCed(rs.getInt(i++));
				integrante.setCedula(rs.getString(i++));
				integrante.setDeCed(rs.getString(i++));
				integrante.setNombres(rs.getString(i++));
				integrante.setApellidos(rs.getString(i++));
				integrante.setMail(rs.getString(i++));
				integrante.setTel1(rs.getString(i++));
				integrante.setCel1(rs.getString(i++));
				integrante.setCodFacultad(rs.getInt(i++));
				integrante.setCodproyCurr(rs.getInt(i++));
				integrante.setPapel(rs.getInt(i++));
				integrante.setFechaVinculacion(rs.getString(i++));
				integrante.setFechaSalida(rs.getString(i++));
				integrante.setGrupo(rs.getLong(i++));				
				integrante.setNombreFacultad(rs.getString(i++));
				integrante.setDeCed(rs.getString(i++));
				integrante.setNombreTipoCed(rs.getString(i++));
				integrante.setNombreProyCurr(rs.getString(i++));
				integrante.setFechaNacimiento(rs.getString(i++));
				integrante.setCel2(rs.getString(i++));
				integrante.setTel2(rs.getString(i++));
				integrante.setMailInst(rs.getString(i++));
				integrante.setCvlac(rs.getString(i++));
				integrante.setNombreTipoPer(rs.getString(i++));
				integrante.setCodareasnies(rs.getInt(i++));
				integrante.setGenero(rs.getInt(i++));
				integrante.setNombreGenero(rs.getString(i++));
				integrante.setProyectosCurriculares(AjaxProyectoCur(integrante.getCodFacultad()));
			}
			integrante=consultarInfoIntegrante(cn,integrante,idPersona);
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return integrante;
	}

	public List getGruposPersona(long cedPersona) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List listadoGrupos=new ArrayList();
		ListaGrupos grupo=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("gruposPersona"));
			ps.setLong(1, cedPersona);
			rs=ps.executeQuery();
		//	System.out.println("***-->consulta lista grupos="+ps);
			while(rs.next()){
				i=1;
				grupo=new ListaGrupos();
				grupo.setIdGrupo(rs.getInt(i++));
				grupo.setNombreGrupo(rs.getString(i++));
				grupo.setTipoGrupoTxt(rs.getString(i++));
				grupo.setRolTxt(rs.getString(i++));
				grupo.setEstadoGrupo(rs.getBoolean(i++));
				grupo.setEstadoIntegrante(rs.getBoolean(i++));
				grupo.setCategoriaGrupo(rs.getInt(i++));
				listadoGrupos.add(grupo);
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
		return listadoGrupos;
	}


	//********************************************muevos métodos de adminsitración Oracle**********************
	public List getCodigosIntegrante(String cedula){
		List listaCodigos=new ArrayList();
		PreparedStatement st=null;
		ResultSet rs=null;
		Connection cn=null;
		try {
			cn=cursor.abrirOracle();
			st=cn.prepareStatement(rb.getString("buscarCodigosUDPersona"));
			st.setString(1,cedula);
			rs=st.executeQuery();
			while(rs.next()){
				listaCodigos.add(rs.getString(1));
			}
		}catch(SQLException e){			
			lanzaExcepcion(e);
		}catch(Exception e){
		//	System.out.println("--Problemas buscando los códugos UD de la cédula--->"+cedula);
			//lanzaExcepcion(e);
		}
		return listaCodigos;
	}

	public List consultarIntegrantes(String cedula, String codUd){
		Integrante datos=null;
		List<Integrante> integrantes=new ArrayList<Integrante>();
		PreparedStatement st=null;
		ResultSet rs=null;
		int i=1;
			try {
				if(cedula.equals("")){
					cedula="%";
				}else{
					cedula="%"+cedula+"%";
				}
				if(codUd.equals("")){
					codUd="%";
				}else{
					codUd="%"+codUd+"%";
				}
				Connection cn=cursor.abrirOracle();
				
				st=cn.prepareStatement(rb.getString("consultarIntegrantes"));
				st.setString(1,cedula);
				st.setString(2,codUd);
				rs=st.executeQuery();

				while(rs.next()){
					i=1;
					datos=new Integrante();
					datos.setCedula(rs.getString(i++));
					datos.setCodigoUd(rs.getString(i++));
					datos.setNombres(rs.getString(i++));
					datos.setApellidos(rs.getString(i++));
					datos.setCodFacultad(rs.getInt(i++));
					datos.setNombreFacultad(rs.getString(i++));
					datos.setCodproyCurr(rs.getInt(i++));
					datos.setNombreProyCurr(rs.getString(i++));
					datos.setNombreTipoPer(rs.getString(i++));


					integrantes.add(datos);
				}
				cerrar(rs);cerrar(st);cerrar(cn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return integrantes;
	}

	public Integrante consultarOracle(String carnetUd){
		Integrante datos=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		Connection cn=null;
		int i=1;
			try {
				cn=cursor.abrirOracle();
				if(cn!=null){
				st=cn.prepareStatement(rb.getString("consultarDatosIntegranteOracle"));
				st.setDouble(1, Double.parseDouble(carnetUd));
//				System.out.println("---stOracle-->"+st);
				rs=st.executeQuery();
				while(rs.next()){
					i=1;
					datos=new Integrante();
					datos.setCodigoUd(carnetUd);
					datos.setCodFacultad(rs.getInt(i++));
					datos.setNombreFacultad(rs.getString(i++));
					datos.setCodproyCurr(rs.getInt(i++));
					datos.setNombreProyCurr(rs.getString(i++));
					datos.setEstado(rs.getInt(i++));
					datos.setNombreEstado(rs.getString(i++));
					datos.setTipoPer(rs.getInt(i++));
					datos.setNombreTipoPer(rs.getString(i++));
					datos.setTipoCed(rs.getInt(i++));
					datos.setNombreTipoCed(rs.getString(i++));
					datos.setContrasena(rs.getString(i++));
					datos.setCedula(rs.getString(i++));
					datos.setDeCed(rs.getString(i++));
					datos.setNombres(rs.getString(i++));
					datos.setApellidos(rs.getString(i++));
					datos.setTel1(rs.getString(i++));
					datos.setCel1(rs.getString(i++));
					datos.setMailInst(rs.getString(i++));
					datos.setMail(rs.getString(i++));
					datos.setFechaNacimiento(rs.getString(i++));
					datos.setFechaIngreso(rs.getString(i++));
					datos.setFechaSalida(rs.getString(i++));
					datos.setGenero(rs.getInt(i++));
					datos.setNombreGenero(rs.getString(i++));
					if(datos.getFechaNacimiento()!=null){
						datos.setFechaNacimiento(datos.getFechaNacimiento().substring(0,datos.getFechaNacimiento().lastIndexOf(" ")));
					}

				}
				cerrar(rs);cerrar(st);cerrar(cn);
				}
			}catch (SQLException e) {
				lanzaExcepcion(e);
			} 
			catch (Exception e) {
				lanzaExcepcion(e);
			}

		return datos;
	}

	public Integrante consultarPostgresql(int id,long idGrupo){
		Connection cn=null;
		Integrante datos=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
			try {
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("consultarDatosPersonaPost"));
				ps.setInt(1, id);
				System.out.println("---stpostgers-->"+ps.toString());
				rs=ps.executeQuery();
				while(rs.next()){
					i=1;
					datos=new Integrante();
					datos.setId(rs.getLong(i++));
					datos.setCodigoUd(rs.getString(i++));					
					datos.setCedula(rs.getString(i++));
					datos.setTipoCed(rs.getInt(i++));
					datos.setNombreTipoCed(rs.getString(i++));
					datos.setNombres(rs.getString(i++));
					datos.setApellidos(rs.getString(i++));
					datos.setMail(rs.getString(i++));
					datos.setTel1(rs.getString(i++));
					datos.setCel1(rs.getString(i++));
					datos.setCel2(rs.getString(i++));
					datos.setDeCed(rs.getString(i++));
					datos.setDireccion(rs.getString(i++));
					datos.setTel2(rs.getString(i++));
					datos.setMailInst(rs.getString(i++));
					datos.setFechaNacimiento(rs.getString(i++));
					datos.setFechaIngreso(rs.getString(i++));
					datos.setFechaSalida(rs.getString(i++));
					datos.setGenero(rs.getInt(i++));
					datos.setNombreGenero(rs.getString(i++));
					datos.setTipoPer(rs.getInt(i++));
					datos.setNombreTipoPer(rs.getString(i++));
					datos.setPerActualizado(rs.getBoolean(i++));					
					datos.setCvlac(rs.getString(i++));
					datos.setCodareasnies(rs.getInt(i++));
					datos.setPapel(rs.getInt(i++));
				}
				if(datos!=null)
					consultarInfoIntegrante(cn,datos,String.valueOf(id));

			} catch (Exception e) {
				e.printStackTrace();
			}
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);

		return datos;
	}
	public Integrante consultarInfoIntegrante(Connection cn,Integrante datos,String idPersona){
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
			try {
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("consultarDatosIntegranteGrupo"));
				ps.setInt(1, Integer.parseInt(idPersona));
		//		ps.setLong(2, idGrupo);
				System.out.println("---stIntegrante :) -->"+ps.toString());
				rs=ps.executeQuery();
				while(rs.next()){
					i=1;
					if(datos==null)	
						datos = new Integrante();
					datos.setCodFacultad(rs.getInt(i++));
					datos.setNombreFacultad(rs.getString(i++));
					datos.setCodproyCurr(rs.getInt(i++));
					datos.setNombreProyCurr(rs.getString(i++));					
					datos.setPapel(rs.getInt(i++));
					datos.setFechaVinculacion(rs.getString(i++));
					datos.setFechaSalidaGrupo(rs.getString(i++));
					datos.setUltimaActua(rs.getString(i++));
					System.out.println("salida"+datos.getFechaSalidaGrupo());
					System.out.println("consuta "+ps.toString());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			cerrar(rs);
			cerrar(ps);

		return datos;
	}

	public Integrante consultaDatosPersona(String cedula,String codUD,long idGrupo, int id){
		Integrante objA=null;
		Integrante objB=null;
		System.out.println("id persona" + id);
		objA=consultarPostgresql(id,idGrupo);
		objB=consultarOracle(codUD);
	//	System.out.println("--consultando datos de cedula= -->"+cedula+" --codigo-> "+codUD);
	//	System.out.println("----PR A--------->"+objB.getCodproyCurr()+"----PR B--------->"+objB.getCodproyCurr());
		if(objA==null){
	//		System.out.println("consulta en post nula");
			//insertar en personal e investigadores post.
			objA=objB;

			objA.setCodFacultad(facultad(objA.getCodFacultad()));
			objA.setFlag(0);
	//		System.out.println("consulta hecha desde Oracle");
		}else{
			if(objB!=null){			
				objA.setEstado(objB.getEstado());
				if(objA.isPerActualizado()){
					objA.setFlag(1);				
					objA.setNombreEstado(objB.getNombreEstado());
				}
				else{	//update personal
					objA.setFlag(2);
					objA.setCedula(objB.getCedula());
					objA.setTipoCed(objB.getTipoCed());
					objA.setNombreTipoCed(objB.getNombreTipoCed());
					objA.setCodigoUd(objB.getCodigoUd());
					if(objA.getDeCed()==null){
						objA.setDeCed(objB.getDeCed());
					}
					objA.setGenero(objB.getGenero());
					objA.setNombreGenero(objB.getNombreGenero());
					objA.setTipoPer(objB.getTipoPer());
					objA.setNombreTipoPer(objB.getNombreTipoPer());
					
					objA.setNombres((objB.getNombres()));
					objA.setApellidos(objB.getApellidos());
					objA.setTel2(objB.getTel1());
					objA.setCel2(objB.getCel1());
	
					if(objB.getMailInst()!=null){
						objA.setMailInst(objB.getMailInst());
					}
					if(objB.getMail()!=null){
						objA.setMail(objB.getMail());
					}
	
					objA.setFechaNacimiento(objB.getFechaNacimiento());
				}
				
		
				if(objB.getCodFacultad()!= 0){
					objA.setCodFacultad(facultad(objB.getCodFacultad()));
					objA.setNombreFacultad(objB.getNombreFacultad());
				}
				
				if(objB.getCodproyCurr()!=0){
					objA.setCodproyCurr(objB.getCodproyCurr());
					objA.setNombreProyCurr(objB.getNombreProyCurr());
				}
			}
		}
		objA.setProyectosCurriculares(AjaxProyectoCur(objA.getCodFacultad()));
	
		return objA;
	}

	public int facultad(int codF){
		int facultad=0;

		switch(codF){
		case 32:
			facultad=1;
			break;
		case 33:
			facultad=2;
			break;
		case 23:
			facultad=3;
			break;
		case 24:
			facultad=4;
			break;
		case 101:
			facultad=5;
			break;
		}
		return facultad;
	}

	public List getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(List listaProyectos) {
		this.listaProyectos = listaProyectos;
	}

	public boolean insertarPersona(Integrante datosIntegrante){
		Connection cn=null;
		boolean retorno=false;
		PreparedStatement st=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
				st=cn.prepareStatement(rb.getString("insertarPersona"));
				st.setString(i++, datosIntegrante.getCedula());
				st.setInt(i++, datosIntegrante.getTipoCed());
				st.setString(i++, datosIntegrante.getNombres());
				st.setString(i++, datosIntegrante.getApellidos());
				st.setString(i++, datosIntegrante.getMail());
				st.setString(i++,datosIntegrante.getMailInst());
				st.setString(i++, datosIntegrante.getTel1());
				st.setString(i++, datosIntegrante.getCel1());
				st.setString(i++, datosIntegrante.getDeCed());
				st.setString(i++, datosIntegrante.getDireccion());
				st.setString(i++, datosIntegrante.getTel2());
				st.setString(i++, datosIntegrante.getFechaNacimiento());
				st.setString(i++, datosIntegrante.getFechaIngreso());
				st.setString(i++, datosIntegrante.getFechaSalida());
				st.setInt(i++, datosIntegrante.getGenero());
				st.setInt(i++, datosIntegrante.getTipoPer());
				st.setBoolean(i++, true);
				st.setString(i++, datosIntegrante.getCel2());
				st.setString(i++, datosIntegrante.getCvlac());
				st.setString(i++, datosIntegrante.getCodigoUd());
				st.executeUpdate();
				retorno=true;
				cn.commit();
		} catch (SQLException e) {
			setMensaje("No se pudo hacer el ingreso de la persona en el sistema");
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {cerrar(cn);cerrar(st);}
		return retorno;
	}

	public boolean insertarPersona(Integrante datosIntegrante,long idGrupo){
		Connection cn=null;
		boolean ss=false;
		PreparedStatement st=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			
			System.out.println("---insertando-->"+datosIntegrante.getFlag());
			if(datosIntegrante.getFlag()==1){
				insertarInvestigador(cn,datosIntegrante,idGrupo);
			}
			if(datosIntegrante.getFlag()==0){
				String idPersona="";
				if ((idPersona=buscarPersonaCod(datosIntegrante.getCodigoUd())).equals("0")) {
					st = cn.prepareStatement(rb.getString("insertarPersona"));
					st.setString(i++, datosIntegrante.getCedula());
					st.setInt(i++, datosIntegrante.getTipoCed());
					st.setString(i++, datosIntegrante.getNombres());
					st.setString(i++, datosIntegrante.getApellidos());
					st.setString(i++, datosIntegrante.getMail());
					st.setString(i++, datosIntegrante.getMailInst());
					st.setString(i++, datosIntegrante.getTel1());
					st.setString(i++, datosIntegrante.getCel1());
					st.setString(i++, datosIntegrante.getDeCed());
					st.setString(i++, datosIntegrante.getDireccion());
					st.setString(i++, datosIntegrante.getTel2());
					st.setString(i++, datosIntegrante.getFechaNacimiento());
					st.setString(i++, datosIntegrante.getFechaIngreso());
					st.setString(i++, datosIntegrante.getFechaSalida());
					st.setInt(i++, datosIntegrante.getGenero());
					st.setInt(i++, datosIntegrante.getTipoPer());
					st.setBoolean(i++, true);
					st.setString(i++, datosIntegrante.getCel2());
					st.setString(i++, datosIntegrante.getCvlac());
					if (datosIntegrante.getPapel() == 6)
						st.setDouble(i++, 00);
					else
						st.setDouble(i++, Double.parseDouble(datosIntegrante
								.getCodigoUd()));
					st.executeUpdate();
					System.out.println("insertar persona: " + st.toString());
				}else{
					datosIntegrante.setId(Long.parseLong(idPersona));
					datosIntegrante.setFlag(1);
				}
				insertarInvestigador(cn, datosIntegrante, idGrupo);
			}//135201
			if(datosIntegrante.getFlag()==2){
				st=cn.prepareStatement(rb.getString("ActualizarPersona"));
				 i=1;
				st.setString(i++, datosIntegrante.getCedula());
				st.setInt(i++, datosIntegrante.getTipoCed());
				st.setString(i++, datosIntegrante.getNombres());
				st.setString(i++, datosIntegrante.getApellidos());
				st.setString(i++, datosIntegrante.getMail());
				st.setString(i++, datosIntegrante.getMailInst());
				st.setString(i++, datosIntegrante.getTel1());
				st.setString(i++, datosIntegrante.getCel1());
				st.setString(i++, datosIntegrante.getDeCed());
				st.setString(i++, datosIntegrante.getDireccion());
				st.setString(i++, datosIntegrante.getTel2());
				st.setString(i++, datosIntegrante.getFechaNacimiento());
				st.setString(i++, datosIntegrante.getFechaIngreso());
				st.setString(i++, datosIntegrante.getFechaSalida());
				st.setInt(i++, datosIntegrante.getGenero());
				st.setInt(i++, datosIntegrante.getTipoPer());
				st.setBoolean(i++, true);
				st.setString(i++, datosIntegrante.getCel2());
				if(datosIntegrante.getPapel()==6)
					st.setDouble(i++, 00);
				else
					st.setDouble(i++, Double.parseDouble(datosIntegrante.getCodigoUd()));
				st.setLong(i++,datosIntegrante.getId());
				st.executeUpdate();
				insertarInvestigador(cn,datosIntegrante,idGrupo);
			}
			cn.commit();
			ss=true;
			//System.out.println("-->"+datosIntegrante.getCedula());
		} catch (SQLException e) {
			lanzaExcepcion(e);
			setMensaje("El integrante ya se encuentra registrado en este grupo");
		}catch (Exception e) {
			lanzaExcepcion(e);
		}
		finally {cerrar(cn);cerrar(st);}
		return ss;
	}
	
	public String buscarPersonaCod(String codigo){
		Connection cn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		String idPersona="0";
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			st=cn.prepareStatement(rb.getString("buscarPersonaCod"));
			st.setString(i++, codigo);
			rs=st.executeQuery();
			while(rs.next()){
				i=1;
				idPersona=(rs.getString(i++));
			}
		} catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}
		finally {
			cerrar(cn);
			cerrar(st);
		}
		return idPersona;
	}

	public boolean insertarInvestigador(Connection cn,Integrante datosIntegrante,long idGrupo)throws SQLException{
		boolean retorno=false;
		PreparedStatement st=null;
		ResultSet rs=null;
		int i=1;
		System.out.println("---insertando investigador-->"+datosIntegrante.getFlag());
		if(datosIntegrante.getFlag()==0){
			st=cn.prepareStatement(rb.getString("insertarInvestigador"));
		}else{
			st=cn.prepareStatement(rb.getString("insertarInvestigador2"));
			st.setLong(i++,datosIntegrante.getId());
		}

		st.setInt(i++, datosIntegrante.getCodFacultad());
		st.setLong(i++, idGrupo);
		st.setInt(i++, datosIntegrante.getCodproyCurr());
		st.setInt(i++, datosIntegrante.getPapel());
		st.setString(i++, datosIntegrante.getFechaVinculacion());
		st.setString(i++, datosIntegrante.getFechaSalidaGrupo());
		st.executeUpdate();
		System.out.println("insertar persona: "+st.toString());
		retorno=true;
			
		cerrar(rs);
		cerrar(st);
		return retorno;
	}

	public boolean insertarDirectorGrupo(Connection cn,Integrante datosIntegrante,int flag)throws SQLException{
		boolean ss=false;
		PreparedStatement st=null;
		ResultSet rs=null;
		int i=1;
			st=cn.prepareStatement(rb.getString("insertarDirector"+flag));
			if(flag==2){
				st.setLong(i++, datosIntegrante.getId());
			}
			st.setInt(i++, datosIntegrante.getCodFacultad());
			st.setInt(i++, datosIntegrante.getCodproyCurr());
			st.executeUpdate();
			ss=true;

		cerrar(rs);cerrar(st);
		return ss;
	}

	public boolean actualizaIntegranteGrupo(Integrante integrante,long idGrupo) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno=false;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("ActualizarPersona"));
			ps.setInt(i++,integrante.getTipoCed());
			ps.setString(i++,integrante.getNombres());
			ps.setString(i++,integrante.getApellidos());
			ps.setString(i++,integrante.getMail());
			ps.setString(i++,integrante.getMailInst());
			ps.setString(i++,integrante.getTel1());
			ps.setString(i++,integrante.getCel1());
			ps.setString(i++,integrante.getDeCed());
			ps.setString(i++,integrante.getDireccion());
			ps.setString(i++,integrante.getTel2());
			ps.setString(i++,integrante.getFechaNacimiento());
			ps.setString(i++,integrante.getFechaIngreso());
			ps.setString(i++,integrante.getFechaSalida());
			ps.setInt(i++,integrante.getGenero());
			ps.setInt(i++,integrante.getTipoPer());
			ps.setBoolean(i++, true);
			ps.setString(i++,integrante.getCel2());
			ps.setString(i++,integrante.getCodigoUd());
			ps.setString(i++,integrante.getCvlac());
			ps.setInt(i++,integrante.getCodareasnies());			
			ps.setString(i++,integrante.getCedula());
			System.out.println("ACTUALIZACION:->"+ps.toString());
			ps.executeUpdate();
			actualizarInvestigador(cn,integrante,idGrupo);

			cn.commit();
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

	public boolean actualizarInvestigador(Connection cn,Integrante integrante,long idGrupo)throws SQLException{
		boolean ss=false;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		ps=cn.prepareStatement(rb.getString("actualizaIntegrante"));
		ps.setInt(i++,integrante.getCodFacultad());
		ps.setInt(i++,integrante.getCodproyCurr());
		ps.setInt(i++,integrante.getPapel());
		ps.setString(i++,integrante.getFechaVinculacion());
		ps.setString(i++,integrante.getFechaSalidaGrupo());
		ps.setInt(i++,integrante.getCodareasnies());
		ps.setLong(i++,integrante.getId());
		ps.setLong(i++,idGrupo);
		System.out.println("---pss-s->"+ps.toString());
		ps.executeUpdate();
		
		ss=true;

		cerrar(rs);cerrar(ps);
		return ss;
	}


//*********************** consulta de grupos desde la página web****************
	
	public List getListaGruposParaWeb(FiltroPagWebGrupo filtro) {
		List listaGrupos= new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		FiltroPagWebGrupo grupoWeb = null;
		int i=1;		
		try {
			cn=cursor.getConnection(super.perfil); 
			
				ps=cn.prepareStatement(rb.getString("listaGruposWeb"));
				ps.setString(i++, filtro.getFacultad());
				ps.setString(i++, filtro.getNombreDirector());
				ps.setString(i++, filtro.getApellidoDirector());
				ps.setString(i++, filtro.getNombreGrupo2());
				ps.setString(i++, filtro.getCategoria());
				rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				grupoWeb=new FiltroPagWebGrupo();
				grupoWeb.setCodigo(rs.getLong(i++));
				grupoWeb.setNombreDirector(rs.getString(i++)+" "+rs.getString(i++));
				grupoWeb.setNombreGrupo(rs.getString(i++));
				grupoWeb.setSigla(rs.getString(i++));				
				grupoWeb.setFacultad(rs.getString(i++));
				grupoWeb.setCategoria(rs.getString(i++));
				listaGrupos.add(grupoWeb);
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
		
		return listaGrupos;
	}
	
	public List getListaSemillerosParaWeb(FiltroPagWebSemillero filtro) {
		List listaSemilleros= new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		FiltroPagWebSemillero semilleroWeb = null;
		int i=1;		
		try {
			cn=cursor.getConnection(super.perfil); 
			
				ps=cn.prepareStatement(rb.getString("listaSemillerosWeb"));
				ps.setString(i++, filtro.getFacultad());
				ps.setString(i++, filtro.getNombreDirector());
				ps.setString(i++, filtro.getApellidoDirector());
				ps.setString(i++, filtro.getNombreSemillero2());
				rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				semilleroWeb = new FiltroPagWebSemillero();
				semilleroWeb.setCodigo(rs.getLong(i++));
				semilleroWeb.setNombreDirector(rs.getString(i++)+" "+rs.getString(i++));
				semilleroWeb.setNombreSemillero(rs.getString(i++));
				semilleroWeb.setSigla(rs.getString(i++));				
				semilleroWeb.setFacultad(rs.getString(i++));
				listaSemilleros.add(semilleroWeb);
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
		
		return listaSemilleros;
	}
	
	public List buscaIntegrantesGrupoWeb(long idGrupo) {
		List listaIntegrantes=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Integrante integrante=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaIntegrantesWeb"));
			ps.setLong(i++, idGrupo);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				integrante=new Integrante();
				integrante.setId(rs.getLong(i++));
				integrante.setNombres(rs.getString(i++));
				integrante.setApellidos(rs.getString(i++));
				integrante.setCvlac(rs.getString(i++));
				listaIntegrantes.add(integrante);
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
		return listaIntegrantes;
	}
	
	public GrupoInvestigacion infoGrupoWeb (String idGrupo)
	{
		GrupoInvestigacion grupo = null;
		grupo = getVerGrupo(idGrupo);
		grupo.setIntegrantes(buscaIntegrantesGrupoWeb(Long.parseLong(idGrupo)));
		return grupo;
	}
	
	public GrupoInvestigacion infoSemilleroWeb (String idSemillero)
	{
		GrupoInvestigacion grupo = null;
		grupo = getVerGrupo(idSemillero);
		grupo.setIntegrantes(buscaIntegrantesGrupoWeb(Long.parseLong(idSemillero)));
		return grupo;
	}	//******************************************************************************
	
	//Cargar �res de conocimiento SCNIES
	
	public List AjaxAreasSnies() {
		List l=new ArrayList();
		AreasConocimiento areaSNIES=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn = cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ajaxAreasSNIES"));			
			rs=ps.executeQuery();
			while(rs.next()){
				areaSNIES=new AreasConocimiento();
				areaSNIES.setCodigo(rs.getInt(1));
				areaSNIES.setNombre(rs.getString(2));
				l.add(areaSNIES);
			}
		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
	//	System.out.println(l.size());
		return l;
	}
}


