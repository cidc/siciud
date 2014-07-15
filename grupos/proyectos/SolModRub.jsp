<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
function tabs(num){
	
		document.formTab.action="<c:url value='/GestionProyectos/ProyectosInvestigador.x' />";
		document.formTab.submit();
}

function guardar(num){
	if(num==30){
		document.formArchivo.action="<c:url value='/proyectos/CargarInformes.x' />";
		document.formArchivo.accion.value = num;
		document.formArchivo.submit();
	}else{
		document.formPersonal.action="<c:url value='/grupos/proyectos/llenar2.jsp' />";
    	document.formPersonal.accion.value = num;
	}
	document.formPersonal.submit();
}
</script>
</head>
<body>

<form name="formTab" method="post">
	<input type="hidden" name="accion">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><img border="0" src='<c:url value="/comp/img/tabs/General1.gif"/>'></td>
			<td><img border="0" src='<c:url value="/comp/img/tabs/Balance2.gif"/>' onclick="tabs(6)"></td>
			<td><img border="0" src='<c:url value="/comp/img/Actualizar.gif"/>' onclick="tab(29)"/></td>
		</tr>
	</table>
</form>	
<form name="formPersonal" method="post" >
	<input type="hidden" name="accion">
	<table class="tablas" align="center" width="90%">
	<caption>SOLICITUD DE MODIFICACION DE RUBROS</caption>
		<tr>
			<td>Nombre</td><td><c:out value="${sessionScope.loginUsuario.nombre}" /><td>
		</tr>
		<tr>
			<td>Tipo de Documento</td><td><c:out value="${sessionScope.loginUsuario.tipoDoc}" /></td>
		</tr>
		<tr>
			<td>No de Documento</td><td><c:out value="${sessionScope.loginUsuario.cedula}" /></td>
		</tr>
		<tr>
			<td>Correo Electrónico</td><td><input type="text" name="mail" value="<c:out value="${sessionScope.loginUsuario.mail}" />"></td>
		</tr>
		<tr>
			<td>Teléfono Móvil</td><td><input type="text" name="celular" value="<c:out value="${sessionScope.loginUsuario.celular}" />"></td>
		</tr>
		<tr>
			<td>Dirección de Correspondencia</td><td><input type="text" name="direccion" value="<c:out value="${sessionScope.loginUsuario.direccion}" />"></td>
		</tr>
		<tr>
			<td colspan="2">Por favor verifique que los datos registrados en el formulario estén actualizados, de lo contrario ingrese la informacion correcta y presione el boton Actualizar. Recuerde que en caso que el CIDC lo requiera, lo contactará por alguno de estos medios</td>
		</tr>
		<tr>
			<td colspan="2" align="center"><img border="0" src='<c:url value="/comp/img/Actualizar.gif"/>' onclick="guardar(31)" /></td>
		</tr>
	</table>
</form>
<form name="formArchivo" method="post" action="/proyectos/CargarInformes.x" enctype="multipart/form-data">
	<input type="hidden" name="accion" value="30">
	<table width="90%" align="center" class="tablas">
	<caption>CARGA DE CARTA DE SOLICITUD</caption>
		<tr><td colspan="2" align="center">mensaje de prueba</td></tr>
		<tr>
			<td>Carta de Solicitud de Modificación de Rubros</td>
			<td><input type="file" id="archivo" name="archivo" ></td>
		</tr>
		<tr><td colspan="2" align="center"><img border="0" src='<c:url value="/comp/img/CargaDoc.gif"/>' onclick="guardar(30)"/></td></tr>
	</table>
</form>
</body>
</html>