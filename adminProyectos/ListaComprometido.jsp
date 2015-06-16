<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<jsp:useBean id="globales" class="cidc.general.obj.Globales" scope="page" />
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/tablas.css"/>">
<script type="text/javascript" language="javascript" src='<c:url value="/comp/js/lytebox.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript">
function comando(id){
	document.frmListaCompr.accion.value=id;
}
function modificar(valor,numero,fecha){
	//asigna los valores
	document.frmListaCompr.valorCompr.value=valor;
	document.frmListaCompr.numNecesidad.value=numero;
	document.frmListaCompr.fechaNecesidad.value=fecha;
	//vuelve los controles visibles
	document.frmListaCompr.valorCompr.style.display='block';
	document.frmListaCompr.numNecesidad.style.display='block';
	document.frmListaCompr.fechaNecesidad.style.display='block';
	document.frmListaCompr.numCDP.style.display='block';
	document.frmListaCompr.fechaCDP.style.display='block';
	document.getElementById("f_trigger_c").style.display='block';
	document.frmListaCompr.numRP.style.display='block';
	document.frmListaCompr.fechaRP.style.display='block';
	document.getElementById("f_trigger_a").style.display='block';
	document.frmListaCompr.Observaciones.style.display='block';
	document.frmListaCompr.bGuardar.style.display='block';
}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form  name="frmListaCompr">
<table>
			<tr>
				<td><input type="image" src='<c:url value="/comp/img/Registrar Compr.png" />' onclick="comando(27)"></td>				
			</tr>
	</table>
<div align="center">
	<input type="hidden" name="idRubro" value='<c:out value="${requestScope.rubro.idRubro}"/>'>
	<input type="hidden" name="accion" value=''>
	<input type="hidden" name="valor" value=''>
	 <input type="hidden" name="numNec" value=''>
	 <input type="hidden" name="fecNec" value=''>
	<fieldset style="width:1000px;">
	<c:if test="${!empty requestScope.listaCompr}">
	<table align="center" class="tablas" width="95%">
	<caption>Lista de Presupuesto Comprometido</caption>
	<tr>
		<th><b>Valor</b></th>
		<th><b>No. de Necesidad</b></th>
		<th><b>Fecha de Necesidad</b></th>
		<th><b>No. CDP</b></th>
		<th style="width: 150px"><b>Fecha de CDP</b></th>
		<th><b>No. CRP</b></th>
		<th style="width: 150px"><b>Fecha de CRP</b></th>
		<th><b>Observaciones</b></th>
		<th><b>Modificar</b></th>
	</tr>
	<c:forEach items="${requestScope.listaCompr}" var="lista">
	<tr>
		<td><c:out value="${lista.valorCompr}"/></td>
		<td><c:out value="${lista.numNecesidad}"/></td>
		<td><c:out value="${lista.fechaNecesidad_d}"/></td>
		<td><c:out value="${lista.numCDP}"/></td>
		<td><c:out value="${lista.fechaCDP_d}"/></td>
		<td><c:out value="${lista.numRP}"/></td>
		<td><c:out value="${lista.fechaRP_d}"/></td>
		<td><c:out value="${lista.observaciones}"/></td>
		<td><img src='<c:url value="/comp/img/Editar.png"/>' title="Modificar" onclick='modificar(<c:out value="${lista.valorCompr}"/>,"<c:out value="${lista.numNecesidad}" />","<c:out value="${lista.fechaNecesidad_d}"/>")'/></td>
	</tr>
	<tr>
		<td><input type="text" name=valorCompr id="valorCompr" value="" disabled="disabled" style="display: none"></td>
		<td><input type="text" name="numNecesidad" id="numNecesidad" value="" disabled="disabled" style="display: none"></td>
		<td><input type="text" name="fechaNecesidad" id="fechaNecesidad" value="" disabled="disabled" style="display: none"></td>
		<td><input type="text" name="numCDP" id="numCDP" value="" style="display: none"></td>
		<td width="20%"><input type="text" style="width: 75%; display: none" readonly='true' id='f_date_c' size="12" name="fechaCDP">
		<button type='button' id='f_trigger_c' style="display: none">...</button>
					<script type='text/javascript'>
		    			Calendar.setup({
			    			inputField     :    'f_date_c',
			    			ifFormat       :    '%Y-%m-%d',
			    			showsTime      :    false,
			    			button         :    'f_trigger_c',
			    			singleClick    :    false,
			    			step           :    1
		    			})
	    			</script>
	    </td>
		<td><input type="text" name="numRP" id="numRP" value="" style="display: none"></td>
		<td><input type="text" name="fechaRP" id="f_date_a" value="" style="width: 75%; display: none" readonly='true'  size="12">
		<button type='button' id='f_trigger_a' style="display: none">...</button>
					<script type='text/javascript'>
		    			Calendar.setup({
			    			inputField     :    'f_date_a',
			    			ifFormat       :    '%Y-%m-%d',
			    			showsTime      :    false,
			    			button         :    'f_trigger_a',
			    			singleClick    :    false,
			    			step           :    1
		    			})
	    			</script>
	    </td>
		<td><input type="text" name="Observaciones" id="Observaciones" value="" style="display: none"></td>
	</tr>
	</c:forEach>
	</table>
	<br>
	<img id="bGuardar" alt="Guardar" src="<c:url value="/comp/img/Guardar.gif" />" title="Guardar" onClick="guardar()" style="align:center; display: none" >
	</c:if>
	<c:if test="${empty requestScope.listaCompr}">
		<h4 align="center">No hay presupuesto comprometido registrado para este rubro</h4>
	</c:if>
	</fieldset>
</div>
</form>
<c:if test="${requestScope.bandera!=null}">
<script language="javaScript">
	parent.location.href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=7"/>';
</script>
</c:if>
</body>
</html>