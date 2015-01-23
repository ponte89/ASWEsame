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

    <body onLoad="inizializzaDati()">
        <section class="container">

            <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   




            <div class="content">
                <h1>Il menu tradizionale</h1>

                <%  if ((session.getAttribute("login") != null)
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
                
                <table>
                    <tr>
                        <td><table id="pizze" border="3" frame="border" style="background-image: url(./../multimedia/tab.jpg); margin:10px" ></table></td>

                        <td><table id="condimenti" border="3" frame="border" style="background-image: url(./../multimedia/tab.jpg); margin:10px"></table></td>
                    </tr>
                </table>
                <%
                    }
                %>        

            </div>

        </section>

        <script >

            var datiPizze;
            var datiCondimenti;
            function inizializzaDati(){
                getPizze();
                getCondimenti();
            }
            
            function getPizze() {
                var xmlhttp2 = new XMLHttpRequest();
                var to = "http://localhost:8080/WebApplication/MenuServlet";
                
                xmlhttp2.onreadystatechange = function () {
                    if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                        var answer = xmlhttp2.responseXML;
                        stampaPizze(answer);
                    }
                };
                
                xmlhttp2.open("POST", to, true);
                xmlhttp2.setRequestHeader("Content-Type", "text/xml");
                
                creaDatiPizze();
                
                xmlhttp2.send(datiPizze);
            }
            
            function creaDatiPizze(){
                datiPizze = document.implementation.createDocument("", "", null);
                var rootData = datiPizze.createElement("tipo");
                var text = datiPizze.createTextNode("text");
                text.nodeValue = "pizze";
                rootData.appendChild(text);
                datiPizze.appendChild(rootData);
            }
            
            function creaDatiCondimenti(){
                datiCondimenti = document.implementation.createDocument("", "", null);
                var rootData = datiCondimenti.createElement("tipo");
                var text = datiCondimenti.createTextNode("text");
                text.nodeValue = "condimenti";
                rootData.appendChild(text);
                datiCondimenti.appendChild(rootData);
            }

            function stampaPizze(data){
                var con = document.getElementById("pizze");
                var dati = data.documentElement;
                var pizze = dati.getElementsByTagName("nome");
                //console.log("lunghezza pizze :"+pizze[0].childNodes[0].nodeValue);
                
                var sopra = "<caption align='attributo' style='font-size:36px'>Le nostre Pizze</caption>";
                var table = ""
                
                for (i = 0; i < pizze.length; i++){
                    var nome = pizze[i].childNodes[0].nodeValue;
                    
                    table +=  "<tr><td width=10>"+(i+1)+"</td><td width=300>"+nome+"</td></tr>";
                    
                }
                
                con.innerHTML = sopra+table;
            }
            
            function stampaCondimenti(data){
                var con =  document.getElementById("condimenti"); 
                var dati = data.documentElement;
                var pizze = dati.getElementsByTagName("nome");
                //console.log("lunghezza pizze :"+pizze[0].childNodes[0].nodeValue);
                
                var sopra = "<caption align='attributo' style='font-size:36px'>I nostri condimenti</caption>";
                var table = ""
                
                for (i = 0; i < pizze.length; i++){
                    var nome = pizze[i].childNodes[0].nodeValue;
                    
                    table +=  "<tr><td width=10>"+(i+1)+"</td><td width=400>"+nome+"</td></tr>";
                    
                }
                
                con.innerHTML = sopra+table;
            }
            
            function getCondimenti(){
                var xmlhttp2 = new XMLHttpRequest();
                var to = "http://localhost:8080/WebApplication/MenuServlet";
                
                xmlhttp2.onreadystatechange = function () {
                    if (xmlhttp2.readyState === 4 && xmlhttp2.status === 200) {
                        var answer = xmlhttp2.responseXML;
                        stampaCondimenti(answer);
                    }
                };
                
                xmlhttp2.open("POST", to, true);
                xmlhttp2.setRequestHeader("Content-Type", "text/xml");
                
                creaDatiCondimenti();
                
                xmlhttp2.send(datiCondimenti);
            }
            
            
        </script>

        <%@ include file="/WEB-INF/jspf/footer.jspf" %> 
    </body>
</html>