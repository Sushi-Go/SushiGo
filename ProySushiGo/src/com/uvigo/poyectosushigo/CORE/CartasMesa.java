/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
 */
package com.uvigo.poyectosushigo.CORE;

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

    //Le añade la carta c a la pila seleccionada en pilaCartas
    public void ponerSobreMesa(Pila<Carta> pilaCartas,Carta c) {
        Pila<Carta> temp=pilaCartas;
        
        cartasMesa.suprimir(pilaCartas);
        temp.push(c);
        cartasMesa.insertarPrincipio(temp);
        numCartasMesa++;
    }

    //retira la ultima carta de la pila seleccionada en pilaCartas
    public void retirarCartaMesa(Pila<Carta> pilaCartas) {
        Pila<Carta> temp=pilaCartas;
        
        cartasMesa.suprimir(pilaCartas);
        temp.pop();
        cartasMesa.insertarPrincipio(temp);
        numCartasMesa--;
    }
    
    //retira la ultima carta de pilaRetirar y la inserta en pilaInsertar
    public void retirarInsertarCarta(Pila<Carta> pilaRetirar,Pila<Carta> pilaInsertar){
        Carta c=pilaRetirar.pop();
        
        pilaInsertar.push(c);
    }

    public int calcularPuntuacion() {
        
    }

    public void limpiarFinalRonda() {
        for (Pila<Carta> i : cartasMesa) {
            i.pop();
        }
    }

<<<<<<< HEAD
    public int calcularNumRollitos(Carta c) {
        int toRet=0;
        
        switch(c.getNombre()){
            case "Maki de 1 rollo":
                toRet=1;
                break;
            case "Maki de 2 rollos":
                toRet=2;
                break;
            case "Maki de 3 rollos":
                toRet=3;
                break;
            default:
                System.err.println("La carta no es un maki");
        }
        
        return toRet;
=======
    public int calcularNumRollitos() {

>>>>>>> 1ca242bedea12ad6e1704d7cfc2044cd0830ba82
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
