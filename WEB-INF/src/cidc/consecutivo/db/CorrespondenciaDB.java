package cidc.consecutivo.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.poi.hssf.util.HSSFColor.GOLD;

import cidc.consecutivo.obj.CorrespondenciaObj;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.Globales;

public class CorrespondenciaDB extends BaseDB{
	
	public CorrespondenciaDB(CursorDB cursor, int perfil) {
		super(cursor, perfil);
		rb=ResourceBundle.getBundle("cidc.consecutivo.consultas");
	}
	
	/**
	 * Trae una lista con los ultimos diez registros almacenados en la tabla correspondencia
	 * @return
	 */
	public List<CorrespondenciaObj> ObtenerUltimos(){
		CorrespondenciaObj corresp=null;
		List<CorrespondenciaObj> registro= new ArrayList<CorrespondenciaObj>();
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consulta_inicial"));
			Globales gl =new Globales();
			ps.setString(1, "%"+gl.getAnoHoy()+"%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				int i=1;
				corresp = new CorrespondenciaObj();
				corresp.setCod(rs.getString(i++));
				corresp.setRemitente(rs.getString(i++));
				corresp.setDestinatario(rs.getString(i++));
				corresp.setObservaciones(rs.getString(i++));
				corresp.setFecha(rs.getDate(i++));
				registro.add(corresp);
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		
		return registro;
	}
	
	/**
	 * crea un nuevo registro en la tabla correspondencia 
	 * @param remitente persona que asigna el consecutivo
	 * @param dest persona a quien va dirigido el destinatario
	 * @param obs observaciones 
	 * @param year año de asignacion
	 * @return
	 */
	public boolean insertarRegistro(String remitente, String dest, String obs, int year){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int secuencia=0;
		try {
			cn = cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ConsultaLast"));
			rs=ps.executeQuery();
			while(rs.next()){
				secuencia=rs.getInt(1);
			}
			Globales glob= new Globales();
			int i=1;
			String cod=glob.codigo(String.valueOf(secuencia));
			ps=cn.prepareStatement(rb.getString("insertar"));
			ps.setInt(i++, secuencia);
			ps.setString(i++,cod);
			ps.setString(i++, remitente);
			ps.setString(i++, dest);
			ps.setString(i++, obs);
			ps.setInt(i++, year);
			ps.executeUpdate();
			return true;
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return false;
	}
	
	/**
	 * aumenta la secuencia de los consecutivos
	 * @return
	 */
	public boolean aumentaConsecutivo(){
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn = cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("aumentarSeq"));
			ps.execute();
			return true;
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return false;
	}
	/**
	 * consulta el nombre del usuario que asigna el consecutivo
	 * @param user
	 * @return
	 */
	public Usuario consultaUsuario(Usuario user){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaPersona"));
			ps.setLong(1, user.getIdUsuario());
			rs=ps.executeQuery();
			while(rs.next()){
				user.setNombre(rs.getString(1)+" "+rs.getString(2));
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return user;
	}
	/**
	 * consulta todos consecutivos asignados, que cumplan con los criterios de busqueda 
	 * @param crp
	 * @return
	 */
	public List<CorrespondenciaObj> consultarFiltro(CorrespondenciaObj crp){
		List<CorrespondenciaObj> miLista= null;
		Connection cn =null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaFiltro"));
			if(crp.getCod()==null)
				ps.setString(1, "%");
			else
				ps.setString(1, "%"+crp.getCod()+"%");
			if(crp.getRemitente()==null)
				ps.setString(2, "%");
			else
				ps.setString(2, "%"+crp.getRemitente()+"%");
			if(crp.getDestinatario()==null)
				ps.setString(3, "%");
			else
				ps.setString(3, "%"+crp.getDestinatario()+"%");
			if(crp.getObservaciones()==null)
				ps.setString(4, "%");
			else
				ps.setString(4, "%"+crp.getObservaciones()+"%");
			ps.setString(5, "%"+crp.getAno()+"%");
			rs=ps.executeQuery();
			System.out.println("consulta: "+ps.toString());
			miLista = new ArrayList<CorrespondenciaObj>();
			while(rs.next()){
				int i=1;
				CorrespondenciaObj cons=new CorrespondenciaObj();
				cons.setCod(rs.getString(i++));
				cons.setRemitente(rs.getString(i++));
				cons.setDestinatario(rs.getString(i++));
				cons.setObservaciones(rs.getString(i++));
				cons.setFecha(rs.getDate(i++));
				miLista.add(cons);
				
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return miLista;
	}
	/**
	 * 
	 * @return
	 */
	public int consultarAño(){
		Connection cn=null;
		ResultSet rs=null;
		PreparedStatement ps=null;
		int year=0;
		try {
			String cod="";
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaUltimoCod"));
			rs=ps.executeQuery();
			while(rs.next()){
				cod=rs.getString(1);
			}
			 year=Integer.parseInt(cod);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return year;
	}
	/**
	 * este metodo reinicia el numero del consecutivo cada vez que se cambia el año
	 */
	public void reiniciarNumeracion(){
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("reiniciarConsecutivo"));
			ps.executeQuery();
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
	
	}
}
