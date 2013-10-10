<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<c:import url="/general.jsp"/>
<script>

	function buscar(){
		if(document.busqueda.ano.selectedIndex!=0){
			document.busqueda.accion.value=2;
			document.busqueda.action='<c:url value="/consecutivo/llenar.jsp"/>';
			document.busqueda.submit();
		}
		else{
			alert("Por favor elija un año");
			return false;
		}
	}

</script>
<body >
	<form name="busqueda" >
	<input type="hidden" name="accion">
		<table class="tablas" align="center">
		<caption>Filtro de Busqueda</caption>
		<tr>
			<td align="center" class="renglones">Consecutivo</td>
			<td align="center"><input type="text" name="cod" maxlength="15" size="15"></td>
			<td align="center" class="renglones">Destinatario</td>
			<td align="center"><input type="text" name="destinatario"></td>
		</tr>
		<tr>
			<td align="center" class="renglones">Remitente</td>
			<td align="center"><input type="text" name="remitente"></td>
			<td align="center" class="renglones">Asunto</td>
			<td align="center"><input type="text" name="observaciones"></td>
		</tr>
		<tr>
			<td align="center" class="renglones" colspan="2">Año</td>
			<td colspan="2">
				<select class="combo" name="ano" >
				<option value="0">------</option>
				<c:forEach items="${sessionScope.listAno}" var="lista" varStatus="st">
					<option value="${lista}"><c:out value="${lista}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
		<td align="center" colspan="4"><img src='<c:url value="/comp/img/Buscar.gif"/>' onclick="buscar()"></TD>
		</tr>
		</table>
	</form>
	
	<c:if test="${requestScope.listaFiltro!=null}">
	<table class="tablas" align="center">
		<caption>Resultados de la Busqueda</caption>
		<tr>
			<td align="center" class="renglones" >Número</td>
			<td align="center" class="renglones" >Remitente</td>
			<td align="center" class="renglones" >Destinatario</td>
			<td align="center" class="renglones" >Asunto</td>
		</tr>
		<c:forEach items="${requestScope.listaFiltro}" var="lista" begin="0">
		<tr>
			<td align="center"><c:out value="${lista.cod}" /></td>
			<td align="center" style="width:140px"><c:out value="${lista.remitente}" /></td>
			<td align="center" style="width:140px"><c:out value="${lista.destinatario}" /></td>
			<td align="center" style="width:600px"><c:out value="${lista.observaciones}" /></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
</body>
</html>