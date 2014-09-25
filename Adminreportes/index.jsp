<%-- 
    Document   : index
    Created on : 10/06/2014, 08:38:02 AM
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
        <h1 align="center"><img src="../comp/img/TituloTextoSiciud1.png" alt="" width="300" height="78"></h1>
    <h1 align="center" dropzone="copy">Reporte General 
          Proyectos de Investigacion <br>
          Universidad Distrital Francisco Jose de Caldas</h1>
    <p align="center" dropzone="copy">En la presente pagina, se pueden generar reportes frente a los proyectos de investigaci&oacuten de la universidad distrital, teniendo en cuenta 3 tipos.    </p>
    <p align="center" dropzone="copy"><span class="cualquiera"><strong>Generar Un Reporte General  de Proyectos de Investigaci&oacuten</strong></span>    </p>
    <form name="form2" method="post" action="http://10.20.12.101:8080/jasperserver/flow.html?

_flowId=viewReportFlow&standAlone=true&_flowId=viewReportFlow&ParentFolderUri=%2Freports

%2Fgruposdeinvestigacion&reportUnit=%2Freports%2Fgruposdeinvestigacion

%2Fprueba2_1&j_username=jasperadmin&j_password=jasperadmin&decorate=no">
    <table  align="center" width="656" border="1">
         <tr bgcolor="#FFCC99">
             <th colspan="2" align="center" bgcolor="#5dad30"><strong>Reporte General</strong></th>
           </tr>
           <tr>
             <th width="296" bgcolor="#5dad30">Por Filtros</th>
             <td width="344"><center>
               <input name="button3" type="submit" id="button3" value="Aplicar Filtros">
             </center>
           </tr>
           </table>
       </form>
        
        <form name="form2" method="post" action="http://10.20.12.101:8080/jasperserver/flow.html?_flowId=viewReportFlow&standAlone=true&_flowId=viewReportFlow&ParentFolderUri=%2Freports%2Fgruposdeinvestigacion&reportUnit=%2Freports%2Fgruposdeinvestigacion%2FProyectos_Proximos_a_Vencer&j_username=jasperadmin&j_password=jasperadmin&decorate=no">
          <table  align="center" width="656" border="1">
            <tr>
              <th width="296" bgcolor="#5dad30"><p>Proyectos Pr<span class="cualquiera">&oacute</span>ximos a Vencerse</p></th>
              <td width="344"><center>
                <input name="button6" type="submit" id="button6" value="Ingresar Tiempo">
              </center>
          </table>
        </form>
<!-- 	hola mundo  -->
    <p align="center">&nbsp;</p>
    </body>
</html>
