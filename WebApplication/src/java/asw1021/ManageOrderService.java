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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
@WebServlet(name = "ManageOrderService", asyncSupported = true, urlPatterns = {"/ManageOrderService"})
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
        try {

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
                if (target != null) {
                    String path = getServletContext().getRealPath("") + "/WEB-INF/xml/ordini_test.xml";
                    Document doc = mngXML.parse(new File(path));
                    NodeList ordini = doc.getElementsByTagName("ordini_utente");
                    NodeList ordine = data.getElementsByTagName("ordine_utente");
                    Element ordineUtente = null;
                    Element ordineU = doc.createElement("ordine_utente");
                    NodeList pizzaS, pizzaP;
                    String id, done, tipo_ordine, numero, nome, plus, base, user;

                    for (int i = 0; i < ordine.getLength(); i++) {
                        ordineUtente = (Element) ordine.item(i);
                        id = ordineUtente.getElementsByTagName("id").item(0).getTextContent();
                        user = ordineUtente.getElementsByTagName("user").item(0).getTextContent();
                        done = ordineUtente.getElementsByTagName("done").item(0).getTextContent();
                        tipo_ordine = ordineUtente.getElementsByTagName("tipo_ordine").item(0).getTextContent();

                        Element userEl = doc.createElement("user");
                        userEl.setTextContent(user);
                        Element idEl = doc.createElement("id");
                        idEl.setTextContent(id);
                        Element typeEl = doc.createElement("tipo_ordine");
                        Element doneEl = doc.createElement("done");
                        doneEl.setTextContent(done);

                        ordineU.appendChild(userEl);
                        ordineU.appendChild(idEl);

                        if (!tipo_ordine.equals("ritiro") && !tipo_ordine.equals("asporto")) {
                            String numero_posti = ordineUtente.getElementsByTagName("posti").item(0).getTextContent();
                            Element postiEl = doc.createElement("posti");
                            postiEl.setTextContent(numero_posti);
                            tipo_ordine = "prenotazione";
                            ordineU.appendChild(postiEl);
                        }

                        typeEl.setTextContent(tipo_ordine);
                        ordineU.appendChild(typeEl);
                        ordineU.appendChild(doneEl);

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
                    //fine ordine
                    OutputStream os = new FileOutputStream(path);
                    mngXML.transform(os, doc);
                    //mngXML.transform(os, data);
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
            System.out.println("Errore nella servlet per ordini");
        }

    }

    private void operations(Document data, HttpServletRequest request, HttpServletResponse response, ManageXML mngXML) throws Exception {

        HttpSession session = request.getSession();
        Element root = data.getDocumentElement();
        String operation = root.getTagName();
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
                        Object value = contexts.get(destUser);
                        if (value instanceof AsyncContext) {
                            System.out.println("Utente in attesa" + (AsyncContext) value);
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
                String idOrdineState = root.getFirstChild().getTextContent();
                System.out.println(idOrdineState);
                boolean found = false;
                path = getServletContext().getRealPath("") + "/WEB-INF/xml/ordini_test.xml";
                doc = mngXML.parse(new File(path));

                OutputStream osStato = new FileOutputStream(path);
                NodeList ordini = doc.getElementsByTagName("ordine_utente");
                Element ordine = null;
                Node ordineChange = null;
                for (int i = 0; i < ordini.getLength(); i++) {
                    ordine = (Element) ordini.item(i);
                    if (ordine.getElementsByTagName("id").item(0).getTextContent().equals(idOrdineState)) {
                        found = true;
                        ordineChange = ordine.getElementsByTagName("done").item(0);
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
