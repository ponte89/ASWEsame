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
public interface Ordine {
    
    public String getId();
    
    public String getUser();
    
    public void addPizza(pizza p);
    
    public ArrayList<pizza> getPizze();
    
    public String getType();
    
    public String getState();
    
    public void setState(String state);
}
