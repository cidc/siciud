<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
</head>

<%-- --%>
<script>
        function ver(acc){
                document.enlace.accion.value=acc;
                document.enlace.action='<c:url value="/evaluacion/EvaluacionComite.x"/>';
                document.enlace.submit();
        }
</script>


<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
<form name="filtro" method="post" action='<c:url value="/evaluacion/EvaluacionComite.x"/>'>
		<input type="hidden" name="accion" value="0"/>
		<table align="center" class="tablas">
		<caption>Filtro de búsqueda</caption>
			<tr>
			<th><b>Año</b></th>
                                <td><select name="ano">
                                                <option value="0">----</option>
                                                <option value="2009" <c:if test="${requestScope.ano==2009}">selected</c:if>>2009</option>
                                                <option value="2010" <c:if test="${requestScope.ano==2010}">selected</c:if>>2010</option>
                                                <option value="2011" <c:if test="${requestScope.ano==2011}">selected</c:if>>2011</option>
                                                <option value="2012" <c:if test="${requestScope.ano==2012}">selected</c:if>>2012</option>
                                                <option value="2013" <c:if test="${requestScope.ano==2012}">selected</c:if>>2013</option>
                                        </select>
                                </td>

			<th><b>Tipo</b></th>
                                <td><select name="tipo">
                                                <option value="0">----</option>
                                                <option value="Movilidad" <c:if test="${requestScope.tipo==mov}">selected</c:if>>mov</option>
                                                <option value="Proyectos de Investigacion" <c:if test="${requestScope.tipo==proy}">selected</c:if>>proy</option>
				 </select>
                                </td>

			<th><b>Numero Conv</b></th>
                                </td>

				<td><img onclick="ver(2)" src='<c:url value="/comp/img/Buscar.gif"/>'></td>
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
