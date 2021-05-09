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
        StringBuilder toRet = new StringBuilder();
        Pila<Carta> temp = new EnlazadaPila<>();

        toRet.append("\nCartas: ");
        for (Pila<Carta> i : cartasMesa) {
            temp = i;
            toRet.append("\n\t").append(i.pop().toString());
        }

        return toRet.toString();
    }

}
