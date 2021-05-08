/*
 * Carta Maki
 */
package com.uvigo.poyectosushigo.CORE.carta;

public class Maki extends Carta{
    private int numRollitos;
    
    public Maki(int numRollitos){
        super("Maki");
        this.numRollitos=numRollitos;
    }

    public int getNumRollitos() {
        return numRollitos;
    }

    public void setNumRollitos(int numRollitos) {
        this.numRollitos = numRollitos;
    }
    
    

}
