<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
</head>
<script>
	function guardar(){
		if(ValidarFormulario()){
			if(confirm("¿Desea asignar este consecutivo?")){
				document.ingresar.action='<c:url value="/Consecutivo/correspondencia.x"/>';
				document.ingresar.submit();
			}else
				return false;
		}
	}
	
	function ValidarFormulario(){
		if(document.ingresar.destinatario.value==""){
			alert("Por favor diligencie todos los campos");
			return false;
		}else if(document.ingresar.observaciones.value==""){
			alert("Por favor diligencie todos los campos");
			return false;
		}
		return true;
	}
	
	function buscar(){
		document.ingresar.action='<c:url value="/consecutivo/Buscar.jsp"/>';
		document.ingresar.submit();
	}
</script>
<body>
<br/>
<br/>
<br/>
<img src="<c:url value="/comp/img/Buscar.gif"/>" onclick="buscar()">

	<table align="center" class="tablas">
		<caption>Consecutivos de Correspondencia</caption>
		<tr>
			<td align="center" class="renglones" >Número</td>
			<td align="center" class="renglones" >Remitente</td>
			<td align="center" class="renglones" >Destinatario</td>
			<td align="center" class="renglones" >Asunto</td>
		</tr>
		<c:forEach items="${requestScope.listaConsecutivos}" var="lista" begin="0">
		<tr>
			<td align="center"><c:out value="${lista.cod}" /></td>
			<td align="center" style="width:140px"><c:out value="${lista.remitente}" /></td>
			<td align="center" style="width:140px"><c:out value="${lista.destinatario}" /></td>
			<td align="center" style="width:600px"><c:out value="${lista.observaciones}" /></td>
		</tr>
		</c:forEach>
	</table>
<br/>
	<form name="ingresar">
	<input type="hidden" name="accion" value="1">
	<table align="center" class="tablas">
		<caption>Nuevo Registro</caption>
		<tr>
			<td align="center" class="renglones" >Destinatario</td>
			<td align="center" class="renglones" >Asunto</td>
		</tr>
		<tr>
			<td><input type="text" name="destinatario" maxlength="50" size="50"/></td>
			<td style="width:400px"><textarea name="observaciones" rows="2" maxlength="140" size="140"></textarea></td>
		</tr>
		<tr>
			<td align="center" colspan="3"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"></td>
		</tr>
	</table>
	</form>
</body>
</html>