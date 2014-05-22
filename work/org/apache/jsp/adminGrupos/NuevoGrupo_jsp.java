package org.apache.jsp.adminGrupos;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class NuevoGrupo_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
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
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link type='text/css' rel='stylesheet' media='all' href='");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("' title='win2k-cold-1' />\r\n");
      out.write("<script type='text/javascript' src='");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("'></script>\r\n");
      out.write("<script type='text/javascript' src='");
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write("'></script>\r\n");
      out.write("<script type='text/javascript' src='");
      if (_jspx_meth_c_005furl_005f3(_jspx_page_context))
        return;
      out.write("'></script>\r\n");
      out.write("<script type=\"text/javascript\" language=\"javascript\" src='");
      if (_jspx_meth_c_005furl_005f4(_jspx_page_context))
        return;
      out.write("'></script>\r\n");
      out.write("<link rel=\"stylesheet\" href='");
      if (_jspx_meth_c_005furl_005f5(_jspx_page_context))
        return;
      out.write("' type=\"text/css\" media=\"screen\" />\r\n");
      if (_jspx_meth_c_005fimport_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tfunction cambioTipo(boton){\r\n");
      out.write("\t\tif(boton.value==2){\r\n");
      out.write("\t\t\tdocument.nuevo.tipo.value=2;\r\n");
      out.write("\t\t\tdocument.nuevo.categoria.disabled=true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse{\r\n");
      out.write("\t\t\tdocument.nuevo.tipo.value=1;\r\n");
      out.write("\t\t\tdocument.nuevo.categoria.disabled=false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction guardar(){\r\n");
      out.write("\t\tif(validar()){\r\n");
      out.write("\t\t\tdocument.nuevo.action='");
      if (_jspx_meth_c_005furl_005f6(_jspx_page_context))
        return;
      out.write("';\r\n");
      out.write("\t\t\tdocument.nuevo.submit();\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction validar(){\r\n");
      out.write("\t\tmensaje=\"\";\r\n");
      out.write("\t\tif(document.nuevo.nombre.value==\"\"){\r\n");
      out.write("\t\t\tmensaje=mensaje+\"\\n-) Nombre del Grupo o Semillero\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.nuevo.tipo.value==\"0\"){\r\n");
      out.write("\t\t\tmensaje=mensaje+\"\\n-) Tipo\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.nuevo.fechaCreacion.value==\"\"){\r\n");
      out.write("\t\t\tmensaje=mensaje+\"\\n-) Fecha de Creación\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.nuevo.nombDirector.value==\"\"){\r\n");
      out.write("\t\t\tmensaje=mensaje+\"\\n-) Nombre del Director\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.nuevo.apellDirector.value==\"\"){\r\n");
      out.write("\t\t\tmensaje=mensaje+\"\\n-) Apellido del Director\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.nuevo.tipo.value==\"1\" && document.nuevo.categoria.selectedIndex==0){\r\n");
      out.write("\t\t\tmensaje=mensaje+\"\\n-) Categoría Colciencias\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.nuevo.numeroCIDC.value==\"\"){\r\n");
      out.write("\t\t\tmensaje=mensaje+\"\\n-) Número de acta del CIDC\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(mensaje!=\"\"){\r\n");
      out.write("\t\t\tmensaje=\"Los siguientes campos son obligatorios: \"+mensaje;\r\n");
      out.write("\t\t\talert (mensaje);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction validarNro(e) {\r\n");
      out.write("\t\tvar key;\r\n");
      out.write("\t\tif(window.event){ // IE\r\n");
      out.write("\t\t\tkey = e.keyCode;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\telse if(e.which){ // Netscape/Firefox/Opera\r\n");
      out.write("\t\t\t\tkey = e.which;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\tif (key < 48 || key > 57)\r\n");
      out.write("\t\t    {\r\n");
      out.write("\t\t    if(key == 8) // Detectar  backspace (retroceso)\r\n");
      out.write("\t\t        { return true; }\r\n");
      out.write("\t\t    else \r\n");
      out.write("\t\t        { return false; }\r\n");
      out.write("\t\t    }\r\n");
      out.write("\t\treturn true;\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body onLoad=\"mensajeAlert(document.getElementById('msg'));\">\r\n");
      out.write("<br>\r\n");
      out.write("\t<table width=\"100%\">\r\n");
      out.write("\t\t<tr>\r\n");
      out.write("\t\t\t<td align=\"center\">\r\n");
      out.write("\t\t\t<a href='");
      if (_jspx_meth_c_005furl_005f7(_jspx_page_context))
        return;
      out.write("'><img border=\"0\" src=\"");
      if (_jspx_meth_c_005furl_005f8(_jspx_page_context))
        return;
      out.write("\"></a>\r\n");
      out.write("\t\t\t</td>\r\n");
      out.write("\t\t</tr>\r\n");
      out.write("\t</table>\r\n");
      out.write("\t<form name=\"nuevo\" method=\"post\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"accion\" value=\"1\">\r\n");
      out.write("\t\t\t<table class=\"tablas\" align=\"center\" width=\"65%\" >\r\n");
      out.write("\t\t\t<caption>Creación de Grupo/Semillero de Investigación</caption>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<table width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"5\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<table width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Facultad:</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td colspan=\"2\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<select name=\"facultad\" onchange=\"ajaxGrupos(this)\" >\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"1\">Tecnológica</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2\">Ingeniería</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"3\">Medio Ambiente</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"4\">Ciencias y Educación</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"5\">Asab</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Siglas</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td><input type=\"text\" name=\"siglas\" size=\"20\" value=\"\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"5\" class=\"renglones\"><b><span id=\"para\">Nombre Grupo/Semillero Investigación</span></b></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"5\"><input type=\"text\" name=\"nombre\" size=\"75\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"5\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<table width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Tipo</b><input type=\"hidden\" name=\"tipo\" value=\"0\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"texto\"><b>Grupo</b><input type=\"radio\" name=\"boton\" checked=\"checked\" value=\"1\" onClick=\"cambioTipo(this)\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"texto\"><b>Semillero</b><input type=\"radio\" name=\"boton\" value=\"2\" onClick=\"cambioTipo(this)\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Categoría Colciencias</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<select name=\"categoria\" style=\"width: 100%\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"1\">Sin Clasificación</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2\">Institucionalizado en UD</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"6\">Reconocido - Colciencias</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"7\">D - Colciencias</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"3\">C - Colciencias</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"4\">B - Colciencias</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"5\">A - Colciencias</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"8\">A1 - Colciencias</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Estado</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<select name=\"estado\" style=\"width: 100%\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"1\">Activo</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t<option value=\"2\">Inactivo</option>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"5\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<table width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Fecha de Creación CIDC</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<input type='text' name='fechaCreacion' class='caj' readonly='true' id='f_date_b' size='13'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<button type='button' id='f_trigger_b'>...</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<script type='text/javascript'>\r\n");
      out.write("\t\t\t\t\t\t\t\t    \t\t\tCalendar.setup({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tinputField     :    'f_date_b',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tifFormat       :    '%d/%m/%Y',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tshowsTime      :    true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tbutton         :    'f_trigger_b',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tsingleClick    :    false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tstep           :    1\r\n");
      out.write("\t\t\t\t\t\t\t\t    \t\t\t})\r\n");
      out.write("\t\t\t\t\t\t\t    \t\t\t</script>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Número de Acta CIDC</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"numeroCIDC\" onkeypress=\"javascript:return validarNro(event)\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Fecha de Creación Facultad</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td >\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<input type='text' name='fechaCreacionFacultad' class='caj' readonly='true' id='f_date_b_fac' size='13'>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<button type='button' id='f_trigger_b_fac'>...</button>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<script type='text/javascript'>\r\n");
      out.write("\t\t\t\t\t\t\t\t    \t\t\tCalendar.setup({\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tinputField     :    'f_date_b_fac',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tifFormat       :    '%d/%m/%Y',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tshowsTime      :    true,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tbutton         :    'f_trigger_b_fac',\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tsingleClick    :    false,\r\n");
      out.write("\t\t\t\t\t\t\t\t\t    \t\t\tstep           :    1\r\n");
      out.write("\t\t\t\t\t\t\t\t    \t\t\t})\r\n");
      out.write("\t\t\t\t\t\t\t    \t\t\t</script>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Número de acta</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"text\" name=\"numeroFac\" onkeypress=\"javascript:return validarNro(event)\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<table class=\"tablas\" width=\"100%\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t<CAPTION>director</CAPTION>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Nombres</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td><input readonly=\"readonly\" type=\"text\" name=\"nombDirector\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td class=\"renglones\"><b>Apellidos</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td><input readonly=\"readonly\" type=\"text\" name=\"apellDirector\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t<td><input type=\"hidden\" name=\"cedDirector\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<input type=\"hidden\" name=\"codUdDirector\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<a href='");
      if (_jspx_meth_c_005furl_005f9(_jspx_page_context))
        return;
      out.write("'\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t\t\ttarget=\"_parent\" rel=\"lyteframe\" title=\"Buscar Director Grupo/Semillero\" rev=\"width: 550px; height: 370px; scrolling: auto;\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t\t<img src='");
      if (_jspx_meth_c_005furl_005f10(_jspx_page_context))
        return;
      out.write("' border=\"0\">\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</a>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"5\" class=\"renglones\"><b>Correo Electrónico</b></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"5\"><input type=\"text\" name=\"mail\" size=\"75\"></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"5\" class=\"renglones\"><b>GrupLac</b></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"5\"><input type=\"text\" name=\"grupLac\" size=\"75\" value='");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("' ></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"5\" class=\"renglones\"><b>Página Web</b></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"5\"><input type=\"text\" name=\"web\" size=\"75\" value='");
      if (_jspx_meth_c_005fout_005f1(_jspx_page_context))
        return;
      out.write("'></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"5\" class=\"renglones\"><b>Descripción del Grupo</b></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"5\"><textarea name=\"descripcion\" style=\"width:100%\">");
      if (_jspx_meth_c_005fout_005f2(_jspx_page_context))
        return;
      out.write("</textarea></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"5\" class=\"renglones\"><b>Observaciones</b></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t<td colspan=\"5\"><textarea name=\"observaciones\" style=\"width:100%\">");
      if (_jspx_meth_c_005fout_005f3(_jspx_page_context))
        return;
      out.write("</textarea></td>\r\n");
      out.write("\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t</table>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td align=\"center\"><img src=\"");
      if (_jspx_meth_c_005furl_005f11(_jspx_page_context))
        return;
      out.write("\" onclick=\"guardar()\"></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
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
    // /adminGrupos/NuevoGrupo.jsp(7,57) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/comp/js/Calendario/calendar-blue2.css");
    int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
    if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
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
    // /adminGrupos/NuevoGrupo.jsp(8,36) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/comp/js/Calendario/calendar.js");
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
    // /adminGrupos/NuevoGrupo.jsp(9,36) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("/comp/js/Calendario/lang/calendar-es.js");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f3 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f3.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(10,36) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setValue("/comp/js/Calendario/calendar-setup.js");
    int _jspx_eval_c_005furl_005f3 = _jspx_th_c_005furl_005f3.doStartTag();
    if (_jspx_th_c_005furl_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f4(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f4 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f4.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(11,58) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f4.setValue("/comp/js/lytebox.js");
    int _jspx_eval_c_005furl_005f4 = _jspx_th_c_005furl_005f4.doStartTag();
    if (_jspx_th_c_005furl_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f5 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f5.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(12,29) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f5.setValue("/comp/css/lytebox.css");
    int _jspx_eval_c_005furl_005f5 = _jspx_th_c_005furl_005f5.doStartTag();
    if (_jspx_th_c_005furl_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f5);
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
    // /adminGrupos/NuevoGrupo.jsp(13,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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

  private boolean _jspx_meth_c_005furl_005f6(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f6 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f6.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(28,26) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f6.setValue("/adminGrupos/llenar.jsp");
    int _jspx_eval_c_005furl_005f6 = _jspx_th_c_005furl_005f6.doStartTag();
    if (_jspx_th_c_005furl_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f7(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f7 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f7.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(90,12) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f7.setValue("/adminGrupos/AdminGrupos.x?accion=0");
    int _jspx_eval_c_005furl_005f7 = _jspx_th_c_005furl_005f7.doStartTag();
    if (_jspx_th_c_005furl_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f8(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f8 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f8.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(90,88) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f8.setValue("/comp/img/Home.png");
    int _jspx_eval_c_005furl_005f8 = _jspx_th_c_005furl_005f8.doStartTag();
    if (_jspx_th_c_005furl_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f9(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f9 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f9.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(217,21) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f9.setValue("/adminGrupos/AdminGrupos.x?accion=20&director=si");
    int _jspx_eval_c_005furl_005f9 = _jspx_th_c_005furl_005f9.doStartTag();
    if (_jspx_th_c_005furl_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f9);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f10(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f10 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f10.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(219,22) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f10.setValue("/comp/img/humanito.gif");
    int _jspx_eval_c_005furl_005f10 = _jspx_th_c_005furl_005f10.doStartTag();
    if (_jspx_th_c_005furl_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f0 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f0.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(236,74) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.grupo.grupLac}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f1 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f1.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(242,70) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.grupo.web}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f2 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f2.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(248,71) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.grupo.descripcion}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f2 = _jspx_th_c_005fout_005f2.doStartTag();
    if (_jspx_th_c_005fout_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f3 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f3.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(254,73) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.grupo.observaciones}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f3 = _jspx_th_c_005fout_005f3.doStartTag();
    if (_jspx_th_c_005fout_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f11(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f11 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f11.setParent(null);
    // /adminGrupos/NuevoGrupo.jsp(260,34) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f11.setValue("/comp/img/Guardar.gif");
    int _jspx_eval_c_005furl_005f11 = _jspx_th_c_005furl_005f11.doStartTag();
    if (_jspx_th_c_005furl_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f11);
    return false;
  }
}
