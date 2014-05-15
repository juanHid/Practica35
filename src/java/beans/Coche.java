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
public class Coche extends Vehiculo {

    public Coche() {
        super();
    }

    public Coche(String matricula, String color, String fabricante, int velocidadMax, String potencia, String cilindros) {
        
        super(matricula,color,fabricante,velocidadMax,potencia,cilindros);
        this.nrDeRuedas = 4;
        
    }

   /* public Coche(String matricula, String color, String fabricante, int velocidadMax,String modelo) {

        this.matricula = matricula;
        this.color = color;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.velocidadMax = velocidadMax;

    }
*/
    

    

}
