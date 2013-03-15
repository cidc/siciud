package cidc.inscripcionConv.db;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import cidc.convMovilidad.obj.InfoGeneral;
import cidc.convMovilidad.obj.Requisitos;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;
import cidc.general.mails.EnvioMail2;
import cidc.general.mails.Reporte;
import cidc.general.obj.CrearClaves;
import cidc.general.obj.Globales;
import cidc.inscripSistema.obj.Persona;
import cidc.inscripcionConv.obj.CoInvestigadorOBJ;
import cidc.inscripcionConv.obj.CompromisosOBJ;
import cidc.inscripcionConv.obj.GruposOBJ;
import cidc.inscripcionConv.obj.InvestigadoresOBJ;
import cidc.inscripcionConv.obj.ListaPropuesta;
import cidc.inscripcionConv.obj.ProyCurOBJ;
import cidc.inscripcionConv.obj.ResumenInscOBJ;
import cidc.inscripcionConv.obj.ResumenRubrosOBJ;
import cidc.inscripcionConv.obj.RubrosOBJ;
import cidc.inscripcionConv.obj.InscripcionConvOBJ;

public class InscripcionConvDB extends BaseDB{

	private String clave;

	public InscripcionConvDB(CursorDB c, int perfil){
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.inscripcionConv.consultas");
	}
	public List AjaxGruposInvestigacion(int facultad,int tipo){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null; 
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ajaxGrupos"+tipo));
			ps.setLong(1,facultad);
			if(tipo!=0)
				ps.setLong(2,tipo);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				GruposOBJ gruposOBJ=new GruposOBJ();
				gruposOBJ.setCodigo(rs.getInt(i++));
				gruposOBJ.setNombre(rs.getString(i++));
				l.add(gruposOBJ);
	//			System.out.println(gruposOBJ.getNombre());
			}
		//	System.out.println("la cantidad de grupos encontrados es:"+l.size());
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

	public InvestigadoresOBJ AjaxDirector(int grupo){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InvestigadoresOBJ investigadoresOBJ=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
		//	System.out.println("Llega al metodo "+grupo);
			ps=cn.prepareStatement(rb.getString("ajaxDirector"));
			ps.setLong(1,grupo);
			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
				investigadoresOBJ=new InvestigadoresOBJ();
				investigadoresOBJ.setCodigo(rs.getInt(i++));
				investigadoresOBJ.setNombre(rs.getString(i++));
		//		System.out.println("encuentra Director "+investigadoresOBJ.getCodigo());
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
		return investigadoresOBJ;
	}

	public List AjaxInvestigadores(long grupo){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
	//		System.out.println("Llega al metodo "+grupo);
			ps=cn.prepareStatement(rb.getString("ajaxInvestigador"));
			ps.setLong(1,grupo);
			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
	//			System.out.println("encuentra investigador");
				InvestigadoresOBJ investigadoresOBJ=new InvestigadoresOBJ();
				investigadoresOBJ.setCodigo(rs.getInt(i++));
				investigadoresOBJ.setNombre(rs.getString(i++));
				l.add(investigadoresOBJ);
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
	
	//Ajax para cargar la lista de los profesores que pertenecen a un grupo/semillero de investigación
	public List AjaxProfesores(long idGrupo){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
	//		System.out.println("Llega al metodo "+grupo);
			ps=cn.prepareStatement(rb.getString("ajaxProfesor"));
			ps.setLong(1,idGrupo);
			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
	//			System.out.println("encuentra investigador");
				InvestigadoresOBJ investigadoresOBJ=new InvestigadoresOBJ();
				investigadoresOBJ.setIdDirector(rs.getInt(i++));
				System.out.println("Id profesor "+rs.getInt(1));
				investigadoresOBJ.setDirector(rs.getString(i++));
				System.out.println("Nombre Profesor "+rs.getString(2));
				l.add(investigadoresOBJ);
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
	
	public List getRubros(long conv,int t){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			if(t==0){
				ps=cn.prepareStatement(rb.getString("lista_rubros"));
				ps.setLong(1,conv);
			}
			else
				ps=cn.prepareStatement(rb.getString("lista_rubros2"));

			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
				RubrosOBJ rubrosOBJ=new RubrosOBJ();
				rubrosOBJ.setCodigo(rs.getInt(i++));
				rubrosOBJ.setNombre(rs.getString(i++));
				if(t==0)
					rubrosOBJ.setValor(rs.getString(i++));
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

	public List consultaRubros(long convocatoria){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("lista_Rubros"));
			ps.setLong(1,convocatoria);
			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
			//	System.out.println("encuentra rubros");
				RubrosOBJ rubrosOBJ=new RubrosOBJ();
				rubrosOBJ.setCodigo(rs.getInt(i++));
				rubrosOBJ.setNombre(rs.getString(i++));
				rubrosOBJ.setValor(rs.getString(i++));
				l.add(rubrosOBJ);
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
	public long getMaxId(Connection c)throws SQLException{
		long id=0;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			if(c==null)
				cn=cursor.getConnection(super.perfil);
			else
				cn=c;
			ps=cn.prepareStatement(rb.getString("maxId"));
			rs=ps.executeQuery();

			while(rs.next()){
				i=1;
				id=rs.getLong(i++);
	//			System.out.println("encuentra id");
			}
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
		}
		return id;
	}

	public boolean insertaInscripcion(InscripcionConvOBJ inscripcionConvOBJ){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			long cod=getMaxId(cn)+1;
			ps=cn.prepareStatement(rb.getString("inserta_inscripcion"));
			cn.setAutoCommit(false);

			ps.setLong(i++,inscripcionConvOBJ.getPropConvId());
			ps.setInt(i++,inscripcionConvOBJ.getPropFacultad());
			ps.setInt(i++,inscripcionConvOBJ.getPropProyCur());
			ps.setInt(i++,inscripcionConvOBJ.getPropGrupoInv());
			ps.setInt(i++,inscripcionConvOBJ.getPropInvesPrincId());
			ps.setString(i++,inscripcionConvOBJ.getPropNombre());
			ps.setString(i++,inscripcionConvOBJ.getPropAbstract());
			ps.setString(i++,inscripcionConvOBJ.getPropPalClave());
			ps.setInt(i++,inscripcionConvOBJ.getPropHorasInv());
			ps.setString(i++, inscripcionConvOBJ.getProyectoinv()); // proyecto segun Plan de Accion
			ps.setString(i++, inscripcionConvOBJ.getPropDirPro()); // Director del proyecto de inv
			ps.setLong(i++,inscripcionConvOBJ.getPropConvId());		
			ps.executeUpdate();

		//	System.out.println("El serial va en "+cod);

			insertaCoInvesti(cn,inscripcionConvOBJ);
			insertaGrupoAsociado(cn,inscripcionConvOBJ);
			insertaRubros(cn,inscripcionConvOBJ,getRubros(inscripcionConvOBJ.getPropConvId(),1));
		//	setClaveIngreso(cn,cod);
			insertaEstado(cn,cod);
			cn.commit();
			retorno=true;
		}catch (SQLException e) {
			lanzaExcepcion(e);
			System.out.println(e.getNextException());
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
			cerrar(cn);
		}
		return retorno;
	}


	private void enviaMailInscripcion(Connection cn,ResumenInscOBJ resumenInscOBJ, InscripcionConvOBJ inscripcionConvOBJ)throws Exception{
		ResourceBundle rb=ResourceBundle.getBundle("cidc.general.mails.correoInscripcion");
		Globales global=new Globales();
		PreparedStatement ps=null;
		ResultSet rs=null;
		String consMail=null,consProp=null;
		String []direcciones={inscripcionConvOBJ.getPropMailInvest()};
		ps=cn.prepareStatement(rb.getString("getConsecutivos"));
		rs=ps.executeQuery();
		while(rs.next()){
			consProp=""+rs.getLong(1);
			consMail=rs.getString(2);
		}
		if(consMail!=null && consProp!=null){
			EnvioMail2 envioMail=new EnvioMail2("siciud");
			StringBuffer texto=new StringBuffer();
			texto.append("CIDC-SI "+consMail+"-"+global.getAnoCortoHoy()+"<br><br>");

			texto.append(rb.getString("i1"));
			texto.append(resumenInscOBJ.getInvestigador());
			texto.append(rb.getString("i2"));
			texto.append(resumenInscOBJ.getPropuesta());
			texto.append(rb.getString("i3"));
			texto.append(inscripcionConvOBJ.getPropId());
			texto.append(rb.getString("i4"));


			texto.append(rb.getString("t1"));
			texto.append(resumenInscOBJ.getConvocatoria());
			texto.append(rb.getString("t2"));
			texto.append(resumenInscOBJ.getFacultad());
			texto.append(rb.getString("tdd"));
			texto.append(resumenInscOBJ.getGrupo());
			texto.append(rb.getString("t3"));
			texto.append(resumenInscOBJ.getInvestigador());
			texto.append(rb.getString("t4"));
			texto.append(resumenInscOBJ.getPropuesta());
			texto.append(rb.getString("t5"));
			texto.append(resumenInscOBJ.getPropAbstract());
			texto.append(rb.getString("t6"));
			texto.append(resumenInscOBJ.getPalClaves());
			if(resumenInscOBJ.getCoInvestigadores().size()>0){
				texto.append(rb.getString("t7"));
				Iterator iterCoinv=resumenInscOBJ.getCoInvestigadores().iterator();
				CoInvestigadorOBJ coInvestigadorOBJ=null;
				while(iterCoinv.hasNext()){
					coInvestigadorOBJ=(CoInvestigadorOBJ)iterCoinv.next();
					texto.append(rb.getString("trd"));
					texto.append(coInvestigadorOBJ.getDocumento());
					texto.append(rb.getString("tdd"));
					texto.append(coInvestigadorOBJ.getNombres());
					texto.append(rb.getString("tdd"));
					texto.append(coInvestigadorOBJ.getApellidos());
					texto.append(rb.getString("tdd"));
					texto.append(coInvestigadorOBJ.getPapel());
					texto.append(rb.getString("td"));
				}
				texto.append(rb.getString("trtab"));
			}
			if(resumenInscOBJ.getRubros().size()>0){
				texto.append(rb.getString("br"));
				texto.append(rb.getString("t8"));
				Iterator iterRub=resumenInscOBJ.getRubros().iterator();
				ResumenRubrosOBJ rubrosOBJ=null;
				while(iterRub.hasNext()){
					rubrosOBJ=(ResumenRubrosOBJ)iterRub.next();
					texto.append(rb.getString("trd"));
					texto.append(rubrosOBJ.getNombre());
					texto.append(rb.getString("tdd"));

					if(rubrosOBJ.getCidc()!="null"&&rubrosOBJ.getCidc()!=null)
						texto.append(rubrosOBJ.getCidc());
					texto.append(rb.getString("tdd"));
					if(rubrosOBJ.getUd()!="null"&&rubrosOBJ.getUd()!=null)
						texto.append(rubrosOBJ.getUd());
					texto.append(rb.getString("tdd"));
					if(rubrosOBJ.getContra()!="null"&&rubrosOBJ.getContra()!=null)
						texto.append(rubrosOBJ.getContra());
					texto.append(rb.getString("td"));
				}
				texto.append(rb.getString("trtab"));
				texto.append(rb.getString("br"));
			}
			if(resumenInscOBJ.getCompromisos().size()>0){
				texto.append(rb.getString("t9"));
				Iterator iterComp=resumenInscOBJ.getCompromisos().iterator();
				CompromisosOBJ compromisosOBJ=null;
				while(iterComp.hasNext()){
					compromisosOBJ=(CompromisosOBJ)iterComp.next();
					texto.append(rb.getString("trd"));
					texto.append(compromisosOBJ.getNumero());
					texto.append(rb.getString("tdd"));
					texto.append(compromisosOBJ.getNombre());
					texto.append(rb.getString("tdd"));
					texto.append(compromisosOBJ.getIndicador());
					texto.append(rb.getString("tdd"));
					texto.append(compromisosOBJ.getObligatorio());
					texto.append(rb.getString("td"));
				}
				texto.append(rb.getString("trtab"));
			}
			texto.append(rb.getString("trtab"));
			texto.append(rb.getString("i5"));

			envioMail.enviar(direcciones,"Inscripciï¿½n Satisfactoria",""+texto);
			Reporte reporteMail=new Reporte(cursor,super.perfil);
			reporteMail.reportar(resumenInscOBJ.getInvestigador(),"Resumen Inscripciï¿½n a Convocatoria",direcciones[0],consMail);
		//	System.out.println("Mensaje Enviado");
		}
	}

	private boolean insertaEstado(Connection cn, long cod) throws SQLException{
		boolean retorno=true;
		PreparedStatement ps=null;
		try {
			ps=cn.prepareStatement(rb.getString("insertaEstado"));
			ps.setLong(1,cod);
			ps.executeUpdate();
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
		}
		return retorno;
	}

	private boolean setClaveIngreso(Connection cn, long cod){
		boolean retorno=true;
		PreparedStatement ps=null;
		try {
			ps=cn.prepareStatement(rb.getString("asignaClave"));
			CrearClaves clave=new CrearClaves();
			String key=clave.getClave();
	//		System.out.println("La clave asiganda es: "+key);
			ps.setLong(1,cod);
			ps.setString(2,key);
			ps.executeUpdate();
			this.clave=key;
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(ps);
		}
		return retorno;
	}
	public boolean insertaCoInvesti(Connection cn,InscripcionConvOBJ inscripcionConvOBJ)throws SQLException{
		boolean retorno=false;
		PreparedStatement ps=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("inserta_coinvestigador"));
			if(inscripcionConvOBJ.getPropCoInvDocumento()!=null){
				for(int a=0;a<inscripcionConvOBJ.getPropCoInvDocumento().length;a++){
					i=1;
					if(!inscripcionConvOBJ.getPropCoInvNombres()[a].equals("")){
						ps.setString(i++,inscripcionConvOBJ.getPropCoInvDocumento()[a]);
						ps.setString(i++,inscripcionConvOBJ.getPropCoInvNombres()[a]);
						ps.setString(i++,inscripcionConvOBJ.getPropCoInvApellidos()[a]);
						ps.setString(i++,inscripcionConvOBJ.getPropCoInvPapel()[a]);
						ps.setInt(i++,inscripcionConvOBJ.getPropCoInvHoras()[a]);
						ps.addBatch();
					}
				}
				ps.executeBatch();
			}
			retorno=true;
		} finally{
			cerrar(ps);
		}
		return retorno;
	}
	
	public boolean insertaGrupoAsociado(Connection cn,InscripcionConvOBJ inscripcionConvOBJ)throws SQLException{
		boolean retorno=false;
		PreparedStatement ps=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("insertaGrupoAsociado"));
			if(inscripcionConvOBJ.getPropGrupoAsociado()!=null){
				for(int a=0;a<inscripcionConvOBJ.getPropGrupoAsociado().length;a++){
					i=1;
					ps.setInt(i++,inscripcionConvOBJ.getPropGrupoAsociado()[a]);
					ps.addBatch();
				}
				ps.executeBatch();
			}
			retorno=true;
		} finally{
			cerrar(ps);
		}
		return retorno;
	}
	
	public boolean insertaRubros(Connection cn,InscripcionConvOBJ inscripcionConvOBJ, List todosRubros)throws SQLException{
		boolean retorno=false;
		PreparedStatement ps=null;
		int i=1;
		try {
			ps=cn.prepareStatement(rb.getString("inserta_rubros"));
	/*		System.out.println("Cantidad de Rubros CIDC="+inscripcionConvOBJ.getPropPresPropRubroUd().length);
			System.out.println("Cantidad de Rubros UD="+inscripcionConvOBJ.getPropPresPropRubroUd().length);
			System.out.println("Cantidad de Rubros OTRO="+inscripcionConvOBJ.getPropPresPropRubroContra().length);*/
			Iterator iterRubros=todosRubros.iterator();
			RubrosOBJ rubrosOBJ;
			String valorCIDC="";
			String valorUD="";
			String valorCONTRA="";

			while(iterRubros.hasNext()){
				i=1;
				valorCIDC="";
				valorUD="";
				valorCONTRA="";
				rubrosOBJ=(RubrosOBJ)iterRubros.next();
				if(inscripcionConvOBJ.getIdRubCidc()!=null)
					for(int a=0;a<inscripcionConvOBJ.getIdRubCidc().length;a++){
						if(rubrosOBJ.getCodigo()==inscripcionConvOBJ.getIdRubCidc()[a]){
							valorCIDC=inscripcionConvOBJ.getPropPresPropRubroCidc()[a];
							break;
						}
					}
				
				if (inscripcionConvOBJ.getPropPresPropRubroContra()!=null && inscripcionConvOBJ.getPropPresPropRubroUd()!=null){
					if(inscripcionConvOBJ.getPropPresPropRubroUd().length>0 && inscripcionConvOBJ.getPropPresPropRubroContra().length>0 && inscripcionConvOBJ.getIdRubOtros()!=null){
						for(int a=0;a<inscripcionConvOBJ.getIdRubOtros().length;a++){
							if(rubrosOBJ.getCodigo()==inscripcionConvOBJ.getIdRubOtros()[a]){
								if(a<inscripcionConvOBJ.getPropPresPropRubroUd().length){
									valorUD=inscripcionConvOBJ.getPropPresPropRubroUd()[a];
									valorCONTRA=inscripcionConvOBJ.getPropPresPropRubroContra()[a];
								}
							}
						}
					}
				/*	System.out.println("valorCIDC "+valorCIDC);
					System.out.println("valorUD "+valorUD);
					System.out.println("valorCONTRA "+valorCONTRA);*/
					if(!(valorCIDC.equals("") && valorUD.equals("") && valorCONTRA.equals(""))){
	//					ps.setLong(i++,cod);
						ps.setLong(i++,rubrosOBJ.getCodigo());
						if(!valorCIDC.equals(""))
							ps.setDouble(i++,Double.parseDouble(valorCIDC));
						else
							ps.setNull(i++,Types.NUMERIC);
	
						if(!valorUD.equals(""))
							ps.setDouble(i++,Double.parseDouble(valorUD));
						else
							ps.setNull(i++,Types.NUMERIC);
	
						if(!valorCONTRA.equals(""))
							ps.setDouble(i++,Double.parseDouble(valorCONTRA));
						else
							ps.setNull(i++,Types.NUMERIC);
						ps.execute();
					//	System.out.println("Inserta el rubro**********");
					}
				}
				else{
					if(inscripcionConvOBJ.getIdRubOtros()!=null){
						for(int a=0;a<inscripcionConvOBJ.getIdRubOtros().length;a++){
							if(rubrosOBJ.getCodigo()==inscripcionConvOBJ.getIdRubOtros()[a]){
								if(a<inscripcionConvOBJ.getPropPresPropRubroUd().length){
									valorUD=inscripcionConvOBJ.getPropPresPropRubroUd()[a];
									//valorCONTRA=inscripcionConvOBJ.getPropPresPropRubroContra()[a];
								}
							}
						}
					}
				/*	System.out.println("valorCIDC "+valorCIDC);
					System.out.println("valorUD "+valorUD);
					System.out.println("valorCONTRA "+valorCONTRA);*/
					if(!(valorCIDC.equals("") && valorUD.equals("") && valorCONTRA.equals(""))){
						//					ps.setLong(i++,cod);
											ps.setLong(i++,rubrosOBJ.getCodigo());
											if(!valorCIDC.equals(""))
												ps.setDouble(i++,Double.parseDouble(valorCIDC));
											else
												ps.setNull(i++,Types.NUMERIC);
						
											if(!valorUD.equals(""))
												ps.setDouble(i++,Double.parseDouble(valorUD));
											else
												ps.setNull(i++,Types.NUMERIC);
						
											if(!valorCONTRA.equals(""))
												ps.setDouble(i++,Double.parseDouble(valorCONTRA));
											else
												ps.setNull(i++,Types.NUMERIC);
											ps.execute();
										//	System.out.println("Inserta el rubro**********");
					}
					
				}
			}
			retorno=true;
		}finally{
			cerrar(ps);
		}
		return retorno;
	}
	public ResumenInscOBJ getResumen(String idProp, InscripcionConvOBJ inscripcionConvOBJ) {
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ResumenInscOBJ resumenInscOBJ=null;
		int i=1;
		try {
			if(inscripcionConvOBJ!=null)
				inscripcionConvOBJ.setPropId(Long.parseLong(idProp));
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getResumenInscripcion"));
			ps.setLong(1,Long.parseLong(idProp));
			rs=ps.executeQuery();
			while(rs.next()){
				resumenInscOBJ=new ResumenInscOBJ();
				resumenInscOBJ.setConvocatoria(rs.getString(i++));
				resumenInscOBJ.setFacultad(rs.getString(i++));
				resumenInscOBJ.setGrupo(rs.getString(i++));
				resumenInscOBJ.setInvestigador(rs.getString(i++));
				resumenInscOBJ.setPropuesta(rs.getString(i++));
				resumenInscOBJ.setPropAbstract(rs.getString(i++));
				resumenInscOBJ.setPalClaves(rs.getString(i++));
			}
			if(resumenInscOBJ!=null){
		//		System.out.println("Entra a buscar co investigadores");
				resumenInscOBJ.setIdPropuesta(Long.parseLong(idProp));
				List listConinv=new ArrayList();
				CoInvestigadorOBJ coInvestigadorOBJ;
				ps=cn.prepareStatement(rb.getString("getResumenCoInvestigadores"));
				ps.setLong(1,Long.parseLong(idProp));
				rs=ps.executeQuery();
				while(rs.next()){
		//			System.out.println("encuntra datos de co investigadores");
					i=1;
					coInvestigadorOBJ=new CoInvestigadorOBJ();
					coInvestigadorOBJ.setDocumento(rs.getString(i++));
					coInvestigadorOBJ.setNombres(rs.getString(i++));
					coInvestigadorOBJ.setApellidos(rs.getString(i++));
					coInvestigadorOBJ.setPapel(rs.getString(i++));
					listConinv.add(coInvestigadorOBJ);
				}
				resumenInscOBJ.setCoInvestigadores(listConinv);

//				System.out.println("Entra a buscar rubros");
				List listRub=new ArrayList();
				ResumenRubrosOBJ resumenRubrosOBJ;
				ps=cn.prepareStatement(rb.getString("getResumenRubros"));
				ps.setLong(1,Long.parseLong(idProp));
				rs=ps.executeQuery();
				while(rs.next()){
					i=1;
	//				System.out.println("encuntra datos de rubros");
					resumenRubrosOBJ=new ResumenRubrosOBJ();
					resumenRubrosOBJ.setNombre(rs.getString(i++));
					resumenRubrosOBJ.setCidc(rs.getString(i++));
					resumenRubrosOBJ.setUd(rs.getString(i++));
					resumenRubrosOBJ.setContra(rs.getString(i++));
					listRub.add(resumenRubrosOBJ);
				}
				resumenInscOBJ.setRubros(listRub);
			}

		//	enviaMailInscripcion(cn,resumenInscOBJ,inscripcionConvOBJ);
		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return resumenInscOBJ;
	}
	public List AjaxProyectoCur() {
		List l=new ArrayList();
		ProyCurOBJ proyCurOBJ=null;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			cn = cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("ajaxProyectCur"));
			rs=ps.executeQuery();
			while(rs.next()){
				proyCurOBJ=new ProyCurOBJ();
				proyCurOBJ.setCodigo(rs.getInt(1));
				proyCurOBJ.setNombre(rs.getString(2));
				l.add(proyCurOBJ);
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
	public String getClave() {
		return clave;
	}

	public boolean insertaCompromisos(InscripcionConvOBJ inscripcionConvOBJ){
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		if(inscripcionConvOBJ.getIdCompromisos()!=null){
			try {
				cn=cursor.getConnection(super.perfil);
				ps=cn.prepareStatement(rb.getString("inserta_Compromisos"));
				cn.setAutoCommit(false);
				for(int j=0;j<inscripcionConvOBJ.getIdCompromisos().length;j++){
					ps.setLong(1,inscripcionConvOBJ.getPropId());
					ps.setInt(2,inscripcionConvOBJ.getIdCompromisos()[j]);
					ps.setInt(3,inscripcionConvOBJ.getCantComp()[j]);
					ps.addBatch();
				}
				ps.executeBatch();
				cn.commit();
				retorno=true;
			}catch (SQLException e) {
				lanzaExcepcion(e);
				setMensaje("No se puede insertar más de una vez el listado de los compromisos");
			}catch (Exception e) {
				lanzaExcepcion(e);
				setMensaje("Favor comunicarse con el Centro de Investigaciones");
			}finally{
				cerrar(ps);
				cerrar(cn);
			}
		}else
			retorno=true;
		return retorno;
	}
	public List getListaPropuestas(long idPersona,int perfil, long convId){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);

			if(perfil==13){
				ps=cn.prepareStatement(rb.getString("listaPropuestasIEIE"));
				ps.setLong(1,convId);
			}else{
				ps=cn.prepareStatement(rb.getString("listaPropuestas"));
				ps.setLong(1,idPersona);
				ps.setLong(2,convId);
			}
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				ListaPropuesta propuesta=new ListaPropuesta();
				propuesta.setIdPropuesta(rs.getLong(i++));
				propuesta.setNombrePropuesta(rs.getString(i++));
				l.add(propuesta);
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
	public ResumenInscOBJ getInfoArchivos(String idProp) {
		// TODO Auto-generated method stub
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		ResumenInscOBJ resumenInscOBJ=new ResumenInscOBJ();
		try {
			resumenInscOBJ.setIdPropuesta(Long.parseLong(idProp));
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getArchivos"));
			ps.setLong(1,Long.parseLong(idProp));
			rs=ps.executeQuery();
			while(rs.next()){
				resumenInscOBJ.setDocCompleto(rs.getString(i++));
				resumenInscOBJ.setDocAnexo(rs.getString(i++));
				resumenInscOBJ.setDocAvalGrupo(rs.getString(i++));
				resumenInscOBJ.setDocAvalDir(rs.getString(i++));
				resumenInscOBJ.setDocCerCurr(rs.getString(i++));
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
		return resumenInscOBJ;
	}
	
	public boolean actArchivo(int caso, long id, String nombre) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("actualizaArchivo"+caso));
			ps.setString(i++, nombre);
			ps.setLong(i++, id);
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

	public List totalGruposInvestigacion(){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("TotalGrupos"));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				GruposOBJ gruposOBJ=new GruposOBJ();
				gruposOBJ.setCodigo(rs.getInt(i++));
				gruposOBJ.setNombre(rs.getString(i++));
				gruposOBJ.setMovilidad(rs.getBoolean(i++));
				gruposOBJ.setEstado(rs.getInt(i++));
				l.add(gruposOBJ);
			//	System.out.println(gruposOBJ.getNombre());
			}
		//	System.out.println("la cantidad de grupos encontrados es:"+l.size());
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
	//FUNCIONA PARA FINALIZAR LA INSCRIPCIÓN
	public boolean enviarMail(String idPropuesta,Persona persona){
		boolean retorno=false;
		System.out.println("Ingreso al envio del mail=------>");
		String []destino={persona.getMail()};
		//destino [1]="cidc@udistrital.edu.co";
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String consMail=null;
		boolean x=false;
		//System.out.println("bandera 1");
		try {
			cn = cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getConsecutivo"));
			rs=ps.executeQuery();
			while(rs.next()){
				consMail=rs.getString(1);
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e1);
		}
		//System.out.println("bandera 2");
		ResourceBundle rb1= ResourceBundle.getBundle("cidc.general.mails.soporteCorreoProyectos");
		ResumenInscOBJ general=consultaIndividual(idPropuesta);
		//Requisitos req=consultaRequisitos(idPropuesta);
		Globales gl=new Globales();
		//System.out.println("bandera 3");
		StringBuffer texto=new StringBuffer();
		texto.append("<b>CONSECUTIVO CIDC "+consMail+"-"+gl.getAnoCortoHoy()+"</b><br><br>");
		texto.append(rb1.getString("rp1")+"  <b>"+persona.getNombre()+"</b>");
		texto.append(rb1.getString("rp2")+"");
		texto.append(general.getConvocatoria()+"-2013.");
		texto.append(rb1.getString("e1"));
		texto.append(persona.getNombre());
/*		texto.append(rb1.getString("e2"));
		texto.append(persona.getGrupo());
*/		texto.append(rb1.getString("e3"));
		texto.append(general.getPropuesta());
		texto.append(rb1.getString("e4"));
		texto.append(general.getIdPropuesta()+"-2013-CIDC <br>");
		//texto.append(general.getIdPropuesta()+" - "+general.getConvocatoria());
		texto.append(rb1.getString("e5"));
		//texto.append(rb1.getString("Documentos1"+ " "));
		texto.append(general.getDocCompleto());
		texto.append(rb1.getString("e7"));
		//texto.append(rb1.getString("Documentos2"));
		texto.append(general.getDocAvalGrupo());
		texto.append(rb1.getString("e7"));
		//texto.append(rb1.getString("Documentos3"));
		texto.append(general.getDocAvalDir());
		texto.append(rb1.getString("e8"));
		//texto.append(rb1.getString("Documentos4"));
		texto.append(general.getDocCerCurr());
		texto.append(rb1.getString("fechain"));		
		texto.append(general.getFechaInscripcion());
		texto.append(rb1.getString("fechafin"));
		texto.append(gl.getFechaSimpleHoy() + " Hora:  ");
		texto.append(gl.getHoraSistema());		
		//texto.append(req.getCompromisos3()+"<br>"+req.getCompromisos4());
		//texto.append(req.getCompromisos3()+"<br>");
		/*texto.append(rb1.getString("e18"));
		texto.append(req.getBeneficiosGrupo1());
		texto.append(rb1.getString("e19"));
		texto.append(req.getBeneficiosGrupo2()+"<br>"+req.getBeneficiosGrupo3());
		texto.append(rb1.getString("e20"));
		texto.append(req.getBeneficiosGrupo4()+"<br>"+req.getBeneficiosGrupo5());
		texto.append(rb1.getString("e118"));
		texto.append(req.getInteresInsti1());
		texto.append(rb1.getString("e119"));
		texto.append(req.getInteresInsti2());
		texto.append(rb1.getString("e120"));
		texto.append(req.getInteresInsti3());
		texto.append(rb1.getString("e121"));
		texto.append(req.getInteresInsti4());*/
		texto.append(rb1.getString("e21"));
		//System.out.println("bandera 4");
	/*	if(req.getPartiEvent()!=null)
			if(req.getPartiEvent().length>0){
				texto.append(rb1.getString("e212"));
				for(int j=0;j<req.getPartiEvent().length;j++){
					texto.append("<tr><td>"+req.getPartiEvent()[j]+"</td></tr>");
				}
				x=true;
			}
			else{
				texto.append(rb1.getString("e211"));
			}
		else{
			texto.append(rb1.getString("e211"));
		}*/
		//System.out.println("bandera 5");
		if(!x)
			texto.append(rb1.getString("e221"));
		else
			texto.append(rb1.getString("e222"));
		texto.append(rb1.getString("e23"));
		EnvioMail2 mail=new EnvioMail2("siciud");
		////System.out.println("bandera 6");
		try {
			//destino= nombre correo electronico, asunto, cuerpo del mensaje
			mail.enviar(destino,"Inscripción Convocatoria CIDC",""+texto);
			////System.out.println("bandera 7");
			Reporte reporteMail=new Reporte(cursor,super.perfil);
			reporteMail.reportar(persona.getNombre(),"Inscripción Convocatoria 2012",destino[0],consMail);
			retorno=true;
			//System.out.println("bandera 8");
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}
		return retorno;
		
	}
	
	public Requisitos consultaRequisitos(String idPropuesta){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Requisitos req=null;
		String []eventos=null;
		int i=1,conteo=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaRequisitos"));
			ps.setLong(1,Long.parseLong(idPropuesta));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				req=new Requisitos();
				req.setCompromisos1(rs.getString(i++));
				req.setCompromisos2(rs.getString(i++));
				req.setCompromisos3(rs.getString(i++));
				req.setCompromisos4(rs.getString(i++));
				req.setBeneficiosGrupo1(rs.getString(i++));
				req.setBeneficiosGrupo2(rs.getString(i++));
				req.setBeneficiosGrupo3(rs.getString(i++));
				req.setBeneficiosGrupo4(rs.getString(i++));
				req.setBeneficiosGrupo5(rs.getString(i++));
				req.setInteresInsti1(rs.getString(i++));
				req.setInteresInsti2(rs.getString(i++));
				req.setInteresInsti3(rs.getString(i++));
				req.setInteresInsti4(rs.getString(i++));
			}
			rs=null;
			if(req!=null){
				//Consulta la cantidad de eventos que el investigador agreso en la lista.
				ps=cn.prepareStatement(rb.getString("consultaEventosCont"));
				ps.setLong(1,Long.parseLong(idPropuesta));
				rs=ps.executeQuery();
				while(rs.next()){
					conteo=rs.getInt(1);
				}
				if(conteo>0){
				ps=cn.prepareStatement(rb.getString("consultaEventos"));
				ps.setLong(1,Long.parseLong(idPropuesta));
				rs=ps.executeQuery();
			//	Result paraContar = ResultSupport.toResult(rs);
			//	//System.out.println("encuentra eventos "+paraContar.getRowCount());
				eventos=new String [conteo];
					i=0;
					while(rs.next()){
						eventos[i++]=rs.getString(1);
					}
					req.setPartiEvent(eventos);
				}
			}
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return req;
	}
	public ResumenInscOBJ consultaIndividual(String idPropuesta){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		ResumenInscOBJ info=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaIndividual"));
			ps.setLong(1,Long.parseLong(idPropuesta));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				info=new ResumenInscOBJ();
				info.setIdPropuesta(Long.parseLong(idPropuesta));
				info.setIdPropuesta(rs.getLong(i++));
				info.setConvocatoria(rs.getString(i++));
				info.setPropuesta(rs.getString(i++));
				info.setDocCompleto(rs.getString(i++));
				info.setFechaInscripcion(rs.getString(i++));
				info.setDocAvalGrupo(rs.getString(i++));
				info.setDocAvalDir(rs.getString(i++));				
				info.setDocCerCurr(rs.getString(i++));				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return info;
	}

}

