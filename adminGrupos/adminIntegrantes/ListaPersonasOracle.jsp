<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp"/>
<script type="text/javascript">

function acci(acc){
document.filtroConsulta.accion.value=acc;
if(document.filtroConsulta.cedula.value=="" && document.filtroConsulta.codigoUd.value==""){
alert("Debe insertar un valor a consultar")
}else{
document.filtroConsulta.submit();
}
}

function enviar(cc,cod){

   document.lista.cedula.value=cc;
   document.lista.codUD.value=cod;
   document.lista.flagMod.value=0;
   document.lista.submit();
}

function asignar(cc,cod,nombre,apellido){
   parent.document.nuevo.cedDirector.value=cc;
   parent.document.nuevo.codUdDirector.value=cod;
   parent.document.nuevo.nombDirector.value=nombre;
   parent.document.nuevo.apellDirector.value=apellido;
   myLytebox.end();
}

var nav4=window.Event ? true : false;

	function numeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}
</script>cidcs
</head>
<body>
	<br>
	<form name="filtroConsulta" action='<c:url value="/adminGrupos/AdminGrupos.x"/>' method="post">
	<input type="hidden" name="accion" value="">
	<input type="hidden" name="director" value='<c:out value="${requestScope.director}" default="no" />'>
		<table class="tablas" align="center" >
		<caption>Filtro de Consulta</caption>
			<tr>
				<td>
					<table>
						<tr>
							<td><b>Documento:</b></td>
							<td><input type="text" name="cedula" maxlength="10" onkeypress="return numeros(event)"></td>
							<td><b>Codigo UD:</b></td>
							<td><input type="text" name="codigoUd" maxlength="11" onkeypress="return numeros(event)"></td>
						</tr>
						<tr>
							<td colspan="4" align="center"><img src="<c:url value="/comp/img/Buscar.gif"/>" onclick="acci(20)" title="Buscar"></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
</form>

<c:if test="${!empty sessionScope.Integrantes}">
<form name="lista" action='<c:url value="/adminGrupos/AdminGrupos.x"/>' method="post">
	<input type="hidden" name="accion" value='<c:out value="${requestScope.accion }" default="10"/>' >
	<input type="hidden" name="cedula" value="">
	<input type="hidden" name="codUD" value="">
	<input type="hidden" name="flagMod" value='<c:out value="${requestScope.accion }" default="0"/>'>
		<br>
		<br>

		<table class="tablas" align="center" width="98%">
		<caption>Resultados de Busqueda</caption>
			<tr>
				<th>Codigo UD</th>
				<th>Nombres</th>
				<th>Apellidos</th>
				<th>Facultad</th>
				<th>Proyecto curricular</th>
				<th>Tipo</th>
				<th>Ver</th>
			</tr>
			<c:forEach begin="0" items="${sessionScope.Integrantes}" var="lista" varStatus="st">
			 <tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if>>
				<td><c:out value="${lista.codigoUd}" default="----------"/></td>
				<td><c:out value="${lista.nombres}" default="----------"/></td>
				<td><c:out value="${lista.apellidos}" default="----------"/></td>
				<td align="center"><c:out value="${lista.nombreFacultad}" default="----------"/></td>
				<td align="center"><c:out value="${lista.nombreProyCurr}" default="----------" /></td>
				<td align="center"><c:out value="${lista.nombreTipoPer}" default="----------"/></td>
				<c:if test="${requestScope.director!='si'}">
				<td><img src='<c:url value="/comp/img/Ver.gif"/>' title="Ver Datos" onclick='enviar(<c:out value="${lista.cedula}"/>,<c:out value="${lista.codigoUd}"/>)' >
				</c:if>
				<c:if test="${requestScope.director=='si'}">
				<td><img src='<c:url value="/comp/img/ok.png"/>' title="Ver Datos" onclick='asignar(<c:out value="${lista.cedula}"/>,<c:out value="${lista.codigoUd}"/>,"<c:out value="${lista.nombres}"/>","<c:out value="${lista.apellidos}"/>")' >
				</c:if>
				</td>
			 </tr>
			</c:forEach>
		</table>
	</form>
</c:if>
</body>
</html>