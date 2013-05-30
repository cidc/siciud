<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>

	function enviar(ac){
		document.requisito.action='<c:url value="/adminPropuestas/llenar1.jsp"/>';
		document.requisito.accion.value=ac;
		document.requisito.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="requisito" action='<c:url value="/convocatoria/crear.x"/>'>
	<input type="hidden" name="accion" value='0'>
	<input type="hidden" name="codPropuesta" value='<c:out value="${requestScope.propuestaOBJ.codPropuesta}"/>'>
	<br><br>

	<table class="tablas" align="center">
	<caption>Datos Documentoss</caption>
		<tr>
			<td class="renglones"><b>Nombre</b></td>
			<td><input type="text" name="docNombre" value='<c:out value="${requestScope.propuestaOBJ.docNombre}"/>'></td>
                       <%-- <td class="renglones"><b>Activar</b></td>
                        <td><input type="checkbox" name="comEstado" <c:if test="${requestScope.convocatoriaOBJ.comEstado==true}">checked</c:if>>
</td>--%>

			<td><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="enviar(24)"></td>
			</tr>
<c:forEach begin="0" items="${sessionScope.listaDocOBJ}" var="lista" varStatus="st">
<td class="listas" width="25px"><c:out value="${lista.codPropuesta}"/></td>
<td class="listas" width="25px">
<a href="/siciud/Documentos/Convocatorias/Requisito/<c:out value="${lista.docNombre}"/>"><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a>
<c:out value="${lista.docNombre}"/></td>
</c:if>
</c:forEach>
		</table>
	</form>
</body>
</html>
