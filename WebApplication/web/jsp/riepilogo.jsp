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
            
            <% if (login != null) {  %>
            
            <div class="content">
                <h1>Riepilogo ordini</h1> 
            </div>
            
            <%} else {%>
            <div class="content">
                <h1>Attenzione!</h1>

                <div id="error" >
                    <p>Non puoi accedere ai servizi di ordinazione, devi essere un utente registrato!</p>
                    <p>Registrati subito e non lasciarti scappare l'occasione di ordinare una pizza
                        nel nuovo ristorante Pizzeria Interattiva</p>

                </div>
            </div>

            <%}%>
            
        </section>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
        <script  src="./../js/functions.js"></script>
    </body>
</html>
