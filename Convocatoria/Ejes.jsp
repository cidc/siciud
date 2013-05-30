<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
<script>

	function enviar(sitio){
		document.parametros.irA.value=sitio;
		document.parametros.submit();
	}

	function chequear(formulario1,formulario2){
		if(formulario2.ejeInc.length){
			for(var i=0;i<formulario1.ejeTematico.length;i++){
				for(var j=0;j<formulario2.ejeInc.length;j++){
					if(formulario2.ejeInc[j].value==formulario1.ejeTematico[i].value){
						formulario1.ejeTematico_[i].checked=true;
					}
				}
			}
		}
		else{
			if(formulario2.ejeInc){
				for(var i=0;i<formulario1.ejeTematico.length;i++){
					if(formulario2.ejeInc.value==formulario1.ejeTematico[i].value){
						formulario1.ejeTematico_[i].checked=true;
					}
				}
			}
		}
	}
	function checks(formulario){
		if(formulario.ejeTematico){
			if(formulario.ejeTematico.length){
				for(var i=0;i<formulario.ejeTematico.length;i++){
					if(formulario.ejeTematico_[i].checked==true){
						formulario.ejeTematico[i].disabled=false;
		//				alert("habilita");
					}else{
						formulario.ejeTematico[i].disabled=true;
		//				alert("deshabilita");
					}
				}
			}
			else{
				if(formulario.ejeTematico_.checked==true){
					formulario.ejeTematico.disabled=false;
				}else{
					formulario.ejeTematico.disabled=true;
				}
			}
		}
	}

	function guardar(){
		checks(document.frmEjes);
		document.frmEjes.action='<c:url value="/Convocatoria/llenar1.jsp"/>';
		document.frmEjes.submit();
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
	<form name="frmEjes" method="post">
		<input type="hidden" name="accion" value='7'>
		<input type="hidden" name="de" value='4'>
		<input type="hidden" name="irA" value="4">
        <table class="tablas" width="600px" align="center">
        <caption>Ejes Temáticos</caption>
         <tr>
          <td class="renglones" colspan="3">&nbsp;</td>
          <td  class="renglones"><b>Nombre del Eje temático</b></td>
          </tr>
				<c:forEach begin="0" items="${requestScope.listaEjesOBJ}" var="lista" varStatus="st">
					<tr>
						<td><c:out value="${st.count}"/></td>
					 	<td><input type="checkbox" name="ejeTematico_">
						 	<input type="hidden" name="ejeTematico" value="<c:out value="${lista.codigo}"/>">
					 	<td>

						<td><c:out value="${lista.nombre}"/></td>
					</tr>
				</c:forEach>
		</table>
         <input type="image" src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()">
	</form>
	<form name="frmInsc">
		<c:forEach begin="0" items="${requestScope.listaEjesInscOBJ}" var="lista" varStatus="st">
			<input type="hidden" name="ejeInc" value='<c:out value="${lista.codigo}"/>'>
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
	chequear(document.frmEjes,document.frmInsc);
</script>
</html>
