<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
</head>
<script>
	function ir(){
		if(document.filtro.ano.value==0)
			alert("Favor seleccionar el año y numero de la convocatoria");
		else
			document.filtro.submit();
	}

</script>

<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
	<form name="filtro" method="post" action='<c:url value="/movilidad/EvaluacionComite.x"/>'>
		<input type="hidden" name="accion" value="1"/>
		<table align="center">
			<tr>
				<td>Año</td>
				<td><select name="ano">
						<option value="0">----</option>
						<option value="2009" <c:if test="${requestScope.ano==2009}">selected</c:if>>2009</option>
						<option value="2010" <c:if test="${requestScope.ano==2010}">selected</c:if>>2010</option>
						<option value="2011" <c:if test="${requestScope.ano==2011}">selected</c:if>>2011</option>
						<option value="2012" <c:if test="${requestScope.ano==2012}">selected</c:if>>2012</option>
					</select>
				</td>
				</td>
				<td><img onclick="ir()" src='<c:url value="/comp/img/Buscar.gif"/>'></td>				
			</tr>
		</table>
	</form>
<br><br><br>
<div align="center">
	<h3  align="center">Favor seleccionar un año</h3>
</div>
<br>
</body>
</html>
