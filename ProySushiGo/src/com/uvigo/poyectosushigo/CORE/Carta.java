/*
 * Representa una carta, formada por un nombre
 */
package com.uvigo.poyectosushigo.CORE;

public class Carta {
    private String nombre;
    
    public Carta(String nombre){
        this.nombre=nombre;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    @Override
    public String toString(){
        return "[" + getNombre() + "]";
    }
	
}
