<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function ver(id,tipo){
		document.listado.id.value=id;
		document.listado.tipo.value=tipo;
		document.listado.action='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x"/>';
		document.listado.target="main";
		document.listado.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<c:if test="${!empty sessionScope.listaProyectos}">
	<form name="listado" method="post">
		<input type="hidden" name="accion" value="2"/>
		<input type="hidden" name="id" value="0">
		<input type="hidden" name="tipo" value="0">
        <table class="tablas" width="90%" align="center">
        <caption >Listado de Proyectos Vigentes</caption>
        <tr>
	        <td width="10px" class="renglones" align="center"><b>#</b></td>
	       	
	       	<td width="80px" class="renglones" align="center"><b>Codigo</b></td>
        	<td width="150px" class="renglones" align="center"><b>Director</b></td>
        	<td class="renglones" align="center"><b>Nombre Proyecto</b></td>
        	<td class="renglones" align="center"><b>Ver</b></td>
        </tr>
	<c:forEach begin="0" items="${sessionScope.listaProyectos}" var="lista" varStatus="st">
		<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if>>
			<td width="10px" class="renglones"><b><c:out value="${st.count}"/></b></td>
			
			<td width="80px" class="listas"><c:out value="${lista.codigo}"/></td>
			<td width="150px" class="listas"><c:out value="${lista.director}"/></td>
			<td class="listas"><c:out value="${lista.proyecto}"/></td>
			<td class="estado" align="center"><img src='<c:url value="/comp/img/VerProy.gif"/>' onclick="ver(<c:out value="${lista.id}"/>,<c:out value="${lista.claseProyecto}"/>)"></td>
		</tr>
	</c:forEach>
		</table>
	</form>
</c:if>
	<c:if test="${empty sessionScope.listaProyectos}">
	<h4 align="center">No hay proyectos para este filtro de selección</h4>
	</c:if>
</body>
</html>