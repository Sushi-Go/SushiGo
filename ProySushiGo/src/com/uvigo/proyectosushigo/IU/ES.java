package com.uvigo.proyectosushigo.IU;

import java.util.Scanner;

public class ES {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * Devuelve una cadena leída por teclado
     *
     * @param mensaje literal que se muestra por pantalla al usuario
     * @return la cadena leída, como String
     */
    public static String pideCadena(String mensaje) {
        String toret = "";
        do {
            System.out.print(mensaje);
            toret = scanner.nextLine();
        } while (toret.isBlank());
        return toret;
    }

    /**
     * Devuelve un entero leído por teclado
     *
     * @param mensaje literal que se muestra por pantalla al usuario
     * @return el entero leído, como int
     */
    public static int pideEntero(String mensaje) {
        boolean esValido = false;
        int leer = 0;

        do {
            try {
                leer = Integer.parseInt(pideCadena(mensaje).trim());
                esValido = true;
            } catch (NumberFormatException e) {
                System.err.println("La cadena introducida no se puede "
                        + "convertir a número entero. Por favor, "
                        + "introdúcela de nuevo.");
            }
        } while (!esValido);

        return leer;
    }

}
