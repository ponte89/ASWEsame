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
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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

    private JTextArea textArea;
    private JScrollPane scroll;

    private JTabbedPane tabbedPane;
    private JPanel panelCondimenti;
    private JPanel panelPizze;
    private JTextField txtNuovaPizza;
    private JTextField txtNuovoCondimento;
    private JTextField txtPrezzoPizza;
    private JTextField txtPrezzoCondimento;

    private JList<String> listaPizze;
    private JList<String> listaCondimenti;
    private JButton btnNewButton;

    @Override
    public void init() {

        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                @Override
                public void run() {
                    initializeGUI();

                    getListaPizze();

                }
            });

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
        panelCondimenti.add(btnAggiungiCond);

        btnRimuoviCond = new JButton("Rimuovi");
        btnRimuoviCond.setBounds(291, 207, 120, 29);
        panelCondimenti.add(btnRimuoviCond);

        panelPizze = new JPanel();
        tabbedPane.addTab("Pizze", null, panelPizze, null);
        panelPizze.setLayout(null);

        btnAggiungiPiz = new JButton("Aggiungi");
        btnAggiungiPiz.setBounds(173, 207, 120, 29);
        panelPizze.add(btnAggiungiPiz);

        btnRimuoviPiz = new JButton("Rimuovi");
        btnRimuoviPiz.setBounds(291, 207, 120, 29);
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

        listaPizze = new JList<String>();
        listaPizze.setBounds(206, 6, 205, 191);
        panelPizze.add(listaPizze);

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

        textArea = new JTextArea();
        
                
        scroll = new JScrollPane(textArea); 
        scroll.setBounds(39, 128, 134, 69);
        panelCondimenti.add(scroll);

        txtPrezzoCondimento = new JTextField();
        txtPrezzoCondimento.setBounds(6, 82, 108, 28);
        panelCondimenti.add(txtPrezzoCondimento);
        txtPrezzoCondimento.setColumns(10);

        listaCondimenti = new JList<String>();
        listaCondimenti.setBounds(206, 6, 205, 191);
        panelCondimenti.add(listaCondimenti);

        btnNewButton = new JButton("CONFERMA");
        btnNewButton.setBounds(16, 285, 415, 29);
        cp.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                

                try {
                    /*String txt = "";
                    txt+="1";
                    //textArea.setText("->" +txt);
                    HTTPClient httpClient = new HTTPClient();
                    txt+="2";
                    //textArea.setText("->" +txt);
                    httpClient.setBase(new URL("http://localhost:8080/WebApplication/MenuServlet"));
                    txt+="3";
                    //textArea.setText("->" +txt);
                    ManageXML mngXML = new ManageXML();
                    txt+="4";
                    //textArea.setText("->" +txt);
                    Document data = mngXML.newDocument();
                    txt+="5";
                    //textArea.setText("->" +txt);
                    data.createElement("ordine_utente");
                    Element user = data.createElement("user");
                    user.setTextContent("pippo");
                    txt+="6";
                    textArea.setText("->" + new URL("http://localhost:8080/WebApplication/MenuServlet"));
                    //Document answer = httpClient.execute("MenuServlet", data);
                    txt+="7";
                    //textArea.setText("->" +txt);
                    //NodeList n = answer.getChildNodes();//getElementsByTagName("pizza standard");*/
                    HTTPClient httpClient = new HTTPClient();
                    httpClient.setBase(new URL("http://localhost:8080/WebApplication/MenuServlet"));

                    ManageXML mngXML = new ManageXML();
                    Document data = mngXML.newDocument();
                    Document answer = httpClient.execute("MenuServlet", data);
                    
                    textArea.setText("Applet OK");
                    
                } catch (Exception x) {
                    textArea.setText(x.getMessage());
                    Logger.getLogger(AdminUpdateMenu.class.getName()).log(Level.SEVERE, null, e);
                }
            }

        });
    }

    private void getListaPizze() {
        try {/*
        
             System.out.println("lunghezza pizze: "+n.getLength());
             String s = txtPrezzoPizza.getText();
             s += n.getLength();
             txtPrezzoPizza.setText(s);*/

        } catch (Exception e) {
            System.err.println("--> " + e.getMessage());
        }
    }
}
