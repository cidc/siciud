<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function registros(id){
		if('<c:out value="${sessionScope.proyectoInvestigador.consecContrato}" />'!='--'){
			document.balance.accion.value="7";
			document.balance.idRub.value=id;
	 		document.balance.target="frameRegistro";
			document.balance.submit();
		}else{
			alert("No se ha creado aun el contrato de este proyecto");
		}
	}
	function ver(accion){
		document.balance.accion.value=accion;
		document.balance.target="frameRegistro";
		document.balance.submit();
	}
	function tabs(num){
    	document.formTab.action="<c:url value='/GestionProyectos/ProyectosInvestigador.x' />";
	    document.formTab.accion.value = num;
		document.formTab.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${sessionScope.proyectoInvestigador!=null}">
<br>
<form name="formTab" method="post" >
	<input type="hidden" name="accion">
	<input type="hidden" name="id" value='<c:out value="${sessionScope.proyectoInvestigador.idProyecto}"/>'>
	<input type="hidden" name="tipo" value='<c:out value="${sessionScope.proyectoInvestigador.tipo}"/>'>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><img border="0" src='<c:url value="/comp/img/tabs/General2.gif"/>' onclick="tabs(1)"></td>
			<td><img border="0" src='<c:url value="/comp/img/tabs/Balance1.gif"/>'></td>
		</tr>
	</table>
</form>
<br>
	<table width="95%" class="tablas" align="center">
		<CAPTION>Datos generales del proyecto</CAPTION>
		<tr>
			<td colspan="3" class="renglones"><b>Nombre de Proyecto</b></td>
		</tr>
		<tr>
			<td colspan="3"><c:out value="${sessionScope.proyectoInvestigador.nombre}"/></td>
		</tr>
		<tr>
			<td class="renglones" width="34%"><b>Director del Proyecto</b></td>
			<td class="renglones" width="33%"><b>Código</b></td>
			<td class="renglones" width="33%"><b>Fecha Aprobación</b></td>
		</tr>
		<tr>
			<td width="34%"><c:out value="${sessionScope.proyectoInvestigador.director}"/></td>
			<td width="33%"><c:out value="${sessionScope.proyectoInvestigador.codigo}"/></td>
			<td width="33%"><c:out value="${sessionScope.proyectoInvestigador.fecAprobacion}"/></td>
		</tr>
	</table>
<br>
	<form name="balance" action='<c:url value="/GestionProyectos/ProyectosInvestigador.x"/>' method="post">
		<input type="hidden" name="accion" value="0">
		<input type="hidden" name="idRub" value="0">
		<table width="95%" align="center" class="tablas">
			<caption>Ficha Balance Rubros</caption>
			<tr>
				<td align="center" class="renglones"><b>Rubro</b></td>
				<td align="center" class="renglones" width="75px"><b>Aprobado</b></td>
				<td align="center" class="renglones" width="75px"><b>Comprometido</b></td>
				<td align="center" class="renglones" width="75px"><b>Ejecutado</b></td>
				<td align="center" class="renglones" width="75px"><b>Saldo</b></td>
				<td align="center" class="renglones" width="5px"><b>*</b></td>
			</tr>
			<c:forEach begin="0" items="${sessionScope.balanceProyecto.listaRubros}" var="lista">
			<tr >
				<td><c:out value="${lista.nombreRubro}"/></td>
				<td class="listas" width="75px" align="right"><c:out value="${lista.valorRubro}"/></td>
				<td width="75px" align="right"><c:out value=""/></td>
				<td width="75px" align="right"><c:out value="${lista.valorEjecutado}"/></td>
				<td width="75px" align="right"><c:out value="${lista.valorSaldo}"/></td>
				<td width="5px" align="center"><img src='<c:url value="/comp/img/find.png"/>' onclick='registros(<c:out value="${lista.idRubro}"/>)'></td>
			</tr>
			</c:forEach>
		</table>
	</form>
	<table align="center" width="100%">
		<tr>
			<td>
				<iframe frameborder="0" height="250px" marginheight="1px" width="100%" name="frameRegistro" id="frameRegistro">
					Pailas no podemos ver frames
				</iframe>
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${sessionScope.proyectoInvestigador==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Proyecto de Investigación</h4>
</c:if>
</body>
</html>