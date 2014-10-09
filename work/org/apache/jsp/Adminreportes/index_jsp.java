package org.apache.jsp.Adminreportes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\r\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\"\r\n");
      out.write("   \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>JSP Page</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <h2 align=\"center\">Reporte General \r\n");
      out.write("          Proyectos de Investigacion <br>\r\n");
      out.write("        Universidad Distrital Francisco Jose de Caldas</h2>\r\n");
      out.write("    <p align=\"center\" dropzone=\"copy\">En la presente pagina, se pueden generar reportes frente a los proyectos de investigaci&oacuten de la universidad distrital, teniendo en cuenta 3 tipos.    </p>\r\n");
      out.write("    <form name=\"form1\" method=\"post\" >\r\n");
      out.write("    <table  align=\"center\" width=\"656\" border=\"1\">\r\n");
      out.write("       <tr bgcolor=\"#FFCC99\">\r\n");
      out.write("             <th colspan=\"2\" align=\"center\" bgcolor=\"#5dad30\">Reporte General</th>\r\n");
      out.write("           </tr>\r\n");
      out.write("           <tr>\r\n");
      out.write("             <th width=\"296\" height=\"40\" bgcolor=\"#5dad30\">Por Filtros</th>\r\n");
      out.write("             <td width=\"344\"><center>\r\n");
      out.write("               <input name=\"button3\" type=\"submit\" id=\"button3\" value=\"Aplicar Filtros\" autofocus onclick=\"window.open('http://10.20.12.101:8080/jasperserver/flow.html?_flowId=viewReportFlow&standAlone=true&_flowId=viewReportFlow&ParentFolderUri=%2Freports%2Fgruposdeinvestigacion&reportUnit=%2Freports%2Fgruposdeinvestigacion%2Fprueba2_1&j_username=invitado&j_password=invitado&singlesingon=y&decorate=no', 'mywindow','menubar=1,resizable=1,location=0,width=1150,height=800')\">\r\n");
      out.write("             </center>\r\n");
      out.write("      </tr>\r\n");
      out.write("           </table>\r\n");
      out.write("     </form>\r\n");
      out.write("        \r\n");
      out.write("        <form name=\"form2\" method=\"post\" >\r\n");
      out.write("          <table  align=\"center\" width=\"656\" border=\"1\">\r\n");
      out.write("            <tr>\r\n");
      out.write("              <th width=\"296\" height=\"40\" bgcolor=\"#5dad30\"><p>Proyectos Pr<span class=\"cualquiera\">&oacute</span>ximos a Vencerse</p></th>\r\n");
      out.write("              <td width=\"344\"><center>\r\n");
      out.write("                <input name=\"button6\" type=\"submit\" id=\"button6\" value=\"Ingresar Tiempo\" onclick=\"window.open('http://10.20.12.101:8080/jasperserver/flow.html?_flowId=viewReportFlow&standAlone=true&_flowId=viewReportFlow&ParentFolderUri=%2Freports%2Fgruposdeinvestigacion&reportUnit=%2Freports%2Fgruposdeinvestigacion%2FProyectos_Proximos_a_Vencer&j_username=invitado&j_password=invitado&decorate=no', 'mywindow','menubar=1,resizable=1,location=0,width=1150,height=400')\">\r\n");
      out.write("              </center>\r\n");
      out.write("          </table>\r\n");
      out.write("        </form>\r\n");
      out.write("        <form name=\"form3\" method=\"post\" >\r\n");
      out.write("          <table  align=\"center\" width=\"656\" border=\"1\">\r\n");
      out.write("            <tr>\r\n");
      out.write("              <th width=\"296\" height=\"129\" bgcolor=\"#5dad30\"><p>Indicadores Proyectos de Investigaci<span class=\"cualquiera\">&oacute</span>n</p></th>\r\n");
      out.write("              <td width=\"344\"><center>\r\n");
      out.write("                <p>\r\n");
      out.write("                  <input name=\"button6\"  type=\"image\" id=\"button6\" title=\"Visualizar Indicadores\" onclick=\"window.open('http://10.20.12.101:8080/jasperserver/flow.html?_flowId=viewReportFlow&amp;standAlone=true&amp;_flowId=viewReportFlow&amp;ParentFolderUri=%2Freports%2FIndicadores&amp;reportUnit=%2Freports%2FIndicadores%2FIndicadores_Grupos_Investigacion&amp;j_username=invitado&amp;j_password=invitado&amp;decorate=no', 'mywindow','menubar=1,resizable=1,location=0,width=1150,height=400')\" value=\"Indicadores\" src=\"../comp/img/indicadores2.jpg\" alt=\"Visualizar Indicadores\" align=\"middle\">\r\n");
      out.write("                </p>\r\n");
      out.write("              </center>\r\n");
      out.write("          </table>\r\n");
      out.write("        </form>\r\n");
      out.write("<!-- \thola mundo  -->\r\n");
      out.write("    <p align=\"center\">&nbsp;</p>\r\n");
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
