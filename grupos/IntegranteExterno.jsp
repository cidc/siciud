<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="loginUsuario" class="cidc.general.login.Usuario" scope="session" />
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" type="time" timeStyle="short" pattern="yyyy-MM-dd" var="hoy"/>
<html>
<head>
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

	function com(id){
		document.comandos.accion.value=id;
		if(id==15)
			document.getElementById("carga").style.display='';
		
		if(id==11){
			if(confirm("Desea eliminar esta persona del Grupo?")){
			document.comandos.submit();
			}
		}else{
			document.comandos.submit();
		}
		
	}
	function ajaxProyecto(obj){
		document.frmAjaxProyecto.dato.value=obj.options[obj.selectedIndex].value;
		document.frmAjaxProyecto.para.value='1';
	 	document.frmAjaxProyecto.target="frameOculto";
		document.frmAjaxProyecto.submit();
	}

	function guardar(acc){
		if(acc==1){
			if(validar()){
				document.nuevo.action='<c:url value="/adminGrupos/adminIntegrantes/llenar.jsp"/>';
				document.nuevo.submit();
			}
		}else{
			document.nuevo.action='<c:url value="/adminGrupos/AdminGrupos.x"/>';
			document.nuevo.accion.value="12";
			document.nuevo.submit();
		}
	}

	function validar(){
		ms="";
		
		if(document.nuevo.deCed.value==""){
			ms=ms+"\n-) Procedencia";
		}
		if(document.nuevo.mailInst.value=="" && document.nuevo.mail.value==""){
			ms=ms+"\n-) Al menos 1 correo";
		}
		if(document.nuevo.genero.selectedIndex==0){
			ms=ms+"\n-) Género";
		}
		if(document.nuevo.cedula.value==""){
			ms=ms+"\n-) Cédula";
		}
		if(document.nuevo.tipoCed.selectedIndex==0){
			ms=ms+"\n-) Tipo de documento";
		}
		if(document.nuevo.nombres.value==""){
			ms=ms+"\n-) Nombres";
		}
		if(document.nuevo.apellidos.value==""){
			ms=ms+"\n-) Apellidos";
		}if(document.nuevo.cel1.value==""){
			ms=ms+"\n-) Celular 1";
		}


		if(document.nuevo.cvlac.value!=""){
			if(document.nuevo.cvlac.value.length>=64){
				var dir=document.nuevo.cvlac.value.substring(0,67);
				if(dir!="http://201.234.78.173:8081/cvlac/visualizador/generarCurriculoCv.do")
					mensaje=mensaje+"\n-) Dirección no válida para CvLac";
			}else{
				mensaje=mensaje+"\n-) Dirección no válida para CvLac";
			}
		}
		if(ms!=""){
			ms="Los siguientes campos son obligatorios: "+ms;
			alert (ms);
		}
		else
			return true;
		return false;
	}
	function deshabilitar(){
		for(var i=2;i<18;i++){
			document.nuevo.elements[i].disabled=true;
		}
		alert("Este integrante está desvinculado del grupo actualmente");
	}

	function acci(acc){
		if(validar()){
			document.nuevo.accion.value=acc;
			document.nuevo.submit();
		}
	}


</script>


</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<br>
	<form name="comandos" action='<c:url value="/grupos/GestionGrupo.x"/>' method="post">
		<input type="hidden" name="accion" value=''>
		
		<input type="hidden" name="idGrupo" value='<c:out value="${sessionScope.idGrupo}"/>'>
		<input type="hidden" name="id" value='<c:out value="${sessionScope.integrante2.id}"/>'>
		<input type="hidden" name="bandera" value="0">
		<table width="100%">
			<tr>
				<td width="50%"><img src="<c:url value="/comp/img/Integrantes.gif"/>" onclick="com(6)">
				<%if(loginUsuario.isPerfil("10") && request.getAttribute("st").equals("9")){ %>
					<img src="<c:url value="/comp/img/EliminaIntegrante.gif"/>" onclick="com(11)">
				<%} %>
			</tr>
		</table>
	</form>

	<table align="center">
		<tr>
			<td align="center"><b>Favor tener en cuenta que el siguiente formulario es UNICAMENTE para el registro de integrantes que no están adscritos a la Universidad Distrital, por tal razón NO es posible asignarles clave de ingreso al sistema SICIUD y tampoco podrán hacer uso de ninguno de los servicios que se ofrecen en el Sistema de Información para integrantes de grupos o semilleros de investigación</b></td>
		</tr>
	</table>
	<br>

	<form action='<c:url value="/grupos/llenarIntegrante.jsp"/>' name="nuevo" method="post">
		<input type="hidden" name="papel" value='6'>
		<input type="hidden" name="codigoUd" value='00'>
		<input type="hidden" name="idGrupo" value="<c:out value="${sessionScope.idGrupo}" />">
		<input type="hidden" name="accion" value="<c:out value="${requestScope.flagMod}" />">
		<input type="hidden" name="id" value='<c:out value="${sessionScope.integrante2.id}"/>'>
		<!-- hidden para la bandera datoInt.flag -->

			<table class="tablas" align="center">
		<caption>Datos Personales integrante</caption>

		<tr>
			<th><b>Documento:</b></th>
			<td><input  type="text" name="cedula" value='<c:out value="${sessionScope.integrante2.cedula}"/>'></td>
			<th><b>Tipo Documento:</b></th>
			<td>
				<select name="tipoCed">
				<option value="0">---</option>
				<option value="1" <c:if test="${sessionScope.integrante2.tipoCed==1}">selected</c:if>>C.C.</option>
				<option value="3" <c:if test="${sessionScope.integrante2.tipoCed==3}">selected</c:if>>C.E.</option>
				<option value="2" <c:if test="${sessionScope.integrante2.tipoCed==2}">selected</c:if>>T.I</option>
			</select>
			</td>
		</tr>
		<tr>
			<th><b>Procedencia:</b></th>
			<td><input  type="text" name="deCed" value='<c:out value="${sessionScope.integrante2.deCed}"/>'></td>
			<th><b>Genero:</b></th>
			<td>
				<select name="genero">
					<option value="0" >-----------</option>
					<option value="1" <c:if test="${sessionScope.integrante2.genero==1}">selected</c:if>>Femenino</option>
					<option value="2" <c:if test="${sessionScope.integrante2.genero==2}">selected</c:if>>Masculino</option>
				</select>
			</td>
		</tr>		
		<tr>
			<th><b>Nombres:</b></th>
			<td><input type="text" name="nombres" value='<c:out value="${sessionScope.integrante2.nombres}"/>'></td>
			<th><b>Apellidos:</b></th>
			<td><input type="text" name="apellidos" value='<c:out value="${sessionScope.integrante2.apellidos}"/>'></td>
		</tr>
		<tr>
			<th><b>Telefono 1:</b></th>
			<td><input type="text" name="tel1" value='<c:out value="${sessionScope.integrante2.tel1}"/>' onkeypress="return numeros(event)"></td>
			<th><b>Telefono 2:</b></th>
			<td><input type="text" name="tel2" value='<c:out value="${sessionScope.integrante2.tel2}"/>' onkeypress="return numeros(event)"></td>
		</tr>
		<tr>
			<th><b>Celular 1:</b></th>
			<td><input type="text" name="cel1" value='<c:out value="${sessionScope.integrante2.cel1}"/>' onkeypress="return numeros(event)"></td>
			<th><b>Celular 2:</b></th>
			<td><input type="text" name="cel2" value='<c:out value="${sessionScope.integrante2.cel2}"/>' onkeypress="return numeros(event)"></td>
		</tr>
		<tr>
			<th><b>Mail Personal 1:</b></th>
			<td><input type="text" name="mail" value='<c:out value="${sessionScope.integrante2.mail}"/>'></td>
			<th><b>Mail Personal 2:</b></th>
			<td><input type="text" name="mailInst" value='<c:out value="${sessionScope.integrante2.mailInst}"/>'></td>
		</tr>
		<tr>
			<td colspan="4">
				<table width="100%">
					<tr>
						<th><b>Fecha de Ingreso Grupo</b></th>
						<th><b>Fecha de Salida Grupo</b></th>
					</tr>
					<tr>
						<td>
							<input type='text' name='fechaVinculacion' class='caj' readonly='true' id='f_date_b' size='13' value='<c:out value="${sessionScope.integrante2.fechaVinculacion}" default="${hoy}"/>'>
							<button type="button" id='f_trigger_b' >...</button>
							<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_b',
					    			ifFormat       :    '%Y-%m-%d',
					    			showsTime      :    true,
					    			button         :    'f_trigger_b',
					    			singleClick    :    false,
					    			step           :    1
				    			})
			    			</script>
						</td>
						<td>
							<input type='text' name='fechaSalida' class='caj' readonly='true' id='f_date_c' size='13' value='<c:out value="${sessionScope.integrante2.fechaSalida}"/>'>
							<button type='button' id='f_trigger_c' >...</button>
							<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_c',
					    			ifFormat       :    '%Y-%m-%d',
					    			showsTime      :    true,
					    			button         :    'f_trigger_c',
					    			singleClick    :    false,
					    			step           :    1
				    			})
			    			</script>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
				<th colspan="4"><b>Link a CvLac</b></th>
			</tr>
			<tr id="cvl1" <c:if test="${sessionScope.integrante2.cvlac!=null}">style="display:none;"</c:if>>
				<td colspan="4"><input style="width:100%" type="text" name="cvlac" value='<c:out value="${sessionScope.integrante2.cvlac}"/>'></td>
			</tr>
				<c:if test="${sessionScope.integrante2.cvlac!=null}">
			<tr id="cvl2">
				<td colspan="3"><A href='<c:out value="${sessionScope.integrante2.cvlac}"/>' target="_new">Click aqui para validar Link de CvLac almacenado</A></td>
				<td><img src='<c:url value="/comp/img/Modificar.gif" />' onclick="document.getElementById('cvl1').style.display='';document.getElementById('cvl2').style.display='none';"></td>
			</tr>
				</c:if>
	</table>
	<br>
	<c:if test="${sessionScope.loginUsuario.cedula==sessionScope.integrante2.cedula}">
		<table align="center">
		<tr>
		<td>
			<img src="<c:url value="/comp/img/Modificar.gif"/>" onclick="acci(<c:out value="${requestScope.st}" />)">
		</td>
		</tr>
		</table>
	</c:if>
	<c:if test="${sessionScope.loginUsuario.cedula!=sessionScope.integrante2.cedula}">
	<%if(loginUsuario.isPerfil("10")){ %>
	<center>
	<img src="<c:url value="/comp/img/Cancelar.gif"/>" onclick="acci(20)">
	<c:if test="${requestScope.flagMod!=1}">
		<img src="<c:url value="/comp/img/Modificar.gif"/>" onclick="acci(<c:out value="${requestScope.st}" />)">
	</c:if>
	<c:if test="${requestScope.flagMod==1}">
		<img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="acci(<c:out value="${requestScope.st}" />)">
	</c:if>
	</center>
	<%} %>
	</c:if>
</form>
	<table>
		<tr>
			<td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td>
		</tr>
	</table>
	<form method="post" name="frmAjaxProyecto" action="<c:url value="/adminGrupos/Ajax.x"/>">
		<input type="hidden" name="dato" value=''>
		<input type="hidden" name="para" value=''>
	</form>

</body>
<script type="text/javascript">
	ajaxProyecto(document.nuevo.facultad);
	if(document.nuevo.fechaSalida.value!="")
		deshabilitar();
</script>
</html>