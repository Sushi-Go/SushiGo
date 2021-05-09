/*
 * Representa una carta, formada por un nombre
 */
package com.uvigo.poyectosushigo.CORE;

public class Carta {

    private final String nombre;

    /**
     * Crea una nueva carta
     *
     * @param nombre nombre de la carta
     */
    public Carta(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el nombre de la carta
     *
     * @return el nombre de la carta, como String
     */
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(nombre).append("]");
        return sb.toString();
    }

}
