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

        //Creamos la baraja
        Baraja baraja = new Baraja();
        baraja.barajar();

        //Cada iteración es una ronda
        for (int ronda = 1; ronda <= RONDAS; ronda++) {
            repartirCartas(jugadores, baraja);

            //Cada iteración es una serie de turnos (todos los jugadores)
            while (!manosVacias(jugadores)) {
                Carta[] pendientes = new Carta[jugadores.length];

                //Cada iteración es un turno
                for (int i = 0; i < jugadores.length; i++) {
                    //Mostramos el estado de la mesa
                    mostrarMesa(jugadores);
                    //Pedimos una carta
                    int jugada = pideCarta(jugadores[i].getMano());
                    //La movemos a las pendientes
                    pendientes[i] = jugadores[i].getMano().getCartaMano(jugada);
                }
                //Ponemos las cartas pendientes de cada jugador en la mesa
                for (int i = 0; i < jugadores.length; i++) {
                    jugadores[i].getCartasMesa().ponerSobreMesa(pendientes[i]);
                }
                //Movemos la cartas de las manos a otros jugadores
                rotarManos(jugadores);
            }
            //Calculamos y mostramos los resultados de la ronda
            calcularPuntos(jugadores, ronda);
            limpiarMesa(jugadores);
            System.out.println("\nResultados de la ronda " + ronda + ":");
            mostrarPuntos(jugadores);
            pulsaEnter();
        }
        //Al acabar todas las rondas mostramos los resultados y el ganador
        System.out.println("\nResultados finales:");
        mostrarPuntos(jugadores);
        System.out.println("\nEl ganador es..."
                + ganador(jugadores).getNombre() + "! Enhorabuena!");
    }

    /**
     * Pide el número de jugadores por teclado y lo devuelve
     *
     * @return el número de jugadores, como entero
     */
    private static int pideNumJugadores() {
        int toret = pideEntero("Número de jugadores: ");

        while (toret < 2 || toret > 5) {
            toret = pideEntero("Número de jugadores: ");
        }
        return toret;
    }

    /**
     * Crea los jugadores leyendo los nombres de teclado
     *
     * @param jugadores array de jugadores
     */
    private static void leeJugadores(Jugador[] jugadores) {
        System.out.println("Introduce los nombres de cada jugador");
        for (int i = 0; i < jugadores.length; i++) {
            String nombre = pideCadena("Jugador " + (i + 1));
            jugadores[i] = new Jugador(nombre);
        }
    }

    /**
     * Reparte el número correspondiente de cartas a la mano de cada jugador
     *
     * @param jugadores array de jugadores
     * @param baraja baraja con las cartas (ya barajada)
     */
    private static void repartirCartas(Jugador[] jugadores, Baraja baraja) {
        int cartasPorJugador = 11 - jugadores.length;

        for (Jugador j : jugadores) {
            for (int i = 0; i < cartasPorJugador; i++) {
                j.getMano().añadirCartaMano(baraja.darCarta());
            }
        }
    }

    /**
     * Indica si los jugadores tienen cartas en la mano
     *
     * @param jugadores array de jugadores
     * @return true solo si ningún jugador tiene cartas en la mano, false en
     * otro caso
     */
    private static boolean manosVacias(Jugador[] jugadores) {
        for (Jugador j : jugadores) {
            if (j.getMano().getCartasMano().esVacio()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Muestra por pantalla el estado actual de la mesa
     *
     * @param jugadores array de jugadores
     */
    private static void mostrarMesa(Jugador[] jugadores) {
        System.out.println("\nEstado actual de la mesa\n");

        for (Jugador j : jugadores) {
            System.out.println(j.getNombre());
            System.out.println(j.getCartasMesa().toString() + "\n");
        }
    }

    /**
     * Devuelve el índice de la carta que quieres jugar el usuario
     *
     * @param mano mano del jugador que elige la carta
     * @return el índice de la carta que elige el usuario, como int
     */
    private static int pideCarta(Mano mano) {
        System.out.println("Tu mano: ");
        System.out.println(mano.toString());
        int toret;

        do {
            toret = pideEntero("Selecciona una carta: ");
        } while (toret < 0 || toret > mano.getNumCartasMano());

        return toret;
    }

    /**
     * Mueve la mano de cada jugador al situado a su izquierda
     *
     * @param jugadores array de jugadores
     */
    private static void rotarManos(Jugador[] jugadores) {
        Mano mano1 = jugadores[0].getMano();

        for (int i = 0; i < (jugadores.length - 1); i++) {
            jugadores[i].setMano(jugadores[i + 1].getMano());
        }
        jugadores[jugadores.length - 1].setMano(mano1);
    }

    /**
     * Guarda la puntuación de la ronda en base a las cartas de la mesa
     *
     * @param jugadores array de jugadores
     * @param ronda
     */
    private static void calcularPuntos(Jugador[] jugadores, int ronda) {
        for (Jugador j : jugadores) {
            j.addPuntos(j.getCartasMesa().calcularPuntuacion(), ronda);
        }
    }

    private static void limpiarMesa(Jugador[] jugadores) {
        for (Jugador j : jugadores) {
            j.getCartasMesa().limpiarFinalRonda();
        }
    }

    /**
     * Muestra las puntuaciones de los jugadores
     *
     * @param jugadores array de jugadores
     */
    private static void mostrarPuntos(Jugador[] jugadores) {
        System.out.println("");
        System.out.printf("%-20s\t%7s\t%7s\t%7s\t%5s\n",
                "Jugadores", "Ronda 1", "Ronda 2", "Ronda 3", "Total");
        for (Jugador j : jugadores) {
            System.out.printf("%-20s\t%7d\t%7d\t%7d\t%5d\n",
                    j.getNombre(), j.getPuntos(1), j.getPuntos(2),
                    j.getPuntos(3), j.getPuntos());
        }
        System.out.println("");
    }

    /**
     * Detiene el programa hasta que el usuario pulsa enter
     */
    private static void pulsaEnter() {
        System.out.print("Pulsa enter para continuar . . .");
        ES.scanner.nextLine();
    }

    /**
     * Devuelve el jugador con más puntos
     *
     * @param jugadores array de jugadores
     * @return el jugador con una puntación mayor
     */
    private static Jugador ganador(Jugador[] jugadores) {
        Jugador toret = jugadores[0];
        for (int i = 1; i < jugadores.length; i++) {
            if (jugadores[i].getPuntos() > toret.getPuntos()) {
                toret = jugadores[i];
            }
        }
        return toret;
    }

}
