<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="aporte" class="cidc.convenios.obj.AportesOBJ" scope="session"/>
<jsp:setProperty name="aporte" property="*"/>
<c:import url="/adminConvenio/AdminConvenio.x"/>