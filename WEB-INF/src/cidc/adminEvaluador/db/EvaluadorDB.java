package cidc.adminEvaluador.db;

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

import cidc.adminEvaluador.obj.AreasTrabOBJ;
import cidc.adminEvaluador.obj.DatEvaluadorOBJ;
import cidc.adminEvaluador.obj.FiltroEvaluadorOBJ;
import cidc.adminEvaluador.obj.GenericoOBJ;
import cidc.adminEvaluador.obj.TitulosOBJ;
import cidc.adminEvaluador.obj.PropuestaOBJ;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.CrearClaves;
import cidc.general.obj.Globales;

public class EvaluadorDB extends BaseDB {

	public EvaluadorDB(CursorDB c, int perfil) {
		super(c, perfil);
		rb=ResourceBundle.getBundle("cidc.adminEvaluador.consultas");
	}


	public boolean insertaEvaluador(DatEvaluadorOBJ evaluadorOBJ){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
		//	System.out.println("tipo eval="+evaluadorOBJ.getTipoEval());
			if(evaluadorOBJ.getTipoEval()==1){
				ps=cn.prepareStatement(rb.getString("insertaEvaluador1"));
				ps.setLong(1,evaluadorOBJ.getId());
				ps.executeUpdate();

				ps=cn.prepareStatement(rb.getString("areaBase1"));
				ps.setLong(1,evaluadorOBJ.getId());
				ps.setLong(2,evaluadorOBJ.getArea());
				ps.setString(3,evaluadorOBJ.getCampos());
				ps.executeUpdate();
			}else{
				ps=cn.prepareStatement(rb.getString("insertaPersona"));
				ps.setString(1,evaluadorOBJ.getNombres());
				ps.setString(2,evaluadorOBJ.getApellidos());
				if(evaluadorOBJ.getMail()!=null)
					ps.setString(3,evaluadorOBJ.getMail());
				else
					ps.setNull(3,Types.VARCHAR);
				if(evaluadorOBJ.getTelefono()!=null)
					ps.setString(4,evaluadorOBJ.getTelefono());
				else
					ps.setNull(4,Types.VARCHAR);
				if(evaluadorOBJ.getCelular()!=null)
					ps.setString(5,evaluadorOBJ.getCelular());
				else
					ps.setNull(5,Types.VARCHAR);
				if(evaluadorOBJ.getNumDoc()!=null)
					ps.setString(6,evaluadorOBJ.getNumDoc());
				else
					ps.setNull(6,Types.VARCHAR);
				if(evaluadorOBJ.getDireccion()!=null)
					ps.setString(7,evaluadorOBJ.getDireccion());
				else
					ps.setNull(7,Types.VARCHAR);
				ps.executeUpdate();

				ps=cn.prepareStatement(rb.getString("insertaEvaluador2"));
				ps.executeUpdate();

				ps=cn.prepareStatement(rb.getString("insertaExterno"));
				ps.setString(1,evaluadorOBJ.getUniversidad());
				ps.setNull(2,Types.VARCHAR);
				ps.executeUpdate();

				ps=cn.prepareStatement(rb.getString("areaBase2"));
				ps.setLong(1,evaluadorOBJ.getArea());
				ps.setString(2,evaluadorOBJ.getCampos());
				ps.executeUpdate();
			}

			cn.commit();
			retorno=true;
		} catch (SQLException e){
			System.out.println("C�digo de error= "+e.getErrorCode());
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


	public List filtraEvaluador(FiltroEvaluadorOBJ filtro) {
		List lista=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);

			ps=cn.prepareStatement(rb.getString("filtroEvaluador"+filtro.getOrden()));
			/*System.out.println("tipo="+filtro.getTipEval());
			System.out.println("nombres="+filtro.getNombres());
			System.out.println("Apell="+filtro.getApellidos());
			System.out.println("campos="+filtro.getCamposTrabajo());*/


			if(filtro.getTipEval()!=0)
				ps.setString(i++,"%"+filtro.getTipEval()+"%");
			else
				ps.setString(i++,"%");
			ps.setString(i++,filtro.getNombres());
			ps.setString(i++,filtro.getApellidos());
			ps.setString(i++,filtro.getCamposTrabajo());
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				DatEvaluadorOBJ datEvaluadorOBJ=new DatEvaluadorOBJ();
				datEvaluadorOBJ.setCodigo(rs.getString(i++));
				datEvaluadorOBJ.setNombres(rs.getString(i++));
				datEvaluadorOBJ.setApellidos(rs.getString(i++));
				datEvaluadorOBJ.setMail(rs.getString(i++));
				datEvaluadorOBJ.setTipoEvaluador(rs.getString(i++));
				datEvaluadorOBJ.setTipoEval(rs.getInt(i++));
				lista.add(datEvaluadorOBJ);
			}
			filtro.setTipEval(0);
			filtro.setNombres("");
			filtro.setApellidos("");
			filtro.setCamposTrabajo("");
		//	System.out.println("lista="+lista.size());
		} catch (SQLException e) {
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
	public List getFacultades() {
		// TODO Auto-generated method stub
		List lista=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		GenericoOBJ gn;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getFacultades"));
			rs=ps.executeQuery();
			while(rs.next()){
				gn=new GenericoOBJ();
				gn.setCodigo(rs.getLong(1));
				gn.setNombre(rs.getString(2));
				lista.add(gn);
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return lista;
	}
	public DatEvaluadorOBJ getEvaluador(String codEval,int tipEval){
		DatEvaluadorOBJ evaluadorOBJ=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("DatosEvaluador"+tipEval));
		//	System.out.println("codigo "+codEval+" --"+tipEval);
			ps.setLong(1,Long.parseLong(codEval));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				evaluadorOBJ=new DatEvaluadorOBJ();
				evaluadorOBJ.setTipoEval(tipEval);
				evaluadorOBJ.setId(rs.getLong(i++));
				evaluadorOBJ.setNombres(rs.getString(i++));
				evaluadorOBJ.setApellidos(rs.getString(i++));
				evaluadorOBJ.setMail(rs.getString(i++));
				evaluadorOBJ.setTelefono(rs.getString(i++));
				evaluadorOBJ.setCelular(rs.getString(i++));
				evaluadorOBJ.setNumDoc(rs.getString(i++));
				evaluadorOBJ.setDireccion(rs.getString(i++));
				if(tipEval==1){
					evaluadorOBJ.setFacultad(rs.getString(i++));
					evaluadorOBJ.setGrupo(rs.getString(i++));
					evaluadorOBJ.setProyCur(rs.getString(i++));
				}
				evaluadorOBJ.setTitulos(titulosPersona(cn,codEval));
				evaluadorOBJ.setAreasTrab(areasPersona(cn,codEval));
		//		System.out.println("encuentra");
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			try{
				cerrar(rs);
				cerrar(ps);
				cerrar(cn);
			}catch (Exception e) {
				lanzaExcepcion(e);
			}
		}
		return evaluadorOBJ;
	}
	private List areasPersona(Connection cn, String codEval) {
		List lista=new ArrayList();
		AreasTrabOBJ areasTrabOBJ=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("areasEval"));
			ps.setLong(1,Long.parseLong(codEval));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				areasTrabOBJ=new AreasTrabOBJ();
				areasTrabOBJ.setIdArea(rs.getLong(i++));
				areasTrabOBJ.setSuperArea(rs.getString(i++));
				areasTrabOBJ.setArea(rs.getString(i++));
				areasTrabOBJ.setCampos(rs.getString(i++));
				lista.add(areasTrabOBJ);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return lista;
	}
	private List titulosPersona(Connection cn, String codEval) {
		List lista=new ArrayList();
		TitulosOBJ titulosOBJ=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("titulosEval"));
			ps.setLong(1,Long.parseLong(codEval));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				titulosOBJ=new TitulosOBJ();
				titulosOBJ.setId(rs.getLong(i++));
				titulosOBJ.setTitulo(rs.getString(i++));
				titulosOBJ.setTipoTitulo(rs.getInt(i++));
				lista.add(titulosOBJ);
			}
		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return lista;
	}
	public boolean eliminaEvaluador(String codEval) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("eliminaEval"));
			ps.setLong(1,Long.parseLong(codEval));
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


	public boolean modificaEvaluador(DatEvaluadorOBJ evaluadorOBJ) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("modificaDatEval"));
			ps.setString(i++,evaluadorOBJ.getNombres());
			ps.setString(i++,evaluadorOBJ.getApellidos());
			ps.setString(i++,evaluadorOBJ.getMail());
			ps.setString(i++,evaluadorOBJ.getTelefono());
			ps.setString(i++,evaluadorOBJ.getCelular());
			ps.setString(i++,evaluadorOBJ.getNumDoc());
			ps.setString(i++,evaluadorOBJ.getDireccion());
			ps.setLong(i++,evaluadorOBJ.getId());
			ps.executeUpdate();
			i=1;
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
	public boolean nuevoTitulo(String id, String tipoTitulo, String titulo) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
	//		System.out.println("con="+super.perfil+" para="+id+" tpt="+tipoTitulo+" nom="+titulo);
			ps=cn.prepareStatement(rb.getString("nuevoTitulo"));
			ps.setLong(i++,Long.parseLong(id));
			ps.setLong(i++,Long.parseLong(tipoTitulo));
			ps.setString(i++,titulo);
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
	public boolean borraTitulo(String idTitulo) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
		//	System.out.println("con="+super.perfil+" para="+idTitulo);
			ps=cn.prepareStatement(rb.getString("borraTitulo"));
			ps.setLong(i++,Long.parseLong(idTitulo));
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
	public List getSuperAreas() {
		AreasTrabOBJ areasTrabOBJ=null;;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getSuperAreas"));
			rs=ps.executeQuery();
			while(rs.next()){
	//			System.out.println("encuentra area");
				areasTrabOBJ=new AreasTrabOBJ();
				areasTrabOBJ.setIdSuperArea(rs.getInt(1));
				areasTrabOBJ.setSuperArea(rs.getString(2));
				lista.add(areasTrabOBJ);
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

	public List ajaxtAreas(int superArea) {
		AreasTrabOBJ areasTrabOBJ=null;;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ajaxAreas"));
			ps.setInt(1,superArea);
			rs=ps.executeQuery();
			while(rs.next()){
		//		System.out.println("encuentra");
				areasTrabOBJ=new AreasTrabOBJ();
				areasTrabOBJ.setIdArea(rs.getInt(1));
				areasTrabOBJ.setArea(rs.getString(2));
				lista.add(areasTrabOBJ);
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
	public boolean nuevaArea(String codEval, String area, String campos) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
		//	System.out.println("con="+super.perfil+" para="+campos);
			ps=cn.prepareStatement(rb.getString("nuevaArea"));
			ps.setLong(i++,Long.parseLong(codEval));
			ps.setLong(i++,Long.parseLong(area));
			ps.setString(i++,campos);
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
	public boolean borraArea(String codEval, String area) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
	//		System.out.println("con="+super.perfil+" para="+area);
			ps=cn.prepareStatement(rb.getString("borraArea"));
			ps.setLong(i++,Long.parseLong(codEval));
			ps.setLong(i++,Long.parseLong(area));
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


	public boolean getAsigarClave(String id) {
		boolean retorno=false;
		CrearClaves clave=new CrearClaves();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String perfil=null,nick=null,key=null;
		String []datos=new String[5];
		int i=1;
		key=clave.getClave();
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("verPerfil"));
			ps.setLong(1,Long.parseLong(id));
			rs=ps.executeQuery();
		//	System.out.println("c�digo de evaluador="+id);
			while(rs.next()){
				perfil=rs.getString(1);
				nick=rs.getString(2);
				datos[2]=rs.getString(3);
				datos[1]=rs.getString(4);
				datos[0]=rs.getString(5);
			}
			if(perfil==null){
				ps=cn.prepareStatement(rb.getString("nuevoUsuario"));
				ps.setLong(i++, Long.parseLong(id));
				ps.setString(i++,"evaluador");
				ps.setString(i++,key);
				ps.setString(i++,"9,0,0");
				ps.executeUpdate();
		//		System.out.println("Nuevo Usuario");
			}else{
				String []partes=perfil.split(",");
				String nuevoPerfil=null;
				if(!partes[0].equals("9")){
					if(partes[1].equals("0"))
						nuevoPerfil=partes[0]+",9,0";
					else
						if(partes[2].equals("0") && !partes[1].equals("9"))
							nuevoPerfil=partes[0]+","+partes[1]+",9";
				}
				if(nuevoPerfil!=null){
					ps=cn.prepareStatement(rb.getString("asignaPerfKey"));
					ps.setString(i++,key);
					ps.setString(i++,nuevoPerfil);
					ps.setLong(i++, Long.parseLong(id));
					ps.executeUpdate();
		//			System.out.println("Asigna perfil y psw "+nuevoPerfil);
				}else{
					ps=cn.prepareStatement(rb.getString("cambioClave"));
					ps.setString(i++,key);
					ps.setLong(i++, Long.parseLong(id));
					ps.executeUpdate();
		//			System.out.println("solo cambia clave");
				}
				datos[3]=nick;
				datos[4]=key;
				mailClaveNueva(datos);
				retorno=true;
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

		return retorno;

	}


	public boolean insertaDocumentacion(String nombre, String tipo, String id, String estado) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
			if(estado.equals("no")){
				ps=cn.prepareStatement(rb.getString("NuevoDocumento"+tipo));
				ps.setString(1,nombre);
				ps.setLong(2,Long.parseLong(id));
				ps.executeUpdate();
			}
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


	public GenericoOBJ getInfoDocs(String id) {
		GenericoOBJ genericoOBJ=new GenericoOBJ();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getEstadoDocs"));
			ps.setLong(1,Long.parseLong(id));
			rs=ps.executeQuery();
			while(rs.next()){
				genericoOBJ.setEstadoCed(rs.getString(1));
				genericoOBJ.setEstadoRut(rs.getString(2));
			}
			genericoOBJ.setCodigo(Long.parseLong(id));
		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return genericoOBJ;
	}

	public void mailClaveNueva(String []datos) throws AddressException,MessagingException{
		String []direcciones={datos[1]};
		ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.correoEvaluadores");
		Globales global=new Globales();
		EnvioMail2 envioMail=new EnvioMail2("siciud");
		StringBuffer texto=new StringBuffer();
		texto.append("<br>SICIUD-"+datos[0]+"-"+global.getAnoCortoHoy()+"<br><br>");
		texto.append(rb.getString("nc1"));
		texto.append(datos[2]);
		texto.append(rb.getString("nc2"));
		texto.append(rb.getString("nc3"));
		texto.append(datos[3]);
		texto.append(rb.getString("nc4"));
		texto.append(datos[4]);
		texto.append(rb.getString("nc5"));
		texto.append(rb.getString("nc6"));
		texto.append(rb.getString("nc7"));

		envioMail.enviar(direcciones,"Clave de Ingreso al SICIUD",""+texto);
		Reporte reporteMail=new Reporte(cursor,super.perfil);
		reporteMail.reportar(datos[2],"Clave de Ingreso sistema",direcciones[0],datos[0]);

	//	System.out.println("Mensaje Enviado");
	}


	public List propAsignadas(String idEval) {
		PropuestaOBJ propuesta=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List lista=new ArrayList();
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getPropEvaluador"));
			ps.setLong(1,Long.parseLong(idEval));
			rs=ps.executeQuery();
			while(rs.next()){
		//		System.out.println("encuentra");
				i=1;
				propuesta=new PropuestaOBJ();
				propuesta.setCodPropuesta(rs.getLong(i++));
				propuesta.setConvocatoria(rs.getString(i++));
				propuesta.setEstadoEval(rs.getInt(i++));
				propuesta.setEstadoPropuesta(rs.getInt(i++));
				propuesta.setNomPropuesta(rs.getString(i++));
				lista.add(propuesta);
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
}
