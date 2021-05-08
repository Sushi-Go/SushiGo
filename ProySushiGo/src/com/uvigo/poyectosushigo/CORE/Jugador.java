/*
 * Representa a un jugador de la partida, identificado por el nombre 
 * Funcionalidad: escoge una carta de su mano; la colocará en su mesa; coge cartas de la baraja para la mano,
 *                entrega las cartas de su mano; coge las cartas de otra mano; calcula su puntuación por ronda;
 *                calcula su puntuación total; cuenta cuantos rollitos tiene en su mesa; ....
 */
package com.uvigo.poyectosushigo.CORE;

import com.uvigo.poyectosushigo.CORE.carta.*;
import lista.Lista;
import pila.Pila;

public class Jugador {

    private String nombre;
    private Mano mano;
    private int puntosAcumulados;
    private int puntosRondaActual;
    private Pila<Carta> cartasMesaJugador;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Mano getMano() {
        return mano;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public int getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(int puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    public int getPuntosRondaActual() {
        return puntosRondaActual;
    }

    public void setPuntosRondaActual(int puntosRondaActual) {
        this.puntosRondaActual = puntosRondaActual;
    }

    public Pila<Carta> getCartasMesaJugador() {
        return cartasMesaJugador;
    }

    public void setCartasMesaJugador(Pila<Carta> cartasMesaJugador) {
        this.cartasMesaJugador = cartasMesaJugador;
    }

    public Carta elegirCarta(Carta c) {
        return mano.getCartaMano(c);
    }
    
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jugador: ").append(nombre);
        sb.append("\nmano: ").append(mano);
        sb.append("\npuntosAcumulados: ").append(puntosAcumulados);
        sb.append("\npuntosRondaActual: ").append(puntosRondaActual);
        return sb.toString();
    }
    

}
