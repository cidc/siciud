package cidc.certificaciones.db;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest; 
import javax.servlet.http.HttpServletResponse;

import cidc.general.db.CursorDB;
import cidc.general.db.BaseDB;
import cidc.general.obj.Globales;
import cidc.general.pdf.DocumentosPDF;
import cidc.proyectosGeneral.obj.Proyecto;
import cidc.certificaciones.obj.CertificacionesOBJ;
import cidc.certificaciones.obj.ProyectoActividad;
import cidc.certificaciones.pdf.GenerarCertificados;
import cidc.certificaciones.signer.Firmar;
import cidc.convMovilidad.obj.InfoGeneral;

public class CertificadoDB extends BaseDB{
	public static char sep=java.io.File.separatorChar;
	
	public CertificadoDB(CursorDB c, int perfil) {
		super(c, perfil);
		rb=ResourceBundle.getBundle("cidc.certificaciones.consultas");
	}
	
	/**
	 * 
	 * @param cedula
	 * @param codVerificacion
	 * @param nombreProyect
	 * @param apellido
	 * @param tipoPersona
	 * @return
	 */
	public CertificacionesOBJ crearcertificado1(CertificacionesOBJ certificado, String path, HttpServletResponse resp){
		System.out.println("Fucntion Certificado1");
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		GenerarCertificados generador= null;
		String consec=null;
		String tabla=null;
		int i=1;
		int ano=Calendar.getInstance().get(Calendar.YEAR);
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("infoCertificado1"));
			ps.setLong(1,certificado.getIdPersona());
			ps.setLong(2,certificado.getIdGrupo());
//			ps.setLong(1,5077);
//			ps.setLong(2,1);	
			System.out.println("Consulta: "+ps);
			rs=ps.executeQuery();
			while(rs.next()){
				consec=rs.getString(i++);				
				certificado.setNombreGrupo(rs.getString(i++));
				certificado.setIdGrupo(rs.getInt(i++));
				certificado.setNombre(rs.getString(i++));
				certificado.setCedula(rs.getString(i++));
				certificado.setNumCedDe(rs.getString(i++));
				certificado.setFecha_cert(rs.getString(i++));
				certificado.setNombreDirector(rs.getString(i++));
				certificado.setRol(rs.getString(i++));
				certificado.setTipo("1");
				certificado.setCategoriaGrupo(rs.getString(i++));
				certificado.setFechaIngreso(rs.getString(i++));
				certificado.setFechaSalida(rs.getString(i++));
				certificado.setPapel(rs.getString(i++));
				certificado.setTipoGrupo(rs.getString(i++));
				
				if(tabla!=null)
					certificado.setConsCert(tabla);
				else
					certificado.setConsCert(consec);
				certificado.setCod_verificacion("CIDC_"+certificado.getTipo()+"_"+consec+"_"+ano);
				String dir=path+sep+"FirmaCIDC.pfx";
				String ruta=path+sep+"Documentos"+sep+"Certificados"+sep+"CIDC_"+certificado.getTipo()+"_"+certificado.getConsCert()+"_"+ano+".pdf";
				generador=new GenerarCertificados();
				generador.crearCertificadoPertenencia(certificado, ruta, resp);
				if(!insertaCertificadoBD(certificado,generador.marcaAgua(ruta,path),dir)){
					return null;
				}
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
		
		return certificado;		
	}
	
	/**
	 * Este metodo inserta los datos del certificado generado 
	 * @param certificado objeto certificado con toda la informacion necesaria para almacenar en la BD
	 * @param url url de donde se almacena el certificado
	 */
	public boolean insertaCertificadoBD(CertificacionesOBJ certificado,String path,String dir){
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			Firmar firmar= new Firmar();
			File documento=new File(path);
			String url =firmar.firmarPDF(documento,dir);
			if(url==null){
				System.out.println("Se produjo un error al firmar");
				return false;
			}
			documento.delete();
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("crearCertificado"));
			ps.setInt(1, Integer.parseInt(certificado.getTipo()));
			ps.setString(2, certificado.getCuerpo_cer());
			ps.setString(3, certificado.getCod_verificacion());
			ps.setLong(4, certificado.getIdPersona());
			ps.setString(5, certificado.getCedula());
			ps.setString(6, url);
//			ps.setString(6, dir);
			ps.setInt(7, certificado.getIdGrupo());
			ps.execute();
			ps=cn.prepareStatement(rb.getString("Certificado++"));
			ps.execute();
			
		} catch (Exception e) {
			lanzaExcepcion(e);
			return false;
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return true;
	}
	/**
	 * 
	 */
	public List buscarCertificados(String cedula, String codVerificacion, String nombre,String apellido,int tipo){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		CertificacionesOBJ certificado=null;
		String cedu;
		String cod;
		String nom;
		String ape;
		List <CertificacionesOBJ>listacertificados=new ArrayList<CertificacionesOBJ>();		
		int i=1;
		try {
			if(cedula!=null){
				if(cedula.trim().equals(""))
					cedu="%";
				else
					cedu="%"+cedula+"%";
			}else
				cedu="%";
			if(codVerificacion!=null){
				if(codVerificacion.trim().equals(""))
					cod="%";
				else
					cod="%"+codVerificacion+"%";
			}else
				cod="%";
			if(nombre!=null){
				if(nombre.trim().equals(""))
					nom="%";
				else
					nom="%"+nombre+"%";
			}else
				nom="%";
			if(apellido!=null){
				if(apellido.trim().equals(""))
					ape="%";
				else
					ape="%"+nombre+"%";
			}else
				ape="%";
			System.out.println("CEDULA---->"+cedu+ " COD_VERIFICACIÓN--->"+cod);
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("BuscarCertificado"));
			ps.setString(1,cedu);
			ps.setString(2,cod);
			ps.setString(3,nom);
			ps.setString(4,ape);
			ps.setInt(5,tipo);
			System.out.println("Consulta: "+ps);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				certificado=new CertificacionesOBJ();
				certificado.setId_certificaciones(rs.getInt(i++));
				certificado.setTipo(rs.getString(i++));
				certificado.setFecha_cert(rs.getString(i++));
				certificado.setCod_verificacion(rs.getString(i++));
				certificado.setAutomatico(rs.getBoolean(i++));
				certificado.setNombre(rs.getString(i++));
				certificado.setUrl(rs.getString(i++));				
				certificado.setNombreGrupo(rs.getString(i++));
				listacertificados.add(certificado);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return listacertificados;
	}
	
	public List buscarCertificadosPersona(long id_persona,int tipo){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		CertificacionesOBJ certificado=null;
		String cedu;
		String cod;
		List <CertificacionesOBJ>listacertificados=new ArrayList<CertificacionesOBJ>();		
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("BuscarCertificadoPersona"));
			ps.setLong(1,id_persona);
			ps.setInt(2, tipo);
			System.out.println("Consulta: "+ps);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				certificado=new CertificacionesOBJ();
				certificado.setId_certificaciones(rs.getInt(i++));
				certificado.setTipo(rs.getString(i++));
				certificado.setFecha_cert(rs.getString(i++));
				certificado.setCod_verificacion(rs.getString(i++));
				certificado.setAutomatico(rs.getBoolean(i++));
				certificado.setNombre(rs.getString(i++));
				certificado.setUrl(rs.getString(i++));				
				certificado.setNombreGrupo(rs.getString(i++));
				listacertificados.add(certificado);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return listacertificados;
	}
	/**
	 * El metodo valida que todos los proyectos de la persona esten es estado terminado o culmidado
	 * @param idPersona es el id de la persona
	 * @return true si todos lo proyectos estan terminados, false si alguno esta en un estado diferente al 3 o 7
	 */
	public boolean consultarProyectosPaz(long idPersona){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Proyecto proy=null;
		boolean aPaz = true;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("PazYSalvo"));
			ps.setLong(1, idPersona);
			ps.setLong(2, idPersona);
			System.out.println("Consulta: "+ps.toString());
			rs=ps.executeQuery();
			while(rs.next()){
				int i=1;
				proy = new Proyecto();
				proy.setEstado(rs.getInt(i++));
				aPaz=((proy.getEstado()!=3&&proy.getEstado()!=7)?false:true);
				if(!aPaz)
					break;
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return aPaz;
	}

	public CertificacionesOBJ certificadoPazSalvo(CertificacionesOBJ certificado, String path, HttpServletResponse resp){
		//System.out.println("Fucnion Certificado2");
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String consec=null;
		int i=1;
		int ano=Calendar.getInstance().get(Calendar.YEAR);
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("datosPersonalPazSalvo"));
			ps.setLong(1,certificado.getIdPersona());
			System.out.println("Consulta: "+ps);
			rs=ps.executeQuery();
			while(rs.next()){
				consec = rs.getString(i++);
				certificado.setNombre(rs.getString(i++));
				certificado.setCedula(rs.getString(i++));
				certificado.setNumCedDe(rs.getString(i++));
				certificado.setTipo("2");
				certificado.setConsCert(consec);
			}
			certificado.setCod_verificacion("CIDC_"+certificado.getTipo()+"_"+consec+"_"+ano);
			String dir=path+sep+"ks";
			path+=sep+"Documentos"+sep+"Certificados"+sep+"CIDC_"+certificado.getTipo()+"_"+certificado.getConsCert()+"_"+ano+".pdf";
			GenerarCertificados cert= new GenerarCertificados();
			cert.crearPazySalvo(certificado, path, resp);
			insertaCertificadoBD(certificado,path,dir);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return null;
	}
	
	/**
	 * el objetivo de este metodo es que retorno el nombre del los proyectos vinculados a una persona ya sea como 
	 * director o investigador principal
	 * @param papelPer es el rol de la persona, si es director o investigador del proyecto 
	 * @param idPer es el id de la persona 
	 * @return retorna una lista de objetos con el nombre de los proyectos  
	 */
	public List<ProyectoActividad> BuscarProyectoActividades( int idPer){
		Connection cn =null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<ProyectoActividad> listProy =new ArrayList<ProyectoActividad>();
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("buscarProyActNombre"));
			ps.setString(1, String.valueOf(idPer));
			ps.setInt(2, idPer);
			ps.setInt(3, idPer);
			rs=ps.executeQuery();
			while(rs.next()){
				int i=1;
				ProyectoActividad ProyAct= new ProyectoActividad();
				ProyAct.setNombre(rs.getString(i++));
				ProyAct.setCodigo(rs.getInt(i++));
				//ProyAct.setTipoPersona(rs.getInt(i++));
				//ProyAct.setTipoProy(rs.getString(i++));
				listProy.add(ProyAct);
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return listProy;
		
	}
	
	/**
	 * 
	 * @return
	 */
	/*public CertificacionesOBJ certificadoActividades(int id){
		//System.out.println("Fucnion Certificado2");
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String consec=null;
		int i=1;
		//int ano=Calendar.getInstance().get(Calendar.YEAR);
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("buscarProyActInfo"));
			ps.setLong(1,certificado.getIdPersona());
			System.out.println("Consulta: "+ps);
			rs=ps.executeQuery();
			while(rs.next()){
				consec = rs.getString(i++);
				certificado.setNombre(rs.getString(i++));
				certificado.setCedula(rs.getString(i++));
				certificado.setNumCedDe(rs.getString(i++));
				certificado.setTipo("2");
				certificado.setConsCert(consec);
			}
			certificado.setCod_verificacion("CIDC_"+certificado.getTipo()+"_"+consec+"_"+ano);
			path+=sep+"Documentos"+sep+"Certificados"+sep+"CIDC_"+certificado.getTipo()+"_"+certificado.getConsCert()+"_"+ano+".pdf";
			String url="CIDC_"+certificado.getTipo()+"_"+certificado.getConsCert()+"_"+ano+".pdf";
			GenerarCertificados cert= new GenerarCertificados();
			cert.crearPazySalvo(certificado, path, resp);
			insertaCertificadoBD(certificado, url);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return null;
	}*/
}
