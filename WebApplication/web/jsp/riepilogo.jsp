<%-- 
    Document   : riepilogo
    Created on : Jan 11, 2015, 9:35:55 PM
    Author     : Alessia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pizzeria Interattiva</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
         <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>

    <body>

        <section class="container">

            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   
            
            <div class="content">
                <h1>Riepilogo ordini</h1> 
            </div>
        </section>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
