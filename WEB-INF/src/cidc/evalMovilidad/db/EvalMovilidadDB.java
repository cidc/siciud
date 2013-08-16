package cidc.evalMovilidad.db;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.SortedMap;
import java.util.TreeMap;

import cidc.convMovilidad.obj.InfoGeneral;
import cidc.convMovilidad.obj.Requisitos;
import cidc.evalMovilidad.obj.Criterio;
import cidc.evalMovilidad.obj.InfoEvento;
import cidc.evalMovilidad.obj.PropuestaMovilidad;
import cidc.evalMovilidad.obj.TrayectoriaInvest;
import cidc.general.db.BaseDB;
import cidc.general.db.CursorDB;


public class EvalMovilidadDB extends BaseDB{



	public EvalMovilidadDB(CursorDB c, int perfil) {
		super(c, perfil);
		// TODO Auto-generated constructor stub
		rb=ResourceBundle.getBundle("cidc.evalMovilidad.consultas");
	}

	//Lista las propuestas que se han presentado según el año y el corte
	public List getPropuestas(String ano,String corte,long idEval, String rol){
		List l=new ArrayList();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		PropuestaMovilidad propuestaOBJ=null;
		int rol_evaluar=Integer.parseInt(rol);
		int rol1=0;
		int rol2=0;
		if(rol_evaluar==2){
			rol1=1;
			rol2=2;
		}
		else{
			rol1=3;
			rol2=5;
		}	
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getPropuestas"));
			ps.setString(1,ano+"%");
			ps.setInt(2,Integer.parseInt(corte));			
			ps.setInt(3,rol1);			
			ps.setInt(4,rol2);
			ps.setLong(5, idEval);
System.out.println(ps.toString());
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				propuestaOBJ=new PropuestaMovilidad();
				propuestaOBJ.setIdPropuesta(rs.getLong(i++));
				propuestaOBJ.setEstado(rs.getInt(i++));
				propuestaOBJ.setInvestigador(rs.getString(i++));
				propuestaOBJ.setNombrePonencia(rs.getString(i++));
				l.add(propuestaOBJ);
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



	public TrayectoriaInvest getTrayectoria(String idProp){
		TrayectoriaInvest trayect=new TrayectoriaInvest();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		String []documentos=new String[11];
		try {
			trayect.setIdPropuesta(Long.parseLong(idProp));
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getTrayectoria"));
			ps.setLong(1,Long.parseLong(idProp));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				trayect.setInvestigador(rs.getString(i++));
				trayect.setPapel(rs.getInt(i++));
				trayect.setNombrePonencia(rs.getString(i++));
				trayect.setNombreProyecto(rs.getString(i++));
				trayect.setCvLac(rs.getString(i++));
				trayect.setNombreGrupo(rs.getString(i++));				
				documentos[0]=rs.getString(i++);//aval grupo
				documentos[1]=rs.getString(i++);//aceptacion del evento
				documentos[2]=rs.getString(i++);//resumen
				documentos[3]=rs.getString(i++);//proyecto curricular
				documentos[4]=rs.getString(i++);//decanatura
				documentos[5]=rs.getString(i++);//consejo de facultad
				documentos[6]=rs.getString(i++);//excelencia academica
				documentos[7]=rs.getString(i++);//Certificación de Grupo
				documentos[8]=rs.getString(i++);//certificacion CIDC
				documentos[9]=rs.getString(i++);//resultados obtenidos
				documentos[10]=rs.getString(i++);//carta apoyo económico
				trayect.setListadDoc(documentos);
				trayect.setListadEventos(getEventos(Long.parseLong(idProp)));
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
		return trayect;
	}

	public String [] getEventos(long idProp){
		TrayectoriaInvest trayect=new TrayectoriaInvest();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=0,cant=0;
		String []eventos=null;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getCountEventos"));
			ps.setLong(1,idProp);
			rs=ps.executeQuery();
			while(rs.next())
				cant=rs.getInt(1);

			if(cant>0){
				eventos=new String [cant];
				ps=cn.prepareStatement(rb.getString("getEventos"));
				ps.setLong(1,idProp);
				rs=ps.executeQuery();
				i=0;
				while(rs.next()){
					eventos[i++]=rs.getString(1);
				}
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
		return eventos;
	}

	//Inserta la Trayectoria del investigador
	public boolean insertaCriterioA(Criterio criterio,int tipo, long eval,long ponencia) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//x=5 porque se deben insertar 5 aspectos de evaluacion para el profesor
		int j=0,x=45, k=0, crit=5;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("inserta_Aspectos"));
			//x=6 porque se deben insertar 4 aspectos de evaluacion de los estudiantes
			if(tipo==3){
				x=50; 
				crit=4;
			}				
			for(int i=x;i<x+crit;i++){
				j=i+1;				
				ps.setLong(1, ponencia);
				ps.setInt(2, i);
				ps.setFloat(3,criterio.getValCri1()[k]);
				ps.setLong(4, eval);
				ps.addBatch();
				k=k+1;
			}
			ps.executeBatch();
			cn.commit();
			retorno=true;
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

	//Consulta la información de la agenda de cooperación
	public Requisitos getInfoB(String idPropuesta){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Requisitos req=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaRequisitos"));
			ps.setLong(1,Long.parseLong(idPropuesta));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				req=new Requisitos();
				//Justificacion
				req.setCompromisos1(rs.getString(i++));
				req.setCompromisos2(rs.getString(i++));
				//Agenda de trabajo durante el evento
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
		} catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
		return req;
	}

	//Esta función se encarga de insertar los criterios de evaluación de la propuesta en la tabla eval_movilidad_aspectos(Agenda de cooperación)
	public boolean insertaCriterioB(Criterio criterio,int tipo, long eval,long ponencia) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		//k inicia en 43 porque los criterios de agenda de cooperación inician en 43 en la tabla aspecto_movilidad 
		int j=0, k=43;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("inserta_Aspectos"));
			//Este for almacena las calificaciones en la tabla eval_movilidad_aspectos
			//El for se especifica según el número de criterios que se evaluan en la agenda de cooperación (2012-4 criterios)
			//El for se especifica según el número de criterios que se evaluan en la agenda de cooperación (2012 Cohorte 02 -2 criterios)
			/*if(tipo==3)
				k=19;*/
			for(int i=k;i<k+2;i++){
				//Identificador de la propuesta
				ps.setLong(1, ponencia);
				//Identificador del criterio a evaluar
				ps.setInt(2, i);
				//Valor del criterio ingresado por el evaluador
				ps.setFloat(3,criterio.getValCri2()[j]);
				//Id del evaluador
				ps.setLong(4, eval);
				ps.addBatch();
				j++;
			}
			ps.executeBatch();
			cn.commit();
			retorno=true;
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

	//Presenta la información de un evento
	public InfoGeneral getInfoEvento(String nombreEvento){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InfoGeneral info=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("consultaIndividual"));
			ps.setString(1,nombreEvento);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				info=new InfoGeneral();
				info.setTipoLetra(rs.getString(i++));
				System.out.println("Tipo de Letra" + info.getTipoLetra());
				info.setPais(rs.getString(i++));
				info.setCiudad(rs.getString(i++));
				info.setInstitucion(rs.getString(i++));
				//Lista de Arbitraje del evento
				info.setLista_arbitraje(rs.getString(i++));
				info.setFechaInicio(rs.getString(i++));
				info.setFechaFin(rs.getString(i++));
				info.setNombrePonencia(rs.getString(i++));
				info.setNombreAutores(rs.getString(i++));
				info.setNombreEvento(rs.getString(i++));
				info.setPagEvento(rs.getString(i++));
				info.setTrayectoria(rs.getString(i++));
				info.setValorInsc(rs.getString(i++));
				info.setMonedaTxt(rs.getString(i++));
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

	public boolean insertaCriterioC(Criterio criterio,String evento) {
		System.out.println("En el metodo insertaCriterioC");
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		List<String> lista=new ArrayList<String>();
		Map<String,TrayectoriaInvest> mapaInscripciones=new HashMap<String,TrayectoriaInvest>();
		SortedMap<String,TrayectoriaInvest> mapaOrdenado=new TreeMap<String, TrayectoriaInvest>();
		Iterator ite=null;
	//	int []evaluadores={3414,3415,3416,3417,3418,3419};
		int j=0;
		try {
			//lista ponencias con ese nombre de evento
			//lista de evluadores
			long id=0;
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getPropuestasPorEvento"));
			ps.setString(1,"%"+evento+"%");
			/*** consultando el listado de inscripciones que tienen ese evento****/
			rs=ps.executeQuery();
			while (rs.next()) {
				lista.add(""+rs.getInt(1));
				mapaInscripciones.put(""+rs.getInt(1),getTrayectoria(""+rs.getInt(1)));
			}
			/**********************************************************************/
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ite=lista.iterator();
			ps=cn.prepareStatement(rb.getString("inserta_Aspectos"));
			while(ite.hasNext()){//recorriendo la lista de propuestas encontradas con el mismo evento
				j=0;
				id=Long.parseLong(""+ite.next());
				for(int k=3414;k<=3419;k++){//for para recorrer el listado de evaluadores 
					j=0;
					System.out.println("Valor de k "+k);
					for(int i=0;i<4;i++){//recorre la cantidad de aspectos a insertar
						System.out.println("Valor de i ***"+i);						
						ps.setLong(1, id);//id de la ponencia a evaluar
						ps.setInt(2, getIdAspecto(mapaInscripciones.get(""+id),i));//numero del criterio 
						//ps.setFloat(3,getValorCrit(mapaInscripciones.get(""+id),criterio.getValCri3()[j],i));//valor del criterio evaluado
						ps.setFloat(3,criterio.getValCri3()[j]);//valor del criterio evaluado
						ps.setLong(4, k);// se inserta inicamente el dato de evaluador DIRECTOR
						ps.addBatch();
						j++;
					}
				}
				ps.executeBatch();
			}
			cn.commit();
			retorno=true;
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

	public int getIdAspecto(TrayectoriaInvest invest,int id){
		int retorno=0;
/*		if(invest.getPapel()==1||invest.getPapel()==2){
			retorno=id+54;			
		}else{
			retorno=id+35;
		}
		
*/		retorno=id+54;
		System.out.println("Id del aspecto "+retorno);
		return retorno;		
	}
	
	
	
	/* calcular el valor del criterio a partir del papel del participante*/
	public int getValorCrit(TrayectoriaInvest infoInvest,float porcent, int numCriterio){
		int retorno=0;
		int maxPuntos=0;
		if(infoInvest.getPapel()==1||infoInvest.getPapel()==2){
			maxPuntos=getValorAspecto(numCriterio+39);			
		}else{
			maxPuntos=getValorAspecto(numCriterio+35);
		}
		System.out.println("Valor del retorno sin redondear************ "+((maxPuntos*porcent)/100));
		retorno=redondear((maxPuntos*porcent)/100);
		System.out.println("Valor del retorno ************ "+retorno);
		return retorno;
	}
	
	public int redondear(double numero) {
	      String val = numero+""; 
	      BigDecimal big = new BigDecimal(val);
	      big = big.setScale(0, RoundingMode.HALF_UP);
	      System.out.println("Número : "+big);
	      return big.intValue();
	}
	
	public int getValorAspecto(int numAspecto){
		int retorno=0;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{			
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getValAspecto"));
			ps.setInt(1,numAspecto);
			rs=ps.executeQuery();
		//	System.out.println("-->"+ps);
			while(rs.next()){
				retorno=rs.getInt(1);
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

	//Lista los eventos que se presentaron por año y corte
	public List<PropuestaMovilidad> getTotales(String ano,String corte){
		List<PropuestaMovilidad> lista=new ArrayList<PropuestaMovilidad>();
		SortedMap<String, PropuestaMovilidad> estudiantes=new TreeMap<String, PropuestaMovilidad>();
		SortedMap<String, PropuestaMovilidad> profesores=new TreeMap<String, PropuestaMovilidad>();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		PropuestaMovilidad propuestaOBJ=null;
		int cont=0;
		int i=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getPropuestas_Totales"));
			ps.setString(1,ano+"%");
			ps.setInt(2,Integer.parseInt(corte));
			ps.setInt(3,1);
			rs=ps.executeQuery();
		//	System.out.println("-->"+ps);
			while(rs.next()){
				i=1;
				cont=0;
				propuestaOBJ=new PropuestaMovilidad();
				propuestaOBJ.setIdPropuesta(rs.getLong(i++));
				propuestaOBJ.setEstado(rs.getInt(i++));
				propuestaOBJ.setInvestigador(rs.getString(i++));
				propuestaOBJ.setNombrePonencia(rs.getString(i++));
				propuestaOBJ.setPapel(rs.getInt(i++));
				if(ano.equals("2012")){
					propuestaOBJ.setTecnologica(calculoFacultad2(propuestaOBJ.getIdPropuesta(), 3414));//3414 967
					propuestaOBJ.setIngenieria(calculoFacultad2(propuestaOBJ.getIdPropuesta(), 3415));//3415 968
					propuestaOBJ.setMedioAmbiente(calculoFacultad2(propuestaOBJ.getIdPropuesta(), 3416));//3416 969
					propuestaOBJ.setEducacion(calculoFacultad2(propuestaOBJ.getIdPropuesta(), 3417));//3417 970
					propuestaOBJ.setArtes(calculoFacultad2(propuestaOBJ.getIdPropuesta(), 3418));//3418 971
					propuestaOBJ.setDirector(calculoFacultad2(propuestaOBJ.getIdPropuesta(), 3419));//3419 972
				}
				/*if(ano.equals("2012") && corte.equals("2")){
					propuestaOBJ.setTecnologica(calculoFacultad3(propuestaOBJ.getIdPropuesta(), 3414));//3414 967
					propuestaOBJ.setIngenieria(calculoFacultad3(propuestaOBJ.getIdPropuesta(), 3415));//3415 968
					propuestaOBJ.setMedioAmbiente(calculoFacultad3(propuestaOBJ.getIdPropuesta(), 3416));//3416 969
					propuestaOBJ.setEducacion(calculoFacultad3(propuestaOBJ.getIdPropuesta(), 3417));//3417 970
					propuestaOBJ.setArtes(calculoFacultad3(propuestaOBJ.getIdPropuesta(), 3418));//3418 971
					propuestaOBJ.setDirector(calculoFacultad3(propuestaOBJ.getIdPropuesta(), 3419));//3419 972					
				}*/
				else{
					propuestaOBJ.setTecnologica(calculoFacultad(propuestaOBJ.getIdPropuesta(), 3414,propuestaOBJ.getPapel()));//3414 967
					propuestaOBJ.setIngenieria(calculoFacultad(propuestaOBJ.getIdPropuesta(), 3415,propuestaOBJ.getPapel()));//3415 968
					propuestaOBJ.setMedioAmbiente(calculoFacultad(propuestaOBJ.getIdPropuesta(), 3416,propuestaOBJ.getPapel()));//3416 969
					propuestaOBJ.setEducacion(calculoFacultad(propuestaOBJ.getIdPropuesta(), 3417,propuestaOBJ.getPapel()));//3417 970
					propuestaOBJ.setArtes(calculoFacultad(propuestaOBJ.getIdPropuesta(), 3418,propuestaOBJ.getPapel()));//3418 971
					propuestaOBJ.setDirector(calculoFacultad(propuestaOBJ.getIdPropuesta(), 3419,propuestaOBJ.getPapel()));//3419 972
				}
				

				if(propuestaOBJ.getTecnologica()>0)
					cont++;
				if(propuestaOBJ.getIngenieria()>0)
					cont++;
				if(propuestaOBJ.getMedioAmbiente()>0)
					cont++;
				if(propuestaOBJ.getEducacion()>0)
					cont++;
				if(propuestaOBJ.getArtes()>0)
					cont++;
				if(propuestaOBJ.getDirector()>0)
					cont++;

				if(cont>0 && ano!="2012")
					propuestaOBJ.setTotal(global.formatearNumero((propuestaOBJ.getTecnologica()+propuestaOBJ.getIngenieria()+propuestaOBJ.getMedioAmbiente()+propuestaOBJ.getEducacion()+propuestaOBJ.getArtes()+propuestaOBJ.getDirector())/cont));
				if(cont>0 && ano.equals("2012"))
					propuestaOBJ.setTotal(global.formatearNumero((propuestaOBJ.getTecnologica()+propuestaOBJ.getIngenieria()+propuestaOBJ.getMedioAmbiente()+propuestaOBJ.getEducacion()+propuestaOBJ.getArtes()+propuestaOBJ.getDirector())));
				if(cont<0)
					propuestaOBJ.setTotal(global.formatearNumero(0));

				propuestaOBJ.setCantEval(cont);

				propuestaOBJ.setInfDocs(getDocumentos(propuestaOBJ.getIdPropuesta()));

				if(propuestaOBJ.getPapel()==1||propuestaOBJ.getPapel()==2)
					profesores.put(propuestaOBJ.getTotal()+propuestaOBJ.getIdPropuesta(),propuestaOBJ);
				else
					estudiantes.put(propuestaOBJ.getTotal()+propuestaOBJ.getIdPropuesta(),propuestaOBJ);
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
		/**/
		Iterator it1=profesores.keySet().iterator();
		Iterator it2=estudiantes.keySet().iterator();
		String key="";
		while(it1.hasNext()){
			key=(String)it1.next();
			lista.add(profesores.get(key));
		}
		
		while(it2.hasNext()){
			key=(String)it2.next();
			lista.add(estudiantes.get(key));
		}
		return lista;
	}	
	//Hace el conteo de los puntos por facultar
	public double calculoFacultad2(long prop, long eval){
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        double total=0;
        try {
            cn=cursor.getConnection(super.perfil);
            ps=cn.prepareStatement(rb.getString("getValores"));
            ps.setLong(1, prop);
            ps.setLong(2, eval);
            rs=ps.executeQuery();
            while (rs.next()) {
                total=rs.getDouble(1);
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
        System.out.println("retorno="+total);
        return total;
    }
	
	//Calculo Facultad para el segundo cohorte
	public double calculoFacultad3(long prop, long eval){
        Connection cn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        double total=0;
        try {
            cn=cursor.getConnection(super.perfil);
            ps=cn.prepareStatement(rb.getString("getValores"));
            ps.setLong(1, prop);
            ps.setLong(2, eval);
            rs=ps.executeQuery();
            while (rs.next()) {
                total=rs.getDouble(1);
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
        System.out.println("retorno="+total);
        return total;
    }

	public double calculoFacultad(long prop, long eval, int tipo){
	//	float valor=0;
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
	//	float []criA=null;
	//	System.out.println("el tipo es de categorï¿½a "+tipo);
	/*	if(tipo==3)
			criA=new float[7];
		else
			criA=new float[6];*/
		float []criB=new float[9];
	//	float []criC=new float[3];
		double crA=0,crB=0,crC=0,total=0;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getValor2"));
			ps.setLong(1, 1);
			ps.setLong(2, prop);
			ps.setLong(3, eval);
			rs=ps.executeQuery();
			int a=0;
			while (rs.next()) {
				crA=crA+rs.getFloat(1);
			//	System.out.println("crA "+crA);
			}

			ps=cn.prepareStatement(rb.getString("getValor2"));
			ps.setLong(1, 3);
			ps.setLong(2, prop);
			ps.setLong(3, eval);
			rs=ps.executeQuery();

			while (rs.next()) {
				crC=crC+rs.getFloat(1);
			//	System.out.println("crC "+crC);
			}

	//		System.out.println("----------->"+crA+"----"+crC);

			ps=cn.prepareStatement(rb.getString("getValor1"));
			ps.setLong(1, 2);
			ps.setLong(2, prop);
			ps.setLong(3, eval);
			rs=ps.executeQuery();
			a=0;
			while (rs.next()) {
				criB[a]=rs.getFloat(1);
			//	System.out.println("crB "+a+" "+criB[a]);
				if(criB[a]!=0)
					a++;
			}

			crB=(criB[0]+criB[1])*0.25;
			crB=crB+(criB[2]+criB[3]+criB[4])*0.3;
			crB=crB+(criB[5]+criB[6]+criB[7]+criB[8])*0.45;

	//		System.out.println("------------------>"+crB);

			total=(crA*0.5)+(crB*0.25)+(crC*0.25);

		}catch (SQLException e) {
			lanzaExcepcion(e);
		}catch (Exception e) {
			lanzaExcepcion(e);
		}finally{
			cerrar(rs);
			cerrar(ps);
			cerrar(cn);
		}
	//	System.out.println("retorno="+total);
		return total;
	}

	public boolean cambiaEstado(String ponencia,String estado, String observacion) {
		boolean retorno=false;
		Connection cn=null;
		PreparedStatement ps=null;
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			cn.setAutoCommit(false);
			ps=cn.prepareStatement(rb.getString("cambiaEstado"));
			ps.setInt(i++, Integer.parseInt(estado));
			ps.setString(i++, observacion);
			ps.setLong(i++, Long.parseLong(ponencia));
			ps.executeUpdate();

			i=1;
/*			ps=cn.prepareStatement(rb.getString("actualizaEstadoInforme"));
			ps.setInt(i++, Integer.parseInt(estado));
			ps.setLong(i++, Long.parseLong(ponencia));*/
			ps.executeUpdate();
			cn.commit();

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

	//Lista los eventos a evaluar.. 
	public List getListaEventos(String ano,String corte){
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		InfoEvento info=null;
		List lista=new ArrayList();
		int i=1;
		try {
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("listaEventos"));
			ps.setString(1,ano+"%");
			ps.setInt(2,Integer.parseInt(corte));
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				info=new InfoEvento();
				info.setNombreEvento(rs.getString(i++));
				info.setPagWeb(rs.getString(i++));
				info.setCant(rs.getInt(i++));
				lista.add(info);
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

	//Consulta los documentos de movilidad
	public TrayectoriaInvest getDocumentos(long idProp){
		TrayectoriaInvest trayect=new TrayectoriaInvest();
		Connection cn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		int i=1;
		String []documentos=new String[11];
		try {
			trayect.setIdPropuesta(idProp);
			cn=cursor.getConnection(super.perfil);
			ps=cn.prepareStatement(rb.getString("getDocumentos"));
			ps.setLong(1,idProp);
			rs=ps.executeQuery();
			while(rs.next()){
				i=1;
				trayect.setPapel(rs.getInt(i++));
				documentos[0]=rs.getString(i++);//aval grupo
				documentos[1]=rs.getString(i++);//aceptacion del evento
				documentos[2]=rs.getString(i++);//articulo completo
				documentos[3]=rs.getString(i++);//proyecto curricular
				documentos[4]=rs.getString(i++);//decanatura
				documentos[5]=rs.getString(i++);//consejo de facultad
				documentos[6]=rs.getString(i++);//excelencia academica
				documentos[7]=rs.getString(i++);//certificacion grupo
				documentos[8]=rs.getString(i++);//certificacion CIDC
				documentos[9]=rs.getString(i++);//resultados obtenidos
				documentos[10]=rs.getString(i++);//carta apoyo económico
				trayect.setListadDoc(documentos);
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
		return trayect;
	}
}