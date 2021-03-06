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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author bea
 */
@WebServlet(name = "ConsegnaService", urlPatterns = {"/ConsegnaService"})
public class ConsegnaService extends HttpServlet {
    
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
        try {

            System.out.println("Servlet per le consegne");

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
                //lettura parametro target ed eventuale scrittura su database
                if (target != null) {
                    String path = getServletContext().getRealPath("") + "/WEB-INF/xml/ordini_test.xml";
                    Document doc = mngXML.parse(new File(path));
                    NodeList ordini = doc.getElementsByTagName("ordini_utente");
                    NodeList ordine = data.getElementsByTagName("ordine_utente");
                    Element ordineUtente = null;
                    Element ordineU = doc.createElement("ordine_utente");
                    NodeList pizzaS, pizzaP;
                    String id, partito, tipo_ordine, address, numero, nome, plus, base, user;

                    for (int i = 0; i < ordine.getLength(); i++) {
                        ordineUtente = (Element) ordine.item(i);
                        id = ordineUtente.getElementsByTagName("id").item(0).getTextContent();
                        user = ordineUtente.getElementsByTagName("user").item(0).getTextContent();
                        partito = ordineUtente.getElementsByTagName("partito").item(0).getTextContent();
                        tipo_ordine = ordineUtente.getElementsByTagName("tipo_ordine").item(0).getTextContent();
                        address = ordineUtente.getElementsByTagName("address").item(0).getTextContent();

                        Element userEl = doc.createElement("user");
                        userEl.setTextContent(user);
                        Element idEl = doc.createElement("id");
                        idEl.setTextContent(id);
                        Element typeEl = doc.createElement("tipo_ordine");
                        typeEl.setTextContent(tipo_ordine);
                        Element partitoEl = doc.createElement("partito");
                        partitoEl.setTextContent(partito);
                        Element addressEl = doc.createElement("address");
                        addressEl.setTextContent(address);

                        ordineU.appendChild(userEl);
                        ordineU.appendChild(idEl);
                        ordineU.appendChild(addressEl);

                        ordineU.appendChild(typeEl);
                        ordineU.appendChild(partitoEl);

                        pizzaS = ordineUtente.getElementsByTagName("pizzaS");
                        pizzaP = ordineUtente.getElementsByTagName("pizzaP");
                        Element pizzaPers = null;
                        Element pizzaStandard = null;

                        for (int j = 0; j < pizzaS.getLength(); j++) {
                            pizzaStandard = doc.createElement("pizzaS");
                            Element pizza = (Element) pizzaS.item(j);
                            nome = pizza.getElementsByTagName("nome_pizza").item(0).getTextContent();
                            numero = pizza.getElementsByTagName("numero_pizze").item(0).getTextContent();
                            plus = pizza.getElementsByTagName("plus").item(0).getTextContent();

                            Element nomeEl = doc.createElement("nome_pizza");
                            nomeEl.setTextContent(nome);
                            Element numeroEl = doc.createElement("numero_pizze");
                            numeroEl.setTextContent(numero);
                            Element plusEl = doc.createElement("plus");
                            plusEl.setTextContent(plus);
                            pizzaStandard.appendChild(nomeEl);
                            pizzaStandard.appendChild(numeroEl);
                            pizzaStandard.appendChild(plusEl);
                            ordineU.appendChild(pizzaStandard);
                        }
                        for (int k = 0; k < pizzaP.getLength(); k++) {
                            pizzaPers = doc.createElement("pizzaP");
                            Element pizza = (Element) pizzaP.item(k);
                            nome = pizza.getElementsByTagName("nome_pizza").item(0).getTextContent();
                            numero = pizza.getElementsByTagName("numero_pizze").item(0).getTextContent();
                            plus = pizza.getElementsByTagName("plus").item(0).getTextContent();
                            base = pizza.getElementsByTagName("base").item(0).getTextContent();

                            Element nomeEl = doc.createElement("nome_pizza");
                            nomeEl.setTextContent(nome);
                            Element numeroEl = doc.createElement("numero_pizze");
                            numeroEl.setTextContent(numero);
                            Element plusEl = doc.createElement("plus");
                            plusEl.setTextContent(plus);
                            Element baseEl = doc.createElement("base");
                            baseEl.setTextContent(base);

                            pizzaPers.appendChild(nomeEl);
                            pizzaPers.appendChild(numeroEl);
                            pizzaPers.appendChild(plusEl);
                            pizzaPers.appendChild(baseEl);

                            NodeList condimenti = pizza.getElementsByTagName("condimento");
                            for (int x = 0; x < condimenti.getLength(); x++) {
                                Element condimento = (Element) condimenti.item(x);
                                String cond = condimento.getTextContent();
                                Element condEl = doc.createElement("condimento");
                                condEl.setTextContent(cond);
                                pizzaPers.appendChild(condEl);
                            }
                            ordineU.appendChild(pizzaPers);
                        }

                    }

                    ordini.item(0).appendChild(ordineU);
                    OutputStream os = new FileOutputStream(path);
                    mngXML.transform(os, doc);
                    mngXML = new ManageXML();
                    os.close();

                    data = mngXML.newDocument("push");
                }

                operations(data, request, response, mngXML);

            } catch (Exception ex) {
                System.out.println(ex);
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Errore nella servlet delle consegne");
        }
    }
    
    private void operations(Document data, HttpServletRequest request, HttpServletResponse response, ManageXML mngXML) throws Exception {

        HttpSession session = request.getSession();
        Element root = data.getDocumentElement();
        String operation = root.getTagName();
        String user;
        Document answer2 = null;
        OutputStream os;
        String path;
        Document doc;

        switch (operation) {
            //operazione con cui si inviano i dati agli utenti in attesa o si accodano nella lista
            case "push":
                System.out.println("push ricevuta");
                synchronized (this) {
                    for (String destUser : contexts.keySet()) {
                        Object value = contexts.get(destUser);
                        if (value instanceof AsyncContext) {
                            System.out.println("Utente in attesa " + (AsyncContext) value);
                            try (OutputStream aos = ((AsyncContext) value).getResponse().getOutputStream()) {
                                mngXML.transform(aos, dataInput);
                            }
                            ((AsyncContext) value).complete();
                            contexts.put(destUser, new LinkedList<Document>());
                        } else {
                            ((LinkedList<Document>) value).addLast(dataInput);
                        }
                    }
                }
                answer2 = mngXML.newDocument("ok");
                os = response.getOutputStream();
                mngXML.transform(os, answer2);
                os.close();
                break;
            //operazione che permette la lettura di dati, nel caso siano presenti oppure pongono 
            //l'utente in attesa
            case "pop":
                user = (String) session.getAttribute("login");
                System.out.println("pop ricevuta da: " + user);

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
                                    System.out.println("Evento timeout per: " + user);

                                    Document answer2 = mngXML.newDocument("timeout");
                                    boolean confirm;
                                    synchronized (ConsegnaService.this) {
                                        if (confirm = (contexts.get(user) instanceof AsyncContext)) {
                                            contexts.put(user, new LinkedList<Document>());
                                        }
                                    }
                                    if (confirm) {
                                        OutputStream tos = asyncContext.getResponse().getOutputStream();
                                        mngXML.transform(tos, answer2);
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
                        answer2 = list.removeFirst();
                    }
                }
                if (!async) {
                    os = response.getOutputStream();
                    response.setContentType("text/xml");
                    mngXML.transform(os, answer2);
                    os.close();
                }
                break;
            //operazione che restituisce la lista degli ordini
            case "getOrdini":
                path = getServletContext().getRealPath("") + "/WEB-INF/xml/ordini_test.xml";
                doc = mngXML.parse(new File(path));
                OutputStream osOrdini = response.getOutputStream();
                response.setContentType("text/xml");
                mngXML.transform(osOrdini, doc);
                osOrdini.close();
                break;
            //operazione che effettua il cambio stato degli ordini (attesa/completato)
            case "cambioStato":
                String idOrdineState = root.getFirstChild().getTextContent();
                System.out.println(idOrdineState);
                boolean fattorinoPartito = false;
                path = getServletContext().getRealPath("") + "/WEB-INF/xml/ordini_test.xml";
                doc = mngXML.parse(new File(path));

                OutputStream osStato = new FileOutputStream(path);
                NodeList ordini = doc.getElementsByTagName("ordine_utente");
                Element ordine = null;
                Node ordineChange = null;
                for (int i = 0; i < ordini.getLength(); i++) {
                    ordine = (Element) ordini.item(i);
                    if (ordine.getElementsByTagName("id").item(0).getTextContent().equals(idOrdineState)) {
                        fattorinoPartito = true;
                        ordineChange = ordine.getElementsByTagName("partito").item(0);
                        ordineChange.setTextContent("true");
                    }
                }

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
