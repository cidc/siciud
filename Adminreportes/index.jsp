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
        <h2 align="center">Reporte General 
          Proyectos de Investigacion <br>
        Universidad Distrital Francisco Jose de Caldas</h2>
    <p align="center" dropzone="copy">En la presente pagina, se pueden generar reportes frente a los proyectos de investigaci&oacuten de la universidad distrital, teniendo en cuenta 3 tipos.    </p>
    <form name="form1" method="post" >
    <table  align="center" width="656" border="1">
       <tr bgcolor="#FFCC99">
             <th colspan="2" align="center" bgcolor="#5dad30">Reporte General</th>
           </tr>
           <tr>
             <th width="296" height="40" bgcolor="#5dad30">Por Filtros</th>
             <td width="344"><center>
               <input name="button3" type="submit" id="button3" value="Aplicar Filtros" autofocus onclick="window.open('http://200.69.103.29:23187/jasperserver/flow.html?_flowId=viewReportFlow&standAlone=true&_flowId=viewReportFlow&ParentFolderUri=%2Freports%2Fgruposdeinvestigacion&reportUnit=%2Freports%2Fgruposdeinvestigacion%2Fprueba2_1&j_username=invitado&j_password=invitado&singlesingon=y&decorate=no', 'mywindow','menubar=1,resizable=1,location=0,width=1150,height=800')">
             </center>
      </tr>
           </table>
     </form>
        
        <form name="form2" method="post" >
          <table  align="center" width="656" border="1">
            <tr>
              <th width="296" height="40" bgcolor="#5dad30"><p>Proyectos Pr<span class="cualquiera">&oacute</span>ximos a Vencerse</p></th>
              <td width="344"><center>
                <input name="button6" type="submit" id="button6" value="Ingresar Tiempo" onclick="window.open('http://200.69.103.29:23187/jasperserver/flow.html?_flowId=viewReportFlow&standAlone=true&_flowId=viewReportFlow&ParentFolderUri=%2Freports%2Fgruposdeinvestigacion&reportUnit=%2Freports%2Fgruposdeinvestigacion%2FProyectos_Proximos_a_Vencer&j_username=invitado&j_password=invitado&decorate=no', 'mywindow','menubar=1,resizable=1,location=0,width=1150,height=400')">
              </center>
          </table>
        </form>
        <form name="form3" method="post" >
          <table  align="center" width="656" border="1">
            <tr>
              <th width="296" height="129" bgcolor="#5dad30"><p>Indicadores Proyectos de Investigaci<span class="cualquiera">&oacute</span>n</p></th>
              <td width="344"><center>
                <p>
                  <input name="button6"  type="image" id="button6" title="Visualizar Indicadores" onclick="window.open('http://200.69.103.29:23187/jasperserver/flow.html?_flowId=viewReportFlow&amp;standAlone=true&amp;_flowId=viewReportFlow&amp;ParentFolderUri=%2Freports%2FIndicadores&amp;reportUnit=%2Freports%2FIndicadores%2FIndicadores_Grupos_Investigacion&amp;j_username=invitado&amp;j_password=invitado&amp;decorate=no', 'mywindow','menubar=1,resizable=1,location=0,width=1150,height=400')" value="Indicadores" src="../comp/img/indicadores2.jpg" alt="Visualizar Indicadores" align="middle">
                </p>
              </center>
          </table>
        </form>
<!-- 	hola mundo  -->
    <p align="center">&nbsp;</p>
    </body>
</html>
