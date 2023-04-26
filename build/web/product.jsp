<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="classes.Product" %>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <title>Produtos</title>
    </head>
    <body>                
        <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Produtos</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                             <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="http://localhost:8080/MiManager/cliente">Clientes</a>
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
                    <button type="button" class="btn btn-outline-primary" onClick="changeView('reports')">Relatórios</button>
                </div>
            </div><br>
            <div class="row" id="list">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>                
                            <th scope="col">Valor</th>
                            <th scope="col">Ação</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            //ArrayList<Product> products = new ArrayList();
                            
                            if(request.getAttribute("products") != null){
                                ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
                                
                                for(Product product : products){
                        %>
                                <tr>
                                        <th scope="row"><%=product.id%></th>
                                        <td><%=product.name%></td>
                                        <td><%=product.value%></td>                                       
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
                        <form method="POST" action="produtos">
                            <div class="input-group">
                                <span class="input-group-text">Nome</span>
                                <input type="text" name="name" aria-label="name" class="form-control"><br>                                      
                            </div><br>
                            <div class="input-group">
                                <span class="input-group-text">Valor</span>
                                <input type="text" name="value" aria-label="value" class="form-control"><br>                                      
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
                        <form method="POST" action="">
                            <div class="input-group">
                                <span class="input-group-text">Tipo:</span>
                                <select class="form-select" name="type" id="type" aria-label="Default select example" onchange="formOpen()">
                                    <option selected></option>
                                    <option value="1">Total de vendas por produto</option>
                                    <option value="2">Total vendido por data</option>
                                    <option value="3">Total vendido por data especifica</option>
                                    <!--<option value="4">Total vendido por ano</option>
                                    <option value="5">Produto especifico</option>-->
                                </select>                             
                            </div><br>
                            <div class="input-group">
                                <span class="input-group-text" >Data</span>
                                <input type="date" name="data" id="data" aria-label="data" class="form-control" disabled><br>                                      
                            </div><br> 
                            <div class="input-group">
                                <span class="input-group-text">Produto</span>
                                <select class="form-select" name="product" id="product" disabled>
                                    <option selected></option>
                                     <%
                            
                            
                                    if(request.getAttribute("products") != null){
                                     ArrayList<Product> products = (ArrayList<Product>)request.getAttribute("products");
                                
                                    for(Product product : products){
                                    %>
                                    <option value="<%=product.id%>"><%=product.name%></option>
                                <%}
                            }
                            
                        
                                %>           
                                </select>
                            </div>
                        </form>
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
            
           
            
            function formOpen(){
                let selected_value =  document.getElementById("type").value;
                let data_inpt = document.getElementById("data");
                let product_inpt = document.getElementById("product");
                
                if (selected_value === "3"){
                    data_inpt.disabled = false;
                }
                //continua...
            }
            
            
         
        </script>
    </body>
    
</html>
