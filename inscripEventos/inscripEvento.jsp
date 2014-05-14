<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<c:import url="/general.jsp"/>
<jsp:useBean id="fecha" class="java.util.Date"/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" type="time" timeStyle="short" pattern="yyyy" var="ano"/>

</head>
	<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<br>
		<table align="center">
			<tr>
				<td align="center"><br>
					<img border="0" src="/siciud/comp/img/Imagen-Home1.png">
				</td>
			</tr>
		</table>

	</body>
</html>