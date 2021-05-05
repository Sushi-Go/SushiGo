package com.uvigo.poyectosushigo.CORE;

import pila.*;
import com.uvigo.poyectosushigo.CORE.carta.*;

public class Baraja {

    private Pila<Carta> baraja;
    private int numCartas;

    public Baraja() {
        numCartas = 0;
        baraja = new EnlazadaPila<>();
        //Creamos las 5 cartas de nigiri de calamar
        for (int i = 1; i <= 5; i++) {
            baraja.push(new NigiriCalamar());
            numCartas++;
        }
        //Creamos las 10 cartas de nigiri de salmon
        for (int i = 1; i <= 10; i++) {
            baraja.push(new NigiriSalmon());
            numCartas++;
        }
        //Creamos las 5 cartas de nigiri de tortilla
        for (int i = 1; i <= 5; i++) {
            baraja.push(new NigiriTortilla());
            numCartas++;
        }
        //Creamos las 6 cartas de wasabi
        for (int i = 1; i <= 6; i++) {
            baraja.push(new Wasabi());
            numCartas++;
        }
        //Creamos las 6 cartas de maki de 1 rollo
        for (int i = 1; i <= 6; i++) {
            baraja.push(new Maki(1));
            numCartas++;
        }
        //Creamos las 12 cartas de maki de 2 rollos
        for (int i = 1; i <= 12; i++) {
            baraja.push(new Maki(2));
            numCartas++;
        }
        //Creamos las 8 cartas de maki de 3 rollos
        for (int i = 1; i <= 8; i++) {
            baraja.push(new Maki(3));
            numCartas++;
        }
        //Creamos las 14 cartas de tempura
        for (int i = 1; i <= 14; i++) {
            baraja.push(new Tempura());
            numCartas++;
        }
        //Creamos las 14 cartas de sashimi
        for (int i = 1; i <= 14; i++) {
            baraja.push(new Sashimi());
            numCartas++;
        }
        //Creamos las 14 cartas de gyoza
        for (int i = 1; i <= 14; i++) {
            baraja.push(new Gyoza());
            numCartas++;
        }
    }

    private void barajar() {

    }

    public Carta darCarta() {
        return baraja.pop();
        //DEvuelve la primera carta en el mazo
    }

}
