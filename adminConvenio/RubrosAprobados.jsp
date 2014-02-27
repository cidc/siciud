<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<script>
var nav4=window.Event ? true : false;
function soloNumeros(eve){
	var key=nav4?eve.which :eve.keyCode;
	return(key<=13 || (key>=48 && key<=57));
}
function guardar(){
	 for(var i=0;i<document.listadoRubros.valorEntidad.length;i++){
		document.listadoRubros.valorEntidad[i].value=eliminaFormatoMoneda(document.listadoRubros.valorEntidad[i].value);
	 }
	 document.listadoRubros.submit();
}
function suma(formulario1){
	 for(var i=0;i<formulario1.idRubro.length;i++){
		document.listadoRubros.valorEntidad[i].value=eliminaFormatoMoneda(document.listadoRubros.valorEntidad[i].value);
	 }
	 document.listadoRubros.submit();
}


</script>
<c:import url="/general.jsp"/>

</head>
<body>

<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=3&idConv=${sessionScope.datoConvenio.idconvenio}"/>'><img border="0" src='<c:url value="/comp/img/convenio/Proyectos.gif"/>'></a></td>
		    <td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/convenio/Documentos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Tiempos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><img border="0" src='<c:url value="/comp/img/convenio/GruposInvClick.gif"/>'></a></td>

		</tr>
	</table>
<br>

<form action='<c:url value="/adminConvenio/AdminConvenio.x"/>' name="listadoRubros">
	<input type="hidden" name="accion" value="16">
	
	
	<c:set var="var2" value="${0}"></c:set>
	<table align="center" class="tablas">
	<caption>Lista de rubros aprobados</caption>
		<c:if test="${!empty requestScope.listaRubros}">
			<tr>
				<th>&nbsp;</th>
				<th><b>Rubro</b></th>
				<th><b>Codigo</b></th>
				
				<c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
				<th><b><c:out value="${lista.entidadid}"/></b></th>
				
				
				  <c:out value="${st.count}"/>
				<c:set var="var2" value="${var2+1}"></c:set>
				</c:forEach>
				<input type="hidden" name="numeroentidad" value="${var2}">
				<c:out value="${var2}" />
				
				<th style="width:400px;" align="center"><b>observacion</b></th>
				
			</tr>
			<c:forEach begin="0" items="${requestScope.listaRubros}" var="lista" varStatus="st">
			<tr>
				<td>
					<input type="checkbox" name="idRubro" value='<c:out value="${lista.codigo}" />'>
				</td>
				<td>
					<c:out value="${lista.nombre}"/>
				</td>
				<td style="width:80px;" align="right">
					<b><input  maxlength="10"  type="text"  name="codigo"></b>
				</td>
				
				<c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
				<td style="width:50px;" align="right">
				    <input type="hidden" name="idconvent" value='<c:out value="${lista.identidadconvenio}"/>'>  
					<input id='ventidad<c:out value="${st.count}" />' maxlength="10" size="10" type="text" onkeypress="return soloNumeros(event)" name="valorEntidad" value="0">
				</td>
				</c:forEach>
				
				<td style="width:400px;" align="right">
					<b><textarea class="texto" name="observacion" style="width: 99%"></textarea></b>
				</td>
				
				</tr>
			</c:forEach>
			
			
		</c:if>
			<tr>
				<td colspan="3" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar()"></td>
			</tr>
	</table>
</form>

</body>
<script>
	chequear(document.listadoRubros,document.frmInsc);
</script>
</html>