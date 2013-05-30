<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<c:import url="/general.jsp"/>
<jsp:useBean id="now" class="java.util.Date"/>
<fmt:formatDate value="${now}" type="time" timeStyle="short" pattern="yyyy" var="ano"/>
<script>
	function enviar(sitio){
		document.parametros.irA.value=sitio;
		document.parametros.submit();
	}

	function compararFechas(){
		var f1=new Date(document.nuevo.convFecInicio.value);
		var f2=new Date(document.nuevo.convFecFin.value);
		if(f1>f2){
			alert("la fecha de inicio no puede estar después de la fecha de cierre");
			document.nuevo.convFecFin.value="";
		}
	}

/*	function check(forma){
		if(forma.convPublica_.checked==true){
           forma.convPublica.value="true";
        }else{
	       forma.convPublica.value="false";
        }
	}*/
	function guardar(){
		if(ValidarFormulario(document.nuevo)){
//			check(document.nuevo);
			document.nuevo.action='<c:url value="/Convocatoria/llenar.jsp"/>';
			document.nuevo.submit();
		}
	}

	function ValidarFormulario(forma){
		if(forma.convNombre.value==""){
			alert("El nombre de la Convocatoria no puede estar vacio");
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
	<br>
	<form name="nuevo" method="post">
	<input type="hidden" name="accion" value="10">

			<table class="tablas" align="center">
			<caption>Datos Generales de Convocatoria</caption>
				<tr>
					<td colspan="4">
						<table>
							<tr>
								<td class="renglones"><b>Año <c:out value="${sessionScope.convocatoriaOBJ.convAno}"/></b></td>
								<td>
									<select class="combo" name="convAno">
									<c:if test="${sessionScope.convocatoriaOBJ.convAno<ano}">
										<option value='<c:out value="${sessionScope.convocatoriaOBJ.convAno}"/>' selected><c:out value="${sessionScope.convocatoriaOBJ.convAno}"/></option>
									</c:if>
										<option value='<c:out value="${ano}"/>' <c:if test="${sessionScope.convocatoriaOBJ.convAno==ano}">selected</c:if>><c:out value="${ano}"/></option>
										<option value='<c:out value="${ano+1}"/>' <c:if test="${sessionScope.convocatoriaOBJ.convAno==ano+1}">selected</c:if>><c:out value="${ano+1}"/></option>
									</select>

								</td>
								<td class="renglones"><b>#</b></td>
								<td>
									<select class="combo" name="convNumero">
										<c:forEach begin="1" end="30" varStatus="st">
											<option value='<c:out value="${st.count}"/>' <c:if test="${sessionScope.convocatoriaOBJ.convNumero==st.count}">selected</c:if>><c:out value="${st.count}"/></option>
										</c:forEach>
									</select>
								</td>
						<%--		<td class="renglones"><b>Publicar ahora</b></td>
								<td>
									<input type="checkbox" name="convPublica_" <c:if test="${sessionScope.convocatoriaOBJ.convPublica==true}">checked</c:if>>
									<input type="hidden" name="convPublica" value="<c:out value="${sessionScope.convocatoriaOBJ.convPublica}"/>">
								</td>--%>
							<%--	<th><b>Fecha Corte Actual</b></th>
								<td>
									<input type='text' name='corteActual' class='caj' readonly='true' id='f_date_a' size='13' value='<c:out value="${sessionScope.convocatoriaOBJ.corteActual}"/>'>
									<button type='button' id='f_trigger_a'>...</button>
									<script type='text/javascript'>
						    			Calendar.setup({
							    			inputField     :    'f_date_a',
							    			ifFormat       :    '%Y-%m-%d',
							    			showsTime      :    false,
							    			button         :    'f_trigger_a',
							    			singleClick    :    false,
							    			step           :    1
						    			})
					    			</script>
								</td>--%>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="renglones" colspan="4"><b>Nombre de Convocatoria</b></td>
				</tr>
				<tr>
					<td colspan="4"><input type="text" size="90" class="caja1" name="convNombre" value='<c:out value="${sessionScope.convocatoriaOBJ.convNombre}"/>'></td>
				</tr>
				<tr>
					<td class="renglones"><b>Duración</b></td>
					<td class="p">
						<select class="combo" name="convDuracion">
							<c:forEach begin="1" end="36" varStatus="st">
								<option value='<c:out value="${st.count}"/>' <c:if test="${sessionScope.convocatoriaOBJ.convDuracion==st.count}">selected</c:if>><c:out value="${st.count}"/></option>
							</c:forEach>
						</select>
						Meses
					</td>
					<td class="renglones">
						<b>Valor a Financiar</b>
					</td>
					<td class="p">
						$<input type="text" class="caja0" name="convCuantia" maxlength="10" size="10" value='<c:out value="${sessionScope.convocatoriaOBJ.convCuantia}"/>'>
					</td>
				</tr>
				<tr>
					<td class="renglones"><b>Fecha de Inicio</b></td>
					<td>
						<input type='text' name='convFecInicio' class='caj' onchange="compararFechas()" readonly='true' id='f_date_b' size='13' value='<c:out value="${sessionScope.convocatoriaOBJ.convFecInicio}"/>'>
						<button type='button' id='f_trigger_b'>...</button>
						<script type='text/javascript'>
			    			Calendar.setup({
				    			inputField     :    'f_date_b',
				    			ifFormat       :    '%Y-%m-%d',
				    			showsTime      :    true,
				    			button         :    'f_trigger_b',
				    			singleClick    :    false,
				    			step           :    1
			    			})
		    			</script>
					</td>
					<td class="renglones"><b>Fecha de Cierre</b></td>
					<td>
						<input type='text' name='convFecFin' class='caj' onchange="compararFechas()" readonly='true' id='f_date_c' size='13' value='<c:out value="${sessionScope.convocatoriaOBJ.convFecFin}"/>'>
						<button type='button' id='f_trigger_c'>...</button>
						<script type='text/javascript'>
			    			Calendar.setup({
				    			inputField     :    'f_date_c',
				    			ifFormat       :    '%Y-%m-%d',
				    			showsTime      :    true,
				    			button         :    'f_trigger_c',
				    			singleClick    :    false,
				    			step           :    1
			    			})
		    			</script>
					</td>
				</tr>
				<tr>
					<td colspan="4" class="renglones">
						<b>Dirigida A:</b>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<textarea  class="area1" name="convDirigido" rows="3"><c:out value="${sessionScope.convocatoriaOBJ.convDirigido}"/></textarea>
					</td>
				</tr>
				<tr>
					<td><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"></td>
				</tr>
			</table>


	</form>
	</c:if>
	<c:if test='${sessionScope.convocatoriaOBJ==null}'>
		<fieldset style="width:450px;">
			<legend class="texto1"><b>Error</b></legend>
			<h5>Favor seleccionar una convocatoria</h5>
		</fieldset>
	</c:if>
</body>
</html>
