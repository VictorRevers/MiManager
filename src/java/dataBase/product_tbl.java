package dataBase;
import classes.Product;
import java.sql.*;
import java.util.ArrayList;

public class product_tbl {
    private Connection db_connection;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public void configConnection(Connection db_connection) {this.db_connection = db_connection;}

    String strSqlCmd = "";
    
    public ArrayList getProducts(){
        ArrayList<Product> products = new ArrayList();
        
        try{
            strSqlCmd = "SELECT * FROM products";
            pst = db_connection.prepareStatement(strSqlCmd);
            rs = pst.executeQuery(); 
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float value = rs.getFloat("value");
                
                Product product = new Product(id, name, value);
                
                products.add(product);                                             
            }
            
            return products;
            
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return null;
        }
    }
    
    public boolean insertProduct(Product product){
        try{
            strSqlCmd = "INSERT INTO products(name, value) VALUES ('"+product.name+"', "+product.value+")";
            pst = db_connection.prepareStatement(strSqlCmd);
            pst.executeUpdate();
            
            return true;
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return false;
        }
    }
    
}
