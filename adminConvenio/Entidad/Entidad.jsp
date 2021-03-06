<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html>
<head>
<script type="text/javascript" language="javascript" src='<c:url value="/comp/js/lytebox.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
<c:import url="/general.jsp"/>




<script>

var nav4=window.Event ? true : false;
function numeros(eve){
var key=nav4?eve.which :eve.keyCode;
return(key<=13 || (key>=48 && key<=57));
}


function guardar()
{
	 if(ValidarFormulario(document.nuevo))
	 {
  if (confirm("�Confirma que desea actualizar los datos de la entidad?"))
  {
  document.nuevo.validar.value = 2;
  document.nuevo.action='<c:url value="/adminConvenio/Entidad/Llenar.jsp"/>';
  document.nuevo.submit();
  }
 }
} 
		 
function ValidarFormulario(formulario)
{
	
 if(formulario.nombre.value=="")
   {alert("El nombre del Convenio no puede estar vacio");
	return false;
   }
 if(formulario.tipo.value=="")
   {alert("El Tipo del convenio no puede estar vacio");
	return false;
   }
 if(formulario.nacionalidad.value<=5)
   {
    alert("El Pais no puede estar vacio");
	return false;
   }
 if(formulario.ciudad.value=="")
 {
  alert("La ciudad no puede estar vacio");
  return false;
 }
  if(formulario.direccion.value=="")
  {
   alert("La direccion no puede estar vacia");
   return false;
  }
	 

return true;
}


		 
</script>

</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">

<br>
	
	<form name="nuevo" method="post">
	<input type="hidden" name="accion" value="1">
			<table class="tablas" align="center" width="65%" >
			<caption>Informacion Entidad</caption>
				<tr>
					<td>
						<table width="100%">
							
							<tr>
								<td colspan="5" class="renglones"><b>Nombre Entidad</b></td>
							</tr>
							<tr>
								<td colspan="5"><textarea class="texto" name="nombre" style="width: 99%"  maxlength="70" >${sessionScope.entidades.nombre}</textarea></td>
							</tr>
							<tr>
								<td colspan="5">
									<table width="100%">
										<tr>
										
										<td class="renglones"><b>Tipo Entidad</b></td>
											<td>
												<select name="tipo" style="width: 100%">
													<option value="Publica" <c:if test="${sessionScope.entidades.tipo=='Publica'}">selected</c:if>>Publica</option>
													<option value="Privada" <c:if test="${sessionScope.entidades.tipo=='Privada'}">selected</c:if>>Privada</option>
												</select>
											</td>
											<td class="renglones"><b>Pais</b></td>
											<td><textarea class="texto" name="nacionalidad" style="width: 99%"  maxlength="50" >${sessionScope.entidades.nacionalidad}</textarea></td>
											<td class="renglones"><b>Ciudad</b></td>
											<td><textarea class="texto" name="ciudad" style="width: 99%"  maxlength="50" >${sessionScope.entidades.ciudad}</textarea></td>
								</tr>
										<tr>
								<td colspan="6" class="renglones"><b>Direccion</b></td>
							</tr>
							<tr>
							
								<td colspan="6"><textarea class="texto" name="direccion" style="width: 99%" rows="1" maxlength="70" >${sessionScope.entidades.direccion}</textarea></td>
							</tr>
										
									</table>
								</td>
							</tr>
			<tr>				
					<td colspan="3">
									<table width="100%">
										<tr>
										
										 <tr><td class="renglones"><b>Observaciones:</b></td></tr>
               		<tr> <td><textarea class="texto" name="observaciones" style="width: 99%" maxlength="200" >${sessionScope.entidades.observaciones}</textarea></td>
										
										</tr>
										
									</table>				
					
               		
               		</td>
               		
               		<td colspan="2">
               			<table width="100%">
						<caption>Lista de Telefonos</caption>
						<tr>
							<td class="renglones" width="10px" align="center" ><b>#</b></td>
							<td class="renglones" width="30px" align="center"><b>Telefonos</b></td>
							</tr>
						<c:if test="${!empty sessionScope.entidades.listatelefonos}"> 
						<c:forEach begin="0" items="${sessionScope.entidades.listatelefonos}" var="lista" varStatus="st">
						<tr>
						
							<tr <c:if test="${(st.count mod 2)==0}">class="trb"</c:if> align="center">
							<td class="renglones" width="10px" align="center"><b><c:out value="${st.count}" /></b></td>
							<td class="listas" align="center"> <c:out value="${lista.telefono}" /></td>
							
							
							</tr>
						</c:forEach>
							</c:if>
						<c:if test="${empty sessionScope.entidades.listatelefonos}">
						<td class="renglones" width="10px" align="center"><b>1</b></td>
						<td class="listas" align="center">No hay Numeros Telefonicos Registrados</td>
						</c:if>
					</table>
               		</td>
               		</tr>   
				 </table>
					</td>
		</tr>
					<tr>
					<td align="center"><img src="<c:url value="/comp/img/Modificar.gif"/>" onclick="guardar()"></td>
				</tr>
					
			</table>
			<input type="hidden" name="aporte" value=<c:out value="${sessionScope.entidades.aporte}"/>>
			<input type="hidden" name="validar">
			<input type="hidden" name="id" value=<c:out value="${sessionScope.entidades.id}"/>>
			<input type="hidden" name="fecha" value=<c:out value="${sessionScope.entidades.fecha}"/>>
				
			
  

	</form>


</body>
<script>
mostrar(document.nuevo.cant_tel);
</script>
<% session.removeAttribute("entidades"); %>
</html>