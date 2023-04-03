
package dataBase;

import java.sql.*;

public class db_connection {
    Connection dbConnection;
    
    public boolean openConnection(){
        String url="jdbc:mysql://localhost:3306/mimanager?user=root&password=senhadb&useSSL=false&allowMultiQueries=true&allowPublicKeyRetrieval=true";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            dbConnection = DriverManager.getConnection(url, "root", "senhadb");
            return true;
        }catch(Exception e){
            System.out.println("Erro: "+e.toString());
            return false;
        }
    }
    
    public boolean closeConnection(){
        try{
            dbConnection.close();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    public Connection getConnection() {return dbConnection;}            
}
