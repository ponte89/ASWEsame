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
    <body>
     <section class="container">
     <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   

            <div class="content">
                <h1>Hai domande? Contattaci subito</h1>
                <table>
                    <tr>
                        <td>

                            <div class="contact">
                                
                                <form action="#" method="post">
                                    <fieldset class="contact">
                                        <label class="contact" for="name">Nome:</label>
                                        <input class="contact" type="text" id="name" placeholder="Inserisci il nome" required/>

                                        <label class = "contact" for="email">Email:</label>
                                        <input class="contact" type="email" id="email" placeholder="Inserisci il tuo indirizzo email" required/>

                                        <label class ="contact" for="message">Messaggio:</label>
                                        <textarea class="contact" id="message" placeholder="Testo del messaggio" required></textarea>

                                        <input class="contact" type="submit" value="Invio" />

                                    </fieldset>
                                </form>
                            </div>

                        </td>

                        <td>
                            <img src="./../multimedia/map.png" class="contact">
                        </td>
                    </tr>
                </table> 
            </div>
        </section>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>  
    </body>
</html>
