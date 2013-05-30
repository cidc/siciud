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
                document.listado.action='<c:url value="/adminPropuestas/llenar1.jsp"/>';
                document.listado.submit();
        }

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="filtro" method="post" action='<c:url value="/AdminPropuestas/ComiteEvaluacion.x"/>'>
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
        <table class="tablas" width="98%" >
        <caption >Listado de Propuestas Inscritas y Aprobadas</caption>
	<c:forEach begin="0" items="${sessionScope.listaCritOBJ}" var="lista3" varStatus="st">
		<tr>
			<th><b>Criterio:</b><c:out value="${lista3.codigo}"/></th>
			<th align="center" width="50px"><b><c:out value="${lista3.nombre}"/></b>	 </th>
		</tr>

		<c:forEach begin="0" items="${sessionScope.listaAspOBJ}" var="lista4" varStatus="st">
			<c:if test="${lista3.codigo==lista4.criterio}">
                	<tr>
				<td align="center" width="25px"><b> </b></td>
				<th align="center" width="25px"><b>Aspecto: </b><c:out value="${lista4.codigo}"/></th>
				<th align="center" width="50px"><b><c:out value="${lista4.nombre}"/></th>
			</tr>
        <tr>
                	<th align="center"><b>Num</b></th>
                	<th align="center"><b>Convocatoria</b></th>
                	<th align="center"><b># Propuesta</b></th>
                	<th align="center"><b>Doc</b></th>
                	<th align="center"><b>Doc</b></th>
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
                	<th align="center"><b>Observaciones</b></th>
        </tr>
			</c:if>
        <c:forEach begin="0" items="${sessionScope.listaPropOBJ}" var="lista" varStatus="st">
	<c:if test="${lista3.codigo==lista4.criterio}">
	<tr>
                        <th align="center" width="25px"><b><c:out value="${st.count}"/></b></th>
			<input type="hidden" name="codProp" value="<c:out value="${lista.codPropuesta}"/>">
                        <td class="listas" width="25px"><c:out value="${num}"/>-<c:out value="${ano}"/></td>
                        <td class="listas" width="25px"><c:out value="${lista.codPropuesta}"/></td>
		<c:forEach begin="0" items="${sessionScope.listaDocOBJ}" var="lista2" varStatus="st">
		<td class="listas" width="25px">
			<c:if test="${lista.codPropuesta==lista2.codPropuesta}">
			<a href="/siciud/Documentos/Convocatorias/Requisito/<c:out value="${lista2.docNombre}"/>"><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a>
			<c:out value="${lista.docNombre}"/>
		</td>
			</c:if>
		</c:forEach>

		<c:if test="${tipo==1}">
			<td>
			<input type="text" name="valorInterno" size="3">
			</td>
			<td>
			<input type="text" name="valorExterno" size="3">
			</td>
			<td>
			<input type="text" name="valorComite" size="3">
			</td>
		</c:if>
		<c:if test="${tipo==2}">
			<c:forEach begin="0" items="${sessionScope.listaEvalOBJ}" var="lista5" varStatus="st">
			<td>
			<input type="text" name="valor" size="3">
			</td>
			</c:forEach>
		</c:if>
		<td class="estado" align="center">
			<input type="text" name="observaciones" size="30">
		</td>
	</tr>
			</c:if>
	</c:forEach>
        <%-- <img src='<c:url value="/comp/img/lupa3.png"/>' onclick="enviar('<c:out value="${lista.codPropuesta}"/>')">--%>

        </c:forEach>
	</c:forEach>
<tr><td>
		<img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()">
</td></tr>	
	</table>
	</form>



	<%--		<a href='<c:url value="/Documentos/Convocatorias/Requisito/Prueba1.pdf"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>--%>
                      <%--  <img src='<c:url value="/comp/img/lupa3.png"/>' onclick="nuevo('<c:out value="${lista.codPropuesta}"/>')"> %-->
                        </td>
			<td align="center" width="15px">
		<%--	<c:if test="${!lista.propActiva}">
				<img src='<c:url value="/comp/img/esc.png"/>' onclick='activar(<c:out value="${lista.codPropuesta}"/>,1)'>
			</c:if>--%>
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
