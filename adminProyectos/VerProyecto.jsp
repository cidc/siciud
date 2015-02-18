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
			alert("Favor digitar la respectiva observaci�n que desea ingresar");
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
			<td><img src='<c:url value="/comp/img/tabs/General1.gif"/>'></td>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=6"/>'><img border="0" src='<c:url value="/comp/img/tabs/Documentos1.gif"/>'></a></td>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/tabs/Balance1.gif"/>'></a></td>
			<td><a href='<c:url value="/adminProyectos/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Tiempos1.gif"/>'></a></td>
			<td><a href='<c:url value="/adminProyectos/Coinvestigadores.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Investigadores1.gif"/>'></a></td>
			<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=22"/>'><img border="0" src='<c:url value="/comp/img/tabs/Productos.gif"/>'></a></td>
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
						<th width="120px"><b>C�digo</b></th>
						<th width="120px"><b>Cant Co-Investigadores</b></th>
						<th width="120px"><b>Fecha Inicio</b></th>
						<th width="120px"><b>Fecha Aprobaci�n</b></th>
						<th width="120px"><b>Duraci�n Proyecto</b></th>
						<th width="120px"><b>Estimado Fin</b></th>
						<c:if test="${sessionScope.fecActaFin==null}">
						<th width="120px"><b>Fecha de Cierre</b></th>
						</c:if>
					</tr>
					<tr>
						<td align="center"><c:out value="${sessionScope.proyecto.director}"/></td>
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.codigo}"/></td>
						<c:if test="${sessionScope.proyecto.cantCoInvest!=0}" >
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.cantCoInvest}"/></td>
						</c:if>
						<c:if test="${sessionScope.fecInicio!=null}">
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.fecInicio}"/></td>
						</c:if>
						<c:if test="${sessionScope.fecInicio==null}">
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.fecActaInicio}"/></td>
						</c:if>
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.fecAprobacion}"/></td>
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.duracion}"/> Meses</td>
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.fecEstimadoFin}"/></td>
						<c:if test="${sessionScope.fecActaFin==null}">
						<td align="center" width="120px"><c:out value="${sessionScope.proyecto.fecActaFin}"/></td>
						</c:if>
					</tr>
				</table>
			</td>
		</tr>
				<td>
					<table>

						<tr>
							<th><b>Documento de Identidad</b></th>
							<th with="150px"><b>Facultad</b></th>
							<th with="150px"><b>Grupo/Semillero de Investigaci�n</b></th>
							<th with="150px"><b>Proyecto Curricular</b></th>
							<th><b>Tel�fono Celular</b></th>
							<th><b>Correo Electr�nico</b></th>
							<c:if test="${sessionScope.proyecto.tutor!=null}" ><th><b>Docente Director</b></th></c:if>
							<th><b>Total Aprobado</b></th>
						</tr>
						<tr>
							<td><c:out value="${sessionScope.proyecto.documento}" /></td>
							<td with="150px"><c:out value="${sessionScope.proyecto.facultad}" /></td>
							<td with="150px"><c:out value="${sessionScope.proyecto.grupoInvestigacion}" /></td>
							<td with="150px"><c:out value="${sessionScope.proyecto.proyCurricular}" /></td>
							<td><c:out value="${sessionScope.proyecto.celular}" /></td>
							<td><c:out value="${sessionScope.proyecto.correo}" /></td>
							<c:if test="${sessionScope.proyecto.tutor!=null}" ><td><c:out value="${sessionScope.proyecto.tutor}" /></td></c:if>
							<td align="center"><c:out value="${sessionScope.balanceProyecto.totalAprobado}"/></td>	
						</tr>
					</table>
				</td>
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
							<c:if test="${sessionScope.loginUsuario.idUsuario==5452 or sessionScope.loginUsuario.idUsuario==3944}">
							<td width="125px" align="center">
								<select name="estado" onchange="subEstados()">
									  <option value="0" <c:if test="${sessionScope.proyecto.estado==0}">selected</c:if>>------------</option>
			                          <option value="1" <c:if test="${sessionScope.proyecto.estado==1}">selected</c:if>>En tr�mite</option>
			                          <option value="2" <c:if test="${sessionScope.proyecto.estado==2}">selected</c:if>>Vigente</option>
			                          <option value="3" <c:if test="${sessionScope.proyecto.estado==3}">selected</c:if>>Finalizado</option>
			                          <option value="4" <c:if test="${sessionScope.proyecto.estado==4}">selected </c:if>>Cancelado</option>
			                          <option value="5" <c:if test="${sessionScope.proyecto.estado==5}">selected</c:if>>En Prueba</option>
			                          <option value="6" <c:if test="${sessionScope.proyecto.estado==6}">selected</c:if>>Suspendido</option>
			                          <option value="7" <c:if test="${sessionScope.proyecto.estado==7}">selected</c:if>>Proc. Finalizaci�n</option>
			                          <option value="8" <c:if test="${sessionScope.proyecto.estado==8}">selected</c:if>>Cr�tico</option>
			                          <option value="9" <c:if test="${sessionScope.proyecto.estado==9}">selected</c:if>>Plazo Adicional</option>
								</select>
							</td>
							</c:if>
							<c:if test="${sessionScope.loginUsuario.idUsuario!=5452 and sessionScope.loginUsuario.idUsuario!=3944}">
							<td width="125px" align="center">
									  <c:if test="${sessionScope.proyecto.estado==0}">------------</c:if>
			                          <c:if test="${sessionScope.proyecto.estado==1}">Aprobado</c:if>
			                          <c:if test="${sessionScope.proyecto.estado==2}">Vigente</c:if>
			                          <c:if test="${sessionScope.proyecto.estado==3}">Finalizado</c:if>
			                          <c:if test="${sessionScope.proyecto.estado==4}">Cancelado </c:if>
			                          <c:if test="${sessionScope.proyecto.estado==5}">En Prueba</c:if>
			                          <c:if test="${sessionScope.proyecto.estado==6}">Aplazado</c:if>
			                          <c:if test="${sessionScope.proyecto.estado==7}">Proc. Finalizaci�n</c:if>
			                          <c:if test="${sessionScope.proyecto.estado==8}">Cr�tico</c:if>
			                          <c:if test="${sessionScope.proyecto.estado==9}">Plazo Adicional</c:if>
							</td>
							</c:if>
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
     		<c:if test="${sessionScope.ajaxsubestado!=null}">
				<table width="95%" class="tablas" align="center">
     			<CAPTION>Sub-Estado del Proyecto</CAPTION>
     				<c:forEach begin="0" items="${sessionScope.ajaxsubestado}" var="sub" varStatus="st"> 
	     				<tr>
	     				<th><b><c:out value="${sub.listaSubEstados}"></c:out></b></th>
	     				<td><input type="radio" name="flag" value='${sub.subEstados}' <c:if test="${sessionScope.proyecto.flag==sub.subEstados}">checked</c:if>></td>
	     				<td><c:out value="${sub.subEstadoDescrip}"></c:out></td>
	     				</tr>
     				</c:forEach>
     				<c:if test="${sessionScope.loginUsuario.idUsuario==5452 or sessionScope.loginUsuario.idUsuario==3944}"> 
     				<tr>
	     			<td colspan="4" align="center"><input type="image" src='<c:url value="/comp/img/Guardar.gif"/>'></td>
	     			</tr>
	     			</c:if>
   				</table>
			</c:if>
     	</form>

     	<form name="observProyect" method="post" action="<c:url value='/GestionGeneralProyectos/AdminGeneralProyectos.x' />">
     		<input type="hidden" name="accion" value="3">
     		<table width="95%" class="tablas" align="center">
     			<CAPTION>Observaciones</CAPTION>
     			<tr>
	     			<th width="120px" ><b>Por</b></th>
     				<th width="70px" ><b>Fecha</b></th>
     				<th ><b>Observaci�n</b></th>
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
<h4 align="center">No se logr� encontrar la informaci�n del Proyecto de Investigaci�n</h4>
</c:if>
<form method="post" name="frmAjax" action="<c:url value="/GestionProyectos/Ajax.x"/>">
		<input type="hidden" name="dato2" value=''>
		<input type="hidden" name="para" value=''>
</form>
</body>
</html>