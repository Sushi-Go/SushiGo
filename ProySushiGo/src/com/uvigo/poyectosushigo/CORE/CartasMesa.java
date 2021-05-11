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
    private int numRollos;

    /**
     * Crea un nuevo CartasMesa vacío
     */
    public CartasMesa() {
        this.cartasMesa = new ArrayList<>();
        this.puntosBase = 0;
        this.numRollos = 0;
    }

    //Calcula la puntuacion de las cartas
//    public int calcularPuntuacion() {
//        int puntos=0,contadorTempura=0,contadorSashimi=0;
//        Carta c;
//        Pila<Carta> temp=new EnlazadaPila<>();
//        
//        for(Pila<Carta> i: cartasMesa){
//            switch(i.top().getNombre()){
//                case "Tempura":
//                    if(i.tamaño()%2==0 && i.tamaño()!=0){
//                        for(int j=0;j<i.tamaño();j=j+2){
//                            puntos+=5;
//                        }
//                    }
//                    break;
//                case "Sashimi":
//                    if(i.tamaño()%3==0 && i.tamaño()!=0){
//                        for(int j=0;j<i.tamaño();j=j+3){
//                            puntos+=10;
//                        }
//                    }
//                    break;
//                case "Gyoza":
//                    switch (i.tamaño()) {
//                        case 1:
//                            puntos+=1;
//                            break;
//                        case 2:
//                            puntos+=3;
//                            break;
//                        case 3:
//                            puntos+=6;
//                            break;
//                        case 4:
//                            puntos+=10;
//                            break;
//                        default:
//                            puntos+=15;
//                    }
//                    break;
//                case "Maki de 1 rollo":
//                    
//                    break;
//                case "Maki de 2 rollos":
//                    
//                    break;
//                case "Maki de 3 rollos":
//                    
//                    break;
//                case "Wasabi":
//                    
//                    break;
//                case "Nigiri de calamar":
//                    
//                    break;
//                case "Nigiri de salmon":
//                    
//                    break;
//                case "Nigiri de tortilla":
//                    
//                    break;
//            }
//        }
//        return puntos;
//    }
    /**
     * Devuelve los puntos base de la mesa
     *
     * @return la suma de todos los puntos, excepto los makis, como int
     */
    public int getPuntosBase() {
        return puntosBase;
    }

    /**
     * Devuelve el total de rollos de de maki
     *
     * @return la suma de los rollos de todas las cartas de la mesa, como int
     */
    public int getNumRollos() {
        return numRollos;
    }

    public int calcularNumRollitos(Carta c) {
        int toRet = 0;

        switch (c.getNombre()) {
            case "Maki de 1 rollo":
                toRet = 1;
                break;
            case "Maki de 2 rollos":
                toRet = 2;
                break;
            case "Maki de 3 rollos":
                toRet = 3;
                break;
            default:
                System.err.println("La carta no es un maki");
        }

        return toRet;
    }

    /**
     * Añade una carta a la mesa en la posición adecuada y actualiza puntosBase
     * y numRollos según la nueva carta
     *
     * @param carta Carta a añadir
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
            if (apilar(carta, "Wasabi") != null) {
                nuevosPuntos *= 3;
            } else {
                if (apilar(carta, carta.getNombre()) == null) {
                    if (apilar(carta, "Nigiri") == null) {
                        apilar(carta, "");
                    }
                }
            }
        } else if (carta.getNombre().equals("Wasabi")) {
            apilar(carta, "");

        } else if (carta.getNombre().startsWith("Maki")) {
            if (apilar(carta, "Maki") == null) {
                apilar(carta, "");
            }
            numRollos += Integer.parseInt(carta.getNombre().substring(8, 9));

        } else if (carta.getNombre().equals("Tempura")) {
            Stack<Carta> pila = apilar(carta, "Tempura");

            if (pila == null) {
                apilar(carta, "");
            } else {
                if (pila.size() % 2 == 0) {
                    nuevosPuntos = 5;
                }
            }

        } else if (carta.getNombre().equals("Sashimi")) {
            Stack<Carta> pila = apilar(carta, "Sashimi");

            if (pila == null) {
                apilar(carta, "");
            } else {
                if (pila.size() % 3 == 0) {
                    nuevosPuntos = 10;
                }
            }

        } else if (carta.getNombre().equals("Gyoza")) {
            Stack<Carta> pila = apilar(carta, "Gyoza");

            if (pila == null) {
                pila = apilar(carta, "");
            }
            if (pila.size() <= 5) {
                nuevosPuntos = pila.size();
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
     * Busca una pila, añade una carta y devuelve la pila. Si 'buscar' es una
     * cadena vacía, se añade otra pila sobre la que se pone la carta (y se
     * devuelve esa pila). Si no se encuentra la pila devuelve null
     *
     * @param carta carta a añadir
     * @param buscar comienzo del nombre de carta que buscamos en las pilas
     * @return la pila en la que se añade la carta, o null si no se encuentra
     */
    private Stack<Carta> apilar(Carta carta, String buscar) {
        if (buscar.equals("")) {
            Stack<Carta> pila = new Stack<>();
            pila.push(carta);
            cartasMesa.add(pila);
            return pila;
        }
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
