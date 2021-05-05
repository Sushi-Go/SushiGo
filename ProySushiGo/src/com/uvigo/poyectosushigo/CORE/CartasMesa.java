/*
* Representa las cartas que coloca el jugador en la mesa (únicamente las suyas).
* Estructura: Se utilizarán TADs adecuados para su respresentación. En concreto:Una lista de pilas 
* Funcionalidad: colocar una carta en la mesa, calcular la puntuación de las cartas de la mesa, calcular el número de rollitos, visualizar cartas de la mesa, descartar cartas de la mesa, etc
*/
package com.uvigo.poyectosushigo.CORE;

import com.uvigo.poyectosushigo.CORE.carta.*;
import lista.*;
import pila.*;

public class CartasMesa {
    private Lista<Baraja> cartasMesa;
    private int numCartasMesa;
    
    public CartasMesa(){
        numCartasMesa=0;
        cartasMesa=new ListaEnlazada<>();
    }
    
    public void ponerSobreMesa(Carta c){
<<<<<<< HEAD
=======
        
>>>>>>> f95d92bc0b30ad388980cda51a545c807cadb2b4
    }
    
    public void retirarCartaMesa(Carta c){

    }
    
    public int calcularPuntuacion(){
        
    }
    
    public void limpiarFinalRonda(){

        
    }
    
    
    public int calcularNumRollitos(){
        
    }
    
    public void visualizarMesa(){
        
    }

}
