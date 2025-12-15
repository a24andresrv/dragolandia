package com.example.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "Dragon")
public class Dragon {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Integer intensidadFuego;
    private Integer resistencia;
    @OneToOne
    private Bosque bosque;

    public Dragon() {
    }

    public Dragon(String nombre, Integer intensidadFuego, Integer resistencia) {
        this.nombre = nombre;
        this.intensidadFuego = intensidadFuego;
        this.resistencia = resistencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIntensidadFuego() {
        return intensidadFuego;
    }

    public void setIntensidadFuego(Integer intensidadFuego) {
        this.intensidadFuego = intensidadFuego;
    }

    public Integer getResistencia() {
        return resistencia;
    }

    public void setResistencia(Integer resistencia) {
        this.resistencia = resistencia;
    }

    public void exhalar(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - this.intensidadFuego);
    }
}
