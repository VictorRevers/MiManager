package dataBase;
import classes.Purchase;
import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.DateFormat; 

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
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy") /*(yyyy-mm-dd)*/;  
        
        try{
            strSqlCmd = "SELECT clientes.name, clientes.tel, purchases.data, SUM(purchases.value) AS value FROM purchases " +
            "INNER JOIN clientes ON purchases.id_cliente = clientes.id " +
            "GROUP BY purchases.data, purchases.id_cliente;";       
            pst = db_connection.prepareStatement(strSqlCmd);
            rs = pst.executeQuery();
                    
            while(rs.next()){
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String data = dateFormat.format(rs.getDate("data"));
                float value = rs.getFloat("value");
                
                Purchase purchase = new Purchase(name,tel,data,value);
                
                purchases.add(purchase);
            }
            
            return purchases;
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return null;
        }
    }
    
}
