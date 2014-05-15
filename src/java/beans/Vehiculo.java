/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author confalonieri
 */
public class Vehiculo {

    //propriedades de la superclase Vehiculo
    protected String matricula;
    protected String color;
    protected String fabricante;
    protected static int velocidadMin = 0;
    protected int velocidadMax;
   // protected String modelo;
    protected Motor motor;
    protected int nrDeRuedas;
    
    public Vehiculo() {
        
    }
    
    public Vehiculo(String matricula,String color, String fabricante, int velocidadMax,String potencia,String cilindros) {
        
        this.matricula = matricula;
        this.color = color;
        this.fabricante = fabricante;
        this.velocidadMax = velocidadMax;
        //creo un objeto de tipo Motor con los parametros pasados el constructor del Coche
        //llamo el constructor de la clase Motor
        this.motor = new Motor(potencia, cilindros);
        
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public static int getVelocidadMin() {
        return velocidadMin;
    }

    public static void setVelocidadMin(int velocidadMin) {
        Vehiculo.velocidadMin = velocidadMin;
    }

    public int getVelocidadMax() {
        return velocidadMax;
    }

    public void setVelocidadMax(int velocidadMax) {
        this.velocidadMax = velocidadMax;
    }

  /* public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
*/
    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public int getNrDeRuedas() {
        return nrDeRuedas;
    }

    public void setNrDeRuedas(int nrDeRuedas) {
        this.nrDeRuedas = nrDeRuedas;
    }

    @Override
    public String toString() {
        String vehString;
        
        vehString = "[Matricula: " + this.matricula + "]<br>"
                + "[Color: " + this.color + "]<br>"
                + "[Fabricante: " + this.fabricante + "]<br>"
                + "[VelocidadMin: " + this.velocidadMin + "]<br>"
                + "[VelocidadMax: " + this.velocidadMax + "]<br>"
              //  + "[Modelo: " + this.modelo + "]<br>"
                + "[NrRuedas: " + this.nrDeRuedas + "]<br>"
                + "[Motor: " + this.motor.toString() + "]"
                + "[Tipo Vehiculo: " + this.getClass().getSimpleName() + "]";
                
        // this.categoria.toString();

        return vehString;
    }
}
