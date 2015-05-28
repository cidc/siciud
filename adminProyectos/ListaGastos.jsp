<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@taglib uri="http://displaytag.sf.net" prefix="display"%>
<jsp:useBean id="globales" class="cidc.general.obj.Globales" scope="page" />
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/tablas.css"/>">
<c:import url="/general.jsp"/>
<script>
	function comando(id){
		document.gastos.accion.value=id;
	}
	function eliminar(id){
		if(confirm("¿Desea eliminar este registro?")){
			document.gastos.accion.value="14";
			document.gastos.idGasto.value=id;
			document.gastos.action='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x"/>';
			document.gastos.submit();
		}
	}
	function modificar(id,fecha,descrip,cod, obser,valor){
		document.gastos.idGasto.value=id;
		document.gastos.descripcionTXT.value=descrip;
		document.gastos.codigoTXT.value=cod;
		document.gastos.observacionesTXT.value=obser;
		document.gastos.fechaTXT.value=fecha;
		document.gastos.valorTXT.value=valor;
		document.gastos.descripcionTXT.style.display='block';
		document.gastos.codigoTXT.style.display='block';
		document.gastos.observacionesTXT.style.display='block';
		document.gastos.valorTXT.style.display='block';
		document.gastos.fechaTXT.style.display='block';
		document.gastos.bGuardar.style.visibility='visible';
	}
	
	function guardar(){
		if(confirm("Desea actualizar este registro")){
			//document.gastos.idGasto.value=id;
			document.gastos.descripcion.value=document.gastos.descripcionTXT.value;
			document.gastos.codigo.value=document.gastos.codigoTXT.value;
			document.gastos.observaciones.value=document.gastos.observacionesTXT.value; 
			document.gastos.valorGasto.value=document.gastos.descripcionTXT.value;
			document.gastos.action='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x"/>';
			document.gastos.accion.value="24";
			document.gastos.submit();
		}
		
	} 
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));"> 

	<form name="gastos" method="post" action='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x"/>'>
	<c:if test="${sessionScope.proyecto.estado!=3}">
		<table>
			<tr>
				<td><input type="image" src='<c:url value="/comp/img/RegistraGasto.gif" />' onclick="comando(12)"></td>				
				<td><input type="image" src='<c:url value="/comp/img/EntregaElementos.gif" />' onclick="comando(10)"></td>
			</tr>
		</table>
	</c:if>
	    <input type="hidden" name="accion" value='8'>
		<input type="hidden" name="idGasto" value=''>
		<input type="hidden" name="descripcion" value=''>
		<input type="hidden" name="observaciones" value=''>
		<input type="hidden" name="codigo" value=''>
		<input type="hidden" name="fecha" value=''>
		<input type="hidden" name="valorGasto" value=''>
		<input type="hidden" name="id" value='<c:out value="${sessionScope.proyecto.id}"/>'>
		<input type="hidden" name="idRub" value='<c:out value="${sessionScope.idRub}"/>'>
		
		<div align="center" class="">
		<c:if test="${!empty requestScope.listaGastosRubro}">
		<fieldset style="width:1000px;">
			<table align="center" class="tablas" width="95%" >	        
	        <caption >Listado de Gastos Rubro <c:out value="${sessionScope.nombreRubro}"/></caption>
	        <tr>
	        	<th style="width:100px;"><b>Fecha</b></th>
	        	<th style="width:100px;"><b>Valor</b></th>
	        	<th style="width:300px;"><b>Descripción</b></th>
	        	<th style="width:100px;"><b>Código</b></th>
	        	<th style="width:300px;"><b>Observación</b></th>
	        	<th style="width:40px;"><b>Ubicación</b></th>
	        	<th style="width:60px;"><b>Opciones</b></th>
	        </tr>
	        <c:forEach items="${requestScope.listaGastosRubro}" var="data" >
	        <tr>
	        	<td style="width:100px;"><c:out value="${data.fecha}"/></td>
	        	<td style="width:100px;"><c:out value="${data.valorGasto}"/></td>
	        	<td style="width:300px;"><c:out value="${data.descripcion}"/></td>
	        	<td style="width:100px;"><c:out value="${data.codigo}"/></td>
	        	<td style="width:300px;"><c:out value="${data.observaciones} - ${data.observacionEntrega}"/></td>
	        	<td><c:if test="${data.ubicacion=='p'}">
							<img src='<c:url value="/comp/img/proy.png"/>' alt="Proyecto Investigación" title="Proyecto Investigación">
						</c:if>
						<c:if test="${data.ubicacion=='b'}">
							<img src='<c:url value="/comp/img/biblio.png"/>' alt="Biblioteca" title="Biblioteca">
						</c:if>
						<c:if test="${data.ubicacion=='g'}">
							<img src='<c:url value="/comp/img/grupo.png"/>' alt="Grupo Investigación" title="Grupo Investigación">
						</c:if>
						<c:if test="${data.ubicacion=='a'}">
							<img src='<c:url value="/comp/img/chart.png"/>' alt="Almacen" title="Almacen">
						</c:if>
						<c:if test="${data.ubicacion=='d'}">
							<img src='<c:url value="/comp/img/persona.png"/>' alt="Director" title="Director">
						</c:if>
				</td>
				<c:if test="${sessionScope.proyecto.estado==2 and data.ubicacion==null}">
								<td><img src='<c:url value="/comp/img/equis1.png"/>' title="Eliminar" onclick='eliminar("<c:out value="${data.idGasto}"/>")'>
								    <img src='<c:url value="/comp/img/Editar.png"/>' title="Modificar" onClick='modificar(<c:out value="${data.idGasto}"/>,"<c:out value="${data.fecha}" />", "<c:out value="${data.descripcion}" />",
								    "<c:out value="${data.codigo}" />","<c:out value="${data.observaciones}" />","<c:out value="${data.valorGasto}" />")' /></td>
				</c:if>	
	        </tr>
	        </c:forEach>
	        <tr>
	        	<td style="width:100px;"><input type="text" id="fechaTXT" style="display:none"  disabled="disabled"/></td>
	        	<td style="width:100px;"><input type="text" id="valorTXT" style="display:none" disabled="disabled" /></td>
	        	<td style="width:300px;"><textarea id="descripcionTXT" rows="2" cols="50" style="display:none"></textarea></td>
	        	<td style="width:100px;"><input type="text" id="codigoTXT" style="display:none"/></td>
	        	<td style="width:300px;"><input type="text" id="observacionesTXT" style="display:none"/></td>
	        </tr>
	        </table>
	        <img id="bGuardar" alt="Guardar" src="<c:url value="/comp/img/Guardar.gif" />" title="Guardar" onClick="guardar()" style="align:center; visibility: hidden" >
		</fieldset>
		</c:if>
		</div>
	</form>
	<c:if test="${empty requestScope.listaGastosRubro}">
	<h4 align="center">No hay gastos registrados para este rubro</h4>
	</c:if>
</body>
<c:if test="${requestScope.bandera!=null}">
<script language="javaScript">
	parent.location.href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=7"/>';
</script>
</c:if>
</html>