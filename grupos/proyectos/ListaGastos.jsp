<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:useBean id="globales" class="cidc.general.obj.Globales" scope="page" />
<html>
<head>
<link type="text/css" rel="stylesheet" href="<c:url value="/comp/css/formatos.css"/>">
<c:import url="/general.jsp"/>
<script>
	function comando(id){
		document.gastos.accion.value=id;
	}

	function tipoPer(obj){
		document.frmAjaxServicios.dato.value=obj.options[obj.selectedIndex].value;
		document.frmAjaxServicios.accion.value='11';
	 	document.frmAjaxServicios.target="frameOculto";
		document.frmAjaxServicios.submit();
	}

	function ajaxProyecto(){
		//document.tipoPersona.action="<c:url value='/GestionProyectos/ProyectosInvestigador.x' />";
	    document.tipoPersona.accion.value = 25;
		document.tipoPersona.submit();
		
	}

	function tipoContrato(){
		document.tipoPersona.accion.value = 26;
		document.tipoPersona.submit();
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br/>
	<form name="tipoPersona" action='<c:url value="/GestionProyectos/ProyectosInvestigador.x"/>' method="post">
	<input type="hidden" name="accion" value="0">
		<table width="95%" align="center" class="tablas">
		<caption>Registro de gastos</caption>
			<tr>
				<td align="left" ><b>Valor Total:</b></td>
				<td align="left" colspan="3"><b><INPUT NAME="valorTotal" MAXLENGTH="25" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr>
				<td align="left"><b>Objeto del Contrato:</b></td>
				<td align="left" colspan="3"><b><textarea class="area2" class="area2" style="width: 100%;"
							name="objeto" id='objeto'></textarea></b></td>
			</tr>
			<tr>
				<td align="left"><b>Detalles:</b></td>
				<td align="left" colspan="3"><b><textarea class="area2" class="area2" style="width: 100%;"
							name="detalles" id='detalles'></textarea></b></td>
			</tr>
			<tr>
				<td align="left"><b>Valor a Pagar por Periodo:</b></td>
				<td align="left" colspan="3"><b><INPUT NAME="valorPeriodo" MAXLENGTH="25" TYPE="TEXT" VALUE=""></b></td>
			</tr>
			<tr>
				<td align="left"><b>Forma de Pago:</b></td>
				<td align="left" colspan="3"><b><INPUT NAME="FormaPago" MAXLENGTH="25" TYPE="TEXT" VALUE=""></b></td>
			</tr>
				<tr>
					<td  align="left"><b><c:out value="Tipo de Persona"/></b>
						</td>
						<td><select name="tipoPersona" onchange="">
							<option value="0">----</option>
							<option value="1">Persona Natural</option>
							<option value="2">Persona Jurídica</option>
						</select>
					</td>
					<td>
						<img src='<c:url value="/comp/img/Terminar.gif" />' onclick='ajaxProyecto()'/>
					</td>
				</tr>
			<tr>
				<td  align="left"><b><c:out value="Tipo Contratación"/></b>
					</td>
				<td width="250px"><select name="tipoContratacion" onchange="">
						<c:forEach items="${sessionScope.tipoContrato}" var="lista">
							<option value='<c:out value="${lista.id}"/>'><c:out value="${lista.nombre}" /></option>
						</c:forEach>
						
					</select>
				</td>		
				<td>
						<img src='<c:url value="/comp/img/Terminar.gif" />' onclick='tipoContrato()'/>
				</td>	
			</tr>
			<tr>
			<c:if test="${sessionScope.tipoPersona!= null and sessionScope.idContrato!= null }">
				<table width="95%" align="center" class="tablas">
					<tr>
						<td align="center" class="renglones"><b>Nombre del Documento</b></td>
						<td align="center" class="renglones" ><b>Archivo</b></td>
						<td align="center" class="renglones" ><b>Clase de Documento</b></td>
						<td align="center" class="renglones" ><b>Fecha de Solicitud</b></td>
					</tr>
					<tr>
						<td align="left"><b>Propuesta de Servicios:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					<c:if test="${sessionScope.tipoPersona==1 and sessionScope.idContrato!=3}">
					<tr>
						<td align="left"><b>Hoja de vida personal con soporte de experiencia:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.idContrato!=3 and sessionScope.idContrato!=2}">
					<tr>
						<td align="left"><b>Hoja de vida de función publica:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==1}">
					<tr>
						<td align="left"><b>fotocopia cédula de ciudadanía:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==1 and sessionScope.idContrato==1 and sessionScope.idContrato==4}">
					<tr>
						<td align="left"><b>Tarjeta profesional (si aplica):</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==1 and sessionScope.idContrato==4}">
					<tr>
						<td align="left"><b>Carné estudiantil:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==1 and sessionScope.idContrato==4}">
					<tr>
						<td align="left"><b>Recibo de pago:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==1 and sessionScope.idContrato==4}">
					<tr>
						<td align="left"><b>Certificado Estudiantil:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==1 and sessionScope.idContrato!=3 and sessionScope.idContrato!=2}">
					<tr>
						<td align="left"><b>Certificado Personeria Distrital:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.idContrato!=3 and sessionScope.idContrato!=2}">
					<tr>
						<td align="left"><b>Antecedentes Disciplianrios - Procuraduria:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.idContrato!=3 and sessionScope.idContrato!=2}">
					<tr>
						<td align="left"><b>Antecedentes Fiscales - Contraloría:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==1 and sessionScope.idContrato==1 and sessionScope.idContrato==4}">
					<tr>
						<td align="left"><b>Certificado de Afiliación a Salud:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==1 and sessionScope.idContrato==1 and sessionScope.idContrato==4}">
					<tr>
						<td align="left"><b>Certificado de Afiliación a Pensiones:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<tr>
						<td align="left"><b>Registro Único Tributario - RUT:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					<c:if test="${sessionScope.tipoPersona==2}">
					<tr>
						<td align="left"><b>Portafolio Con Soportes de Experiencia:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==2}">
					<tr>
						<td align="left"><b>Fotocopia Cédula Representante Legal:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==2}">
					<tr>
						<td align="left"><b>Certificado de Pago de Parafiscales:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==2}">
					<tr>
						<td align="left"><b>Registro Único de Proponente - Si aplica:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
					<c:if test="${sessionScope.tipoPersona==2}">
					<tr>
						<td align="left"><b>Concepto Técnico - Selección del Proveedor:</b></td>
						<td id="f2"><input type="file" name="fichero"></td>
						<td><INPUT NAME="claseDoc" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
						<td><INPUT NAME="fechaSolicitud" MAXLENGTH="25" TYPE="TEXT" VALUE=""></td>
					</tr>
					</c:if>
				</table>
				</c:if>
			</tr>
		</table>
		<table align="center" width="95%">
			<tr>
					<td align="center">				
						<img src='<c:url value="/comp/img/Terminar.gif" />' onclick='temina()'/>					
					</td>
		</table>
	</form>
	<c:if test="${!empty sessionScope.listaGastosRubro}">
        <table align="center" class="tablas" width="95%" >
        <caption >Listado de Gastos Rubro</caption>
        <tr>

	       	<th align="right" width="10px"><b>#</b></th>
	       	<th align="right" width="10px"><b>..</b></th>
        	<th align="center" width="75px"><b>Fecha</b></th>
        	<th align="center" width="75px"><b>Valor</b></th>
        	<th align="center"><b>Descripción</b></th>
        	<th align="center" width="100px"><b>Observacion</b></th>
        </tr>
	<c:forEach begin="0" items="${sessionScope.listaGastosRubro}" var="lista" varStatus="st">
		<tr>
			<td width="10px" class="listas"><c:out value="${st.count}"/></td>
			<td width="10px">
				<c:if test="${lista.tipoGasto==1}"><img src='<c:url value="/comp/img/flag0.gif"/>'></c:if>
				<c:if test="${lista.tipoGasto==-1}"><img src='<c:url value="/comp/img/flag1.gif"/>'></c:if>
			</td>
			<td width="75px" class="listas"><c:out value="${lista.fecha}"/></td>
			<td width="75px" class="listas" align="right"><c:out value="${lista.valorGasto}"/></td>
			<td class="listas"><c:out value="${lista.descripcion}"/></td>
			<td class="listas" width="100px"><c:out value="${lista.observaciones}"/></td>
		</tr>
	</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty sessionScope.listaGastosRubro}">
	<h4 align="center">No hay gastos registrados para este rubro </h4>
	</c:if>
</body>
</html>