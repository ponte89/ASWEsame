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
import java.util.LinkedList;
import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.servlet.annotation.WebServlet;

/**
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
@WebServlet(name = "ManageOrderService",  asyncSupported = true, urlPatterns = {"/ManageOrderService"})
public class ManageOrderService extends HttpServlet {
    
    
    private HashMap<String, Object> contexts;
    private Document dataInput;
    
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
        try{
            
            System.out.println("Servlet per ordini");
            
            InputStream is = request.getInputStream();
            response.setContentType("text/xml;charset=UTF-8");
            
            try {
                ManageXML mngXML = new ManageXML();
                Document data;
                
                data = mngXML.parse(is);
                dataInput = data;
                is.close();
                String target = "";
                target = request.getParameter("target");
                if(target != null){
                   //scrivo il file xml
                   String path = getServletContext().getRealPath("")+"/WEB-INF/xml/ordini_test.xml";
                   OutputStream os = new FileOutputStream(new File(path));
                   mngXML.transform(os, data);
                   mngXML = new ManageXML();
                   os.close();

                   data = mngXML.newDocument("push");    
                }
                                
                operations(data, request, response, mngXML);

            } catch (Exception ex) {
                System.out.println(ex);
            }            
            
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Errore nella servlet per ordini");
        }

    }

    private void operations(Document data, HttpServletRequest request, HttpServletResponse response, ManageXML mngXML) throws Exception {

        HttpSession session = request.getSession();
        Element root = data.getDocumentElement();
        String operation = root.getTagName();
        ServletContext application = getServletContext();
        String user;
        Document answer = null;
        OutputStream os;
        String path;
        Document doc;
        
        switch (operation) {
            case "push":
                System.out.println("push received");
                synchronized (this) {
                       for (String destUser : contexts.keySet()) {
                            System.out.println("Cuoco presente" + destUser);
                            Object value = contexts.get(destUser);
                            if (value instanceof AsyncContext) {
                                System.out.println("Cuoco in attesa" + (AsyncContext) value);
                                try (OutputStream aos = ((AsyncContext) value).getResponse().getOutputStream()) {
                                    mngXML.transform(aos, dataInput);
                                }
                                ((AsyncContext) value).complete();
                                contexts.put(destUser, new LinkedList<Document>());
                            } else {
                                System.out.println("Dati Accodati");
                                ((LinkedList<Document>) value).addLast(dataInput);
                            }
                        }
                }
                answer = mngXML.newDocument("ok");
                os = response.getOutputStream();
                mngXML.transform(os, answer);
                os.close();
                break;
            case "pop":
                user = (String) session.getAttribute("login");
                System.out.println("pop received from: " + user);

                boolean async;
                synchronized (this) {
                    LinkedList<Document> list = (LinkedList<Document>) contexts.get(user);
                    if (async = list.isEmpty()) {
                        AsyncContext asyncContext = request.startAsync();
                        asyncContext.setTimeout(10 * 1000);
                        asyncContext.addListener(new AsyncAdapter() {
                            @Override
                            public void onTimeout(AsyncEvent e) {
                                try {
                                    ManageXML mngXML = new ManageXML();

                                    AsyncContext asyncContext = e.getAsyncContext();
                                    HttpServletRequest reqAsync = (HttpServletRequest) asyncContext.getRequest();
                                    String user = (String) reqAsync.getSession().getAttribute("login");
                                    System.out.println("timeout event launched for: " + user);

                                    Document answer = mngXML.newDocument("timeout");
                                    boolean confirm;
                                    synchronized (ManageOrderService.this) {
                                        if (confirm = (contexts.get(user) instanceof AsyncContext)) {
                                            contexts.put(user, new LinkedList<Document>());
                                        }
                                    }
                                    if (confirm) {
                                        OutputStream tos = asyncContext.getResponse().getOutputStream();
                                        mngXML.transform(tos, answer);
                                        tos.close();
                                        asyncContext.complete();
                                    }
                                } catch (Exception ex) {
                                    System.out.println(ex);
                                }
                            }
                        });
                        contexts.put(user, asyncContext);
                    } else {
                        answer = list.removeFirst();
                    }
                }
                if (!async) {
                    os = response.getOutputStream();
                    response.setContentType("text/xml");
                    mngXML.transform(os, answer);
                    os.close();
                }
                break;
            case "getOrdini":
                path = getServletContext().getRealPath("") + "/WEB-INF/xml/ordini_test.xml";
                doc = mngXML.parse(new File(path));
                OutputStream osOrdini = response.getOutputStream();
                response.setContentType("text/xml");
                mngXML.transform(osOrdini, doc);
                osOrdini.close();
                break;
            case "cambioStato":
                System.out.println("Ciao");
                System.out.println(root.getFirstChild().getTextContent());
                //
                path = getServletContext().getRealPath("") + "/WEB-INF/xml/ordini_test.xml";
                doc = mngXML.parse(new File(path));
                OutputStream osStato = response.getOutputStream();
                response.setContentType("text/xml");
                mngXML.transform(osStato, doc);
                osStato.close();
                break;
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
    
    @Override
    public void init() throws ServletException {
        ServletContext application = getServletContext();
        contexts = (HashMap<String, Object>) application.getAttribute("loginList");
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
