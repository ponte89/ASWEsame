<%-- 
    Document   : ordina
    Created on : Jan 11, 2015, 9:35:35 PM
    Author     : Alessia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pizzeria Interattiva</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
        <script language="javascript">
            function addfield() {
                document.getElementById("ordini").innerHTML = document.getElementById("pizze");
            }
        </script>
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>

    <body>

        <section class="container">

            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %> 

            <% if (login != null) {  %>


            <div class="content">
                <h1>Fai la tua ordinazione</h1>

                <div id="applet" align="center" style="margin:10px;">

                    <applet code="asw1021.UserAppletOrder" 
                            codebase="../applet/" 
                            archive="Applet1.jar, Lib1.jar, Lib2.jar"
                            width="750"
                            height="530">
                        Applet failed to run. No Java plug-in was found.
                    </applet>

                </div>
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
    </body>
</html>
