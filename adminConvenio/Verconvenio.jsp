<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>

</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${requestScope.datoConvenio!=null}">
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
	<CAPTION>Datos generales del Convenio</CAPTION>
		<tr>
			<th colspan="3"><b>Nombre de Convenio</b></th>
		</tr>
		<tr>
			<td colspan="3"><c:out value="${requestScope.datoConvenio.nombreConvenio}"/></td>
		</tr>
		
		<tr>
			<th colspan="3"><b>Nombre del Proyecto</b></th>
		</tr>
		<tr>
			<td colspan="3"><c:out value="${requestScope.datoConvenio.nombreproyecto}"/></td>
		</tr>
		
		<tr>
			<td colspan="3">
				<table width="100%">
					<tr>
						<th><b>Número Convenio</b></th>
						<th><b>Número disponibilidad</b></th>
						<th><b>Suma valor efectivo $</b></th>
						<th width="120px"><b>Suma valor especie $</b></th>
						<th width="120px"><b>Fecha de registro</b></th>
						<th width="120px"><b>Fecha de Inicio</b></th>
						<th width="120px"><b>Fecha de finalizacion</b></th>
					</tr>
					<tr>
						<td align="center"><c:out value="${requestScope.datoConvenio.codigo}"/></td>
						<td align="center"><c:out value="${requestScope.datoConvenio.numDisp}"/></td>
						<td align="center"><c:out value="${requestScope.datoConvenio.VEfectivo}"/></td>
						<td align="center" width="120px"><c:out value="${requestScope.datoConvenio.VEspecie}"/></td>
						<td align="center" width="120px"><c:out value="${requestScope.datoConvenio.fecha}"/></td>
						<td align="center" width="120px"><c:out value="${requestScope.datoConvenio.fechaInicio}"/> Meses</td>
						<td align="center" width="120px"><c:out value="${requestScope.datoConvenio.fechaFinalizacion}"/></td>
					</tr>
				</table>
			</td>
		</tr>
				
				<td>
					<table>

						<tr>
							
							<th with="150px"><b>Facultad</b></th>
							<th with="150px"><b>Grupo/Semillero de Investigación</b></th>
							<th with="150px"><b>Proyecto Curricular</b></th>
						</tr>
						<tr>
							
							<td with="150px"><c:if test="${requestScope.datoConvenio.facultad=='1'}">hola</c:if></td>
							<td with="150px"><c:out value="${requestScope.datoConvenio.grupo}" /></td>
							<td with="150px"><c:out value="${requestScope.datoConvenio.proycurri}" /></td>
						</tr>
					</table>
				</td>
				
				
				
				<td>
					<table>

						<tr>
							
							<th with="150px"><b>Estado</b></th>
							<th with="150px"><b>Tipo</b></th>
							
						</tr>
						<tr>
							
					  <td><select name="estado">
									  <option value="0" <c:if test="${requestScope.datoConvenio.estado==0}">selected</c:if>>------------</option>
			                          <option value="1" <c:if test="${requestScope.datoConvenio.estado==1}">selected</c:if>>Aprobado</option>
			                          <option value="2" <c:if test="${requestScope.datoConvenio.estado==2}">selected</c:if>>Vigente</option>
			                          <option value="3" <c:if test="${requestScope.datoConvenio.estado==3}">selected</c:if>>Finalizado</option>
			                          <option value="4" <c:if test="${requestScope.datoConvenio.estado==4}">selected </c:if>>Cancelado</option>
			                          <option value="5" <c:if test="${requestScope.datoConvenio.estado==5}">selected</c:if>>En Prueba</option>
			                          <option value="6" <c:if test="${requestScope.datoConvenio.estado==6}">selected</c:if>>Aplazado</option>
			                          <option value="7" <c:if test="${requestScope.datoConvenio.estado==7}">selected</c:if>>Proc. Finalización</option>
		              </select></td>
							<td><select name="tipo">
			                <option value="1" <c:if test="${requestScope.datoConvenio.tipo==1}">selected</c:if>>Vigente</option>
			                <option value="2" <c:if test="${requestScope.datoConvenio.tipo==2}">selected</c:if>>Indefinido</option>
			                <option value="3" <c:if test="${requestScope.datoConvenio.tipo==3}">selected</c:if>>Terminado</option>
			                <option value="4" <c:if test="${requestScope.datoConvenio.tipo==4}">selected</c:if>>Cancelado</option>
		             </select></td>
							
						</tr>
					</table>
				</td>
				
				
		<%-- 	
				
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
		
	--%>	
		
		<tr>
  			<td colspan="3" align="center"><input type="image" src='<c:url value="/comp/img/Guardar.gif"/>'></td>
 		</tr>
 		
	</table>
	</form>
<br>

	<%-- <form name="estadoBandera" method="post" action="<c:url value='/GestionGeneralProyectos/AdminGeneralProyectos.x' />">
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
     	--%>

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
<!-- 		     			<textarea name="obsProyecto" style="width:100%"></textarea> -->
	     			</td>
     			</tr>
     			<tr>
     				<td colspan="3" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="observacion(document.observProyect.obsProyecto)"></td>
     			</tr>
     		</table>
     	</form>
</c:if>
<c:if test="${requestScope.datoConvenio==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Proyecto de Investigación</h4>
</c:if>
</body>
</html>