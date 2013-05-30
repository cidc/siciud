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
             document.form1.convocatoria.disabled=true;
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

		function mostrar(combo)
		{for(var i=1; i<=15; i++)
		 {document.getElementById("tr_a"+i).style.display='none';
          document.getElementById("tr_a"+i).disabled=true;
          document.getElementById("tr_b"+i).style.display='none';
          document.getElementById("tr_b"+i).disabled=true;
          document.getElementById("txt_nom_coinv"+i).disabled=true;
          document.getElementById("txt_apell_coinv"+i).disabled=true;
          document.getElementById("txt_tipo_coinv"+i).disabled=true;
         }
         if(combo.selectedIndex!=0)
         {for(var i=1; i<=combo.value; i++)
          {document.getElementById("tr_a"+i).style.display='';
           document.getElementById("tr_a"+i).disabled=false;
           document.getElementById("tr_b"+i).style.display='';
           document.getElementById("tr_b"+i).disabled=false;
           document.getElementById("txt_nom_coinv"+i).disabled=false;
           document.getElementById("txt_apell_coinv"+i).disabled=false;
           document.getElementById("txt_tipo_coinv"+i).disabled=false;
          }
         }
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
		      document.form1.validar.value = 1;
		      document.form1.action='<c:url value="/proyectosAntiguos/Llenar.jsp"/>';
			  document.form1.submit();
		     }
		  }
		 }

		function ValidarFormulario(formulario)
		{
		 if(formulario.nombre.value=="")
		   {alert("El nombre del Proyecto de Investigación no puede estar vacio");
			return false;
		   }
		 if(formulario.presupuesto.value=="")
		   {alert("El valor del presupuesto no puede estar vacio");
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
		 if(document.form1.cant_coinv.selectedIndex!=0)
		 {
		  for(var i=1; i<=document.form1.cant_coinv.value; i++)
          {
           if(document.getElementById("txt_nom_coinv"+i).value=="" || document.getElementById("txt_apell_coinv"+i).value=="")
           {
            alert("Debe digitar los nombres y apellidos del Coinvestigador o Auxiliar");
            i=document.form1.cant_coinv.value;
		    return false;
           }
          }
		 }
	     if(document.getElementById("no").checked){
          if(!document.getElementById("comp1").checked && !document.getElementById("comp2").checked && !document.getElementById("comp3").checked && !document.getElementById("comp4").checked!="checked")
          {alert("Debe seleccionar uno o mas compromisos del proyecto"); return false;}
         }
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
<title>Datos Generales</title>
</head>
<body>
<br>
<form name="form1">
<table class="tablas" align = "center" width="80%">
     <CAPTION>Registro Proyecto de Investigación Antiguo</CAPTION>
     <tr> <td> <table width="100%">
     <tr> <td class="renglones" style="width: 50px"><b>Año: </b> </td>
          <td> <select name="ano">
               <option value="0">----</option>
     	       <c:forEach begin = "1985" end="2010" var="an">
	     	       <option value='<c:out value="${an}"/>'><c:out value="${an}"/></option>
     	       </c:forEach>
               </select>
          </td>
          <td class="renglones"><b>Codigo:</b></td>
          <td><input type="text" maxlength="11" name="codigo" style="width: 85%" onkeypress="return numeros(event)"></td>
          <td class="renglones" width="90px"><b>Convenio:</b></td>
          <td><input type="radio" name="tipo" value="2" onclick="radios(2)" checked="checked" id="si">Si<input type="radio" name="tipo" value="1" onclick="radios(1)" id="no">No</td>
     </tr>
     </table> </td></tr>
     <tr> <td class="renglones"><b><span id="txt_convenio">Convenio:</span></b></td></tr>
     <tr id="select1">
          <td> <select name="convenio" style="width: 100%">
               <option value="0">---------------------------</option>
               <c:forEach begin = "0" items="${requestScope.convenios}" var="conv">
                          <option value='<c:out value="${conv.codigo}"/>'> <c:out value="${conv.nombre}"/> </option>
               </c:forEach>
               </select>
          </td>
     </tr>
     <tr style="display: none" id="txtConvo"> <td><input type="text" name="convocatoria" style="width: 97%"></td></tr>
     <tr> <td class="renglones"><b>Nombre Proyecto:</b></td></tr>
     <tr> <td><textarea class="texto" name="nombre" style="width: 99%"></textarea></td></tr>
     <tr> <td class="renglones"><b>Facultad:</b></td></tr>
     <tr> <td> <select name="facultad" style="width: 100%" onchange="ajaxFacultad(this)">
               <option value="0">-----------------------------------------------</option>
               <option value="1">Facultad Tecnológica</option>
               <option value="2">Facultad de Ingenieria</option>
               <option value="3">Facultad de Medio Ambiente y recursos Naturales</option>
               <option value="4">Facultad de Ciencias y Educación</option>
               <option value="5">ASAB</option>
               </select>
          </td>
     </tr>
     <tr> <td class="renglones"><b>Proyecto Curricular:</b></td></rt>
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
               <tr> <td class="renglones"><b>Coinvestigadores/Auxiliares:</b></td>
                    <td class="renglones"><b>Seleccione Cantidad:</b> <select name="cant_coinv" style="width: 30%" onchange="mostrar(this)">
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
                                                                      <option value="11">11</option>
                                                                      <option value="12">12</option>
                                                                      <option value="13">13</option>
                                                                      <option value="14">14</option>
                                                                      <option value="15">15</option>
                                                                      </select>
                    </td>
               </tr>
     </table> </td> </tr>
     <tr> <td>
              <table width="100%">
              <c:forEach begin="1" end="15" varStatus="i">
               <tr id='tr_a<c:out value="${i.count}" />'> <td class="renglones"><b>Nombres:</b></td>
                   <td class="renglones"><b>Apellidos:</b></td>
                   <td class="renglones"><b>Tipo</b></td>
               </tr>
               <tr id='tr_b<c:out value="${i.count}" />'> <td><input type="text"name="nomCoinvest" style="width: 90%" id='txt_nom_coinv<c:out value="${i.count}" />'></td>
                   <td> <input type="text" name="apellCoinvest" style="width: 90%" id='txt_apell_coinv<c:out value="${i.count}" />'></td>
                   <td> <select name="tipoInv" style="width: 100%" id='txt_tipo_coinv<c:out value="${i.count}" />'>
          			    <option value="1">Coinvestigador</option>
          		        <option value="2">Auxiliar</option>
	          	        </select>
                   </td>
               </tr>
              </c:forEach>
              </table>
     </td> </tr>
     <tr> <td> <table width="100%">
     <tr> <td class="renglones" style="width: 75px"><b>Duración:</b></td>
          <td> <input type="text" maxlength="3" name="duracion" style="text-align:right; width:80%" value="0" onkeypress="return numeros(event)" size="2"></td>
          <td class="renglones" width="80px"><b>Horas: </b></td>
          <td><input type="text" maxlength="2" name="horas" style="text-align:right; width: 80%" value="0" onkeypress="return numeros(event)" size="2"></td>
          <td class="renglones"><b>Presupuesto:</b></td>
          <td><input type="text" maxlength="9" name="presupuesto" style="text-align:right; width: 90%" value="0" onkeypress="return numeros(event)"></td>
     </tr>
     </table> </td> </tr>
     <tr> <td class="renglones" style="width: 99%"><b>Palabras Claves:</b></td></tr>
     <tr> <td><textarea class="texto" name="palabras" style="width: 99%"></textarea></td></tr>
     <tr> <td class="renglones"><b>Objetivo General:</b></td></tr>
     <tr> <td><textarea class="texto" name="objetivo" style="width: 99%"></textarea></td></tr>
     <tr> <td class="renglones"><b>Abstract/Resumen:</b></td></tr>
     <tr> <td><textarea class="texto" name="resumen" style="width: 99%"></textarea></td></tr>
     <tr id="tr_compromisos" style="display: none">
          <td> <table width="100%">
                 <tr> <td class="renglones" colspan="2"><b>Compromisos:</b></td></tr>
                 <tr> <td><input type="checkbox" value="1" name="compromisos" id="comp1" checked="checked">Informe Académico</td>
                      <td><input type="checkbox" value="2" name="compromisos" id="comp2" checked="checked">Informe Técnico</td>
                 </tr>
                 <tr> <td><input type="checkbox" value="3" name="compromisos" id="comp3" checked="checked">Informe Financiero</td>
                      <td><input type="checkbox" value="4" name="compromisos" id="comp4" checked="checked">Artículo Revista Científica</td>
                 </tr>
               </table>
     </td> </tr>
               <tr> <td class="renglones"><b>Observaciones:</b></td></tr>
               <tr> <td><textarea class="texto" name="observacion" style="width: 99%"></textarea></td></tr>
               <tr> <td class="renglones" align="center"><b>Estado Proyecto: </b>
                          <select name="estado">
                          <option value="0" selected="selected">------------------</option>
                          <option value="1">Inscrito</option>
                          <option value="2">Institucionalizado</option>
                          <option value="3">Finalizado</option>
                          <option value="4">Cancelado</option>
                          <option value="5">En Prueba</option>
                         </select>
                    </td>
               </tr>
               <tr></tr> <tr></tr>
               <tr> <td align="center"><img src='<c:url value="/comp/img/Guardar.gif"/>' onclick="guardar()"></td></tr>
</table>
		<input type="hidden" name="validar">
</form>

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
<% session.removeAttribute("proyectos"); %>
</html>