<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="pqr" class="cidc.pqr.ws_Bizagi_obj.Pqr" scope="session"/>
<jsp:setProperty name="pqr" property="*"/>
<c:import url="/pqr/servlet.x"/>