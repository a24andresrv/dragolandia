package com.example.modelo;

import jakarta.persistence.*;

/**
 * Clase BolaNieve - Hechizo que congela al oponente.
 * Deja al monstruo congelado, reduciendo su vida a 0.
 */
//@Entity
public class BolaNieve extends Hechizo {

    public BolaNieve() {
        this.nombre = "Bola de nieve";
    }

    @Override
    public void aplicar(Object objetivo) {
        if (objetivo instanceof Monstruo) {
            Monstruo monstruo = (Monstruo) objetivo;
            monstruo.setVida(0);
        }
    else {
            System.out.println("ERROR: Objetivo no válido para Bola de Fuego");
        }
    }
}