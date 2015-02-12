<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<jsp:useBean id="anoHoy" class="java.util.Date" />
<fmt:formatDate value="${anoHoy}" type="time" timeStyle="short"
	pattern="yyyy" var="ano" />
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
			
		if(validarCombos()==true){
			
			if(validarFormulario(document.frmConvenio)){
			document.frmConvenio.action='<c:url value="/adminConvenio/llenar.jsp"/>';
			document.frmConvenio.idgrupo.value=document.frmAjax.dato.value;
			document.frmConvenio.submit();
			}
		}
	}
	function ajaxFacultad(select)
	{
     document.frmAjax.dato.value = select.value;
     document.frmAjax.para.value = 1;
     document.frmAjax.remove.value = 0;
     document.frmAjax.target="frameOculto";
     document.frmAjax.submit();
    
	}
	function ajaxGrupo(select)
	{
     document.frmAjax.dato.value = select.value;
     document.frmAjax.para.value = 2;
     document.frmAjax.remove.value = 0;
     document.frmAjax.target="frameOculto";
     document.frmAjax.submit();
	}

	function validarFormulario(forma){
		if(forma.codigo.value==""){
			alert("El Codigo del Convenio no puede estar vacio");
			return false;
		}
		
		if(forma.nombreConvenio.value==""){
			alert("El nombre del convenio no puede estar vacio");
			return false;
		}
// 		if(forma.fecha.value==""){
// 			alert("La fecha de registro del convenio no puede estar vacio");
// 			return false;
// 		}
		if(forma.fechaInicio.value==""){
			alert("La fecha de Inicio del convenio no puede estar vacio");
			return false;
		}
		if(forma.duracion.value<=1)
		   {  alert("La duración del proyecto no puede ser menor a 6 meses");
			return false;
		   }
		if(forma.VAprobado.value<=1)
		   {  alert("La el valor del convenio no puede ser menor a 1");
			return false;
		   }
		if(forma.nombreproyecto.value==""){
			alert("El nombre del proyecto no puede estar vacio");
			return false;
		}
		if(forma.objetivo.value==""){
			alert("El objetivo del proyecto no puede estar vacio");
			return false;
		}
		return true;
	}
	
	
	   function validarCombos()
	   {
        if(document.frmConvenio.estado.selectedIndex==0)
        {alert("Debe seleccionar el estado del convenio"); return false;}
        else{if(document.frmConvenio.tipo.selectedIndex==0)
              {alert("Debe selecionar el tipo del convenio"); return false;}
        	else {if(document.frmConvenio.facultad.selectedIndex==0) 
              		{alert("Debe seleccionar la facultad del proyecto"); return false;}
        		else{if(document.frmConvenio.grupo.selectedIndex==0)
                             {alert("Debe seleccionar el Semillero o Grupo de Investigación del proyecto"); return false;}
        				else{if(document.frmConvenio.investigador.selectedIndex==0)
                             {alert("Debe seleccionar el Director"); return false;}
        						else{ return true;}
        				}
                    }                  
             	}
	   		}
	  }
	
	
	
	function mostrar(combo)
	{for(var i=1; i<=15; i++)
	 {document.getElementById("tr_a"+i).style.display='none';
      document.getElementById("tr_a"+i).disabled=true;
      document.getElementById("tr_b"+i).style.display='none';
      document.getElementById("tr_b"+i).disabled=true;
      document.getElementById("txt_tipo_coinv"+i).disabled=true;
      
      
     }
     if(combo.selectedIndex!=0)
     {for(var i=1; i<=combo.value; i++)
      {document.getElementById("tr_a"+i).style.display='';
       document.getElementById("tr_a"+i).disabled=false;
       document.getElementById("tr_b"+i).style.display='';
       document.getElementById("tr_b"+i).disabled=false;
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
<input type="hidden" name="idgrupo" value="">
<table class="tablas" align="center">
	<caption>Registro datos generales de convenio</caption>
	<tr>
		<td class="renglones"><b>Número Convenio</b></td>
		<td><input type="text" name="codigo" style="text-align: right;" onKeyPress='return soloNumeros(event)' value='<c:out value="${requestScope.datoConvenio.codigo}" />'></td>
		
		<td class="renglones"><b>Número disponibilidad</b></td>
		<td><input type="text" name="numDisp" style="text-align: right;" onKeyPress='return soloNumeros(event)' value='<c:out value="${requestScope.datoConvenio.numDisp}" />'></td>
		
		<tr>
		
		<td class="renglones"><b>Tipo</b></td>
		<td><select name="tipo">
			<option value="0" <c:if test="${requestScope.datoConvenio.tipo==0}">selected</c:if>>------------</option>
			<option value="1" <c:if test="${requestScope.datoConvenio.tipo==1}">selected</c:if>>Marco</option>
			<option value="2" <c:if test="${requestScope.datoConvenio.tipo==2}">selected</c:if>>Especifico</option>
			<option value="3" <c:if test="${requestScope.datoConvenio.tipo==3}">selected</c:if>>Acuerdo de cooperación</option>
			<option value="4" <c:if test="${requestScope.datoConvenio.tipo==4}">selected</c:if>>Contrato</option>
			<option value="5" <c:if test="${requestScope.datoConvenio.tipo==5}">selected</c:if>>Inter-Administrativo</option>
		</select></td>
		
		
		
		
		<td class="renglones"><b>Estado</b></td>
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
		
		
		
		<td class="renglones"><b>Fecha de registro</b></td>
				<td><input type='text' name='fecha' class='caj' readonly='true' id='f_date_a' size='13' value='<c:out value="${requestScope.datoConvenio.fecha}" />'>
				<button type='button' id='f_trigger_a'>...</button>
				<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_a',
					    			ifFormat       :    '%Y-%m-%d',
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
		
		
			
				<td class="renglones"  style="width: 200px; "><b>Fecha de Inicio</b></td>
				<td><input type='text' name='fechaInicio' class='caj' readonly='true' id='f_date_i' size='13' value='<c:out value="${requestScope.datoConvenio.fechaInicio}" />'>
				<button type='button' id='f_trigger_i'>...</button>
				<script type='text/javascript'>
				    			Calendar.setup({
					    			inputField     :    'f_date_i',
					    			ifFormat       :    '%Y-%m-%d',
					    			showsTime      :    false,
					    			button         :    'f_trigger_i',
					    			singleClick    :    false,
					    			step           :    1
				    			})
			    </script></td>
			    <td class="renglones" style="width: 179px; "><b>Duracion Meses</b></td>
				<td style="width: 100px; "><input type="text" maxlength="3" name="duracion" style="text-align:right; width:80%" value="0" onkeypress="return numeros(event)" size="2">
				</td>
				 <td class="renglones" style="width: 179px; "><b>Valor Aprobado:</b></td>
				<td><input type="text" maxlength="10" name="VAprobado" style="text-align:right; width: 90%" value="0" onkeypress="return numeros(event)"></td>
				</td>
				
				
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
			<option value="0" <c:if test="${requestScope.datoConvenio.estadop==0}">selected</c:if>>------------</option>
			<option value="1" <c:if test="${requestScope.datoConvenio.estadop==1}">selected</c:if>>Vigente</option>
			<option value="2" <c:if test="${requestScope.datoConvenio.estadop==2}">selected</c:if>>Indefinido</option>
			<option value="3" <c:if test="${requestScope.datoConvenio.estadop==3}">selected</c:if>>Terminado</option>
			<option value="4" <c:if test="${requestScope.datoConvenio.estadop==4}">selected</c:if>>Cancelado</option>
		</select></td>
			<input type="hidden" name="tipop" style="width:100%"   value='0'> 
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
     
     <tr> <td class="renglones" colspan="6"><b>Investigador Principal:</b></td></tr>
     <tr> <td colspan="6"> <select name="investigador" style="width: 100%">
               <option value="0">------------------------</option>
               </select>
          </td>
     </tr>
     
  
	<tr> <td class="renglones" colspan="6"><b>Objetivo General:</b></td></tr>
    <tr> <td colspan="6"><textarea class="texto" name="objetivo" style="width: 99%"><c:out value="${requestScope.datoConvenio.objetivo}"/></textarea></td></tr>
    <tr> <td class="renglones" colspan="6"><b>Abstract/Resumen:</b></td></tr>
    <tr> <td colspan="6"><textarea class="texto" name="resumen" style="width: 99%">--<c:out value="${requestScope.datoConvenio.resumen}"/></textarea></td></tr>
    
     <tr>
		<td colspan="6" class="renglones"><b>Observaciones</b></td>
	</tr>
	<tr>
		<td colspan="6"><textarea name="observaciones" style="width: 100%">--<c:out value="${requestScope.datoConvenio.observaciones}"/></textarea></td>
	</tr>
	
	
	<tr>
		<td colspan="6" align="center"><img src=<c:url value="/comp/img/Guardar.gif" /> onclick="enviar()"></td>
	</tr>
</table>

		<input type="hidden" name="VEfectivo" style="width:100%"   value='0'> 
		<input type="hidden" name="VEspecie" style="width:100%"   value='0'>

</form>

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
	
	
<script>
//mostrar(document.frmConvenio.cant_coinv);
  ajaxFacultad(document.frmConvenio.facultad);
</script>
</body>
</html>