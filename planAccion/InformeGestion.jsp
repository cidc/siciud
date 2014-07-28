<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function buscar(){
	if (validarFiltro()){
		if(${sessionScope.ConsultaInforme}){
			document.filtro.periodo.value=document.filtro.comboPeriodo.value;
		}
		document.filtro.action='<c:url value="/planAccion/llenar.jsp"/>';
		document.filtro.submit();
	}	
}	

function validarFiltro(){
	var mensaje="";
	if(document.filtro.idGrupo.selectedIndex==0){
		mensaje=mensaje+"\n-) Grupo/Semillero de investigación";
	}
	if(document.filtro.periodo.selectedIndex==0){
		mensaje=mensaje+"\n-) Año de Iincio";
	}
	if(mensaje!=""){
		mensaje="Los siguientes campos son obligatorios: "+mensaje;
		alert (mensaje);
	}		
	else
		return true;
	return false;	
}
function guardar(id){
			document.frmActividades.action='<c:url value="/planAccion/llenar.jsp"/>';
			document.frmActividades.submit();
		
}

function eliminar(id){
	document.frmActividades.idActividad.value=id;
	document.frmActividades.accion.value=9;
	document.frmActividades.action='<c:url value="/planAccion/PlanAccion.x"/>';
	document.frmActividades.submit();		
}
function cambio(){
	document.filtro.accion.value='10';
	document.filtro.action='<c:url value="/planAccion/PlanAccion.x"/>';
	document.filtro.submit();
}
//Función que permite solo Números
function soloNumeros(e){
	var key = window.Event ? e.which : e.keyCode
	return (key >= 48 && key <= 57)
}

function validarCombos(){
	var mensaje="";
	formulario = document.frmActividades;
	for(var i=0; i<formulario.elements.length; i++) {
	  var elemento = formulario.elements[i];
	  if(elemento.type == "text") {
	    /* if(elemento.value>100) {
	    	mensaje=mensaje+"\n-) El valor no puede ser superior a 100";
	    }if(elemento.value==""){
	    	mensaje=mensaje+"\n-) Ingrese un porcentaje por favor";
	    }
	    if(mensaje!=null){
	    	return false
	    } */
	  }
	}
	alert(mensaje);
	return true;
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<c:if test="${sessionScope.ConsultaInforme}">
	<img src='<c:url value="/comp/img/Atras.gif"/>' onclick="cambio()">
	</c:if>
	<c:if test="${!sessionScope.ConsultaInforme}">
	<img src='<c:url value="/comp/img/historico.png"/>' onclick="cambio()">
	</c:if>
	<form name="filtro" method="post"
		action='<c:url value="/planAccion/PlanAccion.x"/>'>
		<input type="hidden" name="accion" value="1" />

		<table align="center" class="tablas">
			<caption>Filtrar Por Grupo</caption>
			<tr>
				<th><b>Grupo de Investigación</b></th>
				<td colspan="5"><select name="idGrupo">
						<option value="0">----</option>
						<c:forEach begin="0" items="${sessionScope.listaMisGrupos}"
							var="lista" varStatus="st">
							<option value="<c:out value="${lista.idGrupo}"/>">
								<c:out value="${lista.nombreGrupo}" />
							</option>
						</c:forEach>
						<input type="hidden" name="tipo" value="<c:out value="${lista.tipoGrupoTxt}"/>">
				</select></td>
			</tr>
			<tr>
				<th><b>Período</b></th><td>
				<c:if test="${!sessionScope.ConsultaInforme}">
				<span id="anoInicio">&nbsp;<c:out value="${sessionScope.anoActual}" /></span>
				</c:if>
				<input type="hidden" name="periodo" value="<c:out value="${sessionScope.anoActual}" />" />
<!-- 				<input type="hidden" name="anoinicio" value="" /> -->
				<c:if test="${sessionScope.ConsultaInforme}">
				<select name="comboPeriodo" onchange="">
							<c:forEach begin="0" items="${sessionScope.arregloAnos}" var="lista" >
								<option value="<c:out value="${lista}"/>">
									<c:out value="${lista}" />
								</option>
							</c:forEach>
				</select>
				</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="6" align="center"><img
					src='<c:url value="/comp/img/Ver.gif"/>' onclick="buscar()">
				</td>
			</tr>
		</table>
		<br>
	</form>
<form name="frmActividades" action="">
	<c:if test="${sessionScope.listaCriterios!=null}">
		<table align="center">
			<tr>
				<td align="center">Informe de Gestión <c:out
						value="${sessionScope.nombreGrupo}" /></td>
			</tr>
			<tr>
				<td align="center">PERIODO <c:out
						value="${sessionScope.anoActual}" /></td>
			</tr>
		</table>
		<br>

		<c:forEach begin="0" items="${sessionScope.listaCriterios}" var="criterio" varStatus="st">
			<table align="center" class="tablas" width="90%">
				<caption><c:out value="${st.count}" />. <c:out value="${criterio.descripcion}" /></caption>
				<tr>
					<c:if test="${criterio.idCriterio==2}">
						<th align="center">Proyectos</th>
					</c:if>
					<c:if test="${criterio.idCriterio!=2}">
						<th align="center">Actividad</th>
					</c:if>
					<th width="140px" class="renglones" align="center">Descripción</th>
					<th align="center" width="100px">Meta</th>
					<th align="center" width="50px">Porcentaje</th>
<!-- 					<th align="center" width="41px">&nbsp;</th> -->
				</tr>
				<c:forEach begin="0" items="${sessionScope.listaActividades}"
					var="actividades" varStatus="st1">
					<c:if test="${(criterio.idCriterio==actividades.idCriterio)}">
						<tr>
							<%-- <th align="center"><b><c:out value="${st1.count}" /></b></th> --%>
							<td><p class="texto1j">
									<c:out value="${actividades.actividad}" />
								</p></td>
							<td><p class="texto1j">
									<c:out value="${actividades.descripcion}" />
								</p></td>
							<td><p class="texto1j">
									<c:out value="${actividades.meta}" />
								</p></td>
							<c:if test="${!sessionScope.ConsultaInforme}">
							<td><input type="text" class="area2"  onKeyPress="return soloNumeros(event)"
								style="width:50px;" id="porcentajes" name="porcentajes_" value="<c:out value="${actividades.porcentaje}" />">
							<input type="hidden" name="idActividades" value="<c:out value="${actividades.idActividad}"/>" />
 							<input type="hidden" name="idActividad" value="<c:out value="${actividades.idActividad}"/>" /></td>
							<%-- <c:if test="${!sessionScope.consultar}">
								<td align="center" width="15px"><img src='<c:url value="/comp/img/no.png"/>'
									onclick="eliminar(<c:out value="${actividades.idActividad}"/>)">
								</td>
							 </c:if> --%>
							</c:if>
							<c:if test="${sessionScope.ConsultaInforme}">
								<td><c:out value="${actividades.porcentaje}" /></td>
							</c:if>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</c:forEach>
		<table align="center"  width="90%">
			<c:if test="${!sessionScope.ConsultaInforme}">
					<tr>
						<td colspan="4" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>'
							onclick='guardar(<c:out value="${st.count}"/>)'></td>
					</tr>
				</c:if>
		</table>
	</c:if>

		<input type="hidden" name="accion" value="8" />
		<input type="hidden" name="porcentaje" value="" />
		<input type="hidden" name="idCriterio" value="" />
	</form>
</body>
</html>