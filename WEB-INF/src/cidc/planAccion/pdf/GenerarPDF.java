package cidc.planAccion.pdf;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cidc.planAccion.obj.Actividades;
import cidc.planAccion.obj.Criterios;
import cidc.planAccion.obj.PlanAccionDatos;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


public class GenerarPDF{
	
  private static String FILE = "/usr/local/tomcat/webapps/siciud/Documentos/PlanAccion/";
  private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
      Font.BOLD);
  
  private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.NORMAL, BaseColor.RED);
  private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,
      Font.BOLD);
  private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
      Font.BOLD);

  public  String generar(Object obj, Object obj2, PlanAccionDatos datos) {
    try {
      ArrayList<Actividades> lista =(ArrayList<Actividades>) obj;	
      ArrayList<Criterios> criterio=(ArrayList<Criterios>) obj2;
//      crearA();
      Document document = new Document();
      String nombre=Calendar.getInstance().getTimeInMillis()+".pdf";
      PdfWriter.getInstance(document, new FileOutputStream(FILE+nombre));
      document.open();
      addMetaData(document);
      addTitlePage(document,datos.getAnoinicio(),datos.getNombregrupo());
      addContent(document,lista,criterio);
      document.close();
      return nombre;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  // iText allows to add metadata to the PDF which can be viewed in your Adobe
  // Reader
  // under File -> Properties
  private static void addMetaData(Document document) {
    document.addTitle("Plan de Acción");
    document.addAuthor("CIDC");
    document.addCreator("CIDC");
  }

  private static void addTitlePage(Document document,String ano, String nombre)
      throws DocumentException, MalformedURLException, IOException {
    Paragraph preface = new Paragraph();
    // We add one empty line
    // Lets write a big header
    Image image1 = Image.getInstance("/usr/local/tomcat/webapps/siciud/comp/img/logopdf1.png");
    image1.scalePercent(new Float("70"));
    document.add(image1);
    addEmptyLine(preface, 1);
    preface.add(new Paragraph("CENTRO DE INVESTIGACION Y DESARROLLO CIENTIFICO", catFont));
    document.add(preface);
    Paragraph paragraph = new Paragraph("PLAN DE ACCION "+nombre.toUpperCase(),subFont);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    addEmptyLine(paragraph, 1);
    document.add(paragraph);
    paragraph = new Paragraph("PERIODO: "+ano,subFont);
    paragraph.setAlignment(Element.ALIGN_CENTER);
    addEmptyLine(paragraph, 1);
    document.add(paragraph);
  }

  private static void addContent(Document document, ArrayList<Actividades> lista,ArrayList<Criterios>  criterio) throws DocumentException {
//    Anchor anchor = new Anchor("First Chapter", catFont);
//    anchor.setName("First Chapter");
    for (Criterios crit : criterio) {
    	Paragraph preface = new Paragraph();
    	addEmptyLine(preface, 2);
    	preface.add(new Paragraph(crit.getIdCriterio()+". "+crit.getDescripcion(), smallBold));
        addEmptyLine(preface, 1);
        document.add(preface);
        document.add(createTable(lista,crit));
        
	}

  }

  private static PdfPTable createTable(ArrayList<Actividades> lista,Criterios crit) throws BadElementException {
    PdfPTable table = new PdfPTable(3);

    // t.setBorderColor(BaseColor.GRAY);
    // t.setPadding(4);
    // t.setSpacing(4);
    // t.setBorderWidth(1);

    PdfPCell c1 = new PdfPCell(new Phrase("Actividad"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Descripción"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);

    c1 = new PdfPCell(new Phrase("Meta"));
    c1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(c1);
    table.setHeaderRows(1);

    for (Actividades actividades : lista) {
    	if (crit.getIdCriterio()==actividades.getIdCriterio()) {
			table.addCell(actividades.getActividad());
			table.addCell(actividades.getDescripcion());
			table.addCell(actividades.getMeta());
		}
	}
    return table;
  }


  private static void addEmptyLine(Paragraph paragraph, int number) {
    for (int i = 0; i < number; i++) {
      paragraph.add(new Paragraph(" "));
    }
  }
  
} 
