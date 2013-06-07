<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script>
function buscarPersona(){
	document.pqrForm.action='<c:url value="/pqr/llenar.jsp"/>';
	document.pqrForm.accion.value=4;
	document.pqrForm.submit();
}
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atención al Ciudadano</title> 
</head>
<body>
<form name="pqrConsult"  method="post" action='<c:url value="/pqr/llenar.jsp.x"/>' >
<input type="hidden" name="accion" value="1">
<table align="left" class="tablas" width="90%">
	<caption>Consultar Petición</caption>
	<tr>
		<td colspan="4" align="left"><c:out value="Número del caso"/>
		</td>
		<td  align="left"><INPUT NAME="idCaso" MAXLENGTH="25" TYPE="TEXT" VALUE=''> 
		</td>
	</tr>
	<tr>
		<td id="g1"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.pqrConsult)"></td>
	</tr>
		<tr>
					<td colspan="4" align="left"><c:out value="Estodo de la Solicitud"/>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="respuesta" id='respuesta'><c:out value='${sessionScope.respuestaConsul.respuestaCaso}'/></textarea>
					</td>
		</tr>
</table>
</form>
</body>
</html>