package com.example.modelo;

import java.util.List;

/**
 * Clase BolaNieve - Hechizo que congela al oponente
 * Deja al monstruo congelado, reduciendo su vida a 0
 */
public class BolaNieve extends Hechizo {
    
    /**
     * Constructor de BolaNieve
     * El efecto es irrelevante ya que siempre deja la vida en 0
     */
    public BolaNieve() {
        super("Bola de Nieve", 0); // El efecto no importa, siempre congela
    }
    
    /**
     * Aplica el efecto según el tipo de objetivo recibido
     * Congela al monstruo dejándolo sin vida
     * @param objetivo Puede ser un Monstruo o una List<Monstruo> (solo afecta al primero)
     */
    @Override
    public void aplicarEfecto(Object objetivo) {
        if (objetivo instanceof Monstruo) {
            // Congela a un solo monstruo
            Monstruo monstruo = (Monstruo) objetivo;
            monstruo.setVida(0);
            System.out.println("¡" + monstruo.getNombre() + 
                             " ha sido congelado completamente! Vida: 0");
        } else if (objetivo instanceof List) {
            // Solo congela al primer monstruo de la lista
            @SuppressWarnings("unchecked")
            List<Monstruo> monstruos = (List<Monstruo>) objetivo;
            if (!monstruos.isEmpty()) {
                System.out.println("La Bola de Nieve se dirige al primer objetivo...");
                aplicarEfecto(monstruos.get(0));
            } else {
                System.out.println("La Bola de Nieve no tiene ningún objetivo");
            }
        } else {
            System.out.println("ERROR: Objetivo no válido para Bola de Nieve");
        }
    }
}
