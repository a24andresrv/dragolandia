package com.example.modelo;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Clase Rayo - Hechizo que afecta a un único monstruo
 * Reduce la vida del monstruo objetivo según el efecto
 */
@Entity
@DiscriminatorValue("RAYO")
public class Rayo extends Hechizo {

    /**
     * Representa el daño específico causado por un rayo.
     */
    private static final int DANIO_ESPECIFICO = 50; // Daño específico para Rayo

    /**
     * Constructor de Rayo 
     * @param efecto Cantidad de vida a reducir del monstruo afectado
     */
    public Rayo() {
        super("Rayo", DANIO_ESPECIFICO);
    }

    /**
     * Aplica el efecto según el tipo de objetivo recibido
     * Rayo SOLO puede afectar a un monstruo individual
     * 
     * @param objetivo Debe ser un Monstruo (no acepta listas)
     */
    @Override
    public void aplicarEfecto(Object objetivo) {
        try {
            if (objetivo instanceof Monstruo) {
                // Afecta a un solo monstruo
                Monstruo monstruo = (Monstruo) objetivo;
                int vidaActual = monstruo.getVida();
                monstruo.setVida(vidaActual - this.danio);
            }
        } catch (Exception e) {
            System.out.println("ERROR: Objetivo no válido para Rayo(" + e.getMessage() + ")");
        }

    }
}