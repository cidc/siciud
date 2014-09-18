<%-- 
    Document   : REPORTEGENERALEXCEL
    Created on : 12/06/2014, 02:42:46 PM
    Author     : Desarrollos

    //String idFACULTAD = request.getParameter("txtidfacultad");
    //String idANO = request.getParameter("txtidano");

    parameters.put("FACULTAD", Integer.parseInt(idFACULTAD));
    parameters.put("ANO", Integer.parseInt(idANO));
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="net.sf.jasperreports.engine.*" %>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporter"%>
<%@page import="net.sf.jasperreports.engine.export.JRXlsExporterParameter"%>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>
<%@page import="net.sf.jasperreports.engine.export.*"%>
<%@page import="net.sf.jasperreports.engine.util.JRLoader"%>
<%@page import="net.sf.jasperreports.engine.JRRenderable"%>


<%  Connection conn = null;


         String idCODIGO = request.getParameter("txtidcodigo");
         String formato = "";

         try
         {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/siciudconv","postgres","123456");

            //ServletContext servletContext = this.getServletContext();
            File reportFile = new File(application.getRealPath("REPORTES/REPORTFIVE.jasper"));
            Map parameters = new HashMap();

            int Opcion = Integer.parseInt(request.getParameter("txtidformato4"));

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
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportFile.getPath());
            parameters.put("CODIGO", idCODIGO);

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
    %>
