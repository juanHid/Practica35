
package beans;


public class Coche extends Vehiculo {

    public Coche() {
        super();
    }

    public Coche(String matricula, String color, String fabricante, int velocidadMax, String potencia, String cilindros) {
        
        super(matricula,color,fabricante,velocidadMax,potencia,cilindros);
        this.nrDeRuedas = 4;
        
    }

}
