
package beans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import managers.DatabaseManager;
import managers.LoggerManager;


public class Categoria {

    private String tipo;
    private String descripcion;
    private int id;
    private ArrayList<Vehiculo> listaVehiculos;

    public Categoria(String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.listaVehiculos = new ArrayList<Vehiculo>();
    }

    public Categoria() {

    }

    //Constructor para crear objetos con datos extraidos de base de datos
    public Categoria(int id, String tipo, String descripcion) {
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.id = id;
    }

    public void addVehiculo(Vehiculo vehiculo) {

        this.listaVehiculos.add(vehiculo);
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public ArrayList<Vehiculo> getListaVehiculos() {
        return listaVehiculos;
    }

    public void setListaVehiculos(ArrayList<Vehiculo> listaVehiculos) {
        this.listaVehiculos = listaVehiculos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        String categoriaString;

        categoriaString = "[Tipo: " + this.tipo + "]<br>"
                + "[Descripcion: " + this.descripcion + "]<br>"
                + this.listaVehiculos.toString() + "]<br>";

        return categoriaString;
    }

    public void crearListaVehiculos(int id) {
       // obtener los vehiculos de la categoria con este id
        // y agregarlos a la lista de vehiculos de la categoria

        //Instanciamos una lista de vehiculos 
        //ya que en el constructor que hemos usado no se ha hecho
        this.listaVehiculos = new ArrayList<Vehiculo>();

        String vehiculoSql = "SELECT * FROM vehiculo,motor,vehiculo_tipo "
                + "WHERE vehiculo.motor_id=motor.id "
                + "AND vehiculo.vehiculo_tipo_id=vehiculo_tipo.id "
                + "AND vehiculo.categoria_id=" + id;
        try {
            //Preparar objetos java necesarios y procesar la query
            PreparedStatement stm = DatabaseManager.conn.prepareStatement(vehiculoSql);
            ResultSet resultSet = stm.executeQuery();
            
            // Declaro objetos tipo coche, moto
            Coche coche;
            Moto moto;

            //Procesar el resultado
            while (resultSet.next()) {
                //Obtengo datos de la database
                String matricula = resultSet.getString("matricula");
                String fabricante = resultSet.getString("fabricante");
                String color = resultSet.getString("color");
                int velocidadMax = resultSet.getInt("velocidadMax");
                String potencia = resultSet.getString("potencia");
                String cilindros = resultSet.getString("cilindros");
                String tipoVehiculo = resultSet.getString("tipo");

                if (tipoVehiculo.equals("Coche")) {
                    //construir objeto
                    //agregar a la lista de vehiculos
                    coche = new Coche(matricula, color, fabricante, velocidadMax, potencia, cilindros);
                    this.listaVehiculos.add(coche);

                }
                if (tipoVehiculo.equals("Moto")) {
                    // construir objeto
                    // agregar a la lista de vehiculos
                    moto = new Moto(matricula, color, fabricante, velocidadMax, potencia, cilindros);
                    this.listaVehiculos.add(moto);
                }

            }

            stm.close();
            resultSet.close();
        } catch (SQLException ex) {
            LoggerManager.getLog().error(ex.toString());
        }

    }

}
