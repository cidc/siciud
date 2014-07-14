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
<div align="center">
	<fieldset style="width:90%;"><legend>Información General</legend> 
	<p align="center" class="texto1">Bienvenido al sistema de inscripción para la solicitud de apoyo económico para la apropiación social del conocimiento a partir de presentación de ponencias en modalidad oral en eventos Nacionales y/o Internacionales.</p>
	<!-- el numero 3 de la condicion hace referencia a que tanto docentes como estudiantes se pueden inscribir a la convocatoria -->
	<c:if test="${(sessionScope.datosConv.rol!=3) and (sessionScope.datosConv.rol!=requestScope.rol) }">
	<p align="center" class="lroja3">Usted no puede inscribirse en esta convocatoria debido a que su papel en el grupo/semillero  <b>NO</b> corresponde al de la convocatoria. Si usted  cree que es un error, favor modificar el campo "papel" en sus datos personales en el menú "Mis Grupos"</p>
	</c:if>
	<c:if test="${(sessionScope.datosConv.rol==requestScope.rol) or (sessionScope.datosConv.rol==3) }">
	<p align="center" class="texto1"><br>A continuación usted encontrará el listado de propuestas que ha registrado hasta el momento en el sistema de información.</p>
	<p align="center" class="texto1">Nota: al finalizar su inscripción se le notificará vía correo electrónico, en el cual encontrará un código único de inscripción, tenga en cuenta este código para preguntas, quejas o reclamos.</p>
		<div align="center">
			<a href='<c:url value="/inscripcionConv/Inscripcion.x?accion=5"/>'><img border="0" src='<c:url value="/comp/img/AgregarPropuesta.gif"/>'></a>
		</div>
	</c:if>
	</fieldset>
</div>
	<br>
	<form name="lista">
	<c:if test="${(sessionScope.datosConv.rol==requestScope.rol) or (sessionScope.datosConv.rol==3) }">
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
	</c:if>
	</form>
</body>
</html>
