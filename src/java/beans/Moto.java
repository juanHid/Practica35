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
public class Moto extends Vehiculo {
    
    
    
    public Moto() {
        super();
    }

    public Moto(String matricula, String color, String fabricante, int velocidadMax, String potencia, String cilindros) {
       super(matricula, color, fabricante, velocidadMax, potencia, cilindros);
       this.nrDeRuedas = 2;
    }
    
   
    
    public Moto(String matricula, String color, String fabricante, int velocidadMax) {
        
        this.matricula = matricula;
        
        this.color = color;
        this.fabricante = fabricante;
        this.velocidadMax = velocidadMax;
        
    }
    
    
    
    
}
