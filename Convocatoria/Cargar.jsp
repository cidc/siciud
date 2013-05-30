<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
</head>
<script>
	
	function guardar(caja,formulario){
		if(ValidarFormulario(caja)){
			formulario.submit();
		}
	}
	
	function ValidarFormulario(forma){
		if(forma.value==""){
			alert("Debe seleccionar un Archivo para cargar");
			return false;
		}
		return true;
	}
</script>
<body>
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
	<fieldset style="width:550px;">                                      
    	<legend class="texto1"><b>Documentos de Convocatoria</b></legend> 
			<br>
			<form method="POST" enctype="multipart/form-data" name="frmTerminos" action='<c:url value="/TerminosArchivo.x"/>' method="post">
				<table class="tablas" width="100%">
				<caption>Términos de Referencia</caption>
					<tr>
						<td>
							<input class="caja0" type="file" name="terminos" size="50">
						</td>
						<td>
							<img src="<c:url value="/comp/img/Cargar.gif"/>" onclick="guardar(document.frmTerminos.terminos,document.frmTerminos)"> 
						</td>
					</tr>
				</table>
			</form>
			
			<form method="POST" enctype="multipart/form-data" name="frmResolu" action='<c:url value="/ResolucionArchivo.x"/>' method="post">
			<table class="tablas" width="100%">
			<caption>Resolución de Apertura</caption>
				<tr>
					<td>
						<input class="caja0" type="file" name="resolucion" size="50">
					</td>
					<td>
						<img src="<c:url value="/comp/img/Cargar.gif"/>" onclick="guardar(document.frmResolu.resolucion,document.frmResolu)"> 
					</td>
				</tr>
			</table>
		<%--	<h5><c:out value="${requestScope.estado}" default="Seleccione el archivo que desea cargar "/></h5>--%>
			</form>
		
			<form method="POST" enctype="multipart/form-data" name="frmAdendo" action='<c:url value="/AdendoArchivo.x"/>' method="post">
			<table class="tablas" width="100%">
			<caption>Adendo</caption>
				<tr>
					<td>
						<input class="caja0" type="file" name="adendo" size="50">
					</td>
					<td>
						<img src="<c:url value="/comp/img/Cargar.gif"/>" onclick="guardar(document.frmAdendo.adendo,document.frmAdendo)"> 
					</td>
				</tr>
			</table>
			</form>
			<h5><c:out value="${requestScope.estado}" default="Seleccione el archivo que desea cargar "/></h5>
		</fieldset>
	</c:if>
	<c:if test='${sessionScope.convocatoriaOBJ==null}'>
		<fieldset style="width:450px;">
			<legend class="texto1"><b>Error</b></legend>
			<h5>Favor seleccionar una convocatoria</h5>
		</fieldset>
	</c:if>
</body>
</html>
