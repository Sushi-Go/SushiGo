/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.Iterator;
import lista.*;
import pila.*;

public class CartasMesa {

    private final Lista<Pila<Carta>> cartasMesa;
    private int puntosBase;
    private int numRollos;

    /**
     * Crea un nuevo cartasMesa
     */
    public CartasMesa() {
        this.cartasMesa = new ListaEnlazada<>();
        puntosBase = 0;
        numRollos = 0;
    }

    /**
     * Devuelve los puntos base de la mesa
     * 
     * @return la suma de todos los puntos, excepto los makis
     */
    public int getPuntosBase() {
        return puntosBase;
    }
    
    /**
     * Devuelve el total de rollos
     *
     * @return la suma de los rollos de todas las cartas de la mesa
     */
    public int getNumRollos() {
        return numRollos;
    }

    /**
     * Añade una carta a la mesa en la posición adecuada
     *
     * @param carta Carta a añadir
     */
    public void addCarta(Carta carta) {
        int nuevosPuntos = 0;

        if (carta.getNombre().startsWith("Nigiri")) {

            switch (carta.getNombre().substring(10)) {
                case "calamar":
                    nuevosPuntos = 3;
                    break;
                case "salmón":
                    nuevosPuntos = 2;
                    break;
                case "tortilla":
                    nuevosPuntos = 1;
            }
            if (apilar(carta, "Wasabi") != null) {
                nuevosPuntos *= 3;
            } else {
                if (apilar(carta, carta.getNombre()) == null) {
                    if (apilar(carta, "Nigiri") == null) {
                        Pila<Carta> nueva = new EnlazadaPila<>();
                        nueva.push(carta);
                        cartasMesa.insertarFinal(nueva);
                    }
                }
            }
        }

        puntosBase += nuevosPuntos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Lista<Pila<Carta>> copia = new ListaEnlazada<>();
        final int maxLongitud = 20; //Mayor longitud posible de Carta.toString()
        int altura = 1;

        for (Pila<Carta> p : cartasMesa) {
            copia.insertarFinal(p);
            if (p.tamaño() > altura) {
                altura = p.tamaño();
            }
        }
        //Cada iteración añade las cartas de una altura, de arriba a abajo
        while (altura > 0) {
            for (Pila<Carta> p : copia) {
                if (p.tamaño() == altura) {
                    sb.append(cadenaCentrada(p.pop().toString(), maxLongitud));
                } else {
                    sb.append(cadenaCentrada("", maxLongitud));
                }
                sb.append("\t");
            }
            altura--;
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Devuelve una pila cuya primera carta empiece por 'buscar', añadiendo una
     * carta encima de esa misma pila
     *
     * @param nueva carta a añadir
     * @param buscar comienzo del nombre de carta que buscamos en las pilas
     * @return la Pila de cartasMesa correspondiente, o null en su defecto
     */
    private Pila<Carta> apilar(Carta nueva, String buscar) {
        for (Pila<Carta> actual : cartasMesa) {
            if (actual.top().getNombre().startsWith(buscar)) {
                actual.push(nueva);
                return actual;
            }
        }
        return null;
    }

    /**
     * Devuelve una cadena alineada al centro
     *
     * @param cadena String que se pondrá en el medio
     * @param lon longitud total de la cadena a devolver
     * @return la cadena con los espacios necesarios a cada lado para que esté
     * centrada y su longitud total sea lon, como String
     */
    private static String cadenaCentrada(String cadena, int lon) {
        StringBuilder sb = new StringBuilder();
        int izq = (lon - cadena.length()) / 2;
        int der = (lon - cadena.length()) / 2 + (lon - cadena.length()) % 2;

        for (int i = 0; i < izq; i++) {
            sb.append(" ");
        }
        sb.append(cadena);
        for (int i = 0; i < der; i++) {
            sb.append(" ");
        }
        return sb.toString();
    }

}
