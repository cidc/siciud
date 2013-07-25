<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	    function tabs(num){
	    	document.formTab.action="<c:url value='/GestionProyectos/ProyectosInvestigador.x' />";
		    document.formTab.accion.value = num;
			document.formTab.submit();
		}
</script>
<c:import url="/general.jsp" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto Antiguo Comité</title>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>

<form name="formTab" method="post">
	<input type="hidden" name="accion">
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td><img border="0" src='<c:url value="/comp/img/tabs/General1.gif"/>'></td>
			<td><img border="0" src='<c:url value="/comp/img/tabs/Balance2.gif"/>' onclick="tabs(6)"></td>
		</tr>
	</table>
</form>

<br>
<form name="form1">
<table class="tablas" align="center" width="90%">
	<CAPTION>Consulta Proyecto de Investigación Antiguo</CAPTION>
	<tr>
		<td>
		<table width="100%">
			<tr>
				<th style="width: 50px"><b>Año: </b></th>
				<td><c:out value="${sessionScope.proyectoInvestigador.ano}" /></td>
				<th><b>Codigo:</b></th>
				<td><c:out value="${sessionScope.proyectoInvestigador.codigo}" /></td>
				<th align="center"><b>Estado Proyecto: </b></th>
				<td><c:out value="${sessionScope.proyectoInvestigador.estado}" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<th><b>Convocatoria:</b></th>
	</tr>
	<tr>
		<td><c:out
			value="${sessionScope.proyectoInvestigador.numConvocatoria}" /> - <c:out
			value="${sessionScope.proyectoInvestigador.convocatoria}"
			default="No registra Convocatoria" /></td>
	</tr>
	<tr>
		<th><b>Nombre Proyecto:</b></th>
	</tr>
	<tr>
		<td><c:out value="${sessionScope.proyectoInvestigador.nombre}" /></td>
	</tr>

	<tr>
		<td>
		<table width="100%">
			<tr>
				<th style="width: 210px"><b>Facultad:</b></th>
				<th><b>Proyecto Curricular:</b></th>
			</tr>
			<tr>
				<td><c:out
					value="${sessionScope.proyectoInvestigador.facultad}" /></td>
				<td><c:out
					value="${sessionScope.proyectoInvestigador.proyCurricular}" /></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<th><b>Grupo/Semillero:</b></th>
	</tr>
	<tr>
		<td><c:out
			value="${sessionScope.proyectoInvestigador.grupoInvestigacion}" /></td>
	</tr>

	<tr>
		<td>
		<table width="100%">
			<tr>
				<th><b>Investigador Principal:</b></th>
				<th width="70px"><b>Duración:</b></th>
				<th width="150px"><b>Presupuesto Aprobado:</b></th>
			</tr>
			<tr>
				<td><c:out
					value="${sessionScope.proyectoInvestigador.director}" /></td>
				<td width="70px"><c:out
					value="${sessionScope.proyectoInvestigador.duracion}" default="0" /> Meses</td>
				<td style="text-align: right;width: 105px"><c:out
					value="${sessionScope.proyectoInvestigador.valorLetras}" /></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr>
		<td>
		<table width="100%">
			<caption>Documentos Proyecto de Investigación</caption>
			<tr>
				<th width="15px">&nbsp;</th>
				<th><b>Documento</b></th>
				<th><b>Fecha</b></th>
				<th><b>Observaciones</b></th>

			</tr>
			<tr>
				<td width="15px">
				<c:if test="${sessionScope.proyectoInvestigador.propuesta.url!=null and sessionScope.proyectoInvestigador.propuesta.url!='/ProyectosAntiguos/Propuestas/null'}">
					<a href='<c:url value="/Documentos${sessionScope.proyectoInvestigador.propuesta.url}"/>'><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a>
				</c:if>
				</td>
				<td width="85px"><b>Proyecto</b></td>
				<td width="85px"><c:out value="${sessionScope.proyectoInvestigador.propuesta.fecha}" default="--" /></td>
				<td><c:out value="${sessionScope.proyectoInvestigador.propuesta.observaciones}" default="--" /></td>
			</tr>
			<tr>
				<td width="15px">
				<c:if test="${sessionScope.proyectoInvestigador.contrato.url!=null and sessionScope.proyectoInvestigador.contrato.url!='/ProyectosAntiguos/Propuestas/null'}">
					<a href='<c:url value="/Documentos${sessionScope.proyectoInvestigador.contrato.url}"/>'><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a>
				</c:if>
				</td>
				<td width="85px"><b>Contrato</b></td>
				<td width="85px"><c:out value="${sessionScope.proyectoInvestigador.contrato.fecha}" default="--" /></td>
				<td><c:out value="${sessionScope.proyectoInvestigador.contrato.observaciones}" default="--" /></td>
			</tr>
			<tr>
				<td width="15px">
				<c:if test="${sessionScope.proyectoInvestigador.actaInicio.url!=null and sessionScope.proyectoInvestigador.actaInicio.url!='/ProyectosAntiguos/ActasInicio/null'}">
					<a href='<c:url value="/Documentos${sessionScope.proyectoInvestigador.actaInicio.url}"/>'><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a>
				</c:if>
				</td>
				<td width="85px"><b>Acta Inicio</b></td>
				<td width="85px"><c:out value="${sessionScope.proyectoInvestigador.actaInicio.fecha}" default="--" /></td>
				<td><c:out value="${sessionScope.proyectoInvestigador.actaInicio.observaciones}" default="--" /></td>
			</tr>
			<tr>
				<td width="15px">
				<c:if test="${sessionScope.proyectoInvestigador.actaFinal.url!=null and sessionScope.proyectoInvestigador.actaFinal.url!='/ProyectosAntiguos/ActasFinales/null'}">
					<a href='<c:url value="/Documentos${sessionScope.proyectoInvestigador.actaFinal.url}"/>'><img border="0" src='<c:url value="/comp/img/pdf.png"/>'></a>
				</c:if>
				</td>
				<td width="85px"><b>Acta Final</b></td>
				<td width="85px"><c:out value="${sessionScope.proyectoInvestigador.actaFinal.fecha}" default="--" /></td>
				<td><c:out value="${sessionScope.proyectoInvestigador.actaFinal.observaciones}" default="--" /></td>
			</tr>
			<c:forEach begin="0" items="${sessionScope.proyectoDocumentos}" var="lista" varStatus="st">
		  <tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if>>
		  <td>
		   		<c:if test='${lista.nombreArchivo=="" || lista.nombreArchivo==null}'>
		  			<img border=0 src='<c:url value="/comp/img/equis1.png"/>'>
		  		</c:if>
		  		<c:if test='${lista.nombreArchivo!="" && lista.nombreArchivo!=null}'>
		  			<c:if test='${lista.tipo==2||lista.tipo==3}'>	
		  			<a href='<c:url value="/Documentos/ProyectosAntiguos/Informes/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a>
					 </c:if>
				  	<c:if test='${lista.tipo==1}'>
					  <a href='<c:url value="/Documentos/ProyectosAntiguos/Otros/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a>
					 </c:if>
					 <c:if test='${lista.tipo==21}'>
					  <a href='<c:url value="/Documentos/ProyectosAntiguos/Propuestas/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a>
					 </c:if>
					 <c:if test='${lista.tipo==22}'>
					  <a href='<c:url value="/Documentos/ProyectosAntiguos/Contratos/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a>
					 </c:if>
					  <c:if test='${lista.tipo==23 or lista.tipo==4 or lista.tipo==5}'>
					 	<a href='<c:url value="/Documentos/ProyectosAntiguos/Actas/${lista.nombreArchivo}"/>'><img border=0 src='<c:url value="/comp/img/pdf.png"/>'></a>
					 </c:if>
		  		</c:if>
			</td>
				<td><c:out value="${lista.nombreDocumento}"  /></td>
			  <td align="center"><c:out value="${lista.fechaDoc}" /></td>
			  <td><c:out value="${lista.observaciones}"  /></td>
		  </tr>
		  </c:forEach>
		</table>
		</td>
	</tr>
	<c:if test='${! empty sessionScope.proyectoInvestigador.listaCambios}'>
	<tr>
		<td>
		<table width="100%">
			<caption>Cambios Solicitados</caption>
			<tr>
				<th><b>Tipo</b></th>
				<th><b>Fecha Solicitud</b></th>
				<th><b>Fecha Respuesta</b></th>
				<th><b>Descripcion</b></th>
				<th><b>Observaciones</b></th>
			</tr>
			<c:forEach begin="0" items="${sessionScope.proyectoInvestigador.listaCambios}" var="lista" varStatus="st">
			<tr>
				<td><c:out value="${lista.tipoTxt}"/></td>
				<td><c:out value="${lista.solicitud}"/></td>
				<td><c:out value="${lista.respuesta}"/></td>
				<td><c:out value="${lista.descripcion}"/></td>
				<td><c:out value="${lista.observacion}"/></td>
			</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
	</c:if>
	<c:if test='${sessionScope.proyectoInvestigador.observacionDigit!=null or !empty sessionScope.proyectoInvestigador.listaObservaciones}'>
	<tr>
		<td>
		<table style="width:100%" class="tablas">
			<CAPTION>Observaciones</CAPTION>
			<tr>
				<th width="120px"><b>Por</b></th>
				<th width="70px"><b>Fecha</b></th>
				<th><b>Observación</b></th>
			</tr>
			<c:if test='${sessionScope.proyectoInvestigador.observacionDigit!=null and sessionScope.proyectoInvestigador.observacionDigit!=""}'>
				<tr class="trb">
					<td width="120px">----</td>
					<td width="70px">&nbsp;</td>
					<td class="texto1j"><c:out
						value="${sessionScope.proyectoInvestigador.observacionDigit}"
						default="--" /></td>
				</tr>
			</c:if>
			<c:forEach begin="0"
				items="${sessionScope.proyectoInvestigador.listaObservaciones}"
				var="lista" varStatus="st">
				<tr <c:if test="${st.count mod 2 == 0}">class="trb"</c:if>>
					<td width="120px"><b><c:out value="${lista.usuario}" /></b></td>
					<td width="70px"><c:out value="${lista.fecha}" /></td>
					<td class="texto1j"><c:out value="${lista.observacion}" /></td>
				</tr>
			</c:forEach>
		</table>
		</td>
	</tr>
	</c:if>
</table>
</form>

</body>
</html>
