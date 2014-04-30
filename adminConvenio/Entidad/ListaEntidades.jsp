<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function enviar(cod){
		document.formEntidad.idEnt.value=cod;
		document.formEntidad.validar='<c:url value="/adminEntidad/GestEntidad.x"/>';
		document.formEntidad.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">

<c:if test="${!empty requestScope.listaEntidades}">
<form name="formEntidad">
<input type="hidden" name="idEnt" value="">
<input type="hidden" name="validar"	value='4'>
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
		<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if>>
		<td class="renglones" width="10px"><b><c:out value="${st.count}" /></b></td>
		<td class="listas"><c:out value="${lista.nombre}" /></td>
		<td class="listas"><c:out value="${lista.tipo}" /></td>
		<td class="estado" align="center"><img src=<c:url value="/comp/img/Ver.gif" /> onclick='enviar(<c:out value="${lista.id}" />)'></td>
	</tr>
	</c:forEach>
</table>
</form>
</c:if>
<c:if test="${empty requestScope.listaEntidades}">
<h4 align="center">No se lograron encontrar Registros de Entidades</h4>
</c:if>
</body>
</html>
