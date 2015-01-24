<%-- 
    Document   : gestioneOrdini
    Created on : 16-gen-2015, 22.48.30
    Author     : Lorenzo
--%>

<%@page import="java.util.LinkedList"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="java.util.HashMap"%>
<%@page import="asw1021.ordini.Ordine"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione Ordini</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body onLoad="getMessages()">
             
        <section class="container">
            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   
        <%
            if (session.getAttribute("login") != null) {
                String user = (String) session.getAttribute("login");
                String us = session.getAttribute("type").toString();
                ArrayList<Ordine> ordini = null;
                if (us.equals("cuoco")) {  //cuoco
                    application = getServletContext();
                    HashMap<String, Object> contexts = (HashMap<String, Object>) application.getAttribute("cookList");
                    contexts.put(user, new LinkedList<Document>());
        %>  
        
        <div class="content">
                <h1>Ordini: </h1>
                <table id="riepilgo_ordini" border="1"></table>
        </div>
        </section>
        <%
                }
            }
        %>  
        <script  src="./../js/functions.js"></script>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>