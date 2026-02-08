package com.example.FrankySabado.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "maikyrivera")


public class TablaMaikyRivera {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = false)
    private String nombre;

    @Column(name = "direccion", nullable = false, unique = false)
    private String direccion;
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private String juandavid;

    public TablaMaikyRivera() {
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
