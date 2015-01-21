<%-- 
    Document   : gestioneOrdini
    Created on : 16-gen-2015, 22.48.30
    Author     : Lorenzo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Gestione Ordini</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>
    <body>

        <section class="container">
            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   
            <div class="content">
            </div>
        <%
            if (session.getAttribute("login") != null) {
                String us = session.getAttribute("type").toString();
                
                if (us.equals("admin")) {%>  <!-- admin -->   
                    
                
                <%
                }
            }
        %>    
        </section>
        

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>