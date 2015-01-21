package asw1021.pizze;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lorenzo
 */
public class pizzaPersonalizzata implements pizza{

    ArrayList<String> condimenti = new ArrayList<String>();
    ArrayList<String> aggiunte = new ArrayList<String>();
    int nPizze;
    String base, name;
    
    public pizzaPersonalizzata(){
        name = "personalizzata";
    }
    
    @Override
    public void setNPizze(int n) {
        this.nPizze = n;
    }
    
    @Override
    public void setAggiunta(String aggiunta) {
        this.aggiunte.add(aggiunta);
    }

    @Override
    public ArrayList<String> getAggiunte() {
        return aggiunte;
    }
    @Override
    public int getNPizze() {
        return nPizze;
    }
    
    public String getName(){
        return name;
    }    
    
    public void setBase(String base){
        this.base = base;
    }
    
    public void setCondimento(String cond){
        condimenti.add(cond);
    }
    
    public ArrayList<String> getCondimenti(){
        return condimenti;
    }
}
