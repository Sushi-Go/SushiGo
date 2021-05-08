/*
 * Representa a un jugador de la partida, identificado por el nombre 
 * Funcionalidad: escoge una carta de su mano; la colocará en su mesa; coge cartas de la baraja para la mano,
 *                entrega las cartas de su mano; coge las cartas de otra mano; calcula su puntuación por ronda;
 *                calcula su puntuación total; cuenta cuantos rollitos tiene en su mesa; ....
 */
package com.uvigo.poyectosushigo.CORE;

import static com.uvigo.proyectosushigo.IU.Main.RONDAS;

public class Jugador {

    private final String nombre;
    private Mano mano;
    private CartasMesa cartasMesa;
    private final int puntos[];

    /**
     * Crea un jugador
     *
     * @param nombre nombre del jugador
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.mano = new Mano();
        this.cartasMesa = new CartasMesa();
        this.puntos = new int[RONDAS];
    }

    /**
     * Devuelve el nombre del jugador
     *
     * @return el nombre del jugador, como String
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve la mano del jugador
     *
     * @return la Mano del jugador
     */
    public Mano getMano() {
        return mano;
    }

    /**
     * Cambia la mano del jugador
     *
     * @param mano nueva Mano del jugador
     */
    public void setMano(Mano mano) {
        this.mano = mano;
    }

    /**
     * Devuelve las cartas de la mesa del jugador
     *
     * @return CartasMesa del jugador
     */
    public CartasMesa getCartasMesa() {
        return cartasMesa;
    }

    /**
     * Quita todas las cartas del jugador de la mesa
     */
    public void limpiarMesa() {
        this.cartasMesa = new CartasMesa();
    }

    /**
     * Devuelve los puntos asignados al jugador en una ronda
     *
     * @param ronda la ronda de la que queremos obtener los puntos
     * @return los puntos de esa ronda, como int
     */
    public int getPuntos(int ronda) {
        return puntos[ronda - 1];
    }

    /**
     * Devuelve el total de puntos del jugador
     *
     * @return la suma de los puntos de todas las rondas, como int
     */
    public int getPuntos() {
        int toret = 0;
        for (int i = 0; i < puntos.length; i++) {
            toret += puntos[i];
        }
        return toret;
    }

    /**
     * Añade puntos al jugador en una ronda
     *
     * @param puntos cantidad de puntos a añadir
     * @param ronda ronda en la que se añaden
     */
    public void addPuntos(int puntos, int ronda) {
        this.puntos[ronda - 1] += puntos;
    }

}
