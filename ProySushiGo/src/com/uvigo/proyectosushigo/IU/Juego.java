/**
 * Representa el juego del sushiGo, con sus reglas.
 * Se recomienda una implementación modular.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.Jugador;
import static com.uvigo.proyectosushigo.IU.ES.*;
import java.util.Scanner;

public class Juego {

    private static Jugador[] jugadores;
    private int rondaActual;

    public static void inicio() {

        //Texto de introducción, título, etc
        System.out.println("");

        //Obtenemos el número de jugadores y sus nombres
        System.out.println("El mínimo de jugadores es 2, y el máximo 5");
        jugadores = new Jugador[pideNumJugadores()];
        leeJugadores();

    }

    /**
     * Pide el número de jugadores por teclado y lo devuelve
     *
     * @return el número de jugadores, como entero
     */
    public static int pideNumJugadores() {
        int toret = pideEntero("Número de jugadores: ");

        while (toret < 2 || toret > 5) {
            toret = pideEntero("Número de jugadores: ");
        }
        return toret;
    }

    /**
     * Crea los jugadores leyendo los nombres de teclado
     */
    public static void leeJugadores() {
        System.out.println("Introduce los nombres de cada jugador");
        for (int i = 0; i < jugadores.length; i++) {
            String nombre = pideCadena("Jugador " + (i + 1));
            jugadores[i] = new Jugador(nombre);
        }
    }

    /**
     * Muestra por pantalla el estado actual de la mesa
     */
    public static void mostrarMesa() {
        System.out.println("Estado actual de la mesa\n");

        for (Jugador j : jugadores) {
            System.out.println(j.getNombre());
            //
        }
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public void setRondaActual(int rondaActual) {
        this.rondaActual = rondaActual;
    }

    public void anhadeRonda() {
        setRondaActual(getRondaActual() + 1);
    }

}
