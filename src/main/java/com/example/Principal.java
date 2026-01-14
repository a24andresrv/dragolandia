package com.example;

import com.example.controlador.ControladorMenu;
import com.example.util.HibernateUtil;
/**
 * Clase Principal - Punto de entrada de la aplicación Dragolandia
 * Inicializa el controlador que gestiona toda la lógica del juego
 * 
 * @version 1.0
 * @author Andrés Rodríguez Vázquez
 */
public class Principal {
    
    /**
     * Método main - Inicia la aplicación
     * Crea una instancia del controlador y ejecuta el flujo principal del juego
     * 
     * @param args Argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        HibernateUtil.getEntityManager();
        // Crear instancia del controlador
        
        ControladorMenu controladorMenu = new ControladorMenu();
        controladorMenu.ejecutar();

        HibernateUtil.close();

    }
}