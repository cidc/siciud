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
<c:import url="/general.jsp"/>
</head>
<script>
var nav4=window.Event ? true : false;
function numeros(eve){
	var key=nav4?eve.which :eve.keyCode;
	return(key<=13 || (key>=48 && key<=57));
}
function nuevo(){
    document.getElementById("nuevo").style.display='';
 }
function cancelar(){
    document.getElementById("reembolso").style.display='none';
 }

 
function reembolso(){
    document.getElementById("reembolso").style.display='';
 }
function crear(){
	if(document.formcrpnuevo.crp.value==""){
		alert("Ingrese nombre CRP");
		return false;
	}else if(document.formcrpnuevo.codigocrp.value==""){
		alert("Ingrese codigo CRP");
		return false;
	}else if(document.formcrpnuevo.clientecrp.value==""){
		alert("Ingrese cliente CRP");
		return false;
	}else if(document.formcrpnuevo.valorcrp.value==""){
		alert("Ingrese valor CRP");
		return false;
	}else{
		return true;
		}
}
function borrar(id,valorcrp)
{confirmar=confirm("¿Desea Realmente Eliminar el Registro?");  
if (confirmar)
	eliminar(id,valorcrp)
}

function eliminar(id,valorcrp){

	document.formcargacrp.accion.value=21;
	document.formcargacrp.crpd.value=id;
	document.formcargacrp.submit();
}
function pregunta(){

	if(crear()==true){

		var sumatoria=parseInt(document.formcrpnuevo.sumatoria.value);
		var efectivo=parseInt(document.formcrpnuevo.valorcrp.value);
		var tol=parseInt(document.formcrpnuevo.valor.value);
		
		if((efectivo+sumatoria)<=tol){
			document.formcrpnuevo.submit();
		}else{
			alert("El total Efectivo no debe ser mayor al Total Aprobado");	
			alert("El valor del aporte debe ser menor: $"+(tol-sumatoria));
			}
		}
	}

function preguntaReembolso(){
	if(validarReembolso()==true){
		document.formReembolso.submit();
	}else{
				
	}
}

function validarReembolso(){

	var n=parseInt(document.formReembolso.numeroentidad.value);
	var sumacrp=parseInt(document.formReembolso.ValorSumatoriaCrp.value);
	
	var sumaReembolso=0;
	for(var i=1;i<=n;i++){
				
		var Vreembolso=document.getElementById("vreembolso"+i).value;
		var Vaportado=parseInt(document.getElementById("ValorAportadoEntidad"+i).value);
		var entidad=document.getElementById("Entidad"+i).value;
		if(Vreembolso=="" ){
			alert("El valor del reembolso de "+entidad+" no puede estar vacio");
			return false;
		}else{
			Vreembolso=parseInt(Vreembolso);
		}
		if(Vreembolso>Vaportado){
			alert("El valor del reembolso no puede ser mayor al aportado por esta entidad");
			return false;
		}
		
		sumaReembolso=Vreembolso+sumaReembolso;
	}
	if(sumaReembolso>sumacrp){
		alert("El valor del reembolso no puede ser mayor al total disponible");
		return false;
	}
	
	return true;
}


</script>
<body>
<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
		<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=3&idConv=${sessionScope.datoConvenio.idconvenio}"/>'><img border="0" src='<c:url value="/comp/img/convenio/Proyectos.gif"/>'></a></td>
		    <td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/convenio/Documentos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Tiempos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Grupos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/GruposInv.gif"/>'></a></td>
			<td><a href='<c:url value="/adminEntidad/GestEntidad.x?validar=3&por=2"/>'><img border="0" src='<c:url value="/comp/img/convenio/Entidades.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=15"/>'><img src='<c:url value="/comp/img/prAprobado.gif"/>'></a></td>
		</tr>
	</table>
<br>
<form name="form1">
<table class="tablas" align = "center" width="80%">
<CAPTION>Presupuesto Convenio</CAPTION>
<tr>  <td class="renglones" width="120px"><b>Convenio:</b></td>
      <td width="100px"><c:out value = "${sessionScope.datoConvenio.nombreConvenio}"/></td>
      <td class="renglones" width="100px"><b>Proyecto:</b></td>
      <td><c:out value = "${sessionScope.datoConvenio.nombreproyecto}"/></td>
</tr>

<tr>  <td class="renglones" colspan="5"><b>Numero de Convenio:</b></td> </tr>
<tr>  <td colspan="5"><c:out value = "${sessionScope.datoConvenio.codigo}"/></td></tr>
<tr>  <td class="renglones" colspan="5"><b>Numero de Disponibilidad:</b></td> </tr>
<tr>  <td colspan="5"><c:out value = "${sessionScope.datoConvenio.numDisp}"/></td></tr>
</table>
</form>
<br/>
        <table width="80%" align="center" class="tablas">
			<caption>CDP asociado</caption>
			<tr><td class="renglones" width="115px"><b>CDP: </b></td> 
			    <td class="renglones" width="115px"><b>Valor Asignado: </b></td>
			    
			<tr/> 
			<tr>
			<td width="115px"><c:out value="${requestScope.nombrecdp}"/></td>
			<td width="115px"><c:out value="${requestScope.valortotal}"/></td>
			
		
			
			</tr>
		</table>
		<table width="80%" align="center"> 
		<tr>
		<c:forEach begin="0" items="${sessionScope.datoConvenio.listacdpsConv}" var="li" varStatus="st">
			<c:if test="${li.idcdp==requestScope.idcdp}">
				<c:if test="${ li.reembolsototal=='0'}">
		<td align="center" colspan="3"><img src='<c:url value="/comp/img/Nuevogasto.gif"/>' onclick='nuevo()'> </td>
		</c:if>
		</c:if>
		</c:forEach>
		<td align="center" colspan="3"><img src='<c:url value="/comp/img/Rechazar.gif"/>' onclick='reembolso()'> </td>
		</tr>
        </table>
        <c:set var="numero"  value="0" />
<c:if test="${!empty sessionScope.datoConvenio.listacrpsConv}">
<form name="formcargacrp" method="post" action='<c:url value="/adminConvenio/AdminConvenio.x"/>'>
<input type="hidden" name="accion" value="">
<input type="hidden" name="crpd" value="">
<input type="hidden" name="nombrecdp" value="<c:out value="${requestScope.nombrecdp}"/>">
<input type="hidden" name="idcdp" value="<c:out value="${requestScope.idcdp}"/>">
<input type="hidden" name="valor" value="<c:out value="${requestScope.valortotal}"/>">


<table  class="tablas" align = "center" width="80%">
<CAPTION>Lista de CRP</CAPTION>

  <tr> <td class="renglones" width="115px"><b>CRP: </b></td>
       <td class="renglones"><b>Codigo: </b></td>
       <td class="renglones"><b>Cliente:</b></td>
       <td class="renglones"><b>Valor $:</b></td>
       <td class="renglones"><b>Fecha de registro:</b></td>
       <td class="renglones"><b>Observacion:</b></td>
 </tr>
 <c:forEach begin="0" items="${sessionScope.datoConvenio.listacrpsConv}" var="lista" varStatus="st">
 <tr>
  <td style="width:115px;">
			  <b><input type="text"  name="crp" readonly="readonly" value='<c:out value="${lista.nombre}"/>' ></b>
  </td>
  <td style="width:115px;">
			  <b><input type="text"  name="codigocrp" readonly="readonly" value='<c:out value="${lista.codigo}"/>'></b>
  </td>
  <td style="width:115px;">
			  <b><input type="text"  name="clientecrp" readonly="readonly" value='<c:out value="${lista.cliente}"/>'></b>
  </td>
   <td style="width:115px;">
			  <b><input type="text"  name="valorcrp" readonly="readonly" value='<c:out value="${lista.valor}"/>'></b>
  </td>
   <td style="width:115px;">
			  <b><input type="text"  name="fechacrp" readonly="readonly" value='<c:out value="${lista.fecha}"/>'></b>
  </td>
   <td style="width:400px;">
			   <b><input type="text"  name="observacioncrp" readonly="readonly" value='<c:out value="${lista.observacion}"/>'></b>
  </td>
 <c:forEach begin="0" items="${sessionScope.datoConvenio.listacdpsConv}" var="li" varStatus="st">
			<c:if test="${li.idcdp==requestScope.idcdp}">
				<c:if test="${ li.reembolsototal=='0'}">
  <td width="30px" align="center"><img src='<c:url value="/comp/img/equis1.png"/>' onclick='borrar(<c:out value="${lista.idcrp}"/>,"${lista.valor}")'></td>
</c:if>
</c:if>
</c:forEach>
 <tr/>
 <c:set var="numero"  value="${numero+lista.valor}"/>

</c:forEach>
</table>
</form>
           <table width="50%" align="center" class="tablas">
			<tr>
			<th width="200px"><b>Valor Asignado:</b></th><td><td width="100px" align="center">$<c:out value="${requestScope.valortotal}"/></td></td>
			<th width="200px"><b>Valor Disponible:</b></th><td><td width="100px" align="center">$<c:out value="${requestScope.valortotal-numero}"/></td></td>
			
			</tr>
		   </table>
</c:if>
<c:if test="${empty sessionScope.datoConvenio.listacrpsConv}">
	<h3 align="center">No hay CRP  para este CDP</h3>
</c:if>

<br>

<c:forEach begin="0" items="${sessionScope.datoConvenio.listacdpsConv}" var="li" varStatus="st">
			<c:if test="${li.idcdp==requestScope.idcdp}">
				<c:if test="${ li.reembolsototal!='0'}">


<table class="tablas" align = "center" width="80%">
	
	<caption>Reembolsado Entidades</caption>
	<tr>
 
       <c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
		<th style="width:100px;"><b><c:out value="${lista.entidadid}"/></b></th>
		</c:forEach>
		<th style="width:100px;"><b>Total Reembolsado</b></th>
 </tr>
	<tr>
	
	<c:forEach begin="0" items="${sessionScope.datoConvenio.listacdpsConv}" var="lista" varStatus="st">
					<c:if test="${lista.idcdp==requestScope.idcdp}">
							<c:forEach var="i" items="${lista.reembolsoEntidad}" begin="0"  varStatus="st"> 
								<td>
								  <input type="text" name="reembolsado"  id='reembolsado<c:out value="${st.count}" />' readonly="readonly" style="text-align:center; width: 98%" value='<c:out value="${i}"/>'>
								</td>
							</c:forEach>
							<td>
						<b><input type="text"  name="reembolsototal"   readonly="readonly" value='<c:out value="${lista.reembolsototal}"/>' style="text-align:center; width: 98%"></b>	
							</td>
					</c:if>
			</c:forEach>
	</tr>
</table>	
</c:if>		
</c:if>	
</c:forEach>
	<br>


<form name="formcrpnuevo" method="post" action='<c:url value="/adminConvenio/AdminConvenio.x"/>'>
<input type="hidden" name="accion" value="20">
<input type="hidden" name="idcdp" value="<c:out value="${requestScope.idcdp}"/>">

<input type="hidden" name="nombrecdp" value="<c:out value="${requestScope.nombrecdp}"/>">
<input type="hidden" name="valor" value="<c:out value="${requestScope.valortotal}"/>">
<input type="hidden" name="valortotal" value="<c:out value="${requestScope.valortotal}"/>">
<input type="hidden" name="sumatoria" value="${numero}">

<table id="nuevo" class="tablas" align = "center" width="80%" style="display: none">
<CAPTION>Nuevo CRP</CAPTION>

  <tr> <td class="renglones" width="115px"><b>CRP: </b></td>
       <td class="renglones"><b>Codigo:</b></td>
       <td class="renglones"><b>Cliente:</b></td>
       <td class="renglones"><b>Valor $:</b></td>
 </tr>
 <tr>
  <td style="width:115px;">
			  <b><input type="text"  name="crp" ></b>
  </td>
  <td style="width:115px;">
			  <b><input type="text"  name="codigocrp" ></b>
  </td>
  <td style="width:115px;">
			  <b><input type="text"  name="clientecrp" ></b>
  </td>
   <td style="width:115px;">
			  <b><input type="text"  name="valorcrp"  maxlength="9" onkeypress="return numeros(event)"></b>
  </td>
 <tr/>

<tr> <td class="renglones" colspan="4"><b>Observacion: </b></td> </tr>
  <tr> <td colspan="4"><textarea name="observcrp" style="width: 99%" class="texto"></textarea></td></tr>

  
  <tr>  <td align="center" colspan="4">
                  <img src='<c:url value="/comp/img/Registrar.gif"/>' onclick='pregunta()'>
                  <img src='<c:url value="/comp/img/Cancelar.gif"/>' onclick='cancelar()'>
        </td>
  </tr>
</table>
</form>


<form name="formReembolso" method="post" action='<c:url value="/adminConvenio/AdminConvenio.x"/>'>
<input type="hidden" name="accion" value="23">

<input type="hidden" name="idcdp" value="<c:out value="${requestScope.idcdp}"/>">
<input type="hidden" name="nombrecdp" value="<c:out value="${requestScope.nombrecdp}"/>">
<input type="hidden" name="valor" value="<c:out value="${requestScope.valortotal}"/>">
<input type="hidden" name="ValorSumatoriaCrp" value="${requestScope.valortotal-numero}">
<br>
<br>
<table id="reembolso" class="tablas" align = "center" width="80%" style="display: none">
<CAPTION>Reembolso</CAPTION>

  <tr>
  <c:set var="var2" value="${0}"></c:set>
       <c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
		<th style="width:100px;"><b><c:out value="${lista.entidadid}"/></b></th>
		<c:set var="var2" value="${var2+1}"></c:set>
		</c:forEach>
		<input type="hidden" name="numeroentidad" value="${var2}">
 </tr>
 <tr>
			
			<c:forEach begin="0" items="${sessionScope.datoConvenio.listaentidadesConv}" var="lista" varStatus="st">
			   <input type="hidden" name="entidad" id='Entidad<c:out value="${st.count}"/>' value="<c:out value="${lista.entidadid}"/>">
				<td>
				
				    <input type="hidden" name="idconvent" value='<c:out value="${lista.identidadconvenio}"/>'>  
				   <input type="text" id='vreembolso<c:out value="${st.count}" />' maxlength="9" name="vreembolso" style="text-align:right; width: 98%" value="0" onkeypress="return numeros(event)">
					</td>
						
				</c:forEach>
  </tr>
  
  <tr>
 		<th style="width:100px;"><b>Total Aportado por entidad</b></th>
  </tr>
  <tr>
  
  	<c:forEach begin="0" items="${sessionScope.datoConvenio.listacdpsConv}" var="lista" varStatus="st">
			
					<c:if test="${lista.idcdp==requestScope.idcdp}">
					
							<c:forEach var="i" items="${lista.valores}" begin="0"  varStatus="st"> 
								<td>
								  <input type="text" name="ValorAportadoEntidad"  id='ValorAportadoEntidad<c:out value="${st.count}" />' readonly="readonly" style="text-align:right; width: 98%" value='<c:out value="${i}"/>'>
								</td>
							</c:forEach>
							<c:forEach var="i" items="${lista.idcdpValores}" begin="0"  varStatus="st"> 
								
								  <input type="hidden" name="idEntidadcdp"  id='idEntidadcdp<c:out value="${st.count}" />'  value='<c:out value="${i}"/>'>
								
							</c:forEach>	
					</c:if>
			</c:forEach>
  
  </tr>
  
  <tr>  <td align="center" colspan="4">
                  <img src='<c:url value="/comp/img/Registrar.gif"/>' onclick='preguntaReembolso()'>
                  <img src='<c:url value="/comp/img/Cancelar.gif"/>' onclick='cancelar()'>
        </td>
  </tr>
  
</table>
</form>


		



</body>
</html>