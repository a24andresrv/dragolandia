package com.example.modelo;

/**
 * Clase abstracta Hechizo - Define la estructura base para todos los hechizos del juego
 * Cada tipo de hechizo implementará su propio efecto
 */
public abstract class Hechizo {
    
    protected String nombre;
    protected int efecto; // Daño o impacto del hechizo
    
    /**
     * Constructor de la clase Hechizo
     * @param nombre Nombre del hechizo
     * @param efecto Cantidad de daño o impacto que causa el hechizo
     */
    public Hechizo(String nombre, int efecto) {
        this.nombre = nombre;
        this.efecto = efecto;
    }
    
    /**
     * Método abstracto para aplicar el efecto del hechizo
     * Puede recibir un Monstruo o una Lista de Monstruos
     * Cada hechizo decide cómo manejar cada caso
     * @param objetivo Puede ser un Monstruo o una List<Monstruo>
     */
    public abstract void aplicarEfecto(Object objetivo);
    
    // Getters y Setters
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public int getEfecto() {
        return efecto;
    }
    
    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }
    
    @Override
    public String toString() {
        return nombre + " (Efecto: " + efecto + ")";
    }
}
