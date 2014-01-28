<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<jsp:useBean id="anoHoy" class="java.util.Date" />
<fmt:formatDate value="${anoHoy}" type="time" timeStyle="short"
	pattern="yyyy" var="ano" />
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp" />
<script>

var nav4=window.Event ? true : false;

	function soloNumeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=8 ||key<=7 ||key<=13 || (key>=48 && key<=57));
	}
	function enviar(){
		
		document.frmConvenio.action='<c:url value="/adminConvenio/llenar.jsp"/>';
		if(validarFormulario(document.frmConvenio))
			document.frmConvenio.submit();
	}
	function ajaxFacultad(select)
	{
     document.frmAjax.dato.value = select.value;
     document.frmAjax.para.value = 1;
     document.frmAjax.target="frameOculto";
     document.frmAjax.submit();
    
	}
	function ajaxGrupo(select)
	{
     document.frmAjax.dato.value = select.value;
     document.frmAjax.para.value = 2;
     document.frmAjax.target="frameOculto";
     document.frmAjax.submit();
	}

	function validarFormulario(forma){
		if(forma.nombreConvenio.value==""){
			alert("El nombre del convenio no puede estar vacio");
			return false;
		}
		if(forma.codigo.value==""){
			alert("El numero del convenio no puede estar vacio");
			return false;
		}
		if(forma.numDisp.value==""){
			alert("El numero de disponibilidad del convenio no puede estar vacio");
			return false;
		}
		
		return true;
	}
	function mostrar(combo)
	{for(var i=1; i<=15; i++)
	 {document.getElementById("tr_a"+i).style.display='none';
      document.getElementById("tr_a"+i).disabled=true;
      document.getElementById("tr_b"+i).style.display='none';
      document.getElementById("tr_b"+i).disabled=true;
      //document.getElementById("txt_nom_coinv"+i).disabled=true;
      //document.getElementById("txt_apell_coinv"+i).disabled=true;
      document.getElementById("txt_tipo_coinv"+i).disabled=true;
      
      
     }
     if(combo.selectedIndex!=0)
     {for(var i=1; i<=combo.value; i++)
      {document.getElementById("tr_a"+i).style.display='';
       document.getElementById("tr_a"+i).disabled=false;
       document.getElementById("tr_b"+i).style.display='';
       document.getElementById("tr_b"+i).disabled=false;
       //document.getElementById("txt_nom_coinv"+i).disabled=false;
       //document.getElementById("txt_apell_coinv"+i).disabled=false;
       document.getElementById("txt_tipo_coinv"+i).disabled=false;
      }
     }
	}
	
	

	
	
	
	
	
	
	

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form action='<c:url value="/adminConvenio/AdminConvenio.x"/>'>
<input type="hidden" name="accion" value="2">
<input type="image" src='<c:url value="/comp/img/Buscar.gif" />'>
</form>
<form name="frmConvenio">
<input type="hidden" name="accion"	value='<c:out value="${requestScope.accion}" default="1"/>'>
<input type="hidden" name="idConvenio" value='<c:out value="${requestScope.datoConvenio.idconvenio}"/>'>
<table class="tablas" align="center">
	<caption>Registro datos generales de convenio</caption>
	<tr>
		<td class="renglones"><b>Número Convenio</b></td>
		<td><input type="text" name="codigo" style="text-align: right;" onKeyPress='return soloNumeros(event)' value='<c:out value="${requestScope.datoConvenio.codigo}" />'></td>
		
		<%--<td class="renglones" ><b>Ano</b></td>
		<td><select name="ano">
			<option value="0">----</option>
			<c:forEach begin="1985" end="2012" var="ano" >
				<option value='<c:out value="${ano}" />' <c:if test="${requestScope.datoConvenio.ano==ano}">selected</c:if>><c:out value="${ano}" /></option>
			</c:forEach>
		</select></td>--%>
		
		<td class="renglones"><b>Número disponibilidad</b></td>
		<td><input type="text" name="numDisp" style="text-align: right;" onKeyPress='return soloNumeros(event)' value='<c:out value="${requestScope.datoConvenio.numDisp}" />'></td>
		
		<tr>
		<td class="renglones"><b>Estado</b></td>
		<td><select name="estado">
			<option value="1" <c:if test="${requestScope.datoConvenio.estado==1}">selected</c:if>>Vigente</option>
			<option value="2" <c:if test="${requestScope.datoConvenio.estado==2}">selected</c:if>>Indefinido</option>
			<option value="3" <c:if test="${requestScope.datoConvenio.estado==3}">selected</c:if>>Terminado</option>
			<option value="4" <c:if test="${requestScope.datoConvenio.estado==4}">selected</c:if>>Cancelado</option>
		</select></td>
		
		<td class="renglones"><b>Tipo</b></td>
		<td><select name="tipo">
			<option value="1" <c:if test="${requestScope.datoConvenio.tipo==1}">selected</c:if>>Vigente</option>
			<option value="2" <c:if test="${requestScope.datoConvenio.tipo==2}">selected</c:if>>Indefinido</option>
			<option value="3" <c:if test="${requestScope.datoConvenio.tipo==3}">selected</c:if>>Terminado</option>
			<option value="4" <c:if test="${requestScope.datoConvenio.tipo==4}">selected</c:if>>Cancelado</option>
		</select></td>
		
		<td class="renglones"><b>Fecha de registro</b></td>
				<td><input type='text' name='fecha' class='caj' readonly='true' id='f_date_a' size='13' value='<c:out value="${requestScope.datoConvenio.fecha}" />'>
				<button type='button' id='f_trigger_a'>...</button>
				<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_a',
					    			ifFormat       :    '%d/%m/%Y',
					    			showsTime      :    false,
					    			button         :    'f_trigger_a',
					    			singleClick    :    false,
					    			step           :    1
				    			})
			    </script></td>
			    
		
		</tr>
		
		
		

	<tr>
		<td class="renglones" colspan="6"><b>Nombre convenio</b></td>
	</tr>
	<tr>
		<td class="renglones" colspan="6"><input type="text" name="nombreConvenio" style="width:100%" value='<c:out value="${requestScope.datoConvenio.nombreConvenio}"/>'></td>
	</tr>
	
	<tr>
		<td class="renglones" colspan="6"><b>Suma valor efectivo $</b></td>
	</tr>
	<tr>
		<td class="renglones" colspan="6"><input type="text" name="VEfectivo" style="width:100%"   value='<c:out value="${requestScope.datoConvenio.VEfectivo}" />'></td>
	</tr>
	
	<tr>
		<td class="renglones" colspan="6"><b>Suma valor especie $</b></td>
	</tr>
	<tr>
		<td class="renglones" colspan="6"><input type="text" name="VEspecie" style="width:100%"   value='<c:out value="${requestScope.datoConvenio.VEspecie}" />'></td>
	</tr>
	
	<%--<tr>
		<td class="renglones" colspan="6"><b>Entidades que intervienen</b></td>
	</tr>
	<tr>
		<td class="renglones" colspan="6"><input type="text" name="entidades" style="width:100%" value='<c:out value="${requestScope.datoConvenio.entidades}" />'></td>
	</tr>--%>
	<tr>
		
		
			
				<td class="renglones"  style="width: 200px; "><b>Fecha de Inicio</b></td>
				<td><input type='text' name='fechaInicio' class='caj' readonly='true' id='f_date_i' size='13' value='<c:out value="${requestScope.datoConvenio.fechaInicio}" />'>
				<button type='button' id='f_trigger_i'>...</button>
				<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_i',
					    			ifFormat       :    '%d/%m/%Y',
					    			showsTime      :    false,
					    			button         :    'f_trigger_i',
					    			singleClick    :    false,
					    			step           :    1
				    			})
			    </script></td>
			    			
			    <td class="renglones" style="width: 179px; "><b>Fecha de finalizacion</b></td>
				<td style="width: 189px; "><input type='text' name='fechaFinalizacion' class='caj' readonly='true' id='f_date_f' size='13' value='<c:out value="${requestScope.datoConvenio.fechaFinalizacion}" />'>
				<button type='button' id='f_trigger_f'>...</button>
				<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_f',
					    			ifFormat       :    '%d/%m/%Y',
					    			showsTime      :    false,
					    			button         :    'f_trigger_f',
					    			singleClick    :    false,
					    			step           :    1
				    			})
			    </script></td>
			    						
				
		
			
		
		
		
	</tr>
	
	
	
	
	
	
	
	
	
	
	
	<%--<tr> <td colspan="6"> <table width="100%">
               <tr> <td class="renglones"><b>Entidades vinculadas</b></td>
                    <td class="renglones"><b>Seleccione Cantidad:</b> <select name="cant_coinv" style="width: 30%" onchange="mostrar(this)">
                                                                      <option value="0">--</option>
                                                                      <option value="1">1</option>
                                                                      <option value="2">2</option>
                                                                      <option value="3">3</option>
                                                                      <option value="4">4</option>
                                                                      <option value="5">5</option>
                                                                      <option value="6">6</option>
                                                                      <option value="7">7</option>
                                                                      <option value="8">8</option>
                                                                      <option value="9">9</option>
                                                                      <option value="10">10</option>
                                                                      <option value="11">11</option>
                                                                      <option value="12">12</option>
                                                                      <option value="13">13</option>
                                                                      <option value="14">14</option>
                                                                      <option value="15">15</option>
                                                                      </select>
                    </td>
               </tr>
     </table> </td> </tr>
     --%>
     
     <%--<tr> <td colspan="6">
              <table width="100%">
              <c:forEach begin="1" end="15" varStatus="i">
               <tr id='tr_a<c:out value="${i.count}" />'> 
                  <%--<td class="renglones">
                   <b>Nombres:</b></td>
                   <td class="renglones"><b>Apellidos:</b></td>
                   <td class="renglones"><b>Nombre</b></td>
               </tr>
               <tr id='tr_b<c:out value="${i.count}" />'> 
                   <%--<td><input type="text"name="nomCoinvest" style="width: 90%" id='txt_nom_coinv<c:out value="${i.count}" />'></td>
                   <td> <input type="text" name="apellCoinvest" style="width: 90%" id='txt_apell_coinv<c:out value="${i.count}" />'></td>
                   <td> <select name="tipoInv" style="width: 100%" id='txt_tipo_coinv<c:out value="${i.count}" />'>
          			    <option value="1">Coinvestigador</option>
          		        <option value="2">Auxiliar</option>
	          	        </select>
                   </td>
               </tr>
              </c:forEach>
              </table>
     </td> </tr>
     --%>
     <tr>
		<td colspan="6" class="renglones"><b>Observaciones</b></td>
	</tr>
	<tr>
		<td colspan="6"><textarea name="observaciones" style="width: 100%">--<c:out value="${requestScope.datoConvenio.observaciones}"/></textarea></td>
	</tr>
	
	
	
	<tr>
		<td class="renglones" align="center" colspan="6"><b>REGISTRO PROYECTO DE INVESTIGACION AL CONVENIO</b></td>
	</tr>
	<tr>
		<td class="renglones" colspan="6"><b>Nombre Proyecto</b></td>
	</tr>
	<tr>
		<td class="renglones" colspan="6"><input type="text" name="nombreproyecto" style="width:100%" value='<c:out value="${requestScope.datoConvenio.nombreproyecto}"/>'></td>
	</tr>
	<tr>
    <td class="renglones"><b>Estado Proyecto</b></td>
		<td><select name="estadop">
			<option value="1" <c:if test="${requestScope.datoConvenio.estadop==1}">selected</c:if>>Vigente</option>
			<option value="2" <c:if test="${requestScope.datoConvenio.estadop==2}">selected</c:if>>Indefinido</option>
			<option value="3" <c:if test="${requestScope.datoConvenio.estadop==3}">selected</c:if>>Terminado</option>
			<option value="4" <c:if test="${requestScope.datoConvenio.estadop==4}">selected</c:if>>Cancelado</option>
		</select></td>
		
		<td class="renglones"><b>Tipo Proyecto</b></td>
		<td><select name="tipop">
			<option value="1" <c:if test="${requestScope.datoConvenio.tipop==1}">selected</c:if>>Vigente</option>
			<option value="2" <c:if test="${requestScope.datoConvenio.tipop==2}">selected</c:if>>Indefinido</option>
			<option value="3" <c:if test="${requestScope.datoConvenio.tipop==3}">selected</c:if>>Terminado</option>
			<option value="4" <c:if test="${requestScope.datoConvenio.tipop==4}">selected</c:if>>Cancelado</option>
		</select></td>
		
	</tr>
	<tr> <td class="renglones" colspan="6"><b>Facultad:</b></td></tr>
     <tr> <td colspan="6"> <select name="facultad" style="width: 100%" onchange="ajaxFacultad(this)">
               <option value="0">-----------------------------------------------</option>
               <option value="1">Facultad Tecnológica</option>
               <option value="2">Facultad de Ingenieria</option>
               <option value="3">Facultad de Medio Ambiente y recursos Naturales</option>
               <option value="4">Facultad de Ciencias y Educación</option>
               <option value="5">ASAB</option>
               </select>
          </td>
     </tr>
	<tr> <td class="renglones" colspan="6"><b>Proyecto Curricular:</b></td></rt>
     <tr> <td colspan="6"> <select name="proycurri" style="width: 100%">
                           <option value="0">---------------------</option>
                           </select>
          </td>
     </tr>
     <tr> <td class="renglones" colspan="6"><b>Grupo/Semillero:</b></td></tr>
     <tr> <td colspan="6"> <select name="grupo" style="width: 100%" onchange="ajaxGrupo(this)" >
               <option value="0">-----------</option>
               </select>
          </td>
     </tr>
     
     <%-- <tr> <td class="renglones" colspan="6"><b>Personas vinculadas:</b></td></tr>
     <tr> <td colspan="6"> <select name="grupo" style="width: 100%" onchange="ajaxGrupo(this)">
               <option value="0">-----------</option>
               </select>
          </td>
     </tr>
     --%>
		
	<tr> <td class="renglones" colspan="6"><b>Objetivo General:</b></td></tr>
    <tr> <td colspan="6"><textarea class="texto" name="objetivo" style="width: 99%">--<c:out value="${requestScope.datoConvenio.objetivo}"/></textarea></td></tr>
    <tr> <td class="renglones" colspan="6"><b>Abstract/Resumen:</b></td></tr>
    <tr> <td colspan="6"><textarea class="texto" name="resumen" style="width: 99%">--<c:out value="${requestScope.datoConvenio.resumen}"/></textarea></td></tr>
    
    <tr>
		<td colspan="6" class="renglones"><b>Observaciones</b></td>
	</tr>
	<tr>
		<td colspan="6"><textarea name="observacionesp" style="width: 100%">--<c:out value="${requestScope.datoConvenio.observacionesp}"/></textarea></td>
	</tr>
     
	
	
	
	<tr>
		<td colspan="6" align="center"><img src=<c:url value="/comp/img/Guardar.gif" /> onclick="enviar()"></td>
	</tr>
</table>
</form>

<table>
		<tr>
			<td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td>
		</tr>
	</table>
	<form method="post" name="frmAjax" action="<c:url value="/adminConvenio/Ajax.x"/>">
		<input type="hidden" name="dato" value=''>
		<input type="hidden" name="para" value=''>
	</form>
	
	
<script>
//mostrar(document.frmConvenio.cant_coinv);
  ajaxFacultad(document.frmConvenio.facultad);
</script>
</body>
</html>