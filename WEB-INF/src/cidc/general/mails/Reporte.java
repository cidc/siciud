package cidc.general.mails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;
import java.util.ResourceBundle;

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.obj.Globales;

public class Reporte extends BaseDB{

	public Reporte(CursorDB c, int perfil) {
		super(c, perfil);
		rb=ResourceBundle.getBundle("cidc.general.consultas");
	}
	public void reportar(String persona,String motivo,String correo, String idReporte){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		Globales global=new Globales();
		String consecutivo="SICIUD-"+idReporte+"-"+global.getAnoCortoHoy();
	//	System.out.println("Consecutivo ->"+consecutivo);

		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("reporteMail"));
			ps.setString(i++,consecutivo);
			ps.setString(i++,persona);
			ps.setString(i++,motivo);
			ps.setString(i++,correo);
			ps.executeUpdate();
		} catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}

	}
}
