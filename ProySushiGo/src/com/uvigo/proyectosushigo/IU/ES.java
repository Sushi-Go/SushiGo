package com.uvigo.proyectosushigo.IU;

import java.util.Scanner;

public class ES {

    public static Scanner leer = new Scanner(System.in);

    public static String pideCadena(String mensaje) {
        String toret = "";
        do {
            System.out.print(mensaje);
            toret = leer.nextLine();
        } while (toret.isBlank());
        return toret;
    }

    public static int pideNumero(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        boolean esValido = false; // True: entero leido correctamente
        int leer = 0;

        do {
            try {
                System.out.print(mensaje);
                leer = Integer.parseInt(scanner.nextLine().trim());
                esValido = true;
            } catch (NumberFormatException e) {
                System.err.println("La cadena introducida no se puede "
                        + "convertir a número entero. Por favor, "
                        + "introdúcela de nuevo.");
            }
        } while (!esValido);

        return leer;
    }

    private static double pideReal(String mensaje) {
        Scanner scanner = new Scanner(System.in);
        boolean esValido = false; // True: entero leido correctamente
        double leer = 0;

        do {
            try {
                System.out.print(mensaje);
                leer = Double.parseDouble(scanner.nextLine().trim());
                esValido = true;
            } catch (NumberFormatException e) {
                System.err.println("La cadena introducida no se puede "
                        + "convertir a número real. Por favor, "
                        + "introdúcela de nuevo.");
            }
        } while (!esValido);

        return leer;
    }

    private static char pideCaracter(String msg) {
        return pideCadena(msg).toUpperCase().charAt(0);
    }

}
