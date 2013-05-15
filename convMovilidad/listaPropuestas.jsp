<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
<%session.removeAttribute("movilidad");
session.removeAttribute("requisitos");
%>
</head>
<script>
	function ver(ac,st,id){
		document.lista.accion.value=ac;
		document.lista.id.value=id;
		document.lista.estado.value=st;
		document.lista.action='<c:url value="/movilidad/adminMovilidad.x"/>';
		document.lista.submit();
	}

</script>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
<c:if test="${!empty requestScope.listaMovilidad}"> 
<div align="center">
	<fieldset style="width:90%;"><legend>Información General</legend>
	<p align="center" class="texto1">Bienvenido al sistema de inscripción para la solicitud de apoyo económico para la apropiación social del conocimiento a partir de presentación de ponencias en modalidad oral en eventos Nacionales y/o Internacionales.</p>
	<c:if test="${(sessionScope.datosConv.convNumero==1 or sessionScope.datosConv.convNumero==3) and (sessionScope.persona.papel!=3 and sessionScope.persona.papel!=5)}">
	<p align="center" class="lroja3">Usted no puede inscribirse en esta convocatoria debido a que su papel en el grupo <b>NO</b> es el de <b>Estudiante</b> de la Universidad Distrital. Si usted es profesor, Favor ingresar a la convocatoria de docentes. Si usted es estudiante, favor modificar el campo "papel" en sus datos personales en el menú "Mis Datos"</p>
	</c:if>
	<c:if test="${(sessionScope.datosConv.convNumero==1 or sessionScope.datosConv.convNumero==3) and (sessionScope.persona.papel==3 or sessionScope.persona.papel==5)}">
	<p align="center" class="texto1"><br>A continuación usted encontrará el listado de propuestas que ha registrado hasta el momento en el sistema de información.</p>
	<p align="center" class="texto1">Si desea una copia de su inscripción, puede dar click en el botón <img border="0" src='<c:url value="/comp/img/mail.gif"/>'> y esta le será enviada por correo electrónico a la dirección que tiene registrada en el sistema.</p>
	<p align="center" class="texto1">Nota: al enviar su inscripción vía correo electrónico, usted encontrará también un código único de su inscripción, tenga en cuenta este código para preguntas, quejas o reclamos.</p>
	<div align="center">
		<a href='<c:url value="/convMovilidad/Insercion.jsp"/>'><img border="0" src='<c:url value="/comp/img/AgregarPropuesta.gif"/>'></a>
	</div>
	</c:if>

	<c:if test="${(sessionScope.datosConv.convNumero==2 or sessionScope.datosConv.convNumero==4||sessionScope.datosConv.convNumero==12) and sessionScope.persona.papel!=1 and sessionScope.persona.papel!=2 and sessionScope.persona.papel!=4  and sessionScope.persona.papel!=6}">
	<p align="center" class="lroja3">Usted no puede inscribirse en esta convocatoria debido a que su papel en el grupo <b>NO</b> es el de <b>Profesor</b> de la Universidad Distrital. Si usted es estudiante, Favor ingresar a la convocatoria de Estudiantes. Si usted es profesor, favor modificar el campo "papel" en sus datos personales en el menú "Mis Datos"</p>
	</c:if>
	<c:if test="${(sessionScope.datosConv.convNumero==2 or sessionScope.datosConv.convNumero==4 ||sessionScope.datosConv.convNumero==12) and (sessionScope.persona.papel==1 or sessionScope.persona.papel==2 or sessionScope.persona.papel==4  or sessionScope.persona.papel==6)}">
	<p align="center" class="texto1"><br>A continuación usted encontrará el listado de propuestas que ha registrado hasta el momento en el sistema de información.</p>
	<p align="center" class="texto1">Si desea una copia de su inscripción, puede dar click en el botón <img border="0" src='<c:url value="/comp/img/mail.gif"/>'> y esta le será enviada por correo electrónico a la dirección que tiene registrada en el sistema.</p>
	<p align="center" class="texto1">Nota: al enviar su inscripción vía correo electrónico, usted encontrará también un código único de su inscripción, tenga en cuenta este código para preguntas, quejas o reclamos.</p>
	<div align="center">
		<a href='<c:url value="/convMovilidad/Insercion.jsp"/>'><img border="0" src='<c:url value="/comp/img/AgregarPropuesta.gif"/>'></a>
	</div>
	</c:if>

	</fieldset>
</div>
	<br>

	<form name="lista">
	<input type="hidden" name="accion" value="3">
	<input type="hidden" name="id">
	<input type="hidden" name="estado">
	<c:if test="${((sessionScope.datosConv.convNumero==1 or sessionScope.datosConv.convNumero==3 )and (sessionScope.persona.papel==3 or sessionScope.persona.papel==5))or((sessionScope.datosConv.convNumero==4 or sessionScope.datosConv.convNumero==12)and (sessionScope.persona.papel!=3 and sessionScope.persona.papel!=5))}">
		<table align="center" class="tablas" width="90%">
		<caption>Lista de Propuestas inscritas</caption>
		<tr>
			<td class="renglones" align="center"><b>#</b></td>
			<td class="renglones" align="center"><b>Nombre Propuesta</b></td>
			<td width="140px" class="renglones" align="center"><b>Lugar</b></td>
			<td class="renglones" align="center" width="100px"><b>Documentos</b></td>
			<td class="renglones" align="center" width="18px"><b>Mail</b></td>
		</tr>
		<c:forEach items="${requestScope.listaMovilidad}" begin="0" var="lista" varStatus="st">
			<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				<td><c:out value="${st.count}" /></td>
				<td><c:out value="${lista.nombrePonencia}" /></td>
				<td width="140px"><c:out value="${lista.pais}" /> -<c:out value="${lista.ciudad}" /></td>
				<td width="100px" valign="middle">
					<img src='<c:url value="/comp/img/Modificar.gif"/>' onclick='ver(9,1,<c:out value="${lista.idPropuesta}" />)'>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	</form>
</c:if>
<c:if test="${empty requestScope.listaMovilidad}">
<br><br><br>
<div align="center">
	<fieldset style="width: 90%"><legend>Información General </legend>
		<p align="center" class="texto1">Bienvenido al sistema de inscripción para la solicitud de apoyo económico para la apropiación social del conocimiento a partir de presentación de ponencias en modalidad oral en eventos Nacionales y/o Internacionales.</p>

		<c:if test="${(sessionScope.datosConv.convNumero==1 or sessionScope.datosConv.convNumero==3 ) and !(sessionScope.persona.papel==3 or sessionScope.persona.papel==5)}">
		<p align="center" class="lroja3">Usted no puede inscribirse en esta convocatoria debido a que su papel en el grupo <b>NO</b> es el de <b>Estudiante</b> de la Universidad Distrital. Si usted es profesor, Favor ingresar a la convocatoria de docentes. Si usted es estudiante, favor modificar el campo "papel" en sus datos personales en el menú "Mis Datos"</p>
		</c:if>
		<c:if test="${(sessionScope.datosConv.convNumero==1 or  sessionScope.datosConv.convNumero==3 ) and (sessionScope.persona.papel==3 or sessionScope.persona.papel==5)}">
		<p align="center" class="texto1">En estos momentos usted no tiene ninguna propuesta inscrita, Favor dar clik en el botón "Agregar Propuesta" para insertar una nueva propuesta</p>
		<div align="center">
			<a href='<c:url value="/convMovilidad/Insercion.jsp"/>'><img border="0" src='<c:url value="/comp/img/AgregarPropuesta.gif"/>'></a>
		</div>
		</c:if>

		<c:if test="${(sessionScope.datosConv.convNumero==2 || sessionScope.datosConv.convNumero==4 or sessionScope.datosConv.convNumero==12) and sessionScope.persona.papel!=1 and sessionScope.persona.papel!=2 and sessionScope.persona.papel!=4  and sessionScope.persona.papel!=6}">
		<p align="center" class="lroja3">Usted no puede inscribirse en esta convocatoria debido a que su papel en el grupo <b>NO</b> es el de <b>Profesor</b> de la Universidad Distrital. Si usted es estudiante, Favor ingresar a la convocatoria de Estudiantes. Si usted es profesor, favor modificar el campo "papel" en sus datos personales en el menú "Mis Datos"</p>
		</c:if>
		<c:if test="${(sessionScope.datosConv.convNumero==2 || sessionScope.datosConv.convNumero==4 or sessionScope.datosConv.convNumero==12) and (sessionScope.persona.papel==1 or sessionScope.persona.papel==2 or sessionScope.persona.papel==4  or sessionScope.persona.papel==6)}">
		<p align="center" class="texto1">En estos momentos usted no tiene ninguna propuesta inscrita, Favor dar clik en el botón "Agregar Propuesta" para insertar una nueva propuesta</p>
		<div align="center">
			<a href='<c:url value="/convMovilidad/Insercion.jsp"/>'><img border="0" src='<c:url value="/comp/img/AgregarPropuesta.gif"/>'></a>
		</div>
		</c:if>
	</fieldset>
</div>
</c:if>
</body>
</html>
