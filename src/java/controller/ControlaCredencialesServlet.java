/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import managers.LoggerManager;

public class ControlaCredencialesServlet extends HttpServlet {

    //INIT
    @Override
    public void init() throws ServletException {
        super.init();
        //le paso el path de la aplicacion
        String prefix = getServletContext().getRealPath("/");
        LoggerManager.prefix = prefix;

    }

    //DOGET
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        //obtengo la session que hay
        HttpSession session = request.getSession();
        session.invalidate();
        String path = "/login.jsp";
        request.getRequestDispatcher(path).forward(request, response);

    }

    //DOPOST
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //Guardamos parametros
        String usuario = request.getParameter("usuario");
        String password = request.getParameter("password");
        String path;
        String tipoUsuario;

        if (LoggerManager.DEBUG) {
            LoggerManager.getLog().info("Los parametros recibidos son: " + usuario + " y " + password);
        }
        //obtengo la session que hay
        HttpSession session = request.getSession();
        //Controlamos que existan
        if ((usuario != null && !usuario.equals("")) && (password != null && !password.equals(""))) {

            //Controlo que tipo de usuario
            tipoUsuario = controlaTipoUsuario(usuario, password);

            //Lo almaceno en la sesion
            session.setAttribute("usuario", tipoUsuario);

            //Si es admin lo enviamos a la pagina que se pueden crear coches y motos
            //Si no pues a otra en la que se visualizan los datos
            if (tipoUsuario.equals("admin")) {

                path = "/index.jsp";
            } else {
                path = "/VisualizaDatosServlet";
            }

            //Imprimimos en el log el tipo de usuario
            if (LoggerManager.DEBUG) {
                LoggerManager.getLog().info("El usuario es : " + tipoUsuario);
            }

            //si algun campo del form esta mal    
        } else {

            if (LoggerManager.DEBUG) {
                LoggerManager.getLog().error("Los parametros estan mal definidos");
            }

            path = "/login.jsp";
        }

        //envio a la pagina que sea
        request.getRequestDispatcher(path).forward(request, response);

    } //Acaba el doPost

    // CONTROLA TIPO USUARIO
    private String controlaTipoUsuario(String usuario, String password) {
        String tmp = null;
        //TODO 
        //Codigo para controlar si existe el usuario en BD
        tmp = usuario;
        return tmp;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
