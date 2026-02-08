package com.example.FrankySabado.modelos;

import com.example.FrankySabado.ayudas.Nivel;
import com.example.FrankySabado.ayudas.Tipo;
import jakarta.persistence.*;

@Entity
@Table(name = "habilidad")
public class Habilidad {

    @Column(name = "nombre",nullable = false,unique = false)
    private String nombre;

    @Column(name = "nivel",nullable = false)
    @Enumerated(EnumType.STRING)
    private Nivel nivel;

    @Column(name = "tipo",nullable = false,unique = true)
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    //relacionar con PerfilEstudiante ManyToOne...
    //Pendiente por merge PerfilEstudiante ... para poder relacionar

    public Habilidad() {
    }

    public Habilidad(String nombre, Nivel nivel, Tipo tipo) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Nivel getNivel() {
        return nivel;
    }

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
