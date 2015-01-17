package asw1021;

import asw1021.pizze.pizza;
import asw1021.pizze.pizzaPersonalizzata;
import asw1021.pizze.pizzaStandard;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Lorenzo
 */
public class UserAppletOrder extends JApplet {

    private Container cp;
    
    private JTabbedPane tabbedPane;
    
    private JTextPane textPaneOrdinazione;
    
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
    private JSpinner spinnerStandard;
    private JSpinner spinnerPersonalizzata;
    
    private JCheckBox chckbxBaseKamutS;
    private JCheckBox chckbxGiganteS;
    private JCheckBox chckbxDoppiaMozzarellaS;
    private JCheckBox chckbxBaseKamutP;
    private JCheckBox chckbxGiganteP;
    private JCheckBox chckbxDoppiaMozzarellaP;
    
    private ButtonGroup buttonGroup;
    private JRadioButton rdbtnMargherita;
    private JRadioButton rdbtnBianca;
    
    private ArrayList<pizza> listaOrdinazione;
    
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

    private void initializeGUI() {

        cp = getContentPane();
        cp.setLayout(null);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(11, 6, 270, 390);
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
        btnAggiungiStandard.setBounds(126, 309, 117, 29);
        btnAggiungiStandard.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiPizzaStandard();
            }
        });
        panelStandard.add(btnAggiungiStandard);

        model1 = new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0);
        model2 = new SpinnerNumberModel(1.0, 1.0, 100.0, 1.0);
        
        spinnerStandard = new JSpinner(model1);
        spinnerStandard.setBounds(143, 269, 86, 28);
        panelStandard.add(spinnerStandard);

        label = new JLabel("Numero Pizze:");
        label.setBounds(6, 275, 142, 16);
        panelStandard.add(label);

        label_1 = new JLabel("Aggiunte:");
        label_1.setBounds(6, 186, 61, 16);
        panelStandard.add(label_1);

        chckbxBaseKamutS = new JCheckBox("Base kamut + €2.50");
        chckbxBaseKamutS.setBounds(90, 182, 128, 23);
        panelStandard.add(chckbxBaseKamutS);

        chckbxGiganteS = new JCheckBox("Maxi");
        chckbxGiganteS.setBounds(90, 205, 128, 23);
        panelStandard.add(chckbxGiganteS);

        chckbxDoppiaMozzarellaS = new JCheckBox("Doppia mozzarella + €1.00");
        chckbxDoppiaMozzarellaS.setBounds(90, 227, 153, 23);
        panelStandard.add(chckbxDoppiaMozzarellaS);

        panelPersonalizzata = new JPanel();
        panelPersonalizzata.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        tabbedPane.addTab("Personalizzata", null, panelPersonalizzata, null);

        rdbtnMargherita = new JRadioButton("Margherita");
        rdbtnMargherita.setBounds(92, 6, 99, 23);
        rdbtnMargherita.setSelected(true);

        rdbtnBianca = new JRadioButton("Bianca");
        rdbtnBianca.setBounds(92, 26, 72, 23);
        
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rdbtnMargherita);
        buttonGroup.add(rdbtnBianca);
        
        btnAggiungiPersonalizzata = new JButton("Aggiungi");
        btnAggiungiPersonalizzata.setBounds(107, 314, 117, 29);
        btnAggiungiPersonalizzata.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                aggiungiPizzaPersonalizzata();
            }
        });

        spinnerPersonalizzata = new JSpinner(model2);
        spinnerPersonalizzata.setBounds(161, 277, 63, 28);

        lblNumeroPizze = new JLabel("Numero Pizze:");
        lblNumeroPizze.setBounds(8, 280, 142, 16);

        lblAggiunte = new JLabel("Aggiunte:");
        lblAggiunte.setBounds(8, 206, 61, 16);

        chckbxBaseKamutP = new JCheckBox("Base kamut + €2.50");
        chckbxBaseKamutP.setBounds(71, 202, 128, 23);

        chckbxGiganteP = new JCheckBox("Maxi");
        chckbxGiganteP.setBounds(71, 226, 128, 23);

        chckbxDoppiaMozzarellaP = new JCheckBox("Doppia mozzarella + €1.00");
        chckbxDoppiaMozzarellaP.setBounds(71, 248, 153, 23);

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
        comboBoxCondimento1.setBounds(102, 58, 128, 27);

        comboBoxCondimento2 = new JComboBox();
        comboBoxCondimento2.setBounds(102, 90, 128, 27);

        comboBoxCondimento3 = new JComboBox();
        comboBoxCondimento3.setBounds(102, 123, 128, 27);

        comboBoxCondimento4 = new JComboBox();
        comboBoxCondimento4.setBounds(102, 156, 128, 27);

        
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
        btnConferma.setBounds(427, 367, 141, 29);
        btnConferma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvaOrdinazione();
            }
        });
        cp.add(btnConferma);

        textPaneOrdinazione = new JTextPane();
        textPaneOrdinazione.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 1, true), "Ordinazione", TitledBorder.CENTER, TitledBorder.TOP, null, null));
        textPaneOrdinazione.setEditable(false);
        textPaneOrdinazione.setEnabled(false);
        textPaneOrdinazione.setBackground(new Color(255, 255, 204));
        textPaneOrdinazione.setBounds(293, 25, 275, 332);
        cp.add(textPaneOrdinazione);

        btnAnnulla = new JButton("Annulla Ordine");
        btnAnnulla.setBounds(293, 367, 135, 29);
        btnAnnulla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetOrdinazione();
            }
        });
        cp.add(btnAnnulla);

    }
    
    private void salvaOrdinazione(){
        tabbedPane.setEnabled(false);
        panelStandard.setEnabled(false);
        panelPersonalizzata.setEnabled(false);
    }
    
    private void resetOrdinazione(){
        
        listaOrdinazione.clear();
        
        textPaneOrdinazione.setText("");
    }
    
    private void aggiungiPizzaStandard(){
        
        pizzaStandard pizza = new pizzaStandard();
        
        String select = (String)comboBoxStandard.getSelectedItem();
        
        pizza.setName(select);
        
        int n = ((Double)spinnerStandard.getValue()).intValue();
        
        pizza.setNPizze(n);
        
        String aggiunte="(";
        
        if(chckbxBaseKamutS.isSelected()){
            aggiunte+= " Base Kamut + €2.50";
            pizza.setAggiunta("Base Kamut");
        }
        
        if(chckbxGiganteS.isSelected()){
            aggiunte += " Maxi ";
            pizza.setAggiunta("Maxi");
        }
        
        if(chckbxDoppiaMozzarellaS.isSelected()){
            aggiunte += " Doppia Moz ";
            pizza.setAggiunta("Doppia Mozzarella");
        }
        
        aggiunte += ")";
        
        String ordinazione = ("Pizza: "+select+" n° "+n);
        
        if(aggiunte.equals("()")){
            ordinazione+="\n";
        }else{
            ordinazione+=(" "+aggiunte+"\n");
        }
        String tmp = textPaneOrdinazione.getText();
        tmp+=ordinazione;
        textPaneOrdinazione.setText(tmp);
        
        listaOrdinazione.add(pizza);
    }
    
    private void aggiungiPizzaPersonalizzata(){
        
        pizzaPersonalizzata pizza = new pizzaPersonalizzata();
        
        String ordinazione = "Base Pizza: ";
        if(rdbtnMargherita.isSelected()){
            ordinazione += rdbtnMargherita.getText()+" ";
            pizza.setBase(rdbtnMargherita.getText());
        }else{
            ordinazione += rdbtnBianca.getText()+" ";
            pizza.setBase(rdbtnBianca.getText());
        }
        
        ordinazione += "Condimenti: ";
        
        String select1 = (String)comboBoxCondimento1.getSelectedItem();
        String select2 = (String)comboBoxCondimento2.getSelectedItem();
        String select3 = (String)comboBoxCondimento3.getSelectedItem();
        String select4 = (String)comboBoxCondimento4.getSelectedItem();
        
        if(!select1.equals("nessuna selezione")){
            ordinazione += select1+" ";
            pizza.setAggiunta(select1);
        }
        
        if(!select2.equals("nessuna selezione")){
            ordinazione += select2+" ";
            pizza.setAggiunta(select2);
        }
        
        if(!select3.equals("nessuna selezione")){
            ordinazione += select3+" ";
            pizza.setAggiunta(select3);
        }
        
        if(!select4.equals("nessuna selezione")){
            ordinazione += select4+" ";
            pizza.setAggiunta(select4);
        }
        
        int n = ((Double)spinnerPersonalizzata.getValue()).intValue();
        ordinazione += "n° "+n;
        pizza.setNPizze(n);
        
        
        String aggiunte="(";
        
        if(chckbxBaseKamutP.isSelected()){
            aggiunte+= " Base Kamut ";
            pizza.setAggiunta("Base Kamut");
        }
        
        if(chckbxGiganteP.isSelected()){
            aggiunte += " Maxi ";
            pizza.setAggiunta("Maxi");
        }
        
        if(chckbxDoppiaMozzarellaP.isSelected()){
            aggiunte += " Doppia Moz ";
            pizza.setAggiunta("Doppia Mozzarella");
        }
        
        aggiunte += ")";
        
        //String ordinazione = ("Pizza: "+select+" n° "+n);
        
        if(aggiunte.equals("()")){
            ordinazione+="\n";
        }else{
            ordinazione+=(" "+aggiunte+"\n");
        }
        String tmp = textPaneOrdinazione.getText();
        tmp+=ordinazione;
        textPaneOrdinazione.setText(tmp);
        
        listaOrdinazione.add(pizza);
    }
    
    private void initializeListaCondimenti(){
        ArrayList<String> lista = new ArrayList<String>();
            
            lista.add("Patatine fritte");
            lista.add("Salsiccia");
            lista.add("Salame");
            lista.add("Wurstel");
            lista.add("Peperoni");
            lista.add("Rucola");
            lista.add("Zucchine");
            lista.add("Melanzane");
            lista.add("Pancetta");
            lista.add("Zucca");
            lista.add("Prosciutto crudo");
            lista.add("Prosciutto cotto");
            lista.add("Parmigiano");
            lista.add("Speck");
            lista.add("Mais");
            lista.add("Carciofi");
            lista.add("Tonno");
            lista.add("Cipolla");
            lista.add("Porcini");
            lista.add("Ricotta");
            lista.add("nessuna selezione");
            
            for(String s : lista){
                comboBoxCondimento1.addItem(s);
                comboBoxCondimento2.addItem(s);
                comboBoxCondimento3.addItem(s);
                comboBoxCondimento4.addItem(s);
            }
    }

    private void initializeListaStandard(){
        ArrayList<String> lista = new ArrayList<String>();
            
            lista.add("Margherita");
            lista.add("Marinara");
            lista.add("Cardinale");
            lista.add("Biancaneva");
            lista.add("Capricciosa");
            lista.add("4 Stagioni");
            lista.add("Calabrese");
            lista.add("Wurstel");
            lista.add("Reginella");
            lista.add("Completa");
            lista.add("Tonno");
            lista.add("Tonno e cipolla");
            lista.add("Melanzane");
            lista.add("Peperoni");
            lista.add("Funghi");
            lista.add("Funghi porcini");
            lista.add("Carciofi");
            lista.add("Prosciutto crudo");
            lista.add("Siciliana");
            lista.add("4 Formaggi");
            lista.add("Rucola");
            
            for(String s : lista){
                comboBoxStandard.addItem(s);
            }
    }
    
}
