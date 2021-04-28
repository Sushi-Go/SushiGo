/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.uvigo.proyectosushigo.IU;

import java.util.Scanner;


public class ES
{
    public static Scanner leer = new Scanner(System.in);
    
    public static String pideCadena(String mensaje)
    {
         // Poner el mensaje
            System.out.println(mensaje);
               
             // Pedir
            return leer.nextLine();
               
    }
    
        
    public static int pideNumero(String mensaje)
    {
        
            System.out.println(mensaje);

            // Pedir
            return Integer.parseInt(leer.nextLine());
       
    }
}
