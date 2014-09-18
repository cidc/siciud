<%-- 
    Document   : REPORTEGENERAL
    Created on : 10/06/2014, 08:56:08 AM
    Author     : Desarrollos
    parameters.put("Nombre del Parametro", "Valor del Parametro");
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="net.sf.jasperreports.engine.*" %>
<%@page import="java.util.*" %>
<%@page import="java.io.*" %>
<%@page import="java.sql.*" %>


<%  Connection conexion;
    Class.forName("org.postgresql.Driver").newInstance();
    conexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/siciudconv","postgres","123456");
    
    File reportFile = new File (application.getRealPath("REPORTES/REPORTGENERAL.jasper"));
    Map parameters = new HashMap();
    parameters.put("Nombre del Parametro", "Valor del Parametro");
    byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), parameters, conexion);
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outputStream = response.getOutputStream();
    outputStream.write(bytes, 0, bytes.length);
    outputStream.flush();
    outputStream.close();
    %>
