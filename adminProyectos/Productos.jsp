<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<script type="text/javascript" language="javascript" src='<c:url value="/comp/js/lytebox.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp"/>
<script>
	var nav4=window.Event ? true : false;
	function numeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}
	function eliminar(id){
		document.frmTiempos.accion.value=18;
		document.frmTiempos.idTiempo.value=id;
		document.frmTiempos.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${sessionScope.proyecto!=null}">
<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=2&id=${sessionScope.proyecto.id}&tipo=${sessionScope.proyecto.claseProyecto}"/>'><img border="0" src='<c:url value="/comp/img/tabs/General1.gif"/>'></a></td>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=6"/>'><img border="0" src='<c:url value="/comp/img/tabs/Documentos1.gif"/>'></a></td>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/tabs/Balance1.gif"/>'></a></td>
			<td><img border="0" src='<c:url value="/comp/img/tabs/Tiempos1.gif"/>'></td>
			<td><a href='<c:url value="/adminProyectos/Coinvestigadores.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Investigadores1.gif"/>'></a></td>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=22"/>'><img border="0" src='<c:url value="/comp/img/tabs/Productos.gif"/>'></a></td>
		</tr>
	</table>
<br>
	
	<table width="95%" class="tablas" align="center">
		<CAPTION>Datos generales del proyecto</CAPTION>
		<tr>
			<th colspan="5"><b>Nombre de Proyecto</b></th>
		</tr>
		<tr>
			<td colspan="5"><c:out value="${sessionScope.proyecto.proyecto}"/></td>
		</tr>
		<tr>
			<th width="20%"><b>Director del Proyecto</b></th>
			<th width="20%"><b>Código</b></th>
			<th width="20%"><b>Fecha Aprobación</b></th>
			<th width="20%"><b>Estimado Fin</b></th>
			<th width="20%"><b>Total Aprobado</b></th>
		</tr>
		<tr>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.director}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.codigo}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.fecAprobacion}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.fecEstimadoFin}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.balanceProyecto.totalAprobado}"/></td>
		</tr>
	</table>
	</c:if>
	<br>
	<br>
	<c:if test="${!empty sessionScope.compromisos}">
	<table width="95%" class="tablas" align="center">
	<caption>Compromisos del Investigador principal</caption>
		<tr>
			<td width="15px" class="renglones"><b>#<b></td>
			<td class="renglones"><b>Producto</b></td>
			<td class="renglones"><b>Indicador</b></td>
			<td class="renglones"><b>Cant</b></td>
		</tr>
		<c:forEach begin="0" items="${sessionScope.compromisos}" var="compromisos" varStatus="st">
		<tr>
			<td width="15px"><c:out value="${st.count}"/></td>
			<td><c:out value="${compromisos.producto}"/></td>
			<td><c:out value="${compromisos.indicador}"/></td>
			<td align="center"><c:out value="${compromisos.cantidad}"/></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<c:if test="${sessionScope.proyecto==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Proyecto de Investigación</h4>
</c:if>
</body>
</html>