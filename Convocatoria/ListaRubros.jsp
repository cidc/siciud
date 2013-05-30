<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function enviar(buscar){
		document.listado.rubId.value=buscar;
		document.listado.accion.value='21';
		document.listado.action='<c:url value="/Administrar/AdmRubros.x"/>';
		document.listado.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="listado" method="post">
		<input type="hidden" name="rubId">
		<input type="hidden" name="accion">
        <table class="tablas" align="center" width="600px" >
        <caption >Listado de Rubros</caption>
        <tr>
        	<td class="renglones"><b>Modificar</b></td>
        	<td class="renglones"><b>#</b></td>
        	<td class="renglones"><b>Descripcion</b></td>
        </tr>
			<c:forEach begin="0" items="${sessionScope.listaRubOBJ}" var="lista" varStatus="st">
				<tr>
					<td>
					<img src='<c:url value="/comp/img/lupa3.png"/>' onclick='enviar(<c:out value="${lista.rubId}"/>)'>
					</td>
					<td class="listas"><c:out value="${lista.rubId}"/></td>
					<td class="listas"><c:out value="${lista.rubNombre}"/></td>
</td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
