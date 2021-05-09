/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado. 
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.Iterator;
import lista.Lista;
import lista.ListaEnlazada;

public class Mano {
    
    private final Lista<Carta> cartasMano;
    
    /**
     * Crea una mano de cartas
     */
    public Mano() {
        cartasMano = new ListaEnlazada<>();
    }
    
    /**
     * Devuelve el número de cartas de la mano
     * 
     * @return el número de cartas, como int
     */
    public int getNumCartas() {
        return cartasMano.tamaño();
    }

    /**
     * Añade una carta a la mano
     * 
     * @param carta Carta a añadir
     */
    public void addCarta(Carta carta) {
        cartasMano.insertarFinal(carta);
    }
    
    /**
     * Elimina y devuelve una carta de la mano, o null si pos no es válido
     * 
     * @param pos posición de la carta, empezando en 1
     * @return la Carta de esa posición
     */
    public Carta cogerCarta(int pos) {
        Iterator it = cartasMano.iterator();
        int i = 1;
        
        while (it.hasNext() && pos != i) {
            it.next();
        }
        if (it.hasNext()) {
            return (Carta) it.next();
        } else {
            return null;
        }
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
