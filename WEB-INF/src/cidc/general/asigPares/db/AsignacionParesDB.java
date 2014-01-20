package cidc.general.asigPares.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cidc.adminEvaluador.obj.AreasTrabOBJ;
import cidc.adminPropuestas.obj.DatEvaluadorOBJ;
import cidc.adminPropuestas.obj.FiltroEvaluadorOBJ;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.Globales;

public class AsignacionParesDB extends BaseDB{

	public AsignacionParesDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.general.asigPares.asignaciones");
	}

	public boolean asignaEvaluadorPropuesta(String prop, String codEval,String tipoEval) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String []datos=new String [6];
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("asignacionParPropuesta"));
			ps.setLong(1,Long.parseLong(prop));
			ps.setLong(2,Long.parseLong(codEval));
			ps.executeUpdate();
			ps=cn.prepareStatement(rb.getString("cambia_estado"+tipoEval));
			ps.setInt(1,2);
			ps.setInt(2,Integer.parseInt(prop));
			ps.executeUpdate();
			cn.commit();

			ps=cn.prepareStatement(rb.getString("getMailEvaluador0"));
			ps.setLong(1,Long.parseLong(prop));
			rs=ps.executeQuery();
			while(rs.next()){
				datos[0]=rs.getString(1);
				datos[1]=rs.getString(2);
				datos[2]=rs.getString(3);
				datos[3]=rs.getString(4);
				datos[4]=rs.getString(5);
				datos[5]=rs.getString(6);
			}
			mailAsignacionPropuesta(datos);//********************enviar mail a evaluador
			retorno=true;
		} catch (SQLException e) {
			if(!e.getSQLState().equals("23505"))
					lanzaExcepcion(e);
			System.out.println("Asignaci贸n de Evaluador repetido.... EstadoSQL="+e.getSQLState());
			setMensaje("No se puede asignar nuevamente este evaluador a esta propuesta");
		} catch (AddressException e) {
			setMensaje("La Direccion de correo no es correcta");
			lanzaExcepcion(e);
		}catch (MessagingException e) {
			setMensaje("El correo no pudo ser enviado al Evaluador");
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



	public void mailAsignacionPropuesta(String []datos) throws AddressException,MessagingException{
		String []direcciones={datos[1]};
		ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.correoEvaluadores");
		Globales global=new Globales();
		String consecutivo="\nSICIUD-"+datos[3]+"-"+global.getAnoCortoHoy()+"\n\n";
		EnvioMail2 envioMail=new EnvioMail2("academico");
		String texto=consecutivo;
		texto+=rb.getString("e1");
		texto+=" "+datos[0];//nombre del evaluador
		texto+=rb.getString("e2");
		texto+="<b> "+datos[2]+"</b>";//nombre de la propuesta
		texto+=" "+rb.getString("e3");
		texto+="<b> "+datos[4]+"</b>";//nombre de la Convocatoria

		texto+=rb.getString("e4");
//		System.out.println("tipo eval="+datos[5]);
		if(datos[5].equals("2"))
			texto+=rb.getString("e5");
		texto+=" "+rb.getString("e6");

		envioMail.enviar(direcciones,"Evaluaci髇 de Propuestas de Investigaci髇",texto);
		Reporte reporteMail=new Reporte(cursor,super.perfil);
		reporteMail.reportar(datos[0],"Asignaci髇 Par Evaluador",direcciones[0],datos[3]);
	//	System.out.println("Mensaje Enviado");
	}


	public boolean asignaEvaluadorArticulo(long artic, String codEval) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String []datos=new String [6];
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("asignacionParArticulo"));
			ps.setLong(1,artic);
			ps.setLong(2,Long.parseLong(codEval));
			ps.executeUpdate();

			ps=cn.prepareStatement(rb.getString("cambia_estadoArtic"));
			ps.setInt(1,2);
			ps.setLong(2,artic);
			ps.executeUpdate();

			cn.commit();

			ps=cn.prepareStatement(rb.getString("getMailEvaluador1"));
			ps.setLong(1,artic);
			rs=ps.executeQuery();
			while(rs.next()){
				datos[0]=rs.getString(1);//nombres
				datos[1]=rs.getString(2);//mail
				datos[2]=rs.getString(3);//titulo arti
				datos[3]=rs.getString(4);//consecutivo
				datos[4]=rs.getString(5);//tipo eval
			}
			mailAsignacionArticulo(datos);//********************enviar mail a evaluador
			retorno=true;
		} catch (SQLException e) {
			if(!e.getSQLState().equals("23505"))
					lanzaExcepcion(e);
			System.out.println("Asignaci贸n de Evaluador repetido.... EstadoSQL="+e.getSQLState());
			setMensaje("No se puede asignar nuevamente este evaluador a esta propuesta");
		} catch (AddressException e) {
			setMensaje("La Direccion de correo no es correcta");
			lanzaExcepcion(e);
		}catch (MessagingException e) {
			setMensaje("El correo no pudo ser enviado al Evaluador");
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

	public void mailAsignacionArticulo(String []datos) throws AddressException,MessagingException{
		String []direcciones={datos[1]};
		ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.correoEvaluadores");
		Globales global=new Globales();
		String consecutivo="\nSICIUD-"+datos[3]+"-"+global.getAnoCortoHoy()+"\n\n";
		EnvioMail2 envioMail=new EnvioMail2("academico");
		String texto=consecutivo;
		texto+=rb.getString("f1");
		texto+=" "+datos[0];//nombre del evaluador
		texto+=rb.getString("f2");
		texto+="<b> "+datos[2]+"</b>";//nombre de la propuesta
		texto+=" "+rb.getString("f3");
		texto+=rb.getString("f4");
		texto+=rb.getString("f5");
		texto+=" "+rb.getString("f6");

		envioMail.enviar(direcciones,"Evaluaci贸n de Art铆culo",texto);
		Reporte reporteMail=new Reporte(cursor,super.perfil);
		reporteMail.reportar(datos[0],"Asignaci贸n Par Evaluador Articulo Revista",direcciones[0],datos[3]);
	//	System.out.println("Mensaje Enviado");
	}

	public List filtro(FiltroEvaluadorOBJ filtro) {
		List lista=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			if(filtro.getTipEval()==5){
				System.out.println("entra al 5");
				if(filtro.getFacultad().equals("%") && filtro.getProyectoCur().equals("%")){
					ps=cn.prepareStatement(rb.getString("todos"+filtro.getOrden()));
					ps.setString(i++,filtro.getNombres());
					ps.setString(i++,filtro.getApellidos());
				}else{
					ps=cn.prepareStatement(rb.getString("interno"+filtro.getOrden()));
					System.out.println("interno");
					ps.setString(i++,filtro.getNombres());
					ps.setString(i++,filtro.getApellidos());
					ps.setString(i++,filtro.getFacultad());
					ps.setString(i++,filtro.getProyectoCur());
				}
			}else{
	//			System.out.println("NO entra al 5");
				if(filtro.getTipEval()==2)//machetazo para hacer coincidir las consultas
					filtro.setTipEval(3);
				ps=cn.prepareStatement(rb.getString("filtroEvaluador"+(filtro.getTipEval()+filtro.getOrden())));
				ps.setString(i++,filtro.getNombres());
				ps.setString(i++,filtro.getApellidos());
				if(filtro.getTipEval()==1){
					ps.setString(i++,filtro.getFacultad());
					ps.setString(i++,filtro.getProyectoCur());
				}
			}
			ps.setString(i++,filtro.getAreasTrabajo());
		//	System.out.println("----->"+ps);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				DatEvaluadorOBJ datEvaluadorOBJ=new DatEvaluadorOBJ();
				datEvaluadorOBJ.setTipo(rs.getInt(i++));
				datEvaluadorOBJ.setCodigo(rs.getString(i++));
				datEvaluadorOBJ.setNombres(rs.getString(i++));
				datEvaluadorOBJ.setApellidos(rs.getString(i++));
				datEvaluadorOBJ.setMail(rs.getString(i++));
				lista.add(datEvaluadorOBJ);
				//System.out.println("hay --x-> "+datEvaluadorOBJ.getApellidos());
			}
		} catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return lista;
	}

	public DatEvaluadorOBJ datosEvaluador(String codEval, int tipoEval){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		DatEvaluadorOBJ datEvaluadorOBJ=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getDatosEvaluador"+tipoEval));
			ps.setInt(1,Integer.parseInt(codEval));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				datEvaluadorOBJ=new DatEvaluadorOBJ();
				if(tipoEval==1){
					datEvaluadorOBJ.setTipo(tipoEval);
					datEvaluadorOBJ.setCodigo(codEval);
					datEvaluadorOBJ.setNombres(rs.getString(i++));
					datEvaluadorOBJ.setApellidos(rs.getString(i++));
					datEvaluadorOBJ.setMail(rs.getString(i++));
					datEvaluadorOBJ.setTelefono(rs.getString(i++));
					datEvaluadorOBJ.setCelular(rs.getString(i++));
			//		datEvaluadorOBJ.setAreasTrabajo(rs.getString(i++));
					datEvaluadorOBJ.setFacultad(rs.getString(i++));
					datEvaluadorOBJ.setGrupoInvest(rs.getString(i++));
					datEvaluadorOBJ.setProyectoCur(rs.getString(i++));
				}
				else{
					datEvaluadorOBJ.setTipo(tipoEval);
					datEvaluadorOBJ.setCodigo(codEval);
					datEvaluadorOBJ.setNombres(rs.getString(i++));
					datEvaluadorOBJ.setApellidos(rs.getString(i++));
					datEvaluadorOBJ.setMail(rs.getString(i++));
					datEvaluadorOBJ.setTelefono(rs.getString(i++));
					datEvaluadorOBJ.setCelular(rs.getString(i++));
			//		datEvaluadorOBJ.setAreasTrabajo(rs.getString(i++));
				}
				datEvaluadorOBJ.setAreasTrabajo(areasEvaluador(cn,codEval));
			}
		} catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return datEvaluadorOBJ;
	}

	private List areasEvaluador(Connection cn, String codEval) {
		List lista=new ArrayList();
		PreparedStatement ps=null;
		ResultSet rs=null;
		AreasTrabOBJ areas=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("getAreasTrabajo"));
			ps.setLong(i++,Long.parseLong(codEval));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				areas=new AreasTrabOBJ();
				areas.setIdArea(rs.getLong(i++));
				areas.setArea(rs.getString(i++));
				areas.setCampos(rs.getString(i++));
				lista.add(areas);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			//cerrar(cn);
		}
		return lista;
	}

}
