package cidc.consecutivo.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import cidc.consecutivo.obj.CorrespondenciaObj;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.general.obj.Globales;
import cidc.general.ws_coneccion_Bizagi.ConeccionDB_WS;

public class CorrespondenciaDB extends BaseDB{
	
	public CorrespondenciaDB(CursorDB cursor, int perfil) {
		super(cursor, perfil);
		rb=ResourceBundle.getBundle("cidc.consecutivo.consultas");
	}
	
	public List<CorrespondenciaObj> ObtenerUltimos(){
		CorrespondenciaObj corresp=null;
		List<CorrespondenciaObj> registro= new ArrayList<CorrespondenciaObj>();
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consulta_inicial"));
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				corresp = new CorrespondenciaObj();
				corresp.setCod(rs.getString(1));
				corresp.setRemitente(rs.getString(2));
				corresp.setDestinatario(rs.getString(3));
				corresp.setObservaciones(rs.getString(4));
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
	
	public boolean insertarRegistro(String remitente, String dest, String obs){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int secuencia=0;
		try {
			cn = cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaSeq"));
			rs=ps.executeQuery();
			while(rs.next()){
				secuencia=rs.getInt(1);
			}
			Globales glob= new Globales();
			String cod=glob.codigo(String.valueOf(secuencia));
			ps=cn.prepareStatement(rb.getString("insertar"));
			ps.setString(1,cod);
			ps.setString(2, remitente);
			ps.setString(3, dest);
			ps.setString(4, obs);
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
}
