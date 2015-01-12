package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/WEB-INF/jspf/header.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/sidebar.jspf");
    _jspx_dependants.add("/WEB-INF/jspf/footer.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Pizzeria Interattiva</title>\n");
      out.write("        <link href=\"./../style-sheet/styles.css\" rel=\"stylesheet\" type=\"text/css\">\n");
      out.write("        ");
      out.write("\n");
      out.write("\n");
      out.write("<header class=\"container\">\n");
      out.write("            <div class=\"headerPic\"><img src=\"./../multimedia/headerPic.jpg\"></div>\n");
      out.write("            <nav>\n");
      out.write("                <ul id=\"navlist\">\n");
      out.write("                    <li><a href=\"./../jsp/menu.jsp\">Home</a></li>\n");
      out.write("                    <li><a href=\"./../jsp/menu.jsp\">Menu</a></li>\n");
      out.write("                    <li><a href=\"./../jsp/ordina.jsp\">Ordina</a></li>\n");
      out.write("                    <li><a href=\"./../jsp/riepilogo.jsp\">Riepilogo ordini</a></li>\n");
      out.write("                    <li><a href=\"./../jsp/contatti.jsp\">Contatti</a></li>\n");
      out.write("\n");
      out.write("                </ul>\n");
      out.write("</header>\n");
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("     <section class=\"container\">\n");
      out.write("     ");
      out.write("\n");
      out.write("\n");
      out.write("<div class=\"sidebar1\">\n");
      out.write("                <h2>Area riservata</h2>\n");
      out.write("                <br/><br/>\n");
      out.write("\n");
      out.write("                <div class=\"login\">\n");
      out.write("                    <form class=\"login\">\n");
      out.write("\n");
      out.write("                        <label class=\"login\" for=\"username\">Username:</label>\n");
      out.write("\n");
      out.write("                        <input class=\"login\" type=\"text\" id=\"username\" name=\"username\">\n");
      out.write("\n");
      out.write("                        <label class=\"login\" for=\"password\">Password:</label>\n");
      out.write("\n");
      out.write("                        <input class=\"login\" type=\"password\" id=\"password\" name=\"password\">\n");
      out.write("\n");
      out.write("                        <input class=\"login\" type=\"submit\" value=\"Login\">\n");
      out.write("\n");
      out.write("                        <input class=\"login\" type=\"submit\" value=\"Registrati\">\n");
      out.write("\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                <div id=\"consigli\">\n");
      out.write("                    <h2>Ultime richieste</h2>\n");
      out.write("                </div>\n");
      out.write("</div>\n");
      out.write("   \n");
      out.write("     <div class=\"content\">\n");
      out.write("                <h1 id=\"nome\">Benvenuti nella nuova Pizzeria Interattiva</h1>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <p id = \"benvenuti\">\n");
      out.write("                    Il concetto di questa nuova pizzeria &egrave; semplice, con pochi passaggi ti registri al sito, \n");
      out.write("                    poi potrai subito ordinare le pizze che pi&ugrave; di piacciono!\n");
      out.write("                    <br/>Ma non solo questo: una volta che sarai un nosto cliente potrai anche divertirti \n");
      out.write("                    a crearne di nuove e fantasiose!<br/><br/> Non ti va?Nessun problma scorri il menu del giorno, sar&agrave; \n");
      out.write("                    la nostra cucina a consigliarti un piatto il piatto tipico.\n");
      out.write("                    <br/><br/>Non ti basta ancora? Puoi anche seguire i suggerimenti delle ultime pizze ordinate, \n");
      out.write("                    ogni volta sempre nuovi abbinamenti e nuovi ingredienti per rendere le tue pizze sempre piu sfizione.\n");
      out.write("\n");
      out.write("                    <br/><br/>Per il trasporto? Nessun problema, inserisci l'indirizzo e ti verranno recapitate a casa ancora calde\n");
      out.write("                    e pronte per essere mangiate!\n");
      out.write("                    <br/><br/>Vuoi essere tu a passare da noi?  Consulta l'area clienti e scopri quanto manca prima che \n");
      out.write("                    le pizze siano pronte!\n");
      out.write("\n");
      out.write("\n");
      out.write("                    Vuoi prenotare un tavolo? Anche per questo abbiamo la soluzione potrai selezionarlo personalmente da casa!\n");
      out.write("\n");
      out.write("\n");
      out.write("                    <br/><br/> Che fai ancora li? Vieni a trovarci o registrati, e ordina subito la tua prima pizza da \"Pizzeria Interattiva\"</p>\n");
      out.write("\n");
      out.write("\n");
      out.write("                <blockquote> Pizzeria Interattiva, il gusto di una buona pizza a portata di click!  <cite>Il team di Pizzeria Interattiva </cite>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </section>\n");
      out.write("\n");
      out.write("      ");
      out.write("\n");
      out.write("\n");
      out.write("<footer class=\"container\">\n");
      out.write("            <p>Copyright &copy; Pizzeria Interattiva. All rights reserved. Design by Mezzapesa Beatrice Papini Alessia Pontellini Lorenzo<p>\n");
      out.write("</footer>");
      out.write("    \n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
