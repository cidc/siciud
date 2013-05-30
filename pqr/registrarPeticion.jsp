<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<script>
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Atención al Ciudadano</title> 
</head>
<body>
<form name="pqrForm">
<table align="left" class="tablas" width="90%">
	<caption>Información del Solicitante</caption>
				<tr>
					<td colspan="4" align="left"><c:out value="Número de Documento"/>
					</td>
					<td  align="left"><INPUT NAME="nombre" MAXLENGTH="25" TYPE="TEXT" VALUE="">
					<INPUT NAME="boton" TYPE="reset" VALUE="Limpiar"> 
					<INPUT NAME="boton" TYPE="SUBMIT" VALUE="Buscar"> 
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Tipo de Documento"/>
					</td>
					<td><select name="tipoDoc" onchange="">
						<option value="0">----</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Nombres"/>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="nombres" id='nombres'></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Primer Apellido"/>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="primerApellido" id='primerApellido'></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Correo Electrónico"/>
					</td>
					<td  align="left"><INPUT NAME="correo" MAXLENGTH="25" TYPE="TEXT" VALUE="">
					</td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Dependencia"/>
					</td>
					<td><select name="dependencia" onchange="">
						<option value="0">----</option>
					</select></td>
				</tr>
				<tr>
					<td colspan="4" align="left"><c:out value="Tipo Solicitante"/>
					</td>
					<td><select name="tipoSolicitante" onchange="">
						<option value="0">----</option>
					</select></td>
				</tr>
</table>
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
					<td colspan="4" align="left"><c:out value="Tipo Solicitud"/>
					</td>
					<td><select name="tipoSolicitud" onchange="">
						<option value="0">----</option>
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
					<td colspan="4" align="left"><c:out value="Adjunto"/>
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
					<td colspan="4" align="left"><c:out value="Se Notificarán todos los estados del caso"/>
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
				</tr>	
</table>
</form>
</body>
</html>