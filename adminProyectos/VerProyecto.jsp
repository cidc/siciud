<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>

	function cont(id){
		document.getElementById("");
	}

	function observacion(caja){
		if(caja.value.length<5)
			alert("Favor digitar la respectiva observación que desea ingresar");
		else
			document.observProyect.submit();
	}
	function ver(accion,id){
		document.getElementById("a"+id).style.display='none';
		document.getElementById("espere"+id).style.display='';
		if(accion=="4")
			document.comandos.action='<c:url value="/GestionProyectos/documentosServlet.x"/>';
		document.comandos.accion.value=accion;
		document.comandos.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${sessionScope.proyecto!=null}">
	<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><img src='<c:url value="/comp/img/tabs/General2.gif"/>'></td>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=6"/>'><img border="0" src='<c:url value="/comp/img/tabs/Documentos1.gif"/>'></a></td>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/tabs/Balance1.gif"/>'></a></td>
			<td><a href='<c:url value="/adminProyectos/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Tiempos1.gif"/>'></a></td>
			<td><a href='<c:url value="/adminProyectos/Coinvestigadores.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Investigadores1.gif"/>'></a></td>
		</tr>
	</table>
<br>
<form name="frmEstado" method="post" action="<c:url value='/GestionGeneralProyectos/AdminGeneralProyectos.x' />">
	<input type="hidden" name="accion" value="4">
	<table width="95%" class="tablas" align="center">
	<CAPTION>Datos generales del proyecto</CAPTION>
		<tr>
			<th colspan="3"><b>Nombre de Proyecto</b></th>
		</tr>
		<tr>
			<td colspan="3"><c:out value="${sessionScope.proyecto.proyecto}"/></td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%">
					<tr>
						<th><b>Director del Proyecto</b></th>
						<th width="120px"><b>Código</b></th>
						<th width="120px"><b>Fecha Aprobación</b></th>
						<th width="120px"><b>Duración Proyecto</b></th>
						<th width="120px"><b>Estimado Fin</b></th>
					</tr>
					<tr>
						<td align="center"><c:out value="${sessionScope.proyecto.director}"/></td>
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.codigo}"/></td>
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.fecAprobacion}"/></td>
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.duracion}"/> Meses</td>
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.fecEstimadoFin}"/></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<th><b>Facultad</b></th>
			<th><b>Grupo/Semillero de Investigación</b></th>
			<th><b>Proyecto Curricular</b></th>
		</tr>
		<tr>
			<td><c:out value="${sessionScope.proyecto.facultad}"/></td>
			<td><c:out value="${sessionScope.proyecto.grupoInvestigacion}"/></td>
			<td><c:out value="${sessionScope.proyecto.proyCurricular}"/></td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%">
						<tr>
							<th width="20px"><b></b></th>
							<th ><b>Convocatoria</b></th>
							<th width="125px"><b>Estado</b></th>
						</tr>
						<tr>
							<td width="20px">
							<c:if test="${sessionScope.proyecto.termRefConvo!=null}">
								<a href='<c:url value="/Documentos/Convocatorias/${sessionScope.proyecto.termRefConvo}"/>'><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a>
							</c:if>
							</td>
							<td ><c:out value="${sessionScope.proyecto.numConvocatoria} - ${sessionScope.proyecto.convocatoria}"/></td>
							<td width="125px" align="center">
								<select name="estado">
									  <option value="0" <c:if test="${sessionScope.proyecto.estado==0}">selected</c:if>>------------</option>
			                          <option value="1" <c:if test="${sessionScope.proyecto.estado==1}">selected</c:if>>Aprobado</option>
			                          <option value="2" <c:if test="${sessionScope.proyecto.estado==2}">selected</c:if>>Vigente</option>
			                          <option value="3" <c:if test="${sessionScope.proyecto.estado==3}">selected</c:if>>Finalizado</option>
			                          <option value="4" <c:if test="${sessionScope.proyecto.estado==4}">selected </c:if>>Cancelado</option>
			                          <option value="5" <c:if test="${sessionScope.proyecto.estado==5}">selected</c:if>>En Prueba</option>
			                          <option value="6" <c:if test="${sessionScope.proyecto.estado==6}">selected</c:if>>Aplazado</option>
			                          <option value="7" <c:if test="${sessionScope.proyecto.estado==7}">selected</c:if>>Proc. Finalización</option>
								</select>
							</td>
						</tr>
				</table>
			</td>
		</tr>
		
		<tr>
  			<td colspan="3" align="center"><input type="image" src='<c:url value="/comp/img/Guardar.gif"/>'></td>
 		</tr>
 		
	</table>
	</form>
<br>

	<form name="estadoBandera" method="post" action="<c:url value='/GestionGeneralProyectos/AdminGeneralProyectos.x' />">
     	<input type="hidden" name="accion" value="5">
     		<table width="95%" class="tablas" align="center">
     			<CAPTION>Estado de revisión del proyecto</CAPTION>
  					<tr>
     				<th><b>Sin Revizar</b></th>
     				<td><img src='<c:url value="/comp/img/flag0.gif"/>'></td>
     				<td><input type="radio" name="flag" value="0" <c:if test="${sessionScope.proyecto.flag==0}">checked</c:if>></td>
     				<td>El proyecto aun no tiene revisión</td>
     			</tr>
    				<tr>
     				<th><b>Ok</b></th>
     				<td><img src='<c:url value="/comp/img/flag1.gif"/>'></td>
     				<td><input type="radio" name="flag" value="1" <c:if test="${sessionScope.proyecto.flag==1}">checked</c:if>></td>
     				<td>El proyecto no presenta ninguna novedad</td>
     				</tr>
    				<tr>
     				<th><b>N. Atención</b></th>
     				<td ><img src='<c:url value="/comp/img/flag2.gif"/>'></td>
     				<td><input type="radio" name="flag" value="2" <c:if test="${sessionScope.proyecto.flag==2}">checked</c:if>></td>
     				<td>Necesita revisón detallada de estado académico o financiero<td></td>
     			</tr>
    				<tr>
     				<th><b>Crítico</b></th>
     				<td><img src='<c:url value="/comp/img/flag3.gif"/>'></td>
     				<td><input type="radio" name="flag" value="3" <c:if test="${sessionScope.proyecto.flag==3}">checked</c:if>></td>
     				<td>Necesita atención del Comité de investigaciones</td>
     			</tr>
     			<tr>
	     			<td colspan="4" align="center"><input type="image" src='<c:url value="/comp/img/Guardar.gif"/>'></td>
     			</tr>
     		</table>
     	</form>

     	<form name="observProyect" method="post" action="<c:url value='/GestionGeneralProyectos/AdminGeneralProyectos.x' />">
     		<input type="hidden" name="accion" value="3">
     		<table width="95%" class="tablas" align="center">
     			<CAPTION>Observaciones</CAPTION>
     			<tr>
	     			<th width="120px" ><b>Por</b></th>
     				<th width="70px" ><b>Fecha</b></th>
     				<th ><b>Observación</b></th>
     			</tr>
     			<c:forEach begin="0" items="${sessionScope.proyecto.listaObservaciones}" var="lista" varStatus="st">
				<tr <c:if test="${st.count mod 2 == 0}">class="trb"</c:if>>
					<td width="120px"><b><c:out value="${lista.usuario}"/></b></td>
					<td width="70px"><c:out value="${lista.fecha}"/></td>
					<td class="texto1j"><c:out value="${lista.observacion}"/></td>
				</tr>
     			</c:forEach>
     			<tr>
	     			<td colspan="3">
		     			<textarea name="obsProyecto" style="width:100%"></textarea>
	     			</td>
     			</tr>
     			<tr>
     				<td colspan="3" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="observacion(document.observProyect.obsProyecto)"></td>
     			</tr>
     		</table>
     	</form>
</c:if>
<c:if test="${sessionScope.proyecto==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Proyecto de Investigación</h4>
</c:if>
</body>
</html>