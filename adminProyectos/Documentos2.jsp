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

	function cambioTipo(combo){
		if(combo.value==1||combo.value==4||combo.value==5){
			document.getElementById("st11").style.display="none";
			document.getElementById("st12").style.display="none";
			document.getElementById("st21").style.display="";
			document.getElementById("st22").style.display="";
			document.frmCargaDoc.nombre.readonly=false;
			if(combo.value==4){
				document.frmCargaDoc.nombre.readonly=true;
				document.frmCargaDoc.nombre.value="Acta de Cierre/Cancelacion";
			}
			if(combo.value==5){
				document.frmCargaDoc.nombre.readonly=true;
				document.frmCargaDoc.nombre.value="Acta de Finalizacion";
			}
		}if(combo.value==2||combo.value==3){
			document.getElementById("st11").style.display="";
			document.getElementById("st12").style.display="";
			document.getElementById("st21").style.display="none";
			document.getElementById("st22").style.display="none";
		}
		if(combo.value==6||combo.value==7){
			document.getElementById("st11").style.display="";
			document.getElementById("st12").style.display="";
			document.getElementById("st21").style.display="none";
			document.getElementById("st22").style.display="none";
		}	
	}
	function enviarDocumento(){
		var msg="";
		if(document.frmCargaDoc.tipo.selectedIndex==0)
			msg=msg+"-) Tipo de documento\n";
		if(document.frmCargaDoc.tipo.value=="1"){			
			if(document.frmCargaDoc.nombre.value=="")
				msg=msg+"-) Nombre del documento\n";
			document.frmCargaDoc.accion.value="3";
		}
		if(document.frmCargaDoc.tipo.value=="4"){
			document.frmCargaDoc.accion.value="4";
		}
		if(document.frmCargaDoc.tipo.value=="5"){
			document.frmCargaDoc.accion.value="5";
		}
		if(document.frmCargaDoc.tipo.value=="6"){
			document.frmCargaDoc.accion.value="6";
		}
		if(document.frmCargaDoc.tipo.value=="7"){
			document.frmCargaDoc.accion.value="7";
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
<c:if test="${sessionScope.proyecto!=null}">
<br>
<table cellpadding="0" cellspacing="0">
	<tr>
		<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=2&id=${sessionScope.proyecto.id}&tipo=${sessionScope.proyecto.claseProyecto}"/>'><img border="0" src='<c:url value="/comp/img/tabs/General1.gif"/>'></a></td>
		<td><img src='<c:url value="/comp/img/tabs/Documentos1.gif"/>'></td>
		<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=7"/>'><img border="0" src='<c:url value="/comp/img/tabs/Balance1.gif"/>'></a></td>
		<td><a href='<c:url value="/adminProyectos/VerTiempos.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Tiempos1.gif"/>'></a></td>
		<td><a href='<c:url value="/adminProyectos/Coinvestigadores.jsp"/>'><img border="0" src='<c:url value="/comp/img/tabs/Investigadores1.gif"/>'></a></td>
		<td><a href='<c:url value="/GestionGeneralProyectos/AdminGeneralProyectos.x?accion=22"/>'><img border="0" src='<c:url value="/comp/img/tabs/Productos.gif"/>'></a></td>
	</tr>
</table>
<br>
	<table width="95%" class="tablas" align="center">
		<CAPTION>Datos generales del proyecto</CAPTION>
		<tr>
			<th colspan="5"><b>Nombre de Proyecto</b></th>
		</tr>
		<tr>
			<td colspan="5"><c:out value="${sessionScope.proyecto.proyecto}"/></td>
		</tr>
		<tr>
			<th width="20%"><b>Director del Proyecto</b></th>
			<th width="20%"><b>Código</b></th>
			<th width="20%"><b>Fecha Aprobación</b></th>
			<th width="20%"><b>Estimado Fin</b></th>
			<th width="20%"><b>Total Aprobado</b></th>
		</tr>
		<tr>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.director}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.codigo}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.fecAprobacion}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.proyecto.fecEstimadoFin}"/></td>
			<td width="20%" align="center"><c:out value="${sessionScope.balanceProyecto.totalAprobado}"/></td>
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
		  
		   		<c:if test='${lista.nombreArchivo=="" || lista.nombreArchivo==null}'>
		  			<td><img border=0 src='<c:url value="/comp/img/equis1.png"/>'></td>
		  		</c:if>
		  		
		  		<c:if test='${lista.nombreArchivo!="" && lista.nombreArchivo!=null}'>
		  			<c:if test='${lista.tipo==3}'>	
		  			<td><a href='<c:url value="/Documentos/ProyectosAntiguos/Informes/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
					 <c:if test='${lista.tipo==2}'>	
		  			<td><a href='<c:url value="/Documentos/ProyectosAntiguos/InformesFinales/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
				  	<c:if test='${lista.tipo==1}'>
					  <td><a href='<c:url value="/Documentos/ProyectosAntiguos/Otros/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
					 <c:if test='${lista.tipo==21}'>
					  <td><a href='<c:url value="/Documentos/ProyectosAntiguos/Propuestas/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
					 <c:if test='${lista.tipo==22}'>
					  <td><a href='<c:url value="/Documentos/ProyectosAntiguos/Contratos/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
					  <c:if test='${lista.tipo==23 or lista.tipo==4 or lista.tipo==5}'>
					 	<td><a href='<c:url value="/Documentos/ProyectosAntiguos/Actas/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
					 </c:if>
					   <c:if test='${lista.tipo==6}'>
				  <td><a href='<c:url value="/Documentos/ProyectosAntiguos/Resoluciones/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
				 </c:if>
				 <c:if test='${lista.tipo==7}'>
				  <td><a href='<c:url value="/Documentos/ProyectosAntiguos/LegalizacionTiempos/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a></td>
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
	<form action='<c:url value="/GestionGeneralProyectos/CargarDocumento.x"/>' name="frmCargaDoc" method="post" enctype="multipart/form-data" accept="utf-8" accept-charset="utf-8">
		<input type="hidden" name="accion" value="1">
		<table align="center" width="95%" class="tablas">
		<caption>Cargar Documento</caption>
			<tr>
				<td colspan="2">
					<table width="100%" border="1">
						<tr>
							<th>Tipo</th>
							<td>
								<select name="tipo" style="width:120px;" onchange="cambioTipo(this)" >
									<option value="0">----------------</option>
									<option value="1">Otro Documento</option>
									<option value="2">Informe Final</option>
									<option value="3">Informe Parcial</option>
									<option value="4" <c:if test="${sessionScope.proyecto.fecActaFin!=null}">disabled</c:if>>Acta Finalización</option>
									<option value="5" <c:if test="${sessionScope.proyecto.fecActaFin!=null}">disabled</c:if>>Acta Cierre/Cancelación</option>	
									<option value="6">Resoluciones de Avance</option>
									<option value="7">Legalizacion Tiempos</option>																		
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
							<th id="st11">Estado</th>
							<td id="st12">
								<select name="estado" style="width: 100px;">
									<option value="1">Revisado</option>
									<option value="2">Evaluado</option>
									<option value="3">Devuelto</option>
								</select>
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
<c:if test="${sessionScope.proyecto==null}">
<br><br><br>
<h4 align="center">No se logró encontrar la información del Proyecto de Investigación</h4>
</c:if>
</body>
</html>