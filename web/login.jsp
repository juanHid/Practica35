<%-- 
    Document   : login
    Created on : 06-may-2014, 10:29:37
    Author     : FO-Mañana
--%>

<%@page errorPage="error.jsp" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Pagina de login</h1>
        
                <form action="login" method="post" >
            <center><table bgcolor="#cccccc" border="0" cellpadding="6"
                           cellspacing="0" width="400"><br/>
                    
                    
                 <tr>
                        <td align="right" valign="top"><b>Usuario: </b></td>
                        <td><input type="text" size="25" name="usuario"> </td>
                    </tr>
                 
                
                    <tr>
                        <td align="right" valign="top"><b>Password: </b></td>
                        <td><input type="password" size="25" name="password"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b></b> </td>
                        <td align="center">&nbsp; 
                            <input type="submit"  value=" Enviar ">
                            <input type="reset" value="Borrar" > 
                        </td>
                    </tr>
                </table>
            </center>
        </form>
        
        
        
    </body>
</html>
