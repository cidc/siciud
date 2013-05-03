package cidc.general.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import cidc.general.inicio.CargaInicio;
import cidc.general.login.Usuario;
import cidc.general.obj.Parametros;
import cidc.inscripSistema.obj.Persona;


public class UsuarioDB extends BaseDB{

	public UsuarioDB(CursorDB c, int perfil){
		super(c, perfil);
		rb=ResourceBundle.getBundle("cidc.general.consultas");
	}


	public Persona getPersona(long id){
		System.out.println("Valor de id que llega a Persona "+ id);
		Connection cn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		int i=1;
		boolean retorno=false;
		Persona persona=null;
		try {
			cn=cursor.getConnection(super.perfil);
			pst=cn.prepareStatement(rb.getString("getPersona"));
			pst.setLong(i++,id);
			rs=pst.executeQuery();
			while(rs.next()){
				i=1;
				retorno=true;
				persona=new Persona();
				persona.setIdPersona(rs.getLong(i++));
				persona.setNombre(rs.getString(i++));
				persona.setNombres(rs.getString(i++));
				persona.setApellidos(rs.getString(i++));
				persona.setFacultad(rs.getInt(i++));
				persona.setNombFacultad(rs.getString(i++));
				persona.setProyCur(rs.getLong(i++));
				persona.setNombProyecto(rs.getString(i++));
				persona.setMail(rs.getString(i++));
				persona.setPapel(rs.getInt(i++));
				persona.setCodigo(rs.getString(i++));
				persona.setTelefono(rs.getString(i++));
				persona.setCelular(rs.getString(i++));
				persona.setTipoDoc(rs.getInt(i++));
				persona.setDocumento(rs.getString(i++));
				persona.setDeDoc(rs.getString(i++));
				persona.setCvlac(rs.getString(i++));
				persona.setTipoGrupo(rs.getInt(i++));
			}
			if(retorno==false)
				super.setMensaje("Usuario no encontrado...");
			//***********sentencias para determinar si la critatura tiene campos vacios******************************
			persona.setEstado(true);
			if(persona!=null){
				if(persona.getApellidos()==null){
					persona.setEstado(false);
				}
				if(persona.getFacultad()==0){
					persona.setEstado(false);
				}
				if(persona.getProyCur()==0){
					persona.setEstado(false);
				}
				if(persona.getMail()==null){
					persona.setEstado(false);
				}
				if(persona.getPapel()==0){
					persona.setEstado(false);
				}
				if(persona.getCodigo()==null){
					persona.setEstado(false);
				}
				if(persona.getTelefono()==null){
					persona.setEstado(false);
				}
				if(persona.getCelular()==null){
					persona.setEstado(false);
				}
				if(persona.getTipoDoc()==0){
					persona.setEstado(false);
				}
				if(persona.getDocumento()==null){
					persona.setEstado(false);
				}
				if(persona.getDeDoc()==null){
					persona.setEstado(false);
				}
				if(persona.getCvlac()==null && persona.getTipoGrupo()==1){
					persona.setEstado(false);
				}
			}

		}catch (Exception e){
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}finally{
			cerrar(rs);
			cerrar(pst);
			cerrar(cn);
		}
		return persona;
	}


	public Usuario consultar(String nickname,String clave){
		Connection cn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Usuario user=null;
		CargaInicio cargaInicio=new CargaInicio();
		int i=1;

		if(nickname.toLowerCase().equals("propuesta"))
			user=null;//sesionPropuesta(clave);
		else{
			try {
				cn=cursor.getConnection(Parametros.userVisitante);
				pst=cn.prepareStatement(rb.getString("ingreso_usuario"));
				pst.setString(i++,nickname);
				pst.setString(i++,clave);
				rs=pst.executeQuery();
		//		System.out.println("entra al metodo "+nickname+" "+clave);
				while(rs.next()){
					i=1;
		//			System.out.println("encuentra usuario");
					user=new Usuario();
					user.setIdUsuario(rs.getLong(i++));
					user.setNombre(rs.getString(i++));
					user.setNick(rs.getString(i++));
					user.setPerfilComp(rs.getString(i++));
					user.setCedula(rs.getString(i++));
				}
				if(user==null)
					super.setMensaje("Usuario no encontrado...");
				else{
					i=0;
			//		String []perfiles=new String[3];
			//		System.out.println("Cadena= "+user.getPerfilComp());
					StringTokenizer token=new StringTokenizer(user.getPerfilComp(),",");
					user.setPerfil(Integer.parseInt(""+token.nextElement()));
			//		System.out.println("Se asigna el perfil#"+user.getPerfil());
					super.setMensaje("Bienvenido "+user.getNombre());
					user.setRecursos(cargaInicio.getMenu(user.getPerfilComp(),user.getPerfil()));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try{
					cerrar(rs);
					cerrar(pst);
					cerrar(cn);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return user;
	}

/*	public Usuario sesionPropuesta(String clave){
		Connection cn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Usuario user=null;
		CargaInicio cargaInicio= new CargaInicio();
		int i=1;
		try {
			cn=cursor.getConnection(Parametros.userVisitante);
			pst=cn.prepareStatement(rb.getString("sesion_propuesta"));
			pst.setString(i++,cargaInicio.getPerfil(Parametros.userInvestigador));
			pst.setString(i++,clave);
			rs=pst.executeQuery();

			while(rs.next()){
				i=1;
		//		System.out.println("encuentra usuario");
				user=new Usuario();
				user.setNombre(rs.getString(i++));
				user.setPerfil(rs.getString(i++));
			}
			if(user==null)
				super.setMensaje("Usuario no encontrado...");
			else{
				user.setRecursos(null);
				user.setPerfil(cargaInicio.getPerfil(Parametros.userInvestigador));
				user.setIdUsuario(1);
				user.setRecursos(cargaInicio.getMenu(user.getPerfil()));
				user.setNick(null);

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try{
				cerrar(rs);
				cerrar(pst);
				cerrar(cn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return user;
	}*/
	
	/**
	 * el metodo busca a una personal en la BD por su correo para que se le reestablezca la contraseña
	 * @param correo
	 * @return retorna un objeto de tipo usuario con los datos necesarios para reestablecer la contraseña
	 */
	public Usuario buscarPorCorreo(String correo){
		Connection cn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Usuario user=null;
		int i=1;
		try {
			cn=cursor.getConnection(Parametros.userVisitante);
			pst=cn.prepareStatement(rb.getString("buscarCorreo"));
			pst.setString(i++, correo);
			rs=pst.executeQuery();
			if(rs.next()){
				int j=1;
				user=new Usuario();
				user.setIdUsuario(rs.getLong(j++));
				user.setPapel(rs.getString(j++));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
				cerrar(rs);
				cerrar(pst);
				cerrar(cn);
		}
		return user;
		
	}
	
}
