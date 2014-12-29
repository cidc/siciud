<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
        var nav4=window.Event ? true : false;
	    function numeros(eve){
		var key=nav4?eve.which :eve.keyCode;
		return(key<=13 || (key>=48 && key<=57));
	    }

	    function radios(valor){
		    if (valor==1)
		    {
             document.getElementById("select1").style.display='';
             document.getElementById("txtConvo").style.display='none';
             document.getElementById("txt_convenio").innerHTML="Convenio";
             document.getElementById("tr_compromisos").style.display='none';
             document.form1.convocatoria.value=" ";
		    }
		    else
		    {
             document.getElementById("select1").style.display='none';
             document.getElementById("txtConvo").style.display='';
             document.getElementById("txt_convenio").innerHTML="Convocatoria";
             document.getElementById("tr_compromisos").style.display='';
             document.form1.convocatoria.disabled=false;
             document.form1.convenio.selectedIndex=0;
		    }
		 }

         function radios1(){
             document.getElementById("select1").style.display='none';
             document.getElementById("txtConvo").style.display='';
             document.getElementById("txt_convenio").innerHTML="Convocatoria";
             document.getElementById("tr_compromisos").style.display='';
		 }

		function mostrar(combo)
		{
         if(<c:out value="${sessionScope.proyectos.tipo}"/>==2)
         {radios1();}
		}

		function ajaxFacultad(select)
		{
         document.frmAjax.dato.value = select.value;
         document.frmAjax.para.value = 1;
         document.frmAjax.target="frameOculto";
         document.frmAjax.submit();
		}

		function ajaxGrupo(select)
		{
         document.frmAjax.dato.value = select.value;
         document.frmAjax.para.value = 2;
         document.frmAjax.target="frameOculto";
         document.frmAjax.submit();
		}

		function guardar()
		{
		 if (validarCombos()== true )
		 {
		   if(ValidarFormulario(document.form1))
		     {
		      if (confirm("¿Confirma que desea actualizar los datos del proyecto?"))
		      {
		      document.form1.validar.value = 5;
		      document.form1.action='<c:url value="/proyectosAntiguos/Llenar.jsp"/>';
			  document.form1.submit();
			  }
		     }
		  }
		 }

		 function tabs(num){
		    document.formTab.validar.value = num;
			document.formTab.submit();
		 }

		function ValidarFormulario(formulario)
		{
		 if(formulario.nombre.value=="")
		   {alert("El nombre del Proyecto de Investigación no puede estar vacio");
			return false;
		   }
		 if(formulario.duracion.value<=5)
		   {
		    alert("La duración del proyecto no puede ser menor a 6 meses");
			return false;
		   }
		 if(formulario.codigo.value=="")
		 {
		  alert("El código del proyecto no puede estar vacio");
		  return false;
		 }
		 if(document.getElementById("no").checked)
		 {
          if(formulario.convocatoria.value=="")
		  {
		   alert("Debe digitar la convocatoria del proyecto");
		   return false;
		  }
		 }
	     if(document.getElementById("no").checked){
          if(!document.getElementById("comp1").checked && !document.getElementById("comp2").checked && !document.getElementById("comp3").checked && !document.getElementById("comp4").checked!="checked")
          {alert("Debe seleccionar uno o mas compromisos del proyecto"); return false;}
         }
         if(document.form1.palabras.value=="")
	         document.form1.palabras.value=" ";
	      if(document.form1.resumen.value=="")
	         document.form1.resumen.value=" ";
	      if(document.form1.observacion.value=="")
	         document.form1.observacion.value=" ";
	      if(document.form1.objetivo.value=="")
	         document.form1.objetivo.value=" ";
		 return true;
	   }

	   function validarCombos()
	   {
        if(document.form1.ano.selectedIndex==0)
        {alert("Debe seleccionar el año del proyecto"); return false;}
        else {if(document.form1.facultad.selectedIndex==0)
              {alert("Debe seleccionar la facultad del proyecto"); return false;}
              else {if(document.form1.proycurri.selectedIndex==0)
                    {return true;}
                     else {if(document.form1.grupo.selectedIndex==0)
                            {alert("Debe seleccionar el Semillero o Grupo de Investigación del proyecto"); return false;}
                           else{if(document.form1.investigador.selectedIndex==0)
                                {alert("Debe seleccionar el Investigador Principal del proyecto"); return false;}
                                else{if(document.getElementById("si").checked)
                                     {if(document.form1.convenio.selectedIndex==0)
                                      {alert("Debe seleccionar el Convenio del proyecto"); return false;}
                                      if(document.form1.estado.selectedIndex==0)
                                      {alert("Debe seleccionar el estado del Proyecto"); return false;}
                                      else{return true;}
                                     }
                                     else {return true;}
                                }
                               }
                          }
                   }
             }
	   }

</script>
<c:import url="/general.jsp"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Proyecto Antiguo</title>
</head>
<body onLoad="mensajeAlert(document.getElementById('msg'));">
<br>
<form name="formTab" method="post" action='<c:url value="/proyectosAntiguos/GestProyectos.x"/>'>
<input type="hidden" name="validar">
<input type="hidden" name="id" value=<c:out value="${sessionScope.proyectos.id}"/>>
<table cellspacing="0" cellpadding="0">
<tr>   <td><img src='<c:url value="/comp/img/DatosGenerales.gif"/>' onclick="tabs(6)"></td>
       <td><img src='<c:url value="/comp/img/Docsproyectoant.gif"/>' onclick="tabs(7)"></td>
       <td><img src='<c:url value="/comp/img/Presupuesto.gif"/>' onclick="tabs(8)"></td>
       <td><img src='<c:url value="/comp/img/Investigadores.gif"/>' onclick="tabs(10)"></td>
       <td><img src='<c:url value="/comp/img/Inventario.gif"/>' onclick="tabs(15)"></td>
       <td><img src='<c:url value="/comp/img/Cambios.gif"/>' onclick="tabs(19)"></td>
       <td><img src='<c:url value="/comp/img/Devoluciones.gif"/>' onclick="tabs(22)"></td>
</tr>
</table>
</form>
<br>
<form name="form1">
<input type="hidden" name="validar">
<table class="tablas" align = "center" width="80%">
     <CAPTION>Consulta Proyecto de Investigación Antiguo</CAPTION>
     <tr> <td> <table width="100%">
     <tr> <td class="renglones" style="width: 50px"><b>Año: </b> </td>
          <td> <select name="ano">
               <option value="0">----</option>
               <c:forEach begin = "1985" end="2010" var="an">
	     	       <option value='<c:out value="${an}"/>' <c:if test="${sessionScope.proyectos.ano==an}">selected</c:if>><c:out value="${an}"/></option>
     	       </c:forEach>
               </select>
          </td>
          <td class="renglones"><b>Codigo:</b></td>
          <td><input type="text" maxlength="11" name="codigo" style="width: 85%" onkeypress="return numeros(event)" value='<c:out value="${sessionScope.proyectos.codigo}" />'></td>
          <td class="renglones" width="90px"><b>Convenio:</b></td>
          <td><input type="radio" name="tipo" value="1" onclick="radios(1)" <c:if test="${sessionScope.proyectos.tipo==1}">checked</c:if> id="si">Si<input type="radio" name="tipo" value="2" onclick="radios(2)" <c:if test="${sessionScope.proyectos.tipo==2}">checked</c:if> id="no">No</td>
     </tr>
     </table> </td></tr>
     <tr> <td class="renglones"><b><span id="txt_convenio">Convenio:</span></b></td></tr>
     <tr id="select1">
          <td> <select name="convenio" style="width: 100%">
               <option value="0">---------------------------</option>
               <c:forEach begin = "0" items="${requestScope.convenios}" var="conv">
                  <option value=<c:out value="${conv.codigo}"/> <c:if test="${sessionScope.proyectos.convenio==conv.codigo}">selected</c:if>><c:out value="${conv.nombre}"/></option>
               </c:forEach>
               </select>
          </td>
     </tr>
     <tr style="display: none" id="txtConvo"> <td><input type="text" name="convocatoria" style="width: 97%" value='<c:out value="${sessionScope.proyectos.convocatoria}" />'></td></tr>
     <tr> <td class="renglones"><b>Nombre Proyecto:</b></td></tr>
     <tr> <td><textarea name="nombre" style="width: 99%" class="texto"><c:out value="${sessionScope.proyectos.nombre}" /></textarea></td></tr>
     <tr> <td class="renglones"><b>Facultad:</b></td></tr>
     <tr> <td> <select name="facultad" style="width: 100%" onchange="ajaxFacultad(this)">
               <option value="0">-----------------------------------------------</option>
               <option value="1" <c:if test="${sessionScope.proyectos.facultad==1}">selected</c:if>>Facultad Tecnológica</option>
               <option value="2" <c:if test="${sessionScope.proyectos.facultad==2}">selected</c:if>>Facultad de Ingenieria</option>
               <option value="3" <c:if test="${sessionScope.proyectos.facultad==3}">selected</c:if>>Facultad de Medio Ambiente y recursos Naturales</option>
               <option value="4" <c:if test="${sessionScope.proyectos.facultad==4}">selected</c:if>>Facultad de Ciencias y Educación</option>
               <option value="5" <c:if test="${sessionScope.proyectos.facultad==5}">selected</c:if>>ASAB</option>
               </select>
          </td>
     </tr>
     <tr> <td class="renglones"><b>Proyecto Curricular:</b></td></tr>
     <tr> <td> <select name="proycurri" style="width: 100%">
                           <option value="0">---------------------</option>
                           </select>
          </td>
     </tr>
     <tr> <td class="renglones"><b>Grupo/Semillero:</b></td></tr>
     <tr> <td> <select name="grupo" style="width: 100%" onchange="ajaxGrupo(this)">
               <option value="0">-----------</option>
               </select>
          </td>
     </tr>
     <tr> <td class="renglones"><b>Investigador Principal:</b></td></tr>
     <tr> <td> <select name="investigador" style="width: 100%">
               <option value="0">------------------------</option>
               </select>
          </td>
     </tr>
     <tr> <td> <table width="100%">
     <tr> <td class="renglones" style="width: 75px"><b>Duración:</b></td>
          <td> <input type="text" name="duracion" style="text-align:right; width:80%" value=<c:out value="${proyectos.duracion}"/> onkeypress="return numeros(event)" size="3"    ></td>
          <td class="renglones" width="80px"><b>Horas: </b></td>
          <td><input type="text" name="horas" style="text-align:right; width: 80%" value=<c:out value="${proyectos.horas}"/> onkeypress="return numeros(event)" size="2"></td>
          <td class="renglones"><b>Presupuesto:</b></td>
          <td><input type="text" name="presupuesto" style="text-align:right; width: 90%" value=<c:out value="${proyectos.presupuesto}"/> onkeypress="return numeros(event)"></td>
     </tr>
     </table> </td> </tr>
     <tr> <td class="renglones" style="width: 99%"><b>Palabras Claves:</b></td></tr>
     <tr> <td><textarea name="palabras" style="width: 99%" class="texto"><c:out value="${proyectos.palabras}"/></textarea></td></tr>
     <tr> <td class="renglones"><b>Objetivo General:</b></td></tr>
     <tr> <td><textarea name="objetivo" style="width: 99%" class="texto"><c:out value="${proyectos.objetivo}"/></textarea></td></tr>
     <tr> <td class="renglones"><b>Abstract/Resumen:</b></td></tr>
     <tr> <td><textarea name="resumen" style="width: 99%" class="texto"><c:out value="${proyectos.resumen}"/></textarea></td></tr>
     <tr id="tr_compromisos" style="display: none">
          <td> <table width="100%">
                 <tr> <td class="renglones" colspan="2"><b>Compromisos:</b></td></tr>
                 <c:set var="ban1" value="${0}" />
                 <c:set var="ban2" value="${0}" />
                 <c:set var="ban3" value="${0}" />
                 <c:set var="ban4" value="${0}" />
                 <c:forEach begin = "0" items = "${sessionScope.proyectos.compromisos}" var = "com">
                  <c:if test="${com==1}">
                  <c:set var="ban1" value="${1}" />
                  </c:if>
                  <c:if test="${com==2}">
                  <c:set var="ban2" value="${1}" />
                  </c:if>
                  <c:if test="${com==3}">
                  <c:set var="ban3" value="${1}" />
                  </c:if>
                  <c:if test="${com==4}">
                  <c:set var="ban4" value="${1}" />
                  </c:if>
                  </c:forEach>
                 <tr> <td><input type="checkbox" value="1" name="compromisos" id="comp1"  <c:if test="${ban1==1}"> checked="checked" </c:if> >Informe Académico </td>
                      <td><input type="checkbox" value="2" name="compromisos" id="comp2" <c:if test="${ban2==1}"> checked="checked" </c:if> >Informe Técnico</td>
                 </tr>
                 <tr> <td><input type="checkbox" value="3" name="compromisos" id="comp3" <c:if test="${ban3==1}"> checked="checked" </c:if> >Informe Financiero</td>
                      <td><input type="checkbox" value="4" name="compromisos" id="comp4"  <c:if test="${ban4==1}"> checked="checked" </c:if> >Artículo Revista Científica</td>
                 </tr>
               </table>
    		 </td>
    		</tr>
               <tr> <td class="renglones"><b>Observaciones:</b></td></tr>
               <tr> <td><textarea name="observacion" style="width: 99%"><c:out value="${proyectos.observacion}"/></textarea></td></tr>
               <tr> <td class="renglones" align="center"><b>Estado Proyecto: </b>
                          <select name="estado">
                          <option value="0">------------------</option>
                          <option value="1" <c:if test="${sessionScope.proyectos.estado==1}">selected</c:if>>En Trámite</option>
                          <option value="2" <c:if test="${sessionScope.proyectos.estado==2}">selected</c:if>>Vigente</option>
                          <option value="3" <c:if test="${sessionScope.proyectos.estado==3}">selected</c:if>>Finalizado</option>
                          <option value="4" <c:if test="${sessionScope.proyectos.estado==4}">selected</c:if>>Cancelado</option>
                          <option value="5" <c:if test="${sessionScope.proyectos.estado==5}">selected</c:if>>En Prueba</option>
                          <option value="6" <c:if test="${sessionScope.proyectos.estado==6}">selected</c:if>>Suspendido</option>
                          <option value="7" <c:if test="${sessionScope.proyectos.estado==7}">selected</c:if>>Proc.Finalización</option>
                          <option value="8" <c:if test="${sessionScope.proyectos.estado==8}">selected</c:if>>Crítico</option>
                          <option value="9" <c:if test="${sessionScope.proyectos.estado==9}">selected</c:if>>Plazo Adicional</option>
                         </select>
                    </td>
               </tr>
               <tr></tr> <tr></tr>
               <tr> <td align="center"><img src='<c:url value="/comp/img/Modificar.gif"/>' onclick="guardar()"></td></tr>
               <tr>
	     	<td>

</form>
		     	<%-- 
		     	
		     	este bloque no se elimina debido a que se usara en el futuro para gestionar los subestados
		     	
		     	<form name="estadoBandera" method="post" action="<c:url value='/proyectosAntiguos/GestProyectos.x' />">
		     	<input type="hidden" name="validar" value="24">
		     	<input type="hidden" name="desde" value="">
		     		<table width="100%" class="tablas">
		     			<CAPTION>Estado de revisión del proyecto</CAPTION>
    					<tr>
		     				<td class="renglones"><b>Sin Revizar</b></td>
		     				<td><img src='<c:url value="/comp/img/flag0.gif"/>'></td>
		     				<td><input type="radio" name="flag" value="0" <c:if test="${sessionScope.proyectos.flag==0}">checked</c:if>></td>
		     				<td>El proyecto aun no tiene revisión</td>
		     			</tr>
	     				<tr>
		     				<td class="renglones"><b>Ok</b></td>
		     				<td><img src='<c:url value="/comp/img/flag1.gif"/>'></td>
		     				<td><input type="radio" name="flag" value="1" <c:if test="${sessionScope.proyectos.flag==1}">checked</c:if>></td>
		     				<td>El proyecto no presenta ninguna novedad</td>
		     				</tr>
	     				<tr>
		     				<td class="renglones"><b>N. Atención</b></td>
		     				<td ><img src='<c:url value="/comp/img/flag2.gif"/>'></td>
		     				<td><input type="radio" name="flag" value="2" <c:if test="${sessionScope.proyectos.flag==2}">checked</c:if>></td>
		     				<td>Necesita revisón detallada de estado académico o financiero<td></td>
		     			</tr>
	     				<tr>
		     				<td class="renglones"><b>Crítico</b></td>
		     				<td><img src='<c:url value="/comp/img/flag3.gif"/>'></td>
		     				<td><input type="radio" name="flag" value="3" <c:if test="${sessionScope.proyectos.flag==3}">checked</c:if>></td>
		     				<td>Necesita atención del Comité de investigaciones</td>
		     			</tr>
		     			<tr>
			     			<td colspan="4" align="center"><input type="image" src='<c:url value="/comp/img/Guardar.gif"/>'></td>
		     			</tr>
		     		</table>
		     	</form> --%>
	     	</td>
	     </tr>
</table>


	<table>
		<tr>
			<td style="display:none"><iframe name="frameOculto" id="frameOculto"></iframe></td>
		</tr>
	</table>
	<form method="post" name="frmAjax" action="<c:url value="/proyectosAntiguos/Ajax.x"/>">
		<input type="hidden" name="dato" value=''>
		<input type="hidden" name="para" value=''>
	</form>
</body>
<script>
mostrar(document.form1.cant_coinv);
ajaxFacultad(document.form1.facultad);
</script>
</html>