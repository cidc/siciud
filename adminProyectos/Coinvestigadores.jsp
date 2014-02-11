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
	function enviar(id,accion,cont){
		document.frmCoinvest.accion.value=accion;
		document.frmCoinvest.fechaInicio.value=document.getElementById("f_date_a"+cont).value;
		document.frmCoinvest.fechaFin.value=document.getElementById("f_date_b"+cont).value;
		document.frmCoinvest.id.value=id;
		document.frmCoinvest.submit();
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
			<td><a href='<c:url value="/adminProyectos/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Tiempos1.gif"/>'></a></td>
						<td><img border="0" src='<c:url value="/comp/img/tabs/Investigadores2.gif"/>'></td>
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
	
	<c:if test="${!empty sessionScope.proyecto.listaCoInvestigadores}">
	<form name="frmCoinvest" action='<c:url value="/adminProyectos/llenarInvestigador.jsp"/>' method="post">
		<input type="hidden" name="accion" value="0">
		<input type="hidden" name="id" value="0">
		<input type="hidden" name="fechaInicio" value="">
		<input type="hidden" name="fechaFin" value="">
		<table width="95%" align="center" class="tablas">
			<caption>Lista de Investigadores asociados al proyecto</caption>
			<tr>
				<th width="5px">&nbsp;</th>
				<th width="75px"><b>Fecha Ingreso</b></th>
				<th width="75px"><b>Fecha Salida</b></th>				
				<th width="100px"><b>Documento</b></th>
				<th><b>Nombre</b></th>
				<th width="100px"><b>Papel</b></th>
				<th width="5px">&nbsp;</th>
				<th width="5px">&nbsp;</th>
			</tr>
			<c:forEach begin="0" items="${sessionScope.proyecto.listaCoInvestigadores}" var="lista" varStatus="st">
			<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if> >
				<td width="5px"><c:out value="${st.count}"/></td>
				<td  width="175px" align="center">
					<input type='text' name='fechaEntrada' style="width: 75%" readonly='true' id='f_date_a<c:out value="${st.count}"/>' size='12' value='<c:out value="${lista.fechaInicio}"/>'>
					<button type='button' id='f_trigger_a<c:out value="${st.count}"/>'>...</button>
					<script type='text/javascript'>
		    			Calendar.setup({
			    			inputField     :    'f_date_a<c:out value="${st.count}"/>',
			    			ifFormat       :    '%Y-%m-%d',
			    			showsTime      :    false,
			    			button         :    'f_trigger_a<c:out value="${st.count}"/>',
			    			singleClick    :    false,
			    			step           :    1
		    			})
	    			</script>				
				</td>
				<td  width="175px" align="center">
					<input type='text' name='fechaSalida' style="width: 75%" readonly='true' id='f_date_b<c:out value="${st.count}"/>' size='12' value='<c:out value="${lista.fechaFin}"/>'>
					<button type='button' id='f_trigger_b<c:out value="${st.count}"/>'>...</button>
					<script type='text/javascript'>
		    			Calendar.setup({
			    			inputField     :    'f_date_b<c:out value="${st.count}"/>',
			    			ifFormat       :    '%Y-%m-%d',
			    			showsTime      :    false,
			    			button         :    'f_trigger_b<c:out value="${st.count}"/>',
			    			singleClick    :    false,
			    			step           :    1
		    			})
	    			</script>				
				</td>
				<td width="100px" align="center"><c:out value="${lista.documento}"/></td>
				<td align="center"><c:out value="${lista.nombre}"/></td>
				<td width="100px" align="center"><c:out value="${lista.papel}"/></td>
				<td width="5px" align="center"><img src='<c:url value="/comp/img/equis2.png"/>' onclick='enviar(<c:out value="${lista.id}"/>,20,<c:out value="${st.count}"/>)'></td>
				<td width="5px" align="center"><img src='<c:url value="/comp/img/ok.png"/>' onclick='enviar(<c:out value="${lista.id}"/>,21,<c:out value="${st.count}"/>)'></td>
					
			</tr>
			</c:forEach>
		</table>
	</form>
	</c:if>
	<c:if test="${empty sessionScope.proyecto.listaCoInvestigadores}">
	<h3 align="center">No hay más personas registradas en este proyecto</h3>
	</c:if>
	
	<form method="post" action='<c:url value="/adminProyectos/llenarInvestigador.jsp"/>'>
		<input type="hidden" name="accion" value="19">
		<input type="hidden" name="id" value="">
		
		<table align="center" width="95%" class="tablas">
		<caption>Registro de nuevo investigador asociado</caption>
			<tr>
				<th width="100px">Fecha Ingreso</th>
				<th width="100px">Fecha Salida</th>
				<th width="100px">Documento</th>
				<th>Nombres</th>
				<th>Apellidos</th>
				<th>Papel</th>
			</tr>
			<tr>
				<td  width="150px">
					<input type='text' name='fechaInicio' style="width: 70%" readonly='true' id='f_date_a' size='12'>
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
				<td  width="150px">
					<input type='text' name='fechaFin' style="width: 70%" readonly='true' id='f_date_b' size='12'>
					<button type='button' id='f_trigger_b'>...</button>
					<script type='text/javascript'>
		    			Calendar.setup({
			    			inputField     :    'f_date_b',
			    			ifFormat       :    '%Y-%m-%d',
			    			showsTime      :    false,
			    			button         :    'f_trigger_b',
			    			singleClick    :    false,
			    			step           :    1
		    			})
	    			</script>
				</td>
				<td width="100px"><input type="text" onkeypress="return numeros(event)" name="documento" ></td>
				<td><input type="text" name="nombre" ></td>
				<td><input type="text" name="apellido" ></td>
				<td><input type="text" name="papel" ></td>
			</tr>
			<tr>
				<td colspan="6" align="center"><input type="image" src='<c:url value="/comp/img/Enviar.gif"/>'></td>
			</tr>
		</table>
	</form>
	
</c:if>
<c:if test="${sessionScope.proyecto==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Proyecto de Investigación</h4>
</c:if>
</body>
</html>