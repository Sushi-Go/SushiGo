/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado. 
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    
    private final List<Carta> cartasMano;
    
    /**
     * Crea una mano de cartas
     */
    public Mano() {
        cartasMano = new ArrayList<>();
    }
    
    /**
     * Devuelve el número de cartas de la mano
     * 
     * @return el número de cartas, como int
     */
    public int getNumCartas() {
        return cartasMano.size();
    }

    /**
     * Añade una carta a la mano
     * 
     * @param carta Carta a añadir
     */
    public void addCarta(Carta carta) {
        cartasMano.add(carta);
    }
    
    /**
     * Elimina y devuelve una carta de la mano, o null si pos no es válido
     * 
     * @param pos posición de la carta, empezando en 1
     * @return la Carta de esa posición
     */
    public Carta cogerCarta(int pos) {
        return cartasMano.remove(pos - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        
        for (Carta c : cartasMano) {
            sb.append(i++).append(") ").append(c.toString()).append("\n");
        }
        return sb.toString();
    }
}
