package com.example.modelo;

import java.util.List;
import jakarta.persistence.*;

/**
 * Clase BolaDeFuego - Hechizo que afecta a múltiples monstruos.
 * Reduce la vida de todos los monstruos en la lista según el efecto.
 */
@Entity
public class BolaFuego extends Hechizo {

    public BolaFuego() {
        this.nombre = "Bola de fuego";
    }

    @Override
    public void aplicar(Object objetivo) {
        if (objetivo instanceof List) {
            List<Monstruo> monstruos = (List<Monstruo>) objetivo;
            for (Monstruo monstruo : monstruos) {
               monstruo.setVida(monstruo.getVida() - 5);
            }
        } else {
            System.out.println("ERROR: Objetivo no válido para Bola de Fuego");
        }
        
    }
}
