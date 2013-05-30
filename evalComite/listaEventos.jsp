<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
</head>
<script>
	function ver(eve){
		document.lista.evento.value=eve;
		document.lista.action='<c:url value="/movilidad/EvalMovilidadComite.x"/>';
		document.lista.submit();
	}

	function ir(){
		if(document.filtro.ano.value==0 || document.filtro.corte.value==0)
			alert("Favor seleccionar el año y el corte de la convocatoria");
		else
			document.filtro.submit();
	}
</script>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>

	<form name="filtro" method="post" action='<c:url value="/movilidad/EvalMovilidadComite.x"/>'>
		<input type="hidden" name="accion" value="1"/>
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
					</select>
				</td>
				<th><b>Corte</b></th>

				<td><select name="corte">
						<option value="0">-</option>
						<option value="1" <c:if test="${requestScope.corte==1}">selected</c:if>>1</option>
						<option value="2" <c:if test="${requestScope.corte==2}">selected</c:if>>2</option>
						<option value="3" <c:if test="${requestScope.corte==3}">selected</c:if>>3</option>
						<option value="4" <c:if test="${requestScope.corte==4}">selected</c:if>>4</option>
						<option value="5" <c:if test="${requestScope.corte==5}">selected</c:if>>5</option>
						<option value="6" <c:if test="${requestScope.corte==6}">selected</c:if>>6</option>
						<option value="7" <c:if test="${requestScope.corte==7}">selected</c:if>>7</option>
				</td>
				<td><img onclick="ir()" src='<c:url value="/comp/img/Buscar.gif"/>'></td>
			</tr>
		</table>
	</form>

<c:if test="${! empty requestScope.infoEvento}">
	<br>
	<form name="lista">
	<input type="hidden" name="accion" value="3">
	<input type="hidden" name="evento">
	<input type="hidden" name="ano" value='<c:out value="${requestScope.ano}" />'>
	<input type="hidden" name="corte" value='<c:out value="${requestScope.corte}" />'>
	<table align="center" class="tablas">
		<caption>Lista de Ponencias Inscritas</caption>
		<tr>
			<td class="renglones" align="center" width="10px"><b>#</b></td>
			<td class="renglones" align="center" width="170px"><b>Nombre Evento</b></td>
			<td class="renglones" align="center"><b>Página Web</b></td>
			<td class="renglones" align="center"><b>Cant.</b></td>
			<td class="renglones" align="center" width="50px"><b>Evaluar</b></td>
		</tr>
		<c:forEach items="${requestScope.infoEvento}" begin="0" var="lista" varStatus="st">
			<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				<td width="10px"><c:out value="${st.count}" /></td>
				<td width="170px" style="text-transform:lowercase;"><c:out value="${lista.nombreEvento}" /></td>
				<td width="50px" align="center"><a href='<c:out value="${lista.pagWeb}" />' target="_new"><c:out value="${lista.pagWeb}" /></a></td>
				<td width="50px" align="center"><c:out value="${lista.cant}" /></td>
				<td width="18px" valign="middle" align="center"><img src='<c:url value="/comp/img/Ver.gif"/>'
					onclick='ver("<c:out value="${lista.nombreEvento}" />")'></td>
			</tr>
		</c:forEach>
	</table>
	</form>
</c:if>
<c:if test="${empty requestScope.infoEvento}">
<br><br><br>
<div align="center">
	<h3  align="center">Favor seleccionar un año y corte de convocatoria (eventos)</h3>
</div>
<br>
</c:if>
</body>
</html>
