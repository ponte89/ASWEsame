<%@page import="java.util.LinkedList"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione Prenotazioni</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body onLoad="getOrdini('prenotazioni');getMessages('prenotazioni')">

        <section class="container">
            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>  
            <%
            if (session.getAttribute("login") != null) {
                String user = (String) session.getAttribute("login");
                String us = session.getAttribute("type").toString();
                if (us.equals("admin")) {  //admin
                    HashMap<String, Object> contexts = (HashMap<String, Object>) application.getAttribute("loginList");
                    contexts.put(user, new LinkedList<Document>());
        %>  
        
        <div class="content">
                <h1>Prenotazioni: </h1>
                <table id="riepilogo_prenotazioni" border="2" style="display:none;"></table>
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