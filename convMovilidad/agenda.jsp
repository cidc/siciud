<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<html>
<head>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp" />
<script>

	var nav4=window.Event ? true : false; 

	function numeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}
	function partEvent(combo){
		var cant=combo.selectedIndex;
		document.getElementById("partEv0").style.display='none';
		for (var i=1;i<10;i++){
			document.getElementById("partEv"+i).style.display='none';
			document.nuevo.partiEvent[i-1].disabled=true;
		}
		if(cant>0){
	//	alert(cant);
			document.getElementById("tablero").style.display='';
			for (var i=0;i<cant;i++){
				document.nuevo.partiEvent[i].disabled=false;
				document.getElementById("partEv"+i).style.display='';
			}
		}
	}
	function info(id){
		if(id==4){
			alert("Seleccione la cantidad de eventos financiados por el Centro de Investigaciones a los cuales usted ha asistido y mencione los nombres o descripciï¿½n de dichos eventos");
		}
		if(id==5){
			alert("Compromisos del Investigador");
		}
		if(id==6){
			alert("Beneficios para el Grupo de Investigación");
		}
		if(id==7){
			alert("Interés Institucional");
		}
	}

	function ajaxProyecto(obj){
		document.frmAjaxProyecto.dato.value=obj.options[obj.selectedIndex].value;
		document.frmAjaxProyecto.para.value='1';
	 	document.frmAjaxProyecto.target="frameOculto";
		document.frmAjaxProyecto.submit();
	}

	function guardar(){
		if(validar()){
			document.nuevo.accion.value=8;
			document.nuevo.action='<c:url value="/convMovilidad/llenar.jsp"/>';			
			document.nuevo.submit();
		}
	}

	function validar(){
		mensaje="";

		if(document.nuevo.compromisos1.value.length<250){
				mensaje=mensaje+"\n-) Justificacion de la participacion del investigador en el evento (No menos de 250 caracteres)";
		}
		if(document.nuevo.compromisos1.value==""){
			mensaje=mensaje+"\n-) Debe ingresar una Justificacion de la participacion del investigador en el evento (No menos de 250 caracteres)";
		}
		/*if(document.nuevo.compromisos2.value.length<80){
				mensaje=mensaje+"\n-) Compromisos del Investigador parte 2(Mï¿½nimo 80 caracteres)";
		}*/
		if(document.nuevo.compromisos3.value.length<250){
				mensaje=mensaje+"\n-) Agenda de actividades (No menos de 250 caracteres)";
		}
		if(document.nuevo.compromisos3.value==""){
			mensaje=mensaje+"\n-) Debe ingresar una Agenda de actividades (No menos de 250 caracteres)";
		}		
		/*if(document.nuevo.compromisos4.value.length<80){
				mensaje=mensaje+"\n-) Compromisos del Investigador parte 4(Mï¿½nimo 80 caracteres)";
		}
		if(document.nuevo.beneficiosGrupo1.value.length<200){
			mensaje=mensaje+"\n-) Beneficios para el Grupo de Investigaciï¿½n parte 1(Mï¿½nimo 200 caracteres)";
		}
		if(document.nuevo.beneficiosGrupo2.value.length<200){
			mensaje=mensaje+"\n-) Beneficios para el Grupo de Investigaciï¿½n parte 2(Mï¿½nimo 200 caracteres)";
		}
		if(document.nuevo.beneficiosGrupo3.value.length<80){
			mensaje=mensaje+"\n-) Beneficios para el Grupo de Investigaciï¿½n parte 3(Mï¿½nimo 80 caracteres)";
		}
		if(document.nuevo.beneficiosGrupo4.value.length<200){
			mensaje=mensaje+"\n-) Beneficios para el Grupo de Investigaciï¿½n parte 4(Mï¿½nimo 200 caracteres)";
		}
		if(document.nuevo.beneficiosGrupo5.value.length<80){
			mensaje=mensaje+"\n-) Beneficios para el Grupo de Investigaciï¿½n parte 5(Mï¿½nimo 80 caracteres)";
		}
		if(document.nuevo.interesInsti1.value.length<200){
			mensaje=mensaje+"\n-) Interï¿½s Institucional parte 1(Mï¿½nimo 200 caracteres)";
		}
		if(document.nuevo.interesInsti2.value.length<200){
			mensaje=mensaje+"\n-) Interï¿½s Institucional parte 2(Mï¿½nimo 200 caracteres)";
		}
		if(document.nuevo.interesInsti3.value.length<200){
			mensaje=mensaje+"\n-) Interï¿½s Institucional parte 3(Mï¿½nimo 200 caracteres)";
		}
		if(document.nuevo.interesInsti4.value.length<200){
			mensaje=mensaje+"\n-) Interï¿½s Institucional parte 4(Mï¿½nimo 200 caracteres)";
		}*/
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios: "+mensaje;
			alert (mensaje);
		}
		else
			return true;
		return false;
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
<form method="post" action='<c:url value="/movilidad/adminMovilidad.x"/>'>
	<input type="hidden" name="accion" value="2">
	<input type="image" src='<c:url value="/comp/img/Atras.gif"/>'>
</form>
<form name="nuevo" method="post" action='<c:url value="/movilidad/adminMovilidad.x"/>'>
	<input type="hidden" name="accion" value="">
	<input type="hidden" name="idPersona" value='<c:out value="${sessionScope.persona.idPersona}" />'>
	<input type="hidden" name="propConvId" value='<c:out value="${sessionScope.datosConv.convId}"/>'>
<c:if test='${sessionScope.persona.estado}'>
	<input type="hidden" name="id">
	<input type="hidden" name="estado" value="1">
		<br>
	<div align="center">
	<fieldset style="width:580px;">		
	<div align="center">	
	<table class="tablas" width="95%">
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
		<table class="tablas" width="95%">
		<CAPTION>Agenda de cooperación</CAPTION>
			<tr>
				<td colspan="3" class="renglones"><b>Participación en eventos financiados por del CIDC</b></td>
			</tr>
			<tr>
				<td width="80%">
					<p class="listas">Seleccione la cantidad de Eventos financiados por el CIDC en los que usted ha participado, inmediatamente después digíte el nombre de cada uno de estos.</p>
				</td>
				<td>
					<select onchange="partEvent(this)" style="width:100%">
						<option value="0">Ninguno</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</td>
				<td width="30px" align="left"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick="info(4)"></td>
			</tr>
			<tr>
					<td colspan="3">
						<table width="100%">
							<tr id="tablero" style="display:none;">
								<td class="renglones"><b>Nombre del Evento</b></td>
							</tr>
							<c:if test="${!empty sessionScope.InfoB.partiEvent}">
								<c:set var="contador" value="0"/>
								<c:forEach begin="0" items="${sessionScope.InfoB.partiEvent}" var="listaEvento" varStatus="st">
								<tr id="partEv${st.index}" style="">								
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value='<c:out value="${listaEvento}"/>'></td>									
								</tr>
								<c:set var="contador" value="${st.count}"/>
								</c:forEach>
								
								<c:forEach begin="${contador}" end="10" var="caja" varStatus="st">									
	                                <tr id='partEv<c:out value="${contador}"/>' style="display:none;">
	                                    <td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value='' disabled ></td>
	                                </tr>
	                                <c:set var="contador" value="${contador+1}"/>
	                            </c:forEach>
								
                            </c:if>
							<c:if test="${empty sessionScope.InfoB.partiEvent}">
								<tr id="partEv0" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="1)" disabled "></td>
								</tr>
								<tr id="partEv1" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="2)" disabled "></td>
								</tr>
								<tr id="partEv2" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="3)" disabled "></td>
								</tr>
								<tr id="partEv3" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="4)" disabled "></td>
								</tr>
								<tr id="partEv4" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="5)" disabled "></td>
								</tr>
								<tr id="partEv5" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="6)" disabled "></td>
								</tr>
								<tr id="partEv6" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="7)" disabled "></td>
								</tr>
								<tr id="partEv7" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="8)" disabled "></td>
								</tr>
								<tr id="partEv8" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="9)" disabled "></td>
								</tr>
								<tr id="partEv9" style="display:none;">
									<td><input style="width:100%" class="cajas10" type="text" name="partiEvent" value="10)" disabled "></td>
								</tr>							 
							</c:if>
						</table>
					</td>
				</tr>
			<tr>
				<td colspan="3" class="renglones"><b>Justificación de la participación en el evento(Máximo 250 palabras cada campo)</b></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%">
					<tr>
						<td valign="top"><b>1) Justificación del investigador de la participación en el evento</b></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se debe establecer claramente la Justificación de la participación en el evento en relación con la actividad de investigación.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" class="area2" style="width:100%;" name="compromisos1" value=''><c:out value="${sessionScope.InfoB.compromisos1}"/></textarea>
						</td>
					</tr>
<!--				<tr>
						<td><p class="texto1j">Parï¿½metros de mediciï¿½n de los compromisos asumidos por el investigador con la universidad.</p></td>
					</tr>-->
<!--				<tr>
						<td><textarea class="area2" style="width:100%;" name="compromisos2"></textarea></td>
					</tr>-->
					<tr>
						<td valign="top"><b>2) Agenda de Actividades</b></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se debe establecer claramente la Agenda de Cooperación planteada por el investigador en donde haga explicito los niveles de colaboración (organizador, arbitro, ponente, miembro de comités, par evaluador entre otros) en las actividades previas, durante y posteriores al evento encaminadas a fortalecer la dinámica investigativa del grupo o semillero y de la Universidad.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" style="width:100%;" name="compromisos3" value=''><c:out value="${sessionScope.InfoB.compromisos3}"/></textarea>
						</td>
					</tr>
<!--				<tr>
						<td><p class="texto1j">Parï¿½metros de mediciï¿½n de los compromisos asumidos por el investigador con la universidad.</p></td>
					</tr>
					<tr>
						<td><textarea class="area2" style="width:100%;" name="compromisos4"></textarea></td>
					</tr>-->
				</table>
				</td>
			</tr>

<!--		<tr>
				<td colspan="3" class="renglones"><b>Beneficio Institucional (Mï¿½nimo 280 caracteres cada campo cada campo)</b></td>
			</tr>-->
			<tr>
			<!--<td colspan="3">
					<table width="100%">
					<tr>
						<td valign="top"><b>1) Beneficio personal</b></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se deben establecer claramente los beneficios personales del investigador que se podrï¿½an obtener con la participaciï¿½n del investigador en el evento y que permita fortalecer el desarrollo personal en los campos de investigaciï¿½n, docencia y proyecciï¿½n social.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" style="width:100%;" name="beneficiosGrupo1"></textarea>
						</td>
					</tr>
					<tr>
						<td valign="top"><b>2) 	Beneficios para el grupo/semillero de investigaciï¿½n</b></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se deben establecer claramente los beneficios del grupo/semillero de investigaciï¿½n que se podrï¿½an obtener con la participaciï¿½n del investigador en el evento y que permita fortalecer el desarrollo del grupo/semillero de investigaciï¿½n en los campos de investigaciï¿½n, docencia y proyecciï¿½n social.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" style="width:100%;" name="beneficiosGrupo2"></textarea>
						</td>
					</tr>
					<tr>
						<td><p class="texto1j">Parï¿½metros de mediciï¿½n de los beneficios que podrï¿½a obtener el grupo/semillero de investigaciï¿½n.</p></td>
					</tr>
					<tr>
						<td><textarea class="area2" style="width:100%;" name="beneficiosGrupo3"></textarea></td>
					</tr>
					<tr>
						<td valign="top"><b>3) Beneficio para la universidad</b></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se deben establecer claramente los beneficios, en su orden de, la universidad, la facultad y el(los) proyecto(s) curricular(es) al cual se encuentra adscrito el investigador y que se podrï¿½an obtener con la participaciï¿½n del investigador en el evento y que permita fortalecer el desarrollo de la instituciï¿½n en los campos de investigaciï¿½n, docencia y proyecciï¿½n social.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" style="width:100%;" name="beneficiosGrupo4"></textarea>
						</td>
					</tr>
					<tr>
						<td><p class="texto1j">Parï¿½metros de mediciï¿½n de los beneficios que podrï¿½a obtener la instituciï¿½n.</p></td>
					</tr>
					<tr>
						<td><textarea class="area2" style="width:100%;" name="beneficiosGrupo5"></textarea></td>
					</tr>
				</table>
				</td>-->
			</tr>
<!--		<tr>
				<td colspan="3" class="renglones"><b>Interï¿½s Institucional (Mï¿½nimo 280 caracteres cada campo)</b></td>
			</tr>-->
<!--		<tr>
				<td colspan="3">
					<table width="100%">
					<tr>
						<td valign="top"><b>1) Objetivo institucional para apoyar la participaciï¿½n de un investigador en evento acadï¿½mico</b></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se debe establecer el objetivo institucional para la participaciï¿½n en el evento y por el cual se debe apoyar econï¿½micamente al investigador en el desplazamiento al lugar en donde se desarrollarï¿½ el evento.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" style="width:100%;" name="interesInsti1"></textarea>
						</td>
					</tr>
					<tr>
						<td valign="top"><b>2) Objetivo personal para apoyar la participaciï¿½n de un investigador en evento acadï¿½mico</b></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se debe establecer el objetivo personal para la participaciï¿½n en el evento y por el cual se debe apoyar econï¿½micamente al investigador en el desplazamiento al lugar en donde se desarrollarï¿½ el evento.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" style="width:100%;" name="interesInsti2"></textarea>
						</td>
					</tr>
					<tr>
						<td><p class="texto1j"><b>3) Actividades a desarrollar en el marco del evento o por fuera de ï¿½l que amerite ser tenido en cuenta en el marco de la agenda de cooperaciï¿½n.</b></p></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se debe describir todas las actividades a desarrollar durante el desarrollo del evento como la fecha de la ponencia, encuentros con investigadores de otras instituciones, visitas a laboratorios, grupos, institutos de investigaciï¿½n o instituciones acadï¿½micas, etc., que sean de alta relevancia para el cumplimiento de la agenda de cooperaciï¿½n.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" style="width:100%;" name="interesInsti3"></textarea>
						</td>
					</tr>
					<tr>
						<td><p class="texto1j"><b>4) Justificaciï¿½n de la participaciï¿½n en el evento en cuanto a la importancia del mismo en el cumplimiento de los objetivos institucionales trazados en el Plan Estratï¿½gico de Desarrollo 2007 - 2016 "Saberes, Conocimientos e Investigaciï¿½n de alto impacto para el Desarrollo Humano y Social"<b></p></td>
					</tr>
					<tr>
						<td><p class="texto1j">Se debe justificar la participaciï¿½n del investigador en el evento acadï¿½mico justificando la importancia de participaciï¿½n en el mismo teniendo en cuenta los planes institucionales trazados por el Consejo Superior Universitario que para este caso es el Plan Estratï¿½gico de Desarrollo 2007 - 2016 "Saberes, Conocimientos e Investigaciï¿½n de alto impacto para el Desarrollo Humano y Social" y el Plan Trienal 2008 - 2010.</p></td>
					</tr>
					<tr>
						<td>
						<textarea class="area2" style="width:100%;" name="interesInsti4"></textarea>
						</td>
					</tr>
				</table>
				</td>
			</tr>-->
			<tr>
				<td colspan="3">
					<table align="center">
						<tr>
							<td  colspan="4" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar()"></td>
							<td valign="middle"><b>Paso 2 de 3</b></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</fieldset>
	</div>
</c:if>
<c:if test='${!sessionScope.persona.estado}'>
<h3 align="center">Para poder acceder al formulario de inscripción de esta convocatoria, favor actualizar la información que se encuentra señalada en rojo. Esto lo puede hacer mediante el link "Mis Datos" del menu principal</h3>
</c:if>
</form>
</body>
</html>
