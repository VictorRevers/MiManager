package dataBase;
import classes.Product;
import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.text.DateFormat; 

public class product_tbl {
    private Connection db_connection;
    private PreparedStatement pst;
    private ResultSet rs;
    private Utilitarys utilitarys = new Utilitarys();
    
    public void configConnection(Connection db_connection) {this.db_connection = db_connection;}

    String strSqlCmd = "";
    
   public Product getProductById(int id){ 
        try{
            String name = null;
            float value = 0;
            strSqlCmd = "SELECT * FROM products WHERE id='"+id+"'";
            pst = db_connection.prepareStatement(strSqlCmd);
            rs = pst.executeQuery();
            
            /*if(!rs.next()){
                return null;
            }*/
            
            while(rs.next()){
                name = rs.getString("name");
                value =  rs.getFloat("value");             
            }
            
            if(name != null){
                 Product product = new Product(id,name,value);
                 return product;
            }else{
                return null;
            }
            
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return null;
        }
    }
    
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
            System.out.println("Erro: "+e);
            return null;
        }
        
        
    }
    
    public String[][] totalPDataReport(){
        try{
            utilitarys.configConnection(db_connection);
            strSqlCmd = "SELECT products.name, purchases.data, COUNT(purchases.id_product) AS purchases FROM purchases\n" +
                        "INNER JOIN products ON purchases.id_product = products.id\n" +
                        "GROUP BY purchases.data, purchases.id_product\n" +
                        "ORDER BY data desc"; 
            
            int rowCount = utilitarys.getQueryRowCount(strSqlCmd);
            
            
            pst = db_connection.prepareStatement(strSqlCmd);
            rs = pst.executeQuery();
            
            int i = 0;
            int j;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            
            String[][] reports = new String[rowCount][3];
            
            while(rs.next()){
                j = 0;
                
                String name = rs.getString("name");
                String total = Integer.toString(rs.getInt("purchases"));
                
                String data = dateFormat.format(rs.getDate("data"));
                
                reports[i][j] = name;
                j++;
                reports[i][j] = data;
                j++;
                reports[i][j] = total;
                
                i++;
            }
            
            return reports;
                     
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return null;
        }
    }
    
    
}
