<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
<script>

	function info(id){

		if(id==6){
			alert("Carta escaneada del aval por parte del Consejo de la facultad");
		}
		if(id==7){
			alert("Carta escaneada del aval por parte del Consejo Académico");
		}
		if(id==8){
			alert("Estudiantes: Carta escaneada que certifique haber estado entre los 10 primeros promedios de la carrera; Profesores: Certificado de Evaluación Docente");
		}
	}

	function ValidarFormulario(item,frm){
		var archi=item.value;
		if(archi==""){
			alert("Debe seleccionar un Archivo para cargar");
			return false;
		}else{
			var ext=archi.substr(archi.lastIndexOf('.'),archi.length);
			if(!(ext==".pdf"))
				alert("El archivo debe ser en formato PDF");
			else
				frm.submit();
		}
	}
	function ver(ac,id){
		document.nuevo.accion.value=ac;
		document.nuevo.id.value=id;
		//document.lista.estado.value=st;
		document.nuevo.action='<c:url value="/movilidad/adminMovilidad.x"/>';
		document.nuevo.submit();
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
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm1" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="1">
				<input type="hidden" name="idmovilidad" value=''>
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Carta aval Grupo / Semillero de Investigación</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">Carta de aval del Grupo o Semillero de investigación firmada por el Director o Tutor según corresponda, en donde se exprese que el estudiante o docente no tiene adjudicado ningún tipo de apoyo económico de otra dependencia de la Universidad, ni que lo está tramitando; NO se tendrán en cuenta cartas con visto bueno. (Formato PDF)</p></td>
						</tr>
						<tr>
							<td><input size="60%" type="file" name="arc1"></td>
							<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm1.arc1,frm1)"></td>
						<c:if test="${sessionScope.movilidad.archivoAval!=null}">
							<tr>
								<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoAval}" />'>Ver Documento</a></td>
							</tr>
						</c:if>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm2" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="2">
				<input type="hidden" name="idmovilidad" value=''>
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Carta de aceptación de la organización del evento como ponente.</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">Documento entregado por el organizador del evento donde certifique la aceptación de la presentación de su ponencia en el evento como ponente. (formato PDF)</p></td>
						</tr>
						<tr>
							<td><input size="60%" type="file" name="arc2"></td>
							<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm2.arc2,frm2)"></td>
						<c:if test="${sessionScope.movilidad.archivoAceptacion!=null}">
							<tr>
							<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoAceptacion}" />'>Ver Documento</a></td>
							</tr>
						</c:if>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		<tr>
			<td>
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm3" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="3">
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Artículo aprobado.</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">El Artículo o ponencia relacionada con el proyecto de investigación que será presentado en el evento. (formato PDF)</p></td>
						</tr>
						<tr>
							<td><input size="60%" type="file" name="arc3"></td>
							<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm3.arc3,frm3)"></td>
						<c:if test="${sessionScope.movilidad.archivoResumen!=null}">
							<tr><td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoResumen}" />'>Ver Documento</a></td></tr>
						</c:if>
						</tr>
					</table>
				</form>
			</td>
		</tr>
<!--
		<tr>
			<td>
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm4" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="4">
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Carta Autorizaciï¿½n Consejo Curricular para participar en la convocatoria</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">Carta escaneada del aval por parte del Consejo Curricular </p></td>
						</tr>
						<tr>
						<c:if test='${sessionScope.movilidad.archivoProyCurri==null or sessionScope.movilidad.archivoProyCurri==""}'>
							<td><input size="60%" type="file" name="arc4"></td>
							<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm4.arc4,frm4)"></td>
						</c:if>
						<c:if test="${sessionScope.movilidad.archivoProyCurri!=null}">
							<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoProyCurri}" />'>Ver Documento</a></td>
						</c:if>
						</tr>
					</table>
				</form>
			</td>
		</tr>
-->
		<c:if test='${sessionScope.persona.papel!=3 and sessionScope.persona.papel!=8}'>
		<tr>
			<td>
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm5" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="5">
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Carta aval de jefe inmediato para participar en la convocatoria</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">Carta escaneada de aval del Jefe Inmediato (Rector, Decano(a) o Vicerrector, según el cargo desempeñado por el profesor en el momento del registro de la propuesta) y en donde se exprese el conocimiento de la solicitud del profesor y la autorización de salida de la ciudad por los días del evento.</p></td>
						</tr>
						<tr>
							<td><input size="60%" type="file" name="arc5"></td>
							<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm5.arc5,frm5)"></td>
						<c:if test="${sessionScope.movilidad.archivoDecanatura!=null}">
							<tr><td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoDecanatura}" />'>Ver Documento</a></td></tr>
						</c:if>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		</c:if>
		<c:if test='${sessionScope.persona.papel==3 or sessionScope.persona.papel==8}'>
		<tr>
			<td>
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm6" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="6">
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Carta de aval del Consejo de Facultad.</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">Carta escaneada dede aval del Consejo de Facultad (previo aval del Consejo Curricular) en donde se exprese el conocimiento de la solicitud del estudiante y la autorización de la salida de la ciudad por los días del evento.</p></td>
						</tr>
						<tr>
							<td><input size="60%" type="file" name="arc6"></td>
							<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm6.arc6,frm6)"></td>
						<c:if test="${sessionScope.movilidad.archivoConsFac!=null}">
							<tr>
							<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoConsFac}" />'>Ver Documento</a></td>
							</tr>
						</c:if>
						</tr>
					</table>
				</form>
			</td>
		</tr>
		</c:if>
		<c:if test='${sessionScope.persona.papel!=3 and sessionScope.persona.papel!=8}'>
		<tr>		
			<td>
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm8" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="8">
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Resultados Excelencia Académica</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">Resultados de excelencia académica del último año (Opcional teniendo en cuenta que otorga hasta 5 puntos adicionales).</p></td>
						</tr>
						<tr>
								<td><input size="60%" type="file" name="arc8"></td>
								<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm8.arc8,frm8)"></td>
							<c:if test="${sessionScope.movilidad.archivoExcelencia!=null}">
								<tr>
								<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoExcelencia}" />'>Ver Documento</a></td>
								</tr>
							</c:if>
						</tr>
					</table>						
					</form>	
				</td>					
			</tr>
		</c:if>
			<tr>
				<td>
					<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm9" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="9">
						<table width="100%">
							<tr>
								<td colspan="2" class="renglones"><b>Certificacion de participacion en activdades de investigacion en el grupo/semillero</b></td>
							</tr>
							<tr>
								<td colspan="2"><p class="texto1j">Carta escaneada firmada por el Director del Grupo o Lider del semillero donde se certifique la participación del investigador en actividades de investigación dentro del grupo o semillero según el caso.</p></td>
							</tr>
							<tr>
									<td><input size="60%" type="file" name="arc9"></td>
									<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm9.arc9,frm9)"></td>
								<c:if test="${sessionScope.movilidad.archivoCertificaciones!=null}">
										<tr>
										<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoCertificaciones}" />'>Ver Documento</a></td>
										</tr>
								</c:if>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm10" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="10">
						<table width="100%">
							<tr>
								<td colspan="2" class="renglones"><b>Certificacion de participacion en activdades de investigacion del CIDC</b></td>
							</tr>
							<tr>
								<td colspan="2"><p class="texto1j">Carta escaneada donde certifique la participación en actividades avaladas por el CIDC diferentes a movilidad certificadas por el CIDC o por el Coordinador de la Unidades de Investigación de las Facultades.</p></td>
							</tr>
							<tr>
									<td><input size="60%" type="file" name="arc10"></td>
									<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm10.arc10,frm10)"></td>
								<c:if test="${sessionScope.movilidad.archivoCertificacionesCIDC!=null}">
										<tr>
										<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoCertificacionesCIDC}" />'>Ver Documento</a></td>
										</tr>
								</c:if>
							</tr>
						</table>
					</form>
				</td>
			</tr>
			<tr>
				<td>
					<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm11" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="11">
						<table width="100%">
							<tr>
								<td colspan="2" class="renglones"><b>Resultados Obtenidos</b></td>
							</tr>
							<tr>
								<td colspan="2"><p class="texto1j">Documento escaneado donde certifique resultados obtenidos de agendas de cooperación de movilidad anteriores, reflejados en convenios, profesores visitantes, conferencistas, conformación de redes, entre otros (Un solo documento en formato PDF).</p></td>
							</tr>
							<tr>
									<td><input size="60%" type="file" name="arc11"></td>
									<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm11.arc11,frm11)"></td>
								<c:if test="${sessionScope.movilidad.archivoResultados!=null}">
										<tr>
											<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoResultados}" />'>Ver Documento</a></td>
										</tr>
								</c:if>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		<c:if test='${sessionScope.persona.papel==3 and sessionScope.persona.papel==8}'>
		<tr>		
			<td>
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm12" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="12">
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Carta donde exprese no tener adjudicado otro apoyo económico en la Universidad</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">Carta escaneada y firmada por el Director o Tutor según corresponda donde exprese que no tiene adjudicado ningún tipo de apoyo económico de otra dependencia de la universidad. Si se llega a contar con apoyo económico de otra dependencia de la universidad, se deberá aclarar los montos aprobados y la dependencia que lo apoya. (Formato PDF)</p></td>
						</tr>
						<tr>
								<td><input size="60%" type="file" name="arc12"></td>
								<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm12.arc12,frm12)"></td>
							<c:if test="${sessionScope.movilidad.archivoApoyos!=null}">
								<tr>
								<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoApoyos}" />'>Ver Documento</a></td>
								</tr>
							</c:if>
						</tr>
					</table>						
					</form>	
				</td>					
			</tr>
		</c:if>	
		<tr>
			<td>
				<form action='<c:url value="/movilidad/ArchivosMovilidad.x"/>' name="frm13" method="post" enctype="multipart/form-data">
				<input type="hidden" name="id" value="13">
					<table width="100%">
						<tr>
							<td colspan="2" class="renglones"><b>Formato de Productividad Académica e Investigativa.</b></td>
						</tr>
						<tr>
							<td colspan="2"><p class="texto1j">Formato diligenciado con la productividad académica e investigativa registrada en el CvLac de COLCIENCIAS. (formato PDF)</p></td>
						</tr>
						<tr>
							<td><input size="60%" type="file" name="arc13"></td>
							<td width="75px"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="ValidarFormulario(document.frm13.arc13,frm13)"></td>
						<c:if test="${sessionScope.movilidad.archivoProduccion!=null}">
							<tr>
								<td class="rengVerde" align="right" colspan="2"><a class="lblanca" href='<c:url value="/Documentos/Movilidad/${sessionScope.movilidad.archivoProduccion}" />'>Ver Documento</a></td>
							</tr>
						</c:if>
						</tr>
					</table>
				</form>
			</td>
		</tr>		
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