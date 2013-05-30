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
	var total=0;
	function soloNumeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return((key>=48 && key<=57) || key==46 || key==13 || key==7 || key==8 ||key==0);
	//	return(key<=46 ||key<=13 || (key>=48 && key<=57));
	}


	function checks(formulario){
		if(formulario.aspectos_.length){
			for(var i=0;i<formulario.aspectos_.length;i++){
				if(formulario.aspectos_[i].checked==false){
			//		alert("bloqueado el "+i);
					formulario.aspectos[i].disabled=true;
					formulario.criterios[i].disabled=true;
					formulario.aspValor[i].disabled=true;
					formulario.aspValor[i].value="";
				}
				else{
			//		alert("habilitado el "+i);
					formulario.aspectos[i].disabled=false;
					formulario.criterios[i].disabled=false;
					formulario.aspValor[i].disabled=false;
					if(formulario.aspValor[i].value==""){
						formulario.aspValor[i].value="0";
					}
				}
			}
		}
	}
	function sumar(formulario, caja, criterio){
		var por=0;
		var cr=0;
		total=0;
		for(var i=0;i<formulario.aspectos_.length;i++){
			if(formulario.aspectos_[i].checked==true){
				if(criterio==formulario.criterios[i].value){
					por=parseFloat(formulario.aspValor[i].value)+por;
					if(document.criterios.valor.length){
						for(var j=0;j<document.criterios.valor.length;j++){
							if(document.criterios.cod[j].value==criterio){
						//	alert("entra ->"+document.criterios.valor[j].value+"<->" +por);
								if(document.criterios.valor[j].value<por){
									alert("la sumatoria del criterio "+document.criterios.nombre[j].value+" no debe superar el "+document.criterios.valor[j].value+"%");
									formulario.aspValor[i].value="0";
									por=parseFloat(formulario.aspValor[i].value)-por;
								}
							}
						}
					}
				}
				total=total+parseFloat(formulario.aspValor[i].value);
			}
		}
		if(por>100){
			alert("La sumatoria de puntos no puede ser superior a 100");
			caja.value="0";
			sumar(formulario);
		}
	}
	function chequear(formulario1,formulario2){
		if(formulario2.aspInc){
			if(formulario2.aspInc.length){
				for(var i=0;i<formulario1.aspectos.length;i++){
					formulario1.aspValor[i].disabled=true;
					for(var j=0;j<formulario2.aspInc.length;j++){
						if(formulario2.aspInc[j].value==formulario1.aspectos[i].value){
							formulario1.aspectos_[i].checked=true;
							formulario1.aspValor[i].disabled=false;
							formulario1.aspValor[i].value=formulario2.valor[j].value;
						}
					}
				}
			}else{
				for(var i=0;i<formulario1.aspectos.length;i++){
					formulario1.aspValor[i].disabled=true;
					if(formulario2.aspInc.value==formulario1.aspectos[i].value){
						formulario1.aspectos_[i].checked=true;
						formulario1.aspValor[i].disabled=false;
						formulario1.aspValor[i].value=formulario2.valor.value;
					}
				}
			}
		}
	}

	function iniciar(formulario){
		if(formulario.aspectos){
			if(formulario.aspectos.length){
				for(var i=0;i<formulario.aspectos.length;i++){
					formulario.aspValor[i].disabled=true;
					formulario.aspValor[i].value="";
				}
			}
			else{
				formulario.aspValor.disabled=true;
				formulario.aspValor.value="";
			}
		}
	}

	function guardar(){
		if(ValidarFormulario()){
			document.frmAspectos.action='<c:url value="/Convocatoria/llenar1.jsp"/>';
			document.frmAspectos.submit();
		}
	}
	function ValidarFormulario(){
		if(total<100){
			alert("No se ha comletado el 100% de los puntos");
			return false;
		}
		return true;
	}
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
	<form name="frmAspectos" method="post">
		<input type="hidden" name="accion" value='7'>
		<input type="hidden" name="de" value='1'>
		<input type="hidden" name="irA" value="1">
		<fieldset style="width:600px;">
            <legend class="texto21"><b>Aspectos</b></legend>
            <c:if test="${empty requestScope.listaCritOBJ}">
            <h5>No hay Criterios Asignados a esta convocatoria</h5>
            </c:if>
            <c:if test="${!empty requestScope.listaCritOBJ}">
	            <table width="100%" cellpadding="0" cellspacing="0">
            	<c:forEach begin="0" items="${requestScope.listaCritOBJ}" var="lista">
	            	<tr><td>
		              <table class="tablas" align="left" width="100%" cellpadding="3" cellspacing="0">
		              	<caption><c:out value="${lista.nombre}"/> - <c:out value="${lista.valor}"/>%</caption>
							<c:forEach begin="0" items="${requestScope.listaAspOBJ}" var="lista2" varStatus="s">
								<c:if test="${lista.codigo==lista2.criterio}">
									<tr>
										<td><c:out value="${s.count}"/></td>
									 	<td><input class="renglones" type="checkbox" name="aspectos_" onchange="checks(document.frmAspectos)"><td>
										<td width="80%" align="left"><c:out value="${lista2.nombre}"/></td>
										<td style="width:30px;">
											<input type="text" class="caja0" name="aspValor" size="4" maxlength="5" onKeyPress='return soloNumeros(event)' onkeyup="sumar(document.frmAspectos,this,<c:out value="${lista2.criterio}"/>)">
										</td>
										<td align="left"><b>pts</b>
											<input type="hidden" name="aspectos" value="<c:out value="${lista2.codigo}"/>">
											<input type="hidden" name="criterios" value="<c:out value="${lista2.criterio}"/>">
										</td>
									</tr>
								</c:if>
							</c:forEach>
							<input type="hidden" name="codigoCriterio" value="<c:out value="${lista.codigo}"/>">
							<input type="hidden" name="valorCriterio" value="<c:out value="${lista.valor}"/>">
					</table>
					</td></tr>
     			</c:forEach>
     			<tr>
     				<td><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"> </td>
     			<tr>
     			</table>
     			</c:if>
     		</fieldset>

	</form>
	<form name="frmInsc">
		<c:forEach begin="0" items="${requestScope.listaAspInscOBJ}" var="lista" varStatus="st">
			<input type="hidden" name="aspInc" value='<c:out value="${lista.codigo}"/>'>
			<input type="hidden" name="valor" value='<c:out value="${lista.valor}"/>'>
		</c:forEach>
	</form>
	<form name="criterios">
		<c:forEach begin="0" items="${requestScope.listaCritOBJ}" var="lista" varStatus="st">
			<input type="hidden" name="cod" value='<c:out value="${lista.codigo}"/>'>
			<input type="hidden" name="nombre" value='<c:out value="${lista.nombre}"/>'>
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
	iniciar(document.frmAspectos);
	chequear(document.frmAspectos,document.frmInsc);
</script>
</html>
