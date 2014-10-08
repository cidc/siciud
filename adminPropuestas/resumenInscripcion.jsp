<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<c:import url="/general.jsp"/>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<table class="tablas" border="1" align="center" style="width:550px;">
	<caption>Datos Generales</caption>
		<tr>
			<td class="renglones" colspan="2"><b>Nombre de la Convocatoria:</b></td>
		</tr>
		<tr>
			<td colspan="2"><c:out value="${requestScope.resumen.convocatoria}"/></td>
		</tr>
		<tr>
			<td class="renglones"><b>Facultad:</b></td>
			<td class="renglones"><b>Grupo o Semillero de Investigación:</b></td>
		</tr>
		<tr>
			<td><c:out value="${requestScope.resumen.facultad}"/></td>
			<td><c:out value="${requestScope.resumen.grupo}"/></td>
		</tr>
		<tr>
			<td  class="renglones"><b>Investigador Principal:</b></td>
			<td><c:out value="${requestScope.resumen.investigador}"/></td>
		</tr>
		<tr>
			<td  colspan="2" class="renglones"><b>Nombre de la Propuesta Inscrita:</b></td>
		</tr>
		<tr>
			<td  colspan="2"><c:out value="${requestScope.resumen.propuesta}"/></td>
		</tr>
		<tr>
			<td colspan="2" class="renglones"><b>Abstract:</b></td>
		</tr>
		<tr>
			<td colspan="2"><c:out value="${requestScope.resumen.propAbstract}"/></td>
		</tr>
		<tr>
			<td colspan="2" class="renglones"><b>Palabras Claves:</b></td>
		</tr>
		<tr>
			<td colspan="2"><c:out value="${requestScope.resumen.palClaves}"/></td>
		</tr>
	</table>

	<br>
	<c:if test="${!empty requestScope.resumen.coInvestigadores}">
	<table class="tablas" align="center" style="width:550px;">
	<caption>Co-Investigadores inscritos</caption>
		<tr>
			<td width="95px" class="renglones"><b>Doc. Identidad:<b></td>
			<td class="renglones"><b>Nombres:</b></td>
			<td class="renglones"><b>Apellidos:</b></td>
			<td width="40px" class="renglones"><b>Horas:</b></td>
			<td width="125px" class="renglones"><b>Papel en el proyecto:</b></td>
		</tr>
		<c:forEach begin="0" items="${requestScope.resumen.coInvestigadores}" var="investigadores">
		<tr>
			<td width="95px"><c:out value="${investigadores.documento}"/></td>
			<td><c:out value="${investigadores.nombres}"/></td>
			<td><c:out value="${investigadores.apellidos}"/></td>
			<td width="40px" align="center"><c:out value="${investigadores.horas}"/></td>
			<td width="125px"><c:out value="${investigadores.papel}"/></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<br>
	<c:if test="${!empty requestScope.resumen.compromisos}">
	<table class="tablas" align="center" style="width:550px;">
	<caption>Compromisos del Investigador principal</caption>
		<tr>
			<td width="15px" class="renglones"><b>#<b></td>
			<td class="renglones"><b>Producto</b></td>
			<td class="renglones"><b>Indicador</b></td>
			<td class="renglones"><b>Cant</b></td>
		</tr>
		<c:forEach begin="0" items="${requestScope.resumen.compromisos}" var="compromisos" varStatus="st">
		<tr>
			<td width="15px"><c:out value="${st.count}"/></td>
			<td><c:out value="${compromisos.producto}"/></td>
			<td><c:out value="${compromisos.indicador}"/></td>
			<td align="center"><c:out value="${compromisos.cantidad}"/></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<br>
	<c:if test="${!empty requestScope.resumen.rubros}">
		<table class="tablas" align="center" style="width: 550px;">
			<caption>Valores de los Rubros afectados en la propuesta</caption>
			<tr>
				<td style="width: 300px;" class="renglones"><b>Rubro</b></td>
				<td class="renglones" colspan="3" align="center"><b>Financiado
						por:</b></td>
			</tr>
			<tr>
				<th style="width: 300px;" class="renglones">&nbsp;</th>
				<th style="width: 30px;" class="renglones">CIDC</th>
				<th style="width: 30px;" class="renglones">UD</th>
				<th style="width: 30px;" class="renglones">Contrap..</th>
			</tr>
			<c:forEach begin="0" items="${requestScope.resumen.rubros}"
				var="rubros">
				<tr>
					<td style="width: 300px;"><c:out value="${rubros.nombre}" /></td>
					<td style="text-align: right; width: 30px;"><c:out
							value="${rubros.cidc}" /></td>
					<td style="text-align: right; width: 30px;"><c:out
							value="${rubros.ud}" /></td>
					<td style="text-align: right; width: 30px;"><c:out
							value="${rubros.contra}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${!empty requestScope.docs}">
		<table class="tablas" align="center" style="width: 550px;">
			<caption>Documentos de la propuesta</caption>
			<tr>
				<td style="width: 50px;" class="renglones"
				><b>Documento</b></td>
				<td class="renglones" align="center"><b>Nombre del Documento</b></td>
			</tr>
			<c:forEach begin="0" items="${requestScope.docs}" var="lista">
				<tr>
					<c:if test="${requestScope.docNombre!='' or requestScope.docNombre!=null }">
					<td><a href='<c:url value="${lista.url}/${lista.docNombre}" />'><img src="<c:url value="/comp/img/pdf.png" />"></a></td>
					</c:if>
					<td style="text-align: right;"><c:out value="${lista.nombreDocumentoRequisito}" />
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>

</html>