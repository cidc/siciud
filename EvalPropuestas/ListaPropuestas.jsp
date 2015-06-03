<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:import url="/general.jsp"/>
<script>
	function ver(id){
		document.evaluar.id.value=id;
		document.evaluar.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br><br>
<c:if test="${!empty requestScope.propuestas}">
<form name="evaluar" action='<c:url value="/evalPropuestas/GestEvaluacion.x"/>'>
	<input type="hidden" name="accion" value="1">
	<input type="hidden" name="id" value='0'>
	<input type="hidden" name="psw" value='0'>
	<table width="95%" class="tablas" align="center">
	<caption>Propuestas de Investigacion a evaluar</caption>
		<tr>
		<td class="renglones" align="center"><b>Ok</b></td>
		<td class="renglones" align="center"><b>Doc.</b></td>
		<td class="renglones" align="center"><b>Propuesta de Investigación</b></td>
		<td class="renglones" align="center"><b>Formulario</b></td>

		</tr>
		<c:forEach begin="0" items="${requestScope.propuestas}" var="lista" varStatus="vs">
			<tr>
			<c:if test="${lista.estado==1}">

				<td width="30px" align="center">
					<img src='<c:url value="/comp/img/no.png"/>'>
				</td>
				</c:if>
				<c:if test="${lista.estado==0}">
					<td width="30px" align="center">
						<img src='<c:url value="/comp/img/ok.png"/>'>
					</td>
				</c:if>
				<td width="30px" align="center">
					<a class="icono" href='<c:url value="/Documentos/Propuestas/${lista.id}Proy16_${lista.id}.pdf"/><c:out value="${lista.urlArchivo}"/>'>
						<img border="0" src='<c:url value="/comp/img/pdf.png"/>'>
					</a>
				</td>
				<td>
					<c:out value="${lista.nombre}"/>
				</td>
				<c:if test="${lista.estado==1}">
				<td width="30px" align="center">
					<img src='<c:url value="/comp/img/Evaluar.png"/>' onclick="ver(<c:out value="${lista.id}" />,<c:out value="${vs.count}"/>)">
				</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
</form>
</c:if>
<c:if test="${empty requestScope.propuestas}">
<h4 align="center">No hay Propuestas de Investigación asignadas para usted en este momento</h4>
</c:if>
</body>
</html>