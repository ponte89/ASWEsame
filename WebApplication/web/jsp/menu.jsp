<%-- 
    Document   : menu
    Created on : Jan 11, 2015, 9:35:23 PM
    Author     : Alessia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <title>Pizzeria Interattiva</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
        <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>

    <body onload="func()">
        <section class="container">

            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   




            <div class="content">
                <h1>Il menu tradizionale</h1>

                <%            if ((session.getAttribute("login") != null)
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
                <input type="button" onClick="getMessages();" />

                <%
                    }
                %>        

            </div>

        </section>

        <script >
                var xmlhttp2, answer, data;
                function getMessages() {
                    console.log("ancora!");
                    xmlhttp2 = new XMLHttpRequest();
                    
                    var to = "http://localhost:8080/WebApplication/MenuServlet";
                    
                    xmlhttp2.open("POST", to, true);
                    xmlhttp2.onreadystatechange = function () {
                        if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                            answer = xmlhttp2.responseXML;
                            alert("ho risposta");
                        }
                    };
                    data = document.implementation.createDocument("", "pop", null);
                    xmlhttp2.send(data);
                }
           
        </script>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %> 
    </body>
</html>