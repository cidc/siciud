<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script type="text/javascript">
	
</script>
<c:import url="/general.jsp"/>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
<form action="ingresoUsuario.x" method="post" target="menu">
	<input type="hidden" name="accion" value="3">
		<table width="150px" align="left">
			<tr><td colspan="3" align="center"><h2>¿Olvido su Contraseña?</h2></td></tr>
			<tr><td colspan="3">&nbsp;</td></tr>
			<tr><td colspan="3" align="center"><b>Correo Electronico</b></td></tr>
			<tr><td></td><td width="70%" align="center"><input class="cajas" type="text" name="correo" size="10"></td><td></td></tr>
			<tr><td colspan="3" align="center"><br><input type="image" src='<c:url value="/comp/img/RecuperarClave.png"/>'></td></tr> 
			<tr><td colspan="3" align="center"><br><a href="/siciud/menu.jsp"><img src="/siciud/comp/img/Atras2.gif"/></a></td></tr> 
		</table>
	</form>
</body>
</html>