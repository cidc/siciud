<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>

	function enviar(ac){
		document.frmComplemento.action='<c:url value="/Convocatoria/llenar.jsp"/>';
		document.frmComplemento.accion.value=ac;
		document.frmComplemento.submit();
	}

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="frmComplemento" method="post">
	<input type="hidden" name="accion" value='7'>

	<table class="tablas" align="center">
	<caption>Datos Nuevo Documento</caption>
		<tr>
			<td colspan="4">
				<table>
					<tr>
						<td class="renglones"><b>Activar</b></td>
						<td>
							<input type="checkbox" name="reqEstado">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id="ext1">
			<td class="renglones"><b>Nombre Documento</b></td>
			<td><input type="text" name="reqNombre" size="30"></td>
		</tr>
			</td>
		</tr>
		<tr>
			<td><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="enviar(17)"></td>
		</tr>
				</table>
	</form>
</body>
</html>
