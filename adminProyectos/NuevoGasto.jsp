<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" type="time" timeStyle="short" pattern="yyyy-mm-dd" var="hoy"/>

<c:import url="/general.jsp"/>
<script>
	var nav4=window.Event ? true : false;
	function numeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}

	function deshabilitar(combo,numCombo){
		if(numCombo==1){
			if(combo.selectedIndex==2 || combo.selectedIndex==0){
				document.gastos.para.selectedIndex=1;
				document.gastos.para.selectedIndex=1;
				document.gastos.codigo.value="";
				document.gastos.codigo.disabled=true;
				document.gastos.para[2].disabled=true;
			}
			if(combo.selectedIndex==1){
				document.gastos.para.selectedIndex=0;
				document.gastos.codigo.value="";
				document.gastos.codigo.disabled=false;
				document.gastos.para[2].disabled=false;
			}
		}
		if(numCombo==2){
			if(combo.selectedIndex==0 || combo.selectedIndex==1){
				document.gastos.codigo.value="";
				document.gastos.codigo.disabled=true;
			}
			if(combo.selectedIndex==2){
				document.gastos.codigo.value="";
				document.gastos.codigo.disabled=false;
			}
		}
	}

	function enviar(){
		if (validar()){
			document.gastos.submit();
		}
	}

	function validar(){
		var mensaje="";
		var max=parseFloat(document.gastos.saldo.value);
		var val=parseFloat(document.gastos.valorGasto.value);
		if(document.gastos.tipoGasto.selectedIndex==0){
			mensaje="-) Tipo de gasto\n";
		}
		if(document.gastos.para.selectedIndex==0){
			mensaje=mensaje+"-) Para\n";
		}
		if(document.gastos.descripcion.value!=""){
			if(document.gastos.tipoGasto.selectedIndex==2){
				max=parseFloat(document.gastos.ejecutado.value);
			}
			if(val>max){
				mensaje=mensaje+"-) El valor a registrar no puede ser superior a "+max+"\n";
				document.gastos.valorGasto.value=max;
			}
		}else{
			mensaje=mensaje+"-) El campo 'elemento a registrar' no puede estar vacio";
		}
		if(document.gastos.fecha.value==""){
			mensaje=mensaje+"\n"+"-) El campo 'Fecha' no puede estar vacio";
		}
		if(mensaje==""){
			return true
		}else{
			alert("Los Siguientes campos deben ser corregidos:\n"+mensaje);
		}
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">	

	<form name="gastos" method="post" action='<c:url value="/adminProyectos/llenarGasto.jsp"/>'>
	<c:if test="${sessionScope.proyecto.estado==2}">
		<table>
			<tr>
				<td><input type="image" src='<c:url value="/comp/img/RegistraGasto.gif" />' onclick="comando(12)"></td>				
				<td><input type="image" src='<c:url value="/comp/img/EntregaElementos.gif" />' onclick="comando(10)"></td>
			</tr>
		</table>
	</c:if>
		<input type="hidden" name="accion" value="13"/>
		<input type="hidden" name="idRubro" value='<c:out value="${requestScope.rubro.idRubro}"/>'>
		<input type="hidden" name="saldo" value='<c:out value="${requestScope.rubro.valorSaldo}"/>'>
		<input type="hidden" name="ejecutado" value='<c:out value="${requestScope.rubro.valorEjecutado}"/>'>
		<input type="hidden" name="idProyecto" value='<c:out value="${sessionScope.proyecto.id}"/>'>
        <table align="center" class="tablas" width="95%" >
	        <caption>Inserción de Nuevo Gasto</caption>
	        <tr>
	        	<th colspan="5"><b>Descripción del registro del gasto</b></td>
	        </tr>
			<tr>
				<td colspan="5"><input style="width:100%" type="text" name="descripcion"></td>
			</tr>
	        <tr>
		        <th width="25%"><b>Fecha</b></th>
	        	<th width="70px"><b>Tipo</b></td>
	        	<th width="20%"><b>Valor</b></td>
	        	<th ><b>Para</b></td>
	        	<th ><b>Código</b></td>
	        </tr>
			<tr>
				<td width="20%">
					<input type='text' name='fecha' style="width: 75%" readonly='true' id='f_date_a' size='12'>
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
				<td width="20%">
					<select name="tipoGasto" style="width: 100%" onchange="deshabilitar(this,1)">
						<option value="0">----------</option>
						<option value="1">Gasto</option>
						<option value="-1">Reintegro</option>
					</select>
				</td>
				<td ><input type="text"  style="text-align:right;" name="valorGasto" onkeypress="return numeros(event)"></td>
				<td >
					<select name="para" style="width: 100%" onchange="deshabilitar(this,2)">
						<option value="0">----------</option>
						<option value="1">Consumo</option>
						<option value="2">Inventario</option>
					</select>
				</td>
				<td width="20%"><input type="text" style="text-align:right;" name="codigo"></td>
			</tr>

			<tr>
	        	<th colspan="5"><b>Observaciones</b></td>
	        </tr>
			<tr>
				<td colspan="5"><textarea name="observaciones" rows="2" style="width: 100%"></textarea></td>

			</tr>
			<tr>
				<td colspan="5" align="center"><img src='<c:url value="/comp/img/RegistraGasto.gif" />' onclick="enviar()"></td>
			</tr>
		</table>
	</form>
</body>

<script type="text/javascript">
	document.gastos.saldo.value=eliminaFormatoMoneda(document.gastos.saldo.value);
	document.gastos.ejecutado.value=eliminaFormatoMoneda(document.gastos.ejecutado.value);
	<c:if test="${sessionScope.proyecto.claseProyecto==1}">
	if(document.gastos.saldo.value=="0"){
		alert("No hay saldo disponible para registrar gastos, Solo es posible registrar reintegros ");
		document.gastos.tipoGasto.selectedIndex=2;
		document.gastos.tipoGasto[1].disabled=true;
		deshabilitar(document.gastos.tipoGasto,1);
	}
	</c:if>
</script>
</html>