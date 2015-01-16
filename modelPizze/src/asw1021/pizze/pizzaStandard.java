package asw1021.model;
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
public class pizzaStandard implements pizza{
    
    String name;
    int npizze;
    ArrayList<String> aggiunte = new ArrayList<String>();
    
    public pizzaStandard(){}

    public void setName(String name){
        this.name = name;
    }
    
    @Override
    public void setNPizze(int n) {
        this.npizze = n;
    }

    @Override
    public void setAggiunta(String aggiunta) {
        this.aggiunte.add(aggiunta);
    }
    
    @Override
    public ArrayList<String> getAggiunte(){
        return aggiunte;
    }
    
    public String getName(){
        return name;
    }
    
    @Override
    public int getNPizze(){
        return npizze;
    }
}
