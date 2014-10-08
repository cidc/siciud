package org.apache.jsp.proyectosAntiguos;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class NuevoProyectoAntiguo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems_005fbegin;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fend_005fbegin;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fend_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems_005fbegin.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fend_005fbegin.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("    \r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<script>\r\n");
      out.write("        var nav4=window.Event ? true : false;\r\n");
      out.write("\t    function numeros(eve){\r\n");
      out.write("\t\tvar key=nav4?eve.which :eve.keyCode;\r\n");
      out.write("\t\treturn(key<=13 || (key>=48 && key<=57));\r\n");
      out.write("\t    }\r\n");
      out.write("\r\n");
      out.write("\t    function radios(valor){\r\n");
      out.write("\t\t    if (valor==1)\r\n");
      out.write("\t\t    {\r\n");
      out.write("             document.getElementById(\"select1\").style.display='';\r\n");
      out.write("             document.getElementById(\"txtConvo\").style.display='none';\r\n");
      out.write("             document.getElementById(\"txt_convenio\").innerHTML=\"Convenio\";\r\n");
      out.write("             document.getElementById(\"tr_compromisos\").style.display='none';\r\n");
      out.write("             document.form1.convocatoria.disabled=true;\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t    else\r\n");
      out.write("\t\t    {\r\n");
      out.write("             document.getElementById(\"select1\").style.display='none';\r\n");
      out.write("             document.getElementById(\"txtConvo\").style.display='';\r\n");
      out.write("             document.getElementById(\"txt_convenio\").innerHTML=\"Convocatoria\";\r\n");
      out.write("             document.getElementById(\"tr_compromisos\").style.display='';\r\n");
      out.write("             document.form1.convocatoria.disabled=false;\r\n");
      out.write("             document.form1.convenio.selectedIndex=0;\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\t }\r\n");
      out.write("\r\n");
      out.write("\t\tfunction mostrar(combo)\r\n");
      out.write("\t\t{for(var i=1; i<=15; i++)\r\n");
      out.write("\t\t {document.getElementById(\"tr_a\"+i).style.display='none';\r\n");
      out.write("          document.getElementById(\"tr_a\"+i).disabled=true;\r\n");
      out.write("          document.getElementById(\"tr_b\"+i).style.display='none';\r\n");
      out.write("          document.getElementById(\"tr_b\"+i).disabled=true;\r\n");
      out.write("          document.getElementById(\"txt_nom_coinv\"+i).disabled=true;\r\n");
      out.write("          document.getElementById(\"txt_apell_coinv\"+i).disabled=true;\r\n");
      out.write("          document.getElementById(\"txt_tipo_coinv\"+i).disabled=true;\r\n");
      out.write("         }\r\n");
      out.write("         if(combo.selectedIndex!=0)\r\n");
      out.write("         {for(var i=1; i<=combo.value; i++)\r\n");
      out.write("          {document.getElementById(\"tr_a\"+i).style.display='';\r\n");
      out.write("           document.getElementById(\"tr_a\"+i).disabled=false;\r\n");
      out.write("           document.getElementById(\"tr_b\"+i).style.display='';\r\n");
      out.write("           document.getElementById(\"tr_b\"+i).disabled=false;\r\n");
      out.write("           document.getElementById(\"txt_nom_coinv\"+i).disabled=false;\r\n");
      out.write("           document.getElementById(\"txt_apell_coinv\"+i).disabled=false;\r\n");
      out.write("           document.getElementById(\"txt_tipo_coinv\"+i).disabled=false;\r\n");
      out.write("          }\r\n");
      out.write("         }\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction ajaxFacultad(select)\r\n");
      out.write("\t\t{\r\n");
      out.write("         document.frmAjax.dato.value = select.value;\r\n");
      out.write("         document.frmAjax.para.value = 1;\r\n");
      out.write("         document.frmAjax.target=\"frameOculto\";\r\n");
      out.write("         document.frmAjax.submit();\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction ajaxGrupo(select)\r\n");
      out.write("\t\t{\r\n");
      out.write("         document.frmAjax.dato.value = select.value;\r\n");
      out.write("         document.frmAjax.para.value = 2;\r\n");
      out.write("         document.frmAjax.target=\"frameOculto\";\r\n");
      out.write("         document.frmAjax.submit();\r\n");
      out.write("\t\t}\r\n");
      out.write("\r\n");
      out.write("\t\tfunction guardar()\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t if (validarCombos()== true )\r\n");
      out.write("\t\t {\r\n");
      out.write("\t\t   if(ValidarFormulario(document.form1))\r\n");
      out.write("\t\t     {\r\n");
      out.write("\t\t      document.form1.validar.value = 1;\r\n");
      out.write("\t\t      document.form1.action='");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("';\r\n");
      out.write("\t\t\t  document.form1.submit();\r\n");
      out.write("\t\t     }\r\n");
      out.write("\t\t  }\r\n");
      out.write("\t\t }\r\n");
      out.write("\r\n");
      out.write("\t\tfunction ValidarFormulario(formulario)\r\n");
      out.write("\t\t{\r\n");
      out.write("\t\t if(formulario.nombre.value==\"\")\r\n");
      out.write("\t\t   {alert(\"El nombre del Proyecto de Investigación no puede estar vacio\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t if(formulario.presupuesto.value==\"\")\r\n");
      out.write("\t\t   {alert(\"El valor del presupuesto no puede estar vacio\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t if(formulario.duracion.value<=5)\r\n");
      out.write("\t\t   {\r\n");
      out.write("\t\t    alert(\"La duración del proyecto no puede ser menor a 6 meses\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t   }\r\n");
      out.write("\t\t if(formulario.codigo.value==\"\")\r\n");
      out.write("\t\t {\r\n");
      out.write("\t\t  alert(\"El código del proyecto no puede estar vacio\");\r\n");
      out.write("\t\t  return false;\r\n");
      out.write("\t\t }\r\n");
      out.write("\t\t if(document.form1.cant_coinv.selectedIndex!=0)\r\n");
      out.write("\t\t {\r\n");
      out.write("\t\t  for(var i=1; i<=document.form1.cant_coinv.value; i++)\r\n");
      out.write("          {\r\n");
      out.write("           if(document.getElementById(\"txt_nom_coinv\"+i).value==\"\" || document.getElementById(\"txt_apell_coinv\"+i).value==\"\")\r\n");
      out.write("           {\r\n");
      out.write("            alert(\"Debe digitar los nombres y apellidos del Coinvestigador o Auxiliar\");\r\n");
      out.write("            i=document.form1.cant_coinv.value;\r\n");
      out.write("\t\t    return false;\r\n");
      out.write("           }\r\n");
      out.write("          }\r\n");
      out.write("\t\t }\r\n");
      out.write("\t\t return true;\r\n");
      out.write("\t   }\r\n");
      out.write("\r\n");
      out.write("\t   function validarCombos()\r\n");
      out.write("\t   {\r\n");
      out.write("        if(document.form1.ano.selectedIndex==0)\r\n");
      out.write("        {alert(\"Debe seleccionar el año del proyecto\"); return false;}\r\n");
      out.write("        else {if(document.form1.facultad.selectedIndex==0)\r\n");
      out.write("              {alert(\"Debe seleccionar la facultad del proyecto\"); return false;}\r\n");
      out.write("              else {if(document.form1.proycurri.selectedIndex==0)\r\n");
      out.write("                    {return true;}\r\n");
      out.write("                     else {if(document.form1.grupo.selectedIndex==0)\r\n");
      out.write("                            {alert(\"Debe seleccionar el Semillero o Grupo de Investigación del proyecto\"); return false;}\r\n");
      out.write("                           else{if(document.form1.investigador.selectedIndex==0)\r\n");
      out.write("                                {alert(\"Debe seleccionar el Investigador Principal del proyecto\"); return false;}\r\n");
      out.write("                                else{\r\n");
      out.write("                                     if(document.form1.convocatoria.selectedIndex==0)\r\n");
      out.write("                                      {alert(\"Debe seleccionar la convocatoria del proyecto\"); return false;}\r\n");
      out.write("                                      if(document.form1.estado.selectedIndex==0)\r\n");
      out.write("                                      {alert(\"Debe seleccionar el estado del Proyecto\"); return false;}\r\n");
      out.write("                                      else{return true;}\r\n");
      out.write("                                     \r\n");
      out.write("                                }\r\n");
      out.write("                               }\r\n");
      out.write("                          }\r\n");
      out.write("                   }\r\n");
      out.write("             }\r\n");
      out.write("\t   }\r\n");
      out.write("\r\n");
      out.write("</script>\r\n");
      if (_jspx_meth_c_005fimport_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("<title>Datos Generales</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("<br>\r\n");
      out.write("<form name=\"form1\">\r\n");
      out.write("<table class=\"tablas\" align = \"center\" width=\"80%\">\r\n");
      out.write("     <CAPTION>Registro Proyecto de Investigación Antiguo</CAPTION>\r\n");
      out.write("     <tr> <td> <table width=\"100%\">\r\n");
      out.write("     <tr> <td class=\"renglones\" style=\"width: 50px\"><b>Año: </b> </td>\r\n");
      out.write("          <td> <select name=\"ano\">\r\n");
      out.write("               <option value=\"0\">----</option>\r\n");
      out.write("     \t       ");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("               </select>\r\n");
      out.write("          </td>\r\n");
      out.write("          <td colspan=\"2\" class=\"renglones\"><b>Codigo:</b></td>\r\n");
      out.write("          <td colspan=\"2\"><input type=\"text\" maxlength=\"11\" name=\"codigo\" style=\"width: 85%\" onkeypress=\"return numeros(event)\"></td>\r\n");
      out.write("     </tr>\r\n");
      out.write("     </table> </td></tr>\r\n");
      out.write("     <tr> <td class=\"renglones\"><b><span id=\"txt_convocatoria\">Convocatorias:</span></b></td></tr>\r\n");
      out.write("     <tr id=\"select1\">\r\n");
      out.write("          <td> <select name=\"convocatoria\" style=\"width: 100%\">\r\n");
      out.write("               <option value=\"0\">---------------------------</option>\r\n");
      out.write("               <option value=\"1\">Proyecto Institucionalizado</option>\r\n");
      out.write("               ");
      if (_jspx_meth_c_005fforEach_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("               </select>\r\n");
      out.write("          </td>\r\n");
      out.write("     </tr>\r\n");
      out.write("     <tr> <td class=\"renglones\"><b>Nombre Proyecto:</b></td></tr>\r\n");
      out.write("     <tr> <td><textarea class=\"texto\" name=\"nombre\" style=\"width: 99%\"></textarea></td></tr>\r\n");
      out.write("     <tr> <td class=\"renglones\"><b>Facultad:</b></td></tr>\r\n");
      out.write("     <tr> <td> <select name=\"facultad\" style=\"width: 100%\" onchange=\"ajaxFacultad(this)\">\r\n");
      out.write("               <option value=\"0\">-----------------------------------------------</option>\r\n");
      out.write("               <option value=\"1\">Facultad Tecnológica</option>\r\n");
      out.write("               <option value=\"2\">Facultad de Ingenieria</option>\r\n");
      out.write("               <option value=\"3\">Facultad de Medio Ambiente y recursos Naturales</option>\r\n");
      out.write("               <option value=\"4\">Facultad de Ciencias y Educación</option>\r\n");
      out.write("               <option value=\"5\">ASAB</option>\r\n");
      out.write("               </select>\r\n");
      out.write("          </td>\r\n");
      out.write("     </tr>\r\n");
      out.write("     <tr> <td class=\"renglones\"><b>Proyecto Curricular:</b></td></rt>\r\n");
      out.write("     <tr> <td> <select name=\"proycurri\" style=\"width: 100%\">\r\n");
      out.write("                           <option value=\"0\">---------------------</option>\r\n");
      out.write("                           </select>\r\n");
      out.write("          </td>\r\n");
      out.write("     </tr>\r\n");
      out.write("     <tr> <td class=\"renglones\"><b>Grupo/Semillero:</b></td></tr>\r\n");
      out.write("     <tr> <td> <select name=\"grupo\" style=\"width: 100%\" onchange=\"ajaxGrupo(this)\">\r\n");
      out.write("               <option value=\"0\">-----------</option>\r\n");
      out.write("               </select>\r\n");
      out.write("          </td>\r\n");
      out.write("     </tr>\r\n");
      out.write("     <tr> <td class=\"renglones\"><b>Investigador Principal:</b></td></tr>\r\n");
      out.write("     <tr> <td> <select name=\"investigador\" style=\"width: 100%\">\r\n");
      out.write("               <option value=\"0\">------------------------</option>\r\n");
      out.write("               </select>\r\n");
      out.write("          </td>\r\n");
      out.write("     </tr>\r\n");
      out.write("     <tr> <td> <table width=\"100%\">\r\n");
      out.write("               <tr> <td class=\"renglones\"><b>Coinvestigadores/Auxiliares:</b></td>\r\n");
      out.write("                    <td class=\"renglones\"><b>Seleccione Cantidad:</b> <select name=\"cant_coinv\" style=\"width: 30%\" onchange=\"mostrar(this)\">\r\n");
      out.write("                                                                      <option value=\"0\">--</option>\r\n");
      out.write("                                                                      <option value=\"1\">1</option>\r\n");
      out.write("                                                                      <option value=\"2\">2</option>\r\n");
      out.write("                                                                      <option value=\"3\">3</option>\r\n");
      out.write("                                                                      <option value=\"4\">4</option>\r\n");
      out.write("                                                                      <option value=\"5\">5</option>\r\n");
      out.write("                                                                      <option value=\"6\">6</option>\r\n");
      out.write("                                                                      <option value=\"7\">7</option>\r\n");
      out.write("                                                                      <option value=\"8\">8</option>\r\n");
      out.write("                                                                      <option value=\"9\">9</option>\r\n");
      out.write("                                                                      <option value=\"10\">10</option>\r\n");
      out.write("                                                                      <option value=\"11\">11</option>\r\n");
      out.write("                                                                      <option value=\"12\">12</option>\r\n");
      out.write("                                                                      <option value=\"13\">13</option>\r\n");
      out.write("                                                                      <option value=\"14\">14</option>\r\n");
      out.write("                                                                      <option value=\"15\">15</option>\r\n");
      out.write("                                                                      </select>\r\n");
      out.write("                    </td>\r\n");
      out.write("               </tr>\r\n");
      out.write("     </table> </td> </tr>\r\n");
      out.write("     <tr> <td>\r\n");
      out.write("              <table width=\"100%\">\r\n");
      out.write("              ");
      if (_jspx_meth_c_005fforEach_005f2(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("              </table>\r\n");
      out.write("     </td> </tr>\r\n");
      out.write("     <tr> <td> <table width=\"100%\">\r\n");
      out.write("     <tr> <td class=\"renglones\" style=\"width: 75px\"><b>Duración:</b></td>\r\n");
      out.write("          <td> <input type=\"text\" maxlength=\"3\" name=\"duracion\" style=\"text-align:right; width:80%\" value=\"0\" onkeypress=\"return numeros(event)\" size=\"2\"></td>\r\n");
      out.write("          <td class=\"renglones\" width=\"80px\"><b>Horas: </b></td>\r\n");
      out.write("          <td><input type=\"text\" maxlength=\"2\" name=\"horas\" style=\"text-align:right; width: 80%\" value=\"0\" onkeypress=\"return numeros(event)\" size=\"2\"></td>\r\n");
      out.write("          <td class=\"renglones\"><b>Presupuesto:</b></td>\r\n");
      out.write("          <td><input type=\"text\" maxlength=\"9\" name=\"presupuesto\" style=\"text-align:right; width: 90%\" value=\"0\" onkeypress=\"return numeros(event)\"></td>\r\n");
      out.write("     </tr>\r\n");
      out.write("     </table> </td> </tr>\r\n");
      out.write("     <tr> <td class=\"renglones\" style=\"width: 99%\"><b>Palabras Claves:</b></td></tr>\r\n");
      out.write("     <tr> <td><textarea class=\"texto\" name=\"palabras\" style=\"width: 99%\"></textarea></td></tr>\r\n");
      out.write("     <tr> <td class=\"renglones\"><b>Objetivo General:</b></td></tr>\r\n");
      out.write("     <tr> <td><textarea class=\"texto\" name=\"objetivo\" style=\"width: 99%\"></textarea></td></tr>\r\n");
      out.write("     <tr> <td class=\"renglones\"><b>Abstract/Resumen:</b></td></tr>\r\n");
      out.write("     <tr> <td><textarea class=\"texto\" name=\"resumen\" style=\"width: 99%\"></textarea></td></tr>\r\n");
      out.write("               <tr> <td class=\"renglones\"><b>Observaciones:</b></td></tr>\r\n");
      out.write("               <tr> <td><textarea class=\"texto\" name=\"observacion\" style=\"width: 99%\"></textarea></td></tr>\r\n");
      out.write("               <tr> <td class=\"renglones\" align=\"center\"><b>Estado Proyecto: </b>\r\n");
      out.write("                          <select name=\"estado\">\r\n");
      out.write("                          <option value=\"0\" selected=\"selected\">------------------</option>\r\n");
      out.write("                          <option value=\"1\">Inscrito</option>\r\n");
      out.write("                          <option value=\"2\">Institucionalizado</option>\r\n");
      out.write("                          <option value=\"3\">Finalizado</option>\r\n");
      out.write("                          <option value=\"4\">Cancelado</option>\r\n");
      out.write("                          <option value=\"5\">En Prueba</option>\r\n");
      out.write("                         </select>\r\n");
      out.write("                    </td>\r\n");
      out.write("               </tr>\r\n");
      out.write("               <tr></tr> <tr></tr>\r\n");
      out.write("               <tr> <td align=\"center\"><img src='");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("' onclick=\"guardar()\"></td></tr>\r\n");
      out.write("</table>\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"validar\">\r\n");
      out.write("</form>\r\n");
      out.write("\r\n");
      out.write("\t<table>\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td style=\"display:none\"><iframe name=\"frameOculto\" id=\"frameOculto\"></iframe></td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<form method=\"post\" name=\"frmAjax\" action=\"");
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write("\">\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"dato\" value=''>\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"para\" value=''>\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("<script>\r\n");
      out.write("mostrar(document.form1.cant_coinv);\r\n");
      out.write("ajaxFacultad(document.form1.facultad);\r\n");
      out.write("</script>\r\n");
 session.removeAttribute("proyectos"); 
      out.write("\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f0.setParent(null);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(82,31) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/proyectosAntiguos/Llenar.jsp");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fimport_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:import
    org.apache.taglibs.standard.tag.rt.core.ImportTag _jspx_th_c_005fimport_005f0 = (org.apache.taglibs.standard.tag.rt.core.ImportTag) _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.ImportTag.class);
    _jspx_th_c_005fimport_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fimport_005f0.setParent(null);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(150,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fimport_005f0.setUrl("/general.jsp");
    int[] _jspx_push_body_count_c_005fimport_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fimport_005f0 = _jspx_th_c_005fimport_005f0.doStartTag();
      if (_jspx_th_c_005fimport_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fimport_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fimport_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fimport_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.reuse(_jspx_th_c_005fimport_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(null);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(163,13) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setBegin(1985);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(163,13) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setEnd(((java.lang.Integer) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.ano}", java.lang.Integer.class, (PageContext)_jspx_page_context, null, false)).intValue());
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(163,13) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("an");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t     \t       <option value='");
          if (_jspx_meth_c_005fout_005f0(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write('\'');
          out.write('>');
          if (_jspx_meth_c_005fout_005f1(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</option>\r\n");
          out.write("     \t       ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(164,29) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${an}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(164,53) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${an}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent(null);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(177,15) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setBegin(0);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(177,15) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.convocatoria}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(177,15) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("conv");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("                          <option value='");
          if (_jspx_meth_c_005fout_005f2(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write('\'');
          out.write('>');
          out.write(' ');
          if (_jspx_meth_c_005fout_005f3(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write(" </option>\r\n");
          out.write("               ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f1.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f1[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f1.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f1.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvar_005fitems_005fbegin.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f2(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f2 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f2.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(178,41) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${conv.convNombre}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f2 = _jspx_th_c_005fout_005f2.doStartTag();
    if (_jspx_th_c_005fout_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f3 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(178,79) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${conv.convNombre}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f3 = _jspx_th_c_005fout_005f3.doStartTag();
    if (_jspx_th_c_005fout_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f2 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fend_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f2.setParent(null);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(239,14) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setBegin(1);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(239,14) name = end type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setEnd(15);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(239,14) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f2.setVarStatus("i");
    int[] _jspx_push_body_count_c_005fforEach_005f2 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f2 = _jspx_th_c_005fforEach_005f2.doStartTag();
      if (_jspx_eval_c_005fforEach_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("               <tr id='tr_a");
          if (_jspx_meth_c_005fout_005f4(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
            return true;
          out.write("'> <td class=\"renglones\"><b>Nombres:</b></td>\r\n");
          out.write("                   <td class=\"renglones\"><b>Apellidos:</b></td>\r\n");
          out.write("                   <td class=\"renglones\"><b>Tipo</b></td>\r\n");
          out.write("               </tr>\r\n");
          out.write("               <tr id='tr_b");
          if (_jspx_meth_c_005fout_005f5(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
            return true;
          out.write("'> <td><input type=\"text\"name=\"nomCoinvest\" style=\"width: 90%\" id='txt_nom_coinv");
          if (_jspx_meth_c_005fout_005f6(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
            return true;
          out.write("'></td>\r\n");
          out.write("                   <td> <input type=\"text\" name=\"apellCoinvest\" style=\"width: 90%\" id='txt_apell_coinv");
          if (_jspx_meth_c_005fout_005f7(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
            return true;
          out.write("'></td>\r\n");
          out.write("                   <td> <select name=\"tipoInv\" style=\"width: 100%\" id='txt_tipo_coinv");
          if (_jspx_meth_c_005fout_005f8(_jspx_th_c_005fforEach_005f2, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f2))
            return true;
          out.write("'>\r\n");
          out.write("          \t\t\t    <option value=\"1\">Coinvestigador</option>\r\n");
          out.write("          \t\t        <option value=\"2\">Auxiliar</option>\r\n");
          out.write("\t          \t        </select>\r\n");
          out.write("                   </td>\r\n");
          out.write("               </tr>\r\n");
          out.write("              ");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f2.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f2[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f2.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f2.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fend_005fbegin.reuse(_jspx_th_c_005fforEach_005f2);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f4 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(240,27) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f4.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f4 = _jspx_th_c_005fout_005f4.doStartTag();
    if (_jspx_th_c_005fout_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f5 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(244,27) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f5.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f5 = _jspx_th_c_005fout_005f5.doStartTag();
    if (_jspx_th_c_005fout_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f6 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(244,135) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f6.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f6 = _jspx_th_c_005fout_005f6.doStartTag();
    if (_jspx_th_c_005fout_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f7 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(245,102) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f7.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f7 = _jspx_th_c_005fout_005f7.doStartTag();
    if (_jspx_th_c_005fout_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f2, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f2)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f8 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f2);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(246,85) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f8.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${i.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f8 = _jspx_th_c_005fout_005f8.doStartTag();
    if (_jspx_th_c_005fout_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f1 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f1.setParent(null);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(284,49) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/comp/img/Guardar.gif");
    int _jspx_eval_c_005furl_005f1 = _jspx_th_c_005furl_005f1.doStartTag();
    if (_jspx_th_c_005furl_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f2 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f2.setParent(null);
    // /proyectosAntiguos/NuevoProyectoAntiguo.jsp(294,44) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("/proyectosAntiguos/Ajax.x");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
    return false;
  }
}
