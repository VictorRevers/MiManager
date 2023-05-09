package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import classes.Product;
import dataBase.*;
import java.util.ArrayList;


@WebServlet(name = "product", urlPatterns = {"/produtos"})
public class product extends HttpServlet {
    
     db_connection db = new db_connection();
     product_tbl productTbl = new product_tbl();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                boolean connectionOpen = db.openConnection();
                
                String err = "";
                
                if(request.getParameter("e") != null){
                    err = (String)request.getParameter("e");
                    
                    if(err.equals("ie")){
                         request.setAttribute("errCad", "Ocorreu um erro no servidor!");
                    }else if(err.equals("cd")){
                          request.setAttribute("errCad", "Erro ao cadastrar! Cadastro não realizado!");
                    }
                }
                
                if(connectionOpen){
                    productTbl.configConnection(db.getConnection());
                    
                    ArrayList<Product> products = productTbl.getProducts();
                    
                    request.setAttribute("products", products);
                    
                    request.getRequestDispatcher("product.jsp").forward(request, response);
                    
                    db.closeConnection();
                                                         
                }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                if(request.getParameter("edit") != null){                                    
                    response.sendRedirect("editar_produto?id="+request.getParameter("id"));
                }else{
                    //INSERIR PRODUTO
                String name = request.getParameter("name");
                float value = Float.parseFloat(request.getParameter("value"));
                
                Product product = new Product(name, value);
                
                boolean connectionOpen = db.openConnection();
                
                if(connectionOpen){
                    productTbl.configConnection(db.getConnection());
                    
                    boolean insert = productTbl.insertProduct(product);
                    
                    if(insert){
                        db.closeConnection();
                        response.sendRedirect("produtos");
                    }else{
                        db.closeConnection();
                        response.sendRedirect("produtos?e=cd");
                    }
                }else{
                    db.closeConnection();
                    response.sendRedirect("produtos?e=ie");
                }
                }
                
                               
    }    

}
