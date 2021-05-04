/*
 * Carta de nigiri
 */
package com.uvigo.poyectosushigo.CORE.carta;

public class Nigiri extends Carta{
    private String nombre;
    private int valor;
    
    public Nigiri(String nombre,int valor){
        super(nombre);
        this.valor=valor;
    }
    
}
