/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
 */
package com.uvigo.poyectosushigo.CORE;

import lista.*;
import pila.*;

public class CartasMesa {

    private final Lista<Pila<Carta>> cartasMesa;
    private int puntosBase;
    private int numRollos;

    /**
     * Crea un nuevo cartasMesa
     */
    public CartasMesa() {
        this.cartasMesa = new ListaEnlazada<>();
        puntosBase = 0;
        numRollos = 0;
    }

    //Calcula la puntuacion de las cartas
    public int calcularPuntuacion() {
        int puntos=0,contadorTempura=0,contadorSashimi=0;
        Carta c;
        Pila<Carta> temp=new EnlazadaPila<>();
        
        for(Pila<Carta> i: cartasMesa){
            switch(i.top().getNombre()){
                case "Tempura":
                    if(i.tamaño()%2==0 && i.tamaño()!=0){
                        for(int j=0;j<i.tamaño();j=j+2){
                            puntos+=5;
                        }
                    }
                    break;
                case "Sashimi":
                    if(i.tamaño()%3==0 && i.tamaño()!=0){
                        for(int j=0;j<i.tamaño();j=j+3){
                            puntos+=10;
                        }
                    }
                    break;
                case "Gyoza":
                    switch (i.tamaño()) {
                        case 1:
                            puntos+=1;
                            break;
                        case 2:
                            puntos+=3;
                            break;
                        case 3:
                            puntos+=6;
                            break;
                        case 4:
                            puntos+=10;
                            break;
                        default:
                            puntos+=15;
                    }
                    break;
                case "Maki de 1 rollo":
                    
                    break;
                case "Maki de 2 rollos":
                    
                    break;
                case "Maki de 3 rollos":
                    
                    break;
                case "Wasabi":
                    
                    break;
                case "Nigiri de calamar":
                    
                    break;
                case "Nigiri de salmon":
                    
                    break;
                case "Nigiri de tortilla":
                    
                    break;
            }
        }
        return puntos;
    }
    
    /**
     * Devuelve los puntos base de la mesa
     * 
     * @return la suma de todos los puntos, excepto los makis
     */
    public int getPuntosBase() {
        return puntosBase;
    }
    
    /**
     * Devuelve el total de rollos
     *
     * @return la suma de los rollos de todas las cartas de la mesa
     */
    public int getNumRollos() {
        return numRollos;
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
            Pila<Carta> p = apilar(carta, "Tempura");

            if (p == null) {
                apilar(carta, "");
            } else {
                if (p.tamaño() % 2 == 0) {
                    nuevosPuntos = 5;
                }
            }

        } else if (carta.getNombre().equals("Sashimi")) {
            Pila<Carta> p = apilar(carta, "Sashimi");

            if (p == null) {
                apilar(carta, "");
            } else {
                if (p.tamaño() % 3 == 0) {
                    nuevosPuntos = 10;
                }
            }

        } else if (carta.getNombre().equals("Gyoza")) {
            Pila<Carta> p = apilar(carta, "Gyoza");

            if (p == null) {
                p = apilar(carta, "");
            }
            if (p.tamaño() <= 5) {
                nuevosPuntos = p.tamaño();
            }
        }
        puntosBase += nuevosPuntos;
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
     * Devuelve la pila en la que se inserta 'carta'. Si 'buscar' es "", 'carta'
     * se añade a una pila nueva (que se devuelve). Si no, busca la pila que
     * empiece por 'buscar', y si la encuentra, añade 'carta' y devuelve la 
     * pila. Si no encuentra la pila, 'carta' no se inserta y devuelve null.
     *
     * @param carta carta a añadir
     * @param buscar comienzo del nombre de carta que buscamos en las pilas
     * @return la pila en la que se añade la carta, o null si no se encuentra
     */
    private Pila<Carta> apilar(Carta carta, String buscar) {
        if (buscar.equals("")) {
            Pila<Carta> pila = new EnlazadaPila<>();
            pila.push(carta);
            cartasMesa.insertarFinal(pila);
            return pila;
        }
        for (Pila<Carta> actual : cartasMesa) {
            if (!actual.esVacio() && actual.top().getNombre().startsWith(buscar)) {
                actual.push(carta);
                return actual;
            }
        }
        return null;
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