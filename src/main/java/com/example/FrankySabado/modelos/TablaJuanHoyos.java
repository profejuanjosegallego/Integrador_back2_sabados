package com.example.FrankySabado.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name="TablaJuanHoyos")
public class TablaJuanHoyos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name="nombre",nullable = false,unique = false)
    private String nombre;
    @Column(name="direccion",nullable = false,unique = false)
    private String direccion;
    @OneToOne(mappedBy = "tablaBrando")
    @JsonBackReference(value = "relacionbrandojuanes")
    private TablaBrando tablaBrando;

    public TablaJuanHoyos() {
    }

    public TablaJuanHoyos(Integer id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
