package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataBase.db_connection;
import dataBase.product_tbl;


@WebServlet(name = "productReport", urlPatterns = {"/relatorioProdutos"})
public class productReport extends HttpServlet {
     db_connection db = new db_connection();
     product_tbl productTbl = new product_tbl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                int option = Integer.parseInt(request.getParameter("type"));
                
                boolean connectionOpen = db.openConnection();
                
                if(connectionOpen){
                    if(option == 1){
                        productTbl.configConnection(db.getConnection());
                        
                        String[][] reports = productTbl.totalProductsReport();
                        String[] columns = {"Produto", "Total de vendas"};
                        
                        request.setAttribute("reports", reports);
                        request.setAttribute("columns", columns);
                        
                        request.getRequestDispatcher("productReport.jsp").forward(request, response);
                        
                        db.closeConnection();                                             
                    }
                }else{
                    db.closeConnection();
                    response.sendRedirect("produtos?e=ie");
                }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               int option = Integer.parseInt(request.getParameter("type"));
               
               response.sendRedirect("relatorioProdutos?type="+option);
    }

  

}