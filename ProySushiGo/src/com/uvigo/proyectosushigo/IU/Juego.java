/**
 * Representa el juego del sushiGo, con sus reglas.
 * Se recomienda una implementación modular.
 */
package com.uvigo.proyectosushigo.IU;

import com.uvigo.poyectosushigo.CORE.Jugador;
import java.util.Scanner;

public class Juego {

    private static Jugador[] jugadores;
    private static int numJugadores;
    private int cartasPorJugador;
    private int rondaActual;

    public static void inicio() {
//Primero se obtiene el numero de jugadores
    numJugadores= cuantosJugadores();
//Se crea el array de jugadores de la partida
    jugadores= new Jugador[numJugadores];
//Se añaden los jugadores al array
    
  
    
        

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

    public static void setJugadores(Jugador[] jugadores) {
        Juego.jugadores = jugadores;
    }

    public static void setNumJugadores(int numJugadores) {
        Juego.numJugadores = numJugadores;
    }

    public void setCartasPorJugador(int cartasPorJugador) {
        this.cartasPorJugador = cartasPorJugador;
    }

    public void setRondaActual(int rondaActual) {
        this.rondaActual = rondaActual;
    }
    

    public static int cuantosJugadores(){
        int num;
        Scanner sc= new Scanner(System.in);
        System.out.println("Introduce el número de jugadores de la partida");
        num=sc.nextInt();
        return num;
    }
    
    public void anhadeRonda(){
        setRondaActual(getRondaActual()+1);
    }
    
    

}
