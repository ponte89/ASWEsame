<%-- 
    Document   : registrazione
    Created on : Jan 11, 2015, 9:35:46 PM
    Author     : Alessia
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <title>Pizzeria Interattiva</title>
        <link href="./../style-sheet/styles.css" rel="stylesheet" type="text/css">
         <%@ include file="/WEB-INF/jspf/header.jspf" %>
    </head>

    <body>

       <section class="container">

            <div class="content">
                <h1>Registrazione nuovo utente</h1>
                <%@ include file="/WEB-INF/jspf/message.jspf" %> 
                <form id="reg" action="../LoginServlet?target=registration" method="post">

                    <fieldset>

                        <legend>Generalit&agrave;</legend>

                        <ol>

                            <li>
                                <label for=name>Nome</label>
                                <input  name=nome type=text placeholder="Nome" required autofocus>
                            </li>

                            <li>
                                <label for=name>Surname</label>
                                <input name=cognome type=text placeholder="Cognome" required >
                            </li>

                            <li>
                                <label for=email>Email</label>
                                <input name=email type=email placeholder="example@domain.com" required>
                            </li>

                            <li>
                                <label for=phone>Telefono</label>
                                <input name=tel type=tel placeholder="Eg. +39-1234567890" required>
                            </li>

                            <li>
                                <label for=username>Username</label>
                                <input name=user type=text placeholder="username" required>
                            </li>

                            <li>
                                <label for=password>Password</label>
                                <input name=pass type=password placeholder="password" required>
                            </li>

                        </ol>

                    </fieldset>

                    <fieldset>

                        <legend>Indirizzo di spedizione</legend>

                        <ol>

                            <li>
                                <label for=indirizzo>Indirizzo</label>
                                <textarea id=indirizzo name=indirizzo rows=5 required></textarea>
                            </li>

                            <li>
                                <label for=postcode>Codice postale</label>
                                <input id=postcode name=postcode type=text required>
                            </li>

                            <li>
                                <label for=country>Nazione</label>
                                <input id=country name=country type=text required>
                            </li>

                        </ol>

                    </fieldset>


                    <fieldset>

                        <button type=submit>Conferma</button>

                    </fieldset>

                </form>


            </div>



        </section>

    <%@ include file="/WEB-INF/jspf/footer.jspf" %>
    <script  src="./../js/functions.js"></script>
    </body>
</html>
