<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>

</head>

<body onLoad="mensajeAlert(document.getElementById('msg'));">

        <table align="left">
                        <tr>
<a href='<c:url value="/Administrar/AdmCritAsp.x?accion=31"/>'>
        <img border="0" src="<c:url value="/comp/img/NuevoEvaluador.gif"/>">
                                </a>
                        </tr>
                </table>

	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr><td width="100%" valign='top'>
			<div align="center" style="width:100%;height:190px;overflow:auto;vertical-align:top;">
				<c:import url="/convocatoria/ListaCritAsp.x"/>
			</div>
		</td></tr>
	</table>
	<table cellpadding="0" cellspacing="0">
	</table>
</body>
</html>
