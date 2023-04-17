package servlets;

import java.sql.*;
import classes.*;
import dataBase.*;
import java.util.Date;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "purchase", urlPatterns = {"/compras"})
public class purchase extends HttpServlet {

    db_connection db = new db_connection();
    cliente_tbl clienteTbl = new cliente_tbl();
    product_tbl productTbl = new product_tbl();
    purchase_tbl purchaseTbl = new purchase_tbl();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                boolean connectionOpen = db.openConnection();
                
                if(connectionOpen){
                    Connection connection = db.getConnection();
                    
                    clienteTbl.configConnection(connection);
                    productTbl.configConnection(connection);
                    purchaseTbl.configConnection(connection);
                    //purchaseTbl.configConnection(connection);
                    
                    ArrayList<Cliente> clientes = clienteTbl.getClientes();
                    ArrayList<Product> products = productTbl.getProducts();
                    ArrayList<Purchase> purchases = purchaseTbl.getPurchases();
                    
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("products", products);
                    request.setAttribute("purchases", purchases);
                    
                    
                    
                    
                    request.getRequestDispatcher("purchases.jsp").forward(request, response);
                    
                    db.closeConnection();                                             
                }
                
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //INSERIR COMPRA:
                boolean connectionOpen = db.openConnection();
                
               int id_cliente = Integer.parseInt(request.getParameter("cliente"));
               int id_product = Integer.parseInt(request.getParameter("product"));
               float value = Float.parseFloat(request.getParameter("value"));
               String strData = request.getParameter("data");
               
               //SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
               Date data = new Date();
               Exception err = null;
               
               try{
                    //Date dateStrToDt data = new SimpleDateFormat("yyyy-MM-dd").parse(strData);
                    //data = format.format(dateStrToDt);
               }catch(Exception e){
                   System.out.println("Erro: "+e);
                   db.closeConnection();
                   err = e;                               
               }
               
               if(err != null){
                   response.sendRedirect("compras?e=dtp");
               }else{
                    Purchase purchase = new Purchase(id_cliente, id_product, value, strData);
               
                    if(connectionOpen){
                        purchaseTbl.configConnection(db.getConnection());
                   
                        boolean insert = purchaseTbl.insertPurchase(purchase);
                   
                        if(insert){
                            db.closeConnection();
                            response.sendRedirect("compras");                                          
                        }else{
                            db.closeConnection();
                            response.sendRedirect("compras?e=cd");
                        }
                    }else{
                        db.closeConnection();
                        response.sendRedirect("compras?e=ie");
                    }   
               }            
    }
}
