package org.apache.jsp.grupos.proyectos;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ListaGastos_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems_005fbegin;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems_005fbegin = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fimport_0026_005furl_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems_005fbegin.release();
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
      cidc.general.obj.Globales globales = null;
      synchronized (_jspx_page_context) {
        globales = (cidc.general.obj.Globales) _jspx_page_context.getAttribute("globales", PageContext.PAGE_SCOPE);
        if (globales == null){
          globales = new cidc.general.obj.Globales();
          _jspx_page_context.setAttribute("globales", globales, PageContext.PAGE_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<link type=\"text/css\" rel=\"stylesheet\" href=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\">\r\n");
      out.write("<script type=\"text/javascript\" language=\"javascript\" src='");
      if (_jspx_meth_c_005furl_005f1(_jspx_page_context))
        return;
      out.write("'></script>\r\n");
      out.write("<link rel=\"stylesheet\" href='");
      if (_jspx_meth_c_005furl_005f2(_jspx_page_context))
        return;
      out.write("' type=\"text/css\" media=\"screen\" />\r\n");
      if (_jspx_meth_c_005fimport_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("<script>\r\n");
      out.write("\tfunction comando(id){\r\n");
      out.write("\t\tdocument.gastos.accion.value=id;\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction tipoPer(obj){\r\n");
      out.write("\t\tdocument.frmAjaxServicios.dato.value=obj.options[obj.selectedIndex].value;\r\n");
      out.write("\t\tdocument.frmAjaxServicios.accion.value='11';\r\n");
      out.write("\t \tdocument.frmAjaxServicios.target=\"frameOculto\";\r\n");
      out.write("\t\tdocument.frmAjaxServicios.submit();\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction ajaxProyecto(){\r\n");
      out.write("\t\t//document.tipoPersona.action=\"");
      if (_jspx_meth_c_005furl_005f3(_jspx_page_context))
        return;
      out.write("\";\r\n");
      out.write("\t    document.tipoPersona.accion.value = 25;\r\n");
      out.write("\t\tdocument.tipoPersona.submit();\r\n");
      out.write("\t\t\r\n");
      out.write("\t}\r\n");
      out.write("\r\n");
      out.write("\tfunction cargar(op){\r\n");
      out.write("\t\tdocument.documentosAdj.accion.value = 26;\r\n");
      out.write("\t\tif(validacionAdj(op))\r\n");
      out.write("\t\t\tdocument.documentosAdj.submit();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction infoAdicional(){\r\n");
      out.write("\t\tdocument.rubroSolicitado.accion.value = 27;\r\n");
      out.write("\t\tdocument.rubroSolicitado.submit();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction cambiarNatural(op){\r\n");
      out.write("\t\tvar cambio=\"\";\r\n");
      out.write("\t\tif(op==1){//1 para ocultar 2 para mostrar\r\n");
      out.write("\t\t\tcambio=\"none\";\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tcambio=\"block\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tdocument.getElementById(\"lexpedicion\").style.display=cambio;\r\n");
      out.write("\t\tdocument.tipoPersona.expedicion.style.display=cambio;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction cambiarJuridica(op){\r\n");
      out.write("\t\tvar cambio=\"\";\r\n");
      out.write("\t\tif(op==1){//1 para ocultar 2 para mostrar\r\n");
      out.write("\t\t\tcambio=\"none\";\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tcambio=\"block\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tdocument.getElementById(\"lrepresentante\").style.display=cambio;\r\n");
      out.write("\t\tdocument.tipoPersona.representante.style.display=cambio;\r\n");
      out.write("\t\tdocument.getElementById(\"lcontacto\").style.display=cambio;\r\n");
      out.write("\t\tdocument.tipoPersona.contacto.style.display=cambio;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction cambiarPersona(){\r\n");
      out.write("\t\tif(document.tipoPersona.tipoSolicitud1.value!=9){//cuendo el tipo de solicitud sea resolucion no habilitara estos campos\r\n");
      out.write("\t\t\tdocument.getElementById(\"lnombre\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.nombre.style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcedula\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.cedula.style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"ldireccion\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.direccion.style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcorreo\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.correo.style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"ltelefono\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.telefono.style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcelular\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.celular.style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lformaPago\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.formaPago.style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcargoSupervisor\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.cargoSupervisor.style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"ldepSupervisor\").style.display='block';\r\n");
      out.write("\t\t\tdocument.tipoPersona.depSupervisor.style.display='block';\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tdocument.getElementById(\"lproyecto\").style.display='block';\r\n");
      out.write("\t\tdocument.tipoPersona.proyecto.style.display='block';\r\n");
      out.write("\t\tdocument.getElementById(\"lduracion\").style.display='block';\r\n");
      out.write("\t\tdocument.tipoPersona.duracion.style.display='block';\r\n");
      out.write("\t\tdocument.getElementById(\"lvalorContrato\").style.display='block';\r\n");
      out.write("\t\tdocument.tipoPersona.valorContrato.style.display='block';\r\n");
      out.write("\t\tdocument.getElementById(\"ljustificacion\").style.display='block';\r\n");
      out.write("\t\tdocument.tipoPersona.justificacion.style.display='block';\r\n");
      out.write("\t\tdocument.getElementById(\"lobjetivo\").style.display='block';\r\n");
      out.write("\t\tdocument.tipoPersona.objetivo.style.display='block';\r\n");
      out.write("\t\tdocument.getElementById(\"g1\").style.display='block';\r\n");
      out.write("\t\tif(document.tipoPersona.tipoSolicitud1.value==1||document.tipoPersona.tipoSolicitud1.value==2||document.tipoPersona.tipoSolicitud1.value==4\r\n");
      out.write("\t\t\t\t||document.tipoPersona.tipoSolicitud1.value==6||document.tipoPersona.tipoSolicitud1.value==8){\r\n");
      out.write("\t\t\tif(document.tipoPersona.tipoSolicitud1.value!=9){\r\n");
      out.write("\t\t\t\tcambiarJuridica(1);\r\n");
      out.write("\t\t\t\tcambiarNatural(2);\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}if(document.tipoPersona.tipoSolicitud1.value==3||document.tipoPersona.tipoSolicitud1.value==5||document.tipoPersona.tipoSolicitud1.value==7){\r\n");
      out.write("\t\t\tif(document.tipoPersona.tipoSolicitud1.value!=9){\r\n");
      out.write("\t\t\t\tcambiarJuridica(2);\r\n");
      out.write("\t\t\t\tcambiarNatural(1);\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction validar(){\r\n");
      out.write("\t\tvar mensaje=\"\";\r\n");
      out.write("\t\tif(document.tipoPersona.nombre.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Nombre / Razón Social\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.cedula.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Número de Cédula / NIT\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.direccion.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Dirección\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.correo.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Correo\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.telefono.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Teléfono\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.celular.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Celular\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.tipoSolicitud1.value==1||document.tipoPersona.tipoSolicitud1.value==2||document.tipoPersona.tipoSolicitud1.value==4\r\n");
      out.write("\t\t\t\t||document.tipoPersona.tipoSolicitud1.value==6||document.tipoPersona.tipoSolicitud1.value==8){\r\n");
      out.write("\t\t\tif(document.tipoPersona.expedicion.value==\"\")\r\n");
      out.write("\t\t\t\tmensaje+=\"\\n -Lugar de Expedición\";\r\n");
      out.write("\t\t}if(document.tipoPersona.tipoSolicitud1.value==3||document.tipoPersona.tipoSolicitud1.value==5||document.tipoPersona.tipoSolicitud1.value==7){\r\n");
      out.write("\t\t\tif(document.tipoPersona.representante.value=\"\")\r\n");
      out.write("\t\t\t\tmensaje+=\"\\n -Representante Legal\";\r\n");
      out.write("\t\t\tif(document.tipoPersona.contacto.value==\"\")\r\n");
      out.write("\t\t\t\tmensaje+=\"\\n -Contacto Comercial\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.cargoSupervisor.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Cargo del Supervisor\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.depSupervisor.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Dependencia del Supervisor\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.formaPago.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Forma de Pago\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.tipoSolicitud1.value==9){\r\n");
      out.write("\t\t\tmensaje=\"\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.proyecto.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Proyecto\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.duracion.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Duración\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.valorContrato.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Valor del Contrato\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.justificacion.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Justificación\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.tipoPersona.objetivo.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"\\n -Objetivo\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(mensaje!=\"\"){\r\n");
      out.write("\t\t\tmensaje=\"Los siguientes campos son obligatorios: \"+mensaje;\r\n");
      out.write("\t\t\talert (mensaje);\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tbloquear();\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction bloquear(){\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tdocument.tipoPersona.nombre.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.cedula.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.expedicion.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.representante.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.direccion.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.correo.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.telefono.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.celular.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.contacto.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.proyecto.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.duracion.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.formaPago.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.valorContrato.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.justificacion.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.objetivo.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.cargoSupervisor.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.depSupervisor.readOnly=true;\r\n");
      out.write("\t\tdocument.tipoPersona.accion.value=25;\r\n");
      out.write("\t\tdocument.tipoPersona.tipoSolicitud.value=document.tipoPersona.tipoSolicitud1.value;\r\n");
      out.write("\t\tdocument.tipoPersona.tipoSolicitud1.disable=\"true\";\r\n");
      out.write("\t\tdocument.tipoPersona.submit();\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction documentos(op){\r\n");
      out.write("\t\tif(op>=1&&op<=7){\r\n");
      out.write("\t\t\tdocument.getElementById(\"c1\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c2\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c3\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c4\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c10\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c11\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c15\").style.display='block';\r\n");
      out.write("\t\t}if(op==1||op==2){\r\n");
      out.write("\t\t\tdocument.getElementById(\"c5\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c9\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c12\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c13\").style.display='block';\r\n");
      out.write("\t\t}if(op==3||op==5||op==7){\r\n");
      out.write("\t\t\tdocument.getElementById(\"c14\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c16\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c17\").style.display='block';\r\n");
      out.write("\t\t}if(op==4||op==6){\r\n");
      out.write("\t\t\tdocument.getElementById(\"c9\").style.display='block';\r\n");
      out.write("\t\t}if(op==8){\r\n");
      out.write("\t\t\tdocument.getElementById(\"c1\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c2\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c4\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c6\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c7\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c8\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c15\").style.display='block';\r\n");
      out.write("\t\t}if(op==9){\r\n");
      out.write("\t\t\tdocument.getElementById(\"c18\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c19\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c20\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c21\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c22\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c23\").style.display='block';\r\n");
      out.write("\t\t\tdocument.getElementById(\"c24\").style.display='block';\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tdocument.getElementById(\"tablaAdj\").style.display=\"block\";\r\n");
      out.write("\t\tdocument.getElementById(\"tablaArch\").style.display=\"block\";\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("\tfunction validacionAdj(op){\r\n");
      out.write("\t\t//alert(\"khgf \"+document.getElementById(\"propuesta\").checked);\r\n");
      out.write("\t\tvar mensaje=\"\";\r\n");
      out.write("\t\tif(op>=1&&op<=7){\r\n");
      out.write("\t\t\tif(document.getElementById(\"propuesta\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Propuesta de servicios\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"hojaPersonal\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Hoja de vida personal\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"hojaPublica\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Hoja de vida funcion pública\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"fotocopiaCedula\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) fotocopia de cédula\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"certProcuraduria\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Antecedentes Procuraduría\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"certContraloria\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Antecendentes contraloría\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"rut\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) RUT\\n\";\r\n");
      out.write("\t\t}if(op==1||op==2){\r\n");
      out.write("\t\t\tif(document.getElementById(\"tarjetaProf\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Tarjeta Profesional\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"certPersoneria\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Certificado Personería\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"certSalud\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Afiliación a salud\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"certPension\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Afiliacion a Pensiones\\n\";\r\n");
      out.write("\t\t}if(op==3||op==5||op==7){\r\n");
      out.write("\t\t\tif(document.getElementById(\"certParafiscal\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Pago de Parafiscales\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"proveedor\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Concepto técnico de proveedores\\n\";\r\n");
      out.write("\t\t}if(op==4||op==6){\r\n");
      out.write("\t\t\tif(document.getElementById(\"certPersoneria\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Certificado Personería\\n\";\r\n");
      out.write("\t\t}if(op==8){\r\n");
      out.write("\t\t\tif(document.getElementById(\"propuesta\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Propuesta de servicios\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"fotocopiaCedula\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) fotocopia de cedula\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"rut\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Rut\\n\";\r\n");
      out.write("\t\t}if(op==9){\r\n");
      out.write("\t\t\tif(document.getElementById(\"avance\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Solicitud de avance\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"presupuesto\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Presupuesto\\n\";\r\n");
      out.write("\t\t\tif(document.getElementById(\"pazySalvo\").checked!=true)\r\n");
      out.write("\t\t\t\tmensaje+=\"-) Paz y salde de avance\\n\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(document.documentosAdj.archivo.value==\"\"){\r\n");
      out.write("\t\t\tmensaje+=\"-) Archivo Adjunto\\n\";\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(mensaje!=\"\"){\r\n");
      out.write("\t\t\tmensaje=\"Los siguientes campos son obligatorios:\\n\"+mensaje;\r\n");
      out.write("\t\t\talert(mensaje);\r\n");
      out.write("\t\t}else\r\n");
      out.write("\t\t\treturn true;\r\n");
      out.write("\t\treturn false;\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction mostrar(){\r\n");
      out.write("\t\tdocument.tipoPersona.style.display='block';\r\n");
      out.write("\t\tdocument.nuevaSolicitud.style.display='none';\r\n");
      out.write("\t}\r\n");
      out.write("\t\r\n");
      out.write("\tfunction cambiarSolicitud(){\r\n");
      out.write("\t\tif(document.tipoPersona.tipoSolicitud1.value!=9){\r\n");
      out.write("\t\t\tif(document.tipoPersona.tipoSolicitud1.value!=0){\r\n");
      out.write("\t\t\t\tcambiarPersona();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tdocument.getElementById(\"lnombre\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.nombre.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcedula\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.cedula.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"ldireccion\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.direccion.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lexpedicion\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.expedicion.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcorreo\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.correo.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"ltelefono\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.telefono.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcelular\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.celular.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lformaPago\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.formaPago.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcargoSupervisor\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.cargoSupervisor.style.display='none';\r\n");
      out.write("\t\t\tdocument.getElementById(\"ldepSupervisor\").style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.depSupervisor.style.display='none';\r\n");
      out.write("\t\t\tdocument.tipoPersona.tipoPersona1.style.display=\"none\";\r\n");
      out.write("\t\t\tdocument.getElementById(\"lrepresentante\").style.display=\"none\";\r\n");
      out.write("\t\t\tdocument.tipoPersona.representante.style.display=\"none\";\r\n");
      out.write("\t\t\tdocument.getElementById(\"lcontacto\").style.display=\"none\";\r\n");
      out.write("\t\t\tdocument.tipoPersona.contacto.style.display=\"none\";\r\n");
      out.write("\t\t\tdocument.getElementById(\"ltipoPersona1\").style.display=\"none\";\r\n");
      out.write("\t\t\tcambiarPersona(document.tipoPersona.tipoPersona1);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//documentos(document.tipoPersona.tipoSolicitud1.value);\r\n");
      out.write("\t}\r\n");
      out.write("</script>\r\n");
      out.write("</head>\r\n");
      out.write("<body onLoad=\"mensajeAlert(document.getElementById('msg'));\">\r\n");
      out.write("<br/>\r\n");
      out.write("<!--\r\n");
      out.write("\t<img id=\"nuevaSolicitud\" src='");
      if (_jspx_meth_c_005furl_005f4(_jspx_page_context))
        return;
      out.write("' onclick=\"mostrar()\"/>\r\n");
      out.write("\t<form name=\"tipoPersona\" action='");
      if (_jspx_meth_c_005furl_005f5(_jspx_page_context))
        return;
      out.write("' method=\"post\" style=\"display:none\"> \r\n");
      out.write("\t<input type=\"hidden\" name=\"accion\" value=\"0\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"tipoSolicitud\" value=\"\">\r\n");
      out.write("\t<input type=\"hidden\" name=\"tipoPersona\" value=\"\">\r\n");
      out.write("\t\t<table width=\"95%\" align=\"center\" class=\"tablas\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.formulario}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\t\t<caption>Datos del Contratista</caption>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td  align=\"left\"><b>");
      if (_jspx_meth_c_005fout_005f0(_jspx_page_context))
        return;
      out.write("</b>\r\n");
      out.write("\t\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t\t\t<td><select name=\"tipoSolicitud1\" onchange=\"cambiarSolicitud(this)\">\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"0\">----</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"1\">OPS</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"2\">CPS-PN</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"3\">CPS-PJ</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"4\">OS-PN</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"5\">OS-PJ</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"6\">OC-PN</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"7\">OC-PJ</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"8\">OPSAR</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"9\">RESOLUCION DE AVANCE</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t</td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td  align=\"left\"><b><label id=\"ltipoPersona1\">Tipo de Persona:</label></b></td>-->\r\n");
      out.write("\t\t\t\t\t\t<!-- <td><select name=\"tipoPersona1\" onchange=\"cambiarPersona(this)\"> ESTE BLOQUE ESTA COMENTADO TAL VEZ SE NECESITE DESPUES\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"0\">----</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"1\">Persona Natural</option>\r\n");
      out.write("\t\t\t\t\t\t\t<option value=\"2\">Persona Jurídica</option>\r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td> -->\r\n");
      out.write("\t\t\t<!-- <td  align=\"left\"><b><label id=\"tipoPersona1\" ></label></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lnombre\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Nombres y Apellidos / Razón Social:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"nombre\" MAXLENGTH=\"50\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lcedula\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Número de Cedula / NIT:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"cedula\" MAXLENGTH=\"15\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"/></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lexpedicion\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.natural}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Lugar de Expedición:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"expedicion\" MAXLENGTH=\"20\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.natural}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lrepresentante\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.juridica}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Representante Legal:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"representante\" MAXLENGTH=\"50\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.juridica}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"ldireccion\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Dirección:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"direccion\" MAXLENGTH=\"20\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lcorreo\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Correo Electrónico:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"correo\" MAXLENGTH=\"50\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"ltelefono\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Teléfono Fijo:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"telefono\" MAXLENGTH=\"7\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lcelular\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Teléfono Celular:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"celular\" MAXLENGTH=\"10\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lcontacto\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.juridica}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Contacto Comercial:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"contacto\" MAXLENGTH=\"50\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.juridica}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lproyecto\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Nombre del Proyecto / Convenio:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"proyecto\" MAXLENGTH=\"200\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lduracion\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Duración:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"duracion\" MAXLENGTH=\"20\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lformaPago\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Forma de Pago:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"formaPago\" MAXLENGTH=\"20\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lvalorContrato\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Valor Total del Contrato:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"valorContrato\" MAXLENGTH=\"10\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"ljustificacion\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Justificación:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><textarea NAME=\"justificacion\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></textarea></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lobjetivo\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Objetivo:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><textarea NAME=\"objetivo\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\" ></textarea></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"lcargoSupervisor\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Cargo del Supervisor:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"cargoSupervisor\" MAXLENGTH=\"50\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr >\r\n");
      out.write("\t\t\t\t<td align=\"left\" ><b><label id=\"ldepSupervisor\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">Dependencia del Supervisor:</label></b></td>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><b><INPUT NAME=\"depSupervisor\" MAXLENGTH=\"50\" TYPE=\"TEXT\" VALUE=\"\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"></b></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td id=\"g1\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${requestScope.basico}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\"><img src='");
      if (_jspx_meth_c_005furl_005f6(_jspx_page_context))
        return;
      out.write("' onclick=\"validar()\" ></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t\t<table id=\"tablaAdj\" width=\"95%\" align=\"center\" class=\"tablas\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.lista}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"center\"><label id=\"mensaje\" style=\"display:block\">Señor investigador<br> Tenga en cuenta que usted deberá cargar un solo archivo el cual contenga los siguientes documentos:</label>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"center\"><img  src='");
      if (_jspx_meth_c_005furl_005f7(_jspx_page_context))
        return;
      out.write("' onclick=\"documentos(");
      if (_jspx_meth_c_005fout_005f1(_jspx_page_context))
        return;
      out.write(")\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<TR>\r\n");
      out.write("\t\t\t\t<td id=\"c1\" style=\"display:none\" align=\"left\"><input type=\"checkbox\" id=\"propuesta\" value=\"1\">Propuesta de Servicios<br></td>\r\n");
      out.write("\t\t\t</TR>\r\n");
      out.write("\t\t\t<tr><td id=\"c2\" style=\"display:none\"><input type=\"checkbox\" id=\"hojaPersonal\" value=\"2\" >Hoja de vida Personal Con Soportes <br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c3\" style=\"display:none\"><input type=\"checkbox\" id=\"hojaPublica\" value=\"3\">Hoja de Vida Función Publica<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c4\" style=\"display:none\"><input type=\"checkbox\" id=\"fotocopiaCedula\" value=\"4\">Fotocopia Cédula de Ciudadanía<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c5\" style=\"display:none\"><input type=\"checkbox\" id=\"tarjetaProf\" value=\"5\" >Tarjeta Profesional (si aplica)<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c6\" style=\"display:none\"><input type=\"checkbox\" id=\"carnet\" value=\"6\">Carnet Estudiantil<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c7\" style=\"display:none\"><input type=\"checkbox\" id=\"recibo\" value=\"7\">Recibo de Pago<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c8\" style=\"display:none\"><input type=\"checkbox\" id=\"certEstudiantil\" value=\"8\" >Certificado Estudiantil<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c9\" style=\"display:none\"><input type=\"checkbox\" id=\"certPersoneria\" value=\"9\">Certificado Personeria Distrital<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c10\" style=\"display:none\"><input type=\"checkbox\" id=\"certProcuraduria\" value=\"10\">Antecedentes Disciplinarios - Procuraduría<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c11\" style=\"display:none\"><input type=\"checkbox\" id=\"certContraloria\" value=\"11\" >Antecedentes Fiscales - Contraloría<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c12\" style=\"display:none\"><input type=\"checkbox\" id=\"certSalud\" value=\"12\">Certificado de Afilicación a Salud<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c13\" style=\"display:none\"><input type=\"checkbox\" id=\"certPension\" value=\"13\">Certificado de Afilicación a Pensiones<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c14\" style=\"display:none\"><input type=\"checkbox\" id=\"certParafiscal\" value=\"14\" >Certificado de Pago de Parafiscales<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c15\" style=\"display:none\"><input type=\"checkbox\" id=\"rut\" value=\"15\">Registro Único Tributario - RUT<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c16\" style=\"display:none\"><input type=\"checkbox\" id=\"rup\" value=\"16\">Registro Único de Proponentes<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c17\" style=\"display:none\"><input type=\"checkbox\" id=\"proveedor\" value=\"17\" >Concepto Técnico de Selección de Proveedores<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c18\" style=\"display:none\"><input type=\"checkbox\" id=\"avance\" value=\"18\">Solicitud de Avance<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c19\" style=\"display:none\"><input type=\"checkbox\" id=\"presupuesto\" value=\"19\" >Presupuesto<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c20\" style=\"display:none\"><input type=\"checkbox\" id=\"pazySalvo\" value=\"20\">Paz y Salvos de Avances (Visto Bueno de Tesoreria)<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c21\" style=\"display:none\"><input type=\"checkbox\" id=\"cotizacion\" value=\"21\">Cotizaciones<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c22\" style=\"display:none\"><input type=\"checkbox\" id=\"avalFacultad\" value=\"22\">Aval del Consejo de Facultad (si aplica)<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c23\" style=\"display:none\"><input type=\"checkbox\" id=\"avalAcademico\" value=\"23\">Aval del Consejo Académico (si aplica)<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t<tr><td id=\"c24\" style=\"display:none\"><input type=\"checkbox\" id=\"cronograma\" value=\"24\">Cronograma<br>\r\n");
      out.write("\t\t\t</td></tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t</form>\r\n");
      out.write("\t<form name=\"documentosAdj\" action='");
      if (_jspx_meth_c_005furl_005f8(_jspx_page_context))
        return;
      out.write("' method=\"post\" enctype=\"multipart/form-data\">\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"accion\" value=\"0\">\r\n");
      out.write("\t\t\t<table width=\"95%\" align=\"center\" id=\"tablaArch\" class=\"tablas\" style=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.adjuntos}", java.lang.String.class, (PageContext)_jspx_page_context, null, false));
      out.write("\">\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td align=\"left\"><label id=\"larchivo\" style=\"display:block\"><b>Documentos Adjuntos</b></label>\r\n");
      out.write("\t\t\t\t<td align=\"left\" colspan=\"3\"><input type=\"file\" name=\"archivo\" style=\"display:block\"/></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t\t<tr>\r\n");
      out.write("\t\t\t\t<td id=\"g2\"><img src='");
      if (_jspx_meth_c_005furl_005f9(_jspx_page_context))
        return;
      out.write("' onclick=\"cargar(");
      if (_jspx_meth_c_005fout_005f2(_jspx_page_context))
        return;
      out.write(")\"></td>\r\n");
      out.write("\t\t\t</tr>\r\n");
      out.write("\t\t</table>\r\n");
      out.write("\t</form>\r\n");
      out.write("\r\n");
      out.write("\t<form name=\"rubroSolicitado\" action='");
      if (_jspx_meth_c_005furl_005f10(_jspx_page_context))
        return;
      out.write("' method=\"post\">\r\n");
      out.write("\t\t<input type=\"hidden\" name=\"accion\" value=\"\">\r\n");
      out.write("\t\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t");
      if (_jspx_meth_c_005fif_005f1(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t</form>\r\n");
      out.write("  \r\n");
      out.write(" -->\r\n");
      out.write("\t");
      if (_jspx_meth_c_005fif_005f2(_jspx_page_context))
        return;
      out.write('\r');
      out.write('\n');
      out.write('	');
      if (_jspx_meth_c_005fif_005f5(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("</body>\r\n");
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
    // /grupos/proyectos/ListaGastos.jsp(7,45) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f0.setValue("/comp/css/formatos.css");
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
    // /grupos/proyectos/ListaGastos.jsp(8,58) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f1.setValue("/comp/js/lytebox.js");
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
    // /grupos/proyectos/ListaGastos.jsp(9,29) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f2.setValue("/comp/css/lytebox.css");
    int _jspx_eval_c_005furl_005f2 = _jspx_th_c_005furl_005f2.doStartTag();
    if (_jspx_th_c_005furl_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f2);
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
    // /grupos/proyectos/ListaGastos.jsp(10,0) name = url type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
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

  private boolean _jspx_meth_c_005furl_005f3(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f3 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f3.setParent(null);
    // /grupos/proyectos/ListaGastos.jsp(24,33) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f3.setValue("/GestionProyectos/ProyectosInvestigador.x");
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
    // /grupos/proyectos/ListaGastos.jsp(353,31) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f4.setValue("/comp/img/Guardar.gif");
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
    // /grupos/proyectos/ListaGastos.jsp(354,34) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f5.setValue("/grupos/proyectos/llenar.jsp");
    int _jspx_eval_c_005furl_005f5 = _jspx_th_c_005furl_005f5.doStartTag();
    if (_jspx_th_c_005furl_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f5);
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
    // /grupos/proyectos/ListaGastos.jsp(361,25) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f0.setValue(new String("Tipo de Solicitud"));
    int _jspx_eval_c_005fout_005f0 = _jspx_th_c_005fout_005f0.doStartTag();
    if (_jspx_th_c_005fout_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f0);
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
    // /grupos/proyectos/ListaGastos.jsp(456,57) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f6.setValue("/comp/img/Guardar.gif");
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
    // /grupos/proyectos/ListaGastos.jsp(464,34) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f7.setValue("/comp/img/Siguiente.gif");
    int _jspx_eval_c_005furl_005f7 = _jspx_th_c_005furl_005f7.doStartTag();
    if (_jspx_th_c_005furl_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f7);
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
    // /grupos/proyectos/ListaGastos.jsp(464,96) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f1.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.contratacion.tipoSolicitud}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f1 = _jspx_th_c_005fout_005f1.doStartTag();
    if (_jspx_th_c_005fout_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f1);
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
    // /grupos/proyectos/ListaGastos.jsp(517,36) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f8.setValue("/proyectos/cargarAdj.x");
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
    // /grupos/proyectos/ListaGastos.jsp(525,26) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f9.setValue("/comp/img/Guardar.gif");
    int _jspx_eval_c_005furl_005f9 = _jspx_th_c_005furl_005f9.doStartTag();
    if (_jspx_th_c_005furl_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f9);
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
    // /grupos/proyectos/ListaGastos.jsp(525,82) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f2.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.contratacion.tipoSolicitud}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f2 = _jspx_th_c_005fout_005f2.doStartTag();
    if (_jspx_th_c_005fout_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f2);
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
    // /grupos/proyectos/ListaGastos.jsp(530,38) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f10.setValue("/GestionProyectos/ProyectosInvestigador.x");
    int _jspx_eval_c_005furl_005f10 = _jspx_th_c_005furl_005f10.doStartTag();
    if (_jspx_th_c_005furl_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /grupos/proyectos/ListaGastos.jsp(532,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${1==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t<table align=\"center\" class=\"tablas\" width=\"95%\">\r\n");
        out.write("\t\t\t\t<caption>Listado de Rubros Solicitados</caption>\r\n");
        out.write("\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t<th align=\"right\" width=\"10px\"><b>#</b></th>\r\n");
        out.write("\t\t\t\t\t<th align=\"center\" width=\"75px\"><b>Fecha</b></th>\r\n");
        out.write("\t\t\t\t\t<th align=\"center\" width=\"75px\"><b>Valor</b></th>\r\n");
        out.write("\t\t\t\t\t<th align=\"center\"><b>Descripción</b></th>\r\n");
        out.write("\t\t\t\t\t<th align=\"center\" width=\"100px\"><b>Estado</b></th>\r\n");
        out.write("\t\t\t\t\t<th align=\"center\" width=\"100px\"><b>Ver</b></th>\r\n");
        out.write("\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t<tr>\r\n");
        out.write("\t\t\t\t\t\t<td width=\"10px\" class=\"listas\">");
        if (_jspx_meth_c_005fout_005f3(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("</td>\r\n");
        out.write("\t\t\t\t\t\t<td width=\"75px\" class=\"listas\">");
        if (_jspx_meth_c_005fout_005f4(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("</td>\r\n");
        out.write("\t\t\t\t\t\t<td width=\"75px\" class=\"listas\" align=\"right\">");
        if (_jspx_meth_c_005fout_005f5(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("</td>\r\n");
        out.write("\t\t\t\t\t\t<td class=\"listas\">");
        if (_jspx_meth_c_005fout_005f6(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("</td>\r\n");
        out.write("\t\t\t\t\t\t<td class=\"listas\" width=\"100px\">");
        if (_jspx_meth_c_005fout_005f7(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("</td>\r\n");
        out.write("\t\t\t\t\t\t<td><img src='");
        if (_jspx_meth_c_005furl_005f11(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("' onclick=\"infoAdicional()\"></td>\r\n");
        out.write("\t\t\t\t\t</tr>\r\n");
        out.write("\t\t\t\t");
        if (_jspx_meth_c_005fforEach_005f0(_jspx_th_c_005fif_005f0, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t\t</table>\r\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f3 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /grupos/proyectos/ListaGastos.jsp(544,38) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f3.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${st.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f3 = _jspx_th_c_005fout_005f3.doStartTag();
    if (_jspx_th_c_005fout_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f4 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /grupos/proyectos/ListaGastos.jsp(545,38) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f4.setValue(new String("hoy"));
    int _jspx_eval_c_005fout_005f4 = _jspx_th_c_005fout_005f4.doStartTag();
    if (_jspx_th_c_005fout_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f5(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f5 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f5.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /grupos/proyectos/ListaGastos.jsp(546,52) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f5.setValue(new String("mucho dinero"));
    int _jspx_eval_c_005fout_005f5 = _jspx_th_c_005fout_005f5.doStartTag();
    if (_jspx_th_c_005fout_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f5);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f6(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f6 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f6.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f6.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /grupos/proyectos/ListaGastos.jsp(547,25) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f6.setValue(new String("sin descripcion"));
    int _jspx_eval_c_005fout_005f6 = _jspx_th_c_005fout_005f6.doStartTag();
    if (_jspx_th_c_005fout_005f6.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f6);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f7(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f7 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f7.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f7.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /grupos/proyectos/ListaGastos.jsp(548,39) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f7.setValue(new String("activo"));
    int _jspx_eval_c_005fout_005f7 = _jspx_th_c_005fout_005f7.doStartTag();
    if (_jspx_th_c_005fout_005f7.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f7);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f7);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f11 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /grupos/proyectos/ListaGastos.jsp(549,20) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f11.setValue("/comp/img/find.gif");
    int _jspx_eval_c_005furl_005f11 = _jspx_th_c_005furl_005f11.doStartTag();
    if (_jspx_th_c_005furl_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f11);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f0, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f0);
    // /grupos/proyectos/ListaGastos.jsp(551,4) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setBegin(0);
    // /grupos/proyectos/ListaGastos.jsp(551,4) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new String(""));
    // /grupos/proyectos/ListaGastos.jsp(551,4) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("lista");
    // /grupos/proyectos/ListaGastos.jsp(551,4) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("st");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t\t\t\t<tr>\r\n");
          out.write("\t\t\t\t\t\t<td width=\"10px\" class=\"listas\">");
          if (_jspx_meth_c_005fout_005f8(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t<td width=\"75px\" class=\"listas\">");
          if (_jspx_meth_c_005fout_005f9(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t<td width=\"75px\" class=\"listas\" align=\"right\">");
          if (_jspx_meth_c_005fout_005f10(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t<td class=\"listas\">");
          if (_jspx_meth_c_005fout_005f11(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t<td class=\"listas\" width=\"100px\">");
          if (_jspx_meth_c_005fout_005f12(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t\t\t\t<td><a href='");
          if (_jspx_meth_c_005furl_005f12(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("' target=\"_parent\" rel=\"lyteframe\" title=\"Información General\" rev=\"width: 700px; height: 400px; scrolling: auto;\"><img border=\"0\" src='");
          if (_jspx_meth_c_005furl_005f13(_jspx_th_c_005fforEach_005f0, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f0))
            return true;
          out.write("'></a></td>\r\n");
          out.write("\t\t\t\t\t</tr>\r\n");
          out.write("\t\t\t\t");
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
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems_005fbegin.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f8(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f8 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f8.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f8.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /grupos/proyectos/ListaGastos.jsp(553,38) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f8.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${st.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f8 = _jspx_th_c_005fout_005f8.doStartTag();
    if (_jspx_th_c_005fout_005f8.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f8);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f8);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f9(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f9 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f9.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f9.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /grupos/proyectos/ListaGastos.jsp(554,38) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f9.setValue(new String(""));
    int _jspx_eval_c_005fout_005f9 = _jspx_th_c_005fout_005f9.doStartTag();
    if (_jspx_th_c_005fout_005f9.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f9);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f9);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f10(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f10 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f10.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f10.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /grupos/proyectos/ListaGastos.jsp(555,52) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f10.setValue(new String(""));
    int _jspx_eval_c_005fout_005f10 = _jspx_th_c_005fout_005f10.doStartTag();
    if (_jspx_th_c_005fout_005f10.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f10);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f10);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f11(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f11 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f11.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f11.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /grupos/proyectos/ListaGastos.jsp(556,25) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f11.setValue(new String(""));
    int _jspx_eval_c_005fout_005f11 = _jspx_th_c_005fout_005f11.doStartTag();
    if (_jspx_th_c_005fout_005f11.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f11);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f11);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f12 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f12.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /grupos/proyectos/ListaGastos.jsp(557,39) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f12.setValue(new String(""));
    int _jspx_eval_c_005fout_005f12 = _jspx_th_c_005fout_005f12.doStartTag();
    if (_jspx_th_c_005fout_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f12);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f12(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f12 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f12.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f12.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /grupos/proyectos/ListaGastos.jsp(558,19) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f12.setValue("/GestionProyectos/ProyectosInvestigador.x?accion=27");
    int _jspx_eval_c_005furl_005f12 = _jspx_th_c_005furl_005f12.doStartTag();
    if (_jspx_th_c_005furl_005f12.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f12);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f12);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f0, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f0)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f13 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f13.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f0);
    // /grupos/proyectos/ListaGastos.jsp(558,223) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f13.setValue("/comp/img/find.gif");
    int _jspx_eval_c_005furl_005f13 = _jspx_th_c_005furl_005f13.doStartTag();
    if (_jspx_th_c_005furl_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f13);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f1(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f1 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f1.setParent(null);
    // /grupos/proyectos/ListaGastos.jsp(563,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f1.setTest(false);
    int _jspx_eval_c_005fif_005f1 = _jspx_th_c_005fif_005f1.doStartTag();
    if (_jspx_eval_c_005fif_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t\t\t<h4 align=\"center\">No hay solicitudes registradas para este rubro</h4>\r\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f1.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f1.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f1);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f2(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f2 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f2.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f2.setParent(null);
    // /grupos/proyectos/ListaGastos.jsp(569,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f2.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${!empty sessionScope.listaGastosRubro}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f2 = _jspx_th_c_005fif_005f2.doStartTag();
    if (_jspx_eval_c_005fif_005f2 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("        <table align=\"center\" class=\"tablas\" width=\"95%\" >\r\n");
        out.write("        <caption >Listado de Gastos Rubro</caption>\r\n");
        out.write("        <tr>\r\n");
        out.write("\r\n");
        out.write("\t       \t<th align=\"right\" width=\"10px\"><b>#</b></th>\r\n");
        out.write("\t       \t<th align=\"right\" width=\"10px\"><b>..</b></th>\r\n");
        out.write("        \t<th align=\"center\" width=\"75px\"><b>Fecha</b></th>\r\n");
        out.write("        \t<th align=\"center\" width=\"75px\"><b>Valor</b></th>\r\n");
        out.write("        \t<th align=\"center\"><b>Descripción</b></th>\r\n");
        out.write("        \t<th align=\"center\" width=\"100px\"><b>Observacion</b></th>\r\n");
        out.write("        </tr>\r\n");
        out.write("\t");
        if (_jspx_meth_c_005fforEach_005f1(_jspx_th_c_005fif_005f2, _jspx_page_context))
          return true;
        out.write("\r\n");
        out.write("\t\t</table>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f2.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f2.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f2);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f1(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f2, PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f1 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems_005fbegin.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f1.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f1.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f2);
    // /grupos/proyectos/ListaGastos.jsp(581,1) name = begin type = int reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setBegin(0);
    // /grupos/proyectos/ListaGastos.jsp(581,1) name = items type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${sessionScope.listaGastosRubro}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    // /grupos/proyectos/ListaGastos.jsp(581,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVar("lista");
    // /grupos/proyectos/ListaGastos.jsp(581,1) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f1.setVarStatus("st");
    int[] _jspx_push_body_count_c_005fforEach_005f1 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f1 = _jspx_th_c_005fforEach_005f1.doStartTag();
      if (_jspx_eval_c_005fforEach_005f1 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t<tr>\r\n");
          out.write("\t\t\t<td width=\"10px\" class=\"listas\">");
          if (_jspx_meth_c_005fout_005f13(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t<td width=\"10px\">\r\n");
          out.write("\t\t\t\t");
          if (_jspx_meth_c_005fif_005f3(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("\t\t\t\t");
          if (_jspx_meth_c_005fif_005f4(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("\r\n");
          out.write("\t\t\t</td>\r\n");
          out.write("\t\t\t<td width=\"75px\" class=\"listas\">");
          if (_jspx_meth_c_005fout_005f14(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t<td width=\"75px\" class=\"listas\" align=\"right\">");
          if (_jspx_meth_c_005fout_005f15(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t<td class=\"listas\">");
          if (_jspx_meth_c_005fout_005f16(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t\t<td class=\"listas\" width=\"100px\">");
          if (_jspx_meth_c_005fout_005f17(_jspx_th_c_005fforEach_005f1, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
            return true;
          out.write("</td>\r\n");
          out.write("\t\t</tr>\r\n");
          out.write("\t");
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
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems_005fbegin.reuse(_jspx_th_c_005fforEach_005f1);
    }
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f13(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f13 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f13.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f13.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /grupos/proyectos/ListaGastos.jsp(583,35) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f13.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${st.count}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f13 = _jspx_th_c_005fout_005f13.doStartTag();
    if (_jspx_th_c_005fout_005f13.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f13);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f13);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f3(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f3 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f3.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f3.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /grupos/proyectos/ListaGastos.jsp(585,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f3.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lista.tipoGasto==1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f3 = _jspx_th_c_005fif_005f3.doStartTag();
    if (_jspx_eval_c_005fif_005f3 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<img src='");
        if (_jspx_meth_c_005furl_005f14(_jspx_th_c_005fif_005f3, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write('\'');
        out.write('>');
        int evalDoAfterBody = _jspx_th_c_005fif_005f3.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f3.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f3);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f3, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f14 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f14.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f3);
    // /grupos/proyectos/ListaGastos.jsp(585,49) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f14.setValue("/comp/img/flag0.gif");
    int _jspx_eval_c_005furl_005f14 = _jspx_th_c_005furl_005f14.doStartTag();
    if (_jspx_th_c_005furl_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f14);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f4(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f4 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f4.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f4.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /grupos/proyectos/ListaGastos.jsp(586,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f4.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lista.tipoGasto==-1}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f4 = _jspx_th_c_005fif_005f4.doStartTag();
    if (_jspx_eval_c_005fif_005f4 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("<img src='");
        if (_jspx_meth_c_005furl_005f15(_jspx_th_c_005fif_005f4, _jspx_page_context, _jspx_push_body_count_c_005fforEach_005f1))
          return true;
        out.write('\'');
        out.write('>');
        int evalDoAfterBody = _jspx_th_c_005fif_005f4.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f4.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f4);
    return false;
  }

  private boolean _jspx_meth_c_005furl_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fif_005f4, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f15 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    _jspx_th_c_005furl_005f15.setPageContext(_jspx_page_context);
    _jspx_th_c_005furl_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fif_005f4);
    // /grupos/proyectos/ListaGastos.jsp(586,50) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005furl_005f15.setValue("/comp/img/flag1.gif");
    int _jspx_eval_c_005furl_005f15 = _jspx_th_c_005furl_005f15.doStartTag();
    if (_jspx_th_c_005furl_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f15);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f14(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f14 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f14.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f14.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /grupos/proyectos/ListaGastos.jsp(588,35) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f14.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lista.fecha}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f14 = _jspx_th_c_005fout_005f14.doStartTag();
    if (_jspx_th_c_005fout_005f14.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f14);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f14);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f15(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f15 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f15.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f15.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /grupos/proyectos/ListaGastos.jsp(589,49) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f15.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lista.valorGasto}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f15 = _jspx_th_c_005fout_005f15.doStartTag();
    if (_jspx_th_c_005fout_005f15.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f15);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f15);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f16(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f16 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f16.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f16.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /grupos/proyectos/ListaGastos.jsp(590,22) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f16.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lista.descripcion}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f16 = _jspx_th_c_005fout_005f16.doStartTag();
    if (_jspx_th_c_005fout_005f16.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f16);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f16);
    return false;
  }

  private boolean _jspx_meth_c_005fout_005f17(javax.servlet.jsp.tagext.JspTag _jspx_th_c_005fforEach_005f1, PageContext _jspx_page_context, int[] _jspx_push_body_count_c_005fforEach_005f1)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:out
    org.apache.taglibs.standard.tag.rt.core.OutTag _jspx_th_c_005fout_005f17 = (org.apache.taglibs.standard.tag.rt.core.OutTag) _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.OutTag.class);
    _jspx_th_c_005fout_005f17.setPageContext(_jspx_page_context);
    _jspx_th_c_005fout_005f17.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_c_005fforEach_005f1);
    // /grupos/proyectos/ListaGastos.jsp(591,36) name = value type = null reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fout_005f17.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${lista.observaciones}", java.lang.Object.class, (PageContext)_jspx_page_context, null, false));
    int _jspx_eval_c_005fout_005f17 = _jspx_th_c_005fout_005f17.doStartTag();
    if (_jspx_th_c_005fout_005f17.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f17);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fout_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005fout_005f17);
    return false;
  }

  private boolean _jspx_meth_c_005fif_005f5(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f5 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f5.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f5.setParent(null);
    // /grupos/proyectos/ListaGastos.jsp(596,1) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f5.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${empty sessionScope.listaGastosRubro}", java.lang.Boolean.class, (PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f5 = _jspx_th_c_005fif_005f5.doStartTag();
    if (_jspx_eval_c_005fif_005f5 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\r\n");
        out.write("\t<h4 align=\"center\">No hay gastos registrados para este rubro </h4>\r\n");
        out.write("\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f5.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f5.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f5);
    return false;
  }
}
