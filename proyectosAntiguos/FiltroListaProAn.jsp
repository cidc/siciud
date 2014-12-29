<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:import url="/general.jsp"/>
<head>
<script>

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

		function buscar(){
			document.form1.submit();
		}

		function enviar(id){
            document.form2.idProyecto.value=id;
			document.form2.submit();
		}

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="form1" method="post" action='<c:url value="/proyectosAntiguos/Llenar1.jsp"/>'>
		<input type="hidden" name="validar" value="3"/>
	<br>
	<table class="tablas" align="center" width="90%">
	<CAPTION>Filtro de consulta</CAPTION>
		<tr>
			<th>Facultad:</th>
			<th width="130px">Código:</th>
			<th>Estado:</th>
			 <th>Tipo:</th>
		</tr>
		<tr>
			<td>
				<select name="facultad" style="width: 100%" onchange="ajaxFacultad(this)" >
					<option value="0">------------</option>
					<option value="1">Tecnológica</option>
					<option value="2">Ingeniería</option>
					<option value="3">Medio Ambiente</option>
					<option value="4">Educación</option>
					<option value="5">Asab</option>
				</select>
			</td>
			<td align="center">
				<input type="text" name="codigo" style="width: 100%">
			</td>
			<td>
	        	<select name="estado" style="width: 100%">
	                  <option value="0">------------------</option>
	                  <option value="1" >En Trámite</option>
                       <option value="2" >Vigente</option>
                       <option value="3" >Finalizado</option>
                       <option value="4" >Cancelado</option>
                       <option value="5" >En Prueba</option>
                       <option value="6" >Suspendido</option>
                       <option value="7" >Proc. Finalización</option>
                       <option value="8" >Crítico</option>
                       <option value="9" >Plazo Adicional</option>
	             </select>
	        </td>
	         <td>
	         	<select name="tipo" style="width: 100%">
                  <option value="">--------</option>
                  <option value="2">Convenio</option>
                  <option value="1">Convocatoria</option>
             	 </select>
	          </td>
		</tr>
		<tr>
			<th colspan="4">Proyecto Curricular:</th>
		</rt>
    	<tr>
    		<td colspan="4">
    			<select name="proycurri" style="width: 100%">
                      <option value="0">---------------------</option>
                </select>
          	</td>
    	 </tr>
	     <tr>
	     	<th colspan="4">Grupo/Semillero:</th>
	     </tr>
	     <tr>
	     	<td colspan="4">
	     		<select name="grupo" style="width: 100%" >
	               <option value="0">-----------</option>
	            </select>
	        </td>
	     </tr>
	     <tr>
	     	<th colspan="4">Nombre Proyecto:</th></tr>
	     <tr>
	     	<td colspan="4">
		     	<input type="text" name="nombre" style="width: 100%">
	     	</td>
	     </tr>
     <tr>
	     <td align="center" colspan="4"><input type="image" src='<c:url value="/comp/img/Buscar.gif"/>' onclick="buscar()"></td></tr>
	</table>
	</form>
<br>

<c:if test="${empty sessionScope.lista}">
	<br>
		<h5 align="center">Esta consulta no tiene registros</h5>
	</c:if>

<c:if test="${!empty sessionScope.lista}">
<form name="form2" method="post" action='<c:url value="/proyectosAntiguos/GestProyectos.x" />'>

<input name="idProyecto" type="hidden" value="1">
<input name="validar" type="hidden" value="4">
<table id="lista" class="tablas" align="center" width="95%" >
<CAPTION>Lista de Proyectos Antiguos</CAPTION>
<tr>  <th align="center"><b>#</b></th>
	  
	  <th align="center"><b>Código</b></th>
      <th width="190px" align="center"><b>Director</b></th>
      <th align="center"><b>Nombre de Proyecto</b></th>
      <th align="center"><b>Ver</b></th>
</tr>
<c:forEach begin = "0" items = "${sessionScope.lista}" var = "proy" varStatus="st">
  <tr <c:if test="${st.count mod 2 == 0}">class="trb"</c:if>>
  	   <td class="renglones"><b><c:out value = "${st.count}"/></b></td>
	   
	   <td><c:out value = "${proy.codigo}"/></td>
       <td><c:out value = "${proy.nombreInvestigador}"/></td>
       <td><c:out value = "${proy.nombre}"/></td>
       <td align="center" width="30px"><img src='<c:url value="/comp/img/VerProy.gif"/>' onclick='enviar(<c:out value="${proy.id}"/>)'> </td>
 </tr>
 </c:forEach>
</table>
</form>
</c:if>

<table>
<tr> <td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td></tr>
</table>
<form method="post" name="frmAjax" action="<c:url value="/proyectosAntiguos/Ajax.x"/>">
     <input type="hidden" name="dato" value=''>
	 <input type="hidden" name="para" value=''>
</form>
</body>
</html>