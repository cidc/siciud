package cidc.evalPropuesta.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cidc.convocatorias.obj.AspectosOBJ;
import cidc.evalPropuesta.obj.Criterio;
import cidc.evalPropuesta.obj.DatosCalculo;
import cidc.evalPropuesta.obj.EvalVacia;
import cidc.evalPropuesta.obj.EvalVaciaComite;
import cidc.evalPropuesta.obj.PorEvaluar;
import cidc.evalPropuesta.obj.Aspecto;
import cidc.evalPropuesta.obj.CapturaEvalOBJ;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.Globales;
import cidc.general.obj.Parametros;

public class EvalPropuestaDB extends BaseDB {

	public EvalPropuestaDB(CursorDB c, int perfil) {
		super(c,perfil);
		rb=ResourceBundle.getBundle("cidc.evalPropuesta.consultas");
	}
	//lista las propuestas de movilidad según el perfil de la persona que esta en sesión
	public List getPropuestas(long idPersona){
		PorEvaluar porEvaluar=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		String tipoEval=null;
		int i;
		try {
			cn=cursor.getConnection(super.perfil);
			//System.out.println("perfil="+super.perfil);
			if(super.perfil==Parametros.userComite || super.perfil==Parametros.userFuncionarioA)
				ps=cn.prepareStatement(rb.getString("getPropuestasEvaluar3"));
			if(super.perfil==Parametros.userIeie)
				ps=cn.prepareStatement(rb.getString("getPropuestasEvaluar13"));
			if(super.perfil!=Parametros.userComite && super.perfil!=Parametros.userIeie && super.perfil!=Parametros.userFuncionarioA){
				ps=cn.prepareStatement(rb.getString("getTipoEval"));
				ps.setLong(1,idPersona);
				rs=ps.executeQuery();
				while(rs.next()){
					tipoEval=rs.getString(1);
				}
				ps=cn.prepareStatement(rb.getString("getPropuestasEvaluar"+tipoEval));
		//		System.out.println("Persona="+idPersona+" "+tipoEval);
				ps.setLong(1,idPersona);
			}
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				porEvaluar=new PorEvaluar();
				porEvaluar.setId(rs.getLong(i++));
				porEvaluar.setNombre(rs.getString(i++));
				porEvaluar.setUrlArchivo(rs.getString(i++));
				porEvaluar.setEstado(rs.getInt(i++));
				if(super.perfil==Parametros.userComite || super.perfil==Parametros.userIeie)
					porEvaluar.setConvocatoria(rs.getString(i++));
				lista.add(porEvaluar);
			}
		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(cn);
			cerrar(ps);
			cerrar(rs);
		}
		return lista;
	}

	public EvalVacia getCritAspecto(String idProp) {
		EvalVacia evalVacia=new EvalVacia();
		Criterio criterio=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List listCriterios=new ArrayList();

		try {
			evalVacia.setIdPropuesta(idProp);
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getCriterios"));
			ps.setLong(1,Long.parseLong(idProp));
			rs=ps.executeQuery();
			while(rs.next()){
				criterio=new Criterio();
				criterio.setIdCriterio(rs.getLong(1));
				criterio.setNombre(rs.getString(2));
				criterio.setValor(rs.getDouble(3));
				criterio.setAspectos(getAspectos(cn,criterio.getIdCriterio(),idProp));
				listCriterios.add(criterio);
			}
			evalVacia.setCriterios(listCriterios);
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(cn);
			cerrar(ps);
			cerrar(rs);
		}
		return evalVacia;
	}

	public List getAspectos(Connection cn, long criterio, String propuesta){
		List listAspectos=new ArrayList();
		Aspecto aspecto=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			ps=cn.prepareStatement(rb.getString("getAspectos"));
			ps.setInt(1,Integer.parseInt(propuesta));
			ps.setLong(2,criterio);
			rs=ps.executeQuery();
			while(rs.next()){
				aspecto=new Aspecto();
				aspecto.setIdAspecto(rs.getLong(1));
				aspecto.setNombre(rs.getString(2));
				aspecto.setValor(rs.getDouble(3));
				aspecto.setIdCriterio(rs.getLong(4));

				listAspectos.add(aspecto);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(rs);
		}

		return listAspectos;
	}

	public boolean insertaEvaluacion(CapturaEvalOBJ evaluacion,long idEval){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean retorno=false;
		int tipoEval=0;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			if(super.perfil==Parametros.userComite || super.perfil==Parametros.userIeie){
				tipoEval=super.perfil;
				ps=cn.prepareStatement(rb.getString("insertaAspecto"));
				for(int i=0;i<evaluacion.getIdAspecto().length;i++){
					ps.setLong(1,evaluacion.getProp());
					ps.setInt(2,evaluacion.getIdAspecto()[i]);
					ps.setDouble(3,evaluacion.getValAspecto()[i]);
					ps.setInt(4,tipoEval);
					ps.addBatch();
				}
				ps.executeBatch();
			}
			else{
				ps=cn.prepareStatement(rb.getString("getTipoEval"));
				ps.setLong(1,idEval);
				rs=ps.executeQuery();
				while(rs.next()){
					tipoEval=rs.getInt(1);
				}
				ps=cn.prepareStatement(rb.getString("insertaCriterio"));
				for(int i=0;i<evaluacion.getIdCriterio().length;i++){
					ps.setLong(1,evaluacion.getProp());
					ps.setLong(2,evaluacion.getIdCriterio()[i]);
					ps.setString(3,evaluacion.getObservaciones()[i]);
					ps.setInt(4,tipoEval);
					ps.addBatch();
				}
				ps.executeBatch();
				ps=cn.prepareStatement(rb.getString("insertaAspecto"));
				for(int i=0;i<evaluacion.getIdAspecto().length;i++){
					ps.setLong(1,evaluacion.getProp());
					ps.setLong(2,evaluacion.getIdAspecto()[i]);
					ps.setDouble(3,evaluacion.getValAspecto()[i]);
					ps.setInt(4,tipoEval);
					ps.addBatch();
				}
				ps.executeBatch();
				eliminaPropuesta(evaluacion.getProp(),idEval,cn);
			}

			cambiaEstado(evaluacion.getProp(),tipoEval,cn);

			cn.commit();

			retorno=true;
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(cn);
			cerrar(ps);
			cerrar(rs);
		}
		return retorno;
	}

	private void eliminaPropuesta(long prop, long idEval, Connection cn)throws SQLException {
		PreparedStatement ps=null;
		try{
		/*	ps=cn.prepareStatement(rb.getString("eliminaPropuesta"));
			ps.setLong(1,idEval);
			ps.setLong(2,prop);
			ps.executeUpdate();*/

			ps=cn.prepareStatement(rb.getString("eliminaPropuesta"));
	//		System.out.println("elimina registro "+idEval+" p= "+prop);
			ps.setLong(1,idEval);
			ps.setLong(2,prop);
			ps.executeUpdate();
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
		}
	}

	public void cambiaEstado(long prop, int tipoEval, Connection cn)throws SQLException{
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{

			if(tipoEval!=0){
				ps=cn.prepareStatement(rb.getString("cambia_estado"+tipoEval));
				if(tipoEval==Parametros.userComite ||tipoEval==Parametros.userIeie)
					ps.setInt(1,2);
				else
					ps.setInt(1,5);
				ps.setLong(2,prop);
				ps.executeUpdate();

				ps=cn.prepareStatement(rb.getString("fechaEval"+tipoEval));
				ps.setLong(1,prop);
				ps.executeUpdate();
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(rs);
		}
	}

	public EvalVacia getResultEval(String tipoEval,String idProp) {
		EvalVacia evalVacia=new EvalVacia();
		Criterio criterio=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List listCriterios=new ArrayList();

		try {
			evalVacia.setIdPropuesta(idProp);
			cn=cursor.getConnection(super.perfil);

			ps=cn.prepareStatement(rb.getString("evaluadorPropuesta"));
			ps.setLong(1,Long.parseLong(tipoEval));
			ps.setLong(2,Long.parseLong(idProp));
			rs=ps.executeQuery();
			while(rs.next()){
				evalVacia.setNombEvaluador(rs.getString(1));
			}
			ps=cn.prepareStatement(rb.getString("resultadoCriterio"));
			ps.setLong(1,Long.parseLong(tipoEval));
			ps.setLong(2,Long.parseLong(idProp));
			rs=ps.executeQuery();

		//	System.out.println("tipEval= "+tipoEval+" idprop= "+idProp);
			while(rs.next()){
		//		System.out.println("encuentra criterio");
				criterio=new Criterio();
				criterio.setIdCriterio(rs.getLong(1));
				criterio.setNombre(rs.getString(2));
				criterio.setObservaciones(rs.getString(3));
				criterio.setAspectos(getResultAspectos(cn,criterio.getIdCriterio(),idProp,Integer.parseInt(tipoEval)));
				listCriterios.add(criterio);
			}
			evalVacia.setCriterios(listCriterios);
		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(cn);
			cerrar(ps);
			cerrar(rs);
		}
		return evalVacia;
	}

	private List getResultAspectos(Connection cn, long idCriterio, String idProp,int tipoEval) {
		List listAspectos=new ArrayList();
		Aspecto aspecto=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("resultadoAspecto"));
			ps.setLong(i++,tipoEval);
			ps.setLong(i++,idCriterio);
			ps.setLong(i++,Long.parseLong(idProp));
	//		System.out.println("tipoeval="+tipoEval+" idcriterio="+idCriterio+" prop="+idProp);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
	//			System.out.println("---");
				aspecto=new Aspecto();
				aspecto.setIdCriterio(rs.getLong(i++));
				aspecto.setNombre(rs.getString(i++));
				aspecto.setMaxValor(rs.getDouble(i++));
				aspecto.setValor(rs.getDouble(i++));
	//			System.out.println("aspecto: "+aspecto.getIdCriterio()+"  Valor: "+aspecto.getValor());
				listAspectos.add(aspecto);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return listAspectos;
	}
	private List getResultAspectosComite(Connection cn, String idProp) {
		List listAspectos=new ArrayList();
		Aspecto aspecto=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("resultadoAspectoComite"));
			ps.setLong(i++,Long.parseLong(idProp));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				aspecto=new Aspecto();
				aspecto.setIdCriterio(rs.getLong(i++));
				aspecto.setNombre(rs.getString(i++));
				aspecto.setMaxValor(rs.getDouble(i++));
				aspecto.setValor(rs.getDouble(i++));
		//		System.out.println("aspecto: "+aspecto.getIdCriterio()+"  Valor: "+aspecto.getValor());
				listAspectos.add(aspecto);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return listAspectos;
	}


	public EvalVaciaComite getPorcentajes(String propId) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		EvalVaciaComite evalVaciaComite=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getPorcentaje"));
			ps.setLong(i++,Long.parseLong(propId));
			rs=ps.executeQuery();
	//		System.out.println("prop"+propId);
			while(rs.next()){
	//			System.out.println("encuentra");
				i=1;
				evalVaciaComite=new EvalVaciaComite();
				evalVaciaComite.setIdPropuesta(Long.parseLong(propId));
				evalVaciaComite.setValorPorcentaje(rs.getString(i++));
				evalVaciaComite.setNombPropuesta(rs.getString(i++));
				evalVaciaComite.setNombConvocatoria(rs.getString(i++));
				evalVaciaComite.setListaAspectos(getAspectosComite(cn,propId));
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return evalVaciaComite;
	}

	private List getAspectosComite(Connection cn, String idProp) {
		List listAspectos=new ArrayList();
		Aspecto aspecto=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("getAspectosComite"));
			ps.setLong(1,Long.parseLong(idProp));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				aspecto=new Aspecto();
				aspecto.setIdAspecto(rs.getLong(i++));
				aspecto.setNombre(rs.getString(i++));
				listAspectos.add(aspecto);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return listAspectos;
	}


	public boolean isPswCorrecta(String prop,String clave) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("clavePropuesta"));
			ps.setString(1,prop);
			ps.setString(2,clave);
			rs=ps.executeQuery();
			while(rs.next()){
				retorno=true;
				break;
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
	//	System.out.println("Retorno="+retorno);
		return retorno;
	}

	public EvalVacia getEvaluacionTodos(String idProp) {
		EvalVacia evalVacia=new EvalVacia();
		Criterio criterio=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List listCriterios=new ArrayList();
		try {
			evalVacia.setIdPropuesta(idProp);
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getCriteriosConvocatoria"));
			ps.setLong(1,Long.parseLong(idProp));
			rs=ps.executeQuery();
		//	System.out.println(" idprop= "+idProp);
			while(rs.next()){
				criterio=new Criterio();
				criterio.setIdCriterio(rs.getLong(1));
				criterio.setNombre(rs.getString(2));
				criterio.setAspectos(getResultAspectos(cn,criterio.getIdCriterio(),idProp,1));
				criterio.setAspectos2(getResultAspectos(cn,criterio.getIdCriterio(),idProp,2));
				listCriterios.add(criterio);
			}

			evalVacia.setCriterios(listCriterios);
			evalVacia.setCritComite(getResultAspectosComite(cn,idProp));
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(cn);
			cerrar(ps);
			cerrar(rs);
		}
		return evalVacia;
	}

	public DatosCalculo getDatosCalculo(String prop) {
		DatosCalculo datosCalculo=null;
		Aspecto aspecto=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		int com=0;
		int i=1;
		try{
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getDatosCalculo"));			
			ps.setLong(1,Long.parseLong(prop));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				datosCalculo=new DatosCalculo();
				datosCalculo.setPorcentInt(rs.getString(i++));
				datosCalculo.setPorcentExt(rs.getString(i++));
				datosCalculo.setPorcentComite(rs.getString(i++));
				datosCalculo.setPropuesta(prop);
			}
			if(datosCalculo!=null){
				int comit=0;
				if(super.perfil==3)
					comit=6;
				if(super.perfil==13)
					comit=15;
				if(super.perfil!=13 && super.perfil!=13)
					comit=getComiteConvocatoria(prop);
				ps=cn.prepareStatement(rb.getString("resultadoEvalComite"));
				ps.setInt(1,comit);
				ps.setLong(2,Long.parseLong(prop));
				rs=ps.executeQuery();
//				System.out.println("antres de recibir consulta "+comit+" "+prop+" ");
				while(rs.next()){
					i=1;
					aspecto=new Aspecto();
					aspecto.setNombre(rs.getString(i++));
					aspecto.setValor(Double.parseDouble(rs.getString(i++)));
					datosCalculo.setSumaComite(datosCalculo.getSumaComite()+aspecto.getValor());
					lista.add(aspecto);
	//				System.out.println("encuentra datos evalcomite"+aspecto.getNombre());
				}
				datosCalculo.setFinalComite((datosCalculo.getSumaComite()/lista.size())*(Float.parseFloat(datosCalculo.getPorcentComite())/100));
		/*		System.out.println("porc->"+datosCalculo.getPorcentComite());
				System.out.println("deci->"+(Float.parseFloat(datosCalculo.getPorcentComite())/100));
				System.out.println("val->"+datosCalculo.getSumaComite());
				System.out.println("--- "+(datosCalculo.getSumaComite()/lista.size())*(Float.parseFloat(datosCalculo.getPorcentComite())/100));*/
				datosCalculo.setListaAspectoComite(lista);
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
		return datosCalculo;
	}

	private int getComiteConvocatoria(String prop){
		int conv=6;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getComiteConvocatoria"));
			ps.setLong(1,Long.parseLong(prop));
			rs=ps.executeQuery();
			while(rs.next()){
				conv=rs.getInt(1);
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
		return conv;
	}

	public void propuestaAprobada(String idPropuesta, String nota) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String codigoProyecto=null;
		long facultad=0,proyectoCur=0,consec=0;
		Globales global=new Globales();
		int i=1,estado=0;
		try {
	//		System.out.println("propuesta ="+idPropuesta+" nota="+nota);
			estado=Integer.parseInt(nota);
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("setNotaComite"));
			ps.setInt(1,estado);
			ps.setLong(2,Long.parseLong(idPropuesta));
			ps.executeUpdate();
			switch(estado){
				case 1:
					ps=cn.prepareStatement(rb.getString("cambiaEstado"));
					ps.setInt(1,3);
					ps.setLong(2,Long.parseLong(idPropuesta));
					ps.executeUpdate();

					ps=cn.prepareStatement(rb.getString("getDatosPropuesta"));
					ps.setLong(1,Long.parseLong(idPropuesta));
					rs=ps.executeQuery();
					while(rs.next()){
						facultad=rs.getLong(i++);
						proyectoCur=rs.getLong(i++);
						consec=rs.getLong(i++);
					}
					i=1;
					codigoProyecto=""+facultad+"-"+proyectoCur+"-"+(consec+1)+"-"+global.getAnoCortoHoy();

					ps=cn.prepareStatement(rb.getString("nuevoProyecto"));
					ps.setString(i++,codigoProyecto);
					ps.setLong(i++,Long.parseLong(idPropuesta));
					ps.setInt(i++,Integer.parseInt(new Globales().getAnoHoy()));
					ps.executeUpdate();
					/*
					 * se debe hacer la inserciï¿½n de los rubros aprobados en la nueva tabla de rubros proyecto
					 */
					setMensaje("La propuesta ha sido Aprobada Satisfactoriamente");
				break;
				case 2:
					ps=cn.prepareStatement(rb.getString("cambiaEstado"));
					ps.setInt(1,4);
					ps.setLong(2,Long.parseLong(idPropuesta));
					ps.executeUpdate();
					setMensaje("La propuesta ha sido Rechazada Satisfactoriamente");
				break;
			}
			cn.commit();
	//		System.out.println("El valor del cï¿½digo es "+codigoProyecto);
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

	public void notificarEvaluacion(String []datos){
			String []direcciones={datos[1]};
			ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.correoEvaluadores");
			Globales global=new Globales();
			EnvioMail2 envioMail=new EnvioMail2("siciud");
			StringBuffer texto=new StringBuffer();
			texto.append("<br>SICIUD-"+datos[0]+"-"+global.getAnoCortoHoy()+"<br><br>");
			texto.append(rb.getString("rp1"));
			texto.append(datos[2]);
			texto.append(rb.getString("rp2"));
			texto.append(datos[3]);
			if(datos[4].equals("1")){
				texto.append(rb.getString("rp31"));
				texto.append(rb.getString("rp41"));
			}
			else{
				texto.append(rb.getString("rp32"));
				texto.append(rb.getString("rp41"));
			}
			texto.append(rb.getString("rp5"));
			texto.append(rb.getString("rp6"));
			texto.append(rb.getString("rp7"));
			try {
/*
 * hay que arreglar la cuestiï¿½n del calculo del puntaje de evaluaciï¿½n para el mï¿½dulo de
 * comite de invest.
 * */
				envioMail.enviar(direcciones,datos[3],""+texto);
			} catch (AddressException e) {
				lanzaExcepcion(e);
			} catch (MessagingException e) {
				lanzaExcepcion(e);
			}
			Reporte reporteMail=new Reporte(cursor,super.perfil);
			reporteMail.reportar(datos[2],"Clave de Ingreso sistema",direcciones[0],datos[0]);

		//	System.out.println("Mensaje Enviado");
	}
}
