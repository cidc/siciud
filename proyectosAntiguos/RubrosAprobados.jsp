<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:useBean id="loginUsuario" class="cidc.general.login.Usuario" scope="session" />
<html>
<head>
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
	function tabs(num){
	    document.formTab.validar.value = num;
		document.formTab.submit();
	 }

	 function guardar(){
		 for(var i=0;i<document.listadoRubros.valorRubro.length;i++){
			document.listadoRubros.valorRubro[i].value=eliminaFormatoMoneda(document.listadoRubros.valorRubro[i].value);
		 }
		 document.listadoRubros.submit();
	 }
	function check(caja,id){
		if(caja.checked)
			document.getElementById("rubro"+id).disabled=false;
		else
			document.getElementById("rubro"+id).disabled=true;
	}

	function chequear(formulario1,formulario2){

		if(formulario2.rubInc){
			if(formulario2.rubInc.length){
				for(var i=0;i<formulario1.idRubro.length;i++){
					formulario1.valorRubro[i].disabled=true;
					for(var j=0;j<formulario2.rubInc.length;j++){
						if(formulario2.rubInc[j].value==formulario1.idRubro[i].value){
							formulario1.idRubro[i].checked=true;
							formulario1.valorRubro[i].disabled=false;
							formulario1.valorRubro[i].value=formulario2.valor[j].value;
						}
					}
				}
			}
			else{
				for(var i=0;i<formulario1.idRubro.length;i++){
					formulario1.valorRubro[i].disabled=true;
					if(formulario2.rubInc.value==formulario1.idRubro[i].value){
						formulario1.idRubro[i].checked=true;
						formulario1.valorRubro[i].disabled=false;
						formulario1.valorRubro[i].value=formulario2.valor.value;
					}
				}
			}
		}
	}
</script>

<c:import url="/general.jsp"/>

<title>Devoluciones Proyecto Antiguo</title>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
<form name="formTab" method="post" action='<c:url value="/proyectosAntiguos/GestProyectos.x"/>'>
<input type="hidden" name="validar">
<input type="hidden" name="id" value=<c:out value="${sessionScope.proyectos.id}"/>>
<table cellspacing="0" cellpadding="0">

<tr>   <td><img src='<c:url value="/comp/img/DatosGenerales.gif"/>' onclick="tabs(6)"></td>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("4")||loginUsuario.isPerfil("1")){ %>
       		<td><img src='<c:url value="/comp/img/Docsproyectoant.gif"/>' onclick="tabs(7)"></td>
       	<%} %>
       	<%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("6")||loginUsuario.isPerfil("7")||loginUsuario.isPerfil("8")||loginUsuario.isPerfil("1")){ %>
	       <td><img src='<c:url value="/comp/img/Presupuesto.gif"/>' onclick="tabs(8)"></td>
	       <td><img src='<c:url value="/comp/img/prAprobado.gif"/>' onclick="tabs(27)"></td>
       <%} %>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("5")||loginUsuario.isPerfil("1")){ %>
       <td><img src='<c:url value="/comp/img/Investigadores.gif"/>' onclick="tabs(10)"></td>
       <%} %>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("7")||loginUsuario.isPerfil("1")){ %>
       <td><img src='<c:url value="/comp/img/Inventario.gif"/>' onclick="tabs(15)"></td>
       <td><img src='<c:url value="/comp/img/Cambios.gif"/>' onclick="tabs(19)"></td>
       <td><img src='<c:url value="/comp/img/Devoluciones.gif"/>' onclick="tabs(22)"></td>
       <%} %>
       <td><img src='<c:url value="/comp/img/Cambios.gif"/>' onclick="tabs(19)"></td>
</tr>
</table>
</form>
<br>

<form action='<c:url value="/proyectosAntiguos/GestProyectos.x"/>' name="listadoRubros">
	<input type="hidden" name="validar" value="28">
	<table align="center" class="tablas">
	<caption>Lista de rubros aprobados</caption>
		<c:if test="${!empty requestScope.listaRubros}">
			<tr>
				<th>&nbsp;</th>
				<th><b>Rubro</b></th>
				<th style="width:50px;" align="center"><b>Valor</b></th>
			</tr>
			<c:forEach begin="0" items="${requestScope.listaRubros}" var="lista" varStatus="st">
			<tr>
				<td>
					<input type="checkbox" name="idRubro" onchange="check(this,<c:out value="${st.count}" />)" value='<c:out value="${lista.codigo}" />'>
				</td>
				<td>
					<c:out value="${lista.nombre}"/>
				</td>
				<td style="width:50px;" align="right">
					<input id='rubro<c:out value="${st.count}" />' disabled style="text-align:right" maxlength="10" size="10" type="text" onkeypress="return soloNumeros(event)" name="valorRubro" value="0"></td>
			</tr>
			</c:forEach>
		</c:if>
			<tr>
				<td colspan="3" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar()"></td>
			</tr>
	</table>
</form>

	<form name="frmInsc">
		<c:forEach begin="0" items="${requestScope.listaRubrosAprobados}" var="lista" varStatus="st">
			<input type="hidden" name="rubInc" value='<c:out value="${lista.codigo}"/>'>
			<input type="hidden" name="valor" value='<c:out value="${lista.valor}"/>'>
		</c:forEach>
	</form>
</body>
<script>
	chequear(document.listadoRubros,document.frmInsc);
</script>
</html>