<%-- 
    Document   : index
    Created on : 23-apr-2014, 9.24.53
    Author     : confalonieri


<jsp:include page="WEB-INF/header.jsp" />
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Practica Clases</h1>
        
  
     
        

        <form action="ServletClaseCreacion" method="post" >
            <center><table bgcolor="#cccccc" border="0" cellpadding="6"
                           cellspacing="0" width="400">

                    <tr>
                        <td align="right" valign="top"><input type="radio" name="vehiculo" value="Coche" checked="checked" /><b>Coche</b></td>
                        <td><input type="radio" name="vehiculo" value="Moto"  /><b>Moto</b> </td>       
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b>Tipo: </b></td>
                        <td><input type="text" size="25" name="tipo"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b>Descripcion: </b></td>
                        <td><input type="text" size="25" name="descripcion"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b>Matricula: </b></td>
                        <td><input type="text" size="25" name="matricula"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b>Color: </b></td>
                        <td><input type="text" size="25" name="color"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b>Fabricante: </b></td>
                        <td><input type="text" size="25" name="fabricante"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b>Velocidad Max.: </b></td>
                        <td><input type="text" size="25" name="velocidadMax"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b>Potencia: </b></td>
                        <td><input type="text" size="25" name="potencia"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b>Nº Cilindros: </b></td>
                        <td><input type="text" size="25" name="cilindros"> </td>
                    </tr>
                    <tr>
                        <td align="right" valign="top"><b></b> </td>
                        <td align="center">&nbsp; 
                            <input type="submit" name="Enviar datos" value=" Enviar ">
                            <input type="reset" value="Borrar" name="B1"> 
                        </td>
                    </tr>
                </table>
            </center>
        </form>
        
        
        
        
        
    </body>
</html>
