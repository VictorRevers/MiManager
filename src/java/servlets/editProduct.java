package servlets;
import classes.Product;

import dataBase.db_connection;
import dataBase.product_tbl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "editProduct", urlPatterns = {"/editar_produto"})
public class editProduct extends HttpServlet {

    db_connection db = new db_connection();
    product_tbl productTbl = new product_tbl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //setando a codificação dos caracteres para poder receber palavras com caracteres especiais.
                request.setCharacterEncoding("UTF-8");
                
                int id = Integer.parseInt(request.getParameter("id"));
                
                boolean connectionOpen = db.openConnection();
                
                if(connectionOpen){
                    productTbl.configConnection(db.getConnection());
                    
                    Product product = productTbl.getProductById(id);
                    
                    if(product != null){
                        request.setAttribute("product", product);
                        request.getRequestDispatcher("editProduct.jsp").forward(request, response); 
                        db.closeConnection();
                    }else{
                        db.closeConnection();
                        response.sendRedirect("produtos");
                    }
                }
                
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                 //setando a codificação dos caracteres para poder receber palavras com caracteres especiais.
                 request.setCharacterEncoding("UTF-8");
                 boolean connectionOpen = db.openConnection();
                 
                 int id = Integer.parseInt(request.getParameter("id"));
                 String name = request.getParameter("name");
                 float value = Float.parseFloat(request.getParameter("value"));
                 
                 Product product = new Product(id, name, value);
                 
                 if(connectionOpen){
                     productTbl.configConnection(db.getConnection());
                     
                     boolean update = productTbl.updateProduct(product);
                     
                     if(update){
                         response.sendRedirect("produtos");
                     }else{
                         response.sendRedirect("produtos?e=ie");
                     }
                 }
        
    }

  
   

}
