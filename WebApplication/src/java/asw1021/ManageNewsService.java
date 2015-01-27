/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asw1021;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lorenzo
 */
@WebServlet(name = "ManageNewsService", asyncSupported = true, urlPatterns = {"/ManageNewsService"})
public class ManageNewsService extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        System.out.println("Servlet per news");

        //comet
        // InputStream is = request.getInputStream();
        
        response.setContentType("text/xml;charset=UTF-8");
        
        
        final HttpServletRequest requestmy = request; 
        final HttpServletResponse responsemy = response;

        synchronized (this) {

            AsyncContext asyncContext = request.startAsync();
            asyncContext.setTimeout(10 * 1000);
            asyncContext.addListener(new AsyncAdapter() {
                @Override
                public void onTimeout(AsyncEvent e) {

                    try {
                        ManageXML mngXML = new ManageXML();

                        String path = getServletContext().getRealPath("") + "/WEB-INF/xml/pizze_novita.xml";
                        //Document doc = manageXml.parse(new File(path));
                        Document data = mngXML.parse(new File(path));

                        InputStream in = requestmy.getInputStream();

                        Document doc = mngXML.parse(in);
                        NodeList risp = doc.getElementsByTagName("tipo");

                        if (risp.item(0).getTextContent().equals("news")) {

                            OutputStream out = responsemy.getOutputStream();
                            responsemy.setContentType("text/xml");
                            mngXML.transform(out, data);
                            out.close();

                        }
                    } catch (Exception ex) {
                    }
                }
            });

        }

        /*

         try (PrintWriter out = response.getWriter()) {
         /* TODO output your page here. You may use following sample code. 
         out.println("<!DOCTYPE html>");
         out.println("<html>");
         out.println("<head>");
         out.println("<title>Servlet ManageNewsService</title>");
         out.println("</head>");
         out.println("<body>");
         out.println("<h1>Servlet ManageNewsService at " + request.getContextPath() + "</h1>");
         out.println("</body>");
         out.println("</html>");
         }*/
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
