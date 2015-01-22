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
/** Classe usata per la gestione degli ordini fatti dai clienti 
 */
public class OrdineCliente implements Ordine{
    
    String id, user, type;
    boolean state;
    ArrayList<pizza> elencoPizze;
    
    /** Setto il nome utente, l'identificativo dell'ordine, il tipo di richiesta
     * e lo stato dell'ordine
     * @param user
     * @param id
     * @param type
     * @param state 
     */
    
    public OrdineCliente(String user, String id, String type, boolean state){
        this.user = user;
        this.id = id;
        this.type = type;
        this.state = state;
    }
    
    /** Ottengo l'identificativo dell'ordine
     * @return String che appunto mi individua l'ordine relativo
     */
    
    @Override
    public String getId() {
        return id;        
    }
    
    /** Ottengo il nome del cliente che ha richiesto l'ordine
     * @return String con il nome dello user
    */

    @Override
    public String getUser() {
        return user;
    }
    
    /** Una volta confermato l'ordine dal cliente, aggiungo le pizze da lui scelte
     * @param p è una pizza o un elenco di pizze
     */
    
    @Override
    public void addPizza(pizza p) {
        elencoPizze.add(p);
    }
    
    /** Ottengo le pizze richieste nell'ordine da parte del cliente
     * @return Arraylist<pizza> che contiene o una pizza o l'elenco di pizze
     * presenti nell'ordine
     */
    
    @Override
    public ArrayList<pizza> getPizze() {
        return elencoPizze;
    }
    
    /** Ottengo che tipo di richiesta ha fatto il cliente
     * @return String che può essere asporto, ritiro o prenotazione
     */

    @Override
    public String getType() {
        return type;
    }

    /** Ottengo lo stato dell'ordine, cioè se è già stato processato o meno
     * @return Boolean che mi dice true o false
     */
    
    @Override
    public boolean getState() {
        return state;
    }
    
    /** Il cuoco setta lo stato dell'ordine, cioè se le pizze sono pronte o meno
     * @param state contiene al suo interno il valore true o false
     */

    @Override
    public void setState(boolean state) {
        this.state = state;
    }
    
}
