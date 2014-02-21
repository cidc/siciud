<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<title>Insert title here</title>
</head>
<body>
<c:if test="${sessionScope.datoConvenio!=null}">
<br>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><img src='<c:url value="/comp/img/convenio/ProyectosClick.gif"/>'></td>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/convenio/Documentos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Tiempos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Grupos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/GruposInv.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/EntidadConvenio.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Entidades.gif"/>'></a></td>
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

<tr>  <td class="renglones" colspan="5"><b>Nombre Proyecto:</b></td> </tr>
<tr>  <td colspan="5"><c:out value = "${proyectos.nombre}"/></td></tr>

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



<table class="tablas" align = "center" width="80%">
<caption>lista de CDP</caption>
	<tr>
		<th><b>CDP(Descripcion)</b></th>
		<th><b>Codigo</b></th>
		<th><b>Fecha</b></th>
		<th><b>Medio de pago</b></th>
		<th><b>Observacion</b></th>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      </b></th>
	</tr>
	<c:forEach items="${requestScope.listaRubrosAprobados}" var="lista" varStatus="st">
	<tr <c:if test="${(st.count mod 2) ==0}">class="trb"</c:if>>
		<td><c:out value="${lista.nombre}"/></td>
		<td width="80px" align="right" ><span style="text-align: right;" id="rub<c:out value="${lista.codigo}"/>"><c:out value="${lista.valor}"/></span></td>
	</tr>
	</c:forEach>
</table>
<br>

<form name="formcdp" method="post" action='<c:url value="/proyectosAntiguos/Llenar3.jsp" />'>
<table id="nuevo" class="tablas" align = "center" width="80%" >
<CAPTION>Nuevo CDP</CAPTION>
  <tr> <td colspan="3"> <input type="hidden" name="validar"> </td></tr>
  
  <tr> <td class="renglones" colspan="6"><b>CDP(Descripcion):</b></td></tr>
  <tr>
  <td colspan="6"><textarea name="observaciones" style="width: 100%">--<c:out value="${requestScope.datoConvenio.observaciones}"/></textarea></td>
  </tr>
   <td class="renglones" ><b>Codigo: </b></td>
       <td class="renglones" ><b>Fecha:</b></td>
       <td class="renglones" ><b>Medio de pago:</b></td>
       <td class="renglones"><b>Observacion:</b></td>
  <tr>
  <td><input type="text"  name="valor"></td>
  <td><input type="text"  name="codigo"></td>
  
   <td><input type="text" name="fecha" size="11"  class="caj" readonly="true" id="f_date">
       <button type='button' id="id_fecha">...</button>
  </td>
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
	  
	<td><select name="mpago">
			<option value="0" <c:if test="${requestScope.datoConvenio.estadop==0}">selected</c:if>>------------</option>
			<option value="1" <c:if test="${requestScope.datoConvenio.estadop==1}">selected</c:if>>Efectivo</option>
			<option value="2" <c:if test="${requestScope.datoConvenio.estadop==2}">selected</c:if>>Cuotas</option>
			<option value="3" <c:if test="${requestScope.datoConvenio.estadop==3}">selected</c:if>>Otros</option>
	</select>
	</td>
  
	  
	 
  </tr>
  


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




</c:if>
<c:if test="${sessionScope.datoConvenio==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Convenio</h4>
</c:if>

</body>
</html>