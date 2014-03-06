<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
</head>
<script>
	function buscar(buscar){
		document.listado.accion.value='1';
		document.listado.ver.value=buscar;
		document.listado.action='<c:url value="/inscripcionConv/ListaConvocatoria.x" />';
		document.listado.submit();
	}
</script>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<c:if test="${!empty sessionScope.listaConvOBJ}">
	<form name="listado" method="post">
		<input type="hidden" name="ver">
		<input type="hidden" name="accion">
        <table class="tablas" width="97%" align="center" >
        <caption >Convocatorias Abiertas Vigentes</caption>
        <tr>
        	<td width="28px" class="renglones">&nbsp</td>
        	<td width="30px" class="renglones"><b>Año</b></td>
        	<td width="20px" class="renglones"><b>#</b></td>
        	<td class="renglones"><b>Nombre</b></td>
        </tr>
			<c:forEach begin="0" items="${sessionScope.listaConvOBJ}" var="lista" varStatus="st">
				<tr>
					<td><c:if test="${lista.convId==sessionScope.datosConv.convId}"><img src='<c:url value="/comp/img/ok.png"/>'></c:if>
						<c:if test="${lista.convId!=sessionScope.datosConv.convId}"><img src='<c:url value="/comp/img/find.gif"/>' onclick="buscar('<c:out value="${lista.convId}"/>')"></c:if>
					</td>
					<td class="listas"><c:out value="${lista.convAno}"/></td>
					<td class="listas"><c:out value="${lista.convNumero}"/></td>
					<td class="listas"><c:out value="${lista.convNombre}"/></td>
				</tr>
			</c:forEach>
		</table>
	</form>

	<c:if test="${sessionScope.datosConv==null}">
		<h4>Seleccione una convocatoria del listado superior</h4>
	</c:if>
	<c:if test="${sessionScope.datosConv!=null}">

		 <table class="tablas" align="center"  id="info" width="400px" >
	        <caption>Descripcion de Convocatoria</caption>
	        <tr>
	        	<td colspan="2" class="renglones"><b>Nombre:</b></td>
	        </tr>
	        <tr>
	        	<td colspan="2"><p class="texto1j"><c:out value="${sessionScope.datosConv.convNombre}"/></p></td>
	        </tr>
	        <tr>
	        	<td colspan="2" class="renglones"><b>Tipo:</b></td>
	        </tr>
	        <tr>
			<c:if test="${sessionScope.datosConv.convTipo==2}">
	        	<td colspan="2"><p class="texto1j">Movilidad</p></td>
			</c:if>
			<c:if test="${sessionScope.datosConv.convTipo==1}">
	        	<td colspan="2"><p class="texto1j">Proy. Inv</p></td>
			</c:if>
	        </tr>
	        <tr>
	        	<td class="renglones" width="50%"><b>Fecha Publicación:</b></td>
	        	<td><c:out value="${sessionScope.datosConv.convFecInicio}"/></td>
	        </tr>
	        <tr>
	        	<td class="renglones" width="50%"><b>Fecha Cierre:</b></td>
	        	<td><c:out value="${sessionScope.datosConv.convFecFin}"/></td>
	        </tr>
	        <tr>
	        	<td class="renglones" width="50%"><b>Duración:</b></td>
	        	<td><c:out value="${sessionScope.datosConv.convDuracion}"/> Mes(es)</td>
	        </tr>
	        <tr>
	        	<td colspan="2" class="renglones"><b>Dirigido A:</b></td>
	        </tr>
	        <tr>
	        	<td colspan="2"><p class="texto1j"><c:out value="${sessionScope.datosConv.convDirigido}"/></p></td>
	        </tr>
	        <tr>
		        <td colspan="2" class="renglones"><b>Información general de la convocatoria</b></td>
	        </tr>
	        <tr>
		        <td colspan="2">
		        	<table width="100%">
				        <tr>
				            <td>
			        		<c:if test='${sessionScope.datosConv.convArchivo!="" and sessionScope.datosConv.convArchivo!=null}'>
					        	<a class="menu"  href='<c:url value="/Documentos/Convocatorias/${sessionScope.datosConv.convArchivo}"/>'>
			    		    		 <img border="0" src='<c:url value="/comp/img/pdf.png"/>'> Términos de Referencia
			        			</a>
		      				</c:if>
			       			</td>
		       				<td>
		        			<c:if test='${sessionScope.datosConv.convArchivo!="" and sessionScope.datosConv.convResolucion!=null}'>
					        	<a class="menu" href='<c:url value="/Documentos/Convocatorias/${sessionScope.datosConv.convResolucion}"/>'>
					        		<img border="0" src='<c:url value="/comp/img/pdf.png"/>'> Resolución de Apertura
					        	</a>
		       				</c:if>
		    	   			</td>
						
                                                <c:forEach begin="0" items="${sessionScope.datosConvDoc}" var="lista2" varStatus="st"> 
                                                        <td>
							<a class="menu" href='<c:url value="/Documentos/Convocatorias/${lista2.convAd}"/>'>
                                                                <img border="0" src='<c:url value="/comp/img/pdf.png"/>'> Adenda
                                                        </a>
							</td>
                                                </c:forEach>
                                                </td>


		       			</tr>
		       		<c:if test='${sessionScope.datosConv.convNumero==9 and sessionScope.datosConv.convAno==2011}'>
		       		<tr>
			       		<td colspan="2" class="renglones"><b>Formatos</b></td>
		       		</tr>
		       		<tr>
			       		<td colspan="2">
			       		<table>
			       			<tr>
				       			<td align="center">
			        				<a class="menu" href='<c:url value="/Documentos/formato_inscripcion.xls"/>'><img border="0" src="<c:url value="/comp/img/pdf.png"/>"> Formato de Inscripción</a>
					        	</td>
					        	<td align="center">
			        				<a class="menu" href='<c:url value="/Documentos/formato_oferta_tecnologica.doc"/>'><img border="0" src="<c:url value="/comp/img/pdf.png"/>"> Formato oferta tecnológica</a>
					        	</td>
					        	<td align="center">
			        				<a class="menu" href='<c:url value="/Documentos/formato_presentacion_propuesta.doc"/>'><img border="0" src="<c:url value="/comp/img/pdf.png"/>"> Formato de Inscripción</a>
					        	</td>
					        </tr>
			       		</table>
			       		</td>
					</tr>
			        </c:if>
		        	</table>
		        </td>
	        </tr>
			<tr>
			<c:if test='${(sessionScope.datosConv.convNumero==5 or sessionScope.datosConv.convNumero==6) and sessionScope.datosConv.convAno==2009 }'>
				<td colspan="2">
	       		<table>
	       			<tr>
		       			<td align="center">
	        				<a class="menu" href='<c:url value="/Documentos/IEIE/Anexo1.xls"/>'><img border="0" src="<c:url value="/comp/img/xcel.jpg"/>"> Formato de presentación de proyectos</a>
			        	</td>
			        	<td align="center">
	        				<a class="menu" href='<c:url value="/Documentos/formato_oferta_tecnologica.doc"/>'><img border="0" src="<c:url value="/comp/img/word.png"/>"> Formato de presupuesto</a>
			        	</td>
			        </tr>
	       		</table>
	       		</td>
			</c:if>
			</tr>
			<tr>
			<c:if test='${sessionScope.loginUsuario.perfil==13}'>
				<td colspan="2" align="center">
					<form action='<c:url value="/inscripcionConv/Inscripcion.x"/>'>
				        <input type="hidden" name="accion" value="1">
				        <table  align="center" width="400px">
			        		<tr>
			        			<td align="center">
			        				<input type="image"  src="<c:url value="/comp/img/Inscripcion.gif"/>" >
					        	</td>
					       </tr>
					     </table>
				     </form>
				</td>
			</c:if>
			<%--<c:if test='${sessionScope.loginUsuario.perfil!=13 and sessionScope.datosConv.inscripcion}'>--%>
			<c:if test='${sessionScope.loginUsuario.perfil!=13}'>
				<td colspan="2" align="center">
					<form action='<c:url value="/inscripcionConv/Inscripcion.x"/>'>
				        <input type="hidden" name="accion" value="1">
				        <table  align="center" width="400px">
			        		<tr>
			        			<td align="center">
			        				<input type="image"  src="<c:url value="/comp/img/Inscribir.gif"/>" >
					        	</td>
					       </tr>
					     </table>
				     </form>
				</td>
			</c:if>

			</tr>
	      </table>
     </c:if>
</c:if>
<c:if test="${empty sessionScope.listaConvOBJ}">
<br>
	<h4 align="center">En este momento No hay convocatorias abiertas</h4>
</c:if>
</body>
</html>
