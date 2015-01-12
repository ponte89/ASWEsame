<%-- 
    Document   : index
    Created on : Jan 11, 2015, 7:05:01 PM
    Author     : Alessia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pizzeria Interattiva</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>
        <% response.sendRedirect(request.getContextPath()+"/jsp/home.jsp"); %> 
    </body>
</html>
