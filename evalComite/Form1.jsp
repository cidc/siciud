<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
<script>

	var nav4=window.Event ? true : false;

	function soloNumeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57)|| key==46);
	}

	function ValidarCaja(item,max){
		x=parseFloat(item.value);
		var suma=0;
		if(x>max){
			alert("Este item no puede superar los "+max+" puntos");
			item.value=max;
		}
		for(var i=0;i<9;i++){
			y=document.getElementById("val"+i).value;
			if(y!="")
				suma=suma+parseFloat(y);
		}
		document.getElementById('total').innerHTML=suma;
	}

	/*function validar(){
		mensaje="";
		if(document.evaluar.valCri2=""){
			document.nuevo.action='<c:url value="/convMovilidad/llenar.jsp"/>';
			mensaje=mensaje+"\n-) Debe ingresar una calificación valida.";
		}
		else
			return true;
		return false;
	}*/
	
	function enviar(){
		document.evaluar.submit();		
	}
	
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>

	<fieldset style="width:590px;">
	<legend>Datos Investigador y Ponencia</legend>
		<table class="tablas" width="100%">
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<td class="renglones" width="150px"><b>Nombre del Investigador</b></td>
							<td><p style="text-transform:lowercase;"><c:out value="${sessionScope.InfoA.investigador}" /></p></td>
							<td class="renglones" width="50px"><b>Perfil</b></td>
							<td><c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">Profesor</c:if>
								<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">Estudiante</c:if>
							</td>
							<td class="renglones" width="70px"><b>link CvLac</b></td>
							<td align="center"><a target="_new" href='<c:out value="${sessionScope.InfoA.cvLac}" />'>VER LINK</a></td>
						</tr>
					</table>
				<td>
			</tr>

			<tr>
				<td class="renglones" width="200px"><b>Grupo/Semillero de Investigación</b></td>
				<td><p style="text-transform:lowercase;"><c:out value="${sessionScope.InfoA.nombreGrupo}" /></p></td>
			</tr>
			<tr><td colspan="2" class="renglones"><b>Nombre de ponencia aprobada</b></td></tr>
			<tr><td colspan="2"><c:out value="${sessionScope.InfoA.nombrePonencia}" /></td></tr>
			<tr><td colspan="2" class="renglones"><b>Nombre del proyecto relacionado con la ponencia (Plan de Acción)</b></td></tr>
			<tr><td colspan="2"><c:out value="${sessionScope.InfoA.nombreProyecto}" /></td></tr>
		</table>
	</fieldset>

	<fieldset>
	<legend>Justificación de la participación y Agenda de Cooperación planteada por el investigador</legend>
		<table>
			<tr>
				<td colspan="3" class="renglones"><b>Justificación de la participación y Agenda de Cooperación planteada por el Investigador</b></td>
			</tr>
			<tr>
				<td colspan="3">
				<table width="100%">
					<tr>
						<td valign="top"><b>1) Justificación de la participación en el evento</b></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly" class="area2" rows="5" style="width:100%;"><c:out value="${sessionScope.InfoB.compromisos1}" /></textarea>
						</td>
					</tr>
					<!--<tr>
						<td><p class="texto1j">Parámetros de medición de los compromisos asumidos por el investigador con el grupo / semillero.</p></td>
					</tr>
					<tr>
						<td><textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.compromisos2}" /></textarea></td>
					</tr>-->
					<tr>
						<td valign="top"><b>2) Agenda de las actividades previas, durante y posteriores al evento.</b></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.compromisos3}" /></textarea>
						</td>
					</tr>
<!-- 				<tr>
						<td><p class="texto1j">Parámetros de medición de los compromisos asumidos por el investigador con la universidad.</p></td>
					</tr>
					<tr>
						<td><textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.compromisos4}" /></textarea></td>
					</tr>-->
				</table>
				</td>
			</tr>

<!--  		<tr>
				<td colspan="3" class="renglones"><b>Beneficio Institucional</b></td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%">
					<tr>
						<td valign="top"><b>1) Beneficio personal</b></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly"   class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.beneficiosGrupo1}"/></textarea>
						</td>
					</tr>
					<tr>
						<td valign="top"><b>2) 	Beneficios para el grupo/semillero de investigación</b></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.beneficiosGrupo2}"/></textarea>
						</td>
					</tr>
					<tr>
						<td><p class="texto1j">Parámetros de medición de los beneficios que podría obtener el grupo/semillero de investigación.</p></td>
					</tr>
					<tr>
						<td><textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.beneficiosGrupo3}"/></textarea></td>
					</tr>
					<tr>
						<td valign="top"><b>3) Beneficio para la universidad</b></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.beneficiosGrupo4}"/></textarea>
						</td>
					</tr>
					<tr>
						<td><p class="texto1j">Parámetros de medición de los beneficios que podría obtener la institución.</p></td>
					</tr>
					<tr>
						<td><textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.beneficiosGrupo5}"/></textarea></td>
					</tr>
				</table>
				</td>
			</tr>-->
<!--  		<tr>
				<td colspan="3" class="renglones"><b>Interés Institucional</b></td>
			</tr>
			<tr>
				<td colspan="3">
					<table width="100%">
					<tr>
						<td valign="top"><b>1) Objetivo institucional para apoyar la participación de un investigador en evento académico</b></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.interesInsti1}"/></textarea>
						</td>
					</tr>
					<tr>
						<td valign="top"><b>2) Objetivo personal para apoyar la participación de un investigador en evento académico</b></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.interesInsti2}"/></textarea>
						</td>
					</tr>
					<tr>
						<td><p class="texto1j"><b>3) Actividades a desarrollar en el marco del evento o por fuera de él que amerite ser tenido en cuenta en el marco de la agenda de cooperación.</b></p></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.interesInsti3}"/></textarea>
						</td>
					</tr>
					<tr>
						<td><p class="texto1j"><b>4) Justificación de la participación en el evento en cuanto a la importancia del mismo en el cumplimiento de los objetivos institucionales trazados en el Plan Estratégico de Desarrollo 2007 - 2016 "Saberes, Conocimientos e Investigación de alto impacto para el Desarrollo Humano y Social"<b></p></td>
					</tr>
					<tr>
						<td>
						<textarea readonly="readonly"  class="area2" rows="5"style="width:100%;"><c:out value="${sessionScope.InfoB.interesInsti4}"/></textarea>
						</td>
					</tr>
				</table>
				</td>
			</tr>-->
		</table>
	</fieldset>

	<fieldset>
	<legend>Evaluación Justificación y Agenda de Cooperación</legend>
	<form name="evaluar" action='<c:url value="/evalMovilidad/llenar.jsp"/>' method="post">
	<input type="hidden" name="accion" value="3">
		<table class="tablas" align="center">
			<tr>
				<td class="renglones" align="center"><b>Item a Evaluar</b></td>
				<td class="renglones" align="center"><b>Valoración</b></td>
				<td class="renglones" align="center"><b>Máximo</b></td>
			</tr>
			<tr>
				<td colspan="3" class="Renglones"><b>Justificación de la participación y Agenda de Cooperación planteada por el investigador (30 puntos)</b></td>
			</tr>
			<tr>
				<td>Justificación de la participación en el evento en relación con la actividad de investigación.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val0" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,10)"></td>
				<td align="center"><b>10 puntos</b></td>
			</tr>
			<tr>
				<td>Agenda de cooperación planteada por el investigador.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val1" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,20)"></td>
				<td align="center"><b>20 puntos</b></td>
			</tr>
			
			<!--<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">-->
			<!--</c:if>-->
			<!--<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td colspan="3" class="Renglones"><b>Justificación de la participación y Agenda de Cooperación planteada por el investigador (34 puntos)</b></td>
			</tr>
			</c:if>-->
			<!--<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr>
				<td colspan="3" class="Renglones"><b>Justificación de la participación en el evento (12 puntos)</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td colspan="3" class="Renglones"><b>Justificación de la participación en el evento (14 puntos)</b></td>
			</tr>
			</c:if>-->			
			<!--<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">-->
			<!--</c:if>-->
			<!--<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">-->			
			<!--</c:if>-->
			<!--<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr class="trb">
				<td>Justificación de la participación en el evento en cuanto a la importancia del mismo en el cumplimiento de los objetivos del grupo/semillero de investigación</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val1" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,4)"> <b>puntos</b></td>
				<td align="center"><b>4</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr class="trb">
				<td>Justificación de la participación en el evento en cuanto a la importancia del mismo en el cumplimiento de los objetivos del grupo/semillero de investigación</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val1" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,5)"> <b>puntos</b></td>
				<td align="center"><b>5</b></td>
			</tr>
			</c:if>-->
<!--		<tr>
				<td colspan="3" class="Renglones"><b>Beneficio Institucional (30%)</b></td>
			</tr>-->
			<!--<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr>
				<td>Justificación de la participación en el evento en cuanto a la importancia del mismo en el cumplimiento de los objetivos de la Universidad.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val2" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,4)"> <b>puntos</b></td>
				<td align="center"><b>4</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td>Justificación de la participación en el evento en cuanto a la importancia del mismo en el cumplimiento de los objetivos de la Universidad.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val2" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,5)"> <b>puntos</b></td>
				<td align="center"><b>5</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr>
				<td colspan="3" class="Renglones"><b>Agenda de las actividades previas, durante y posteriores al evento (18 puntos).</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td colspan="3" class="Renglones"><b>Agenda de las actividades previas, durante y posteriores al evento (20 puntos).</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr class="trb">
				<td>Agenda de las actividades previas, durante y posteriores al evento.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val3" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,18)"> <b>puntos</b></td>
				<td align="center"><b>18</b></td>
			</tr>
			</c:if>			
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr class="trb">
				<td>Agenda de las actividades previas, durante y posteriores al evento.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val3" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,20)"> <b>puntos</b></td>
				<td align="center"><b>20</b></td>
			</tr>
			</c:if>-->
<!--		<tr>
				<td>Beneficio para la universidad</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val4" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,40)"> <b>%</b></td>
				<td align="center"><b>40</b></td>
			</tr>
			<tr>
				<td colspan="3" class="Renglones"><b>Interés Institucional (45%)</b></td>
			</tr>
			<tr>
				<td>Objetivo(s) institucional(es)</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val5" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,15)"> <b>%</b></td>
				<td align="center"><b>15</b></td>
			</tr>
			<tr class="trb">
				<td>Objetivo personal</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val6" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,15)"> <b>%</b></td>
				<td align="center"><b>15</b></td>
			</tr>
			<tr>
				<td>Actividades a desarrollar en el marco del evento o por fuera de él que amerite ser tenido en cuenta en el marco de la agenda de cooperación</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val7" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,35)"> <b>%</b></td>
				<td align="center"><b>35</b></td>
			</tr>
			<tr class="trb">
				<td>Justificación de la participación en el evento en cuanto a la importancia del mismo en el cumplimiento de los objetivos institucionales</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val8" name="valCri2" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,35)"> <b>%</b></td>
				<td align="center"><b>35</b></td>
			</tr>
			<tr>
				<td class="renglones" align="right"><b>Total</b></td>
				<td class="renglones" align="center"><b><span id="total">&nbsp;</span></b></td>
				<td class="renglones">&nbsp;</td>
			</tr>-->
			<tr>
				<td colspan="3" align="center">
					<img src='<c:url value="/comp/img/Siguiente.gif"/>' onclick="enviar()">
				</td>
			</tr>
		</table>
		</form>
	</fieldset>
</body>
</html>