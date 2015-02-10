<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:useBean id="globales" class="cidc.general.obj.Globales" scope="page" />
<html>
<head>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp"/>
<script>
	function comando(id){
		document.gastos.accion.value=id;
	}

	function cambioUbicacion(combo,id){
		if(document.getElementsByName('grupo_Acargo').length<2){
			if(combo.selectedIndex==1){
				document.elementos.grupo_Acargo.disabled=false;
			}
			else{
				if(combo.selectedIndex==2)
					document.elementos.observacionEntrega.value="El elemento fue entregado a la biblioteca de la Universidad Distrital";			
				if(combo.selectedIndex==3)
					document.elementos.observacionEntrega.value="El elemento no se envía a ninguna dependencia o grupo";
				if(combo.selectedIndex==4)
					document.elementos.observacionEntrega.value="El elemento fue entregado a el departamento de almacén de la Universidad Distrital";
				if(combo.selectedIndex==5)
					document.elementos.observacionEntrega.value="El elemento fue entregado al docente Director del Grupo/Semillero";
				document.elementos.grupo_Acargo.disabled=true;
				document.elementos.grupoAcargo.value=0;
			}
			
		}else{
			if(combo.selectedIndex==1){
				document.elementos.grupo_Acargo[(id-1)].disabled=false;
				
			}
			else{
				document.elementos.grupoAcargo[(id-1)].value=0;
				if(combo.selectedIndex==2)
					document.elementos.observacionEntrega[(id-1)].value="El elemento fue entregado a la biblioteca de la Universidad Distrital";								
				if(combo.selectedIndex==3)
					document.elementos.observacionEntrega[(id-1)].value="El elemento no se envía a ninguna dependencia o grupo";
				if(combo.selectedIndex==5)
					document.elementos.observacionEntrega.value="El elemento fue entregado al docente Director del Grupo/Semillero";
				document.elementos.grupo_Acargo[(id-1)].disabled=true;
				document.elementos.grupoAcargo[(id-1)].value=0;
			}
		}
	}

	function asignaGrupo(combo,id){
		//alert("entra "+id);
		if(document.getElementsByName('grupo_Acargo').length<2){
		document.elementos.grupoAcargo.value=combo.value;
		document.elementos.observacionEntrega.value="El elemento fue entregado al grupo: "+ combo[combo.selectedIndex].text;
		}else{
			document.elementos.grupoAcargo[(id-1)].value=combo.value;
			document.elementos.observacionEntrega[(id-1)].value="El elemento fue entregado al grupo: "+ combo[combo.selectedIndex].text;
		}
	}
	
	function eliminar(id){
		if(confirm("¿Está seguro de realizar esta acción?")){
			document.gastos.accion.value="12";
			document.gastos.idGasto.value=id;
			document.gastos.action='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x"/>';
			document.gastos.submit();
		}
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">

	<form name="elementos" method="post" action='<c:url value="/adminProyectos/llenarEntrega.jsp"/>'>
	<c:if test="${sessionScope.proyecto.estado==2 or sessionScope.proyecto.estado==7}">
		<table>
			<tr>
				<td><input type="image" src='<c:url value="/comp/img/RegistraGasto.gif" />' onclick="comando(12)"></td>				
				<td><input type="image" src='<c:url value="/comp/img/EntregaElementos.gif" />' onclick="comando(10)"></td>
			</tr>
		</table>
	</c:if>
	
		<input type="hidden" name="accion" value='11'>
		<input type="hidden" name="idProyecto" value='<c:out value="${sessionScope.proyecto.id}"/>'>
		<input type="hidden" name="idRub" value='<c:out value="${sessionScope.idRub}"/>'>		
		
	<c:if test="${!empty requestScope.listaElementos}">
        <table align="center" class="tablas" width="95%" >
        <caption>Lista elementos a entregar <c:out value="${sessionScope.nombreRubro}"/></caption>
        <tr>
	       	<th align="right" width="10px"><b>#</b></th>
        	<th align="center" width="200px"><b>Descripción Elemento</b></th>
        	<th align="center" width="100px"><b>Código</b></th>
        	<th align="center" width="300px">Datos de Entrega:</th>
        	
        </tr>
	<c:forEach begin="0" items="${requestScope.listaElementos}" var="lista" varStatus="st">
		<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if>>
			<td width="10px" class="listas"><c:out value="${st.count}"/>
				<input type="hidden" name="idGasto" value='<c:out value="${lista.idGasto}"/>'>
			</td>
			<td class="listas" width="200px"><c:out value="${lista.descripcion}"/></td>
			<td class="listas" width="100px"><input type="text" name="codigo" value='<c:out value="${lista.codigo}"/>'></td>
			<td width="300px">
				<table width="100%" class="tablas">
					<tr>
						<td>
							<select name="ubicar" onchange='cambioUbicacion(this,<c:out value="${st.count}"/>)'>
								<option value="n">---Enviar A:----</option>								
								<option value="g" <c:if test="${lista.ubicacion=='g'}">selected</c:if>>Grupo/Semillero de Investigación</option>
								<option value="b" <c:if test="${lista.ubicacion=='b'}">selected</c:if>>Biblioteca</option>
								<option value="p" <c:if test="${lista.ubicacion=='p'}">selected</c:if>>Se queda en proyecto</option>
								<option value="a" <c:if test="${lista.ubicacion=='a'}">selected</c:if>>Almacén</option>
								<option value="d" <c:if test="${lista.ubicacion=='d'}">selected</c:if>>Docente de Planta</option>								
							</select>
						</td>
						<td>
							<input type='text' name='fechaEntrega' style="width: 70%" readonly='true' id='f_date_a_<c:out value="${st.count}" />' size='13' value='<c:out value="${lista.fechaEntrega}"/>'>
							<button type='button' id='f_trigger_a_<c:out value="${st.count}" />'>...</button>
							<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_a_<c:out value="${st.count}" />',
					    			ifFormat       :    '%Y-%m-%d',
					    			showsTime      :    false,
					    			button         :    'f_trigger_a_<c:out value="${st.count}" />',
					    			singleClick    :    false,
					    			step           :    1
				    			})
			    			</script>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="hidden" name="grupoAcargo" id='grupo<c:out value="${st.count}"/>' value='<c:out value="${lista.grupoAcargo}"/>'>
							<select name="grupo_Acargo" style="300px" <c:if test="${lista.grupoAcargo==0}">disabled="disabled"</c:if> id="comboGrupo<c:out value="${st.count}" />" onchange='asignaGrupo(this,<c:out value="${st.count}"/>)'>
								<option value="0">---Grupo/Semillero de Investigación--------</option>
								<c:forEach begin="0" items="${requestScope.listaGrupos}" var="grupos" varStatus="st">
									<option value='<c:out value="${grupos.codigo}"/>' <c:if test="${lista.grupoAcargo==grupos.codigo}">selected</c:if>><c:out value="${grupos.nombre}"/></option>
								</c:forEach>
							</select>
						</td>	
					</tr>
					<tr>
						<th colspan="2" align="center">Observación</th>
					</tr>
					<tr>
						<td colspan="2">
							<textarea rows="1" cols="" name="observacionEntrega"><c:out value="${lista.observacionEntrega}"/></textarea>
						</td>		
					</tr>
				</table>
			</td>					
		</tr>
	</c:forEach>
		<tr>
			<td align="center" colspan="5"><input type="image" src='<c:url value="/comp/img/Guardar.gif"/>'></td>
		</tr>
		</table>
	</c:if>
	</form>
	<c:if test="${empty requestScope.listaElementos}">
	<h4 align="center">No hay registros de elementos para devolver en este rubro </h4>
	</c:if>
</body>
<%
session.removeAttribute("listaDevolutivo");

%>
</html>