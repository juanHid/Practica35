

package beans;


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
