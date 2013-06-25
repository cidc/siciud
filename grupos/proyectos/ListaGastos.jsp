<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:useBean id="globales" class="cidc.general.obj.Globales" scope="page" />
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function comando(id){
		document.gastos.accion.value=id;
	}

	function tipoPer(obj){
		document.frmAjaxServicios.dato.value=obj.options[obj.selectedIndex].value;
		document.frmAjaxServicios.accion.value='11';
	 	document.frmAjaxServicios.target="frameOculto";
		document.frmAjaxServicios.submit();
	}

	function ajaxProyecto(){
		//document.tipoPersona.action="<c:url value='/GestionProyectos/ProyectosInvestigador.x' />";
	    document.tipoPersona.accion.value = 25;
		document.tipoPersona.submit();
		
	}

	function tipoContrato(){
		document.tipoPersona.accion.value = 26;
		document.tipoPersona.submit();
	}
	
	function cambiarNatural(op){
		alert("natural");
		var cambio="";
		if(op==1){//1 para ocultar 2 para mostrar
			cambio="none";
		}else{
			cambio="block";
		}
		document.tipoPersona.lexpedicion.style.display=cambio;
		document.tipoPersona.expedicion.style.display=cambio;
	}
	
	function cambiarJuridica(op){
		alert("juridica");
		var cambio="";
		if(op==1){//1 para ocultar 2 para mostrar
			cambio="none";
		}else{
			cambio="block";
		}
		document.tipoPersona.lrepresentante.style.display='block'';
		document.tipoPersona.representante.style.display='block';
		document.tipoPersona.lcontacto.style.display=cambio;
		document.tipoPersona.contacto.style.display=cambio;
	}
	
	function cambiarPersona(obj){
	alert("entro");
		if(obj.options[obj.selectedIndex].value==1){
			alert("case 1");
			cambiarJuridica(1);
			cambiarnatural(2);
		}if(obj.options[obj.selectedIndex].value==2){
			alert("case 2");
			cambiarJuridica(2);
			cambiarnatural(1);
		}
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br/>
	<form name="tipoPersona" action='<c:url value="/GestionProyectos/ProyectosInvestigador.x"/>' method="post">
	<input type="hidden" name="accion" value="0">
		<table width="95%" align="center" class="tablas">
		<caption>Datos del Contratista</caption>
			<tr>
				<td  align="left"><b><c:out value="Tipo de Persona"/></b>
						</td>
						<td><select name="tipoPersona" onchange="cambiarPersona(this)">
							<option value="0">----</option>
							<option value="1">Persona Natural</option>
							<option value="2">Persona Jurídica</option>
						</select>
					</td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lnombre">Nombres y Apellidos / Razón Social:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="nombre" MAXLENGTH="50" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="<c:out value="${requestScope.basico}"/>">
				<td align="left" ><b><label for="lcedula">Número de Cedula / NIT:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="cedula" MAXLENGTH="15" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.natural}">
				<td align="left" ><b><label for="lexpedicion">Lugar de Expedición:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="expedicion" MAXLENGTH="20" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.juridica}">
				<td align="left" ><b><label for="lrepresentante">Representante Legal:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="representante" MAXLENGTH="50" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="ldireccion">Dirección:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="direccion" MAXLENGTH="20" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lcorreo">Correo Electrónico:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="correo" MAXLENGTH="50" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="ltelefono">Teléfono Fijo:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="telefono" MAXLENGTH="7" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lcelular">Teléfono Celular:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="celular" MAXLENGTH="10" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.juridica}">
				<td align="left" ><b><label for="lcontacto">Contacto Comercial:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="contacto" MAXLENGTH="50" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lproyecto">Nombre del Proyecto / Convenio:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="proyecto" MAXLENGTH="200" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lduracion">Duración:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="duracion" MAXLENGTH="20" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lformaPago">Forma de Pago:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="formaPago" MAXLENGTH="20" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lvalorContrato">Valor Total del Contrato:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="valorContrato" MAXLENGTH="10" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="ljustificacion">Justificación:</label></b></td>
				<td align="left" colspan="3"><b><textarea NAME="justificacion"></textarea></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lobjetivo">Objetivo:</label></b></td>
				<td align="left" colspan="3"><b><textarea NAME="objetivo"></textarea></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="lcargoSupervisor">Cargo del Supervisor:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="cargoSupervisor" MAXLENGTH="50" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr style="${requestScope.basico}">
				<td align="left" ><b><label for="ldepSupervisor">Dependencia del Supervisor:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="depSupervisor" MAXLENGTH="50" TYPE="TEXT" VALUE=""></b></td>
			</tr>
		</table>
	</form>
	
	
	
	
	<c:if test="${!empty sessionScope.listaGastosRubro}">
        <table align="center" class="tablas" width="95%" >
        <caption >Listado de Gastos Rubro</caption>
        <tr>

	       	<th align="right" width="10px"><b>#</b></th>
	       	<th align="right" width="10px"><b>..</b></th>
        	<th align="center" width="75px"><b>Fecha</b></th>
        	<th align="center" width="75px"><b>Valor</b></th>
        	<th align="center"><b>Descripción</b></th>
        	<th align="center" width="100px"><b>Observacion</b></th>
        </tr>
	<c:forEach begin="0" items="${sessionScope.listaGastosRubro}" var="lista" varStatus="st">
		<tr>
			<td width="10px" class="listas"><c:out value="${st.count}"/></td>
			<td width="10px">
				<c:if test="${lista.tipoGasto==1}"><img src='<c:url value="/comp/img/flag0.gif"/>'></c:if>
				<c:if test="${lista.tipoGasto==-1}"><img src='<c:url value="/comp/img/flag1.gif"/>'></c:if>
			</td>
			<td width="75px" class="listas"><c:out value="${lista.fecha}"/></td>
			<td width="75px" class="listas" align="right"><c:out value="${lista.valorGasto}"/></td>
			<td class="listas"><c:out value="${lista.descripcion}"/></td>
			<td class="listas" width="100px"><c:out value="${lista.observaciones}"/></td>
		</tr>
	</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty sessionScope.listaGastosRubro}">
	<h4 align="center">No hay gastos registrados para este rubro </h4>
	</c:if>
</body>
</html>