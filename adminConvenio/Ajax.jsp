<%@ page contentType="text/html; charset=iso-8859-1" language="java" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<script>

	function borrar_combo(combo){
		for(var i = combo.length - 1; i >= 0; i--) {
			if(navigator.appName == "Netscape")
				combo.options[i] = null;
			else
				combo.remove(i);
		}
		combo.options[0] = new Option("---------------------","0");
	}

	<c:choose>
	  <c:when test="${requestScope.para==1}">
	  
	  		borrar_combo(parent.document.frmConvenio.proycurri);
	  		borrar_combo(parent.document.frmConvenio.grupo);
	  	//	borrar_combo(parent.document.form1.investigador);

			<c:forEach begin="0" items="${sessionScope.ajaxProyCur}" var="pro" varStatus="st2">
			    <c:if test="${sessionScope.Convenio.proycurri==pro.codigo}">
				parent.document.frmConvenio.proycurri.options[<c:out value="${st2.count}"/>] = new Option('<c:out value="${pro.nombre}"/>','<c:out value="${pro.codigo}"/>','selected');
				</c:if>
				<c:if test="${sessionScope.Convenio.proycurri!=pro.codigo}">
				parent.document.frmConvenio.proycurri.options[<c:out value="${st2.count}"/>] = new Option('<c:out value="${pro.nombre}"/>','<c:out value="${pro.codigo}"/>');
				</c:if>
			</c:forEach>


			<c:forEach begin="0" items="${sessionScope.ajaxGrupos}" var="gru" varStatus="st2">
			    <c:if test="${sessionScope.Convenio.grupo==gru.codigo}">
			    parent.document.frmConvenio.grupo.options[<c:out value="${st2.count}"/>] = new Option('<c:out value="${gru.nombre}"/>','<c:out value="${gru.codigo}"/>','selected');
			    </c:if>
				<c:if test="${sessionScope.Convenio.grupo!=gru.codigo}">
				parent.document.frmConvenio.grupo.options[<c:out value="${st2.count}"/>] = new Option('<c:out value="${gru.nombre}"/>','<c:out value="${gru.codigo}"/>');
				</c:if>
            </c:forEach>
			parent.ajaxGrupo(parent.document.frmConvenio.grupo);
		</c:when>


		<c:when test="${requestScope.para==2}">
	  	borrar_combo(parent.document.form1.investigador);

		<c:forEach begin="0" items="${sessionScope.ajaxInvest}" var="inv" varStatus="st2">
		    <c:if test="${sessionScope.proyectos.investigador==inv.codigo}">
			parent.document.form1.investigador.options[<c:out value="${st2.count}"/>] = new Option('<c:out value="${inv.nombre}"/>','<c:out value="${inv.codigo}"/>','selected');
			</c:if>
			<c:if test="${sessionScope.proyectos.investigador!=inv.codigo}">
			parent.document.form1.investigador.options[<c:out value="${st2.count}"/>] = new Option('<c:out value="${inv.nombre}"/>','<c:out value="${inv.codigo}"/>');
			</c:if>
		</c:forEach>
	</c:when>

	</c:choose>

</script>