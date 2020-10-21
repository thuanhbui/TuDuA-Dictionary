/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tudua.dictionary;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author AnhThu_dbez
 */
public class Connect {
     /** 
     * Connect to a sample database 
     */  
        private static Connection conn;
    public Connect(){

    }
    public void connect() {
        conn = null;  
        try {  
            // db parameters  
            String url = "jdbc:sqlite:C:/sqlite/dict_hh.db";  
            // create a connection to the database  
            conn = DriverManager.getConnection(url);  
              
            System.out.println("Connection to SQLite has been established.");  
              
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } 
    }  
    
    public  String getExplain(String target) throws SQLException {
        String query = "SELECT description FROM av " + "WHERE word=" + "'" + target + "'";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        
        while (resultSet.next()) {
            return resultSet.getString("description");
        }
        return "Khong tim thay";
    } 
   
    public  ArrayList<Word> getAll() throws SQLException{
        String query = "select * from av";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        ArrayList<Word> result = new ArrayList<>();
        while(resultSet.next()){
            result.add(new Word(resultSet.getString("word"), resultSet.getString("description"),"",""));
        }
        return result;
    }
    
    
    /** 
     * @param args the command line arguments 
     */  
    /*public static void main(String[] args) throws SQLException {
        connect();  
     //   System.out.println(getExplain("hello ' OR 1 == 1"));
        System.out.println(getAll().get(77).getWord_target() + "    " + getAll().get(77).getWord_explain());
    }  */
}