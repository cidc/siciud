package org.apache.jsp.Adminreportes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import java.util.*;
import java.io.*;
import java.sql.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.JRRenderable;

public final class REPORTEGENERAL2_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
  Connection conn = null;

         String formato = "";

         try
         {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/siciudconv","postgres","123456");

            //ServletContext servletContext = this.getServletContext();
            File reportFile = new File(application.getRealPath("REPORTES/REPORTGENERAL.jasper"));
            Map parameters = new HashMap();

            int Opcion = Integer.parseInt(request.getParameter("txtidformato5"));

            if(Opcion == 1 )
            {   
                formato = "pdf";
             }else if(Opcion == 2){
                formato = "excel";
            }else if(Opcion == 3)
            {
                formato = "html";
            }else{
                formato = "nada";
            }
            

            if("pdf".equals(formato)){

               byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conn);
               response.setContentType("application/pdf");
               response.addHeader("Content-Disposition", "attachment; filename=report.pdf");
               response.setContentLength(bytes.length);
               ServletOutputStream ouputStream = response.getOutputStream();
               ouputStream.write(bytes, 0, bytes.length);
               ouputStream.flush();
               ouputStream.close();
            } else if ("excel".equals(formato)){

               JRXlsExporter  exporter = new JRXlsExporter();
               ByteArrayOutputStream oStream = new ByteArrayOutputStream();
               JasperPrint jasperPrint = JasperFillManager.fillReport(reportFile.getPath(), parameters, conn);
               exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
               exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, oStream);
               exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
               exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
               exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
               exporter.exportReport();

               byte[] bytes = oStream.toByteArray();
               response.setContentType("application/vnd.ms-excel");
               response.addHeader("Content-Disposition", "attachment; filename=report.xls");
               response.setContentLength(bytes.length);
               ServletOutputStream ouputStream = response.getOutputStream();
               ouputStream.write(bytes, 0, bytes.length);
               ouputStream.flush();
               ouputStream.close();
            }else if ("html".equals(formato)){
            	
            	JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());

                 JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
                 JRHtmlExporter exporter = new JRHtmlExporter();
                 exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                 exporter.setParameter(JRExporterParameter.OUTPUT_WRITER, out);

                 exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, Boolean.FALSE);
                 exporter.exportReport();
                 out.flush();
                 conn.close();

            }
         }
         catch (JRException jre)
         {
            System.out.println("JRException:" + jre.getMessage());
         }
         catch (Exception e)
         {
            System.out.println("Exception:" + e.getMessage());
         }
         finally{
            try
            {
               conn.close();
            }
            catch (SQLException ex)
            {
               // TODO Auto-generated catch block
               ex.printStackTrace();
            }
         }
    
      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
