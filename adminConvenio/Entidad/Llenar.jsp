<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="entidades" class="cidc.adminEntidad.obj.EntidadOBJ" scope="session"/>
<jsp:setProperty name="entidades" property="*"/>
<c:import url="/adminEntidad/GestEntidad.x"/>