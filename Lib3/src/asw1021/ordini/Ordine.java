/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asw1021.ordini;

import asw1021.pizze.pizza;
import java.util.ArrayList;

/**
 *
 * @author Mezzapesa Beatrice, Papini Alessia, Pontellini Lorenzo
 */
/** Quest'interfaccia viene usata come modello base per la gestione degli ordini
 */
public interface Ordine {
    
    /** Ottengo l'identificativo dell'ordine
     * @return String che appunto mi individua l'ordine relativo
     */
    
    public String getId();
    
    /** Ottengo il nome del cliente che ha richiesto l'ordine
     * @return String con il nome dello user
     */
    public String getUser();
    
    /** Una volta confermato l'ordine dal cliente, aggiungo le pizze da lui scelte
     * @param p è una pizza o un elenco di pizze
     */
    
    public void addPizza(pizza p);
    
    /** Ottengo le pizze richieste nell'ordine da parte del cliente
     * @return Arraylist<pizza> che contiene o una pizza o l'elenco di pizze
     * presenti nell'ordine
     */
    
    public ArrayList<pizza> getPizze();
    
    /** Ottengo che tipo di richiesta ha fatto il cliente
     * @return String che può essere asporto, ritiro o prenotazione
     */
    
    public String getType();
    
    /** Ottengo lo stato dell'ordine, cioè se è già stato processato o meno
     * @return Boolean che mi dice true o false
     */
    
    public Boolean getState();
    
    /** Il cuoco setta lo stato dell'ordine, cioè se le pizze sono pronte o meno
     * @param state contiene al suo interno il valore true o false
     */
    
    public void setState(Boolean state);
}
