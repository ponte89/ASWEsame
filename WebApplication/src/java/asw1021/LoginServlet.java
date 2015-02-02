/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asw1021;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.w3c.dom.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
@WebServlet(name = "LoginServlet", asyncSupported = true, urlPatterns = {"/LoginServlet"})
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
        
        String url = request.getContextPath() + "/jsp/home.jsp";
        HttpSession session = request.getSession();
        String target = request.getParameter("target");
        String user, password,type;
        try{
            switch(target){
                case "login":
                    user = request.getParameter("username");
                    password = request.getParameter("password");
                    type = request.getParameter("type");
                    
                    if(checkUser(user, password,type)){
                         session.setAttribute("login", user);
                         session.setAttribute("type",type);
                         request.setAttribute("user", user);
                    } else {
                         session.setAttribute("message", "Attenzione! I dati inseriti non sono corretti");   
                    }
                    break;
                case "logout":
                    session.invalidate();
                    break;
                case "registration":
                    String error = "";
                    boolean found = false;
                    String name = request.getParameter("nome");
                    String surname = request.getParameter("cognome");
                    String mail = request.getParameter("email");
                    String tel = request.getParameter("tel");
                    user = request.getParameter("user");
                    password = request.getParameter("pass");
                    String address = request.getParameter("indirizzo");
                    String cap = request.getParameter("postcode");
                    String country = request.getParameter("country");
                    
                    //controllo mail
                    if(!mail.equals("")){
                        if(!mail.matches("[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}")){
                            error = "Inserire un indirizzo mail valido";
                        }
                    }else if(name.equals("") && surname.equals("") && tel.equals("") && address.equals("")
                            && cap.equals("") && country.equals("") && user.equals("") && password.equals("")){
                        error = "Inserire tutti i dati richiesti";
                    }
                    
                    //controllo user unico
                    
                    if(error.equals("")){
                        InputStream is = getServletContext().getResourceAsStream("/WEB-INF/xml/anagrafica_test.xml");
                        ManageXML manageXml = new ManageXML();
                        Document doc = manageXml.parse(is);
                        
                        //Element root = doc.getDocumentElement();
                        
                        NodeList users= doc.getElementsByTagName("utente");
                        
                        NodeList utenti = doc.getElementsByTagName("utenti");
                        
                        Element us;

                        for (int i = 0; i < users.getLength(); i++) {
                                us = (Element)users.item(i);
                               if(us.getElementsByTagName("username").item(0).getTextContent().equals(user)){
                                    found = true;
                               }
                        }
                        if(!found){
                        //inserisci il nuovo utente
                            Element newUser = doc.createElement("utente");
                            Element newName = doc.createElement("name");
                            newName.setTextContent(name);
                            Element newSurname = doc.createElement("surname");
                            newSurname.setTextContent(surname);
                            Element newMail = doc.createElement("mail");
                            newMail.setTextContent(mail);
                            Element newTel = doc.createElement("tel");
                            newTel.setTextContent(tel);
                            Element newUsername = doc.createElement("username");
                            newUsername.setTextContent(user);
                            Element newPassword = doc.createElement("password");
                            newPassword.setTextContent(password);
                            Element newAddress = doc.createElement("address");
                            newAddress.setTextContent(address);
                            Element newCap = doc.createElement("cap");
                            newCap.setTextContent(cap);
                            Element newCountry = doc.createElement("country");
                            newCountry.setTextContent(country);
                            
                            newUser.appendChild(newName);
                            newUser.appendChild(newSurname);
                            newUser.appendChild(newMail);
                            newUser.appendChild(newTel);
                            newUser.appendChild(newUsername);
                            newUser.appendChild(newPassword);
                            newUser.appendChild(newAddress);
                            newUser.appendChild(newCap);
                            newUser.appendChild(newCountry);
                            //root.appendChild(newUser);
                            
                            utenti.item(0).appendChild(newUser);
                            
                            String path = getServletContext().getRealPath("")+"/WEB-INF/xml/anagrafica_test.xml";
                            //OutputStream os = new FileOutputStream(new File(path));             
                            OutputStream os = new FileOutputStream(path);
                            manageXml.transform(os, doc);
                            os.close();

                            session.setAttribute("login", user);
                            session.setAttribute("type","utente");
                 
                        }else{
                            System.out.println("Utente già presente" + request.getContextPath() + "/jsp/registrazione.jsp");
                            session.setAttribute("message","Username già presente");
                            url = request.getContextPath() + "/jsp/registrazione.jsp";
                        }
                    }else{  
                        System.out.println("Errore");
                        session.setAttribute("message", error);
                        url = request.getContextPath() + "/jsp/registrazione.jsp";
                    }
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("url: "+url);
        
        response.sendRedirect(url);
    }
    
    /** Controllo se l'utente è già registrato all'interno del mio file xml
     * @param us è lo username dell'utente
     * @param pwd è la password dell'utente
     * @param type è il tipo di utente che si sta loggando: admin, cuoco o cliente 
     * @return un boolean che mi dice se l'utente è già registrato o meno
     */
    
    private boolean checkUser(String us, String pwd,String type) {
       try{
            
        InputStream is = getServletContext().getResourceAsStream("/WEB-INF/xml/anagrafica_test.xml");
        ManageXML manageXml = new ManageXML();
        Document doc = manageXml.parse(is);
        NodeList users= doc.getDocumentElement().getElementsByTagName(type);
        Element user;
        
        for (int i = 0; i < users.getLength(); i++) {
               user = (Element)users.item(i);
               if(user.getElementsByTagName("username").item(0).getTextContent().equals(us)){
                    return (user.getElementsByTagName("password").item(0).getTextContent().equals(pwd));
               }
        }
        
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("L'utente non è registrato");
            return false;
        }
       return false;
    }
    
    @Override
    public void init() throws ServletException {
        ServletContext application = getServletContext();
        HashMap<String,Object> login = new HashMap<String,Object>();
        application.setAttribute("loginList", login);
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
