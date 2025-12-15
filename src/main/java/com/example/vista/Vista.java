package com.example.vista;

import java.util.List;
import java.util.Scanner;

import com.example.modelo.Bosque;
import com.example.modelo.Dragon;
import com.example.modelo.Hechizo;
import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.modelo.TipoMonstruo;

/**
 * Clase Vista - Gestiona la interacción con el usuario
 * Maneja la entrada y salida de datos según el patrón MVC
 */
public class Vista {
    private Scanner sc = new Scanner(System.in);

    public void mostrarMensje(String mensaje) {
        System.out.println(mensaje);
    }

    public void menu() {
        System.out.println("-----MENU-----");
        System.out.println("1. Crear Mago");
        System.out.println("2. Crear Monstruo");
        System.out.println("3. Crear Bosque");
        System.out.println("4. Mostrar Jefe del Bosque");
        System.out.println("5. Cambiar Jefe del Bosque");
        System.out.println("6. Combate");
        System.out.println("7. Añadir hechizo al mago");
        System.out.println("8. Crear Dragón");
        System.out.println("9. Aprender hechizo");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    public Integer leerEntero() {
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

    public Dragon datosDragon() {
        System.out.print("Ingrese el nombre del dragón: ");
        String nombre = leerLinea();
        System.out.print("Ingrese la intensidad de fuego del dragón: ");
        Integer intensidadFuego = leerEntero();
        System.out.print("Ingrese la resistencia del dragón: ");
        Integer resistencia = leerEntero();
        return new Dragon(nombre, intensidadFuego, resistencia);
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
    public void mostrarNoHayMonstruos() {
        System.out.println("No hay monstruos disponibles. Cree uno nuevo.");
    }

    public void mostrarNoHayBosque() {
        System.out.println("No hay bosque creado. Cree uno nuevo.");
    }

    public void mostrarSaliendoAplicacion() {
        System.out.println("Saliendo de la aplicación...");
    }

    public void mostrarOpcionNoValida() {
        System.out.println("Opción no válida. Intente de nuevo.");
    }

    public void mostrarNoHayMagos() {
        System.out.println("No hay magos disponibles. Cree uno nuevo.");
    }
    public void mostrarNoHayDragon() {
        System.out.println("El mago no tiene un dragón convocado. El mago pierde su turno.");
    }

    public void magoAtaca(String nombreMago, String nombreMonstruo, int dano) {
        System.out.println("El mago " + nombreMago + " ataca al monstruo " + nombreMonstruo + " causando " + dano + " puntos de daño.");
    }

    public void monstruoAtaca(String nombreMonstruo, String nombreMago, int dano) {
        System.out.println("El monstruo " + nombreMonstruo + " ataca al mago " + nombreMago + " causando " + dano + " puntos de daño.");
    }

    public void mostrarGanadorMago(String nombreMago) {
        System.out.println("El mago " + nombreMago + " ha ganado el combate.");
    }

    public void mostrarGanadorMonstruo(String nombreMonstruo) {
        System.out.println("El monstruo " + nombreMonstruo + " ha ganado el combate.");
    }

    public void opcionesMago() {
        System.out.println("------OPCIONES DEL MAGO------");
        System.out.println("1. Exhalar fuego (dragón)");
        System.out.println("2. Lanzar hechizo");
    }
    public Hechizo seleccionarHechizos(List<Hechizo> hechizosDisponibles) {
        System.out.println("Selección de hechizo:");
        Hechizo hechizoSeleccionado = null;
        Integer opcion = -1;
        while (opcion < 1 || opcion > hechizosDisponibles.size()) {
            for (int i = 0; i < hechizosDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + hechizosDisponibles.get(i).getNombre());
            }
            System.out.print("Seleccione un número: ");
            opcion = leerEntero();
            if (opcion > 0 && opcion <= hechizosDisponibles.size()) {
                hechizoSeleccionado = hechizosDisponibles.get(opcion - 1);
                System.out.println("Hechizo seleccionado: " + hechizoSeleccionado.getNombre());
            } else {
                System.out.println("Ingrese una opción válida");
            }
        }
        return hechizoSeleccionado;
    }

    public Hechizo mostrarHechizosAprender(List<Hechizo> hechizosDisponibles) {
        System.out.println("Selección de hechizo para aprender:");
        Hechizo hechizoSeleccionado = null;
        Integer opcion = -1;
        while (opcion < 1 || opcion > hechizosDisponibles.size()) {
            for (int i = 0; i < hechizosDisponibles.size(); i++) {
                System.out.println((i + 1) + ". " + hechizosDisponibles.get(i).getNombre() + " - Daño: " + hechizosDisponibles.get(i).getDanio());
            }
            System.out.print("Seleccione un número: ");
            opcion = leerEntero();
            if (opcion > 0 && opcion <= hechizosDisponibles.size()) {
                hechizoSeleccionado = hechizosDisponibles.get(opcion - 1);
                System.out.println("Hechizo seleccionado: " + hechizoSeleccionado.getNombre());
            } else {
                System.out.println("Ingrese una opción válida.");
            }
        }
        return hechizoSeleccionado;
    }
}