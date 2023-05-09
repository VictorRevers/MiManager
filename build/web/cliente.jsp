<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="classes.Cliente" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <title>Clientes</title>
    </head>
    <body>                
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Clientes</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="http://localhost:8080/MiManager/produtos">Produtos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="http://localhost:8080/MiManager/compras">Compras</a>
                            </li>
                        </ul>              
                    </div>
            </div>
        </nav><br>
                               
        <div class="container text-center"  >
            <div class="row">
                <div class="btn-group" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-outline-primary" onClick="changeView('list')">Listar</button>
                    <button type="button" class="btn btn-outline-primary" onClick="changeView('create')">Cadastrar</button>              
                </div>
            </div><br>
            <div class="row" id="list">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>
                            <th scope="col">Telefone</th>
                            <th scope="col">Endereço</th>
                            <th scope="col">Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            ArrayList<Cliente> clientes = new ArrayList();
                            
                            if(request.getAttribute("clientes") != null){
                                clientes = (ArrayList<Cliente>)request.getAttribute("clientes");
                                
                                for(Cliente cliente : clientes){
                        %>
                                <tr>
                                        <th scope="row"><%=cliente.id%></th>
                                        <td><%=cliente.name%></td>
                                        <td><%=cliente.tel%></td>
                                        <td><%=cliente.address%></td>
                                        <td>                                           
                                            <button type="button" class="btn btn-sm btn-primary">Editar</button>
                                        </td>
                                    </tr>
                                <%}
                            }
                            
                        
                                %>                                           
                    </tbody>
                </table>
            </div>
            
            <div class="row" id="create" hidden="">
                <div class="card">
                    <div class="card-body">
                        <form method="POST" action="cliente">
                            <div class="input-group">
                                <span class="input-group-text">Nome</span>
                                <input type="text" name="name" aria-label="name" class="form-control"><br>                                      
                            </div><br>
                            <div class="input-group">
                                <span class="input-group-text">Telefone</span>
                                <input type="text" name="tel" aria-label="tel" class="form-control"><br>                                      
                            </div><br>
                            <div class="input-group">
                                <span class="input-group-text">Endereço</span>
                                <input type="text" name="address" aria-label="address" class="form-control"><br>                                      
                            </div><br>
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="Submit">Cadastrar</button>
                            </div>
                        </form>
                    </div>                   
                </div>
            </div>
            
            <div class="row" id="reports" hidden="">              
                <div class="card">
                    <div class="card-body">
                        <div class="btn-group" role="group" aria-label="Basic example">                     
                            <button type="button" class="btn btn-outline-primary">Maior valor gasto</button>
                            <button type="button" class="btn btn-outline-primary">Mais comprou</button>
                            <button type="button" class="btn btn-outline-primary">XXX</button>
                        </div>
                    </div>
                </div>                              
            </div>
        </div>
                    
        <script src="./Js/navBtns.js"></script>
        <script>
            <%
                String errCad = (String)request.getAttribute("errCad");
                
                if(errCad != null){
                 %>   
                    window.alert("<%=errCad%>");
                <%}
            %>
                                 
        </script>
    </body>
    
</html>
