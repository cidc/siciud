package cidc.cuentasUsuario.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



import cidc.cuentasUsuario.obj.Objeto;
import cidc.cuentasUsuario.obj.DatosCuenta;
import cidc.cuentasUsuario.obj.FiltroCuentas;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.CrearClaves;
import cidc.general.obj.Globales;

public class CuentasUsuDB extends BaseDB {

	public CuentasUsuDB(CursorDB c, int perfil) {
		super(c,perfil);
		rb=ResourceBundle.getBundle("cidc.cuentasUsuario.consultas");
	}

	public boolean cambiaNickClave(long id, String nick, String clave){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("cambiaClave"));
			ps.setString(i++, nick);
			ps.setString(i++, clave);
			ps.setLong(i++, id);
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

	public DatosCuenta getCuenta(long idPersona){
		DatosCuenta cuenta=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		String []perfiles={"0","0","0"};
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getCuenta"));
			ps.setLong(i++, idPersona);
	//		System.out.println("ID a buscar ="+idPersona);
			rs=ps.executeQuery();
			while (rs.next()) {
				i=1;
				cuenta=new DatosCuenta();
				cuenta.setId(rs.getLong(i++));
				cuenta.setNombre(rs.getString(i++));
				cuenta.setApellido(rs.getString(i++));
				cuenta.setNick(rs.getString(i++));
				cuenta.setPerfiles(rs.getString(i++));
				perfiles=cuenta.getPerfiles().split(",");
				cuenta.setPerf1(perfiles[0]);
				cuenta.setPerf2(perfiles[1]);
				cuenta.setPerf3(perfiles[2]);
		//		System.out.println("encuentra cuenta");
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
		return cuenta;
	}
	public boolean cambiaPerfil(String id, String p1,String p2,String p3){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("cambiaPerfil"));
			ps.setString(i++, p1+","+p2+","+p3);
			ps.setLong(i++, Long.parseLong(id));
			ps.executeUpdate();
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

	public boolean actualizaCuenta(DatosCuenta datos){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		String []per=datos.getPerfiles().split(",");
		i=per.length;
		if(i==1)
			datos.setPerfiles(datos.getPerfiles()+",0,0");
		if(i==2)
			datos.setPerfiles(datos.getPerfiles()+",0");
		try {
			i=1;
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizaCuenta"));
			ps.setString(i++, datos.getNick());
			ps.setString(i++,datos.getPerfiles());
			ps.setLong(i++, datos.getId());
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

	public List buscarCuentas(FiltroCuentas filtro){
		List listaCuentas=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		DatosCuenta cuenta=null;
		int i=1;
		String []perfil={"0","0","0"};
		try {
			cn=cursor.getConnection(super.perfil);
			if(filtro.getPerfil().equals("1"))
				ps=cn.prepareStatement(rb.getString("getListaCuentasa"+filtro.getOrden()));
			else{
				ps=cn.prepareStatement(rb.getString("getListaCuentasb"+filtro.getOrden()));
				ps.setString(i++, "%"+filtro.getPerfil()+"%");
			}
			ps.setString(i++, filtro.getNick());
			ps.setString(i++, filtro.getNombre());
			ps.setString(i++, filtro.getApellido());

	/*		System.out.println("perfil "+filtro.getPerfil());
			System.out.println("nombre "+filtro.getNombre());
			System.out.println("apellido "+filtro.getApellido());
			System.out.println("nick "+filtro.getNick());*/
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				cuenta=new DatosCuenta();
				cuenta.setId(rs.getLong(i++));
				cuenta.setNick(rs.getString(i++));
				cuenta.setNombre(rs.getString(i++));
				cuenta.setApellido(rs.getString(i++));
				cuenta.setPerfiles(rs.getString(i++));
				perfil=cuenta.getPerfiles().split(",");
				cuenta.setPerf1(perfil[0]);
				cuenta.setPerf2(perfil[1]);
				cuenta.setPerf3(perfil[2]);
				listaCuentas.add(cuenta);
		//		System.out.println(perfil[0]+" "+perfil[1]+" "+perfil[2]);
				cuenta.setFecha(rs.getString(i++));

				if(cuenta.getFecha()!=null && !cuenta.getFecha().equals("")){
					cuenta.setFecha(cuenta.getFecha().substring(0,cuenta.getFecha().lastIndexOf(".")));
				}
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return listaCuentas;
	}

	public boolean eliminaCuenta(DatosCuenta cuenta) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("EliminaCuenta"));
			ps.setLong(i++, cuenta.getId());
			ps.executeUpdate();
			retorno=true;
		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}

	public boolean resetearClave(String id){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		CrearClaves clave=new CrearClaves();
		String []datos=new String[5];
		int i=1;
		String key=clave.getClave();
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("resetearClave"));
			ps.setString(i++, key);
			ps.setLong(i++, Long.parseLong(id));
			ps.executeUpdate();
			i=1;
			ps=cn.prepareStatement(rb.getString("verPerfil"));
			ps.setLong(1,Long.parseLong(id));
			rs=ps.executeQuery();
			while(rs.next()){
				datos[0]=rs.getString(i++);
				datos[1]=rs.getString(i++);
				datos[2]=rs.getString(i++);
				datos[3]=rs.getString(i++);
				datos[4]=key;
				mailClaveNueva(datos);
				retorno=true;
				break;
			}
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


	public void mailClaveNueva(String []datos) throws AddressException,MessagingException{
		String []direcciones={datos[1]};
		ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.grupoInvestigacion");
		Globales global=new Globales();
		EnvioMail2 envioMail=new EnvioMail2("general");
		StringBuffer texto=new StringBuffer();
		texto.append("<br>SICIUD-"+datos[0]+"-"+global.getAnoCortoHoy()+"<br><br>");
		texto.append(rb.getString("cg1"));
		texto.append(" "+datos[2]);
		texto.append(rb.getString("cg2"));
		texto.append(rb.getString("cg3"));
		texto.append(" "+datos[3]);
		texto.append(rb.getString("cg4"));
		texto.append(" "+datos[4]);
		texto.append(rb.getString("cg5"));
		texto.append(rb.getString("cg6"));
		texto.append(rb.getString("cg7"));
		envioMail.enviar(direcciones,"Clave de Ingreso al SICIUD",""+texto);
		Reporte reporteMail=new Reporte(cursor,super.perfil);
		reporteMail.reportar(datos[2],"Clave Investigador",direcciones[0],datos[0]);
	}

	public List getListaPerfiles(){
		List listaPerfiles=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Objeto perfil=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getListaPerfiles"));
			rs=ps.executeQuery();
			while (rs.next()) {
				perfil=new Objeto();
				perfil.setId(rs.getInt(1));
				perfil.setNombre(rs.getString(2));
				listaPerfiles.add(perfil);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}

		return listaPerfiles;
	}

}

