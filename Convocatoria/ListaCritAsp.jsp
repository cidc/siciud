<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function enviar(buscar){
		document.listado.critId.value=buscar;
		document.listado.accion.value='30';
		document.listado.action='<c:url value="/Administrar/AdmCritAsp.x"/>';
		document.listado.submit();
	}
	function nuevoaspecto(buscar){
		document.listado.critId.value=buscar;
		document.listado.accion.value='32';
		document.listado.action='<c:url value="/Administrar/AdmCritAsp.x"/>';
		document.listado.submit();
	}
	function enviaraspecto(buscar){
		document.listado.aspId.value=buscar;
		document.listado.accion.value='34';
		document.listado.action='<c:url value="/Administrar/AdmCritAsp.x"/>';
		document.listado.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="listado" method="post">
		<input type="hidden" name="critId">
		<input type="hidden" name="aspId">
		<input type="hidden" name="accion">
        <table class="tablas" align="center" width="600px" >
        <caption >Listado de Criterios y Aspectos</caption>
        <tr>
        	<td class="renglones"><b>Modificar</b></td>
        	<td class="renglones"><b>#</b></td>
        	<td class="renglones"><b>Descripcion</b></td>
        	<td class="renglones"><b>Activar</b></td>
        	<td class="renglones"><b>Nuevo Aspecto</b></td>
        </tr>
			<c:forEach begin="0" items="${sessionScope.listaCritOBJ}" var="lista" varStatus="st">
				<tr>
					<td>
					<img src='<c:url value="/comp/img/lupa3.png"/>' onclick='enviar(<c:out value="${lista.critId}"/>)'>
					</td>
					<td class="listas"><c:out value="${lista.critId}"/></td>
					<td class="listas"><c:out value="${lista.critNombre}"/></td>
<td><input type="checkbox" name="critEstado" <c:if test="${lista.critEstado==true}">checked</c:if> disabled=true> 
				<td class="listas"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick='nuevoaspecto(<c:out value="${lista.critId}"/>)'></td>
</td>
				</tr>
<%-- --%>
			<c:forEach begin="0" items="${sessionScope.listaAspOBJ}" var="lista1" varStatus="st">
                     <c:if test='${lista.critId==lista1.critId}'>
			           <tr>
					
                                        <td>
                                        <img src='<c:url value="/comp/img/lupa3.png"/>' onclick='enviaraspecto(<c:out value="${lista1.aspId}"/>)'>
                                        </td>
					<td> </td>
                                       <%-- <td class="listas"><c:out value="${lista1.aspId}"/></td>--%>
                                        <td class="listas"><c:out value="${lista1.aspNombre}"/></td>
<td><input type="checkbox" name="aspEstado" <c:if test="${lista1.aspEstado==true}">checked</c:if> disabled=true>
					</td>
                                </tr>
			</c:if>
			</c:forEach>
			</c:forEach>
		</table>
	</form>
</body>
</html>
