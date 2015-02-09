/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asw1021;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/** Servlet per la gestione dei messaggi inviati all'admin dai clienti
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
@WebServlet(name = "MessageServlet", urlPatterns = {"/MessageServlet"})
public class MessageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String name = request.getParameter("nome");
            String message = request.getParameter("messaggio");

            String target = request.getParameter("target");

            if (target.equals("new")) {
                ManageXML manageXml = new ManageXML();

                String path = getServletContext().getRealPath("") + "/WEB-INF/xml/messaggi.xml";
                Document doc = manageXml.parse(new File(path));

                Node messaggi = doc.getElementsByTagName("messaggi").item(0);

                Node messaggio = doc.createElement("messaggio");
                Element nome = doc.createElement("nome");
                nome.setTextContent(name);
                Element testo = doc.createElement("contenuto");
                testo.setTextContent(message);

                messaggio.appendChild(nome);
                messaggio.appendChild(testo);

                messaggi.appendChild(messaggio);

                OutputStream os = new FileOutputStream(path);
                response.setContentType("text/xml");
                manageXml.transform(os, doc);

                os.flush();
                os.close();
            } else if (target.equals("manage")) {
                System.out.println("richiesta manage");
                
                ManageXML manageXml = new ManageXML();
                String path = getServletContext().getRealPath("") + "/WEB-INF/xml/messaggi.xml";
                Document doc = manageXml.parse(new File(path));
                
                OutputStream os = response.getOutputStream();
                response.setContentType("text/xml");
                manageXml.transform(os, doc);

                os.flush();
                os.close();
            }

           
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        response.sendRedirect(request.getContextPath() + "/jsp/home.jsp");

        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MessageServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MessageServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
