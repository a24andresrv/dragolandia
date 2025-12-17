package com.example;

import com.example.controlador.ControladorMenu;
import com.example.controlador.ControladorCombate;

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

        
        ControladorMenu controladorMenu = new ControladorMenu();
        controladorMenu.ejecutar();

    

    }
}