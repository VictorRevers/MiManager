package dataBase;

import classes.Cliente;
import java.sql.*;
import java.util.ArrayList;

public class cliente_tbl {
    private Connection db_connection;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public void configConnection(Connection db_connection) {this.db_connection = db_connection;}
    
    String strSQLCmd = "";
    
    public Cliente getClienteById(int id){
        try{
            strSQLCmd = "SELECT * FROM clientes WHERE id='"+id+"'";
            pst = db_connection.prepareStatement(strSQLCmd);
            rs = pst.executeQuery();
            
            
            String name = null;
            String tel = null;
            String address = null;
            
            while(rs.next()){
                name = rs.getString("name");
                tel =  rs.getString("tel");
                address = rs.getString("address");
            }
            
            if(name != null){
                Cliente cliente = new Cliente(id, name, tel, address);
                return cliente;
            }else{
                return null;
            }
                                 
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return null;
        }
        
    }
    
    public int checkTel(String tel){
        try{
            strSQLCmd = "SELECT tel FROM clientes WHERE tel="+tel+"";
            pst = db_connection.prepareStatement(strSQLCmd);
            rs = pst.executeQuery();
            
            if(rs.next()){
                return 1;
            }else{
                return 0;
            }       
            
        }catch(Exception e){
            System.out.println("Erro: "+e);  
            return 2;
        }             
    }
    
    public ArrayList<Cliente> getClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        
        try{
            strSQLCmd = "SELECT * FROM clientes";
            pst = db_connection.prepareStatement(strSQLCmd);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String tel = rs.getString("tel");
                String address = rs.getString("address");
                
                Cliente cliente = new Cliente(id, name, tel, address);
                
                clientes.add(cliente);
            }
            
            return clientes;
        }catch(Exception e){
            System.out.println("Erro: "+ e);
            return null;
        }
    }
    
    public boolean insertCliente(Cliente cliente){
        try{
            strSQLCmd = "INSERT INTO clientes(name, tel, address) VALUES('"+cliente.name+"','"+cliente.tel+"','"+cliente.address+"')";
            pst = db_connection.prepareStatement(strSQLCmd);
            pst.executeUpdate();
        
            return true;
        }catch(Exception e){
            System.out.println("Erro: "+e);
            return false;
        }
        
    }
    
}
