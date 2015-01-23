/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asw1021;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;

/**
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
@WebServlet(name = "MenuServlet", urlPatterns = {"/MenuServlet"})
public class MenuServlet extends HttpServlet {

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

        try {
            System.out.println("Servlet per modifica menu");
            ManageXML manageXml = new ManageXML();

            InputStream is = request.getInputStream();
            Document docin = manageXml.parse(is);
            String s = docin.getElementsByTagName("tipo").item(0).getTextContent();
            is.close();
            //System.out.println("contenuto: "+docin.getElementsByTagName("pizza_standard").getLength());
            
            
            
            if (s.equals("pizze")) {
                String path = getServletContext().getRealPath("") + "/WEB-INF/xml/pizze_standard_test.xml";
                Document doc = manageXml.parse(new File(path));
                OutputStream os = response.getOutputStream();
                response.setContentType("text/xml");
                manageXml.transform(os, doc);
                
                os.close();
            } else if (s.equals("condimenti")) {
                String path = getServletContext().getRealPath("") + "/WEB-INF/xml/condimenti_test.xml";
                Document doc = manageXml.parse(new File(path));
                OutputStream os = response.getOutputStream();
                response.setContentType("text/xml");
                manageXml.transform(os, doc);
                
                os.close();
            }else if(s.equals("nuovePizze")){
                //String path = getServletContext().getRealPath("") + "/WEB-INF/xml/pizze_standard_test.xml";
                //Document doc = manageXml.parse(new File(path));
                //Document doc = 
                System.out.println("Richiesta sostituzione pizze");
                
               // docin.replaceChild(doc, docin);
            }else if(s.equals("condimenti")){
                String path = getServletContext().getRealPath("") + "/WEB-INF/xml/condimenti_test.xml";
                Document doc = manageXml.parse(new File(path));
                
            }
            
        } catch (Exception e) {
            System.out.println("--> Servlet " + e.getMessage());
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
