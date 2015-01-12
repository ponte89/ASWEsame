<%-- 
    Document   : index
    Created on : Jan 11, 2015, 7:05:01 PM
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
                <h1 id="nome">Benvenuti nella nuova Pizzeria Interattiva</h1>

                <p id = "benvenuti">
                    Il concetto di questa nuova pizzeria &egrave; semplice, con pochi passaggi ti registri al sito, 
                    poi potrai subito ordinare le pizze che pi&ugrave; di piacciono!
                    <br/>Ma non solo questo: una volta che sarai un nosto cliente potrai anche divertirti 
                    a crearne di nuove e fantasiose!<br/><br/> Non ti va?Nessun problma scorri il menu del giorno, sar&agrave; 
                    la nostra cucina a consigliarti un piatto il piatto tipico.
                    <br/><br/>Non ti basta ancora? Puoi anche seguire i suggerimenti delle ultime pizze ordinate, 
                    ogni volta sempre nuovi abbinamenti e nuovi ingredienti per rendere le tue pizze sempre piu sfizione.

                    <br/><br/>Per il trasporto? Nessun problema, inserisci l'indirizzo e ti verranno recapitate a casa ancora calde
                    e pronte per essere mangiate!
                    <br/><br/>Vuoi essere tu a passare da noi?  Consulta l'area clienti e scopri quanto manca prima che 
                    le pizze siano pronte!


                    Vuoi prenotare un tavolo? Anche per questo abbiamo la soluzione potrai selezionarlo personalmente da casa!


                    <br/><br/> Che fai ancora li? Vieni a trovarci o registrati, e ordina subito la tua prima pizza da "Pizzeria Interattiva"</p>


                <blockquote> Pizzeria Interattiva, il gusto di una buona pizza a portata di click!  <cite>Il team di Pizzeria Interattiva </cite>

            </div>



        </section>

      <%@ include file="/WEB-INF/jspf/footer.jspf" %>    
    </body>
</html>
