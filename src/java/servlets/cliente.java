package servlets;
import classes.Cliente;
import dataBase.*;
import java.util.ArrayList;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet(name = "cliente", urlPatterns = {"/cliente"})
public class cliente extends HttpServlet {
  
    db_connection db = new db_connection();
    cliente_tbl clienteTbl = new cliente_tbl();

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //setando a codificação dos caracteres para poder receber palavras com caracteres especiais.
                request.setCharacterEncoding("UTF-8");
                
                boolean connectionOpen = db.openConnection();
                
                
                
                String err = "";               
                
                if(request.getParameter("e") != null){
                    err = (String)request.getParameter("e");
                    
                    if(err.equals("ie")){
                         request.setAttribute("errCad", "Ocorreu um erro no servidor!");
                    }else if(err.equals("cd")){
                          request.setAttribute("errCad", "Erro ao cadastrar! Verifique se o cliente já está cadastrado!");
                    }
                }
                
                if(connectionOpen){
                    clienteTbl.configConnection(db.getConnection());
                    
                    ArrayList<Cliente> clientes = clienteTbl.getClientes();
                    
                    request.setAttribute("clientes", clientes);
                                                     
                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
                    
                    db.closeConnection();
                }
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                //setando a codificação dos caracteres para poder receber palavras com caracteres especiais.
                request.setCharacterEncoding("UTF-8");
                
                if(request.getParameter("edit") != null){                                    
                    response.sendRedirect("editar_cliente?id="+request.getParameter("id"));
                }else{
                    //INSERIR CLIENTE
                    String name = (String)request.getParameter("name");
                    String tel = (String)request.getParameter("tel");
                    String address = (String)request.getParameter("address");
                
                    Cliente cliente = new Cliente(name, tel, address);
                
                    boolean connectionOpen =  db.openConnection();
               
                    if(connectionOpen){
                        clienteTbl.configConnection(db.getConnection());
                   
                        int checkTel = clienteTbl.checkTel(tel);
                   
                        if(checkTel == 0){
                            boolean insert = clienteTbl.insertCliente(cliente);
                   
                            if(insert){
                                db.closeConnection();
                                response.sendRedirect("cliente");
                            }else{
                                db.closeConnection();
                                response.sendRedirect("cliente?e=cd");                      
                            }
                        }else if(checkTel == 1){
                            db.closeConnection();
                            response.sendRedirect("cliente?e=cd");
                        }else{
                            db.closeConnection();
                            response.sendRedirect("cliente?e=ie");
                        }
                    }  
                }
                
                                                       
    }
} 
