<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
<%session.removeAttribute("movilidad");
session.removeAttribute("requisitos");%>
</head>
<script>
	function ver(ac,id){
		document.lista.idProp.value=id;
		document.lista.accion.value=ac;
		document.lista.action='<c:url value="/inscripcionConv/Inscripcion.x"/>';
//		alert(document.lista.accion.value);
		document.lista.submit();
	}
	

</script>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
<c:if test="${!empty requestScope.listaPropuestas}">
<div align="center">
	<fieldset style="width:90%;"><legend>Información General</legend>
	<p align="center" class="texto1">Bienvenido al sistema de inscripción de propuestas de investigación, Convocatorias CIDC 2013 </p>
	<p align="center" class="lroja3">Por favor tenga en cuenta su rol dentro del sistema antes de aplicar a la convocatoria <c:out value="${sessionScope.datosConv.convAno} ${sessionScope.datosConv.convNumero}"/>.
		

		<div align="center">
			<a href='<c:url value="/inscripcionConv/Inscripcion.x?accion=5"/>'><img border="0" src='<c:url value="/comp/img/AgregarPropuesta.gif"/>'></a>
		</div>
	</fieldset>
</div>
	<br>

	<form name="lista">
	<input type="hidden" name="accion" value="0">
	<input type="hidden" name="idProp">
	<table align="center" class="tablas" width="90%">
		<caption>Lista de Propuestas inscritas</caption>
		<tr>
			<td class="renglones" align="center"><b>#</b></td>
			<td class="renglones" align="center" width="50px"><b>Código</b></td>
			<td class="renglones" align="center"><b>Nombre Propuesta</b></td>
			<td class="renglones" align="center" width="100px"><b>Documentos</b></td>
		</tr>
		<c:forEach items="${requestScope.listaPropuestas}" begin="0" var="lista" varStatus="st">
			<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				<td><c:out value="${st.count}" /></td>
				<td width="50px" align="center"><c:out value="${lista.idPropuesta}" /></td>
				<td><c:out value="${lista.nombrePropuesta}" /></td>
				<td width="100px" valign="middle"><img src='<c:url value="/comp/img/CargaDoc.gif"/>'
					onclick='ver(4,<c:out value="${lista.idPropuesta}" />)'></td>			
			</tr>
		</c:forEach>
	</table>
	</form>
</c:if>
<c:if test="${empty requestScope.listaPropuestas}">
<br><br><br>
<div align="center">
	<fieldset style="width: 90%"><legend>Información General</legend>
		<p align="center" class="texto1">Bienvenido al sistema de inscripción de propuestas de investigación, Convocatorias CIDC 2013 </p>
		<p align="center" class="texto1">En estos momentos usted no tiene ninguna propuesta inscrita para la convocatoria <c:out value="${sessionScope.datosConv.convAno} ${sessionScope.datosConv.convNumero}"/>, Favor dar clik en el botón "Agregar Propuesta" para insertar una nueva propuesta</p>
		<p align="center" class="lroja3">Por favor tenga en cuenta su rol dentro del sistema antes de aplicar a la convocatoria <c:out value="${sessionScope.datosConv.convAno} ${sessionScope.datosConv.convNumero}"/>. 
	</fieldset>
</div>
<br>	
	<div align="center">
				<a href='<c:url value="/inscripcionConv/Inscripcion.x?accion=5"/>'><img border="0" src='<c:url value="/comp/img/AgregarPropuesta.gif"/>'></a>
	</div>	
</c:if>
</body>
</html>