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
                //setando a codificação dos caracteres para poder receber palavras com caracteres especiais.
                request.setCharacterEncoding("UTF-8");
                
                int option = Integer.parseInt(request.getParameter("type"));
                
                boolean connectionOpen = db.openConnection();
                
                if(connectionOpen){
                    if(option == 1){
                        productTbl.configConnection(db.getConnection());
                        
                        String[][] reports = productTbl.totalProductsReport();
                        String[] columns = {"Produto", "Total de vendas"};
                        
                        request.setAttribute("reports", reports);
                        request.setAttribute("columns", columns);
                        request.setAttribute("type", "1");
                        
                        request.getRequestDispatcher("productReport.jsp").forward(request, response);
                        
                        db.closeConnection();                                             
                    }
                    if(option == 2){
                        productTbl.configConnection(db.getConnection());
                        
                        String[][] reports = productTbl.totalPDataReport();
                        String[] columns = {"Produto", "Data", "Total de vendas"};
                        
                        request.setAttribute("reports", reports);
                        request.setAttribute("columns", columns);
                        request.setAttribute("type", "2");
                        
                        request.getRequestDispatcher("productReport.jsp").forward(request, response);
                    }
                }else{
                    db.closeConnection();
                    response.sendRedirect("produtos?e=ie");
                }
        
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               //setando a codificação dos caracteres para poder receber palavras com caracteres especiais.
                request.setCharacterEncoding("UTF-8");
                
               int option = Integer.parseInt(request.getParameter("type"));
               
               response.sendRedirect("relatorioProdutos?type="+option);
    }

  

}
