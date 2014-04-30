<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<c:import url="/general.jsp" />

<script> 
	function guarda(){
			if(validar()){ 
				document.CertEsp.accion.value=10;
				document.CertEsp.action='<c:url value="/Certificados/llenar.jsp"/>';
				document.CertEsp.submit();
			}
	}
	
	function validar(){
		mensaje="";
		diferencia=document.CertEsp.cuerpo_cer.value.length-830
		if(document.CertEsp.cedula.value=="")
			mensaje+="\nN�mero de C�dula";
		if(document.CertEsp.cuerpo_cer.value=="")
			mensaje+="\nContenido del certificado";
		if(document.CertEsp.cuerpo_cer.value.length>830){
			mensaje+="\n\nEl contenido no puede exceder  830 caracteres. El mensaje contiene "+diferencia+" de m�s";
		}
		if(mensaje!=""){
			mensaje="Los siguientes campos son obligatorios:"+mensaje;
			alert(mensaje);
			return false;
		}else
			return true;
	}
	
	function isNumberKey(evt){
       var charCode = (evt.which) ? evt.which : event.keyCode
       if (charCode > 31 && (charCode < 48 || charCode > 57))
          return false;

       return true;
    }
	function textCounter(field, maxlimit) {
		if (field.value.length<maxlimit){ 
			alert("El cuerpo del certificado no puede exceder los 850 caracteres")
			return true;
		}else
		 	return false;
		}
</script>

    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Certificado Especial</title>
</head>
<body onload="mensajeAlert(document.getElementById('msg'));">Generaci�n de Certificados Especiales
<div align="center">
<fieldset style="width:580px;">
<form name="CertEsp" method="post" >
	<input type="hidden" name="accion" value="0">
		<table width="100%" align="center">
				<th colspan="4">Generar Certificado</th>
				<tr>
					<td colspan="4" align="justify">El siguiente formulario permite la generaci�n de Certificados Especiales, para ello por favor digite el n�mero de c�dula de la persona a quien se le har� el certificado y el contenido que tendr� el mismo; 
					recuerde que no podr� exceder los 850 caracteres. Al terminar presione el bot�n "Generar Certificado" para crear al archivo.</td>
				</tr>
				<tr>
					<th >N�mero de Cedula</th>
					<td ><input type="text" name="cedula"  onkeypress="return isNumberKey(event)"></td>
				</tr>
				<tr>
					<th colspan="4">Cuerpo del Certificado</th>
				</tr>
				<tr>
					<td colspan="4"><textarea rows="10" cols="20" id="cuerpo_cer" name="cuerpo_cer" ></textarea></td>
				</tr>
				<tr>
					<td  colspan="4" align="center"><img src='<c:url value="/comp/img/nuevocertificado.jpg"/>' onclick="guarda()" width="60" height="40">
					<p>Generar Certificado</p></td>					
				</tr>
		</table>
	</form>
</fieldset>
</div>
<br>

<div align="center">
	<fieldset style="width:580px;">
			<table>
				<th colspan="3">Consideraciones Importantes:</th>
				<tr>
					<td>
						<p align="justify">
						1. Por favor <b class="lroja">valide los datos</b> que aparecen en la parte superior de �ste formulario, en caso de que se presente alguna inconsistencia por favor ingrese a la opci�n Gesti�n Grupo, Mis grupos, Integrantes y actualice su informaci�n
						</p>					
						<p align="justify">
						Si se le presenta alg�n inconveniente con la actualizaci�n de los datos y con la actualizaci�n, por favor env�enos un correo a centroi@udistrital.edu.co 
						</p>
					</td>					
					<td>
					</td>
				</tr>	
				<tr>
					<td>
						<p align="justify">
							2. Para usar, imprimir y visualizar nuestros contenidos de forma �ptima es necesario instalar o tener en el computador el <b class="lroja">programa Adobe Acrobat Reader</b> el cual lo podr� descargar de forma gratuita desde internet.
						</p>
					</td>			
				</tr>
				<tr>
					<td>
						<p align="justify">
						3. La <b class="lroja">conexi�n a internet<b> debe ser estable.
						</p>
					</td>
				</tr>
				<tr>
					<td>
						<p align="justify">
						4. El CIDC no garantiza el acceso y uso continuado o ininterrumpido del sitio. La aplicaci�n puede eventualmente no estar disponible debido a dificultades t�cnicas o fallas de Internet, o por cualquier otra circunstancia ajena; en tales casos se procurar� restablecerlo con la mayor celeridad posible sin que por ello pueda imput�rsele alg�n tipo de responsabilidad.
						</p>
					</td>				
				</tr>	
				<tr>
					<td>
						<p align="justify">
						5. El CIDC no se responsabiliza por la certeza de los datos personales provistos por los usuarios. La informaci�n personal se procesa y almacena en servidores o medios magn�ticos que mantienen altos est�ndares de seguridad y protecci�n tanto f�sica como tecnol�gica.
						</p>
					</td>				
				</tr>	
				<tr>
					<td>
						<p align="justify">
						6. El sistema de informaci�n asignar� un <b class="lroja">identificador al certificado<b>, el cual podr� ser utilizado por terceros para consultar la validez del certificado en la p�gina del CIDC.
						</p>
					</td>				
				</tr>	
			</table>		
	</fieldset>
</div>
<div>
<fieldset>
	<table align="center" class="tablas" width="100%">
		<caption>Lista de Certificados Generados</caption>
		<tr>
			<td class="renglones" align="center" width="5%"><b>#</b></td>
			<td class="renglones" align="center" ><b>Tipo Certificado</b></td>
			<td width="20%" class="renglones" align="center"><b>Fecha y Hora</b></td>
			<td class="renglones" align="center" width="5%"><b>Ver</b></td>
		</tr>
		<c:forEach items="${sessionScope.listaEspeciales}" begin="0" var="lista" varStatus="st">
			<tr <c:if test="${st.count mod 2==0}">class="trb"</c:if>>
				<td><c:out value="${st.count}" /></td>
				<td><c:out value="${lista.tipo}" /></td>
				<td><c:out value="${lista.fecha_cert}" /></td>
				<td valign="middle">
				<a href='<c:url value="/Documentos/Certificados/${lista.url}"/>'><img align="middle" border="0"  src='<c:url value="/comp/img/pdf.png"/>'></a>
				</td>
			</tr>
		</c:forEach>
	</table>
</fieldset>
</div>
</body>
</html>