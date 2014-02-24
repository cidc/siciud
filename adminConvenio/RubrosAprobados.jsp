<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=3&idConv=${sessionScope.datoConvenio.idconvenio}"/>'><img border="0" src='<c:url value="/comp/img/convenio/Proyectos.gif"/>'></a></td>
		    <td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/convenio/Documentos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Tiempos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><img border="0" src='<c:url value="/comp/img/convenio/GruposInvClick.gif"/>'></a></td>

		</tr>
	</table>
<br>

<form action='<c:url value="/proyectosAntiguos/Llenar6.jsp"/>' name="listadoRubros">
	<input type="hidden" name="validar" value="28">
	<table align="center" class="tablas">
	<caption>Lista de rubros aprobados</caption>
		<c:if test="${!empty requestScope.listaRubros}">
			<tr>
				<th>&nbsp;</th>
				<th><b>Rubro</b></th>
				<th style="width:50px;" align="center"><b>Valor</b></th>
			</tr>
			<c:forEach begin="0" items="${requestScope.listaRubros}" var="lista" varStatus="st">
			<tr>
				<td>
					<input type="checkbox" name="idRubro" onchange="check(this,<c:out value="${st.count}" />)" value='<c:out value="${lista.codigo}" />'>
				</td>
				<td>
					<c:out value="${lista.nombre}"/>
				</td>
				<td style="width:50px;" align="right">
					<input id='rubro<c:out value="${st.count}" />' disabled style="text-align:right" maxlength="10" size="10" type="text" onkeypress="return soloNumeros(event)" name="valorRubro" value="0"></td>
			</tr>
			</c:forEach>
		</c:if>
			<tr>
				<td colspan="3" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar()"></td>
			</tr>
	</table>
</form>

</body>
</html>