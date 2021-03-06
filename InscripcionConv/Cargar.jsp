<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page"
	class="cidc.convocatorias.obj.ParametrosOBJ" />
<c:import url="/general.jsp" />
<script>
	function guardar(item,frm){
		if(ValidarFormulario(item)){
			document.getElementById("f"+item).style.display='none';
			document.getElementById("g"+item).style.display='none';
			document.getElementById("carga"+item+"1").style.display='';
			document.getElementById("carga"+item+"2").style.display='';
			frm.submit();
		}
	}

	function ValidarFormulario(caja){
		var archi=caja.value;
        if(caja.value==""){
                alert("Debe seleccionar un Archivo para cargar");
                return false;
        }
        var ext=archi.substr(archi.lastIndexOf('.'),archi.length);
        if(!(ext==".pdf")){
        	alert("El archivo debe estar en formato PDF");
            return false;
        }
        return true;
	}

	function temina(){
		document.finaliza.submit();
	}
	
	function guardareq(caja,formulario,iddoc){
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
        		var archi=caja.value;
                if(caja.value==""){
                        alert("Debe seleccionar un Archivo para cargar");
                        return false;
                }
                var ext=archi.substr(archi.lastIndexOf('.'),archi.length);
                if(!(ext==".pdf")){
                	alert("El archivo debe estar en formato PDF");
                    return false;
                }
                return true;
        }
</script>
</head>
<body>
	<br>
	<br>
	<fieldset style="width: 550px;">
		<legend class="texto1">
			<b>Documento Propuesta Investigaci�n</b>
		</legend>
		<table>
			<tr>
				<td colspan="2" align="justify">La carga de estos documentos la
					puede hacer en cualquier momento, pero debe ser antes de la fecha
					de corte de la convocatoria, de lo contrario su propuesta no ser�
					tenida en cuenta en el proceso de evaluaci�n.</td>
			</tr>
			<tr>
				<td><c:forEach begin="0" items="${requestScope.listaDocOBJ}"
						var="listDoc" varStatus="st">

						<form action='<c:url value="/RequisitosArchivoProy.x"/>'
							name="${listDoc.nombreDocumentoRequisito}" method="post"
							enctype="multipart/form-data">
							<input type="hidden" name="id" value="${listDoc.idDocumentoRequisito}">
							<input type="hidden" name="propConvId"
								value="${sessionScope.datosConv.convId}"> <input
								type="hidden" name="DocId" value="${listDoc.idDocumentoRequisito}"> <input
								type="hidden" name="idPropuesta"
								value="${sessionScope.inscripcionConvOBJ.propId}">

							<table width="100%">
								<th colspan="2" align="center">Documentos Requeridos</th>
								<tr>
									<td colspan="2" class="renglones"><b><c:out
												value="${listDoc.nombreDocumentoRequisito}" />
								</tr>
								<tr>
									<td colspan="2"><p class="texto1j">Formato PDF.</p></td>
								</tr>
								<c:out value="${listDoc.url}" />
								<c:if test="${listDoc.url!=null}">
									<tr>
										<td class="rengVerde" align="right" colspan="2"><a
											class="lblanca"
											href='<c:url value="${listDoc.url}${listDoc.docNombre}"/>'>Ver Documento</a></td>
									</tr>
								</c:if>
								<tr>
									<td><input size="60%" type="file" name="archivo"></td>
									<td width="75px"><img
										src='<c:url value="/comp/img/Guardar.gif"/>'
										onclick="guardareq(document.${listDoc.nombreDocumentoRequisito}.archivo,document.${listDoc.nombreDocumentoRequisito},<c:out value="${listDoc.idDocumentoRequisito}" />)">
									</td>
								</tr>
							</table>
						</form>
					</c:forEach></td>
			</tr>
			<tr>
				<td align="center">
					<form action='<c:url value="/inscripcionConv/Inscripcion.x" />'
						method="post" name="finaliza">
						<input type="hidden" name="accion" value="1"> 
						<input type="hidden" name="terminar" value="si"> 
						<input type="hidden" name="idProp" value='<c:out value="${requestScope.archivos.idPropuesta}"/>'>
						<img src='<c:url value="/comp/img/Terminar.gif" />'
							onclick='temina()' />
					</form>
				</td>
			</tr>

		</table>
		<br>
	</fieldset>
	<br>
</body>
</html>
