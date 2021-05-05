/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado. 
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
 */
package com.uvigo.poyectosushigo.CORE;

import com.uvigo.poyectosushigo.CORE.carta.*;
import lista.*;

public class Mano {
    private int numCartasMano;
    private Lista<Carta> cartasMano;

    public Mano(int numCartasMano,Lista<Carta> cartasMano){
        this.numCartasMano=numCartasMano;
        this.cartasMano=cartasMano;
    }
    
    public Mano(int numCartasMano){
        this.numCartasMano=numCartasMano;
    }
    
    public void aniadirCartaMano(Carta c){
        
    }
    
    public void quitarCartaMano(Carta c){
        
    }

    public int getNumCartasMano() {
        return numCartasMano;
    }

    public Lista<Carta> getCartasMano() {
        return cartasMano;
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
