<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<script>

var nav4=window.Event ? true : false;
function soloNumeros(eve){
	var key=nav4?eve.which :eve.keyCode;
	return(key<=13 || (key>=48 && key<=57));
}
function guardar(){
	
		if(validarcampos()==true){
			if(validarcdp()==true){
				document.formCDP.submit();
				}
		}
	
}

function validarcampos(){
	
	
	var total=0;
	var n=parseInt(document.formCDP.numeroentidad.value);
	for(var i=1;i<=n;i++){
		
		var cdp=parseInt(document.getElementById("ventidad"+i).value);
		total=total+cdp;
	}


	if(total=="0"){
		 alert("Los valor total de cdp no puede ser 0");
		 return false;
	}
	if(document.formCDP.codigo.value==""){
		document.formCDP.codigo.value="Sin codigo"
		//alert("El Codigo del CDP no puede estar vacio ");	
		// return false;
	}
	
	return true;
		
}
function borrar(id,valorcdp)
{confirmar=confirm("¿Desea Realmente Eliminar el Registro?");  
if (confirmar)
	eliminar(id,valorcdp)


}
function eliminar(id,valorcdp){

	document.listadocdp.accion.value=22;
	document.listadocdp.idcdp.value=id;
	document.listadocdp.submit();
}

function validarcdp(){

	var n=parseInt(document.formCDP.numeroentidad.value);
	for(var i=1;i<=n;i++){
		
		var cdp=parseInt(document.getElementById("ventidad"+i).value);
		var sumacdp=parseInt(document.getElementById("sumaCDP"+i).value);
		var sumaaporte=parseInt(document.getElementById("sumaAporte"+i).value);
		var reembolso=parseInt(document.getElementById("reembolsoEntidad"+i).value);
		
		if(cdp>(sumaaporte-sumacdp+reembolso)){
		var entidad=document.getElementById("Entidad"+i).value
		alert("El valor del cdp asignado a la entidad "+entidad+" no puede ser mayor a "+(sumaaporte-sumacdp+reembolso));
		return false;
		}
	}
	return true;
	
}
function suma(formulario1){
	 for(var i=0;i<formulario1.idRubro.length;i++){
		document.listadoRubros.valorEntidad[i].value=eliminaFormatoMoneda(document.listadoRubros.valorEntidad[i].value);
	 }
	 document.listadoRubros.submit();
}
	 function enviar(id,action,nombre,valor){
	 	
	 	 document.listadocdp.accion.value=action;
	     document.listadocdp.idcdp.value=id;
	     document.listadocdp.nombrecdp.value=nombre;
	     document.listadocdp.valor.value=valor;
	     document.listadocdp.submit();
	 }	

    
</script>
<c:import url="/general.jsp"/>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
		<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=3&idConv=${sessionScope.datoConvenio.idconvenio}"/>'><img border="0" src='<c:url value="/comp/img/convenio/Proyectos.gif"/>'></a></td>
		    <td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/convenio/Documentos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Tiempos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Grupos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/GruposInv.gif"/>'></a></td>
			<td><a href='<c:url value="/adminEntidad/GestEntidad.x?validar=3&por=2 "/>'><img border="0" src='<c:url value="/comp/img/convenio/Entidades.gif"/>'></a></td>
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
			<th width="20%"><b>Total Aprobado Efectivo</b></th>
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
	<br>
<c:if test="${!empty sessionScope.datoConvenio.listacdpsConv}">
<form  action='<c:url value="/adminConvenio/AdminConvenio.x"/>' name="listadocdp">
<input type="hidden" name="accion" value="19">
<input type="hidden" name="idcdp" value="">
<input type="hidden" name="nombrecdp" value="">
<input type="hidden" name="valor" value="">

<table align="center" class="tablas">
<caption>Lista de CDPs</caption>
<c:set var="cantidade" value="${0}"></c:set>
	        <tr>
				<th style="width:400px;"><b>Rubro</b></th>
				<th><b>Codigo</b></th>
				<c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lis" varStatus="st">
				<th style="width:100px;"><b><c:out value="${lis.entidadid}"/></b></th>
				<c:set var="cantidade" value="${cantidade+1}"></c:set>
				
				</c:forEach>
				<th style="width:80px;" align="center"><b>Valor total Comprometido</b></th>
				<th style="width:80px;" align="center"><b>Valor Ejecutado</b></th>
				<th style="width:80px;" align="center"><b>Valor Reembolsado</b></th>
				<th style="width:80px;" align="center"><b>Valor Disponible</b></th>
				<th style="width:80px;" align="center"><b>fecha Registro</b></th>
				<th style="width:500px;" align="center"><b>observacion</b></th>
				<th style="width:20px;" align="center"><b>CRP</b></th>
			</tr>
			<c:set var="reembolsado"  value="0" />
			<c:set var="ejecutado"  value="0" />
			<c:set var="disponible"  value="0" />
			<c:forEach begin="0" items="${sessionScope.datoConvenio.listacdpsConv}" var="lista" varStatus="st">
				<c:set var="reembolsado"  value="${reembolsado+lista.reembolsototal}"/>
				<c:set var="ejecutado"  value="${ejecutado+lista.valorejecutado}"/>
				<c:set var="disponible"  value="${disponible+(lista.valortotal-(lista.valorejecutado+lista.reembolsototal))}"/>
				<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if>>
					<td style="width:400px;" align="center">
						  <c:out value="${lista.nombre}"/> 
					</td>
					<td style="width:80px;" align="center">
						  <c:out value="${lista.codigo}"/>
					</td>
					<c:forEach var="i" items="${lista.valoresString}" begin="0">
						 <td style="width:100px;" align="center">
					     	<c:out value="${i}"/>
						</td>
					</c:forEach>
					<td style="width:80px;" align="center">
					 	<c:out value="${lista.valorTotalString}"/>
					</td>
					<td style="width:80px;" align="center">
						  <c:out value="${lista.valorEjecutadoString}"/>
					</td>
					<td style="width:80px;" align="center">
						<c:out value="${lista.reembolsoTotalString}"/>
					</td>
					<td style="width:80px;" align="center">
						<c:out value="${lista.saldoDisponible}"/>
					</td>
					<td style="width:80px;" align="center">
						  <c:out value="${lista.fechaRegistro}"/>
					</td>
					<td style="width:500px;" align="center">
						  <c:out value="${lista.observacion}"/>
					</td>
					<td class="estado" align="center"><img src='<c:url value="/comp/img/Ver.gif"/>'  onclick='enviar("${lista.idcdp}",19,"<c:out value="${lista.nombre}"/>","${lista.valortotal}")'></td>
					<td width="5px" align="center"><img src='<c:url value="/comp/img/equis1.png"/>'  onclick='borrar(<c:out value="${lista.idcdp}"/>,"${lista.valortotal}")'></td>
				</tr>
			</c:forEach>
			<c:set var="totalcdp"  value="0" />
			<tr>
		    <td width="5px"></td>
		    <th><b>Total</b></th>
		    <c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
			  <td style="width:100px;" align="center">
				<c:out value="${lista.VCdpsString}"/>
				</td>
				<c:set var="totalcdp"  value="${totalcdp+lista.VCdps}"/>
			</c:forEach>
		   	<td width="100px" align="center"><c:out value="${sessionScope.datoConvenio.totalcdp}"/></td>
		   	<td width="100px" align="center"><c:out value="${sessionScope.datoConvenio.ejecutado}"/></td>
		   	<td width="100px" align="center"><c:out value="${sessionScope.datoConvenio.reembolsado}"/></td>
		   	<td width="100px" align="center"><c:out value="${sessionScope.datoConvenio.disponible}"/></td>
		    </tr>
		    
			   
</table>
</form>
<br/>
</c:if>

<c:if test="${empty sessionScope.datoConvenio.listacdpsConv}">
	<h3 align="center">No hay CDPS aprobados para este proyecto</h3>
</c:if>

<form name="formCDP" action='<c:url value="/adminConvenio/AdminConvenio.x"/>' name="listadoRubros">
	<input type="hidden" name="accion" value="16">
    <input type="hidden" name="idfinanza" value='<c:out value="${sessionScope.datoConvenio.finanza.idfinanza}"/>'>
	<c:set var="var2" value="${0}"></c:set>
	<table align="center" class="tablas">
	<caption>Insertar CDP</caption>
		<c:if test="${!empty requestScope.listaRubros}">
			<tr>
				<th style="width:150px;"><b>Rubro</b></th>
				<th><b>Codigo</b></th>
				<c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
				<th style="width:100px;"><b><c:out value="${lista.entidadid}"/></b></th>
				<c:set var="var2" value="${var2+1}"></c:set>
				</c:forEach>
				<input type="hidden" name="numeroentidad" value="${var2}">
				<th style="width:400px;" align="center"><b>observacion</b></th>
			</tr>
			<tr>
			   <td> <select name="idRubro" style="width:150px" >
     		   <c:if test="${!empty requestScope.listaRubros}">
			   <c:forEach begin="0" items="${requestScope.listaRubros}" var="lista" varStatus="st">
		       <option value="${lista.codigo}"> <c:out value="${lista.nombre}" /> </option>
			   </c:forEach>
		       </c:if>   
               </select>
               </td>
			   <td style="width:80px;" align="right">
					<b><input  maxlength="10"  type="text"  name="codigo"></b>
			   </td>
			   <c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
			   <input type="hidden" name="entidad" id='Entidad<c:out value="${st.count}"/>' value="<c:out value="${lista.entidadid}"/>">
				<input type="hidden" name="sumaCDP" id='sumaCDP<c:out value="${st.count}"/>' value="<c:out value="${lista.VCdps}"/>">
				<input type="hidden" name="sumaAporte" id='sumaAporte<c:out value="${st.count}"/>' value="<c:out value="${lista.VAportado}"/>">
				<input type="hidden" name="reembolsoEntidad" id='reembolsoEntidad<c:out value="${st.count}"/>' value="<c:out value="${lista.VReembolsado}"/>">
				
				<td style="width:100px;" align="right">
				    <input type="hidden" name="idconvent" value='<c:out value="${lista.identidadconvenio}"/>'>  
					<input id='ventidad<c:out value="${st.count}" />' maxlength="10" size="10" type="text" onkeypress="return soloNumeros(event)" name="valorEntidad" value="0">
				</td>
				</c:forEach>
						<td style="width:400px;" align="right">
							<b><textarea class="texto" name="observacion" style="width: 99%"></textarea></b>
						</td>
				</tr>
		</c:if>
		
		
		
			<tr>
				<td colspan="3" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>'  onclick="guardar()"></td>
			</tr>
	</table>
</form>

</body>
<script>

</script>
</html>