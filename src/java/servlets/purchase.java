package servlets;

import classes.*;
import dataBase.*;
import java.sql.*;
import java.util.ArrayList;

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
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                boolean connectionOpen = db.openConnection();
                
                if(connectionOpen){
                    Connection connection = db.getConnection();
                    
                    clienteTbl.configConnection(connection);
                    productTbl.configConnection(connection);
                    //purchaseTbl.configConnection(connection);
                    
                    ArrayList<Cliente> clientes = clienteTbl.getClientes();
                    ArrayList<Product> products = productTbl.getProducts();
                    
                    request.setAttribute("clientes", clientes);
                    request.setAttribute("products", products);
                    
                    request.getRequestDispatcher("purchase.jsp").forward(request, response);
                    
                    db.closeConnection();                                             
                }
                
    }
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
}
