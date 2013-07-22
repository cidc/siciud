<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<script type="text/javascript" language="javascript" src='<c:url value="/comp/js/lytebox.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
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
		if(document.nuevo.fechaCreacion.value==""){
			mensaje=mensaje+"\n-) Fecha de Creación";
		}
		if(document.nuevo.nombDirector.value==""){
			mensaje=mensaje+"\n-) Nombre del Director";
		}
		if(document.nuevo.apellDirector.value==""){
			mensaje=mensaje+"\n-) Apellido del Director";
		}
		if(document.nuevo.tipo.value=="1" && document.nuevo.categoria.selectedIndex==0){
			mensaje=mensaje+"\n-) Categoría Colciencias";
		}
		if(document.nuevo.numeroCIDC.value==""){
			mensaje=mensaje+"\n-) Número de acta del CIDC";
		}
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios: "+mensaje;
			alert (mensaje);
		}
		else
			return true;
		return false;
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
		    if(key == 8) // Detectar  backspace (retroceso)
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
	<table width="100%">
		<tr>
			<td align="center">
			<a href='<c:url value="/adminGrupos/AdminGrupos.x?accion=0" />'><img border="0" src="<c:url value="/comp/img/Home.png"/>"></a>
			</td>
		</tr>
	</table>
	<form name="nuevo" method="post">
	<input type="hidden" name="accion" value="1">
			<table class="tablas" align="center" width="65%" >
			<caption>Creación de Grupo/Semillero de Investigación</caption>
				<tr>
					<td>
						<table width="100%">
							<tr>
								<td colspan="5">
									<table width="100%">
										<tr>
											<td class="renglones"><b>Facultad:</b></td>
											<td colspan="2">
												<select name="facultad" onchange="ajaxGrupos(this)" >
													<option value="1">Tecnológica</option>
													<option value="2">Ingeniería</option>
													<option value="3">Medio Ambiente</option>
													<option value="4">Ciencias y Educación</option>
													<option value="5">Asab</option>
												</select>
											</td>
											<td class="renglones"><b>Siglas</b></td>
											<td><input type="text" name="siglas" size="20" value=""></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="5" class="renglones"><b><span id="para">Nombre Grupo/Semillero Investigación</span></b></td>
							</tr>
							<tr>
								<td colspan="5"><input type="text" name="nombre" size="75"></td>
							</tr>
							<tr>
								<td colspan="5">
									<table width="100%">
										<tr>
											<td class="renglones"><b>Tipo</b><input type="hidden" name="tipo" value="0"></td>
											<td class="texto"><b>Grupo</b><input type="radio" name="boton" checked="checked" value="1" onClick="cambioTipo(this)"></td>
											<td class="texto"><b>Semillero</b><input type="radio" name="boton" value="2" onClick="cambioTipo(this)"></td>
											<td class="renglones"><b>Categoría Colciencias</b></td>
											<td>
												<select name="categoria" style="width: 100%">
													<option value="1">Sin Clasificación</option>
													<option value="2">Institucionalizado en UD</option>
													<option value="6">Reconocido - Colciencias</option>
													<option value="7">D - Colciencias</option>
													<option value="3">C - Colciencias</option>
													<option value="4">B - Colciencias</option>
													<option value="5">A - Colciencias</option>
													<option value="8">A1 - Colciencias</option>
												</select>
											</td>
											<td class="renglones"><b>Estado</b></td>
											<td>
												<select name="estado" style="width: 100%">
													<option value="1">Activo</option>
													<option value="2">Inactivo</option>
												</select>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="5">
									<table width="100%">
										<tr>
											<td class="renglones"><b>Fecha de Creación CIDC</b></td>
											<td >
											<input type='text' name='fechaCreacion' class='caj' readonly='true' id='f_date_b' size='13'>
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
												<input type="text" name="numeroCIDC" onkeypress="javascript:return validarNro(event)">
											</td>
										</tr>
										<tr>
											<td class="renglones"><b>Fecha de Creación Facultad</b></td>
											<td >
											<input type='text' name='fechaCreacionFacultad' class='caj' readonly='true' id='f_date_b_fac' size='13'>
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
												<input type="text" name="numeroFac" onkeypress="javascript:return validarNro(event)">
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									<table class="tablas" width="100%">
									<CAPTION>director</CAPTION>
										<tr>
											<td class="renglones"><b>Nombres</b></td>
											<td><input readonly="readonly" type="text" name="nombDirector"></td>
											<td class="renglones"><b>Apellidos</b></td>
											<td><input readonly="readonly" type="text" name="apellDirector"></td>
											<td><input type="hidden" name="cedDirector">
												<input type="hidden" name="codUdDirector">
												<a href='<c:url value="/adminGrupos/AdminGrupos.x?accion=20&director=si"/>'
														target="_parent" rel="lyteframe" title="Buscar Director Grupo/Semillero" rev="width: 550px; height: 370px; scrolling: auto;">
												<img src='<c:url value="/comp/img/humanito.gif"/>' border="0">
											</a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td colspan="5" class="renglones"><b>Correo Electrónico</b></td>
							</tr>
							<tr>
								<td colspan="5"><input type="text" name="mail" size="75"></td>
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
					<td align="center"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"></td>
				</tr>
			</table>
	</form>
</body>
</html>