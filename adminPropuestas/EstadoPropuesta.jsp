<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function respuesta(eval,resp){
		if(confirm("      Confirmar Acción")){
			document.historico.action='<c:url value="/adminPropuestas/EstadoPropuesta.x"/>';
			document.historico.accion.value="1";
			document.historico.codEval.value=eval;
			document.historico.resp.value=resp;
			document.historico.submit();
		}
	}
	function cancela(eval){
		if(confirm("      Confirmar Acción")){
			document.historico.action='<c:url value="/adminPropuestas/EstadoPropuesta.x"/>';
			document.historico.accion.value="2";
			document.historico.codEval.value=eval;
			document.historico.submit();
		}
	}
	function docs(eval){
		if(confirm("      Confirmar Acción")){
			document.historico.action='<c:url value="/adminPropuestas/EstadoPropuesta.x"/>';
			document.historico.accion.value="11";
			document.historico.codEval.value=eval;
			document.historico.submit();
			document.getElementById("espere").style.display='';
		}
	}
	function asigna(ac){
		document.comandos.action='<c:url value="/adminPropuestas/EstadoPropuesta.x"/>';
		document.comandos.accion.value=ac;
		document.comandos.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<form name="comandos" action='<c:url value="/adminPropuestas/ListaPropuesta.x"/>'>
	<input type="hidden" name="ano" value='<c:out value="${sessionScope.ano}"/>'>
	<input type="hidden" name="num" value='<c:out value="${sessionScope.num}"/>'>
	<input type="hidden" name="activa" value='<c:out value="${sessionScope.activa}"/>'>
	<input type="hidden" name="idProp" value='<c:out value="${sessionScope.prop}"/>'/>
	<input type="hidden" name="tipoEval" value='<c:out value="${sessionScope.tipEval}"/>'/>
	<input type="hidden" name="accion" value='1'>
	<table align="left">
		<tr>
			<td><input type="image" src='<c:url value="/comp/img/Atras.gif"/>'></td>
			<c:if test='${(sessionScope.datosPropuesta.idEvalAceptado==null || sessionScope.datosPropuesta.idEvalAceptado=="") and sessionScope.loginUsuario.perfil!=12}'>
			<td>
				<input type="image" src='<c:url value="/comp/img/AsignaEvaluador.gif"/>' onclick='asigna(6)'/>
			</td>
			</c:if>
			<c:if test='${sessionScope.datosPropuesta.fechaEval!=null && sessionScope.datosPropuesta.fechaEval!=""}'>
			<td>
				<input type="image" src='<c:url value="/comp/img/VerEvaluacion.gif"/>' onclick='asigna(10)'/>
			</td>
			</c:if>
			<%-- <c:if test='${sessionScope.datosPropuesta.archivo!=null and sessionScope.datosPropuesta.archivo!=""}'>
			<td><a href='<c:url value="/Documentos/Propuestas/${sessionScope.datosPropuesta.archivo}"/>'>
					<img border="0" src='<c:url value="/comp/img/Propuesta.gif"/>'>
				</a>
			</td>
			</c:if>
			<c:if test='${sessionScope.datosPropuesta.anexos!=null and sessionScope.datosPropuesta.anexos!=""}'>
			<td><a href='<c:url value="/Documentos/Propuestas/${sessionScope.datosPropuesta.anexos}"/>'>
					<img border="0" src='<c:url value="/comp/img/Anexos.gif"/>'>
				</a>
			</td>
			</c:if> --%>
			<td>
				<input type="image" src='<c:url value="/comp/img/ResumenInscripcion.gif"/>' onclick='asigna(12)'/>
			</td>
			<td valign="top" id="espere" style="display:none;" ><img  src='<c:url value="/comp/img/cargando2.gif"/>'><b>Enviando Mensaje...</b></td>
		</tr>
	</table>
</form>
<br>
<br>
<br>
<br>
	<form name="historico" method="post">
	<table class="tablas" align="center">

		<tr>
			<td>
				<input type="hidden" name="accion" value="0"/>
				<input type="hidden" name="resp" value="0"/>
				<input type="hidden" name="codEval" value="0"/>

				<table class="tablas" width="550px">
				<caption>Estado de Propuesta de Investigación</caption>
				<tr>
					<td colspan="4">
						<table>
							<tr>
								<td class="renglones"><b>Nombre Propuesta:</b></td>
								<td><c:out value="${sessionScope.datosPropuesta.nombrePropuesta}"/><td>
							</td>
							<tr>
								<td class="renglones"><b>Investigador Principal:</b></td>
								<td><c:out value="${sessionScope.datosPropuesta.nombreInvest}"/><td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="renglones" align="center"><b>Nombre de Evaluador</b></td>
					<td class="renglones" align="center"><b>Asignación</b></td>
					<td class="renglones" align="center"><b>Respuesta</b></td>
					<td class="renglones" align="center"><b>Cancelación</b></td>
				</tr>
				<c:if test="${!empty sessionScope.datosPropuesta.listaEvaluador}">
				<c:forEach begin="0" items="${sessionScope.datosPropuesta.listaEvaluador}" var="lista" varStatus="st">
					<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if>>
						<td><c:out value="${lista.nombreEval}"/></td>
						<td align="center"><c:out value="${lista.fechaAsig}" /></td>
						<td align="center">
							<c:out value="${lista.fechaResp}" />
							<c:if test='${lista.fechaResp==null and sessionScope.loginUsuario.perfil!=12}'>
							<input type="button" value="Si" onclick='respuesta(<c:out value="${lista.codEval}"/>,1)'/>
							<input type="button" value="No" onclick='respuesta(<c:out value="${lista.codEval}"/>,2)'/>
							</c:if>
						</td>
						<td align="center">
							<c:out value="${lista.fechaCancela}"/>
							<c:if test='${sessionScope.datosPropuesta.fechaEval==null or sessionScope.datosPropuesta.fechaEval==""}'>
							<c:if test='${lista.fechaCancela!="" && sessionScope.datosPropuesta.idEvalAceptado!="" && lista.estado==1} && sessionScope.loginUsuario.perfil!=12'>
							<input type="image" src='<c:url value="/comp/img/Cancela.gif"/>' onclick='cancela(<c:out value="${lista.codEval}"/>)'/>
							</c:if>
							</c:if>
						</td>
					</tr>
				</c:forEach>
				</c:if>
				<c:if test="${empty sessionScope.datosPropuesta.listaEvaluador}">
				<tr>
					<td colspan="4" align="center"><h2>No hay Evaluadores Asignados</h2></td>
				</tr>
				</c:if>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table class="tablas" width="100%">
					<tr>
						<td colspan="2" class="renglones" align="center"><b>Enviar Mail Login</b></td>
						<td class="renglones" align="center"><b>Entrega Documentos</b></td>
						<td class="renglones" align="center"><b>Entrega Evaluación</b></td>
					</tr>
					<c:if test='${sessionScope.datosPropuesta.idEvalAceptado!=null && sessionScope.datosPropuesta.idEvalAceptado!="" && sessionScope.datosPropuesta.idEvalAceptado!=null}'>
					<tr>
						<td align="center">
							<c:out value="${sessionScope.datosPropuesta.fechaLoginPsw}"/>
						</td>

						<td align="center">&nbsp;</td>

						<td align="center">
							<c:if test='${(sessionScope.datosPropuesta.fechaDocs==null or sessionScope.datosPropuesta.fechaDocs=="") and sessionScope.loginUsuario.perfil!=12}'>
								<input type="image" src='<c:url value="/comp/img/Entregado.gif"/>' onclick='docs(<c:out value="${sessionScope.datosPropuesta.idEvalAceptado}" />,1)'>
							</c:if>
							<c:if test='${sessionScope.datosPropuesta.fechaDocs!=null or sessionScope.datosPropuesta.fechaDocs!=""}'>
								<c:out  value="${sessionScope.datosPropuesta.fechaDocs}"/></td>
							</c:if>
						<td align="center"><c:out  value="${sessionScope.datosPropuesta.fechaEval}"/></td>
					</tr>

					</c:if>
					<c:if test="${sessionScope.datosPropuesta.idEvalAceptado==null}">
					<tr>
						<td colspan="4" align="center"><h2>El evaluador no se ha asignado</h2></td>
					</tr>
					</c:if>
				</table>

			</td>
		</tr>
	</table>
	</form>
</body>
</html>