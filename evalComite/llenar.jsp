<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%><%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<jsp:useBean id="criterioValor" class="cidc.evalMovilidad.obj.Criterio" scope="session"/>
<jsp:setProperty name="criterioValor" property="*"/>
<c:import url="/movilidad/evalMovilidad.x"/>