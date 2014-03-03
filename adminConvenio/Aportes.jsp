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
			<td><a href='<c:url value="/adminConvenio/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Tiempos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Grupos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/GruposInv.gif"/>'></a></td>
			<td><a href='<c:url value="/adminEntidad/GestEntidad.x?validar=3&por=2"/>'><img border="0" src='<c:url value="/comp/img/convenio/EntidadesClick.gif"/>'></a></td>
			<td><a href='<c:url value=""/>'><img border="0" src='<c:url value="/comp/img/tabs/Balance1.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=15"/>'><img src='<c:url value="/comp/img/prAprobado.gif"/>'></a></td>
		</tr>
	</table>
<br>
<table>
<tr><td><a href='<c:url value="/adminEntidad/GestEntidad.x?validar=3&por=2"/>'><img border="0" src='<c:url value="/comp/img/Atras.gif"/>'></a></td></tr>
	</table>
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
			<th width="20%"><b>Total Aportado</b></th>
		</tr>
		<tr>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.codigo}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaInicio}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaFinalizacion}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.finanza.VAprobado}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.finanza.VAportado}"/></td>
		</tr>
	</table>
	
	
	<table width="95%" align="center" class="tablas">
			<caption>Lista de Aportes Realizados</caption>
			<tr>
			
			<th colspan="5"><b>Entidad</b></th>	</tr>
			<tr>
			<td width="100%" align="center"><c:out value="${requestScope.entidadaporte.identidadconvenio}"/></td>
			
		</tr>
		</table>
	<c:if test="${!empty requestScope.listaAportesEntidad}">
	<form name="frmListAportes" action='<c:url value="/adminConvenio/AdminConvenio.x"/>' method="post">
		<input type="hidden" name="accion" value="0">
		
		<br>
			<table width="95%" align="center" class="tablas">
			<tr>
				<th width="5px">&nbsp;</th>
				<th width="75px"><b>Cod Aporte</b></th>
				<th width="75px"><b>Tipo Aporte</b></th>				
				<th width="75px"><b>Valor Aporte</b></th>
				<th width="75px"><b>Fecha Aporte</b></th>
				<th width="75px"><b>Cliente</b></th>
				
				<th width="10px">&nbsp;</th>
			</tr>
			<c:forEach begin="0" items="${requestScope.listaAportesEntidad}" var="lista" varStatus="st">
			<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if> >
				<td width="5px"><c:out value="${st.count}"/></td>
				<td width="75px" align="center"><c:out value="${lista.codAporte}"/></td>
				<td width="75px" align="center"><c:out value="${lista.tipoAporte}"/></td>
				<td width="75px" align="center"><c:out value="${lista.valorAporte}"/></td>
				<td width="75px" align="center"><c:out value="${lista.fechaAporte}"/></td>
				<td width="75px" align="center"><c:out value="${lista.personaOpcional}"/></td>
				<%--<td width="5px" align="center"><img src='<c:url value="/comp/img/equis1.png"/>' onclick='enviar(<c:out value="${lista.idAporte}"/>)'></td>--%>
			</tr>
			</c:forEach>
		</table>
	</form>
	</c:if>
	<c:if test="${empty requestScope.listaAportesEntidad}">
	<h3 align="center">No hay aporte registrados a esta entidad.</h3>
	</c:if>
	
	<form method="post" action='<c:url value="/adminConvenio/llenarAporte.jsp"/>'>
	<input type="hidden" name="accion" value="18"> 
	<input type="hidden" name="idCon" value="<c:out value="${requestScope.idCon}"/>"> 
	<input type="hidden" name="nombreEntidad" value="<c:out value="${requestScope.nombreEntidad}"/>"> 
		<table align="center" width="90%" class="tablas">
		<caption>Registro de un Nuevo Aporte</caption>
			<tr>
				<th width="175px">Codigo Aporte</th>
				<th width="75px">Fecha Aporte</th>
				<th width="100px">Valor Aporte</th>
				<th width="300px">Cliente</th>
				
			</tr>
			<tr>
			<td> 
				<input type="text"  name="codAporte" style="width: 100%; text-align: right;"> 
				
				</td>
				
				<td  width="175px">
					<input type='text' name='fechaAporte' style="width: 75%" readonly='true' id='f_date_a' size='12'>
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
				<td width="100px">
				<input type="text" maxlength="9" name="valorAporte" style="text-align:right; width: 90%" value="0" onkeypress="return numeros(event)">
				</td>
				<td width="100px">
					<input type="text" name="personaOpcional" style="width: 100%;text-align: right;">
				</td>
				</tr>
				
				<tr>
				<tr>
						<td colspan="6" class="renglones"><b>Observación</b></td>
							</tr>
							<tr>
								<td colspan="6"><input type="text" name="Aobservacion" size="70" maxlength="70"></td>
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