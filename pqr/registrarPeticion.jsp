<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
function guardar(pqrForm){
	if(validarPersona()&&validarCaso()){	
		document.pqrForm.action='<c:url value="/pqr/llenar.jsp"/>';
		document.pqrForm.submit();
	}
	
}

function crearPersona(pqrForm){
	if(validarPersona()){
		document.pqrForm.action='<c:url value="/pqr/llenar.jsp"/>';
		document.pqrForm.accion.value=2;
		document.pqrForm.submit();
	}else{	
	return false;
	}
}

function buscarPersona(){
	document.pqrForm.action='<c:url value="/pqr/llenar.jsp"/>';
	document.pqrForm.accion.value=3;
	document.pqrForm.submit();
}

function documento(){
	document.pqrForm.action='<c:url value="/pqr/CargaArchivos.jsp"/>';
	document.pqrForm.submit();
}
	
function cambiar(obj){
	document.pqrForm.nombre.style.display='block';
	document.getElementById("lnombre").style.display="block";
	document.pqrForm.direccion.style.display='block';
	document.getElementById("ldireccion").style.display='block';
	document.pqrForm.celular.style.display='block';
	document.getElementById("lcelular").style.display='block';
	document.pqrForm.correo.style.display='block';
	document.getElementById("lcorreo").style.display='block';
	document.getElementById("bGuardar").style.display='block';
	
	if(obj.options[obj.selectedIndex].value==1){	
		//externo
			document.pqrForm.tipoExterno.style.display='block';
			document.pqrForm.tipoInterno.style.display='none';
			//document.pqrForm.ltipoInterno.style.display='none';
			document.getElementById("ltipoInterno").style.display='none';
			document.getElementById("ltipoExterno").style.display='block';
			//document.pqrForm.tipoDoc.style.display='none';
			//document.getElementById("ltipoDoc").style.display='none';
			document.pqrForm.proyInv.style.display='none';
			document.getElementById("lproyInv").style.display='none';
			document.pqrForm.codigo.style.display='none';
			document.getElementById("lcodigo").style.display='none';
			document.pqrForm.facultad.style.display='none';
			document.getElementById("lfacultad").style.display='none';
			document.pqrForm.ciudad.style.display='block';
			document.getElementById("lciudad").style.display='block';
			//2 = persona juridica
			if(document.pqrForm.tipoExterno.value==2){
				document.pqrForm.representante.style.display='block';
				document.getElementById("lrepresentante").style.display='block';
				document.pqrForm.contacto.style.display='block';
				document.getElementById("lcontacto").style.display='block';
				document.pqrForm.tipoDoc.style.display='none';
				document.getElementById("ltipoDoc").style.display='none';
			}else{
				document.pqrForm.representante.style.display='none';
				document.getElementById("lrepresentante").style.display='none';
				document.pqrForm.contacto.style.display='none';
				document.getElementById("lcontacto").style.display='none';
				document.pqrForm.tipoDoc.style.display='block';
				document.getElementById("ltipoDoc").style.display='block';
			}
		}
		else {
			//interno
			document.pqrForm.tipoInterno.style.display='block';
			document.pqrForm.tipoExterno.style.display='none';
			//document.pqrForm.ltipoExterno.style.display='none';
			document.getElementById("ltipoExterno").style.display='none';
			document.getElementById("ltipoInterno").style.display='block';
			document.pqrForm.tipoDoc.style.display='block';
			document.getElementById("ltipoDoc").style.display='block';
			document.pqrForm.proyInv.style.display='block';
			document.getElementById("lproyInv").style.display='block';
			document.pqrForm.codigo.style.display='block';
			document.getElementById("lcodigo").style.display='block';
			document.pqrForm.facultad.style.display='block';
			document.getElementById("lfacultad").style.display='block';
			document.pqrForm.representante.style.display='none';
			document.getElementById("lrepresentante").style.display='none';
			document.pqrForm.contacto.style.display='none';
			document.getElementById("lcontacto").style.display='none';
			document.pqrForm.titulo.style.display='block';
			document.getElementById("ltitulo").style.display='block';
			document.pqrForm.ciudad.style.display='none';
			document.getElementById("lciudad").style.display='none';
		}	
	
}

function externo(){
	if(document.pqrForm.tipoExterno.value==51){
		document.pqrForm.titulo.style.display='block';
		document.getElementById("ltitulo").style.display='block';
		document.pqrForm.tipoDoc.style.display='block';
		document.getElementById("ltipoDoc").style.display='block';
		document.pqrForm.representante.style.display='none';
		document.getElementById("lrepresentante").style.display='none';
		document.pqrForm.contacto.style.display='none';
		document.getElementById("lcontacto").style.display='none';
		
	}if(document.pqrForm.tipoExterno.value==52){
		document.pqrForm.titulo.style.display='none';
		document.getElementById("ltitulo").style.display='none';
		document.pqrForm.tipoDoc.style.display='none';
		document.getElementById("ltipoDoc").style.display='none';
		document.pqrForm.representante.style.display='block';
		document.getElementById("lrepresentante").style.display='block';
		document.pqrForm.contacto.style.display='block';
		document.getElementById("lcontacto").style.display='block';
	}
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
	    if(key == 46 || key == 8) // Detectar . (punto) y backspace (retroceso)
	        { return true; }
	    else 
	        { return false; }
	    }
	return true;
}

function validarPersona(){
	mensaje="";
	if(document.pqrForm.documento.value=="")
		mensaje+="\n -Numero de Documento / Nit";
	if(document.pqrForm.nombre.value=="")
		mensaje+="\n -Nombre / Razón Social";
	if(document.pqrForm.direccion.value=="")
		mensaje+="\n -Dirección de Correspondencia";
	if(document.pqrForm.celular.value=="")
		mensaje+="\n -Teléfono";
	if(document.pqrForm.correo.value=="")
		mensaje+="\n -Correo Electrónico";
	if(document.pqrForm.tipoExterno.selectedIndex!=1 && document.pqrForm.titulo.value==""&&document.getElementById("ltitulo").style.display=="block")
		mensaje+="\n -Titulo";
	if(mensaje!=""){
		mensaje="Los siguientes campos son obligatorios: "+mensaje;
	alert (mensaje);
	return false;
	}else
		return true;
	
}

function validarCaso(){
	mensaje="";
	if(document.pqrForm.recepcion.value=="")
		mensaje+="\n -Medio de Recepción";
	if(document.pqrForm.tipoSolicitud.selectedIndex==0)
		mensaje+="\n -Tipo de Requerimiento";
	if(document.pqrForm.asunto.value=="")
		mensaje+="\n -Asunto";
	if(document.pqrForm.descripcion.value=="")
		mensaje+="\n -Descripción";
	if(document.pqrForm.notificacionCorreo.value=="")
		mensaje+="\n -Notificación por Correo";
	if(mensaje!=""){
		mensaje="Los siguientes campos son obligatorios: "+mensaje;
		alert (mensaje);
	}else
		return true;
	return false;
}
</script>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<script type="text/javascript" language="javascript" src='<c:url value="/comp/js/lytebox.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
<html>
<head>
<title>Atención al Ciudadano</title> 
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="pqrForm"  method="post" action='<c:url value="/pqr/llenar.jsp.x"/>'>
<input type="hidden" name="accion" value="1">
<table align="left" class="tablas" width="90%">
	<caption>Información del Solicitante</caption>
				
				<tr>
					<td colspan="4" align="left"><label for="documento" id="ldocumentoIdent">Documento de Identidad / NIT *</label></td>
					<td  align="left"><INPUT NAME="documento" MAXLENGTH="25" TYPE="TEXT"  onkeypress="javascript:return validarNro(event)" VALUE='<c:out value="${sessionScope.personaDatos.documentoIdNit}"/>'>

					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="tipoDoc" id="ltipoDoc" style='<c:out value="${sessionScope.basico}"/>'>Tipo de Documento*</label>
					</td>
					<td><select name="tipoDoc" style='<c:out value="${sessionScope.basico}"/>'>
						<OPTION VALUE="1" <c:if test="${sessionScope.personaDatos.tipoDocumento==1}">selected</c:if>>Cédula de Ciudadanía</OPTION> 
						<OPTION VALUE="2" <c:if test="${sessionScope.personaDatos.tipoDocumento==2}">selected</c:if>>Tarjeta de Identidad</OPTION>
						<OPTION VALUE="3" <c:if test="${sessionScope.personaDatos.tipoDocumento==3}">selected</c:if>>Cédula de Extranjería</OPTION> 
						<OPTION VALUE="4" <c:if test="${sessionScope.personaDatos.tipoDocumento==4}">selected</c:if>>Pasaporte</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="ltipoSol" style='<c:out value="${sessionScope.tipoSolicitante}"/>'>Tipo de Solicitante</label>
					</td>
					<td><select name="tipoSolicitante" onchange="cambiar(this)" style='<c:out value="${sessionScope.tipoSolicitante}"/>'> 
						<OPTION VALUE="2" <c:if test="${sessionScope.personaDatos.tipoPersona!=51&&sessionScope.personaDatos.tipoPersona!=52}">selected</c:if> >Interno</OPTION>
						<OPTION VALUE="1" <c:if test="${sessionScope.personaDatos.tipoPersona==51 ||sessionScope.personaDatos.tipoPersona==52}">selected</c:if>>Externo</OPTION>
						<OPTION VALUE="0" <c:if test="${sessionScope.personaDatos.personaID==null}">selected</c:if>>-------</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="tipoInterno" id="ltipoInterno" style='<c:out value="${sessionScope.opcionales}"/>'>Solicitante Interno</label>
					</td>
					<td><select name="tipoInterno" style='<c:out value="${sessionScope.opcionales}"/>' >
						<OPTION VALUE="0">-------</OPTION>
						<OPTION VALUE="2" <c:if test="${sessionScope.personaDatos.tipoInterno==2}">selected</c:if>>Docente</OPTION>
						<OPTION VALUE="3" <c:if test="${sessionScope.personaDatos.tipoInterno==3}">selected</c:if>>Estudiante</OPTION>
						
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="tipoExterno" id="ltipoExterno" style='<c:out value="${sessionScope.juridico}"/>'>Solicitante Externo</label>
					</td>
					<td><select name="tipoExterno" onchange="externo()" style='<c:out value="${sessionScope.juridico}"/>'>
						<OPTION VALUE="0">-------</OPTION>
						<OPTION VALUE="51" <c:if test="${sessionScope.personaDatos.tipoPersona==51}">selected</c:if>>Persona Natural</OPTION>
						<OPTION VALUE="52" <c:if test="${sessionScope.personaDatos.tipoPersona==52}">selected</c:if>>Persona Jurídica</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="titulo" id="ltitulo" style='<c:out value="${sessionScope.basico}"/>'>Titulo*</label>
					</td>
					<td><select name="titulo"  style='<c:out value="${sessionScope.basico}"/>' >
						<OPTION VALUE="1" <c:if test="${sessionScope.personaDatos.titulo==1}">selected</c:if>>Sr.</OPTION>
						<OPTION VALUE="2" <c:if test="${sessionScope.personaDatos.titulo==2}">selected</c:if>>Sra.</OPTION>
						<OPTION VALUE="3" <c:if test="${sessionScope.personaDatos.titulo==3}">selected</c:if>>Señores</OPTION>
						<OPTION VALUE="4" <c:if test="${sessionScope.personaDatos.titulo==4}">selected</c:if>>Dr.</OPTION> 
						<OPTION VALUE="5" <c:if test="${sessionScope.personaDatos.titulo==5}">selected</c:if>>Ing.</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left" ><label for="nombre" id="lnombre" style='<c:out value="${sessionScope.basico}"/>'>Nombre / Razón Social</label>
					<td><textarea class="area2" class="area2"  style='<c:out value="${sessionScope.basico}"/>'
							name="nombre" id='nombre'><c:out value='${sessionScope.personaDatos.nombreRazonSocial}'/></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="representante" id="lrepresentante" style='<c:out value="${sessionScope.complemento}"/>'>Representante Legal</label>
					<td><input width="100%" class="area2" class="area2"  style='<c:out value="${sessionScope.complemento}" />'
							name="representante" id='representante' value="<c:out value='${sessionScope.personaDatos.representante}'/>">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="contacto" id="lcontacto" style='<c:out value="${sessionScope.complemento}"/>'>Persona de Contacto</label>
					<td><input class="area2" class="area2"  style='<c:out value="${sessionScope.complemento}"/>'
							name="contacto" id='contacto' value="<c:out value='${sessionScope.personaDatos.contacto}'/>">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="direccion" id="ldireccion" style='<c:out value="${sessionScope.basico}"/>'>Dirección de correspondencia</label>
					</td>
					<td  align="left"><INPUT NAME="direccion" MAXLENGTH="25" TYPE="TEXT" style='<c:out value="${sessionScope.basico}"/>' VALUE="<c:out value="${sessionScope.personaDatos.direccion}"/>">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="celular" id="lcelular" style='<c:out value="${sessionScope.basico}"/>'>Teléfono(s)</label>
					</td>
					<td  align="left"><INPUT NAME="celular" MAXLENGTH="25" TYPE="TEXT" style='<c:out value="${sessionScope.basico}"/>' VALUE="<c:out value="${sessionScope.personaDatos.telefonoMovil}"/>">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="correo" id="lcorreo" style='<c:out value="${sessionScope.basico}"/>'>Correo Electrónico</label>
					</td>
					<td  align="left"><INPUT NAME="correo" MAXLENGTH="50" TYPE="TEXT" style='<c:out value="${sessionScope.basico}"/>' VALUE="<c:out value="${sessionScope.personaDatos.correoElectronico}"/>">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="ciudad" id="lciudad" style='<c:out value="${sessionScope.basico}"/>'>Ciudad</label>
					<!--</td>
					<td  align="left"><INPUT NAME="ciudad" MAXLENGTH="25" TYPE="TEXT" style='<c:out value="${sessionScope.basico}"/>' VALUE="<c:out value="${sessionScope.personaDatos.ciudad}"/>">
					</td>-->
					<td><select name="ciudad"  style='<c:out value="${sessionScope.basico}"/>' >
						<OPTION VALUE="2" <c:if test="${sessionScope.personaDatos.ciudad==2}">selected</c:if>>Bogotá</OPTION>
					</select></td>
				</tr>
				
				
				<tr>
					<td  align="right">
							<table>
							<tr><td>
							<input type=image src="/siciud/comp/img/Buscar.gif" onclick="buscarPersona()" align="top">
							</td><td> 
							<img id="bGuardar" src='<c:url value="/comp/img/Guardar.gif"/>' onclick="crearPersona()" style="<c:out value="${requestScope.botonCrear}" />"/>
							</td></tr>
							</table>
					</td>
				</tr>
</table>
<br>
<table class="tablas" width="90%" style="<c:out value="${requestScope.crearCaso}"/>">
	<caption>Información de la Solicitud</caption>
				<tr>
					<td colspan="4" align="left"><c:out value="Archivo del caso"/>
					<td><img border="0" src='<c:url value="/comp/img/Cargar.gif"/>' onclick="documento()"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><c:out value="${sessionScope.nombreArc}"/></td>
				<tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Medio de Recepción"/>
					</td>
					<td><select name="recepcion" >
						<option value="1">Télefono</option>
						<option  value="2">Carta</option>
						<option  value="3">Correo Electrónico</option>
						<option  value="4" selected>WEB</option>
						<option  value="5">Verbal</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Tipo Requerimiento"/>
					</td>
					<td><select name="tipoSolicitud" onchange="">
						<OPTION VALUE="0">-----------------</OPTION>
						<OPTION VALUE="11">Solicitud General</OPTION> 
						<OPTION VALUE="1">Queja</OPTION>
						<OPTION VALUE="2">Reclamo</OPTION> 
						<OPTION VALUE="3">Sugerencia</OPTION>
						<OPTION VALUE="4">Derecho de Petición</OPTION> 
						<OPTION VALUE="5">Solcitud de Información</OPTION>
						<OPTION VALUE="6">Consulta de Documentos</OPTION> 
						<OPTION VALUE="7">Expedición de copias</OPTION>
						<OPTION VALUE="8">Consultas</OPTION> 
						<OPTION VALUE="9">Certificaciones</OPTION>
						<OPTION VALUE="10">Solicitud de otra Dependencia</OPTION> 
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="proyInv" id="lproyInv" style='<c:out value="${sessionScope.opcionales}"/>'>Proyecto de Investigación</label>
					</td>
					<td  align="left"><INPUT NAME="proyInv" MAXLENGTH="250" TYPE="TEXT" style='<c:out value="${sessionScope.opcionales}"/>' VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="codigo" id="lcodigo" style='<c:out value="${sessionScope.opcionales}"/>'>Código</label>
					</td>
					<td  align="left"><INPUT NAME="codigo" MAXLENGTH="25" TYPE="TEXT" style='<c:out value="${sessionScope.opcionales}"/>' VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="facultad" id="lfacultad" style='<c:out value="${sessionScope.opcionales}"/>'>Facultad</label>
					</td>
					<td  align="left"><INPUT NAME="facultad" MAXLENGTH="25" TYPE="TEXT" style='<c:out value="${sessionScope.opcionales}"/>' VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Asunto"/>
					</td>
					<td  align="left"><INPUT NAME="asunto" MAXLENGTH="110" TYPE="TEXT" VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Descripción"/>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="descripcion" id='descripcion'></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Medio de Respuesta"/>
					</td>
					<td><select name="medioRespuesta" >
						<option value="1">Télefono</option>
						<option  value="2">Carta</option>
						<option  value="3">Correo Electrónico</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="¿Recibir notificaciones por correo electrónico?: "/>
					</td>
					<td>
						<input type="radio" name=notificacionCorreo value="true">Si
						<input type="radio" name=notificacionCorreo value="false" checked>No
					</td>
				</tr>	
				<tr>
				<td id="g1"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.pqrForm)"></td>
				</tr>
</table>
</form>
</body>
</html>