package com.example.modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "Bosque")
public class Bosque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Integer nivelPeligro;
    @OneToOne
    private Monstruo monstruoJefe;
    @OneToMany(targetEntity = Monstruo.class)
    private List<Monstruo> monstruos = new ArrayList<>();

    public Bosque() {
    }

    public Bosque(String nombre, Integer nivelPeligro, Monstruo monstruoJefe) {
        this.nombre = nombre;
        this.nivelPeligro = nivelPeligro;
        this.monstruoJefe = monstruoJefe;
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

    public Integer getNivelPeligro() {
        return nivelPeligro;
    }

    public void setNivelPeligro(Integer nivelPeligro) {
        this.nivelPeligro = nivelPeligro;
    }

    public Monstruo getMonstruoJefe() {
        return monstruoJefe;
    }

    public void setMonstruoJefe(Monstruo monstruoJefe) {
        this.monstruoJefe = monstruoJefe;
    }

    public void mostrarJefe() {
        Monstruo jefe = getMonstruoJefe();
        if (jefe != null) {
            System.out.println("El jefe del bosque " + this.nombre + " es el monstruo " + jefe.getNombre());
        } else {
            System.out.println("El bosque " + this.nombre + " no tiene jefe asignado.");
        }
    }

    public void cambiarJefe(Monstruo nuevoJefe) {
        setMonstruoJefe(nuevoJefe);
    }
}