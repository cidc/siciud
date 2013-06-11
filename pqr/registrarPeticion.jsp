<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
function guardar(pqrForm){
	//alert("hola");
	document.pqrForm.action='<c:url value="/pqr/llenar.jsp"/>';
	document.pqrForm.submit();
	
}

function buscarPersona(){
	document.pqrForm.action='<c:url value="/pqr/llenar.jsp"/>';
	document.pqrForm.accion.value=3;
	document.pqrForm.submit();
}
	
function cambiar(obj){
	if(obj.options[obj.selectedIndex].value==1){	
		//externo
			document.pqrForm.tipoExterno.style.display='block';
			document.pqrForm.tipoInterno.style.display='none';
			//document.pqrForm.ltipoInterno.style.display='none';
			document.getElementById("ltipoInterno").style.visibility="hidden";
			document.getElementById("ltipoExterno").style.visibility="visible";
			//document.pqrForm.tipoDoc.style.display='none';
			//document.getElementById("ltipoDoc").style.visibility="hidden";
			document.pqrForm.proyInv.style.display='none';
			document.getElementById("lproyInv").style.visibility="hidden";
			document.pqrForm.codigo.style.display='none';
			document.getElementById("lcodigo").style.visibility="hidden";
			document.pqrForm.facultad.style.display='none';
			document.getElementById("lfacultad").style.visibility="hidden";
			if(document.pqrForm.tipoExterno.value==2){
				document.pqrForm.representante.style.display='block';
				document.getElementById("lrepresentante").style.visibility="visible";
				document.pqrForm.contacto.style.display='block';
				document.getElementById("lcontacto").style.visibility="visible";
			}else{
				document.pqrForm.representante.style.display='none';
				document.getElementById("lrepresentante").style.visibility="hidden";
				document.pqrForm.contacto.style.display='none';
				document.getElementById("lcontacto").style.visibility="hidden";
			}
		}
		else {
			//interno
			document.pqrForm.tipoInterno.style.display='block';
			document.pqrForm.tipoExterno.style.display='none';
			//document.pqrForm.ltipoExterno.style.display='none';
			document.getElementById("ltipoExterno").style.visibility="hidden";
			document.getElementById("ltipoInterno").style.visibility="visible";
			document.pqrForm.tipoDoc.style.display='block';
			document.getElementById("ltipoDoc").style.visibility="visible";
			document.pqrForm.proyInv.style.display='block';
			document.getElementById("lproyInv").style.visibility="visible";
			document.pqrForm.codigo.style.display='block';
			document.getElementById("lcodigo").style.visibility="visible";
			document.pqrForm.facultad.style.display='block';
			document.getElementById("lfacultad").style.visibility="visible";
			document.pqrForm.representante.style.display='none';
			document.getElementById("lrepresentante").style.visibility="hidden";
			document.pqrForm.contacto.style.display='none';
			document.getElementById("lcontacto").style.visibility="hidden";
			document.pqrForm.titulo.style.display='block';
			document.getElementById("ltitulo").style.visibility="visible";
		}	
	
}

function externo(){
	if(document.pqrForm.tipoExterno.value==1){
		document.pqrForm.titulo.style.display='block';
		document.getElementById("ltitulo").style.visibility="visible";
		document.pqrForm.tipoDoc.style.display='block';
		document.getElementById("ltipoDoc").style.visibility="visible";
		document.pqrForm.representante.style.display='none';
		document.getElementById("lrepresentante").style.visibility="hidden";
		document.pqrForm.contacto.style.display='none';
		document.getElementById("lcontacto").style.visibility="hidden";
		
	}else{
		document.pqrForm.titulo.style.display='none';
		document.getElementById("ltitulo").style.visibility="hidden";
		document.pqrForm.tipoDoc.style.display='none';
		document.getElementById("ltipoDoc").style.visibility="hidden";
		document.pqrForm.representante.style.display='block';
		document.getElementById("lrepresentante").style.visibility="visible";
		document.pqrForm.contacto.style.display='block';
		document.getElementById("lcontacto").style.visibility="visible";
	}
}
</script>
<html>
<head>
<title>Atención al Ciudadano</title> 
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="pqrForm"  method="post" action='<c:url value="/pqr/llenar.jsp.x"/>' >
<input type="hidden" name="accion" value="1">

<table align="left" class="tablas" width="90%">
	<caption>Información del Solicitante</caption>
				<tr>
					<td colspan="4" align="left"><label for="ltipoSol">Tipo de Solicitud</label>
					</td>
					<td><select name="tipoSol" onchange="cambiar(this)">
						<OPTION VALUE="0">-------</OPTION>
						<OPTION VALUE="2">Interno</OPTION>
						<OPTION VALUE="1">Externo</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="tipoInterno" id="ltipoInterno">Solicitante Interno</label>
					</td>
					<td><select name="tipoInterno" onchange="" >
						<OPTION VALUE="1">Docente</OPTION>
						<OPTION VALUE="2">Estudiante</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="tipoExterno" id="ltipoExterno">Solicitante Externo</label>
					</td>
					<td><select name="tipoExterno" onchange="externo()">
						<OPTION VALUE="1">Persona Natural</OPTION>
						<OPTION VALUE="2">Persona Jurídica</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="documento" id="ldocumentoIdent">Documento de Identidad / NIT *</label></td>
					<td  align="left"><INPUT NAME="documento" MAXLENGTH="25" TYPE="TEXT" VALUE='<c:out value="${sessionScope.personaDatos.documentoIdNit}"/>'>
					<input type=image src="/siciud/comp/img/Buscar.gif" onclick="buscarPersona()" align="top"> 
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="tipoDoc" id="ltipoDoc">Tipo de Documento*</label>
					</td>
					<td><select name="tipoDoc" onchange="">
						<OPTION VALUE="1">Cédula de Ciudadanía</OPTION> 
						<OPTION VALUE="2">Tarjeta de Identidad</OPTION>
						<OPTION VALUE="3">Cédula de Extranjería</OPTION> 
						<OPTION VALUE="4">Pasaporte</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="titulo" id="ltitulo">Titulo*</label>
					</td>
					<td><select name="titulo" onchange="">
						<OPTION VALUE="1">Sr.</OPTION>
						<OPTION VALUE="2">Sra.</OPTION>
						<OPTION VALUE="3">Señores</OPTION>
						<OPTION VALUE="4">Dr.</OPTION> 
						<OPTION VALUE="5">Ing.</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Nombre / Razón Social"/></td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="nombre" id='nombre'><c:out value='${sessionScope.personaDatos.nombreRazonSocial}'/></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="representante" id="lrepresentante">Representante Legal</label>
					<td><input class="area2" class="area2" style="width: 100%;"
							name="representante" id='representante'><c:out value=''/>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="contacto" id="lcontacto" >Persona de Contacto</label>
					<td><input class="area2" class="area2" style="width: 100%;"
							name="contacto" id='contacto'><c:out value=''/>
					</td>
				</tr>
				<!--  <tr>
					<td colspan="4" align="left"><c:out value="Tipo de Persona"/>
					</td>
					<td><select name="tipoPersona" onchange="">
						<OPTION VALUE="51">Natural</OPTION> 
						<OPTION VALUE="52">Jurídica</OPTION>
					</select></td>
				</tr>-->
				<tr>
					<td colspan="4" align="left"><c:out value="Dirección de correspondencia"/>
					</td>
					<td  align="left"><INPUT NAME="direccion" MAXLENGTH="25" TYPE="TEXT" VALUE="<c:out value="${sessionScope.personaDatos.direccion}"/>">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Teléfono(s)"/>
					</td>
					<td  align="left"><INPUT NAME="celular" MAXLENGTH="25" TYPE="TEXT" VALUE="<c:out value="${sessionScope.personaDatos.telefonoMovil}"/>">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Correo Electrónico"/>
					</td>
					<td  align="left"><INPUT NAME="correo" MAXLENGTH="50" TYPE="TEXT" VALUE="<c:out value="${sessionScope.personaDatos.correoElectronico}"/>">
					</td>
				</tr>
				<!--  <tr>
					<td colspan="4" align="left"><c:out value="Ciudad"/>
					</td>
					<td  align="left"><INPUT NAME="ciudad" MAXLENGTH="25" TYPE="TEXT" VALUE="<c:out value="${sessionScope.personaDatos.ciudad}"/>">
					</td>
				</tr>-->
				<tr>
					<td colspan="4" align="left"><label for="proyInv" id="lproyInv">Proyecto de Investigación</label>
					</td>
					<td  align="left"><INPUT NAME="proyInv" MAXLENGTH="250" TYPE="TEXT" VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="codigo" id="lcodigo">Código</label>
					</td>
					<td  align="left"><INPUT NAME="codigo" MAXLENGTH="25" TYPE="TEXT" VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><label for="facultad" id="lfacultad">Facultad</label>
					</td>
					<td  align="left"><INPUT NAME="facultad" MAXLENGTH="25" TYPE="TEXT" VALUE="">
					</td>
				</tr>
</table>
<br>
<table class="tablas" width="90%">
	<caption>Información de la Solicitud</caption>
				<tr>
					<td colspan="4" align="left"><c:out value="Medio de Recepción"/>
					</td>
					<td>
						<input type="radio" name=recepcion value="1">Télefono
						<input type="radio" name=recepcion value="2">Carta
						<input type="radio" name=recepcion value="3">Correo Electrónico
						<input type="radio" name=recepcion value="4">WEB
						<input type="radio" name=recepcion value="5">Verbal
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Tipo Requerimiento"/>
					</td>
					<td><select name="tipoSolicitud" onchange="">
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
					<td colspan="4" align="left"><c:out value="Asunto"/>
					</td>
					<td  align="left"><INPUT NAME="asunto" MAXLENGTH="25" TYPE="TEXT" VALUE="">
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
					<td colspan="4" align="left"><c:out value="Archivo del caso"/>
					</td>
					<td><input type="file" name="archivoAdjunto" size="30">
					</td>
				</tr>

				<tr>
					<td colspan="4" align="left"><c:out value="¿Recibir notificaciones por correo electrónico?: "/>
					</td>
					<td>
						<input type="radio" name=notificacionCorreo value="true">Si
						<input type="radio" name=notificacionCorreo value="false">No
					</td>
				</tr>	
				<tr>
				<td id="g1"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.pqrForm)"></td>
				</tr>
</table>
</form>
</body>
</html>