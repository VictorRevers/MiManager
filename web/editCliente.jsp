<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="classes.Cliente" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <title>Editar Cliente</title>
    </head>
    <body>
       <nav class="navbar navbar-expand-lg bg-body-tertiary">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">Editar Cliente</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="http://localhost:8080/MiManager/cliente">Voltar</a>
                            </li>                       
                        </ul>              
                    </div>
            </div>
        </nav><br>
        
        <div class="container text-center">
            <div class="row" id="create" >
                <div class="card">
                    <div class="card-body">
                        <form method="POST" action="editar_cliente">
                            <% Cliente cliente = (Cliente)request.getAttribute("cliente"); %>
                            <input type="text" value="<%=cliente.id%>" name="id" hidden >
                            <div class="input-group">
                                <span class="input-group-text">Nome</span>
                                <input type="text" name="name" aria-label="name" value="<%=cliente.name%>" class="form-control"><br>                                      
                            </div><br>
                            <div class="input-group">
                                <span class="input-group-text">Telefone</span>
                                <input type="text" name="tel" aria-label="tel" value="<%=cliente.tel%>" class="form-control"><br>                                      
                            </div><br>
                            <div class="input-group">
                                <span class="input-group-text">Endereço</span>
                                <input type="text" name="add" aria-label="add" value="<%=cliente.address%>" class="form-control"><br>                                      
                            </div><br>  
                            <div class="d-grid gap-2">
                                <button class="btn btn-primary" type="Submit">Editar</button>
                            </div>
                        </form>
                    </div>                   
                </div>
            </div>
        </div>
    </body>
</html>
