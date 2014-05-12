package cidc.general.inicio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.obj.Parametros;
//import cidc.general.obj.menu.Menu;


public class CargaInicio {
	ResourceBundle rb=null;
	private static CursorDB cursor=new CursorDB();
	private BaseDB baseDB=new BaseDB(cursor,2);
	public CargaInicio(){
		rb=ResourceBundle.getBundle("cidc.general.consultas");
	}

	public String getPerfil(int tipo){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String perfil=null;
		int i=1;
		try {
			if(tipo==Parametros.userVisitante)
				cn=cursor.getConnection(Parametros.userVisitante);
			else
				cn=cursor.getConnection(tipo);
			Parametros p;
			switch(tipo){
			case Parametros.userAdministrador:
				ps=cn.prepareStatement(rb.getString("getPerfilAdministrador"));
			break;
			case Parametros.userEvaluador:
				ps=cn.prepareStatement(rb.getString("getPerfilEvaluador"));
			break;
			case Parametros.userInvestigador:
				ps=cn.prepareStatement(rb.getString("getPerfilInvestigador"));
			break;
			case Parametros.userVisitante:
				ps=cn.prepareStatement(rb.getString("getPerfilVisitante"));
			break;
			}
			rs=ps.executeQuery();
			while(rs.next()){
				perfil=rs.getString(1);
			}
		} catch (Exception e) {
			baseDB.lanzaExcepcion(e);
		}finally{
			baseDB.cerrar(rs);
			baseDB.cerrar(ps);
			baseDB.cerrar(cn);
		}
		return perfil;
	}


	public List getMenu(String perfilComp,int perfil){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List listaMenu=new ArrayList();
		Menu menu=null;
		int i=0;
		StringTokenizer token=new StringTokenizer(perfilComp,",");
		String []perfiles=new String[token.countTokens()];
		while(token.hasMoreTokens()){
			perfiles[i]=(String)token.nextElement();
			i++;
		}
	/*	System.out.println("El primer perfil es: "+perfiles[0]);
		System.out.println("El primer perfil es: "+perfiles[1]);
		System.out.println("El primer perfil es: "+perfiles[2]);*/
		i=1;
		try {
			cn=cursor.getConnection(perfil);

			ps=cn.prepareStatement(rb.getString("get_menu"));
			ps.setInt(1,Integer.parseInt(perfiles[0]));
			ps.setInt(2,Integer.parseInt(perfiles[1]));
			ps.setInt(3,Integer.parseInt(perfiles[2]));
			rs=ps.executeQuery();
	//		System.out.println("**********************************************");
			while(rs.next()){
				i=1;
				menu=new Menu();
				menu.setRecurso(rs.getString(i++));
				menu.setItem(rs.getString(i++));
				menu.setCategoria(rs.getString(i++));
				menu.setPrimario(rs.getBoolean(i++));
				menu.setVisible(rs.getBoolean(i++));
				menu.setEncriptado(rs.getString(i++));
				listaMenu.add(menu);
	//			System.out.println("encuentra recurso "+menu.getRecurso());
			}
	//		System.out.println("**********************************************");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			baseDB.lanzaExcepcion(e);
		}catch (Exception e) {
			// TODO Auto-generated catch block
			baseDB.lanzaExcepcion(e);
		}finally{			
			baseDB.cerrar(rs);
			baseDB.cerrar(ps);
			baseDB.cerrar(cn);			
		}

		return listaMenu;
	}

	public String[] getRecursosPerfil(int perfil){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String []recursos=null;
		Menu menu=null;
		int i=1;
		try {
			cn=cursor.getConnection(perfil);
			ps=cn.prepareStatement(rb.getString("cont_recursos"));
			ps.setInt(1,perfil);
			rs=ps.executeQuery();
			while(rs.next()){
				recursos=new String[rs.getInt(1)];
	//			System.out.println("Encontrados "+rs.getInt(1)+" recursos para el perfil "+perfil);
			}
			if(recursos!=null){
				ps=cn.prepareStatement(rb.getString("get_recursos"));
				ps.setInt(1,perfil);
				rs=ps.executeQuery();
				int contador=0;
				while(rs.next()){
					i=1;
					recursos[contador]=rs.getString(i++);
	//				System.out.println("recurso= "+recursos[contador]);
					contador++;
	//				System.out.println("encuentra datos para el menu");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			baseDB.lanzaExcepcion(e);
		}finally{
			baseDB.cerrar(rs);
			baseDB.cerrar(ps);
			baseDB.cerrar(cn);
		}
		return recursos;
	}
}

