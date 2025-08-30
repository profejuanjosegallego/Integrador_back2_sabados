package com.example.FrankySabado.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tablajuanjosevilla")

public class TablaJuanJoseVilla {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre", length = 50, nullable = false, unique = false)
    private String nombre;
    @Column(name = "direccion", length = 50, nullable = false, unique = false)
    private String direccion;

    @OneToOne
    @JoinColumn(name="fk_usuario", referencedColumnName = "id")
    @JsonManagedReference(value="relacionjuanjoseusuario")
    private Usuario usuario;


    public TablaJuanJoseVilla() {
    }

    public TablaJuanJoseVilla(Integer id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
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
