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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Lorenzo
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

    private void rimuoviCondimento() {
        if (listaCondimenti.getSelectedIndex() != -1) {
            modelCondimenti.remove(listaCondimenti.getSelectedIndex());
            listaCondimenti.setModel(modelCondimenti);
        }
    }

    private void aggiungiCondimento() {

        if (!txtNuovoCondimento.getText().isEmpty()) {
            String p = txtNuovoCondimento.getText();
            modelCondimenti.addElement(p);
            listaCondimenti.setModel(modelCondimenti);
        }
    }

    private void rimuoviPizza() {
        if (listaPizze.getSelectedIndex() != -1) {
            modelPizze.remove(listaPizze.getSelectedIndex());
            listaPizze.setModel(modelPizze);
        }
    }

    private void aggiungiPizza() {
        if (!txtNuovaPizza.getText().isEmpty()) {
            String p = txtNuovaPizza.getText();
            modelPizze.addElement(p);
            listaPizze.setModel(modelPizze);
        }
    }
    
    private void salvaModificheMenu(){
        
    }
}
