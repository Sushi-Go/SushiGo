/*
* Representa la baraja del sushiGo, 94 cartas, cada una representa a una comida 
* Estructura: se utilizará un TAD adecuado
* Funcionalidad: estando la baraja desordenada, devolverá la carta situada encima del montón de cartas
 */
package com.uvigo.poyectosushigo.CORE;

import java.util.Stack;

public class Baraja {
    
    private final Stack<Carta> baraja;
    
    /**
     * Crea una baraja con todas las cartas
     */
    public Baraja() {
        baraja = new Stack<>();
        addCartas("Nigiri de calamar", 5);
        addCartas("Nigiri de salmón", 10);
        addCartas("Nigiri de tortilla", 5);
        addCartas("Wasabi", 6);
        addCartas("Maki de 1 rollo", 6);
        addCartas("Maki de 2 rollos", 12);
        addCartas("Maki de 3 rollos", 8);
        addCartas("Tempura", 14);
        addCartas("Sashimi", 14);
        addCartas("Gyoza", 14);
    }
    
    /**
     * Devuelve el número de cartas de la baraja
     * 
     * @return el número de cartas, como int
     */
    public int numCartas() {
        return baraja.size();
    }
    
    /**
     * Elimina y devuelve la primera carta de la baraja
     * 
     * @return la primera carta de la pila
     */
    public Carta cogerCarta() {
        return baraja.pop();
    }
    
    /**
     * Reordena la baraja de forma aleatoria
     */
    public void barajar() {
        int numCartas = numCartas();
        Carta[] aux = new Carta[numCartas];
        int random;
        //Mueve todas las cartas a un array
        for (int i = 0; !baraja.empty(); i++) {
            aux[i] = cogerCarta();
        }
        //En cada iteración mueve una carta aleatoria del array a la baraja
        for (int i = numCartas - 1; i >= 0; i--) {
            random = (int) (Math.random() * i);
            baraja.push(aux[random]);
            aux[random] = aux[i];
        }
    }
    
    /**
     * Añade varias cartas a la baraja
     * 
     * @param nombre nombre de las cartas
     * @param cantidad número de cartas a añadir
     */
    private void addCartas(String nombre, int cantidad) {
        for (int i = 0; i < cantidad; i++) {
            baraja.push(new Carta(nombre));
        }
    }
    
}
