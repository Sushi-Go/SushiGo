package com.uvigo.proyectosushigo.IU;

import java.util.Scanner;

public class ES {

    public static Scanner scanner = new Scanner(System.in);

    /**
     * Devuelve una cadena leída por teclado
     *
     * @param mensaje literal que se imprime por pantalla al usuario
     * @param permiteVacia indica si se admite una cadena en blanco
     * @return la cadena leída, como String
     */
    public static String pideCadena(String mensaje, boolean permiteVacia) {
        String toret = "";
        do {
            System.out.print(mensaje);
            toret = scanner.nextLine();
        } while (toret.isBlank());
        return toret;
    }

    /**
     * Devuelve una cadena leída por teclado
     *
     * @param mensaje literal que se imprime por pantalla al usuario
     * @return la cadena leída, como String
     */
    public static String pideCadena(String mensaje) {
        return pideCadena(mensaje, false);
    }

    /**
     * Devuelve un entero leído por teclado
     *
     * @param mensaje literal que se imprime por pantalla al usuario
     * @return el entero, como int
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

    /**
     * Devuelve un real leído por teclado
     *
     * @param mensaje literal que se imprime por pantalla al usuario
     * @return el real leído, como double
     */
    private static double pideReal(String mensaje) {
        boolean esValido = false;
        double leer = 0;

        do {
            try {
                leer = Double.parseDouble(pideCadena(mensaje).trim());
                esValido = true;
            } catch (NumberFormatException e) {
                System.err.println("La cadena introducida no se puede "
                        + "convertir a número real. Por favor, "
                        + "introdúcela de nuevo.");
            }
        } while (!esValido);

        return leer;
    }

    /**
     * Devuelve un carácter leído por teclado
     *
     * @param mensaje literal que se imprime por pantalla al usuario
     * @return el carácter leído, como char
     */
    private static char pideCaracter(String msg) {
        return pideCadena(msg).toUpperCase().charAt(0);
    }

}
