<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function ajaxGrupos(obj){
	//alert("tipo="+document.filtro.tipo.value);
		var val=obj.value;
		if(val!=''){
			document.frmAjax.dato[0].value=val;
			document.frmAjax.para.value='2';
	 		document.frmAjax.target="frameOculto";
			document.frmAjax.submit();
		}
	}
	
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="filtro" method="post" action='<c:url value="/adminProyectos/llenar.jsp"/>' target="frameLista">
		<input type="hidden" name="accion" value="1"/>
	<br>
	<table class="tablas" align="center" width="80%">
	<CAPTION>Filtro de consulta</CAPTION>
		<tr>
			<th><b>Facultad:</b></th>
			<th colspan="4"><b>Proyecto Curricular:</b></th>
		</tr>
		<tr>
			<td>
				<select name="facultad" onchange="ajaxGrupos(this)" >
					<option value="0">------------</option>
					<option value="1">Tecnológica</option>
					<option value="2">Ingeniería</option>
					<option value="3">Medio Ambiente</option>
					<option value="4">Educación</option>
					<option value="5">Asab</option>
				</select>
			</td>
			<td colspan="4">
				<select name="proyCur" style="width:100%">
					<option value=''>----------------------------------------</option>
				<c:if test="${!empty sessionScope.ajaxProyCur}">
					<c:forEach begin="0" items="${sessionScope.ajaxProyCur}" var="proy" varStatus="st">
						<option value='<c:out value="${proy.codigo}"/>'><c:out value="${proy.nombre}"/></option>
					</c:forEach>
				</c:if>
				</select>
			</td>
		</tr>
		<tr>
			<th><b>Codigo:</b></th>
			<th><b>Estado:</b></th>
			<th><b>Tipo Grupo:</b></th>
			<th><b>Tipo Convocatoria</b></th>
			<th><b>Año</b></th>
		</tr>
		<tr>
			<td width="25%"><input type="text" name="codigo" 	style="width: 100%"></td>
			<td width="25%">
				<select name="estado">
					   <option value="" >------------</option>
                       <option value="1" >Aprobado</option>
                       <option value="2" >Vigente</option>
                       <option value="3" >Finalizado</option>
                       <option value="4" >Cancelado</option>
                       <option value="5" >En Prueba</option>
                       <option value="6" >Aplazado</option>
				</select>
			</td>
			<td width="25%" align="center">
				<table>
					<tr>
						<select name="tipoGrupo">
						   <option value="" >----------</option>
	                       <option value="1">Grupo</option>
	                       <option value="2">Semillero</option>
	                    </select>
					</tr>
				</table>
			</td>
			<td>
				<select width="25%" name="tipoConvocatoria">
					<option value="1">Proyectos de Investigación</option>
					<option value="3">Apoyo a Grupos y Semilleros</option>
				</select>
			</td>			
			<td>
			<select width="25%" name="ano">
			<option value="">-----</option>
			<c:forEach begin="0" items="${requestScope.ano}" var="ano" varStatus="st">
				<option value="${ano}"><c:out value="${ano}"/></option>
			</c:forEach>
			</select>
				<!-- <select name="tipoProyecto" onchange="ajaxTipoProy(this)">
					<option value="" >----------</option>
					<option value="1">Convocatoria</option>
					<option value="2">Convenio</option>
				</select> -->
			</td>
		</tr>
		<tr>
			<td colspan="5" class="renglones"><b><span id="para">Grupo Investigación</span></b></td>
		</tr>
		<tr>
			<td colspan="5">
				<select name="grupo" onchange="ajaxInvestigadores(this)">
					<option value=''>----------------------------------------</option>
				<c:if test="${!empty sessionScope.ajaxGrupos}">
					<c:forEach begin="0" items="${sessionScope.ajaxGrupos}" var="grupos" varStatus="st">
						<option value='<c:out value="${grupos.codigo}"/>'><c:out value="${grupos.nombre}"/></option>
					</c:forEach>
				</c:if>
				</select>
			</td>
		</tr>
		<tr> 
			<th colspan="5">Nombre del Proyecto</th>
		</tr>
		<tr>
			<td colspan="5"><input type="text" name="nombreProyecto"></td>
		</tr>
		<tr> 
			<th colspan="5">Palabras Claves</th>
		</tr>
		<tr>
			<td colspan="5"><input type="text" name="palabrasClaves"></td>
		</tr>
				
		<tr>
			<td colspan="5" align="center"><input type="image" src='<c:url value="/comp/img/Buscar.gif"/>'></td>
		</tr>
	</table>
	</form>
	<table align="center" width="100%">
		<tr>
			<td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td>
		</tr>
		<tr>
			<td width="100%"><iframe width="100%" height="700px" scrolling="auto" name="frameLista" id="frameLista" frameborder="0"></iframe></td>
		</tr>
	</table>
	<form method="post" name="frmAjax" action="<c:url value="/GestionProyectos/Ajax.x"/>">
		<input type="hidden" name="dato" value=''>
		<input type="hidden" name="dato" value=''>
		<input type="hidden" name="para" value=''>
	</form>
</body>
</html>