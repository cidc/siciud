<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
<script>	
	function ver(ac,id){
		document.nuevo.accion.value=ac;
		document.nuevo.id.value=id;
		//document.lista.estado.value=st;
		document.nuevo.action='<c:url value="/movilidad/adminMovilidad.x"/>';
		document.nuevo.submit();
	}
	function guardar(caja,formulario,iddoc){
		alert(caja.value);
                if(ValidarFormularioDoc(caja)){
			formulario.DocId.value=iddoc;
                        formulario.submit();
                }
        }
        
        function ValidarFormularioDoc(caja){
                if(caja.value==""){
                        alert("Debe seleccionar un Archivo para cargar");
                        return false;
                }
                return true;
        }
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
	<form name="nuevo" method="post" action='<c:url value="/movilidad/adminMovilidad.x"/>'>
		<input type="hidden" name="accion" value="2">
		<input type="image" src='<c:url value="/comp/img/Atras.gif"/>'>
	<fieldset style="width:590px;">
		<div align="center">		
			<table class="tablas" width="100%">
				<tr>
					<td>
						<img src='<c:url value="/comp/img/1.gif"/>' onclick='ver(9,<c:out value="${movilidad.idPropuesta}" />)'>
					</td>
					<td>
						<img src='<c:url value="/comp/img/2.gif"/>' onclick='ver(10,<c:out value="${movilidad.idPropuesta}" />)'>
					</td>
					<td>
						<img src='<c:url value="/comp/img/3.gif"/>' onclick='ver(11,<c:out value="${movilidad.idPropuesta}" />)'>
					</td>		
				</tr>
			</table>		
		</div>
	</fieldset>	
	</form>	

<fieldset style="width:590px;">
	<legend>Datos Resumen de Evento y Ponencia</legend>
		<table class="tablas" width="100%">
			<tr>
				<td class="renglones" width="200px"><b>Nombre del Investigador</b></td>
				<td><p style="text-transform:lowercase;"><c:out value="${sessionScope.persona.nombre}" /></p></td>
			</tr>
			<tr>
				<td class="renglones" width="200px"><b>Grupo/Semillero de Investigación</b></td>
				<td><p style="text-transform:lowercase;"><c:out value="${sessionScope.persona.nombGrupo}" /></p></td>
			</tr>
			<tr>
				<td colspan="2">
					<table width="100%">
						<tr>
							<td class="renglones"><b>Tipo de evento</b></td>
							<td><c:out value="${sessionScope.movilidad.tipoLetra}" /></td>
							<td class="renglones"><b>Lugar del Evento</b></td>
							<td><c:out value="${sessionScope.movilidad.pais} - ${sessionScope.movilidad.ciudad}" /></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr>

			</tr>
			<tr><td colspan="4" class="renglones"><b>Nombre del evento al que desea asistir</b></td></tr>
			<tr><td colspan="4"><c:out value="${sessionScope.movilidad.nombreEvento}" /></td></tr>
			<tr><td colspan="4" class="renglones"><b>Institución organizadora</b></td></tr>
			<tr><td colspan="4"><c:out value="${sessionScope.movilidad.institucion}" /></td></tr>
			<tr><td colspan="4" class="renglones"><b>Nombre de ponencia aprobada</b></td></tr>
			<tr><td colspan="4"><c:out value="${sessionScope.movilidad.nombrePonencia}" /></td></tr>
			<tr><td colspan="4" class="renglones"><b>Nombre de proyecto de investigación</b></td></tr>
			<tr><td colspan="4"><c:out value="${sessionScope.movilidad.proyectoinv}" /></td></tr>
			<tr><td colspan="4" class="renglones"><b>Nombre autores de ponencia</b></td></tr>
			<tr><td colspan="4"><c:out value="${sessionScope.movilidad.nombreAutores}" /></td></tr>
		</table>
	</fieldset>
	<br>
<fieldset style="width:590px;">
	<legend>Documentos requeridos </legend>
	<table class="tablas" width="100%">
                <tr>
                        <td>
			<c:forEach begin="0" items="${sessionScope.listaDocOBJ}" var="lista2" varStatus="st">

                                <form action='<c:url value="/RequisitosArchivo.x"/>' name="${lista2.docNombre}" method="post" enctype="multipart/form-data">
                                <input type="hidden" name="id" value="${lista2.codigo}">
                                <input type="hidden" name="propConvId" value="${sessionScope.datosConv.convId}">
                                <input type="hidden" name="DocId" value="${lista2.codigo}">
                                <input type="hidden" name="idPropuesta" value="<c:out value="${movilidad.idPropuesta}"/>">

                                        <table width="100%">
					 <th colspan="2" align="center">Documentos Requeridos</th>
                                                <tr>
                                                        <td colspan="2" class="renglones"><b><c:out value="${lista2.docNombre}"/>-<c:out value="${sessionScope.datosConv.convId}"/>-<c:out value="${lista2.codigo}"/></b></td>
                                                </tr>
                                                <tr>
                                                        <td colspan="2"><p class="texto1j">Formato PDF</p></td>
                                                </tr>
                                                <tr>
                                                        <td><input size="60%" type="file" name="archivo"></td>
                                                        <td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(document.${lista2.docNombre}.archivo,document.${lista2.docNombre},<c:out value="${lista2.codigo}" />)"></td>
                                                        <c:if test="${lista2.nombreDocumentoRequisito!=null}">
														<tr>
															<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${lista2.nombreDocumentoRequisito}" />'>Ver Documento</a></td>
														</tr>
													</c:if>
												</tr>			
                                        </table>
                                </form>
				</c:forEach>
                        </td>
                </tr>

<%-- --%>	
	</table>
	<br>
		<form name="nuevo1" method="post" action='<c:url value="/movilidad/adminMovilidad.x"/>'>
			<input type="hidden" name="accion" value="2">
			<table align="center">
				<tr>
					<td>
						<input type="image" src='<c:url value="/comp/img/Terminar.gif"/>'>
					</td>
					<td valign="middle"><b>Paso 3 de 3</b></td>
				</tr>
			</table>
		</form>
</fieldset>
	<br>
</body>
</html>
