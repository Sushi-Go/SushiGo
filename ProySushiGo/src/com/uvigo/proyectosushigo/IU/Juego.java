/**
 * Representa el juego del sushiGo, con sus reglas.
 * Se recomienda una implementación modular.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.*;
import static com.uvigo.proyectosushigo.IU.ES.*;
import static com.uvigo.proyectosushigo.IU.Main.RONDAS;

public class Juego {

    //Número máximo de jugadores
    public static final int MAX_NUM_JUGADORES = 5;

    //Máima longitud posible de los nombres de los jugadores
    public static final int MAX_LONG_NOMBRE = 30;

    public static void inicio() {

        creditos();
        Jugador[] jugadores;

        //Obtenemos el número de jugadores y sus nombres
        System.out.println("El mínimo de jugadores es 2, y el máximo "
                + MAX_NUM_JUGADORES);
        jugadores = new Jugador[pideNumJugadores()];
        leeJugadores(jugadores);

        //Creamos la baraja
        Baraja baraja = new Baraja();
        baraja.barajar();

        //Cada iteración es una ronda
        for (int ronda = 1; ronda <= RONDAS; ronda++) {
            System.out.println("");
            System.out.println("\t┌---------┐");
            System.out.println("\t│ RONDA " + ronda + " │");
            System.out.println("\t└---------┘\n");
            pulsaEnter();

            repartirCartas(jugadores, baraja);

            //Cada iteración es una serie de turnos (todos los jugadores)
            while (!manosVacias(jugadores)) {
                Carta[] pendientes = new Carta[jugadores.length];

                //Cada iteración es un turno
                for (int i = 0; i < jugadores.length; i++) {
                    mostrarMesa(jugadores);
                    int jugada = pideCarta(jugadores[i]);
                    pendientes[i] = jugadores[i].getMano().cogerCarta(jugada);
                }
                //Ponemos las cartas pendientes de cada jugador en la mesa
                for (int i = 0; i < jugadores.length; i++) {
                    jugadores[i].getCartasMesa().addCarta(pendientes[i]);
                }
                //Movemos la cartas de las manos a otros jugadores
                rotarManos(jugadores);
            }
            //Contabilizamos los puntos y mostramos los resultados de la ronda
            contarPuntos(jugadores, ronda);
            limpiarMesa(jugadores);
            System.out.println("\nResultados de la ronda " + ronda + ":");
            mostrarPuntos(jugadores);
            pulsaEnter();
        }
        //Al acabar todas las rondas mostramos el ganador
        System.out.println("\nEl ganador es..."
                + ganador(jugadores).getNombre() + "! Enhorabuena!");
    }
    
    /**
     * Muestra el título y los autores
     */
    public static void creditos() {
        System.out.println(""
                + "     __^__                  __^__\n"
                + "    ( ___ )----------------( ___ )\n"
                + "     | / |                  | \\ |\n"
                + "     | / |     SUSHI GO     | \\ |\n"
                + "     |___|                  |___|\n"
                + "    (_____)----------------(_____)\n\n");
    }

    /**
     * Pide el número de jugadores por teclado y lo devuelve
     *
     * @return el número de jugadores, como entero
     */
    private static int pideNumJugadores() {
        int toret = pideEntero("Número de jugadores: ");

        while (toret < 2 || toret > MAX_NUM_JUGADORES) {
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
        System.out.println("\nIntroduce los nombres de cada jugador"
                + " (máximo " + MAX_LONG_NOMBRE + " caracteres)");

        for (int i = 0; i < jugadores.length; i++) {
            String nombre = pideCadena("Jugador " + (i + 1) + ": ");

            while (nombre.length() > MAX_LONG_NOMBRE) {
                System.out.println("El máximo de caracteres es "
                        + MAX_LONG_NOMBRE);
                nombre = pideCadena("Jugador " + (i + 1) + ": ");
            }
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
                j.getMano().addCarta(baraja.cogerCarta());
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
            if (j.getMano().getNumCartas() > 0) {
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
            System.out.println(j.getNombre() + "\n");
            System.out.println(j.getCartasMesa().toString());
        }

    }

    /**
     * Devuelve el índice de la carta que quiere jugar el usuario
     *
     * @param jugador el jugador que elige la carta
     * @return el índice de la carta que elige el usuario, como int
     */
    private static int pideCarta(Jugador jugador) {
        System.out.println("Tu mano: ");
        System.out.println(jugador.getMano().toString());
        int toret;

        do {
            toret = pideEntero("Selecciona una carta, "
                    + jugador.getNombre() + ": ");
        } while (toret < 1 || toret > jugador.getMano().getNumCartas());

        return toret;
    }

    /**
     * Mueve la mano de cada jugador al situado a su izquierda
     *
     * @param jugadores array de jugadores
     */
    private static void rotarManos(Jugador[] jugadores) {
        Mano manoJ1 = jugadores[0].getMano();

        for (int i = 0; i < (jugadores.length - 1); i++) {
            jugadores[i].setMano(jugadores[i + 1].getMano());
        }
        jugadores[jugadores.length - 1].setMano(manoJ1);
    }

    /**
     * Añade a los jugadores los puntos de su mesa y los correspondientes a su
     * número de rollos de maki
     *
     * @param jugadores array de jugadores
     * @param ronda ronda en la que se añaden
     */
    public static void contarPuntos(Jugador[] jugadores, int ronda) {
        int maxRollos = 0;
        int numSuman = 1;

        for (Jugador j : jugadores) {
            j.addPuntos(j.getCartasMesa().getPuntosBase(), ronda);

            if (j.getCartasMesa().getNumRollos() > maxRollos) {
                maxRollos = j.getCartasMesa().getNumRollos();
                numSuman = 1;
            } else if (j.getCartasMesa().getNumRollos() == maxRollos) {
                numSuman++;
            }
        }
        if (maxRollos != 0) {
            for (Jugador j : jugadores) {
                if (j.getCartasMesa().getNumRollos() == maxRollos) {
                    j.addPuntos(6 / numSuman, ronda);
                }
            }
        }
    }

    /**
     * Quita todas las cartas de la mesa
     *
     * @param jugadores array de jugadores
     */
    private static void limpiarMesa(Jugador[] jugadores) {
        for (Jugador j : jugadores) {
            j.limpiarMesa();
        }
    }

    /**
     * Muestra las puntuaciones de los jugadores (en cada ronda y el total)
     *
     * @param jugadores array de jugadores
     */
    private static void mostrarPuntos(Jugador[] jugadores) {
        String formato = "%-" + MAX_LONG_NOMBRE + "s\t%10s%10s%10s%10s\n";
        System.out.println("");
        System.out.printf(formato, "Jugadores", "Ronda 1", "Ronda 2",
                "Ronda 3", "Total");

        for (Jugador j : jugadores) {
            System.out.printf(formato, j.getNombre(), j.getPuntos(1),
                    j.getPuntos(2), j.getPuntos(3), j.getPuntos());
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
