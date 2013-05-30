<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="loginUsuario" class="cidc.general.login.Usuario" scope="session" />

<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function ir(){
		if(document.filtro.num.selectedIndex==0)
			alert("favor seleccionar un número de convocatoria");
		else
			document.filtro.submit();
	}
	function ajaxNumeros(obj){
		var val=obj.value;
		if(val!=0){
			document.frmAjaxNumero.dato.value=val;
	 		document.frmAjaxNumero.target="frameOculto";
			document.frmAjaxNumero.submit();
		}
	}
	function ver(prop,tipo){
		document.listado.prop.value=prop;
		document.listado.tipEval.value=tipo;
		if(tipo==3){
			document.listado.action='<c:url value="/evalPropuestas/GestEvaluacion.x"/>';
			document.listado.accion.value="6";
		}else{
			document.listado.action='<c:url value="/adminPropuestas/EstadoPropuesta.x"/>';
		}

		document.listado.submit();
	}
	function verResPar(prop,tipo){
		document.listado.prop.value=prop;
		document.listado.accion.value="13";
		document.listado.action='<c:url value="/adminPropuestas/EstadoPropuesta.x"/>';
		document.listado.submit();
	}
	function activar(prop,tipo){
		if(confirm("¿Desea desacivar esta propuesta?")){
			document.listado.prop.value=prop;
			document.listado.accion.value="16";
			document.listado.activa.value=tipo;
			document.listado.action='<c:url value="/adminPropuestas/EstadoPropuesta.x"/>';
			document.listado.submit();
		}
	}
        function enviar(buscar){
                	document.listado.codProp.value=buscar;
                	document.listado.accion.value='2';
                	document.listado.action='<c:url value="/adminPropuestas/llenar1.jsp"/>';
                	document.listado.submit();
        }
	function nuevo(buscar){
                document.listado.codProp.value=buscar;
                document.listado.accion.value='3';
                document.listado.action='<c:url value="/AdminPropuestas/ComiteEvaluacion.x"/>';
                document.listado.submit();
        }
	        function guardar(){
                document.listado.accion.value='2';
                document.listado.action='<c:url value="/adminPropuestas/llenar2.jsp"/>';
                document.listado.submit();
        }

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="filtro" method="post" action='<c:url value="/AdminPropuestas/ResultadoEvaluacion.x"/>'>
		<input type="hidden" name="accion" value="1"/>
		<table align="center" class="tablas">
		<caption>Filtro de Búsqueda</caption>
			<tr>
			<%if(!loginUsuario.isPerfil("13")){ %>
				<td class="renglones">Año</td>
				<td><select name="ano" onchange="ajaxNumeros(this)">
						<option value="0">----</option>
					<c:forEach begin="0" items="${requestScope.listaConv}" var="anos">
						<option value='<c:out value="${anos}"/>' <c:if test="${sessionScope.ano==anos}">selected</c:if>><c:out value="${anos}"/></option>
					</c:forEach>
					</select>
				</td>
				<td class="renglones">#</td>
				<td><select name="num">
						<option value="0">----</option>
					<c:forEach begin="0" items="${requestScope.listaNum}" var="num">
						<option value='<c:out value="${num}"/>' <c:if test="${sessionScope.num==num}">selected</c:if>><c:out value="${num}"/></option>
					</c:forEach>
					</select>
				</td>
				<td><select name="tipo">
                                                <option value="1">Proy. Inv</option>
                                                <option value="2">Movilidad</option>
                                        </select>
                                </td>
				<td class="renglones">Estado</td>
				<td>Activa <input type="radio" name="activa" value="true"  <c:if test="${sessionScope.activa==true or sessionScope.activa==null}">checked="checked"</c:if>></td>
				<td>Desactiva <input type="radio" name="activa" value="false" <c:if test="${sessionScope.activa==false}">checked="checked"</c:if>></td>
				<%} %>
				<%if(loginUsuario.isPerfil("13")){ %>
				<input type="hidden" name="activa" value="true">
				<td>Año</td>
				<td><select name="ano">
						<option value="0">----</option>
						<option value='2009'<c:if test="${sessionScope.ano==2009}">selected</c:if>>2009</option>
					</select>
				</td>
				<td>#</td>
				<td><select name="num">
						<option value="0">----</option>
						<option value="5" <c:if test="${sessionScope.num==5}">selected</c:if>>5</option>
					</select>
				</td>
				<%} %>
				<td><img onclick="ir()" src='<c:url value="/comp/img/Buscar.gif"/>'></td>

			</tr>

		</table>
	</form>
	<br>
	<c:if test="${requestScope.convEstado!=null}">
	<br>
	<form name="listado" method="post" action='<c:url value="/AdminPropuestas/ComiteEvaluacion.x"/>'>
		<input type="hidden" name="accion" value="0"/>
		<input type="hidden" name="prop" value="0">
		<input type="hidden" name="activa" value="0">
		<input type="hidden" name="tipEval" value="0">
		<input type="hidden" name="codPropuesta" value="0">
		<input type="hidden" name="tipo" value="${tipo}">
        <table class="tablas" width="98%" >
        <caption >Resultados de Propuestas Inscritas y Aprobadas</caption>

        <tr>
                	<th align="center"><b>Num</b></th>
                	<th align="center"><b>Convocatoria</b></th>
                	<th align="center"><b># Propuesta</b></th>
			<c:if test="${tipo==1}">
                	<th align="center"><b>Evaluador Interno</b></th>
                	<th align="center"><b>Evaluador Externo</b></th>
                	<th align="center"><b>Comite de Investigaciones</b></th>
			</c:if>
			<c:if test="${tipo==2}">
			<c:forEach begin="0" items="${sessionScope.listaEvalOBJ}" var="lista5" varStatus="st">	
			<th align="center"><b><c:out value="${lista5.nombre}"/><c:out value="${lista5.apellido}"/></th>
			</c:forEach>
			</c:if>
                	<th align="center"><b>Puntaje Total</b></th>
                	<th align="center"><b>Observaciones</b></th>
        </tr>
        <c:forEach begin="0" items="${sessionScope.listaPropOBJ}" var="lista" varStatus="st">
	<c:if test="${lista3.codigo==lista4.criterio}">
	<tr>
                        <th align="center" width="25px"><b><c:out value="${st.count}"/></b></th>
<input type="hidden" name="codProp2" value="<c:out value="${lista.codPropuesta}"/>">
<input type="hidden" name="codAsp2" value="<c:out value="${lista4.codigo}"/>">
<input type="hidden" name="codCrit2" value="<c:out value="${lista4.criterio}"/>">
<input type="hidden" name="convId2" value="<c:out value="${lista.conv}"/>">
                        <td class="listas" width="25px"><c:out value="${num}"/>-<c:out value="${ano}"/></td>
                        <td class="listas" width="25px"><c:out value="${lista.codPropuesta}"/></td>

		<c:if test="${tipo==1}">
		<input type="hidden" name="codProp" value="<c:out value="${lista.codPropuesta}"/>">
		<input type="hidden" name="convId" value="<c:out value="${lista.conv}"/>">
		<input type="hidden" name="codCrit" value="<c:out value="${lista3.codigo}"/>">
		<input type="hidden" name="codAsp" value="<c:out value="${lista4.codigo}"/>">
		<td class="estado" align="center">
			<input type="text" name="observaciones1" size="20" '<c:forEach begin="0" items="${sessionScope.listaCalProy}" var="lista6" varStatus="st">' '<c:if test="${lista6.codPropuesta==lista.codPropuesta}">' value="${lista6.total1}" '</c:if>' '</c:forEach>' disabled>
		</td>
		<td class="estado" align="center">
			<input type="text" name="observaciones2" size="20" '<c:forEach begin="0" items="${sessionScope.listaCalProy}" var="lista6" varStatus="st">' '<c:if test="${lista6.codPropuesta==lista.codPropuesta}">' value="${lista6.total2}" '</c:if>' '</c:forEach>' disabled>
		</td>
		<td class="estado" align="center">
			<input type="text" name="observaciones3" size="20" '<c:forEach begin="0" items="${sessionScope.listaCalProy}" var="lista6" varStatus="st">' '<c:if test="${lista6.codPropuesta==lista.codPropuesta}">' value="${lista6.total3}" '</c:if>' '</c:forEach>' disabled>
		</td>
		
		</c:if>
		<c:if test="${tipo==2}">
			<c:forEach begin="0" items="${sessionScope.listaEvalOBJ}" var="lista5" varStatus="st">
			<td>
			<input type="text" name="valorCal" size="2" '<c:forEach begin="0" items="${sessionScope.listaCalProy}" var="lista6" varStatus="st">' '<c:if test="${lista6.evaluador==lista5.codigo}">'  value="${lista6.total1}" '</c:if>' '</c:forEach>' disabled>
			</td>
<input type="hidden" name="codProp" value="<c:out value="${lista.codPropuesta}"/>">
<input type="hidden" name="convId" value="<c:out value="${lista.conv}"/>">
<input type="hidden" name="codCrit" value="<c:out value="${lista3.codigo}"/>">
<input type="hidden" name="codEvaluador" value="<c:out value="${lista5.codigo}"/>">
<input type="hidden" name="codAsp" value="<c:out value="${lista4.codigo}"/>">
			</c:forEach>
		</c:if>
		<td class="estado" align="center">
		<c:if test="${tipo==1}">
		<c:forEach begin="0" items="${sessionScope.listaCalProy}" var="lista6" varStatus="st"> 
		<c:if test="${lista6.codPropuesta==lista.codPropuesta}">
		<c:set var="sum1" value="${lista6.total1}" />
		<c:set var="sum2" value="${lista6.total2}" />
		<c:set var="sum3" value="${lista6.total3}" />
		<c:set var="sum" value="${sum1 + sum2 + sum3}" />
		</c:if>
		</c:forEach>
		</c:if>
		<c:if test="${tipo==2}">
		<c:forEach begin="0" items="${sessionScope.listaCalProy}" var="lista6" varStatus="st"> 
		<c:if test="${lista6.codPropuesta==lista.codPropuesta}">
		<c:set var="sum1" value="${lista6.total1}" />
		<c:set var="sum" value="${sum + sum1}" />
		</c:if>
		</c:forEach>
		</c:if>
			<input type="text" name="puntaje" size="20" value="${sum}" disabled>
		</td>
		<td class="estado" align="center">
			<input type="text" name="observaciones" size="20" '<c:forEach begin="0" items="${sessionScope.listaCalObs}" var="lista7" varStatus="st">' '<c:if test="${lista7.codPropuesta==lista.codPropuesta}">' value="${lista7.observa}" '</c:if>' '</c:forEach>' disabled>
		</td>
	</tr>
			</c:if>

	</c:forEach>
<%--<tr><td>
		<img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()">
</td></tr>--%>	
	</table>
	</form>

	</c:if>
	<c:if test="${requestScope.convEstado==null}">
	<h4 align="center">Seleccione un Año y un Número de Convocatoria</h4>
	<h4 align="center"><c:out value="${requestScope.estado}"/></h4>
	</c:if>
	<table>
		<tr>
			<td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td>
		</tr>
	</table>
	<form method="post" name="frmAjaxNumero" action="<c:url value="/adminPropuestas/Ajax.x"/>">
		<input type="hidden" name="accion" value='7'>
		<input type="hidden" name="dato" value=''>
	</form>

</body>

</html>
