package cidc.general.pdf;

import java.io.FileNotFoundException;





import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.StringFormulaCell;
import jxl.Workbook;

import cidc.certificaciones.obj.CertificacionesOBJ;
import cidc.general.obj.Globales;
import cidc.proyectos.obj.BalanceGeneral;
import cidc.proyectosGeneral.*;
import cidc.general.db.CursorDB;
import cidc.general.login.Usuario;
import cidc.proyectosGeneral.obj.CoInvest;
import cidc.proyectosGeneral.obj.CompromisosOBJ;
import cidc.proyectosGeneral.obj.Proyecto;
import cidc.proyectosGeneral.obj.Rubros;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;


public class DocumentosPDF {
	
	int numeroPagina=0;
	char sep=java.io.File.separatorChar;
	boolean actaInicio=false;
	Globales global=new Globales();
	public ResourceBundle rb =ResourceBundle.getBundle("cidc.general.pdf.pdfContrato");
	public ResourceBundle rb1 =ResourceBundle.getBundle("cidc.general.pdf.pdfContratoEspecial");
	public ResourceBundle rbDir =ResourceBundle.getBundle("cidc.general.conect");
	public ResourceBundle rbcert =ResourceBundle.getBundle("cidc.general.pdf.pdfCertificaciones");
	String numContrato="";
	String numCertificado="";
	String nombreDirector="";
	String nombrePersona="";
	String convocatoria="";
	public PdfWriter writer=null;
	public Document document = new Document(new PageSize().LETTER,80f,60f,140f,80f);
	public Proyecto proyecto=null;
	private static String pathArchivo = "DocumentoGenerico.pdf";
	private static Font texto5 = new Font(Font.FontFamily.HELVETICA, 5,Font.NORMAL);
	private static Font texto7 = new Font(Font.FontFamily.HELVETICA, 7,Font.NORMAL);
	private static Font paginacion = new Font(Font.FontFamily.HELVETICA, 8,Font.BOLD);
	private static Font texto9 = new Font(Font.FontFamily.HELVETICA, 9,Font.NORMAL);
	private static Font texto9n = new Font(Font.FontFamily.HELVETICA, 9,Font.BOLD);
	private static Font texto8 = new Font(Font.FontFamily.HELVETICA, 8,Font.NORMAL);
	private static Font texto10 = new Font(Font.FontFamily.HELVETICA, 10,Font.NORMAL);
	private static Font texto10n = new Font(Font.FontFamily.HELVETICA, 10,Font.BOLD);
	private static Font texto10s = new Font(Font.FontFamily.HELVETICA, 10,Font.UNDERLINE);
//	private static Font texto11 = new Font(Font.FontFamily.HELVETICA, 11,Font.NORMAL);
	private static Font texto11n = new Font(Font.FontFamily.HELVETICA, 11,Font.BOLD);
	private static Font texto15Blanca = new Font(Font.FontFamily.HELVETICA, 10,Font.BOLD, BaseColor.WHITE);
//	private static Font texto12n = new Font(Font.FontFamily.HELVETICA, 12,Font.BOLD);
	//private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
	

	public static void main(String[] args) {
		DocumentosPDF claseContrato=new DocumentosPDF();
		claseContrato.crearContrato(new Proyecto(),"D://Contrato.pdf");		
	}

	public void crearActaInicio(Proyecto proyecto, String path){
		actaInicio=true;
		pathArchivo=path;
		Phrase []textoDocumento=new Phrase [1];
		Phrase texto=new Phrase();
		PdfPTable tablaDatos =new PdfPTable(2);
		PdfPTable tablaFirmas =new PdfPTable(2);
		
		try {
			tablaFirmas.setWidths(new int[]{250,250});
			tablaFirmas.setTotalWidth(550);
			tablaFirmas.setLockedWidth(true);
			tablaFirmas.getDefaultCell().setFixedHeight(10);

			tablaDatos.setWidths(new int[]{150,350});
			tablaDatos.setTotalWidth(480);
			tablaDatos.setLockedWidth(true);
			tablaDatos.getDefaultCell().setFixedHeight(10);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		tablaDatos.addCell(new PdfPCell(new Phrase("COMPROMISO DE INVESTIGACIÓN No°",texto9n)));
/**/	tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getConsecContrato()+"-"+global.getAnoFecha(proyecto.getFecContrato()),texto8)));
		//tablaDatos.addCell(new PdfPCell(new Phrase("TIPO DE CONTRATO",texto9n)));
		//tablaDatos.addCell(new PdfPCell(new Phrase("RECUPERACION CONTINGENTE",texto8)));
		tablaDatos.addCell(new PdfPCell(new Phrase("OBJETO",texto9n)));

		Phrase objeto=new Phrase();
		objeto.add(new Phrase(rb.getString("ai0"),texto8));
/****/	objeto.add(new Phrase(" seleccionado en la convocatoria N° ",texto8));
/****/	objeto.add(new Phrase(proyecto.getNumConvocatoria(),texto8));
/****/	objeto.add(new Phrase(", clasificado con el código "+proyecto.getCodigo(),texto8));
/****/	objeto.add(new Phrase(", aprobado segun Acta N° "+proyecto.getSesion()+" de "+global.getAnoFecha(proyecto.getFecActa()),texto8));
/****///	objeto.add(new Phrase(" 01 del 20 de noviembre de 2011 ",texto8));
		objeto.add(new Phrase(" del Comité de Investigaciones y presentado por el Grupo de Investigación ",texto8));
/****/	objeto.add(new Phrase("'"+proyecto.getGrupoInvestigacion().trim().toLowerCase()+"'",texto8));
		PdfPCell p=new PdfPCell(objeto);
		p.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
		tablaDatos.addCell(new PdfPCell(p));

		tablaDatos.addCell(new PdfPCell(new Phrase("NOMBRE DEL PROYECTO",texto9n)));
/**/	tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getProyecto().trim().toLowerCase(),texto8)));
		tablaDatos.addCell(new PdfPCell(new Phrase("VALOR",texto9n)));
/**/	tablaDatos.addCell(new PdfPCell(new Phrase(" "+proyecto.getValor()+" pesos M/CTE",texto8)));
		if(proyecto.getNumConvocatoria().contains("12-2011")){
			tablaDatos.addCell(new PdfPCell(new Phrase("TUTOR(A) DEL  PROYECTO",texto9n)));
			tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getTutor()+"",texto8)));
		}
		else{
			if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012"))){
				tablaDatos.addCell(new PdfPCell(new Phrase("DIRECTOR(A) DEL  PROYECTO DE GRADO",texto9n)));
				tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getDirector()+"",texto8)));
			}
			if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012")||proyecto.getNumConvocatoria().contains("11-2012"))){
				tablaDatos.addCell(new PdfPCell(new Phrase("INVESTIGADOR(A) PRINCIPAL",texto9n)));
				tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getDirector()+"",texto8)));
			}
			
		}		
		if(proyecto.isGestorfinanciero()){
			tablaDatos.addCell(new PdfPCell(new Phrase("REALIZADOR(A) DEL PROYECTO",texto9n)));
			tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getRealizador(),texto8)));
			tablaDatos.addCell(new PdfPCell(new Phrase("GESTOR(A) FINANCIERO",texto9n)));
			tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getGestor(),texto8)));			
		}

		tablaDatos.addCell(new PdfPCell(new Phrase("DURACION",texto9n)));
/**/	tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getDuracion()+" Meses",texto8)));
		if (proyecto.getListaCoInvestigadores().size()!=0 && proyecto.getListaCoInvestigadores()!=null){
			if(proyecto.isGestorfinanciero() && proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012"))
				tablaDatos.addCell(new PdfPCell(new Phrase("REALIZADOR(A)(ES)",texto9n)));
			else
				tablaDatos.addCell(new PdfPCell(new Phrase("INVESTIGADOR(A)(ES)/ASOCIADO",texto9n)));

			CoInvest coinvest=null;
			int j=9;
			String nombres_concatenados="";
			for(int i=0;i<proyecto.getListaCoInvestigadores().size();i++){			
				//proyecto=(Proyecto)itEstudiante.next();
				coinvest=proyecto.getListaCoInvestigadores().get(i);
				//System.out.println("Ingreso al recorrido de los estudiantes"+clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10)));			
				nombres_concatenados=nombres_concatenados+coinvest.getNombre().toUpperCase()+" "+coinvest.getApellido().toUpperCase()+".\n";						
				//clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10));
				j++;
				//proyecto.getCedulaDirDe()+" "+
			}			
			tablaDatos.addCell(new PdfPCell(new Phrase(nombres_concatenados,texto10)));			
		}
		
		tablaDatos.addCell(new PdfPCell(new Phrase("FECHA DE INICIACIÓN ",texto9n)));
/**/	tablaDatos.addCell(new PdfPCell(new Phrase(proyecto.getFecActaInicio(),texto8)));
		tablaDatos.addCell(new PdfPCell(new Phrase("FECHA DE TERMINACION",texto9n)));
		//String fecha=global.sumarMesesFecha(proyecto.getFecActaInicio(), Integer.parseInt(proyecto.getDuracion()));
		//int dia=Integer.parseInt(fecha);		
/**/	tablaDatos.addCell(new PdfPCell(new Phrase(global.sumarMesesFecha(proyecto.getFecActaInicio(), Integer.parseInt(proyecto.getDuracion()))+"",texto8)));
		tablaDatos.addCell(new PdfPCell(new Phrase("SUPERVISOR",texto9n)));
		tablaDatos.addCell(new PdfPCell(new Phrase("Director del Centro de Investigaciones y Desarrollo Científico de la Universidad Distrital.",texto8)));

/****/	texto.add(new Phrase("En Bogotá D.C., a los "+global.getDiaFecha(proyecto.getFecActaInicio(), 0)+" días del mes de "+global.getNombreMes(proyecto.getFecActaInicio(), 1)+" del año "+global.getAnoFecha(proyecto.getFecActaInicio())+ " se reunieron, el(la) docente ",texto9));


		if(proyecto.getNumConvocatoria().contains("12-2012")){
			/** si es de semillero */
			//String cedTut=(proyecto.getCedulaTutor()==null?proyecto.getCedulaDir():proyecto.getCedulaTutor()); //si la cedula del tutor esta vacia entonces se usa la cedula del director
			//String cedTutDe=(proyecto.getCedulaTutorDe()==null?proyecto.getCedulaDirDe():proyecto.getCedulaTutorDe()); // si el lugar de expedicion del tutor esta vacio se usa el del director
			texto.add(new Phrase(proyecto.getTutor().toUpperCase()+" identificado con cédula de ciudadanía N° "+proyecto.getCedulaTutor()+" expedida en "+proyecto.getCedulaTutorDe(),texto9));
			texto.add(new Phrase(" en calidad de Tutor del Proyecto; El estudiante ",texto9));
	/****/	texto.add(new Phrase(proyecto.getDirector().toLowerCase()+" identificado con cédula de ciudadanía N° "+proyecto.getCedulaDir()+" expedida en "+proyecto.getCedulaDirDe(),texto9));
			texto.add(new Phrase(" en calidad de Investigador Principal del proyecto de investigación ",texto9));
		//***************************		
		}
		if(proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012")||proyecto.getNumConvocatoria().contains("11-2012")){
			/** Otros Proyectos*/			
	/****/	texto.add(new Phrase(proyecto.getDirector().toLowerCase()+" identificado con cédula de ciudadanía N° "+proyecto.getCedulaDir()+" expedida en "+proyecto.getCedulaDirDe(),texto9));
			texto.add(new Phrase(" en calidad de Investigador Principal del proyecto de investigación ",texto9));
		//***************************		
		}

		
		if(proyecto.isGestorfinanciero() && (proyecto.getNumConvocatoria().contains("5-2012") ||proyecto.getNumConvocatoria().contains("6-2012"))){
			texto.add(new Phrase(" en calidad de Director del trabajo de grado; el realizador del proyecto, el Gestor Financiero ",texto9));
		}		
		texto.add(new Phrase(rb.getString("ai1"),texto9));
/****/	texto.add(new Phrase(" "+proyecto.getNumConvocatoria(),texto9));
		texto.add(new Phrase(" "+rb.getString("ai2"),texto9));
/****/	texto.add(new Phrase(" "+proyecto.getSesion()+" de "+global.getAnoFecha(proyecto.getFecAprobacion()),texto9));
		texto.add(new Phrase(" "+rb.getString("ai3"),texto9));
		String fechaFinal=global.sumarMesesFecha(proyecto.getFecActaInicio(), Integer.parseInt(proyecto.getDuracion()));
		//System.out.println("-final->"+fechaFinal);
		/****/	texto.add(new Phrase(" "+global.getDiaFecha(fechaFinal,0)+" del més de "+global.getNombreMes(fechaFinal, 1)+" del año "+global.getAnoFecha(fechaFinal)+".",texto9));
/****/  //texto.add(new Phrase(" "+global.getDiaFecha(proyecto.getFecActaInicio(), 1)+" dÃ­as del mÃ©s de "+global.getNombreMes(proyecto.getFecActaInicio(), 1)+" del aÃ±o "+global.getAnoFecha(proyecto.getFecActaInicio()),texto9));

		textoDocumento[0]=texto;

		CoInvest tutor=null;
		if(proyecto.getListaCoInvestigadores()!=null){
			if(proyecto.getListaCoInvestigadores().size()>0)
				tutor=(CoInvest)proyecto.getListaCoInvestigadores().get(proyecto.getPrincipal());
			else{
				tutor=new CoInvest();
				tutor.setNombre(proyecto.getDirector());
			}
		}
		PdfPCell ralla1=new PdfPCell(new Phrase("_____________________________ ",texto10n));
		PdfPCell ralla2=new PdfPCell(new Phrase("_____________________________ ",texto10n));
		PdfPCell ralla3=new PdfPCell(new Phrase("_____________________________ ",texto10n));
		PdfPCell celdaVacia=new PdfPCell(new Phrase());
		
		PdfPCell directorCIDC=new PdfPCell(new Phrase(rbDir.getString("directorCIDC").toUpperCase(),texto10n));
		
		
		PdfPCell gestorFinanciero=new PdfPCell();
		PdfPCell principalProyecto=new PdfPCell(new Phrase(proyecto.getDirector().toUpperCase(),texto10n));
		if(proyecto.isGestorfinanciero()){			
			gestorFinanciero=new PdfPCell(new Phrase(proyecto.getGestor().toUpperCase(),texto10n));			
		}
		
		PdfPCell tituloCIDC=new PdfPCell(new Phrase("Director Centro de Investigaciones \ny Desarrollo Científico",texto10));		
		String titulo="";
		if(proyecto.isGestorfinanciero() || proyecto.getNumConvocatoria().contains("5-2012") ||proyecto.getNumConvocatoria().contains("6-2012"))		
			titulo="Director Proyecto de Grado";
		if(proyecto.getNumConvocatoria().contains("9-2012") ||proyecto.getNumConvocatoria().contains("10-2012")||proyecto.getNumConvocatoria().contains("11-2012"))		
			titulo="Director del Proyecto de Grado";
		if(proyecto.getNumConvocatoria().contains("12-2012"))		
			titulo="Investigador Principal";
		else
			titulo="Investigador Principal";
		PdfPCell tituloPrincipal=new PdfPCell(new Phrase(titulo+"",texto10));
		PdfPCell tituloGestor=new PdfPCell(new Phrase("Gestor Financiero",texto10));
		
		
/*		PdfPCell tituloTutor=new PdfPCell(new Phrase("Tutor Proyecto InvestigaciÃ³n",texto10));
		PdfPCell tutorProyecto=new PdfPCell(new Phrase(tutor.getNombre().toUpperCase(),texto10n));*/
		
/*****/ 
/*****/ 
		
		celdaVacia.setBorder(Rectangle.NO_BORDER);
		celdaVacia.setFixedHeight(10);
		celdaVacia.setColspan(2);

		ralla1.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla1.setBorder(Rectangle.NO_BORDER);
		
		ralla2.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla2.setBorder(Rectangle.NO_BORDER);
		
		ralla3.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla3.setBorder(Rectangle.NO_BORDER);		
		
		directorCIDC.setHorizontalAlignment(Element.ALIGN_CENTER);
		directorCIDC.setBorder(Rectangle.NO_BORDER);
		
		gestorFinanciero.setHorizontalAlignment(Element.ALIGN_CENTER);
		gestorFinanciero.setBorder(Rectangle.NO_BORDER);	

		
		tituloCIDC.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloCIDC.setBorder(Rectangle.NO_BORDER);
		
		tituloGestor.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloGestor.setBorder(Rectangle.NO_BORDER);
		

		tituloPrincipal.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloPrincipal.setBorder(Rectangle.NO_BORDER);
		
		principalProyecto.setHorizontalAlignment(Element.ALIGN_CENTER);
		principalProyecto.setBorder(Rectangle.NO_BORDER);

		
		if(proyecto.getNumConvocatoria().contains("6-2011")){
			principalProyecto.setColspan(2);
			tituloPrincipal.setColspan(2);
		}
		
/*		tituloTutor.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloTutor.setBorder(Rectangle.NO_BORDER);
		tutorProyecto.setHorizontalAlignment(Element.ALIGN_CENTER);
		tutorProyecto.setBorder(Rectangle.NO_BORDER);	*/	
		
		
		tablaFirmas.addCell(ralla1);
		tablaFirmas.addCell(ralla2);
		tablaFirmas.addCell(directorCIDC);
		tablaFirmas.addCell(principalProyecto);
		tablaFirmas.addCell(tituloCIDC);
		tablaFirmas.addCell(tituloPrincipal);
		
		tablaFirmas.addCell(celdaVacia);
        tablaFirmas.addCell(celdaVacia);
				
		if(proyecto.getNumConvocatoria().contains("12-2012")){
        	PdfPCell ralla=new PdfPCell(new Phrase("_________________________________ ",texto10n));
        	PdfPCell Tutor=new PdfPCell(new Phrase(proyecto.getTutor().toUpperCase()+"\nDocente Tutor(a)",texto10n));
        	//PdfPCell Tutor=new PdfPCell(new Phrase(proyecto.getTutor().toUpperCase(),texto10n));
        	PdfPCell tituloTutor=new PdfPCell(new Phrase("Docente Tutor(a)",texto10));

        	ralla.setHorizontalAlignment(Element.ALIGN_CENTER);
    		ralla.setBorder(Rectangle.NO_BORDER);	
    		Tutor.setHorizontalAlignment(Element.ALIGN_CENTER);
    		Tutor.setBorder(Rectangle.NO_BORDER);
    		tituloTutor.setHorizontalAlignment(Element.ALIGN_CENTER);
    		tituloTutor.setBorder(Rectangle.NO_BORDER);
    		celdaVacia.setHorizontalAlignment(Element.ALIGN_CENTER);
    		celdaVacia.setBorder(Rectangle.NO_BORDER);

    		tablaFirmas.addCell(celdaVacia);
    		tablaFirmas.addCell(celdaVacia);
    		tablaFirmas.addCell(ralla);    
    		tablaFirmas.addCell(celdaVacia);
    		tablaFirmas.addCell(Tutor); 		
    		tablaFirmas.addCell(celdaVacia);
    		tablaFirmas.addCell(tituloTutor);  	

        }
		
		try {			
			inicarDocumento();
			Rectangle rectangulo=this.writer.getPageSize();
			PdfPTable tablaPagPie=new PdfPTable(1);
			tablaPagPie.setWidths(new int[]{500});
			tablaPagPie.setTotalWidth(500);
			tablaPagPie.setLockedWidth(true);
			tablaPagPie.getDefaultCell().setFixedHeight(10);
			PdfPCell infoCopias=new PdfPCell(new Phrase("Original - Universidad Distrital - CIDC\nCopia Investigador",texto7));
			infoCopias.setBorder(Rectangle.NO_BORDER);
			infoCopias.setHorizontalAlignment(Element.ALIGN_LEFT);
			tablaPagPie.addCell(infoCopias);
			infoCopias=new PdfPCell(new Phrase("Página 1 de 1",texto7));
			infoCopias.setBorder(Rectangle.NO_BORDER);
			infoCopias.setHorizontalAlignment(Element.ALIGN_LEFT);
			tablaPagPie.addCell(infoCopias);
			tablaPagPie.writeSelectedRows(0, 5, (rectangulo.getLeft()+rectangulo.getRight())/10, 60 , writer.getDirectContent());
			agregarContenido(tablaDatos, textoDocumento,tablaFirmas);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-Acta de inicio Caso Especial Creada->");
	}
	
	public void crearContrato(Proyecto proyecto,String path){	
		
		actaInicio=false;
		pathArchivo=path;
		numContrato=proyecto.getConsecContrato()+" de "+global.getAnoHoy();
		nombreDirector=proyecto.getDirector().toUpperCase();
		convocatoria=proyecto.getNumConvocatoria();
		Phrase []textoDocumento=new Phrase [20];
		Phrase clausulas=new Phrase();
		
		textoDocumento[0]=new Phrase(rb.getString("ci1"),texto10);
		textoDocumento[1]=new Phrase(" "+rbDir.getString("directorCIDC"),texto10n);
		textoDocumento[2]=new Phrase(", con cédula de ciudadanía  "+ rbDir.getString("cedDirectorCIDC"),texto10);
		textoDocumento[3]=new Phrase(" "+ rb.getString("ci2"),texto10);
		String textoaux="";
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012")||proyecto.getNumConvocatoria().contains("11-2012")))
			textoaux=rb.getString("ci213");		
		else
			textoaux=rb1.getString("ci213");
		textoDocumento[4]=new Phrase(" "+rbDir.getString("resolucionNombramiento")+"  "+textoaux,texto10);
/****/	textoDocumento[5]=new Phrase(proyecto.getDirector().toUpperCase(),texto10n);
/****/	textoDocumento[6]=new Phrase(", identificado con cédula de ciudadanía "+ proyecto.getCedulaDir(),texto10);
/****/	textoDocumento[7]=new Phrase(" expedida en "+proyecto.getCedulaDirDe(),texto10);
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
			textoDocumento[8]=new Phrase(", "+rb.getString("ci3"),texto10);
		else 
			textoDocumento[8]=new Phrase(", "+rb.getString("cin3"),texto10);
		
		if((proyecto.getNumConvocatoria().contains("12-2012"))){
			clausulas.add(new Phrase(" y el (la) docente "+proyecto.getTutor()+" ",texto10));
			clausulas.add(new Phrase(" "+rb.getString("cin44"),texto10));
		}
					
		//IMPRIMIR LISTA DE ESTUDIANTES

		CoInvest coinvest=null;
		int j=9;
		String texto="";
		if(proyecto.getListaCoInvestigadores().size()!=0)
			texto=", y el (los) (la) (las) estudiante";		
		
		clausulas.add(new Phrase(texto,texto10));
		if(proyecto.getListaCoInvestigadores().size()!=0){
			for(int i=0;i<proyecto.getListaCoInvestigadores().size();i++){			
				//proyecto=(Proyecto)itEstudiante.next();
				coinvest=proyecto.getListaCoInvestigadores().get(i);
				//System.out.println("Ingreso al recorrido de los estudiantes"+clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10)));
				clausulas.add(new Phrase(" "+coinvest.getNombre().toUpperCase()+" "+coinvest.getApellido().toUpperCase()+" adscrito(s) al programa"+"  "+coinvest.getProyectocnombre()+"  "+"con código estudiantil"+"  "+ coinvest.getCodigo()+", ",texto10));
				//clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10));
				j++;
				//proyecto.getCedulaDirDe()+" "+
			}
		}
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
			clausulas.add(new Phrase(rb.getString("ci38"),texto10));				
		clausulas.add(new Phrase(rb.getString("cin38"),texto10));
		clausulas.add(new Phrase(" CLAUSULA PRIMERA.- ",texto10n));
		clausulas.add(new Phrase("Objeto: ",texto10s));
		clausulas.add(new Phrase(", "+rb.getString("ci4")+" ",texto10));
/****/	clausulas.add(new Phrase(" "+proyecto.getProyecto().trim().toUpperCase(),texto10n));
		clausulas.add(new Phrase(" representado y dirigido  por ",texto10));
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
			textoDocumento[9]=new Phrase(rb.getString("cin4"),texto10);
		else 
			textoDocumento[9]=new Phrase(rb.getString("cin5"),texto10);		
		
		clausulas.add(new Phrase(proyecto.getDirector().toUpperCase(),texto10n));
/****/	clausulas.add(new Phrase(" seleccionado en la  convocatoria  N°",texto10));
/****/	clausulas.add(new Phrase(proyecto.getNumConvocatoria(),texto10));
/****/	clausulas.add(new Phrase(", clasificado con el código "+proyecto.getCodigo(),texto10));
/****/	clausulas.add(new Phrase(", aprobado segun Acta N° "+proyecto.getSesion(),texto10));
/****/	clausulas.add(new Phrase(" de "+global.getNombreMes(proyecto.getFecActa(), 1)+" "+"del "+global.getDiaFecha(proyecto.getFecActa(), 2),texto10));

		clausulas.add(new Phrase(" del Comité de Investigaciones y presentado por el Grupo o Semillero de Investigación ",texto10));
/****/	clausulas.add(new Phrase(" '"+proyecto.getGrupoInvestigacion()+"'. ",texto10n));
		clausulas.add(new Phrase(" CLAUSULA SEGUNDA.- ",texto10n));
		clausulas.add(new Phrase("Obligaciones de las partes ",texto10s));
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
			clausulas.add(new Phrase(rb.getString("cin11"),texto10n));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012")))
			clausulas.add(new Phrase(rb.getString("cin12"),texto10n));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(rb.getString("cin13"),texto10n));
		
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
			clausulas.add(new Phrase(", Por una parte el (la) "+rb.getString("tipo2")+" "+rb.getString("ci5")+ " "+rb.getString("tipo2")+" "+rb.getString("cinn5"),texto10));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012")||proyecto.getNumConvocatoria().contains("11-2012")||proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(", Por una parte el (la) "+rb.getString("tipo1")+rb.getString("ci5")+" "+rb.getString("tipo1")+" "+rb.getString("cinn5"),texto10));


		//COMPROMISOS DE LOS INVESTIGADORES
///****/	clausulas.add(new Phrase(proyecto.getCompromisosConv(),texto10));
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
			clausulas.add(new Phrase(rb.getString("cinn6"),texto10));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(rb.getString("cinn61"),texto10));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012") ||proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(rb.getString("cinn62"),texto10));
				
		CompromisosOBJ compromisos=null;
			
		for(int i=0;i<proyecto.getListaCompromisos().size();i++){			
			//proyecto=(Proyecto)itEstudiante.next();
			compromisos=proyecto.getListaCompromisos().get(i);
			//System.out.println("Ingreso al recorrido de los estudiantes"+clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10)));
			clausulas.add(new Phrase((i+1)+") "+compromisos.getNombre()+", ",texto10));
			//clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10));

		}	
						
		
		clausulas.add(new Phrase(" de conformidad con la propuesta presentada.",texto10));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(rb.getString("cinn6234"),texto10));		
		clausulas.add(new Phrase(" 2. LA UNIVERSIDAD",texto10n));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(", "+rb.getString("ci6123"),texto10));
		else 
			clausulas.add(new Phrase(", "+rb.getString("ci6"),texto10));		
		clausulas.add(new Phrase(" CLAUSULA TERCERA.- ",texto10n));
		clausulas.add(new Phrase("Duración:",texto10s));
		clausulas.add(new Phrase(" La duración total del proyecto es de ",texto10));
/****/	clausulas.add(new Phrase(proyecto.getDuracionLetras()+"("+proyecto.getDuracion()+") Meses ",texto10));
		clausulas.add(new Phrase(", "+rb.getString("ci61"),texto10));		
		clausulas.add(new Phrase(" CLAUSULA CUARTA.- ",texto10n));
		clausulas.add(new Phrase("Productividad: ",texto10s));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012") ||proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(" "+rb.getString("cc412"),texto10));
		else 
			clausulas.add(new Phrase(" "+rb.getString("cc4"),texto10));
		clausulas.add(new Phrase(" CLAUSULA QUINTA.- ",texto10n));
		clausulas.add(new Phrase("Valor de la financiación: ",texto10s));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012") ||proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(" "+rb.getString("c5co"),texto10));
		else 
			clausulas.add(new Phrase(" "+rb.getString("c5c"),texto10));		
/****/	clausulas.add(new Phrase(proyecto.getValorLetras().toUpperCase(),texto10));
/****/	clausulas.add(new Phrase(" ("+proyecto.getValor()+") M/CTE",texto10));		
		clausulas.add(new Phrase(", "+rb.getString("ci11"),texto10));
		clausulas.add(new Phrase(" Parágrafo 1. ",texto10n));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012")))		
			clausulas.add(new Phrase(" "+rb.getString("ci12ot"),texto10));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(" "+rb.getString("ci12otro"),texto10));
		else 
			clausulas.add(new Phrase(" "+rb.getString("ci12"),texto10));
		clausulas.add(new Phrase(" CLAUSULA SEXTA.- ",texto10n));
		clausulas.add(new Phrase("Documentos del Compromiso Apoyo Económico y Administración de Recursos de Investigación: ",texto10s));
		clausulas.add(new Phrase(" "+rb.getString("ci13"),texto10));
		clausulas.add(new Phrase(" PARAGRAFO: ",texto10n));
		clausulas.add(new Phrase(" "+rb.getString("ci14"),texto10));
		
		clausulas.add(new Phrase(" CLAUSULA SEPTIMA.- ",texto10n));
		clausulas.add(new Phrase("Acta de cierre y liquidación de compromisos: ",texto10s));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012")))		
			clausulas.add(new Phrase(" "+rb.getString("ci1510"),texto10));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(" "+rb.getString("ci1512"),texto10));
		else 
			clausulas.add(new Phrase(" "+rb.getString("ci15"),texto10));		
		clausulas.add(new Phrase(" CLAUSULA OCTAVA.- ",texto10n));		
		clausulas.add(new Phrase("Recuperación Contingente : ",texto10s));		
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(rb.getString("ci1612"),texto10));
		else
			clausulas.add(new Phrase(" "+rb.getString("ci16"),texto10));
		clausulas.add(new Phrase(" CLAUSULA NOVENA.- ",texto10n));
		clausulas.add(new Phrase("Mérito Ejecutivo: ",texto10s));
		clausulas.add(new Phrase(" "+rb.getString("ci17"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMA.- ",texto10n));
		clausulas.add(new Phrase("Supervisión: ",texto10s));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012")))		
			clausulas.add(new Phrase(" "+rb.getString("ci1810"),texto10));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(" "+rb.getString("ci1812"),texto10));
		else 
			clausulas.add(new Phrase(" "+rb.getString("ci18"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO PRIMERA.- ",texto10n));		
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
			clausulas.add(new Phrase("El (la) " + rb.getString("tipo2")+rb.getString("ci19"),texto10));		
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012")))
			clausulas.add(new Phrase("El (la) " + rb.getString("tipo1")+rb.getString("ci19"),texto10));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(" "+rb.getString("c1112"),texto10));
		clausulas.add(new Phrase(" PARÁGRAFO. ",texto10n));
		clausulas.add(new Phrase(" "+rb.getString("ci20"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO SEGUNDA.- ",texto10n));
		clausulas.add(new Phrase(" Erogaciones. ",texto10s));
		clausulas.add(new Phrase(" Toda erogación deberá soportarse en Disponibilidad y Registro presupuestal.",texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO TERCERA.- ",texto10n));
		clausulas.add(new Phrase("Divulgación de las fuentes de financiación: ",texto10s));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012")||proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(" "+rb.getString("c1312"),texto10));
		else
			clausulas.add(new Phrase(" "+rb.getString("ci21"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO CUARTA.- ",texto10n));
		clausulas.add(new Phrase("Requisitos de perfeccionamiento y ejecución del compromiso: ",texto10s));
		clausulas.add(new Phrase(" "+rb.getString("ci22"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO QUINTA.- ",texto10n));
		clausulas.add(new Phrase("Propiedad intelectual: ",texto10s));
		clausulas.add(new Phrase(" "+rb.getString("ci23"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO SEXTA.- ",texto10n));
		clausulas.add(new Phrase("Invenciones y otras Creaciones: ",texto10s));
		clausulas.add(new Phrase(" "+rb.getString("ci24"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO SEPTIMA.- ",texto10n));
		clausulas.add(new Phrase("Regalías: ",texto10s));
		if((proyecto.getNumConvocatoria().contains("12-2012")||proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012")||proyecto.getNumConvocatoria().contains("11-2012")))
			clausulas.add(new Phrase(" "+rb.getString("cinn25"),texto10));
		else
			clausulas.add(new Phrase(" "+rb.getString("ci25"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO OCTAVA.- ",texto10n));
		clausulas.add(new Phrase("Constancia escrita: ",texto10s));
		if((proyecto.getNumConvocatoria().contains("12-2012")||proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012")||proyecto.getNumConvocatoria().contains("11-2012")))
			clausulas.add(new Phrase(" "+rb.getString("ci2612"),texto10));
		else
			clausulas.add(new Phrase(" "+rb.getString("ci26"),texto10));		
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO NOVENA.- ",texto10n));
		clausulas.add(new Phrase("Régimen Convencional: ",texto10s));
		
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
			clausulas.add(new Phrase(" "+ rb.getString("cin27")+rb.getString("ci27"),texto10));
		if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012")))
			clausulas.add(new Phrase(" "+ rb.getString("cin30")+rb.getString("ci27"),texto10));
		if((proyecto.getNumConvocatoria().contains("12-2012")))
			clausulas.add(new Phrase(" "+ rb.getString("cin29")+rb.getString("ci27"),texto10));
		
/****/	clausulas.add(new Phrase(" "+global.getDiaHoy()+" días del mes de "+global.getNombreMesHoy()+" de "+global.getAnoHoy()+".",texto10));
		
		textoDocumento[9]=clausulas;

		PdfPTable tablaFirmas =new PdfPTable(2);
		
		try {
			tablaFirmas.setWidths(new int[]{200,200});
			tablaFirmas.setTotalWidth(450);
			tablaFirmas.setLockedWidth(true);
			tablaFirmas.getDefaultCell().setFixedHeight(10);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PdfPCell ralla1=new PdfPCell(new Phrase("_________________________________ ",texto10n));
		PdfPCell ralla2=new PdfPCell(new Phrase("_________________________________ ",texto10n));
		PdfPCell directorCIDC=new PdfPCell(new Phrase(rbDir.getString("directorCIDC").toUpperCase(),texto10n));
		PdfPCell directorProyecto=new PdfPCell(new Phrase(proyecto.getDirector().toUpperCase(),texto10n));
		PdfPCell tituloCIDC=new PdfPCell(new Phrase("Director Centro de Investigaciones",texto10));
		PdfPCell tituloDirector=new PdfPCell();
		if((proyecto.getNumConvocatoria().contains("6-2012")||proyecto.getNumConvocatoria().contains("5-2012")))
				tituloDirector=new PdfPCell(new Phrase("Director(a) Proyecto de Grado",texto10));
		else 
			if((proyecto.getNumConvocatoria().contains("9-2012")||proyecto.getNumConvocatoria().contains("10-2012") ||proyecto.getNumConvocatoria().contains("11-2012")))
				tituloDirector=new PdfPCell(new Phrase("Director(a) del Proyecto de Investigación",texto10));
			if((proyecto.getNumConvocatoria().contains("12-2012")))
				tituloDirector=new PdfPCell(new Phrase("Investigador(a) Principal",texto10));

		ralla1.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla1.setBorder(Rectangle.NO_BORDER);		
		ralla2.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla2.setBorder(Rectangle.NO_BORDER);
		
		directorCIDC.setHorizontalAlignment(Element.ALIGN_CENTER);
		directorCIDC.setBorder(Rectangle.NO_BORDER);		
		directorProyecto.setHorizontalAlignment(Element.ALIGN_CENTER);
		directorProyecto.setBorder(Rectangle.NO_BORDER);

		tituloCIDC.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloCIDC.setBorder(Rectangle.NO_BORDER);		
		tituloDirector.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloDirector.setBorder(Rectangle.NO_BORDER);
		
		tablaFirmas.addCell(ralla1);
		tablaFirmas.addCell(ralla2);
		tablaFirmas.addCell(directorCIDC);
		tablaFirmas.addCell(directorProyecto);
		tablaFirmas.addCell(tituloCIDC);
		tablaFirmas.addCell(tituloDirector);
		
		//*********************************+
		
		coinvest=null;
        PdfPCell rallaGenerica=null;
        PdfPTable tablaGenerica=null;        
        PdfPCell nombreGenerico=new PdfPCell(new Phrase(proyecto.getDirector().toUpperCase(),texto10n));
        PdfPCell tituloGenerico=new PdfPCell(new Phrase("Realizador",texto10n));
        
        
        PdfPTable tablaBlanca =new PdfPTable(1);
        PdfPCell celdaVacia=new PdfPCell(new Phrase("\n....\n",texto15Blanca));
        PdfPCell celdatablaVacia=new PdfPCell();
        
        celdaVacia.setBorder(Rectangle.NO_BORDER);
        tablaBlanca.addCell(celdaVacia);
        PdfPCell celdaGenerica=new PdfPCell();
        PdfPCell celdaTablaVacia=new PdfPCell(tablaBlanca);
        celdaTablaVacia.setBorder(Rectangle.NO_BORDER);
        celdaGenerica.setBorder(Rectangle.NO_BORDER);
        
        
        tablaFirmas.addCell(celdaTablaVacia);
        tablaFirmas.addCell(celdaTablaVacia);        
        
        if(proyecto.getNumConvocatoria().contains("12-2012")){
        	PdfPCell ralla=new PdfPCell(new Phrase("_________________________________ ",texto10n));
        	PdfPCell Tutor=new PdfPCell(new Phrase(proyecto.getTutor().toUpperCase()+"\nDocente Tutor(a)",texto10n));
        	PdfPCell tituloTutor=new PdfPCell(new Phrase("Docente Tutor(a)",texto10));

        	ralla.setHorizontalAlignment(Element.ALIGN_CENTER);
    		ralla.setBorder(Rectangle.NO_BORDER);	
    		Tutor.setHorizontalAlignment(Element.ALIGN_CENTER);
    		Tutor.setBorder(Rectangle.NO_BORDER);
    		tituloTutor.setHorizontalAlignment(Element.ALIGN_CENTER);
    		tituloTutor.setBorder(Rectangle.NO_BORDER);
    		celdaTablaVacia.setHorizontalAlignment(Element.ALIGN_CENTER);
    		celdaTablaVacia.setBorder(Rectangle.NO_BORDER);


    		tablaFirmas.addCell(celdaTablaVacia);
    		tablaFirmas.addCell(celdaTablaVacia);
    		tablaFirmas.addCell(ralla);    
    		tablaFirmas.addCell(celdaTablaVacia);
    		tablaFirmas.addCell(Tutor); 		
    		tablaFirmas.addCell(celdaTablaVacia);
    		tablaFirmas.addCell(tituloTutor);  		

        }
        
        if(proyecto.getListaCoInvestigadores()!=null){
	        for(int i=0;i<proyecto.getListaCoInvestigadores().size();i++){
	        	if(i%2==0 && i!=0){
	                tablaFirmas.addCell(celdaTablaVacia);
	                tablaFirmas.addCell(celdaTablaVacia);
	                tablaFirmas.addCell(celdaTablaVacia);
	                tablaFirmas.addCell(celdaTablaVacia);
	            }
	            System.out.println("Ingreso a la lista de firmas "+i);
	            rallaGenerica=new PdfPCell(new Phrase("_________________________________ ",texto10n));
	            coinvest=proyecto.getListaCoInvestigadores().get(i);
	            nombreGenerico=new PdfPCell(new Phrase((coinvest.getNombre()+" "+coinvest.getApellido()).toUpperCase(),texto10n));
	            
	            rallaGenerica.setHorizontalAlignment(Element.ALIGN_CENTER);
	            rallaGenerica.setBorder(Rectangle.NO_BORDER);
	            
	            nombreGenerico.setHorizontalAlignment(Element.ALIGN_CENTER);
	            nombreGenerico.setBorder(Rectangle.NO_BORDER);
	            
	            tituloGenerico.setHorizontalAlignment(Element.ALIGN_CENTER);
	            tituloGenerico.setBorder(Rectangle.NO_BORDER);
	            
	            tablaGenerica =new PdfPTable(1);
	            
	            tablaGenerica.addCell(rallaGenerica);
	            tablaGenerica.addCell(nombreGenerico);
	            tablaGenerica.addCell(tituloGenerico);
	            celdaGenerica=new PdfPCell(tablaGenerica);
	            celdaGenerica.setBorder(Rectangle.NO_BORDER);
	            tablaFirmas.addCell(celdaGenerica);
	            if(proyecto.getListaCoInvestigadores().size()%2!=0 && i==(proyecto.getListaCoInvestigadores().size()-1))
	                tablaFirmas.addCell(celdaTablaVacia);            
	        }
        }
	        
				
		try {
			inicarDocumento();
			agregarContenido(textoDocumento,tablaFirmas);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-Documento Contrato creado->");		
	}

	
	
	private void agregarContenido(PdfPTable tablaInicial, Phrase[] texto,PdfPTable tablaFirmas) throws DocumentException {

		Paragraph total=new Paragraph();
		for(int i=0;i<texto.length;i++)
			total.add(texto[i]);
		total.setAlignment(3);
						
		Paragraph p=new Paragraph("");
		if(actaInicio){
			total.add(p);total.add(p);total.add(p);total.add(p);
		}else{			
			total.add(p);total.add(p);total.add(p);total.add(p);total.add(p);total.add(p);
		}
				
		document.add(tablaInicial);
		document.add(total);		
		document.add(tablaFirmas);
		document.close();
	}
	
	private void agregarContenido(Phrase[] texto,PdfPTable tablaFirmas) throws DocumentException {

		Paragraph total=new Paragraph();
		for(int i=0;i<texto.length;i++)
			total.add(texto[i]);
		total.setAlignment(3);
						
		Paragraph p=new Paragraph("");
		total.add(p);total.add(p);total.add(p);total.add(p);total.add(p);total.add(p);
				
		document.add(total);		
		document.add(tablaFirmas);
		document.close();
	}

	class EventosDocumento extends PdfPageEventHelper{
		PdfWriter writer=null;
		Document document=null;
		PdfTemplate plantilla=null;
		
		public EventosDocumento(PdfWriter writer, Document document){
			this.writer=writer;
			this.document=document;
		}
		
		
		@Override
		public void onOpenDocument(PdfWriter writer, Document document) {
			plantilla=writer.getDirectContent().createTemplate(7, 7);
		}

		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			numeroPagina++;
			try {				
					agregarCabedera();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onEndPage(PdfWriter writer1, Document document1) {
			
			try {
				if(!actaInicio)
					agregarPieDePagina();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onCloseDocument(PdfWriter writer1, Document document1) {
			// TODO Auto-generated method stub
			super.onCloseDocument(writer, document);
			ColumnText.showTextAligned(plantilla, Element.ALIGN_LEFT, new Paragraph(String.valueOf(writer.getPageNumber()-1),paginacion), 1, 1, 0);
			//System.out.println("Documento terminado con un Total de pÃ¡ginas="+(writer1.getPageNumber()-1));
		}
		
		private void agregarCabedera()	throws DocumentException {
			PdfPCell c0 = null;
			Rectangle rectangulo=this.writer.getPageSize();
			
			PdfPTable tablaEscudo =new PdfPTable(1);
			tablaEscudo.setWidths(new float[]{(rectangulo.getLeft()+rectangulo.getRight()-120)});
			tablaEscudo.setTotalWidth((rectangulo.getLeft()+rectangulo.getRight()-120));
			tablaEscudo.getDefaultCell().setFixedHeight(70);
						
			try {				
				
				Image escudo=Image.getInstance(pathArchivo.substring(0,pathArchivo.lastIndexOf("Documentos"))+sep+"comp"+sep+"img"+sep+"escudo.png");
				escudo.setBorder(0);
				tablaEscudo.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				tablaEscudo.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				tablaEscudo.addCell(escudo);
				
				proyecto=new Proyecto();				
				CoInvest coinvest=null;
				String nombresestudiantes="";
/*				for(int i=0;i<proyecto.getListaCoInvestigadores().size();i++){			
					//proyecto=(Proyecto)itEstudiante.next();
					coinvest=proyecto.getListaCoInvestigadores().get(i);
					//System.out.println("Ingreso al recorrido de los estudiantes"+clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10)));
					nombresestudiantes=nombresestudiantes+" "+coinvest.getNombre().toUpperCase()+" "+coinvest.getApellido().toUpperCase()+",";
					//clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10));					
					//proyecto.getCedulaDirDe()+" "+
				}*/
				
				if(writer.getPageNumber()==1){
					if(actaInicio)
/***/					c0=new PdfPCell(new Paragraph("\nACTA DE INICIO PARA EL APOYO ECONÓMICO Y ADMINISTRACIÓN DE RECURSOS DE INVESTIGACIÓN ",texto11n));
					else
/***/					c0=new PdfPCell(new Paragraph("\nACTA COMPROMISORIA No°" +numContrato.toUpperCase()+ "\nCOMPROMISO DE APOYO ECONOMICO Y ADMINISTRACION DE RECURSOS DE INVESTIGACION CELEBRADO ENTRE LA UNIVERSIDAD DISTRITAL FRANCISCO JOSE DE CALDAS Y "+nombreDirector+" "+nombresestudiantes,texto11n));
					c0.setHorizontalAlignment(Element.ALIGN_CENTER);
					c0.setBorder(Rectangle.NO_BORDER);
					tablaEscudo.addCell(c0);
				}
				tablaEscudo.writeSelectedRows(0, 5, 72, 780 , writer.getDirectContent());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(writer.getPageNumber()>1){
				
				PdfPTable tablaCabecera =new PdfPTable(3);
				tablaCabecera.setWidths(new int[]{20,7,200});
				tablaCabecera.setTotalWidth(450);
				tablaCabecera.setLockedWidth(true);
				tablaCabecera.getDefaultCell().setFixedHeight(10);
				
				PdfPCell txtNumPag=new PdfPCell(new Paragraph("Pag. "+writer.getPageNumber()+" de ",paginacion));
				txtNumPag.setHorizontalAlignment(Element.ALIGN_LEFT);
				txtNumPag.setBorder(Rectangle.NO_BORDER);
				tablaCabecera.addCell(txtNumPag);
				
				tablaCabecera.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				tablaCabecera.addCell(Image.getInstance(plantilla));
				
				PdfPCell c1 = new PdfPCell(new Paragraph("Continuación",paginacion));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setBorder(Rectangle.NO_BORDER);
				tablaCabecera.addCell(c1);
				
				PdfPCell c2 = new PdfPCell(new Paragraph("Acta Compromisoria N°: "+numContrato,texto10n));
				c2.setHorizontalAlignment(Element.ALIGN_LEFT);
				c2.setBorder(Rectangle.NO_BORDER);
				c2.setColspan(3);
				tablaCabecera.addCell(c2);
				c2 = new PdfPCell(new Paragraph("Convocatoria N°: "+convocatoria,texto10n));
				c2.setHorizontalAlignment(Element.ALIGN_LEFT);
				c2.setBorder(Rectangle.NO_BORDER);
				c2.setColspan(3);
				tablaCabecera.addCell(c2);
				
				tablaCabecera.writeSelectedRows(0, 5, 72, 700 , writer.getDirectContent());
			}
		}
		
		private void agregarPieDePagina()	throws DocumentException {
			
			Rectangle rectangulo=this.writer.getPageSize();			
			
			PdfPTable tablaPagPie =new PdfPTable(1);
			PdfPTable tablaMailPie =new PdfPTable(1);
			
			tablaPagPie.setWidths(new int[]{400});
			tablaPagPie.setTotalWidth(450);
			tablaPagPie.setLockedWidth(true);
			tablaPagPie.getDefaultCell().setFixedHeight(10);
			
			tablaMailPie.setWidths(new int[]{100});
			tablaMailPie.setTotalWidth(150);
			/*	
			PdfPCell txtNumPag=new PdfPCell(new Paragraph("Pag. "+writer.getPageNumber()+" de ",paginacion));
			txtNumPag.setHorizontalAlignment(Element.ALIGN_RIGHT);
			txtNumPag.setBorder(Rectangle.NO_BORDER);
			tablaPagPie.addCell(txtNumPag);			
			
			tablaPagPie.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tablaPagPie.addCell(Image.getInstance(plantilla));		*/	
			
			PdfPCell c1 = new PdfPCell(new Paragraph("Centro de Investigaciones y Desarrollo Científico CIDC",paginacion));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			tablaPagPie.addCell(c1);
			
			PdfPCell c2 = new PdfPCell(new Paragraph("3239300 ext 2610 cidc@udistrital.edu.co",texto8));
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setBorder(Rectangle.NO_BORDER);
			c2.setColspan(3);
			tablaMailPie.addCell(c2);
			
			PdfPCell c3 = new PdfPCell(new Paragraph("Todos los derechos reservados",texto8));
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			c3.setBorder(Rectangle.NO_BORDER);
			c3.setColspan(3);
			tablaMailPie.addCell(c3);
			
			tablaPagPie.writeSelectedRows(0, 5, ((rectangulo.getLeft()+rectangulo.getRight())/3)+10, 70 , writer.getDirectContent());
			tablaMailPie.writeSelectedRows(0, 5, (rectangulo.getLeft()+rectangulo.getRight())/2.5f, 60 , writer.getDirectContent());
		}
	}
	
	
	private void inicarDocumento() {
		try {
			writer=PdfWriter.getInstance(document, new FileOutputStream(pathArchivo));
			writer.setPageEvent(new EventosDocumento(writer,document));
			document.open();
			document.addTitle("Acta compromisoria de Investigación CIDC");
			document.addSubject("Acta compromisoria");
			document.addKeywords("Compromisoria, investigacion,o.j.a.s");
			document.addAuthor("CIDC");
			document.addCreator("Sistema SICIUD");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	///////////////////////////////////////////////DOCUMENTOS CON GESTOR FINANCIERO
	
public void crearContrato1(Proyecto proyecto,String path){
	
		System.out.println("Ingreso a Crear 1");
		
		actaInicio=false;
		pathArchivo=path;
		numContrato=proyecto.getConsecContrato()+" de "+global.getAnoHoy();
		nombreDirector=proyecto.getDirector().toUpperCase();
		convocatoria=proyecto.getNumConvocatoria();
		Phrase []textoDocumento=new Phrase [20];
		Phrase clausulas=new Phrase();
		Phrase clausulasinicio=new Phrase();
		
		clausulasinicio.add(new Phrase(rb1.getString("ci1"),texto10));
		clausulasinicio.add(new Phrase(" "+rbDir.getString("directorCIDC"),texto10n));
		clausulasinicio.add(new Phrase(", con cédula de ciudadanía  "+ rbDir.getString("cedDirectorCIDC"),texto10));
		clausulasinicio.add(new Phrase(" "+ rb1.getString("ci2"),texto10));
		clausulasinicio.add(new Phrase(" "+rbDir.getString("resolucionNombramiento")+", ",texto10));
		//Realizador del proyecto AGREGAR
		clausulasinicio.add(new Phrase(proyecto.getRealizador().toUpperCase(),texto10n));
		clausulasinicio.add(new Phrase(", identificado(a) con cédula de ciudadanía "+ proyecto.getCedulaRealizador(),texto10));
		clausulasinicio.add(new Phrase(" expedida en "+proyecto.getCedulaRealizadorDe(),texto10));
		clausulasinicio.add(new Phrase(", "+rb1.getString("ci3"),texto10));
		
		//Datos del DOCENTE DIRECTOR DEL TRABAJO DE GRADO
		
		clausulasinicio.add(new Phrase(proyecto.getDirector().toUpperCase(),texto10n));
		clausulasinicio.add(new Phrase(", identificado(a) con cédula de ciudadanía "+ proyecto.getCedulaDir(),texto10));
		clausulasinicio.add(new Phrase(" expedida en "+proyecto.getCedulaDirDe(),texto10));
		clausulasinicio.add(new Phrase(", "+rb1.getString("ci333"),texto10));
		
		//Datos del DOCENTE DIRECTOR DEL GESTOR FINANCIERO
		clausulasinicio.add(new Phrase(proyecto.getGestor().toUpperCase(),texto10n));
		clausulasinicio.add(new Phrase(", identificado(a) con cédula de ciudadanía "+ proyecto.getCedulaGestor(),texto10));
		clausulasinicio.add(new Phrase(" expedida en "+proyecto.getCedulaGestorDe(),texto10));
		clausulasinicio.add(new Phrase(", "+rb1.getString("ci334"),texto10));		
		
		textoDocumento[0]=clausulasinicio;
		textoDocumento[1]=clausulas;
		//IMPRIMIR LISTA DE ESTUDIANTES

		CoInvest coinvest=null;
		int j=9;
		String texto="";
		if(proyecto.getListaCoInvestigadores().size()!=0)
			texto=", y el (los) (la) (las) estudiante";
				
		clausulas.add(new Phrase(texto,texto10));
		for(int i=0;i<proyecto.getListaCoInvestigadores().size();i++){			
			//proyecto=(Proyecto)itEstudiante.next();
			coinvest=proyecto.getListaCoInvestigadores().get(i);
			//System.out.println("Ingreso al recorrido de los estudiantes"+clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10)));
			clausulas.add(new Phrase(" "+coinvest.getNombre().toUpperCase()+" "+coinvest.getApellido().toUpperCase()+" adscrito(s) al programa"+"  "+coinvest.getProyectocnombre()+"  "+"con código estudiantil"+"  "+ coinvest.getCodigo()+", ",texto10));
			//clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10));
			j++;
			//proyecto.getCedulaDirDe()+" "+
		}
				
		clausulas.add(new Phrase(" "+rb1.getString("ci38"),texto10));				
		clausulas.add(new Phrase(" CLAUSULA PRIMERA.- ",texto10n));
		clausulas.add(new Phrase("Objeto: ",texto10s));
		clausulas.add(new Phrase(", "+rb1.getString("ci4")+" ",texto10));
/****/	clausulas.add(new Phrase(" "+proyecto.getProyecto().trim().toUpperCase(),texto10n));
		clausulas.add(new Phrase(" representado y dirigido  por EL(LA) REALIZADOR(A) ",texto10));		
/****/	clausulas.add(new Phrase(" seleccionado en la  convocatoria  N°",texto10));
/****/	clausulas.add(new Phrase(proyecto.getNumConvocatoria(),texto10));
/****/	clausulas.add(new Phrase(", clasificado con el código "+proyecto.getCodigo(),texto10));
/****/	clausulas.add(new Phrase(", aprobado segun Acta N° "+proyecto.getSesion(),texto10));
/****/	clausulas.add(new Phrase(" de "+global.getNombreMes(proyecto.getFecActa(), 1)+" "+"del "+global.getDiaFecha(proyecto.getFecActa(), 2),texto10));

		clausulas.add(new Phrase(" del Comité de Investigaciones y presentado por el Grupo o Semillero de Investigación ",texto10));
/****/	clausulas.add(new Phrase(" '"+proyecto.getGrupoInvestigacion().toUpperCase()+"'. ",texto10n));
		clausulas.add(new Phrase(" CLAUSULA SEGUNDA.- ",texto10n));
		clausulas.add(new Phrase("Obligaciones de las partes ",texto10s));
		clausulas.add(new Phrase(" 1. EL (LA) REALIZADOR(A) DEL PROYECTO DE INVESTIGACION, EL DIRECTOR DE TRABAJO DE GRADO, EL GESTOR FINANCIERO Y DEMÁS PARTÍCIPES. ",texto10));		
		clausulas.add(new Phrase(", "+" "+rb1.getString("ci5"),texto10));

		//COMPROMISOS DE LOS INVESTIGADORES
///****/	clausulas.add(new Phrase(proyecto.getCompromisosConv(),texto10));

		CompromisosOBJ compromisos=null;
		
		for(int i=0;i<proyecto.getListaCompromisos().size();i++){			
			//proyecto=(Proyecto)itEstudiante.next();
			compromisos=proyecto.getListaCompromisos().get(i);
			//System.out.println("Ingreso al recorrido de los estudiantes"+clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10)));
			clausulas.add(new Phrase((i+1)+") "+compromisos.getNombre()+", ",texto10));
			//clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10));	

		}		
		clausulas.add(new Phrase(" de conformidad con la propuesta presentada.",texto10));
		clausulas.add(new Phrase(" PARAGRAFO: ",texto10n));
		clausulas.add(new Phrase(rb1.getString("ci55"),texto10));
		clausulas.add(new Phrase(" 2. LA UNIVERSIDAD",texto10n));
		clausulas.add(new Phrase(", "+rb1.getString("ci6"),texto10));		
		clausulas.add(new Phrase(" CLAUSULA TERCERA.- ",texto10n));
		clausulas.add(new Phrase("Duración:",texto10s));
		clausulas.add(new Phrase(" La duración total del proyecto es de ",texto10));
/****/	clausulas.add(new Phrase(proyecto.getDuracionLetras()+"("+proyecto.getDuracion()+") Meses ",texto10));
		clausulas.add(new Phrase(", "+rb1.getString("ci61"),texto10));		
		clausulas.add(new Phrase(" CLAUSULA CUARTA.- ",texto10n));
		clausulas.add(new Phrase("Productividad: ",texto10s));
		clausulas.add(new Phrase(" El (la) Realizador(a) y los partícipes del proyecto se comprometen a hacer la entrega de los productos registrados en la propuesta presentada para la convocatoria. ",texto10));
		clausulas.add(new Phrase(" CLAUSULA QUINTA.- ",texto10n));
		clausulas.add(new Phrase("Valor de la financiación: ",texto10s));
		clausulas.add(new Phrase(" La Universidad coloca a disposición (el) (la) Realizador(a) del proyecto de grado recursos económicos por  la suma de ",texto10));
/****/	clausulas.add(new Phrase(proyecto.getValorLetras().toUpperCase(),texto10));
/****/	clausulas.add(new Phrase(" ("+proyecto.getValor()+") M/CTE",texto10));		
		clausulas.add(new Phrase(", "+rb1.getString("ci11"),texto10));
		clausulas.add(new Phrase(" Parágrafo 1. ",texto10n));
		clausulas.add(new Phrase(" "+rb1.getString("ci12"),texto10));
		
		clausulas.add(new Phrase(" CLAUSULA SEXTA.- ",texto10n));
		clausulas.add(new Phrase("Documentos del Compromiso Apoyo Económico y Administración de Recursos de Investigación: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci13"),texto10));
		clausulas.add(new Phrase(" PARAGRAFO: ",texto10n));
		clausulas.add(new Phrase(" "+rb1.getString("ci14"),texto10));
		
		clausulas.add(new Phrase(" CLAUSULA SEPTIMA.- ",texto10n));
		clausulas.add(new Phrase("Acta de cierre y liquidación de compromisos: ",texto10s));		
		clausulas.add(new Phrase(" "+rb1.getString("ci15"),texto10));
		clausulas.add(new Phrase(" CLAUSULA OCTAVA.- ",texto10n));
		clausulas.add(new Phrase("Recuperación Contingente : ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci16"),texto10));
		clausulas.add(new Phrase(" CLAUSULA NOVENA.- ",texto10n));
		clausulas.add(new Phrase("Mérito Ejecutivo: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci17"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMA.- ",texto10n));
		clausulas.add(new Phrase("Supervisión: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci18"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO PRIMERA.- ",texto10n));		
		clausulas.add(new Phrase(" "+rb1.getString("ci19"),texto10));		
		clausulas.add(new Phrase(" PARÁGRAFO. ",texto10n));
		clausulas.add(new Phrase(" "+rb1.getString("ci20"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO SEGUNDA.- ",texto10n));
		clausulas.add(new Phrase(" Erogaciones. ",texto10s));
		clausulas.add(new Phrase(" Toda erogación deberá soportarse en Disponibilidad y Registro presupuestal.",texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO TERCERA.- ",texto10n));
		clausulas.add(new Phrase("Divulgación de las fuentes de financiación: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci21"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO CUARTA.- ",texto10n));
		clausulas.add(new Phrase("Requisitos de perfeccionamiento y ejecución del compromiso: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci22"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO QUINTA.- ",texto10n));
		clausulas.add(new Phrase("Propiedad intelectual: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci23"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO SEXTA.- ",texto10n));
		clausulas.add(new Phrase("Invenciones y otras Creaciones: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci24"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO SEPTIMA.- ",texto10n));
		clausulas.add(new Phrase("Regalías: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci25"),texto10));
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO OCTAVA.- ",texto10n));
		clausulas.add(new Phrase("Constancia escrita: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci26"),texto10));		
		clausulas.add(new Phrase(" CLAUSULA DÉCIMO NOVENA.- ",texto10n));
		clausulas.add(new Phrase("Régimen Convencional: ",texto10s));
		clausulas.add(new Phrase(" "+rb1.getString("ci27"),texto10));
		
/****/	clausulas.add(new Phrase(" "+global.getDiaHoy()+" días del mes de "+global.getNombreMesHoy()+" de "+global.getAnoHoy()+".",texto10));

		
		

		PdfPTable tablaFirmas =new PdfPTable(2);
		
		try {
			tablaFirmas.setWidths(new int[]{200,200});
			tablaFirmas.setTotalWidth(450);
			tablaFirmas.setLockedWidth(true);
			tablaFirmas.getDefaultCell().setFixedHeight(10);
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		PdfPCell rallaGenerica=null;
        PdfPTable tablaGenerica=null;        
        PdfPCell nombreGenerico=new PdfPCell(new Phrase(proyecto.getDirector().toUpperCase(),texto10n));
        PdfPCell tituloGenerico=new PdfPCell(new Phrase("Realizador",texto10n));        
        
        PdfPTable tablaBlanca =new PdfPTable(1);
        PdfPCell celdaVacia=new PdfPCell(new Phrase("\n....\n",texto15Blanca));
        PdfPCell celdatablaVacia=new PdfPCell();
        
        celdaVacia.setBorder(Rectangle.NO_BORDER);
        tablaBlanca.addCell(celdaVacia);
        PdfPCell celdaGenerica=new PdfPCell();
        PdfPCell celdaTablaVacia=new PdfPCell(tablaBlanca);
        celdaTablaVacia.setBorder(Rectangle.NO_BORDER);
        celdaGenerica.setBorder(Rectangle.NO_BORDER);
		
		
		PdfPCell ralla1=new PdfPCell(new Phrase("_________________________________ ",texto10n));
		PdfPCell ralla2=new PdfPCell(new Phrase("_________________________________ ",texto10n));
		PdfPCell directorCIDC=new PdfPCell(new Phrase(rbDir.getString("directorCIDC").toUpperCase(),texto10n));
		PdfPCell directorProyecto=new PdfPCell(new Phrase(proyecto.getDirector().toUpperCase(),texto10n));
		PdfPCell tituloCIDC=new PdfPCell(new Phrase("Director Centro de Investigaciones",texto10));
		PdfPCell tituloDirector=new PdfPCell(new Phrase("Director Proyecto de Grado",texto10));
		
		PdfPCell ralla3=new PdfPCell(new Phrase("_________________________________ ",texto10n));
		PdfPCell ralla4=new PdfPCell(new Phrase("_________________________________ ",texto10n));
		PdfPCell Realizador=new PdfPCell(new Phrase(proyecto.getRealizador().toUpperCase(),texto10n));
		PdfPCell Gestor=new PdfPCell(new Phrase(proyecto.getGestor().toUpperCase(),texto10n));
		PdfPCell titulorealizador=new PdfPCell(new Phrase("Realizador del Proyecto",texto10));
		PdfPCell titulogestor=new PdfPCell(new Phrase("Gestor Financiero",texto10));
		
		ralla1.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla1.setBorder(Rectangle.NO_BORDER);		
		ralla2.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla2.setBorder(Rectangle.NO_BORDER);
		
		ralla3.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla3.setBorder(Rectangle.NO_BORDER);		
		ralla4.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla4.setBorder(Rectangle.NO_BORDER);
		
		directorCIDC.setHorizontalAlignment(Element.ALIGN_CENTER);
		directorCIDC.setBorder(Rectangle.NO_BORDER);		
		directorProyecto.setHorizontalAlignment(Element.ALIGN_CENTER);
		directorProyecto.setBorder(Rectangle.NO_BORDER);
		
		Realizador.setHorizontalAlignment(Element.ALIGN_CENTER);
		Realizador.setBorder(Rectangle.NO_BORDER);		
		Gestor.setHorizontalAlignment(Element.ALIGN_CENTER);
		Gestor.setBorder(Rectangle.NO_BORDER);

		tituloCIDC.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloCIDC.setBorder(Rectangle.NO_BORDER);		
		tituloDirector.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloDirector.setBorder(Rectangle.NO_BORDER);
		
		titulorealizador.setHorizontalAlignment(Element.ALIGN_CENTER);
		titulorealizador.setBorder(Rectangle.NO_BORDER);		
		titulogestor.setHorizontalAlignment(Element.ALIGN_CENTER);
		titulogestor.setBorder(Rectangle.NO_BORDER);
		

		tablaFirmas.addCell(ralla1);
		tablaFirmas.addCell(ralla2);
		tablaFirmas.addCell(directorCIDC);
		tablaFirmas.addCell(directorProyecto);
		tablaFirmas.addCell(tituloCIDC);
		tablaFirmas.addCell(tituloDirector);
		
		tablaFirmas.addCell(celdaTablaVacia);
        tablaFirmas.addCell(celdaTablaVacia);
        tablaFirmas.addCell(celdaTablaVacia);
        tablaFirmas.addCell(celdaTablaVacia);
		
		tablaFirmas.addCell(ralla3);
		tablaFirmas.addCell(ralla4);
		tablaFirmas.addCell(Realizador);
		tablaFirmas.addCell(Gestor);
		tablaFirmas.addCell(titulorealizador);
		tablaFirmas.addCell(titulogestor);
		
		//*********************************+
		
		coinvest=null;
        
                
        tablaFirmas.addCell(celdaTablaVacia);
        tablaFirmas.addCell(celdaTablaVacia);
        tablaFirmas.addCell(celdaTablaVacia);
        tablaFirmas.addCell(celdaTablaVacia);        
        
        
        for(int i=0;i<proyecto.getListaCoInvestigadores().size();i++){
        	if(i%2==0 && i!=0){
                tablaFirmas.addCell(celdaTablaVacia);
                tablaFirmas.addCell(celdaTablaVacia);
                tablaFirmas.addCell(celdaTablaVacia);
                tablaFirmas.addCell(celdaTablaVacia);
            }
            System.out.println("Ingreso a la lista de firmas "+i);
            rallaGenerica=new PdfPCell(new Phrase("_________________________________ ",texto10n));
            coinvest=proyecto.getListaCoInvestigadores().get(i);
            nombreGenerico=new PdfPCell(new Phrase((coinvest.getNombre()+" "+coinvest.getApellido()).toUpperCase(),texto10n));
            
            rallaGenerica.setHorizontalAlignment(Element.ALIGN_CENTER);
            rallaGenerica.setBorder(Rectangle.NO_BORDER);
            
            nombreGenerico.setHorizontalAlignment(Element.ALIGN_CENTER);
            nombreGenerico.setBorder(Rectangle.NO_BORDER);
            
            tituloGenerico.setHorizontalAlignment(Element.ALIGN_CENTER);
            tituloGenerico.setBorder(Rectangle.NO_BORDER);
            
            tablaGenerica =new PdfPTable(1);
            
            tablaGenerica.addCell(rallaGenerica);
            tablaGenerica.addCell(nombreGenerico);
            tablaGenerica.addCell(tituloGenerico);
            celdaGenerica=new PdfPCell(tablaGenerica);
            celdaGenerica.setBorder(Rectangle.NO_BORDER);
            tablaFirmas.addCell(celdaGenerica);
            if(proyecto.getListaCoInvestigadores().size()%2!=0 && i==(proyecto.getListaCoInvestigadores().size()-1))
                tablaFirmas.addCell(celdaTablaVacia);            
        }
	        
				
		try {
			inicarDocumento();
			agregarContenido(textoDocumento,tablaFirmas);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-Documento Contrato creado->");		
	}
	
	public HttpServletResponse cearCertificado1(CertificacionesOBJ certificado, String path,  HttpServletResponse resp){
		System.out.println("Creando Certificado Pertenencia a Grupo/Semillero de Investigación");
		String contenido=""; 
		actaInicio=false;
		pathArchivo=path;
		numCertificado=certificado.getConsCert()+" de "+global.getAnoHoy();
		nombrePersona=certificado.getNombre().toUpperCase();		
		Phrase []textoDocumento=new Phrase [20];
		Phrase clausulas=new Phrase();
		Phrase clausulasinicio=new Phrase();		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy h:mm:ss a");
		String formattedDate = sdf.format(date);		
		clausulasinicio.add(new Phrase("\n\nNúmero de Verificación: "+certificado.getCod_verificacion()+"\n",texto10n));
		clausulasinicio.add(new Phrase("Generado el: "+formattedDate+"\n\n\n",texto10n));
		clausulasinicio.add(new Phrase("El(La) investigador(a) ",texto10));
		clausulasinicio.add(new Phrase(certificado.getNombre().toUpperCase()+",",texto10n));
		clausulasinicio.add(new Phrase("identificado(a) con la cédula de ciudadanía N° "+certificado.getCedula()+" de "+ certificado.getNumCedDe()+" ",texto10));
		clausulasinicio.add(new Phrase("es integrante del ",texto10));
		clausulasinicio.add(new Phrase(certificado.getNombreGrupo(),texto10n));
		clausulasinicio.add(new Phrase(", institucionalizado por el Centro de Investigaciones y Desarrollo Científico el ",texto10));
		clausulasinicio.add(new Phrase(global.getDiaFecha(certificado.getFecha_cert(), 1) +" de "+global.getNombreMes(certificado.getFecha_cert(), 1) +" de "+ global.getAnoFecha(certificado.getFecha_cert())+", el cual se encuentra ",texto10));
		String categoria ="";
		if(certificado.getCategoriaGrupo().equals("1")||certificado.getCategoriaGrupo().equals("2")){
			categoria=(certificado.getCategoriaGrupo().equals("1"))?" sin clasificación ":" institucionalizado ante la Universidad Distrital Francisco José de Caldas.";
		}else{
			categoria=" clasificado en categoria "+certificado.getCategoriaGrupo()+" por Colciencias en la convocatoria de grupos colombianos de investigación.";
		}
		clausulasinicio.add(new Phrase(categoria,texto10));
		clausulasinicio.add(new Phrase(" Bajo la dirección de la(el) profesor(a) "+certificado.getNombreDirector().toUpperCase()+".\n\n",texto10));
		clausulasinicio.add(new Phrase("Se expide la presente a solicitud del (de la) interesado(a) a los ",texto10));
		clausulasinicio.add(new Phrase(" "+global.getDiaHoy()+" días del mes de "+global.getNombreMesHoy()+" de "+global.getAnoHoy()+".",texto10));
		contenido=clausulasinicio.toString()+" "+clausulas.toString();	
		
		textoDocumento[0]=clausulasinicio;
		textoDocumento[1]=clausulas;
		
		PdfPTable tablaFirmas =new PdfPTable(2);
		PdfPTable tablaEscudo =new PdfPTable(1);
		//Rectangle rectangulo=this.writer.getPageSize();
		PdfPCell c0 = null;
        Image firmaD=null;
		
		
		try {			
			/*tablaEscudo.setWidths(new float[]{(rectangulo.getLeft()+rectangulo.getRight()-120)});
			tablaEscudo.setTotalWidth((rectangulo.getLeft()+rectangulo.getRight()-120));*/
			tablaEscudo.setWidths(new int[]{200});
			tablaEscudo.setTotalWidth(450);
			tablaEscudo.getDefaultCell().setFixedHeight(70);
			
			firmaD=Image.getInstance(pathArchivo.substring(0,pathArchivo.lastIndexOf("Documentos"))+sep+"comp"+sep+"img"+sep+"firma_Director.jpg");
			firmaD.setBorder(0);
			tablaEscudo.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tablaEscudo.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);			
			
			tablaFirmas.setWidths(new int[]{200,200});
			tablaFirmas.setTotalWidth(450);
			tablaFirmas.setLockedWidth(true);
			tablaFirmas.getDefaultCell().setFixedHeight(10);
			tablaFirmas.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
			tablaEscudo.writeSelectedRows(0, 5, 72, 780 , writer.getDirectContent());
		} catch (DocumentException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
        PdfPTable tablaBlanca =new PdfPTable(1);
        PdfPCell celdaVacia=new PdfPCell(new Phrase("\n....\n",texto15Blanca));
        
        celdaVacia.setBorder(Rectangle.NO_BORDER);
        tablaBlanca.addCell(celdaVacia);
        PdfPCell celdaGenerica=new PdfPCell();
        PdfPCell celdaTablaVacia=new PdfPCell(tablaBlanca);
        celdaTablaVacia.setBorder(Rectangle.NO_BORDER);
        celdaGenerica.setBorder(Rectangle.NO_BORDER);
		
        PdfPCell ralla1=new PdfPCell(new Phrase("_________________________________ ",texto10n));
		PdfPCell directorCIDC=new PdfPCell(new Phrase(rbDir.getString("directorCIDC").toUpperCase(),texto10n));
		PdfPCell tituloCIDC=new PdfPCell(new Phrase("Director Centro de Investigaciones",texto10));
		
		ralla1.setHorizontalAlignment(Element.ALIGN_CENTER);
		ralla1.setBorder(Rectangle.NO_BORDER);		
		
		directorCIDC.setHorizontalAlignment(Element.ALIGN_CENTER);
		directorCIDC.setBorder(Rectangle.NO_BORDER);		
		
		tituloCIDC.setHorizontalAlignment(Element.ALIGN_CENTER);
		tituloCIDC.setBorder(Rectangle.NO_BORDER);		
		
		tablaEscudo.addCell(firmaD);		
		tablaEscudo.addCell(ralla1);		
		tablaEscudo.addCell(directorCIDC);		
		tablaEscudo.addCell(tituloCIDC);		
		
		//*********************************+
		try {
			certificado.setCuerpo_cer(contenido);
			inicarDocumentoCertificado(resp);
			agregarContenido(textoDocumento,tablaEscudo);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("-Certificado Electrónico creado->");
		return resp;
		
	}

	// 
	
	private HttpServletResponse inicarDocumentoCertificado(HttpServletResponse resp) {
		try {
			writer=PdfWriter.getInstance(document, new FileOutputStream(pathArchivo));
			resp.setContentType("application/pdf");
			resp.setHeader("Content-disposition","inline; filename=test.pdf");			
			PdfWriter.getInstance(document, new FileOutputStream("Certificado.pdf"));
			
			writer.setPageEvent(new EventosDocumentoCertificado(writer,document));
			document.open();			
			document.addTitle("\n EL SUSCRITO DIRECTOR DEL CENTRO DE INVESTIGACIONES Y DESARROLLO CIENTÍFICO \n CERTIFICA QUE:");
			document.addSubject("Certificado Electrónico");
			document.addKeywords("Certificado, electrónico,o.j.a.s");
			document.addAuthor("CIDC");
			document.addCreator("Sistema SICIUD");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return resp;
	}
	
	class EventosDocumentoCertificado extends PdfPageEventHelper{
		PdfWriter writer=null;
		Document document=null;
		PdfTemplate plantilla=null;
		
		public EventosDocumentoCertificado(PdfWriter writer, Document document){
			this.writer=writer;
			this.document=document;
		}
		
		
		@Override
		public void onOpenDocument(PdfWriter writer, Document document) {
			plantilla=writer.getDirectContent().createTemplate(7, 7);
		}

		@Override
		public void onStartPage(PdfWriter writer, Document document) {
			numeroPagina++;
			try {				
					agregarCabedera();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onEndPage(PdfWriter writer1, Document document1) {
			
			try {
				if(!actaInicio)
					agregarPieDePagina();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onCloseDocument(PdfWriter writer1, Document document1) {
			// TODO Auto-generated method stub
			super.onCloseDocument(writer, document);
			ColumnText.showTextAligned(plantilla, Element.ALIGN_LEFT, new Paragraph(String.valueOf(writer.getPageNumber()-1),paginacion), 1, 1, 0);
			//System.out.println("Documento terminado con un Total de pÃ¡ginas="+(writer1.getPageNumber()-1));
		}
		
		private void agregarCabedera()	throws DocumentException {
			PdfPCell c0 = null;
			Rectangle rectangulo=this.writer.getPageSize();
			
			PdfPTable tablaEscudo =new PdfPTable(1);
			tablaEscudo.setWidths(new float[]{(rectangulo.getLeft()+rectangulo.getRight()-120)});
			tablaEscudo.setTotalWidth((rectangulo.getLeft()+rectangulo.getRight()-120));
			tablaEscudo.getDefaultCell().setFixedHeight(70);
						
			try {				
				
				Image escudo=Image.getInstance(pathArchivo.substring(0,pathArchivo.lastIndexOf("Documentos"))+sep+"comp"+sep+"img"+sep+"escudo.png");
				escudo.setBorder(0);
				tablaEscudo.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				tablaEscudo.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
				tablaEscudo.addCell(escudo);
				
				proyecto=new Proyecto();				
				CoInvest coinvest=null;
				String nombresestudiantes="";
/*				for(int i=0;i<proyecto.getListaCoInvestigadores().size();i++){			
					//proyecto=(Proyecto)itEstudiante.next();
					coinvest=proyecto.getListaCoInvestigadores().get(i);
					//System.out.println("Ingreso al recorrido de los estudiantes"+clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10)));
					nombresestudiantes=nombresestudiantes+" "+coinvest.getNombre().toUpperCase()+" "+coinvest.getApellido().toUpperCase()+",";
					//clausulas.add(new Phrase(coinvest.getNombre()+" "+coinvest.getApellido().toUpperCase()+"estudiante adscrito al programa "+coinvest.getProyectocnombre()+" con código estudiantil" + coinvest.getCodigo(),texto10));					
					//proyecto.getCedulaDirDe()+" "+
				}*/
				
				if(writer.getPageNumber()==1){
					if(actaInicio)
/***/					c0=new PdfPCell(new Paragraph("\n\n\nEL SUSCRITO DIRECTOR DEL CENTRO DE INVESTIGACIONES \n\n\nCERTIFICA QUE:",texto11n));
					else
/***/					c0=new PdfPCell(new Paragraph("\n\n\nEL SUSCRITO DIRECTOR DEL CENTRO DE INVESTIGACIONES \n\n\nCERTIFICA QUE: ",texto11n));
					c0.setHorizontalAlignment(Element.ALIGN_CENTER);
					c0.setBorder(Rectangle.NO_BORDER);
					tablaEscudo.addCell(c0);
				}
				tablaEscudo.writeSelectedRows(0, 5, 72, 780 , writer.getDirectContent());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(writer.getPageNumber()>1){
				
				PdfPTable tablaCabecera =new PdfPTable(3);
				tablaCabecera.setWidths(new int[]{20,7,200});
				tablaCabecera.setTotalWidth(450);
				tablaCabecera.setLockedWidth(true);
				tablaCabecera.getDefaultCell().setFixedHeight(10);
				
				PdfPCell txtNumPag=new PdfPCell(new Paragraph("Pag. "+writer.getPageNumber()+" de ",paginacion));
				txtNumPag.setHorizontalAlignment(Element.ALIGN_LEFT);
				txtNumPag.setBorder(Rectangle.NO_BORDER);
				tablaCabecera.addCell(txtNumPag);
				
				tablaCabecera.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				tablaCabecera.addCell(Image.getInstance(plantilla));
				
				PdfPCell c1 = new PdfPCell(new Paragraph("Continuación",paginacion));
				c1.setHorizontalAlignment(Element.ALIGN_LEFT);
				c1.setBorder(Rectangle.NO_BORDER);
				tablaCabecera.addCell(c1);
				
				PdfPCell c2 = new PdfPCell(new Paragraph("Acta Compromisoria N°: "+numContrato,texto10n));
				c2.setHorizontalAlignment(Element.ALIGN_LEFT);
				c2.setBorder(Rectangle.NO_BORDER);
				c2.setColspan(3);
				tablaCabecera.addCell(c2);
				c2 = new PdfPCell(new Paragraph("Convocatoria N°: "+convocatoria,texto10n));
				c2.setHorizontalAlignment(Element.ALIGN_LEFT);
				c2.setBorder(Rectangle.NO_BORDER);
				c2.setColspan(3);
				tablaCabecera.addCell(c2);
				
				tablaCabecera.writeSelectedRows(0, 5, 72, 700 , writer.getDirectContent());
			}
		}
		
		private void agregarPieDePagina()	throws DocumentException {
			
			Rectangle rectangulo=this.writer.getPageSize();			
			
			PdfPTable tablaPagPie =new PdfPTable(1);
			PdfPTable tablaMailPie =new PdfPTable(1);
			
			tablaPagPie.setWidths(new int[]{400});
			tablaPagPie.setTotalWidth(450);
			tablaPagPie.setLockedWidth(true);
			tablaPagPie.getDefaultCell().setFixedHeight(10);
			
			tablaMailPie.setWidths(new int[]{100});
			tablaMailPie.setTotalWidth(150);
			/*	
			PdfPCell txtNumPag=new PdfPCell(new Paragraph("Pag. "+writer.getPageNumber()+" de ",paginacion));
			txtNumPag.setHorizontalAlignment(Element.ALIGN_RIGHT);
			txtNumPag.setBorder(Rectangle.NO_BORDER);
			tablaPagPie.addCell(txtNumPag);			
			
			tablaPagPie.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			tablaPagPie.addCell(Image.getInstance(plantilla));		*/	
			
			PdfPCell c1 = new PdfPCell(new Paragraph("Centro de Investigaciones y Desarrollo Científico CIDC",paginacion));
			c1.setHorizontalAlignment(Element.ALIGN_LEFT);
			c1.setBorder(Rectangle.NO_BORDER);
			tablaPagPie.addCell(c1);
			
			PdfPCell c2 = new PdfPCell(new Paragraph("3239300 ext 2610 cidc@udistrital.edu.co",texto8));
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setBorder(Rectangle.NO_BORDER);
			c2.setColspan(3);
			tablaMailPie.addCell(c2);			
			
			PdfPCell c3 = new PdfPCell(new Paragraph("Todos los derechos reservados",texto8));
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			c3.setBorder(Rectangle.NO_BORDER);
			c3.setColspan(3);
			tablaMailPie.addCell(c3);
			
			tablaPagPie.writeSelectedRows(0, 5, ((rectangulo.getLeft()+rectangulo.getRight())/3)+10, 70 , writer.getDirectContent());
			tablaMailPie.writeSelectedRows(0, 5, (rectangulo.getLeft()+rectangulo.getRight())/2.5f, 60 , writer.getDirectContent());
		}
	}
	
}
