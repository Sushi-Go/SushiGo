package com.uvigo.poyectosushigo.CORE;

import pila.*;
import lista.*;
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

    public void barajar() {
            Pila<Carta> aux1=null;//Crea dos pilas auxiliares
            Pila<Carta> aux2=null;
            //Se repite varias veces para aumentar la aleatoriedad
            for(int i=0; i<10;i++){
            //mientras que la baraja no este vacia:
            //introduce aleatoriamente los elementos en dos arrays
            while(!baraja.esVacio()){
                 int aleatorio= (int)Math.random()*10; 
                 if(aleatorio<5){
                     aux1.push(baraja.pop());
                 }
                 else{
                     aux2.push(baraja.pop());
                 }
            }
            //se devuelven a la pila principal
            while(!aux1.esVacio()){
                baraja.push(aux1.pop());
            }
            while(!aux2.esVacio()){
                baraja.push(aux2.pop());
            }
            }
    }

    public Carta darCarta() {
        return baraja.pop();
        //Devuelve la primera carta en el mazo
    }

}
