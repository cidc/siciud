<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:import url="/general.jsp" />
<script>	
	function buscar(){
		if(document.nuevo.cod_verificacion.value!="" && document.nuevo.cedula.value!=""){
			document.nuevo.accion.value='4';
			document.nuevo.action='<c:url value="/certificaciones/AdminCertificados.x?accion=6"/>';
			document.nuevo.submit();
		}else
			alert("Por favor Verifique:\n-)Código de Verificación\n-)Número de Cédula");
		
	}
	function ver(acc, id, url){
		document.nuevo.accion.value=acc;
		document.nuevo.id_certificaciones.value=id;
		document.nuevo.url.value=url;
		
	}
</script>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Busqueda de Certificados</title>
</head>
<body>
<div align="center">
<fieldset style="width:580px;">
<form name="nuevo" method="post" action='<c:url value="/certificados/llenar.jsp"/>'>
	<input type="hidden" name="accion" value="0">	
	<input type="hidden" name="id_certificaciones" value="0">
	<input type="hidden" name="url" value="0">
	<input type="hidden" name="idPersona" value='<c:out value="${sessionScope.persona.idPersona}" />'>	
		<table width="100%" align="center">
				<th colspan="8">Parametros de Búsqueda</th>
				<tr>
					<td class="renglones" width="200px" colspan="2"><b>Número de Cédula</b></td>
					<td class="renglones" width="200px" colspan="2"><b><input type="text" name="cedula" style="width: 100%"></b></td>
					<td class="renglones" width="200px" colspan="2"><b>Código de Verificación</b></td>
					<td class="renglones" width="200px" colspan="2"><b><input type="text" name="cod_verificacion" style="width: 100%"></b></td>
				</tr>
				<tr>
					<td class="renglones" width="200px" colspan="4"><b>Tipo de Certificado</b></td>
					<td class="renglones" width="200px" colspan="4">
						<select name="tipos">
							<option value='1'>Pertenencia a Grupo/Semillero</option>
							<option value='3'>Certificado Especial</option>
							<option value='2'>Paz y Salvo</option> 
						</select>
					</td>
				</tr>
				<tr>
					<td  colspan="8" align="center"><img src='<c:url value="/comp/img/Buscar.gif"/>' onclick="buscar()">									
				</tr>				
		</table>
	</form>
</fieldset>
</div>
<div>
<c:if test="${sessionScope.listacertificados!=null}">
<fieldset>
	<form name="lista">
	<input type="hidden" name="accion" value="3">
	<input type="hidden" name="id">
	<input type="hidden" name="estado">
	<table align="center" class="tablas" width="100%">
		<caption>Lista de Certificados Generados</caption>
		<tr>
			<td class="renglones" align="center"><b>#</b></td>
			<td class="renglones" align="center" width="20%"><b>Tipo Certificado</b></td>
			<td class="renglones" align="center" width="20%"><b>Código de Verificación</b></td>
			<td width="20%" class="renglones" align="center"><b>Fecha y Hora</b></td>
			<td class="renglones" align="center" width="55%"><b>Grupo/Semillero</b></td>
			<td class="renglones" align="center" width="5%"><b>Ver</b></td>
		</tr>
		<c:forEach items="${sessionScope.listacertificados}" begin="0" var="lista" varStatus="st">
			<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				<td><c:out value="${st.count}" /></td>
				<td><c:out value="${lista.tipo}" /></td>
				<td><c:out value="${lista.cod_verificacion}" /></td>
				<td><c:out value="${lista.fecha_cert}" /></td>
				<td><c:out value="${lista.nombreGrupo}" /></td>
				<td valign="middle">
				<a href='<c:url value="/Documentos/Certificados/${lista.url}"/>'><img align="middle" border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
				</td>
			</tr>
		</c:forEach>
	</table>
	</form>
</fieldset>
</c:if>
</div>

</body>
</html>