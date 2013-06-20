<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
	function guardar(pqrForm){
		alert(document.pqrForm.accion.value);
		//document.pqrForm.action='<c:url value="/pqr/llenar.jsp"/>';
		document.pqrForm.accion.value=5;
		document.pqrForm.submit();
		
	}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear Persona</title> 
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="pqrForm" method="post"action='<c:url value="/pqr/servletArchivo.x"/>' enctype="multipart/form-data">
<input type="hidden" name="accion" value="5">
<table align="left" class="tablas" width="90%">
			<caption>Adjuntar Documento</caption>
			<tr>
				<td colspan="4" align="left"><c:out value="Archivo del caso" />
				</td>
				<td><input type="file" name="archivoAdjunto" size="30">
				</td>
			</tr>
			<tr>
				<td id="g1"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.pqrForm)"></td>
			</tr>
		</table>
</form>
</body>
</html>