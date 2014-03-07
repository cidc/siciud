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
	function pregunta(id)
	{confirmar=confirm("¿Desea Realmente Eliminar el Registro?");  
	if (confirmar)
		eliminar(id)
	
	
	}
	function eliminar(id){
		document.frmTiempos.accion.value=9;
		document.frmTiempos.idTiempo.value=id;
		document.frmTiempos.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${sessionScope.datoConvenio!=null}">
<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
		
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=3&idConv=${sessionScope.datoConvenio.idconvenio}"/>'><img border="0" src='<c:url value="/comp/img/convenio/Proyectos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/convenio/Documentos.gif"/>'></a></td>
			<td><img border="0" src='<c:url value="/comp/img/convenio/TiemposClick.gif"/>'></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Grupos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/GruposInv.gif"/>'></a></td>
			<td><a href='<c:url value="/adminEntidad/GestEntidad.x?validar=3&por=2"/>'><img border="0" src='<c:url value="/comp/img/convenio/Entidades.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=15"/>'><img src='<c:url value="/comp/img/prAprobado.gif"/>'></a></td>
	
		</tr>
	</table>
<br>
	
	<table width="95%" class="tablas" align="center">
		<CAPTION>Datos generales del proyecto</CAPTION>
		<tr>
			<th colspan="5"><b>Nombre de Proyecto</b></th>
		</tr>
		<tr>
			<td colspan="5"><c:out value="${sessionScope.datoConvenio.nombreproyecto}"/></td>
		</tr>
		<tr>
			
			<th width="20%"><b>Número Convenio</b></th>
			<th width="20%"><b>Fecha Inicio</b></th>
			<th width="20%"><b>Estimado Fin</b></th>
			<th width="20%"><b>Total Aprobado</b></th>
		</tr>
		<tr>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.codigo}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaInicio}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaFinalizacion}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.finanza.VAprobado}"/></td>
		</tr>
	</table>
	
	<c:if test="${!empty sessionScope.datoConvenio.listaTiempos}">
	<form name="frmTiempos" action='<c:url value="/adminConvenio/AdminConvenio.x"/>' method="post">
		<input type="hidden" name="accion" value="0">
		<input type="hidden" name="idTiempo" value="0">
		<table width="95%" align="center" class="tablas">
			<caption>Lista de Tiempos aprobados</caption>
			<tr>
				<th width="5px">&nbsp;</th>
				<th width="75px"><b>Fecha Asignación</b></th>
				<th width="75px"><b>Aprobado</b></th>				
				<th width="75px"><b>Tipo</b></th>
				<th width="150px"><b>Registrado por</b></th>
				<th ><b>Observación</b></th>
				<th width="10px">&nbsp;</th>
			</tr>
			<c:forEach begin="0" items="${sessionScope.datoConvenio.listaTiempos}" var="lista" varStatus="st">
			<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if> >
				<td width="5px"><c:out value="${st.count}"/></td>
				<td width="75px" align="center"><c:out value="${lista.fechaTiempo}"/></td>
				<td width="75px" align="center"><c:out value="${lista.valorTiempo}"/>  (meses)</td>
				<td width="75px" align="center">
				<c:if test="${lista.tipoTiempo==1}">Prórroga</c:if>
				<c:if test="${lista.tipoTiempo==2}">Aplazamiento</c:if>
				<c:if test="${lista.tipoTiempo==3}">Suspensión</c:if>
				<c:if test="${lista.tipoTiempo==4}">Extensión</c:if>
				</td>
				<td width="150px" align="center"><c:out value="${lista.regitradoPor}"/></td>
				<td width="150px" align="center"><c:out value="${lista.descripcion}"/></td>
				<td width="5px" align="center"><img src='<c:url value="/comp/img/equis1.png"/>' onclick='pregunta(<c:out value="${lista.idTiempo}"/>)'></td>
			</tr>
			</c:forEach>
		</table>
	</form>
	</c:if>
	<c:if test="${empty sessionScope.datoConvenio.listaTiempos}">
	<h3 align="center">No hay tiempos adicionales aprobados para este proyecto</h3>
	</c:if>
	
	<form method="post" action='<c:url value="/adminConvenio/llenarTiempo.jsp"/>'>
	<input type="hidden" name="accion" value="8"> 
		<table align="center" width="90%" class="tablas">
		<caption>Registro de nuevo tiempo adicional</caption>
			<tr>
				<th width="175px">Fecha Asignación</th>
				<th width="100px">Tiempo Aprobado</th>
				<th width="100px">Tipo</th>
				<th>Observaciones</th>
			</tr>
			<tr>
				<td  width="175px">
					<input type='text' name='fechaTiempo' style="width: 75%" readonly='true' id='f_date_a' size='12'>
					<button type='button' id='f_trigger_a'>...</button>
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
				<td width="100px"><input type="text" maxlength="3" onkeypress="return numeros(event)" name="valorTiempo" style="width: 50%;text-align: right;"> Meses</td>
				<td width="100px">
					<select name="tipoTiempo">
						<option value="1">Prórroga</option>
						<option value="2">Aplazamiento</option>
						<option value="3">Suspensión</option>
						<option value="4">Extensión</option>
					</select>
				</td>
				<td><input type="text" name="descripcion"></td>
			</tr>
			<tr>
				<td colspan="4" align="center"><input type="image" src='<c:url value="/comp/img/Enviar.gif"/>'></td>
			</tr>
		</table>
	</form>
	
</c:if>
<c:if test="${sessionScope.datoConvenio==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Convenio</h4>
</c:if>
</body>
</html>