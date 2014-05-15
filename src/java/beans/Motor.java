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
public class Motor {
    
    private String potencia;
    private String cilindros;

    public Motor(String potencia, String cilindros) {
        this.potencia = potencia;
        this.cilindros = cilindros;
    }

    @Override
    public String toString() {
         return  cilindros +" cilindros y "+ potencia +" cv.";
    }

    public String getPotencia() {
        return potencia;
    }

    public void setPotencia(String potencia) {
        this.potencia = potencia;
    }

    public String getCilindros() {
        return cilindros;
    }

    public void setCilindros(String cilindros) {
        this.cilindros = cilindros;
    }

    
    
}
