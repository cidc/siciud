package cidc.evaluacion.db;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.evaluacion.obj.ObjetoEjemplo;

public class ClaseNuevaEjm extends BaseDB {

	public ClaseNuevaEjm(CursorDB c, int p) {
		super(c,p);
	}

	       //Lista de prueba.. 
        public List getLista(String ano,String corte){
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
         //       InfoEvento info=null;
                List lista=new ArrayList();
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("listaTotales"));
                        ps.setString(1,ano+"%");
                        ps.setInt(2,Integer.parseInt(corte));
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
//                                info=new InfoEvento();
//                                info.setNombreEvento(rs.getString(i++));
//                                info.setPagWeb(rs.getString(i++));
//                                info.setCant(rs.getInt(i++));
//				                                lista.add(info);
                        }
                } catch (Exception e) {
                        // TODO Auto-generated catch block
                        lanzaExcepcion(e);
                }finally{
                        cerrar(rs);
                        cerrar(ps);
                        cerrar(cn);
                }
                return lista;
        }

	public boolean insertar(ObjetoEjemplo obj) {
		boolean retorno=false;
		return retorno;
	}
	public ObjetoEjemplo consultarElemento(ObjetoEjemplo obj) {
		ObjetoEjemplo retorno=null;
		return retorno;
	}
	public List<ObjetoEjemplo> consulta(ObjetoEjemplo obj) {
		List<ObjetoEjemplo> retorno=new ArrayList<ObjetoEjemplo>();
		return retorno;
	}
	public boolean eliminar(ObjetoEjemplo obj) {
		boolean retorno=false;
		return retorno;
	}
	public boolean actualizar(ObjetoEjemplo obj) {
		boolean retorno=false;
		return retorno;
	}
}
