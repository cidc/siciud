<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<script type="text/javascript" language="javascript" src='<c:url value="/comp/js/lytebox.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp"/>
<script>
	var nav4=window.Event ? true : false;
	function numeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}
	
	function pregunta(){
		
		
		if(validar()==true){
		var sumatoria=parseInt(document.formularioEnviar.sumatoria.value);
		var efectivo=parseInt(document.formularioEnviar.VEfectivoConv.value);
		var tol=parseInt(document.formularioEnviar.total.value);
		if((efectivo+sumatoria)<=tol){
						
			document.formularioEnviar.action='<c:url value="/adminConvenio/llenarEntidadConv.jsp"/>';
			document.formularioEnviar.submit();
		}else{
			alert("El total Efectivo no debe ser mayor al Total Aprobado");		
			alert("El valor del Efectivo debe ser menor: $"+(tol-sumatoria));
		}
		
		}
	}	
	
	function validar(){
		if(document.formularioEnviar.VEfectivoConv.value==""){
			alert("El Valor Efectivo no puede estar vacio");	
			return false;
			}
			if(document.formularioEnviar.VEfectivoConv.value=="0"){
			alert("El Valor Efectivo no puede ser 0");
			return false;
			}			
			if(document.formularioEnviar.VEspecieConv.value==""){
			alert("El Valor Efectivo no puede ser 0");
			return false;
			}
			return true;
	}
		
		
	
	function enviar(id,action,entidad){
			
			document.frmAporte.accion.value=action;
			document.frmAporte.idCon.value=id;
			document.frmAporte.nombreEntidad.value=entidad;
			document.frmAporte.submit();
	
	}                                              


</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${sessionScope.datoConvenio!=null}">
<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=3&idConv=${sessionScope.datoConvenio.idconvenio}"/>'><img border="0" src='<c:url value="/comp/img/convenio/Proyectos.gif"/>'></a></td>
		    <td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/convenio/Documentos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Tiempos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Grupos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/GruposInv.gif"/>'></a></td>
			<td><img border="0" src='<c:url value="/comp/img/convenio/EntidadesClick.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=15"/>'><img src='<c:url value="/comp/img/prAprobado.gif"/>'></a></td>
	
		</tr>
	</table>
<br>
	
	<table width="95%" class="tablas" align="center">
		<CAPTION>Datos generales del proyecto</CAPTION>
		<tr>
			<th colspan="5"><b>Nombre de Proyecto</b></th>
		</tr>
		<tr>
			<td colspan="5"><c:out value="${sessionScope.datoConvenio.nombreproyecto}"/></td>
		</tr>
		<tr>
			
			<th width="20%"><b>Número Convenio</b></th>
			<th width="20%"><b>Fecha Inicio</b></th>
			<th width="20%"><b>Estimado Fin</b></th>
			<th width="20%"><b>Total Aprobado</b></th>
			<th width="20%"><b>Total Aportado</b></th>
		</tr>
		<tr>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.codigo}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaInicio}"/></td>
			<td width="20%" align="center">$<c:out value="${sessionScope.datoConvenio.fechaFinalizacion}"/></td>
			<td width="20%" align="center">$<c:out value="${sessionScope.datoConvenio.finanza.VAprobado}"/></td>
			<td width="20%" align="center">$<c:out value="${sessionScope.datoConvenio.finanza.VAportado}"/></td>
		</tr>
	</table>
	
	<c:if test="${!empty sessionScope.datoConvenio.listaentidadesConv}">
	<form name="frmAporte"  method="post" action='<c:url value="/adminConvenio/AdminConvenio.x"/>'>
		<input type="hidden" name="accion" value="0">
		<input type="hidden" name="idCon" value="0">
		<input type="hidden" name="nombreEntidad" value="">
			<table width="95%" align="center" class="tablas">
			<caption>Lista de entidades Asociadas al convenio</caption>
			<tr>
				<th width="5px">&nbsp;</th>
			    <th><b>Nombre</b></th>
			    <th><b>Valor Especie (Informativo)</b></th>
			    <th><b>Valor Efectivo</b></th>
			    <th><b>Valor Total</b></th>
			    <th><b>Aportes</b></th>
			</tr>			
			<c:set var="numero"  value="0" />
			<c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
			<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if> >
				<td width="5px"><c:out value="${st.count}"/></td>
				<td width="100px" align="center"><c:out value="${lista.entidadid}"/></td>
				<td width="100px" align="center">$<c:out value="${lista.VEspecieConv}"/></td>
				<td width="100px" align="center">$<c:out value="${lista.VEfectivoConv}"/></td>
				<td width="100px" align="center">$<c:out value="${lista.VTotal}"/></td>
				<td class="estado" align="center"><img src='<c:url value="/comp/img/Ver.gif"/>' onclick='enviar(<c:out value="${lista.identidadconvenio}"/>,17,"${lista.entidadid}")'></td>
				
				<c:set var="numero"  value="${numero+lista.VEfectivoConv}"/>
					</tr>
		    </c:forEach>
		    
		</table>
	</form>
	
	</c:if>
	<c:if test="${empty sessionScope.datoConvenio.listaentidadesConv}">
	<c:set var="numero"  value="0" />
	<h3 align="center">No hay entidades vinculadas al convenio</h3>
	</c:if>
	
	
	
	<form  name="formularioEnviar">
		<input type="hidden" name="accion" value="14">
		<input type="hidden" name="validar" value="3">
		<input type="hidden" name="por" value="2">
		<input type="hidden" name="total" value="${sessionScope.datoConvenio.finanza.VAprobado}">
		<input type="hidden" name="sumatoria" value="${numero}">
		<table align="center" width="95%" class="tablas">
		<caption>Registro de una Entidad asociada </caption>
		<tr>
		
		 <td class="renglones"><b>Entidad</b></td>
    	 <td colspan="6"> <select name="entidadid" style="width: 100%" >
     		  <c:if test="${!empty requestScope.listaEntidades}">
				<c:forEach begin="0" items="${requestScope.listaEntidades}" var="lista" varStatus="st">
		           <option value="${lista.id}"> <c:out value="${lista.nombre}" /> </option>
			 </c:forEach>
		
		</c:if>   
               </select>
          </td>
          
     </tr>
     <tr>
     
     <td class="renglones"><b>Valor Especie:</b></td>
          <td><input type="text" maxlength="9" name="VEspecieConv" style="text-align:right; width: 90%" value="0" onkeypress="return numeros(event)"></td>
     <td class="renglones"><b>Valor Efectivo:</b></td>
          <td><input type="text" maxlength="9" name="VEfectivoConv" style="text-align:right; width: 90%" value="0" onkeypress="return numeros(event)"></td>
     
     </tr>		
			<tr>
				<td colspan="6" align="center">
				<img src=<c:url value="/comp/img/Enviar.gif" /> onclick="pregunta()"></td>
			</tr>
			
		</table>
	</form>
	
</c:if>
<table>
		<tr>
			<td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td>
		</tr>
	</table>
	<form method="post" name="frmAjax" action="<c:url value="/adminConvenio/Ajax.x"/>">
		<input type="hidden" name="dato" value=''>
		<input type="hidden" name="para" value=''>
		<input type="hidden" name="remove" value=''>
	</form>

<c:if test="${sessionScope.datoConvenio==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Convenio</h4>
</c:if>

</body>
</html>