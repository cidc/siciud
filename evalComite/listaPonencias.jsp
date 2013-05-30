<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
</head>
<script>
	function ver(ac,id){
		document.lista.idPon.value=id;
		document.lista.accion.value=ac;
		document.lista.action='<c:url value="/movilidad/evalMovilidad.x"/>';
		document.lista.submit();
	}
	function ir(){
		if(document.filtro.ano.value==0 || document.filtro.corte.value==0 || document.filtro.rol.value==0)
			alert("Favor seleccionar el año, el corte de la convocatoria y el rol a evaluar");
		else
			document.filtro.submit();
	}

</script>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>

	<form name="filtro" method="post" action='<c:url value="/movilidad/evalMovilidad.x"/>'>
		<input type="hidden" name="accion" value="0"/>
		<table align="center">
			<tr>
				<td>Año</td>
				<td><select name="ano">
						<option value="0">----</option>
						<option value="2009" <c:if test="${requestScope.ano==2009}">selected</c:if>>2009</option>
						<option value="2010" <c:if test="${requestScope.ano==2010}">selected</c:if>>2010</option>
						<option value="2011" <c:if test="${requestScope.ano==2011}">selected</c:if>>2011</option>
						<option value="2012" <c:if test="${requestScope.ano==2012}">selected</c:if>>2012</option>
					</select>
				</td>
				<td>Corte</td>
				<td><select name="corte">
						<option value="0">-</option>
						<option value="1" <c:if test="${requestScope.corte==1}">selected</c:if>>1</option>
						<option value="2" <c:if test="${requestScope.corte==2}">selected</c:if>>2</option>
						<option value="3" <c:if test="${requestScope.corte==3}">selected</c:if>>3</option>
						<option value="4" <c:if test="${requestScope.corte==4}">selected</c:if>>4</option>
						<option value="5" <c:if test="${requestScope.corte==5}">selected</c:if>>5</option>
						<option value="6" <c:if test="${requestScope.corte==6}">selected</c:if>>6</option>
						<option value="7" <c:if test="${requestScope.corte==7}">selected</c:if>>7</option>
					</select>
				</td>
				<td>Rol</td>
				<td><select name="rol">
						<option value="0">----------</option>
						<option value="1" <c:if test="${requestScope.rol==1}">selected</c:if>>Estudiante</option>
						<option value="2" <c:if test="${requestScope.rol==2}">selected</c:if>>Profesor</option>
					</select>
				</td>
				<td><img onclick="ir()" src='<c:url value="/comp/img/Buscar.gif"/>'></td>				
			</tr>
		</table>
	</form>

<c:if test="${! empty requestScope.listaPonencias}">
	<br>
	<form name="lista">
	<input type="hidden" name="accion" value="0">
	<input type="hidden" name="idPon">
	<table align="center" class="tablas" width="95%">
		<caption>Lista de Ponencias Inscritas</caption>
		<tr>
			<td class="renglones" align="center" width="10px"><b>#</b></td>
			<td class="renglones" align="center" width="170px"><b>Nombre Presentador</b></td>
			<td class="renglones" align="center"><b>Nombre Ponencia</b></td>
			<td class="renglones" align="center" width="85px"><b>Evaluar</b></td>
		</tr>
		<c:forEach items="${requestScope.listaPonencias}" begin="0" var="lista" varStatus="st">
			<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				<td width="10px"><c:out value="${lista.idPropuesta}" /></td>
				<td width="170px"><c:out value="${lista.investigador}" /></td>
				<td style="text-transform: lowercase;"><c:out value="${lista.nombrePonencia}" /></td>
				<td width="50px" align="center">
					<c:if test="${lista.estado==1}"><img src='<c:url value="/comp/img/Evaluar.gif"/>' onclick='ver(1,<c:out value="${lista.idPropuesta}" />)'></c:if>
					<c:if test="${lista.estado==2}"><img src='<c:url value="/comp/img/ok.png"/>'></c:if>
					<c:if test="${lista.estado==3}"><img src='<c:url value="/comp/img/no.png"/>'></c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>
</c:if>
<c:if test="${empty requestScope.listaPonencias}">
<br><br><br>
<div align="center">
	<h3  align="center">Favor seleccionar un año y corte de convocatoria</h3>
</div>
<br>
</c:if>
</body>
</html>
