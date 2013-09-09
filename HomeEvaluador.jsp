<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>
<br><br>
<h4 align="center">Bienvenido <c:out value="${sessionScope.loginUsuario.nombre}" default="--" /></h4>
<div align="center">
	<a href='<c:url value="/Documentos/Evaluadores/Instructivo_SICIUD_Evaluadores.pdf"/>'><img border="0" src="<c:url value="/comp/img/InstructivoEvaluadores.gif"/>"></a>
</div>
<table align="center" >
	<tr>
		<td align="center"><img src="/siciud/comp/img/Imagen-Home.png"></td>
	</tr>
	<tr>
		<td align="center" colspan="2"><a href='<c:url value="/Documentos/InstructivoIntegrante.pdf"/>'><img border="0" src='<c:url value="/comp/img/pdf.png"/>'> Instructivo Directores de Grupos y/o Semilleros de Investigación</a></td>
	</tr>
</table>

<table width="100%">
<tr>
<td>
<iframe  width="98%" height="270px" frameborder="0" src='<c:url value="/notificaciones/adminNotificacio.x"/>' name="frameOculto" id="frameOculto">
</iframe>
</td>
</tr>
</table>
</body>
</html>