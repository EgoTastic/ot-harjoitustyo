/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leaguecounter;
import java.util.*;
import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author miikakoskela
 */
public class DatabaseIF {
    
    private Connection database;
    
    public DatabaseIF() throws SQLException{
        this.database = DriverManager.getConnection("jdbc:sqlite:Picks.db");
        
    }
    
    public ArrayList<Double> getPercent(ArrayList<String> champs, int pick) {
        ArrayList<Double> percents = new ArrayList<>();
        String statement = "";
        boolean first = true;
        
        for(String champion: champs){
            if (first){
                statement = "SELECT " + "\"" + champion + "\"";
                first = false;
            } else {
                statement = statement + ", " + "\"" + champion + "\"";
            }                       
        }
               
        statement = statement + " FROM baseStatistics WHERE id = " + pick;
        try {
            PreparedStatement sqlstatement = this.database.prepareStatement(statement); 
            ResultSet r = sqlstatement.executeQuery();
            int index = 1;
            while(r.next()){
                percents.add(r.getDouble(index));
                index++;
            }
        } catch(SQLException e){
            System.out.println("Error: " +e.getMessage());
        }
        return percents;
    }
    
    public String getChampName(int championNumber){
        try {
            PreparedStatement sqlstatement = this.database.prepareStatement("SELECT champion FROM baseStatistics where id = ?");
            sqlstatement.setInt(1, championNumber);
            return sqlstatement.executeQuery().getString(1);
        } catch(SQLException e){
            return "Error";
        }
        
    }
    
    public ArrayList<String> getChampList(){
        ArrayList<String> champlist = new ArrayList<>();
        try {
            PreparedStatement sqlstatement = this.database.prepareStatement("SELECT champion FROM baseStatistics");
            ResultSet r = sqlstatement.executeQuery();
            while(r.next()){
                champlist.add(r.getString("champion"));
            }
        } catch (SQLException e){
            
        }
        return champlist;
    }
    
}
