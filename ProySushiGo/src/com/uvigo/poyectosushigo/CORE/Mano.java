/*
* Representa las cartas que tiene un jugador en la mano (las que dispone para jugar).
* Estructura: Se utilizarán TAD adecuado. 
* Funcionalidad: añadir carta a la mano, quitar carta de la mano, visualizar cartas de la mano,...
 */
package com.uvigo.poyectosushigo.CORE;

import com.uvigo.poyectosushigo.CORE.carta.*;
import java.util.List;
import lista.*;

public class Mano {

    private int numCartasMano;
    private Lista<Carta> cartasMano;

    public Mano(Lista<Carta> cartasMano) {
        numCartasMano = cartasMano.tamaño();
        this.cartasMano = cartasMano;
    }

    public Mano() {
        numCartasMano = 0;
        cartasMano = new ListaEnlazada<>();
    }

    public void añadirCartaMano(Carta c) {
        cartasMano.insertarPrincipio(c);
    }

    public void quitarCartaMano(Carta c) {
        cartasMano.suprimir(c);
    }

    public void quitarCartaMano(int pos) {
        Carta c;
        List l = (List) getCartasMano();
        c = (Carta) l.get(pos);
        quitarCartaMano(c);

    }

    public Carta getCartaMano(int pos) {
        Carta c;
        List l = (List) getCartasMano();
        c = (Carta) l.get(pos);
        return c;

    }

    public Carta getCartaMano(Carta c) {
        Carta car;

        List l = (List) getCartasMano();
        car = (Carta) l.get(l.indexOf(c));

        return car;
    }

    public int getNumCartasMano() {
        return numCartasMano;
    }

    public Lista<Carta> getCartasMano() {
        return cartasMano;
    }

    public void setCartasMano(Lista<Carta> cartasMano) {
        this.cartasMano = cartasMano;
    }

    @Override
    public String toString() {
        StringBuilder toRet = new StringBuilder();

        toRet.append("Lista de cartas de la mano:");
        for (Carta i : cartasMano) {
            toRet.append("\n\t").append(i.toString());
        }

        return toRet.toString();
    }
}
