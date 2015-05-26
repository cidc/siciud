<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%
	session.getAttribute("planaccion");
%>
<script>
	

	function periodo(anoActual){
			document.getElementById('anoInicio').innerHTML=anoActual;
			document.getElementById('anoFinal').innerHTML=anoActual;
			document.filtro.anoinicio.value=anoActual;
			document.filtro.anofinal.value=anoActual;
	}	
	
	function consultar(){
		var ano = document.filtroPDF.periodo.value;
		document.frmActividades.anoinicio.value=ano;
		document.frmActividades.anofinal.value=ano;
		if(validarPDF()){
			document.filtroPDF.action='<c:url value="/planAccion/llenar.jsp"/>';
			document.filtroPDF.submit();
		}
	}
	
	function validarPDF(){
		if(document.filtroPDF.idGrupo.selectedIndex==0){
			alert ("Grupo/Semillero de investigación");
			return false;
		}
		return true
	}
	
	function buscar(){
		if (validarFiltro()){
			document.filtro.action='<c:url value="/planAccion/llenar.jsp"/>';
			document.filtro.submit();
		}	
	}	

	
	function validarFiltro(){
		var mensaje="";
		if(document.filtro.idGrupo.selectedIndex==0){
			mensaje=mensaje+"\n-) Grupo/Semillero de investigación";
		}
		if(document.filtro.anoinicio.selectedIndex==0){
			mensaje=mensaje+"\n-) Año de Incio";
		}
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios: "+mensaje;
			alert (mensaje);
		}		
		else
			return true;
		return false;	
	}

	function eliminar(id){
		document.frmActividades.idActividad.value=id;
		document.frmActividades.accion.value='3';
		document.frmActividades.action='<c:url value="/planAccion/PlanAccion.x"/>';
		document.frmActividades.submit();		
	}
	
	function actualizar(id){
		document.frmActividades.idActividad.value=id;
		document.frmActividades.accion.value='6';
		document.frmActividades.action='<c:url value="/planAccion/llenar.jsp"/>';
		document.frmActividades.submit();
	}
	
	function guardar(id){
		document.frmActividades.actividad.value=document.getElementById("actividad"+id).value;
		document.frmActividades.descripcion.value=document.getElementById("descripcion"+id).value;
		document.frmActividades.meta.value=document.getElementById("meta"+id).value;
		document.frmActividades.idCriterio.value=id;
		if(validar1()){		
			document.frmActividades.action='<c:url value="/planAccion/llenar.jsp"/>';
			document.frmActividades.submit();
		}				
	}
	
	function generarPDF(){
			document.frmActividades.accion.value='5';
			document.frmActividades.action='<c:url value="/planAccion/PlanAccion.x"/>';
			document.frmActividades.submit();
	}
	
	function cambio(){
		document.frmActividades.accion.value='4';
		document.frmActividades.action='<c:url value="/planAccion/PlanAccion.x"/>';
		document.frmActividades.submit();
	}
	
	function validar1(){
		var mensaje="";
		if(document.frmActividades.actividad.value==""){
			mensaje=mensaje+"\n-) Ingrese una actividad Actividad por favor";
		}
		if(document.frmActividades.descripcion.value==""){
			mensaje=mensaje+"\n-) Ingrese una actividad Descripción";
		}
		if(document.frmActividades.meta.value==""){
			mensaje=mensaje+"\n-) Ingrese una actividad Meta";
		}
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios: "+mensaje;
			alert (mensaje);
		}		
		else
			return true;
		return false;	
	}
	
	function info(){
		alert("Pulse el boton verde para que esta actividad \nsea copiada al plan de accion de este año");
	}
	
</script>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Plan de Acción</title> 
</head>
<body>
<br>
<c:if test="${sessionScope.consultar}">
	<img src='<c:url value="/comp/img/ingresar_plan.png"/>' onclick="cambio()">
</c:if>
<c:if test="${!sessionScope.consultar}">
	<img src='<c:url value="/comp/img/historico.png"/>' onclick="cambio()">
</c:if>
	<c:if test="${!sessionScope.consultar}">
		<form name="filtro" method="post"
			action='<c:url value="/planAccion/PlanAccion.x"/>'>
			<input type="hidden" name="accion" value="1" />

			<table align="center" class="tablas">
				<caption>Filtrar Por Grupo</caption>
				<tr>
					<th><b>Grupo de Investigación</b></th>
					<td colspan="5"><select name="idGrupo" onchange="periodo(${sessionScope.anoActual})">
							<option value="0">----</option>
							<c:forEach begin="0" items="${sessionScope.listaMisGrupos}"
								var="lista" varStatus="st">
								<option value="<c:out value="${lista.idGrupo}"/>" <c:if test="${lista.idGrupo==sessionScope.planaccion.idGrupo}">selected</c:if> >
									<c:out value="${lista.nombreGrupo}" />
								</option>
							</c:forEach>
							<input type="hidden" name="tipo"
							value="<c:out value="${lista.tipoGrupoTxt}"/>">
					</select></td>
				</tr>
				<tr>
					<th><b>Periodo Actual</b></th>
					<td><span id="anoInicio">&nbsp;<c:out value="${sessionScope.anoActual}" /></span></td>
					<input type="hidden" name="anoinicio" value="" />
				</tr>
				<tr>
					<td colspan="6" align="center"><img
						src='<c:url value="/comp/img/Ver.gif"/>' onclick="buscar()">
					</td>
				</tr>
			</table>
			<br>
		</form>
</c:if>

<c:if test="${sessionScope.consultar}">
<form name="filtroPDF" method="post"
		action='<c:url value="/planAccion/PlanAccion.x"/>'>
		<input type="hidden" name="accion" value="1" />

		<table align="center" class="tablas">
			<caption>Filtrar Plan de Acción Por Año</caption>
			<tr>
				<th><b>Grupo/Semillero de Investigación</b>
				</th>
				<td colspan="5">
					<select name="idGrupo" onchange="">
						<option value="0">----</option>
						<c:forEach begin="0" items="${sessionScope.listaMisGrupos}"
							var="lista" varStatus="st">
							<option value="<c:out value="${lista.idGrupo}"/>">
								<c:out value="${lista.nombreGrupo}" />
							</option>
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
				<th><b>Periodo</b></th>
					<td><select name="periodo" onchange="">
							<c:forEach begin="0" items="${sessionScope.arregloAnos}" var="lista" >
								<option value="<c:out value="${lista}"/>">
									<c:out value="${lista}" />
								</option>
							</c:forEach>
						</select>
					</td>	
			</tr>
			<tr>
				<td colspan="6" align="center"><img
					src='<c:url value="/comp/img/Ver.gif"/>' onclick="consultar()">
				</td>
			</tr>
		</table>
		
	</form>
</c:if>
<c:if test="${sessionScope.listaCriterios!=null}">
	
<table align="center">
				<tr>
					<td align="center"> PLAN DE ACCION <c:out
							value="${sessionScope.nombreGrupo}" /></td>
				</tr>
				<tr>
					<td align="center"> PERIODO <c:out
							value="${sessionScope.anoActual}" /></td>
				</tr>
				<c:if test="${!sessionScope.corte}">
				<tr>
					<td aling="center">
						<c:out value="La fecha límite para ingresar el plan de acción ha expirado"/>
					</td>
				</tr>
				</c:if>
			</table>
		<br>
		<c:if test="${sessionScope.nombrePdf!=null}">
		<a target="_blank" href="/siciud/Documentos/PlanAccion/<c:out value="${sessionScope.nombrePdf}"/>">
			<img src ='<c:url value="/comp/img/verPdf.png"/>'></a>
		</c:if>
		<c:forEach begin="0" items="${sessionScope.listaCriterios}"
			var="criterio" varStatus="st">

			<table align="center" class="tablas" width="90%">
				<caption>
					<c:out value="${st.count}" />
					.
					<c:out value="${criterio.descripcion}" />
				</caption>

				<tr>
					<c:if test="${criterio.idCriterio==2}">
						<th align="center">Proyectos</th>
					</c:if>
					<c:if test="${criterio.idCriterio!=2}">
						<th align="center">Actividad</th>
					</c:if>
					<th width="140px" class="renglones" align="center">Descripción</th>
					<th align="center" width="100px">Meta</th>
					<th align="center" width="41px">&nbsp;</th>
				</tr>
			<br>
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
							<c:if test="${!sessionScope.consultar}">
							<td align="center" width="15px"><img
								src='<c:url value="/comp/img/no.png"/>'
								onclick="eliminar(<c:out value="${actividades.idActividad}"/>)">
							</td></c:if>
							<c:if test="${sessionScope.consultar&&sessionScope.habilitar&&sessionScope.corte}">
								<td align="center" width="15px">
								<img src='<c:url value="/comp/img/agregar.png"/>' onclick="actualizar(<c:out value="${actividades.idActividad}"/>)">
								<img src='<c:url value="/comp/img/lupa3.png"/>' onclick="info()">
							</td>
							</c:if>
						</tr>
					</c:if>
				</c:forEach>
				<c:if test="${!sessionScope.consultar && sessionScope.corte}">
				<tr>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="actividad" id='actividad<c:out value="${st.count}"/>'></textarea>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="descripcion" id='descripcion<c:out value="${st.count}"/>'></textarea>
					</td>
					<td><textarea class="area2" class="area2" style="width: 100%;"
							name="meta" id='meta<c:out value="${st.count}"/>'></textarea></td>
				</tr>
				<tr>
					<td colspan="4" align="center"><img
						src='<c:url value="/comp/img/Guardar.gif"/>'
						onclick='guardar(<c:out value="${st.count}"/>)'></td>
				</tr>
				</c:if>
			</table>
		</c:forEach>
</c:if>
		<c:if test="${sessionScope.consultar&&sessionScope.listaActividades!=null}">
			<br>
				<table align="center">
				<tr>
					<td colspan="4" align="center"><img
						src='<c:url value="/comp/img/generarPDF.png"/>'
						onclick='generarPDF()'></td>
				</tr>
				</table>
		</c:if>
	<form name="frmActividades" action="">
		<input type="hidden" name="accion" value="2" />
		 <input type="hidden" name="idActividad" value="0" /> 
		 <input type="hidden"name="actividad" value="" /> 
		 <input type="hidden" name="descripcion" value="" /> 
		 <input type="hidden" name="idPlan"	value='<c:out value="${sessionScope.planaccion.idPlan}"/>' /> 
		<input type="hidden" name="idGrupo"	value='<c:out value="${sessionScope.planaccion.idGrupo}"/>' /> 
		<input type="hidden" name="anoinicio" value='<c:out value="${sessionScope.anoActual}"/>' /> 
		<input type="hidden" name="idCriterio" value='' /> 
		<input type="hidden" name="anofinal" value='<c:out value="${sessionScope.anoActual}"/>' />
		<input type="hidden" name="nombreGrupo"	value='<c:out value="${sessionScope.planaccion.nombregrupo}"/>' /> 
		<input type="hidden" name="meta" value="" />
		
	</form>
</body>
</html>