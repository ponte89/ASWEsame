<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Pizzeria Interattiva</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>

    </head>

    <body onLoad="inizializzaDati()">
        <section class="container">

            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   

            <div class="content">
                <h1>Il menu tradizionale</h1>

                <%  if ((session.getAttribute("login") != null)
                            && (session.getAttribute("type").toString().equals("admin"))) {
                %>

                <div id="applet" align="center" style="margin:5px;">

                    <applet code="asw1021.AdminUpdateMenu"
                            codebase="../applet/" 
                            archive="Applet2.jar, Lib1.jar"
                            width="450"
                            height="325">
                        Applet failed to run. No Java plug-in was found.
                    </applet>

                </div>

                <%
                } else {
                %>  

                <table>
                    <tr>
                        <td><table id="pizze" border="3" frame="border"></table></td>

                        <td><table id="condimenti" border="3" frame="border"></table></td>
                    </tr>
                </table>
                <%
                    }
                %>        

            </div>

        </section>

        <script  src="./../js/functions.js"></script>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %> 
    </body>
</html>