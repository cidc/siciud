<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>

	function enviar(ac){
		document.requisito.action='<c:url value="/Convocatoria/llenar.jsp"/>';
		document.requisito.accion.value=ac;
		document.requisito.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="requisito" action='<c:url value="/convocatoria/crear.x"/>'>
	<input type="hidden" name="accion" value='0'>
	<input type="hidden" name="reqId" value='<c:out value="${requestScope.convocatoriaOBJ.reqId}"/>'>
	<br><br>

	<table class="tablas" align="center">
	<caption>Datos Documento</caption>
		<tr>
			<td class="renglones"><b>Nombre</b></td>
			<td><input type="text" name="reqNombre" value='<c:out value="${requestScope.convocatoriaOBJ.reqNombre}"/>'></td>
			<td class="renglones"><b>Activar</b></td>
			<td><input type="checkbox" name="reqEstado" <c:if test="${requestScope.convocatoriaOBJ.reqEstado==true}">checked</c:if>>
</td>
		</tr>
			<tr>
			<td><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="enviar(18)"></td>
			</tr>
		</table>
	</form>
</body>
</html>
