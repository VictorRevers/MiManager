<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <title>Relatório de produtos</title>
    </head>
    <body onload="printReport()">
         <div class="row" id="list">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th scope="col"></th>
                            <%
                                if(request.getAttribute("columns") != null){
                                    String[] columns = (String[])request.getAttribute("columns");
                            
                                    for(int i = 0; i<columns.length;i++){
                                
                            %>
                            <th scope="col"><%=columns[i]%></th>
                                    <%}
                                }%>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            if(request.getAttribute("reports") != null){
                                String[][] reports = (String[][])request.getAttribute("reports");
                                int i = 0;
                                int j;
                                
                                while(i<reports.length){
                                    j = 0;
                                
                        %>
                                    <tr>
                                        <th scope="row"></th>
                                        <td><%=reports[i][j]%></td>
                                        <%j++;%>
                                        <td><%=reports[i][j]%></td>                                                                                                                         
                                    </tr>
                                    
                                <%i++;}
                            }%>  
                            
                            
                        
                                                                          
                    </tbody>
                </table>
            </div>
        <script src="./Js/printPage.js"></script>
    </body>
</html>
