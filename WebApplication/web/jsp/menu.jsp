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

<body>
  <section class="container">

      <%@ include file="/WEB-INF/jspf/sidebar.jspf" %>   
      
      
     
      
      <div class="content">
        <h1>Il menu tradizionale</h1>
        
        <%
            if((session.getAttribute("login") != null)&&
                (session.getAttribute("type").toString().equals("admin"))) { 
        %>
                
            
                <div id="applet" align="center" style="margin:5px;">

                    <applet code="asw1021.AdminUpdateMenu"
                            codebase="../applet/" 
                            archive="Applet2.jar, Lib1.jar"
                            width="570"
                            height="410">
                        Applet failed to run. No Java plug-in was found.
                    </applet>

                </div>
                
        <%
            }else{
        %>  
            <div class="Menù"><img src="./../multimedia/Menu.jpg"></div>
        
        <%
            }
        %>        
        
      </div>
           <!--  -->
      
      
      
  </section>
  
  <%@ include file="/WEB-INF/jspf/footer.jspf" %> 
</body>
</html>
