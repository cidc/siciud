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

function guardar(){
	if (validar()){
		document.gastos.submit();
	}
}

function validar(){
	var mensaje="";
	if(document.comprometido.valor.value==""){
		mensaje+="-) Valor a comprometer \n";
	}
	if(document.comprometido.nCDP.value==""){
		mensaje+="-) Número de CDP\n";
	}
	if(document.comprometido.fechaCDP.value==""){
		mensaje+="-) Fecha de CDP\n";
	}
	if(mensaje==""){
		return true
	}else{
		alert("Los Siguientes campos son obligatorios:\n"+mensaje);
	}
}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="comprometido" method="post" action="">
		<input type="hidden" name="idRubro" value='<c:out value="${requestScope.rubro.idRubro}"/>'>
		<input type="hidden" name="saldo" value='<c:out value="${requestScope.rubro.valorSaldo}"/>'>
		<input type="hidden" name="idProyecto" value='<c:out value="${sessionScope.proyecto.id}"/>'>
	<c:if test="${sessionScope.proyecto.estado==2}">
		<table width="95%" class="tablas" align="center" name="comprometido" method="post">
		<caption>Ingresar Presupuesto Comprometido</caption>
		<tr>
			<td class="renglones" align="center"><b>valor</b></td>
			<td class="renglones" align="center"><b>No. CDP</b></td>
			<td class="renglones" align="center"><b>Fecha CDP</b></td>
			<td class="renglones" align="center"><b>No. RP</b></td>
			<td class="renglones" align="center"><b>Fecha RP</b></td>
		</tr>
		<tr>
			<td><input type="text" id="valor" onkeypress="return numeros(event)"></td>
			<td><input type="text" id="nCDP" onkeypress="return numeros(event)"></td>
			<td>
					<input type='text' name='fechaCDP' style="width: 75%" readonly='true' id='f_date_a' size='12'>
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
			<td><input type="text" id="nRP" onkeypress="return numeros(event)"></td>
			<td>
					<input type='text' name='fechaRP' style="width: 75%" readonly='true' id='f_date_b' size='12'>
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
		</tr>
		<tr>
			<td class="renglones" align="center" colspan="5"><b>Observaciones</b></td>
		</tr>
		<tr>
			<td colspan="5"><textarea rows="3" name="observaciones"></textarea></td>
		</tr>
		<tr>
			<td align="center" colspan="5"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"></td>
		</tr>
		</table>
	</c:if>
	<c:if test="${sessionScope.proyecto.estado!=2}">
		<h4 align="center">El Proyecto no se encuentra vigente</h4>
	</c:if>	
	</form>
</body>
</html>