package com.example.FrankySabado.modelos;

import jakarta.persistence.*;

@Entity
public class TablaValentina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(length = 50, nullable = false)
    private String nombre;

    @Column(length = 50, unique = true)
    private String direccion;

    public TablaValentina() {
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public TablaValentina(Integer id, String nombre, String direccion) {
        Id = id;
        this.nombre = nombre;
        this.direccion = direccion;


    }
}