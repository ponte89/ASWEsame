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
var tableRiepilogo = "";
var riepilogo = "riepilogo";
var color = "";
var value = "";

function inizializzaDati() {
    getDati("pizze");
    getDati("condimenti");
}

function getDati(value) {
    if (value === "pizze") {
        var xmlhttp2 = new XMLHttpRequest();

        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                var answer = xmlhttp2.responseXML;
                stampaDati(answer, "pizze");
            }
        };

        xmlhttp2.open("POST", "../MenuServlet", true);
        xmlhttp2.setRequestHeader("Content-Type", "text/xml");

        creaRichiestaDati("pizze");

        xmlhttp2.send(datiPizze);
    } else if (value === "condimenti") {
        var xmlhttp2 = new XMLHttpRequest();

        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                var answer = xmlhttp2.responseXML;
                stampaDati(answer, "condimenti");
            }
        };

        xmlhttp2.open("POST", "../MenuServlet", true);
        xmlhttp2.setRequestHeader("Content-Type", "text/xml");

        creaRichiestaDati("condimenti");

        xmlhttp2.send(datiCondimenti);
    }else if(value === "messaggi"){
        var xmlhttp2 = new XMLHttpRequest();

        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                var answer = xmlhttp2.responseXML;
                                
                stampaDati(answer, "messaggi");
            }
        };

        xmlhttp2.open("POST", "../MessageServlet?target=manage", true);
        xmlhttp2.setRequestHeader("Content-Type", "text/xml");

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
        
        var pizze = data.getElementsByTagName("nome");
        
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
                table += "<tr><td width=25px align='center'>" + (i+1) + "</td><td width=400>&nbsp;&nbsp;" + nome + "</td></tr>";

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
                            console.log(value);
                            if(value === "ordini"){
                                stampaOrdini(answer, "ordini");   
                            }else if (value === "prenotazioni") {
                                stampaOrdini(answer, "prenotazioni");   
                            }
                        }
                        if(value === "ordini"){
                                getMessages("ordini");  
                        }else if (value === "prenotazioni") {
                                getMessages("prenotazioni");  
                        }
                    }
                };
            data = document.implementation.createDocument("", "pop", null);                     
            xmlhttp2.send(data);
}

function stampaOrdini(data, value){
    if (value === "ordini"){
        var ordiniElement = document.getElementById("riepilogo_ordini");
        ordiniElement.style.display ="table";
        var dati = data.documentElement;
        var ordini = dati.getElementsByTagName("ordine_utente");
        var done, doneLog, nOrdine, idLog;
        
        for(k = 0; k < ordini.length; k++){
                nOrdine = k;
                var user = ordini[k].getElementsByTagName("user");
                var userLog = user[0].childNodes[0].nodeValue;
                var id = ordini[k].getElementsByTagName("id");
                idLog = id[0].childNodes[0].nodeValue;
                var tipo_ordine = ordini[k].getElementsByTagName("tipo_ordine");
                var tipo_ordineLog = tipo_ordine[0].childNodes[0].nodeValue;
                if (tipo_ordineLog === "prenotazione"){
                    var nPosti = ordini[k].getElementsByTagName("posti");
                    var nPostiLog = nPosti[0].childNodes[0].nodeValue;
                }
                done = ordini[k].getElementsByTagName("done");
                doneLog = done[0].childNodes[0].nodeValue;
                if(doneLog === "false"){
                    doneLog = "Attesa";
                    color = "#FF000";
                    value = "button";
                }else{
                    doneLog = "Completato";
                    color = "#00FF00";
                    value = "hidden";
                }
                if(tipo_ordineLog === "prenotazione"){
                  tableOrdini += "<tr><td><b>Utente: </b>" + userLog + " <b>IdOrdine: </b>" + idLog + " <b>Prenotazione per: </b>" + nPostiLog + "</br>";    
                }else{
                  tableOrdini += "<tr><td><b>Utente: </b>" + userLog + " <b>IdOrdine: </b>" + idLog + " <b>Consegna: </b>" + tipo_ordineLog + " </br>";  
                }

                var pizzeS = ordini[k].getElementsByTagName("pizzaS");
                var pizzeP = ordini[k].getElementsByTagName("pizzaP");
                var numeroLog, plusLog, nomeLog, baseLog;
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
                tableOrdini += "<td width=100px id='2"+idLog+"' align='center' bgcolor='"+color+"'><b>Stato: </b></br>" + "<label id='"+ idLog +"' type='text' size='10' value='"+ doneLog + "'>"+ doneLog +"</label>" + "</br><input type='"+value+"' id='3"+idLog+"' value='Completato' onclick='ordineCompletato(\""+idLog+"\");'/></td></tr>";
         }
    ordiniElement.innerHTML = tableOrdini; 
    }else if(value === "prenotazioni"){
        var prenotazioniElement = document.getElementById("riepilogo_prenotazioni");
        prenotazioniElement.style.display ="table";
        var dati = data.documentElement;
        var ordini = dati.getElementsByTagName("ordine_utente");

        for(k = 0; k < ordini.length; k++){
            var tipo_ordine = ordini[k].getElementsByTagName("tipo_ordine");
            var tipo_ordineLog = tipo_ordine[0].childNodes[0].nodeValue;
            console.log("Entro in stampa" + tipo_ordineLog);
            if (tipo_ordineLog === "prenotazione"){
                var user = ordini[k].getElementsByTagName("user");
                var userLog = user[0].childNodes[0].nodeValue;
                var id = ordini[k].getElementsByTagName("id");
                var idLog = id[0].childNodes[0].nodeValue;
                if (tipo_ordineLog === "prenotazione"){
                    var nPosti = ordini[k].getElementsByTagName("posti");
                    var nPostiLog = nPosti[0].childNodes[0].nodeValue;
                }
                var done = ordini[k].getElementsByTagName("done");
                var doneLog = done[0].childNodes[0].nodeValue;
                if(doneLog === "false"){
                    doneLog = "Attesa";
                    color = "#FF000";
                    value = "button";
                }else{
                    doneLog = "Completato";
                    color = "#00FF00";
                    value = "hidden";
                }
                tablePrenotazioni += "<tr><td><b>Utente: </b>" + userLog + " <b>IdOrdine: </b>" + idLog + " <b>Prenotazione per: </b>" + nPostiLog + " </br>";    
                
                var pizzeS = ordini[k].getElementsByTagName("pizzaS");
                var pizzeP = ordini[k].getElementsByTagName("pizzaP");
                var numeroLog, plusLog, nomeLog, baseLog;
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

function ordineCompletato(idOrdine){
    
    var r = "2"+idOrdine;
    var complete = "3"+idOrdine;
    
    document.getElementById(r).style.background = "#00FF00";
    document.getElementById(complete).type = "hidden";
    document.getElementById(idOrdine).innerHTML = 'Completato';
        var xmlhttp2 = new XMLHttpRequest();
        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                console.log("Modifico l'ordine");
            }
        };
        xmlhttp2.open("POST", "../ManageOrderService", true);
        xmlhttp2.setRequestHeader("Content-Type", "text/xml");

        var doc = document.implementation.createDocument("", "cambioStato", null);
        var nameElement = doc.createElement("idOrdine");
        var name = doc.createTextNode(idOrdine);

        nameElement.appendChild(name);

        doc.documentElement.appendChild(nameElement);
        xmlhttp2.send(doc);
}

function getOrdini(value){
        var xmlhttp2 = new XMLHttpRequest();
        xmlhttp2.onreadystatechange = function () {
            if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                var answer = xmlhttp2.responseXML;
                if(value === "ordini"){
                    stampaOrdini(answer, "ordini");   
                }else if(value === "prenotazioni"){
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
    getDati("messaggi");
}
