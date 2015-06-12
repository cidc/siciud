<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<jsp:useBean id="globales" class="cidc.general.obj.Globales" scope="page" />
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/tablas.css"/>">
<c:import url="/general.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function comando(id){
	document.frmListaCompr.accion.value=id;
}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form action="" name="frmListaCompr">
<table>
			<tr>
				<td><input type="image" src='<c:url value="/comp/img/RegistraGasto.gif" />' onclick="comando(27)"></td>				
			</tr>
	</table>
<div align="center">
	<input type="hidden" name="idRubro" value='<c:out value="${requestScope.rubro.idRubro}"/>'>
	<input type="hidden" name="accion" value=''> 
	<fieldset style="width:1000px;">
	<c:if test="${!empty requestScope.listaCompr}">
	<table align="center" class="tablas" width="95%">
	<caption>Lista de Presupuesto Comprometido</caption>
	<tr>
		<th><b>Valor</b></th>
		<th><b>No. de Necesidad</b></th>
		<th><b>Fecha de Necesidad</b></th>
		<th><b>No. CDP</b></th>
		<th><b>Fecha de CDP</b></th>
		<th><b>No. CRP</b></th>
		<th><b>Fecha de RP</b></th>
		<th><b>Observaciones</b></th>
		<th><b>Modificar</b></th>
	</tr>
	<c:forEach items="${requestScope.listaCompr}" var="lista">
	<tr>
		<td><c:out value="${lista.valorCompr}"/></td>
		<td><c:out value="${lista.numNecesidad}"/></td>
		<td><c:out value="${lista.fechaNecesidad_d}"/></td>
		<td><c:out value="${lista.numCDP}"/></td>
		<td><c:out value="${lista.fechaCDP_d}"/></td>
		<td><c:out value="${lista.numRP}"/></td>
		<td><c:out value="${lista.fechaRP_d}"/></td>
		<td><c:out value="${lista.observaciones}"/></td>
		<td><img src='<c:url value="/comp/img/Editar.png"/>' title="Modificar" onclick=""/></td>
	</tr>
	</c:forEach>
	</table>
	<img id="bGuardar" alt="Guardar" src="<c:url value="/comp/img/Guardar.gif" />" title="Guardar" onClick="guardar()" style="align:center; visibility: hidden" >
	</c:if>
	<c:if test="${empty requestScope.listaCompr}">
		<h4 align="center">No hay presupuesto comprometido registrado para este rubro</h4>
	</c:if>
	</fieldset>
</div>
</form>
<c:if test="${requestScope.bandera!=null}">
<script language="javaScript">
	parent.location.href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=7"/>';
</script>
</c:if>
</body>
</html>