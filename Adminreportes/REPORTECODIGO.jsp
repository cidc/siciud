<%-- 
    Document   : REPORTECODIGO
    Created on : 10/06/2014, 08:56:46 AM
    Author     : Desarrollos
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="net.sf.jasperreports.engine.*" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="Clases.*" %>

<% /* Parametros para realizar la conexion*/
    Conexion_Postgresql conexion = new Conexion_Postgresql();
        //Establecemos la ruta del reporte
    File reportFile = new File (application.getRealPath("REPORTES/REPORTE1.jasper"));

    /* No enviamos parametros porque nuestro reporte no los necesita por lo que
 * escribimos cualquier cadena de texto ya que solo seguiremos el formato del
 * metodo runReportToPdf*/
    Map parameters = new HashMap();

    String idCODIGO = request.getParameter("txtidcodigo");
    
    parameters.put("CODIGO", idCODIGO);
    /* Enviamos la ruta del reporte, los parametros y la conexión (objeto Connection)*/
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion.getConnection());
/* Indicamos que la respuesta va a ser en formato .PDF*/
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outputStream = response.getOutputStream();
    outputStream.write(bytes, 0, bytes.length);
/* Limpiamos y cerramos Flujos de Salida*/
    outputStream.flush();
    outputStream.close();
%>