package asw1021.pizze;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/** Classe usata per la gestione delle pizze di tipo standard e successivamente
 *  anche per la gestione degli ordini.
 * 
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
public class pizzaStandard implements pizza{
    
    String name;
    int npizze;
    ArrayList<String> aggiunte = new ArrayList<String>();
    
    public pizzaStandard(){}

    /** Il cliente sceglie quale tipo di pizza vuole tra quelle che gli vengono
     *  elencate
     * @param name è il nome della pizza
     */
    public void setName(String name){
        this.name = name;
    }
    
    /** Il cliente setta il numero delle pizze dello stesso tipo, che vuole ordinare
     * @param n dove n è il numero settato
     */
    
    @Override
    public void setNPizze(int n) {
        this.npizze = n;
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
    public ArrayList<String> getAggiunte(){
        return aggiunte;
    }
    
    /** Ottengo il nome della pizza richiesta dal cliente
     * @return String con all'interno il nome della pizza
     */
    
    public String getName(){
        return name;
    }
    
    /** Ottengo il numero settato dal cliente di pizze dello stesso tipo
     * @return int cioè il numero delle pizze
     */
    
    @Override
    public int getNPizze(){
        return npizze;
    }
}
