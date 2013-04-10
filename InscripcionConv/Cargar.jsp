<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
<script>
	function guardar(item,frm){
		if(ValidarFormulario(frm)){
			document.getElementById("f"+item).style.display='none';
			document.getElementById("g"+item).style.display='none';
			document.getElementById("carga"+item+"1").style.display='';
			document.getElementById("carga"+item+"2").style.display='';
			frm.submit();
		}
	}

	function ValidarFormulario(forma){
		if(forma.fichero.value==""){
			alert("Debe seleccionar un Archivo para cargar");
			return false;
		}else{
			archi=forma.fichero.value;
			var ext=archi.substr(archi.lastIndexOf('.'),archi.length);
			if(!(ext==".pdf")){
				alert("El archivo debe ser en formato PDF");
				return false;
			}
		}
		return true;
	}

	function temina(){
		alert("At least");
		if((document.frm11.fichero!=null || document.frm11.fichero!="") && (document.frm3.fichero!=null || document.frm3.fichero!="") && (document.frm4.fichero!=null || document.frm4.fichero!="") && (document.frm5.fichero!=null || document.frm5.fichero!=""))
			document.finaliza.submit();
		else
			alert("Para terminar debe cargar los documentos faltantes");
	}

</script>
</head>
<body>
<br><br>
	<fieldset style="width:550px;">
    	<legend class="texto1"><b>Documento Propuesta Investigación </b></legend>
			<table>
				<tr>
					<td colspan="2" align="justify">						
						La carga de estos documentos la puede hacer en cualquier momento, pero debe ser antes de la fecha de corte de la convocatoria, de lo contrario su propuesta no será tenida en cuenta en el proceso de evaluación.
					</td>
				</tr>
				<tr>
					<td>
					<br>
						<form action='<c:url value="/inscripcionConv/Propuesta.x"/>' name="frm11" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="6">
						<input type="hidden" name="idProp" value='<c:out value="${requestScope.archivos.idPropuesta}" default="${sessionScope.inscripcionConvOBJ.propId}"/>'>
							<table width="100%">
								<tr>
									<td colspan="2" class="renglones"><b>Documento completo de propuesta de investigación</td>
								</tr>
								<tr>
									<td colspan="2"><p class="texto1j">Por favor seleccione el documento correspondiente a la propuesta de investigación y anexos que desea inscribir... (Tamaño máximo del archivo: 10 MB). <br>Diligencie las declaraciones de impacto ambiental, pertinencia  social y aporte a la educación.</p><br>
									</td>
								</tr>
								<tr>
									<c:if test="${requestScope.archivos.docCompleto!=null}">
									<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Propuestas/${requestScope.archivos.docCompleto}" />'>Ver Documento</a></td>
								</c:if>
								</tr>
								<tr>
									<td id="f1"><input size="60%" type="file" name="fichero"></td>
									<td id="g1" width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(1,document.frm11)"></td>

								<td id="carga11" style="display:none;"><h5>Un Momento por favor....Almacenando Archivo</h5></td>
								<td id="carga12" style="display:none;"><img  src='<c:url value="/comp/img/cargando.gif"/>'></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				<c:if test="${sessionScope.datosConv.convAno==2013 and sessionScope.datosConv.convNumero==6}">
				<tr>
					<td>
						<form action='<c:url value="/inscripcionConv/Propuesta.x"/>' name="frm2" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="7">
						<input type="hidden" name="idProp" value='<c:out value="${requestScope.archivos.idPropuesta}" default="${sessionScope.inscripcionConvOBJ.propId}"/>'>
							<table width="100%">
								<tr>
									<td colspan="2" class="renglones"><b>Documentos anexos</b></td>
								</tr>
								<tr>
									<td colspan="2"><p class="texto1j">Un solo documento en formato PDF con los siguientes Datos: Carta de pertinencia social, Impacto ambiental, aporte a la educación <c:if test="${sessionScope.datosConv.convAno==2013 and sessionScope.datosConv.convNumero!=6}">y Acta de posesión (El Acta de posesión es ÚNICAMENTE para propuestas que se inscriban en la convocatoria 2009-7)</c:if></p></td>
								</tr>
								<tr>
									<c:if test="${requestScope.archivos.docAnexo!=null}">
									<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Propuestas/${requestScope.archivos.docAnexo}" />'>Ver Documento</a></td>
								</c:if>
								</tr>
								<tr>
									<td id="f2"><input size="60%" type="file" name="fichero"></td>
									<td id="g2" width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(2,document.frm2)"></td>
								<td id="carga21" style="display:none;"><h5>Un Momento por favor....Almacenando Archivo</h5></td>
								<td id="carga22" style="display:none;"><img  src='<c:url value="/comp/img/cargando.gif"/>'></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
				</c:if>
				<c:if test="${sessionScope.datosConv.convAno==2013 and (sessionScope.datosConv.convNumero==5 or sessionScope.datosConv.convNumero==7 or sessionScope.datosConv.convNumero==12)}">
				<tr>
					<td>
						<form action='<c:url value="/inscripcionConv/Propuesta.x"/>' name="frm3" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="8">
						<input type="hidden" name="idProp" value='<c:out value="${requestScope.archivos.idPropuesta}" default="${sessionScope.inscripcionConvOBJ.propId}"/>'>
							<table width="100%">
								<th colspan="2" align="left">Aval grupo de investigación</th>
								<tr>
									<td colspan="2"><p class="texto1j">Carta de aval del grupo o semillero de investigación para la presente convocatoria, firmada por el director o tutor según corresponda.</p></td>
								</tr>
								<tr>
								<c:if test="${requestScope.archivos.docAvalGrupo!=null}">
									<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Propuestas/${requestScope.archivos.docAvalGrupo}" />'>Ver Documento</a></td>
								</c:if>
								</tr>
								<tr>
									<td id="f3"><input size="60%" type="file" name="fichero"></td>
									<td id="g3" width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(3,document.frm3)"></td>
								<td id="carga31" style="display:none;"><h5>Un Momento por favor....Almacenando Archivo</h5></td>
								<td id="carga32" style="display:none;"><img  src='<c:url value="/comp/img/cargando.gif"/>'></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>				
				</c:if>
				<c:if test="${sessionScope.datosConv.convAno==2013 and (sessionScope.datosConv.convNumero==5 or sessionScope.datosConv.convNumero==6 or sessionScope.datosConv.convNumero==7 or sessionScope.datosConv.convNumero==12)}">
				<tr>
					<td>
						<form action='<c:url value="/inscripcionConv/Propuesta.x"/>' name="frm3" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="8">
						<input type="hidden" name="idProp" value='<c:out value="${requestScope.archivos.idPropuesta}" default="${sessionScope.inscripcionConvOBJ.propId}"/>'>
							<table width="100%">
								<th colspan="2" align="left">Actas de su institucionalización</th>
								<tr>
									<td colspan="2"><p class="texto1j">Anexar actas de institucionalización ante el Consejo de la Facultad de Ciencias y Educación y ante el CIDC, o evidencias que el proceso de institucionalización del
																		proyecto de tesis ante el Consejo de la Facultad de Ciencias y Educación y ante el CIDC ya se ha solicitado.(Formato PDF)</p></td>
								</tr>
								<tr>
								<c:if test="${requestScope.archivos.docAvalGrupo!=null}">
									<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Propuestas/${requestScope.archivos.docAvalGrupo}" />'>Ver Documento</a></td>
								</c:if>
								</tr>
								<tr>
									<td id="f3"><input size="60%" type="file" name="fichero"></td>
									<td id="g3" width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(3,document.frm3)"></td>
								<td id="carga31" style="display:none;"><h5>Un Momento por favor....Almacenando Archivo</h5></td>
								<td id="carga32" style="display:none;"><img  src='<c:url value="/comp/img/cargando.gif"/>'></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>				
				</c:if>	
				<c:if test="${sessionScope.datosConv.convAno==2013 and (sessionScope.datosConv.convNumero==5 or sessionScope.datosConv.convNumero==7)}">
				<tr>
					<td>
						<form action='<c:url value="/inscripcionConv/Propuesta.x"/>' name="frm4" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="9">
						<input type="hidden" name="idProp" value='<c:out value="${requestScope.archivos.idPropuesta}" default="${sessionScope.inscripcionConvOBJ.propId}"/>'>
							<table width="100%">
								<th colspan="2" align="left">Carta director del proyecto.</th>
								<tr>
									<td colspan="2"><p class="texto1j">Carta del director del proyecto de grado en donde manifieste la intención de participar en la presente convocatoria.</p></td>
								</tr>
								<tr>
								<c:if test="${requestScope.archivos.docAvalDir!=null}">
									<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Propuestas/${requestScope.archivos.docAvalDir}" />'>Ver Documento</a></td>
								</c:if>
								</tr>
								<tr>
									<td id="f4"><input size="60%" type="file" name="fichero"></td>
									<td id="g4" width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(4,document.frm4)"></td>
								<td id="carga41" style="display:none;"><h5>Un Momento por favor....Almacenando Archivo</h5></td>
								<td id="carga42" style="display:none;"><img  src='<c:url value="/comp/img/cargando.gif"/>'></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>				
				</c:if>				
				<c:if test="${sessionScope.datosConv.convAno==2013 and (sessionScope.datosConv.convNumero==5  or sessionScope.datosConv.convNumero==7)}">
				<tr>
					<td>
						<form action='<c:url value="/inscripcionConv/Propuesta.x"/>' name="frm5" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="10">
						<input type="hidden" name="idProp" value='<c:out value="${requestScope.archivos.idPropuesta}" default="${sessionScope.inscripcionConvOBJ.propId}"/>'>
							<table width="100%">
								<th colspan="2" align="left">Certificado Consejo Cirrucular.</th>
								<tr>
									<td colspan="2"><p class="texto1j">Certificado de aprobación del anteproyecto de grado en modalidad investigación, emitido por el Consejo Curricular al que pertenece(n) el (los) estudiante(s).</p></td>
								</tr>
								<tr>
									<c:if test="${requestScope.archivos.docCerCurr!=null}">
									<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Propuestas/${requestScope.archivos.docCerCurr}" />'>Ver Documento</a></td>
									</c:if>
								</tr>
								<tr>
									<td id="f5"><input size="60%" type="file" name="fichero"></td>
									<td id="g5" width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar(5,document.frm5)"></td>
								<td id="carga51" style="display:none;"><h5>Un Momento por favor....Almacenando Archivo</h5></td>
								<td id="carga52" style="display:none;"><img  src='<c:url value="/comp/img/cargando.gif"/>'></td>
								</tr>
							</table>
						</form>
					</td>
				</tr>				
				</c:if>				
				<tr>
					<td align="center">
					<form action='<c:url value="/inscripcionConv/Inscripcion.x" />' method="post" name="finaliza">
						<input type="hidden" name="accion" value="1">
						<input type="hidden" name="terminar" value="si">
						<input type="hidden" name="idProp" value='<c:out value="${requestScope.archivos.idPropuesta}"/>'>				
						<img src='<c:url value="/comp/img/Terminar.gif" />' onclick='temina()'/>					
					</form>
					</td>
				</tr>
			</table>
			<br>
	</fieldset>
	<br>
</body>
</html> 