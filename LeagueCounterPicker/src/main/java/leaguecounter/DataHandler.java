/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaguecounter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author miikakoskela
 */
public class DataHandler{
    
    private DatabaseIF database;
    
    public DataHandler() throws SQLException{
        this.database = new DatabaseIF();
    }
    
    public boolean save(String status, String top, String jgl, String mid, String adc, String sup, String pick){
        
        if(status == "Champion" || top == "Champion" || jgl == "Champion" || adc == "Champion" || sup == "Champion" || pick == "Champion"){
            return false;
        }
        
        return true;
    }
    
    public String getPersPick(ArrayList<String> champs){
        return "asd";
    }
    
    public String getNormPick(ArrayList<String> champs){
        double bestPercent = 0.0;
        int bestChamp = 0;
        double compPercent = 0;
        for(int i = 1; i < 153; i++){
            //compPercent = this.database.getPercent(champs, i);
            ArrayList<Double> percents = this.database.getPercent(champs, i);
            compPercent = 0;
            int a = 0;
            for(double percent:percents){
                compPercent += percent;
                a++;
            }
            compPercent = compPercent / a;
            if (compPercent >= bestPercent){
                bestPercent = compPercent;
                bestChamp = i;
            }            
        }
        
        String nameChamp = this.database.getChampName(bestChamp);
        
        return nameChamp + " with " + bestPercent*100 + "%";
    }
    
    public ArrayList<String> listChampions(){
        ArrayList<String> champlist = new ArrayList<>();
        champlist.add("Champion");
        ArrayList<String> list = this.database.getChampList();
        for(String champ:list){
            champlist.add(champ);
        }
        
        return champlist;
    }
}
