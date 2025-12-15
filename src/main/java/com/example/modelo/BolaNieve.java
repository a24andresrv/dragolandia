package com.example.modelo;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Clase BolaNieve - Hechizo que congela al oponente
 * Deja al monstruo congelado, reduciendo su vida a 0
 */
@Entity
@Table(name = "BolaNieve")
public class BolaNieve {

    /**
     * Constructor de BolaNieve
     * El daño es irrelevante ya que siempre deja la vida en 0
     */
    private static int danho = 30; // Daño específico para Bola de Fuego

    /**
     * Aplica el efecto según el tipo de objetivo recibido
     * Congela al monstruo dejándolo sin vida
     * 
     * @param objetivo Puede ser un Monstruo o una List<Monstruo> (solo afecta a
     *                 primero)
     */
    public void aplicarEfecto(Object objetivo) {
        try {
            if (objetivo instanceof Monstruo) {
                // Congela a un solo monstruo
                Monstruo monstruo = (Monstruo) objetivo;
                monstruo.setVida(0);
            }
        } catch (Exception e) {
            System.out.println("ERROR: Objetivo no válido para Bola de Nieve(" + e.getMessage() + ")");
        }
    }
}
