<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:useBean id="loginUsuario" class="cidc.general.login.Usuario" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

   var nav4=window.Event ? true : false;
	    function numeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	    }

   function validarEnviar(caja, formulario){
   var archivo=caja.value;
		if(archivo==""){
			alert("Debe seleccionar un Archivo para cargar");
			return false;
		}else{
			  var extension=archivo.substr(archivo.lastIndexOf('.'),archivo.length);
			   if (formulario.tipo.value == "6"){
			         if((extension!=".rar") && (extension!=".zip" && (extension!=".pdf")))
			           {alert("Extension de archivo incorrecta, debe ser .rar, .zip o .pdf");
			            return false;
			           }
			      }
			  if(formulario.tipo.value != "6") {
			           if(!(extension==".pdf"))
			             {alert("El archivo debe ser en formato PDF"); return false;}
			        }

	   if (formulario.tipo.value != "1" && formulario.tipo.value != "6")
	   {
         if (formulario.fecha.value == ""){alert("Debe resgistrar la fecha del Archivo"); return false;}
	   }
	   if (formulario.tipo.value == "3")
	    {
		  if(formulario.numero.value == ""){alert("Debe registrar el número del contrato"); return false;}
		}
		}
			 formulario.submit();
   }

         function tabs(num){
		    document.formTab.validar.value = num;
			document.formTab.submit();
		 }

</script>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Archivos Proyecto Antiguo</title>
</head>
<body>
<br>

<form name="formTab" method="post" action='<c:url value="/proyectosAntiguos/GestProyectos.x"/>'>
<input type="hidden" name="validar">
<input type="hidden" name="id" value=<c:out value="${sessionScope.proyectos.id}"/>>
<table cellspacing="0" cellpadding="0">
<tr>   <td><img src='<c:url value="/comp/img/DatosGenerales.gif"/>' onclick="tabs(6)"></td>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("4")||loginUsuario.isPerfil("1")){ %>
       		<td><img src='<c:url value="/comp/img/Docsproyectoant.gif"/>' onclick="tabs(7)"></td>
       	<%} %>
       	<%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("6")||loginUsuario.isPerfil("7")||loginUsuario.isPerfil("8")||loginUsuario.isPerfil("1")){ %>
	       <td><img src='<c:url value="/comp/img/Presupuesto.gif"/>' onclick="tabs(8)"></td>
	       <td><img src='<c:url value="/comp/img/prAprobado.gif"/>' onclick="tabs(27)"></td>
       <%} %>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("5")||loginUsuario.isPerfil("1")){ %>
       <td><img src='<c:url value="/comp/img/Investigadores.gif"/>' onclick="tabs(10)"></td>
       <%} %>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("7")||loginUsuario.isPerfil("1")){ %>
       <td><img src='<c:url value="/comp/img/Inventario.gif"/>' onclick="tabs(15)"></td>
       <td><img src='<c:url value="/comp/img/Cambios.gif"/>' onclick="tabs(19)"></td>
       <td><img src='<c:url value="/comp/img/Devoluciones.gif"/>' onclick="tabs(22)"></td>
       <%} %>
       <td><img src='<c:url value="/comp/img/Cambios.gif"/>' onclick="tabs(19)"></td>
</tr>
</table>
</form>
<br>
<form name="propuesta" method="post" action='<c:url value="/proyectosAntiguos/ArchivoServlet.x" />' enctype="multipart/form-data" accept="ISO-8859-1" accept-charset="ISO-8859-1">
<table class="tablas" align = "center" width="95%">
<CAPTION>Documentos Proyecto de Investigación Antiguo</CAPTION>
   <c:if test='${sessionScope.proyectos.nombrePropuesta==null or sessionScope.proyectos.nombrePropuesta==""}'>
   <tr> <td class="renglones" style="width: 170px"><b>Propuesta del Proyecto:</b></td>
		<td><input type="file" name="archivo1"></td>
		<td align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>'onclick="validarEnviar(document.propuesta.archivo1,propuesta)"></td> </tr>
        <tr> <td class="renglones" colspan="3"><b>Observaciones:</b></td></tr>
        <tr> <td colspan="3"><textarea class="texto" name="observacion" style="width: 99%"></textarea></td></tr>
        <tr> <td><input type="hidden" name="tipo" value="1"></td></tr>
   </c:if>
   <c:if test="${sessionScope.proyectos.nombrePropuesta!=null}">
          <tr> <td class="renglones" style="width: 170px"><b>Propuesta del Proyecto:</b></td> </tr>
	      <tr> <td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/Propuestas/${sessionScope.proyectos.nombrePropuesta}" />'>Ver Documento</a></td> </tr>
   </c:if>
   <c:if test="${sessionScope.proyectos.obserPro!=null}">
           <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
           <tr> <td colspan="6"><c:out value = "${sessionScope.proyectos.obserPro}"/></td></tr>
   </c:if>
</table>
</form>
<form name="actainicio" method="post" enctype="multipart/form-data" action='<c:url value="/proyectosAntiguos/ArchivoServlet.x" />'>
<table class="tablas" align = "center" width="95%">
 <c:if test='${sessionScope.proyectos.nombreActainicio==null or sessionScope.proyectos.nombreActainicio==""}'>
   <tr> <td class="renglones" style="width: 160px"><b>Acta de Inicio:</b></td>
     <td><input type="file" name="archivo2"></td>
     <td><input type="text" name="fecha" size="11"  class="caj" readonly="true" id="f_date_ai"></td>
     <td><button type='button' id="id_fecha_ai">...</button></td>
     <script type="text/javascript">
 			Calendar.setup({
   			inputField     :    'f_date_ai',
   			ifFormat       :    '%Y-%m-%d',
   			showsTime      :    false,
   			button         :    'id_fecha_ai',
   			singleClick    :    false,
   			step           :    1
   			})
	 </script>
     <td><input type="hidden" name="tipo" value="2"></td>
     <td align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="validarEnviar(document.actainicio.archivo2,actainicio)"></td>
   </tr>
   <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
   <tr> <td colspan="6"><textarea class="texto" name="observacion" style="width: 99%"></textarea></td></tr>
   </c:if>
   <c:if test="${sessionScope.proyectos.nombreActainicio!=null}">
           <tr> <td class="renglones" style="width: 160px"><b>Acta de Inicio:</b></td>
	      <tr> <td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/ActasInicio/${sessionScope.proyectos.nombreActainicio}" />'>Ver Documento</a></td> </tr>
   </c:if>
    <c:if test="${sessionScope.proyectos.obserAcIn!=null}">
           <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
           <tr> <td colspan="6"><c:out value = "${sessionScope.proyectos.obserAcIn}"/></td></tr>
   </c:if>
</table>
</form>
<form name="contrato" method="post" action='<c:url value="/proyectosAntiguos/ArchivoServlet.x" />' enctype="multipart/form-data" accept="ISO-8859-1" accept-charset="ISO-8859-1">
<table class="tablas" align = "center" width="95%">
 <c:if test='${sessionScope.proyectos.nombreContrato==null or sessionScope.proyectos.nombreContrato==""}'>
   <tr> <td class="renglones" style="width: 160px"><b>Contrato:</b></td>
     <td><input type="file" name="archivo3"></td>
     <td><input type="text" name="fecha" size="11" class="caj" readonly="true" id="f_date_con"></td>
     <td><button type='button' id="id_fecha_con">...</button></td>
     <script type="text/javascript">
 			Calendar.setup({
   			inputField     :    'f_date_con',
   			ifFormat       :    '%Y-%m-%d',
   			showsTime      :    false,
   			button         :    'id_fecha_con',
   			singleClick    :    false,
   			step           :    1
   			})
	 </script>
     <td class="renglones"><b>N:</b></td>
     <td><input type="text" maxlength="4" name="numero" size="8" onkeypress="return numeros(event)"></td>
     <td><input type="hidden" name="tipo" value="3"></td>
     <td align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="validarEnviar(document.contrato.archivo3,contrato)"></td>
   </tr>
   <tr> <td class="renglones" colspan="8"><b>Observaciones:</b></td></tr>
   <tr> <td colspan="8"><textarea class="texto" class="texto" name="observacion" style="width: 99%"></textarea></td></tr>
   </c:if>
    <c:if test="${sessionScope.proyectos.nombreContrato!=null}">
           <tr> <td class="renglones" style="width: 160px"><b>Contrato:</b></td>
	      <tr> <td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/Contratos/${sessionScope.proyectos.nombreContrato}" />'>Ver Documento</a></td> </tr>
   </c:if>
   <c:if test="${sessionScope.proyectos.obserCon!=null}">
           <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
           <tr> <td colspan="6"><c:out value = "${sessionScope.proyectos.obserCon}"/></td></tr>
   </c:if>
</table>
</form>

<form name="infofinal" method="post" action='<c:url value="/proyectosAntiguos/ArchivoServlet.x" />' enctype="multipart/form-data" accept="ISO-8859-1" accept-charset="ISO-8859-1">
<table class="tablas" align = "center" width="95%">
<c:if test='${sessionScope.proyectos.nombreInfofinal==null or sessionScope.proyectos.nombreInfofinal==""}'>
   <tr> <td class="renglones" style="width: 160px"><b>Informe Final:</b></td>
     <td><input type="file" name="archivo4"></td>
     <td><input type="text" name="fecha" size="11"  class="caj" readonly="true" id="f_date_if"></td>
     <td><button type='button' id="id_fecha_if">...</button></td>
     <script type="text/javascript">
 			Calendar.setup({
   			inputField     :    'f_date_if',
   			ifFormat       :    '%Y-%m-%d',
   			showsTime      :    false,
   			button         :    'id_fecha_if',
   			singleClick    :    false,
   			step           :    1
   			})
	 </script>
     <td><input type="hidden" name="tipo" value="4"></td>
     <td align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="validarEnviar(document.infofinal.archivo4,infofinal)"></td>
   </tr>
   <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
   <tr> <td colspan="6"><textarea class="texto" name="observacion" style="width: 99%"></textarea></td></tr>
    </c:if>
    <c:if test="${sessionScope.proyectos.nombreInfofinal!=null}">
            <tr> <td class="renglones" style="width: 160px"><b>Informe Final:</b></td>
	      <tr> <td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/InformesFinales/${sessionScope.proyectos.nombreInfofinal}" />'>Ver Documento</a></td> </tr>
   </c:if>
   <c:if test="${sessionScope.proyectos.obserInFin!=null}">
           <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
           <tr> <td colspan="6"><c:out value = "${sessionScope.proyectos.obserInFin}"/></td></tr>
   </c:if>
</table>
</form>

<form name="anexos" method="post" action='<c:url value="/proyectosAntiguos/ArchivoServlet.x" />' enctype="multipart/form-data" accept="ISO-8859-1" accept-charset="ISO-8859-1">
<table class="tablas" align = "center" width="95%">
   <c:if test='${sessionScope.proyectos.nombreAnexos==null or sessionScope.proyectos.nombreAnexos==""}'>
   <tr> <td class="renglones" style="width: 170px"><b>Anexos del Proyecto:</b></td>
		<td><input type="file" name="archivo6"></td>
		<td align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>'onclick="validarEnviar(document.anexos.archivo6,anexos)"></td> </tr>
        <tr> <td class="renglones" colspan="3"><b>Observaciones:</b></td></tr>
        <tr> <td colspan="3"><textarea class="texto" name="observacion" style="width: 99%"></textarea></td></tr>
        <tr> <td><input type="hidden" name="tipo" value="6"></td></tr>
   </c:if>
   <c:if test="${sessionScope.proyectos.nombreAnexos!=null}">
          <tr> <td class="renglones" style="width: 170px"><b>Anexos del Proyecto:</b></td> </tr>
	      <tr> <td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/Anexos/${sessionScope.proyectos.nombreAnexos}" />'>Ver Documento</a></td> </tr>
   </c:if>
   <c:if test="${sessionScope.proyectos.obserAnex!=null}">
           <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
           <tr> <td colspan="6"><c:out value = "${sessionScope.proyectos.obserAnex}"/></td></tr>
   </c:if>
</table>
</form>
<form name="actafinal" method="post" action='<c:url value="/proyectosAntiguos/ArchivoServlet.x" />' enctype="multipart/form-data" accept="ISO-8859-1" accept-charset="ISO-8859-1">
<table class="tablas" align = "center" width="95%">
<c:if test='${sessionScope.proyectos.nombreActafinal==null or sessionScope.proyectos.nombreActafinal==""}'>
   <tr> <td class="renglones" style="width: 160px"><b>Acta Final:</b></td>
     <td><input type="file" name="archivo5"></td>
      <td><input type="text" name="fecha" size="11"  class="caj" readonly="true" id="f_date_af"></td>
     <td><button type='button' id="id_fecha_af">...</button></td>
     <script type="text/javascript">
 			Calendar.setup({
   			inputField     :    'f_date_af',
   			ifFormat       :    '%Y-%m-%d',
   			showsTime      :    false,
   			button         :    'id_fecha_af',
   			singleClick    :    false,
   			step           :    1
   			})
	 </script>
     <td><input type="hidden" name="tipo" value="5"></td>
     <td align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="validarEnviar(document.actafinal.archivo5,actafinal)"></td>
   </tr>
   <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
   <tr> <td colspan="6"><textarea class="texto" name="observacion" style="width: 99%"></textarea></td></tr>
    </c:if>
    <c:if test="${sessionScope.proyectos.nombreActafinal!=null}">
           <tr> <td class="renglones" style="width: 160px"><b>Acta Final:</b></td>
	      <tr> <td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/ActasFinales/${sessionScope.proyectos.nombreActafinal}" />'>Ver Documento</a></td> </tr>
   </c:if>
   <c:if test="${sessionScope.proyectos.obserAcFin!=null}">
           <tr> <td class="renglones" colspan="6"><b>Observaciones:</b></td></tr>
           <tr> <td colspan="6"><c:out value = "${sessionScope.proyectos.obserAcFin}"/></td></tr>
   </c:if>
</table>
</form>
</body>
<script>
</script>
</html>