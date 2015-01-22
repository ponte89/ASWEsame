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
 * @author Alessia
 */
public class OrdineCliente implements Ordine{
    
    String id, user, type, state;
    ArrayList<pizza> elencoPizze;
    
    public OrdineCliente(String user, String id, String type, String state){
        this.user = user;
        this.id = id;
        this.type = type;
        this.state = state;
    }
    @Override
    public String getId() {
        return id;        
    }

    @Override
    public String getUser() {
        return user;
    }
    
    @Override
    public void addPizza(pizza p) {
        elencoPizze.add(p);
    }
    
    @Override
    public ArrayList<pizza> getPizze() {
        return elencoPizze;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }
    
}
