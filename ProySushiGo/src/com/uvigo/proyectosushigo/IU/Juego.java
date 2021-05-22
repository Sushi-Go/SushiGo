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

    //Máxima longitud posible de los nombres de los jugadores
    public static final int MAX_LONG_NOMBRE = 30;

    public static void inicio() {

        mostrarCreditos();
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
            mostrarRonda(ronda);
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
            mostrarMesa(jugadores);
            contarPuntos(jugadores, ronda);
            limpiarMesa(jugadores);
            System.out.println("\nResultados de la ronda " + ronda + ":");
            mostrarPuntos(jugadores);
            if (ronda != RONDAS) {
                pulsaEnter();
            }
        }
        //Acabamos todas las rondas
        mostrarGanadores(jugadores);
    }

    /**
     * Muestra el título
     */
    private static void mostrarCreditos() {
        System.out.println(""
                + "     __^__                   __^__\n"
                + "    ( ___ )-----------------( ___ )\n"
                + "     | / |                   | \\ |\n"
                + "     | / |     SUSHI GO!     | \\ |\n"
                + "     |___|                   |___|\n"
                + "    (_____)-----------------(_____)\n\n");
    }

    /**
     * Informa sobre el comienzo o cambio de ronda
     *
     * @param ronda número de la ronda
     */
    private static void mostrarRonda(int ronda) {
        System.out.println("");
        System.out.println("\t┌---------┐");
        System.out.println("\t│ RONDA " + ronda + " │");
        System.out.println("\t└---------┘\n");
        pulsaEnter();
    }

    /**
     * Pide el número de jugadores por teclado y lo devuelve
     *
     * @return el número de jugadores, como int
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
        boolean repetir;
        String[] usados = new String[jugadores.length];

        System.out.println("\nIntroduce los nombres de cada jugador"
                + " (máximo " + MAX_LONG_NOMBRE + " caracteres)");

        for (int i = 0; i < jugadores.length; i++) {
            String nombre;
            do {
                repetir = false;
                nombre = pideCadena("Jugador " + (i + 1) + ": ");

                if (nombre.length() > MAX_LONG_NOMBRE) {
                    System.err.println("El máximo de caracteres es "
                            + MAX_LONG_NOMBRE);
                    repetir = true;
                }
                for (String s : usados) {
                    if (nombre.equals(s)) {
                        System.err.println("El nombre ya está en uso");
                        repetir = true;
                    }
                }
            } while (repetir);
            jugadores[i] = new Jugador(nombre);
            usados[i] = nombre;
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
    private static void contarPuntos(Jugador[] jugadores, int ronda) {
        int maxRollos = 0;
        int cuantosGananRollos = 1;

        for (Jugador j : jugadores) {
            j.addPuntos(j.getCartasMesa().getPuntosBase(), ronda);

            if (j.getCartasMesa().getNumRollos() > maxRollos) {
                maxRollos = j.getCartasMesa().getNumRollos();
                cuantosGananRollos = 1;
            } else if (j.getCartasMesa().getNumRollos() == maxRollos) {
                cuantosGananRollos++;
            }
        }
        if (maxRollos != 0) {
            for (Jugador j : jugadores) {
                if (j.getCartasMesa().getNumRollos() == maxRollos) {
                    j.addPuntos(6 / cuantosGananRollos, ronda);
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
     * Muestra las puntuaciones de los jugadores en cada ronda y su total
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
     * Muestra por pantalla el o los ganadores del juego
     *
     * @param jugadores array de jugadores
     */
    private static void mostrarGanadores(Jugador[] jugadores) {
        Jugador[] ganadores = new Jugador[jugadores.length];
        ganadores[0] = jugadores[0];
        int numGanadores = 1;

        //Ignoraremos los jugadores de 'ganadores' de índice >= numGanadores
        for (int i = 1; i < jugadores.length; i++) {
            if (jugadores[i].getPuntos() > ganadores[0].getPuntos()) {
                ganadores[0] = jugadores[i];
                numGanadores = 1;
            } else if (jugadores[i].getPuntos() == ganadores[0].getPuntos()) {
                ganadores[numGanadores++] = jugadores[i];
            }
        }
        //Mostramos el resultado por pantalla
        if (numGanadores == 1) {
            System.out.println("El ganador es "
                    + ganadores[0].getNombre() + "!");
        } else {
            System.out.println("Hay empate! Los ganadores son:");
            for (int i = 0; i < numGanadores; i++) {
                System.out.println(ganadores[i].getNombre());
            }
        }
        System.out.println("Enhorabuena!");
    }

}
