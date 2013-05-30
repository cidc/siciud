<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
</head>
<script>
	function ver(acc){
		document.enlace.accion.value=acc;
		document.enlace.action='<c:url value="/movilidad/EvalMovilidadComite.x"/>';
		document.enlace.submit();
	}
</script>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<br>
	<form name="enlace" method="post" action='<c:url value="/movilidad/EvalMovilidadComite.x"/>'>
		<input type="hidden" name="accion" value="0"/>
		<table align="center">
			<tr>
				<td><img onclick="ver(1)" src='<c:url value="/comp/img/EvalEventos.gif"/>'></td>
				<td><img onclick="ver(2)" src='<c:url value="/comp/img/EvaluarDefinitiva.gif"/>'></td>
			</tr>
		</table>
	</form>
</body>
</html>
