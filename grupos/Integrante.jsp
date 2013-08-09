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
		document.comandos.papel.value=document.nuevo.papel.value;
		if(id==11 && document.nuevo.papel.selectedIndex==1){
			alert("No se puede Eliminar el Director del Grupo");
		}else{
			if(id==11){
				if(confirm("Esta opción solo se debe utilizar para vinculaciones erróneas \n en cualquier otro caso utilizar el campo fecha de salida del grupo")){
				document.comandos.submit();
				}
			}else{
				document.comandos.submit();
			}
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
		if(document.nuevo.codFacultad.value==0){
			ms=ms+"\n-) Facultad";
		}
		if(document.nuevo.codproyCurr.value==0){
			ms=ms+"\n-) Proyecto Curricular";
		}
		if(document.nuevo.codigoUd.value==0){
			ms=ms+"\n-) Código UD";
		}
		if(document.nuevo.fechaNacimiento.value==""){
			ms=ms+"\n-) Fecha de nacimiento";
		}
		if(document.nuevo.deCed.value==""){
			ms=ms+"\n-) Procedencia";
		}
		if(document.nuevo.mailInst.value=="" && document.nuevo.mail.value==""){
			ms=ms+"\n-) Al menos 1 correo";
		}
		if(document.nuevo.papel.selectedIndex==0){
			ms=ms+"\n-) Papel en el grupo";
		}
		if(document.nuevo.genero.selectedIndex==0){
			ms=ms+"\n-) Género";
		}
		if(document.nuevo.codareasnies.selectedIndex==0){
			ms=ms+"\n-) Área Snies";
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
		<input type="hidden" name="papel" value='8'>
		<input type="hidden" name="idGrupo" value='<c:out value="${sessionScope.idGrupo}"/>'>
		<input type="hidden" name="id" value='<c:out value="${sessionScope.integrante2.id}"/>'>
		<input type="hidden" name="bandera" value="0">
		<table width="100%">
			<tr>
				<td width="50%"><img src="<c:url value="/comp/img/Integrantes.gif"/>" onclick="com(6)">
				<%if(loginUsuario.isPerfil("10") && request.getAttribute("st").equals("9")){ %>
				<c:if test='${sessionScope.integrante2.mail!=null and sessionScope.integrante2.mail!=""}'>
				<img src="<c:url value="/comp/img/AsigClave.gif"/>" onclick="com('15')">
				</c:if>
				<img src="<c:url value="/comp/img/EliminaIntegrante.gif"/>" onclick="com(11)">
				<%} %>
				<td id="carga" style="display:none"><img src="<c:url value="/comp/img/cargando2.gif"/>"> Enviando Clave...</td>
			</tr>
		</table>
	</form>

	<table align="center">
		<tr>
			<td align="center"><b><c:out value="${sessionScope.grupo.nombre}"/></b></td>
		</tr>
	</table>
	<br>

	<form action='<c:url value="/grupos/llenarIntegrante.jsp"/>' name="nuevo" method="post">
		<input type="hidden" name="idGrupo" value="<c:out value="${sessionScope.idGrupo}" />">
		<input type="hidden" name="accion" value="<c:out value="${requestScope.flagMod}" />">
		<input type="hidden" name="id" value='<c:out value="${sessionScope.integrante2.id}"/>'>
		<!-- hidden para la bandera datoInt.flag -->

			<table class="tablas" align="center">
		<caption>Datos Personales integrante</caption>

		<tr>
			<th><b>Documento:</b></th>
			<td><input readonly="readonly" type="text" name="cedula" value='<c:out value="${sessionScope.integrante2.cedula}"/>'></td>
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
			<th><b>Codigo UD:</b></th>
			<td>
			<select name="codigoUd">
			<c:forEach begin="0" items="${requestScope.listaCodigos}" var="lista">
				<option value='<c:out value="${lista}"/>' <c:if test="${lista==sessionScope.integrante2.codigoUd}">selected</c:if>><c:out value="${lista}"/></option>
			</c:forEach>
			</select>
<!--  			<input readonly="readonly" type="text" name="codigoUd" value='<c:out value="${sessionScope.integrante2.codigoUd}"/>'></td> -->
		</tr>
		<tr>
			<th><b>Facultad:</b></th>
				<td colspan="3">
					<select name="codFacultad" onchange="ajaxProyecto(this)">
						<option value="0">-----------------------------------------------------------------------</option>
						<option value="1" <c:if test="${sessionScope.integrante2.codFacultad==1}"> selected </c:if>>Facultad de Tecnologia - Politecnica / Tecnologica</option>
						<option value="2" <c:if test="${sessionScope.integrante2.codFacultad==2}"> selected </c:if>>Facultad de Ingenieria</option>
						<option value="3" <c:if test="${sessionScope.integrante2.codFacultad==3}"> selected </c:if>>Facultad de Medio ambiente y recursos naturales</option>
						<option value="4" <c:if test="${sessionScope.integrante2.codFacultad==4}"> selected </c:if>>Facultad de Ciencias y educación</option>
						<option value="5" <c:if test="${sessionScope.integrante2.codFacultad==5}"> selected </c:if>>Facultad de Artes -ASAB</option>
					</select>
				</td>
		</tr>
		<tr>
			<th><b>Proyecto Curricular:</b></th>			
			<td colspan="3">
				<select name="codproyCurr">
					<option value="0">-----------------------------------------------------------------------</option>
					<c:forEach begin="0" items="${sessionScope.integrante2.proyectosCurriculares}" var="lista" varStatus="st">
						<option style="text-transform: lowercase;" value='<c:out value="${lista.codigo}"/>' <c:if test="${sessionScope.integrante2.codproyCurr==lista.codigo}">selected</c:if>><c:out value="${lista.nombre}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th><b>Área de Conocimiento SNIES:</b></th>			
			<td colspan="3">
				<select name="codareasnies">
					<option value="0">-----------------------------------------------------------------------</option>
					<c:forEach begin="0" items="${sessionScope.integrante2.areasSNIES}" var="lista" varStatus="st">
						<option style="text-transform: lowercase;" value='<c:out value="${lista.codigo}"/>' <c:if test="${sessionScope.integrante2.codareasnies==lista.codigo}">selected</c:if>><c:out value="${lista.nombre}"/></option>
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<table width="100%">
					<tr>
						<th><b>Fecha de Nacimiento</b></th>
						<th><b>Tipo:</b></th>
						<th><b>Estado:</b></th>
						<th><b>Genero:</b></th>
					</tr>
					<tr>
						<td>
							<c:if test="${sessionScope.integrante2.fechaNacimiento!=null}">
							<input  type='text' name='fechaNacimiento' class='caj' readonly='true' id='f_date_a' size='13' value='<c:out value="${sessionScope.integrante2.fechaNacimiento}"/>'>
							</c:if>
							<c:if test="${sessionScope.integrante2.fechaNacimiento==null}">
							<input  type='text' name='fechaNacimiento' class='caj' readonly='true' id='f_date_a' size='13' >
							<button type="button" id='f_trigger_a' >...</button>
							<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_a',
					    			ifFormat       :    '%Y-%m-%d',
					    			showsTime      :    true,
					    			button         :    'f_trigger_a',
					    			singleClick    :    false,
					    			step           :    1
				    			})
			    			</script>
							</c:if>

						</td>
						<input type="hidden" name="tipoPer" value='<c:out value="${sessionScope.integrante2.tipoPer}"/>'>
						<td><input readonly="readonly" name="nombreTipoPer" type="text" value='<c:out value="${sessionScope.integrante2.nombreTipoPer}"/>'></td>
						<input type="hidden" name="estado" value='<c:out value="${sessionScope.integrante2.estado}"/>'>
						<td><input readonly="readonly" name="nombreEstado" type="text" value='<c:out value="${sessionScope.integrante2.nombreEstado}"/>'></td>
						<td>
							<select name="genero">
								<option value="0" >-----------</option>
								<option value="1" <c:if test="${sessionScope.integrante2.genero==1}">selected</c:if>>Femenino</option>
								<option value="2" <c:if test="${sessionScope.integrante2.genero==2}">selected</c:if>>Masculino</option>
							</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<th><b>Nombres:</b></th>
			<td><input readonly="readonly" type="text" name="nombres" value='<c:out value="${sessionScope.integrante2.nombres}"/>'></td>
			<th><b>Apellidos:</b></th>
			<td><input readonly="readonly" type="text" name="apellidos" value='<c:out value="${sessionScope.integrante2.apellidos}"/>'></td>
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
			<th><b>Mail Institucional:</b></th>
			<td><input type="text" name="mailInst" value='<c:out value="${sessionScope.integrante2.mailInst}"/>'></td>
			<th><b>Mail Personal:</b></th>
			<td><input type="text" name="mail" value='<c:out value="${sessionScope.integrante2.mail}"/>'></td>
		</tr>
		<tr>
			<td colspan="4">
				<table width="100%">
					<tr>
						<th><b>Papel: </b></th>
						<th><b>Fecha de Ingreso Grupo</b></th>
						<th><b>Fecha de Salida Grupo</b></th>
					</tr>
					<tr>
						<td>
							<select name="papel">
								<option value="0">----------</option>
								<option <c:if test="${sessionScope.integrante2.papel==1}" >selected</c:if> value="1" disabled >Director</option>
								<option <c:if test="${sessionScope.integrante2.papel==2}" >selected</c:if> value="2" >Profesor</option>
								<option <c:if test="${sessionScope.integrante2.papel==3}" >selected</c:if> value="3" >Estudiante</option>
								<option <c:if test="${sessionScope.integrante2.papel==4}" >selected</c:if> value="4" >Egresado</option>
								<option <c:if test="${sessionScope.integrante2.papel==5}" >selected</c:if> value="5" <c:if test="${sessionScope.grupo.tipo==1}">disabled</c:if>>Lider Semillero</option>
								<option <c:if test="${sessionScope.integrante2.papel==6}" >selected</c:if> value="6" >Invitado</option>
							</select>
						</td>
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
							<input type='text' name='fechaSalidaGrupo' class='caj' readonly='true' id='f_date_c' size='13' value='<c:out value="${sessionScope.integrante2.fechaSalidaGrupo}"/>'>
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