<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
<script>

	function enviar(cod){
		document.frmConvenio.idConv.value=cod;
		document.frmConvenio.action='<c:url value="/adminEntidad/GestEntidad.x"/>';
		document.frmConvenio.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">

<c:if test="${!empty requestScope.listaEntidades}">
<form name="frmConvenio">
<input type="hidden" name="idConv" value="">
<input type="hidden" name="accion"	value='3'>
<table class="tablas" align="center" width="90%">
	<caption>Lista de entidades insertadas</caption>
	<tr>
		<td class="renglones" width="10px"><b>#</b></td>
		<td class="renglones"><b>Nombre Entidad</b></td>
		<td class="renglones" width="30px"><b>Tipo</b></td>
		<td class="renglones" width="30px"><b>Ver</b></td>
	</tr>
	<c:forEach begin="0" items="${requestScope.listaEntidades}" var="lista" varStatus="st">
	<tr>
		<td class="renglones" width="10px"><b><c:out value="${st.count}" /></b></td>
		<td><c:out value="${lista.nombre}" /></td>
		<td><c:out value="${lista.tipo}" />		</td>
		<td><img src=<c:url value="/comp/img/Ver.gif" /> onclick='enviar(<c:out value="${lista.id}" />)'></td>
	</tr>
	</c:forEach>
</table>
</form>
</c:if>
<c:if test="${!empty requestScope.listaEntidades}">
</c:if>
</body>
</html>
