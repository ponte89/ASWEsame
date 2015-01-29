/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var datiPizze;
var datiCondimenti;
var datiMessaggi;
var tableOrdini = "";
var tablePrenotazioni = "";
var ordini = "ordini";
var prenotazioni = "prenotazioni";

function inizializzaDati() {
    getDati("pizze");
    getDati("condimenti");
}

function getDati(value) {
    if (value === "pizze") {
        var xmlhttp2 = new XMLHttpRequest();
        var to = "http://localhost:8080/WebApplication/MenuServlet";

        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                var answer = xmlhttp2.responseXML;
                stampaDati(answer, "pizze");
            }
        };

        xmlhttp2.open("POST", to, true);
        xmlhttp2.setRequestHeader("Content-Type", "text/xml");

        creaRichiestaDati("pizze");

        xmlhttp2.send(datiPizze);
    } else if (value === "condimenti") {
        var xmlhttp2 = new XMLHttpRequest();
        var to = "http://localhost:8080/WebApplication/MenuServlet";

        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                var answer = xmlhttp2.responseXML;
                stampaDati(answer, "condimenti");
            }
        };

        xmlhttp2.open("POST", to, true);
        xmlhttp2.setRequestHeader("Content-Type", "text/xml");

        creaRichiestaDati("condimenti");

        xmlhttp2.send(datiCondimenti);
    }else if(value === "messaggi"){
        var xmlhttp2 = new XMLHttpRequest();
        var to = "http://localhost:8080/WebApplication/MessageServlet?target=manage";

        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                var answer = xmlhttp2.responseXML;
                
                //console.log(answer);
                
                stampaDati(answer, "messaggi");
            }
        };

        xmlhttp2.open("POST", to, true);
        xmlhttp2.setRequestHeader("Content-Type", "text/xml");

        //creaRichiestaDati("condimenti");
        datiMessaggi = document.implementation.createDocument("", "", null);
        var rootData = datiMessaggi.createElement("tipo");
        var text = datiMessaggi.createTextNode("text");
        text.nodeValue = "messaggi";
        rootData.appendChild(text);
        datiMessaggi.appendChild(rootData);


        xmlhttp2.send(datiMessaggi);
    }
}

function creaRichiestaDati(value) {
    if (value === "pizze") {
        datiPizze = document.implementation.createDocument("", "", null);
        var rootData = datiPizze.createElement("tipo");
        var text = datiPizze.createTextNode("text");
        text.nodeValue = "pizze";
        rootData.appendChild(text);
        datiPizze.appendChild(rootData);
    } else if (value === "condimenti") {
        datiCondimenti = document.implementation.createDocument("", "", null);
        var rootData = datiCondimenti.createElement("tipo");
        var text = datiCondimenti.createTextNode("text");
        text.nodeValue = "condimenti";
        rootData.appendChild(text);
        datiCondimenti.appendChild(rootData);
    }
}

function stampaDati(data, value) {
    if (value === "pizze") {
        var con = document.getElementById("pizze");
        
        //var dati = data.documentElement;
        var pizze = data.getElementsByTagName("nome");
        
        
        var prova = data.getElementsByTagName("pizza_standard");
        console.log("dimensione pizza standard: "+prova.length);
        
        
        var sopra = "<caption align='attributo' style='font-size:36px'>Le nostre Pizze</caption>";
        var table = "";

        for (i = 0; i < pizze.length; i++) {
            var nome = pizze[i].childNodes[0].nodeValue;

            table += "<tr><td width=25px align='center'>" + (i + 1) + "</td><td width=300>&nbsp;&nbsp;Pizza " + nome + "</td></tr>";

        }

        con.innerHTML = sopra + table;
    } else if (value === "condimenti") {
        var con = document.getElementById("condimenti");
        var dati = data.documentElement;
        var pizze = dati.getElementsByTagName("nome");
        var sopra = "<caption align='attributo' style='font-size:36px'>I nostri condimenti</caption>";
        var table = "";

        for (i = 0; i < pizze.length; i++) {

            var nome = pizze[i].childNodes[0].nodeValue;
            if (nome != "Nessuna selezione")
                table += "<tr><td width=25px align='center'>" + (i) + "</td><td width=400>&nbsp;&nbsp;" + nome + "</td></tr>";

        }

        con.innerHTML = sopra + table;
    }else if (value === "messaggi") {
        
        var con = document.getElementById("tabMessaggi");
        
        var dati = data.documentElement;
        var nome = dati.getElementsByTagName("nome");
        var messaggio = dati.getElementsByTagName("contenuto");
        var sopra = "<caption align='attributo' style='font-size:36px; margin:10px;'>Messaggi Utenti</caption>";
        var table = "<tr><th>Utente</th><th>Messaggio</th></tr>";

        for (i = 0; i < messaggio.length; i++) {

            var name = nome[i].childNodes[0].nodeValue;
            var mess = messaggio[i].childNodes[0].nodeValue;
            table += "<tr><td width=100px align='center'>"+name+"</td><td width=400>&nbsp;&nbsp;" + mess + "</td></tr>";

        }

        con.innerHTML = sopra + table;
    }
}

//GESTIONE ORDINI e PRENOTAZIONI
function getMessages(value){      
                var xmlhttp2,answer,data;
                console.log("Richiesta ordini");
                xmlhttp2 = new XMLHttpRequest();
                xmlhttp2.open("POST", "../ManageOrderService", true);
                xmlhttp2.setRequestHeader("Content-Type", "text/xml");
                xmlhttp2.onreadystatechange=function(){
                    if (xmlhttp2.readyState === 4 && xmlhttp2.status===200) {   
                        answer = xmlhttp2.responseXML;
                        if(answer.documentElement.tagName === "ordini_utente"){
                            if(value === "ordini"){
                                stampaOrdini(answer, "ordini");   
                            }else{
                                stampaOrdini(answer, "prenotazioni");   
                            }
                        }
                        getMessages();
                    }
                };
            data = document.implementation.createDocument("", "pop", null);                     
            xmlhttp2.send(data);
}

function stampaOrdini(data, value){
    if (value === "ordini"){
        var ordiniElement = document.getElementById("riepilgo_ordini");
        var dati = data.documentElement;
        var ordini = dati.getElementsByTagName("ordine_utente");
        var done, doneLog, nOrdine, idLog;
        
        for(k = 0; k < ordini.length; k++){
                nOrdine = k;
                var prenotazioneLog = "";
                var user = ordini[k].getElementsByTagName("user");
                var userLog = user[0].childNodes[0].nodeValue;
                var id = ordini[k].getElementsByTagName("id");
                idLog = id[0].childNodes[0].nodeValue;
                var tipo_ordine = ordini[k].getElementsByTagName("tipo_ordine");
                var tipo_ordineLog = tipo_ordine[0].childNodes[0].nodeValue;
                if (tipo_ordineLog === null){
                    var prenotazione = tipo_ordine[0].getElementsByTagName("prenotazione");
                    prenotazioneLog = prenotazione[0].childNodes[0].nodeValue;
                    var nPosti = tipo_ordine[0].getElementsByTagName("posti");
                    var nPostiLog = nPosti[0].childNodes[0].nodeValue;
                }
                done = ordini[k].getElementsByTagName("done");
                doneLog = done[0].childNodes[0].nodeValue;
                if(doneLog === "false"){
                    doneLog = "da fare";
                }else{
                    doneLog = "fatto";
                }
                if(prenotazioneLog === "prenotazione"){
                  tableOrdini += "<tr><td><b>Utente: </b>" + userLog + " <b>IdOrdine: </b>" + idLog + " <b>Prenotazione per: </b>" + nPostiLog + "</br>";    
                }else if(prenotazioneLog === ""){
                  tableOrdini += "<tr><td><b>Utente: </b>" + userLog + " <b>IdOrdine: </b>" + idLog + " <b>Consegna: </b>" + tipo_ordineLog + " </br>";  
                }


                var pizzeS = ordini[k].getElementsByTagName("pizzaS");
                var pizzeP = ordini[k].getElementsByTagName("pizzaP");
                var pizza, numeroLog, plusLog, nomeLog, baseLog;
                var condimentiLog = "";
                for (i = 0; i < pizzeS.length; i++) {

                    var nome = pizzeS[i].getElementsByTagName("nome_pizza");
                    nomeLog = nome[0].childNodes[0].nodeValue;
                    var numero = pizzeS[i].getElementsByTagName("numero_pizze");
                    numeroLog = numero[0].childNodes[0].nodeValue;
                    var plus = pizzeS[i].getElementsByTagName("plus");
                    plusLog = plus[0].childNodes[0].nodeValue;

                    tableOrdini += "<b> Pizza: </b>" + nomeLog + "<b> Numero: </b>" + numeroLog + "<b> Aggiunte: </b>" + plusLog + " </br>";
                }

                for (i = 0; i < pizzeP.length; i++) {

                    var nome = pizzeP[i].getElementsByTagName("nome_pizza");
                    nomeLog = nome[0].childNodes[0].nodeValue;
                    var numero = pizzeP[i].getElementsByTagName("numero_pizze");
                    numeroLog = numero[0].childNodes[0].nodeValue;
                    var plus = pizzeP[i].getElementsByTagName("plus");
                    plusLog = plus[0].childNodes[0].nodeValue;
                    var base = pizzeP[i].getElementsByTagName("base");
                    baseLog = base[0].childNodes[0].nodeValue;
                    var condimenti = pizzeP[i].getElementsByTagName("condimento");
                    for(j = 0; j < condimenti.length; j++){
                        if(j === condimenti.length - 1){
                           condimentiLog += condimenti[j].childNodes[0].nodeValue + " ";
                        }else{
                           condimentiLog += " " + condimenti[j].childNodes[0].nodeValue + ", ";
                        }
                    }

                    tableOrdini += "<b> Pizza: </b>" + nomeLog + "<b> Numero: </b>" + numeroLog + "<b> Aggiunte: </b>" + plusLog + "<b> Base: </b>" + baseLog + "<b> Condimenti: </b>" + condimentiLog +" </br></td>";
                }
         }
    ordiniElement.innerHTML = tableOrdini + "<td width=50px align='center'><b>Stato: </b>" + "<input id='"+ nOrdine +"' type='text' size='10' value='"+ doneLog + "'>" + "</br><input type='button' value='Completato' onclick='ordineCompletato('"+nOrdine+"','"+idLog+"');'></td></tr>"; 
    }else if(value === "prenotazioni"){
        var prenotazioniElement = document.getElementById("riepilogo_prenotazioni");
        var dati = data.documentElement;
        var ordini = dati.getElementsByTagName("ordine_utente");

        for(k = 0; k < ordini.length; k++){
            var tipo_ordine = ordini[k].getElementsByTagName("tipo_ordine");
            var tipo_ordineLog = tipo_ordine[0].childNodes[0].nodeValue;
            
            if (tipo_ordineLog === null){
                var prenotazioneLog = "";
                var user = ordini[k].getElementsByTagName("user");
                var userLog = user[0].childNodes[0].nodeValue;
                var id = ordini[k].getElementsByTagName("id");
                var idLog = id[0].childNodes[0].nodeValue;
                if (tipo_ordineLog === null){
                    var prenotazione = tipo_ordine[0].getElementsByTagName("prenotazione");
                    prenotazioneLog = prenotazione[0].childNodes[0].nodeValue;
                    var nPosti = tipo_ordine[0].getElementsByTagName("posti");
                    var nPostiLog = nPosti[0].childNodes[0].nodeValue;
                }
                var done = ordini[k].getElementsByTagName("done");
                var doneLog = done[0].childNodes[0].nodeValue;
                if(doneLog === "false"){
                    doneLog = "da fare";
                }else{
                    doneLog = "fatto";
                }
                if(prenotazioneLog === "prenotazione"){
                  tablePrenotazioni += "<tr><td><b>Utente: </b>" + userLog + " <b>IdOrdine: </b>" + idLog + " <b>Prenotazione per: </b>" + nPostiLog + " <b>Stato: </b>" + doneLog + " </br>";    
                }else if(prenotazioneLog === ""){
                  tablePrenotazioni += "<tr><td><b>Utente: </b>" + userLog + " <b>IdOrdine: </b>" + idLog + " <b>Consegna: </b>" + tipo_ordineLog + " <b>Stato: </b>" + doneLog + " </br>";  
                }


                var pizzeS = ordini[k].getElementsByTagName("pizzaS");
                var pizzeP = ordini[k].getElementsByTagName("pizzaP");
                var pizza, numeroLog, plusLog, nomeLog, baseLog;
                var condimentiLog = "";
                for (i = 0; i < pizzeS.length; i++) {

                    var nome = pizzeS[i].getElementsByTagName("nome_pizza");
                    nomeLog = nome[0].childNodes[0].nodeValue;
                    var numero = pizzeS[i].getElementsByTagName("numero_pizze");
                    numeroLog = numero[0].childNodes[0].nodeValue;
                    var plus = pizzeS[i].getElementsByTagName("plus");
                    plusLog = plus[0].childNodes[0].nodeValue;

                    tablePrenotazioni += "<b> Pizza: </b>" + nomeLog + "<b> Numero: </b>" + numeroLog + "<b> Aggiunte: </b>" + plusLog + " </br>";
                }

                for (i = 0; i < pizzeP.length; i++) {

                    var nome = pizzeP[i].getElementsByTagName("nome_pizza");
                    nomeLog = nome[0].childNodes[0].nodeValue;
                    var numero = pizzeP[i].getElementsByTagName("numero_pizze");
                    numeroLog = numero[0].childNodes[0].nodeValue;
                    var plus = pizzeP[i].getElementsByTagName("plus");
                    plusLog = plus[0].childNodes[0].nodeValue;
                    var base = pizzeP[i].getElementsByTagName("base");
                    baseLog = base[0].childNodes[0].nodeValue;
                    var condimenti = pizzeP[i].getElementsByTagName("condimento");
                    for(j = 0; j < condimenti.length; j++){
                        if(j === condimenti.length - 1){
                           condimentiLog += condimenti[j].childNodes[0].nodeValue + " ";
                        }else{
                           condimentiLog += " " + condimenti[j].childNodes[0].nodeValue + ", ";
                        }
                    }

                    tablePrenotazioni += "<b> Pizza: </b>" + nomeLog + "<b> Numero: </b>" + numeroLog + "<b> Aggiunte: </b>" + plusLog + "<b> Base: </b>" + baseLog + "<b> Condimenti: </b>" + condimentiLog +" </br></td></tr>";
                }
         }
     }
    prenotazioniElement.innerHTML = tablePrenotazioni; 
    }

}

function ordineCompletato(nOrdine, idOrdine){
    document.getElementById(nOrdine).value = 'fatto';
    
}

function getOrdini(value){
        var xmlhttp2 = new XMLHttpRequest();
        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                var answer = xmlhttp2.responseXML;
                if(value === "ordini"){
                    stampaOrdini(answer, "ordini");   
                }else{
                    stampaOrdini(answer, "prenotazioni");   
                }
                console.log("Stampo gli ordini del DB");
            }
        };
        xmlhttp2.open("POST", "../ManageOrderService", true);
        xmlhttp2.setRequestHeader("Content-Type", "text/xml");

        dataOrdini = document.implementation.createDocument("", "getOrdini", null);
        xmlhttp2.send(dataOrdini);
}

function getMessaggi(){
    
    //alert("messaggi");
    
    getDati("messaggi");
}

function ciao(){
    console.log("funziona");
    
    
    //id della 
}

function getNews(){      
                var xmlhttp2,answer,data;
                console.log("Richiesta news");
                xmlhttp2 = new XMLHttpRequest();
                xmlhttp2.open("POST", "../ManageNewsService", true);
                xmlhttp2.setRequestHeader("Content-Type", "text/xml");
                xmlhttp2.onreadystatechange=function(){
                    if (xmlhttp2.readyState === 4 && xmlhttp2.status===200) {   
                        answer = xmlhttp2.responseXML;
                        
                        /*
                        if(answer.documentElement.tagName === "ordini_utente"){
                            if(value === "ordini"){
                                stampaOrdini(answer, "ordini");   
                            }else{
                                stampaOrdini(answer, "prenotazioni");   
                            }
                        }*/
                        
                        
                        
                        getNews();
                        
                        
                    }
                };
                
            data = document.implementation.createDocument("", "pop", null);                     
            xmlhttp2.send(data);
}