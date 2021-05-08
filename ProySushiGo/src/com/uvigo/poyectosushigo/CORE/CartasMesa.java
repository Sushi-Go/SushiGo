/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
 */
package com.uvigo.poyectosushigo.CORE;

import com.uvigo.poyectosushigo.CORE.carta.*;
import lista.*;
import pila.*;

public class CartasMesa {

    private Lista<Pila<Carta>> cartasMesa;
    private int numCartasMesa;

    public CartasMesa() {
        numCartasMesa = 0;
        cartasMesa = new ListaEnlazada<>();
    }

    public CartasMesa(Lista<Pila<Carta>> cartasMesa) {
        this.cartasMesa = cartasMesa;
        numCartasMesa = cartasMesa.tamaño();
    }

    public void ponerSobreMesa(Carta c) {

    }

    public void retirarCartaMesa(Carta c) {

    }

    public int calcularPuntuacion() {

    }

    public void limpiarFinalRonda() {
        for (Pila<Carta> i : cartasMesa) {
            i.pop();
        }
    }

    public int calcularNumRollitos() {

    }

    public Lista<Pila<Carta>> getCartasMesa() {
        return cartasMesa;
    }

    public void setCartasMesa(Lista<Pila<Carta>> cartasMesa) {
        this.cartasMesa = cartasMesa;
    }

    public int getNumCartasMesa() {
        return numCartasMesa;
    }

    public void setNumCartasMesa(int numCartasMesa) {
        this.numCartasMesa = numCartasMesa;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Lista<Pila<Carta>> copia = new ListaEnlazada<>();
        int altura = 1;

        for (Pila<Carta> p : cartasMesa) {
            copia.insertarFinal(p);
            if (p.tamaño() > altura) {
                altura = p.tamaño();
            }
        }

        while (altura > 0) {
            for (Pila<Carta> p : copia) {
                if (p.tamaño() == altura) {
                    sb.append(cadenaCentrada(p.pop().toString(), 18));
                } else {
                    sb.append(cadenaCentrada("", 18));
                }
            }
            altura--;
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Devuuelve una cadena alineada al centro
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
