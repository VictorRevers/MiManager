package dataBase;
import java.sql.*;

public class Utilitarys {
    private Connection db_connection;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public void configConnection(Connection db_connection) {this.db_connection = db_connection;}
    String strSqlCmd = "";
    
    int getQueryRowCount(String query){       
        try{           
            pst = db_connection.prepareStatement(query);
            rs = pst.executeQuery();
            
            int rowCount = 0;
            while(rs.next()){
                rowCount++;
            }
            return rowCount;
            
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return 0;
        }
    }
}
