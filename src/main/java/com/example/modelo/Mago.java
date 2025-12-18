package com.example.modelo;



import jakarta.persistence.*;

@Entity
@Table(name = "Magos")
public class Mago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    private Integer vida;
    private Integer nivelMagia;


    public Mago() {
    }

    public Mago(String nombre, Integer vida, Integer nivelMagia) {
        this.nombre = nombre;
        this.vida = vida;
        this.nivelMagia = nivelMagia;
    }

    public Integer getId() {
        return id;
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

    public Integer getNivelMagia() {
        return nivelMagia;
    }

    public void setNivelMagia(Integer nivelMagia) {
        if (nivelMagia < 0) {
            this.nivelMagia = 0;
        } else {
            this.nivelMagia = nivelMagia;
        }
    }


    public void lanzarHechizo(Monstruo monstruo) {
        monstruo.setVida(monstruo.getVida() - this.nivelMagia);
    }

    public void addHechizo(Hechizo hechizoNuevo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addHechizo'");
    }


}