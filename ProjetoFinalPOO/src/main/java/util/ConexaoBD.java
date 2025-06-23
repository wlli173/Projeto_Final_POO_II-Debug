/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Willighan
 */
public class ConexaoBD {
    
    private static final String URL = "jdbc:sqlite:TaskJDB.db";
    
    public static Connection getConnection(){
        
        Connection conn = null;
        
        try {
            
            conn = DriverManager.getConnection(URL);
            
        }catch (SQLException sqlEx) {
            
            System.out.println(sqlEx.getMessage());
            sqlEx.printStackTrace();
            
        }
        
        try {
            
            Statement stmt = conn.createStatement();
            stmt.execute("PRAGMA foreign_keys = ON;");
            
        }catch (SQLException sqlEx) {
            System.out.println("Erro ao ativar foreign keys: " + sqlEx.getMessage());
        }
        
        return conn;
        
    }
    
}
