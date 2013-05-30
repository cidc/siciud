<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	//		document.getElementById("partEvent0").style.display='';
			for (var i=0;i<=cant;i++){
				document.nuevo.partiEvent[i].disabled=false;
				document.getElementById("partEv"+i).style.display='';
			}
		}
	}
	function info(id){
		if(id==1){
			alert("siglas del organizador principal del evento");
		}
		if(id==2){
			alert("Liste Universidades e Instituciones que soporten el evento");
		}
		if(id==8){
			alert("Diligenciar lista del Comite de Arbitraje, publicacion e indexacion de resultados y cronograma del evento");
		}		
		if(id==3){
			alert("N&uacute;mero de evento realizado. Ejemplo 'Quinto Congreso sobre.....' (Para este ejemplo se debe digitar en n&uacute;mero 5)");
		}
		if(id==4){
			alert("Seleccione la cantidad de eventos financiados por el Centro de Investigaciones a los cuales usted ha asistido y mencione los nombres o descripciï¿½n de dichos eventos");
		}
		if(id==5){
			alert("Compromisos del Investigador");
		}
		if(id==6){
			alert("Beneficios para el Grupo de Investigaci&oacute;n");
		}
		if(id==7){
			alert("Inter&eacute;s Institucional");
		}
		if(id==9){
			alert("Debe relacionar un proyecto de investigación, según Plan de Acción cargado por el grupo/semillero");
		}
	}

	function ajaxProyecto(obj){
		document.frmAjaxProyecto.dato.value=obj.options[obj.selectedIndex].value;
		document.frmAjaxProyecto.para.value='1';
	 	document.frmAjaxProyecto.target="frameOculto";
		document.frmAjaxProyecto.submit();
	}
	function compararFechas(){
		var f1=new Date(document.nuevo.fechaInicio.value);
		var f2=new Date(document.nuevo.fechaFin.value);
		if(f1>f2){
			alert("la fecha de inicio no puede estar después de la fecha de cierre");
			document.nuevo.fechaFin.value="";
		}
	}
	function guardar(){
		if(validar()){
			document.nuevo.accion.value=1;
			document.nuevo.action='<c:url value="/convMovilidad/llenar.jsp"/>';
			document.nuevo.submit();
		}
	}

	function validar(){
		mensaje="";
		if(document.nuevo.tipo.selectedIndex==0){
			mensaje=mensaje+"\n-) Tipo de evento";
		}
		if(document.nuevo.idGrupo.selectedIndex==0){
			mensaje=mensaje+"\n-) Grupo de Investigaci&oacute;n";
		}else{
			document.nuevo.nombGrupo.value=document.nuevo.idGrupo[document.nuevo.idGrupo.selectedIndex].text;
		}
		if(document.nuevo.pais.value==""){
			mensaje=mensaje+"\n-) Pais Evento";
		}
		if(document.nuevo.ciudad.value==""){
			mensaje=mensaje+"\n-) Ciudad Evento";
		}
		if(document.nuevo.nombreEvento.value==""){
			mensaje=mensaje+"\n-) Nombre del evento al que desea asistir";
		}
		if(document.nuevo.institucion.value==""){
			mensaje=mensaje+"\n-) Lista de Institucion organizadora";
		}
		if(document.nuevo.lista_arbitraje.value==""){
			mensaje=mensaje+"\n-) Lista de arbitraje";
		}		
		if(document.nuevo.fechaInicio.value==""){
			mensaje=mensaje+"\n-) Fecha de inicio del evento";
		}
//else{
//		if(comparar_fechas(document.nuevo.fechaInicio.value,document.nuevo.fechaConv.value))
//			mensaje=mensaje+"\n-) La fecha de inicio del evento est&aacute; fuera del corte actual de la convocatoria";
//		}
		if(document.nuevo.fechaFin.value==""){
			mensaje=mensaje+"\n-) Fecha de cierre del evento";
		}
		if(document.nuevo.pagEvento.value==""){
			mensaje=mensaje+"\n-) P&aacute;gina web del evento";
		}
		if(document.nuevo.trayectoria.value==""){
			mensaje=mensaje+"\n-) Trayectoria del evento";
		}
		if(document.nuevo.valorInsc.value==""){
			mensaje=mensaje+"\n-) Valor Inscripciï¿½n del evento";
		}
		if(document.nuevo.valorInsc.selectedIndex==0){
			mensaje=mensaje+"\n-) Tipo de Moneda";
		}
		if(document.nuevo.nombrePonencia.value==""){
			mensaje=mensaje+"\n-) Nombre de la ponencia aprobada";
		}
		if(document.nuevo.proyectoinv.selectedIndex==0){
			mensaje=mensaje+"\n-) Proyecto de investigación";
		}
		if(document.nuevo.nombreAutores.value==""){
			mensaje=mensaje+"\n-) Nombre de los autores de la ponencia";
		}
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios: "+mensaje;
			alert (mensaje);
		}
		else
			return true;
		return false;
	}

	function comparar_fechas(fecha, fecha2){
     var xMonth=fecha.substring(5,7);
     var xDay=fecha.substring(8,10);
     var xYear=fecha.substring(0,4);
     var yMonth=fecha2.substring(5,7);
     var yDay=fecha2.substring(8,10);
     var yYear=fecha2.substring(0,4);
     if (xYear> yYear)
     {
         return(true)
     }
     else
     {
       if (xYear == yYear)
       {
         if (xMonth> yMonth)
         {
             return(true)
         }
         else
         {
           if (xMonth == yMonth)
           {
            if (xDay> yDay)
               return(true);
             else
               return(false);
           }
           else
             return(false);
         }
       }
       	else
        	 return(false);
    	 }
 	}
	function ajaxGrupos(obj){
		var val=obj.value;
		if(val!=0){
			document.frmAjaxGrupo.dato.value=val;
			document.frmAjaxGrupo.para.value='1';
	 		document.frmAjaxGrupo.target="frameOculto";
			document.frmAjaxGrupo.submit();
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
<body onload="ajaxProyecto(document.nuevo.idGrupo);">
<br>
<form name="nuevo1" method="post" action='<c:url value="/movilidad/adminMovilidad.x"/>'>
	<input type="hidden" name="accion" value="2">
	<input type="image" src='<c:url value="/comp/img/Atras.gif"/>'>
</form>
<form name="nuevo" method="post" action='<c:url value="/movilidad/adminMovilidad.x"/>'>
	<input type="hidden" name="accion" value="1">
	<input type="hidden" name="fechaConv" value='<c:out value="${sessionScope.datosConv.corteActual}" />'>
	<input type="hidden" name="idPersona" value='<c:out value="${sessionScope.persona.idPersona}" />'>
	<input type="hidden" name="propConvId" value='<c:out value="${sessionScope.datosConv.convId}"/>'>
<div align="center">
<fieldset style="width:580px;">
<div align="center">
<c:if test="${sessionScope.movilidad!=null}">
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
</c:if>
</div>
</fieldset>

        <fieldset style="width:80%;">
        <legend class="texto1"><b>Convocatoria a la que desea presentarse</b></legend>
                <table cellspacing="2">
                        <tr class="trb">
                                <td align="center" class="texto0" width="100px"><b><c:out value="${sessionScope.datosConv.convAno}"/> - <c:out value="${sessionScope.datosConv.convNumero}"/></b></td>
                                <td align="center"><b><c:out value="${sessionScope.datosConv.convNombre}"/></b></td>
                        </tr>
                </table>
        </fieldset>


<fieldset style="width:580px;">
<legend>Datos Investigador</legend>
<table class="tablas" width="100%">
	<tr>
		<td colspan="6" align="center">
			<table width="100%" align="center">
				<tr>
					<td width="60px" class="renglones"><b>Facultad:</b></td>
					<td class="renglones" width="200px"><b>Grupo/Semillero de Investigación</b></td>
					<td class="renglones" width="110px"><b>Papel en el grupo:</b></td>
				</tr>
				<tr>
					<td>
						<c:if test="${sessionScope.persona.facultad==0}"><p class="lroja">No Registrado</p></c:if>
						<c:if test="${sessionScope.persona.facultad==1}">Tecnol&oacute;gica</c:if>
						<c:if test="${sessionScope.persona.facultad==2}">Ingenier&iacute;a</c:if>
						<c:if test="${sessionScope.persona.facultad==3}">Medio Ambiente</c:if>
						<c:if test="${sessionScope.persona.facultad==4}">Ciencias y Educaci&oacute;n</c:if>
						<c:if test="${sessionScope.persona.facultad==5}">Asab</c:if>
					</td>
					<td>
						<input type="hidden" name="nombGrupo">
						<select name="idGrupo" onchange="ajaxProyecto(this)">
							<option value="0">----------------</option>
							<c:forEach begin="0" items="${sessionScope.listaMisGrupos}" var="lista">
							<option value='<c:out value="${lista.idGrupo}"/>' <c:if test="${!lista.estadoGrupo}">disabled</c:if><c:if test="${lista.idGrupo==sessionScope.movilidad.grupo}">selected</c:if>><c:out value="${lista.nombreGrupo}"/></option>
							</c:forEach>
						</select>
					</td>
					<td>
						<c:if test="${sessionScope.persona.papel==0}"><p class="lroja">No Registrado</p></c:if>
						<c:if test="${sessionScope.persona.papel==3}">Estudiante</c:if>
						<c:if test="${sessionScope.persona.papel==2}">Prof Planta</c:if>
						<c:if test="${sessionScope.persona.papel==4}">Prof TCO</c:if>
						<c:if test="${sessionScope.persona.papel==5}">Prof MTO</c:if>
						<c:if test="${sessionScope.persona.papel==6}">Prof HC</c:if>
						<c:if test="${sessionScope.persona.papel==7}">Prof Vin. Especial</c:if>
						<c:if test="${sessionScope.persona.papel==8}">Lider Semillero</c:if>
						<c:if test="${sessionScope.persona.papel==1}">Director</c:if>

						<c:if test="${sessionScope.persona.papel==9}">Invitado</c:if>
						<c:if test="${sessionScope.persona.papel==10}">Egresado</c:if>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" class="renglones"><b>Proyecto Curricular:</b></td>
		<td colspan="5">
		<c:if test='${sessionScope.persona.nombProyecto!=null}'>
			<c:out value="${sessionScope.persona.nombProyecto}"/>
		</c:if>
		<c:if test='${sessionScope.persona.nombProyecto==null}'>
			<p class="lroja">No Registrado</p>
		</c:if>
		</td>
	</tr>
	<tr>
		<td colspan="6">
			<table width="100%">
				<tr>
					<td class="renglones"><b>Nombres:</b></td>
					<td class="renglones"><b>Apellidos:</b></td>
					<td class="renglones" width="100px"><b>Codigo UD:</b></td>
				</tr>
				<tr>
					<td><c:out value="${sessionScope.persona.nombres}" default="No Registrado"/></td>
					<td><c:out value="${sessionScope.persona.apellidos}" default="No Registrado"/></td>
				<c:if test='${sessionScope.persona.codigo!="" and sessionScope.persona.codigo!=null}'>
					<td ><c:out value="${sessionScope.persona.codigo}" /></td>
				</c:if>
				<c:if test='${sessionScope.persona.codigo==null}'>
					<td ><p class="lroja">No Registrado</p></td>
				</c:if>
				</tr>
				<tr>
					<td class="renglones"><b>Teléfono:</b></td>
					<td class="renglones"><b>Celular:</b></td>
					<td class="renglones"><b>e-mail:</b></td>
				</tr>
				<tr>
				<c:if test='${sessionScope.persona.telefono!=null}'>
					<td><c:out value="${sessionScope.persona.telefono}"/></td>
				</c:if>
				<c:if test='${sessionScope.persona.telefono==null}'>
					<td><p class="lroja">No Registrado</p></td>
				</c:if>
				<c:if test='${sessionScope.persona.celular!=null}'>
					<td><c:out value="${sessionScope.persona.celular}"/></td>
				</c:if>
				<c:if test='${sessionScope.persona.celular==null}'>
					<td><p class="lroja">No Registrado</p></td>
				</c:if>
				<c:if test='${sessionScope.persona.mail!=null}'>
					<td><c:out value="${sessionScope.persona.mail}"/></td>
				</c:if>
				<c:if test='${sessionScope.persona.mail==null}'>
					<td><p class="lroja">No Registrado</p></td>
				</c:if>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="renglones" colspan="6"><b>Documento de identidad:</b></td>
	</tr>
	<tr>
		<td class="renglones"><b>Tipo:</b></td>
		<td width="60px">
			<c:if test="${sessionScope.persona.tipoDoc==0}"><p class="lroja">------</p></c:if>
			<c:if test="${sessionScope.persona.tipoDoc==1}">T.I</c:if>
			<c:if test="${sessionScope.persona.tipoDoc==2}">C.C</c:if>
			<c:if test="${sessionScope.persona.tipoDoc==3}">C.E</c:if>
		</td>
		<td width="10px" class="renglones"><b>#</b></td>
	<c:if test='${sessionScope.persona.documento!=null}'>
		<td><c:out value="${sessionScope.persona.documento}" default="No Registrado"/></td>
	</c:if>
	<c:if test='${sessionScope.persona.documento==null}'>
			<td><p class="lroja">No Registrado</p></td>
	</c:if>
		<td class="renglones"><b>de:</b></td>
	<c:if test='${sessionScope.persona.deDoc!=null}'>
		<td><c:out value="${sessionScope.persona.deDoc}" default="No Registrado"/></td>
	</c:if>
	<c:if test='${sessionScope.persona.deDoc==null}'>
		<td><p class="lroja">No Registrado</p></td>
	</c:if>
	</tr>
	<tr>
		<td colspan="6" class="renglones"><b>P&aacute;gina CvLac Colciencias: </b></td>
	</tr>
	<tr>
		<c:if test='${sessionScope.persona.cvlac!=null}'>
			<td colspan="6"><c:out value="${sessionScope.persona.cvlac}" default="No Registrado"/></td>
		</c:if>
		<c:if test='${sessionScope.persona.cvlac==null}'>
			<td colspan="6"><p class="lroja">No Registrado</p></td>
		</c:if>
	</tr>
</table>
</fieldset>
</div>
<c:if test='${sessionScope.persona.estado}'>
<div align="center">
	<fieldset style="width:580px;">
	<!--<input type="hidden" name="idPropuesta" value="${sessionScope.idPropuesta}">-->
	<input type="hidden" name="id">	
	<input type="hidden" name="estado" value="0">
	<legend>Datos de Evento y Ponencia</legend>
		<table class="tablas">
		<tr>
			<td colspan="4">
				<table width="100%">
					<tr><td class="renglones"><b>Tipo de evento</b></td>
						<td>
							<select name="tipo">
							<option value="0" <c:if test="${sessionScope.movilidad.tipo==0}">selected</c:if> onclick="document.nuevo.pais.value='';document.nuevo.pais.readOnly=false;">-------------</option>
							<option value="1" <c:if test="${sessionScope.movilidad.tipo==1}">selected</c:if> onclick="document.nuevo.pais.value='Colombia';document.nuevo.pais.readOnly=true;" <c:if test="${sessionScope.movilidad.tipo==1}">selected</c:if>>Nacional</option>
							<option value="2" <c:if test="${sessionScope.movilidad.tipo==2}">selected</c:if> onclick="document.nuevo.pais.value='';document.nuevo.pais.readOnly=false;" <c:if test="${sessionScope.movilidad.tipo==2}">selected</c:if>>Internacional</option>
							</select>
						</td>
						<td class="renglones"><b>Pais Evento</b></td>
						<td><input type="text" name="pais" value='<c:out value="${sessionScope.movilidad.pais}"/>'></td>
						<td class="renglones"><b>Ciudad Evento</b></td>
						<td><input style="width:100%" type="text" name="ciudad" value='<c:out value="${sessionScope.movilidad.ciudad}"/>'></td>
					</tr>
				</table>
			</td>
		</tr>
			<tr><td colspan="4" class="renglones"><b>Nombre del evento al que desea asistir</b></td></tr>
			<tr><td colspan="4"><input style="width:100%" type="text" name="nombreEvento" value='<c:out value="${sessionScope.movilidad.nombreEvento}"/>'></td></tr>
			<tr>
				<td colspan="4">
					<table width="100%">
						<tr><td colspan="5" class="renglones"><b>Institución organizadora</b></td></tr>
						<tr>
							<td class="renglones"><b>Siglas</b></td>
							<td><input type="text" name="siglasInstitu" value='<c:out value="${sessionScope.movilidad.siglasInstitu}"/>'></td>
							<td width="30px" align="left"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick="info(1)"></td>
						</tr>
						<tr>
							<tr><td colspan="5" class="renglones"><b>Lista de Universidades Organizadoras</b></td></tr>							
							<tr><td colspan="5"><p class="texto1j">El investigador debe ingresar la lista de universidades e instituciones que soportan el evento.</p></td></tr>
							<tr>
								<td colspan="4"><textarea class="area2" class="area2" style="width:100%;" name="institucion"><c:out value="${sessionScope.movilidad.institucion}"/></textarea></td>
								<td width="30px" align="left"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick="info(2)"></td>
							</tr>
							
						</tr>
						<tr>
							<tr><td colspan="5" class="renglones"><b>Lista de Comite Organizador</b></td></tr>
							<tr><td colspan="5"><p class="texto1j">El investigador debe ingresar la lista del Comité de Arbitraje, publicaciones e indexación de resultados y breve cronograma.</p></td></tr>
							<tr>
								<td colspan="4"><textarea class="area2" class="area2" style="width:100%;" name="lista_arbitraje"><c:out value="${sessionScope.movilidad.lista_arbitraje}"/></textarea></td>
								<td width="30px" align="left"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick="info(8)"></td>
							</tr>
						</tr>
					</table>
				</td>
			</tr>

			<tr>
					<td width="25%" class="renglones"><b>Fecha de inicio del evento</b></td>
					<td width="25%">
						<input type='text' name='fechaInicio' class='caj' readonly='true' id='f_date_a' size='13' value='<c:out value="${sessionScope.movilidad.fechaInicio}"/>'>
						<button type='button' id='f_trigger_a'>...</button>
						<script type='text/javascript'>
			    			Calendar.setup({
				    			inputField     :    'f_date_a',
				    			ifFormat       :    '%Y-%m-%d',
				    			showsTime      :    true,
				    			button         :    'f_trigger_a',
				    			singleClick    :    false,
				    			step           :    1
			    			})
		    			</script>
					</td>
					<td width="25%" class="renglones"><b>Fecha de cierre del evento</b></td>
					<td width="25%">
						<input type='text' name='fechaFin' class='caj' readonly='true' id='f_date_b' size='13' value='<c:out value="${sessionScope.movilidad.fechaFin}"/>'>
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
				</tr>
				<tr>
					<td class="renglones"><b>Página web del Evento</b></td>
					<td colspan="5"><input style="width:100%" type="text" name="pagEvento" value='<c:out value="${sessionScope.movilidad.pagEvento}"/>'></td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%">
							<tr>
								<td class="renglones"><b>Trayectoria del Evento</b></td>
								<td><input  type="text" name="trayectoria" onkeypress="return numeros(event)" value='<c:out value="${sessionScope.movilidad.trayectoria}"/>'></td>
								<td width="30px" align="left"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick="info(3)"></td>
							</tr>
							<tr>
								<td class="renglones"><b>Valor Inscripción Evento</b></td>
								<td><input type="text" name="valorInsc" onkeypress="return numeros(event)" value='<c:out value="${sessionScope.movilidad.valorInsc}"/>'></td>
								<td class="renglones"><b>Moneda</b></td>
								<td>
									<select name="moneda">
										<option value="0" <c:if test="${sessionScope.movilidad.moneda==0}">selected</c:if>>--------</option>
										<option value="1" <c:if test="${sessionScope.movilidad.moneda==1}">selected</c:if>>Peso Colombiano</option>
										<option value="2" <c:if test="${sessionScope.movilidad.moneda==2}">selected</c:if>>Dolar</option>
										<option value="3" <c:if test="${sessionScope.movilidad.moneda==3}">selected</c:if>>Euro</option>
									</select>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td colspan="4" class="renglones"><b>Nombre de ponencia aprobada</b></td></tr>				
				<tr><td colspan="4"><input style="width:100%" type="text" name="nombrePonencia" value='<c:out value="${sessionScope.movilidad.nombrePonencia}"/>'></td></tr>
				<tr><td colspan="4" class="renglones"><b>Proyecto de investigación realacionado con el artículo que presenta</b></td></tr>				
				<tr>
					<td colspan="3">
						<select name="proyectoinv">
							<option value="0">--------</option>							
						</select>				
					</td>
					<td width="30px" align="left"><img src='<c:url value="/comp/img/lupa3.png"/>' onclick="info(9)"></td>
				</tr>				
				<tr><td colspan="4" class="renglones"><b>Nombre autores de ponencia</b></td></tr>
				<tr><td colspan="4"><input style="width:100%" type="text" name="nombreAutores" value='<c:out value="${sessionScope.movilidad.nombreAutores}"/>'></td></tr>
				<tr>
				<td colspan="4">
					<table align="center">
						<tr>
							<td  colspan="4" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar()"></td>
							<td valign="middle"><b>Paso 1 de 3</b></td>
						</tr>
					</table>
				</td>
			</tr>

		</table>
	</fieldset>
	</div>
</c:if>
<c:if test='${!sessionScope.persona.estado}'>
<h3 align="center">Para poder acceder al formulario de inscripción de esta convocatoria, favor actualizar la información que se encuentra señalada en rojo. Esto lo puede hacer mediante el link "Mis Grupos" del menu principal</h3>
</c:if>
</form>
<table>
	<tr>
		<td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td>
	</tr>
</table>
<form method="post" name="frmAjaxProyecto" action="<c:url value="/movilidad/Ajax.x"/>">
	<input type="hidden" name="dato" value=''>
	<input type="hidden" name="para" value='1'>
</form>
<script type="text/javascript">
ajaxProyecto(document.nuevo.idGrupo);
</script>
</body>
</html>
