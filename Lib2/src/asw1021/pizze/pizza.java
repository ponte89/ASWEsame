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
/** Quest'interfaccia viene usata come modello base per la gestione delle pizze,
 *  poi in base alla pizza che ordino (Standard o Personalizzata) avrò in più 
 *  ulteriori metodi, differenti a seconda del tipo di ordine.
 */

public interface pizza {
    
    /** Il cliente setta il numero delle pizze dello stesso tipo, che vuole ordinare
     * @param n dove n è il numero settato
     */
    public void setNPizze(int n);
    
    /** Il cliente sceglie quali aggiunte fare alla sua pizza
     * @param aggiunta dove aggiunta può essere base kamut, maxi e doppia margherita
     */
    public void setAggiunta(String aggiunta);
    
    /** Ottengo cosa il cliente ha richiesto come aggiunte
     * @return ArrayList<String> contente una stringa con la/e aggiunta/e
     */
    
    public ArrayList<String> getAggiunte();
    
    /** Ottengo il numero settato dal cliente di pizze dello stesso tipo
     * @return int cioè il numero delle pizze
     */
    
    public int getNPizze();
    
    /** Ottengo il nome della pizza nel caso in cui sia una pizza di tipo standard
     *  altrimenti ottengo personalizzata, così da poter meglio gestire gli ordini
     * @return String che rappresenta il tipo della pizza
     */
    
    public String getName();
    
    
}
