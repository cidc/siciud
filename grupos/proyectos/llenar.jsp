<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="contratacion" class="cidc.proyectos.obj.Contratacion" scope="session"/>
<jsp:setProperty name="contratacion" property="*"/>
<c:import url="/GestionProyectos/ProyectosInvestigador.x"/>
