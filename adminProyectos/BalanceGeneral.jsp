<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<script type="text/javascript" language="javascript" src='<c:url value="/comp/js/lytebox.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
<c:import url="/general.jsp"/>
<script>
	function registros(id){
		if('<c:out value="${sessionScope.proyecto.consecContrato}" />'!='--'){
			document.balance.accion.value="8";
			document.balance.idRub.value=id;
	 		document.balance.target="frameRegistro";
			document.balance.submit();
		}else{
			alert("No se ha creado aun el contrato de este proyecto");
		}
	}
	function ver(accion){
		document.balance.accion.value=accion;
		document.balance.target="frameRegistro";
		document.balance.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${sessionScope.proyecto!=null}">
<br>
	<table width="100%">
		<tr>
			<td align="left">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=2&id=${sessionScope.proyecto.id}&tipo=${sessionScope.proyecto.claseProyecto}"/>'><img border="0" src='<c:url value="/comp/img/tabs/General1.gif"/>'></a></td>
						<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=6"/>'><img border="0" src='<c:url value="/comp/img/tabs/Documentos1.gif"/>'></a></td>
						<td><img border="0" src='<c:url value="/comp/img/tabs/Balance2.gif"/>'></td>
						<td><a href='<c:url value="/adminProyectos/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Tiempos1.gif"/>'></a></td>
						<td><a href='<c:url value="/adminProyectos/Coinvestigadores.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Investigadores1.gif"/>'></a></td>
					</tr>
				</table>			
			</td>
			<td align="right">
				<table cellpadding="0" cellspacing="0">
					<tr>
						<td><img border="0" src='<c:url value="/comp/img/impresora.gif"/>' onclick="print()"></td>
				<!--  	<td><a href='<c:url value="/GestionProyectos/CrearResumenExcel.x" />'><img border="0" src='<c:url value="/comp/img/xcel2.gif"/>'></a></td> -->
						<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=15"/>' target="_parent" rel="lyteframe" title="Modificación de presupuesto" rev="width: 700px; height: 400px; scrolling: auto;"><img border="0" src='<c:url value="/comp/img/Cambio.png"/>'></a></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>	
<br>
	<table width="95%" class="tablas" align="center">
		<CAPTION>Datos generales del proyecto</CAPTION>
		<tr>
			<th colspan="5"><b>Nombre de Proyecto</b></th>
		</tr>
		<tr>
			<td colspan="5"><c:out value="${sessionScope.proyecto.proyecto}"/></td>
		</tr>
		<tr>
			<th width="20%"><b>Director del Proyecto</b></th>
			<th width="20%"><b>Código</b></th>
			<th width="20%"><b>Fecha Aprobación</b></th>
			<th width="20%"><b>Estimado Fin</b></th>
			<th width="20%"><b>Total Aprobado</b></th>
		</tr>
		<tr>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.director}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.codigo}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.fecAprobacion}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.fecEstimadoFin}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.balanceProyecto.totalAprobado}"/></td>			
		</tr>
	</table>

	<form name="balance" action='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x"/>' method="post">
		<input type="hidden" name="accion" value="0">
		<input type="hidden" name="idRub" value="0">
		<input type="hidden" name="idProyecto" value='<c:out value="${sessionScope.proyecto.id}"/>'/>
		<table width="95%" align="center" class="tablas">
			<caption>Ficha Balance Rubros</caption>
			<tr>
				<td align="center" class="renglones"><b>Rubro</b></td>
				<td align="center" class="renglones" width="75px"><b>Aprobado</b></td>
				<td align="center" class="renglones" width="75px"><b>Comprometido</b></td>
				<td align="center" class="renglones" width="75px"><b>Ejecutado</b></td>
				<td align="center" class="renglones" width="75px"><b>Saldo</b></td>
				<td align="center" class="renglones" width="5px"><b>*</b></td>
			</tr>
			<c:forEach begin="0" items="${sessionScope.balanceProyecto.listaRubros}" var="lista" varStatus="st">
			<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if> >
				<td><c:out value="${lista.nombreRubro}"/></td>
				<td class="listas" width="75px" align="right"><c:out value="${lista.valorRubro}"/></td>
				<td width="75px" align="right"><c:out value=""/></td>
				<td width="75px" align="right"><c:out value="${lista.valorEjecutado}"/></td>
				<td width="75px" align="right"><c:out value="${lista.valorSaldo}"/></td>
				<td width="5px" align="center"><img src='<c:url value="/comp/img/find.png"/>' onclick='registros(<c:out value="${lista.idRubro}"/>)'></td>
			</tr>
			</c:forEach>		
			<tr>
				<th align="right">Totales</th>
				<th align="center"><span id="totalAprobado"><c:out value="${sessionScope.balanceProyecto.totalAprobado}"/></span></th>
				<th align="center"><span id="totalComprometido"><c:out value=""/></span></th> 
				<th align="center"><span id="totalEjecutado"><c:out value="${sessionScope.balanceProyecto.totalEjecutado}"/></span></th>
				<th align="center"><span id="totalSaldo"><c:out value="${sessionScope.balanceProyecto.totalSaldo}"/></span></th>
				<th>&nbsp;</th>
			</tr>
		</table>
	</form>
	<table align="center" width="100%">
		<tr>
			<td>
				<iframe frameborder="0" height="350px" marginheight="1px" width="100%" name="frameRegistro" id="frameRegistro">
					Pailas no podemos ver frames
				</iframe>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${sessionScope.proyecto==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Proyecto de Investigación</h4>
</c:if>
</body>
</html>