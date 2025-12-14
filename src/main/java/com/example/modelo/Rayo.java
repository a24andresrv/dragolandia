package com.example.modelo;

import java.util.List;

/**
 * Clase Rayo - Hechizo que afecta a un único monstruo
 * Reduce la vida del monstruo objetivo según el efecto
 */
public class Rayo extends Hechizo {
    
    /**
     * Constructor de Rayo
     * @param efecto Cantidad de vida a reducir del monstruo afectado
     */
    public Rayo(int efecto) {
        super("Rayo", efecto);
    }
    
    /**
     * Aplica el efecto según el tipo de objetivo recibido
     * Rayo SOLO puede afectar a un monstruo individual
     * @param objetivo Debe ser un Monstruo (no acepta listas)
     */
    @Override
    public void aplicarEfecto(Object objetivo) {
        if (objetivo instanceof Monstruo) {
            // Afecta a un solo monstruo
            Monstruo monstruo = (Monstruo) objetivo;
            int vidaActual = monstruo.getVida();
            monstruo.setVida(vidaActual - this.efecto);
            System.out.println("¡Rayo impacta a " + monstruo.getNombre() + 
                             "! Daño: " + this.efecto + " puntos");
        } else if (objetivo instanceof List) {
            // Rayo NO puede afectar a múltiples objetivos
            System.out.println("ERROR: Rayo solo puede afectar a un único monstruo, no a múltiples objetivos.");
        } else {
            System.out.println("ERROR: Objetivo no válido para Rayo");
        }
    }
}
