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
	function eliminar(buscar){
		if(confirm('¿DESEA ELIMINAR ESTA CONVOCATORIA?')){
			document.listado.cod.value=buscar;
			document.listado.ver.value='2';
			document.listado.action='<c:url value="/convocatoria/Parametrizar.x" />';
			document.listado.submit();
		}
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="listado" method="post">
		<input type="hidden" name="ver">
		<input type="hidden" name="cod">
        <table class="tablas" align="center" width="600px" >
        <caption >Listado de convocatorias almacenadas</caption>
        <tr>
        	<td class="renglones" colspan="2">&nbsp</td>
        	<td class="renglones"><b>Año</b></td>
        	<td class="renglones"><b>#</b></td>
        	<td class="renglones"><b>Nombre</b></td>
        	<td class="renglones"><b>Tipo</b></td>
        </tr>
			<c:forEach begin="0" items="${sessionScope.listaConvOBJ}" var="lista" varStatus="st">
				<tr>
					<td valign="middle"	>
						<c:if test="${lista.convId==sessionScope.convocatoriaOBJ.convId}"><img src='<c:url value="/comp/img/esc.png"/>'></c:if>
						<c:if test="${lista.convId!=sessionScope.convocatoriaOBJ.convId}"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick="buscar('<c:out value="${lista.convId}"/>')"></c:if>
					</td>
					<td>
						<img src='<c:url value="/comp/img/equis1.png"/>' onclick="eliminar('<c:out value="${lista.convId}"/>')">
					</td>
					<td class="listas"><c:out value="${lista.convAno}"/></td>
					<td class="listas"><c:out value="${lista.convNumero}"/></td>
					<td class="listas"><c:out value="${lista.convNombre}"/></td>
					<c:if test="${lista.convTipo==2}">
					<td class="listas">Movilidad</td>
					</c:if>
					<c:if test="${lista.convTipo==1}">
					<td class="listas">Proy. Inv</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>
