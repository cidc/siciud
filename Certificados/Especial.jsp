<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:import url="/general.jsp" />

<script>
	function guardar(){
		alert("entre");
		if(validar()){ 
			document.CertEsp.accion.value=10;
			document.CertEsp.action='<c:url value="/Certificados/llenar.jsp"/>';
			document.CertEsp.submit();
		}
	}
	
	function validar(){
		mensaje=null;
		if(document.CertEsp.nombre.value=="")
			mensaje="\nNombre de la persona";
		if(document.CertEsp.cedula.value=="")
			mensaje+="\nNúmero de Cédula";
		if(document.CertEsp.cuerpo_cer.value=="")
			mensaje+="\nCuerpo del Mensaje";
		if(mensaje!=null){
			mensaje="Por favor diligencie los siguientes campos:"+mensaje;
			alert(mensaje);
			return false;
		}else
			return true;
	}
	
	function numeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}
</script>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Certificado Especial</title>
</head>
<body>Generación de Certificados Especiales
<div align="center">
<fieldset style="width:580px;">
<form name="CertEsp" method="post" >
	<input type="hidden" name="accion" value="0">
		<table width="100%" align="center">
				<th colspan="4">Generar Certificado</th>
				<tr>
					<td>texto a ser citado</td>
				</tr>
				<tr>
					<th>Nombre Completo</th>
					<td><input type="text" name="nombre"></td>
					<th>Número de Cedula</th>
					<td><input type="text" name="cedula"  onkeypress="return numeros(event)"></td>
				</tr>
				<tr>
					<th colspan="4">Cuerpo del Certificado</th>
				</tr>
				<tr>
					<td colspan="4"><textarea rows="10" cols="20" id="cuerpo_cer" name="cuerpo_cer"></textarea></td>
				</tr>
				<tr>
					<td  colspan="4" align="center"><img src='<c:url value="/comp/img/nuevocertificado.jpg"/>' onclick="guardar()" width="60" height="40">
					<p>Generar Certificado</p></td>					
				</tr>
		</table>
	</form>
</fieldset>
</div>
</body>
</html>