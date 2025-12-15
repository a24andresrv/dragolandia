package com.example.modelo;

import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Clase BolaDeFuego - Hechizo que afecta a múltiples monstruos
 * Reduce la vida de todos los monstruos en la lista según el efecto
 */
@Entity
@Table(name = "BolaDeFuego")
public class BolaDeFuego{

    private static int danho = 30; // Daño específico para Bola de Fuego
    

    /**
     * Aplica el efecto según el tipo de objetivo recibido
     * @param objetivo Puede ser un Monstruo o una List<Monstruo>
     */
    public void aplicarEfecto(Object objetivo) {
        try {
            if (objetivo instanceof List) {
                List<Monstruo> monstruos = (List<Monstruo>) objetivo;
                if (!monstruos.isEmpty()) {
                    for (Monstruo monstruo : monstruos) {
                        aplicarEfecto(monstruo);
                        monstruo.setVida(monstruo.getVida() - danho);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR: Objetivo no válido para Bola de Fuego(" + e.getMessage() + ")");
        }
    }
}
