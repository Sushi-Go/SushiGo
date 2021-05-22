/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import static com.uvigo.poyectosushigo.CORE.Carta.MAX_LONG_CARTA;

public class CartasMesa {

    private final List<Stack<Carta>> cartasMesa;
    private int puntosBase;

    /**
     * Crea un nuevo CartasMesa vacío
     */
    public CartasMesa() {
        this.cartasMesa = new ArrayList<>();
        this.puntosBase = 0;
    }

    /**
     * Devuelve los puntos base de la mesa
     *
     * @return la suma de todos los puntos, excepto los makis, como int
     */
    public int getPuntosBase() {
        return puntosBase;
    }

    /**
     * Devuelve el total de rollos de maki
     *
     * @return la suma de los rollos de todas las cartas de la mesa, como int
     */
    public int getNumRollos() {
        int toret = 0;
        int pila = 0;

        while (toret == 0 && pila < cartasMesa.size()) {
            if (cartasMesa.get(pila).peek().getNombre().startsWith("Maki")) {
                for (Carta c : cartasMesa.get(pila)) {
                    toret += Integer.parseInt(c.getNombre().substring(8, 9));
                }
            }
            pila++;
        }
        return toret;
    }

    /**
     * Añade una carta a la mesa en la posición adecuada y actualiza puntosBase
     * y numRollos según la nueva carta
     *
     * @param carta carta a añadir
     */
    public void addCarta(Carta carta) {
        int nuevosPuntos = 0;

        if (carta.getNombre().startsWith("Nigiri")) {
            if (carta.getNombre().endsWith("calamar")) {
                nuevosPuntos = 3;
            } else if (carta.getNombre().endsWith("salmón")) {
                nuevosPuntos = 2;
            } else if (carta.getNombre().endsWith("tortilla")) {
                nuevosPuntos = 1;
            }
            if (apilarSobre(carta, "Wasabi") != null) {
                nuevosPuntos *= 3;
            } else {
                if (apilarSobre(carta, carta.getNombre()) == null) {
                    if (apilarSobre(carta, "Nigiri") == null) {
                        apilar(carta);
                    }
                }
            }
        } else if (carta.getNombre().equals("Wasabi")) {
            apilar(carta);

        } else if (carta.getNombre().startsWith("Maki")) {
            if (apilarSobre(carta, "Maki") == null) {
                apilar(carta);
            }

        } else if (carta.getNombre().equals("Tempura")) {
            Stack<Carta> pila = apilarSobre(carta, "Tempura");

            if (pila == null) {
                apilar(carta);
            } else {
                if (pila.size() % 2 == 0) {
                    nuevosPuntos = 5;
                }
            }

        } else if (carta.getNombre().equals("Sashimi")) {
            Stack<Carta> pila = apilarSobre(carta, "Sashimi");

            if (pila == null) {
                apilar(carta);
            } else {
                if (pila.size() % 3 == 0) {
                    nuevosPuntos = 10;
                }
            }

        } else if (carta.getNombre().equals("Gyoza")) {
            Stack<Carta> pila = apilarSobre(carta, "Gyoza");

            if (pila == null) {
                apilar(carta);
                nuevosPuntos = 1;
            } else {
                if (pila.size() <= 5) {
                    nuevosPuntos = pila.size();
                }
            }
        }
        puntosBase += nuevosPuntos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Stack<Carta>> copia = new ArrayList<>(cartasMesa.size());
        int altura = 1;

        for (Stack<Carta> pila : cartasMesa) {
            copia.add((Stack<Carta>) pila.clone());
            if (pila.size() > altura) {
                altura = pila.size();
            }
        }
        //Cada iteración añade las cartas de una altura, empezando por arriba
        while (altura > 0) {
            for (Stack<Carta> pila : copia) {
                if (pila.size() == altura) {
                    sb.append(cadenaCentrada(
                            pila.pop().toString(), MAX_LONG_CARTA));
                } else {
                    sb.append(cadenaCentrada("", MAX_LONG_CARTA));
                }
                sb.append("\t");
            }
            altura--;
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Añade la carta a una pila nueva
     *
     * @param carta carta a añadir
     */
    private void apilar(Carta carta) {
        Stack<Carta> pila = new Stack<>();
        pila.push(carta);
        cartasMesa.add(pila);
    }

    /**
     * Busca una pila, le añade una carta y devuelve la pila. Si no se encuentra
     * la pila, devuelve null
     *
     * @param carta carta a añadir
     * @param buscar comienzo del nombre de la carta que buscamos en las pilas
     * @return la pila en la que se añade la carta, o null si no se encuentra
     */
    private Stack<Carta> apilarSobre(Carta carta, String buscar) {
        for (Stack<Carta> pila : cartasMesa) {
            if (!pila.empty() && pila.peek().getNombre().startsWith(buscar)) {
                pila.push(carta);
                return pila;
            }
        }
        return null;
    }

    /**
     * Devuelve una cadena alineada al centro
     *
     * @param cadena String que se pondrá en el centro
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
