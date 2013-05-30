<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
<script>

	function checks(formulario){
		if(formulario.compromiso_){
			if(formulario.compromiso_.length){
				for(var i=0;i<formulario.compromiso_.length;i++){
					if(formulario.compromiso_[i].checked==true){
						formulario.compromiso[i].disabled=false;
						formulario.obligatorio[i].disabled=false;
						if(formulario.obligatorio_[i].checked==true){
							formulario.obligatorio[i].value="1";



						}else{
							nuevoCompromisos.obligatorio[i].value="0";
				//			alert("entraB ="+formulario.obligatorio[i].value);
						}
					}else{
						formulario.compromiso[i].disabled=true;
						formulario.obligatorio[i].disabled=true;
						formulario.observaciones[i].disabled=true;
                                        //if(formulario.observaciones[i].value==""){
                                                //formulario.observaciones[i].value="0";
                                        //}
					}
				}
			}
		}
	}

	function chequear(formulario1,formulario2){
		if(formulario2.compInc){
			if(formulario2.compInc.length){
				for(var i=0;i<formulario1.compromiso.length;i++){
					for(var j=0;j<formulario2.compInc.length;j++){
						if(formulario2.compInc[j].value==formulario1.compromiso[i].value){
							formulario1.compromiso_[i].checked=true;
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
				if(formulario2.compInc){
					for(var i=0;i<formulario1.compromiso.length;i++){
						if(formulario2.compInc.value==formulario1.compromiso[i].value){
							formulario1.compromiso_[i].checked=true;
						}
					}
				}
			}
		}
	}
	function borrar(formulario){
		if(formulario.compromiso.length){
			for(var i=0;i<formulario.compromiso.length;i++){
				if(formulario.compromiso_[i].checked==true){
					formulario.obligatorio_[i].disabled=false;
				}else{
					formulario.obligatorio_[i].checked=false;
					formulario.obligatorio_[i].disabled=true;
				}
			}
		}else{
			if(formulario.compromiso.checked==true){
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
			<td class="texto1"><c:out value="${sessionScope.convocatoriaOBJ.convNombre}"/></td>
		</tr>
	</table>
	<form name="nuevoCompromisos" method="post">
		<table align="center">
			<tr>
				<td class="texto1"><b>Cantidad de compromisos adicionales que debe seleccionar el investigador</b></td>
				<td class="texto1"><input type="text" size="1" maxlength="1" name="aspectComit" value='<c:out value="${requestScope.cantComp}" />'></td>
			</tr>
		</table>
		<br>
		<input type="hidden" name="accion" value='7'>
		<input type="hidden" name="de" value='2'>
		<input type="hidden" name="irA" value="2">
		<table class="tablas" align="center" cellpadding="3" cellspacing="0">
		<caption>Compromisos de la Convocatoria</caption>
            <tr>
          		<td width="5px" class="renglones">&nbsp;</td><td class="renglones">&nbsp;</td>
          		<td width="5px" class="renglones">&nbsp;</td>
          		<td class="renglones"><b>Nombre del Compromiso</b></td>
          		<td width="270px" class="renglones"><b>Indicador</b></td>
          		<td class="renglones"><b>Oblig.</b></td>
          		<td class="renglones"><b>Valor</b></td>
            </tr>
			<c:forEach begin="0" items="${requestScope.listaCompOBJ}" var="lista" varStatus="st">
				<tr>
					<td width="5px"><c:out value="${st.count}"/></td>
				 	<td width="5px"><input type="checkbox" name="compromiso_" onclick="borrar(document.nuevoCompromisos)" ><td>
				 	<input type="hidden" name="compromiso" value="<c:out value="${lista.codigo}"/>">
					<td><c:out value="${lista.nombre}"/></td>
					<td width="270px"><c:out value="${lista.indicador}"/></td>
					<td align="center"><input type="checkbox" name="obligatorio_" disabled="disabled">
						<input type="hidden" name="obligatorio" value="0">
					<td style="width:30px;"><input type="text" name="observaciones" size="4" maxlength="5" '<c:forEach begin="0" items="${requestScope.listaCompInscOBJ}" var="lista1">' '<c:if test="${lista1.codigo==lista.codigo}">' value="${lista1.valor}" '</c:if>' '</c:forEach>'></td>
					</td>
				</tr>
					</c:forEach>
			<tr>
    				<td colspan="4"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"> </td>
    			<tr>
		</table>
	</form>
	<form name="frmInsc">
		<c:forEach begin="0" items="${requestScope.listaCompInscOBJ}" var="lista2" varStatus="st">
			<input type="hidden" name="compInc" value='<c:out value="${lista2.codigo}"/>'>
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
