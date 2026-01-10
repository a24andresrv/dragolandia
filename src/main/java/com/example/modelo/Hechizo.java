package com.example.modelo;

import jakarta.persistence.*;

/**
 * Clase abstracta Hechizo - Define la estructura base para todos los hechizos
 * del juego.
 * Cada tipo de hechizo implementará su propio efecto.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Hechizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    protected String nombre;

    public abstract void aplicar(Object monstruo);

    public String getNombre() {
        return nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
