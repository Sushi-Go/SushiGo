package a5e1;


import pila.*;

public class Baraja {

    private Pila<Carta> baraja;
    private int numCartas;

    public Baraja() {
        numCartas = 0;
        baraja = new EnlazadaPila<>();
        //Creamos las 5 carta de nigiri de calamar
        for(int i=1; i<=5;i++){
            baraja.push(new Carta("Nigiri de calamar"));
            numCartas++;
        }
         //Creamos las 5 carta de nigiri de salmon
        for(int i=1; i<=10;i++){
            baraja.push(new Carta("Nigiri de salmón"));
            numCartas++;
        }
        for(int i=1; i<=5;i++){
            baraja.push(new Carta("Nigiri de tortilla"));
            numCartas++;
        }
        for(int i=1; i<=6;i++){
            baraja.push(new Carta("Wasabi"));
            numCartas++;
        }
        for(int i=1; i<=6;i++){
            baraja.push(new Carta("Maki 1 Rollo"));
            numCartas++;
        }
        for(int i=1; i<=12;i++){
            baraja.push(new Carta("Maki 2 Rollo"));
            numCartas++;
        }
        for(int i=1; i<=8;i++){
            baraja.push(new Carta("Maki 3 Rollo"));
            numCartas++;
        }
        for(int i=1; i<=14;i++){
            baraja.push(new Carta("Tempura"));
            numCartas++;
        }
        for(int i=1; i<=14;i++){
            baraja.push(new Carta("Sashimi"));
            numCartas++;
        }
        for(int i=1; i<=14;i++){
            baraja.push(new Carta("Gyoza"));
            numCartas++;
        }
    }
    private void barajar(){
        
    }
    
    public Carta darCarta(){
        return baraja.pop(); 
    //DEvuelve la primera carta en el mazo
    }

}
