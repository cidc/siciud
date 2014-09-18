<%-- 
    Document   : FormularioSimple
    Created on : 16/07/2014, 11:59:17 AM
    Author     : Desarrollos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <h1 align="center" dropzone="copy"> Reporte General <br>Proyectos de Investigacion <br> Universidad Distrital Francisco Jose de Caldas</h1>
    <p align="center" dropzone="copy">En la presente pagina, se exponen 3 tipos de Busqueda </p>

    <form action="REPORTECODIGO.jsp" method="post" name="form1">
      <table width="487" align="center"  border="1">
          <tr>
            <td colspan="2" align="center"><strong>Reportes PDF con parametros JSP</strong></td>
          </tr>
          <tr>
            <td width="168">INGRESE CODIGO:</td>
            <td width="415"><label for="txtidcliente">txtidcliente</label>
        	<input type="text" name="txtidcliente" id="txtidcliente"></td>
          </tr>
          <tr>
          	<td>&nbsp;</td>
            <td><input name="button" type="submit" id="button" value="Generar Reporte"> </td>
          </tr>
      </table>
    </form>
    </body>
</html>
