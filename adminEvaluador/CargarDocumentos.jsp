<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp"/>
</head>
<script>

	function guardar(caja,formulario){
		if(ValidarFormulario(caja)){
			formulario.submit();
		}
	}

	function ValidarFormulario(forma){
		if(forma.value==""){
			alert("Debe seleccionar un Archivo para cargar");
			return false;
		}
		return true;
	}
</script>
<body>

	<fieldset style="width:450px;">
    	<legend class="texto1"><b>Documentos Evaluador</b></legend>
		<br>
		<form method="POST" enctype="multipart/form-data" name="frmCedula" action='<c:url value="/adminEvaluador/DocumEvaluador.x"/>' method="post">
			<table align="center" class="tablas" width="400px">
			<caption>Fotocopia Cédula</caption>
				<tr>
					<td>
						<input class="caja0" type="file" name="cedula" size="50">
					</td>
					<td>
						<img src="<c:url value="/comp/img/Cargar.gif"/>" onclick="guardar(document.frmCedula.cedula,document.frmCedula)">
					</td>
				</tr>
			</table>
		</form>

		<form method="POST" enctype="multipart/form-data" name="frmRut" action='<c:url value="/adminEvaluador/DocumEvaluador.x"/>' method="post">
		<input type="hidden" name="id" value='<c:out value="${requestScope.datos.codigo}" />'>
		<input type="hidden" name="doc" value='2'>
		<input type="hidden" name="estado" value='<c:out value="${requestScope.datos.estadoRut}" />'>
		<table align="center" class="tablas" width="400px">
		<caption>RUT</caption>
			<tr>
				<td>
					<input class="caja0" type="file" name="rut" size="50">
				</td>
				<td>
					<img src="<c:url value="/comp/img/Cargar.gif"/>" onclick="guardar(document.frmRut.rut,document.frmRut)">
				</td>
			</tr>
		</table>
		</form>
	</fieldset>
</body>
</html>
