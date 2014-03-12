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
function nuevo(){
    document.getElementById("nuevo").style.display='';
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
function pregunta(){

	if(crear()==true){
		alert("entro");
		
		
		var sumatoria=parseInt(document.formcrpnuevo.sumatoria.value);
		var efectivo=parseInt(document.formcrpnuevo.valorcrp.value);
		var tol=parseInt(document.formcrpnuevo.valor.value);
		
		alert("sumatoria"+sumatoria);
		if((efectivo+sumatoria)<=tol){
			alert("Exito");	
			document.formcrpnuevo.submit();
		}else{
			alert("El total Efectivo no debe ser mayor al Total Aprobado");	
			alert("El valor del aporte debe ser menor: $"+(tol-sumatoria));
			}
		
		
	}
	
	
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
			<td><img border="0" src='<c:url value="/comp/img/convenio/GruposInvClick.gif"/>'></a></td>

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

<tr> <td colspan="4"> <table class="tablas" width="100%">
          <tr>  <td class="renglones" width="115px"><b>Total Aprobado: </b></td>
                <td class="renglones" width="130px"><b>Por Comprometer: </b></td>
                <td class="renglones" width="100px"><b>Comprometido: </b></td>
         </tr>
         <tr> <td align="right"><c:out value = "${proyectos.presupuesto}"/></td>
              <td align="right" width="130px"><c:out value = "${proyectos.ejecutado}"/></td>
              <td align="right"><c:out value = "${proyectos.saldo}"/></td>
         </tr>
          </table>
     </td>
</tr>
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
		<table width="80%" align="center"> <tr><td align="center" colspan="3"><img src='<c:url value="/comp/img/Nuevogasto.gif"/>' onclick='nuevo()'> </td></tr>
        </table>
        <c:set var="numero"  value="0" />
<c:if test="${!empty sessionScope.datoConvenio.listacrpsConv}">
<form name="formcargacrp" method="post" action='<c:url value="/adminConvenio/AdminConvenio.x"/>'>
<input type="hidden" name="accion" value="20">


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
<form name="formcrpnuevo" method="post" action='<c:url value="/adminConvenio/AdminConvenio.x"/>'>
<input type="hidden" name="accion" value="20">
<input type="hidden" name="idcdp" value="<c:out value="${requestScope.idcdp}"/>">

<input type="hidden" name="nombrecdp" value="<c:out value="${requestScope.nombrecdp}"/>">
<input type="hidden" name="valor" value="<c:out value="${requestScope.valortotal}"/>">
<input type="hidden" name="sumatoria" value="${numero}">

<table id="nuevo" class="tablas" align = "center" width="80%" style="display: none">
<CAPTION>Nuevo CRP</CAPTION>

  <tr> <td class="renglones" width="115px"><b>CRP: </b></td>
       <td class="renglones"><b>Codigo: </b></td>
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
			  <b><input type="text"  name="valorcrp" ></b>
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
</body>
</html>