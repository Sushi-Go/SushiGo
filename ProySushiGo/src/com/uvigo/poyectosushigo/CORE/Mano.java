/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado. 
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
 */
package com.uvigo.poyectosushigo.CORE;

import com.uvigo.poyectosushigo.CORE.carta.*;
import lista.*;

public class Mano {

    private int numCartas;
    private Lista<Carta> cartasMano;

    public Mano(){
        
    }
    
    public void aniadirCartaMano(Carta c){
        
    }
    
    public void quitarCartaMano(Carta c){
        
    }
    
    public void visualizarCartas(){
        StringBuilder vis=new StringBuilder();
        Lista<Carta> temp=new ListaEnlazada<>();
        
        vis.append("Lista de Cartas:");
        for(Carta i:cartasMano){
            vis.append("\n\t").append(i.toString());
            temp.insertarPrincipio(i);
        }
        cartasMano=temp;
        
        System.out.println(vis);
    }
}
