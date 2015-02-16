<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<script>
<!--
	function borrar_combo(combo){
		for(var i = combo.length - 1; i >= 0; i--) {
			if(navigator.appName == "Netscape")
				combo.options[i] = null;
			else
				combo.remove(i);
		}
		combo.options[0] = new Option("----------------------------------------","");
	}

	<c:choose>
	  <c:when test="${requestScope.para==2}">
	  	borrar_combo(parent.document.filtro.grupo);
		borrar_combo(parent.document.filtro.proyCur);
		<c:forEach begin="0" items="${sessionScope.ajaxGrupos}" var="grupos" varStatus="st">
			parent.document.filtro.grupo.options[<c:out value="${st.count}"/>] = new Option('<c:out value="${grupos.nombre}"/>','<c:out value="${grupos.codigo}"/>');
		</c:forEach>
		<c:forEach begin="0" items="${sessionScope.ajaxProyCur}" var="pro" varStatus="st2">
			parent.document.filtro.proyCur.options[<c:out value="${st2.count}"/>] = new Option('<c:out value="${pro.nombre}"/>','<c:out value="${pro.codigo}"/>');
		</c:forEach>
	  </c:when>
	  </c:choose>
	  <c:choose>
	  <c:when test="${requestScope.para==29}">
	  	borrar_combo(parent.document.frmEstado.subEstado);
	  	<c:forEach begin="0" items="${sessionScope.ajaxsubestado}" var="sub" varStatus="st3">
			parent.document.frmEstado.subEstado.options[<c:out value="${st3.count}"/>] = new Option('<c:out value="${sub.subEstados}"/>','<c:out value="${sub.listaSubEstados}"/>');
		</c:forEach>
	  </c:when>
	</c:choose>
  	
	
//-->
</script>