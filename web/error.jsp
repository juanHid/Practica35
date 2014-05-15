<%-- 
    Document   : error
    Created on : 07-may-2014, 9:14:11
    Author     : FO-Mañana
--%>

<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de error</title>
    </head>
    <body>
        <h1>Error page</h1>
        <p>Tipo de error:
          <%= exception.getMessage()%></p>
        <p><%= exception.toString()%></p>
        
        <h2><a href='login.jsp'>Volver</a></h2>
    </body>
</html>
