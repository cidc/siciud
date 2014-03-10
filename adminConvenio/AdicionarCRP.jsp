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
        <table width="95%" align="center" class="tablas">
			<caption>CDP asociado</caption>
			<tr><td class="renglones" width="115px"><b>CDP: </b></td> 
			    <td class="renglones" width="115px"><b>Valor: </b></td>
			<tr/> 
			<tr>
			<td width="115px"><c:out value="${requestScope.nombrecdp}"/></td>
			<td width="115px"><c:out value="${requestScope.valortotal}"/></td>
		
			
			</tr>
		</table>
		<table width="80%" align="center"> <tr><td align="center" colspan="3"><img src='<c:url value="/comp/img/Nuevogasto.gif"/>' onclick='nuevo()'> </td></tr>
</table>
<br>
<form name="form3" method="post" action='<c:url value="/proyectosAntiguos/Llenar3.jsp" />'>
<table id="nuevo" class="tablas" align = "center" width="80%" style="display: none">
<CAPTION>Nuevo CRP</CAPTION>
  <tr> <td colspan="3"> <input type="hidden" name="validar"> </td></tr>
  <tr> <td class="renglones" width="115px"><b>Fecha: </b></td>
       <td class="renglones"><b>Valor: </b></td>
       <td class="renglones"><b>Rubro:</b></td>
       <td class="renglones" id="ti_tipo" style="display: none"><b>Tipo:</b></td>
  </tr>
  <tr> <td><input type="text" name="fecha" size="11"  class="caj" readonly="true" id="f_date">
       <button type='button' id="id_fecha">...</button></td>
       <script type="text/javascript">
 			Calendar.setup({
   			inputField     :    'f_date',
   			ifFormat       :    '%d/%m/%Y',
   			showsTime      :    false,
   			button         :    'id_fecha',
   			singleClick    :    false,
   			step           :    1
   			})
	  </script>
	  <td><input type="text" maxlength="9" name="valor" onkeypress="return numeros(event)" size="10"></td>
	  <td>  <select name="rubro" style="width: 205px" onchange="combos()">
               <option value="0">---------------------------</option>
               <c:forEach begin = "0" items="${requestScope.rubros}" var="rub">
                          <option value='<c:out value="${rub.codigo}"/>'><c:out value="${rub.nombre}"/> </option>
               </c:forEach>
            </select>
	  </td>
	  <td  id="select1" style="display: none">  <select name="tipo" style="width: 100%" id="tipoc" onchange="comboinventario(this)">
	           <option value="5">---------------</option>
               <option value="1">Contrataciones</option>
               <option value="2">Inventario</option>
               <option value="3">Fungibles</option>
               <option value="4">Avances</option>
            </select>
	  </td>
  </tr>
  <tr> <td class="renglones" colspan="4"><b>Descripcion: </b></td> </tr>
  <tr> <td colspan="4"><textarea name="descripcion" style="width: 99%" class="texto"></textarea></td></tr>
  <tr> <td class="renglones" colspan="4"><b>Observacion: </b></td> </tr>
  <tr> <td colspan="4"><textarea name="observ" style="width: 99%" class="texto"></textarea></td></tr>

  <tr id="tab_inventario" style="display: none"><td colspan="4">
  <table class="tablas" width="100%">
  <caption>Inventario</caption>
      <tr><td></td></tr>
      <tr>   <td class="renglones" width="65px"><b>Placa:</b></td>
             <td class="renglones"><b>Marca:</b></td>
             <td class="renglones"><b>Serie:</b></td>
      </tr>
      <tr>  <td><input type="text" maxlength="9" name="placa" size="10" id="idplaca"></td>
            <td><input type="text" name="marca" style="width: 95%" id="idmarca"></td>
            <td><input type="text" name="serie" style="width: 95%" id="serie"></td>
      </tr>
      <tr> <td class="renglones" colspan="3"><b>Observación:</b></td> </tr>
     <tr> <td colspan="3"><textarea name="observacion" style="width: 99%" id="idobservacion"></textarea></td></tr>
  </table>
  </td></tr>
  <tr>  <td align="center" colspan="4">
                  <img src='<c:url value="/comp/img/Registrar.gif"/>' onclick='crear()'>
                  <img src='<c:url value="/comp/img/Cancelar.gif"/>' onclick='cancelar()'>
       </td>
  </tr>
</table>
</form>
</body>
</html>