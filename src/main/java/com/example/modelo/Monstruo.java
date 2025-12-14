package com.example.modelo;

import jakarta.persistence.*;

/**
 * Clase Monstruo - Representa un monstruo en el juego
 * Puede atacar a magos y tiene diferentes tipos y niveles de fuerza
 */
@Entity
@Table(name = "Monstruos")
public class Monstruo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Integer vida;
    @Enumerated(EnumType.STRING)
    private TipoMonstruo tipo;
    private Integer fuerza;


    public Monstruo() {
    }

    public Monstruo(String nombre, Integer vida, TipoMonstruo tipo, Integer fuerza) {
        this.nombre = nombre;
        this.vida = vida;
        this.tipo = tipo;
        this.fuerza = fuerza;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        if (vida < 0) {
            this.vida = 0;
        } else {
            this.vida = vida;
        }
    }

    public Integer getFuerza() {
        return fuerza;
    }

    public void setFuerza(Integer fuerza) {
        if (fuerza < 0) {
            this.fuerza = 0;
        } else {
            this.fuerza = fuerza;
        }
    }

    public TipoMonstruo getTipo() {
        return tipo;
    }

    public void setTipo(TipoMonstruo tipo) {
        this.tipo = tipo;
    }

    public void atacar(Mago mago) {
        mago.setVida(mago.getVida() - this.fuerza);
    }
}
