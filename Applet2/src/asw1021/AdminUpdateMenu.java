/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asw1021;

import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import java.awt.Container;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import static java.lang.System.console;
import java.net.URL;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
/**
 * Applet per modificare il menu In base al pannello che si va a scegliere,
 * condimenti o pizze, posso modificare il mio menu aggiungendoci all'interno
 * condimenti o pizze differenti da quelli già presenti; ma posso anche
 * rimuovere ciò che è già presente nel menu.
 */
public class AdminUpdateMenu extends JApplet {

    private Container cp;

    private JButton btnAggiungiCond;
    private JButton btnRimuoviPiz;
    private JButton btnRimuoviCond;
    private JButton btnAggiungiPiz;

    private JLabel lblNuovaPizza;
    private JLabel lblNuovaCondimento;
    private JLabel lblPrezzoPizza;
    private JLabel lblPrezzoCondimento;

    //private JTextArea textArea;
    private JScrollPane scrollCondimenti;
    private JScrollPane scrollPizze;
    private JTabbedPane tabbedPane;
    private JPanel panelCondimenti;
    private JPanel panelPizze;
    private JTextField txtNuovaPizza;
    private JTextField txtNuovoCondimento;
    private JTextField txtPrezzoPizza;
    private JTextField txtPrezzoCondimento;

    private JList<String> listaPizze;
    private DefaultListModel modelPizze;

    private JList<String> listaCondimenti;
    private DefaultListModel modelCondimenti;

    private JButton btnNewButton;

    private JTextArea textArea;

    /**
     * Inizializzazione dell' applet con creazione della GUI
     */
    @Override
    public void init() {

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    initializeGUI();

                }
            });

            getListaPizze();

            getListaCondimenti();

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Definizione dell' interfaccia grafica con appropriati componenti grafici
     * per l'aggiunta e la rimozione di condimenti o pizze. Utilizziamo un
     * Layout totalmente gestito da noi in cui a ogni componente inseriamo le
     * coordinate.
     */
    private void initializeGUI() {
        cp = getContentPane();
        cp.setLayout(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(6, 6, 438, 288);
        cp.add(tabbedPane);

        panelCondimenti = new JPanel();
        tabbedPane.addTab("Condimenti", null, panelCondimenti, null);
        panelCondimenti.setLayout(null);

        btnAggiungiCond = new JButton("Aggiungi");
        btnAggiungiCond.setBounds(173, 207, 120, 29);
        btnAggiungiCond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiCondimento();
            }
        });

        panelCondimenti.add(btnAggiungiCond);

        btnRimuoviCond = new JButton("Rimuovi");
        btnRimuoviCond.setBounds(291, 207, 120, 29);
        btnRimuoviCond.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rimuoviCondimento();
            }
        });
        panelCondimenti.add(btnRimuoviCond);

        panelPizze = new JPanel();
        tabbedPane.addTab("Pizze", null, panelPizze, null);
        panelPizze.setLayout(null);

        btnAggiungiPiz = new JButton("Aggiungi");
        btnAggiungiPiz.setBounds(173, 207, 120, 29);
        btnAggiungiPiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiPizza();
            }
        });

        panelPizze.add(btnAggiungiPiz);

        btnRimuoviPiz = new JButton("Rimuovi");
        btnRimuoviPiz.setBounds(291, 207, 120, 29);
        btnRimuoviPiz.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rimuoviPizza();
            }
        });
        panelPizze.add(btnRimuoviPiz);

        txtNuovaPizza = new JTextField();
        txtNuovaPizza.setBounds(6, 24, 108, 28);
        txtNuovaPizza.setColumns(10);
        panelPizze.add(txtNuovaPizza);

        lblNuovaPizza = new JLabel("Nuova Pizza");
        lblNuovaPizza.setBounds(6, 6, 108, 16);
        panelPizze.add(lblNuovaPizza);

        lblPrezzoPizza = new JLabel("Prezzo");
        lblPrezzoPizza.setBounds(6, 65, 61, 16);
        panelPizze.add(lblPrezzoPizza);

        txtPrezzoPizza = new JTextField();
        txtPrezzoPizza.setBounds(6, 82, 108, 28);
        txtPrezzoPizza.setColumns(10);
        panelPizze.add(txtPrezzoPizza);

        textArea = new JTextArea();
        textArea.setBounds(6, 64, 188, 172);
        panelPizze.add(textArea);

        listaPizze = new JList();

        txtNuovoCondimento = new JTextField();
        txtNuovoCondimento.setBounds(6, 24, 108, 28);
        panelCondimenti.add(txtNuovoCondimento);
        txtNuovoCondimento.setColumns(10);

        lblNuovaCondimento = new JLabel("Nuovo Condimento");
        lblNuovaCondimento.setBounds(6, 6, 129, 16);
        panelCondimenti.add(lblNuovaCondimento);

        lblPrezzoCondimento = new JLabel("Prezzo");
        lblPrezzoCondimento.setBounds(6, 65, 61, 16);
        panelCondimenti.add(lblPrezzoCondimento);

        listaCondimenti = new JList();

        scrollCondimenti = new JScrollPane(listaCondimenti);
        scrollCondimenti.setBounds(206, 6, 205, 191);
        panelCondimenti.add(scrollCondimenti);

        scrollPizze = new JScrollPane(listaPizze);
        scrollPizze.setBounds(206, 6, 205, 191);
        panelPizze.add(scrollPizze);

        txtPrezzoCondimento = new JTextField();
        txtPrezzoCondimento.setBounds(6, 82, 108, 28);
        panelCondimenti.add(txtPrezzoCondimento);
        txtPrezzoCondimento.setColumns(10);

        btnNewButton = new JButton("CONFERMA");
        btnNewButton.setBounds(16, 285, 415, 29);
        cp.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                salvaModificheMenu();
            }

        });
    }

    /**
     * Nella pannello "Pizze" e precisamente nel riquadro sulla destra ottengo
     * tutta la lista di pizze che in quell'istante è visibile dalla voce menù
     */
    private void getListaPizze() {
        try {

            HTTPClient httpClient = new HTTPClient();
            httpClient.setBase(new URL("http://localhost:8080/WebApplication/MenuServlet"));

            ManageXML mngXML = new ManageXML();
            Document data = mngXML.newDocument();
            Element rootFile = data.createElement("tipo");
            rootFile.setTextContent("pizze");
            data.appendChild(rootFile);

            Document answer = httpClient.execute("MenuServlet", data);
            NodeList l = answer.getElementsByTagName("pizza_standard");

            modelPizze = new DefaultListModel();
            for (int i = 0; i < l.getLength(); i++) {
                Element n = (Element) l.item(i);
                modelPizze.addElement(n.getElementsByTagName("nome").item(0).getTextContent());
            }
            listaPizze.setModel(modelPizze);

        } catch (Exception e) {
            System.err.println("--> " + e.getMessage());
        }
    }

    /**
     * Nella pannello "Condimenti" e precisamente nel riquadro sulla destra
     * ottengo tutta la lista di condimenti che in quell'istante sono visibili
     * una volta che richiedo una pizza personalizzata.
     */
    private void getListaCondimenti() {
        try {
            HTTPClient httpClient = new HTTPClient();
            httpClient.setBase(new URL("http://localhost:8080/WebApplication/MenuServlet"));

            ManageXML mngXML = new ManageXML();
            Document data = mngXML.newDocument();
            Element rootFile = data.createElement("tipo");
            rootFile.setTextContent("condimenti");
            data.appendChild(rootFile);

            Document answer = httpClient.execute("MenuServlet", data);
            NodeList l = answer.getElementsByTagName("condimento");

            modelCondimenti = new DefaultListModel();
            for (int i = 0; i < l.getLength(); i++) {
                Element n = (Element) l.item(i);

                modelCondimenti.addElement(n.getElementsByTagName("nome").item(0).getTextContent());
            }
            listaCondimenti.setModel(modelCondimenti);

        } catch (Exception e) {
            System.err.println("--> " + e.getMessage());
        }
    }

    /**
     * Nel pannello "Condimenti" sulla destra trovo i condimenti già visibili al
     * cliente e selezionandone uno, basta premere il pulsante "rimuovi" per
     * eliminarlo dalla lista visibile all'utente.
     */
    private void rimuoviCondimento() {
        if (listaCondimenti.getSelectedIndex() != -1) {
            modelCondimenti.remove(listaCondimenti.getSelectedIndex());
            listaCondimenti.setModel(modelCondimenti);
        }
    }

    /**
     * Nel pannello "Condimenti" all'interno della text area "nuovo condimento"
     * posso inserirne uno e aggiungerlo con visibilità anche ai clienti
     * premendo il pulsante "aggiungi".
     */
    private void aggiungiCondimento() {

        if (!txtNuovoCondimento.getText().isEmpty()) {
            String p = txtNuovoCondimento.getText();
            modelCondimenti.addElement(p);
            listaCondimenti.setModel(modelCondimenti);
        }
    }

    /**
     * Nel pannelo "pizze" sulla destra trovo le pizze già visibili al cliente e
     * selezionandone una, basta premere il pulsante "rimuovi" per eliminarla
     * dalla lista visibile all'utente.
     */
    private void rimuoviPizza() {
        if (listaPizze.getSelectedIndex() != -1) {
            modelPizze.remove(listaPizze.getSelectedIndex());
            listaPizze.setModel(modelPizze);
        }
    }

    /**
     * Nel pannello "pizze" all'interno della text area "nuova pizza" posso
     * inserirne una e aggiungerla con visibilità anche ai clienti premendo il
     * pulsante "aggiungi".
     */
    private void aggiungiPizza() {
        if (!txtNuovaPizza.getText().isEmpty()) {
            String p = txtNuovaPizza.getText();
            modelPizze.addElement(p);
            listaPizze.setModel(modelPizze);
        }
    }

    /**
     * Richiesta di salvataggio, tramite apposito xml passato alla servlet,
     * dell'ordine, al cui interno ci sono tutte le modifiche e le aggiunte alle
     * pizze e ai condimenti.
     */
    private void salvaModificheMenu() {

        creaXML("pizze");

        creaXML("condimenti");

        invia();
    }

    private void creaXML(String value) {
        try {
            if (value.equals("pizze")) {

                HTTPClient httpClient = new HTTPClient();
                httpClient.setBase(new URL("http://localhost:8080/WebApplication/MenuServlet"));

                ManageXML mngXML = new ManageXML();

                Document data = mngXML.newDocument();
                
                Element rootNode = data.createElement("tipo");
                rootNode.setTextContent("nuovePizze");
               // data.appendChild(rootNode);
                
                //Element pizze = data.createElement("pizze_standard");

                //Element pizza;
                Element nome;

                for (int i = 0; i < listaPizze.getModel().getSize(); i++) {

                    //pizza = data.createElement("pizza_standard");
                    nome = data.createElement("nome");
                    nome.setTextContent(listaPizze.getModel().getElementAt(i));
                    //pizza.appendChild(nome);
                    rootNode.appendChild(nome);

                }

                //data.appendChild(pizze);
                data.appendChild(rootNode);

                /*CAMBIARE LA SERVLET ALLA QUALE INVIARE LE INFORMAZIONI
                        */
                httpClient.execute("MenuServlet", data);

            } else if (value.equals("condimenti")) {

            }
        } catch (Exception e) {
            textArea.setText("eccezione" + e.getMessage());
        }
    }

    private void invia() {
    }
}
