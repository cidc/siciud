<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function ajaxFacultad(obj,ac){
		var val=obj.value;
		if(val!=0){
			document.frmAjaxNumero.accion.value=ac;
			document.frmAjaxNumero.dato.value=val;
	 		document.frmAjaxNumero.target="frameOculto";
			document.frmAjaxNumero.submit();
		}
	}
	function ajaxArea(obj,ac){
		var val=obj.value;
		if(val!=0){
			document.frmAjaxNumero.accion.value=ac;
			document.frmAjaxNumero.dato.value=val;
	 		document.frmAjaxNumero.target="frameOculto";
			document.frmAjaxNumero.submit();
		}
	}
	function tipo(tip){
		var x=tip.value;
		for(var i=1;i<4;i++){
			if(x==1){
				document.getElementById("int"+i).style.display='';
				document.getElementById("ext"+i).style.display='none';
			}else{
				document.getElementById("int"+i).style.display='none';
				document.getElementById("ext"+i).style.display='';
			}
		}
	}

	function enviar(ac){
		document.frmComplemento.action='<c:url value="/adminEvaluador/llenar2.jsp"/>';
		document.frmComplemento.accion.value=ac;
		document.frmComplemento.submit();
	}

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="frmComplemento" >
	<input type="hidden" name="accion" value='0'>

	<table class="tablas" align="center">
	<caption>Datos Personales</caption>
		<tr>
			<td colspan="4">
				<table>
					<tr>
						<td class="renglones"><b>Tipo de Evaluador a Insertar</b></td>
						<td>
							<select name="tipoEval" onchange="tipo(this)" style="width:120px">
								<option value="1" >Interno</option>
								<option value="2" >Externo</option>
							</select>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr id="ext1">
			<td class="renglones"><b>Nombres</b></td>
			<td><input type="text" name="nombres" size="30"></td>
			<td class="renglones"><b>Apellidos</b></td>
			<td><input type="text" name="apellidos" size="30"></td>
		</tr>
		<tr id="ext2">
			<td class="renglones"><b>Mail</b></td>
			<td><input type="text" name="mail" size="30"></td>
			<td class="renglones"><b>Celular</b></td>
			<td><input type="text" name="celular" size="30"></td>
		</tr>
		<tr id="ext3">
			<td class="renglones"><b>Teléfono</b></td>
			<td><input type="text" name="telefono" size="30"></td>
			<td class="renglones"><b>Universidad</b></td>
			<td><input type="text" name="universidad" size="30"></td>
		</tr>
		<tr id="int1">
			<td class="renglones"><b>Facultad</b></td>
			<td colspan="3">
				<select name="idFac" onchange="ajaxFacultad(this,2)" style="width:230px">
					<option value="0" >-------</option>
				<c:forEach begin="0" items="${requestScope.listaFacultades}" var="xx" varStatus="st">
					<option value='<c:out value="${xx.codigo}"/>'><c:out value="${xx.nombre}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr id="int2">
			<td class="renglones"><b>Grupo de Investigación</b></td>
			<td colspan="3">
				<select name="idGrupo" onchange="ajaxFacultad(this,3)" style="width:230px">
					<option value="0" >--------</option>
				</select>
			</td>
		</tr>
		<tr id="int3">
			<td class="renglones"><b>Nombre Nuevo Evaluador</b></td>
			<td colspan="3">
				<select name="id" name="id" style="width:230px">
					<option value="0" >--------</option>
				</select>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<table width="100%" class="tablas">
				<caption>Areas de Trabajo</caption>
					<tr>
						<td class="renglones"><b>Super Área</b></td>
						<td class="renglones"><b>Área</b></td>
					</tr>
					<tr>
						<td>
							<select name="superArea" style="width:190px" onchange="ajaxArea(this,1)">
								<option value="0" >----</option>
								<c:forEach begin="0" items="${requestScope.listaSuperAreas}" var="super">
								<option value='<c:out value="${super.idSuperArea}"/>'><c:out value="${super.superArea}"/></option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select name="area" style="width:190px" >
								<option value="0">----</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="renglones"><b>Campos</b></td>
					</tr>
					<tr>
						<td colspan="2">
							<textarea name="campos" rows="1" cols="25"></textarea>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="enviar(8)"></td>
		</tr>
	</table>
	</form>
	<table>
		<tr>
			<td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td>
		</tr>
	</table>
	<form method="post" name="frmAjaxNumero" action="<c:url value="/adminEvaluador/Ajax.x"/>">
		<input type="hidden" name="accion" value='0'>
		<input type="hidden" name="dato" value=''>
	</form>
</body>
<script>
	tipo(document.frmComplemento.tipoEval);
</script>
</html>
