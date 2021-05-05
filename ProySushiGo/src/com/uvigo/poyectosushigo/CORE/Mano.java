/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado. 
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
 */
package com.uvigo.poyectosushigo.CORE;
import com.uvigo.poyectosushigo.CORE.carta.*;
public class Mano {

    private int numCartas;
    private Carta[] cartasMano;

    public Mano(int numCartas, Carta[] cartasMano) {
        this.numCartas = numCartas;
        this.cartasMano = cartasMano;
    }

}
