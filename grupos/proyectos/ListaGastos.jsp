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
		var cambio="";
		if(op==1){//1 para ocultar 2 para mostrar
			cambio="none";
		}else{
			cambio="block";
		}
		document.getElementById("lexpedicion").style.display=cambio;
		document.tipoPersona.expedicion.style.display=cambio;
	}
	
	function cambiarJuridica(op){
		var cambio="";
		if(op==1){//1 para ocultar 2 para mostrar
			cambio="none";
		}else{
			cambio="block";
		}
		document.getElementById("lrepresentante").style.display=cambio;
		document.tipoPersona.representante.style.display=cambio;
		document.getElementById("lcontacto").style.display=cambio;
		document.tipoPersona.contacto.style.display=cambio;
	}
	
	function cambiarPersona(){
		if(document.tipoPersona.tipoSolicitud.value!=9){//cuendo el tipo de solicitud sea resolucion no habilitara estos campos
			document.getElementById("lnombre").style.display='block';
			document.tipoPersona.nombre.style.display='block';
			document.getElementById("lcedula").style.display='block';
			document.tipoPersona.cedula.style.display='block';
			document.getElementById("ldireccion").style.display='block';
			document.tipoPersona.direccion.style.display='block';
			document.getElementById("lcorreo").style.display='block';
			document.tipoPersona.correo.style.display='block';
			document.getElementById("ltelefono").style.display='block';
			document.tipoPersona.telefono.style.display='block';
			document.getElementById("lcelular").style.display='block';
			document.tipoPersona.celular.style.display='block';
			document.getElementById("lformaPago").style.display='block';
			document.tipoPersona.formaPago.style.display='block';
			document.getElementById("lcargoSupervisor").style.display='block';
			document.tipoPersona.cargoSupervisor.style.display='block';
			document.getElementById("ldepSupervisor").style.display='block';
			document.tipoPersona.depSupervisor.style.display='block';
			
		}
		document.getElementById("lproyecto").style.display='block';
		document.tipoPersona.proyecto.style.display='block';
		document.getElementById("lduracion").style.display='block';
		document.tipoPersona.duracion.style.display='block';
		document.getElementById("lvalorContrato").style.display='block';
		document.tipoPersona.valorContrato.style.display='block';
		document.getElementById("ljustificacion").style.display='block';
		document.tipoPersona.justificacion.style.display='block';
		document.getElementById("lobjetivo").style.display='block';
		document.tipoPersona.objetivo.style.display='block';
		document.getElementById("g1").style.display='block';
		document.getElementById("larchivo").style.display='none';
		document.tipoPersona.archivo.style.display='none';
		if(document.tipoPersona.tipoPersona.value==1){
			if(document.tipoPersona.tipoSolicitud.value!=9){
				cambiarJuridica(1);
				cambiarNatural(2);				
			}
		}if(document.tipoPersona.tipoPersona.value==2){
			if(document.tipoPersona.tipoSolicitud.value!=9){
				cambiarJuridica(2);
				cambiarNatural(1);				
			}
		}
	}
	
	function validar(){
		var mensaje="";
		if(document.tipoPersona.nombre.value==""){
			mensaje+="\n -Nombre / Razón Social";
		}
		if(document.tipoPersona.cedula.value==""){
			mensaje+="\n -Número de Cédula / NIT";
		}
		if(document.tipoPersona.direccion.value==""){
			mensaje+="\n -Dirección";
		}
		if(document.tipoPersona.correo.value==""){
			mensaje+="\n -Correo";
		}
		if(document.tipoPersona.telefono.value==""){
			mensaje+="\n -Teléfono";
		}
		if(document.tipoPersona.celular.value==""){
			mensaje+="\n -Celular";
		}
		if(document.tipoPersona.tipoPersona.value==1){
			if(document.tipoPersona.expedicion.value=="")
				mensaje+="\n -Lugar de Expedición";
		}if(document.tipoPersona.tipoPersona.value==2){
			if(document.tipoPersona.representante.value="")
				mensaje+="\n -Representante Legal";
			if(document.tipoPersona.contacto.value=="")
				mensaje+="\n -Contacto Comercial";
		}
		if(document.tipoPersona.cargoSupervisor.value==""){
			mensaje+="\n -Cargo del Supervisor";
		}
		if(document.tipoPersona.depSupervisor.value==""){
			mensaje+="\n -Dependencia del Supervisor";
		}
		if(document.tipoPersona.formaPago.value==""){
			mensaje+="\n -Forma de Pago";
		}
		if(document.tipoPersona.tipoSolicitud.value==9){
			mensaje="";
		}
		if(document.tipoPersona.proyecto.value==""){
			mensaje+="\n -Proyecto";
		}
		if(document.tipoPersona.duracion.value==""){
			mensaje+="\n -Duración";
		}
		if(document.tipoPersona.valorContrato.value==""){
			mensaje+="\n -Valor del Contrato";
		}
		if(document.tipoPersona.justificacion.value==""){
			mensaje+="\n -Justificación";
		}
		if(document.tipoPersona.objetivo.value==""){
			mensaje+="\n -Objetivo";
		}
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios: "+mensaje;
			alert (mensaje);
		}else{
			bloquear();
			return true;
		}
		return false;
	}
	
	function bloquear(){
		
		document.tipoPersona.tipoPersona.readOnly=true;
		document.tipoPersona.nombre.readOnly=true;
		document.tipoPersona.cedula.readOnly=true;
		document.tipoPersona.expedicion.readOnly=true;
		document.tipoPersona.representante.readOnly=true;
		document.tipoPersona.direccion.readOnly=true;
		document.tipoPersona.correo.readOnly=true;
		document.tipoPersona.telefono.readOnly=true;
		document.tipoPersona.celular.readOnly=true;
		document.tipoPersona.contacto.readOnly=true;
		document.tipoPersona.proyecto.readOnly=true;
		document.tipoPersona.duracion.readOnly=true;
		document.tipoPersona.formaPago.readOnly=true;
		document.tipoPersona.valorContrato.readOnly=true;
		document.tipoPersona.justificacion.readOnly=true;
		document.tipoPersona.objetivo.readOnly=true;
		document.tipoPersona.cargoSupervisor.readOnly=true;
		document.tipoPersona.depSupervisor.readOnly=true;
		document.tipoPersona.archivo.style.display="block";
		document.getElementById("larchivo").style.display="block"
		document.tipoPersona.accion.value="25";
		document.tipoPersona.submit();
	}
	
	function documentos(op){
		if(op>=1&&op<=7){
			document.getElementById("c1").style.display='block';
			document.getElementById("c2").style.display='block';
			document.getElementById("c3").style.display='block';
			document.getElementById("c4").style.display='block';
			document.getElementById("c10").style.display='block';
			document.getElementById("c11").style.display='block';
			document.getElementById("c15").style.display='block';
		}if(op==1||op==2){
			document.getElementById("c5").style.display='block';
			document.getElementById("c9").style.display='block';
			document.getElementById("c12").style.display='block';
			document.getElementById("c13").style.display='block';
		}if(op==3||op==5||op==7){
			document.getElementById("c14").style.display='block';
			document.getElementById("c16").style.display='block';
			document.getElementById("c17").style.display='block';
		}if(op==4||op==6){
			document.getElementById("c9").style.display='block';
		}if(op==8){
			document.getElementById("c1").style.display='block';
			document.getElementById("c2").style.display='block';
			document.getElementById("c4").style.display='block';
			document.getElementById("c6").style.display='block';
			document.getElementById("c7").style.display='block';
			document.getElementById("c8").style.display='block';
			document.getElementById("c15").style.display='block';
		}if(op==9){
			document.getElementById("c18").style.display='block';
			document.getElementById("c19").style.display='block';
			document.getElementById("c20").style.display='block';
			document.getElementById("c21").style.display='block';
			document.getElementById("c22").style.display='block';
			document.getElementById("c23").style.display='block';
			document.getElementById("c24").style.display='block';
		}
	}
	
	function cambiarSolicitud(){
		if(document.tipoPersona.tipoSolicitud.value!=9){
			document.tipoPersona.tipoPersona.style.display="block";	
			document.getElementById("ltipoPersona").style.display="block";
			if(document.tipoPersona.tipoPersona.value!=0){
				cambiarPersona();
			}
		}else{
			document.getElementById("lnombre").style.display='none';
			document.tipoPersona.nombre.style.display='none';
			document.getElementById("lcedula").style.display='none';
			document.tipoPersona.cedula.style.display='none';
			document.getElementById("ldireccion").style.display='none';
			document.tipoPersona.direccion.style.display='none';
			document.getElementById("lexpedicion").style.display='none';
			document.tipoPersona.expedicion.style.display='none';
			document.getElementById("lcorreo").style.display='none';
			document.tipoPersona.correo.style.display='none';
			document.getElementById("ltelefono").style.display='none';
			document.tipoPersona.telefono.style.display='none';
			document.getElementById("lcelular").style.display='none';
			document.tipoPersona.celular.style.display='none';
			document.getElementById("lformaPago").style.display='none';
			document.tipoPersona.formaPago.style.display='none';
			document.getElementById("lcargoSupervisor").style.display='none';
			document.tipoPersona.cargoSupervisor.style.display='none';
			document.getElementById("ldepSupervisor").style.display='none';
			document.tipoPersona.depSupervisor.style.display='none';
			document.tipoPersona.tipoPersona.style.display="none";
			document.getElementById("lrepresentante").style.display="none";
			document.tipoPersona.representante.style.display="none";
			document.getElementById("lcontacto").style.display="none";
			document.tipoPersona.contacto.style.display="none";
			document.getElementById("ltipoPersona").style.display="none";
			cambiarPersona(document.tipoPersona.tipoPersona);
		}
		documentos(document.tipoPersona.tipoSolicitud.value);
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br/>
	<form name="tipoPersona" action='<c:url value="/grupos/proyectos/llenar.jsp"/>' method="post">
	<input type="hidden" name="accion" value="0">
		<table width="95%" align="center" class="tablas">
		<caption>Datos del Contratista</caption>
			<tr>
				<td  align="left"><b><c:out value="Tipo de Solicitud"/></b>
						</td>
						<td><select name="tipoSolicitud" onchange="cambiarSolicitud(this)">
							<option value="0">----</option>
							<option value="1">OPS</option>
							<option value="2">CPS-PN</option>
							<option value="3">CPS-PJ</option>
							<option value="4">OS-PN</option>
							<option value="5">OS-PJ</option>
							<option value="6">OC-PN</option>
							<option value="7">OC-PJ</option>
							<option value="8">OPSAR</option>
							<option value="9">RESOLUCION DE AVANCE</option>
						</select>
				</td>
			</tr>
			<tr>
				<td  align="left"><b><label id="ltipoPersona">Tipo de Persona:</label></b></td>
						<td><select name="tipoPersona" onchange="cambiarPersona(this)">
							<option value="0">----</option>
							<option value="1">Persona Natural</option>
							<option value="2">Persona Jurídica</option>
						</select>
					</td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lnombre" style="${requestScope.basico}">Nombres y Apellidos / Razón Social:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="nombre" MAXLENGTH="50" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr>
				<td align="left" ><b><label id="lcedula" style="${requestScope.basico}">Número de Cedula / NIT:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="cedula" MAXLENGTH="15" TYPE="TEXT" VALUE="" style="${requestScope.basico}"/></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lexpedicion" style="${requestScope.natural}">Lugar de Expedición:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="expedicion" MAXLENGTH="20" TYPE="TEXT" VALUE="" style="${requestScope.natural}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lrepresentante" style="${requestScope.juridica}">Representante Legal:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="representante" MAXLENGTH="50" TYPE="TEXT" VALUE="" style="${requestScope.juridica}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="ldireccion" style="${requestScope.basico}">Dirección:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="direccion" MAXLENGTH="20" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lcorreo" style="${requestScope.basico}">Correo Electrónico:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="correo" MAXLENGTH="50" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="ltelefono" style="${requestScope.basico}">Teléfono Fijo:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="telefono" MAXLENGTH="7" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lcelular" style="${requestScope.basico}">Teléfono Celular:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="celular" MAXLENGTH="10" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lcontacto" style="${requestScope.juridica}">Contacto Comercial:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="contacto" MAXLENGTH="50" TYPE="TEXT" VALUE="" style="${requestScope.juridica}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lproyecto" style="${requestScope.basico}">Nombre del Proyecto / Convenio:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="proyecto" MAXLENGTH="200" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lduracion" style="${requestScope.basico}">Duración:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="duracion" MAXLENGTH="20" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lformaPago" style="${requestScope.basico}">Forma de Pago:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="formaPago" MAXLENGTH="20" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="lvalorContrato" style="${requestScope.basico}">Valor Total del Contrato:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="valorContrato" MAXLENGTH="10" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="ljustificacion" style="${requestScope.basico}">Justificación:</label></b></td>
				<td align="left" colspan="3"><b><textarea NAME="justificacion" style="${requestScope.basico}"></textarea></b></td>
			</tr>
			<tr>
				<td align="left" ><b><label id="lobjetivo" style="${requestScope.basico}">Objetivo:</label></b></td>
				<td align="left" colspan="3"><b><textarea NAME="objetivo" style="${requestScope.basico}" ></textarea></b></td>
			</tr>
			<tr>
				<td align="left" ><b><label id="lcargoSupervisor" style="${requestScope.basico}">Cargo del Supervisor:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="cargoSupervisor" MAXLENGTH="50" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr >
				<td align="left" ><b><label id="ldepSupervisor" style="${requestScope.basico}">Dependencia del Supervisor:</label></b></td>
				<td align="left" colspan="3"><b><INPUT NAME="depSupervisor" MAXLENGTH="50" TYPE="TEXT" VALUE="" style="${requestScope.basico}"></b></td>
			</tr>
			<tr>
				<td id="g1" style="${requestScope.basico}"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="validar()" ></td>
			</tr>
			<tr>
				<td align="left"><label id="larchivo" style="display:none"><b>Documentos Adjuntos</b></label>
				<td align="left" colspan="3"><input type="file" name="archivo" style="display:none"/></td>
			</tr>
		</table>
		<table width="95%" align="center" class="tablas">
			<tr>
				<td align="center"><label id="mensaje"  style="display:block">Señor investigador<br> Tenga en cuenta que usted deberá cargar un solo archivo el cual contenga los siguientes documentos:</label>
			</tr>
			<TR>
				<td id="c1" style="display:none" align="left">
					<input type="checkbox" name="propuesta" value="1">Propuesta de Servicios<br>
				</td>
			</TR>
			<tr><td id="c2" style="display:none"><input type="checkbox" name="hojaPersonal" value="2" >Hoja de vida Personal Con Soportes <br>
			</td></tr>
			<tr><td id="c3" style="display:none"><input type="checkbox" name="hojaPublica" value="3">Hoja de Vida Función Publica<br>
			</td></tr>
			<tr><td id="c4" style="display:none"><input type="checkbox" name="fotocopiaCedula" value="4">Fotocopia Cédula de Ciudadanía<br>
			</td></tr>
			<tr><td id="c5" style="display:none"><input type="checkbox" name="tarjetaProf" value="5" >Tarjeta Profesional (si aplica)<br>
			</td></tr>
			<tr><td id="c6" style="display:none"><input type="checkbox" name="carnet" value="6">Carnet Estudiantil<br>
			</td></tr>
			<tr><td id="c7" style="display:none"><input type="checkbox" name="recibo" value="7">Recibo de Pago<br>
			</td></tr>
			<tr><td id="c8" style="display:none"><input type="checkbox" name="certEstudiantil" value="8" >Certificado Estudiantil<br>
			</td></tr>
			<tr><td id="c9" style="display:none"><input type="checkbox" name="certPersoneria" value="9">Certificado Personeria Distrital<br>
			</td></tr>
			<tr><td id="c10" style="display:none"><input type="checkbox" name="certProcuraduria" value="10">Antecedentes Disciplinarios - Procuraduría<br>
			</td></tr>
			<tr><td id="c11" style="display:none"><input type="checkbox" name="certContraloria" value="11" >Antecedentes Fiscales - Contraloría<br>
			</td></tr>
			<tr><td id="c12" style="display:none"><input type="checkbox" name="certSalud" value="12">Certificado de Afilicación a Salud<br>
			</td></tr>
			<tr><td id="c13" style="display:none"><input type="checkbox" name="certPension" value="13">Certificado de Afilicación a Pensiones<br>
			</td></tr>
			<tr><td id="c14" style="display:none"><input type="checkbox" name="certParafiscal" value="14" >Certificado de Pago de Parafiscales<br>
			</td></tr>
			<tr><td id="c15" style="display:none"><input type="checkbox" name="rut" value="15">Registro Único Tributario - RUT<br>
			</td></tr>
			<tr><td id="c16" style="display:none"><input type="checkbox" name="rup" value="16">Registro Único de Proponentes<br>
			</td></tr>
			<tr><td id="c17" style="display:none"><input type="checkbox" name="proveedor" value="17" >Concepto Técnico de Selección de Proveedores<br>
			</td></tr>
			<tr><td id="c18" style="display:none"><input type="checkbox" name="avance" value="18">Solicitud de Avance<br>
			</td></tr>
			<tr><td id="c19" style="display:none"><input type="checkbox" name="presupuesto" value="19" >Presupuesto<br>
			</td></tr>
			<tr><td id="c20" style="display:none"><input type="checkbox" name="pazySalvo" value="20">Paz y Salvos de Avances (Visto Bueno de Tesoreria)<br>
			</td></tr>
			<tr><td id="c21" style="display:none"><input type="checkbox" name="cotizacion" value="21">Cotizaciones<br>
			</td></tr>
			<tr><td id="c22" style="display:none"><input type="checkbox" name="avalFacultad" value="22">Aval del Consejo de Facultad (si aplica)<br>
			</td></tr>
			<tr><td id="c23" style="display:none"><input type="checkbox" name="avalAcademico" value="23">Aval del Consejo Académico (si aplica)<br>
			</td></tr>
			<tr><td id="c24" style="display:none"><input type="checkbox" name="cronograma" value="24">Cronograma<br>
			</td></tr>
			<tr>
				<td id="g2"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="documentos()"></td>
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