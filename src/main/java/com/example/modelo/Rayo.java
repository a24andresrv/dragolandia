package com.example.modelo;

import jakarta.persistence.*;

/**
 * Clase Rayo - Hechizo que inflige daño directo a un objetivo.
 */

//@Entity
public class Rayo extends Hechizo {

    public Rayo() {
        this.nombre = "Rayo";
    }

    @Override
    public void aplicar(Object objetivo) {
        if (objetivo instanceof Monstruo) {
            Monstruo monstruo = (Monstruo) objetivo;
            monstruo.setVida(monstruo.getVida() - 3);
        }else {
            System.out.println("ERROR: Objetivo no válido para Bola de Fuego");
        }
    }
}
