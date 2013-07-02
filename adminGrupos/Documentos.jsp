<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="buscar" action="">
	<input type="hidden" name="accion" value="">
	<table align="center" class="tablas">
	<caption>Filtro de consulta</caption>
		<tr>
			<td class="renglones"><b>Facultad:</b></td>
			<td>
				<select name="facultad">
					<option value="0">-------------</option>
					<option value="1" >Tecnológica</option>
					<option value="2" >Ingeniería</option>
					<option value="3" >Medio Ambiente</option>
					<option value="4" >Ciencias y Educación</option>
					<option value="5" > Artes (Asab)</option>
				</select>
			</td>
			<td><b>Grupo</b><input type="radio" name="boton" value="1" ></td>
			<td><b>Semillero</b><input type="radio" name="boton" value="2" ></td>
			<td><img align="left" src="<c:url value="/comp/img/Buscar.gif"/>" onclick="buscarGrupo()"></td>
		</tr>
		<tr>
			<td><b>Seleccione un grupo o semillero</b></td>
			<td>
				<select name="grupo" onchange="grupoSeleccionado()">
				<option value="0">-------------</option>
				<c:forEach begin="0" items="${requestScope.listaGrupos}" var="lista" varStatus="st">
					<option value="<c:out value="${lista.codigo}"/>"><c:out value="${lista.nombre}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	</form>
	<table id="tablaDoc">
		<form name="" action=""></form>
	</table>
	
</body>
</html>