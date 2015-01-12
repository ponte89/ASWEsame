/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asw1021;

import asw1012.ManageXML;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 *
 * @author Alessia
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
        
        String target = request.getParameter("target");
        
        switch(target){
        
            case "login":
                String user = request.getParameter("username");
                String password = request.getParameter("password");
                
                checkUser(user, password);
                
                
                break;
        }
        
        
        
    }
    
    private void checkUser(String us, String pw) {
        try{
        String context = getServletContext().getRealPath("") + "/WEB-INF/xml/anagrafica_test.xml";
        FileInputStream in = new FileInputStream(context);
        //System.out.println(context);
        
        
        ManageXML mng = new ManageXML();
        Document doc = mng.parse(in);
        
        NodeList list = doc.getDocumentElement().getElementsByTagName("admin");
        //System.out.println("-->"+list.)
        
        System.out.println("---->"+list.getLength());//list.item(0).getAttributes().getNamedItem("username").getNodeValue());
       // System.out.println("---->"+list.item(0).getNodeName());
        
        
        
        }catch (Exception e){
            System.out.println(e.toString());
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
