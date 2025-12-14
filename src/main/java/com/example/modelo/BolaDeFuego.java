package com.example.modelo;

import java.util.List;

/**
 * Clase BolaDeFuego - Hechizo que afecta a múltiples monstruos
 * Reduce la vida de todos los monstruos en la lista según el efecto
 */
public class BolaDeFuego extends Hechizo {
    
    /**
     * Constructor de BolaDeFuego
     * @param efecto Cantidad de vida a reducir de cada monstruo afectado
     */
    public BolaDeFuego(int efecto) {
        super("Bola de Fuego", efecto);
    }
    
    /**
     * Aplica el efecto según el tipo de objetivo recibido
     * @param objetivo Puede ser un Monstruo o una List<Monstruo>
     */
    @Override
    public void aplicarEfecto(Object objetivo) {
        if (objetivo instanceof Monstruo) {
            // Afecta a un solo monstruo
            Monstruo monstruo = (Monstruo) objetivo;
            int vidaActual = monstruo.getVida();
            monstruo.setVida(vidaActual - this.efecto);
            System.out.println("¡Bola de Fuego impacta a " + monstruo.getNombre() + 
                             "! Daño: " + this.efecto + " puntos");
        } else if (objetivo instanceof List) {
            // Afecta a múltiples monstruos
            @SuppressWarnings("unchecked")
            List<Monstruo> monstruos = (List<Monstruo>) objetivo;
            if (!monstruos.isEmpty()) {
                System.out.println("¡Bola de Fuego explota afectando a " + monstruos.size() + " monstruos!");
                for (Monstruo monstruo : monstruos) {
                    aplicarEfecto(monstruo);
                }
            } else {
                System.out.println("La Bola de Fuego no impactó a ningún objetivo");
            }
        } else {
            System.out.println("ERROR: Objetivo no válido para Bola de Fuego");
        }
    }
}
