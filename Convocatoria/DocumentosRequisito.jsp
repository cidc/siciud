<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
<script>

	function checks(formulario){
		if(formulario.documentos_){
			if(formulario.documentos_.length){
				for(var i=0;i<formulario.documentos_.length;i++){
					if(formulario.documentos_[i].checked==true){
						formulario.documentos[i].disabled=false;
						formulario.obligatorio[i].disabled=false;
						if(formulario.obligatorio_[i].checked==true){
							formulario.obligatorio[i].value="1";
				//			alert("entraA ="+formulario.obligatorio[i].value);
						}else{
							nuevoCompromisos.obligatorio[i].value="0";
				//			alert("entraB ="+formulario.obligatorio[i].value);
						}
					}else{
						formulario.documentos[i].disabled=true;
						formulario.obligatorio[i].disabled=true;
					}
				}
			}
		}
	}

	function chequear(formulario1,formulario2){
		if(formulario2.docInc){
			if(formulario2.docInc.length){
				for(var i=0;i<formulario1.documentos.length;i++){
					for(var j=0;j<formulario2.docInc.length;j++){
						if(formulario2.docInc[j].value==formulario1.documentos[i].value){
							formulario1.documentos_[i].checked=true;
							formulario1.obligatorio_[i].disabled=false;
							if(formulario2.obl[j].value==1){
						//	alert("habilita");
								formulario1.obligatorio_[i].checked=true;
								formulario1.obligatorio_[i].value=="1";
							}
						}
					}
				}
			}
			else{
				if(formulario2.docInc){
					for(var i=0;i<formulario1.documentos.length;i++){
						if(formulario2.docInc.value==formulario1.documentos[i].value){
							formulario1.documentos_[i].checked=true;
						}
					}
				}
			}
		}
	}
	function borrar(formulario){
		if(formulario.documentos.length){
			for(var i=0;i<formulario.documentos.length;i++){
				if(formulario.documentos_[i].checked==true){
					formulario.obligatorio_[i].disabled=false;
				}else{
					formulario.obligatorio_[i].checked=false;
					formulario.obligatorio_[i].disabled=true;
				}
			}
		}else{
			if(formulario.documentos.checked==true){
				formulario.obligatorio_.disabled=false;
			}else{
				formulario.obligatorio_.checked=false;
				formulario.obligatorio_.disabled=true;
			}
		}

	}
	function guardar(){
		checks(document.nuevoCompromisos);
		document.nuevoCompromisos.action='<c:url value="/Convocatoria/llenar1.jsp"/>';
		document.nuevoCompromisos.submit();
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
<%--			<td class="texto1"><c:out value="${sessionScope.convocatoriaOBJ.convNombre}"/></td>--%>
		</tr>
	</table>
	<form name="nuevoCompromisos" method="post">
		<br>
		<input type="hidden" name="accion" value='7'>
		<input type="hidden" name="de" value='35'>
		<input type="hidden" name="irA" value="35">
		<table class="tablas" align="center" cellpadding="3" cellspacing="0">
		<caption>Documentos Requisito de la Convocatoria</caption>
            <tr>
          		<td width="5px" class="renglones">&nbsp;</td><td class="renglones">&nbsp;</td>
          		<td width="5px" class="renglones">&nbsp;</td>
          		<td class="renglones"><b>Nombre del Documento</b></td>
          		<td class="renglones"><b>Oblig.</b></td>
            </tr>
			<c:forEach begin="0" items="${requestScope.listaDocOBJ}" var="lista" varStatus="st">
				<tr>
					<td width="5px"><c:out value="${st.count}"/></td>
				 	<td width="5px"><input type="checkbox" name="documentos_" onclick="borrar(document.nuevoCompromisos)" ><td>
				 	<input type="hidden" name="documentos" value="<c:out value="${lista.codigo}"/>">
					<td><c:out value="${lista.nombre}"/></td>
					<td align="center"><input type="checkbox" name="obligatorio_" disabled="disabled">
						<input type="hidden" name="obligatorio" value="0">

					</td>
				</tr>
			</c:forEach>
			<tr>
    				<td colspan="4"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"> </td>
    			<tr>
		</table>
	</form>
	<form name="frmInsc">
		<c:forEach begin="0" items="${requestScope.listaDocInscOBJ}" var="lista2" varStatus="st">
			<input type="hidden" name="docInc" value='<c:out value="${lista2.codigo}"/>'>
			<input type="hidden" name="obl" value='<c:out value="${lista2.obligatorio}"/>'>
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
	chequear(document.nuevoCompromisos,document.frmInsc);
</script>
</html>
