package org.apache.jsp.Adminreportes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class formulario_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n");
      out.write("   \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>JSP Page</title>\r\n");
      out.write("    <script src=\"jss/SpryValidationTextField.js\" type=\"text/javascript\"></script><script>\r\n");
      out.write("    $(\"#myform\").submit(function(e) {\r\n");
      out.write("    $(\"#first\").hide();\r\n");
      out.write("    $(\"#second\").show();\r\n");
      out.write("\t\t\t\t\t\t\t\t\t\t});\r\n");
      out.write("    </script>\r\n");
      out.write("    <link href=\"css/SpryValidationTextField.css\" rel=\"stylesheet\" type=\"text/css\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("    <h1 align=\"center\" dropzone=\"copy\"><img src=\"../img/CIDCLogo.jpg\" width=\"300\" height=\"78\"> </h1>\r\n");
      out.write("    <h1 align=\"center\" dropzone=\"copy\">Reporte General \r\n");
      out.write("    Proyectos de Investigacion <br> Universidad Distrital Francisco Jose de Caldas</h1>\r\n");
      out.write("    <p align=\"center\" dropzone=\"copy\">En la presente pagina, se pueden generar reportes frente a los proyectos de investigaci&oacuten de la universidad distrital, teniendo en cuenta 3 tipos.</p>\r\n");
      out.write("<p align=\"center\" dropzone=\"copy\"><a name=\"Tutorial\"></a></p>\r\n");
      out.write("<p align=\"center\" dropzone=\"copy\">&nbsp;</p>\r\n");
      out.write("    <p align=\"center\" ><strong>Reporte General</strong></p>\r\n");
      out.write("    <p align=\"center\">\r\n");
      out.write("      <object type=\"application/pdf\" data=\"http://localhost:8084/reportesPDF/REPORTEGENERAL.jsp\" width=\"800\" height=\"650\" align=middle>\r\n");
      out.write("      </object>\r\n");
      out.write("    </p>\r\n");
      out.write("    <p align=\"center\">Descargar  en : </p>\r\n");
      out.write("    <form name=\"form5\" method=\"post\" action=\"REPORTEGENERAL2.jsp\">\r\n");
      out.write("      <table align=\"center\" width=\"200\" border=\"1\" cellspacing=\"1\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td align=\"center\"><select name=\"txtidformato5\" id=\"txtidformato5\">\r\n");
      out.write("            <option value=\"1\" selected=\"selected\">Formato PDF</option>\r\n");
      out.write("            <option value=\"2\">Formato EXCEL</option>\r\n");
      out.write("            <option value=\"3\">Formato HTML</option>\r\n");
      out.write("          </select></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td align=\"center\"><input name=\"button2\" type=\"submit\" id=\"button2\" value=\"Generar Reporte\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("    </form>\r\n");
      out.write("    <p align=\"center\"><span class=\"cualquiera\">Generar Un Reporte General  de Proyectos de Investigaci&oacuten por <em><u>Facultad</u></em> y <u><em>A&ntildeo</em></u>.</span>    </p>\r\n");
      out.write("    <form name=\"form2\" method=\"post\" action=\"REPORTEONE.jsp\">\r\n");
      out.write("    <table  align=\"center\" width=\"487\" border=\"1\">\r\n");
      out.write("      <tr bgcolor=\"#FFCC99\">\r\n");
      out.write("          <th colspan=\"2\" align=\"center\" bgcolor=\"#5dad30\"><strong>Reporte por filtros &quot;Facultad y a&ntildeo&quot;</strong></th>\r\n");
      out.write("      </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th width=\"168\" bgcolor=\"#5dad30\">Facultad</th>\r\n");
      out.write("          <td width=\"415\"><center>\r\n");
      out.write("            <select name=\"txtidfacultad\" id=\"txtidfacultad\">\r\n");
      out.write("              <option value=\"2\" selected=\"selected\">Ingenieria</option>\r\n");
      out.write("              <option value=\"1\">Tecnologica</option>\r\n");
      out.write("              <option value=\"3\">Ambiental</option>\r\n");
      out.write("              <option value=\"4\">Educación</option>\r\n");
      out.write("              <option value=\"5\">Artes</option>\r\n");
      out.write("            </select>\r\n");
      out.write("          </center>\r\n");
      out.write("      </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th width=\"168\" bgcolor=\"#5dad30\">A&ntildeo</th>\r\n");
      out.write("          <td width=\"415\"><label for=\"txtidano\">\"Digite el A&ntildeo\"</label>\r\n");
      out.write("            <input name=\"txtidano\" type=\"text\" id=\"txtidano\" value=\"2014\">\r\n");
      out.write("            <center>\r\n");
      out.write("            </center>\r\n");
      out.write("      <tr bgcolor=\"#FFCC99\">\r\n");
      out.write("            <th align=\"center\" bgcolor=\"#5dad30\">Formato</th>\r\n");
      out.write("            <th align=\"center\" bgcolor=\"#FFFFFF\"><select name=\"txtidformato\" id=\"txtidformato\">\r\n");
      out.write("              <option value=\"1\" selected=\"selected\">PDF</option>\r\n");
      out.write("              <option value=\"2\">EXCEL</option>\r\n");
      out.write("            </select></th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr bgcolor=\"#FFCC99\">\r\n");
      out.write("          <th colspan=\"2\" align=\"center\" bgcolor=\"#FFFFFF\"> <input name=\"button3\" type=\"submit\" id=\"button3\" value=\"Generar Reporte\"></th>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("    </form>\r\n");
      out.write("    \r\n");
      out.write("    <p align=\"center\">Reporte de Proyectos de Investigaci&oacuten por Grupo de Investigaci&oacuten o Semillero.</p>\r\n");
      out.write("    \r\n");
      out.write("    <form name=\"form3\" method=\"post\" action=\"REPORTETHREE.jsp\">\r\n");
      out.write("\r\n");
      out.write("      <table align=\"center\" width=\"509\" border=\"1\" cellspacing=\"1\" \r\n");
      out.write("          summary=\"Escoge si deseas un in\r\n");
      out.write("          e de proyectos de investigacion por grupo o semillero\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th colspan=\"2\" bgcolor=\"#5dad30\" scope=\"col\"><strong>Informe por Grupo o Semillero</strong></th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th bgcolor=\"#5dad30\" scope=\"col\">Seleccione Grupo o Semillero</th>\r\n");
      out.write("          <th scope=\"col\" align=\"justify\"><select name=\"txtidgruposem2\" id=\"txtidgruposem2\">\r\n");
      out.write("            <option value=\"1\" selected=\"selected\">Grupo de Investigacion</option>\r\n");
      out.write("            <option value=\"2\">Semillero</option>\r\n");
      out.write("          </select></th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th width=\"219\" bgcolor=\"#5dad30\" scope=\"col\">Facultad</th>\r\n");
      out.write("          <th width=\"277\" scope=\"col\" align=\"justify\"><select name=\"txtidfacultad2\" id=\"txtidfacultad2\">\r\n");
      out.write("            <option value=\"2\" selected=\"selected\">Ingenieria</option>\r\n");
      out.write("            <option value=\"1\">Tecnologica</option>\r\n");
      out.write("            <option value=\"3\">Ambiental</option>\r\n");
      out.write("            <option value=\"4\">Educación</option>\r\n");
      out.write("            <option value=\"5\">Artes</option>\r\n");
      out.write("          </select></th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th bgcolor=\"#5dad30\" scope=\"col\">Formato</th>\r\n");
      out.write("          <th scope=\"col\" align=\"justify\" ><select name=\"txtidformato2\" id=\"txtidformato2\">\r\n");
      out.write("            <option value=\"1\" selected=\"selected\">PDF</option>\r\n");
      out.write("            <option value=\"2\">EXCEL</option>\r\n");
      out.write("          </select></th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"2\" align=\"center\"><input name=\"button4\" type=\"submit\" id=\"button4\" value=\"Generar Reporte\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("    </form>\r\n");
      out.write("    <p align=\"center\">Reporte de Proyectos de Investigaci&oacuten a culminar. </p>\r\n");
      out.write("    <form name=\"form4\" method=\"post\" action=\"REPORTEFOUR.jsp\">\r\n");
      out.write("      <table  align=\"center\" width=\"439\" border=\"1\" cellspacing=\"3\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"5\" align=\"center\" bgcolor=\"#5dad30\"><strong> Proyectos de Investigacion a culminar</strong></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th width=\"57\" align=\"center\" bgcolor=\"#5dad30\" scope=\"col\">Facultad</th>\r\n");
      out.write("          <th width=\"106\" scope=\"col\" align=\"center\"><select name=\"txtidfacultad4\" id=\"txtidfacultad4\">\r\n");
      out.write("            <option value=\"2\" selected=\"selected\">Ingenieria</option>\r\n");
      out.write("            <option value=\"1\">Tecnologica</option>\r\n");
      out.write("            <option value=\"3\">Ambiental</option>\r\n");
      out.write("            <option value=\"4\">Educación</option>\r\n");
      out.write("            <option value=\"5\">Artes</option>\r\n");
      out.write("          </select>\r\n");
      out.write("            </center></th>\r\n");
      out.write("          <th width=\"50\" align=\"center\" bgcolor=\"#5dad30\" scope=\"col\">Tiempo</th>\r\n");
      out.write("          <th width=\"144\" bgcolor=\"#5dad30\" scope=\"col\"> <input name=\"txtidtempo\" type=\"text\" id=\"txtidtempo\" value=\"15\"></th>\r\n");
      out.write("          <th width=\"42\" scope=\"col\">Dias</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <th colspan=\"3\" align=\"center\" bgcolor=\"#5dad30\" scope=\"col\">Formato</th>\r\n");
      out.write("          <th align=\"center\" scope=\"col\"><select name=\"txtidformato3\" id=\"txtidformato3\">\r\n");
      out.write("            <option value=\"1\" selected=\"selected\">PDF</option>\r\n");
      out.write("            <option value=\"2\">EXCEL</option>\r\n");
      out.write("          </select></th>\r\n");
      out.write("          <th align=\"center\" scope=\"col\">&nbsp;</th>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"5\" align=\"center\"><input name=\"button5\" type=\"submit\" id=\"button5\" value=\"Generar Reporte\">\r\n");
      out.write("            </strong></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("    </form>\r\n");
      out.write("    <p></p>\r\n");
      out.write("    <p align=\"center\"> Reporte por codigo: </p>\r\n");
      out.write("    <form action=\"REPORTEFIVE.jsp\" method=\"post\" name=\"form1\">\r\n");
      out.write("      <table align=\"center\" width=\"487\"  border=\"1\">\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"2\" align=\"center\" bgcolor=\"#5dad30\"><strong>Reportes PDF con parametros JSP</strong></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td width=\"168\" bgcolor=\"#5dad30\" align=\"center\"><strong>Codigo de Proyecto</strong></td>\r\n");
      out.write("          <td width=\"415\"  align=\"center\"><input name=\"txtidcodigo\" type=\"text\" id=\"txtidcodigo\" value=\"\"></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td bgcolor=\"#5dad30\" align=\"center\"><strong>Formato</strong></td>\r\n");
      out.write("          <td align=\"center\"><select name=\"txtidformato4\" id=\"txtidformato4\">\r\n");
      out.write("            <option value=\"1\" selected=\"selected\">PDF</option>\r\n");
      out.write("            <option value=\"2\">EXCEL</option>\r\n");
      out.write("            <option value=\"3\">HTML</option>\r\n");
      out.write("          </select></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("        <tr>\r\n");
      out.write("          <td colspan=\"2\"><center>\r\n");
      out.write("            <input name=\"button\" type=\"submit\" id=\"button\" value=\"Generar Reporte\">\r\n");
      out.write("          </center></td>\r\n");
      out.write("        </tr>\r\n");
      out.write("      </table>\r\n");
      out.write("    </form>\r\n");
      out.write("    <p>&nbsp; </p>\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("var sprytextfield1 = new Spry.Widget.ValidationTextField(\"sprytextfield1\");\r\n");
      out.write("    </script>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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
}
