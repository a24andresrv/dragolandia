package com.example.vista;

import java.util.Scanner;

public interface Vista {
    
    Scanner sc= new Scanner(System.in);

    public static Integer leerEntero() {
        Integer entero = null;
        while (entero == null) {
            try {
                entero = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Introduce un entero: " + e.getMessage());
            }
        }
        return entero;
    }

    public static String leerLinea() {
        return sc.nextLine();
    }
}
