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
		for(var i=0;i<<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">6</c:if><c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">5</c:if>;i++){
			y=document.getElementById("val"+i).value;
			if(y!="")
				suma=suma+parseFloat(y);
		}
		document.getElementById('total').innerHTML=suma;
	}

	function enviar(){
		document.evaluar.submit();
	}
	
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
	<form name="nuevo" method="post" action='<c:url value="/movilidad/evalMovilidad.x"/>'>
		<input type="hidden" name="accion" value="0">
		<input type="image" src='<c:url value="/comp/img/Atras.gif"/>'>
	</form>
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
		</table>
	</fieldset>
	<fieldset style="width:590px;">
	<legend>Documentos Almacenados</legend>
		<table class="tablas" >
			<tr>
				<td class="renglones" width="200px"><b>Aval del Grupo de Investigación</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[0]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[0]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[0]==null}">
				<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>
			<tr>
				<td class="renglones" width="200px"><b>Carta de aceptacion del evento</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[1]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[1]}"/>'><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[1]==null}">
				<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>
			<tr>
				<td class="renglones" width="200px"><b>Artículo aprobada</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[2]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[2]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[2]==null}">
				<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>
			<!--  
			<tr>
				<td class="renglones" width="200px"><b>Carta aval del consejo curricular..</b></td>
					<c:if test="${sessionScope.InfoA.listadDoc[3]!=null}">
					<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[3]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					</c:if>
					<c:if test="${sessionScope.InfoA.listadDoc[3]==null}">
					<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
					</c:if>
			</tr>
			-->
			<c:if test="${sessionScope.InfoA.papel!=3 and sessionScope.InfoA.papel!=8 and sessionScope.InfoA.papel!=9 and sessionScope.InfoA.papel!=10}">
			<tr>			
				<td class="renglones" width="200px"><b>Carta aval jefe inmediato</b></td>
				
					<c:if test="${sessionScope.InfoA.listadDoc[4]!=null}">
					<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[4]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					</c:if>
					<c:if test="${sessionScope.InfoA.listadDoc[4]==null}">
					<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
					</c:if>				
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==8 or sessionScope.InfoA.papel==9 or sessionScope.InfoA.papel==10}">
			<tr>
				<td class="renglones" width="200px"><b>Aval del consejo de facultad</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[5]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[5]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[5]==null}">
				<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel!=3 and sessionScope.InfoA.papel!=8 and sessionScope.InfoA.papel!=9 and sessionScope.InfoA.papel!=10}">			
			<tr>
				<td class="renglones" width="200px"><b>Excelencia académica</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[6]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[6]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[6]==null}">
					<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>
			</c:if>			
			<tr>
				<!-- Especificar el nuevo documento que cargan los investigadores Certificaciones de otras actividades-->
				<td class="renglones" width="200px"><b>Certificaciones de otras actividades realizadas en el grupo de investigación</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[7]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[7]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[7]==null}">
					<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>
			<tr>
				<!-- Especificar el nuevo documento que cargan los investigadores actividades certificadas por el CIDC-->
				<td class="renglones" width="200px"><b>Actividades avaladas por el CIDC o la Unidad de Investigaciones</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[8]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[8]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[8]==null}">
					<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>	
			<tr>
				<!-- Especificar el nuevo documento que cargan los investigadores Resultados Obtenidos-->
				<td class="renglones" width="200px"><b>Resultados de Investigación Obtenidos.</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[9]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[9]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[9]==null}">
					<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>
			<c:if test="${sessionScope.InfoA.papel!=3 and sessionScope.InfoA.papel!=8 and sessionScope.InfoA.papel!=9 and sessionScope.InfoA.papel!=10}">	
			<tr>
				<!-- Especificar el nuevo documento carta donde certifica no tener apoyos económicos-->
				<td class="renglones" width="200px"><b>Carta donde expresa no tener adjudicado otro apoyo económico.</b></td>
				<c:if test="${sessionScope.InfoA.listadDoc[10]!=null}">
				<td><a href='<c:url value="/Documentos/Movilidad/${sessionScope.InfoA.listadDoc[10]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				</c:if>
				<c:if test="${sessionScope.InfoA.listadDoc[10]==null}">
					<td align="center"><img src='<c:url value="/comp/img/equis1.png"/>'></td>
				</c:if>
			</tr>
			</c:if>			
		</table>
	</fieldset>
	<legend>Participación en eventos Financiados por el CIDC</legend>
	<c:if test="${!empty sessionScope.InfoA.listadEventos}">
		<table class="tablas" width="100%">
			<tr>
				<td class="renglones" width="10px"><b>#</b></td>
				<td class="renglones"><b>Nombre del evento</b></td>
			</tr>
			<c:forEach begin="0" items="${sessionScope.InfoA.listadEventos}" var="lista" varStatus="st">
			<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				<td class="renglones" width="10px"><b><c:out value="${st.count}"/></b></td>
				<td><c:out value="${lista}"/></td>
			</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:if test="${empty sessionScope.InfoA.listadEventos}">
	<h4 align="center">El investigador no ha participado en ningún evento financiado por el CIDC</h4>
	</c:if>
	</fieldset>
	<br>


	<fieldset>
	<!--  
	<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
	<legend>Evaluación de trayectoria y proyeccción del investigador (50 puntos)</legend>
	</c:if>
	-->
	<legend>Evaluación de trayectoria y proyeccción del investigador (50 puntos)</legend>
	<form name="evaluar" action='<c:url value="/evalMovilidad/llenar.jsp"/>' method="post">
	<input type="hidden" name="accion" value="2">
		<table class="tablas">
			<tr>
				<td class="renglones" align="center"><b>Item a Evaluar</b></td>
				<td class="renglones" align="center"><b>Valoración</b></td>
				<td class="renglones" align="center"><b>Máximo</b></td>
			</tr>
			<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr>
				<td>Registro de productividad durante los cinco (3) últimos años registrada en CvLAC</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val0" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,30)"><b>puntos</b></td>
				<td align="center"><b>30</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td>Registro de productividad durante los tres (3) últimos años registrada en CvLAC</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val0" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,15)"><b>puntos</b></td>
				<td align="center"><b>15</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">			
			<tr>
				<td>Participación en actividades de investigación del grupo o semillero.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val1" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,10)"> <b>puntos</b></td>
				<td align="center"><b>10</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">			
			<tr>
				<td>Participación en actividades de investigación del grupo o semillero.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val1" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,25)"> <b>puntos</b></td>
				<td align="center"><b>25</b></td>
			</tr>
			</c:if>
			<!--<tr>
				<td>Participación en grupos o semilleros de investigación</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val2" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">10</c:if><c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">15</c:if>)"> <b>%</b></td>
				<td align="center"><b><c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">10</c:if><c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">15</c:if></b></td>
			</tr>-->
			<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr>
				<td>Participación en actividades avaladas por el CIDC diferentes a movilidad.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val2" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,10)"> <b>puntos</b></td>
				<td align="center"><b>10</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td>Participación en actividades avaladas por el CIDC diferentes a movilidad.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val2" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,10)"> <b>puntos</b></td>
				<td align="center"><b>10</b></td>
			</tr>
			</c:if>
			<th colspan=3>Puntos Adicionales</th>
			<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr>
				<td>Resultados de excelencia académica en el último año</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val3" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,5)"> <b>puntos</b></td>
				<td align="center"><b>5</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==1 or sessionScope.InfoA.papel==2}">
			<tr>
				<td>Resultados obtenidos de agendas de cooperación de movilidad anteriores</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val4" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,3)"> <b>puntos</b></td>
				<td align="center"><b>3</b></td>
			</tr>
			</c:if>
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td>Resultados obtenidos de agendas de cooperación de movilidad anteriores</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val4" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,3)"> <b>puntos</b></td>
				<td align="center"><b>3</b></td>
			</tr>
			</c:if>
			<!-- 
			<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td>Resultados de excelencia académica en el último año.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val3" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,4)"> <b>puntos</b></td>
				<td align="center"><b>4</b></td>
			</tr>
			</c:if>
			 -->
			<!--<c:if test="${sessionScope.InfoA.papel==3 or sessionScope.InfoA.papel==5}">
			<tr>
				<td>Participación como auxiliar de investigación en proyectos financiados por el CIDC</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val5" name="valCri1" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,20)"> <b>%</b></td>
				<td align="center"><b>20</b></td>
			</tr>
			</c:if>
			<tr>
				<td class="renglones" align="right"><b>Total</b></td>
				<td class="renglones" align="center"><b><span id="total">&nbsp;</span></b></td>
				<td class="renglones">&nbsp;</td>
			</tr>-->
			<tr>
				<td align="center" colspan="3">
					<img src='<c:url value="/comp/img/Siguiente.gif"/>' onclick="enviar()">
				</td>
			</tr>

		</table>

	</fieldset>


</body>
</html>