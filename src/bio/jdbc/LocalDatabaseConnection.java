/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bio.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author HP
 */
public class LocalDatabaseConnection {
    private static Connection con= null;
    static{
      //  String url = "jdbc:mysql://192.168.222.17:3306/biodb2021";
        String url = "jdbc:mysql://localhost:3306/biodb2021";
        String user = "root";
        String password = "";
        
        try{
            //instanciation of com.mysq.jdbc.Driver
            //This object will registered itself with DriverManager
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            con = DriverManager.getConnection(url, user, password);
        }
        
        catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection(){
        return con;
    }
}
