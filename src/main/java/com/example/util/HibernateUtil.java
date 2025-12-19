package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Clase HibernateUtil - Implementa un SessionFactory como Singleton
 * para gestionar sesiones de Hibernate en toda la aplicación.
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;

    /**
     * Método para obtener la instancia única de SessionFactory.
     *
     * @return La instancia de SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                // Crear la configuración y construir el SessionFactory
                sessionFactory = new Configuration().configure().buildSessionFactory();
            } catch (Throwable ex) {
                // Manejo de errores en la inicialización
                System.err.println("Error al crear el SessionFactory: " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }

    /**
     * Método para cerrar el SessionFactory al finalizar la aplicación.
     */
    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}