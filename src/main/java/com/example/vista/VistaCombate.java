package com.example.vista;

import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.modelo.Bosque;

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

    public void mostrarNoHayDragon() {
        System.out.println("No hay un dragón disponible para el combate.");
    }

    public void mostrarInicioCombate() {
        System.out.println("\nComienza el combate\n");
    }

    public void mostrarFinCombate() {
        System.out.println("\nFin del combate\n");
    }

    public void mostrarInicioRonda(int numeroRonda) {
        System.out.println("\n--- Ronda " + numeroRonda + " ---");
    }

    public void mostrarFaseMagos() {
        System.out.println("\nFase de Magos:");
    }

    public void mostrarFaseMonstruos() {
        System.out.println("\nFase de Monstruos:");
    }

    public void mostrarFaseDragon() {
        System.out.println("\nFase del Dragón:");
    }

    public void mostrarTurnoMago(String nombreMago) {
        System.out.println("Turno de " + nombreMago);
    }

    public void mostrarLanzamientoHechizo(String nombreMago, String nombreHechizo) {
        System.out.println(nombreMago + " lanza " + nombreHechizo);
    }

    public void mostrarPenalizacionHechizo(String nombreMago, String nombreHechizo) {
        System.out.println(nombreMago + " no conoce " + nombreHechizo + " y pierde 1 punto de vida");
    }

    public void magoAtaca(String nombreMago, String nombreMonstruo, int nivelMagia) {
        System.out.println(nombreMago + " ataca a " + nombreMonstruo);
    }

    public void monstruoAtaca(String nombreMonstruo, String nombreMago, int fuerza) {
        System.out.println(nombreMonstruo + " ataca a " + nombreMago + " causando " + fuerza + " de daño");
    }

    public void mostrarAtaqueDragon(String nombreDragon, String nombreMonstruo, int intensidad) {
        System.out.println(nombreDragon + " ataca a " + nombreMonstruo);
    }

    public void mostrarMagoMuerto(String nombreMago) {
        System.out.println(nombreMago + " ha sido derrotado");
    }

    public void mostrarMonstruoMuerto(String nombreMonstruo) {
        System.out.println(nombreMonstruo + " ha sido eliminado");
    }

    public void mostrarNuevoJefe(String nombreNuevoJefe) {
        System.out.println("Nuevo jefe del bosque: " + nombreNuevoJefe);
    }

    public void mostrarReporte(List<Mago> magosVivos, List<Mago> magosMuertos,
                               List<Monstruo> monstruosVivos, List<Monstruo> monstruosMuertos,
                               Bosque bosque) {
        System.out.println("\nEstado del combate:");
        
        System.out.println("Magos vivos:");
        if (magosVivos.isEmpty()) {
            System.out.println("  Ninguno");
        } else {
            for (Mago mago : magosVivos) {
                System.out.println("  " + mago.getNombre() + " (Vida: " + mago.getVida() + ")");
            }
        }
        
        System.out.println("Magos muertos:");
        if (magosMuertos.isEmpty()) {
            System.out.println("  Ninguno");
        } else {
            for (Mago mago : magosMuertos) {
                System.out.println("  " + mago.getNombre());
            }
        }
        
        System.out.println("Monstruos vivos:");
        if (monstruosVivos.isEmpty()) {
            System.out.println("  Ninguno");
        } else {
            for (Monstruo monstruo : monstruosVivos) {
                System.out.println("  " + monstruo.getNombre() + " (Vida: " + monstruo.getVida() + ")");
            }
        }
        
        System.out.println("Monstruos muertos:");
        if (monstruosMuertos.isEmpty()) {
            System.out.println("  Ninguno");
        } else {
            for (Monstruo monstruo : monstruosMuertos) {
                System.out.println("  " + monstruo.getNombre());
            }
        }
        
        System.out.println("Jefe del bosque:");
        if (bosque != null && bosque.getMonstruoJefe() != null) {
            Monstruo jefe = bosque.getMonstruoJefe();
            System.out.println("  " + jefe.getNombre() + " (Vida: " + jefe.getVida() + ")");
        } else {
            System.out.println("  Sin jefe");
        }
        System.out.println();
    }

    public void mostrarVictoriaMagos() {
        System.out.println("\nLos magos han derrotado a todos los monstruos.");
    }

    public void mostrarGanadorMonstruos() {
        System.out.println("\nLos monstruos han vencido a todos los magos.");
    }

    public void mostrarGanadorMago(String nombreMago) {
        System.out.println("\n" + nombreMago + " ha ganado el combate.");
    }

    public void mostrarEmpate() {
        System.out.println("\nEmpate - Todos han caído en batalla.");
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
