<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="loginUsuario" class="cidc.general.login.Usuario" scope="session" />
<html>
<head>
<script type="text/javascript" src='<c:url value="/comp/js/Tooltip/libtooltip/prototype/prototype.js"/>'></script>
<script type="text/javascript" src=<c:url value="/comp/js/Tooltip/libtooltip/libtooltip/scriptaculous/scriptaculous.js"/>'></script>
<script type="text/javascript" src='<c:url value="/comp/js/Tooltip/srctooltip/HelpBalloon.js"/>'></script>
<c:import url="/general.jsp"/>
<script>

	HelpBalloon.Options.prototype = Object.extend(HelpBalloon.Options.prototype,
		{
			icon: '<c:url value="/comp/img/tooltip/tooltip.png"/>',
			button: '<c:url value="/comp/img/tooltip/button.png"/>',
			balloonPrefix: '<c:url value="/comp/img/tooltip/balloon-"/>'
		});


	function tooltip(tipo)
	{
	    var x='<c:url value="/AyudaTooltip.jsp?dato=uno"/>'
	  //  alert(x);
	    /**/
        var hb4 = new HelpBalloon
			  ({
	  		  dataURL: x
			  });
	}
	function com(id){
		document.comandos.accion.value=id;
		document.comandos.submit();
	}
	function ver(id,cod,ced){
		document.lista.action='<c:url value="/grupos/GestionGrupo.x"/>';
		document.lista.id.value=id;
		document.lista.codUD.value=cod;
		document.lista.cedula.value=ced;
		document.lista.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
<%if(loginUsuario.isPerfil("10")){ %>
	<form action='<c:url value="/grupos/GestionGrupo.x" />' name="comandos" method="post">
		<input type="hidden" name="accion" value="">
		<input type="hidden" name="codigo" value='<c:out value="${sessionScope.idGrupo}"/>'>
		<table >
			<tr>
				<td align="left">
					<img src="<c:url value="/comp/img/NuevoIntegrante-UD.gif"/>" onclick="com(0)">
				</td>
				<td align="left">
					<img src="<c:url value="/comp/img/NuevoIntegrante-Ext.gif"/>" onclick="com(21)">
				</td>
			</tr>
		</table>
	</form>
<%} %>
<br>
	<c:if test="${!empty requestScope.listaIntegrantes}">
	<form name="lista">
	<input type="hidden" name="accion" value="8">
	<input type="hidden" name="id" value="">
	<input type="hidden" name="codUD" value="">
	<input type="hidden" name="cedula" value="">
	<input type="hidden" name="flagMod" value="0">
	<table align="center">
		<tr>
			<td align="center"><b><c:out value="${requestScope.nombreGrupo}"/></b></td>
		</tr>
	</table>
	<br>
	<table class="tablas" align="center" width="450px">
	<caption>Listado de Integrantes</caption>
		<tr>
			<td align="center" class="renglones"><b>Nombres</b></td>
			<td align="center" class="renglones"><b>Apellidos</b></td>
			<td width="80px" align="center" class="renglones"><b>Papel</b></td>
			<td width="20px" align="center" class="renglones"><b>Activo</b></td>
			<td width="50px" align="center" class="renglones"><b>Ver</b></td>
		</tr>
	<c:forEach begin="0" items="${requestScope.listaIntegrantes}" var="lista" varStatus="st">
		<tr <c:if test="${(st.count mod 2)!=0}">class="trb"</c:if>>
			<td><c:out value="${lista.nombres}"/></td>
			<td><c:out value="${lista.apellidos}"/></td>
			<td width="80px">
				<c:if test='${lista.papel=="1"}'>Director</c:if>
				<c:if test='${lista.papel=="2"}'>Profesor</c:if>
				<c:if test='${lista.papel=="3"}'>Estudiante</c:if>
				<c:if test='${lista.papel=="4"}'>Egresado</c:if>
				<c:if test='${lista.papel=="5"}'>Lider Semillero</c:if>
				<c:if test='${lista.papel=="6"}'>Invitado</c:if>
			</td>
			<c:if test="${lista.fechaSalida!=null}">
				<td width="20px" align="center"><img src='<c:url value="/comp/img/Inactivo.png"/>'></td>
			</c:if>
			<c:if test="${lista.fechaSalida==null}">
				<td width="20px" align="center"><img src='<c:url value="/comp/img/Activo.png"/>'></td>
			</c:if>
			<td width="50px" align="center">
			<c:if test='${lista.cedula==null or lista.cedula=="" or lista.codigoUd==null or lista.codigoUd==""}'>
						 <script type="text/javascript">
						 tooltip(1);
						 </script>
			</c:if>
			<c:if test='${lista.cedula!=null and lista.cedula!="" and lista.codigoUd!=null and lista.codigoUd!=""}'>
				<img src='<c:url value="/comp/img/VerProy.gif"/>' onclick='ver(<c:out value="${lista.id}"/>,<c:out value="${lista.codigoUd}" default="-1" />,<c:out value="${lista.cedula}" default="-1"/>)'>
			</c:if>
			</td>
		</tr>
	</c:forEach>
	</table>
	</form>
	</c:if>
	<c:if test="${empty requestScope.listaIntegrantes}">
	<h5 align="center">No hay integrantes en este grupo/semillero de investigación</h5>
	</c:if>
</body>
</html>