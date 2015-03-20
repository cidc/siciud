<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<c:import url="/general.jsp" />    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:forEach begin="0" items="${sessionScope.listaCriterios}" var="criterio" varStatus="st">
			<table align="center" class="tablas" width="90%">
				<caption>
					<c:out value="${st.count}" />.<c:out value="${criterio.descripcion}" />
				</caption>
				<tr>
					<c:if test="${criterio.idCriterio==2}">
						<th align="center">Proyectos</th>
					</c:if>
					<c:if test="${criterio.idCriterio!=2}">
						<th align="center">Actividad</th>
					</c:if>
					<th width="140px" class="renglones" align="center">Descripción</th>
					<th align="center" width="100px">Meta</th>
				</tr>
			<br>
				<c:forEach begin="0" items="${sessionScope.listaActividades}"
					var="actividades" varStatus="st1">
					<c:if test="${(criterio.idCriterio==actividades.idCriterio)}">
						<tr>
							<td><p class="texto1j">
									<c:out value="${actividades.actividad}" />
								</p></td>
							<td><p class="texto1j">
									<c:out value="${actividades.descripcion}" />
								</p></td>
							<td><p class="texto1j">
									<c:out value="${actividades.meta}" />
								</p></td>
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</c:forEach>
</body>
</html>