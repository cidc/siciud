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


<%  Connection conn = null;

         String idFACULTAD = request.getParameter("txtidfacultad");
         String idANO = request.getParameter("txtidano");
         String formato = "";

         try
         {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/siciudconv","postgres","123456");

            //ServletContext servletContext = this.getServletContext();
            File reportFile = new File(application.getRealPath("REPORTES/REPORTTWO.jasper"));
            Map parameters = new HashMap();

            int Opcion = Integer.parseInt(request.getParameter("txtidformato"));

            if(Opcion == 1 )
            {
                System.out.println("txtidformato es igual a " + request.getParameter("txtidformato"));
                formato = "pdf";
                System.out.println("se ha escogido PDF");
             }else if(Opcion == 2){
                formato = "excel";
                System.out.println("se ha escogido EXCEL");
            }else{
                System.out.println("txtidformato es igual a " + request.getParameter("txtidformato"));
                System.out.println("Y Opcion es igual a " + Opcion);
                formato = "nada";
                System.out.println("ningun Formato");
            }

            parameters.put("FACULTAD", Integer.parseInt(idFACULTAD));
            parameters.put("ANO", Integer.parseInt(idANO));

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
