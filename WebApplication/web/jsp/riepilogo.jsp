<%-- 
    Document   : riepilogo
    Created on : Jan 11, 2015, 9:35:55 PM
    Author     : Alessia
--%>

<%@page import="java.io.InputStream"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Pizzeria Interattiva</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
         <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>

    <body onLoad="getOrdini(riepilogo)">

        <section class="container">

            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   
            <div class="content">
            <h1>Riepilogo ordini</h1> 
            <table id="riepilogo" border="1" frame="border">
            <% if (login != null) { 
                String user = (String) session.getAttribute("login");
                String us = session.getAttribute("type").toString();
                
                    
                if (us.equals("utente")) {
                    InputStream is = getServletContext().getResourceAsStream("/WEB-INF/xml/ordini_test.xml");
                    ManageXML manageXml = new ManageXML();
                    Document doc = manageXml.parse(is);
                    Element root = doc.getDocumentElement();
                    NodeList ordini= doc.getElementsByTagName("ordine_utente");
                    Element ordine;
                    String ordineLog = "";
                    
                    for (int i = 0; i < ordini.getLength(); i++) {
                            ordine = (Element)ordini.item(i);
                           if(ordine.getElementsByTagName("user").item(0).getTextContent().equals(user)){
                               //generare string ordine
                             %>
                             <tr><td><%=ordineLog%></td></tr>
                             
                             <%
                           }
                    }
                 %>
                 
                

                 <%
                
            %>
           
            </table> 
            </div>
            
            <%
                } 
            }else {%>
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
