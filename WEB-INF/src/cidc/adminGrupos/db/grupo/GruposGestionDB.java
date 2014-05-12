package cidc.adminGrupos.db.grupo;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cidc.adminGrupos.obj.Integrante;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.mails.EnvioMail;
import cidc.general.mails.Reporte;
import cidc.general.obj.CrearClaves;
import cidc.general.obj.Globales;

public class GruposGestionDB extends BaseDB{

	public GruposGestionDB(CursorDB cursor, int perfil) {
		super(cursor, perfil);
		rb=ResourceBundle.getBundle("cidc.adminGrupos.db.grupo.consultas");
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
			while(rs.next()){
				i=1;
				integrante=new Integrante();
				integrante.setId(rs.getLong(i++));
				integrante.setNombres(rs.getString(i++));
				integrante.setApellidos(rs.getString(i++));
				integrante.setPapel(rs.getInt(i++));
				integrante.setFechaSalida(rs.getString(i++));
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

	public boolean actualizaDatosIntegrante(Integrante integ) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno=false;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("actualizaPersona"));
			ps.setString(i++,integ.getMail());
			ps.setString(i++,integ.getTel1());
			ps.setString(i++,integ.getCel1());
			ps.setString(i++,integ.getCodigoUd());
			ps.setInt(i++,integ.getTipoCed());
			ps.setString(i++,integ.getCedula());
			ps.setString(i++,integ.getDeCed());
			ps.setString(i++,integ.getNombres());
			ps.setString(i++,integ.getApellidos());
			ps.setString(i++,integ.getCvlac());
			ps.setLong(i++,integ.getId());
			System.out.println("----->"+ps.executeUpdate());
			i=1;
			ps=cn.prepareStatement(rb.getString("actualizaIntegrante"));
			ps.setLong(i++, integ.getCodFacultad());
			ps.setLong(i++, integ.getIdProyectoCurr());
			ps.setLong(i++, integ.getPapel());
			ps.setString(i++, integ.getFechaVinculacion());
			ps.setString(i++, integ.getFechaSalida());
			ps.setLong(i++,integ.getId());
			System.out.println("----->"+ps.executeUpdate());
			cn.commit();
			retorno=true;
	//		System.out.println("---Actualizar papel-->"+integ.getPapel());
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
		//	System.out.println("----->"+ps);
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
				integrante.setNombreFacultad(rs.getString(i++));
				integrante.setNombreProyCurr(rs.getString(i++));
				integrante.setPapel(rs.getInt(i++));
				integrante.setFechaVinculacion(rs.getString(i++));
				integrante.setFechaSalida(rs.getString(i++));
				integrante.setCodFacultad(rs.getInt(i++));
				integrante.setIdProyectoCurr(rs.getLong(i++));
				integrante.setUltimaActua(rs.getString(i++));
				integrante.setCvlac(rs.getString(i++));

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

	public boolean claveInvestigador(String idPersona,String papel, String identificador){
	//	//System.out.println("persona "+idPersona+" papel "+papel);
		boolean retorno=false;
		if(papel.equals("1"))
			papel="10";
		else
			papel="8";
		CrearClaves clave=new CrearClaves();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String perfil=null,nick=null,key=null;
		String []datos=new String[5];
		int i=1;
		key=clave.getClave();
		nick= identificador;
		//System.out.println("Bandera 1");
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("verPerfil"));
			ps.setLong(1,Long.parseLong(idPersona));
			rs=ps.executeQuery();
			while(rs.next()){
				//System.out.println("Bandera 2");
				perfil=rs.getString(1);
//Borrado Por Diego	-->    nick=rs.getString(2);
				nick=identificador;
				datos[2]=rs.getString(3);
				datos[1]=rs.getString(4);
				datos[0]=rs.getString(5);
			}
			//System.out.println("Bandera 3");
			if(perfil==null){
				//System.out.println("Bandera 4");
				ps=cn.prepareStatement(rb.getString("nuevoUsuario"));
				ps.setLong(i++, Long.parseLong(idPersona));
				nick=identificador;
				ps.setString(i++,nick);
				ps.setString(i++,key);
				ps.setString(i++,papel+",0,0");
				ps.executeUpdate();
				System.out.println("\nBandera 1\n");
				}else{
				//System.out.println("Bandera 5");
					
				String []partes=perfil.split(",");
				String nuevoPerfil=null;
				if(!partes[0].equals(papel)){
					if(partes[1].equals("0"))
						nuevoPerfil=partes[0]+","+papel+",0";
					else
						if(partes[2].equals("0") && !partes[1].equals(papel))
							nuevoPerfil=partes[0]+","+partes[1]+","+papel;
					System.out.println("\nBandera 2\n");
				}
				//System.out.println("Bandera 6");
				if(nuevoPerfil!=null){
					//System.out.println("Bandera 7");
					
					System.out.println("\nActualizo base de datos aqui\n");
					ps=cn.prepareStatement(rb.getString("asignaPerfKey"));
					ps.setString(i++,key);
					ps.setString(i++,nuevoPerfil);
					//ps.setString(i++, nick);
					ps.setLong(i++, Long.parseLong(idPersona));
					ps.executeUpdate();
					System.out.println("\nBandera 3\n");
		//			//System.out.println("Asigna perfil y psw "+nuevoPerfil);
				}else{
					//System.out.println("Bandera 8");
					ps=cn.prepareStatement(rb.getString("cambioClave"));
					ps.setString(i++,key);
					ps.setString(i++,nick);
					ps.setLong(i++, Long.parseLong(idPersona));
					//ps.setString(i++, nick);
					ps.executeUpdate();
		//			//System.out.println("solo cambia clave");
					System.out.println("\nBandera 4\n");
				}
				//System.out.println("Bandera 9");
			}
			datos[3]=nick;
			datos[4]=key;
			mailClaveNueva(datos);
			
			System.out.println("\nAqui el nick es = " + nick);
			System.out.println("\nBandera Final\n");
			
			//System.out.println("Bandera 10");
			retorno=true;
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

	public void mailClaveNueva(String []datos) throws AddressException,MessagingException{
		String []direcciones={datos[1]};
		ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.grupoInvestigacion");
		Globales global=new Globales();
		//System.out.println("Bandera 9-1");
		EnvioMail envioMail=new EnvioMail("general");
		//System.out.println("Bandera 9-2");
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
		//System.out.println("Bandera 9-3");
		envioMail.enviar(direcciones,"Clave de Ingreso al SICIUD",""+texto);
		//System.out.println("Bandera 9-4");
		Reporte reporteMail=new Reporte(cursor,super.perfil);
		//System.out.println("Bandera 9-5");
		reporteMail.reportar(datos[2],"Clave Investigador",direcciones[0],datos[0]);
		//System.out.println("Bandera 9-6");
	}

	public boolean nuevoIntegranteGrupo(Integrante integrante) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno=false;
		int i=1;
		try {
			System.out.println("Bandera Nuevo Integrante 1");
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
		System.out.println("Bandera Nuevo Integrante 2");
		return retorno;
	}
}
