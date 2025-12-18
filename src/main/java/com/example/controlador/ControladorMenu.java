package com.example.controlador;

import com.example.vista.VistaMenu;
import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.modelo.Bosque;
import com.example.modelo.Dragon;
import com.example.modelo.Hechizo;
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
    private List<Monstruo> monstruosDisponibles;
    private List<Mago> magosDisponibles;
    private List<Bosque> bosquesDisponibles;
    private Bosque bosque;
    private Dragon dragon;

    public ControladorMenu() {
        this.vista = new VistaMenu();
        this.gestorBosque = new GestorBosque();
        this.gestorMago = new GestorMago();
        this.gestorMonstruo = new GestorMonstruo();
        this.gestorDragon = new GestorDragon();
        this.monstruosDisponibles = new ArrayList<>();
        this.magosDisponibles = new ArrayList<>();
        this.bosquesDisponibles = new ArrayList<>();
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
                    dragon = vista.datosDragon(bosquesDisponibles);
                    gestorDragon.crearDragon(dragon);
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
                        Hechizo seleccionaHechizo = vista.seleccionarHechizo();
                        magoSeleccionado.addHechizo(seleccionaHechizo);
                    }
                    break;
                // Combate
                case 8:
                    ControladorCombate controladorCombate = new ControladorCombate();
                    controladorCombate.iniciarCombate(magosDisponibles, monstruosDisponibles, dragon);
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
}