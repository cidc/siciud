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
	var nav4=window.Event ? true : false;

	function soloNumeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}

	/*function compararFechas(){
		var f1=new Date(document.nuevo.convFecInicio.value);
		var f2=new Date(document.nuevo.convFecFin.value);
		if(f1>f2){
			alert("la fecha de inicio no puede estar después de la fecha de cierre");
			document.nuevo.convFecFin.value="";
		}
	}*/

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
	<form name="nuevo" method="post">
	<input type="hidden" name="accion" value="7">

	<table class="tablas" align="center">
	<caption>Datos de Convocatoria</caption>
		<tr>
			<td colspan="4">
				<table>
					<tr>
						<td class="renglones"><b>Año</b></td>
						<td>
							<select name="convAno">
								<option value='<c:out value="${ano}"/>'><c:out value="${ano}"/></option>
								<option value='<c:out value="${ano+1}"/>'><c:out value="${ano+1}"/></option>
							</select>

						</td>
						<td class="renglones"><b>#</b></td>
						<td>
							<select name="convNumero">
								<c:forEach begin="1" end="30" varStatus="st">
									<option value="<c:out value="${st.count}"/>"><c:out value="${st.count}"/></option>
								</c:forEach>
							</select>
						</td>
                                                <td class="renglones"><b>Tipo</b></td>
                                                <td>
                                                        <select name="convTipo">
                                                                <option value='2'/>Movilidad</option>
                                                                <option value='1'/>Proyectos de Investigacion</option>
                                                        </select>

                                                </td>
					<%--	<td class="renglones"><b>Publicar ahora</b></td>
						<td>
							<input type="checkbox" name="convPublica_">
							<input type="hidden" name="convPublica" value="">
						</td>--%>
					<%--	<td>
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
			<td colspan="4"><input  type="text" name="convNombre" size="90" class="caja1"></td>
		</tr>
		<tr>
			<td class="renglones"><b>Duración</b></td>
			<td class="texto0">
				<select name="convDuracion">
					<c:forEach begin="1" end="36" varStatus="st">
						<option value="<c:out value="${st.count}"/>"><c:out value="${st.count}"/></option>
					</c:forEach>
				</select>
				Meses
			</td>
			<td class="renglones">
				<b>Valor a Financiar</b>
			</td>
			<td class="texto0">
				$<input type="text" name="convCuantia" maxlength="9" size="9" onKeyPress='return soloNumeros(event)'>
			</td>
		</tr>
		<tr>
			<td class="renglones"><b>Fecha de Inicio</b></td>
			<td>
				<input type='text' name='convFecInicio' class='caj' readonly='true' id='f_date_a' size='13'>
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
			</td>
			<td class="renglones"><b>Fecha de Cierre</b></td>
			<td>
				<input type='text' name='convFecFin' class='caj'  readonly='true' id='f_date_c' size='13'>
				<button type='button' id='f_trigger_c'>...</button>
				<script type='text/javascript'>
	    			Calendar.setup({
		    			inputField     :    'f_date_c',
		    			ifFormat       :    '%Y-%m-%d',
		    			showsTime      :    false,
		    			button         :    'f_trigger_c',
		    			singleClick    :    false,
		    			step           :    1
	    			})
    			</script>
			</td>
		</tr>
		<tr>
			<td class="renglones" colspan="4">
				<b>Dirigida A:</b>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea style="width: 100%" class="area1" name="convDirigido" rows="2"></textarea>
			</td>
		</tr>
		<tr>
			<td><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"> </td>
		<tr>
	</table>
	</form>
</body>
</html>
