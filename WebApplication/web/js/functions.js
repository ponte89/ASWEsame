/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var datiPizze;
var datiCondimenti;
var table = "";

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
        var dati = data.documentElement;
        var pizze = dati.getElementsByTagName("nome");
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
    }
}

function getMessages(){
                var i = 0;            
                var ordiniElement = document.getElementById("riepilgo_ordini");                         
                var msg = "";
                var xmlhttp2,answer,to,data;
                console.log("ancora!");
                xmlhttp2 = new XMLHttpRequest();
                xmlhttp2.open("POST", "../OrderServlet", true);
                xmlhttp2.setRequestHeader("Content-Type", "text/xml");
                xmlhttp2.onreadystatechange=function(){
                if (xmlhttp2.readyState == 4 && xmlhttp2.status==200) {   
                    answer = xmlhttp2.responseXML;
                        var tableOrdini = document.getElementById("ordini");
                        var dati = answer.documentElement;
                        var ordine = dati.getElementsByTagName("ordini_utente");
                        var user = dati.getElementsByTagName("user");
                        var userLog = user[0].childNodes[0].nodeValue;
                        var id = dati.getElementsByTagName("id");
                        var idLog = id[0].childNodes[0].nodeValue;
                        var tipo_ordine = dati.getElementsByTagName("tipo_ordine");
                        var tipo_ordineLog = tipo_ordine[0].childNodes[0].nodeValue;
                        var done = dati.getElementsByTagName("done");
                        var doneLog = done[0].childNodes[0].nodeValue;
                        var title = "<caption align='center' style='font-size:36px'>Ordini:</caption>";
                        table += "<tr><td>" + userLog + " " + idLog + " " + tipo_ordineLog + " " + doneLog;
                        ordiniElement.innerHTML = title + table; 
                        getMessages();
                }
               };
            data = document.implementation.createDocument("", "pop", null);                     
            xmlhttp2.send(data);
}