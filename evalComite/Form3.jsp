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
		return(key<=44 || key<=13 || (key>=48 && key<=57));
	}

	function ValidarCaja(item,max){
		x=parseFloat(item.value);
		var suma=0;
		if(x>max){
			alert("Este item no puede superar los "+max+" puntos");
			item.value=max;
		}
		for(var i=0;i<4;i++){
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
<div align="center">
	<fieldset style="width:590px;">
	<legend>Datos Resumen de Evento y Ponencia</legend>
		<table class="tablas" width="100%" align="center">
		<CAPTION>Información del evento</CAPTION>
			<tr>
				<td class="renglones"><b>Tipo de evento</b></td>
				<td><c:out value="${sessionScope.infoEvento.tipoLetra}" /></td>
				<td class="renglones"><b>Lugar del Evento</b></td>
				<td><c:out value="${sessionScope.infoEvento.pais} - ${sessionScope.infoEvento.ciudad}" /></td>
			</tr>
			<tr>
				<td class="renglones"><b>Inicio del evento</b></td>
				<td><c:out value="${sessionScope.infoEvento.fechaInicio}" /></td>
				<td class="renglones"><b>Fin del Evento</b></td>
				<td><c:out value="${sessionScope.infoEvento.fechaFin}" /></td>
			</tr>
			<tr>
				<td colspan="4">
					<table width="100%">
						<tr>
							<td class="renglones"><b>Trayectoria</b></td>
							<td align="center"><c:out value="${sessionScope.infoEvento.trayectoria}" /></td>
							<td class="renglones"><b>Valor Inscrición</b></td>
							<td align="center"><c:out value="${sessionScope.infoEvento.valorInsc}" /></td>
							<td class="renglones"><b>Moneda</b></td>
							<td align="center"><c:out value="${sessionScope.infoEvento.monedaTxt}" /></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr><td colspan="4" class="renglones"><b>Nombre del evento al que desea asistir</b></td></tr>			
			<tr><td colspan="4"><c:out value="${sessionScope.infoEvento.nombreEvento}" /></td></tr>
			<tr><td colspan="4" class="renglones"><b>Página web del evento.</b></td></tr>			
			<tr><td colspan="4"><a href='<c:out value="${sessionScope.infoEvento.pagEvento}" />' target="_new"><c:out value="${sessionScope.infoEvento.pagEvento}" /></a></td></tr>			
			<tr><td colspan="4" class="renglones"><b>Lista de Universidades e  Instituciones que soportan el evento</b></td></tr>
			<tr><td colspan="4"><c:out value="${sessionScope.infoEvento.institucion}" /></td></tr>
			<tr><td colspan="4" class="renglones"><b>Comité de Arbitraje, publicación e indexación de resultados, cronograma</b></td></tr>
			<tr><td colspan="4"><textarea readonly="readonly" class="area2" rows="5" style="width:100%;"><c:out value="${sessionScope.infoEvento.lista_arbitraje}" /></textarea></td></tr>
		</table>
	</fieldset>
	</div>
<br>
	<form name="evaluar" action='<c:url value="/evalMovilidad/llenar1.jsp"/>' method="post">
	<input type="hidden" name="accion" value="5">
	<input type="hidden" name="evento" value='<c:out value="${requestScope.evento}" />'>
		<table class="tablas" align="center" >
		<CAPTION>Aspectos de evaluación</CAPTION>
			<tr>
				<td class="renglones" align="center"><b>Item a Evaluar</b></td>
				<td class="renglones" align="center"><b>Valoración</b></td>
				<td class="renglones" align="center"><b>Máximo</b></td>
			</tr>
			<tr>
				<td>Organizador del evento. Lista de Universidades e Instituciones que soportan el evento.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val0" name="valCri3" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,8)"> <b>puntos</b></td>
				<td align="center"><b>8</b></td>
			</tr>			
			<tr>
				<td>Participa la Universidad Distrital en el Comité ejecutivo, la organización o el arbitraje de los trabajos del evento. (Adicional)</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val1" name="valCri3" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,2)"> <b>puntos</b></td>
				<td align="center"><b>2</b></td>
			</tr>			
			<tr>
				<td>Organizador del evento. Comité de arbitraje, publicación e indexación de resultados y cronograma.</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val2" name="valCri3" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,6)"> <b>puntos</b></td>
				<td align="center"><b>6</b></td>
			</tr>
			<tr>
				<td>Trayectoria del evento</td>
				<td align="center" width="80px"><input class="caja11d" size="5" value="0" type="text" id="val3" name="valCri3" onKeyPress='return soloNumeros(event)' onkeyup="ValidarCaja(this,6)"> <b>puntos</b></td>
				<td align="center"><b>6</b></td>
			</tr>
			<tr>
				<td class="renglones" align="right"><b>Total</b></td>
				<td class="renglones" align="center"><b><span id="total">&nbsp;</span></b></td>
				<td class="renglones">&nbsp;</td>
			</tr>
			<tr>
				<td align="center" colspan="3">
					<input type="image" src='<c:url value="/comp/img/Terminar.gif"/>' onclick="enviar()">
				</td>
			</tr>
		</table>
</body>
</html>