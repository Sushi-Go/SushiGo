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

    public Mano(int numCartas,Lista<Carta> cartasMano){
        this.numCartas=numCartas;
        this.cartasMano=cartasMano;
    }
    
    public Mano(int numCartas){
        this.numCartas=numCartas;
    }
    
    public void aniadirCartaMano(Carta c){
        
    }
    
    public void quitarCartaMano(Carta c){
        
    }
    
    @Override
    public String toString(){
        StringBuilder toRet=new StringBuilder();
        Lista<Carta> temp=new ListaEnlazada<>();
        
        toRet.append("Lista de Cartas:");
        for(Carta i:cartasMano){
            toRet.append("\n\t").append(i.toString());
            temp.insertarPrincipio(i);
        }
        cartasMano=temp;
        
        return toRet.toString();
    }
}
