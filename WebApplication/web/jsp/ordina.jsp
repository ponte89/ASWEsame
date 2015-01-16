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

            <% if(login!=null){  %>
                
            
            <div class="content">
                <h1>Fai la tua ordinazione</h1>
                
                //QUESTA IN REALTA DEVE ESSERE UNA APPLET
                
                <div class="ordini">
                    <form >
                        <input type="radio" name="prenotazione" value="loco" checked>Ritiro in loco<br/><br/>
                        <input type="radio" name="prenotazione" value="prenotazione">Prenotazione tavolo<br/><br/>
                        <input type="radio" name="prenotazione" value="spedizione">Spedizione<br/><br/>


                        <select name="pizze" id="pizze">
                            <option value="margherita">margherita</option>
                            <option value="quattro stagioni">quattro stagioni</option>
                            <option value="quattro formaggi">quattro formaggi</option>
                            <option value="capricciosa">capricciosa</option>
                            <option value="bianca">bianca</option>
                            <option value="marinara">marinara</option>
                        </select>

                        <input type="number" id="number" value="1">
                        
                        <textarea rows="10" cols="30" id="ordini" style="resize:none;"></textarea><br/><br/>

                        <button type="submit" onclick="addfield()" >Aggiungi</button>


                        <button type="submit" >Fatto</button>
                    </form>
                </div>
            </div>
                
            <%}else{%>
                <div class="error">
                    <p>
                    Devi essere loggato per fare un'ordinazione!
                    </p>
                </div>

            <%}%>



        </section>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    </body>
</html>
