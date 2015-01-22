package asw1021.pizze;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
/** Classe usata per la gestione delle pizze di tipo personalizzata e successivamente
 *  anche per la gestione degli ordini.
 */
public class pizzaPersonalizzata implements pizza{

    ArrayList<String> condimenti = new ArrayList<String>();
    ArrayList<String> aggiunte = new ArrayList<String>();
    int nPizze;
    String base, name;
    
    public pizzaPersonalizzata(){
        name = "personalizzata";
    }
    
    /** Il cliente setta il numero delle pizze dello stesso tipo, che vuole ordinare
     * @param n dove n è il numero settato
     */
    
    @Override
    public void setNPizze(int n) {
        this.nPizze = n;
    }
    
    /** Il cliente sceglie quali aggiunte fare alla sua pizza
     * @param aggiunta dove aggiunta può essere base kamut, maxi e doppia margherita
     */
    
    @Override
    public void setAggiunta(String aggiunta) {
        this.aggiunte.add(aggiunta);
    }

    /** Ottengo cosa il cliente ha richiesto come aggiunte
     * @return ArrayList<String> contente una stringa con la/e aggiunta/e
     */
    
    @Override
    public ArrayList<String> getAggiunte() {
        return aggiunte;
    }
    
    /** Ottengo il numero settato dal cliente di pizze dello stesso tipo
     * @return int cioè il numero delle pizze
     */
    
    @Override
    public int getNPizze() {
        return nPizze;
    }
    
    /** Ottengo il parametro personalizzata per gestire così al meglio gli ordini
     * @return String con all'interno il parametro "personalizzata"
     */
    
    public String getName(){
        return name;
    }    
    
    /** Il cliente setta quale base vuole per la sua pizza personalizzata
     * @param base è di tipo string e può essere bianca o margherita
     */
    
    public void setBase(String base){
        this.base = base;
    }
    
    /** Ottengo la base che il cliente ha scelto per la sua pizza personalizzata
     * @return String il cui valore è bianca o margherita
     */
    
    public String getBase(){
        return base;
    } 
    
    /** Il cliente setta quale/i condimento/i vuole per la sua pizza personalizzata
     * @param cond è di tipo string e contiene il nome del condimento richiesto
     */
    
    public void setCondimento(String cond){
        condimenti.add(cond);
    }
    
    /** Ottengo il/i condimento/i che il cliente ha scelto per la sua pizza personalizzata
     * @return ArrayList<String> con all'interno tutti i condimenti richiesti 
     * (al massimo 4)
     */
    
    public ArrayList<String> getCondimenti(){
        return condimenti;
    }
}
