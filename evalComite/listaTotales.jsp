<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<c:import url="/general.jsp" />
</head>
<script>
	function ver(v,p){
		document.lista.idPon.value=p;
		document.lista.definitiva.value=v;
		document.lista.action='<c:url value="/movilidad/EvalMovilidadComite.x"/>';
		var ob=prompt("Observaciones para esta ponencia","Ninguna");
		document.lista.observacion.value=ob;
		if(ob!="" && ob!=null)
			document.lista.submit();
	}

	function guardar(){

	}
	function ir(){
		if(document.filtro.ano.value==0 || document.filtro.corte.value==0)
			alert("Favor seleccionar el año y el corte de la convocatoria");
		else
			document.filtro.submit();
	}
</script>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>

	<form name="filtro" method="post" action='<c:url value="/movilidad/EvalMovilidadComite.x"/>'>
		<input type="hidden" name="accion" value="2"/>
		<table align="center" class="tablas">
		<caption>Filtro de búsqueda</caption>
			<tr>
				<th><b>Año</b></th>
				<td><select name="ano">
						<option value="0">----</option>
						<option value="2009" <c:if test="${requestScope.ano==2009}">selected</c:if>>2009</option>
						<option value="2010" <c:if test="${requestScope.ano==2010}">selected</c:if>>2010</option>
						<option value="2011" <c:if test="${requestScope.ano==2011}">selected</c:if>>2011</option>
						<option value="2012" <c:if test="${requestScope.ano==2012}">selected</c:if>>2012</option>
					</select>
				</td>
				<th><b>Corte</b></th>

				<td><select name="corte">
						<option value="0">--</option>
						<option value="1" <c:if test="${requestScope.corte==1}">selected</c:if>>1</option>
						<option value="2" <c:if test="${requestScope.corte==2}">selected</c:if>>2</option>
						<option value="3" <c:if test="${requestScope.corte==3}">selected</c:if>>3</option>
						<option value="4" <c:if test="${requestScope.corte==4}">selected</c:if>>4</option>
						<option value="5" <c:if test="${requestScope.corte==5}">selected</c:if>>5</option>
						<option value="6" <c:if test="${requestScope.corte==6}">selected</c:if>>6</option>
						<option value="7" <c:if test="${requestScope.corte==7}">selected</c:if>>7</option>
				</td>
				<td><img onclick="ir()" src='<c:url value="/comp/img/Buscar.gif"/>'></td>
			</tr>
		</table>
	</form>

<c:if test="${! empty requestScope.listaTotales}">
	<br>

	<form name="lista">
	<input type="hidden" name="accion" value="4">
	<input type="hidden" name="idPon">
	<input type="hidden" name="definitiva">
	<input type="hidden" name="observacion">
	<input type="hidden" name="ano" value='<c:out value="${requestScope.ano}" />'>
	<input type="hidden" name="corte" value='<c:out value="${requestScope.corte}" />'>
	<table align="center" class="tablas" width="1200px">
		<caption>Lista de Ponencias Inscritas</caption>
		<tr>
			<td class="renglones" align="center" width="10px"><b>#</b></td>
			<td class="renglones" align="center" width="10px"><b>Id</b></td>
			<td class="renglones" align="center" width="170px"><b>Nombre Presentador</b></td>
			<td class="renglones" align="center"><b>Nombre Ponencia</b></td>
			<td class="renglones" align="center" width="40px"><b>Tec.</b></td>
			<td class="renglones" align="center" width="40px"><b>Ing.</b></td>
			<td class="renglones" align="center" width="40px"><b>Med.</b></td>
			<td class="renglones" align="center" width="40px"><b>Edu.</b></td>
			<td class="renglones" align="center" width="40px"><b>Art.</b></td>
			<td class="renglones" align="center" width="40px"><b>Dir.</b></td>
			<td class="renglones" align="center" width="40px"><b>#Eval.</b></td>
			<td class="renglones" align="center" width="40px"><b>Papel</b></td>
			<td class="renglones" align="center" width="40px"><b>AvGr</b></td>
			<td class="renglones" align="center" width="40px"><b>Acept</b></td>
			<td class="renglones" align="center" width="40px"><b>Resu</b></td>
			<td class="renglones" align="center" width="40px"><b>AvCCur</b></td>
			<td class="renglones" align="center" width="40px"><b>AvJef</b></td>
			<td class="renglones" align="center" width="40px"><b>AvCFa</b></td>			
			<td class="renglones" align="center" width="40px"><b>Exe</b></td>
			<td class="renglones" align="center" width="40px"><b>CeGru</b></td>
			<td class="renglones" align="center" width="40px"><b>CeCIDC</b></td>
			<td class="renglones" align="center" width="40px"><b>Resul.</b></td>
			<td class="renglones" align="center" width="40px"><b>Econ.</b></td>
			<td class="renglones" align="center" width="40px"><b>Total</b></td>
			<td class="renglones" align="center" width="40px" colspan="2"><b>Evaluar</b></td>
		</tr>
		<c:forEach items="${requestScope.listaTotales}" begin="0" var="lista" varStatus="st">
			<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				<td width="10px"><c:out value="${st.count}" /></td>
				<td width="10px"><c:out value="${lista.idPropuesta}" /></td>
				<td width="170px"><c:out value="${lista.investigador}" /></td>
				<td ><c:out value="${lista.nombrePonencia}" /></td>
				<td width="40px" align="center"><c:out value="${lista.tecnologica}" /></td>
				<td width="40px" align="center"><c:out value="${lista.ingenieria}" /></td>
				<td width="40px" align="center"><c:out value="${lista.medioAmbiente}" /></td>
				<td width="40px" align="center"><c:out value="${lista.educacion}" /></td>
				<td width="40px" align="center"><c:out value="${lista.artes}" /></td>
				<td width="40px" align="center"><c:out value="${lista.director}" /></td>
				<td width="40px" align="center"><c:out value="${lista.cantEval}" /></td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.papel==3 || lista.infDocs.papel==5}">Est</c:if><c:if test="${lista.infDocs.papel==1 || lista.infDocs.papel==2}">Prof</c:if>
				</td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.listadDoc[0]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[0]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[0]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.listadDoc[1]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[1]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[1]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.listadDoc[2]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[2]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[2]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.listadDoc[3]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[3]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[3]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</td>
				<td width="40px" align="center">
				<c:if test="${lista.infDocs.papel!=3 and lista.infDocs.papel!=8 and lista.infDocs.papel!=9 and lista.infDocs.papel!=10}">
					<c:if test="${lista.infDocs.listadDoc[4]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[4]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[4]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</c:if>
				</td>
				<td width="40px" align="center">
				<c:if test="${lista.infDocs.papel==3 || lista.infDocs.papel==8 || lista.infDocs.papel==9 || lista.infDocs.papel==10}">
					<c:if test="${lista.infDocs.listadDoc[5]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[5]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[5]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</c:if>
				</td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.listadDoc[6]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[6]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[6]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.listadDoc[7]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[7]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[7]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.listadDoc[8]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[8]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[8]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</td>
				<td width="40px" align="center">
					<c:if test="${lista.infDocs.listadDoc[9]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[9]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[9]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</td>
				<td width="40px" align="center">
				<c:if test="${lista.infDocs.papel==3 || lista.infDocs.papel==8 || lista.infDocs.papel==9 || lista.infDocs.papel==10}">
					<c:if test="${lista.infDocs.listadDoc[10]!=null}">
					<a href='<c:url value="/Documentos/Movilidad/${lista.infDocs.listadDoc[10]}"/>'><img border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
					</c:if>
					<c:if test="${lista.infDocs.listadDoc[10]==null}">
					<img src='<c:url value="/comp/img/equis1.png"/>'>
					</c:if>
				</c:if>
				</td>				
				<td class="renglones" width="50px" align="center"><c:out value="${lista.total}" /></td>
				<c:if test="${lista.estado==1}">
				<td width="18px" valign="middle" align="center"><img src='<c:url value="/comp/img/ok.png"/>'
					onclick='ver(2,<c:out value="${lista.idPropuesta}" />)'></td>
				<td width="18px" valign="middle" align="center"><img src='<c:url value="/comp/img/no.png"/>'
					onclick='ver(3,<c:out value="${lista.idPropuesta}" />)'></td>
				</c:if>
				<c:if test="${lista.estado==2}">
					<td colspan="2" class="rengVerde"></td>
				</c:if>
				<c:if test="${lista.estado==3}">
					<td colspan="2" class="rengGris"></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	</form>
</c:if>
<c:if test="${empty requestScope.listaTotales}">
<br><br><br>
<div align="center">
	<h3  align="center">Favor seleccionar un año y corte de convocatoria</h3>
</div>
<br>
</c:if>
</body>
</html>
