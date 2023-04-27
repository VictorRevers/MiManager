package dataBase;
import classes.Product;
import java.sql.*;
import java.util.ArrayList;

public class product_tbl {
    private Connection db_connection;
    private PreparedStatement pst;
    private ResultSet rs;
    private Utilitarys utilitarys = new Utilitarys();
    
    public void configConnection(Connection db_connection) {this.db_connection = db_connection;}

    String strSqlCmd = "";
    
   /* public float getProductValue(int id){ 
        try{
            strSqlCmd = "SELECT value FROM products WHERE id='"+id+"'";
            pst = db_connection.prepareStatement(strSqlCmd);
            rs = pst.executeQuery();
            
            rs.next();
            float value =  rs.getFloat("value");
            
            return value;
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return 0;
        }
    }*/
    
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
    
    //RELATÓRIOS:
    public String[][] totalProductsReport(){
        try{
            utilitarys.configConnection(db_connection);
            strSqlCmd = "SELECT products.name, COUNT(purchases.id_product) AS purchases FROM purchases\n" +
                        "INNER JOIN products ON purchases.id_product = products.id\n" +
                        "GROUP BY purchases.id_product";
            
            int rowCount = utilitarys.getQueryRowCount(strSqlCmd);
            
            pst = db_connection.prepareStatement(strSqlCmd);
            rs = pst.executeQuery();
            
            int i = 0;
            int j;
            
            /*if(rs.last()){
                rowCount = rs.getRow();
                rs.beforeFirst();
            }*/          
                    
            String[][] reports = new String[rowCount][2];
            
            while(rs.next()){
                j = 0;
                
                String name = rs.getString("name");
                String total = Integer.toString(rs.getInt("purchases"));
                
                reports[i][j] = name;
                j++;
                reports[i][j] = total;
                
                i++;
            }
            
            return reports;
        }catch(Exception e){
            System.out.println("Erro: "+ e);
            return null;
        }
        
        
    }
    
    
}
