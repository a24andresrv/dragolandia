package com.example.controlador;

import com.example.vista.VistaCombate;
import com.example.modelo.Mago;
import com.example.modelo.Monstruo;
import com.example.modelo.Dragon;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase ControladorCombate - Gestiona la lógica del combate
 */
public class ControladorCombate {

    private VistaCombate vista;

    public ControladorCombate() {
        this.vista = new VistaCombate();
    }

    public void iniciarCombate(List<Mago> magos, List<Monstruo> monstruos, Dragon dragon) {
        if (monstruos.isEmpty() || magos.isEmpty()) {
            if (monstruos.isEmpty()) {
                vista.mostrarNoHayMonstruos();
            }
            if (magos.isEmpty()) {
                vista.mostrarNoHayMagos();
            }
            return;
        }
        Mago mago = vista.seleccionarMago(magos);
        ArrayList<Monstruo> monstruosVivos = new ArrayList<>();
        Integer nMonstruos = (int) (Math.random() * monstruos.size()) + 1;
        for (int i = 0; i < nMonstruos; i++) {
            Integer indiceAleatorio = (int) (Math.random() * monstruos.size());
            monstruosVivos.add(monstruos.get(indiceAleatorio));
        }
        while (mago.getVida() > 0 && !monstruosVivos.isEmpty()) {
            vista.opcionesMago();
            switch (vista.leerEntero()) {
                case 1:
                    if (dragon == null) {
                        vista.mostrarNoHayDragon();
                    } else {
                        Monstruo monstruo = vista.seleccionarMonstruo(monstruosVivos);
                        dragon.exhalar(monstruo);
                    }
                    break;
                case 2:
                    Monstruo monstruo = vista.seleccionarMonstruo(monstruosVivos);
                    // Hechizo hechizoSeleccionado = vista.seleccionarHechizos(hechizosDisponibles);
                    // mago.lanzarHechizo(monstruo, hechizoSeleccionado);
                    vista.magoAtaca(mago.getNombre(), monstruo.getNombre(), mago.getNivelMagia());
                    break;
                default:
                    break;
            }

            if (mago.getVida() > 0 && !monstruosVivos.isEmpty()) {
                Monstruo monstruo = monstruosVivos.get(0); // Selecciona el primer monstruo vivo
                mago.lanzarHechizo(monstruo);
                vista.monstruoAtaca(monstruo.getNombre(), mago.getNombre(), monstruo.getFuerza());
            }

        }
        if (mago.getVida() > 0) {
            vista.mostrarGanadorMago(mago.getNombre());
        } else if (!monstruosVivos.isEmpty()) {
            vista.mostrarGanadorMonstruos();
        }
    }
}