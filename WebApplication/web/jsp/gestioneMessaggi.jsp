<%-- 
    Document   : contatti
    Created on : Jan 11, 2015, 9:35:08 PM
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
    <body onload="getMessaggi()">
     <section class="container">
     <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   

            <div class="content">
                <table id="tabContatti">
                    
                </table>
            </div>
        </section>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %> 
    <script  src="./../js/functions.js"></script>
    </body>
</html>
