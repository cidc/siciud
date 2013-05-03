<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<link href='<c:url value="/comp/css/tablas.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="/comp/css/formatos.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="/comp/css/fondos.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="/comp/css/menu.css"/>' rel="stylesheet" type="text/css">
<script type="text/javascript" src='<c:url value="/comp/js/menu.js"/>'></script>
<html>
<c:import url="/general.jsp"/>
<jsp:useBean id="crypto" scope="page" class="cidc.general.obj.EncriptarURL"/>
<body>
<c:if test="${sessionScope.loginUsuario.nombre!=null}">

<table background='<c:url value="/comp/img/FondoUsuario.png" />' width="150px">
<tr>
	<td align="center">
		<table class="tablas" align="left" width="100%">
			<tr><td align="center" class="nombreUsuario"><c:out value="${sessionScope.loginUsuario.nombre}"/></td></tr>
		</table>
	</td>
</tr>
<tr>
	<td align="right"><a href='<c:url value="ingresoUsuario.x?accion=0"/>' target="menu"><img border="0" src='<c:url value="/comp/img/logout.png"/>'></a></td>
</tr>
<c:if test="${sessionScope.loginUsuario.idUsuario!=0}">
</c:if>
</table>
<br>
</c:if>
<c:set value="${sessionScope.loginUsuario}" var="usuario" scope="page"/>
<c:set value="" var="categoria" scope="page"/>
<c:set value="0" var="contador"/>
	<table width="100">
		<tr>
			<td id="mainContainer">
				<div id="leftMenu">
					<div id="dhtmlgoodies_slidedown_menu">
						<ul>
							<c:forEach begin="0" items="${usuario.recursos}" var="recursos" varStatus="st">
							<c:if test="${categoria!=recursos.categoria && recursos.primario==true && contador!=0}">
								</ul>
							</li>
							</c:if>
								<c:if test="${categoria!=recursos.categoria && recursos.primario==true}">
									<c:set value="${recursos.categoria}" var="categoria"/>
									<li>
										<a href="#">&nbsp;<c:out value="${categoria}"/></a>
										<ul>
											<c:set value="${contador+1}" var="contador"/>
											<c:if test="${sessionScope.loginUsuario.idUsuario==0 and categoria=='Resultados' and recursos.item=='Convocatorias 2009'}">
											<li><a  id='<c:out value="${contador}" />' onmouseover="color(this.id)" onmouseout="uncolor(this.id)" target="main" href='/Resultados/main.html'>Convocatorias 2008</a></li>
											<c:set value="${contador+1}" var="contador"/>
									</c:if>
								</c:if>

								<c:if test="${recursos.primario==true}">
								<li><a  id='<c:out value="${contador}" />' onmouseover="color(this.id)" onmouseout="uncolor(this.id)" target="main" href='<c:url value="${recursos.recurso}"/>'><c:out value="${recursos.item}"/></a></li>
								<c:set value="${contador+1}" var="contador"/>
								</c:if>
							</c:forEach>
								</ul>
							</li>
						</ul>
					</div>
				</div>
			</td>
		</tr>
	</table>
<c:if test="${sessionScope.loginUsuario.idUsuario==0}">
<table>
	<tr><td>
	<form action="ingresoUsuario.x" method="post" target="menu">
	<input type="hidden" name="accion" value="1">
		<table width="150px" align="left">
			<tr><td colspan="3">&nbsp;</td></tr>
			<tr><td colspan="3" align="center"><b>Usuario</b></td></tr>
			<tr><td></td><td width="70%" align="center"><input class="cajas" type="text" name="usuario" size="10"></td><td></td></tr>
			<tr><td colspan="3" align="center"><b>Clave</b></td></tr>
			<tr><td></td><td width="70%" align="center"><input class="cajas" type="password" name="clave" size="10"></td><td></td></tr>
			<tr><td colspan="3" align="center"><br><input type="image" src='<c:url value="/comp/img/Ingresar.gif"/>'></td></tr>
		</table>
	</form>
	</td></tr>
	<tr><td>
	<form action="ingresoUsuario.x" method="post">
		<input type="hidden" name="accion" value="2">
		<table width="150px" align="left">
			<tr><td colspan="3" align="center"><br><input type="image" src='<c:url value="/comp/img/RecuperarClave.png"/>'></td></tr>
		</table>
	</form>
	</td></tr>
</table>
</c:if>


<script language="javaScript">
<c:if test="${sessionScope.loginUsuario.pagina!=null}">
	parent.parent.main.location.href='<c:url value="${sessionScope.loginUsuario.pagina}"/>';
</c:if>
<c:if test="${sessionScope.loginUsuario.pagina==null}">
	parent.parent.main.location.href='<c:url value="/main.html"/>';
</c:if>
</script>
</body>
</html>







