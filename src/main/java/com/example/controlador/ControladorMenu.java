package com.example.controlador;

import com.example.vista.VistaMenu;
import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.modelo.Bosque;
import com.example.modelo.Dragon;
import com.example.modelo.Hechizo;
import com.example.modelo.BolaFuego;
import com.example.modelo.Rayo;
import com.example.modelo.BolaNieve;
import com.example.gestores.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase ControladorMenu - Gestiona las opciones del menú principal
 */
public class ControladorMenu {

    private VistaMenu vista;
    private GestorBosque gestorBosque;
    private GestorMago gestorMago;
    private GestorMonstruo gestorMonstruo;
    private GestorDragon gestorDragon;
    private GestorHechizo gestorHechizo;
    private List<Monstruo> monstruosDisponibles;
    private List<Mago> magosDisponibles;
    private List<Bosque> bosquesDisponibles;
    private List<Hechizo> hechizosDisponibles;
    private Bosque bosque;
    private Dragon dragon;

    public ControladorMenu() {
        this.vista = new VistaMenu();
        this.gestorBosque = new GestorBosque();
        this.gestorMago = new GestorMago();
        this.gestorMonstruo = new GestorMonstruo();
        this.gestorDragon = new GestorDragon();
        this.gestorHechizo = new GestorHechizo();
        this.monstruosDisponibles = new ArrayList<>();
        this.magosDisponibles = new ArrayList<>();
        this.bosquesDisponibles = new ArrayList<>();
        this.hechizosDisponibles = new ArrayList<>();

        // Inicializar hechizos disponibles
        inicializarHechizos();
    }

    /**
     * Inicializa la lista de hechizos disponibles en el juego
     * Solo crea instancias en memoria, NO persiste en BD hasta que se añaden a un
     * mago
     */
    private void inicializarHechizos() {
        // Crear hechizos solo en memoria (no en BD)
        this.hechizosDisponibles.add(new BolaFuego());
        this.hechizosDisponibles.add(new Rayo());
        this.hechizosDisponibles.add(new BolaNieve());
    }

    public void ejecutar() {
        Integer opcion;
        do {
            vista.menu();
            opcion = vista.leerEntero();
            switch (opcion) {
                // Crear mago
                case 1:
                    Mago mago = vista.datosMago();
                    gestorMago.crearMago(mago);
                    magosDisponibles.add(mago);
                    break;
                // Crear monstruo
                case 2:
                    Monstruo monstruo = vista.datosMonstruo();
                    gestorMonstruo.crearMonstruo(monstruo);
                    monstruosDisponibles.add(monstruo);
                    break;
                // Crear bosque
                case 3:
                    if (monstruosDisponibles.isEmpty()) {
                        vista.mostrarNoHayMonstruos();
                        monstruo = vista.datosMonstruo();
                        gestorMonstruo.crearMonstruo(monstruo);
                        monstruosDisponibles.add(monstruo);
                    }
                    bosque = vista.datosBosque(monstruosDisponibles);
                    gestorBosque.crearBosque(bosque);
                    bosquesDisponibles.add(bosque);
                    break;
                // Crear dragón
                case 4:
                    if (bosquesDisponibles.isEmpty()) {
                        vista.mostrarNoHayBosque();
                    } else {
                        dragon = vista.datosDragon(bosquesDisponibles);
                        gestorDragon.crearDragon(dragon);
                    }
                    break;
                // Mostrar jefe del bosque
                case 5:
                    if (bosque == null) {
                        vista.mostrarNoHayBosque();
                    } else {
                        bosque.mostrarJefe();
                    }
                    break;
                // Cambiar jefe del bosque
                case 6:
                    Monstruo nuevoJefe = vista.seleccionarMonstruo(monstruosDisponibles);
                    bosque.cambiarJefe(nuevoJefe);
                    break;
                // Añadir hechizo al mago
                case 7:
                    if (magosDisponibles.isEmpty()) {
                        vista.mostrarNoHayMagos();
                    } else {
                        Mago magoSeleccionado = vista.seleccionarMago(magosDisponibles);
                        // No está implemnatdo el modelo vista contorlador en este método, no se si con
                        // un metodo mostrar mensaje se arreglaria.
                        System.out.println("Añadir 2 hechizos al mago");

                        // Añadir primer hechizo
                        System.out.println("Seleccione el PRIMER hechizo:");
                        Hechizo hechizoModelo1 = vista.seleccionarHechizoDisponible(hechizosDisponibles);
                        Hechizo hechizoNuevo1 = crearNuevaInstanciaHechizo(hechizoModelo1);
                        magoSeleccionado.addHechizo(hechizoNuevo1);
                        System.out.println("Primer hechizo '" + hechizoNuevo1.getNombre() + "' añadido.");

                        // Añadir segundo hechizo
                        System.out.println("Seleccione el SEGUNDO hechizo:");
                        Hechizo hechizoModelo2 = vista.seleccionarHechizoDisponible(hechizosDisponibles);
                        Hechizo hechizoNuevo2 = crearNuevaInstanciaHechizo(hechizoModelo2);
                        magoSeleccionado.addHechizo(hechizoNuevo2);
                        System.out.println("Segundo hechizo '" + hechizoNuevo2.getNombre() + "' añadido.");

                        // Persistir el mago (cascade guardará automáticamente ambos hechizos)
                        gestorMago.modificarMago(magoSeleccionado);
                        System.out.println("Ambos hechizos guardados en BD correctamente.");
                    }
                    break;
                // Combate
                case 8:
                    if (magosDisponibles.size() < 2) {
                        vista.mostrarNoHayMagosSuficientes();
                    }
                    if (monstruosDisponibles.size() < 3) {
                        vista.mostrarNoHayMonstruosSuficientes();
                    }
                    if(dragon == null) {
                        vista.mostrarNoHayDragon();
                    }
                    ControladorCombate controladorCombate = new ControladorCombate();
                    controladorCombate.iniciarCombate(magosDisponibles, monstruosDisponibles, dragon,hechizosDisponibles);
                    break;
                case 0:
                    vista.mostrarSaliendoAplicacion();
                    break;
                default:
                    vista.mostrarOpcionNoValida();
            }
        } while (opcion != 0);
    }

    public List<Mago> getMagosDisponibles() {
        return magosDisponibles;
    }

    public List<Monstruo> getMonstruosDisponibles() {
        return monstruosDisponibles;
    }

    public Dragon getDragon() {
        return dragon;
    }

    /**
     * Crea una nueva instancia de un hechizo basándose en el tipo del hechizo
     * modelo
     * 
     * @param hechizoModelo El hechizo modelo del que crear una nueva instancia
     * @return Una nueva instancia del mismo tipo de hechizo
     */
    private Hechizo crearNuevaInstanciaHechizo(Hechizo hechizoModelo) {
        if (hechizoModelo instanceof BolaFuego) {
            return new BolaFuego();
        } else if (hechizoModelo instanceof Rayo) {
            return new Rayo();
        } else if (hechizoModelo instanceof BolaNieve) {
            return new BolaNieve();
        }
        // Por defecto, retornar un Rayo
        return new Rayo();
    }
}