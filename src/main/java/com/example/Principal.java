package com.example;

import com.example.controlador.Controlador;

/**
 * Clase Principal - Punto de entrada de la aplicación Dragolandia
 * Inicializa el controlador que gestiona toda la lógica del juego
 * 
 * @author Cristina Puga Fernández
 * @version 1.0
 */
public class Principal {
    
    /**
     * Método main - Inicia la aplicación
     * Crea una instancia del controlador y ejecuta el flujo principal del juego
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        // Crear instancia del controlador
        Controlador controlador = new Controlador();
        
        // Ejecutar la aplicación
        controlador.ejecutar();
    }
}