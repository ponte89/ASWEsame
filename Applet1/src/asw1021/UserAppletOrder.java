package asw1021;

import asw1021.deploymentUtils.IDeployment;
import asw1021.pizze.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/** Applet per effettuare ordini. 
 * Visualizzazione in base al pannello scelto, standard o personalizzata, 
 * delle possibili pizze da voler ordinare. Nel pannello "Standard" posso 
 * selezionare le pizze tradizionali presenti e visibili all'interno della
 * pagina menu; mentre nel pannello "Personalizzata" posso riadattare la
 * pizza a seconda delle mie preferenze partendo dalla base, bianca o 
 * margherita, e dai possibili condimenti da aggiungere sopra. In entrambi
 * i pannelli in più ho la possibilità di scegliere se la mia pizza la
 * volessi a base kamut o con doppia mozzarella o di dimensioni maxi; e nel caso
 * in cui avessi più pizze dello stesso tipo è stato inserito appositamente un
 * contatore da incrementare per non dover compilare la stessa procedura n
 * volte.
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
public class UserAppletOrder extends JApplet implements IDeployment{

    public UserAppletOrder() {
    }

    private Container cp;

    private JTabbedPane tabbedPane;

    private JScrollPane scroll;
    private JTextArea textPaneOrdinazione;

    private JPanel panelStandard;
    private JPanel panelPersonalizzata;

    private JLabel lblPizza;
    private JLabel label;
    private JLabel label_1;
    private JLabel lblNumeroPizze;
    private JLabel lblAggiunte;
    private JLabel lblBase;
    private JLabel lblCondimento1;
    private JLabel lblCondimento2;
    private JLabel lblCondimento3;
    private JLabel lblCondimento4;
    private JLabel lblPosti;

    private JComboBox comboBoxStandard;
    private JComboBox comboBoxCondimento1;
    private JComboBox comboBoxCondimento2;
    private JComboBox comboBoxCondimento3;
    private JComboBox comboBoxCondimento4;

    private JButton btnAggiungiPersonalizzata;
    private JButton btnAggiungiStandard;
    private JButton btnConferma;
    private JButton btnAnnulla;

    private SpinnerNumberModel model1;
    private SpinnerNumberModel model2;
    private SpinnerNumberModel model3;
    private JSpinner spinnerStandard;
    private JSpinner spinnerPersonalizzata;
    private JSpinner spinnerPosti;

    private JCheckBox chckbxBaseKamutS;
    private JCheckBox chckbxGiganteS;
    private JCheckBox chckbxDoppiaMozzarellaS;
    private JCheckBox chckbxBaseKamutP;
    private JCheckBox chckbxGiganteP;
    private JCheckBox chckbxDoppiaMozzarellaP;

    private ButtonGroup buttonGroup;
    private JRadioButton rdbtnMargherita;
    private JRadioButton rdbtnBianca;

    private ButtonGroup buttonGroup2;
    private JRadioButton rdbtnRitiro;
    private JRadioButton rdbtnAsporto;
    private JRadioButton rdbtnPrenotazione;
    private int nPosti;

    private ArrayList<pizza> listaOrdinazione;

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

            initializeListaCondimenti();
            initializeListaStandard();

            listaOrdinazione = new ArrayList<pizza>();

        } catch (Exception ex) {
            System.err.println(ex);
        }
    }

    /**
     * Definizione dell' interfaccia grafica con appropriati componenti grafici
     * per la visualizzazione di tutte le possibili informazioni riguardanti gli
     * ordini. Utilizziamo un Layout totalmente gestito da noi in cui a ogni
     * componente inseriamo le coordinate.
     */
    private void initializeGUI() {

        cp = getContentPane();
        cp.setLayout(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(6, 6, 314, 390);
        cp.add(tabbedPane);

        panelStandard = new JPanel();
        tabbedPane.addTab("Standard", null, panelStandard, null);
        panelStandard.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        panelStandard.setLayout(null);

        lblPizza = new JLabel("Pizza:");
        lblPizza.setBounds(6, 25, 61, 16);
        panelStandard.add(lblPizza);

        comboBoxStandard = new JComboBox();
        comboBoxStandard.setBounds(57, 21, 186, 27);
        panelStandard.add(comboBoxStandard);

        btnAggiungiStandard = new JButton("Aggiungi");
        btnAggiungiStandard.setBounds(152, 309, 117, 29);
        btnAggiungiStandard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiPizzaStandard();
            }
        });
        panelStandard.add(btnAggiungiStandard);

        model1 = new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0);
        model2 = new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0);
        model3 = new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0);

        spinnerStandard = new JSpinner(model1);
        spinnerStandard.setBounds(102, 269, 86, 28);
        panelStandard.add(spinnerStandard);

        label = new JLabel("Numero Pizze:");
        label.setBounds(6, 275, 142, 16);
        panelStandard.add(label);

        label_1 = new JLabel("Aggiunte:");
        label_1.setBounds(6, 186, 61, 16);
        panelStandard.add(label_1);

        chckbxBaseKamutS = new JCheckBox("Base kamut");
        chckbxBaseKamutS.setBounds(67, 182, 164, 23);
        panelStandard.add(chckbxBaseKamutS);

        chckbxGiganteS = new JCheckBox("Maxi");
        chckbxGiganteS.setBounds(67, 204, 128, 23);
        panelStandard.add(chckbxGiganteS);

        chckbxDoppiaMozzarellaS = new JCheckBox("Doppia mozzarella");
        chckbxDoppiaMozzarellaS.setBounds(67, 228, 220, 23);
        panelStandard.add(chckbxDoppiaMozzarellaS);

        panelPersonalizzata = new JPanel();
        panelPersonalizzata.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        tabbedPane.addTab("Personalizzata", null, panelPersonalizzata, null);

        rdbtnMargherita = new JRadioButton("Margherita");
        rdbtnMargherita.setBounds(53, 6, 99, 23);
        rdbtnMargherita.setSelected(true);

        rdbtnBianca = new JRadioButton("Bianca");
        rdbtnBianca.setBounds(53, 27, 72, 23);

        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnMargherita);
        buttonGroup.add(rdbtnBianca);

        btnAggiungiPersonalizzata = new JButton("Aggiungi");
        btnAggiungiPersonalizzata.setBounds(170, 309, 117, 29);
        btnAggiungiPersonalizzata.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiPizzaPersonalizzata();
            }
        });

        spinnerPersonalizzata = new JSpinner(model2);
        spinnerPersonalizzata.setBounds(107, 274, 63, 28);

        lblNumeroPizze = new JLabel("Numero Pizze:");
        lblNumeroPizze.setBounds(8, 280, 142, 16);

        lblAggiunte = new JLabel("Aggiunte:");
        lblAggiunte.setBounds(8, 206, 61, 16);

        chckbxBaseKamutP = new JCheckBox("Base kamut");
        chckbxBaseKamutP.setBounds(71, 202, 216, 23);

        chckbxGiganteP = new JCheckBox("Maxi");
        chckbxGiganteP.setBounds(71, 226, 128, 23);

        chckbxDoppiaMozzarellaP = new JCheckBox("Doppia mozzarella");
        chckbxDoppiaMozzarellaP.setBounds(71, 248, 203, 23);

        lblBase = new JLabel("Base:");
        lblBase.setBounds(8, 10, 61, 16);

        lblCondimento1 = new JLabel("Condimento 1:");
        lblCondimento1.setBounds(6, 62, 104, 16);

        lblCondimento2 = new JLabel("Condimento 2:");
        lblCondimento2.setBounds(6, 94, 104, 16);

        lblCondimento3 = new JLabel("Condimento 3:");
        lblCondimento3.setBounds(7, 127, 93, 16);

        lblCondimento4 = new JLabel("Condimento 4:");
        lblCondimento4.setBounds(7, 160, 93, 16);

        comboBoxCondimento1 = new JComboBox();
        comboBoxCondimento1.setBounds(102, 58, 172, 27);

        comboBoxCondimento2 = new JComboBox();
        comboBoxCondimento2.setBounds(102, 90, 172, 27);

        comboBoxCondimento3 = new JComboBox();
        comboBoxCondimento3.setBounds(102, 123, 172, 27);

        comboBoxCondimento4 = new JComboBox();
        comboBoxCondimento4.setBounds(102, 156, 172, 27);

        panelPersonalizzata.setLayout(null);
        panelPersonalizzata.add(rdbtnMargherita);
        panelPersonalizzata.add(rdbtnBianca);
        panelPersonalizzata.add(btnAggiungiPersonalizzata);
        panelPersonalizzata.add(spinnerPersonalizzata);
        panelPersonalizzata.add(lblNumeroPizze);
        panelPersonalizzata.add(lblAggiunte);
        panelPersonalizzata.add(chckbxBaseKamutP);
        panelPersonalizzata.add(chckbxGiganteP);
        panelPersonalizzata.add(chckbxDoppiaMozzarellaP);
        panelPersonalizzata.add(lblBase);

        panelPersonalizzata.add(comboBoxCondimento1);
        panelPersonalizzata.add(comboBoxCondimento2);
        panelPersonalizzata.add(comboBoxCondimento3);
        panelPersonalizzata.add(comboBoxCondimento4);

        panelPersonalizzata.add(lblCondimento1);
        panelPersonalizzata.add(lblCondimento2);
        panelPersonalizzata.add(lblCondimento3);
        panelPersonalizzata.add(lblCondimento4);

        btnConferma = new JButton("Conferma Ordine");
        btnConferma.setBounds(467, 380, 141, 29);
        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaOrdinazione();
            }
        });
        cp.add(btnConferma);

        textPaneOrdinazione = new JTextArea();
        textPaneOrdinazione.setBackground(new Color(255, 255, 204));
        textPaneOrdinazione.setEditable(false);

        scroll = new JScrollPane(textPaneOrdinazione);

        scroll.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Ordinazione", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        scroll.setEnabled(false);
        scroll.setBackground(new Color(255, 255, 204));
        scroll.setBounds(322, 21, 275, 231);
        cp.add(scroll);

        btnAnnulla = new JButton("Annulla Ordine");
        btnAnnulla.setBounds(337, 380, 135, 29);
        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetOrdinazione();
            }
        });
        cp.add(btnAnnulla);

        rdbtnRitiro = new JRadioButton("Ritiro");
        rdbtnRitiro.setBounds(332, 320, 70, 25);
        rdbtnRitiro.setSelected(true);
        rdbtnRitiro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                spinnerPosti.setEnabled(false);

            }
        });

        rdbtnAsporto = new JRadioButton("Asporto");
        rdbtnAsporto.setBounds(332, 299, 85, 23);
        rdbtnAsporto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                spinnerPosti.setEnabled(false);

            }
        });

        rdbtnPrenotazione = new JRadioButton("Prenotazione");
        rdbtnPrenotazione.setBounds(332, 280, 130, 23);
        rdbtnPrenotazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                spinnerPosti.setEnabled(true);

            }
        });

        buttonGroup2 = new ButtonGroup();
        buttonGroup2.add(rdbtnRitiro);
        buttonGroup2.add(rdbtnAsporto);
        buttonGroup2.add(rdbtnPrenotazione);

        cp.add(rdbtnRitiro);
        cp.add(rdbtnAsporto);
        cp.add(rdbtnPrenotazione);

        spinnerPosti = new JSpinner(model3);
        spinnerPosti.setEnabled(false);
        spinnerPosti.setBounds(484, 278, 45, 28);
        getContentPane().add(spinnerPosti);

        lblPosti = new JLabel("Posti");
        lblPosti.setBounds(484, 264, 61, 16);
        getContentPane().add(lblPosti);

    }

    /**
     * Richiesta di salvataggio, tramite apposito xml passato alla servlet,
     * dell'ordine, al cui interno ci sono tutte le pizze aggiunte e confermate
     * dal cliente.
     */
    private void salvaOrdinazione() {
        if (textPaneOrdinazione.getText().equals("")) {
            textPaneOrdinazione.setText("Scegliere almeno una pizza");
        } else {
            tabbedPane.setEnabled(false);
            panelStandard.setEnabled(false);
            panelPersonalizzata.setEnabled(false);
            btnAggiungiPersonalizzata.setEnabled(false);
            btnAggiungiStandard.setEnabled(false);
            btnConferma.setEnabled(false);
            String typeDelivery = "";
            if (rdbtnRitiro.isSelected()) {
                typeDelivery = "ritiro";
            } else if (rdbtnAsporto.isSelected()) {
                typeDelivery = "asporto";
            } else if (rdbtnPrenotazione.isSelected()) {
                typeDelivery = "prenotazione";
                nPosti = ((Double) spinnerPosti.getValue()).intValue();
            }
            try {
                HTTPClient httpClient = new HTTPClient();
                httpClient.setBase(new URL(SERVER_BASE_URL));
                ManageXML mngXML = new ManageXML();
                String idUser = getParameter("user");
                Document data = mngXML.newDocument();
                Element rootFile = data.createElement("ordini_utente");
                Element root = data.createElement("ordine_utente");
                Element user = data.createElement("user");
                user.setTextContent(idUser);
                Element done = data.createElement("done");
                boolean state = false;
                done.setTextContent("" + state);
                Element id = data.createElement("id");
                String idOrdine = new SimpleDateFormat("dd-M-yyyy hh:mm:ss").format(new Date());
                id.setTextContent(idUser + idOrdine);
                Element type = data.createElement("tipo_ordine");
                Element posti = null;
                          
                type.setTextContent(typeDelivery);
                root.appendChild(user);
                root.appendChild(id);
                root.appendChild(type);
                if (typeDelivery.equals("prenotazione")) {
                    posti = data.createElement("posti");
                    posti.setTextContent("" + nPosti);
                    root.appendChild(posti);
                }
                root.appendChild(done);

                Element typePizza, name, number, plus, base, condimento;
                String plusString;
                pizza newPizza = null;
                for (int i = 0; i < listaOrdinazione.size(); i++) {
                    plusString = " ";
                    newPizza = listaOrdinazione.get(i);
                    if (!newPizza.getName().equals("personalizzata")) {
                        typePizza = data.createElement("pizzaS");
                    } else {
                        typePizza = data.createElement("pizzaP");
                    }
                    name = data.createElement("nome_pizza");
                    name.setTextContent(newPizza.getName());
                    number = data.createElement("numero_pizze");
                    number.setTextContent("" + newPizza.getNPizze());
                    plus = data.createElement("plus");
                    for (int j = 0; j < newPizza.getAggiunte().size(); j++) {
                        plusString += " " + newPizza.getAggiunte().get(j);
                    }
                    if (plusString.equals("")) {
                        plusString = "Nessuna selezione";
                    }
                    plus.setTextContent(plusString);

                    typePizza.appendChild(name);
                    typePizza.appendChild(number);
                    typePizza.appendChild(plus);
                    if (newPizza.getName().equals("personalizzata")) {
                        pizzaPersonalizzata pizzaPers = (pizzaPersonalizzata) newPizza;
                        base = data.createElement("base");
                        base.setTextContent(pizzaPers.getBase());
                        typePizza.appendChild(base);
                        for (int k = 0; k < pizzaPers.getCondimenti().size(); k++) {
                            condimento = data.createElement("condimento");
                            condimento.setTextContent(pizzaPers.getCondimenti().get(k));
                            typePizza.appendChild(condimento);
                        }
                    }

                    root.appendChild(typePizza);
                }

                rootFile.appendChild(root);
                data.appendChild(rootFile);

                Document answer = httpClient.execute("ManageOrderService?target=push", data);
                if (answer.getDocumentElement().getTagName().equals("ok")) {
                    textPaneOrdinazione.setText("Ordine Confermato");
                } else {
                    textPaneOrdinazione.setText("Ordine non effettuato");
                }

            } catch (Exception e) {
                Logger.getLogger(UserAppletOrder.class.getName()).log(Level.SEVERE, null, e);
                textPaneOrdinazione.setText("Errore");
            }
        }
    }

    /**
     * Nel momento in cui volessi annullare l'ordine per eventuali errori
     * commessi, basta premere il pulsante "Annulla Ordine" che mi resetta il
     * contenuto della mia JTextArea.
     */
    private void resetOrdinazione() {

        listaOrdinazione.clear();
        textPaneOrdinazione.setText("");
    }

    /**
     * Una volta decisa la pizza da ordinare, premendo "Aggiungi", questa viene
     * inserita nella JTextArea e poi posso continuare a completare l'ordine
     * con le pizze che voglio richiedere.
     */
    private void aggiungiPizzaStandard() {

        if (textPaneOrdinazione.getText().equals("Scegliere almeno una pizza")) {
            resetOrdinazione();
        }

        pizzaStandard pizza = new pizzaStandard();

        String select = (String) comboBoxStandard.getSelectedItem();

        pizza.setName(select);

        int n = ((Double) spinnerStandard.getValue()).intValue();

        pizza.setNPizze(n);

        String aggiunte = "(";

        if (chckbxBaseKamutS.isSelected()) {
            aggiunte += " Base Kamut ";
            pizza.setAggiunta("Base Kamut");
        }

        if (chckbxGiganteS.isSelected()) {
            aggiunte += " Maxi ";
            pizza.setAggiunta("Maxi");
        }

        if (chckbxDoppiaMozzarellaS.isSelected()) {
            aggiunte += " Doppia Moz ";
            pizza.setAggiunta("Doppia Mozzarella");
        }

        aggiunte += ")";

        String ordinazione = ("Pizza: " + select + " n° " + n);

        if (aggiunte.equals("()")) {
            ordinazione += "\n";
        } else {
            ordinazione += (" " + aggiunte + "\n");
        }
        String tmp = textPaneOrdinazione.getText();
        tmp += ordinazione;
        textPaneOrdinazione.setText(tmp);

        listaOrdinazione.add(pizza);
    }

    /**
     * Una volta decisa la pizza da ordinare, premendo "Aggiungi", questa viene
     * inserita nella JTextArea e poi posso continuare a completare l'ordine
     * con le pizze che voglio richiedere.
     */
    private void aggiungiPizzaPersonalizzata() {

        if (textPaneOrdinazione.getText().equals("Scegliere almeno una pizza")) {
            resetOrdinazione();
        }

        pizzaPersonalizzata pizza = new pizzaPersonalizzata();

        String ordinazione = "Base Pizza: ";
        if (rdbtnMargherita.isSelected()) {
            ordinazione += rdbtnMargherita.getText() + " ";
            pizza.setBase(rdbtnMargherita.getText());
        } else {
            ordinazione += rdbtnBianca.getText() + " ";
            pizza.setBase(rdbtnBianca.getText());
        }

        ordinazione += "Condimenti: ";

        String select1 = (String) comboBoxCondimento1.getSelectedItem();
        String select2 = (String) comboBoxCondimento2.getSelectedItem();
        String select3 = (String) comboBoxCondimento3.getSelectedItem();
        String select4 = (String) comboBoxCondimento4.getSelectedItem();

        if (!select1.equals("nessuna selezione")) {
            ordinazione += select1 + " ";
            pizza.setCondimento(select1);
        }

        if (!select2.equals("nessuna selezione")) {
            ordinazione += select2 + " ";
            pizza.setCondimento(select2);
        }

        if (!select3.equals("nessuna selezione")) {
            ordinazione += select3 + " ";
            pizza.setCondimento(select3);
        }

        if (!select4.equals("nessuna selezione")) {
            ordinazione += select4 + " ";
            pizza.setCondimento(select4);
        }

        int n = ((Double) spinnerPersonalizzata.getValue()).intValue();
        ordinazione += "n° " + n;
        pizza.setNPizze(n);

        String aggiunte = "(";

        if (chckbxBaseKamutP.isSelected()) {
            aggiunte += " Base Kamut ";
            pizza.setAggiunta("Base Kamut");
        }

        if (chckbxGiganteP.isSelected()) {
            aggiunte += " Maxi ";
            pizza.setAggiunta("Maxi");
        }

        if (chckbxDoppiaMozzarellaP.isSelected()) {
            aggiunte += " Doppia Moz ";
            pizza.setAggiunta("Doppia Mozzarella");
        }

        aggiunte += ")";

        if (aggiunte.equals("()")) {
            ordinazione += "\n";
        } else {
            ordinazione += (" " + aggiunte + "\n");
        }
        String tmp = textPaneOrdinazione.getText();
        tmp += ordinazione;
        textPaneOrdinazione.setText(tmp);

        listaOrdinazione.add(pizza);
    }

    /**
     * Inizializzo i condimenti possibili da scegliere per la mia pizza
     * personalizzata.
     */
    private void initializeListaCondimenti() {
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
            for (int i = 0; i < l.getLength(); i++) {
                Element n = (Element) l.item(i);

                comboBoxCondimento1.addItem(n.getElementsByTagName("nome").item(0).getTextContent());
                comboBoxCondimento2.addItem(n.getElementsByTagName("nome").item(0).getTextContent());
                comboBoxCondimento3.addItem(n.getElementsByTagName("nome").item(0).getTextContent());
                comboBoxCondimento4.addItem(n.getElementsByTagName("nome").item(0).getTextContent());

            }

        } catch (Exception e) {
            System.err.println("--> " + e.getMessage());
        }
    }

    /**
     * Inizializzo le pizze standard presenti anche all'interno del nostro menu.
     */
    private void initializeListaStandard() {

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

            for (int i = 0; i < l.getLength(); i++) {
                Element n = (Element) l.item(i);
                comboBoxStandard.addItem(n.getElementsByTagName("nome").item(0).getTextContent());
            }

        } catch (Exception e) {
            System.err.println("--> " + e.getMessage());
        }
    }
}
