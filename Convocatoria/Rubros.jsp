<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
<script>
	var nav4=window.Event ? true : false;
	var por=0;

	function soloNumeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}
	function enviar(sitio){
		document.parametros.irA.value=sitio;
		document.parametros.submit();
	}
	function checks(formulario){
		if(formulario.rubros_.length){
			for(var i=0;i<formulario.rubros_.length;i++){
				if(formulario.rubros_[i].checked==false){
					formulario.rubros[i].disabled=true;
					formulario.rubValor[i].disabled=true;
					formulario.rubValor[i].value="";
				}
				else{
					formulario.rubros[i].disabled=false;
					formulario.rubValor[i].disabled=false;
					if(formulario.rubValor[i].value==""){
						formulario.rubValor[i].value="0";
					}
				}
			}
		}
	//	sumar(formulario);
		calcular();
	}
/*	function sumar(formulario, caja){
		por=0;
		for(var i=0;i<formulario.rubros_.length;i++){
			if(formulario.rubros_[i].checked==true){
				por=parseInt(formulario.rubValor[i].value)+por;
				document.getElementById('total').innerHTML=por;
			}
		}
		//alert(por+" "+document.frmInsc.maxValor.value);
		if(por>parseInt(document.frmInsc.maxValor.value)){
			alert("La sumatoria no puede ser superior a "+document.frmInsc.maxValor.value);
			caja.value="0";
			sumar(formulario);
		}
	}*/
	function chequear(formulario1,formulario2){
		for(var i=0;i<formulario1.rubros.length;i++){
			formulario1.rubValor[i].disabled=true;
		}
		if(formulario2.rubInc){
			if(formulario2.rubInc.length){
				for(var i=0;i<formulario1.rubros.length;i++){
					formulario1.rubValor[i].disabled=true;
					for(var j=0;j<formulario2.rubInc.length;j++){
						if(formulario2.rubInc[j].value==formulario1.rubros[i].value){
							formulario1.rubros_[i].checked=true;
							formulario1.rubValor[i].disabled=false;
							formulario1.rubValor[i].value=formulario2.valor[j].value;
						}
					}
				}
			}
			else{
				for(var i=0;i<formulario1.rubros.length;i++){
					formulario1.rubValor[i].disabled=true;
					if(formulario2.rubInc.value==formulario1.rubros[i].value){
						formulario1.rubros_[i].checked=true;
						formulario1.rubValor[i].disabled=false;
						formulario1.rubValor[i].value=formulario2.valor.value;
					}
				}
			}
		}
	}

	function calcular(){
		var max=parseInt(document.frmInsc.maxValor.value);
		var poner=0;
		for(var i=0;i<document.frmRubros.rubros.length;i++){
		poner=max*(parseInt(document.frmRubros.rubValor[i].value)/100);
		if(!isNaN(poner))
			document.getElementById('v'+(i+1)).innerHTML=poner;
		}
	}
	function guardar(){
	//	if(ValidarFormulario()){
		checks(document.frmRubros);
		document.frmRubros.action='<c:url value="/Convocatoria/llenar1.jsp"/>';
		document.frmRubros.submit();
	//	}
	}

/*	function ValidarFormulario(forma){
		if(por<parseInt(document.frmInsc.maxValor.value)){
			alert("La sumatoria de los rubros es menor que el valor de la cuantía");
			return false;
		}
		return true;
	}*/

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr><td width="100%" valign='top'>
			<div align="center" style="width:100%;height:180px;overflow:auto;vertical-align:top;">
				<c:import url="/convocatoria/Listas.x"/>
			</div>
		</td></tr>
	</table>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<a href='<c:url value="/convocatoria/Parametrizar.x?irA=8&accion=6"/>'>
					<img border="0" src="<c:url value="/comp/img/General.gif"/>">
				</a>
			</td>
			<td>
				<a href='<c:url value="/convocatoria/Parametrizar.x?irA=9&accion=6"/>'>
					<img border="0" src="<c:url value="/comp/img/Documentos.gif"/>">
				</a>
			</td>
			<td>
				<a href='<c:url value="/convocatoria/Parametrizar.x?irA=4&accion=6"/>'>
					<img border="0" src="<c:url value="/comp/img/Ejes.gif"/>">
				</a>
			</td>
			<td>
				<a href='<c:url value="/convocatoria/Parametrizar.x?irA=2&accion=6"/>'>
					<img border="0" src="<c:url value="/comp/img/Compromisos.gif"/>">
				</a>
			</td>
			<td>
				<a href='<c:url value="/convocatoria/Parametrizar.x?irA=3&accion=6"/>'>
					<img border="0" src="<c:url value="/comp/img/Criterios.gif"/>">
				</a>
			</td>
			<td>
				<a href='<c:url value="/convocatoria/Parametrizar.x?irA=1&accion=6"/>'>
					<img border="0" src="<c:url value="/comp/img/Aspectos.gif"/>">
				</a>
			</td>
			<td>
				<a href='<c:url value="/convocatoria/Parametrizar.x?irA=12&accion=6"/>'>
					<img border="0" src="<c:url value="/comp/img/Porcentajes.gif"/>">
				</a>
			</td>
			<td>
				<a href='<c:url value="/convocatoria/Parametrizar.x?irA=11&accion=6"/>'>
					<img border="0" src="<c:url value="/comp/img/Rubros.gif"/>">
				</a>
			</td>
		</tr>
	</table>
	<br>
	<c:if test='${sessionScope.convocatoriaOBJ!=null}'>
	<table align="center">
		<tr>
			<td width="55px" class="texto1"><b><c:out value="${sessionScope.convocatoriaOBJ.convAno}"/> - <c:out value="${sessionScope.convocatoriaOBJ.convNumero}"/></b></td>
			<td class="texto1"><c:out value="${sessionScope.convocatoriaOBJ.convNombre}"/></td>
		</tr>
	</table>
	<br>
	<form name="frmRubros" method="post">
		<input type="hidden" name="accion" value='7'>
		<input type="hidden" name="de" value='11'>
		<input type="hidden" name="irA" value="11">
         <table class="tablas" align="center">
			<caption>Rubros a Financiar</caption>
           	<tr>
           		<td>
	              	<table>
		              	<tr>
	              			<td class="renglones" colspan="2">&nbsp;</td>
							<td class="renglones" colspan="2"><b>Rubro</b></td>
							<td class="renglones" colspan="2"><b>Porcentaje</b></td>
							<td class="renglones"><b>Valor ($)</b></td>
						</tr>
						<c:forEach begin="0" items="${requestScope.ListaRubrosOBJ}" var="lista" varStatus="st">
						<tr>
							<td><c:out value="${st.count}"/><input type="hidden" name="rubros" value="<c:out value="${lista.codigo}"/>"></td>
						 	<td><input type="checkbox" name="rubros_" onchange="checks(document.frmRubros)"><td>
							<td><c:out value="${lista.nombre}"/></td>
							<td align="right"><b>%</b></td>
							<td align="right" width="30px"><input style="text-align:right" type="text" name="rubValor" size="4" maxlength="4" onKeyPress='return soloNumeros(event)' onkeyup="calcular()"></td>
							<td width="60px" align="right"><b><span id='v<c:out value="${st.count}"/>'>&nbsp;</span></b></td>
						</tr>
						</c:forEach>
						<tr>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td align="right"><b>Total</b></td>
							<td align="right"><span id='total'>&nbsp;</span></td>
							<td align="right"><span id='total2'>&nbsp;</span></td>
						</tr>
					</table>
				</td>
			</tr>
		<tr>
			<td colspan="4">
				<table width="100%">
				<tr><td class="renglones"><b>Observaciones</b></td></tr>
				<tr><td><textarea style="width:100%;" class="area2" name="observacion" cols="56" rows="5"><c:out value="${sessionScope.convocatoriaOBJ.observacion}"/></textarea></td></tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()">
			</td>
		</tr>
	</table>
	</form>
	<form name="frmInsc">
		<input type="hidden" name="maxValor" value="<c:out value="${sessionScope.convocatoriaOBJ.convCuantia}"/>">
		<c:forEach begin="0" items="${requestScope.ListaRubrosInscOBJ}" var="lista" varStatus="st">
			<input type="hidden" name="rubInc" value='<c:out value="${lista.codigo}"/>'>
			<input type="hidden" name="valor" value='<c:out value="${lista.valor}"/>'>
		</c:forEach>
	</form>

	</c:if>
	<c:if test='${sessionScope.convocatoriaOBJ==null}'>
		<fieldset style="width:450px;">
			<legend class="texto1"><b>Error</b></legend>
			<h5>Favor seleccionar una convocatoria</h5>
		</fieldset>
	</c:if>
</body>
<script>
	chequear(document.frmRubros,document.frmInsc);
//	sumar(document.frmRubros);
	calcular();
</script>
</html>
