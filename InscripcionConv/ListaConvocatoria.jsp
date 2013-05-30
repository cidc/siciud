<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function buscar(buscar){
		document.listado.cod.value=buscar;
		document.listado.ver.value='1';
		document.listado.action='<c:url value="/convocatoria/Parametrizar.x" />';
		document.listado.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="listado" method="post">
		<input type="hidden" name="ver">
		<input type="hidden" name="cod">
        <table class="tablas" width="500px" >
        <caption >Listado de convocatorias almacenadas</caption>
        <tr>
        	<td class="renglones">&nbsp</td><td class="renglones"><b>Año</b></td><td class="renglones"><b>#</b></td><td class="renglones"><b>Nombre</b></td>
        </tr>
			<c:forEach begin="0" items="${sessionScope.listaConvOBJ}" var="lista" varStatus="st">
				<tr>
					<td><c:if test="${lista.convId==sessionScope.convocatoriaOBJ.convId}"><img src='<c:url value="/comp/img/ok.png"/>'></c:if>
						<c:if test="${lista.convId!=sessionScope.convocatoriaOBJ.convId}"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick="buscar('<c:out value="${lista.convId}"/>')"></c:if>
					</td>
					<td class="listas"><c:out value="${lista.convAno}"/></td>
					<td class="listas"><c:out value="${lista.convNumero}"/></td>
					<td class="listas"><c:out value="${lista.convNombre}"/></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
