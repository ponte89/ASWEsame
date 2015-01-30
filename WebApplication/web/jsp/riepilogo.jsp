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
                    Element ordine, numero_posti;
                    NodeList pizzaS, pizzaP, tipo;
                    String id, done, tipo_ordine, numero, nome, plus, base;
                    String ordineLog = "";
                    
                    for (int i = 0; i < ordini.getLength(); i++) {
                            ordine = (Element)ordini.item(i);
                           if(ordine.getElementsByTagName("user").item(0).getTextContent().equals(user)){
                               id = ordine.getElementsByTagName("id").item(0).getTextContent();
                               done = ordine.getElementsByTagName("done").item(0).getTextContent();
                               if(done.equals("true")){
                                   done = "fatto";
                               }else{
                                   done = "non fatto";
                               }
                               tipo_ordine = ordine.getElementsByTagName("tipo_ordine").item(0).getTextContent();
                               if(!tipo_ordine.equals("ritiro") && !tipo_ordine.equals("asporto") ){
                                  tipo = ordine.getElementsByTagName("tipo_ordine");
                                  numero_posti = (Element)tipo.item(0);
                                  String posti = numero_posti.getElementsByTagName("posti").item(0).getTextContent();
                                  tipo_ordine = " prenotazione <b>Posti: </b>" + posti;
                               }
                               ordineLog += "<b>IdOrdine:</b> " + id + " <b>Cliente:</b> " + user + " <b>Stato: </b>" + done + " <b>Consegna: </b>" + tipo_ordine;
                               pizzaS = ordine.getElementsByTagName("pizzaS");
                               pizzaP = ordine.getElementsByTagName("pizzaP");
                               for(int j = 0; j < pizzaS.getLength(); j++){
                                  Element pizza = (Element) pizzaS.item(j);
                                  nome = pizza.getElementsByTagName("nome_pizza").item(0).getTextContent();
                                  numero = pizza.getElementsByTagName("numero_pizze").item(0).getTextContent();
                                  plus = pizza.getElementsByTagName("plus").item(0).getTextContent();
                                  ordineLog += "<br /> <b>Pizza: </b>" + nome + " <b>Numero: </b>" + numero + " <b>Aggiunte: </b>" + plus;
                               }
                               for(int k = 0; k < pizzaP.getLength(); k++){
                                  Element pizza = (Element) pizzaP.item(k);
                                  nome = pizza.getElementsByTagName("nome_pizza").item(0).getTextContent();
                                  numero = pizza.getElementsByTagName("numero_pizze").item(0).getTextContent();
                                  plus = pizza.getElementsByTagName("plus").item(0).getTextContent();
                                  base = pizza.getElementsByTagName("base").item(0).getTextContent();
                                  ordineLog += "<br /> <b>Pizza: </b>" + nome + " <b>Numero: </b>" + numero + " <b>Aggiunte: </b>" + plus + " <b>Base: </b>" + base + " <b>Condimenti: </b>";
                                  NodeList condimenti = pizza.getElementsByTagName("condimenti");
                                  for(int x = 0; x < condimenti.getLength(); x++){
                                      Element condimento = (Element) condimenti.item(x);
                                      String cond = condimento.getElementsByTagName("condimento").item(0).getTextContent();
                                      ordineLog += " " + cond;
                                  }
                               }
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
