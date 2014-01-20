package cidc.inscripSistema.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.CrearClaves;
import cidc.general.obj.Globales;
import cidc.inscripSistema.obj.Persona;


public class InscripcionSisDB extends BaseDB{

	public InscripcionSisDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.inscripSistema.consultas");
	}

	public String setClaveIngreso(long cod)throws SQLException {
		Connection cn=null;
		PreparedStatement ps=null;
		String key=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("asignaClaveIngreso"));
			CrearClaves crearClave=new CrearClaves();
			key=crearClave.getClave();
			System.out.println("La clave de ingreso asiganda es: "+key);
			ps.setLong(1,cod);
			ps.setString(2,key);
			ps.executeUpdate();
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return key;
	}

	public boolean insertaPersonaIngreso(Persona per) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno=false;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("insertaPersonaIngreso"));
			ps.setInt(i++,per.getFacultad());
			ps.setLong(i++,per.getGrupo());
			ps.setLong(i++,per.getProyCur());
			ps.setLong(i++,per.getIdNombre());
			ps.setString(i++,per.getMail());
			ps.setString(i++,per.getNick());
			ps.executeUpdate();
			retorno=true;
		//	System.out.println("Inserta Correctamente "+per.getIdNombre());
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

	public List getListaInscripciones(){
		Persona persona=null;
		List listaPersona=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getListaInscripcion"));
			rs=ps.executeQuery();
			while(rs.next()){
				persona=new Persona();
				i=1;
			//	System.out.println("----------------");
				//		persona.setFacultad(rs.getInt(i++));
				persona.setIdPersona(rs.getLong(i++));
				persona.setNombFacultad(rs.getString(i++));
		//		persona.setProyCur(rs.getLong(i++));
				persona.setNombProyecto(rs.getString(i++));
		//		persona.setGrupo(rs.getInt(i++));
				persona.setNombGrupo(rs.getString(i++));
				persona.setNombre(rs.getString(i++));
				persona.setMail(rs.getString(i++));
				persona.setMailBase(rs.getString(i++));
				persona.setIdUserSis(rs.getLong(i++));
				listaPersona.add(persona);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
		}
		return listaPersona;
	}

	public boolean AceptaInscripcion(String idInscripcion, String caso){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String key="";
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("acepInscUpdateMail"));
			ps.setLong(i++,Long.parseLong(idInscripcion));
			ps.setLong(i++,Long.parseLong(idInscripcion));
			ps.executeUpdate();
			CrearClaves clave=new CrearClaves();
			key=clave.getClave();
			i=1;
	//		System.out.println("*****"+key);
			if(caso.equals("1")){
				ps=cn.prepareStatement(rb.getString("acepInscUpdateNickKey"));
				ps.setString(i++,key);
				ps.setLong(i++,Long.parseLong(idInscripcion));
				ps.setLong(i++,Long.parseLong(idInscripcion));
				ps.executeUpdate();
			}else{
				ps=cn.prepareStatement(rb.getString("acepInscNewNickKey"));
				ps.setLong(i++,Long.parseLong(idInscripcion));
				ps.setLong(i++,Long.parseLong(idInscripcion));
				ps.setString(i++,key);
				ps.executeUpdate();
			}
			ps=cn.prepareStatement(rb.getString("getDatosMail"));
			ps.setLong(1,Long.parseLong(idInscripcion));
			rs=ps.executeQuery();
			while(rs.next()) {
				enviaMailAceptacion(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),key);
				break;
			}


			retorno=true;
			BorraInscripcion(cn,idInscripcion);
			cn.commit();
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


	private void enviaMailAceptacion(String idMail, String nombre, String mail, String nick, String clave) throws Exception{
		ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.correoInscripcion");
		EnvioMail2 envioMail=new EnvioMail2("general");
		String []direccion={mail};
		StringBuffer texto=new StringBuffer();
		Globales global=new Globales();
		texto.append("SICIUD-"+idMail+"-"+global.getAnoCortoHoy()+"<br><br>");

		texto.append(rb.getString("is1"));
		texto.append(nombre);
		texto.append(rb.getString("is2"));
		texto.append(rb.getString("is3"));
		texto.append(nick);
		texto.append(rb.getString("is4"));
		texto.append(clave);
		texto.append(rb.getString("is5"));
		texto.append(rb.getString("is6"));
		texto.append(rb.getString("is7"));

		envioMail.enviar(direccion,"Su Inscripción se ha Completado",""+texto);
//		System.out.println("Mensaje Enviado");
		Reporte reporteMail=new Reporte(cursor,super.perfil);
		reporteMail.reportar(nombre,"Confirmación de Inscripcion al Sistema",mail,idMail);
	}

	public boolean BorraInscripcion(Connection cn2, String idInscripcion){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			if(cn2==null)
				cn=cursor.getConnection(super.perfil);
			else
				cn=cn2;
			ps=cn.prepareStatement(rb.getString("borraInscripcion"));
			ps.setLong(i++,Long.parseLong(idInscripcion));
			ps.executeUpdate();
			retorno=true;
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			if(cn2==null)
				cerrar(cn);
		}
		return retorno;
	}


}

