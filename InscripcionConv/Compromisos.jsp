<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<c:import url="/general.jsp"/>

<script type="text/javascript">
	function guardar1(){
		 var cantComp=sumaCombos();
		//alert(cantComp+" "+<c:out value="${sessionScope.datosConv.cantProduct}"/>);
		if(cantComp>=<c:out value="${sessionScope.datosConv.cantProduct}"/>){
			document.nuevo.action='<c:url value="/InscripcionConv/llenar.jsp"/>';
			document.nuevo.submit();
		}else{
			alert("Según los términos de referencia de la convocatoria usted debe selecionar por lo menos "+<c:out value="${sessionScope.datosConv.cantProduct}"/>+" compromisos opcionales");
		}
	}

	function sumaCombos(){
		cantComp=0;
		formulario = document.nuevo;
		for(var i=0; i<formulario.elements.length; i++) {
		  var elemento = formulario.elements[i];
		  if(elemento.type == "checkbox") {
		    if(elemento.checked) {
		    	cantComp++;
		    }
		  }
		}
		return cantComp;
	}

	
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<c:if test="${requestScope.resumen!=null}">
	<div align="center">
		<fieldset style="width:90%;">
			<div class="aviso"><p class="lroja3"><b>Paso # 2, A continuación encontrará el listado de compromisos establecidos en el marco de esta convocatoria, favor seleccionar los productos que usted, como investigador principal, se compromete a entregar al Centro de Investigaciones</b></p></div>
		</fieldset>
	</div>
	<br>
	</c:if>

	<c:if test='${sessionScope.inscripcionConvOBJ!=null}'>
	<form name="nuevo" method="post" >
		<input type="hidden" name="accion" value='3'>
		<input type="hidden" name="propConvId" value="${sessionScope.datosConv.convId}">
		<table width="90%" class="tablas" align="center" cellpadding="3" cellspacing="0">
		<caption>Compromisos Obligatorios del Investigador</caption>
            <tr>
          		<td class="renglones"><b>Nombre del Compromiso</b></td>
          		<td width="270px" class="renglones"><b>Indicador</b></td>
          		<td class="renglones"><b>Cant</b></td>
            </tr>
			<c:forEach begin="0" items="${sessionScope.listaComp}" var="lista" varStatus="vst">
				<tr>
					<td><c:if test="${lista.obligatorio==1}"><c:out value="${lista.nombre}"/></c:if></td>
					<td width="270px"><c:if test="${lista.obligatorio==1}"><c:out value="${lista.indicador}"/></c:if></td>
					<td align="center">
					<c:if test="${lista.obligatorio==1}"><c:out value="${lista.valor}"></c:out></c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
		<table width="90%" class="tablas" align="center" cellpadding="3" cellspacing="0">
		<caption>Compromisos Opcionales del Investigador</caption>
            <tr>
          		<td width="5px" class="renglones">&nbsp;</td>
          		<td width="5px" class="renglones">&nbsp;</td>
          		<td class="renglones"><b>Nombre del Compromiso</b></td>
          		<td width="270px" class="renglones"><b>Indicador</b></td>
          		<td class="renglones"><b>Cant</b></td>
            </tr>
			<c:forEach begin="0" items="${sessionScope.listaComp}" var="lista" varStatus="st">
			<c:if test="${lista.obligatorio!=1}">
				<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
					<td width="5px"><c:out value="${st.count}"/></td>
				 	<td width="5px">
				 		<input type="checkbox" name="compromiso_" <c:if test="${lista.obligatorio==1}">checked="checked"</c:if> value="${lista.codigo}" >
				 		<input type="hidden" name="idCompromisos" value='<c:out value="${lista.codigo}"/>'>
				 		
				 	</td>
					<td><c:out value="${lista.nombre}"/></td>
					<td width="270px"><c:out value="${lista.indicador}"/></td>
					<td align="center">
					<c:out value="${lista.valor}"/>
						<input type="hidden" name="cantComp" id="cantComp<c:out value="${st.count}"/>" value="<c:out value="${lista.valor}"/>">
					</td>
				</tr>
			</c:if>
			</c:forEach>

		</table>
		<table align="center">
			<tr>
    			<td align="center"><img src="<c:url value="/comp/img/Siguiente.gif"/>" onclick="guardar1()"></td>
   			<tr>
		</table>
	</form>
	</c:if>

	<table class="tablas" border="1" align="center" style="width:550px;">
	<caption>Datos Generales Propuesta Investigación</caption>
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
			<td class="renglones"><b>Doc. Identidad:<b></td>
			<td class="renglones"><b>Nombres:</b></td>
			<td class="renglones"><b>Apellidos:</b></td>
			<td class="renglones"><b>Papel en el proyecto:</b></td>
		</tr>
		<c:forEach begin="0" items="${requestScope.resumen.coInvestigadores}" var="investigadores">
		<tr>
			<td><c:out value="${investigadores.documento}"/></td>
			<td><c:out value="${investigadores.nombres}"/></td>
			<td><c:out value="${investigadores.apellidos}"/></td>
			<td><c:out value="${investigadores.papel}"/></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
	<br>
	<c:if test="${!empty requestScope.resumen.rubros}">
	<table class="tablas" align="center" style="width:550px;">
		<caption>Valores de los Rubros afectados en la propuesta</caption>
		<tr>
			<td style="width:300px;" class="renglones"><b>Rubro</b></td>
			<td class="renglones" colspan="3" align="center"><b>Financiado por:</b></td>
		</tr>
		<tr>
			<th style="width:300px;" class="renglones">&nbsp;</th>
			<th style="width:30px;" class="renglones">CIDC</th>
			<th style="width:30px;" class="renglones">UD</th>
			<th style="width:30px;" class="renglones">Contrap..</th>
		</tr>
		<c:forEach begin="0" items="${requestScope.resumen.rubros}" var="rubros">
		<tr>
			<td style="width:300px;"><c:out value="${rubros.nombre}"/></td>
			<td style="text-align:right;width:30px;"><c:out value="${rubros.cidc}"/></td>
			<td style="text-align:right;width:30px;"><c:out value="${rubros.ud}"/></td>
			<td style="text-align:right;width:30px;"><c:out value="${rubros.contra}"/></td>
		</tr>
		</c:forEach>
	</table>
	</c:if>
</body>

</html>
