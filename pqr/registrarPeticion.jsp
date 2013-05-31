<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<script>
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script>
function guardar(pqrForm){
	//alert("hola");
	document.pqrForm.action='<c:url value="/pqr/llenar.jsp"/>';
	document.pqrForm.submit();
	
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atención al Ciudadano</title> 
</head>
<body>
<form name="pqrForm" method="post"action='<c:url value="/pqr/llenar.jsp.x"/>'>
<input type="hidden" name="accion" value="1">
<table align="left" class="tablas" width="90%">
	<caption>Información del Solicitante</caption>
				<tr>
					<td colspan="4" align="left"><c:out value="Tipo de Solicitante"/>
					</td>
					<td><select name="tipoSol" onchange="">
						<OPTION VALUE="1">Externo</OPTION>
						<OPTION VALUE="2">Interno</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Persona"/>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="persona" id='persona'></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Titulo"/>
					</td>
					<td><select name="titulo" onchange="">
						<OPTION VALUE="sr">Sr.</OPTION>
						<OPTION VALUE="sra">Sra.</OPTION>
						<OPTION VALUE="senores">Señores</OPTION>
						<OPTION VALUE="dr">Dr.</OPTION> 
						<OPTION VALUE="ing">Ing.</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Nombre / Razón Social"/>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="nombre" id='nombre'></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Tipo de Persona"/>
					</td>
					<td><select name="tipoPersona" onchange="">
						<OPTION VALUE="natural">Natural</OPTION> 
						<OPTION VALUE="juridica">Jurídica</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Tipo de Documento"/>
					</td>
					<td><select name="tipoDoc" onchange="">
						<OPTION VALUE="CC">Cédula de Ciudadanía</OPTION> 
						<OPTION VALUE="TI">Tarjeta de Identidad</OPTION>
						<OPTION VALUE="CE">Cédula de Extranjería</OPTION> 
						<OPTION VALUE="pasaporte">Pasaporte</OPTION>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Documento de Identidad / NIT"/>
					</td>
					<td  align="left"><INPUT NAME="documento" MAXLENGTH="25" TYPE="TEXT" VALUE="">
					<INPUT NAME="boton" TYPE="reset" VALUE="Limpiar"> 
					<INPUT NAME="boton" TYPE="SUBMIT" VALUE="Buscar"> 
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Correo Electrónico"/>
					</td>
					<td  align="left"><INPUT NAME="correo" MAXLENGTH="25" TYPE="TEXT" VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Dirección"/>
					</td>
					<td  align="left"><INPUT NAME="direccion" MAXLENGTH="25" TYPE="TEXT" VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Teléfono Móvil"/>
					</td>
					<td  align="left"><INPUT NAME="celular" MAXLENGTH="25" TYPE="TEXT" VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Ciudad"/>
					</td>
					<td  align="left"><INPUT NAME="ciudad" MAXLENGTH="25" TYPE="TEXT" VALUE="">
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
						<input type="radio" name=myradio value="1">Télefono
						<input type="radio" name=myradio value="2">Carta
						<input type="radio" name=myradio value="3">Correo Electrónico
						<input type="radio" name=myradio value="4">WEB
						<input type="radio" name=myradio value="5">Verbal
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
					<td colspan="4" align="left"><c:out value="Escalado de otra De pendencia: "/>
					</td>
					<td>
						<input type="radio" name=myradio value="1">Si
						<input type="radio" name=myradio value="2">No
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Archivo del caso"/>
					</td>
					<td><input type="file" name="datasize" size="30">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Enviar Respuesta Por: "/>
					</td>
					<td>
						<input type="radio" name=myradio value="1">Télefono
						<input type="radio" name=myradio value="2">Carta
						<input type="radio" name=myradio value="3">Correo Electrónico
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Solución Inmediata: "/>
					</td>
					<td>
						<input type="radio" name=myradio value="1">Si
						<input type="radio" name=myradio value="2">No
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="¿Recibir notificaciones por correo electrónico?: "/>
					</td>
					<td>
						<input type="radio" name=myradio value="1">Si
						<input type="radio" name=myradio value="2">No
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Si no, Se notificará al final del caso"/>
					</td>
					<td>
						<input type="radio" name=myradio value="1">Si
						<input type="radio" name=myradio value="2">No
					</td>
				</tr>	
				<tr>
				<td id="g1"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.pqrForm)"></td>
				</tr>
</table>
</form>
</body>
</html>