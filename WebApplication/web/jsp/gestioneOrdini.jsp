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
    <body>
        
        <script>
            var i = 0;            
            var contentElement = document.getElementById("content");
            var timeoutElement = document.getElementById("timeout");                            
            var msg = "";
            var xmlhttp2,answer,to,data;
            function getMessages(){
                console.log("ancora!");
                xmlhttp2 = new XMLHttpRequest();
                xmlhttp2.open("POST", "../OrderServlet", true);
                xmlhttp2.onreadystatechange=function(){
                //if (xmlhttp2.readyState == 4 && xmlhttp2.status==200) {                            
                    answer = xmlhttp2.responseXML;
                    //if (answer.documentElement.tagName == "push") {
                        console.log("Cerco di leggere i dati");
                        msg= answer.documentElement.childNodes.item(0).data;
                        document.getElementById("content").value = msg; 
                    //}
                    getMessages();
                //}
               };
            data = document.implementation.createDocument("", "pop", null);                     
            xmlhttp2.send(data);
            }
        </script>
        <div><input type="button" onClick="getMessages();" value="SEND" /></div>
        <div><input type="text" id="content" readonly="readonly"/><span id="timeout"><b>not started</b></span></div>
        <section class="container">
            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   
            <div class="content">
            </div>
        <%
            if (session.getAttribute("login") != null) {
                String user = (String) session.getAttribute("login");
                String us = session.getAttribute("type").toString();
                ArrayList<Ordine> ordini = null;
                if (us.equals("cuoco")) {  //cuoco
                    application = getServletContext();
                    HashMap<String, Object> contexts = (HashMap<String, Object>) application.getAttribute("cookList");
                    contexts.put(user, new LinkedList<Document>());

                }
            }
        %>    
        </section>
        

        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>