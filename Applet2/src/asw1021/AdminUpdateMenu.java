/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asw1021;

import asw1021.deploymentUtils.IDeployment;
import javax.swing.JApplet;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTabbedPane;
import java.awt.Container;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/** Applet per modificare il menù 
 * In base al pannello che si va a scegliere,condimenti o pizze, 
 * posso modificare il mio menu aggiungendoci all'interno
 * condimenti o pizze differenti da quelli già presenti; ma posso anche
 * rimuovere ciò che è già presente nel menu.
 * 
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */

public class AdminUpdateMenu extends JApplet implements IDeployment{

    private Container cp;

    private JButton btnAggiungiCond;
    private JButton btnRimuoviPiz;
    private JButton btnRimuoviCond;
    private JButton btnAggiungiPiz;

    private JLabel lblNuovaPizza;
    private JLabel lblNuovaCondimento;
    
    private JScrollPane scrollCondimenti;
    private JScrollPane scrollPizze;
    private JTabbedPane tabbedPane;
    private JPanel panelCondimenti;
    private JPanel panelPizze;
    private JTextField txtNuovaPizza;
    private JTextField txtNuovoCondimento;
  
    private JList<String> listaPizze;
    private DefaultListModel modelPizze;

    private JList<String> listaCondimenti;
    private DefaultListModel modelCondimenti;

    private JButton btnNewButton;
    
    private ArrayList<String> pizzeNuove;

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
     * per l'aggiunta e la rimozione di condimenti o pizze. 
     * Utilizziamo un layout totalmente gestito da noi in cui a ogni 
     * componente inseriamo le coordinate.
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

        listaPizze = new JList();

        pizzeNuove = new ArrayList<String>();
        
        txtNuovoCondimento = new JTextField();
        txtNuovoCondimento.setBounds(6, 24, 108, 28);
        panelCondimenti.add(txtNuovoCondimento);
        txtNuovoCondimento.setColumns(10);

        lblNuovaCondimento = new JLabel("Nuovo Condimento");
        lblNuovaCondimento.setBounds(6, 6, 129, 16);
        panelCondimenti.add(lblNuovaCondimento);

        listaCondimenti = new JList();

        scrollCondimenti = new JScrollPane(listaCondimenti);
        scrollCondimenti.setBounds(206, 6, 205, 191);
        panelCondimenti.add(scrollCondimenti);

        scrollPizze = new JScrollPane(listaPizze);
        scrollPizze.setBounds(206, 6, 205, 191);
        panelPizze.add(scrollPizze);

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
     * Nel pannello "Pizze" e precisamente nel riquadro sulla destra ottengo
     * tutta la lista di pizze che in quell'istante è visibile dalla voce menù
     */
    private void getListaPizze() {
        try {

            HTTPClient httpClient = new HTTPClient();
            httpClient.setBase(new URL(SERVER_BASE_URL));

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
     * Nel pannello "Condimenti" e precisamente nel riquadro sulla destra
     * ottengo tutta la lista di condimenti che in quell'istante sono visibili
     * una volta che richiedo una pizza personalizzata.
     */
    private void getListaCondimenti() {
        try {
            HTTPClient httpClient = new HTTPClient();
            httpClient.setBase(new URL(SERVER_BASE_URL));

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

                if (!(n.getElementsByTagName("nome").item(0).getTextContent()).equals("Nessuna selezione")) {
                    modelCondimenti.addElement(n.getElementsByTagName("nome").item(0).getTextContent());
                }
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
     * Nel pannelo "Pizze" sulla destra trovo le pizze già visibili al cliente e
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
     * Nel pannello "Pizze" all'interno della text area "nuova pizza" posso
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
     * Richiesta di salvataggio, richiamando il metodo inviaDatiXML. 
     */
    private void salvaModificheMenu() {

        inviaDatiXML("pizze");
        
        inviaDatiXML("condimenti");
        
    }
    
    /**
     * Salvo i dati tramite apposito xml (pizze_standard_test.xml & condimenti_test.xml)
     * passato alla servlet, dell'ordine, al cui interno ci sono tutte le 
     * modifiche e le aggiunte alle pizze e ai condimenti.
     * 
     * @param value che rappresenta i dati che elabora in quella chiamata.
     */
    private void inviaDatiXML(String value) {
        try {
            if (value.equals("pizze")) {

                HTTPClient httpClient = new HTTPClient();
                httpClient.setBase(new URL(SERVER_BASE_URL));

                ManageXML mngXML = new ManageXML();

                Document data = mngXML.newDocument();

                Element rootNode = data.createElement("tipo");
                rootNode.setTextContent("nuovePizze");
                Element nome;

                for (int i = 0; i < listaPizze.getModel().getSize(); i++) {

                    nome = data.createElement("nome");
                    nome.setTextContent(listaPizze.getModel().getElementAt(i));
                    rootNode.appendChild(nome);

                }
                data.appendChild(rootNode);

                httpClient.execute("MenuServlet", data);

            } else if (value.equals("condimenti")) {

                HTTPClient httpClient = new HTTPClient();
                httpClient.setBase(new URL(SERVER_BASE_URL));

                ManageXML mngXML = new ManageXML();

                Document data = mngXML.newDocument();

                Element rootNode = data.createElement("tipo");
                rootNode.setTextContent("nuoviCondimenti");
                Element nome;

                for (int i = 0; i < listaCondimenti.getModel().getSize(); i++) {

                    nome = data.createElement("nome");
                    nome.setTextContent(listaCondimenti.getModel().getElementAt(i));
                    rootNode.appendChild(nome);

                }

                data.appendChild(rootNode);

                httpClient.execute("MenuServlet", data);

            }
        } catch (Exception e) {
            
        }

    }
    
}
