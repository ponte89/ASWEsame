<%@page import="java.util.LinkedList"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Consegna Pizze</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body onLoad="getOrdini('ordini');getMessages('ordini')">
             
        <section class="container">
            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   
        <%
            if (session.getAttribute("login") != null) {
                String user = (String) session.getAttribute("login");
                String us = session.getAttribute("type").toString();
                if (us.equals("fattorino")) {  //fattorino
                    HashMap<String, Object> contexts = (HashMap<String, Object>) application.getAttribute("loginList");
                    contexts.put(user, new LinkedList<Document>());
        %>  
        
        <div class="content">
                <h1>Ordini: </h1>
                <table id="riepilogo_ordini" border="2" style="display:none;"></table>
        </div>
        </section>
        <%
                }
            }
        %>  
        <script src="./../js/functions.js"></script>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>