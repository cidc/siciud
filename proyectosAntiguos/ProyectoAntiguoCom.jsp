<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<jsp:useBean id="loginUsuario" class="cidc.general.login.Usuario" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	    function tabs(num){
	    	document.formTab.action="<c:url value='/proyectosAntiguos/GestProyectos.x' />";
		    document.formTab.validar.value = num;
			document.formTab.submit();
		}
</script>
<c:import url="/general.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto Antiguo Comité</title>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>

<form name="formTab" method="post" action='<c:url value="/proyectosAntiguos/GestProyectos.x"/>'>
<input type="hidden" name="validar">
<input type="hidden" name="id" value=<c:out value="${sessionScope.proyectos.id}"/>>
<table cellspacing="0" cellpadding="0">
<tr>   <td><img src='<c:url value="/comp/img/DatosGenerales.gif"/>' onclick="tabs(6)"></td>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("4")||loginUsuario.isPerfil("1")){ %>
       		<td><img src='<c:url value="/comp/img/Docsproyectoant.gif"/>' onclick="tabs(7)"></td>
       	<%} %>
       	<%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("6")||loginUsuario.isPerfil("7")||loginUsuario.isPerfil("8")||loginUsuario.isPerfil("1")){ %>
	       <td><img src='<c:url value="/comp/img/Presupuesto.gif"/>' onclick="tabs(8)"></td>
	       <td><img src='<c:url value="/comp/img/prAprobado.gif"/>' onclick="tabs(27)"></td>
       <%} %>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("5")||loginUsuario.isPerfil("1")){ %>
       <td><img src='<c:url value="/comp/img/Investigadores.gif"/>' onclick="tabs(10)"></td>
       <%} %>
       <%if(loginUsuario.isPerfil("16")||loginUsuario.isPerfil("7")||loginUsuario.isPerfil("1")){ %>
       <td><img src='<c:url value="/comp/img/Inventario.gif"/>' onclick="tabs(15)"></td>
       <td><img src='<c:url value="/comp/img/Cambios.gif"/>' onclick="tabs(19)"></td>
       <td><img src='<c:url value="/comp/img/Devoluciones.gif"/>' onclick="tabs(22)"></td>
       <%} %>
       <td><img src='<c:url value="/comp/img/Cambios.gif"/>' onclick="tabs(19)"></td>
</tr>
</table>
</form>

<br>

<table class="tablas" align = "center" width="90%">
     <CAPTION>Consulta Proyecto de Investigación Antiguo</CAPTION>
     <tr> <td>
     <form name="estadoProyecto" method="post" action="<c:url value='/proyectosAntiguos/GestProyectos.x' />">
        <input type="hidden" name="validar" value="29">
		<input type="hidden" name="desde" value="Com">
     <table width="100%">
     <tr> <td class="renglones" style="width: 50px"><b>Año: </b> </td>
          <td ><c:out value="${sessionScope.proyectos.ano}"/></td>
          <td class="renglones"><b>Codigo:</b></td>
          <td ><c:out value="${sessionScope.proyectos.codigo}"/></td>
          <td class="renglones" align="center"><b>Estado Proyecto: </b></td>
          <td>
          <select name="estado">
			<option value="1" <c:if test="${sessionScope.proyectos.estado==1}">selected</c:if>>En Trámite</option>
			<option value="2" <c:if test="${sessionScope.proyectos.estado==2}">selected</c:if>>Vigente</option>
			<option value="3" <c:if test="${sessionScope.proyectos.estado==3}">selected</c:if>>Finalizado</option>
			<option value="4" <c:if test="${sessionScope.proyectos.estado==4}">selected</c:if>>Cancelado</option>
			<option value="5" <c:if test="${sessionScope.proyectos.estado==5}">selected</c:if>>En Prueba</option>
			<option value="6" <c:if test="${sessionScope.proyectos.estado==6}">selected</c:if>>Suspendido</option>
			<option value="7" <c:if test="${sessionScope.proyectos.estado==7}">selected</c:if>>Proc.Finalización</option>
			<option value="8" <c:if test="${sessionScope.proyectos.estado==8}">selected</c:if>>Crítico</option>
			<option value="9" <c:if test="${sessionScope.proyectos.estado==9}">selected</c:if>>Plazo Adicional</option>
	      </select>
          </td>
     </tr>
     </table> 
     
     </td></tr>
     <c:if test="${sessionScope.proyectos.convocatoria!=null}">
     <tr> <td class="renglones"><b><span>Convocatoria:</span></b></td></tr>
     <tr> <td style="width: 97%"> <c:out value="${sessionScope.proyectos.convocatoria}"/> </td></tr>
     </c:if>
     <c:if test="${sessionScope.proyectos.convocatoria==null}">
     <tr> <td class="renglones"><b><span>Convenio:</span></b></td></tr>
     <tr> <c:forEach begin = "0" items="${requestScope.convenios}" var="conv">
          <c:if test="${sessionScope.proyectos.convenio==conv.codigo}"> <td
          > <c:out value="${conv.nombre}"/></td></c:if>
          </c:forEach>
     </tr>
     </c:if>

     <tr> <td class="renglones"><b>Nombre Proyecto:</b></td></tr>
     <tr> <td style="width: 99%"><c:out value="${sessionScope.proyectos.nombre}" /></td></tr>

     <tr> <td> <table width="100%">
     <tr> <td class="renglones" style="width: 210px"><b>Facultad:</b></td>
          <td class="renglones"><b>Proyecto Curricular:</b></td>
     </tr>
       <tr> <td ><c:out value="${sessionScope.proyectos.nomFac}"/></td>
            <td ><c:out value="${sessionScope.proyectos.nomProyCurri}"/></td>
       </tr>
     </table> </td> </tr>

     <tr> <td class="renglones"><b>Grupo/Semillero:</b></td></tr>
     <tr> <td ><c:out value="${sessionScope.proyectos.nomGrupo}"/></td> </tr>

     <tr> <td> <table width="100%">
     <tr> <td class="renglones"><b>Investigador Principal:</b></td>
          <td class="renglones" style="width: 70px"><b>Duración:</b></td>
          <td class="renglones" style="width: 120px"><b>Presupuesto:</b></td>
     </tr>
     <tr> <td><c:out value="${sessionScope.proyectos.nomInves}"/></td>
          <td><c:out value="${proyectos.duracion}"/></td>
          <td><c:out value="${proyectos.presupuesto}"/></td>
     </tr>
     <tr>
		<td colspan="3" align="center"><input type="image" src='<c:url value="/comp/img/Guardar.gif"/>'></td>
	</tr>
     </table>
     </form>
      </td> </tr>

     <tr> <td> <table width="100%">
     <caption>Documentos Proyecto antiguo</caption>
              <tr> <td class="renglones"></td>
                   <td class="renglones"><b>Doc</b></td>
                   <td class="renglones"><b>Fecha</b></td>
                   <td class="renglones"><b>Observaciones</b></td>
              </tr>

              <tr> <td class="renglones"><b>Propuesta:</b></td>
                   <c:if test='${sessionScope.proyectos.nombrePropuesta==null or sessionScope.proyectos.nombrePropuesta==""}'> <td align="center"> <img src='<c:url value="/comp/img/equis1.png"/>'> </td> </c:if>
                   <c:if test="${sessionScope.proyectos.nombrePropuesta!=null}"> <td align="center"> <a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/Propuestas/${sessionScope.proyectos.nombrePropuesta}" />'> <img src='<c:url value="/comp/img/pdf.png"/>'> </a> </td> </c:if>
                   <td align="center">No Aplica</td>
                   <c:if test='${sessionScope.proyectos.obserPro==null or sessionScope.proyectos.obserPro==""}'> <td align="center">Ninguna</td> </c:if>
                   <c:if test="${sessionScope.proyectos.obserPro!=null}"> <td> <c:out value="${sessionScope.proyectos.obserPro}"/> </td> </c:if>
              </tr>

              <tr> <td class="renglones"><b>Acta Inicio:</b></td>
                   <c:if test='${sessionScope.proyectos.nombreActainicio==null or sessionScope.proyectos.nombreActainicio==""}'> <td align="center"> <img src='<c:url value="/comp/img/equis1.png"/>'> </td> </c:if>
                   <c:if test="${sessionScope.proyectos.nombreActainicio!=null}"> <td align="center"> <a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/ActasInicio/${sessionScope.proyectos.nombreActainicio}" />'> <img src='<c:url value="/comp/img/pdf.png"/>'> </a> </td> </c:if>
                   <c:if test='${sessionScope.proyectos.fechaAcIn==null or sessionScope.proyectos.fechaAcIn==""}'> <td align="center"><b>-</b></td> </c:if>
                   <c:if test="${sessionScope.proyectos.fechaAcIn!=null}"> <td align="center"> <c:out value="${sessionScope.proyectos.fechaAcIn}"/> </td> </c:if>
                   <c:if test='${sessionScope.proyectos.obserAcIn==null or sessionScope.proyectos.obserAcIn==""}'> <td align="center">Ninguna</td> </c:if>
                   <c:if test="${sessionScope.proyectos.obserAcIn!=null}"> <td> <c:out value="${sessionScope.proyectos.obserAcIn}"/> </td> </c:if>
              </tr>

              <tr> <td class="renglones"><b>Contrato:</b></td>
                   <c:if test='${sessionScope.proyectos.nombreContrato==null or sessionScope.proyectos.nombreContrato==""}'> <td align="center"> <img src='<c:url value="/comp/img/equis1.png"/>'> </td> </c:if>
                   <c:if test="${sessionScope.proyectos.nombreContrato!=null}"> <td align="center"> <a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/Contratos/${sessionScope.proyectos.nombreContrato}" />'> <img src='<c:url value="/comp/img/pdf.png"/>'> </a> </td> </c:if>
                   <c:if test='${sessionScope.proyectos.fechaCon==null or sessionScope.proyectos.fechaCon==""}'> <td align="center"><b>-</b></td> </c:if>
                   <c:if test="${sessionScope.proyectos.fechaCon!=null}"> <td align="center"> <c:out value="${sessionScope.proyectos.fechaCon}"/> </td> </c:if>
                   <c:if test='${sessionScope.proyectos.obserCon==null or sessionScope.proyectos.obserCon==""}'> <td align="center">Ninguna</td> </c:if>
                   <c:if test="${sessionScope.proyectos.obserCon!=null}"> <td> <c:out value="${sessionScope.proyectos.obserCon}"/> </td> </c:if>
              </tr>

              <tr> <td class="renglones" style="width: 67px"><b>Info Final:</b></td>
                   <c:if test='${sessionScope.proyectos.nombreInfofinal==null or sessionScope.proyectos.nombreInfofinal==""}'> <td align="center"> <img src='<c:url value="/comp/img/equis1.png"/>'> </td> </c:if>
                   <c:if test="${sessionScope.proyectos.nombreInfofinal!=null}"> <td align="center"> <a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/InformesFinales/${sessionScope.proyectos.nombreInfofinal}" />'> <img src='<c:url value="/comp/img/pdf.png"/>'> </a> </td> </c:if>
                   <c:if test='${sessionScope.proyectos.fechaInFin==null or sessionScope.proyectos.fechaInFin==""}'> <td align="center"><b>-</b></td> </c:if>
                   <c:if test="${sessionScope.proyectos.fechaInFin!=null}"> <td align="center"> <c:out value="${sessionScope.proyectos.fechaInFin}"/> </td> </c:if>
                   <c:if test='${sessionScope.proyectos.obserInFin==null or sessionScope.proyectos.obserInFin==""}'> <td align="center">Ninguna</td> </c:if>
                   <c:if test="${sessionScope.proyectos.obserInFin!=null}"> <td> <c:out value="${sessionScope.proyectos.obserInFin}"/> </td> </c:if>
              </tr>

              <tr> <td class="renglones"><b>Acta Final:</b></td>
                   <c:if test='${sessionScope.proyectos.nombreActafinal==null or sessionScope.proyectos.nombreActafinal==""}'> <td align="center"> <img src='<c:url value="/comp/img/equis1.png"/>'> </td> </c:if>
                   <c:if test="${sessionScope.proyectos.nombreActafinal!=null}"> <td align="center"> <a class="lblanca" href='<c:url value="/Documentos/ProyectosAntiguos/ActasFinales/${sessionScope.proyectos.nombreActafinal}" />'> <img src='<c:url value="/comp/img/pdf.png"/>'> </a> </td> </c:if>
                   <c:if test='${sessionScope.proyectos.fechaAcFin==null or sessionScope.proyectos.fechaAcFin==""}'> <td align="center"><b>-</b></td> </c:if>
                   <c:if test="${sessionScope.proyectos.fechaAcFin!=null}"> <td align="center"> <c:out value="${sessionScope.proyectos.fechaAcFin}"/> </td> </c:if>
                   <c:if test='${sessionScope.proyectos.obserAcFin==null or sessionScope.proyectos.obserAcFin==""}'> <td align="center">Ninguna</td> </c:if>
                   <c:if test="${sessionScope.proyectos.obserAcFin!=null}"> <td> <c:out value="${sessionScope.proyectos.obserAcFin}"/> </td> </c:if>
              </tr>
     		</table>
	     </td>
	    </tr>
		<%-- 
		
		este bloque no se elimina debido a que se usara en el futuro para gestionar los subestados
		
		<tr>
	     	<td>
		     	<form name="estadoBandera" method="post" action="<c:url value='/proyectosAntiguos/GestProyectos.x' />">
		     	<input type="hidden" name="validar" value="24">
		     	<input type="hidden" name="desde" value="Com">
		     		<table width="100%" class="tablas">
		     			<CAPTION>Estado de revisión del proyecto <c:out value="${sessionScope.proyectos.flag}" /></CAPTION>
    					<tr>
		     				<td class="renglones"><b>Sin Revizar</b></td>
		     				<td><img src='<c:url value="/comp/img/flag0.gif"/>'></td>
		     				<td><input type="radio" name="flag" value="0" <c:if test="${sessionScope.proyectos.flag==0}">checked</c:if>></td>
		     				<td>El proyecto aun no tiene revisión</td>
		     			</tr>
	     				<tr>
		     				<td class="renglones"><b>Ok</b></td>
		     				<td><img src='<c:url value="/comp/img/flag1.gif"/>'></td>
		     				<td><input type="radio" name="flag" value="1" <c:if test="${sessionScope.proyectos.flag==1}">checked</c:if>></td>
		     				<td>El proyecto no presenta ninguna novedad</td>
		     				</tr>
	     				<tr>
		     				<td class="renglones"><b>N. Atención</b></td>
		     				<td ><img src='<c:url value="/comp/img/flag2.gif"/>'></td>
		     				<td><input type="radio" name="flag" value="2" <c:if test="${sessionScope.proyectos.flag==2}">checked</c:if>></td>
		     				<td>Necesita revisón detallada de estado académico o financiero<td></td>
		     			</tr>
	     				<tr>
		     				<td class="renglones"><b>Crítico</b></td>
		     				<td><img src='<c:url value="/comp/img/flag3.gif"/>'></td>
		     				<td><input type="radio" name="flag" value="3" <c:if test="${sessionScope.proyectos.flag==3}">checked</c:if>></td>
		     				<td>Necesita atención del Comité de investigaciones</td>
		     			</tr>
		     			<tr>
			     			<td colspan="4" align="center"><input type="image" src='<c:url value="/comp/img/Guardar.gif"/>'></td>
		     			</tr>
		     		</table>
		     	</form>
	     	</td>
	     </tr> --%>
	     <tr>
	     	<td>
	     	<form name="observProyect" method="post">
	     	<input type="hidden" name="validar" value="25">
	     	<input type="hidden" name="idObservacion" value="">
	     		<table style="width:100%" class="tablas">
	     			<CAPTION>Observaciones</CAPTION>
	     			<tr>
		     			<td width="120px" class="renglones"><b>Por</b></td>
	     				<td width="70px" class="renglones"><b>Fecha</b></td>
	     				<td class="renglones"><b>Observación</b></td>
	     			</tr>
	     			<tr class="trb">
	     				<td width="120px">Digitador</td>
	     				<td width="70px">&nbsp;</td>
	     				<td class="texto1j"><c:out value="${sessionScope.proyectos.observacion}" default="--"/></td>
	     			</tr>
	     			<c:forEach begin="0" items="${sessionScope.proyectos.listaObservaciones}" var="lista" varStatus="st">
					<tr <c:if test="${st.count mod 2 == 0}">class="trb"</c:if>>
						<td width="120px"><b><c:out value="${lista.usuario}"/></b></td>
						<td width="70px"><c:out value="${lista.fecha}"/></td>
						<td class="texto1j"><c:out value="${lista.observacion}"/></td>
					</tr>
	     			</c:forEach>
	     			<tr>
		     			<td colspan="3">
			     			<textarea name="obsProyecto" style="width:100%"></textarea>
		     			</td>
	     			</tr>
	     			<tr>
	     				<td colspan="3" align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="document.observProyect.submit();"></td>
	     			</tr>
	     		</table>
	     	</form>
	     	</td>
	     </tr>
</table>

</body>
</html>