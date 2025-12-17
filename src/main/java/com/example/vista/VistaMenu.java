package com.example.vista;

import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.modelo.TipoMonstruo;
import com.example.modelo.Bosque;
import com.example.modelo.Dragon;

import java.util.List;
import java.util.Scanner;

/**
 * Clase VistaMenu - Gestiona la interacción con el usuario para el menú
 * principal
 */
public class VistaMenu {
    private Scanner sc = new Scanner(System.in);

    public void menu() {
        System.out.println("1. Crear Mago");
        System.out.println("2. Crear Monstruo");
        System.out.println("3. Crear Bosque");
        System.out.println("4. Crear Dragón");
        System.out.println("5. Mostrar Jefe del Bosque");
        System.out.println("6. Cambiar Jefe del Bosque");
        System.out.println("7. Añadir hechizo al mago (no implementado)");
        System.out.println("8. Combate");
        System.out.println("0. Salir");
    }

    public void mostrarNoHayMonstruos() {
        System.out.println("No hay monstruos disponibles.");
    }

    public void mostrarNoHayBosque() {
        System.out.println("No hay un bosque creado.");
    }

    public void mostrarSaliendoAplicacion() {
        System.out.println("Saliendo de la aplicación...");
    }

    public void mostrarOpcionNoValida() {
        System.out.println("Opción no válida. Por favor, intente de nuevo.");
    }

    public Mago datosMago() {
        System.out.print("Ingrese el nombre del mago: ");
        String nombre = leerLinea();
        System.out.print("Ingrese la vida del mago: ");
        Integer vida = leerEntero();
        System.out.print("Ingrese el nivel de magia del mago: ");
        Integer nivelMagia = leerEntero();

        return new Mago(nombre, vida, nivelMagia);
    }

    public Monstruo datosMonstruo() {
        System.out.print("Ingrese el nombre del monstruo: ");
        String nombre = leerLinea();
        System.out.print("Ingrese la vida del monstruo: ");
        Integer vida = leerEntero();
        System.out.println("Seleccione el tipo de monstruo:");
        TipoMonstruo[] tipos = TipoMonstruo.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.println((i + 1) + ". " + tipos[i]);
        }
        int opcionTipo = -1;
        while (opcionTipo < 1 || opcionTipo > tipos.length) {
            System.out.print("Ingrese el número correspondiente al tipo: ");
            opcionTipo = leerEntero();
            if (opcionTipo < 1 || opcionTipo > tipos.length) {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        }
        TipoMonstruo tipo = tipos[opcionTipo - 1];
        System.out.print("Ingrese la fuerza del monstruo: ");
        Integer fuerza = leerEntero();
        return new Monstruo(nombre, vida, tipo, fuerza);
    }

    public Bosque datosBosque(List<Monstruo> monstruosDisponibles) {
        System.out.print("Ingrese el nombre del bosque: ");
        String nombre = leerLinea();
        System.out.print("Ingrese el nivel de peligro: ");
        Integer nivelPeligro = leerEntero();
        System.out.println("Seleccione el monstruo jefe del bosque:");
        Monstruo jefeSeleccionado = seleccionarMonstruo(monstruosDisponibles);
        return new Bosque(nombre, nivelPeligro, jefeSeleccionado);
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

    public Bosque seleccionarBosque(List<Bosque> bosquesDisponibles) {
        System.out.println("Selección de bosque:");
        Bosque bosqueSeleccionado = null;
        Integer opcion = -1;
        while (opcion < 1 || opcion > bosquesDisponibles.size()) {
            for (int i = 0; i < bosquesDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + bosquesDisponibles.get(i).getNombre());
            }
            System.out.print("Seleccione un número: ");
            opcion = leerEntero();
            if (opcion > 0 && opcion <= bosquesDisponibles.size()) {
                bosqueSeleccionado = bosquesDisponibles.get(opcion - 1);
                System.out.println("Bosque seleccionado: " + bosqueSeleccionado.getNombre());
            } else {
                System.out.println("Ingrese una opción válida");
            }
        }
        return bosqueSeleccionado;
    }

    public Dragon datosDragon(List<Bosque> bosquesDisponibles) {
        System.out.print("Ingrese el nombre del dragón: ");
        String nombre = leerLinea();
        System.out.print("Ingrese la intensidad de fuego del dragón: ");
        Integer intensidadFuego = leerEntero();
        System.out.print("Ingrese la resistencia del dragón: ");
        Integer resistencia = leerEntero();
        Bosque bosque = seleccionarBosque(bosquesDisponibles);
        return new Dragon(nombre, intensidadFuego, resistencia);
    }

    /*
     * public Hechizo mostrarHechizosAprender(List<Hechizo> hechizosDisponibles) {
     * System.out.println("Selección de hechizo para aprender:");
     * Hechizo hechizoSeleccionado = null;
     * Integer opcion = -1;
     * while (opcion < 1 || opcion > hechizosDisponibles.size()) {
     * for (int i = 0; i < hechizosDisponibles.size(); i++) {
     * System.out.println((i + 1) + ". " + hechizosDisponibles.get(i).getNombre() +
     * " - Daño: " + hechizosDisponibles.get(i).getDanio());
     * }
     * System.out.print("Seleccione un número: ");
     * opcion = leerEntero();
     * if (opcion > 0 && opcion <= hechizosDisponibles.size()) {
     * hechizoSeleccionado = hechizosDisponibles.get(opcion - 1);
     * System.out.println("Hechizo seleccionado: " +
     * hechizoSeleccionado.getNombre());
     * } else {
     * System.out.println("Ingrese una opción válida.");
     * }
     * }
     * return hechizoSeleccionado;
     * }
     */

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