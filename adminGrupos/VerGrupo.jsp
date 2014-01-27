<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp"/>
<script>

	function cambioTipo(boton){
		if(boton.value==2){
			document.nuevo.tipo.value=2;
			document.nuevo.categoria.disabled=true;
		}
		else{
			document.nuevo.tipo.value=1;
			document.nuevo.categoria.disabled=false;
		}
	}
	function ver(cmd){
		document.comandos.accion.value=cmd;
		document.comandos.submit();
	}

	function guardar(){
		if(validar()){
			document.nuevo.action='<c:url value="/adminGrupos/llenar.jsp"/>';
			document.nuevo.submit();
		}
	}

	function validar(){
		mensaje="";
		if(document.nuevo.nombre.value==""){
			mensaje=mensaje+"\n-) Nombre del Grupo o Semillero";
		}
		if(document.nuevo.tipo.value=="0"){
			mensaje=mensaje+"\n-) Tipo";
		}
		if(document.nuevo.dirNombre.value==""){
			mensaje=mensaje+"\n-) Nombre del Director";
		}
		if(document.nuevo.dirApellido.value==""){
			mensaje=mensaje+"\n-) Apellido del Director";
		}
		if(document.nuevo.tipo.value=="1" && document.nuevo.categoria.selectedIndex==0){
			mensaje=mensaje+"\n-) Categoría Colciencias";
		}
		if(document.nuevo.codAreaSNIES.value=="0"){
			mensaje=mensaje+"\n-) Area Snies";
		}
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios: "+mensaje;
			alert (mensaje);
		}
		else
			return true;
		return false;
	}

	function ajaxProyecto(obj){
		document.frmAjaxProyecto.dato.value=obj.options[obj.selectedIndex].value;
		document.frmAjaxProyecto.para.value='1';
	 	document.frmAjaxProyecto.target="frameOculto";
		document.frmAjaxProyecto.submit();
	}
	
	function validarNro(e) {
		var key;
		if(window.event){ // IE
			key = e.keyCode;
		}
		else if(e.which){ // Netscape/Firefox/Opera
				key = e.which;
			}
	
		if (key < 48 || key > 57)
		    {
		    if(key == 8) // Detectar . (punto) y backspace (retroceso)
		        { return true; }
		    else 
		        { return false; }
		    }
		return true;
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
	<form action='<c:url value="/adminGrupos/AdminGrupos.x" />' name="comandos" method="post">
		<input type="hidden" name="accion" value="">
		<input type="hidden" name="codigo" value='<c:out value="${sessionScope.infoGrupo.codigo}"/>'>
		<table width="100%">
			<tr>
				<td  align="left">
					<img src="<c:url value="/comp/img/Integrantes.gif"/>" onclick="ver(6)">
					<a href='<c:url value="/inventario/InventarioGrupos.x?accion=4&idGrupo=${sessionScope.grupo.codigo}" />'><img border="0" src="<c:url value="/comp/img/Inventario2.gif"/>"></a>
				</td>
				<td width="50%" align="left">
					<a href='<c:url value="/adminGrupos/AdminGrupos.x?accion=0" />'><img border="0" src="<c:url value="/comp/img/Home.png"/>"></a>
				</td>
			</tr>
		</table>
	</form>
	<table align="center">
		<tr>
			<td align="center"><b><c:out value="${sessionScope.infoGrupo.nombre}" /></b></td> 
		</tr>
	</table>
	<br>
	<form name="nuevo" method="post" action='<c:url value="/adminGrupos/llenar.jsp" />'>
		<input type="hidden" name="accion" value="5">
		<input type="hidden" name="codigo" value='<c:out value="${sessionScope.grupo.codigo}"/>'>
		<input type="hidden" name="idDir" value='<c:out value="${sessionScope.grupo.idDir}"/>'>
		<table align="center" width="80%">
			<tr>
				<td>
					<table class="tablas" align="center" width="100%" border="1">
					<CAPTION>Información general Grupo / Semillero investigación</CAPTION>
						<tr>
							<td class="renglones"><b>Facultad:</b></td>
							<td>
								<select name="facultad" onchange="ajaxProyecto(this)">
									<option value="1" <c:if test="${sessionScope.grupo.facultad==1}">selected</c:if>>Tecnológica</option>
									<option value="2" <c:if test="${sessionScope.grupo.facultad==2}">selected</c:if>>Ingeniería</option>
									<option value="3" <c:if test="${sessionScope.grupo.facultad==3}">selected</c:if>>Medio Ambiente</option>
									<option value="4" <c:if test="${sessionScope.grupo.facultad==4}">selected</c:if>>Ciencias y Educación</option>
									<option value="5" <c:if test="${sessionScope.grupo.facultad==5}">selected</c:if>>Asab</option>
								</select>
							</td>
							<th colspan="2">Habilitado Movilidad</th>
							<td>
								SI <input type="radio" name="movilidad" value="true" <c:if test="${sessionScope.grupo.movilidad==true}">checked="checked"</c:if>>
								NO <input type="radio" name="movilidad" value="false" <c:if test="${sessionScope.grupo.movilidad==false}">checked="checked"</c:if>>
							</td>
						</tr>
						<tr>
							<td class="renglones">Proyecto Curricular</td>
							<td colspan="4" class="renglones">
								<select name="codproyCurr">
									<option value="0">-----------------------------------------------------------------------</option>
									<c:forEach begin="0" items="${sessionScope.grupo.proyectosCurriculares}" var="lista" varStatus="st">
										<option style="text-transform: lowercase;" value='<c:out value="${lista.codigo}"/>' <c:if test="${sessionScope.grupo.codproyCurr==lista.codigo}">selected</c:if>><c:out value="${lista.nombre}"/></option>
									</c:forEach>
								</select>							
							</td>
						</tr>
						
						<tr>
							<td colspan="5" class="renglones"><b><span id="para">Nombre Grupo/Semillero Investigación</span></b></td>
						</tr>
						<tr>
							<td colspan="5"><input type="text" name="nombre" style="width: 100%" value='<c:out value="${sessionScope.grupo.nombre}" />'></td>
						</tr>
						<tr>
							<td colspan="6">
							<table width="100%">
								<tr>
									<td class="renglones"><b>Siglas</b></td>
									<td><input type="text" name="siglas" size="10" value='<c:out value="${sessionScope.grupo.siglas}"/>'></td>
									<td class="renglones"><b>Tipo</b><input type="hidden" name="tipo" value='<c:out value="${sessionScope.grupo.tipo}"/>'></td>
									<td class="texto"><b>Grupo</b><input type="radio" name="boton" <c:if test="${sessionScope.grupo.tipo==1}">checked="checked"</c:if> value="1" onClick="cambioTipo(this)"></td>
									<td class="texto"><b>Semillero</b><input type="radio" <c:if test="${sessionScope.grupo.tipo==2}">checked="checked"</c:if> name="boton" value="2" onClick="cambioTipo(this)"></td>
									<td class="renglones"><b>Código Colciencias</b></td>
									<td><input type="text" name="codColciencias" size="20" value='<c:out value="${sessionScope.grupo.codColciencias}"/>'></td>
								</tr>
							</table>
							</td>							
						</tr>
						<tr>
							<td colspan="5" class="renglones"><b>Director</b></td>
						</tr>
						<tr>
							<td class="renglones"><b>Nombres</b></td>
							<td><input type="text" readonly="readonly" name="dirNombre" size="20" value='<c:out value="${sessionScope.grupo.dirNombre}"/>' ></td>
							<td class="renglones"><b>Apellidos</b></td>
							<td colspan="2"><input type="text" readonly="readonly" name="dirApellido" size="20" value='<c:out value="${sessionScope.grupo.dirApellido}"/>'></td>
						</tr>
						<tr>
							<td colspan="5">
								<table width="100%">
									<tr>
										<td width="30%" class="renglones"><b>Fecha de Creación CIDC</b></td>
										<td >
										<input type='text' name='fechaCreacion' style="width:80%" class='caj' readonly='true' id='f_date_b' size='13' value='<c:out value="${sessionScope.grupo.fechaCreacion}"/>'>
										<button type='button' id='f_trigger_b'>...</button>
										<script type='text/javascript'>
							    			Calendar.setup({
								    			inputField     :    'f_date_b',
								    			ifFormat       :    '%d/%m/%Y',
								    			showsTime      :    true,
								    			button         :    'f_trigger_b',
								    			singleClick    :    false,
								    			step           :    1
							    			})
						    			</script>
										</td>
										<td class="renglones"><b>Número de Acta CIDC</b></td>
										<td>
												<input type="text" name="numeroCIDC" onkeypress="javascript:return validarNro(event)" value="${sessionScope.grupo.numeroCIDC}">
										</td>
										<td class="renglones"><b>Archivo Acta CIDC</b></td>
										<td>
											<a href="/siciud/Documentos/Actas_Grupos/${sessionScope.grupo.actaCidc}" target="_blank"><img src="/siciud/comp/img/pdf.png" /></a>
										</td>
									</tr>
									<tr>
										<td width="30%" class="renglones"><b>Fecha de Creación Facultad</b></td>
										<td >
											<input type='text' name='fechaCreacionFacultad' style="width:80%" class='caj' readonly='true' id='f_date_b_fac' size='13' value="${sessionScope.grupo.fechaCreacionFacultad}">
											<button type='button' id='f_trigger_b_fac'>...</button>
											<script type='text/javascript'>
								    			Calendar.setup({
									    			inputField     :    'f_date_b_fac',
									    			ifFormat       :    '%d/%m/%Y',
									    			showsTime      :    true,
									    			button         :    'f_trigger_b_fac',
									    			singleClick    :    false,
									    			step           :    1
								    			})
							    			</script>
										</td>
										<td class="renglones"><b>Número de acta</b></td>
										<td>
												<input type="text" name="numeroFac" onkeypress="javascript:return validarNro(event)" value="${sessionScope.grupo.numeroFac}">
										</td>
										<td class="renglones"><b>Archivo Acta FAcultad</b></td>
										<td>
											<a href="/siciud/Documentos/Actas_Grupos/${sessionScope.grupo.actaFacultad}" target="_blank"><img src="/siciud/comp/img/pdf.png" /></a>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td class="renglones"><b>Categoría Colciencias</b></td>
							<td>
								<select name="categoria">
									<option value="0">--------------</option>
									<option value="1" <c:if test="${sessionScope.grupo.categoria==1}">selected</c:if>>Sin Clasificación</option>
									<option value="2" <c:if test="${sessionScope.grupo.categoria==2}">selected</c:if>>Institucionalizado en UD</option>
									<option value="6" <c:if test="${sessionScope.grupo.categoria==6}">selected</c:if>>Reconocido - Colciencias</option>
									<option value="7" <c:if test="${sessionScope.grupo.categoria==7}">selected</c:if>>D - Colciencias</option>
									<option value="3" <c:if test="${sessionScope.grupo.categoria==3}">selected</c:if>>C - Colciencias</option>
									<option value="4" <c:if test="${sessionScope.grupo.categoria==4}">selected</c:if>>B - Colciencias</option>
									<option value="5" <c:if test="${sessionScope.grupo.categoria==5}">selected</c:if>>A - Colciencias</option>
									<option value="8" <c:if test="${sessionScope.grupo.categoria==8}">selected</c:if>>A1 - Colciencias</option>
								</select>
							</td>
							<td colspan="2" class="renglones"><b>Estado</b></td>
							<td colspan="2" >
								<select name="estado" style="width: 100%">
									<option value="1" <c:if test="${sessionScope.grupo.estado==1}">selected</c:if>>Activo</option>
									<option value="2" <c:if test="${sessionScope.grupo.estado==2}">selected</c:if>>Inactivo</option>
								</select>
							</td>
						</tr>
						<tr>
							<td class="renglones">Área de Conocimiento (SNIES)</td>
							<td colspan="4" class="renglones">
								<select name="codAreaSNIES">
									<option value="0">-----------------------------------------------------------------------</option>
									<c:forEach begin="0" items="${sessionScope.grupo.areasSNIES}" var="lista" varStatus="st">
										<option style="text-transform: lowercase;" value='<c:out value="${lista.codigo}"/>' <c:if test="${sessionScope.grupo.codAreaSNIES==lista.codigo}">selected</c:if>><c:out value="${lista.nombre}"/></option>
									</c:forEach>
								</select>							
							</td>
						</tr>
						<tr>
							<td colspan="5" class="renglones"><b>Correo Electrónico</b></td>
						</tr>
						<tr>
							<td colspan="5"><input type="text" name="mail" size="75" value='<c:out value="${sessionScope.grupo.mail}"/>' ></td>
						</tr>
						<tr>
							<td colspan="5" class="renglones"><b>GrupLac</b></td>
						</tr>
						<tr>
							<td colspan="5"><input type="text" name="grupLac" size="75" value='<c:out value="${sessionScope.grupo.grupLac}"/>' ></td>
						</tr>
						<tr>
							<td colspan="5" class="renglones"><b>Página Web</b></td>
						</tr>
						<tr>
							<td colspan="5"><input type="text" name="web" size="75" value='<c:out value="${sessionScope.grupo.web}"/>'></td>
						</tr>
						<tr>
							<td colspan="5" class="renglones"><b>Misión del Grupo</b></td>
						</tr>
						<tr>
							<td colspan="5"><textarea name="mision" style="width:100%"><c:out value="${sessionScope.grupo.mision}"/></textarea></td>
						</tr>
						<tr>
							<td colspan="5" class="renglones"><b>Visión del Grupo</b></td>
						</tr>
						<tr>
							<td colspan="5"><textarea name="vision" style="width:100%"><c:out value="${sessionScope.grupo.vision}"/></textarea></td>
						</tr>
						<tr>
							<td colspan="5" class="renglones"><b>Descripción del Grupo</b></td>
						</tr>
						<tr>
							<td colspan="5"><textarea name="descripcion" style="width:100%"><c:out value="${sessionScope.grupo.descripcion}"/></textarea></td>
						</tr>
						<tr>
							<td colspan="5" class="renglones"><b>Observaciones</b></td>
						</tr>						
						<tr>
							<td colspan="5"><textarea name="observaciones" style="width:100%"><c:out value="${sessionScope.grupo.observaciones}"/></textarea></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
			<td align="center"><img  src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"></td>
			</tr>
		</table>				
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
<script>
if(document.nuevo.tipo.value=="2")
	document.nuevo.categoria.disabled=true;
</script>
</html>