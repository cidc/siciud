<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function enviar(ac){
		document.barra.accion.value=ac;
		document.barra.submit();
	}
	function verEvaluador(cod,tipo){
		document.ver.tipEval.value=tipo;
		document.ver.codEval.value=cod;
		document.ver.action='<c:url value="/adminEvaluador/AdminEvaluador.x"/>';
	//	alert(document.ver.tipEval.value);
		document.ver.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<br>
	<form name="barra" action='<c:url value="/adminEvaluador/AdminEvaluador.x"/>'>
	<input type="hidden" name="accion" value='0'>
	<table align="left">
			<tr>
				<td><img src='<c:url value="/comp/img/NuevoEvaluador.gif"/>' onclick="enviar(13)"></td>
			</tr>
		</table>
	</form>
	<br>
	<form name="filtro" method="post" action='<c:url value="/adminEvaluador/llenar.jsp"/>'>
		<input type="hidden" name="accion" value="1"/>

		<table class="tablas" align="center">
		<caption>Filtro de Búsqueda</caption>
			<tr>
				<td class="renglones"><b>Nombres</b></td>
				<td><input type="text" name="nombres"></td>
				<td class="renglones"><b>Apellidos</b></td>
				<td><input type="text" name="apellidos"></td>
			</tr>
			<tr>
				<td colspan="2" class="renglones"><b>Campos de Trabajo</b></td>
				<td class="renglones"><b>Orden Por:</b></td>
				<td class="renglones"><b>Tipo Evaluador</b></td>
			</tr>
			<tr>
				<td colspan="2"><input type="text" size="32" name="camposTrabajo"></td>
				<td>
					<select name="orden">
						<option value='1'>Nombres</option>
						<option value='2'>Apellidos</option>
					</select>
				</td>
				<td>
					<select name="tipEval">
						<option value='0'>-------</option>
						<option value='1'>Interno</option>
						<option value='2'>Externo</option>
					</select>
				</td>
			</tr>
			<tr>
				<td colspan="4"><input type="image" src='<c:url value="/comp/img/Buscar.gif" />'></td>
			</tr>
		</table>
	</form>

	<c:if test="${!empty requestScope.listaEvaluadores}">
	<form name="ver" method="post">
		<input type="hidden" name="accion" value="7">
		<input type="hidden" name="codEval" value="0">
		<input type="hidden" name="tipEval" value="0">
		<table align="center" class="tablas" width="550px">
			<caption>Evaluadores Encontrados</caption>
			<tr>
				<td class="renglones"><b>Código</b></td>
				<td class="renglones"><b>Nombres</b></td>
				<td class="renglones"><b>Apellidos</b></td>
				<td width="180px" class="renglones"><b>e-mail</b></td>
				<td class="renglones"><b>Tipo</b></td>
				<td class="renglones">&nbsp;</td>
			</tr>
			<c:forEach begin="0" items="${requestScope.listaEvaluadores}" var="lista">
			<tr>
				<td><c:out value="${lista.codigo}"/></td>
				<td><c:out value="${lista.nombres}"/></td>
				<td><c:out value="${lista.apellidos}"/></td>
				<td width="180px"><c:out value="${lista.mail}"/></td>
				<td><c:out value="${lista.tipoEvaluador}"/></td>
				<td><img src='<c:url value="/comp/img/Ver.gif"/>' onclick='verEvaluador(<c:out value="${lista.codigo}"/>,<c:out value="${lista.tipoEval}"/>)'></td>
			</tr>
			</c:forEach>
		</table>
	</form>
	</c:if>
</body>
</html>
