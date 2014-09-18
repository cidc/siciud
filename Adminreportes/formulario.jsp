<%-- 
    Document   : formulario
    Created on : 10/06/2014, 11:47:30 AM
    Author     : Desarrollos
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    <script src="jss/SpryValidationTextField.js" type="text/javascript"></script><script>
    $("#myform").submit(function(e) {
    $("#first").hide();
    $("#second").show();
										});
    </script>
    <link href="css/SpryValidationTextField.css" rel="stylesheet" type="text/css">
    </head>
    <body>
    <h1 align="center" dropzone="copy"><img src="../img/CIDCLogo.jpg" width="300" height="78"> </h1>
    <h1 align="center" dropzone="copy">Reporte General 
    Proyectos de Investigacion <br> Universidad Distrital Francisco Jose de Caldas</h1>
    <p align="center" dropzone="copy">En la presente pagina, se pueden generar reportes frente a los proyectos de investigaci&oacuten de la universidad distrital, teniendo en cuenta 3 tipos.</p>
<p align="center" dropzone="copy"><a name="Tutorial"></a></p>
<p align="center" dropzone="copy">&nbsp;</p>
    <p align="center" ><strong>Reporte General</strong></p>
    <p align="center">
      <object type="application/pdf" data="http://localhost:8084/reportesPDF/REPORTEGENERAL.jsp" width="800" height="650" align=middle>
      </object>
    </p>
    <p align="center">Descargar  en : </p>
    <form name="form5" method="post" action="REPORTEGENERAL2.jsp">
      <table align="center" width="200" border="1" cellspacing="1">
        <tr>
          <td align="center"><select name="txtidformato5" id="txtidformato5">
            <option value="1" selected="selected">Formato PDF</option>
            <option value="2">Formato EXCEL</option>
            <option value="3">Formato HTML</option>
          </select></td>
        </tr>
        <tr>
          <td align="center"><input name="button2" type="submit" id="button2" value="Generar Reporte"></td>
        </tr>
      </table>
    </form>
    <p align="center"><span class="cualquiera">Generar Un Reporte General  de Proyectos de Investigaci&oacuten por <em><u>Facultad</u></em> y <u><em>A&ntildeo</em></u>.</span>    </p>
    <form name="form2" method="post" action="REPORTEONE.jsp">
    <table  align="center" width="487" border="1">
      <tr bgcolor="#FFCC99">
          <th colspan="2" align="center" bgcolor="#5dad30"><strong>Reporte por filtros &quot;Facultad y a&ntildeo&quot;</strong></th>
      </tr>
        <tr>
          <th width="168" bgcolor="#5dad30">Facultad</th>
          <td width="415"><center>
            <select name="txtidfacultad" id="txtidfacultad">
              <option value="2" selected="selected">Ingenieria</option>
              <option value="1">Tecnologica</option>
              <option value="3">Ambiental</option>
              <option value="4">Educación</option>
              <option value="5">Artes</option>
            </select>
          </center>
      </tr>
        <tr>
          <th width="168" bgcolor="#5dad30">A&ntildeo</th>
          <td width="415"><label for="txtidano">"Digite el A&ntildeo"</label>
            <input name="txtidano" type="text" id="txtidano" value="2014">
            <center>
            </center>
      <tr bgcolor="#FFCC99">
            <th align="center" bgcolor="#5dad30">Formato</th>
            <th align="center" bgcolor="#FFFFFF"><select name="txtidformato" id="txtidformato">
              <option value="1" selected="selected">PDF</option>
              <option value="2">EXCEL</option>
            </select></th>
        </tr>
        <tr bgcolor="#FFCC99">
          <th colspan="2" align="center" bgcolor="#FFFFFF"> <input name="button3" type="submit" id="button3" value="Generar Reporte"></th>
        </tr>
      </table>
    </form>
    
    <p align="center">Reporte de Proyectos de Investigaci&oacuten por Grupo de Investigaci&oacuten o Semillero.</p>
    
    <form name="form3" method="post" action="REPORTETHREE.jsp">

      <table align="center" width="509" border="1" cellspacing="1" 
          summary="Escoge si deseas un in
          e de proyectos de investigacion por grupo o semillero">
        <tr>
          <th colspan="2" bgcolor="#5dad30" scope="col"><strong>Informe por Grupo o Semillero</strong></th>
        </tr>
        <tr>
          <th bgcolor="#5dad30" scope="col">Seleccione Grupo o Semillero</th>
          <th scope="col" align="justify"><select name="txtidgruposem2" id="txtidgruposem2">
            <option value="1" selected="selected">Grupo de Investigacion</option>
            <option value="2">Semillero</option>
          </select></th>
        </tr>
        <tr>
          <th width="219" bgcolor="#5dad30" scope="col">Facultad</th>
          <th width="277" scope="col" align="justify"><select name="txtidfacultad2" id="txtidfacultad2">
            <option value="2" selected="selected">Ingenieria</option>
            <option value="1">Tecnologica</option>
            <option value="3">Ambiental</option>
            <option value="4">Educación</option>
            <option value="5">Artes</option>
          </select></th>
        </tr>
        <tr>
          <th bgcolor="#5dad30" scope="col">Formato</th>
          <th scope="col" align="justify" ><select name="txtidformato2" id="txtidformato2">
            <option value="1" selected="selected">PDF</option>
            <option value="2">EXCEL</option>
          </select></th>
        </tr>
        <tr>
          <td colspan="2" align="center"><input name="button4" type="submit" id="button4" value="Generar Reporte"></td>
        </tr>
      </table>
    </form>
    <p align="center">Reporte de Proyectos de Investigaci&oacuten a culminar. </p>
    <form name="form4" method="post" action="REPORTEFOUR.jsp">
      <table  align="center" width="439" border="1" cellspacing="3">
        <tr>
          <td colspan="5" align="center" bgcolor="#5dad30"><strong> Proyectos de Investigacion a culminar</strong></td>
        </tr>
        <tr>
          <th width="57" align="center" bgcolor="#5dad30" scope="col">Facultad</th>
          <th width="106" scope="col" align="center"><select name="txtidfacultad4" id="txtidfacultad4">
            <option value="2" selected="selected">Ingenieria</option>
            <option value="1">Tecnologica</option>
            <option value="3">Ambiental</option>
            <option value="4">Educación</option>
            <option value="5">Artes</option>
          </select>
            </center></th>
          <th width="50" align="center" bgcolor="#5dad30" scope="col">Tiempo</th>
          <th width="144" bgcolor="#5dad30" scope="col"> <input name="txtidtempo" type="text" id="txtidtempo" value="15"></th>
          <th width="42" scope="col">Dias</th>
        </tr>
        <tr>
          <th colspan="3" align="center" bgcolor="#5dad30" scope="col">Formato</th>
          <th align="center" scope="col"><select name="txtidformato3" id="txtidformato3">
            <option value="1" selected="selected">PDF</option>
            <option value="2">EXCEL</option>
          </select></th>
          <th align="center" scope="col">&nbsp;</th>
        </tr>
        <tr>
          <td colspan="5" align="center"><input name="button5" type="submit" id="button5" value="Generar Reporte">
            </strong></td>
        </tr>
      </table>
    </form>
    <p></p>
    <p align="center"> Reporte por codigo: </p>
    <form action="REPORTEFIVE.jsp" method="post" name="form1">
      <table align="center" width="487"  border="1">
        <tr>
          <td colspan="2" align="center" bgcolor="#5dad30"><strong>Reportes PDF con parametros JSP</strong></td>
        </tr>
        <tr>
          <td width="168" bgcolor="#5dad30" align="center"><strong>Codigo de Proyecto</strong></td>
          <td width="415"  align="center"><input name="txtidcodigo" type="text" id="txtidcodigo" value=""></td>
        </tr>
        <tr>
          <td bgcolor="#5dad30" align="center"><strong>Formato</strong></td>
          <td align="center"><select name="txtidformato4" id="txtidformato4">
            <option value="1" selected="selected">PDF</option>
            <option value="2">EXCEL</option>
            <option value="3">HTML</option>
          </select></td>
        </tr>
        <tr>
          <td colspan="2"><center>
            <input name="button" type="submit" id="button" value="Generar Reporte">
          </center></td>
        </tr>
      </table>
    </form>
    <p>&nbsp; </p>
    <script type="text/javascript">
var sprytextfield1 = new Spry.Widget.ValidationTextField("sprytextfield1");
    </script>
    </body>
</html>
