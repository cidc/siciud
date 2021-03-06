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
	function enviar(){
		//document.frmConvenio.accion.value=accion;
		document.frmConvenio.idgrupo.value=document.frmAjax.dato.value;
		document.frmConvenio.submit();
	}
	
	function ajaxGrupo(select)
	{
     document.frmAjax.dato.value = select.value;
     document.frmAjax.para.value = 2;
     document.frmAjax.target="frameOculto";
     document.frmAjax.submit();
	}
	
	function ajaxFacultad(select)
	{
     document.frmAjax.dato.value = select.value;
     document.frmAjax.para.value = 1;
     document.frmAjax.remove.value =1;
     document.frmAjax.target="frameOculto";
     document.frmAjax.submit();
     
    
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
			<td><img border="0" src='<c:url value="/comp/img/convenio/GruposInvClick.gif"/>'></td>
			<td><a href='<c:url value="/adminEntidad/GestEntidad.x?validar=3&por=2"/>'><img border="0" src='<c:url value="/comp/img/convenio/Entidades.gif"/>'></a></td>
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
			
			<th width="20%"><b>N�mero Convenio</b></th>
			<th width="20%"><b>Fecha Inicio</b></th>
			<th width="20%"><b>Estimado Fin</b></th>
			<th width="20%"><b>Total Aprobado</b></th>
		</tr>
		<tr>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.codigo}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaInicio}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaFinalizacion}"/></td>
			<td width="20%" align="center">$<c:out value="${sessionScope.datoConvenio.finanza.VAprobado}"/></td>
		</tr>
	</table>
	
	<c:if test="${!empty sessionScope.datoConvenio.listagrupos}">
	<form name="frmPersona"  method="post">
		<input type="hidden" name="accion" value="0">
		<input type="hidden" name="idPersona" value="0">
		<input type="hidden" name="fechaInicio" value="">
		<input type="hidden" name="fechaFin" value="">
		<input type="hidden" name="observacion" value="">
		<table width="95%" align="center" class="tablas">
			<caption>Lista de grupos asociados al proyecto</caption>
			<tr>
				<th width="5px">&nbsp;</th>
			    <th><b>Nombre</b></th>
				
			</tr>
			
			
			<c:forEach begin="0" items="${sessionScope.datoConvenio.listagrupos}" var="lista" varStatus="st">
			<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if> >
				<td width="5px"><c:out value="${st.count}"/></td>
				<td width="100px" align="center"><c:out value="${lista.nombre}"/></td>
<%--				<td width="5px" align="center"><img src='<c:url value="/comp/img/equis2.png"/>' onclick='enviar(<c:out value="${lista.idConvGru}"/>,11,<c:out value="${st.count}"/>)'></td>--%>
<%-- 			<td width="5px" align="center"><img src='<c:url value="/comp/img/ok.png"/>' onclick='enviar(<c:out value="${lista.idPersona}"/>,12,<c:out value="${st.count}"/>)'></td>--%>
			</tr>
		    </c:forEach>
		</table>
	</form>
	
	</c:if>
	<c:if test="${empty sessionScope.datoConvenio.listagrupos}">
	<h3 align="center">No hay m�s grupos en este Convenio</h3>
	</c:if>
	
	<form name="frmConvenio" method="post" action='<c:url value="/adminConvenio/AdminConvenio.x"/>'>
		<input type="hidden" name="accion" value="13">
		<input type="hidden" name="idPersona" value="">
		<input type="hidden" name="idgrupo" value="">
		
		<table align="center" width="95%" class="tablas">
		<caption>Registro de un Grupo asociado </caption>
		<tr> <td class="renglones" colspan="6"><b>Facultad:</b></td></tr>
     <tr> <td colspan="6"> <select name="facultad" style="width: 100%" onchange="ajaxFacultad(this)">
               <option value="0">-----------------------------------------------</option>
               <option value="1">Facultad Tecnol�gica</option>
               <option value="2">Facultad de Ingenieria</option>
               <option value="3">Facultad de Medio Ambiente y recursos Naturales</option>
               <option value="4">Facultad de Ciencias y Educaci�n</option>
               <option value="5">ASAB</option>
               </select>
          </td>
     </tr>
     
		<tr> 
		<td class="renglones" colspan="6"><b>Grupo/Semillero:</b></td>
		</tr>
        <tr> 
        <td colspan="6"> <select name="grupo" style="width: 100%" onchange="ajaxGrupo(this)" >
        <option value="0">-----------</option>
        </select>
        </td>
        </tr>
				
				
				
			
		
		
			<tr>
				<td colspan="6" align="center"><input type="image" src='<c:url value="/comp/img/Enviar.gif"/>' onclick="enviar()"></td>
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
<h4 align="center">No se logr� encontrar la informaci�n del Convenio</h4>
</c:if>
<script>
 
  ajaxFacultad(document.frmConvenio.facultad);
</script>
</body>
</html>