package com.example.vista;

import com.example.modelo.Mago;
import com.example.modelo.Monstruo;

import java.util.List;
import java.util.Scanner;

/**
 * Clase VistaCombate - Gestiona la interacción con el usuario para los combates
 */
public class VistaCombate {
    private Scanner sc = new Scanner(System.in);

    public void mostrarNoHayMonstruos() {
        System.out.println("No hay monstruos disponibles para el combate.");
    }

    public void mostrarNoHayMagos() {
        System.out.println("No hay magos disponibles para el combate.");
    }

    public void opcionesMago() {
        System.out.println("1. Usar dragón");
        System.out.println("2. Atacar monstruo");
    }

    public Mago seleccionarMago(List<Mago> magosDisponibles) {
        System.out.println("Selección de mago:");
        Mago magoSeleccionado = null;
        Integer opcion = -1;
        while (opcion < 1 || opcion > magosDisponibles.size()) {
            for (int i = 0; i < magosDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + magosDisponibles.get(i).getNombre());
            }
            System.out.print("Seleccione un número: ");
            opcion = leerEntero();
            if (opcion > 0 && opcion <= magosDisponibles.size()) {
                magoSeleccionado = magosDisponibles.get(opcion - 1);
                System.out.println("Mago seleccionado: " + magoSeleccionado.getNombre());
            } else {
                System.out.println("Ingrese una opción válida");
            }
        }
        return magoSeleccionado;
    }

    public Monstruo seleccionarMonstruo(List<Monstruo> monstruosDisponibles) {
        System.out.println("Selección de monstruo:");
        Monstruo monstruoSeleccionado = null;
        Integer opcion = -1;
        while (opcion < 1 || opcion > monstruosDisponibles.size()) {
            for (int i = 0; i < monstruosDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + monstruosDisponibles.get(i).getNombre());
            }
            System.out.print("Seleccione un número: ");
            opcion = leerEntero();
            if (opcion > 0 && opcion <= monstruosDisponibles.size()) {
                monstruoSeleccionado = monstruosDisponibles.get(opcion - 1);
                System.out.println("Monstruo seleccionado: " + monstruoSeleccionado.getNombre());
            } else {
                System.out.println("Ingrese una opción válida");
            }
        }
        return monstruoSeleccionado;
    }

    public void magoAtaca(String nombreMago, String nombreMonstruo, int nivelMagia) {
        System.out.println(nombreMago + " ataca a " + nombreMonstruo + " con magia de nivel " + nivelMagia);
    }

    public void monstruoAtaca(String nombreMonstruo, String nombreMago, int fuerza) {
        System.out.println(nombreMonstruo + " ataca a " + nombreMago + " con fuerza de " + fuerza);
    }

    public void mostrarGanadorMago(String nombreMago) {
        System.out.println("El mago " + nombreMago + " ha ganado el combate.");
    }

    public void mostrarGanadorMonstruos() {
        System.out.println("Los monstruos han ganado el combate.");
    }

    public void mostrarNoHayDragon() {
        System.out.println("No hay un dragón disponible para el combate.");
    }

    public int leerEntero() {
        Integer entero = null;
        while (entero == null) {
            try {
                entero = sc.nextInt();
                sc.nextLine();
                return entero;
            } catch (Exception e) {
                System.out.println("Introduce un entero: " + e.getMessage());
                sc.nextLine();
            }
        }
        return entero;
    }

    public String leerLinea() {
        return sc.nextLine();
    }
}