<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>

<script type="text/javascript">
function estado(val,id,tipo){
	document.frmNotifica.valor.value=val;
	document.frmNotifica.idNotifi.value=id;
	document.frmNotifica.submit();
}
</script>
<c:import url="/general.jsp"/>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${!empty requestScope.listaNotificaciones}">
<table width="100%">
	<tr>
		<td><p class="lroja">Las siguientes son las notificaciones que el Sistema SICIUD tiene para usted. Favor dar click en el botón Ok para confirmar que usted ha leido la notificación y click en el botón X si desea borrarla de la lista</p></td>
	</tr>
</table>
<form name="frmNotifica" action='<c:url value="/notificaciones/adminNotificacio.x"/>' method="post">
<input type="hidden" name="idNotifi" value="">
<input type="hidden" name="valor" value="">
<input type="hidden" name="accion" value="2">
</form>
<table class="tablas" align="center" width="95%">
<CAPTION>Notificaciones del Sistema</CAPTION>
	<tr>
		<th width="10px">&nbsp;</th>
		<th width="70px"><b>Fecha</b></th>
		<th><b>Descripción</b></th>
		<th><b>Asignada por</b></th>
		<th>&nbsp;</th>
	</tr>
	<c:forEach begin="0" items="${requestScope.listaNotificaciones}" var="lista" varStatus="st">
	<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
		<td width="10px"><img src='<c:url value="/comp/img/flag${lista.nivel}.gif"/>'></td>
		<td width="70px"><c:out value="${lista.fechaPublicacion}"/></td>
		<td><c:out value="${lista.descripcion}"/></td>
		<td align="center"><c:out value="${lista.asigPor}"/></td>
		<c:if test="${lista.estado==1}">
		<td><img src='<c:url value="/comp/img/Ok.png"/>' onclick="estado(2,<c:out value="${lista.idNotificacion}"/>)" ></td>
		</c:if>
		<c:if test="${lista.estado==2}">
		<td><img src='<c:url value="/comp/img/Equis.gif"/>' onclick="estado(3,<c:out value="${lista.idNotificacion}"/>)"></td>
		</c:if>
	</tr>
	</c:forEach>
</table>
</c:if>
</body>
</html>

