package com.uvigo.poyectosushigo.CORE;

import pila.*;
import com.uvigo.poyectosushigo.CORE.carta.*;

public class Baraja {

    private Pila<Carta> baraja;
    private int numCartas;

    public Baraja() {
        numCartas = 0;
        baraja = new EnlazadaPila<>();
        //Creamos las 5 carta de nigiri de calamar
        for (int i = 1; i <= 5; i++) {
            baraja.push(new NigiriCalamar());
            numCartas++;
        }
        //Creamos las 5 carta de nigiri de salmon
        for (int i = 1; i <= 10; i++) {
            baraja.push(new NigiriSalmon());
            numCartas++;
        }
        for (int i = 1; i <= 5; i++) {
            baraja.push(new NigiriTortilla());
            numCartas++;
        }
        for (int i = 1; i <= 6; i++) {
            baraja.push(new Wasabi());
            numCartas++;
        }
        for (int i = 1; i <= 6; i++) {
            baraja.push(new Maki(1));
            numCartas++;
        }
        for (int i = 1; i <= 12; i++) {
            baraja.push(new Maki(2));
            numCartas++;
        }
        for (int i = 1; i <= 8; i++) {
            baraja.push(new Maki(3));
            numCartas++;
        }
        for (int i = 1; i <= 14; i++) {
            baraja.push(new Tempura());
            numCartas++;
        }
        for (int i = 1; i <= 14; i++) {
            baraja.push(new Sashimi());
            numCartas++;
        }
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
