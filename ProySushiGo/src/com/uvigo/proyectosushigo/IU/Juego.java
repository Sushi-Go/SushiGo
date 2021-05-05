/**
 * Representa el juego del sushiGo, con sus reglas.
 * Se recomienda una implementación modular.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.Jugador;
import java.util.Scanner;

public class Juego {

    private Jugador[] jugadores;
    private  int numJugadores;
    private int cartasPorJugador;
    private int rondaActual;

    public  void inicio() {
//Primero se obtiene el numero de jugadores
    numJugadores= cuantosJugadores();
    jugadores=new Jugador[20];
    
        

    }

    public Jugador[] getJugadores() {
        return jugadores;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public int getCartasPorJugador() {
        return cartasPorJugador;
    }

    public int getRondaActual() {
        return rondaActual;
    }

    public  int cuantosJugadores(){
        int num;
        Scanner sc= new Scanner(System.in);
        System.out.println("Introduce el número de jugadores de la partida");
        num=sc.nextInt();
        return num;
    }
    
    

}
