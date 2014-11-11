<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:useBean id="loginUsuario" class="cidc.general.login.Usuario" scope="session" />
<html>
<head>
<link type='text/css' rel='stylesheet' media='all' href='<c:url value="/comp/js/Calendario/calendar-blue2.css"/>' title='win2k-cold-1' />
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/lang/calendar-es.js"/>'></script>
<script type='text/javascript' src='<c:url value="/comp/js/Calendario/calendar-setup.js"/>'></script>
<script type="text/javascript">
	function enviar(informe,st){
		document.frmStInforme.idInforme.value=informe;
		document.frmStInforme.estado.value=st;
		document.frmStInforme.submit();
	}
	function cambioTipo1(combo){
		if(combo.value==1){
			document.getElementById("st21").style.display="block";
			document.getElementById("st22").style.display="block";
		}else{
			document.getElementById("st21").style.display="none";
			document.getElementById("st22").style.display="none";
			
			
		}
	}
	function enviarDocumento(){
		var msg="";
		if(document.frmCargaDoc.tipo.selectedIndex==0)
			msg=msg+"-) Tipo de documento\n";
		if(document.frmCargaDoc.tipo.selectedIndex=="1"){			
			if(document.frmCargaDoc.nombre.value=="")
				msg=msg+"-) Nombre del documento\n";
			document.frmCargaDoc.accion.selectedIndex="3";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="4"){
			document.frmCargaDoc.accion.value="4";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="2"){
			document.frmCargaDoc.accion.value="9";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="3"){
			document.frmCargaDoc.accion.value="9";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="5"){
			document.frmCargaDoc.accion.value="5";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="6"){
			document.frmCargaDoc.accion.value="5";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="7"){
			document.frmCargaDoc.accion.value="10";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="8"){
			document.frmCargaDoc.accion.value="11";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="9"){
			document.frmCargaDoc.accion.value="12";
		}
		if(document.frmCargaDoc.tipo.selectedIndex=="10"){
			document.frmCargaDoc.accion.value="13";
		}
		if(document.frmCargaDoc.fechaDoc.value=="")
			msg=msg+"-) Fecha del Documento\n";		
		if(document.frmCargaDoc.archivo.value=="")
			msg=msg+"-) Seleccionar un documento para cargar\n";
		else{
			archi=document.frmCargaDoc.archivo.value;
			var ext=archi.substr(archi.lastIndexOf('.'),archi.length);
			if(!(ext==".pdf"))
				msg=msg+"-) El archivo debe ser en formato PDF\n";
		}
		if(document.frmCargaDoc.observaciones.value=="")
			msg=msg+"-) Observaciones del documento\n";
		if(msg!=""){
			msg="Los siguientes campos son obligatorios\n"+msg;	
			alert(msg);
		}else{
			document.frmCargaDoc.submit();
		}
		
	}

</script>
</head>
<c:import url="/general.jsp"/>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${sessionScope.datoConvenio!=null}">
<br>
<table cellpadding="0" cellspacing="0">
	<tr>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=3&idConv=${sessionScope.datoConvenio.idconvenio}"/>'><img border="0" src='<c:url value="/comp/img/convenio/Proyectos.gif"/>'></a></td>
			<td><img src='<c:url value="/comp/img/convenio/DocumentosClick.gif"/>'></td>
			<td><a href='<c:url value="/adminConvenio/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Tiempos.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Personas.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/Participantes.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/Grupos.jsp"/>'><img border="0" src='<c:url value="/comp/img/convenio/GruposInv.gif"/>'></a></td>
			<td><a href='<c:url value="/adminEntidad/GestEntidad.x?validar=3&por=2"/>'><img border="0" src='<c:url value="/comp/img/convenio/Entidades.gif"/>'></a></td>
			<td><a href='<c:url value="/adminConvenio/AdminConvenio.x?accion=15"/>'><img src='<c:url value="/comp/img/prAprobado.gif"/>'></a></td>
	     </tr>
	
</table>
<br>
	<table width="95%" class="tablas" align="center">
		<CAPTION>Datos generales del Convenio</CAPTION>
		<tr>
			<th colspan="5"><b>Nombre del Convenio</b></th>
		</tr>
		<tr>
			<td colspan="5"><c:out value="${sessionScope.datoConvenio.nombreConvenio}"/></td>
		</tr>
		
		<tr>
			<th colspan="5"><b>Nombre del Proyecto</b></th>
		</tr>
		<tr>
			<td colspan="5"><c:out value="${sessionScope.datoConvenio.nombreproyecto}"/></td>
		</tr>
		
		<tr>
			<th width="20%"><b>Número Convenio</b></th>
			<th width="20%"><b>Número disponibilidad</b></th>
			<th width="20%"><b>Fecha de registro</b></th>
			<th width="20%"><b>Fecha de Inicio</b></th>
			<th width="20%"><b>Fecha de finalizacion</b></th>
		</tr>
		<tr>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.codigo}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.numDisp}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fecha}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaInicio}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.datoConvenio.fechaFinalizacion}"/></td>
		</tr>
	</table>
<br>
	<table align="center" width="95%" class="tablas">
		<CAPTION>Documentos</CAPTION>
		  <tr>
		    <th>&nbsp;</th>
		    <th width="75px">Fecha Doc</th>
		    <th width="200px">Nombre Documento</th>
		    <th>Observaciones</th>
		    <th width="100px">Cargado Por</th>
		    <th width="75px">Opción</th>
		  </tr>
					
		  <c:forEach begin="0" items="${sessionScope.listaDocs}" var="lista" varStatus="st">
		  <tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if>>
		 
		   
		  		<c:if test='${lista.nombreArchivo!="" && lista.nombreArchivo!=null}'>
		  			<c:if test='${lista.tipo==2||lista.tipo==3}'>	
		  			<td><a href='<c:url value="/Documentos/Convenios/Informes/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
				  	<c:if test='${lista.tipo==1}'>
					  <td><a href='<c:url value="/Documentos/Convenios/Otros/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
					 
					  <c:if test='${lista.tipo==23 or lista.tipo==4 or lista.tipo==5 or lista.tipo==6}'>
					 	<td><a href='<c:url value="/Documentos/Convenios/Actas/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
					 <c:if test='${lista.tipo==7}'>
				     <td><a href='<c:url value="/Documentos/Convenios/Memorandos/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				     </c:if>
				     <c:if test='${lista.tipo==8}'>
				     <td><a href='<c:url value="/Documentos/Convenios/Proyecto/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				     </c:if>
				     <c:if test='${lista.tipo==9}'>
				     <td><a href='<c:url value="/Documentos/Convenios/Otrosi/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				     </c:if>
				     <c:if test='${lista.tipo==10}'>
				     <td><a href='<c:url value="/Documentos/Convenios/Contratos/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				     </c:if>
		  		</c:if>
			
			  <td align="center"><c:out value="${lista.fechaDoc}" /></td>
			  <td><c:out value="${lista.nombreDocumento}"  /></td>
			  <td><c:out value="${lista.observaciones}"  /></td>
			  <td><c:out value="${lista.usuario}"  /></td>
			  <td>&nbsp;</td>
		  </tr>
		  </c:forEach>
		   
	</table>

	<br>
	<form action='<c:url value="/adminConvenio/CargarDocumento.x"/>' name="frmCargaDoc" method="post" enctype="multipart/form-data" accept="utf-8" accept-charset="utf-8">
		<input type="hidden" name="accion" value="1">
		<table align="center" width="95%" class="tablas">
		<caption>Cargar Documento</caption>
			<tr>
				<td colspan="2">
					<table width="100%" border="1">
						<tr>
							<th>Tipo</th>
							<td>
								<select name="tipo" style="width:120px;" onchange="cambioTipo1(this)" >
									<option value="0">----------------</option>
									<option value="1">Otro Documento</option>
									<option value="2">Informe Final</option>
									<option value="3">Informe Parcial</option>
									<option value="4" >Acta Finalización</option>
									<option value="5" >Acta Cierre/Cancelación</option>
									<option value="6">Acta de Inicio</option>
									<option value="7">Memorandos</option>	
									<option value="8">Proyecto</option>
									<option value="9">Otro si</option>			
									<option value="10">Convenio o Contrato</option>																	
								</select>								
							</td>
							<th><b>Fecha de Entrega</b></th>
							<td width="150px">
								<input type='text' name='fechaDoc' style='width: 70%'  readonly='true' id='f_date_a' size="12" >
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
							
							<th id="st21" style="display: none">Nombre Doc</th>
							<td id="st22" style="display: none"><input name="nombre" style="width: 100%;"></td>
						</tr>					
					</table>
				</td>
			</tr>			
			<tr>
				<th ><b>Documento</b></th>
				<td ><input type="file" name="archivo" style="width: 100%"></td>
			</tr>
			<tr>
				<th colspan="2"><b>Observaciones</b></th>
			</tr>
			<tr>
				<td colspan="2"><textarea name="observaciones" style="width: 100%" rows="2"></textarea></td>
			</tr>
			<tr>
				<td colspan="2"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="enviarDocumento()"> </td>
			</tr>
		</table>
	</form>
		
</c:if>
<c:if test="${sessionScope.datoConvenio==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Proyecto de Investigación</h4>
</c:if>
</body>
</html>