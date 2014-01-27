package cidc.adminArticulos.db;

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
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.CrearClaves;
import cidc.general.obj.Globales;
import cidc.adminArticulos.obj.ArticRevista;
import cidc.adminArticulos.obj.Articulo;
import cidc.adminArticulos.obj.DatEvaluador;
import cidc.adminArticulos.obj.EstadoArticulo;
import cidc.adminArticulos.obj.FiltroArticulo;
import cidc.adminArticulos.obj.Parametros;



public class AdminArticuloRevistaDB extends BaseDB{

	public AdminArticuloRevistaDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.adminArticulos.consultas");
	}

	public boolean nuevoArticuloRevista(ArticRevista articulo) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("nuevoArticuloRevista"+articulo.getTipo()));
			ps.setString(i++,articulo.getTitulo());
			ps.setString(i++,articulo.getArchivo());
		//	ps.setInt(i++,articulo.getTipo());
			if(articulo.getTipo()==1)
				ps.setLong(i++,articulo.getInvestigador());
			if(articulo.getTipo()==2){
				ps.setString(i++,articulo.getNombreAutor());
				ps.setString(i++,articulo.getApellidoAutor());
				ps.setString(i++,articulo.getDe());
			}
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

	public long getIdArchivo() {
		long id=0;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("idArchivo"));
			rs=ps.executeQuery();
			while (rs.next()) {
				id=rs.getLong(1);
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
		return id;
	}

	public List getListaArticulosRevista(FiltroArticulo filtro){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		ArticRevista articulo=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaArticulosRevista"+filtro.getInterno()));
			rs=ps.executeQuery();
			while (rs.next()){
				i=1;
				articulo=new ArticRevista();
				articulo.setIdArticulo(rs.getLong(i++));
				articulo.setNombreAutor(rs.getString(i++));
				articulo.setApellidoAutor(rs.getString(i++));
				articulo.setTitulo(rs.getString(i++));
				articulo.setEstado((rs.getInt(i++)));
				lista.add(articulo);
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
	public List getListaArticEvenIberdiscap(){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		Articulo articulo=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaArticulosIberdiscap"));
			rs=ps.executeQuery();
			while (rs.next()){
				articulo=new Articulo();
				i=1;
				articulo.setIdArticulo(rs.getLong(i++));
				articulo.setPresentador(rs.getString(i++));
				articulo.setTituloArticulo(rs.getString(i++));
				articulo.setFechaRecibido((rs.getString(i++)));
				articulo.setEstado((rs.getInt(i++)));
				lista.add(articulo);
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
	public List getListaArticEvenGrupos(){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		Articulo articulo=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaArticulosEvenGrupos"));
			rs=ps.executeQuery();
			while (rs.next()){
				i=1;
				articulo=new Articulo();
				articulo.setIdArticulo(rs.getLong(i++));
				articulo.setPresentador(rs.getString(i++));
				articulo.setTituloArticulo(rs.getString(i++));
				articulo.setFechaRecibido((rs.getString(i++)));
				articulo.setEstado((rs.getInt(i++)));
				lista.add(articulo);
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
	public Object getInfoArticulo(String idArticulo,int tipo, int evento, int interno){
		EstadoArticulo estadoArticulo=null;
		ArticRevista articRevista=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String fecha=null;
		Object objeto=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			if(tipo==1)
				ps=cn.prepareStatement(rb.getString("getInfoArticuloRevista"+interno));
			if(tipo==2)
				ps=cn.prepareStatement(rb.getString("getInfoArticuloEvento"+evento));

			ps.setLong(1, Long.parseLong(idArticulo));
			rs=ps.executeQuery();
			while (rs.next()){
				i=1;
				if(tipo==2){
					//if(evento==Parametros.cmdEvenGruposSemilleros){
						estadoArticulo=new EstadoArticulo();
						estadoArticulo.setIdArticulo(rs.getLong(i++));
						estadoArticulo.setPresentador(rs.getString(i++));
						estadoArticulo.setTituloArticulo(rs.getString(i++));
						estadoArticulo.setFecRecibido(rs.getString(i++));
						estadoArticulo.setArchivo(rs.getString(i++));

						ps=cn.prepareStatement(rb.getString("getEstadoArticulo"));
						ps.setLong(1, Long.parseLong(idArticulo));
						rs=ps.executeQuery();

						while (rs.next()){
							i=1;
							estadoArticulo.setCodEval(rs.getString(i++));
							fecha=rs.getString(i++);
							if(fecha!=null)
								if(fecha.contains("."))
									fecha=fecha.replace('-','/').substring(0,fecha.lastIndexOf('.'));
							estadoArticulo.setFecMail(fecha);
							estadoArticulo.setFecDocumentos(rs.getString(i++));
							estadoArticulo.setFecEvaluacion(rs.getString(i++));
					//		System.out.println("cod eval="+estadoArticulo.getCodEval());
						}
						estadoArticulo.setListaEvaluador(getListaEvaluador(idArticulo,cn,2));
					//}
						objeto=estadoArticulo;
				}
				if(tipo==1){
		//			System.out.println("Encuentra cosa");
					articRevista=new ArticRevista();
					articRevista.setIdArticulo(rs.getLong(i++));
					articRevista.setTitulo(rs.getString(i++));
					articRevista.setNombreAutor(rs.getString(i++));
					articRevista.setApellidoAutor(rs.getString(i++));
					articRevista.setArchivo(rs.getString(i++));
					articRevista.setFechaRecibido(rs.getString(i++));
					articRevista.setEstado(rs.getInt(i++));
					articRevista.setFechaEvaluado(rs.getString(i++));
		//			System.out.println("Estado:--->"+articRevista.getEstado());

					ps=cn.prepareStatement(rb.getString("getEstadoArticulo"));
					ps.setLong(1, Long.parseLong(idArticulo));
					rs=ps.executeQuery();
					while (rs.next()){
						i=1;
						articRevista.setIdEval(""+rs.getLong(i++));

						fecha=rs.getString(i++);
						if(fecha!=null)
							if(fecha.contains("."))
								fecha=fecha.replace('-','/').substring(0,fecha.lastIndexOf('.'));
						articRevista.setFechaEvaluado(fecha);
						fecha=rs.getString(i++);
						if(fecha!=null)
							if(fecha.contains("."))
								fecha=fecha.replace('-','/').substring(0,fecha.lastIndexOf('.'));
						articRevista.setFechaDocumentos(fecha);

				//		System.out.println("cod eval="+estadoArticulo.getCodEval());
					}

					articRevista.setListaEval(getListaEvaluador(idArticulo,cn,1));
					objeto=articRevista;
					break;
				}

				break;
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}
		catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return objeto;
	}

	private List getListaEvaluador(String idArticulo, Connection cn, int tipo) {
		List listaEval=new ArrayList();
		PreparedStatement ps=null;
		ResultSet rs=null;
		DatEvaluador evaluador=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getHistoEval"+tipo));
			ps.setLong(1, Long.parseLong(idArticulo));
			rs=ps.executeQuery();
			while (rs.next()){
				i=1;
				evaluador=new DatEvaluador();
				evaluador.setId(rs.getLong(i++));
				evaluador.setNombre(rs.getString(i++));
				evaluador.setAsignacion(rs.getString(i++));
				evaluador.setRespuesta(rs.getString(i++));
				evaluador.setEstado(rs.getString(i++));
				evaluador.setCancela(rs.getString(i++));
				evaluador.setTipo(rs.getInt(i++));
				listaEval.add(evaluador);
			}
		}catch (SQLException e){
			lanzaExcepcion(e);
		}catch (Exception e){
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return listaEval;
	}

	public boolean asignaEvaluador(String artic, String codEval, int tipo) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String []datos=new String [4];
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("fechaAsignacion"+tipo));
	//		System.out.println(artic+"*********"+codEval);
			ps.setLong(1,Long.parseLong(codEval));
			ps.setLong(2,Long.parseLong(artic));
			ps.executeUpdate();

			ps=cn.prepareStatement(rb.getString("getMailEvaluador"));
			ps.setLong(1,Long.parseLong(artic));
	//		ps.setLong(2,Long.parseLong(codEval));
			rs=ps.executeQuery();

			while(rs.next()){
	//			System.out.println("encuentra datos eval");
				datos[0]=rs.getString(2);
				datos[1]=rs.getString(3);
				datos[2]=rs.getString(4);
				datos[3]=rs.getString(5);
			}
		//	System.out.println("ENTRA A ASIGNACION");
		//	mailAsignacion(datos);//********************enviar mail a evaluador

			retorno=true;

			ps=cn.prepareStatement(rb.getString("cambia_estado"));
			ps.setInt(1,2);
			ps.setString(2,artic);
			ps.executeUpdate();
			System.out.println("cambia estado");
			cn.commit();
			retorno=true;
		} catch (SQLException e) {
			lanzaExcepcion(e);
			setMensaje("No se puede asignar nuevamente este evaluador a esta propuesta");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			setMensaje("La Direccion de correo no es correcta");
			lanzaExcepcion(e);
		}catch (MessagingException e) {
			// TODO Auto-generated catch block
			setMensaje("El correo no pudo ser enviado al Evaluador");
			lanzaExcepcion(e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}
	public void mailAsignacion(String []datos) throws AddressException,MessagingException{
		String []direcciones={datos[1]};
		ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.correoEvaluadores");
		Globales global=new Globales();
		String consecutivo="\nSICIUD-"+datos[2]+"-"+global.getAnoCortoHoy()+"\n\n";
		EnvioMail2 envioMail=new EnvioMail2("academico");
		String texto=consecutivo;
		texto+=rb.getString("f1");
		texto+=" "+datos[0];//nombre del evaluador
		texto+=rb.getString("f2");
		texto+="<b> "+datos[3]+"</b>";//	nombre de la propuesta
		texto+=" "+rb.getString("f3");
		texto+=rb.getString("f4");
		texto+=rb.getString("f5");
		texto+=" "+rb.getString("f6");

		envioMail.enviar(direcciones,"Evaluación de Artículo",texto);
		Reporte reporteMail=new Reporte(cursor,super.perfil);
		reporteMail.reportar(datos[0],"Asignación Par Evaluador Articulo",direcciones[0],datos[3]);
	//	System.out.println("Mensaje Enviado");
	}

	public boolean asignaRespuesta(String artic, String resp, String codEval) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("asignaRespuesta"));
			ps.setInt(i++,Integer.parseInt(resp));
			ps.setString(i++,codEval);
			ps.setLong(i++,Long.parseLong(artic));
			ps.executeUpdate();
			ps=cn.prepareStatement(rb.getString("cambia_estado"));
			if(Integer.parseInt(resp)==1)
				ps.setInt(1,3);
			else
				ps.setInt(1,1);
			ps.setLong(2,Long.parseLong(artic));
			ps.executeUpdate();
		//	System.out.println("+++"+ps.getUpdateCount());
			retorno=true;
		} catch (SQLException e) {
			lanzaExcepcion(e);
			retorno=false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}

	public boolean cancelaEval(String artic, String codEval) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("cancelaEvaluador"));
			ps.setString(i++,codEval);
			ps.setLong(i++,Long.parseLong(artic));
			ps.executeUpdate();
			ps=cn.prepareStatement(rb.getString("cambia_estado"));
			ps.setInt(1,1);
			ps.setLong(2,Long.parseLong(artic));
			ps.executeUpdate();
			cn.commit();
			retorno=true;
		} catch (SQLException e) {
			lanzaExcepcion(e);
			retorno=false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}
	public boolean entregaDocs(String artic) {
		Connection cn=null;
		PreparedStatement ps=null;
		boolean retorno=false;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("entregaDocs"));
			ps.setLong(1,Long.parseLong(artic));
			ps.executeUpdate();
			ps=cn.prepareStatement(rb.getString("cambia_estado"));
			ps.setInt(1,6);
			ps.setLong(2,Long.parseLong(artic));
			ps.executeUpdate();
			retorno=true;
		} catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}
		return retorno;
	}
}


