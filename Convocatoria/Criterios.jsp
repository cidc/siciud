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

	function soloNumeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return((key>=48 && key<=57) || key==46 || key==13 || key==7 || key==8 ||key==0);
	//	return(key<=46 ||key<=13 || (key>=48 && key<=57));
	}

	function enviar(sitio){
		document.parametros.irA.value=sitio;
		document.parametros.submit();
	}
	function checks(formulario){
		if(formulario.criterios_.length){
			for(var i=0;i<formulario.criterios_.length;i++){
				if(formulario.criterios_[i].checked==false){
					formulario.criterios[i].disabled=true;
					formulario.critValor[i].disabled=true;
					formulario.critValor[i].value="";
				}
				else{
					formulario.criterios[i].disabled=false;
					formulario.critValor[i].disabled=false;
					if(formulario.critValor[i].value==""){
						formulario.critValor[i].value="0";
					}
				}
			}
		}
		sumar(formulario);
	}
	function sumar(formulario, caja){
		var por=0;
		for(var i=0;i<formulario.criterios_.length;i++){
			if(formulario.criterios_[i].checked==true){
				por=parseFloat(formulario.critValor[i].value)+por;
				document.getElementById('total').innerHTML=por;
			}
		}
		if(por>100){
			alert("La sumatoria no puede superar el 100%");
			caja.value="0";
			sumar(formulario);
		}
	}
	function chequear(formulario1,formulario2){
		for(var i=0;i<formulario1.criterios.length;i++){
			formulario1.critValor[i].disabled=true;
		}
		if(formulario2.critInc){
			if(formulario2.critInc.length){
				for(var i=0;i<formulario1.criterios.length;i++){
					formulario1.critValor[i].disabled=true;
					for(var j=0;j<formulario2.critInc.length;j++){
						if(formulario2.critInc[j].value==formulario1.criterios[i].value){
							formulario1.criterios_[i].checked=true;
							formulario1.critValor[i].disabled=false;
							formulario1.critValor[i].value=formulario2.valor[j].value;
						}
					}
				}
			}
			else{

					for(var i=0;i<formulario1.criterios.length;i++){
						formulario1.critValor[i].disabled=true;
						if(formulario2.critInc.value==formulario1.criterios[i].value){
							formulario1.criterios_[i].checked=true;
							formulario1.critValor[i].disabled=false;
							formulario1.critValor[i].value=formulario2.valor.value;
						}
					}

			}
		}
	}
	function guardar(){
		if(ValidarFormulario()){
			checks(document.frmCriterios);
			document.frmCriterios.action='<c:url value="/Convocatoria/llenar1.jsp"/>';
			document.frmCriterios.submit();
		}
	}

	function ValidarFormulario(){
		var total=parseInt(document.getElementById('total').innerHTML);
		if(total<100){
			alert("No se ha completado el 100%");
			return false;
		}
		return true;
	}
</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr><td width="100%" valign='top'>
			<div style="width:670px;height:180px;overflow:auto;vertical-align:top;background-color:#ffffff;">
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
			<td>
                                <a href='<c:url value="/convocatoria/Parametrizar.x?irA=35&accion=6"/>'>
                                        <img border="0" src="<c:url value="/comp/img/Documentos.gif"/>">
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
	<form name="frmCriterios" method="post">
		<input type="hidden" name="accion" value='7'>
		<input type="hidden" name="de" value='3'>
		<input type="hidden" name="irA" value="3">
		<div align="center">
		<fieldset style="width:430px;">
            <legend><b>Criterios</b></legend>
              <table class="tablas" width="100%" align="left">
              		<tr>
              			<td class="renglones" colspan="2">&nbsp;</td>
						<td class="renglones" colspan="2"><b>Nombre Criterio</b></td>
						<td class="renglones" colspan="2"><b>Valor</b></td>
					</tr>
					<c:forEach begin="0" items="${requestScope.ListaCritOBJ}" var="lista" varStatus="st">
						<tr>
							<td><c:out value="${st.count}"/></td>
						 	<td><input type="checkbox" name="criterios_" onchange="checks(document.frmCriterios)"><td>
							<td><c:out value="${lista.nombre}"/></td>

							<td style="width:30px;"><input type="text" name="critValor" size="4" maxlength="5" onKeyPress='return soloNumeros(event)' onkeyup="sumar(document.frmCriterios,this)"></td>
							<td align="left"><b>%</b>
								<input type="hidden" name="criterios" value="<c:out value="${lista.codigo}"/>">
							</td>

						</tr>
<%--
		<c:forEach begin="0" items="${requestScope.listaAspOBJ}" var="lista2" varStatus="s">
      			<c:if test="${lista.codigo==lista2.criterio}"> 
                               <tr>

<td></td>
<td><c:out value="${s.count}"/></td>

<td width="80%" align="left"><c:out value="${lista2.nombre}"/></td>
                                                                                <td style="width:30px;">
<input type="text" class="caja0" name="aspValor" size="4" maxlength="5" onKeyPress='return soloNumeros(event)' onkeyup="sumar(document.frmAspectos,this,<c:out value="${lista2.criterio}"/>)">
</td>
                                                                                <td align="left"><b>pts</b> --%>
<%--
                                                                                        <input type="hidden" name="aspectos" value="<c:out value="${lista2.codigo}"/>">

        <input type="hidden" name="criterios" value="<c:out value="${lista2.criterio}"/>">

</td>

 --%>
                                           <%--  </tr>
                                             </c:if> 
                                        </c:forEach> --%>
					</c:forEach>
					<tr>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td align="right"><b>Total</b></td>
					<td><span id='total'>&nbsp;</span></td>
					<td align="left"><b>%</b></td>
					</tr>
				<tr>
   					<td colspan="3"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"> </td>
   				<tr>
			</table>
     	</fieldset>
     	</div>
	</form>
	<form name="frmInsc">
		<c:forEach begin="0" items="${requestScope.ListaCritInscOBJ}" var="lista" varStatus="st">
			<input type="hidden" name="critInc" value='<c:out value="${lista.codigo}"/>'>
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
	chequear(document.frmCriterios,document.frmInsc);
	sumar(document.frmCriterios);
</script>
</html>
