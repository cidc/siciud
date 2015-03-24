<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:import url="/general.jsp" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<script type="text/javascript">
function buscarGrupo(){
	if(validar()){
		document.buscar.action='<c:url value="/planAccion/PlanAccion.x"/>';
		document.buscar.submit();
	}
}

function validar(){
	var mensaje="";
	if(document.buscar.facultad.value==""){
		mensaje="-> Facultad\n";
	}
	if(document.buscar.boton.value==""){
		mensaje="-> Grupo/Semillero\n";
	}
	if(document.buscar.ano.value==""){
		mensaje="-> Año";
	}
	if(mensaje!=""){
		mensaje="Los siguientes campos son obligatorios:\n"+mensaje;
		alert(mensaje);
		return false;
	}else
		return true;
}
 function buscarPlan(id){
	 //alert("entre");
	 document.grupo.idGrupo.value=id;
	 //document.grupo.ano.value=nombre;
	 document.grupo.submit();
	 
 }
</script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="buscar" method="post">
<input type="hidden" name="accion" value="12">
<h2 align="center">Consulta de Planes de Acción</h2>
<table align="center" class="tablas">
	<caption>Filtro de consulta</caption>
		<tr>
			<td class="renglones"><b>Facultad:</b></td>
			<td>
				<select name="facultad">
					<option value="0">-------------</option>
					<option value="1" <c:if test='${requestScope.facultad==1}'>selected</c:if> >Tecnológica</option>
					<option value="2" <c:if test='${requestScope.facultad==2}'>selected</c:if> >Ingeniería</option>
					<option value="3" <c:if test='${requestScope.facultad==3}'>selected</c:if> >Medio Ambiente</option>
					<option value="4" <c:if test='${requestScope.facultad==4}'>selected</c:if> >Ciencias y Educación</option>
					<option value="5" <c:if test='${requestScope.facultad==5}'>selected</c:if> > Artes (Asab)</option>
				</select>
			</td>
			<td><b>Grupo</b><input type="radio" name="boton" value="1" <c:if test='${requestScope.boton==1}'>checked</c:if>>
			</td>
			<td><b>Semillero</b><input type="radio" name="boton" value="2" <c:if test='${requestScope.boton==2}'>checked</c:if>></td>
			<th>
				<b>Año</b>
			</th>
			<td>
				<select name="ano" style="width: 70px">
					<c:forEach items="${requestScope.listaAnos}" var="lista">
						<option value="${lista}" <c:if test='${requestScope.ano==lista}'>selected</c:if> ><c:out value="${lista}"/></option>
					</c:forEach>
				</select>
			</td>
			<td><img align="left" src="<c:url value="/comp/img/Buscar.gif"/>" onclick="buscarGrupo()"></td>
		</tr>
	</table>
</form>
<form name="grupo" method="post" action='<c:url value="/planAccion/PlanAccion.x"/>'>
<input type="hidden" name="accion" value="13" />
<input type="hidden" name="nombreGrupo"  />
<input type="hidden" name="idPlan" />
<input type="hidden" name="ano" value="${requestScope.ano}" />
<input type="hidden" name="idGrupo" />
<c:if test="${sessionScope.ListaGruposPlan!=null}">
<table align="center" class="tablas">
<caption>Listado de <c:if test="${requestScope.boton==1}">Grupos</c:if><c:if test="${requestScope.boton==2}">Semilleros</c:if> de Investigación</caption>
<tr>
	<td align="center" class="renglones"><b>Nombre</b></td>
	<td align="center" class="renglones"><b>Director</b></td>
	<td align="center" class="renglones" width="10%"><b>Estado</b></td>
	<td align="center" class="renglones" width="10%"><b>Ver</b></td>
</tr>
<c:forEach items="${sessionScope.ListaGruposPlan}" var="lista" >
<tr>
	<td><c:out value="${lista.nombregrupo}" /></td>
	<td><c:out value="${lista.director}" /></td>
	<td align="center" width="10%">
		<c:if test="${lista.contActividades==0}"><img src ='<c:url value="/comp/img/Equis.gif"/>'/></c:if>
		<c:if test="${lista.contActividades!=0}"><img src ='<c:url value="/comp/img/ok.png"/>'/></c:if>
	</td>
	<td width="10%">
		<c:if test="${lista.contActividades!=0}"><img src ='<c:url value="/comp/img/VerProy.gif"/>' onclick="buscarPlan(<c:out value="${lista.idGrupo}" />)"/></c:if>
	</td>
</tr>
</c:forEach>
</table>
</c:if>
</form>
</body>
</html>