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
                document.listado.action='<c:url value="/AdminPropuestas/VerificaRequisitos.x"/>';
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
	<form name="filtro" method="post" action='<c:url value="/AdminPropuestas/VerificaRequisitos.x"/>'>
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
	<form name="listado" method="post" action='<c:url value="/AdminPropuestas/VerificaRequisitos.x"/>'>
		<input type="hidden" name="accion" value="0"/>
		<input type="hidden" name="prop" value="0">
		<input type="hidden" name="activa" value="0">
		<input type="hidden" name="tipEval" value="0">
		<input type="hidden" name="codPropuesta" value="0">
        <table class="tablas" width="98%" >
        <caption >Listado de Propuestas Inscritas</caption>
        <tr>
        	<th align="center"><b>#</b></th>
        	<th align="center"><b>Convocatoria</b></th>
        	<th align="center"><b>Propuesta</b></th>
        	<th align="center"><b>Documentos</b></th>
        	<th align="center"><b>Aprobo</b></th>
        	<th align="center"><b>Observaciones</b></th>
        </tr>
	<c:forEach begin="0" items="${sessionScope.listaPropOBJ}" var="lista" varStatus="st">
		<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
			<th align="center" width="25px"><b><c:out value="${st.count}"/></b></th>
<input type="hidden" name="codProp" value="<c:out value="${lista.codPropuesta}"/>">
<input type="hidden" name="convId" value="<c:out value="${lista.conv}"/>">
<td class="listas" width="25px"><c:out value="${num}"/>-<c:out value="${ano}"/></td>
			<td class="listas" width="25px"><c:out value="${lista.codPropuesta}"/></td>
<td class="listas" width="25px">
<c:forEach begin="0" items="${sessionScope.listaDocOBJ}" var="lista2" varStatus="st">
<c:if test="${lista.codPropuesta==lista2.codPropuesta}">
<a href="/siciud/Documentos/Movilidad/<c:out value="${lista2.docNombre}"/>"><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a>
<c:out value="${lista.docNombre}"/>
</c:if>
</c:forEach>
</td>
			</td>
			<td class="estado" align="center">
			<input type="checkbox" name="propAprobada"
	<c:forEach begin="0" items="${sessionScope.listaPropApOBJ}" var="lista6" varStatus="st">
 <c:if test="${lista.codPropuesta==lista6.codPropuesta}">checked</c:if>
</c:forEach>
>
			</td>
			<td class="estado" align="center">
			<input type="text" name="observaciones" size="30" '<c:forEach begin="0" items="${sessionScope.listaPropApOBJ}" var="lista7" varStatus="st">' '<c:if test="${lista.codPropuesta==lista7.codPropuesta}">' value="<c:out value="${lista7.observa}"/>" '</c:if>' '</c:forEach>'>
			</td>
		<%--	<td class="estado" align="center">
			<input type="checkbox" name="aprobo" size="30">
			</td>--%>
			<td>
			</td>
		</tr>
	</c:forEach>
<tr><td>
		<img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()">
</td></tr>	
	</table>
	</form>
	</c:if>
<%--	<c:if test="${requestScope.convEstado==null}">
	<h4 align="center">Seleccione un Año y un Número de Convocatoria</h4>
	<h4 align="center"><c:out value="${requestScope.estado}"/></h4>
	</c:if>--%>
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
