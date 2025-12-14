package com.example.controlador;

import com.example.vista.Vista;
import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.modelo.Bosque;

import java.util.ArrayList;
import java.util.List;

import com.example.gestores.*;

/**
 * Clase Controlador - Gestiona la lógica de la aplicación
 * Coordina las interacciones entre la Vista y el Modelo según el patrón MVC
 */
public class Controlador {

    // Atributos
    private Vista vista;
    private GestorBosque gestorBosque;
    private GestorMago gestorMago;
    private GestorMonstruo gestorMonstruo;
    private List<Monstruo> monstruosDisponibles;
    private List<Mago> magosDisponibles;
    Bosque bosque = null;
    Mago mago = null;
    Monstruo monstruo = null;

    /**
     * Constructor - Inicializa la vista y los gestores
     */
    public Controlador() {
        this.vista = new Vista();
        this.gestorBosque = new GestorBosque();
        this.gestorMago = new GestorMago();
        this.gestorMonstruo = new GestorMonstruo();
        this.monstruosDisponibles = new ArrayList<Monstruo>();
        this.magosDisponibles = new ArrayList<Mago>();
    }

    public void ejecutar() {
        Integer opcion;
        do {
            vista.menu();
            opcion = vista.leerEntero();
            switch (opcion) {

                case 1:
                    mago = vista.datosMago();
                    gestorMago.crearMago(mago);
                    magosDisponibles.add(mago);
                    break;
                case 2:
                    monstruo = vista.datosMonstruo();
                    gestorMonstruo.crearMonstruo(monstruo);
                    monstruosDisponibles.add(monstruo);
                    break;
                case 3:
                    if (monstruosDisponibles.isEmpty()) {
                        vista.mostrarNoHayMonstruos();
                        Monstruo monstruo = vista.datosMonstruo();
                        gestorMonstruo.crearMonstruo(monstruo);
                        monstruosDisponibles.add(monstruo);
                    }
                    bosque = vista.datosBosque(monstruosDisponibles);
                    gestorBosque.crearBosque(bosque);
                    break;
                case 4:
                    if (monstruosDisponibles.isEmpty()) {
                        vista.mostrarNoHayMonstruos();
                    } else if (bosque == null) {
                        vista.mostrarNoHayBosque();
                    } else {
                        bosque.mostrarJefe();
                    }

                    break;
                case 5:
                    Monstruo nuevoJefe = vista.seleccionarMonstruo(monstruosDisponibles);
                    bosque.cambiarJefe(nuevoJefe);
                    break;
                case 6:
                    combate(magosDisponibles, monstruosDisponibles);
                    break;
                case 7:
                    Dragon dragon = vista.datosDragon();
                    bosque.addDragon(dragon);
                    break;
                case 8:
                    Hechizo hechizo = vista.datosHechizo();
                    mago.addHechizo(hechizo);
                    break;
                case 9:
                    Mago magoSeleccionado = vista.seleccionarMago(magosDisponibles);
                    Monstruo monstruoSeleccionado = vista.seleccionarMonstruo(monstruosDisponibles);
                    magoSeleccionado.lanzarHechizo(monstruoSeleccionado);
                    vista.mostrarHechizoLanzado(magoSeleccionado.getNombre(), monstruoSeleccionado.getNombre());
                    break;
                case 10:
                    Dragon dragonSeleccionado = vista.seleccionarDragon(bosque.getDragones());
                    Monstruo monstruoAExhalar = vista.seleccionarMonstruo(monstruosDisponibles);
                    dragonSeleccionado.exhalar(monstruoAExhalar);
                    vista.mostrarExhalacion(dragonSeleccionado.getNombre(), monstruoAExhalar.getNombre());
                    break;
                case 0:
                    vista.mostrarSaliendoAplicacion();
                    break;
                default:
                    vista.mostrarOpcionNoValida();
            }
        } while (opcion != 0);

    }

    public void combate(List<Mago> magos, List<Monstruo> monstruos) {
        if (monstruos.isEmpty()) {
            vista.mostrarNoHayMonstruos();
            return;
        }
        if (magos.isEmpty()) {
            vista.mostrarNoHayMagos();
            return;
        }
        Mago mago = vista.seleccionarMago(magos);
        Monstruo monstruo = vista.seleccionarMonstruo(monstruos);
        while (mago.getVida() > 0 && monstruo.getVida() > 0) {
            monstruo.atacar(mago);
            vista.monstruoAtaca(monstruo.getNombre(), mago.getNombre(), monstruo.getFuerza());
            if (mago.getVida() > 0) {
                mago.lanzarHechizo(monstruo);
                vista.magoAtaca(mago.getNombre(), monstruo.getNombre(), mago.getNivelMagia());
            }
        }
        if(mago.getVida() > 0) {
            vista.mostrarGanadorMago(mago.getNombre());
        } else {
            vista.mostrarGanadorMonstruo(monstruo.getNombre());
        }
    }
}
