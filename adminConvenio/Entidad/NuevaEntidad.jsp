<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat"%>
<html>
<head>
<script type="text/javascript" language="javascript" src='<c:url value="/comp/js/lytebox.js"/>'></script>
<link rel="stylesheet" href='<c:url value="/comp/css/lytebox.css"/>' type="text/css" media="screen" />
<c:import url="/general.jsp"/>

<%
   Date dNow = new Date();
   SimpleDateFormat ft =
   new SimpleDateFormat ("yyyy-dd-MM");
   String currentDate = ft.format(dNow);
%>




<script>

var nav4=window.Event ? true : false;
function numeros(eve){
var key=nav4?eve.which :eve.keyCode;
return(key<=13 || (key>=48 && key<=57));
}

function mostrar(combo)
		{for(var i=1; i<=10; i++)
		 {document.getElementById("tr_a"+i).style.display='none';
          document.getElementById("tr_a"+i).disabled=true;
          document.getElementById("tr_b"+i).style.display='none';
          document.getElementById("tr_b"+i).disabled=true;
          document.getElementById("txt_telefono"+i).disabled=true;
         
         }
         if(combo.selectedIndex!=0)
         {for(var i=1; i<=combo.value; i++)
          {document.getElementById("tr_a"+i).style.display='';
           document.getElementById("tr_a"+i).disabled=false;
           document.getElementById("tr_b"+i).style.display='';
           document.getElementById("tr_b"+i).disabled=false;
           document.getElementById("txt_telefono"+i).disabled=false;
           
          }
         }
		}
function guardar()
		{
	if(ValidarFormulario(document.nuevo))
    {
	
		     document.nuevo.validar.value = 1;
		      document.nuevo.action='<c:url value="/adminConvenio/Entidad/Llenar.jsp"/>';
			  document.nuevo.submit();
		  
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
		 if(formulario.nacionalidad.value=="")
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
		 
		 if(document.nuevo.cant_tel.selectedIndex!=0)
		 {
		  for(var i=1; i<=document.nuevo.cant_tel.value; i++)
          { 
           if(document.getElementById("txt_telefono"+i).value=="")
           {
            alert("Debe digitar los numeros Telefonicos");
            i=document.nuevo.cant_tel.value;
		    return false;
           }
          }
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
			<caption>Creación de Nueva Entidad</caption>
				<tr>
					<td>
						<table width="100%">
							
							<tr>
								<td colspan="5" class="renglones"><b>Nombre Entidad</b></td>
							</tr>
							<tr>
								<td colspan="5"><input type="text" name="nombre" size="70" maxlength="70"></td>
							</tr>
							<tr>
								<td colspan="5">
									<table width="100%">
										<tr>
										
										<td class="renglones"><b>Tipo Entidad</b></td>
											<td>
												<select name="tipo" style="width: 100%">
													<option value="Publica">Publica</option>
													<option value="Privada">Privada</option>
												
												</select>
											</td>
										
											<td class="renglones"><b>Pais</b></td>
											<td><input type="text" name="nacionalidad" size="40" maxlength="50"></td>
											<td class="renglones"><b>Ciudad</b></td>
											<td><input type="text" name="ciudad" size="40" maxlength="50"></td>
								</tr>
										<tr>
								<td colspan="6" class="renglones"><b>Direccion</b></td>
							</tr>
							<tr>
								<td colspan="6"><input type="text" name="direccion" size="70" maxlength="70"></td>
							</tr>
										
										
										
										
									</table>
								</td>
							</tr>
							
							
							<!--Inicio de telefonos -->
							
			<tr> <td> <table width="100%">
               <tr> <td class="renglones"><b>Telefonos:</b></td>
                    <td class="renglones"><b>Seleccione Cantidad:</b> <select name="cant_tel" style="width: 30%" onchange="mostrar(this)">
                                                                      <option value="0">--</option>
                                                                      <option value="1">1</option>
                                                                      <option value="2">2</option>
                                                                      <option value="3">3</option>
                                                                      <option value="4">4</option>
                                                                      <option value="5">5</option>
                                                                      <option value="6">6</option>
                                                                      <option value="7">7</option>
                                                                      <option value="8">8</option>
                                                                      <option value="9">9</option>
                                                                      <option value="10">10</option>
                                                                                                                   </select>
                    </td> 
               </tr>
     </table> </td> </tr>
     <tr> <td>
              <table width="30%">
              <c:forEach begin="1" end="10" varStatus="i">
               <tr id='tr_a<c:out value="${i.count}" />'>
              
               <td class="renglones"><b>Telefono</b></td>
                 
                  
               </tr>
               <tr id='tr_b<c:out value="${i.count}" />'>
                <td><input type="text" name="telefonos" style="width: 100%" id='txt_telefono<c:out value="${i.count}" />'  onkeypress="return numeros(event)"></td>
                  
                   
               </tr>
              </c:forEach>
              </table>
     </td> </tr>		
							
						<!--  fin telefonos -->	
							
							
										
					 <tr> <td class="renglones"><b>Observaciones:</b></td></tr>
               		<tr> <td><textarea class="texto" name="observaciones" style="width: 99%" maxlength="200" >--</textarea></td></tr>
						</table>
					</td>
				</tr>
				<tr>
					<td align="center"><img src="<c:url value="/comp/img/Guardar.gif"/>" onclick="guardar()"></td>
				</tr>
			</table>
			<input type="hidden" name="aporte" value='0'>
			<input type="hidden" name="validar">
			<input type="hidden" name="fecha" value='<%=currentDate%>'>
				
			
  

	</form>


</body>
<script>
mostrar(document.nuevo.cant_tel);
</script>
<% session.removeAttribute("entidades"); %>
</html>