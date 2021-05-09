package com.uvigo.poyectosushigo.CORE;

import pila.EnlazadaPila;
import pila.Pila;

public class Baraja {
    
    private final Pila<Carta> baraja;
    
    /**
     * Crea una baraja con todas las cartas
     */
    public Baraja() {
        baraja = new EnlazadaPila<>();
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
        return baraja.tamaño();
    }
    
    /**
     * Devuelve la primera carta de la baraja
     * 
     * @return la primera Carta de la pila
     */
    public Carta quitarCarta() {
        return baraja.pop();
    }
    
    /**
     * Reordena la baraja de forma aleatoria
     */
    public void barajar() {
        int numCartas = numCartas();
        Carta[] aux = new Carta[numCartas];
        int random;

        for (int i = 0; !baraja.esVacio(); i++) {
            aux[i] = quitarCarta();
        }

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
