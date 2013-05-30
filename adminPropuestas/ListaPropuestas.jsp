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

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<form name="filtro" method="post" action='<c:url value="/adminPropuestas/ListaPropuesta.x"/>'>
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
						<option value="6" <c:if test="${sessionScope.num==6}">selected</c:if>>6</option>
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
	<form name="listado" method="post">
		<input type="hidden" name="accion" value="0"/>
		<input type="hidden" name="prop" value="0">
		<input type="hidden" name="activa" value="0">
		<input type="hidden" name="tipEval" value="0">
        <table class="tablas" width="98%" >
        <caption >Listado de Propuestas Inscritas</caption>
        <tr>
	       	<th align="center" width="25px"><b>#</b></th>
	       	<th align="center" width="25px"><b>Cod</b></th>
        	<td width="350px" class="renglones" align="center"><b>Nombre de Propuesta</b></th>
        	<th align="center" colspan="3"><b>Estado</b></th>
        	<th align="center" width="15px"><b></b></th>
        </tr>
        <tr>
        	<th colspan="3">&nbsp;</th>
        	<th align="center"><b>Eval-Int</b></th>
        	<th align="center"><b>Eval-Ext1</b></th>
        	<c:if test="${sessionScope.ano==2012}">
        	<th align="center"><b>Eval-Ext2</b></th>
        	</c:if>
        	<th align="center"><b>Eval-Comit</b></th>
        </tr>
	<c:forEach begin="0" items="${sessionScope.listaPropOBJ}" var="lista" varStatus="st">
		<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
			<th align="center" width="25px"><b><c:out value="${st.count}"/></b></th>
			<td class="listas" width="25px"><c:out value="${lista.codPropuesta}"/></td>
			<td width="350px" class="listas"><c:out value="${lista.nomPropuesta}"/></td>
			<td class="estado" align="center">
				<c:if test="${lista.estadoEvalInt==1}"><img src='<c:url value="/comp/img/est0.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,1)'></c:if>
				<c:if test="${lista.estadoEvalInt==2}"><img src='<c:url value="/comp/img/est1.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,1)'></c:if>
				<c:if test="${lista.estadoEvalInt==3}"><img src='<c:url value="/comp/img/est2.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,1)'></c:if>
				<c:if test="${lista.estadoEvalInt==4}"><img src='<c:url value="/comp/img/est3.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,1)'></c:if>
				<c:if test="${lista.estadoEvalInt==5}"><img src='<c:url value="/comp/img/est4.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,1)'></c:if>
				<c:if test="${lista.estadoEvalInt==6}"><img src='<c:url value="/comp/img/est5.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,1)'></c:if>
			</td>
			<td class="estado" align="center">
				<c:if test="${lista.estadoEvalExt==1}"><img src='<c:url value="/comp/img/est0.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,2)'></c:if>
				<c:if test="${lista.estadoEvalExt==2}"><img src='<c:url value="/comp/img/est1.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,2)'></c:if>
				<c:if test="${lista.estadoEvalExt==3}"><img src='<c:url value="/comp/img/est2.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,2)'></c:if>
				<c:if test="${lista.estadoEvalExt==4}"><img src='<c:url value="/comp/img/est3.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,2)'></c:if>
				<c:if test="${lista.estadoEvalExt==5}"><img src='<c:url value="/comp/img/est4.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,2)'></c:if>
				<c:if test="${lista.estadoEvalExt==6}"><img src='<c:url value="/comp/img/est5.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,2)'></c:if>
			</td>
			<c:if test="${sessionScope.ano==2012}">			
			<td class="estado" align="center">
				<c:if test="${lista.estadoEvalExt==1}"><img src='<c:url value="/comp/img/est0.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,4)'></c:if>
				<c:if test="${lista.estadoEvalExt==2}"><img src='<c:url value="/comp/img/est1.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,4)'></c:if>
				<c:if test="${lista.estadoEvalExt==3}"><img src='<c:url value="/comp/img/est2.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,4)'></c:if>
				<c:if test="${lista.estadoEvalExt==4}"><img src='<c:url value="/comp/img/est3.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,4)'></c:if>
				<c:if test="${lista.estadoEvalExt==5}"><img src='<c:url value="/comp/img/est4.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,4)'></c:if>
				<c:if test="${lista.estadoEvalExt==6}"><img src='<c:url value="/comp/img/est5.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,4)'></c:if>
			</td>
			</c:if>
			<td class="estado" align="center">
				<c:if test="${lista.estadoEvalComit==1}">
					<img src='<c:url value="/comp/img/est0.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,3)'>
				</c:if>
				<%if(loginUsuario.isPerfil("3")||loginUsuario.isPerfil("13")||loginUsuario.isPerfil("4")){ %>
					<c:if test="${lista.estadoEvalComit==2}"><img src='<c:url value="/comp/img/est6.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,3)'></c:if>
					<c:if test="${lista.estadoEvalComit==3}"><img src='<c:url value="/comp/img/est7.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,3)'></c:if>
					<c:if test="${lista.estadoEvalComit==4}"><img src='<c:url value="/comp/img/est8.gif"/>' onclick='ver(<c:out value="${lista.codPropuesta}"/>,3)'></c:if>
					<c:if test="${lista.estadoEvalComit==5}"><b onclick='ver(<c:out value="${lista.codPropuesta}"/>,3)'>No evaluado</b></c:if>
				<%} %>
			</td>
			<td align="center" width="15px">
			<c:if test="${lista.propActiva}">
				<c:if test="${lista.estadoEvalComit!=3 and lista.estadoEvalComit!=4 and lista.estadoEvalComit!=5}"><img src='<c:url value="/comp/img/equis1.png"/>' onclick='activar(<c:out value="${lista.codPropuesta}"/>,2)'></c:if>
			</c:if>
			<c:if test="${!lista.propActiva}">
				<img src='<c:url value="/comp/img/esc.png"/>' onclick='activar(<c:out value="${lista.codPropuesta}"/>,1)'>
			</c:if>
			</td>
		</tr>
	</c:forEach>
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
