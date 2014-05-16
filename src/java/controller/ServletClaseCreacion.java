/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import beans.Categoria;
import beans.Coche;
import beans.Moto;
import beans.Vehiculo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import managers.DatabaseManager;
import managers.LoggerManager;

/**
 * En esta servlet se enseña como guardar objetos de tipo Categoria en una lista
 * de categoria.
 *
 * El servlet recibe la peticion desde un formulario html enviado desde una .jsp
 *
 */
public class ServletClaseCreacion extends HttpServlet {

    private final String COCHE_ID = "1";
    private final String MOTO_ID = "2";
    /*
     propriedad de la clase ServletClaseCreacion, lista de categoriasList
     se necesita para guardar la categoriasList y los coches que se van creando en la servlet
     */
    private ArrayList<Categoria> categoriasList;

    @Override
    public void init() throws ServletException {
        super.init();
        categoriasList = new ArrayList<Categoria>();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        //Abro conexion
        DatabaseManager.openConnection();

        //guardo los parametros enviados por el formulario
        String tipo = request.getParameter("tipo");
        String descripcion = request.getParameter("descripcion");
        String matricula = request.getParameter("matricula");
        String color = request.getParameter("color");
        String fabricante = request.getParameter("fabricante");
        String valocidadMax = request.getParameter("velocidadMax");
        String tipoVehiculo = request.getParameter("vehiculo");
        String potencia = request.getParameter("potencia");
        String cilindros = request.getParameter("cilindros");

        //controlo que los datos esten bien definidos
        if ((tipo != null && !tipo.equals("")) && (descripcion != null && !descripcion.equals(""))
                && (matricula != null && !matricula.equals("")) && (color != null && !color.equals(""))
                && (fabricante != null && !fabricante.equals("")) && (valocidadMax != null && !valocidadMax.equals(""))
                && (tipoVehiculo != null && !tipoVehiculo.equals("")) && (potencia != null && !potencia.equals(""))
                && (cilindros != null && !cilindros.equals(""))) {

            //si estan bien definidos, los processo
            //hago el parsing de un string a un int (el parametro velocidadMax tiene que ser un intero)
            int velocidadMaxInt = Integer.parseInt(valocidadMax);

            //creo una instancia/objeto de tipo categoria con los parametros enviado por el formulario
            Categoria categoria = new Categoria(tipo, descripcion);
            //creo una instancia/objeto de tipo coche con los parametro enviados por el formulario

            Coche coche = null;
            Moto moto = null;
            if (tipoVehiculo.equals("Coche")) {
                coche = new Coche(matricula, color, fabricante, velocidadMaxInt, potencia, cilindros);
            }
            if (tipoVehiculo.equals("Moto")) {
                moto = new Moto(matricula, color, fabricante, velocidadMaxInt, potencia, cilindros);
            }

            //ahora, lo que no quiero hacer es añadir una categoria a la lista si ésta
            //ya existe en la lista
            //para poder hacer esto, busco la categoria (por tipo) en la lista categoriasList
            int index = -1;

            //llamo una funcion que busca una categoria por tipo en la lista
            index = getCategoria(tipo);

            //si la categoria no existe
            if (index == -1) {

                //controlo que tipo de vehiculo queiro añadir
                if (tipoVehiculo.equals("Coche")) {
                    //añado el motor, el coche y la categoria a la base de datos
                    añadirCategoriaYVehiculo(categoria, coche);
                }
                if (tipoVehiculo.equals("Moto")) {
                    //añado el motor, la moto y la categoria a la base de datos            
                    añadirCategoriaYVehiculo(categoria, moto);

                }
                // categoriasList.add(categoria);

            } //si la categoria existe
            else {
                //controlo que tipo de vehiculo queiro añadir
                if (tipoVehiculo.equals("Coche")) {
                    //añado el coche a la base de datos con categoria con ese index
                    añadoVehiculo(index, coche, true);
                }
                if (tipoVehiculo.equals("Moto")) {
                    //añado la moto a la base de datos con categoria con ese index
                    añadoVehiculo(index, moto, true);
                }

            }

            // se envia a la pagina de visualizacion de datos
            request.getRequestDispatcher("/VisualizaDatosServlet").forward(request, response);

        } else {
            //instrucion para volver a una index.jsp
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }

        DatabaseManager.closeConnection();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletClaseCreacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ServletClaseCreacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /*
     * metodo que busca una categoria por tipo
     * devuelve -1 si la categoria no existe, 
     la posicion de la categoria si existe 
     */
    private int getCategoria(String tipo) {
        int index = -1;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //Declaro la query
            String categoriaSql = "SELECT categoria.id FROM categoria WHERE categoria.tipo='" + tipo + "'";
            //Declaro los objetos java necesarios para ejecutar la query

            //preparo la query a la base de datos
            preparedStatement = DatabaseManager.conn.prepareStatement(categoriaSql);

            //ejecuto la query
            resultSet = preparedStatement.executeQuery();

            //proceso resultado
            while (resultSet.next()) {
                //guardo en la variable index el id del tipo de categoria correspondiente
                index = resultSet.getInt("id");

            }

            //Cierro objetos java
            preparedStatement.close();
            resultSet.close();

        } catch (SQLException ex) {
            //Se ejecuta si da error
            LoggerManager.getLog().error(ex.toString());
            preparedStatement.close();
            resultSet.close();

        } finally {
            //Esto siempre se ejecuta
            return index;
        }
    }

    private void añadirCategoriaYVehiculo(Categoria categoria, Vehiculo vehiculo) throws SQLException {

        //Para grabarlo en nuestro logger
        if (LoggerManager.DEBUG) {
            LoggerManager.getLog().info("Añadiendo: " + categoria.toString() + " y " + vehiculo.toString());
        }

        // Declaro la sentencia sql necesaria
        String categoriaSql = "INSERT INTO categoria (tipo,descripcion) VALUES "
                + "('" + categoria.getTipo() + "','" + categoria.getDescripcion() + "')";

        try {
            //Deshabilitar el autoCommit
            DatabaseManager.conn.setAutoCommit(false);
            //Añadir la categoria y obtener su index con el metodo executeUpdate
            int categoriaId = DatabaseManager.executeUpdate(categoriaSql);
            //Añadir el vehiculo a la categoria con ese index
            boolean hacerCommit = false;  //Flag para hacer o no commit en la funcion añadoVehiculo
            añadoVehiculo(categoriaId, vehiculo, hacerCommit);
            // Hacer commit 
            DatabaseManager.conn.commit();
        } catch (SQLException ex) {
            DatabaseManager.conn.rollback();
            LoggerManager.getLog().error("arriba " + ex.toString());
        } finally {
            DatabaseManager.conn.setAutoCommit(true);
        }
    }

    private void añadoVehiculo(int categoriaIndex, Vehiculo vehiculo, Boolean hacerCommit) {

        if (LoggerManager.DEBUG) {
            LoggerManager.getLog().info("indice de categoria " + categoriaIndex);
            LoggerManager.getLog().info("vehiculo " + vehiculo.getFabricante());
        }

        //para añadir el vehiculo hay que añadir el motor primero para saber su id(del motor)
        String motorSql = "INSERT INTO motor (potencia,cilindros) VALUES "
                + "('" + vehiculo.getMotor().getPotencia() + "','" + vehiculo.getMotor().getCilindros() + "')";
        String vehiculoSql = "INSERT INTO vehiculo (matricula,fabricante,color,velocidadMax,categoria_id,motor_id,vehiculo_tipo_id) VALUES "
                + "('" + vehiculo.getMatricula() + "','" + vehiculo.getFabricante() + "','" + vehiculo.getColor() + "'," + vehiculo.getVelocidadMax() + "," + categoriaIndex + ",MOTOR_ID,VEHICULO_TIPO_ID)";

        //Si vehiculo es del tipo Coche
        if (vehiculo instanceof Coche) {
            //llamo a replaceAll() para sustituir VEHICULO_TIPO_ID por el valor id que corresponde a Coche
            //Almacenado en la constante tipo String al principio de esta clase
            vehiculoSql = vehiculoSql.replaceAll("VEHICULO_TIPO_ID", COCHE_ID);

        }
        if (vehiculo instanceof Moto) {
            vehiculoSql = vehiculoSql.replaceAll("VEHICULO_TIPO_ID", MOTO_ID);
        }

        //falta el motor_id
        //hay que insertarlo y obtener el id
        //Creamos un bloque try-catch donde ira el codigo de la transaccion
        //Declaramos unas variables para trabajar el query
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //Deshabilitar el autoCommit

            if (hacerCommit) {
                DatabaseManager.conn.setAutoCommit(false);
            }

            // LLamar a una funcion que ejecuta el INSERT motorSql y
            // me devuelve el id del elemento añadido a la base de datos
            int motorId = DatabaseManager.executeUpdate(motorSql);

            //Completo el INSERT vehiculoSql con el id del motor
            vehiculoSql = vehiculoSql.replaceAll("MOTOR_ID", String.valueOf(motorId));

            // Ejecutar el INSERT vehiculoSql y obtener el id
            DatabaseManager.executeUpdate(vehiculoSql);

            // Hacer commit
            if (hacerCommit) {
                DatabaseManager.conn.commit();
            }
        } catch (SQLException ex) {
            try {
                DatabaseManager.conn.rollback();
                LoggerManager.getLog().error("abajo " + ex.toString());

            } catch (SQLException ex1) {
                //Lo grabamos con nuestro logger
                LoggerManager.getLog().error(ex.toString());
            }

        } finally {
            //Habilitar autoCommit
            try {
                if (hacerCommit) {
                    DatabaseManager.conn.setAutoCommit(true);
                }
            } catch (SQLException ex) {
                LoggerManager.getLog().error(ex.toString());
            }
        }

    }

}
