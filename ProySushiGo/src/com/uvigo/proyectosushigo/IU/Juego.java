/**
 * Representa el juego del sushiGo, con sus reglas.
 * Se recomienda una implementación modular.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.*;
import com.uvigo.poyectosushigo.CORE.carta.*;
import static com.uvigo.proyectosushigo.IU.ES.*;
import static com.uvigo.proyectosushigo.IU.Main.RONDAS;

public class Juego {

    public static void inicio() {

        Jugador[] jugadores;

        //Texto de introducción, título, etc
        System.out.println("");

        //Obtenemos el número de jugadores y sus nombres
        System.out.println("El mínimo de jugadores es 2, y el máximo 5");
        jugadores = new Jugador[pideNumJugadores()];
        leeJugadores(jugadores);

        //Cada iteración es una ronda
        for (int ronda = 1; ronda <= RONDAS; ronda++) {

            //Repartimos cartas
            Baraja baraja = new Baraja();
            baraja.barajar();
            repartirCartas(jugadores, baraja);

            //Cada iteración es una serie de turnos (todos los jugadores)
            while (!manosVacias(jugadores)) {
                Carta[] pendientes = new Carta[jugadores.length];

                //Cada iteración es un turno
                for (int i = 0; i < jugadores.length; i++) {

                    //Mostramos información y pedimos que juegue una carta
                    mostrarMesa(jugadores);
                    System.out.println("Tu mano:");
                    System.out.println(jugadores[i].getMano().toString());
                    int jugada = pideEntero("Selecciona una carta: ");

                    //Movemos la carta de la mano a las pendientes
                    pendientes[i] = jugadores[i].getMano().quitarCarta(jugada));
                }
                
                //Ponemos las cartas pendientes de cada jugador en la mesa
                for (int i = 0; i < jugadores.length; i++) {
                    jugadores[i].getCartasMesa().ponerSobreMesa(pendientes[i]);
                }
            }

        }

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
    public static void leeJugadores(Jugador[] jugadores) {
        System.out.println("Introduce los nombres de cada jugador");
        for (int i = 0; i < jugadores.length; i++) {
            String nombre = pideCadena("Jugador " + (i + 1));
            jugadores[i] = new Jugador(nombre);
        }
    }

    /**
     * Muestra por pantalla el estado actual de la mesa
     */
    public static void mostrarMesa(Jugador[] jugadores) {
        System.out.println("Estado actual de la mesa\n");

        for (Jugador j : jugadores) {
            System.out.println(j.getNombre());
            System.out.println("\n" + j.getCartasMesa().toString() + "\n");
        }
    }

    /**
     * Reparte el número correspondiente de cartas a la mano de cada jugador
     *
     * @param jugadores array de jugadores que reciben las cartas
     * @param baraja baraja con las cartas (ya barajada)
     */
    public static void repartirCartas(Jugador[] jugadores, Baraja baraja) {
        int cartasPorJugador = 11 - jugadores.length;

        for (Jugador j : jugadores) {
            for (int i = 0; i < cartasPorJugador; i++) {
                j.getMano().añadirCartaMano(baraja.darCarta());
            }
        }
    }

}
