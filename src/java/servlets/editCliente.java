
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dataBase.db_connection;
import dataBase.cliente_tbl;
import classes.Cliente;


@WebServlet(name = "editCliente", urlPatterns = {"/editar_cliente"})
public class editCliente extends HttpServlet {
    db_connection db = new db_connection();
    cliente_tbl clienteTbl = new cliente_tbl();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //setando a codificação dos caracteres para poder receber palavras com caracteres especiais.
                request.setCharacterEncoding("UTF-8");
                int id = Integer.parseInt(request.getParameter("id"));
                
                boolean connectionOpen = db.openConnection();
                                                
                if(connectionOpen){
                    clienteTbl.configConnection(db.getConnection());
                    
                    Cliente cliente = clienteTbl.getClienteById(id);
                    
                    if(cliente != null){
                        request.setAttribute("cliente", cliente);
                        request.getRequestDispatcher("editCliente.jsp").forward(request, response); 
                        db.closeConnection();
                    }else{
                        db.closeConnection();
                        response.sendRedirect("cliente");
                    }
                }
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //setando a codificação dos caracteres para poder receber palavras com caracteres especiais.
                request.setCharacterEncoding("UTF-8");
                boolean connectionOpen = db.openConnection();
                
                
                if(connectionOpen){
                    String name = (String)request.getParameter("name");
                    String tel = (String)request.getParameter("tel");
                    String address = (String)request.getParameter("add");
                    int id = Integer.parseInt(request.getParameter("id"));
                    
                    Cliente cliente = new Cliente(id, name, tel, address);
                    
                    clienteTbl.configConnection(db.getConnection());
                    
                   boolean update =  clienteTbl.updateCliente(cliente);
                   
                   if(update){
                       db.closeConnection();
                       response.sendRedirect("cliente");
                   }else{
                       db.closeConnection();
                       response.sendRedirect("cliente?e=ie");
                   }
                }else{
                    db.closeConnection();
                    response.sendRedirect("cliente?e=ie");
                }
                
                
                
                
                
       
    }

}
