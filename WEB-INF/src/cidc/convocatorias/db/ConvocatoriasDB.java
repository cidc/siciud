package cidc.convocatorias.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Types;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import cidc.general.obj.Globales;
import cidc.convocatorias.obj.AspectosOBJ;
import cidc.convocatorias.obj.CompromisosOBJ;
import cidc.convocatorias.obj.DocumentosOBJ;
import cidc.convocatorias.obj.ConvocatoriaOBJ;
import cidc.convocatorias.obj.CriteriosOBJ;
import cidc.convocatorias.obj.EjesOBJ;
import cidc.convocatorias.obj.InsercionGralOBJ;
import cidc.convocatorias.obj.ParametrosOBJ;
import cidc.convocatorias.obj.RubrosOBJ;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.inscripcionConv.obj.ConvCorte;

public class ConvocatoriasDB extends BaseDB{

	public ConvocatoriasDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.convocatorias.consultas");
	}
	public List consultaCriterios(int consulta,long convocatoria){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
	//		System.out.println("Consulta="+consulta);
			System.out.println("Convocatoria="+convocatoria);
			ps=cn.prepareStatement(rb.getString("lista_criterios"+consulta));
			if(consulta==1)
				ps.setLong(1,convocatoria);
			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
			//	System.out.println("encuentra criterios");
				CriteriosOBJ criteriosOBJ=new CriteriosOBJ();
				criteriosOBJ.setCodigo(rs.getInt(i++));
				criteriosOBJ.setNombre(rs.getString(i++));
				if(consulta==1){
					criteriosOBJ.setValor(rs.getFloat(i++));
		//			System.out.println("ingresa valor "+criteriosOBJ.getValor());
				}
				l.add(criteriosOBJ);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return l;
	}
	public List consultaCompromisos(int tipo, long conv){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("lista_compromisos"+tipo));
			if(tipo!=0)
				ps.setLong(1,conv);
			rs=ps.executeQuery();

			System.out.println("tipo:"+tipo+"consulta:"+ps);
			while(rs.next()){
				i=1;
				CompromisosOBJ compromisosOBJ=new CompromisosOBJ();
				compromisosOBJ.setCodigo(rs.getInt(i++));
				compromisosOBJ.setNombre(rs.getString(i++));
				compromisosOBJ.setIndicador(rs.getString(i++));
				if(tipo!=0){
			//		System.out.println("entra");
					compromisosOBJ.setObligatorio(rs.getInt(i++));
					compromisosOBJ.setValor(rs.getInt(i++));
				}
				l.add(compromisosOBJ);
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
		return l;
	}

	        public List consultaDocumentosRequisito(int tipo, long conv){
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=0;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("lista_documentosrequisito"+tipo));
                        if(tipo!=0)
                                ps.setLong(1,conv);
                        rs=ps.executeQuery();

                        while(rs.next()){
                                i=1;
                                DocumentosOBJ documentosOBJ=new DocumentosOBJ();
                                documentosOBJ.setCodigo(rs.getInt(i++));
                                documentosOBJ.setNombre(rs.getString(i++));
                               // documentosOBJ.setEstado(rs.getInt(i++));
			//	if(tipo==0){
                          //              documentosOBJ.setEstado(rs.getBoolean(i++));
                            //    }
				if(tipo!=0){
                                	documentosOBJ.setObligatorio(rs.getInt(i++));
				}
                                l.add(documentosOBJ);
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
                return l;
        }


	public int getCantCompromisos(long conv){
		int i=0,retorno=0;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getCantComp"));
			ps.setLong(1,conv);
			rs=ps.executeQuery();

			while(rs.next()){
				retorno=rs.getInt(1);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}
//	
        public ConvocatoriaOBJ getRequisito(int reqId){
                ConvocatoriaOBJ convocatoriaOBJ=null;
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("DatosRequisito"));
                        ps.setInt(1,reqId);
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setReqNombre(rs.getString(i++));
                                convocatoriaOBJ.setReqEstado(rs.getBoolean(i++));
                                convocatoriaOBJ.setReqId(rs.getInt(i++));
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
                return convocatoriaOBJ;
        }

        public ConvocatoriaOBJ getRubro(int rubId){
                ConvocatoriaOBJ convocatoriaOBJ=null;
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("DatosRubro"));
                        ps.setInt(1,rubId);
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setRubNombre(rs.getString(i++));
                                convocatoriaOBJ.setRubId(rs.getInt(i++));
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
                return convocatoriaOBJ;
        }

        public ConvocatoriaOBJ getCompromiso(int comId){
                ConvocatoriaOBJ convocatoriaOBJ=null;
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("DatosCompromiso"));
                        ps.setInt(1,comId);
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setComNombre(rs.getString(i++));
				convocatoriaOBJ.setComEstado(rs.getBoolean(i++));
                                convocatoriaOBJ.setComId(rs.getInt(i++));
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
                return convocatoriaOBJ;
        }

        public ConvocatoriaOBJ getCriterio(int critId){
                ConvocatoriaOBJ convocatoriaOBJ=null;
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("DatosCriterio"));
                        ps.setInt(1,critId);
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setCritNombre(rs.getString(i++));
                                convocatoriaOBJ.setCritEstado(rs.getBoolean(i++));
                                convocatoriaOBJ.setCritId(rs.getInt(i++));
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
                return convocatoriaOBJ;
        }

        public ConvocatoriaOBJ getAspecto(int aspId){
                ConvocatoriaOBJ convocatoriaOBJ=null;
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("DatosAspecto"));
                        ps.setInt(1,aspId);
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setAspNombre(rs.getString(i++));
                                convocatoriaOBJ.setAspEstado(rs.getBoolean(i++));
                                convocatoriaOBJ.setAspId(rs.getInt(i++));
                                convocatoriaOBJ.setCritId(rs.getInt(i++));
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
                return convocatoriaOBJ;
        }


        public ConvocatoriaOBJ getEje(int ejeId){
                ConvocatoriaOBJ convocatoriaOBJ=null;
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("DatosEje"));
                        ps.setInt(1,ejeId);
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setEjeNombre(rs.getString(i++));
                                convocatoriaOBJ.setEjeEstado(rs.getBoolean(i++));
                                convocatoriaOBJ.setEjeId(rs.getInt(i++));
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
                return convocatoriaOBJ;
        }

//
	public List consultaEjes(int tipo,long convocatoria){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("lista_ejes"+tipo));
		//	System.out.println("tipo= "+tipo);
			if(tipo==ParametrosOBJ.ListaEje)
				ps.setLong(1,convocatoria);
			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
				EjesOBJ ejesOBJ=new EjesOBJ();
				ejesOBJ.setCodigo(rs.getInt(i++));
				ejesOBJ.setNombre(rs.getString(i++));
				l.add(ejesOBJ);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return l;
	}

	public List getDependencias(){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		CriteriosOBJ crit=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getDependencias"));
			rs=ps.executeQuery();
			while(rs.next()){
				crit=new CriteriosOBJ();
				crit.setCodigo(rs.getInt(1));
				crit.setNombre(rs.getString(2));
				l.add(crit);
			}
		}catch (SQLException e){
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return l;
	}

	public List getAspectosComite(String dependencia){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			if(dependencia!=null && !dependencia.equals("null")){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("lista_aspectos3"));
				ps.setInt(1,Integer.parseInt(dependencia));
				rs=ps.executeQuery();
				while(rs.next()){
					i=1;
					AspectosOBJ aspectosOBJ=new AspectosOBJ();
					aspectosOBJ.setCodigo(rs.getInt(i++));
					aspectosOBJ.setNombre(rs.getString(i++));
					l.add(aspectosOBJ);
				}
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return l;
	}

	public int getComiteEvaluador(long conv){
		int comite=0;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getComiteEval"));
			ps.setLong(1,conv);
			rs=ps.executeQuery();
			while(rs.next()){
				comite=rs.getInt(1);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return comite;
	}


	public List getAspectos3(ConvocatoriaOBJ convocatoriaOBJ){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			if(convocatoriaOBJ!=null){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("getAspGuardadosComite"));
				ps.setLong(1,convocatoriaOBJ.getConvId());
				rs=ps.executeQuery();
				while(rs.next()){
					l.add(rs.getString(1));
				}
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return l;
	}

	public List consultaAspectos(int tipo,long convocatoria){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("lista_aspectos"+tipo));
			if(tipo==ParametrosOBJ.ListaAspecto)
				ps.setLong(1,convocatoria);
			rs=ps.executeQuery();
		//	System.out.println("antes tipo="+tipo);
			while(rs.next()){
				i=1;
				AspectosOBJ aspectosOBJ=new AspectosOBJ();
				aspectosOBJ.setCodigo(rs.getInt(i++));
				aspectosOBJ.setCriterio(rs.getInt(i++));
				aspectosOBJ.setNombre(rs.getString(i++));
				if(tipo==ParametrosOBJ.ListaAspecto){
					aspectosOBJ.setValor(rs.getFloat(i++));
				}
				l.add(aspectosOBJ);
	//			System.out.println("Aspecto "+aspectosOBJ.getNombre());
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return l;
	}
	public List listaConvocatorias(long convocatoria){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("lista_convocatorias"+convocatoria));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				ConvocatoriaOBJ convocatoriaOBJ=new ConvocatoriaOBJ();
				convocatoriaOBJ.setConvId(rs.getLong(i++));
				convocatoriaOBJ.setConvAno(rs.getInt(i++));
				convocatoriaOBJ.setConvNumero(rs.getInt(i++));
				convocatoriaOBJ.setConvNombre(rs.getString(i++));
				convocatoriaOBJ.setConvTipo(rs.getInt(i++));
				l.add(convocatoriaOBJ);
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
		return l;
	}

	public List listaRequisitosDoc(long convocatoria){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("lista_requisitosdoc"));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				ConvocatoriaOBJ convocatoriaOBJ=new ConvocatoriaOBJ();
				convocatoriaOBJ.setReqId(rs.getInt(i++));
				convocatoriaOBJ.setReqNombre(rs.getString(i++));
				convocatoriaOBJ.setReqEstado(rs.getBoolean(i++));
				l.add(convocatoriaOBJ);
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
		return l;
	}

        public List listaRubros(long convocatoria){
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("lista_rubros"));
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                ConvocatoriaOBJ convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setRubId(rs.getInt(i++));
                                convocatoriaOBJ.setRubNombre(rs.getString(i++));
                                l.add(convocatoriaOBJ);
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
                return l;
        }

        public List listaCompromisos(long convocatoria){
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("lista_compromisos"));
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                ConvocatoriaOBJ convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setComId(rs.getInt(i++));
                                convocatoriaOBJ.setComNombre(rs.getString(i++));
                                convocatoriaOBJ.setComEstado(rs.getBoolean(i++));
                                l.add(convocatoriaOBJ);
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
                return l;
        }

        public List listaCriterios(long convocatoria){
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("lista_criterios"));
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                ConvocatoriaOBJ convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setCritId(rs.getInt(i++));
                                convocatoriaOBJ.setCritNombre(rs.getString(i++));
                                convocatoriaOBJ.setCritEstado(rs.getBoolean(i++));
                                l.add(convocatoriaOBJ);
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
                return l;
        }

	public List listaAspectos(long convocatoria){
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("lista_aspectos"));
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                ConvocatoriaOBJ convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setAspId(rs.getInt(i++));
                                convocatoriaOBJ.setAspNombre(rs.getString(i++));
                                convocatoriaOBJ.setAspEstado(rs.getBoolean(i++));
                                convocatoriaOBJ.setCritId(rs.getInt(i++));
                                l.add(convocatoriaOBJ);
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
                return l;
        }

        public List listaEjes(long convocatoria){
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("lista_ejes"));
                        rs=ps.executeQuery();
                        while(rs.next()){
                                i=1;
                                ConvocatoriaOBJ convocatoriaOBJ=new ConvocatoriaOBJ();
                                convocatoriaOBJ.setEjeId(rs.getInt(i++));
                                convocatoriaOBJ.setEjeNombre(rs.getString(i++));
                                convocatoriaOBJ.setEjeEstado(rs.getBoolean(i++));
                                l.add(convocatoriaOBJ);
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
                return l;
        }

	public boolean insertaConvocatoria(ConvocatoriaOBJ convocatoriaOBJ){
		boolean retorno=false;
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
			System.out.println("entrooo");
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("inserta_convocatoria"));

			ps.setInt(i++,convocatoriaOBJ.getConvAno());
			ps.setInt(i++,convocatoriaOBJ.getConvNumero());
			ps.setInt(i++,convocatoriaOBJ.getConvTipo());
			ps.setString(i++,convocatoriaOBJ.getConvNombre().toLowerCase());
			ps.setString(i++,convocatoriaOBJ.getConvCuantia());
			ps.setLong(i++,convocatoriaOBJ.getConvDuracion());
			ps.setString(i++,convocatoriaOBJ.getConvFecInicio());
			ps.setString(i++,convocatoriaOBJ.getConvFecFin());
			ps.setString(i++,convocatoriaOBJ.getConvDirigido());
		//	ps.setBoolean(i++,convocatoriaOBJ.isConvPublica());
			ps.setString(i++,convocatoriaOBJ.getCorteActual());
			ps.setInt(i++, convocatoriaOBJ.getRol());
			ps.execute();
			retorno=true;
		} catch (SQLException e) {
			lanzaExcepcion(e);
			setMensaje("No se puede repetir el a�o y n�mero de la convocatoria");
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}
	public boolean modificarConvocatoria(ConvocatoriaOBJ convocatoriaOBJ){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("modificar_convocatoria"));

			ps.setInt(i++,convocatoriaOBJ.getConvAno());
			ps.setInt(i++,convocatoriaOBJ.getConvNumero());
			ps.setString(i++,convocatoriaOBJ.getConvNombre().toLowerCase());
			ps.setString(i++,convocatoriaOBJ.getConvCuantia());
			ps.setLong(i++,convocatoriaOBJ.getConvDuracion());
			ps.setString(i++,convocatoriaOBJ.getConvFecInicio());
			ps.setString(i++,convocatoriaOBJ.getConvFecFin());
			ps.setString(i++,convocatoriaOBJ.getConvDirigido());
			ps.setBoolean(i++,convocatoriaOBJ.isConvPublica());
			ps.setString(i++,convocatoriaOBJ.getCorteActual());
			ps.setInt(i++, convocatoriaOBJ.getConvTipo());
			ps.setInt(i++, convocatoriaOBJ.getRol());
			
	//		System.out.println("publica ="+convocatoriaOBJ.isConvPublica());

			ps.setLong(i++,convocatoriaOBJ.getConvId());
			ps.execute();
			retorno=true;
		} catch (SQLException e) {
			lanzaExcepcion(e);
			setMensaje("Ya se encuentra almacenada una convocatoria con ese a�o y n�mero");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			setMensaje("Problemas en la conexi�n a la base de datos");
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}

	public boolean modificaRequisitoDoc(ConvocatoriaOBJ convocatoriaOBJ){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("modificar_requisitodoc"));
			ps.setString(i++,convocatoriaOBJ.getReqNombre());
			ps.setBoolean(i++,convocatoriaOBJ.isReqEstado());
			ps.setInt(i++,convocatoriaOBJ.getReqId());
			ps.execute();
			System.out.println("ReqNombre ="+convocatoriaOBJ.getReqNombre()+"ReqId ="+convocatoriaOBJ.getReqId());
			retorno=true;
		} catch (SQLException e) {
			lanzaExcepcion(e);
			setMensaje("Ya se encuentra almacenada");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			setMensaje("Problemas en la conexi�n a la base de datos");
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}

        public boolean modificaRubro(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("modificar_rubro"));
                        ps.setString(i++,convocatoriaOBJ.getRubNombre());
                        ps.setInt(i++,convocatoriaOBJ.getRubId());
                        ps.execute();
                        System.out.println("RubNombre ="+convocatoriaOBJ.getRubNombre()+"RubId ="+convocatoriaOBJ.getRubId());
                        retorno=true;
                } catch (SQLException e) {
                        lanzaExcepcion(e);
                        setMensaje("Ya se encuentra almacenada");
                }catch (Exception e) {
                        // TODO Auto-generated catch block
                        setMensaje("Problemas en la conexi�n a la base de datos");
                        lanzaExcepcion(e);
                }finally{
                        cerrar(ps);
                        cerrar(cn);
                }
                return retorno;
        }

        public boolean modificaCompromiso(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("modificar_compromiso"));
                        ps.setString(i++,convocatoriaOBJ.getComNombre());
                        ps.setBoolean(i++,convocatoriaOBJ.isComEstado());
                        ps.setInt(i++,convocatoriaOBJ.getComId());
                        ps.execute();
                        System.out.println("ComNombre ="+convocatoriaOBJ.getComNombre()+"ComId ="+convocatoriaOBJ.getComId());
                        retorno=true;
                } catch (SQLException e) {
                        lanzaExcepcion(e);
                        setMensaje("Ya se encuentra almacenada");
                }catch (Exception e) {
                        // TODO Auto-generated catch block
                        setMensaje("Problemas en la conexi�n a la base de datos");
                        lanzaExcepcion(e);
                }finally{
                        cerrar(ps);
                        cerrar(cn);
                }
                return retorno;
        }

        public boolean modificaCriterio(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("modificar_criterio"));
                        ps.setString(i++,convocatoriaOBJ.getCritNombre());
                        ps.setBoolean(i++,convocatoriaOBJ.isCritEstado());
                        ps.setInt(i++,convocatoriaOBJ.getCritId());
                        ps.execute();
                        System.out.println("ComNombre ="+convocatoriaOBJ.getCritNombre()+"CritId ="+convocatoriaOBJ.getCritId());
                        retorno=true;
                } catch (SQLException e) {
                        lanzaExcepcion(e);
                        setMensaje("Ya se encuentra almacenada");
                }catch (Exception e) {
                        // TODO Auto-generated catch block
                        setMensaje("Problemas en la conexi�n a la base de datos");
                        lanzaExcepcion(e);
                }finally{
                        cerrar(ps);
                        cerrar(cn);
                }
                return retorno;
        }

        public boolean modificaAspecto(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("modificar_aspecto"));
                        ps.setString(i++,convocatoriaOBJ.getAspNombre());
                        ps.setBoolean(i++,convocatoriaOBJ.isAspEstado());
                        ps.setInt(i++,convocatoriaOBJ.getAspId());
                        ps.execute();
                        System.out.println("AspNombre ="+convocatoriaOBJ.getAspNombre()+"AspId ="+convocatoriaOBJ.getAspId());
                        retorno=true;
                } catch (SQLException e) {
                        lanzaExcepcion(e);
                        setMensaje("Ya se encuentra almacenada");
                }catch (Exception e) {
                        // TODO Auto-generated catch block
                        setMensaje("Problemas en la conexi�n a la base de datos");
                        lanzaExcepcion(e);
                }finally{
                        cerrar(ps);
                        cerrar(cn);
                }
                return retorno;
        }

        public boolean modificaEje(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("modificar_eje"));
                        ps.setString(i++,convocatoriaOBJ.getEjeNombre());
                        ps.setBoolean(i++,convocatoriaOBJ.isEjeEstado());
                        ps.setInt(i++,convocatoriaOBJ.getEjeId());
                        ps.execute();
                        System.out.println("EjeNombre ="+convocatoriaOBJ.getEjeNombre()+"EjeId ="+convocatoriaOBJ.getEjeId());
                        retorno=true;
                } catch (SQLException e) {
                        lanzaExcepcion(e);
                        setMensaje("Ya se encuentra almacenada");
                }catch (Exception e) {
                        // TODO Auto-generated catch block
                        setMensaje("Problemas en la conexi�n a la base de datos");
                        lanzaExcepcion(e);
                }finally{
                        cerrar(ps);
                        cerrar(cn);
                }
                return retorno;
        }


	public boolean quitarConvocatoria(long idconv){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("borrar_convocatoria"));

			ps.setLong(i++,idconv);
			ps.execute();
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

        public List getDocConvocatoria(long id){
                Connection cn=null;
                PreparedStatement ps=null;
                ResultSet rs=null;
                ConvocatoriaOBJ convocatoriaOBJ=null;
		List l=new ArrayList();
                int i=1;
		System.out.println("entrooo");
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("getDocConvocatoria"));
                        ps.setLong(1,id);
                        rs=ps.executeQuery();
		System.out.println("consulta:"+ps);
                        while(rs.next()){
				i=1;
                                convocatoriaOBJ=new ConvocatoriaOBJ();
                        //      convocatoriaOBJ.setConvTipo(rs.getString(i++));
                                convocatoriaOBJ.setConvAd(rs.getString(i++));
				l.add(convocatoriaOBJ);
		System.out.println("convvv:");
		}
                } catch (SQLException e) {
                        lanzaExcepcion(e);
                }catch (Exception e) {
                        lanzaExcepcion(e);
                }finally{
                        cerrar(rs);
                        cerrar(ps);
                        cerrar(cn);
                }
                return l;
        }

	public ConvocatoriaOBJ getConvocatoria(long id){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ConvocatoriaOBJ convocatoriaOBJ=null;
		List listaCortes=new ArrayList();
		ConvCorte corte=null;
		String ini=null,fin=null;
		Globales g=new Globales();
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getConvocatoria"));
			ps.setLong(1,id);
                        System.out.println("entra a consulta "+ps);
			rs=ps.executeQuery();
			while(rs.next()){
				convocatoriaOBJ=new ConvocatoriaOBJ();

				convocatoriaOBJ.setConvId(rs.getLong(i++));
				convocatoriaOBJ.setConvAno(rs.getInt(i++));
				convocatoriaOBJ.setConvNumero(rs.getInt(i++));
			//	convocatoriaOBJ.setConvTipo(rs.getString(i++));
				convocatoriaOBJ.setConvNombre(rs.getString(i++));
				convocatoriaOBJ.setConvCuantia(rs.getString(i++));
				convocatoriaOBJ.setConvDuracion(rs.getInt(i++));
				convocatoriaOBJ.setConvFecInicio(rs.getString(i++));
				convocatoriaOBJ.setConvFecFin(rs.getString(i++));
				convocatoriaOBJ.setConvDirigido(rs.getString(i++));
				convocatoriaOBJ.setConvPublica(rs.getBoolean(i++));
				convocatoriaOBJ.setConvArchivo(rs.getString(i++));
				convocatoriaOBJ.setObservacion(rs.getString(i++));
				convocatoriaOBJ.setConvResolucion(rs.getString(i++));
				convocatoriaOBJ.setCantProduct(rs.getInt(i++));
				convocatoriaOBJ.setCorteActual(rs.getString(i++));
				convocatoriaOBJ.setConvTipo(rs.getInt(i++));
				convocatoriaOBJ.setConvEvalua(rs.getBoolean(i++));
				convocatoriaOBJ.setConvTipo(rs.getInt(i++));
				convocatoriaOBJ.setRol(rs.getInt(i++));
			}
			if(convocatoriaOBJ!=null){
				ps=cn.prepareStatement(rb.getString("getCortesConvocatoria"));
				ps.setLong(1,id);
				rs=ps.executeQuery();
				while(rs.next()){
					i=1;
					corte=new ConvCorte();
					corte.setNumCorte(rs.getInt(i++));
					corte.setFechaApertura(""+rs.getDate(i++));
					corte.setFechaCierre(""+rs.getDate(i++));
					corte.setAuxApertura(""+rs.getDate(i++));
					corte.setAuxCierre(""+rs.getDate(i++));
					listaCortes.add(corte);
					ini=corte.getFechaApertura().replace("-","/");
					fin=corte.getFechaCierre().replace("-","/");

					if(g.entreFechas(new java.util.Date(ini),new java.util.Date(fin),new java.util.Date())){
						convocatoriaOBJ.setInscripcion(true);
					}
				}
			}

		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return convocatoriaOBJ;
	}




	public boolean borrar(int en, long convId) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		try {
			cn=cursor.getConnection(super.perfil);
		}catch(Exception e){
			lanzaExcepcion(e);
		}
	//	System.out.println("entra metodo de eliminacion");
		switch(en){
			case ParametrosOBJ.ListaAspecto:
				try {
					ps=cn.prepareStatement(rb.getString("elimina_Aspecto"));
					ps.setLong(1,convId);
					ps.execute();
					retorno=true;
				}catch (SQLException e) {
					lanzaExcepcion(e);
				}catch (Exception e) {
					lanzaExcepcion(e);
				}finally{
					cerrar(ps);
					cerrar(cn);
				}
			break;
			case ParametrosOBJ.ListaCompromiso:
				try {
					ps=cn.prepareStatement(rb.getString("elimina_compromiso"));
					ps.setLong(1,convId);
					ps.execute();
					retorno=true;
				}catch (SQLException e) {
					lanzaExcepcion(e);
				}catch (Exception e) {
					lanzaExcepcion(e);
				}finally{
					cerrar(ps);
					cerrar(cn);
				}
			break;
			case ParametrosOBJ.ListaCriterio:
				try {
					ps=cn.prepareStatement(rb.getString("elimina_criterio"));
					ps.setLong(1,convId);
					ps.execute();
					retorno=true;
				}catch (SQLException e) {
					lanzaExcepcion(e);
				}catch (Exception e) {
					lanzaExcepcion(e);
				}finally{
					cerrar(ps);
					cerrar(cn);
				}
			break;
			case ParametrosOBJ.ListaEje:
				try {
		//			System.out.println("entra a eliminar "+convId);
					ps=cn.prepareStatement(rb.getString("elimina_eje"));
					ps.setLong(1,convId);
					ps.execute();
					retorno=true;
		//			System.out.println("Termina "+convId);
				}catch (SQLException e) {
					lanzaExcepcion(e);
				}catch (Exception e) {
					lanzaExcepcion(e);
				}finally{
					cerrar(ps);
					cerrar(cn);
				}
			break;
			case ParametrosOBJ.ListaRubros:
				try {
		//			System.out.println("entra a eliminar "+convId);
					ps=cn.prepareStatement(rb.getString("elimina_rubro"));
					ps.setLong(1,convId);
					ps.execute();
					retorno=true;
		//			System.out.println("Termina "+convId);
				}catch (SQLException e) {
					lanzaExcepcion(e);
				}catch (Exception e) {
					lanzaExcepcion(e);
				}finally{
					cerrar(ps);
					cerrar(cn);
				}
			break;
			case ParametrosOBJ.ListaDocumentosRequisito:
                                try {
                //                      System.out.println("entra a eliminar "+convId);
                                        ps=cn.prepareStatement(rb.getString("elimina_documento"));
                                        ps.setLong(1,convId);
                                        ps.execute();
                                        retorno=true;
                //                      System.out.println("Termina "+convId);
                                }catch (SQLException e) {
                                        lanzaExcepcion(e);
                                }catch (Exception e) {
                                        lanzaExcepcion(e);
                                }finally{
                                        cerrar(ps);
                                        cerrar(cn);
                                }
                        break;

			case ParametrosOBJ.porcentajes:
				try {
					ps=cn.prepareStatement(rb.getString("elimina_porcentajes"));
					ps.setLong(1,convId);
					ps.execute();
					retorno=true;
				}catch (SQLException e) {
					lanzaExcepcion(e);
				}catch (Exception e) {
					lanzaExcepcion(e);
				}finally{
					cerrar(ps);
					cerrar(cn);
				}
			break;
			case ParametrosOBJ.AspectoComite:
				try {
					ps=cn.prepareStatement(rb.getString("elimina_Aspecto_Comite"));
					ps.setLong(1,convId);
					ps.execute();
					retorno=true;
				}catch (SQLException e) {
					lanzaExcepcion(e);
				}catch (Exception e) {
					lanzaExcepcion(e);
				}finally{
					cerrar(ps);
					cerrar(cn);
				}
			break;
		}
		return retorno;
	}
	public void insertaAspectos(InsercionGralOBJ insercionGralOBJ, long convId) {
		Connection cn=null;
		PreparedStatement ps=null;
		int c=1;
		try {
			//System.out.println("LLega al metodo de insercion "+insercionGralOBJ.getAspectos().length);
			if(insercionGralOBJ.getAspectos()!=null){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("inserta_aspectos"));
		//		System.out.println("cantidad aspectos => "+insercionGralOBJ.getAspectos().length);
				for(int i=0;i<insercionGralOBJ.getAspectos().length;i++){
					c=1;
					ps.setLong(c++,convId);
					ps.setLong(c++,insercionGralOBJ.getCriterios()[i]);
					ps.setLong(c++,insercionGralOBJ.getAspectos()[i]);
					ps.setFloat(c++,insercionGralOBJ.getAspValor()[i]);
					ps.execute();
				}
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
	}
	public void insertaAspectosComite(InsercionGralOBJ insercionGralOBJ, long convId) {
		Connection cn=null;
		PreparedStatement ps=null;
		int c=1;
		try {
			//System.out.println("LLega al metodo de insercion "+insercionGralOBJ.getAspectos().length);q
			if(insercionGralOBJ.getAspectos()!=null){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("inserta_aspectos_comite"));
		//		System.out.println("cantidad aspectos => "+insercionGralOBJ.getAspectos().length);
				for(int i=0;i<insercionGralOBJ.getAspectos().length;i++){
					c=1;
					ps.setLong(c++,convId);
					ps.setLong(c++,insercionGralOBJ.getAspectos()[i]);
					ps.setString(c++,insercionGralOBJ.getComite());
					ps.setFloat(c++,100);
					ps.execute();
				}
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
	}
	public void insertaCriterios(InsercionGralOBJ insercionGralOBJ, long convId) {
		Connection cn=null;
		PreparedStatement ps=null;
		int c=1;
		try {
			if(insercionGralOBJ.getCriterios()!=null){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("inserta_criterios"));
				for(int i=0;i<insercionGralOBJ.getCriterios().length;i++){
					c=1;
					ps.setLong(c++,convId);
					ps.setLong(c++,insercionGralOBJ.getCriterios()[i]);
					ps.setLong(c++,insercionGralOBJ.getCritValor()[i]);
					ps.execute();
				}
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
	}
	public void insertaEjes(InsercionGralOBJ insercionGralOBJ, long convId) {
		Connection cn=null;
		PreparedStatement ps=null;
		int c=1;
		try {
			if(insercionGralOBJ.getEjeTematico()!=null){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("inserta_ejes"));
				for(int i=0;i<insercionGralOBJ.getEjeTematico().length;i++){
					c=1;
					ps.setLong(c++,convId);
					ps.setLong(c++,insercionGralOBJ.getEjeTematico()[i]);
					ps.execute();
				}
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
	}
	public boolean insertaCompromisos(InsercionGralOBJ insercionGralOBJ, long convId) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int num=1,c=1;
		try {
			if(insercionGralOBJ.getCompromiso()!=null){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("inserta_cant_comp"));
				ps.setInt(c++,insercionGralOBJ.getAspectComit());
				ps.setLong(c++,convId);
				ps.executeUpdate();
				ps=cn.prepareStatement(rb.getString("inserta_compromisos"));
				
				for(int i=0;i<insercionGralOBJ.getCompromiso().length;i++){
					c=1;
					System.out.println("inserta comp: "+insercionGralOBJ.getCompromiso()[i]+" = "+insercionGralOBJ.getObligatorio()[i]);
					System.out.println("valor1: "+insercionGralOBJ.getObservaciones()[i]);
					ps.setLong(c++,num++);
					ps.setLong(c++,convId);
					ps.setLong(c++,insercionGralOBJ.getCompromiso()[i]);
					ps.setLong(c++,insercionGralOBJ.getObligatorio()[i]);
					ps.setString(c++,insercionGralOBJ.getObservaciones()[i]);
					int valor = Integer.parseInt(insercionGralOBJ.getObservaciones()[i]);
					System.out.println("valor: "+valor);
					ps.setLong(c++,valor);
					ps.addBatch();
				}
				ps.executeBatch();
				retorno=true;
			}
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
	public boolean setTerminos(long convId, String nombre) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("archivo_convocatoria"));
			//System.out.println("El d�digo "+convId);
			ps.setString(i++,nombre);
			ps.setLong(i++,convId);
			ps.execute();
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
	public boolean setResolucion(long convId, String nombre) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("archivo_convocatoria2"));
			ps.setString(i++,nombre);
			ps.setLong(i++,convId);
			ps.execute();
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

        public boolean setAdendo(long convId, String nombre) {
                boolean retorno=false;
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
		System.out.println("nombre:"+nombre + "Convid:"+convId);
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("archivo_adendo"));
                        ps.setString(i++,nombre);
                        ps.setLong(i++,convId);
                        ps.execute();
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

        public int numAdendo(long convId) {
                int num_adendo=0;
                Connection cn=null;
                PreparedStatement ps=null;
		ResultSet rs=null;
                int i=1;
                System.out.println("Convid:"+convId);
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("numadendo"));
                        ps.setLong(i++,convId);
			rs=ps.executeQuery();
                        while(rs.next()){
                                num_adendo=rs.getInt(1);
                        }
                System.out.println("Numadendo:"+num_adendo);
                }catch (SQLException e) {
                        lanzaExcepcion(e);
                }catch (Exception e) {
                        lanzaExcepcion(e);
                }finally{
                        cerrar(ps);
                        cerrar(cn);
                }
                return num_adendo;
        }

	public List consultaRubros(int consulta,long convocatoria){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
		//	System.out.println("Consulta="+consulta);
		//	System.out.println("Convocatoria="+convocatoria);
			ps=cn.prepareStatement(rb.getString("lista_Rubros"+consulta));
			if(consulta==11)
				ps.setLong(1,convocatoria);
			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
	//			System.out.println("encuentra rubros");
				RubrosOBJ rubrosOBJ=new RubrosOBJ();
				rubrosOBJ.setCodigo(rs.getInt(i++));
				rubrosOBJ.setNombre(rs.getString(i++));
				if(consulta==11){
					rubrosOBJ.setValor(rs.getString(i++));
		//			System.out.println("ingresa valor "+criteriosOBJ.getValor());
				}
				l.add(rubrosOBJ);
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
		return l;
	}
	public void insertaRubros(InsercionGralOBJ insercionGralOBJ, long convId) {
		Connection cn=null;
		PreparedStatement ps=null;
		int c=1;
		try {
			if(insercionGralOBJ.getRubros()!=null){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("inserta_rubros"));
				for(int i=0;i<insercionGralOBJ.getRubros().length;i++){
					c=1;
					ps.setLong(c++,convId);
					ps.setLong(c++,insercionGralOBJ.getRubros()[i]);
					ps.setDouble(c++,insercionGralOBJ.getRubValor()[i]);
					ps.execute();
				}
				insertaObservRubros(insercionGralOBJ,convId);
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
	}

        public boolean insertaDocumentosRequisito(InsercionGralOBJ insercionGralOBJ, long convId) {
                boolean retorno=false;
		Connection cn=null;
                PreparedStatement ps=null;
                int num=1,c=1;
                try {
                        if(insercionGralOBJ.getDocumentos()!=null){
		System.out.println("inserta compro:" );
                                cn=cursor.getConnection(super.perfil);
                                ps=cn.prepareStatement(rb.getString("inserta_documentosrequisito"));
                                for(int i=0;i<insercionGralOBJ.getDocumentos().length;i++){

                                        c=1;
                                        ps.setLong(c++,convId);
                                        ps.setLong(c++,insercionGralOBJ.getDocumentos()[i]);
					ps.setLong(c++,insercionGralOBJ.getObligatorio()[i]);
                                        ps.addBatch();
                                }
				ps.executeBatch();
                                retorno=true;
                        }
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

	public boolean insertaObservRubros(InsercionGralOBJ insercionGralOBJ, long convId) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int c=1;
		try {
	//		System.out.println("observaciones="+convId+"--"+insercionGralOBJ.getObservacion());
			if(insercionGralOBJ.getRubros()!=null){
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("insertaObservRubros"));
				ps.setString(c++,insercionGralOBJ.getObservacion());
				ps.setLong(c++,convId);
				ps.execute();
			}
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
	public boolean insertaPorcentajes(InsercionGralOBJ insercionGralOBJ, long convId) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int c=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("insertaPorcentajes"));
			ps.setLong(c++,convId);
			ps.setDouble(c++,insercionGralOBJ.getPorcentInt());
			ps.setDouble(c++,insercionGralOBJ.getPorcentExt());
			ps.setDouble(c++,insercionGralOBJ.getPorcentComit());
			ps.executeUpdate();
			setMensaje("Porcentajes almacenados correctamente");
			retorno=true;
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

        public boolean actualizaEvalua(InsercionGralOBJ insercionGralOBJ, long convId) {
                boolean retorno=false;
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                try {
                        cn=cursor.getConnection(super.perfil);
                        ps=cn.prepareStatement(rb.getString("actualizaEvalua"));
			System.out.println("entrooo a actualiza");
                        ps.setBoolean(i++,insercionGralOBJ.getConvEvalua());
                        ps.setLong(i++,convId);
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

	public InsercionGralOBJ getPorcentajes(long convId) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int c=1;
		InsercionGralOBJ insercionGralOBJ=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getPorcentajes"));
			ps.setLong(c++,convId);
			rs=ps.executeQuery();
			while(rs.next()){
				insercionGralOBJ=new InsercionGralOBJ();
				insercionGralOBJ.setPorcentInt(rs.getDouble(1));
				insercionGralOBJ.setPorcentExt(rs.getDouble(2));
				insercionGralOBJ.setPorcentComit(rs.getDouble(3));
			}
		} catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return insercionGralOBJ;
	}
	public List getCortes(long convId) {
		List lista=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InsercionGralOBJ corte=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaCortes"));
			ps.setLong(1,convId);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				corte=new InsercionGralOBJ();
				corte.setConvId(convId);
				corte.setIdCorte(rs.getInt(i++));
				corte.setNumCorte(rs.getInt(i++));
				corte.setFechaApertura(rs.getString(i++));
				corte.setFechaCierre(rs.getString(i++));
				corte.setAuxApertura(rs.getString(i++));
				corte.setAuxCierre(rs.getString(i++));
				lista.add(corte);
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

	public boolean insertaCorte(InsercionGralOBJ corte, long convId) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("insertaCorte"));
			ps.setLong(i++,corte.getConvId());
			ps.setInt(i++,corte.getNumCorte());
			ps.setDate(i++,Date.valueOf(corte.getFechaApertura()));
			ps.setDate(i++,Date.valueOf(corte.getFechaCierre()));
			if(corte.getAuxApertura()!=null)
				ps.setDate(i++,Date.valueOf(corte.getAuxApertura()));
			else
				ps.setNull(i++, Types.DATE);
			if(corte.getAuxCierre()!=null)
				ps.setDate(i++,Date.valueOf(corte.getAuxCierre()));
			else
				ps.setNull(i++, Types.DATE);

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

	public boolean actualizaCorte(InsercionGralOBJ corte) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizaCorte"));
			ps.setDate(i++,Date.valueOf(corte.getFechaApertura()));
			ps.setDate(i++,Date.valueOf(corte.getFechaCierre()));
			if(corte.getAuxApertura()!=null)
				ps.setDate(i++,Date.valueOf(corte.getAuxApertura()));
			else
				ps.setNull(i++, Types.DATE);
			if(corte.getAuxCierre()!=null)
				ps.setDate(i++,Date.valueOf(corte.getAuxCierre()));
			else
				ps.setNull(i++, Types.DATE);
			ps.setLong(i++,corte.getIdCorte());

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

	public boolean eliminaCorte(InsercionGralOBJ corte) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("eliminaCorte"));
			ps.setLong(i++,corte.getIdCorte());
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


        public boolean nuevoRequisitoDoc(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
		int i=1;
			System.out.println("entrooo a nuevo");
                try {
                        cn=cursor.getConnection(super.perfil);
                                ps=cn.prepareStatement(rb.getString("insertaRequisitoDoc"));
                                ps.setString(i++,convocatoriaOBJ.getReqNombre());
		                ps.setBoolean(i++,convocatoriaOBJ.isReqEstado());
                                ps.execute();
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

        public boolean nuevoRubro(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                        System.out.println("entrooo a nuevo");
                try {
                        cn=cursor.getConnection(super.perfil);
                                ps=cn.prepareStatement(rb.getString("insertaRubro"));
                                ps.setInt(i++,convocatoriaOBJ.getRubId());
                                ps.setString(i++,convocatoriaOBJ.getRubNombre());
                                ps.execute();
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

        public boolean nuevoCompromiso(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                        System.out.println("entrooo a nuevo");
                try {
                        cn=cursor.getConnection(super.perfil);
                                ps=cn.prepareStatement(rb.getString("insertaCompromiso"));
                                ps.setInt(i++,convocatoriaOBJ.getComId());
                                ps.setString(i++,convocatoriaOBJ.getComNombre());
				ps.setBoolean(i++,convocatoriaOBJ.isComEstado());
                                ps.execute();
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

        public boolean nuevoCriterio(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                        System.out.println("entrooo a nuevo");
                try {
                        cn=cursor.getConnection(super.perfil);
                                ps=cn.prepareStatement(rb.getString("insertaCriterio"));
                                ps.setInt(i++,convocatoriaOBJ.getCritId());
                                ps.setString(i++,convocatoriaOBJ.getCritNombre());
                                ps.setBoolean(i++,convocatoriaOBJ.isCritEstado());
                                ps.execute();
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

        public boolean nuevoAspecto(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                        System.out.println("entrooo a nuevo");
                try {
                        cn=cursor.getConnection(super.perfil);
                                ps=cn.prepareStatement(rb.getString("insertaAspecto"));
                                ps.setInt(i++,convocatoriaOBJ.getAspId());
                                ps.setString(i++,convocatoriaOBJ.getAspNombre());
                                ps.setBoolean(i++,convocatoriaOBJ.isAspEstado());
                                ps.setInt(i++,convocatoriaOBJ.getCritId());
                                ps.execute();
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

        public boolean nuevoEje(ConvocatoriaOBJ convocatoriaOBJ){
                boolean retorno=false;
                List l=new ArrayList();
                Connection cn=null;
                PreparedStatement ps=null;
                int i=1;
                        System.out.println("entrooo a nuevo");
                try {
                        cn=cursor.getConnection(super.perfil);
                                ps=cn.prepareStatement(rb.getString("insertaEje"));
                                ps.setInt(i++,convocatoriaOBJ.getEjeId());
                                ps.setString(i++,convocatoriaOBJ.getEjeNombre());
                                ps.setBoolean(i++,convocatoriaOBJ.isEjeEstado());
                                ps.execute();
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

}
