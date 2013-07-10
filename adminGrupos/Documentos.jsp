<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<c:import url="/general.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	function buscarGrupo(){
	      	 if (document.buscar.tipo[0].checked ||document.buscar.tipo[1].checked){
	      		document.buscar.action='<c:url value="/adminGrupos/llenar.jsp"/>';
				document.buscar.submit();
	   	}else
			alert("Por favor seleccione Grupo o Semillero");
	}
	function guardar(frm){
		if(ValidarFormulario(frm)){
			frm.submit();
		}
	}
	function grupoSeleccionado(){
		//alert(document.buscar.grupo.value);
		document.getElementById("capaDocumentos").style.display="block";
		document.formCIDC.idGrupo.value=document.buscar.grupo.value;
		document.frmFac.idGrupo.value=document.buscar.grupo.value;
	}
	function ValidarFormulario(forma){
		if(forma.fichero.value==""){
			alert("Debe seleccionar un Archivo para cargar");
			return false;
		}else{
			archi=forma.fichero.value;
			var ext=archi.substr(archi.lastIndexOf('.'),archi.length);
			if(!(ext==".pdf")){
				alert("El archivo debe ser en formato PDF");
				return false;
			}
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
	<br>
	<form name="buscar" action="">
	<input type="hidden" name="accion" value="22">
	<table align="center" class="tablas">
	<caption>Filtro de consulta</caption>
		<tr>
			<td class="renglones"><b>Facultad:</b></td>
			<td>
				<select name="facultad">
					<option value="0">-------------</option>
					<option value="1" <c:if test="${requestScope.facultad==1}">selected</c:if>>Tecnológica</option>
					<option value="2" <c:if test="${requestScope.facultad==2}">selected</c:if>>Ingeniería</option>
					<option value="3" <c:if test="${requestScope.facultad==3}">selected</c:if>>Medio Ambiente</option>
					<option value="4" <c:if test="${requestScope.facultad==4}">selected</c:if>>Ciencias y Educación</option>
					<option value="5" <c:if test="${requestScope.facultad==5}">selected</c:if>> Artes (Asab)</option>
				</select>
			</td>
			<td><b>Grupo</b><input type="radio" name="tipo" value=1 <c:if test="${requestScope.tipo==1}">checked</c:if>></td>
			<td><b>Semillero</b><input type="radio" name="tipo" value=2 <c:if test="${requestScope.tipo==2}">checked</c:if>></td>
			<td><img align="left" src="<c:url value="/comp/img/Buscar.gif"/>" onclick="buscarGrupo()"></td>
		</tr>
		<tr id="comboGrupo" style="${requestScope.grupo}">
			<td><b>Seleccione un grupo o semillero</b></td>
			<td>
				<select name="grupo" onchange="grupoSeleccionado()">
				<option value="0">-------------</option>
				<c:forEach begin="0" items="${requestScope.listaGrupos}" var="lista" varStatus="st">
					<option value="<c:out value="${lista.codigo}"/>"><c:out value="${lista.nombre}"/></option>
				</c:forEach>
				</select>
			</td>
		</tr>
	</table>
	</form>
	<br>
	<div id="capaDocumentos" style="display:none">
		<table align="center">
		<caption>Cargar Actas de Creación</caption>
				<tr>
					<td>
					<br>
						<form action='<c:url value="/adminGrupos/archivoActas.x"/>' name="formCIDC" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="1">
						<input type="hidden" name="idGrupo" value="">
							<table width="100%">
								<tr>
									<td colspan="2" class="renglones"><b>Acta de Cómite</td>
								</tr>
								<tr>
									<td id="f1"><input size="60%" type="file" name="fichero"></td>
									<td id="g1" width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.formCIDC)"></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				<tr>
					<td>
						<form action='<c:url value="/adminGrupos/archivoActas.x"/>' name="frmFac" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="2">
						<input type="hidden" name="idGrupo" value="">
							<table width="100%">
								<tr>
									<td colspan="2" class="renglones"><b>Acta de Facultad</b></td>
								</tr>
								<tr>
									<td id="f2"><input size="60%" type="file" name="fichero"></td>
									<td id="g2" width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.frmFac)"></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
		</table>
	</div>
</body>
</html>