/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
*/
package com.uvigo.poyectosushigo.CORE;

import com.uvigo.poyectosushigo.CORE.carta.*;
import lista.*;

public class CartasMesa {
    private Lista<Carta> cartasMesa;
    private int numCartasMesa;
    
    public CartasMesa(){
        numCartasMesa=0;
        cartasMesa=new ListaEnlazada<>();
    }
    
    public void ponerSobreMesa(Carta c){
        cartasMesa.insertarPrincipio(c);
    }
    
    public void retirarCarta(Carta c){
        cartasMesa.suprimir(c);
    }
    
    
    

}
