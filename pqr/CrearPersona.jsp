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
<title>Crear Persona</title> 
</head>
<body>
<form name="pqrForm" method="post"action='<c:url value="/pqr/llenar.jsp.x"/>'>
<input type="hidden" name="accion" value="2">
<table align="left" class="tablas" width="90%">
	<caption>Información del Solicitante</caption>
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
		<tr>
				<td id="g1"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.pqrForm)"></td>
				</tr>
</table>
</form>

</body>
</html>