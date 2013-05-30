<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<jsp:useBean id="ParametrosOBJ" scope="page" class="cidc.convocatorias.obj.ParametrosOBJ"/>
<c:import url="/general.jsp"/>
</head>
<script>
	var nav4=window.Event ? true : false;

	function soloNumeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	}

	function cambioComite(combo){
		document.frmAspectos.comite.value=combo.value;
		document.frmDep.submit()
	}

	function checks(formulario){
		if(formulario.aspectos_.length){
			for(var i=0;i<formulario.aspectos_.length;i++){
				if(formulario.aspectos_[i].checked==false){
			//		alert("bloqueado el "+i);
					formulario.aspectos[i].disabled=true;
				}
				else{
			//		alert("habilitado el "+i);
					formulario.aspectos[i].disabled=false;
				}
			}
		}
	}

	function chequear(formulario1,formulario2){
		if(formulario2.aspInsc){
			if(formulario2.aspInsc.length){
				for(var i=0;i<formulario1.aspectos.length;i++){
					for(var j=0;j<formulario2.aspInsc.length;j++){
						if(formulario2.aspInsc[j].value==formulario1.aspectos[i].value){
							formulario1.aspectos_[i].checked=true;
						}
					}
				}
			}
			else{
				if(formulario2.aspInsc){
					for(var i=0;i<formulario1.aspectos.length;i++){
						if(formulario2.aspInsc.value==formulario1.aspectos[i].value){
							formulario1.aspectos_[i].checked=true;
						}
					}
				}
			}
		}
	}

	function guardar(){
		var formulario=document.porcentajes;
		if(ValidarFormulario(formulario)){
			formulario.action='<c:url value="/Convocatoria/llenar1.jsp"/>';
			formulario.submit();
		}
	}

	function guardaCriterios(){
		if(document.frmDep.dependencia.selectedIndex!=0){
			document.frmAspectos.action='<c:url value="/Convocatoria/llenar1.jsp"/>';
			document.frmAspectos.submit();
		}else{
			alert("seleccione el comité evaluador de esta convocatoria");
		}
	}

	function ValidarFormulario(forma){
		var inte=parseFloat(forma.porcentInt.value);
		var exte=parseFloat(forma.porcentExt.value);
		var comite=parseFloat(forma.porcentComit.value);
		var x=inte+exte+comite;
		if(x<100 || isNaN(x) || x>100){
			alert("Los porcentajes de evaluación deben sumar el 100%");
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
	<form method="POST" name="porcentajes" action='<c:url value="/TerminosArchivo.x"/>' method="post">
		<input type="hidden" name="accion" value="7">
		<input type="hidden" name="irA" value="12">
		<table class="tablas" width="70%" align="center">
		<caption>Porcentajes de Evaluación</caption>
			<tr>
				<td class="renglones"><b>Evaluación Interna</b></td>
				<td class="renglones"><b>Evaluación Externa</b></td>
				<td class="renglones"><b>Comité Investigaciones</b></td>
			<tr>
			<tr>
				<td align="center"><input class="caja0" type="text" name="porcentInt" size="4" onKeyPress='return soloNumeros(event)' value='<c:out value="${requestScope.porcentajes.porcentInt}"/>'><b>.Pts</b></td>
				<td align="center"><input class="caja0" type="text" name="porcentExt" size="4" onKeyPress='return soloNumeros(event)' value='<c:out value="${requestScope.porcentajes.porcentExt}"/>'><b>.Pts</b></td>
				<td align="center"><input class="caja0" type="text" name="porcentComit" size="4" onKeyPress='return soloNumeros(event)' value='<c:out value="${requestScope.porcentajes.porcentComit}"/>'><b>.Pts</b></td>
			</tr>
			<c:if test='${sessionScope.convocatoriaOBJ.convTipo==1}'>
				<tr>
				<th align="center">Se evalua:<input type="checkbox" name="convEvalua"></th>
				</tr>
			</c:if>
			<tr>
 				<td align="center" colspan="3"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"> </td>
 			<tr>
		</table>
	</form>
	<form name="frmDep" method="post" action='<c:url value="/Convocatoria/llenar1.jsp"/>'>
		<input type="hidden" name="accion" value="6">
		<input type="hidden" name="irA" value="15">
		<table align="center">
			 <tr>
				 <td class="renglones"><b>Comité Evaluador de esta convocatoria....</b></td>
			 	<td>
			 		<select name="dependencia" onchange="cambioComite(this);">
			 		<option value="0">--</option>
			 		<c:forEach begin="0" items="${requestScope.listaDepend}" var="lista">
				 		<option value='<c:out value="${lista.codigo}" />' <c:if test="${requestScope.id==lista.codigo}">Selected</c:if> ><c:out value="${lista.nombre}" /></option>
				 	</c:forEach>
			 		</select>
			 	</td>
			 </tr>
		</table>
	</form>

	<form name="frmAspectos" method="post">
		<input type="hidden" name="accion" value='7'>
		<input type="hidden" name="irA" value='14'>
		<input type="hidden" name="comite" value='<c:out value="${requestScope.id}"/>'>
		<table class="tablas" align="center" width="90%" cellpadding="3" cellspacing="0">
            <caption>Aspectos a evaluar por parte del Comité evaluador</caption>
			<c:forEach begin="0" items="${requestScope.aspectos}" var="lista2" varStatus="st">
				<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				 	<td width="10px">
				 		<input type="hidden" name="aspectos" value="<c:out value="${lista2.codigo}"/>">
				 		<input type="hidden" name="aspValor" value="100">
				 		<input class="renglones" type="checkbox" name="aspectos_" onchange="checks(document.frmAspectos)">
				 	<td>
					<td align="left"><c:out value="${lista2.nombre}"/></td>
				</tr>
			</c:forEach>
			<tr>
 				<td align="center" colspan="3"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardaCriterios()"> </td>
 			<tr>
		</table>
	</form>
	</c:if>
	<c:if test='${sessionScope.convocatoriaOBJ==null}'>
		<fieldset style="width:450px;">
			<legend class="texto1"><b>Error</b></legend>
			<h5>Favor seleccionar una convocatoria</h5>
		</fieldset>
	</c:if>
	<form name="frmInsc">
		<c:forEach begin="0" items="${requestScope.aspGuard}" var="lista2">
			<input type="hidden" name="aspInsc" value='<c:out value="${lista2}"/>'>
		</c:forEach>
	</form>
</body>
<script type="text/javascript">
	chequear(document.frmAspectos,document.frmInsc);
</script>
</html>
