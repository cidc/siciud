<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
function buscarCaso(){
	if(validar()){
		document.pqrConsult.action='<c:url value="/pqr/servlet.x"/>';
		document.pqrConsult.accion.value=4;
		document.pqrConsult.submit();
	}
}

function validar(){
	mensaje="";
	if(document.pqrConsult.idCaso.value==""){
		mensaje+="-) Ingrese el número del caso \n";
	}
	if(document.pqrConsult.cedula.value==""){
		mensaje+="-) Ingrese el número de cédula \n";
	}
	if(mensaje!=""){
		alert("Los siguientes campos son obligatorios: \n"+mensaje);
		return false
	}
	return true;
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atención al Ciudadano</title> 
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="pqrConsult"  method="post" action='<c:url value="/pqr/llenar.jsp.x"/>' >
<input type="hidden" name="accion" value="4">
<table align="left" class="tablas" width="90%">
	<caption>Consultar Petición</caption>
	<tr>
		<td colspan="4" align="left"><c:out value="Número del caso"/>
		</td>
		<td  align="left"><INPUT NAME="idCaso" MAXLENGTH="25" TYPE="TEXT" VALUE=''> 
		</td>
	</tr>
	<tr>
		<td colspan="4" align="left"><c:out value="Número de Cédula"/></td>
		<td align="left"><INPUT NAME="cedula" MAXLENGTH="25" TYPE="TEXT" VALUE=''> </td>
	</tr>
	<tr>
		<td id="g1"><img src='<c:url value="/comp/img/Buscar.gif"/>' onclick="buscarCaso()"></td>
	</tr>
	<c:if test="${requestScope.respuesta!=null}">
		<tr>
					<td colspan="4" align="left" ><c:out value="Estado de la Solicitud"/>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%"  rows="15" 
							name="respuesta" id='respuesta'><c:out value='${requestScope.respuestaConsul}'/></textarea>
					</td>
		</tr>
	</c:if>
		<tr><c:if test="${requestScope.archRespuesta!=null}">
			<td align="right"><a target="_blank" href='<c:out value="/siciud/Documentos/Bizagi/${requestScope.archRespuesta}"/>'><img align="center" src="<c:url value="/comp/img/verPdf.png"/>"/></a></td>
			</c:if>
		</tr>
</table>
</form>
</body>
</html>