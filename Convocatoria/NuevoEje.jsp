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
	<caption>Datos Nuevo Eje</caption>
		<tr>
			<td colspan="4">
			</td>
		</tr>
		<tr id="ext1">
			<td class="renglones"><b>Id Eje</b></td>
			<td><input type="integer" name="ejeId" size="30"></td>
		</tr>
                <tr>
                        <td colspan="4">
                                <table>
                                        <tr>
                                                <td class="renglones"><b>Activar</b></td>
                                                <td>
                                                        <input type="checkbox" name="ejeEstado">
                                                </td>
                                        </tr>
                                </table>
                        </td>
                </tr>

		<tr id="ext1">
			<td class="renglones"><b>Nombre Eje</b></td>
			<td><input type="text" name="ejeNombre" size="30"></td>
		</tr>
			</td>
		</tr>
		<tr>
			<td><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="enviar(26)"></td>
		</tr>
				</table>
	</form>
</body>
</html>
