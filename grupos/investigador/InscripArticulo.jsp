<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<html>
<head>
<c:import url="/general.jsp"/>
<script>

	function comando(){
	//	document.comm.action.value='<c:url value="/invest/articInvestig.x"/>';
		document.comm.submit();
	}

	function cambio(val){
		document.nuevo.accion=val;
		if(val==1){
			document.getElementById("uno").style.display=""
			document.getElementById("dos").style.display="none"
		}else{
			document.getElementById("uno").style.display="none"
			document.getElementById("dos").style.display=""
		}
	}

	function enviar(tipoInscripcion){
		if(tipoInscripcion==1){
			if(document.nuevo.archivo.value!=""){
				var archi=document.nuevo.archivo.value;
				var ext=archi.substr(archi.lastIndexOf('.'),archi.length);
				if(ext!=".doc" && ext!=".pdf")
					alert("-) El archivo debe ser en formato Word 97-2003 o PDF");
				else
					if(validar())
						document.nuevo.submit();
			}
		}
		else{
			document.nuevo.accion.value=2;
			document.nuevo.submit();
		}
	}

	function validar(){
		mensaje="";
		if(document.nuevo.titulo.value==""){
			mensaje=mensaje+"\n-) Titulo del Artículo";
		}
		if(document.nuevo.autores.value==""){
			mensaje=mensaje+"\n-) Autores";
		}
		if(document.nuevo.palabClaves.value==""){
			mensaje=mensaje+"\n-) Palabras Claves";
		}
		if(document.nuevo.tema.selectedIndex==0){
			mensaje=mensaje+"\n-) Área de Trabajo";
		}
		if(document.nuevo.archivo.value==""){
			mensaje=mensaje+"\n-) Archivo Resumen";
		}
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios: "+mensaje;
			alert (mensaje);
		}
		else
			return true;
		return false;
	}

</script>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
	<br>
	<br>
	<table align="center">
		<tr>
			<td align="center"><b>II ENCUENTRO DE GRUPOS Y SEMILLEROS DE INVESTIGACIÓN DE LA UNIVERSIDAD DISTRITAL</b></td>
		</tr>
		<tr>
			<td align="center"><b>19 y 20 de Octubre de 2009.</b></td>
		</tr>
	</table>

	<table align="center" width="90%">
		<tr>
			<td colspan="2" >
			<p class="texto1j">
				<b>
				Para participar en el II ENCUENTRO DE GRUPOS Y SEMILLEROS DE INVESTIGACIÓN DE LA UNIVERSIDAD DISTRITAL, usted deberá seleccionar
				alguna de las dos posibles opciones: 1) Ponente: Personas interesadas en participar me diante la inscripción de un artículo de investigación el cual deberá exponer en el evento 2) Asistente: Personas que asistirán a las conferencias pero no expondrán ningun artículo de investigación.
				</b>
			</p>
			</td>
		</tr>
		<tr>
			<tr>
			<td align="center"><br>
				<input type="radio" value="1" name="op" onclick="cambio(1)">
				<b>Ponente</b>
			</td>
			<td align="center"><br>
				<input type="radio" value="2" name="op" onclick="cambio(2)">
				<b>Asistente</b>
			</td>
		</tr>
		</tr>
		<tr>
			<td align="center" colspan="2"><p class="lroja">Para un correcto funcionamiento del sistema se recomienda usar el navegador Mozilla o habilitar el contenido de Javascript de su navegador Internet Explorer</p>
			</td>
		</tr>
	</table>
<br>
	<form name="nuevo" method="post"  enctype="multipart/form-data"  action='<c:url value="/invest/articInvestig.x"/>'>
		<input type="hidden" name="accion" value="1">
		<input type="hidden" name="tipo" value="1">
		<input type="hidden" name="presentador" value='<c:out value="${sessionScope.loginUsuario.idUsuario}" />'>
		<table id="uno" class="tablas" align="center" width="480px" style="display: none">
		<caption>Inscripción de Artículo</caption>
			<tr>
				<td class="renglones"><b>Título Artículo </b></td>
				<td><input style="width:100%" type="text" name="titulo" value=""></td>
			</tr>
			<tr>
				<td class="renglones"><b>Presentador</b></td>
				<td><c:out value="${sessionScope.loginUsuario.nombre}" default="--" /></td>
			</tr>
			<tr>
				<td class="renglones"><b>Autores</b></td>
				<td><input style="width:100%" type="text" name="autores" value=""></td>
			</tr>
			<tr>
				<td class="renglones"><b>Palabras Claves</b></td>
				<td><input  style="width:100%" type="text" name="palabClaves" value=""></td>
			</tr>
			<tr>
				<td class="renglones"><b>Área de Trabajo</b></td>
				<td>
					<select style="width:100%;" name="tema" style="width:190px">
						<option value="0">----------------------------</option>
						<option value="1">Educación, Desarrollo y Sociedad</option>
						<option value="2">Ciencia, Tecnologia e Innovacion</option>
						<option value="3">Comunicación, Arte y Cultura</option>
						<option value="4">Ciencia y Tecnología de la Información y el conocimiento</option>
						<option value="5">Espacio público, Ambiente, Biodiversidad y Sostenibilidad</option>
						<option value="6">Competitividad y Emprendimiento</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="renglones"><b>Tipo de Presentación</b></td>
				<td>
					<select style="width:100%;" name="tipoPre" style="width:190px">
						<option value="1">Oral</option>
						<option value="2">Poster</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="renglones"><b>Presentar en:</b></td>
				<td>
					<select style="width:100%;" name="para" style="width:190px">
						<option value="1" <c:if test="${sessionScope.grupo.tipo==1}">selected="selected"</c:if> >Grupos de Investigación</option>
						<option value="2" <c:if test="${sessionScope.grupo.tipo==2}">selected="selected"</c:if> >Semillero de Investigación</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="renglones"><b>Documento</b></td>
				<td><input style="width:100%" readonly type="file" name="archivo" ></td>
			</tr>
			<tr>
				<td colspan="2" class="lroja">* Favor utilizar el navegador Mozilla ya que Internet Explorer presenta algunos inconvenientes en la carga del documento</td>
			</tr>
			<tr>
				<td colspan="2" align="center"><img src='<c:url value="/comp/img/Enviar.gif"/>' onclick="enviar(1)"></td>
			</tr>
		</table>
		<table id="dos" style="display:none" align="center">
			<tr>
				<td align="center">
					<br>
					Para inscribir su participación como aisstente solo es necesario dar clik en el botón "Inscribir"
					<br>
					<br>
					<img border="0" src='<c:url value="/comp/img/Inscribir.gif" />' onclick="enviar(2)">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
