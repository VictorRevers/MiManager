package dataBase;
import classes.Purchase;
import java.sql.*;
import java.util.ArrayList;

public class purchase_tbl {
    private Connection db_connection;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public void configConnection(Connection db_connection) {this.db_connection = db_connection;}
    
    String strSqlCmd = "";
    
    public boolean insertPurchase(Purchase purchase){
        try{
            strSqlCmd = "INSERT INTO purchases (id_cliente, id_product, data, value) VALUES ("+purchase.id_cliente+", "+purchase.id_product+", DATE '"+purchase.data+"',"+purchase.value+")";
            pst = db_connection.prepareStatement(strSqlCmd);
            pst.executeUpdate();
            
            return true;
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return false;
        }
    }
    
    public ArrayList getPurchases(){
        ArrayList<Purchase> purchases = new ArrayList();
        
        try{
            strSqlCmd = "SELECT clientes.name, clientes.tel, purchases.data, SUM(purchases.value) AS value FROM purchases\n" +
            "INNER JOIN clientes ON purchases.id_cliente = clientes.id\n" +
            "GROUP BY purchases.data, purchases.id_cliente;";       
            pst = db_connection.prepareStatement(strSqlCmd);
            pst.executeQuery();
                    
            return purchases;
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return null;
        }
    }
    
}
