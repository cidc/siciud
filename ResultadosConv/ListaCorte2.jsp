<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<c:import url="/general.jsp"/>
<script>
	function ver(id){
		document.res.id.value=id;
		document.res.submit();
	}
</script>
</head>
<body>
	<br>
	<div align="center">
	<a href='<c:url value="/Resultados/Convocatorias/AdminResConv.x?ano=${requestScope.ano}" />'><img border="0" src='<c:url value="/comp/img/Atras.gif" />'></a>
	</div>
	<br>
	<form name="res" action='<c:url value="/Resultados/Convocatorias/AdminResConv.x" />'>
	<input type="hidden" name="accion" value="2">
	<input type="hidden" name="id" value="0">
	<input type="hidden" name="corte" value='<c:out value="${requestScope.corte}"/>'>
	<input type="hidden" name="ano" value='<c:out value="${requestScope.ano}"/>'>
	<input type="hidden" name="conv" value='<c:out value="${requestScope.conv}"/>'>

	<table align="center" class="tablas" cellspacing="1" width="95%">
		<caption>Proyectos Inscritos</caption>
		<tr>
			<td class="renglones"><b>#</b></td>
			<td align="center" class="renglones"><b>Nombre Ponencia</b></td>
			<td width="170px" align="center" class="renglones"><b>Presentador</b></td>
			<td class="renglones"><b>Ver</b></td>
		</tr>
		<c:forEach begin="0" items="${requestScope.listaInscritos}" var="lista" varStatus="st">
		<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
			<td class="texto"><c:out value="${st.count}"/></td>
			<td class="texto"><c:out value="${lista.nombrePropuesta}"/></td>
			<td width="170px" class="texto"><c:out value="${lista.nombreInvestigador}"/></td>
			<td ><img border="0" src="/siciud/comp/img/Ver.gif" onclick="ver(<c:out value="${lista.idPropuesta}"/>)"></td>
		</tr>
		</c:forEach>
	</table>
	</form>
</body>
</html>
